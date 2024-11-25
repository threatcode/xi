package edu.action.college;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import edu.action.common.BaseAction;
import edu.dao.UserDAO;
import edu.dao.college.AdmissionDAO;
import edu.dto.UserDTO;
import edu.dto.application.SscInfoDTO;
import edu.dto.board.ApplicantInfoBoardDTO;
import edu.dto.college.ApplicantInfoDTO;
import edu.dto.college.CollegeCourseDTO;
import edu.dto.college.CollegeDTO;
import edu.helpers.SmsSender;



public class Admission extends BaseAction{
	
	private String from_where;
	private String errorMessage; 
	private String successMessage;
	
	
	private String eiinCode;
	private String pMerit;
	private String pShift_id;
	private String pVersion_id;
	private String pGroup_id;
	private String pRoll;
	private String applicationID;
	private String dataString;
	
	private String roll_no;
	private String board_id;
	private String passing_year;
	private String reg_no;
	
	   private File myFile;
	   private String myFileContentType;
	   private String myFileFileName;
	   private String destPath;

	private static final long serialVersionUID = 2775177229066309566L;
	
	public String alreadyAdded(){		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		AdmissionDAO nonApprovedStudentListOfMeritDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> lstAlreadyAdded = nonApprovedStudentListOfMeritDAO.getAlreadyAddred(Integer.parseInt(pShift_id),Integer.parseInt(pVersion_id),
				Integer.parseInt(pGroup_id),userDto.getEiin());
		request.setAttribute("lstAlreadyAdded", lstAlreadyAdded);
		request.setAttribute("userInfo", userDto);
		return "success";
	}	
	
	public String showStudent(){		
//		CollegeDTO userDto = (CollegeDTO) session.get("user");
//		if(userDto == null){
//			return "input";
//		}
		AdmissionDAO nonApprovedStudentListOfMeritDAO = new AdmissionDAO();
//		if(nonApprovedStudentListOfMeritDAO.checAdmission(roll_no, Integer.parseInt(board_id),Integer.parseInt(passing_year))>0)
//		{
//			request.setAttribute("ErrorMassage", "This student has admission!!!");
//			return "success";
//		}
//		else
//		{
			SscInfoDTO lststudent = nonApprovedStudentListOfMeritDAO.showStudent(roll_no,Integer.parseInt(board_id),Integer.parseInt(passing_year),reg_no);
			request.setAttribute("lststudent", lststudent);
			return "success";
//		}
	}	
	
	public String addStudent(){		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		AdmissionDAO nonApprovedStudentListOfMeritDAO = new AdmissionDAO();
		int tmp = nonApprovedStudentListOfMeritDAO.addStudent(Integer.parseInt(pShift_id),Integer.parseInt(pVersion_id),
				Integer.parseInt(pGroup_id),userDto.getEiin(),roll_no,Integer.parseInt(board_id),Integer.parseInt(passing_year));
		if(tmp>0)
		{
			try{
				if(tmp==123)
				{
					response.setContentType("text/html");
					response.getWriter().write("<font color='red' size='5'>This Student Already Inserted!!!</font>");
				}
				else if(tmp==124)
				{
					response.setContentType("text/html");
					response.getWriter().write("<font color='red' size='5'>THIS APPLICANT HAS ANOTHER COLLEGE APPROVAL!!!</font>");
				}
				else
				{
					response.setContentType("text/html");
					response.getWriter().write("<font color='green' size='5'>Data Insert Succesfull!!!</font>");
				}
			}
			catch(Exception e) {e.printStackTrace();}
		}
		else
		{
			try{
		    	 response.setContentType("text/html");
		    	 response.getWriter().write("<font color='red' size='5'>Data Insert Error!!!</font>");
			}
			catch(Exception e) {e.printStackTrace();}
		}
		
		return null;
	}
	
	public String deleteAdded(){		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
//		System.out.println(eiinCode.split("###")[0]);
//		System.out.println(eiinCode.split("###")[1]);
//		System.out.println(eiinCode.split("###")[2]);
		
		try
		{
		AdmissionDAO nonApprovedStudentListOfMeritDAO = new AdmissionDAO();
		int tmp = nonApprovedStudentListOfMeritDAO.deleteAdded( applicationID);
		if(tmp>0)
		{
			try{
				response.setContentType("text/html");
				response.getWriter().write("<font color='green' size='5'>Data Delete Succesfull!!!</font>");
				return null;
			}
			catch(Exception e) {e.printStackTrace();}
		}
		else
		{
			try{
		    	 response.setContentType("text/html");
		    	 response.getWriter().write("<font color='red' size='5'>Data Delete Error!!!</font>");
		    	 return null;
			}
			catch(Exception e) {e.printStackTrace();}
		}
		}
		catch(Exception e1)
		{
			if(e1.getMessage().contains("THIS APPLICANT HAS REGISTRATION!!!"))
			{
				try{
					response.setContentType("text/html");
		  	  	 	response.getWriter().write("<font color='red' size='5'>THIS APPLICANT HAS REGISTRATION!!!</font>");
		    	 	return null;
				}
				catch(Exception e) {e.printStackTrace();}
			}
		}
		
		return null;
	}
	
	
	public String nonApprovedStudentListOfMerit1(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		AdmissionDAO nonApprovedStudentListOfMeritDAO = new AdmissionDAO();
		/*
		ApplicantInfoDTO seatInfo=nonApprovedStudentListOfMeritDAO.getSeatInfo(pShift_id,pVersion_id,pGroup_id,userDto.getEiin());
		if(seatInfo!=null)
		{
			request.setAttribute("totalSeat", seatInfo.getTotalSeat());
			request.setAttribute("availableSeat", seatInfo.getAvailableSeat());
		}
		else
		{
			request.setAttribute("totalSeat", "No Available Data");
			request.setAttribute("availableSeat", "No Available Data");
		}

		if(!pMerit.equalsIgnoreCase("4"))
			pRoll = null;
		*/
		
		List<ApplicantInfoDTO> nonApprovedStudentListOfMerit = nonApprovedStudentListOfMeritDAO.getNonApprovedStudentListOfMerit1(pShift_id,pVersion_id,pGroup_id,userDto.getEiin());
		request.setAttribute("nonApprovedStudentListOfMerit", nonApprovedStudentListOfMerit);
		request.setAttribute("hiddenShiftID", pShift_id);
		request.setAttribute("hiddenVersionID", pVersion_id);
		request.setAttribute("hiddenGroupID", pGroup_id);
		request.setAttribute("userInfo", userDto);
		
		request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
	    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
	    
		
		return "success";

				
	}	

	public String nonApprovedStudentListOfMerit(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
/*		if(userDto.getMobileVerifiedYN().equalsIgnoreCase("N"))
			return "validate";*/
		this.from_where="nonApprovedStudentListOfMerit";
		//OTP Checking
/*		Boolean otpCheck = (Boolean)session.get("otp_validation");
	    if(otpCheck == null || otpCheck == false)
	    	return "optform";*/

		
		AdmissionDAO nonApprovedStudentListOfMeritDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> nonApprovedStudentListOfMerit = nonApprovedStudentListOfMeritDAO.getNonApprovedStudentListOfMerit(userDto.getEiin());
		request.setAttribute("nonApprovedStudentListOfMerit", nonApprovedStudentListOfMerit);
		
		request.setAttribute("userInfo", userDto);
		
		request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
	    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
	    
		
		return "success";

				
	}
	


	public String receiveNonApproveStudentOfMerit()
	{	
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}		
		String[] applicants = dataString.split("###"); 
		ArrayList<JSONObject> listResponseJSON = new ArrayList<JSONObject>();
		
		JSONObject jsonObj = new JSONObject();
		try {
			String[] response = new String[applicants.length]; 
			String[] application_ID =  new String[applicants.length];
			String[] merit_type =  new String[applicants.length];
			String[] shift_id =  new String[applicants.length];
			String[] version_id =  new String[applicants.length];
			String[] group_id =  new String[applicants.length];
//			String[] pin_number =  new String[applicants.length];
			String[] quota =  new String[applicants.length];
			for (int i = 0; i < applicants.length; i++) {
				String[] applicantData = applicants[i].split("#");
				application_ID[i] = applicantData[0];
				merit_type[i] = applicantData[1];
				shift_id[i] = applicantData[2];
				version_id[i] = applicantData[3];
				group_id[i] = applicantData[4];
//				pin_number[i] = applicantData[8];
//				if(applicantData[9].equalsIgnoreCase("General"))quota[i]="GENERAL";
//				else if(applicantData[9].equalsIgnoreCase("Freedom Fighter Quota"))quota[i]="FREEDOM";
//				else if(applicantData[9].equalsIgnoreCase("Education Quota"))quota[i]="EDUCATION";
//				else if(applicantData[9].equalsIgnoreCase("Division/District Quota"))quota[i]="DISTRICT";
//				else if(applicantData[9].equalsIgnoreCase("Expatriate Quota"))quota[i]="FOREIGN";
//				else if(applicantData[9].equalsIgnoreCase("BKSP Quota"))quota[i]="BKSP";
//				else if(applicantData[9].equalsIgnoreCase("Special Quota"))quota[i]="SPECIAL";
//				else if(applicantData[9].equalsIgnoreCase("OWN Category"))quota[i]="OWN";
//				else quota[i] = applicantData[9];
			}
			
			AdmissionDAO receiveNonApproveStudentOfMeritDAO=new AdmissionDAO();
			response = receiveNonApproveStudentOfMeritDAO.receiveAddmission(application_ID,  merit_type, shift_id, version_id,
					group_id, quota, userDto.getEiin(), getIpAddressDTO());
			
			int counter=0;
			
			for (int i = 0; i < applicants.length; i++) {
				String[] applicantData = applicants[i].split("#");
				JSONObject tmp = new JSONObject();
				tmp.put("appid", applicantData[0]);
				tmp.put("appname", applicantData[6]);
				tmp.put("rowIndex", applicantData[7]);
				tmp.put("message", response[i]);
				listResponseJSON.add(tmp);
				
//				if(response[i].equalsIgnoreCase("Successfully approved."))
//				{
//					counter=counter+1;
//					
//					SmsSender smsSender=new SmsSender();
//					smsSender.setAppid(applicantData[0]);
//					smsSender.setText("Dear " + applicantData[6]+", your admission has been confirmed successfully. -- Board Authority");
//					smsSender.setOperation("sendResult_TT");
//					
//					Thread thread = new Thread(smsSender);
//					thread.start();
//				}
				
			}

			
			jsonObj.put("counter", counter);
			jsonObj.put("approved", listResponseJSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setJsonResponse(jsonObj.toString());
		return null;
//		CollegeDTO userDto = (CollegeDTO) session.get("user");
//		if(userDto == null){
//	//		System.out.println("User DTO NULL!!!!!!!!!!!!!!!!!!!!!!!!!!");
//			return "input";
//		}
//		
//		String hiddenShiftID=request.getParameter("hiddenShiftID");
//		String hiddenVersionID=request.getParameter("hiddenVersionID");
//		String hiddenGroupID=request.getParameter("hiddenGroupID");
//		String hiddenMerit=request.getParameter("hiddenMerit");
//		
//		String[] application_ID=null;
//		String[] merit_type=null;
//		String[] shift_id=null;
//		String[] version_id=null;
//		String[] group_id=null;
//		String[] pin_index=null;
//		String[] pin=null;
//		String[] pin_number=null;
//		boolean resp=false;
//
///*		String	applicationID[] =request.getParameterValues("applicationID");
//		String	pinNumber[] =request.getParameterValues("pinNumber");*/
//		
//	//	System.out.println("applicationID Problem!!!!!!!!!!!!!!!!!!!!!!!!!!");
////		System.out.println(pinNumber.length);
//		String	applicationID[]=dataString.split("###"); 
//	
//		if(applicationID!=null) {
//	//		System.out.println("applicationID Problem!!!!!!!!!!!!!!!!!!!!!!!!!!");
//			application_ID = new String[applicationID.length]; 
//			merit_type = new String[applicationID.length] ;
//			
//			shift_id = new String[applicationID.length] ;
//			version_id = new String[applicationID.length] ;
//			group_id = new String[applicationID.length] ;
//			
//			pin_index = new String[applicationID.length] ;
//		//	pin = new String[pinNumber.length];
//			pin_number = new String[applicationID.length] ;
//			
//			
//
//			
//			for(int i=0;i<applicationID.length;i++){
//	//			System.out.println("applicationID 1 Problem!!!!!!!!!!!!!!!!!!!!!!!!!!");				
//				String serialNo=applicationID[i];
//				String[] parts = serialNo.split("#");
//				String string1 = parts[0]; 
//				String string2 = parts[1];
//				String string3 = parts[2];
//				String string4 = parts[3];
//				String string5 = parts[4];
//			//	String string6 = parts[5];
//				String string6 = parts[6];
//
//				
//				application_ID[i]=string1;
//				merit_type[i]=string2;
//				
//				shift_id[i]=string3;
//				version_id[i]=string4;
//				group_id[i]=string5;
//				pin_number[i]=string6;
//	/*			pin_index[i]=string6;*/
//				
//			}
//			
///*			for(int j=0;j<pinNumber.length;j++){
//				
//	
//				pin[j]=pinNumber[j];
//	         	System.out.println(pin[j]);
//			
//				
//			}
//			
//			for(int k=0;k<applicationID.length;k++){
//				pin_number[k]=pin[Integer.parseInt(pin_index[k])];
//			
//			}*/
//			
//			AdmissionDAO receiveNonApproveStudentOfMeritDAO=new AdmissionDAO();
//	//		System.out.println("Database Procedure 1            !!!!!!!!!!!!!!!!!!!!!!!!!!");
//			resp=receiveNonApproveStudentOfMeritDAO.receiveAddmission(application_ID,pin_number,merit_type,shift_id,version_id,group_id,userDto.getEiin(), getIpAddressDTO());
//	//		System.out.println("Database Procedure 2            !!!!!!!!!!!!!!!!!!!!!!!!!!");
//			if (resp == true)
//			{
//				successMessage = "College Received Successfully";		
//			}
//			else
//			{
//				errorMessage = "Somthing Wrong !!!..Try again.";
//			}
//			
//
//			
//			AdmissionDAO nonApprovedStudentListOfMeritDAO = new AdmissionDAO();
//			List<ApplicantInfoDTO> nonApprovedStudentListOfMerit = nonApprovedStudentListOfMeritDAO.getNonApprovedStudentListOfMerit1(hiddenShiftID,hiddenVersionID,hiddenGroupID,hiddenMerit,userDto.getEiin());
//			request.setAttribute("nonApprovedStudentListOfMerit", nonApprovedStudentListOfMerit);
//			
//
//			
//			request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
//		    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
//		}
//		return "success";
			          

	}
	
	
	public String approvedStudentListOfMerit(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		
		AdmissionDAO approvedStudentListOfMeritDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> 	approvedStudentListOfMerit = approvedStudentListOfMeritDAO.getApprovedStudentListOfMerit(pShift_id,pVersion_id,pGroup_id,pMerit,userDto.getEiin());
		request.setAttribute("approvedStudentListOfMerit", approvedStudentListOfMerit);
		request.setAttribute("hiddenShiftID", pShift_id);
		request.setAttribute("hiddenVersionID", pVersion_id);
		request.setAttribute("hiddenGroupID", pGroup_id);
		request.setAttribute("hiddenMerit", pMerit);
		request.setAttribute("userInfo", userDto);
		
		request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
	    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
	    
		
		return "success";

				
	}
	
/*	public String approvedStudentListOfMerit(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		this.from_where="approvedStudentListOfMerit";
		//OTP Checking
		Boolean otpCheck = (Boolean)session.get("otp_validation");
	    if(otpCheck == null || otpCheck == false)
	    	return "optform";

		
		AdmissionDAO approvedStudentListOfMeritDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> 	approvedStudentListOfMerit = approvedStudentListOfMeritDAO.getApprovedStudentListOfMerit(userDto.getEiin());
		request.setAttribute("approvedStudentListOfMerit", approvedStudentListOfMerit);
		
		request.setAttribute("userInfo", userDto);
		
		request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
	    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
	    
		
		return "success";

				
	}*/
	
	public String totalApprovedStudentListOfMerit(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
/*		this.from_where="totalApprovedStudentListOfMerit";
		//OTP Checking
		Boolean otpCheck = (Boolean)session.get("otp_validation");
	    if(otpCheck == null || otpCheck == false)
	    	return "optform";*/

		
		AdmissionDAO approvedStudentListOfMeritDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> 	totalApprovedStudentListOfMerit = approvedStudentListOfMeritDAO.getTotalApprovedStudentListOfMerit(userDto.getEiin());
		request.setAttribute("totalApprovedStudentListOfMerit", totalApprovedStudentListOfMerit);
		
		request.setAttribute("userInfo", userDto);
		
		request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
	    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
	    
		
		return "success";

				
	}
	
	public String cancelApproveStudentOfMerit(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}		
		String[] applicants = dataString.split("###"); 
		ArrayList<JSONObject> listResponseJSON = new ArrayList<JSONObject>();
		
		JSONObject jsonObj = new JSONObject();
		try {
			String[] response = new String[applicants.length]; 
			String[] application_ID =  new String[applicants.length];
			String[] merit_type =  new String[applicants.length];
			String[] shift_id =  new String[applicants.length];
			String[] version_id =  new String[applicants.length];
			String[] group_id =  new String[applicants.length];
			String[] quota =  new String[applicants.length];
			String[] shift_name =  new String[applicants.length];
			String[] version_name =  new String[applicants.length];
			String[] group_name =  new String[applicants.length];

			for (int i = 0; i < applicants.length; i++) {
				String[] applicantData = applicants[i].split("#");
				application_ID[i] = applicantData[0];
				merit_type[i] = applicantData[1];
				shift_id[i] = applicantData[2];
				version_id[i] = applicantData[3];
				group_id[i] = applicantData[4];
				quota[i] = applicantData[6];
				shift_name[i] = applicantData[9];
				version_name[i] = applicantData[10];
				group_name[i] = applicantData[11];

			}
			
			AdmissionDAO receiveNonApproveStudentOfMeritDAO=new AdmissionDAO();
			response = receiveNonApproveStudentOfMeritDAO.cancelAddmission(application_ID, merit_type, shift_id, version_id,
					group_id,quota, userDto.getEiin(), getIpAddressDTO());
			
			for (int i = 0; i < applicants.length; i++) {

				
				String[] applicantData = applicants[i].split("#");
				UserDAO udao = new UserDAO();
				UserDTO tmpUDTO = udao.getApplicantContactNo(applicantData[0]);

				
				JSONObject tmp = new JSONObject();
				tmp.put("appid", applicantData[0]);
				tmp.put("appname", applicantData[7]);
				tmp.put("rowIndex", applicantData[8]);
				tmp.put("message", response[i]);
				tmp.put("cname", userDto.getCollege_name());
				tmp.put("eiin", userDto.getEiin());
				tmp.put("shift", shift_name[i]);
				tmp.put("version", version_name[i]);
				tmp.put("group", group_name[i]);
				tmp.put("quota", quota[i]);
				tmp.put("roll", tmpUDTO.getSsc_roll());
				listResponseJSON.add(tmp);
				
				if(response[i].equalsIgnoreCase("Successfully cancelled."))
				{
	            	SmsSender smsSender=new SmsSender();
					smsSender.setMobile(tmpUDTO.getMobile());
					smsSender.setText("Your (Roll:"+tmpUDTO.getSsc_roll()+") admission at "+userDto.getEiin()+
							" ("+shift_name[i]+", "+version_name[i]+", "+group_name[i]+") has been cancelled - Board Authority");
					smsSender.setOperation("CancelledSMS");
					Thread thread = new Thread(smsSender);
					thread.start();
				}
				
			}

			
			
			jsonObj.put("cancelled", listResponseJSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setJsonResponse(jsonObj.toString());
		return null;
		
		
/*		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		String[] application_ID=null;
		String[] merit_type=null;	
		boolean resp=false;

			String	applicationID[] =request.getParameterValues("applicationID");	
			if(applicationID!=null) {
		for(int i=0;i<applicationID.length;i++){
			
		    String serialNo=applicationID[i];
		    String[] parts = serialNo.split("#");
		    String string1 = parts[0]; 
		    String string2 = parts[1];
		    
		    
		    application_ID = new String[applicationID.length]; 
		    merit_type = new String[applicationID.length] ;
		    
		    
		    application_ID[i]=string1;
		    merit_type[i]=string2;
		    
		    
		    AdmissionDAO receiveNonApproveStudentOfMeritDAO=new AdmissionDAO();
			resp=receiveNonApproveStudentOfMeritDAO.cancelAddmission(application_ID, merit_type, userDto.getEiin(), getIpAddressDTO());
		}
			
		if (resp == true) {
			
			successMessage = "Admission Cancelled Successfully";		
		}

		else{
			errorMessage = "Somthing Wrong !!!..Try again.";
			
		    }
			}

			
			return "success";*/

				
			          

		}
	
	public String admissionCancelListOfMerit(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
/*		this.from_where="admissionCancelList";
		//OTP Checking
		Boolean otpCheck = (Boolean)session.get("otp_validation");
	    if(otpCheck == null || otpCheck == false)
	    	return "optform";*/

		
		AdmissionDAO admissionCancelListDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> 	admissionCancelListOfMerit = admissionCancelListDAO.getCancelledStudentListOfMerit(userDto.getEiin());
		request.setAttribute("admissionCancelListOfMerit", admissionCancelListOfMerit);
		
		request.setAttribute("userInfo", userDto);
		
		request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
	    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
	    
		
		return "success";

				
	}
	
	
	
	
	public String svgLoad(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		AdmissionDAO svgLoadDAO = new AdmissionDAO();
		List<CollegeCourseDTO> shiftList = svgLoadDAO.getShiftList(userDto.getEiin());
		request.setAttribute("shiftList", shiftList);
		List<CollegeCourseDTO> versionList = svgLoadDAO.getVersionList(userDto.getEiin());
		request.setAttribute("versionList", versionList);
		List<CollegeCourseDTO> groupList = svgLoadDAO.getGroupList(userDto.getEiin());
		request.setAttribute("groupList", groupList);
		
		request.setAttribute("userInfo", userDto);
				
		return "success";

				
	}
	
	public String svgLoad3(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		AdmissionDAO svgLoadDAO = new AdmissionDAO();
		List<CollegeCourseDTO> shiftList = svgLoadDAO.getShiftList(userDto.getEiin());
		request.setAttribute("shiftList", shiftList);
		List<CollegeCourseDTO> versionList = svgLoadDAO.getVersionList(userDto.getEiin());
		request.setAttribute("versionList", versionList);
		List<CollegeCourseDTO> groupList = svgLoadDAO.getGroupList(userDto.getEiin());
		request.setAttribute("groupList", groupList);
		
		request.setAttribute("userInfo", userDto);
				
		return "success";

				
	}
	
	
	
	String ErrorMassage="";
public String uploadXLSNonApprovedapplicant(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");

		destPath = servlet.getRealPath("");

        try{
       	 System.out.println("Src File name: " + myFile);
       	 System.out.println("Dst File name: " + myFileFileName);
       	    	 
       	 if(myFileFileName==null)
       		 {
       		  ErrorMassage=ErrorMassage+" Please Upload Excel file";
       		  request.setAttribute("ErrorMassage", ErrorMassage);
       		  return "success";      
       		 }
       	 
       	 File destFile  = new File(destPath, myFileFileName);
       	
      	 FileUtils.copyFile(myFile, destFile);
    
        }catch(IOException e){
           e.printStackTrace();
           return ERROR;
        }
        
         List<String> lsAppID=  new ArrayList<String>();
		 List<String> lsSSC_ROLL=  new ArrayList<String>();
		 
		 
        boolean isOk=readUploadedExcel(lsAppID,lsSSC_ROLL,destPath+"/"+myFileFileName);
        if(!isOk){
        	addActionError("Unable to read Excel file");
        	return ERROR;
        }
     
     
        AdmissionDAO showNonApproveStudentOfMeritDAO = new AdmissionDAO();
        
        List<String> ValidAppID=  new ArrayList<String>();
        List<String> inValidAppID=  new ArrayList<String>();
        
        ErrorMassage="Invalid Information or Previously Received  Application ID:";
        
        for(int App=0;App<lsAppID.size();App++)
        {
        	String appID=(String)lsAppID.get(App);
        	String ssc_roll=(String)lsSSC_ROLL.get(App);
        	if(showNonApproveStudentOfMeritDAO.isValidApp(appID,ssc_roll,userDto.getEiin()))
     //   	if(SQStdCollegeDAO.isValidApp(appID,ssc_roll,userDto.getEiin()))
        	{
        		ValidAppID.add(appID);
        	}
        	else
        	{
        		ErrorMassage=ErrorMassage+appID+",";
        		inValidAppID.add(appID);
        	//	ErrorMassage=ErrorMassage+appID +" ["+ssc_roll+"],  ";
        	}
        }
        
        if(inValidAppID.size()<1)
        {
        	ErrorMassage="No Error Occurs";
        }
        
        
        applicationID="";
        for(int App=0;App<ValidAppID.size();App++)
        {
        	if(App!=ValidAppID.size()-1)
        	 applicationID=applicationID+"'"+ValidAppID.get(App)+"',";
        	else
        		 applicationID=applicationID+"'"+ValidAppID.get(App)+"'";
        }
        
        
        List<ApplicantInfoDTO> showNonApproveStudentOfMeritList = showNonApproveStudentOfMeritDAO.getNonApprovedStudentListOfMeritFromXLS(applicationID,userDto.getEiin());
        request.setAttribute("showNonApproveStudentOfMeritList", showNonApproveStudentOfMeritList);
        
        request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
	    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
	    
		
        request.setAttribute("ErrorMassage", ErrorMassage);
		request.setAttribute("userInfo", userDto);
		
		request.setAttribute("successMessage", "");
	    request.setAttribute("errorMessage", "");
	    
		return "success";          	
	}
	
	
	private boolean readUploadedExcel(List lsAppID,List lsSSC_ROLL,String fileName)
	{
		 System.setProperty("file.encoding", "UTF-8");
		 Charset utf8charset = Charset.forName("UTF-8");
		 System.out.println("Default Charset=" + Charset.defaultCharset());

		 try
		{
			FileInputStream file = new FileInputStream(new File(fileName));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			int rowNum=-1;
			while (rowIterator.hasNext()) 
			{
				Row row = rowIterator.next();
				//For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				rowNum=rowNum+1;
				if(rowNum!=0)
				{
				int index=1;
				if(rowNum>500)break;
				while (cellIterator.hasNext()) 
				{
					Cell cell = cellIterator.next();
					String cellValue= new String();
					switch (cell.getCellType()) 
					  {
					    case Cell.CELL_TYPE_NUMERIC:
					    	cellValue=String.valueOf(cell.getNumericCellValue());
					    	cellValue=cellValue.replace(".0", "");
							break;
						case Cell.CELL_TYPE_STRING:
							cellValue=cell.getStringCellValue();
						   	break;
						case Cell.CELL_TYPE_BLANK:
							cellValue="";
						   	break;
					   }
					cellValue=cellValue.trim();
					cellValue=cellValue.replace("-", "");
					System.out.print(cellValue + "\t");
					if((cellValue==null||cellValue.equals(""))&& rowNum!=500)
					{
						ErrorMassage="Please Check your Excel file at ROW No. "+(rowNum+1)+" and COLUMN No. "+index;
						//return false;
					}
					else
					{
						ErrorMassage="You have successfully uploaded";
					}
					
					if(index==1)
					{
						lsAppID.add(cellValue);
					}
					else if(index==2)
						lsSSC_ROLL.add(cellValue);
					
					index++;
					
				}
			 }
				System.out.println("");
			}
			file.close();
			
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		
		return true;
	}

	public String getFrom_where() {
		return from_where;
	}

	public void setFrom_where(String from_where) {
		this.from_where = from_where;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	private ServletContext servlet;
	public ServletContext getServletContext()
	{
		return servlet;
	}

	public void setServletContext(ServletContext servlet)
	{
		this.servlet = servlet;
	}

	public String getEiinCode() {
		return eiinCode;
	}

	public void setEiinCode(String eiinCode) {
		this.eiinCode = eiinCode;
	}



	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}

	public ServletContext getServlet() {
		return servlet;
	}

	public void setServlet(ServletContext servlet) {
		this.servlet = servlet;
	}

	public String getDataString() {
		return dataString;
	}

	public void setDataString(String dataString) {
		this.dataString = dataString;
	}

	public String getPMerit() {
		return pMerit;
	}

	public void setPMerit(String merit) {
		pMerit = merit;
	}

	public String getPShift_id() {
		return pShift_id;
	}

	public void setPShift_id(String shift_id) {
		pShift_id = shift_id;
	}

	public String getPVersion_id() {
		return pVersion_id;
	}

	public void setPVersion_id(String version_id) {
		pVersion_id = version_id;
	}

	public String getPGroup_id() {
		return pGroup_id;
	}

	public void setPGroup_id(String group_id) {
		pGroup_id = group_id;
	}

	public String getPRoll() {
		return pRoll;
	}

	public void setPRoll(String roll) {
		pRoll = roll;
	}

	public String getRoll_no() {
		return roll_no;
	}

	public void setRoll_no(String roll_no) {
		this.roll_no = roll_no;
	}

	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

	public String getPassing_year() {
		return passing_year;
	}

	public void setPassing_year(String passing_year) {
		this.passing_year = passing_year;
	}

	public String getReg_no() {
		return reg_no;
	}

	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}
	
	
	
}
