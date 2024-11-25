package edu.dao.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import oracle.jdbc.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.struts2.ServletActionContext;

import com.cache.dto.Result;

import edu.action.application.SendOApplication;
import edu.dto.IpAddressDTO;
import edu.dto.applicant.ResultDTO;
import edu.dto.application.ApplicantDTO;
import edu.dto.application.ChoiceDTO;
import edu.dto.application.CollegeEligibilityDTO;
import edu.dto.application.ResponseDTO;
import edu.helpers.SmsSender;
import edu.utils.connection.ConnectionManager;


public class ApplicationDAO {

	@SuppressWarnings("unchecked")
	public ResponseDTO validateApplication(ApplicantDTO applicant,List<ChoiceDTO> choiceList,String type){			
		ResponseDTO response =new ResponseDTO();
		Set<Integer> prioritySet = new HashSet<Integer>();
		SscDataDAO sscDAO=new SscDataDAO();
		response.setStatus("VALID");
		ApplicantDTO applicantOriginalData=null;
		if(type.equalsIgnoreCase("NEW"))
			applicantOriginalData=sscDAO.getApplication(applicant.getSsc_info().getRoll(), applicant.getSsc_info().getBoard_id(), applicant.getSsc_info().getPassing_year(),applicant.getSsc_info().getReg_no());
		else
			applicantOriginalData=applicant;
		
		if(type.equalsIgnoreCase("NEW")){
			if(applicantOriginalData==null){
				response.setStatus("INVALID");
				response.setMessage("Invalid SSC Information Found.");
				return response;
			}
			else if(isAlreadyApplied(applicantOriginalData.getApplication_id())==true){
				response.setStatus("INVALID");
				response.setMessage("Sorry, you have already applied. <br/><br/>Please login to your account with Application No and Password. There you may apply for more colleges.");
				return response;
			}
			else if(isSscInfoManipulated(applicant,applicantOriginalData)){
				response.setStatus("INVALID");
				response.setMessage("Sorry, you have manipulated your SSC basic information. We can't accept your application.");
				return response;
			}
		}
		//Fetch actual application Information from Database and cross it against the submitted values
				
		HashMap<String, CollegeEligibilityDTO> eligibilityMap=(HashMap<String, CollegeEligibilityDTO>)ServletActionContext.getServletContext().getAttribute("ELIGIBILITY_MAP");
		Set<String> groupSet=(Set<String>)ServletActionContext.getServletContext().getAttribute("GROUP_SET");
		
		StringBuilder key=null;
		StringBuilder message=new StringBuilder();
		
		
		for(ChoiceDTO choice:choiceList)
		{
			try{
				int p=Integer.valueOf(choice.getPriority());
				if(p!=0 && p<=choiceList.size())
					prioritySet.add(p);
			}
			catch(Exception ex){
				response.setStatus("INVALID");
				response.setMessage("Prioirty is not correct.");
				return response;
			}
			
			if(choice.getVia()==null || choice.getVia().equalsIgnoreCase("W") || choice.getVia().equalsIgnoreCase("R")){
				
			
			if(!groupSet.contains(new String(applicant.getSsc_info().getGroup_id()+"|"+choice.getGroup_id()))){
				
				response.setStatus("INVALID");
				response.setMessage("You are not eligible in group criteria for priority number "+choice.getPriority());
				return response;
			}
			
			key=new StringBuilder(choice.getEiin());
			key.append(choice.getShift_id()).append(choice.getVersion_id()).append(choice.getGroup_id()).append(applicant.getSsc_info().getGender_id());
			CollegeEligibilityDTO eligibility=eligibilityMap.get(key.toString());
			
			if(eligibility==null){
				key=new StringBuilder(choice.getEiin());
				key.append(choice.getShift_id()).append(choice.getVersion_id()).append(choice.getGroup_id()).append("C");
				eligibility=eligibilityMap.get(key.toString());
			}
			
			//TODO : Need to place group checking here
			if(eligibility!=null){
				
			    if(choice.getSpecial_quota().equalsIgnoreCase("Y") && eligibility.getSq_yn().equalsIgnoreCase("N")){
			    	
			    	message.append("Invalid Seical Quota Selection");
			    	response.setStatus("INVALID");
			    }
			    else if(choice.getSpecial_quota().equalsIgnoreCase("Y") && eligibility.getSq_yn().equalsIgnoreCase("Y")){
			    	if(applicant.getSsc_info().getGpa()<eligibility.getSq_gpa()){			    		
			    		if(applicant.getSsc_info().getEiin()!=null && applicant.getSsc_info().getEiin().equalsIgnoreCase(choice.getEiin())){
			    			if(applicant.getSsc_info().getGpa()<eligibility.getOwn_gpa()){
			    				message.append("Special Quota GPA does not match the critaria.");
			    				response.setStatus("INVALID");
			    			}
			    		}
			    		
			    	}
			    	
			    }
			    else if(applicant.getSsc_info().getEiin()!=null && applicant.getSsc_info().getEiin().equalsIgnoreCase(choice.getEiin())){
					if(applicant.getSsc_info().getGpa()<eligibility.getOwn_gpa()){
						message.append("Invalid Choice : ")
							   .append("Priority Number :"+choice.getPriority()).append(", ")
							   .append("College EIIN :"+choice.getEiin()).append(" ");
							   //.append("College Shift :"+choice.getShift_id())
							   //.append("College Version :"+choice.getVersion_id())
							   //.append("College Group :"+choice.getGroup_id());
						response.setStatus("INVALID");
					}
				}
				else{
					if(applicant.getSsc_info().getGpa()<eligibility.getMinimum_gpa()){
						message.append("Invalid Choice : ")
							   .append("Priority Number :"+choice.getPriority()).append(", ")
							   .append("College EIIN :"+choice.getEiin()).append(" ");						
							   //.append("College Shift :"+choice.getShift_id())
							   //.append("College Version :"+choice.getVersion_id())
							   //.append("College Group :"+choice.getGroup_id());
						response.setStatus("INVALID");
					}
				}
			}
			else{
				message.append("Invalid Choice Selection.")
					   .append("Priority Number :"+choice.getPriority()).append(", ")
					   .append("College EIIN :"+choice.getEiin()).append(" ");
				response.setStatus("INVALID");
			}
				
		  } //End of web choice checking...	
		}
		if(prioritySet.size()!=choiceList.size()){
			response.setMessage("Prioirty is not correct.");
			response.setStatus("INVALID");
			return response;
		}
		Set<String> choiceEiinSet = new HashSet<String>();
		for(int i=0;i<choiceList.size();i++){
			choiceEiinSet.add(choiceList.get(i).getEiin());
		}
//		if(choiceEiinSet.size()<5){
//			response.setMessage("You should select at least 5(Five) different colleges for Release Slip Submission.");
//			response.setStatus("INVALID");
//			return response;
//		}
		if(choiceEiinSet.size()>5){
			response.setMessage("You can select maximum 5(Five) different colleges.");
			response.setStatus("INVALID");
			return response;
		}
		
		response.setMessage(message.toString());
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseDTO validateApplicationNew(ApplicantDTO applicant,List<ChoiceDTO> choiceList,String type){			
		ResponseDTO response =new ResponseDTO();
		Set<Integer> prioritySet = new HashSet<Integer>();
		SscDataDAO sscDAO=new SscDataDAO();
		response.setStatus("VALID");
		ApplicantDTO applicantOriginalData=null;
		if(type.equalsIgnoreCase("NEW"))
			applicantOriginalData=sscDAO.getApplicationNew(applicant.getSsc_info().getRoll(), applicant.getSsc_info().getBoard_id(), applicant.getSsc_info().getPassing_year(),applicant.getSsc_info().getReg_no(),applicant.getSsc_info().getMother_name());
		else
			applicantOriginalData=applicant;
		
		if(type.equalsIgnoreCase("NEW")){
			if(applicantOriginalData==null){
				response.setStatus("INVALID");
				response.setMessage("Invalid SSC Information Found.");
				return response;
			}
			else if(isAlreadyApplied(applicantOriginalData.getApplication_id())==true){
				response.setStatus("INVALID");
				response.setMessage("Sorry, you have already applied. <br/><br/>Please login to your account with Application No and Password. There you may apply for more colleges.");
				return response;
			}
			else if(isSscInfoManipulated(applicant,applicantOriginalData)){
				response.setStatus("INVALID");
				response.setMessage("Sorry, you have manipulated your SSC basic information. We can't accept your application.");
				return response;
			}
		}
		//Fetch actual application Information from Database and cross it against the submitted values
				
		HashMap<String, CollegeEligibilityDTO> eligibilityMap=(HashMap<String, CollegeEligibilityDTO>)ServletActionContext.getServletContext().getAttribute("ELIGIBILITY_MAP");
		Set<String> groupSet=(Set<String>)ServletActionContext.getServletContext().getAttribute("GROUP_SET");
		
		StringBuilder key=null;
		StringBuilder message=new StringBuilder();
		
		
		for(ChoiceDTO choice:choiceList)
		{
			try{
				int p=Integer.valueOf(choice.getPriority());
				if(p!=0 && p<=choiceList.size())
					prioritySet.add(p);
			}
			catch(Exception ex){
				response.setStatus("INVALID");
				response.setMessage("Prioirty is not correct.");
				return response;
			}
			
			
			if(!groupSet.contains(new String(applicant.getSsc_info().getGroup_id()+"|"+choice.getGroup_id()))){
				
				response.setStatus("INVALID");
				response.setMessage("You are not eligible in group criteria for priority number "+choice.getPriority());
				return response;
			}
			
			key=new StringBuilder(choice.getEiin());
			key.append(choice.getShift_id()).append(choice.getVersion_id()).append(choice.getGroup_id()).append(applicant.getSsc_info().getGender_id());
			CollegeEligibilityDTO eligibility=eligibilityMap.get(key.toString());

			if(eligibility==null){
				key=new StringBuilder(choice.getEiin());
				key.append(choice.getShift_id()).append(choice.getVersion_id()).append(choice.getGroup_id()).append("C");
				eligibility=eligibilityMap.get(key.toString());
			}
			
			int avaiable_seat=0;
			//TODO : Need to place group checking here
			if(eligibility!=null){
				try{avaiable_seat=eligibility.getAvailable_seat();}
				catch(Exception ex){ex.printStackTrace();avaiable_seat=0;} 
//				if(avaiable_seat<=0){
//					message.append("Available Seat is Zero : ")
//					   .append("Priority Number :"+choice.getPriority()).append(", ")
//					   .append("College EIIN :"+choice.getEiin()).append(" ");
//				
//			    	response.setStatus("INVALID");
//				}
//				else 
					if(choice.getSpecial_quota().equalsIgnoreCase("Y") && eligibility.getSq_yn().equalsIgnoreCase("N")){
			    	
			    	message.append("Invalid Speical Quota Selection");
			    	response.setStatus("INVALID");
			    }
			    else if(choice.getSpecial_quota().equalsIgnoreCase("Y") && eligibility.getSq_yn().equalsIgnoreCase("Y")){
			    	if(applicant.getSsc_info().getGpa()<eligibility.getSq_gpa()){			    		
			    		if(applicant.getSsc_info().getEiin()!=null && applicant.getSsc_info().getEiin().equalsIgnoreCase(choice.getEiin())){
			    			if(applicant.getSsc_info().getGpa()<eligibility.getOwn_gpa()){
			    				message.append("Special Quota GPA does not match the critaria.");
			    				response.setStatus("INVALID");
			    			}
			    		}
			    		
			    	}
			    	
			    }
			    else if(applicant.getSsc_info().getEiin()!=null && applicant.getSsc_info().getEiin().equalsIgnoreCase(choice.getEiin())){
					if(applicant.getSsc_info().getGpa()<eligibility.getOwn_gpa()){
						message.append("Invalid Choice : ")
							   .append("Priority Number :"+choice.getPriority()).append(", ")
							   .append("College EIIN :"+choice.getEiin()).append(" ");
							   //.append("College Shift :"+choice.getShift_id())
							   //.append("College Version :"+choice.getVersion_id())
							   //.append("College Group :"+choice.getGroup_id());
						response.setStatus("INVALID");
					}
				}
				else{
					if(applicant.getSsc_info().getGpa()<eligibility.getMinimum_gpa()){
						message.append("Invalid Choice : ")
							   .append("Priority Number :"+choice.getPriority()).append(", ")
							   .append("College EIIN :"+choice.getEiin()).append(" ");						
							   //.append("College Shift :"+choice.getShift_id())
							   //.append("College Version :"+choice.getVersion_id())
							   //.append("College Group :"+choice.getGroup_id());
						response.setStatus("INVALID");
					}
				}
			}
			else{
				message.append("Invalid Choice Selection.")
					   .append("Priority Number :"+choice.getPriority()).append(", ")
					   .append("College EIIN :"+choice.getEiin()).append(" ");
				response.setStatus("INVALID");
			}
				
		}
		if(prioritySet.size()!=choiceList.size()){
			response.setMessage("Prioirty is not correct.");
			response.setStatus("INVALID");
			return response;
		}
		
		Set<String> choiceEiinSet = new HashSet<String>();
		for(int i=0;i<choiceList.size();i++){
			choiceEiinSet.add(choiceList.get(i).getEiin());
		}
		if(choiceEiinSet.size()<5){
			response.setMessage("You should select at least 5(Five) different colleges.");
			response.setStatus("INVALID");
			return response;
		}
		if(choiceEiinSet.size()>10){
			response.setMessage("You can select maximum 10(Ten) different colleges.");
			response.setStatus("INVALID");
			return response;
		}
		
		response.setMessage(message.toString());
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseDTO validateApplication_TT(ApplicantDTO applicant, List<ChoiceDTO> choiceList, String type){			
		ResponseDTO response =new ResponseDTO();
		Set<Integer> prioritySet = new HashSet<Integer>();
		SscDataDAO sscDAO=new SscDataDAO();
		response.setStatus("VALID");
		ApplicantDTO applicantOriginalData=null;
		if(type.equalsIgnoreCase("NEW"))
			applicantOriginalData=sscDAO.getApplication_TT(applicant.getSsc_info().getRoll(), applicant.getSsc_info().getBoard_id(), applicant.getSsc_info().getPassing_year(),applicant.getSsc_info().getReg_no());
		else
			applicantOriginalData=applicant;
		
		applicant = applicantOriginalData;
		if(type.equalsIgnoreCase("NEW")){
			if(applicantOriginalData==null){
				System.out.println("Invalid SSC Information Found.");
				response.setStatus("INVALID");
				response.setMessage("Invalid SSC Information Found.");
				return response;
			}
		}
		else
		{
			if(applicantOriginalData.getApplied().equalsIgnoreCase("Y")){
				System.out.println("Already Applied");
				response.setStatus("INVALID");
				response.setMessage("Already Applied.");
				return response;
			}
		}
		//Fetch actual application Information from Database and cross it against the submitted values
				
		HashMap<String, CollegeEligibilityDTO> eligibilityMap=(HashMap<String, CollegeEligibilityDTO>)ServletActionContext.getServletContext().getAttribute("ELIGIBILITY_MAP");
		Set<String> groupSet=(Set<String>)ServletActionContext.getServletContext().getAttribute("GROUP_SET");
		
		StringBuilder key=null;
		StringBuilder message=new StringBuilder();
		
		int choiceNumber = 0;
		for(ChoiceDTO choice:choiceList)
		{
			choiceNumber++;
			try{
				int p=Integer.valueOf(choice.getPriority());
				if(p!=0 && p<=choiceList.size())
					prioritySet.add(p);
			}
			catch(Exception ex){
				
				response.setStatus("INVALID");
				response.setMessage("Prioirty is not correct.");
				ex.printStackTrace();
				return response;
			}
//			if(choice.getEiin().equalsIgnoreCase("114832"))
//				choice.setEiin("107977");
			if(choice.getVia()==null || choice.getVia().equalsIgnoreCase("W") || choice.getVia().equalsIgnoreCase("R")){
//				
//			
			if(!groupSet.contains(new String(applicant.getSsc_info().getGroup_id()+"|"+choice.getGroup_id()))){	
				System.out.println("##################################################");
//				System.out.println("roll="+applicant.getSsc_info().getRoll());
//				System.out.println("board="+applicant.getSsc_info().getBoard_id());
//				System.out.println("year="+applicant.getSsc_info().getPassing_year());
				System.out.println("ssc_group="+applicant.getSsc_info().getGroup_id());
				System.out.println("app_group="+choice.getGroup_id());
				System.out.println("##################################################");
				System.out.println("You are not eligible in group criteria for priority number "+choice.getPriority());
				response.setStatus("INVALID");
				response.setMessage("You are not eligible in group criteria for priority number "+choice.getPriority());
				return response;
			}
//			
			key=new StringBuilder(choice.getEiin());
			key.append(choice.getShift_id()).append(choice.getVersion_id()).append(choice.getGroup_id()).append(applicant.getSsc_info().getGender_id());
			CollegeEligibilityDTO eligibility=eligibilityMap.get(key.toString());
			
			if(eligibility==null){
				key=new StringBuilder(choice.getEiin());
				key.append(choice.getShift_id()).append(choice.getVersion_id()).append(choice.getGroup_id()).append("C");
				eligibility=eligibilityMap.get(key.toString());
			}
//			
//			//TODO : Need to place group checking here
			if(eligibility!=null){
//				
			    if(choice.getSpecial_quota().equalsIgnoreCase("Y") && eligibility.getSq_yn().equalsIgnoreCase("N")){
			    	System.out.println("Invalid Seical Quota Selection");
			    	message.append("Invalid Seical Quota Selection");
			    	response.setStatus("INVALID");
			    }
			    else if(choice.getSpecial_quota().equalsIgnoreCase("Y") && eligibility.getSq_yn().equalsIgnoreCase("Y")){
			    	if(applicant.getSsc_info().getGpa()<eligibility.getSq_gpa()){			    		
			    		if(applicant.getSsc_info().getEiin()!=null && applicant.getSsc_info().getEiin().equalsIgnoreCase(choice.getEiin())){
			    			if(applicant.getSsc_info().getGpa()<eligibility.getOwn_gpa()){
			    				System.out.println("Special Quota GPA does not match the critaria.");
			    				message.append("Special Quota GPA does not match the critaria.");
			    				response.setStatus("INVALID");
			    			}
			    		}
			    		
			    	}
			    	
			    }
			    else if(applicant.getSsc_info().getEiin()!=null && applicant.getSsc_info().getEiin().equalsIgnoreCase(choice.getEiin())){
					if(applicant.getSsc_info().getGpa()<eligibility.getOwn_gpa()){
						message.append("Invalid Choice : ")
							   .append("Serial Number :"+choiceNumber).append(", ")
							   .append("College EIIN :"+choice.getEiin()).append(" ");
							   //.append("College Shift :"+choice.getShift_id())
							   //.append("College Version :"+choice.getVersion_id())
							   //.append("College Group :"+choice.getGroup_id());
						System.out.println("Invalid Choice.");
						response.setStatus("INVALID");
					}
				}
				else{
					if(applicant.getSsc_info().getGpa()<eligibility.getMinimum_gpa()){
						message.append("Invalid Choice : ")
							   .append("Serial Number :"+choiceNumber).append(", ")
							   .append("College EIIN :"+choice.getEiin()).append(" ");						
							   //.append("College Shift :"+choice.getShift_id())
							   //.append("College Version :"+choice.getVersion_id())
							   //.append("College Group :"+choice.getGroup_id());
						System.out.println("Invalid Choice.");
						response.setStatus("INVALID");
					}
				}
			}
			else{
				message.append("Invalid Choice Selection.")
					   .append("Serial Number :"+choiceNumber).append(", ")
					   .append("College EIIN :"+choice.getEiin()).append(" ");
				System.out.println("Invalid Choice..");
				response.setStatus("INVALID");
			}
//				
		  } //End of web choice checking...	
		}
		if(prioritySet.size()!=choiceList.size()){
			response.setMessage("Prioirty is not correct.");
			response.setStatus("INVALID");
			return response;
		}
		
		
		
		response.setMessage(message.toString());
		return response;
	}
	
	
	public boolean isAlreadyApplied(String application_id){
		if(application_id!=null && !application_id.equalsIgnoreCase(""))
			return true;
		else
			return false;
	}
	
	public boolean isSscInfoManipulated(ApplicantDTO submittedApplicant,ApplicantDTO originalApplicant){

		if(submittedApplicant.getSsc_info().getGpa()!=originalApplicant.getSsc_info().getGpa())
			return true;
		else if(!submittedApplicant.getSsc_info().getGender_id().equalsIgnoreCase(originalApplicant.getSsc_info().getGender_id()))
			return true;
		else if(!submittedApplicant.getSsc_info().getEiin().equalsIgnoreCase(originalApplicant.getSsc_info().getEiin()))
			return true;
		else if(!submittedApplicant.getSsc_info().getGroup_id().equalsIgnoreCase(originalApplicant.getSsc_info().getGroup_id()))
			return true;
		else
			return false;
	}
	
	public ResponseDTO saveApplication(ApplicantDTO applicant,List<ChoiceDTO> choiceList,IpAddressDTO ipAddress)
	{
		ResponseDTO response =new ResponseDTO();
		Connection conn = ConnectionManager.getWriteConnection();
		OracleCallableStatement stmt = null;
		
		String[] eiinList = new String[choiceList.size()];
		String[] shiftList = new String[choiceList.size()];
		String[] versionList = new String[choiceList.size()];
		String[] groupList = new String[choiceList.size()];
		String[] sQuotaList = new String[choiceList.size()];
		String[] priorityList	 = new String[choiceList.size()];

		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			
			eiinList[i] 	  		=  choice.getEiin();
			shiftList[i]  	=  choice.getShift_id();
			versionList[i] 	=  choice.getVersion_id();
			groupList[i]=  choice.getGroup_id();
			sQuotaList[i]= choice.getSpecial_quota();
			priorityList[i] 		  	=  choice.getPriority();
		}
		
		try {
       	 
	    	ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
			
			ARRAY inputEiin=new ARRAY(arrString,conn,eiinList);
			ARRAY inputShift=new ARRAY(arrString,conn,shiftList);
			ARRAY inputVersion=new ARRAY(arrString,conn,versionList);
			ARRAY inputGroup=new ARRAY(arrString,conn,groupList);
			ARRAY inputSQuota=new ARRAY(arrString,conn,sQuotaList);
			ARRAY inputPriority=new ARRAY(arrString,conn,priorityList);
			
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call SaveApplication(?,?,?,?,?,?," +
            																		 "?,?,?,?,?,?, " +
            																		 "?,?,?,?,?,?,?,?)  }");
            
            stmt.setString(1, applicant.getSsc_info().getRoll());
            stmt.setString(2, applicant.getSsc_info().getBoard_id());
            stmt.setString(3, applicant.getSsc_info().getPassing_year());
            stmt.setString(4, applicant.getApplication_info().getMobile_number());
            stmt.setString(5, applicant.getApplication_info().getQuota_yn());
            stmt.setString(6, applicant.getApplication_info().getQuota_ff()==null?"N":applicant.getApplication_info().getQuota_ff());
            stmt.setString(7, applicant.getApplication_info().getQuota_eq()==null?"N":applicant.getApplication_info().getQuota_eq());
            stmt.setARRAY(8, inputEiin);
            stmt.setARRAY(9, inputShift);
            stmt.setARRAY(10, inputVersion);
            stmt.setARRAY(11, inputGroup);
            stmt.setARRAY(12, inputSQuota);
            stmt.setARRAY(13, inputPriority);
            
            stmt.setString(14, ipAddress.getxForward());
            stmt.setString(15, ipAddress.getVia());
            stmt.setString(16, ipAddress.getRemoteAddress());
            
            stmt.registerOutParameter(17, java.sql.Types.INTEGER); 
            stmt.registerOutParameter(18, java.sql.Types.VARCHAR); 
            stmt.registerOutParameter(19, java.sql.Types.VARCHAR); 
            stmt.registerOutParameter(20, java.sql.Types.VARCHAR); 

            
            stmt.executeUpdate();
            if(stmt.getInt(17)==1){
            	response.setStatus("OK");
            	String appid = stmt.getString(19);
            	String pass = stmt.getString(20);
            	
            	SendOApplication soa = new SendOApplication();
            	soa.setApplicant(applicant);
            	soa.setApp_id(appid);
            	soa.setEiinList(eiinList);
            	soa.setGroupList(groupList);
            	soa.setShiftList(shiftList);
            	soa.setVersionList(versionList);
            	soa.setPriorityList(priorityList);
            	soa.setOp("NEW");
            	Thread thread = new Thread(soa);
    			thread.start();
    			

    			
//				SmsSender smsSender=new SmsSender();
//				smsSender.setMobile(applicant.getApplication_info().getConfirm_mobile_number());
//				smsSender.setAppid(appid);
//				smsSender.setPass(pass);
//				smsSender.setName(applicant.getSsc_info().getStudent_name());
//				smsSender.setOperation("smsWPayment");
//				thread = new Thread(smsSender);
//				thread.start();
            	
//            	sendOapplication(stmt.getString(16),applicant.getSsc_info().getStudent_name() ,applicant.getSsc_info().getGender_id(),"quota",
//            			applicant.getSsc_info().getBoard_id(),applicant.getSsc_info().getRoll(),
//            			applicant.getSsc_info().getPassing_year(),"regno",applicant.getApplication_info().getConfirm_mobile_number(),
//            			StringUtils.join(eiinList , "|"),StringUtils.join(groupList , "|"),StringUtils.join(shiftList , "|"),StringUtils.join(versionList , "|"),
//            			StringUtils.join(priorityList , "|"),String.valueOf(priorityList.length));
				
            	response.setApplication_id(stmt.getString(19));
            	response.setPassword(stmt.getString(20));
            	response.setApplication_id(appid);
            	response.setPassword(pass);
            	response.setSscInfo(applicant.getSsc_info());
            }
            else{
            	System.out.print("ERROR--SaveApplication proc");
            	response.setStatus("ERROR");
            	response.setMessage(stmt.getString(18));
            }
		} 
		catch (Exception e){e.printStackTrace();
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
        
        return response;
        

	}
	public ResponseDTO saveApplication_TT(ApplicantDTO applicant,List<ChoiceDTO> choiceList,IpAddressDTO ipAddress,String scode)
	{
		ResponseDTO response =new ResponseDTO();
		Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		

		
		
		String[] eiinList = new String[choiceList.size()];
		String[] shiftList = new String[choiceList.size()];
		String[] versionList = new String[choiceList.size()];
		String[] groupList = new String[choiceList.size()];
		String[] sQuotaList = new String[choiceList.size()];
		String[] gbQuotaList = new String[choiceList.size()];
		String[] priorityList	 = new String[choiceList.size()];

		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			
			eiinList[i] 	  		=  choice.getEiin();
			shiftList[i]  	=  choice.getShift_id();
			versionList[i] 	=  choice.getVersion_id();
			groupList[i]=  choice.getGroup_id();
			sQuotaList[i]= choice.getSpecial_quota();
			gbQuotaList[i]= choice.getGb_quota();
			priorityList[i] 		  	=  choice.getPriority();
		}
		java.util.Set result = new java.util.HashSet(java.util.Arrays.asList(eiinList));
		//System.out.println("Total unique college : "+result.size());
	    //System.out.println("Total unique college list : "+result);
	    
	    if(result.size()>10)
	      {  
	    	  response.setStatus("ERROR");
			  response.setMessage("You can not select more than ten unique colleges....!!!");  
			  System.out.println("You can not select more than ten unique colleges....!!!");
	      }
	      
	      else
	      {
		try {
       	 
	    	ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
	    	
			
			ARRAY inputEiin=new ARRAY(arrString,conn,eiinList);
			ARRAY inputShift=new ARRAY(arrString,conn,shiftList);
			ARRAY inputVersion=new ARRAY(arrString,conn,versionList);
			ARRAY inputGroup=new ARRAY(arrString,conn,groupList);
			ARRAY inputSQuota=new ARRAY(arrString,conn,sQuotaList);
			ARRAY inputGBQuota=new ARRAY(arrString,conn,gbQuotaList);
			ARRAY inputPriority=new ARRAY(arrString,conn,priorityList);
			
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call SaveApplication_TT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");//,?," +
//            																		 "?,?,?, " +
//            																		 "?,?,?" +
//            																		 "?,?,?,?,?,?)  }");
            int parameterIndex = 1;
            
            stmt.setString(parameterIndex++, applicant.getSsc_info().getRoll());
            stmt.setInt(parameterIndex++, Integer.parseInt(applicant.getSsc_info().getBoard_id()));
            stmt.setInt(parameterIndex++, Integer.parseInt(applicant.getSsc_info().getPassing_year()));
            stmt.setString(parameterIndex++, applicant.getSsc_info().getReg_no());
//            
            stmt.setString(parameterIndex++, applicant.getApplication_info().getMobile_number());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_ff());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_eq());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_bksp());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_expatriate());
//            
            stmt.setARRAY(parameterIndex++, inputEiin);
            stmt.setARRAY(parameterIndex++, inputShift);
            stmt.setARRAY(parameterIndex++, inputVersion);            
            stmt.setARRAY(parameterIndex++, inputGroup);
            stmt.setARRAY(parameterIndex++, inputSQuota);
            stmt.setARRAY(parameterIndex++, inputGBQuota);
            stmt.setARRAY(parameterIndex++, inputPriority);
//            
            stmt.setString(parameterIndex++, applicant.getSsc_info().getBoard_name());
            stmt.setString(parameterIndex++, applicant.getSsc_info().getStudent_name());
            stmt.setString(parameterIndex++, applicant.getSsc_info().getFather_name());
            stmt.setString(parameterIndex++, applicant.getSsc_info().getMother_name());
            stmt.setString(parameterIndex++, applicant.getSsc_info().getGender_id());
//
            stmt.setString(parameterIndex++, ipAddress.getxForward());
            stmt.setString(parameterIndex++, ipAddress.getVia());            
            stmt.setString(parameterIndex++, ipAddress.getRemoteAddress());
            stmt.registerOutParameter(parameterIndex++, java.sql.Types.VARCHAR);
            
            stmt.registerOutParameter(parameterIndex++, java.sql.Types.INTEGER); 
            stmt.registerOutParameter(parameterIndex++, java.sql.Types.VARCHAR); 
            stmt.registerOutParameter(parameterIndex++, java.sql.Types.VARCHAR); 
            stmt.registerOutParameter(parameterIndex++, java.sql.Types.VARCHAR);
            
            
            stmt.setString(parameterIndex++, applicant.getApplication_id());
            stmt.setString(parameterIndex++, applicant.getSsc_info().getEiin());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getNid_number());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getNidholder());
            stmt.setString(parameterIndex++, applicant.getSsc_info().getBirth_date());
            // Added 6 parameter
            
            
            
            
            stmt.executeUpdate();
            if( stmt.getInt(26)==1){//17
            	response.setStatus("OK");
            	
            	String outscode = stmt.getString(25);
            	String appid = stmt.getString(27);//19
            	String pass = stmt.getString(28);//20
            	
            	//http://114.130.64.35:9999/CAD/buet2TTOapplication.php
            	
//            	SendOApplication soa = new SendOApplication();
//            	soa.setApplicant(applicant);
//            	soa.setApp_id(appid);
//            	soa.setEiinList(eiinList);
//            	soa.setGroupList(groupList);
//            	soa.setShiftList(shiftList);
//            	soa.setVersionList(versionList);
//            	soa.setPriorityList(priorityList);
//            	soa.setOp("NEW");
//            	Thread thread = new Thread(soa);
//    			thread.start();
    			
            	if(scode!=null || !scode.equalsIgnoreCase(""))
            	{
	            	java.util.Date now = new java.util.Date();
	            	SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
					SmsSender smsSender=new SmsSender();
					smsSender.setMobile(applicant.getApplication_info().getMobile_number().substring(applicant.getApplication_info().getMobile_number().length()-11));
					String text ="Your ("+applicant.getSsc_info().getRoll()+") online application has been submitted successfully and your security code is "+outscode+" - Board Authority.";					
					smsSender.setText(text);
					smsSender.setOperation("sms");
					Thread thread = new Thread(smsSender);
					thread.start();
            	}
            	
//            	sendOapplication(stmt.getString(16),applicant.getSsc_info().getStudent_name() ,applicant.getSsc_info().getGender_id(),"quota",
//            			applicant.getSsc_info().getBoard_id(),applicant.getSsc_info().getRoll(),
//            			applicant.getSsc_info().getPassing_year(),"regno",applicant.getApplication_info().getConfirm_mobile_number(),
//            			StringUtils.join(eiinList , "|"),StringUtils.join(groupList , "|"),StringUtils.join(shiftList , "|"),StringUtils.join(versionList , "|"),
//            			StringUtils.join(priorityList , "|"),String.valueOf(priorityList.length));
				
//            	response.setApplication_id(stmt.getString(25));//19
//            	response.setPassword(stmt.getString(26));//20
            	response.setApplication_id(appid);
            	response.setPassword(pass);
            	response.setSscInfo(applicant.getSsc_info());
            	response.setApplication_info(applicant.getApplication_info());
            	response.setScode(outscode);
            	response.setNid(applicant.getApplication_info().getNid_number());
            }
            else{
            	response.setStatus("INVALID");
            	response.setMessage(stmt.getString(29));//18
            	System.out.println(stmt.getString(29));//18
            }
		} 
		catch (Exception e){e.printStackTrace();
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		finally{try{
			stmt.close();
			ConnectionManager.closeConnection(conn);
			} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
	      }
        return response;
        

	}
	
	public ResponseDTO saveApplicationEdit_TT(ApplicantDTO applicant,List<ChoiceDTO> choiceList,IpAddressDTO ipAddress,String scode, String contactno)
	{
		ResponseDTO response =new ResponseDTO();
		Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		
		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			choice.setMobile_no(applicant.getApplication_info().getMobile_number());
		}

		
		String[] eiinList = new String[choiceList.size()];
		String[] shiftList = new String[choiceList.size()];
		String[] versionList = new String[choiceList.size()];
		String[] groupList = new String[choiceList.size()];
		String[] sQuotaList = new String[choiceList.size()];
		String[] gbQuotaList = new String[choiceList.size()];
		String[] priorityList	 = new String[choiceList.size()];
		String[] appType	 = new String[choiceList.size()];
		

		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			
			eiinList[i] 	  		=  choice.getEiin();
			shiftList[i]  	=  choice.getShift_id();
			versionList[i] 	=  choice.getVersion_id();
			groupList[i]=  choice.getGroup_id();
			sQuotaList[i]= choice.getSpecial_quota();
			gbQuotaList[i]= choice.getGb_quota();
			priorityList[i] 		  	=  choice.getPriority();
			appType[i] 		  	=  choice.getApplication_type();
			
		}
		
		java.util.Set result = new java.util.HashSet(java.util.Arrays.asList(eiinList));
	    
	    if(result.size()>10)
	      {  
	    	  response.setStatus("ERROR");
			  response.setMessage("You can not select more than ten unique colleges....!!!"); 
			  System.out.println("You can not select more than ten unique colleges....!!!");
	      }
	      
	      else
	      {
		try {
       	 
			ArrayDescriptor arrString = null;
			try{
				arrString = new ArrayDescriptor("VARCHARARRAY", conn);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				conn = null;
				conn = ConnectionManager.getConnectionStatic();
			}
	    	
			
			ARRAY inputEiin=new ARRAY(arrString,conn,eiinList);
			ARRAY inputShift=new ARRAY(arrString,conn,shiftList);
			ARRAY inputVersion=new ARRAY(arrString,conn,versionList);
			ARRAY inputGroup=new ARRAY(arrString,conn,groupList);
			ARRAY inputSQuota=new ARRAY(arrString,conn,sQuotaList);
			ARRAY inputGbQuota=new ARRAY(arrString,conn,gbQuotaList);
			
			ARRAY inputPriority=new ARRAY(arrString,conn,priorityList);
			ARRAY inputApptype=new ARRAY(arrString,conn,appType);

			
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call SaveApplicationEdit_TT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");

            int parameterIndex = 1;
            
            stmt.setString(parameterIndex++, applicant.getSsc_info().getRoll());
            stmt.setInt(parameterIndex++, Integer.parseInt(applicant.getSsc_info().getBoard_id()));
            stmt.setInt(parameterIndex++, Integer.parseInt(applicant.getSsc_info().getPassing_year()));
            stmt.setString(parameterIndex++, applicant.getSsc_info().getReg_no());
//            
            stmt.setString(parameterIndex++, applicant.getApplication_info().getMobile_number());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_ff());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_eq());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_bksp());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_expatriate());
//            
            stmt.setARRAY(parameterIndex++, inputEiin);
            stmt.setARRAY(parameterIndex++, inputShift);
            stmt.setARRAY(parameterIndex++, inputVersion);            
            stmt.setARRAY(parameterIndex++, inputGroup);
            stmt.setARRAY(parameterIndex++, inputSQuota);
            stmt.setARRAY(parameterIndex++, inputGbQuota);
            
            stmt.setARRAY(parameterIndex++, inputPriority);
            stmt.setARRAY(parameterIndex++, inputApptype);
//            
            stmt.setString(parameterIndex++, applicant.getSsc_info().getBoard_name());
            stmt.setString(parameterIndex++, applicant.getSsc_info().getStudent_name());
            stmt.setString(parameterIndex++, applicant.getSsc_info().getFather_name());
            stmt.setString(parameterIndex++, applicant.getSsc_info().getMother_name());
            stmt.setString(parameterIndex++, applicant.getSsc_info().getGender_name());
//
            stmt.setString(parameterIndex++, ipAddress.getxForward());
            stmt.setString(parameterIndex++, ipAddress.getVia());            
            stmt.setString(parameterIndex++, ipAddress.getRemoteAddress());
            if(!scode.equalsIgnoreCase(""))
            	stmt.setString(parameterIndex++, scode);
            else
            	stmt.setString(parameterIndex++, null);
            
            stmt.registerOutParameter(parameterIndex++, java.sql.Types.INTEGER);
            stmt.registerOutParameter(parameterIndex++, java.sql.Types.VARCHAR);
            
            stmt.setString(parameterIndex++, applicant.getApplication_info().getNid_number());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getNidholder());
            
            // Added 6 parameter
            
            
            
            
            stmt.executeUpdate();
            if( stmt.getInt(27)==1){//17
            	response.setStatus("OK");
            	response.setMessage("OK");
            	String appid = applicant.getSsc_info().getBoard_id()+applicant.getSsc_info().getPassing_year()+applicant.getSsc_info().getRoll();//19
            			
            	response.setApplication_id(appid);
            	response.setSscInfo(applicant.getSsc_info());
            	response.setApplication_info(applicant.getApplication_info());
            }
            else{
            	response.setStatus("INVALID");
            	System.out.println(""+stmt.getString(28));
            	response.setMessage(stmt.getString(28)); 
            	if(stmt.getString(28).contains("APP_COUNT_UQ1"))
    			{
    				response.setMessage("Please edit application from first!!!");
    			}
            }
		} 
		catch (Exception e){e.printStackTrace();
			response.setStatus("INVALID");
			response.setMessage(e.getMessage());
			if(e.getMessage().contains("APP_COUNT_UQ1"))
			{
				response.setMessage("Please edit application from first!!!");
			}
			System.out.println(e.getMessage());
		}
		finally{try{
			stmt.close();
			ConnectionManager.closeConnection(conn);
			} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
	      
	      } 
        return response;
        

	}
	public ResponseDTO editApplicationSMSOnly(ApplicantDTO applicant,List<ChoiceDTO> choiceList,IpAddressDTO ipAddress,String scode, String contactno)
	{
		ResponseDTO response =new ResponseDTO();
		Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		
		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			choice.setMobile_no(applicant.getApplication_info().getMobile_number());
		}

		
		String[] eiinList = new String[choiceList.size()];
		String[] shiftList = new String[choiceList.size()];
		String[] versionList = new String[choiceList.size()];
		String[] groupList = new String[choiceList.size()];
		String[] sQuotaList = new String[choiceList.size()];
		String[] gbQuotaList = new String[choiceList.size()];
		String[] priorityList	 = new String[choiceList.size()];
		String[] appType	 = new String[choiceList.size()];
		

		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			
			eiinList[i] 	  		=  choice.getEiin();
			shiftList[i]  	=  choice.getShift_id();
			versionList[i] 	=  choice.getVersion_id();
			groupList[i]=  choice.getGroup_id();
			sQuotaList[i]= choice.getSpecial_quota();
			gbQuotaList[i]= choice.getGb_quota();
			priorityList[i] 		  	=  choice.getPriority();
			appType[i] 		  	=  choice.getApplication_type();
			
		}
		
		java.util.Set result = new java.util.HashSet(java.util.Arrays.asList(eiinList));
	    
	    if(result.size()>10)
	      {  
	    	  response.setStatus("ERROR");
			  response.setMessage("You can not select more than ten unique colleges....!!!"); 
			  System.out.println("You can not select more than ten unique colleges....!!!");
	      }
	      
	      else
	      {
		try {
       	 
			ArrayDescriptor arrString = null;
			try{
				arrString = new ArrayDescriptor("VARCHARARRAY", conn);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				conn = null;
				conn = ConnectionManager.getConnectionStatic();
			}
	    	
			
			ARRAY inputEiin=new ARRAY(arrString,conn,eiinList);
			ARRAY inputShift=new ARRAY(arrString,conn,shiftList);
			ARRAY inputVersion=new ARRAY(arrString,conn,versionList);
			ARRAY inputGroup=new ARRAY(arrString,conn,groupList);
			ARRAY inputSQuota=new ARRAY(arrString,conn,sQuotaList);
			ARRAY inputGbQuota=new ARRAY(arrString,conn,gbQuotaList);
			
			ARRAY inputPriority=new ARRAY(arrString,conn,priorityList);
			ARRAY inputApptype=new ARRAY(arrString,conn,appType);

			
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call editApplicationSMSOnly(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");

            int parameterIndex = 1;
            
            stmt.setString(parameterIndex++, applicant.getSsc_info().getRoll());
            stmt.setInt(parameterIndex++, Integer.parseInt(applicant.getSsc_info().getBoard_id()));
            stmt.setInt(parameterIndex++, Integer.parseInt(applicant.getSsc_info().getPassing_year()));
            stmt.setString(parameterIndex++, applicant.getSsc_info().getReg_no());
//            
            stmt.setString(parameterIndex++, applicant.getApplication_info().getMobile_number());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_ff());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_eq());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_bksp());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_expatriate());
//            
            stmt.setARRAY(parameterIndex++, inputEiin);
            stmt.setARRAY(parameterIndex++, inputShift);
            stmt.setARRAY(parameterIndex++, inputVersion);            
            stmt.setARRAY(parameterIndex++, inputGroup);
            stmt.setARRAY(parameterIndex++, inputSQuota);
            stmt.setARRAY(parameterIndex++, inputGbQuota);
            
            stmt.setARRAY(parameterIndex++, inputPriority);
            stmt.setARRAY(parameterIndex++, inputApptype);
//            
            stmt.setString(parameterIndex++, applicant.getSsc_info().getBoard_name());
            stmt.setString(parameterIndex++, applicant.getSsc_info().getStudent_name());
            stmt.setString(parameterIndex++, applicant.getSsc_info().getFather_name());
            stmt.setString(parameterIndex++, applicant.getSsc_info().getMother_name());
            stmt.setString(parameterIndex++, applicant.getSsc_info().getGender_name());
//
            stmt.setString(parameterIndex++, ipAddress.getxForward());
            stmt.setString(parameterIndex++, ipAddress.getVia());            
            stmt.setString(parameterIndex++, ipAddress.getRemoteAddress());
            if(!scode.equalsIgnoreCase(""))
            	stmt.setString(parameterIndex++, scode);
            else
            	stmt.setString(parameterIndex++, null);
            
            stmt.registerOutParameter(parameterIndex++, java.sql.Types.INTEGER);
            stmt.registerOutParameter(parameterIndex++, java.sql.Types.VARCHAR);
            
            stmt.setString(parameterIndex++, applicant.getApplication_info().getNid_number());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getNidholder());
            
            // Added 6 parameter
            
            
            
            
            stmt.executeUpdate();
            if( stmt.getInt(27)==1){//17
            	response.setStatus("OK");
            	response.setMessage("OK");
            	String appid = applicant.getSsc_info().getBoard_id()+applicant.getSsc_info().getPassing_year()+applicant.getSsc_info().getRoll();
            			
            	response.setApplication_id(appid);
            	response.setSscInfo(applicant.getSsc_info());
            	response.setApplication_info(applicant.getApplication_info());
            }
            else{
            	response.setStatus("INVALID");
            	System.out.println(""+stmt.getString(28));
            	response.setMessage(stmt.getString(28)); 
            }
		} 
		catch (Exception e){e.printStackTrace();
			response.setStatus("INVALID");
			response.setMessage(e.getMessage());
			System.out.println(e.getMessage());
		}
		finally{try{
			stmt.close();
			ConnectionManager.closeConnection(conn);
			} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
	      
	      } 
        return response;
        

	}
	//////////////////////////////////////////
	public ResponseDTO saveMigrationNew(ApplicantDTO applicant,List<ChoiceDTO> choiceList, String autoMigrationYN, IpAddressDTO ipAddress)
	{
		ResponseDTO response =new ResponseDTO();
		response.setStatus("OK");
		Connection conn = ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		
		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			choice.setMobile_no(applicant.getApplication_info().getMobile_number());
		}

		
		String[] eiinList = new String[choiceList.size()];
		String[] shiftList = new String[choiceList.size()];
		String[] versionList = new String[choiceList.size()];
		String[] groupList = new String[choiceList.size()];
		String[] sQuotaList = new String[choiceList.size()];
		String[] priorityList	 = new String[choiceList.size()];
		String[] appType	 = new String[choiceList.size()];
		

		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			
			eiinList[i] 	  		=  choice.getEiin();
			shiftList[i]  	=  choice.getShift_id();
			versionList[i] 	=  choice.getVersion_id();
			groupList[i]=  choice.getGroup_id();
			sQuotaList[i]= choice.getSpecial_quota();
			priorityList[i] 		  	=  choice.getPriority();
			appType[i] 		  	=  choice.getApplication_type();
			
		}
		
		java.util.Set result = new java.util.HashSet(java.util.Arrays.asList(eiinList));
		System.out.println("Total unique college : "+result.size());
	    System.out.println("Total unique college list : "+result);
	    
	    if(result.size()>10)
	      {  
	    	System.out.println("You can not select more than ten unique colleges....!!!");  
	    	  response.setStatus("ERROR");
			  response.setMessage("You can not select more than ten unique colleges....!!!");  
	      }
	      
	      else
	      {
		try {
       	 
			ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
			
			ARRAY inputEiin=new ARRAY(arrString,conn,eiinList);
			ARRAY inputShift=new ARRAY(arrString,conn,shiftList);
			ARRAY inputVersion=new ARRAY(arrString,conn,versionList);
			ARRAY inputGroup=new ARRAY(arrString,conn,groupList);
			ARRAY inputSQuota=new ARRAY(arrString,conn,sQuotaList);
			ARRAY inputPriority=new ARRAY(arrString,conn,priorityList);
			ARRAY inputApptype=new ARRAY(arrString,conn,appType);

			
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call SaveMigrationNew(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");

            int parameterIndex = 1;
            
            stmt.setString(parameterIndex++, applicant.getApplication_id());
            
            stmt.setString(parameterIndex++, applicant.getSsc_info().getRoll());
            stmt.setInt(parameterIndex++, Integer.parseInt(applicant.getSsc_info().getBoard_id()));
            stmt.setInt(parameterIndex++, Integer.parseInt(applicant.getSsc_info().getPassing_year()));
            stmt.setString(parameterIndex++, applicant.getSsc_info().getReg_no());
//            
            stmt.setString(parameterIndex++, applicant.getApplication_info().getMobile_number());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_ff());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_eq());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_bksp());
            stmt.setString(parameterIndex++, applicant.getApplication_info().getQuota_expatriate());
//            
            stmt.setARRAY(parameterIndex++, inputEiin);
            stmt.setARRAY(parameterIndex++, inputShift);
            stmt.setARRAY(parameterIndex++, inputVersion);            
            stmt.setARRAY(parameterIndex++, inputGroup);
            stmt.setARRAY(parameterIndex++, inputSQuota);
            stmt.setARRAY(parameterIndex++, inputPriority);
            stmt.setARRAY(parameterIndex++, inputApptype);
            
            stmt.setString(parameterIndex++, autoMigrationYN);

            stmt.setString(parameterIndex++, ipAddress.getxForward());
            stmt.setString(parameterIndex++, ipAddress.getVia());            
            stmt.setString(parameterIndex++, ipAddress.getRemoteAddress());
            
            stmt.registerOutParameter(parameterIndex++, java.sql.Types.VARCHAR);
            
            
            
            stmt.executeUpdate();
            
            System.out.println(""+stmt.getString(22));
            response.setMessage(stmt.getString(22));
            
		} 
		catch (Exception e){
			e.printStackTrace();
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		finally{
			try{
			stmt.close();
			ConnectionManager.closeConnection(conn);
			} catch (Exception e){e.printStackTrace();}stmt = null;conn = null;} 		
	      
	      } 
        return response;
        

	}
	//////////////////////////////////////////
	public boolean cancelAdmission(String application_id,String merit_type)
	{
		boolean tmp=false;
		Connection conn = ConnectionManager.getWriteConnection();
		OracleCallableStatement stmt = null;
		try {
			stmt = (OracleCallableStatement) conn.prepareCall("{ call cancelAdmissionApplicant(?,?)  }");
			stmt.setString(1, application_id);
            stmt.setString(2, merit_type);
			stmt.executeUpdate();
			tmp = true;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 	
		return tmp;
	}
	
	public ResponseDTO saveNewApplication(ApplicantDTO applicant,List<ChoiceDTO> choiceList,IpAddressDTO ipAddress)
	{
		ResponseDTO response =new ResponseDTO();
		Connection conn = ConnectionManager.getWriteConnection();
		OracleCallableStatement stmt = null;
		
		String[] eiinList = new String[choiceList.size()];
		String[] shiftList = new String[choiceList.size()];
		String[] versionList = new String[choiceList.size()];
		String[] groupList = new String[choiceList.size()];
		String[] sQuotaList = new String[choiceList.size()];
		String[] eQuotaList	 = new String[choiceList.size()];
		String[] priorityList	 = new String[choiceList.size()];
		

		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			
			eiinList[i] 	  		=  choice.getEiin();
			shiftList[i]  	=  choice.getShift_id();
			versionList[i] 	=  choice.getVersion_id();
			groupList[i]=  choice.getGroup_id();
			sQuotaList[i]= choice.getSpecial_quota();
			eQuotaList[i]=choice.getEducation_quota();
			priorityList[i] 		  	=  choice.getPriority();
		}
		
		try {
       	 
	    	ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
			
			ARRAY inputEiin=new ARRAY(arrString,conn,eiinList);
			ARRAY inputShift=new ARRAY(arrString,conn,shiftList);
			ARRAY inputVersion=new ARRAY(arrString,conn,versionList);
			ARRAY inputGroup=new ARRAY(arrString,conn,groupList);
			ARRAY inputSQuota=new ARRAY(arrString,conn,sQuotaList);
			ARRAY inputEQuota=new ARRAY(arrString,conn,eQuotaList);			
			ARRAY inputPriority=new ARRAY(arrString,conn,priorityList);
			
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call SaveNewApplication(?,?,?,?,?,?,?," +
            																		 "?,?,?,?,?,?, " +
            																		 "?,?,?,?,?,?,?,?,?)  }");
            
            stmt.setString(1, applicant.getSsc_info().getRoll());
            stmt.setString(2, applicant.getSsc_info().getBoard_id());
            stmt.setString(3, applicant.getSsc_info().getPassing_year());
            stmt.setString(4, applicant.getApplication_info().getMobile_number());
            stmt.setString(5, applicant.getApplication_info().getQuota_yn());
            stmt.setString(6, applicant.getApplication_info().getQuota_ff()==null?"N":applicant.getApplication_info().getQuota_ff());
            stmt.setARRAY(7, inputEiin);
            stmt.setARRAY(8, inputShift);
            stmt.setARRAY(9, inputVersion);
            stmt.setARRAY(10, inputGroup);
            stmt.setARRAY(11, inputSQuota);
            stmt.setARRAY(12, inputEQuota);
            stmt.setARRAY(13, inputPriority);
            
            stmt.setString(14, ipAddress.getxForward());
            stmt.setString(15, ipAddress.getVia());
            stmt.setString(16, ipAddress.getRemoteAddress());
            stmt.setString(17, applicant.getSsc_info().getReg_no());
            
            stmt.registerOutParameter(18, java.sql.Types.INTEGER); 
            stmt.registerOutParameter(19, java.sql.Types.VARCHAR); 
            stmt.registerOutParameter(20, java.sql.Types.VARCHAR); 
            stmt.registerOutParameter(21, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(22, java.sql.Types.VARCHAR); 

            
            stmt.executeUpdate();
            if(stmt.getInt(18)==1){
            	SendOApplication soa = new SendOApplication();
            	Thread thread = new Thread(soa);            	
            	response.setStatus("OK");
            	String appid = stmt.getString(20);
            	String pass = stmt.getString(21);
            	
            	if(stmt.getString(22).equalsIgnoreCase("N")){
            	
            	soa.setApplicant(applicant);
            	soa.setApp_id(appid);
            	soa.setEiinList(eiinList);
            	soa.setGroupList(groupList);
            	soa.setShiftList(shiftList);
            	soa.setVersionList(versionList);
            	soa.setPriorityList(priorityList);
            	soa.setOp("NEW");            	
    			thread.start();
            	}

    			
//				SmsSender smsSender=new SmsSender();
//				smsSender.setMobile(applicant.getApplication_info().getConfirm_mobile_number());
//				smsSender.setAppid(appid);
//				smsSender.setPass(pass);
//				smsSender.setName(applicant.getSsc_info().getStudent_name());
//				smsSender.setOperation("smsWPayment");
//				thread = new Thread(smsSender);
//				thread.start();
				
            	response.setApplication_id(stmt.getString(20));
            	response.setPassword(stmt.getString(21));
            	response.setApplication_id(appid);
            	response.setPassword(pass);
            	response.setSscInfo(applicant.getSsc_info());
            	response.setPayment_status(stmt.getString(22));
            }
            else{
            	response.setStatus("ERROR");
            	response.setMessage(stmt.getString(19));
            }
		} 
		catch (Exception e){e.printStackTrace();
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
        
        return response;
        

	}
	
	public ResponseDTO updateChoiceList(ApplicantDTO applicant,List<ChoiceDTO> choiceList,IpAddressDTO ipAddress,String otp)
	{
		ResponseDTO response =new ResponseDTO();
		Connection conn = ConnectionManager.getWriteConnection();
		OracleCallableStatement stmt = null;
		
		int webChoiceCount = 0;
		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			if(choice.getVia().equalsIgnoreCase("W"))webChoiceCount++;
		}		
		String[] eiinList1 = new String[webChoiceCount];
		String[] shiftList1 = new String[webChoiceCount];
		String[] versionList1 = new String[webChoiceCount];
		String[] groupList1 = new String[webChoiceCount];
		String[] priorityList1	 = new String[webChoiceCount];
		int webINCR = 0 ;
		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			if(choice.getVia().equalsIgnoreCase("W"))
			{
				eiinList1[webINCR] 	  		=  choice.getEiin();
				shiftList1[webINCR]  	=  choice.getShift_id();
				versionList1[webINCR] 	=  choice.getVersion_id();
				groupList1[webINCR]=  choice.getGroup_id();
				priorityList1[webINCR] 		  	=  choice.getPriority();
				webINCR++;
			}
		}
		
		String[] choiceIdList = new String[choiceList.size()];
		String[] eiinList = new String[choiceList.size()];
		String[] shiftList = new String[choiceList.size()];
		String[] versionList = new String[choiceList.size()];
		String[] groupList = new String[choiceList.size()];
		String[] sQuotaList = new String[choiceList.size()];
		String[] priorityList	 = new String[choiceList.size()];
		String[] viaList	 = new String[choiceList.size()];

		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			
			choiceIdList[i] 	  		=  choice.getChoice_id();
			eiinList[i] 	  		=  choice.getEiin();
			shiftList[i]  	=  choice.getShift_id();
			versionList[i] 	=  choice.getVersion_id();
			groupList[i]=  choice.getGroup_id();
			sQuotaList[i]= choice.getSpecial_quota();
			priorityList[i] 		  	=  choice.getPriority();
			viaList[i] 		  	=  choice.getVia();
		}
		
		try {
       	 
	    	ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
			
	    	ARRAY inputChoiceId=new ARRAY(arrString,conn,choiceIdList);
			ARRAY inputEiin=new ARRAY(arrString,conn,eiinList);
			ARRAY inputShift=new ARRAY(arrString,conn,shiftList);
			ARRAY inputVersion=new ARRAY(arrString,conn,versionList);
			ARRAY inputGroup=new ARRAY(arrString,conn,groupList);
			ARRAY inputSQuota=new ARRAY(arrString,conn,sQuotaList);
			ARRAY inputPriority=new ARRAY(arrString,conn,priorityList);
			ARRAY inputVia=new ARRAY(arrString,conn,viaList);
			
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call updateChoiceList(?, " +
            																		  "?,?,?,?,?,?,?,?," +
            																		  "?,?,?,?,?,?)  }");
            
            stmt.setString(1, applicant.getApplication_id());
            stmt.setARRAY(2, inputChoiceId);
            stmt.setARRAY(3, inputEiin);
            stmt.setARRAY(4, inputShift);
            stmt.setARRAY(5, inputVersion);
            stmt.setARRAY(6, inputGroup);
            stmt.setARRAY(7, inputSQuota);
            stmt.setARRAY(8, inputVia);
            stmt.setARRAY(9, inputPriority);
            stmt.setString(10, otp);
            
            
            stmt.setString(11, ipAddress.getxForward());
            stmt.setString(12, ipAddress.getVia());
            stmt.setString(13, ipAddress.getRemoteAddress());

            
            stmt.registerOutParameter(14, java.sql.Types.INTEGER);
            stmt.registerOutParameter(15, java.sql.Types.VARCHAR);

            
            stmt.executeUpdate();
            if(stmt.getInt(14)==1){
            	response.setStatus("OK");

            	SendOApplication soa = new SendOApplication();
            	soa.setApplicant(applicant);
            	soa.setApp_id(applicant.getApplication_id());
            	
            	soa.setEiinList(eiinList1);
            	soa.setGroupList(groupList1);
            	soa.setShiftList(shiftList1);
            	soa.setVersionList(versionList1);
            	soa.setPriorityList(priorityList1);
            	
            	soa.setOp("UPDATE");
            	Thread thread = new Thread(soa);
    			thread.start();

            	
            	
            	response.setMessage(stmt.getString(15));
            }
            else{
            	response.setStatus("ERROR");       
            	response.setMessage(stmt.getString(15));
            }
		} 
		catch (Exception e){e.printStackTrace();
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
        
        return response;
        

	}
	
	public ResponseDTO newUpdateChoiceList(ApplicantDTO applicant,List<ChoiceDTO> choiceList,IpAddressDTO ipAddress,String otp)
	{
		ResponseDTO response =new ResponseDTO();
		Connection conn = ConnectionManager.getWriteConnection();
		OracleCallableStatement stmt = null;
		
		int webChoiceCount = 0;
		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			if(choice.getVia().equalsIgnoreCase("W"))webChoiceCount++;
		}		
		String[] eiinList1 = new String[webChoiceCount];
		String[] shiftList1 = new String[webChoiceCount];
		String[] versionList1 = new String[webChoiceCount];
		String[] groupList1 = new String[webChoiceCount];
		String[] priorityList1	 = new String[webChoiceCount];
		int webINCR = 0 ;
		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			if(choice.getVia().equalsIgnoreCase("W"))
			{
				eiinList1[webINCR] 	  		=  choice.getEiin();
				shiftList1[webINCR]  	=  choice.getShift_id();
				versionList1[webINCR] 	=  choice.getVersion_id();
				groupList1[webINCR]=  choice.getGroup_id();
				priorityList1[webINCR] 		  	=  choice.getPriority();
				webINCR++;
			}
		}
		
		String[] choiceIdList = new String[choiceList.size()];
		String[] eiinList = new String[choiceList.size()];
		String[] shiftList = new String[choiceList.size()];
		String[] versionList = new String[choiceList.size()];
		String[] groupList = new String[choiceList.size()];
		String[] sQuotaList = new String[choiceList.size()];
		String[] eQuotaList = new String[choiceList.size()];
		String[] priorityList	 = new String[choiceList.size()];

		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			
			choiceIdList[i] 	  		=  choice.getChoice_id();
			eiinList[i] 	  		=  choice.getEiin();
			shiftList[i]  	=  choice.getShift_id();
			versionList[i] 	=  choice.getVersion_id();
			groupList[i]=  choice.getGroup_id();
			sQuotaList[i]= choice.getSpecial_quota();
			eQuotaList[i]= choice.getEducation_quota();
			priorityList[i] 		  	=  choice.getPriority();
		}
		
		try {
       	 
	    	ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
			
	    	ARRAY inputChoiceId=new ARRAY(arrString,conn,choiceIdList);
			ARRAY inputEiin=new ARRAY(arrString,conn,eiinList);
			ARRAY inputShift=new ARRAY(arrString,conn,shiftList);
			ARRAY inputVersion=new ARRAY(arrString,conn,versionList);
			ARRAY inputGroup=new ARRAY(arrString,conn,groupList);
			ARRAY inputSQuota=new ARRAY(arrString,conn,sQuotaList);
			ARRAY inputEQuota=new ARRAY(arrString,conn,eQuotaList);
			ARRAY inputPriority=new ARRAY(arrString,conn,priorityList);
			
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call updateNewChoiceList(?, " +
            																		  "?,?,?,?,?,?,?,?," +
            																		  "?,?,?,?,?,?)  }");
            
            stmt.setString(1, applicant.getApplication_id());
            stmt.setARRAY(2, inputChoiceId);
            stmt.setARRAY(3, inputEiin);
            stmt.setARRAY(4, inputShift);
            stmt.setARRAY(5, inputVersion);
            stmt.setARRAY(6, inputGroup);
            stmt.setARRAY(7, inputSQuota);
            stmt.setARRAY(8, inputEQuota);
            stmt.setARRAY(9, inputPriority);
            stmt.setString(10, otp);
            
            
            stmt.setString(11, ipAddress.getxForward());
            stmt.setString(12, ipAddress.getVia());
            stmt.setString(13, ipAddress.getRemoteAddress());

            
            stmt.registerOutParameter(14, java.sql.Types.INTEGER);
            stmt.registerOutParameter(15, java.sql.Types.VARCHAR);

            
            stmt.executeUpdate();
            if(stmt.getInt(14)==1){
            	response.setStatus("OK");

            	SendOApplication soa = new SendOApplication();
            	soa.setApplicant(applicant);
            	soa.setApp_id(applicant.getApplication_id());
            	
            	soa.setEiinList(eiinList1);
            	soa.setGroupList(groupList1);
            	soa.setShiftList(shiftList1);
            	soa.setVersionList(versionList1);
            	soa.setPriorityList(priorityList1);
            	
            	soa.setOp("UPDATE");
            	Thread thread = new Thread(soa);
    			thread.start();

            	
            	
            	response.setMessage(stmt.getString(15));
            }
            else{
            	response.setStatus("ERROR");       
            	response.setMessage(stmt.getString(15));
            }
		} 
		catch (Exception e){e.printStackTrace();
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
        
        return response;
        

	}
	
	public ResponseDTO updateReleaseSlip(ApplicantDTO applicant,List<ChoiceDTO> choiceList,IpAddressDTO ipAddress,String otp)
	{
		ResponseDTO response =new ResponseDTO();
		Connection conn = ConnectionManager.getWriteConnection();
		OracleCallableStatement stmt = null;
				
		String[] choiceIdList = new String[choiceList.size()];
		String[] eiinList = new String[choiceList.size()];
		String[] shiftList = new String[choiceList.size()];
		String[] versionList = new String[choiceList.size()];
		String[] groupList = new String[choiceList.size()];
		String[] sQuotaList = new String[choiceList.size()];
		String[] eQuotaList = new String[choiceList.size()];
		String[] priorityList	 = new String[choiceList.size()];

		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			
			choiceIdList[i] 	  		=  choice.getChoice_id();
			eiinList[i] 	  		=  choice.getEiin();
			shiftList[i]  	=  choice.getShift_id();
			versionList[i] 	=  choice.getVersion_id();
			groupList[i]=  choice.getGroup_id();
			sQuotaList[i]= choice.getSpecial_quota();
			eQuotaList[i]= choice.getEducation_quota();
			priorityList[i] 		  	=  choice.getPriority();
		}
		
		try {
       	 
	    	ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
			
	    	ARRAY inputChoiceId=new ARRAY(arrString,conn,choiceIdList);
			ARRAY inputEiin=new ARRAY(arrString,conn,eiinList);
			ARRAY inputShift=new ARRAY(arrString,conn,shiftList);
			ARRAY inputVersion=new ARRAY(arrString,conn,versionList);
			ARRAY inputGroup=new ARRAY(arrString,conn,groupList);
			ARRAY inputSQuota=new ARRAY(arrString,conn,sQuotaList);
			ARRAY inputEQuota=new ARRAY(arrString,conn,eQuotaList);
			ARRAY inputPriority=new ARRAY(arrString,conn,priorityList);
			
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call updateReleaseSlip(?, " +
            																		  "?,?,?,?,?,?,?,?," +
            																		  "?,?,?,?,?,?)  }");
            
            stmt.setString(1, applicant.getApplication_id());
            stmt.setARRAY(2, inputChoiceId);
            stmt.setARRAY(3, inputEiin);
            stmt.setARRAY(4, inputShift);
            stmt.setARRAY(5, inputVersion);
            stmt.setARRAY(6, inputGroup);
            stmt.setARRAY(7, inputSQuota);
            stmt.setARRAY(8, inputEQuota);
            stmt.setARRAY(9, inputPriority);
            stmt.setString(10, otp);
            
            
            stmt.setString(11, ipAddress.getxForward());
            stmt.setString(12, ipAddress.getVia());
            stmt.setString(13, ipAddress.getRemoteAddress());

            
            stmt.registerOutParameter(14, java.sql.Types.INTEGER);
            stmt.registerOutParameter(15, java.sql.Types.VARCHAR);

            
            stmt.executeUpdate();
            if(stmt.getInt(14)==1){
            	response.setStatus("OK");
            	response.setMessage(stmt.getString(15));
            }
            else{
            	response.setStatus("ERROR");       
            	response.setMessage(stmt.getString(15));
            }
		} 
		catch (Exception e){e.printStackTrace();
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
        
        return response;
        

	}	
	public ResponseDTO updateEQ(String application_id,List<ChoiceDTO> choiceList,IpAddressDTO ipAddress,String otp)
	{
		ResponseDTO response =new ResponseDTO();
		Connection conn = ConnectionManager.getWriteConnection();
		OracleCallableStatement stmt = null;
		
		
		String[] choiceIdList = new String[choiceList.size()];
		String[] eqList = new String[choiceList.size()];

		for (int i = 0; i < choiceList.size(); i++)
		{
			ChoiceDTO choice = choiceList.get(i);
			
			choiceIdList[i] 	  		=  choice.getChoice_id();
			eqList[i] 	  		=  choice.getEducation_quota();
		}
		
		try {
       	 
	    	ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
			
	    	ARRAY inputChoiceId=new ARRAY(arrString,conn,choiceIdList);
			ARRAY inputEQ=new ARRAY(arrString,conn,eqList);
			
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call updateEQInfo(?,?,?,?,?,?,?,?,?)  }");
            
            stmt.setString(1, application_id);
            stmt.setARRAY(2, inputChoiceId);
            stmt.setARRAY(3, inputEQ);
            stmt.setString(4, otp);
            stmt.setString(5, ipAddress.getxForward());
            stmt.setString(6, ipAddress.getVia());
            stmt.setString(7, ipAddress.getRemoteAddress());
            
            stmt.registerOutParameter(8, java.sql.Types.INTEGER);
            stmt.registerOutParameter(9, java.sql.Types.VARCHAR);

            
            stmt.executeUpdate();
            if(stmt.getInt(8)==1){
            	response.setStatus("OK");
            	response.setMessage(stmt.getString(9));
            }
            else{
            	response.setStatus("ERROR");       
            	response.setMessage(stmt.getString(9));
            }
		} 
		catch (Exception e){e.printStackTrace();
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
        
        return response;
        

	}

	public String generateOtp(String application_id,IpAddressDTO ipAddress){

		Connection conn = ConnectionManager.getWriteConnection();
		OracleCallableStatement stmt = null;
		String otp=null;
		try {
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call generateOTP(?,?,?,?,?)  }");
            
            stmt.setString(1, application_id);      
            stmt.setString(2, ipAddress.getxForward());
            stmt.setString(3, ipAddress.getVia());
            stmt.setString(4, ipAddress.getRemoteAddress());
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            
            stmt.executeUpdate();
            otp=stmt.getString(5);
            
		} 
		catch (Exception e){e.printStackTrace();
		}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
        
        return otp;
        
        
	}
	
	public String generateOtpNew(String application_id){

		Connection conn = ConnectionManager.getWriteConnection();
		OracleCallableStatement stmt = null;
		String otp=null;
		
		String[] abList = new String[2];
					
		
		try {
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call generateOTP_New(?,?,?)  }");
            
    		ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
    		
    		ARRAY inputArr=new ARRAY(arrString,conn,abList);
    		
            stmt.setString(1, application_id);
            stmt.setARRAY(2, inputArr);
            
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            
            stmt.executeUpdate();
            otp=stmt.getString(2);
            
		} 
		catch (Exception e){e.printStackTrace();
		}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
        
        return otp;
        
        
	}
	public boolean isValidOtp(String application_id,String secure_code, String otp){

		int total=0;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql= " Select count(SECURE_CODE) TOTAL from OTP  Where Application_Id=? and Secure_Code=? and used_YN='N'";
		if(otp.equalsIgnoreCase("OTP"))
			sql= " Select count(SECURE_CODE) TOTAL from OTP  Where Application_Id=? and Secure_Code=? and used_YN='N'";
		else
			sql= " select count(*) TOTAL from application_info ai,board_data_ssc bd where AI.SSC_ROLL_NO=BD.ROLL_NO and AI.SSC_BOARD_ID=BD.BOARD_ID and" +
					" AI.SSC_PASSING_YEAR=BD.PASSING_YEAR and AI.APPLICATION_ID=? and BD.REG_NO=?";
		
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, application_id);
			    stmt.setString(2, secure_code);
				r = stmt.executeQuery();
				if (r.next())
				{

					total=r.getInt("TOTAL");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(total>0)
			return true;
		else
			return false;

	}
	
	public boolean isValidOtpNew(String application_id,String secure_code, String otp){

		int total=0;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql= " Select count(SECURE_CODE) TOTAL from OTP  Where Application_Id=? and Secure_Code=? and used_YN='N'";
		if(otp.equalsIgnoreCase("OTP"))
			sql= " Select count(SECURE_CODE) TOTAL from OTP  Where Application_Id=? and Secure_Code=? and used_YN='N'";
		else
			sql= " select count(*) TOTAL from application_info ai,board_data_ssc bd where AI.SSC_ROLL_NO=BD.ROLL_NO and AI.SSC_BOARD_ID=BD.BOARD_ID and" +
					" AI.SSC_PASSING_YEAR=BD.PASSING_YEAR and AI.APPLICATION_ID=? and BD.REG_NO=?";
		
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, application_id);
			    stmt.setString(2, secure_code);
				r = stmt.executeQuery();
				if (r.next())
				{

					total=r.getInt("TOTAL");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(total>0)
			return true;
		else
			return false;

	}
	public int getChoiceOtpCount(String application_id)
	{	
		int total=0;
		Connection conn = ConnectionManager.getReadConnection();
		String sql= " Select totalOtpCount(?) opt_total from dual";

		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, application_id);
				r = stmt.executeQuery();
				if (r.next())
				{

					total=r.getInt("OPT_TOTAL");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return total;
	}
	
	public boolean changeOtpStatus(String application_id,String secure_code,String status){

		boolean response=false;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql= " Update OTP set USED_YN=? Where application_id=? and secure_code=?";

		   PreparedStatement stmt = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, status);
			    stmt.setString(2, application_id);
			    stmt.setString(3, secure_code);
				stmt.executeUpdate();
				response=true;
				
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		return response;

	}

	public boolean changeWebPaymentN(String application_id){

		boolean response=false;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql= " Update APPLICATION_COLLEGES set payment_status='N' Where application_id=? and application_type='W'";

		   PreparedStatement stmt = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, application_id);
				stmt.executeUpdate();
				response=true;
				
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		return response;

	}

	
	public boolean updateQuotaInfo(String application_id,String quota_YN,String quota_ff,IpAddressDTO ipAddress,String otp){


		if(application_id.equalsIgnoreCase("application_id"))
			return false;
		Connection conn = ConnectionManager.getWriteConnection();
		OracleCallableStatement stmt = null;
		int operation=0;
		try {
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call updateQuotaInfo(?,?,?,?,?,?,?,?)  }");
            
            stmt.setString(1, application_id);      
            stmt.setString(2, quota_YN);
            stmt.setString(3, quota_ff);
            stmt.setString(4, otp);
            stmt.setString(5, ipAddress.getxForward());
            stmt.setString(6, ipAddress.getVia());
            stmt.setString(7, ipAddress.getRemoteAddress());
            stmt.registerOutParameter(8, java.sql.Types.INTEGER);
            
            stmt.executeUpdate();
            operation=stmt.getInt(8);

		} 
		catch (Exception e){e.printStackTrace();
		}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
        
		if(operation==1)
			return true;
		else
			return false;
        
	}
	
	// can I make it static
	public void setttsend(String app_id)
	{
		Connection conn = ConnectionManager.getWriteConnection();
		String sql = "update APPLICATION_COLLEGES set ttsend='Y' where application_id='"+app_id+"'";
		if(app_id.startsWith("7"))
			sql = "update APPLICATION_COLLEGES_NEW set ttsend='Y' where application_id='"+app_id+"'";
		PreparedStatement stmt = null;
		try{
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate();
		}catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e){e.printStackTrace();}
			stmt = null;conn = null;
		}
	}
	public void setPayment(String app_id)
	{
		Connection conn = ConnectionManager.getWriteConnection();
		String sql = "update APPLICATION_COLLEGES set payment_status='Y' where application_id='"+app_id+"'";
		if(app_id.startsWith("7"))
			sql = "update APPLICATION_COLLEGES_NEW set payment_status='Y' where application_id='"+app_id+"'";
		PreparedStatement stmt = null;
		try{
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate();
		}catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e){e.printStackTrace();}
			stmt = null;conn = null;
		}
	}
//	public boolean PaymentOapplication(String app_id,String ssc_roll,String ssc_board,String ssc_year,String ssc_regno,String ssc_group,String ssc_gpa
//			,String ssc_eiin,String ssc_name,String ssc_father,String ssc_mother,String gender,String ssc_board_name
//			 ,String trackid,String contact_no)
//	{
//		boolean tmp=false;
//		Connection conn = ConnectionManager.getProcConnection();
//		OracleCallableStatement stmt = null;
//		try {
//			stmt = (OracleCallableStatement) conn.prepareCall("{ call webpayment(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");
//			int parameterIndex = 1;
//			stmt.setString(parameterIndex++, app_id);
//            stmt.setString(parameterIndex++, ssc_roll);
//			stmt.setInt(parameterIndex++, Integer.parseInt(ssc_board));
//            stmt.setInt(parameterIndex++, Integer.parseInt(ssc_year));
//			stmt.setString(parameterIndex++, ssc_regno);
//            stmt.setString(parameterIndex++, ssc_group);
//			stmt.setString(parameterIndex++, ssc_gpa);
//            stmt.setString(parameterIndex++, ssc_eiin);
//			stmt.setString(parameterIndex++, ssc_name);
//            stmt.setString(parameterIndex++, ssc_father);
//			stmt.setString(parameterIndex++, ssc_mother);
//            stmt.setString(parameterIndex++, gender);
//			stmt.setString(parameterIndex++, ssc_board_name);
//            stmt.setString(parameterIndex++, trackid);
//			stmt.setString(parameterIndex++, contact_no);
//			stmt.executeUpdate();
//			tmp = true;
//		}
//		catch (Exception e){
//			e.printStackTrace();
//		}
//		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
//		{e.printStackTrace();}stmt = null;conn = null;} 	
//		return tmp;
//	}
	static PreparedStatement stmtPaymentOapplication = null;
	static PreparedStatement stmtPaymentDataInsert = null;
	static PreparedStatement stmtPayment = null;
	static String sqlPaymentOapplication = "update BOARD_DATA_SSC set GPA=?,TT_TRANS_NUMBER=?,CONTACT_NO=?,APPLICATION_ID=? " +
			" where ROLL_NO=? and BOARD_ID=? and PASSING_YEAR=?";
	static String sqlPaymentDataInsert = "insert into board_data_ssc (ROLL_NO, BOARD_ID, PASSING_YEAR, REG_NO, GROUP_ID, STUDENT_NAME, FATHER_NAME," +
			" MOTHER_NAME, GENDER, GPA ,SSC_EIIN, TT_TRANS_NUMBER, APPLICATION_ID, CONTACT_NO,INSERTED) values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,'TT')";
	static String sqlPayment = "insert into applicant_payment (app_id,trackid,pin,contact_no,paymobile,paytime) values ( ?,?,?,?,?,?)";
	
	public static synchronized int PaymentOapplication(String app_id,String ssc_roll,String ssc_board,String ssc_year,String ssc_regno,String ssc_group,String ssc_gpa
			,String ssc_eiin,String ssc_name,String ssc_father,String ssc_mother,String gender,String ssc_board_name
			 ,String trackid,String contact_no, String pin, String paytime, String paymobile)
    {
		int tmp = 0;
           try {
	            
	            if(stmtPaymentOapplication==null)
	            	stmtPaymentOapplication = ConnectionManager.getConnectionStatic().prepareStatement(sqlPaymentOapplication);
	            int parameterIndex = 1;
	            stmtPaymentOapplication.setFloat(parameterIndex++, Float.parseFloat(ssc_gpa));
	            stmtPaymentOapplication.setString(parameterIndex++, trackid);
	            stmtPaymentOapplication.setString(parameterIndex++, contact_no);	            
	            stmtPaymentOapplication.setString(parameterIndex++, app_id);
	            
	            stmtPaymentOapplication.setString(parameterIndex++, ssc_roll);
	            stmtPaymentOapplication.setInt(parameterIndex++, Integer.parseInt(ssc_board));
	            stmtPaymentOapplication.setInt(parameterIndex++, Integer.parseInt(ssc_year));

	            tmp = stmtPaymentOapplication.executeUpdate();
	            stmtPaymentOapplication.clearParameters();

	            	            


	            if(tmp==0)
	            {
		            if(stmtPaymentDataInsert==null)
		            	stmtPaymentDataInsert = ConnectionManager.getConnectionStatic().prepareStatement(sqlPaymentDataInsert);
		            parameterIndex = 1;
		            stmtPaymentDataInsert.setString(parameterIndex++, ssc_roll);
		            stmtPaymentDataInsert.setInt(parameterIndex++, Integer.parseInt(ssc_board));
		            stmtPaymentDataInsert.setInt(parameterIndex++, Integer.parseInt(ssc_year));
		            stmtPaymentDataInsert.setString(parameterIndex++, ssc_regno);
		            stmtPaymentDataInsert.setInt(parameterIndex++, Integer.parseInt(ssc_group));

		            stmtPaymentDataInsert.setString(parameterIndex++, ssc_name);
		            stmtPaymentDataInsert.setString(parameterIndex++, ssc_father);
		            stmtPaymentDataInsert.setString(parameterIndex++, ssc_mother);
		            stmtPaymentDataInsert.setString(parameterIndex++, gender);
		            stmtPaymentDataInsert.setFloat(parameterIndex++, Float.parseFloat(ssc_gpa));
		            stmtPaymentDataInsert.setString(parameterIndex++, ssc_eiin);
		            
		            stmtPaymentDataInsert.setString(parameterIndex++, trackid);
		            stmtPaymentDataInsert.setString(parameterIndex++, app_id);
		            stmtPaymentDataInsert.setString(parameterIndex++, contact_no);

		            tmp = stmtPaymentDataInsert.executeUpdate();
		            stmtPaymentDataInsert.clearParameters();
	            	
	            }
		          if(tmp>0)
		          {
//		        	  if(stmtPayment==null)
//			            	stmtPayment = ConnectionManager.getConnectionStatic().prepareStatement(sqlPayment);
//			            parameterIndex = 1;
//			            stmtPayment.setString(parameterIndex++, app_id);
//			            stmtPayment.setString(parameterIndex++, trackid);	            
//			            stmtPayment.setString(parameterIndex++, pin);	            
//			            stmtPayment.setString(parameterIndex++, contact_no);	            
//			            stmtPayment.setString(parameterIndex++, paymobile);	            
//			            stmtPayment.setString(parameterIndex++, paytime);
//			            tmp = stmtPayment.executeUpdate();
//			            stmtPayment.clearParameters();
			            
		          }
	          } catch (SQLException e) {
	            System.out.println(e.toString());
	            stmtPaymentOapplication = null;
	            stmtPaymentDataInsert = null;
	            stmtPayment = null;
	            ConnectionManager.closeStatic();
	        }	
	          return tmp;
    }
	
	
	
	public int PaymentOapplication1(String app_id,String ssc_roll,String ssc_board,String ssc_year,String ssc_regno,String ssc_group,String ssc_gpa
			,String ssc_eiin,String ssc_name,String ssc_father,String ssc_mother,String gender,String ssc_board_name
			 ,String trackid,String contact_no, String pin, String paytime, String paymobile)
    {
		int tmp = 0;
		PreparedStatement stmtPaymentOapplication1 = null;
		PreparedStatement stmtPaymentDataInsert1 = null;
		PreparedStatement stmtPayment1 = null;
		String sqlPaymentOapplication1 = "update BOARD_DATA_SSC set GPA=?,TT_TRANS_NUMBER=?,CONTACT_NO=?,APPLICATION_ID=? " +
				" where ROLL_NO=? and BOARD_ID=? and PASSING_YEAR=?";
		String sqlPaymentDataInsert1 = "insert into board_data_ssc (ROLL_NO, BOARD_ID, PASSING_YEAR, REG_NO, GROUP_ID, STUDENT_NAME, FATHER_NAME," +
				" MOTHER_NAME, GENDER, GPA ,SSC_EIIN, TT_TRANS_NUMBER, APPLICATION_ID, CONTACT_NO,INSERTED) values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,'TT')";
		String sqlPayment1 = "insert into applicant_payment (app_id,trackid,pin,contact_no,paymobile,paytime) values ( ?,?,?,?,?,?)";
		Connection con = ConnectionManager.getWriteConnection();
		try {
	            
			stmtPaymentOapplication1 = con.prepareStatement(sqlPaymentOapplication1);
			int parameterIndex = 1;
			stmtPaymentOapplication1.setFloat(parameterIndex++, Float.parseFloat(ssc_gpa));
			stmtPaymentOapplication1.setString(parameterIndex++, trackid);
			stmtPaymentOapplication1.setString(parameterIndex++, contact_no);	            
			stmtPaymentOapplication1.setString(parameterIndex++, app_id);
	            
			stmtPaymentOapplication1.setString(parameterIndex++, ssc_roll);
			stmtPaymentOapplication1.setInt(parameterIndex++, Integer.parseInt(ssc_board));
			stmtPaymentOapplication1.setInt(parameterIndex++, Integer.parseInt(ssc_year));

			tmp = stmtPaymentOapplication1.executeUpdate();

	            	            


	            if(tmp==0)
	            {
	            	stmtPaymentDataInsert1 = con.prepareStatement(sqlPaymentDataInsert1);
		            parameterIndex = 1;
		            stmtPaymentDataInsert1.setString(parameterIndex++, ssc_roll);
		            stmtPaymentDataInsert1.setInt(parameterIndex++, Integer.parseInt(ssc_board));
		            stmtPaymentDataInsert1.setInt(parameterIndex++, Integer.parseInt(ssc_year));
		            stmtPaymentDataInsert1.setString(parameterIndex++, ssc_regno);
		            stmtPaymentDataInsert1.setInt(parameterIndex++, Integer.parseInt(ssc_group));

		            stmtPaymentDataInsert1.setString(parameterIndex++, ssc_name);
		            stmtPaymentDataInsert1.setString(parameterIndex++, ssc_father);
		            stmtPaymentDataInsert1.setString(parameterIndex++, ssc_mother);
		            stmtPaymentDataInsert1.setString(parameterIndex++, gender);
		            stmtPaymentDataInsert1.setFloat(parameterIndex++, Float.parseFloat(ssc_gpa));
		            stmtPaymentDataInsert1.setString(parameterIndex++, ssc_eiin);
		            
		            stmtPaymentDataInsert1.setString(parameterIndex++, trackid);
		            stmtPaymentDataInsert1.setString(parameterIndex++, app_id);
		            stmtPaymentDataInsert1.setString(parameterIndex++, contact_no);

		            tmp = stmtPaymentDataInsert1.executeUpdate();
	            	
	            }
		          if(tmp>0)
		          {
		        	  stmtPayment1 = con.prepareStatement(sqlPayment1);
			            parameterIndex = 1;
			            stmtPayment1.setString(parameterIndex++, app_id);
			            stmtPayment1.setString(parameterIndex++, trackid);	            
			            stmtPayment1.setString(parameterIndex++, pin);	            
			            stmtPayment1.setString(parameterIndex++, contact_no);	            
			            stmtPayment1.setString(parameterIndex++, paymobile);	            
			            stmtPayment1.setString(parameterIndex++, paytime);
			            tmp = stmtPayment1.executeUpdate();
			            
		          }
	          } catch (SQLException e) {
	        	  e.printStackTrace();
	      		try{stmtPaymentOapplication1.close();}catch (Exception e1){}
	      		try{stmtPaymentDataInsert1.close();}catch (Exception e2){}
	      		try{stmtPayment1.close();}catch (Exception e3){}
	    		con = null;
	            ConnectionManager.closeStatic();
	        }	
	          return tmp;
    }
	
	public int PaymentOapplication2(String app_id,String ssc_roll,String ssc_board,String ssc_year,String ssc_regno,String ssc_group,String ssc_gpa
			,String ssc_eiin,String ssc_name,String ssc_father,String ssc_mother,String gender,String ssc_board_name
			 ,String trackid,String contact_no, String pin, String paytime, String paymobile)
	{
		int tmp=0;
		Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		try {
			stmt = (OracleCallableStatement) conn.prepareCall("{ call webpayment(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, app_id);
            stmt.setString(parameterIndex++, ssc_roll);
			stmt.setInt(parameterIndex++, Integer.parseInt(ssc_board));
            stmt.setInt(parameterIndex++, Integer.parseInt(ssc_year));
			stmt.setString(parameterIndex++, ssc_regno);
            stmt.setString(parameterIndex++, ssc_group);
			stmt.setString(parameterIndex++, ssc_gpa);
            stmt.setString(parameterIndex++, ssc_eiin);
			stmt.setString(parameterIndex++, ssc_name);
            stmt.setString(parameterIndex++, ssc_father);
			stmt.setString(parameterIndex++, ssc_mother);
            stmt.setString(parameterIndex++, gender);
			stmt.setString(parameterIndex++, ssc_board_name);
            stmt.setString(parameterIndex++, trackid);
			stmt.setString(parameterIndex++, contact_no);
			
			
			stmt.setString(parameterIndex++, pin);
			stmt.setString(parameterIndex++, paytime);
			stmt.setString(parameterIndex++, paymobile);

			stmt.registerOutParameter(parameterIndex, java.sql.Types.INTEGER); 
            
			stmt.executeUpdate();
			if( stmt.getInt(parameterIndex)==1)
				tmp = 1;
		}
		catch (Exception e){
//			e.printStackTrace();
			System.out.println(e.toString());
		}
		finally{try{stmt.close();
		ConnectionManager.closeConnection(conn);
		} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 	
		return tmp;
	}
	static PreparedStatement stmtPaymentDataInsert3 = null;
	static String sqlPaymentDataInsert3 = "insert into APPLICATION_PAYMENT_WEB (ROLL_NO, BOARD_ID, PASSING_YEAR, GPA , TT_TRANS_NUMBER, APPLICATION_ID, CONTACT_NO) values ( ?,?,?,?,?,?,?)";
	
	public static synchronized int PaymentOapplication3(String app_id,String ssc_roll,String ssc_board,String ssc_year,String ssc_regno,String ssc_group,String ssc_gpa
			,String ssc_eiin,String ssc_name,String ssc_father,String ssc_mother,String gender,String ssc_board_name
			 ,String trackid,String contact_no, String pin, String paytime, String paymobile)
    {
		int tmp = 0;
           try {
	            
		            if(stmtPaymentDataInsert3==null)
		            	stmtPaymentDataInsert3 = ConnectionManager.getConnectionStatic().prepareStatement(sqlPaymentDataInsert3);
		            int parameterIndex = 1;
		            stmtPaymentDataInsert3.setString(parameterIndex++, ssc_roll);
		            stmtPaymentDataInsert3.setInt(parameterIndex++, Integer.parseInt(ssc_board));
		            stmtPaymentDataInsert3.setInt(parameterIndex++, Integer.parseInt(ssc_year));
		            stmtPaymentDataInsert3.setFloat(parameterIndex++, Float.parseFloat(ssc_gpa));
		            
		            stmtPaymentDataInsert3.setString(parameterIndex++, trackid);
		            stmtPaymentDataInsert3.setString(parameterIndex++, app_id);
		            stmtPaymentDataInsert3.setString(parameterIndex++, contact_no);

		            tmp = stmtPaymentDataInsert3.executeUpdate();
		            stmtPaymentDataInsert3.clearParameters();
	            	
	            
	          } catch (SQLException e) {
	            System.out.println(e.toString());
	            stmtPaymentDataInsert3 = null;
	            ConnectionManager.closeStatic();
	        }	
	          return tmp;
    }
	
	static PreparedStatement stmtDeleteAPPCount = null;
	static String sqlDeleteAPPCount = "delete from app_count where APPLICANT_ID=?";
	
	public static synchronized int DeleteAPPCount(String app_id)
    {
		int tmp = 0;
           try {
	            
		            if(stmtDeleteAPPCount==null)
		            	stmtDeleteAPPCount = ConnectionManager.getConnectionStatic().prepareStatement(sqlDeleteAPPCount);
		            int parameterIndex = 1;
		            stmtDeleteAPPCount.setString(parameterIndex++, app_id);

		            tmp = stmtDeleteAPPCount.executeUpdate();
		            stmtDeleteAPPCount.clearParameters();
	            	
	            
	          } catch (SQLException e) {
	            System.out.println(e.toString());
	            stmtDeleteAPPCount = null;
	            ConnectionManager.closeStatic();
	        }	
	          return tmp;
    }
	

	
	static PreparedStatement stmtSMSUpdate = null;
	static PreparedStatement stmtSMSInsert = null;
	static String sqlSMSUpdate = "update application_sms set application='Y',sendtime=sysdate where application_id=?";
	static String sqlSMSInsert = "insert into application_sms (application_id,mobile_no,application,sendtime) values (?,?,'Y',sysdate)";
	
	public static synchronized int ApplicationSMSUpdate(String app_id,String mobile_no)
    {
		int tmp = 0;
           try {
	            
	            if(stmtSMSUpdate==null)
	            	stmtSMSUpdate = ConnectionManager.getConnectionStatic().prepareStatement(sqlSMSUpdate);
	            int parameterIndex = 1;
	            stmtSMSUpdate.setString(parameterIndex++, app_id);

	            tmp = stmtSMSUpdate.executeUpdate();
	            stmtSMSUpdate.clearParameters();
	            
	            if(tmp==0)
	            {
		            if(stmtSMSInsert==null)
		            	stmtSMSInsert = ConnectionManager.getConnectionStatic().prepareStatement(sqlSMSInsert);
		            parameterIndex = 1;
		            stmtSMSInsert.setString(parameterIndex++, app_id);
		            stmtSMSInsert.setString(parameterIndex++, mobile_no);

		            tmp = stmtSMSInsert.executeUpdate();
		            stmtSMSInsert.clearParameters();
	            	
	            }
	          } catch (SQLException e) {
	            System.out.println(e.toString());
	            stmtSMSUpdate = null;
	            stmtSMSInsert = null;
	            ConnectionManager.closeStatic();
	        }	
	          return tmp;
    }
	
	
	static PreparedStatement stmtSCODEUpdate = null;
	static String sqlSCODEUpdate = "update BOARD_DATA_SSC set SCODE=? where application_id=?";
	
	public static synchronized int sentSecurityCode(String app_id,String scode)
    {
		int tmp = 0;
           try {
	            
	            if(stmtSCODEUpdate==null)
	            	stmtSCODEUpdate = ConnectionManager.getConnectionStatic().prepareStatement(sqlSCODEUpdate);
	            int parameterIndex = 1;
	            stmtSCODEUpdate.setString(parameterIndex++, scode);
	            stmtSCODEUpdate.setString(parameterIndex++, app_id);

	            tmp = stmtSCODEUpdate.executeUpdate();
	            stmtSCODEUpdate.clearParameters();
	            

	          } catch (SQLException e) {
	            System.out.println(e.toString());
	            stmtSCODEUpdate = null;
	            ConnectionManager.closeStatic();
	        }	
	          return tmp;
    }
	
	
	static PreparedStatement stmtASUpdate = null;
	static String sqlASUpdate = "update APPLICANT_MESS set status='Y', SENDTIME=sysdate where APPLICATION_ID=? and MOBILE=?";
	
	public static synchronized int sentAppSMS(String app_id,String mob)
    {
		int tmp = 0;
           try {
	            
	            if(stmtASUpdate==null)
	            	stmtASUpdate = ConnectionManager.getConnectionStatic().prepareStatement(sqlASUpdate);
	            int parameterIndex = 1;	            
	            stmtASUpdate.setString(parameterIndex++, app_id);
	            stmtASUpdate.setString(parameterIndex++, mob);

	            tmp = stmtASUpdate.executeUpdate();
	            stmtASUpdate.clearParameters();
	            

	          } catch (SQLException e) {
	            System.out.println(e.toString());
	            stmtASUpdate = null;
	            ConnectionManager.closeStatic();
	        }	
	          return tmp;
    }
	
	
	
	
	static PreparedStatement stmtSendResultUpdate = null;
	static String sqlSendResultUpdate = "update SMS_RESULT set send='Y',sendtime=sysdate where application_id=? and mobile_no=?";
	
	public static synchronized int sendTextMSG_TT(String app_id,String mobile_no)
    {
		int tmp = 0;
           try {
	            
	            if(stmtSendResultUpdate==null)
	            	stmtSendResultUpdate = ConnectionManager.getConnectionStatic().prepareStatement(sqlSendResultUpdate);
	            int parameterIndex = 1;
	            stmtSendResultUpdate.setString(parameterIndex++, app_id);
	            stmtSendResultUpdate.setString(parameterIndex++, mobile_no);
	            tmp = stmtSendResultUpdate.executeUpdate();
	            stmtSendResultUpdate.clearParameters();
	            
	          } catch (SQLException e) {
	            System.out.println(e.toString());
	            stmtSendResultUpdate = null;
	            ConnectionManager.closeStatic();
	        }	
	          return tmp;
    }
	
	
	
	
	
	
	
	
	
	static PreparedStatement stmtReceiveResultUpdate = null;
	static String sqlReceiveResultUpdate = "update APPLICATION_SMS set result='Y',sendtime=sysdate where application_id=? and mobile_no=?";
	
	public static synchronized int sendReceiveResultUpdate(String app_id,String mobile_no)
    {
		int tmp = 0;
           try {
	            
	            if(stmtReceiveResultUpdate==null)
	            	stmtReceiveResultUpdate = ConnectionManager.getConnectionStatic().prepareStatement(sqlReceiveResultUpdate);
	            int parameterIndex = 1;
	            stmtReceiveResultUpdate.setString(parameterIndex++, app_id);
	            stmtReceiveResultUpdate.setString(parameterIndex++, mobile_no);
	            tmp = stmtReceiveResultUpdate.executeUpdate();
	            stmtReceiveResultUpdate.clearParameters();
	            
	          } catch (SQLException e) {
	            System.out.println(e.toString());
	            stmtReceiveResultUpdate = null;
	            ConnectionManager.closeStatic();
	        }	
	          return tmp;
    }
	
	static PreparedStatement stmtGetMobileNo = null;
	static ResultSet rsGetMobileNo = null;
	static String sqlGetMobileNo = "select mobile_no from APPLICATION_SMS where application_id=? and rownum<2";
	
	public static synchronized String getMobileNo(String app_id)
    {
		String tmp = "";
           try {
	            
	            if(stmtGetMobileNo==null)
	            	stmtGetMobileNo = ConnectionManager.getConnectionStatic().prepareStatement(sqlGetMobileNo);
	            int parameterIndex = 1;
	            stmtGetMobileNo.setString(parameterIndex++, app_id);
	            rsGetMobileNo = stmtGetMobileNo.executeQuery();
	            if(rsGetMobileNo.next())
	            	tmp = rsGetMobileNo.getString("mobile_no");
	            stmtGetMobileNo.clearParameters();
	            
	          } catch (SQLException e) {
	            System.out.println(e.toString());
	            stmtGetMobileNo = null;
	            ConnectionManager.closeStatic();
	        }	
	          return tmp;
    }
	
	public boolean InsertSMSapplication(String app_id,String ssc_roll,String ssc_board,String ssc_year,String ssc_regno,String ssc_group,String ssc_gpa
			,String ssc_eiin,String ssc_name,String ssc_father,String ssc_mother,String gender,String ssc_board_name
			 ,String trackid,String contact_no,String quota,String eiin,String shift,String medium,String group, String pin, String paytime, String paymobile)
	{
		boolean tmp=false;
		Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		try {
			stmt = (OracleCallableStatement) conn.prepareCall("{ call smspayment(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, app_id);
            stmt.setString(parameterIndex++, ssc_roll);
			stmt.setInt(parameterIndex++, Integer.parseInt(ssc_board));
            stmt.setInt(parameterIndex++, Integer.parseInt(ssc_year));
			stmt.setString(parameterIndex++, ssc_regno);
            stmt.setString(parameterIndex++, ssc_group);
			stmt.setString(parameterIndex++, ssc_gpa);
            stmt.setString(parameterIndex++, ssc_eiin);
			stmt.setString(parameterIndex++, ssc_name);
            stmt.setString(parameterIndex++, ssc_father);
			stmt.setString(parameterIndex++, ssc_mother);
            stmt.setString(parameterIndex++, gender);
			stmt.setString(parameterIndex++, ssc_board_name);
            stmt.setString(parameterIndex++, trackid);
			stmt.setString(parameterIndex++, contact_no);
			
			stmt.setString(parameterIndex++, quota);
			stmt.setString(parameterIndex++, eiin);
			stmt.setString(parameterIndex++, shift);
			stmt.setString(parameterIndex++, medium);
			stmt.setString(parameterIndex++, group);
			
			stmt.setString(parameterIndex++, pin);
			stmt.setString(parameterIndex++, paytime);
			stmt.setString(parameterIndex++, paymobile);

			stmt.registerOutParameter(parameterIndex, java.sql.Types.INTEGER); 
            
			stmt.executeUpdate();
			if( stmt.getInt(parameterIndex)==1)
				tmp = true;
		}
		catch (Exception e){
//			e.printStackTrace();
			System.out.println(e.toString());
		}
		finally{try{stmt.close();
		ConnectionManager.closeConnection(conn);
		} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 	
		return tmp;
	}

	public int InsertSMSapplication_2018(String ssc_roll,String ssc_board,String ssc_year,String ssc_regno,
			String trackid,String contact_no,String quota,String eiin,String shift,String medium,String group, String pin, String paytime, String paymobile)
	{
		int code=0;
		Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		contact_no=contact_no.substring(contact_no.length()-11);
		try {
			stmt = (OracleCallableStatement) conn.prepareCall("{ call sms_app_payment(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");
			int parameterIndex = 1;
			//stmt.setString(parameterIndex++, app_id);
            stmt.setString(parameterIndex++, ssc_roll);
			stmt.setInt(parameterIndex++, Integer.parseInt(ssc_board));
            stmt.setInt(parameterIndex++, Integer.parseInt(ssc_year));
			stmt.setString(parameterIndex++, ssc_regno);
            /*
            stmt.setString(parameterIndex++, ssc_group);
			stmt.setString(parameterIndex++, ssc_gpa);
            stmt.setString(parameterIndex++, ssc_eiin);
			stmt.setString(parameterIndex++, ssc_name);
            stmt.setString(parameterIndex++, ssc_father);
			stmt.setString(parameterIndex++, ssc_mother);
            stmt.setString(parameterIndex++, gender);
			stmt.setString(parameterIndex++, ssc_board_name);
			*/
            stmt.setString(parameterIndex++, trackid);
			stmt.setString(parameterIndex++, contact_no);
			
			stmt.setString(parameterIndex++, quota);
			stmt.setString(parameterIndex++, eiin);
			stmt.setString(parameterIndex++, shift);
			stmt.setString(parameterIndex++, medium);
			stmt.setString(parameterIndex++, group);
			
			stmt.setString(parameterIndex++, pin);
			stmt.setString(parameterIndex++, paytime);
			stmt.setString(parameterIndex++, paymobile);

			stmt.registerOutParameter(parameterIndex++, java.sql.Types.INTEGER); 
			stmt.registerOutParameter(parameterIndex, java.sql.Types.VARCHAR); 
            
			stmt.executeUpdate();
			//if( stmt.getInt(parameterIndex-1)==1)
			//	tmp = true;
			code=stmt.getInt(parameterIndex-1);
			
			System.out.println(stmt.getString(parameterIndex));
				
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println(e.toString());
		}
		finally{try{stmt.close();
		ConnectionManager.closeConnection(conn);
		} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 	
		return code;
	}
	
public String getApplicationId(String ssc_roll_no,String ssc_board_id,String ssc_passing_year, String ssc_reg_no){
	
	String applicationId="";
	Connection conn = ConnectionManager.getReadConnection();
	String sql = "SELECT application_id " +
	"	  FROM application_info " +
	"	 WHERE (ssc_roll_no, ssc_board_id, ssc_passing_year) IN ( " +
	"              SELECT roll_no, board_id, passing_year " +
	"                FROM board_data_ssc " +
	"               WHERE roll_no = ? " +
	"                 AND board_id = ? " +
	"                 AND passing_year = ? " +
	"                 AND reg_no = ?) ";

	PreparedStatement stmt = null;
	ResultSet r = null;
	try
	{
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, ssc_roll_no);
		stmt.setString(2, ssc_board_id);
		stmt.setString(3, ssc_passing_year);
		stmt.setString(4, ssc_reg_no);
		
		r = stmt.executeQuery();		
		if(r.next())
		{
			applicationId=r.getString("application_id");

		}
	} 
	catch (Exception e){e.printStackTrace();}
	finally{
		try{
			stmt.close();
			conn.close();
			} 
		catch (Exception e){
				e.printStackTrace();
			}
		stmt = null;
		conn = null;
	}
	
	return applicationId;
	  
}
public String isEligibleForReleaseSlip(String application_id){
	
	String eligible="";
	Connection conn = ConnectionManager.getReadConnection();
	String sql = "Select isEligibleForReleaseSlip(?) eligible from dual";

	PreparedStatement stmt = null;
	ResultSet r = null;
	try
	{
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, application_id);
		r = stmt.executeQuery();		
		if(r.next())
		{
			eligible=r.getString("eligible");

		}
	} 
	catch (Exception e){e.printStackTrace();}
	finally{
		try{
			stmt.close();
			conn.close();
			} 
		catch (Exception e){
				e.printStackTrace();
			}
		stmt = null;
		conn = null;
	}
	
	return eligible;
	  
}

public String isEligibleForMigration(String application_id){
	
	String isEligible="false";
	Connection conn = ConnectionManager.getReadConnection();
	String sql = "Select * from BOARD_RESULT where APPLICATION_ID=? and IS_ACTIVE='1' and MERIT_TYPE IN (SELECT MAX(MERIT_TYPE) FROM BOARD_RESULT WHERE Application_Id=?)";

	PreparedStatement stmt = null;
	ResultSet r = null;
	try
	{
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, application_id);
		stmt.setString(2, application_id);
		r = stmt.executeQuery();		
		if(r.next())
		{
			isEligible="true";

		}
	} 
	catch (Exception e){e.printStackTrace();}
	finally{
		try{
			stmt.close();
			conn.close();
			} 
		catch (Exception e){
				e.printStackTrace();
			}
		stmt = null;
		conn = null;
	}
	
	return isEligible;
	  
}

public String isEligibleForBTEBApplication(String application_id){
	
	String eligible="N";
	Connection conn = ConnectionManager.getReadConnection();
	String sql = "select count(*) counts from board_result where (application_id, merit_type) in (" +
			" select application_id,max(merit_type) merit_type from board_result group by application_id" +
			") and application_id=?  and status='College Received'";

	PreparedStatement stmt = null;
	ResultSet r = null;
	try
	{
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, application_id);
		r = stmt.executeQuery();		
		if(r.next())
		{
			if(r.getInt("counts")==0)
				eligible = "Y";

		}
	} 
	catch (Exception e){e.printStackTrace();}
	finally{
		try{
			stmt.close();
			conn.close();
			} 
		catch (Exception e){
				e.printStackTrace();
			}
		stmt = null;
		conn = null;
	}
	
	return eligible;
	  
}
static PreparedStatement stmtResultByApplicant = null;
static ResultSet rsResultByApplicant = null;
static String sqlResultByApplicant = "  SELECT *" +
		"    FROM (SELECT T5.STUDENT_NAME," +
		"                 T1.APPLICANT_ID," +
		"                 T1.MERIT_TYPE," +
		"                 T1.MERIT_POSITTION," +
		"                 T5.ROLL_NO," +
		"                 T5.BOARD_ID," +
		"                 T5.PASS_YEAR," +
		"                 T5.REG_NO," +
		"                 T3.EIIN," +
		"                 T3.COLLEGE_NAME," +
		"                 T1.SHIFT_ID," +
		"                 T1.VERSION_ID," +
		"                 T4.GROUP_ID," +
		"                 T4.GROUP_NAME," +
		"                 t1.QUOTA_TYPE," +
		"                 'mtable' tab," +
		"                 t1.STATUS" +
		"            FROM BOARD_RESULT t1," +
		"                 mst_college t3," +
		"                 mst_group t4," +
		"                 board_data_ssc t5" +
		"           WHERE     substr(applicant_id,1,2) = T5.board_id" +
		"           and substr(applicant_id,3,4) = pass_year" +
		"           and substr(applicant_id,7) = roll_no" +
		"                 AND T1.EIIN = T3.EIIN" +
		"                 AND T1.GROUP_ID = T4.GROUP_ID" +
		"                 AND T5.ROLL_NO = ?" +
		"                 AND T5.BOARD_ID = ?" +
		"                 AND T5.PASS_YEAR = ?" +
		"                 AND T5.REG_NO = ?)" +
		" ORDER BY APPLICANT_ID," +
		"         tab," +
		"         merit_type," +
		"         eiin," +
		"         QUOTA_TYPE"; 
				
public synchronized static Result getResultByApplicant(String sscRoll, String sscBoard, String sscYear,String sscReg)
{
	Result tmpResult = null;
		
	try
	{
        if(stmtResultByApplicant==null)
        	stmtResultByApplicant = ConnectionManager.getConnectionStatic().prepareStatement(sqlResultByApplicant);
        int parameterIndex = 1;
        stmtResultByApplicant.setString(parameterIndex++, sscRoll.trim());
        stmtResultByApplicant.setInt(parameterIndex++, Integer.parseInt(sscBoard));
        stmtResultByApplicant.setInt(parameterIndex++, Integer.parseInt(sscYear));
        stmtResultByApplicant.setString(parameterIndex++, sscReg.trim());
//        stmtResultByApplicant.setString(parameterIndex++, sscRoll.trim());
//        stmtResultByApplicant.setInt(parameterIndex++, Integer.parseInt(sscBoard));
//        stmtResultByApplicant.setInt(parameterIndex++, Integer.parseInt(sscYear));
//		stmtResultByApplicant.setString(parameterIndex++, sscReg.trim());
		rsResultByApplicant = stmtResultByApplicant.executeQuery();
		
		if (rsResultByApplicant.next())
		{
			tmpResult = new Result();
			String tmpQuota = "";
			if(rsResultByApplicant.getString("quota_type") == null)
				tmpQuota="GENERAL";
			else if(rsResultByApplicant.getString("quota_type").trim().equalsIgnoreCase("GEN"))
				tmpQuota = "GENERAL" ;
			else
				tmpQuota = rsResultByApplicant.getString("quota_type");
			
			tmpResult.setAPPLICATION_ID(rsResultByApplicant.getString("APPLICANT_ID"));
			tmpResult.setName(rsResultByApplicant.getString("STUDENT_NAME"));
			tmpResult.setSSC_ROLL(rsResultByApplicant.getString("ROLL_NO"));
			tmpResult.setSSC_BOARD(rsResultByApplicant.getString("BOARD_ID"));
			tmpResult.setSSC_PASSING_YEAR(rsResultByApplicant.getString("PASS_YEAR"));
			tmpResult.setSSC_REG(rsResultByApplicant.getString("REG_NO"));
			tmpResult.setMerit_type(rsResultByApplicant.getString("MERIT_TYPE"));
			tmpResult.setQUOTA_TYPE(tmpQuota);
			tmpResult.setMerit_position(rsResultByApplicant.getString("MERIT_POSITTION"));
			tmpResult.setEIIN(rsResultByApplicant.getString("EIIN"));
			tmpResult.setCOLLEGE_NAME(rsResultByApplicant.getString("COLLEGE_NAME"));
			tmpResult.setSHIFT_ID(rsResultByApplicant.getString("SHIFT_ID"));
			tmpResult.setVERSION_ID(rsResultByApplicant.getString("VERSION_ID"));
			tmpResult.setGROUP_ID(rsResultByApplicant.getString("GROUP_ID"));
			tmpResult.setGROUP_NAME(rsResultByApplicant.getString("GROUP_NAME"));
//			tmpResult.setContact(rsResultByApplicant.getString("CONTACT_NO"));
//			tmpResult.setAppliedweb(rsResultByApplicant.getString("APPLIEDWEB"));
//			tmpResult.setAppliedsms(rsResultByApplicant.getString("APPLIEDSMS"));
//			tmpResult.setAm(rsResultByApplicant.getString("AUTO_MIGRATION"));
			tmpResult.setStatus(rsResultByApplicant.getString("STATUS"));
			
		}
//		else
//		{
//			System.out.println(sqlResultByApplicant + ":"+sscRoll+ ":"+sscBoard+ ":"+sscYear+ ":"+sscReg);
//		}
		stmtResultByApplicant.clearParameters();
	} 
	catch (Exception e){
        System.out.println(e.toString());
        stmtResultByApplicant = null;
        ConnectionManager.closeStatic();
	}
	
//	if(tmpResult.getAPPLICATION_ID()!=null)System.out.println("From Database");
	return tmpResult; 
}
static PreparedStatement stmtResultByApplicant_Final = null;
static ResultSet rsResultByApplicant_Final = null;
static String sqlResultByApplicant_Final = "select * from BOARD_RESULT_Delwar_Final where ROLL_NO = ? AND BOARD_ID = ? AND PASSING_YEAR = ? AND REG_NO = ?";
//static String sqlResultByApplicant_Final = "select * from (" +
//		"select tmp1.*,COLLEGE_NAME,GROUP_NAME from (" +
//		"  SELECT *" +
//		"    FROM (SELECT T2.STUDENT_NAME," +
//		"                 T2.APPLICATION_ID," +
//		"                 T1.MERIT_TYPE," +
//		"                 0 MERIT_POSITTION," +
//		"                 T2.ROLL_NO," +
//		"                 T2.BOARD_ID," +
//		"                 T2.PASSING_YEAR," +
//		"                 T2.REG_NO," +
//		"                 T1.EIIN," +
//		"                 T1.SHIFT_ID," +
//		"                 T1.VERSION_ID," +
//		"                 T1.GROUP_ID," +
//		"                 t1.QUOTA_TYPE," +
//		"                 t2.contact_no," +
//		"                 t2.appliedweb," +
//		"                 t2.appliedsms," +
//		"                 t1.AUTO_MIGRATION," +
//		"                 t1.status," +
//		"                 decode(t1.status,'MIGRATED','YES',t1.is_paid) is_paid," +
//		"                 'Not Received' college_receive" +
//		"            FROM (select * from BOARD_RESULT where COLLEGE_RECEIVE='College Received') t1," +
//		"                 (select * from board_data_ssc  where ROLL_NO = ? AND BOARD_ID = ? AND PASSING_YEAR = ? AND REG_NO = ?) t2" +
//		"           WHERE     T1.APPLICATION_ID = T2.APPLICATION_ID and contact_no is not null" +
//		"                 )" +
//		") tmp1, mst_college tmp2,mst_group tmp3 where to_char(tmp1.EIIN) = tmp2.EIIN AND tmp1.GROUP_ID = tmp3.GROUP_ID";


public synchronized static Result getResultByApplicant_Final(String sscRoll, String sscBoard, String sscYear,String sscReg)
{
	Result tmpResult = null;
		
	try
	{
        if(stmtResultByApplicant_Final==null)
        	stmtResultByApplicant_Final = ConnectionManager.getConnectionStatic().prepareStatement(sqlResultByApplicant_Final);
        int parameterIndex = 1;
        stmtResultByApplicant_Final.setString(parameterIndex++, sscRoll.trim());
        stmtResultByApplicant_Final.setInt(parameterIndex++, Integer.parseInt(sscBoard));
        stmtResultByApplicant_Final.setInt(parameterIndex++, Integer.parseInt(sscYear));
        stmtResultByApplicant_Final.setString(parameterIndex++, sscReg.trim());
		rsResultByApplicant_Final = stmtResultByApplicant_Final.executeQuery();
		
		if (rsResultByApplicant_Final.next())
		{
			tmpResult = new Result();
			String tmpQuota = "";
			if(rsResultByApplicant_Final.getString("quota_type") == null)
				tmpQuota=".";
			else if(rsResultByApplicant_Final.getString("quota_type").trim().equalsIgnoreCase("GEN"))
				tmpQuota = "GENERAL" ;
			else
				tmpQuota = rsResultByApplicant_Final.getString("quota_type");
			
			tmpResult.setAPPLICATION_ID(rsResultByApplicant_Final.getString("APPLICATION_ID"));
			tmpResult.setName(rsResultByApplicant_Final.getString("STUDENT_NAME"));
			tmpResult.setSSC_ROLL(rsResultByApplicant_Final.getString("ROLL_NO"));
			tmpResult.setSSC_BOARD(rsResultByApplicant_Final.getString("BOARD_ID"));
			tmpResult.setSSC_PASSING_YEAR(rsResultByApplicant_Final.getString("PASSING_YEAR"));
			tmpResult.setSSC_REG(rsResultByApplicant_Final.getString("REG_NO"));
			tmpResult.setMerit_type(rsResultByApplicant_Final.getString("MERIT_TYPE"));
			tmpResult.setQUOTA_TYPE(tmpQuota);
			tmpResult.setMerit_position(rsResultByApplicant_Final.getString("MERIT_POSITTION"));
			tmpResult.setEIIN(rsResultByApplicant_Final.getString("EIIN"));
			tmpResult.setCOLLEGE_NAME(rsResultByApplicant_Final.getString("COLLEGE_NAME"));
			tmpResult.setSHIFT_ID(rsResultByApplicant_Final.getString("SHIFT_ID"));
			tmpResult.setVERSION_ID(rsResultByApplicant_Final.getString("VERSION_ID"));
			tmpResult.setGROUP_ID(rsResultByApplicant_Final.getString("GROUP_ID"));
			tmpResult.setGROUP_NAME(rsResultByApplicant_Final.getString("GROUP_NAME"));
			tmpResult.setContact(rsResultByApplicant_Final.getString("CONTACT_NO"));
			tmpResult.setAppliedweb(rsResultByApplicant_Final.getString("APPLIEDWEB"));
			tmpResult.setAppliedsms(rsResultByApplicant_Final.getString("APPLIEDSMS"));
			tmpResult.setAm(rsResultByApplicant_Final.getString("AUTO_MIGRATION"));
			tmpResult.setStatus(rsResultByApplicant_Final.getString("STATUS"));
			
		}
//		else
//		{
//			System.out.println(sqlResultByApplicant + ":"+sscRoll+ ":"+sscBoard+ ":"+sscYear+ ":"+sscReg);
//		}
		stmtResultByApplicant_Final.clearParameters();
	} 
	catch (Exception e){
        System.out.println(e.toString());
        stmtResultByApplicant_Final = null;
        ConnectionManager.closeStatic();
	}
	
	if(tmpResult.getAPPLICATION_ID()!=null)System.out.println("From Database");
	return tmpResult; 
}




static PreparedStatement stmtRegPayment = null;
static ResultSet rsRegPayment = null;
static String sqlRegPayment = "select count(*) counts from BOARD_RESULT t1,application_info t2 where T1.APPLICATION_ID = T2.APPLICATION_ID and " +
		" IS_PAID='YES' AND SSC_ROLL_NO = ? AND SSC_BOARD_ID = ? AND SSC_PASSING_YEAR = ? AND SSC_REG = ?";

public synchronized static int getRegPayment(String sscRoll, String sscBoard, String sscYear,String sscReg)
{
	int tmpResult = 0;
		
	try
	{
	    if(stmtRegPayment==null)
	    	stmtRegPayment = ConnectionManager.getConnectionStatic().prepareStatement(sqlRegPayment);
	    int parameterIndex = 1;
	    stmtRegPayment.setString(parameterIndex++, sscRoll.trim());
	    stmtRegPayment.setInt(parameterIndex++, Integer.parseInt(sscBoard));
	    stmtRegPayment.setInt(parameterIndex++, Integer.parseInt(sscYear));
	    stmtRegPayment.setString(parameterIndex++, sscReg.trim());
	    rsRegPayment = stmtRegPayment.executeQuery();
		
		if (rsRegPayment.next())
		{
			tmpResult = rsRegPayment.getInt("COUNTS");
		}
		stmtRegPayment.clearParameters();
	} 
	catch (Exception e){
	    System.out.println(e.toString());
	    stmtRegPayment = null;
	    ConnectionManager.closeStatic();
	}
	return tmpResult; 
}

static PreparedStatement stmtRegPaymentRec = null;
static ResultSet rsRegPaymentRec = null;
static String sqlRegPaymentRec = "SELECT IS_PAID, COLLEGE_RECEIVE" +
		"  FROM (SELECT *" +
		"          FROM board_result" +
		"         WHERE (APPLICANT_ID, merit_type) IN" +
		"                  (  SELECT APPLICANT_ID, MAX (merit_type)" +
		"                       FROM BOARD_RESULT" +
		"                   GROUP BY APPLICANT_ID)) t1," +
		"       board_data_ssc t2" +
		" WHERE     t1.applicant_id = T2.BOARD_ID||T2.PASS_YEAR||T2.ROLL_NO " +
		"       AND ROLL_NO = ?" +
		"       AND BOARD_ID = ?" +
		"       AND PASS_YEAR = ?" +
		"       AND REG_NO = ?";

public synchronized static ResultDTO getRegPaymentRec(String sscRoll, String sscBoard, String sscYear,String sscReg)
{
	ResultDTO tmpResult = null;
		
	try
	{
	    if(stmtRegPaymentRec==null)
	    	stmtRegPaymentRec = ConnectionManager.getConnectionStatic().prepareStatement(sqlRegPaymentRec);
	    int parameterIndex = 1;
	    stmtRegPaymentRec.setString(parameterIndex++, sscRoll.trim());
	    stmtRegPaymentRec.setInt(parameterIndex++, Integer.parseInt(sscBoard));
	    stmtRegPaymentRec.setInt(parameterIndex++, Integer.parseInt(sscYear));
	    stmtRegPaymentRec.setString(parameterIndex++, sscReg.trim());
	    rsRegPaymentRec = stmtRegPaymentRec.executeQuery();
		
		if (rsRegPaymentRec.next())
		{
			tmpResult = new ResultDTO();
			tmpResult.setPaid(rsRegPaymentRec.getString("IS_PAID"));
			tmpResult.setCollegereceive(rsRegPaymentRec.getString("COLLEGE_RECEIVE"));
		}
		stmtRegPaymentRec.clearParameters();
	} 
	catch (Exception e){
	    System.out.println(e.toString());
	    stmtRegPaymentRec = null;
	    ConnectionManager.closeStatic();
	}
	return tmpResult; 
}


//    private boolean sendSMS(String mobile, String appid, String pass, String name)
//    {
//        try
//        {           
//            URL yahoo;
//            yahoo = new URL("http://123.49.3.58:8081/web_send_sms.php?ms="+URLEncoder.encode("88"+mobile)+
//                        "&txt="+URLEncoder.encode("ONLINE E APNAR ABEDONER STATUS, PRIORITY JANTE LOGIN KORUN: xiclassadmission.gov.bd " +
//                        		"User Id: " + appid + " Password: "+pass)+"&username="+URLEncoder.encode("IICT")+
//                        "&password="+URLEncoder.encode("iict2341"));
//            URLConnection yc = yahoo.openConnection();
//            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
//            String inputLine;
//            String inputLine1="";
//            while ((inputLine = in.readLine()) != null)
//            {
//                System.out.println(inputLine);
//                if(inputLine!=null)
//                    inputLine1+=inputLine;
//            }
//            in.close();
//            System.out.println("SMS=="+inputLine1);
//            if(inputLine1.startsWith("<br><br>Received"))
//                return true;
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        return false;
//    }
 


public ResponseDTO makeWebPayment(ResponseDTO applicant ,String scode)
{
	ResponseDTO response =new ResponseDTO();
	Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
	OracleCallableStatement stmt = null;
	try {
		stmt = (OracleCallableStatement) conn.prepareCall("{ call makeWebPayment(?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");
        int parameterIndex = 1;
        
        stmt.setString(parameterIndex++, applicant.getRoll());
        stmt.setInt(parameterIndex++, Integer.parseInt(applicant.getBoard()));
        stmt.setInt(parameterIndex++, Integer.parseInt(applicant.getYear()));
        stmt.setString(parameterIndex++, applicant.getReg());            
        stmt.setString(parameterIndex++, applicant.getContactno().substring(applicant.getContactno().length()-11));
        stmt.setString(parameterIndex++, applicant.getTrxid());
        stmt.setString(parameterIndex++, applicant.getPaytime());
        stmt.setString(parameterIndex++, applicant.getPaymobile().substring(applicant.getPaymobile().length()-11));
        stmt.setString(parameterIndex++, applicant.getOrganization()); 
        stmt.setString(parameterIndex++, scode);
        stmt.registerOutParameter(parameterIndex++, java.sql.Types.INTEGER); 
        stmt.registerOutParameter(parameterIndex++, java.sql.Types.VARCHAR);
        stmt.registerOutParameter(parameterIndex++, java.sql.Types.VARCHAR);
        stmt.registerOutParameter(parameterIndex++, java.sql.Types.VARCHAR);
        stmt.executeUpdate();
        System.out.println(stmt.getString(12)+" :sdfsdfsd");
        if( stmt.getInt(11)==1){//17
        	response.setStatus("OK");
        	response.setMessage("Data inserted successfully");
        }
        else{
        	response.setStatus("ERROR");
        	
        	if(stmt.getString(12).contains("APPLICANT_INFO_UK"))
        		response.setMessage("Your mobile number is used by another applicant, please contact to your board");//18
        	else       //if(stmt.getString(12).contains("APPLICANT_INFO_PK"))
        		response.setMessage("Your payment data is invalid, please contact to your board");//18
          }
	} 
	catch (Exception e){e.printStackTrace();
		response.setStatus("ERROR");
		response.setMessage(e.getMessage());
	}
	finally{try{
		stmt.close();
		ConnectionManager.closeConnection(conn);
		} catch (Exception e)
	{e.printStackTrace();}stmt = null;conn = null;} 		

    return response;
    

}
static PreparedStatement stmtReg = null;
static ResultSet rsReg = null;
static String sqlReg = "select reg_no from BOARD_DATA_SSC where ROLL_NO=? and BOARD_ID=? and PASS_YEAR=?";
public static synchronized String getREGNO(String roll, String board, String year)
{
	String reg_no = "";
	try {
		if(stmtReg==null)
			stmtReg = ConnectionManager.getConnectionStatic().prepareStatement(sqlReg);
		int parameterIndex = 1;
		stmtReg.setString(parameterIndex++, roll);
		stmtReg.setInt(parameterIndex++, Integer.parseInt(board));
		stmtReg.setInt(parameterIndex++, Integer.parseInt(year));
		rsReg = stmtReg.executeQuery();
		stmtReg.clearParameters();
		if(rsReg.next())
		{
			reg_no = rsReg.getString("reg_no");
		}
	} catch (Exception e) {
		System.out.println(e.toString());
		stmtReg = null;
		ConnectionManager.closeStatic();
	}	
	return reg_no;
}











	static PreparedStatement stmtSMS = null;
	static ResultSet rsSMS = null;
	static String sqlSMS = "Select appid, mobile, text from SMSFirstSMS where status='N' order by mobile";
	
	public static void sendSMS()
	{
//		try {
//			if(stmtSMS==null)
//				stmtSMS = ConnectionManager.getConnectionStatic().prepareStatement(sqlSMS);
//			rsSMS = stmtSMS.executeQuery();
//			int total = 0;
//			if(rsSMS.next())
//			{
//				SmsSender smsSender=new SmsSender();
//				smsSender.setAppid(rsSMS.getString("appid"));
//				smsSender.setMobile(rsSMS.getString("MOBILE"));
//				smsSender.setText(rsSMS.getString("TEXT"));
//				smsSender.setOperation("StaticSMS");
//				Thread thread = new Thread(smsSender);
//				thread.start();
//				total++;
//				if(total==50)
//				{
//					total=0;
//					try{Thread.sleep(5000);}catch(Exception e){e.printStackTrace();};
//				}
//			}
//		} catch (Exception e) {
//			System.out.println(e.toString());
//			stmtSMS = null;
//			ConnectionManager.closeStatic();
//		}	
	}
	
	static PreparedStatement stmtSMSPopulate = null;
	static String sqlSMSPopulate = "select APPLICANT_ID,contact_no,'Your ('||ROLL_NO||') security code is '||scode||' - Board Authority' from APPLICANT_INFO where APPLICANT_ID in (" +
			" select APPLICANT_ID from APPLICANT_INFO where HAS_WEB_TRNS='Y' and applied_web='N' and applied_sms='Y'" +
			" minus" +
			" select APPID from SMSFIRSTSMS)";
	public static void sendSMSPopulate()
	{
	

		
		try {
			if(stmtSMSPopulate==null)
				stmtSMSPopulate = ConnectionManager.getConnectionStatic().prepareStatement(sqlSMSPopulate);
			stmtSMSPopulate.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.toString());
			stmtSMSPopulate = null;
			ConnectionManager.closeStatic();
		}	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	static PreparedStatement stmtStaticSMSUpdate = null;
	static String sqlStaticSMSUpdate = "update SMSFirstSMS set status='Y',sendtime=sysdate where appid=?";
	
	public static synchronized int StaticSMSUpdate(String appid)
    {
		int tmp = 0;
           try {
	            
	            if(stmtStaticSMSUpdate==null)
	            	stmtStaticSMSUpdate = ConnectionManager.getConnectionStatic().prepareStatement(sqlStaticSMSUpdate);
	            int parameterIndex = 1;
	            stmtStaticSMSUpdate.setString(parameterIndex++, appid);

	            tmp = stmtStaticSMSUpdate.executeUpdate();
	            stmtStaticSMSUpdate.clearParameters();
	            
	          } catch (SQLException e) {
	            System.out.println(e.toString());
	            stmtStaticSMSUpdate = null;
	            ConnectionManager.closeStatic();
	        }	
	          return tmp;
    }




	
}
