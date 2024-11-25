package edu.action.applicant;

import java.awt.image.renderable.ParameterBlock;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.OpImage;
import javax.media.jai.RenderedOp;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.SeekableStream;

import edu.action.application.Application;
import edu.action.common.BaseAction;
import edu.dao.applicant.ResultDAO;
import edu.dao.application.ApplicationDAO;
import edu.dao.application.SscDataDAO;
import edu.dao.college.CollegeDAO;
import edu.dto.UserDTO;
import edu.dto.applicant.ResultDTO;
import edu.dto.application.ApplicantDTO;
import edu.dto.application.ChoiceDTO;
import edu.dto.application.ResponseDTO;
import edu.helpers.SmsSender;
import edu.utils.Configuration;
import edu.utils.UploadResponse;
import edu.utils.UploadToProvider;

public class Applicant extends BaseAction{

	private static final long serialVersionUID = -6359752797744360840L;
	ArrayList<ChoiceDTO> choice=new ArrayList<ChoiceDTO>();
	private ApplicantDTO applicant;
	private int totalOtpCount;
	private String otp;
	private String from_where;
	private String quota_YN;
	private String quota_ff;
	private String quota_eq;
	private ResultDTO admission_result;
	private String autoMigrationYN;
	private String message;
	
	private File fileUpload;
	private String fileUploadContentType;
	
	private String fileUploadFileName;
	
	private boolean imageValidYN ;
	private InputStream inputStream;
	
		// FileUploder Variable
		static Logger mLogger = Logger.getLogger(Application.class);
	public String applicantInfo(){

		totalOtpCount=(Integer)session.get("totalOtpCount");
		
		if(session.get("otp_validation")!=null && (Boolean)session.get("otp_validation")==true){
			UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
			setApplicantInfo(loggedInUser.getUserid());
			return SUCCESS;
		}
		else{
			if(session.get("applicant_info")==null){
			UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
			setApplicantInfo(loggedInUser.getUserid());
			}
			else{
				applicant=(ApplicantDTO)session.get("applicant_info");
			}
			
//			if(applicant.getApplication_info().getEditable().equalsIgnoreCase("N"))
//				return null;
			
			this.from_where="personal_info";
			return "otp_form";
		}

	}
	public String applicantInfoReadOnly(){
		if(session.get("applicant_info")==null){
			UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
			setApplicantInfo(loggedInUser.getUserid());			
		}
		else
			applicant=(ApplicantDTO)session.get("applicant_info");
		
		return SUCCESS;
	}
	
	public String newApplicantInfo(){

		totalOtpCount=(Integer)session.get("totalOtpCount");
		
		if(session.get("otp_validation")!=null && (Boolean)session.get("otp_validation")==true){
			UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
			setNewApplicantInfo(loggedInUser.getUserid());
			return SUCCESS;
		}
		else{
			if(session.get("applicant_info")==null){
			UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
			setApplicantInfo(loggedInUser.getUserid());
			}
			else{
				applicant=(ApplicantDTO)session.get("applicant_info");
			}
			
			this.from_where="personal_info";
			return "otp_form";
		}

	}

	public String newApplicantInfoReadOnly(){
		if(session.get("applicant_info")==null){
			UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
			setNewApplicantInfo(loggedInUser.getUserid());			
		}
		else
			applicant=(ApplicantDTO)session.get("applicant_info");
		
		return SUCCESS;
	}
	
	
	public String choiceList(){
//		if(session.get("isEligibleForBTEBApplication").toString().equalsIgnoreCase("N"))
			return "read_only";
		
//		totalOtpCount=(Integer)session.get("totalOtpCount");
//		applicant=(ApplicantDTO)session.get("applicant_info");
//		
////		if(applicant.getApplication_info().getEditable().equalsIgnoreCase("N"))
////			return null;
//
//		if(session.get("otp_validation")!=null && (Boolean)session.get("otp_validation")==true){
//			UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
//			ApplicationDAO appDao = new ApplicationDAO();
//			appDao.changeWebPaymentN(loggedInUser.getUserid());
//			CollegeDAO collegeDAO=new CollegeDAO();
//			choice=collegeDAO.getChoiceList(loggedInUser.getUserid());
//			return SUCCESS;
//		}
//		else{
//			this.from_where="choice_info";
//			return "otp_form";
//		}
		
	} 
	public String choiceListReadOnly(){
		applicant=(ApplicantDTO)session.get("applicant_info");
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		CollegeDAO collegeDAO=new CollegeDAO();
		choice=collegeDAO.getChoiceList(loggedInUser.getUserid());
		return SUCCESS;
	}
	
public String newChoiceList(){
		
		totalOtpCount=(Integer)session.get("totalOtpCount");
		applicant=(ApplicantDTO)session.get("applicant_info");
		
		if(session.get("otp_validation")!=null && (Boolean)session.get("otp_validation")==true){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		CollegeDAO collegeDAO=new CollegeDAO();
		choice=collegeDAO.getNewChoiceList(loggedInUser.getUserid());
		return SUCCESS;
		}
		else{
			this.from_where="choice_info";
			return "otp_form";
		}
		
	} 

	public String newChoiceListReadOnly(){
		applicant=(ApplicantDTO)session.get("applicant_info");
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		CollegeDAO collegeDAO=new CollegeDAO();
		choice=collegeDAO.getNewChoiceList(loggedInUser.getUserid());
		return SUCCESS;
	}
	
	
	public String updateQuotaInfo(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(quota_YN.equalsIgnoreCase("N"))
			{this.quota_ff="N";}
		
		ApplicationDAO applicationDAO=new ApplicationDAO();
				
		boolean resp=applicationDAO.updateQuotaInfo(loggedInUser.getUserid(),this.quota_YN,this.quota_ff,getIpAddressDTO(),(String)session.get("user_otp"));
		String respMsg="";
		if(resp==true){		
			setApplicantInfo(loggedInUser.getUserid());
			respMsg="Successfully Updated Quota Information.";
		}
		else
			respMsg="Problem in Updating Quota Information.";
		
		try{
	    	 response.setContentType("text/html");
	    	 response.getWriter().write(respMsg);
	          }
	        catch(Exception e) {e.printStackTrace();}
		return null;
		
	}
	
	public String sendNewOtp(){
		String responseMsg="";
		if(session.get("isOtpRquestedInthisSession")!=null){
			responseMsg="You have already requested for one OTP(One Time Password). You will receive this OTP very soon in your mobile number. " +
					" \n\nIf you still want to get another OTP, please logout and then request for a new OTP.";
		}
		else{
			
			totalOtpCount=(Integer)session.get("totalOtpCount");
			if(totalOtpCount>=2){
				responseMsg="You have already used 2 OTP. You can't request more.";
			}
			else{
				
				ApplicantDTO applicant=(ApplicantDTO)session.get("applicant_info");
				ApplicationDAO applicationDAO=new ApplicationDAO();
				String otp=applicationDAO.generateOtp(applicant.getApplication_id(),getIpAddressDTO());
				session.put("totalOtpCount", totalOtpCount+1);
				session.put("otp_validation",false);
				session.put("isOtpRquestedInthisSession","true");
				responseMsg="An OTP has been sent to your Mobile Number :"+applicant.getApplication_info().getMobile_number()+". Please write down the code in the textbox above and verify it.";
				SmsSender smsSender=new SmsSender();
				smsSender.setOtp(otp);
				smsSender.setMobile(applicant.getApplication_info().getMobile_number());
				smsSender.setOperation("otp");
				Thread thread = new Thread(smsSender);
				thread.start();
				}
		}
		try{
	    	 response.setContentType("text/html");
	    	 response.getWriter().write(responseMsg);
	          }
	        catch(Exception e) {e.printStackTrace();}
		return null;
	}
	public String validateOtp(){
		ApplicantDTO applicant=(ApplicantDTO)session.get("applicant_info");
		ApplicationDAO applicationDAO=new ApplicationDAO();
		boolean isValid=applicationDAO.isValidOtp(applicant.getApplication_id(), otp, "OTP");
		String responseMsg="";
		if(isValid==true){
			responseMsg="valid";
			session.put("otp_validation",true);
			session.put("user_otp",otp);
//			applicationDAO.changeOtpStatus(applicant.getApplication_id(), otp, "Y");
		}
		else
			responseMsg="Sorry, your Registration Number/OTP is not correct.";
		try{
	    	 response.setContentType("text/html");
	    	 response.getWriter().write(responseMsg);
	          }
	        catch(Exception e) {e.printStackTrace();}
		return null;
	}
	public String validateOtpRl(){
		ApplicantDTO applicant=(ApplicantDTO)session.get("applicant_info");
		ApplicationDAO applicationDAO=new ApplicationDAO();
		boolean isValid=applicationDAO.isValidOtpNew(applicant.getApplication_id(), otp, "REG");
		String responseMsg="";
		if(isValid==true){
			responseMsg="valid";
			session.put("otp_validation",true);
			session.put("user_otp",otp);
//			applicationDAO.changeOtpStatus(applicant.getApplication_id(), otp, "Y");
		}
		else
			responseMsg="Sorry, your OTP is not correct.";
		try{
	    	 response.setContentType("text/html");
	    	 response.getWriter().write(responseMsg);
	          }
	        catch(Exception e) {e.printStackTrace();}
		return null;
	}
	public String choiceListReport(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		CollegeDAO collegeDAO=new CollegeDAO();
		setApplicantInfo(loggedInUser.getUserid());
		choice=collegeDAO.getChoiceList(loggedInUser.getUserid());
		return SUCCESS;		
	}
	
	public String newChoiceListReport(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		CollegeDAO collegeDAO=new CollegeDAO();
		setNewApplicantInfo(loggedInUser.getUserid());
		choice=collegeDAO.getNewChoiceList(loggedInUser.getUserid());
		return SUCCESS;		
	}
	
	
	public String updateChoiceList(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		ResponseDTO response=new ResponseDTO();
		ApplicationDAO applicationDAO=new ApplicationDAO();
		SscDataDAO sscDAO=new SscDataDAO();
		ApplicantDTO applicant_original=sscDAO.getApplication(loggedInUser.getUserid());
		response=applicationDAO.validateApplication(applicant_original, choice,"UPDATE");
		
		if(response.getStatus().equalsIgnoreCase("VALID")){
			response=applicationDAO.updateChoiceList(applicant_original, choice,getIpAddressDTO(),(String) session.get("user_otp"));
		}
		System.out.println("Application Update Status :<<"+response.getStatus()+">> || <<"+response.getApplication_id()+">>");
		
		Gson gson = new Gson();
		String json = gson.toJson(response);
		setJsonResponse(json);
        return null;
		
	}
	public String newUpdateChoiceList(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		ResponseDTO response=new ResponseDTO();
		ApplicationDAO applicationDAO=new ApplicationDAO();
		SscDataDAO sscDAO=new SscDataDAO();
		ApplicantDTO applicant_original=sscDAO.getNewApplication(loggedInUser.getUserid());
		response=applicationDAO.validateApplicationNew(applicant_original, choice,"UPDATE");
		
		if(response.getStatus().equalsIgnoreCase("VALID")){
			response=applicationDAO.newUpdateChoiceList(applicant_original, choice,getIpAddressDTO(),(String) session.get("user_otp"));
			System.out.println("Application Update Status :<<"+response.getStatus()+">> || <<"+response.getApplication_id()+">>");
		}
		
		
		Gson gson = new Gson();
		String json = gson.toJson(response);
		setJsonResponse(json);
        return null;
		
	}
	public String updateReleaseList(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		ResponseDTO response=new ResponseDTO();
		ApplicationDAO applicationDAO=new ApplicationDAO();
		SscDataDAO sscDAO=new SscDataDAO();
		ApplicantDTO applicant_original=sscDAO.getApplication(loggedInUser.getUserid());
		response=applicationDAO.validateApplicationNew(applicant_original, choice,"UPDATE");
		
		if(response.getStatus().equalsIgnoreCase("VALID")){
			response=applicationDAO.updateReleaseSlip(applicant_original, choice,getIpAddressDTO(),(String) session.get("user_otp"));
		}
		System.out.println("Release Slip Update Status :<<"+response.getStatus()+">>");
		
		Gson gson = new Gson();
		String json = gson.toJson(response);
		setJsonResponse(json);
        return null;
		
	}
	public String updateEducationQuotaInfo(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		ResponseDTO response=new ResponseDTO();
		ApplicationDAO applicationDAO=new ApplicationDAO();
		response=applicationDAO.updateEQ(loggedInUser.getUserid(), choice,getIpAddressDTO(),(String) session.get("user_otp"));
		System.out.println("EQ Change Status :<<"+response.getStatus()+">> || <<"+response.getApplication_id()+">>");		
		Gson gson = new Gson();
		String json = gson.toJson(response);
		setJsonResponse(json);
        return null;
		
	}
	public String ApplicantViewResult()
	{
		if(session.get("applicant_info")==null){
			UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
			setApplicantInfo(loggedInUser.getUserid());
			}
			else{
				applicant=(ApplicantDTO)session.get("applicant_info");
			}
		//if(applicant.getApplication_info().getEditable().equalsIgnoreCase("Y"))
		//	return null;
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		ResultDAO resultDAO=new ResultDAO();
		admission_result=resultDAO.getResult(loggedInUser.getUserid());
		session.put("admissionResult", admission_result);
		return SUCCESS;
	}
	
	public String viewNewAppResult()
	{
		if(session.get("applicant_info")==null){
			UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
			setNewApplicantInfo(loggedInUser.getUserid());
			}
			else{
				applicant=(ApplicantDTO)session.get("applicant_info");
			}
		//if(applicant.getApplication_info().getEditable().equalsIgnoreCase("Y"))
		//	return null;
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		ResultDAO resultDAO=new ResultDAO();
		admission_result=resultDAO.getResult(loggedInUser.getUserid());
		session.put("admissionResult", admission_result);
		return SUCCESS;
	}
	public String updateAutoMigration(){
		
		applicant=(ApplicantDTO)session.get("applicant_info");
		if(applicant.getApplication_info().getEditable().equalsIgnoreCase("Y"))
			return null;
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		
		ResultDAO resultDAO=new ResultDAO();
		Boolean updateStatus = resultDAO.updateAutoMigration(loggedInUser.getUserid(), autoMigrationYN, getIpAddressDTO(), (String) session.get("user_otp"));
		if(updateStatus){			
			admission_result=resultDAO.getResult(loggedInUser.getUserid());
			session.put("admissionResult", admission_result);
			return SUCCESS;
		} else
			return "failure";
				
	}
	
	public String autoMigrationForm(){
		//applicant=(ApplicantDTO)session.get("applicant_info");
		/*
		if(applicant.getApplication_info().getEditable().equalsIgnoreCase("Y"))
			return null;
		if(session.get("otp_validation")!=null && (Boolean)session.get("otp_validation")==true){
			applicant=(ApplicantDTO)session.get("applicant_info");
			return SUCCESS;
		} else {
			applicant=(ApplicantDTO)session.get("applicant_info");
			this.from_where="automigration_form";
			return "otp_form";
		}
		*/
		if(session.get("applicant_info")==null){
			UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
			setApplicantInfo(loggedInUser.getUserid());
			}
			else{
				applicant=(ApplicantDTO)session.get("applicant_info");
			}
		//if(applicant.getApplication_info().getEditable().equalsIgnoreCase("Y"))
		//	return null;
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		ResultDAO resultDAO=new ResultDAO();
		admission_result=resultDAO.getResult(loggedInUser.getUserid());
		session.put("admissionResult", admission_result);
		return SUCCESS;
	}
	
	
	public String migrationNew(){
		//applicant=(ApplicantDTO)session.get("applicant_info");
		/*
		if(applicant.getApplication_info().getEditable().equalsIgnoreCase("Y"))
			return null;
		if(session.get("otp_validation")!=null && (Boolean)session.get("otp_validation")==true){
			applicant=(ApplicantDTO)session.get("applicant_info");
			return SUCCESS;
		} else {
			applicant=(ApplicantDTO)session.get("applicant_info");
			this.from_where="automigration_form";
			return "otp_form";
		}
		*/
		String isMigrationEligible=(String)session.get("isMigrationEligible");
		if(isMigrationEligible.equals("false"))
		{
			return "basicInfo";
		}
		if(session.get("applicant_info")==null){
			UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
			setApplicantInfo(loggedInUser.getUserid());
			}
			else{
				applicant=(ApplicantDTO)session.get("applicant_info");
			}
		//if(applicant.getApplication_info().getEditable().equalsIgnoreCase("Y"))
		//	return null;
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		ResultDAO resultDAO=new ResultDAO();
		admission_result=resultDAO.getResult(loggedInUser.getUserid());
		session.put("admissionResult", admission_result);
		
		
		/*
		if(((String)session.get("releaseSlipEligibility")).equalsIgnoreCase("N")){
			message="You are not allowed to apply. You were in the 1st or 2nd merit list and your admission has been confirmed by your college.";
			return "invalid";
		}
		else if(((String)session.get("releaseSlipEligibility")).equalsIgnoreCase("I")){
			message="You are not a valid Applicant. You did not pay your application fee during the first application period.";
			return "invalid";
		}
			*/
		
		loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(session.get("applicant_info")==null){		
			setApplicantInfo(loggedInUser.getUserid());
			}
		else{
				applicant=(ApplicantDTO)session.get("applicant_info");
		}
		
		//totalOtpCount=(Integer)session.get("totalOtpCount");		

		this.from_where="release_slip";
		
		/*if(session.get("otp_validation")!=null && (Boolean)session.get("otp_validation")==true){
			CollegeDAO collegeDAO=new CollegeDAO();
			choice=collegeDAO.getReleaseSlipChoiceList(loggedInUser.getUserid());
			return SUCCESS;
		}
		else{			
			return "otp_form";
		}*/
		CollegeDAO collegeDAO=new CollegeDAO();
		choice=collegeDAO.getChoiceList_TT(loggedInUser.getUserid());
		
		return SUCCESS;
	}
	
public String submitMigrationNew(){
	
	    ResponseDTO response=new ResponseDTO();
	    Gson gson = new Gson();
	    
		applicant=(ApplicantDTO)session.get("applicant_info");
		//if(applicant.getApplication_info().getEditable().equalsIgnoreCase("Y"))
		//	return null;
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		
		ResultDAO resultDAO=new ResultDAO();
		//Boolean updateStatus = resultDAO.updateAutoMigration(loggedInUser.getUserid(), autoMigrationYN, getIpAddressDTO(), (String) session.get("user_otp"));
		
		
		/////////////////////////////////////////
		/////////////////////////////////////////
		/////////////////////////////////////
		for (int i = 0; i < choice.size(); i++)
		{
			if(choice.get(i)==null)
				choice.remove(i);
		}
		Set<String> choiceEiinSet = new HashSet<String>();
		for(int i=0;i<choice.size();i++){
			if(choice.get(i).getApplication_type()==null || (choice.get(i).getApplication_type().equalsIgnoreCase("WEB")))
				choiceEiinSet.add(choice.get(i).getEiin());
		}
		if(choiceEiinSet.size()>10){
			System.out.print("You can select maximum 10 (Ten) different colleges.");
			response.setMessage("You can select maximum 10 (Ten) different colleges.");
			response.setStatus("INVALID");
			String json = gson.toJson(response);setJsonResponse(json);return null;
		}		
		for (int i = choice.size()-1; i >=0; i--)
		{
			if(choice.get(i)!=null && choice.get(i).getApplication_type()!=null && choice.get(i).getApplication_type().equalsIgnoreCase("WEB"))
				applicant.getApplication_info().setMobile_number(choice.get(i).getMobile_no());
//			if(choice.get(i)==null || choice.get(i).getApplication_type()!=null)
//			{
//				choice.remove(i);
//			}
		}
		Set<String> uniqueChoiceSet = new HashSet<String>();
		for(int i=0;i<choice.size();i++){
			ChoiceDTO tmpchoice = choice.get(i);
			uniqueChoiceSet.add(tmpchoice.getEiin()+""+tmpchoice.getShift_id()+""+tmpchoice.getVersion_id()+""+tmpchoice.getGroup_id()+""+tmpchoice.getSpecial_quota());
		}
		if(uniqueChoiceSet.size()!=choice.size())
		{  
			System.out.print("Not unique college");
			response.setStatus("ERROR");
			response.setMessage("Sorry, you have manipulated your Application Data. We can't accept your application.!!!");
			String json = gson.toJson(response);setJsonResponse(json);return null;
		}

		
		ApplicationDAO applicationDAO=new ApplicationDAO();
		response=applicationDAO.validateApplication_TT(applicant, choice,"UPDATE");
		
		
		if(response.getStatus().equalsIgnoreCase("VALID"))
		{
			response=applicationDAO.saveMigrationNew(applicant, choice, autoMigrationYN, getIpAddressDTO());
		}
		//System.out.println("Migration Status :<<"+response.getStatus()+">> || <<"+response.getApplication_id()+">>");
		if(!response.getStatus().equals("ERROR"))
		{
			response.setMessage(" <h2 class=\"mainHeader\">Migration Response</h2><div class='rounded-corner' style='height: 100px; width: 757px; text-align: center; background: #DDE8F0;font-size: 24px;color: #EC0F18;'><div class=\"successMessageDiv\" id=\"finalResultDiv\"><center><div class=\"successResultMessage\">Your Migration Request Received Successfully</div></center></div></div>");
			String json = gson.toJson(response);
			setJsonResponse(json);
		}
		else
		{
			response.setMessage(" <h2 class=\"mainHeader\">Migration Response</h2><div class='rounded-corner' style='height: 100px; width: 757px; text-align: center; background: #DDE8F0;font-size: 24px;color: #EC0F18;'><div class=\"successMessageDiv red\" id=\"finalResultDiv\"><center><div class=\"successResultMessage red\">Sorry, Your Migration Request is not successful.</div></center></div></div>");
			String json = gson.toJson(response);
			setJsonResponse(json);
		}
		
		////////////////////////////////////
		/*
		loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		ResponseDTO response=new ResponseDTO();
		ApplicationDAO applicationDAO=new ApplicationDAO();
		SscDataDAO sscDAO=new SscDataDAO();
		ApplicantDTO applicant_original=sscDAO.getApplication(loggedInUser.getUserid());
		response=applicationDAO.validateApplicationNew(applicant_original, choice,"UPDATE");
		
		if(response.getStatus().equalsIgnoreCase("VALID")){
			response=applicationDAO.updateReleaseSlip(applicant_original, choice,getIpAddressDTO(),(String) session.get("user_otp"));
		}
		System.out.println("Release Slip Update Status :<<"+response.getStatus()+">>");
		
		Gson gson = new Gson();
		String json = gson.toJson(response);
		setJsonResponse(json);
		 */
        //return "success";
		return null;
       
        
        
				
	}

	public String releaseSlipReadOnly(){
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(session.get("applicant_info")==null){			
			setApplicantInfo(loggedInUser.getUserid());
		}
		else{
			applicant=(ApplicantDTO)session.get("applicant_info");
		}
			
		if(((String)session.get("releaseSlipEligibility")).equalsIgnoreCase("N")){
			message="You are not allowed to apply. You were in the 1st or 2nd merit list and your admission has been confirmed by your college.";
			return "invalid";
		}
		else if(((String)session.get("releaseSlipEligibility")).equalsIgnoreCase("I")){
			message="You are not a valid Applicant. You did not pay your application fee during the first application period.";
			return "invalid";
		}
		
		
		CollegeDAO collegeDAO=new CollegeDAO();
		choice=collegeDAO.getReleaseSlipChoiceList(loggedInUser.getUserid());
		this.from_where="release_slip";
		if(choice.size()==0){
			//if(session.get("otp_validation")!=null && (Boolean)session.get("otp_validation")==true)
				return SUCCESS;
			//else
				//return "otp_form";
		}
		else{
			return SUCCESS;
		}
		
		
	}
	public String releaseSlip(){
		
		if(((String)session.get("releaseSlipEligibility")).equalsIgnoreCase("N")){
			message="You are not allowed to apply. You were in the 1st or 2nd merit list and your admission has been confirmed by your college.";
			return "invalid";
		}
		else if(((String)session.get("releaseSlipEligibility")).equalsIgnoreCase("I")){
			message="You are not a valid Applicant. You did not pay your application fee during the first application period.";
			return "invalid";
		}
			
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(session.get("applicant_info")==null){		
			setApplicantInfo(loggedInUser.getUserid());
			}
		else{
				applicant=(ApplicantDTO)session.get("applicant_info");
		}
		
		//totalOtpCount=(Integer)session.get("totalOtpCount");		

		this.from_where="release_slip";
		
		/*if(session.get("otp_validation")!=null && (Boolean)session.get("otp_validation")==true){
			CollegeDAO collegeDAO=new CollegeDAO();
			choice=collegeDAO.getReleaseSlipChoiceList(loggedInUser.getUserid());
			return SUCCESS;
		}
		else{			
			return "otp_form";
		}*/
		CollegeDAO collegeDAO=new CollegeDAO();
		choice=collegeDAO.getReleaseSlipChoiceList(loggedInUser.getUserid());
		return SUCCESS;
	}
	public String releaseSlipReport(){
		if(((String)session.get("releaseSlipEligibility")).equalsIgnoreCase("N")){
			message="You are not allowed to apply. You were in the 1st or 2nd merit list and your admission has been confirmed by your college.";
			return "invalid";
		}
		else if(((String)session.get("releaseSlipEligibility")).equalsIgnoreCase("I")){
			message="You are not a valid Applicant. You did not pay your application fee during the first application period.";
			return "invalid";
		}
		
		
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
		CollegeDAO collegeDAO=new CollegeDAO();
		choice=collegeDAO.getReleaseSlipChoiceList(loggedInUser.getUserid());
		setApplicantInfo(loggedInUser.getUserid());		
		return SUCCESS;
	}
	
	public String applicantPhotoChangeView()
	{
		if(session.get("applicant_info")==null){
			UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
			setApplicantInfo(loggedInUser.getUserid());
			}
			else{
				applicant=(ApplicantDTO)session.get("applicant_info");
			}
		
		return SUCCESS;
	}
	// Photo Validation
		public String photoValidation() throws Exception {
			/*
			if(session.get("applicant_info")==null){
				UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
				setApplicantInfo(loggedInUser.getUserid());
				}
				else{
					applicant=(ApplicantDTO)session.get("applicant_info");
				}
			*/
			
			
			imageValidYN=false;
			Gson gson = new Gson();
			String json="";
	        //System.out.println("Test");
		    String type = fileUploadContentType.split("/")[1];
		    if (type.endsWith("jpeg")) {
		      type = "jpeg";
		    }
		    else if (type.endsWith(
		            "jpg")) {
		      type = "jpg";
		    }
		    else if (type.endsWith("JPG")) {
		      type = "jpg";
		    }
		    else if (type.endsWith("JPEG")) {
		      type = "jpg";
		    }
		    else {
		      inputStream = IOUtils.toInputStream("error#Not a Valid type. Only Supported Image Type is '.jpg'", "UTF-8");
		       json = gson.toJson("error#Not a Valid type. Only Supported Image Type is '.jpg'");
			  setJsonResponse(json);
		      return null;
		    }

		    FileInputStream in = new FileInputStream(fileUpload);
		    BufferedInputStream bf = new BufferedInputStream(in);
		    byte[] b = new byte[(int) fileUpload.length()];
		    bf.read(b);

		    String t = "";
		    if (type.equalsIgnoreCase("jpeg") || type.equalsIgnoreCase("jpg")) {
		      t = "JPEG";
		    }
		    else {
		      inputStream = IOUtils.toInputStream("error#Not a Valid type. Only Supported Image Type is '.jpg'", "UTF-8");
		      json = gson.toJson("error#Not a Valid type. Only Supported Image Type is '.jpg'");
			  setJsonResponse(json);
		      return null;
		    }

		    int dimX = 120;
		    int dimY = 150;
		    byte[] finaldata = null;
		    try {

		      ByteArrayInputStream is = new ByteArrayInputStream(b);
		      SeekableStream s = SeekableStream.wrapInputStream(is, true);
		      RenderedOp objImage = JAI.create("stream", s);
		      ((OpImage) objImage.getRendering()).setTileCache(null);

		      float width = (float) objImage.getWidth();
		      float height = (float) objImage.getHeight();

//		      if (width > 125 || width < 115 || height > 155 || height < 145) {
//
//		        inputStream = IOUtils.toInputStream("error#Image with 120 px Width and 150 px Height are allowed to upload.", "UTF-8");
//		        json = gson.toJson("error#Image with 120 px Width and 150 px Height are allowed to upload.");
//				setJsonResponse(json);
//			    return null;
//		      }

		      ParameterBlock pb = new ParameterBlock();
		      pb.addSource(objImage); // The source image
		      pb.add(dimX / width); // The xScale
		      pb.add(dimY / height); // The yScale
		      pb.add(0.0F); // The x translation
		      pb.add(0.0F); // The y translation
		      pb.add(new InterpolationNearest()); // The interpolation

		      objImage = JAI.create("scale", pb, null);

		      ByteArrayOutputStream out1 = new ByteArrayOutputStream();

		      ImageEncoder imgEnc = ImageCodec.createImageEncoder(t, out1, null);
		      imgEnc.encode(objImage);
		      finaldata = out1.toByteArray();
		      
		      float aspectRatio = height/width;
		      if(aspectRatio<1.15 || aspectRatio>1.40)
		      {
		    	  try{
		    	  inputStream = IOUtils.toInputStream("error#Please upload PORTRAIT photo", "UTF-8");
			        json = gson.toJson("error#Please upload PORTRAIT photo");
					setJsonResponse(json);
			    	  }catch (Exception ex)
			    	  {ex.printStackTrace();}
				    return null;
		      }
		      File newFile = new File(request.getSession().getId()+".jpg");
				try
				{
					Thumbnails.of(fileUpload).size(120, 150).toFile(newFile);
					fileUpload = newFile;
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				

		      if ((fileUpload.length() / 1024) > 15) {
		    	  try{
		        inputStream = IOUtils.toInputStream("error#Maximum allowed Photo size is 15 Kb", "UTF-8");
		        json = gson.toJson("error#Maximum allowed Photo size is 15 Kb");
				setJsonResponse(json);
		    	  }catch (Exception ex)
		    	  {ex.printStackTrace();}
			    return null;
		      }


		    }
		    catch (Exception e) {
		      e.printStackTrace();
		      mLogger.error("Exception during File Upload Operation(Photo Change).");

		      inputStream = IOUtils.toInputStream("error#Probelm in Uploading the Image", "UTF-8");
		      json = gson.toJson("error#Probelm in Uploading the Image");
			  setJsonResponse(json);
			  return null;
		    }
		    
		    //String filepath = applicant.getApplication_id()+ ".jpg";
		    String filepath = fileUploadFileName+ ".jpg";
		    File fileToCreate = new File(filepath);
		    FileOutputStream out = new FileOutputStream(fileToCreate);
		    out.write(finaldata);
		    UploadResponse uploadResponse = UploadToProvider.getInstance().upload(fileToCreate, "tmpPhoto");
		    ServletActionContext.getRequest().getSession().setAttribute("uploadedImage", finaldata);

		    fileToCreate.delete();

		    inputStream = IOUtils.toInputStream(uploadResponse.getResponseText(), "UTF-8");
		    json = gson.toJson("success");
			setJsonResponse(json);
			imageValidYN=true;
			return null;
		  }
		
		public String changePhoto() throws Exception{
			
			 photoValidation();
			 Gson gson = new Gson();
			 ResponseDTO response=new ResponseDTO();
			
			if(imageValidYN)
			{
		    //setApplicant();

		
						
						 Configuration configuration = Configuration.getInstance();
						
						
						String localFilePath=configuration.getConfigurationMap().get(configuration.getEnvironment() + ".application.image.local.directory")
		                        + "applicantPhoto";
						
						File destFile = new File(localFilePath,applicant.getApplication_id()+ ".jpg");
						FileUtils.copyFile(fileUpload, destFile);
						
					//	System.out.println(localFilePath);
						
						String localUploadedFilePath=configuration.getConfigurationMap().get(configuration.getEnvironment() + ".application.image.local.directory")
		                        + "applicantPhoto/"+applicant.getApplication_id()+ ".jpg";
						File f1 = new File(localUploadedFilePath);
						 
	/*					String tempFilePath=configuration.getConfigurationMap().get(configuration.getEnvironment() + ".application.image.local.directory")
		                        + "tmpPhoto/"+ preview_ssc_roll+ "_" + preview_ssc_board +"_"+preview_ssc_year+ ".jpg";*/
						
	/*					System.out.println(tempFilePath);
						File f1 = new File(tempFilePath);*/
						
						
				        if(f1.exists()){
						
						   File fileToCreate = new File(applicant.getApplication_id()+ ".jpg");
						   FileUtils.copyFile(fileUpload, fileToCreate);
						   UploadResponse uploadResponse = UploadToProvider.getInstance().upload(fileToCreate, "applicantPhoto");
						   fileToCreate.delete();
						   
						   if (uploadResponse.getStatusCode() == 200)
						   {
							   response.setStatus("SUCCESS");
							   response.setMessage("Your photo has been changed successfully.");
							   

							   
							   System.out.println("Applicant ID:"+applicant.getApplication_id()+", Photo has been uploaded successfully.");
						   }
						   
						   else
							{
							   response.setStatus("ERROR");
							   response.setMessage("Photo not uploaded.Please try again...!!!");
							   

							   
								System.out.println("Photo not uploaded.Please try again...!!!");
							}
						    

					}
						else
						{
							response.setStatus("ERROR");
							response.setMessage("File upload error.Please try again...!!!");
							

							System.out.println("File upload error.Please try again...!!!");
						}
							  

				}
			
			   String json = gson.toJson(response);
			   setJsonResponse(json);	

			return null;
			}

	public void setApplicantInfo(String applicant_id){
		SscDataDAO sscDAO=new SscDataDAO();
		applicant=sscDAO.getApplication(applicant_id);
		session.put("applicant_info",applicant);
	}
	public void setNewApplicantInfo(String applicant_id){
		SscDataDAO sscDAO=new SscDataDAO();
		applicant=sscDAO.getNewApplication(applicant_id);
		session.put("applicant_info",applicant);
	}
public void setApplicant(){
		
		if(session.get("applicant_info")==null){
			UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
			SscDataDAO sscDAO=new SscDataDAO();
			applicant=sscDAO.getNewApplication(loggedInUser.getUserid());
		}
		else{
			applicant=(ApplicantDTO)session.get("applicant_info");
		}

	}
	public ArrayList<ChoiceDTO> getChoice() {
		return choice;
	}

	public void setChoice(ArrayList<ChoiceDTO> choice) {
		this.choice = choice;
	}

	public ApplicantDTO getApplicant() {
		return applicant;
	}

	public void setApplicant(ApplicantDTO applicant) {
		this.applicant = applicant;
	}

	public int getTotalOtpCount() {
		return totalOtpCount;
	}

	public void setTotalOtpCount(int totalOtpCount) {
		this.totalOtpCount = totalOtpCount;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getFrom_where() {
		return from_where;
	}
	public void setFrom_where(String fromWhere) {
		from_where = fromWhere;
	}
	public String getQuota_YN() {
		return quota_YN;
	}
	public void setQuota_YN(String quotaYN) {
		quota_YN = quotaYN;
	}
	public String getQuota_ff() {
		return quota_ff;
	}
	public void setQuota_ff(String quotaFf) {
		quota_ff = quotaFf;
	}
	public String getQuota_eq() {
		return quota_eq;
	}
	public void setQuota_eq(String quotaEq) {
		quota_eq = quotaEq;
	}
	public ResultDTO getAdmission_result() {
		return admission_result;
	}
	public void setAdmission_result(ResultDTO admissionResult) {
		admission_result = admissionResult;
	}
	public String getAutoMigrationYN() {
		return autoMigrationYN;
	}
	public void setAutoMigrationYN(String autoMigrationYN) {
		this.autoMigrationYN = autoMigrationYN;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
	public File getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}
	public String getFileUploadContentType() {
		return fileUploadContentType;
	}
	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
	public String getFileUploadFileName() {
		return fileUploadFileName;
	}
	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
	public boolean isImageValidYN() {
		return imageValidYN;
	}
	public void setImageValidYN(boolean imageValidYN) {
		this.imageValidYN = imageValidYN;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}
