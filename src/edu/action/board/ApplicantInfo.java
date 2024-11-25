package edu.action.board;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Random;

import javax.servlet.jsp.tagext.TryCatchFinally;

import oracle.jdbc.OracleCallableStatement;

import org.objectweb.asm.xwork.tree.TryCatchBlockNode;

import com.google.gson.Gson;

import edu.action.common.BaseAction;
import edu.dao.applicant.RetrivedPinDAO;
import edu.dao.application.SscDataDAO;
import edu.dao.board.ApplicantInfoDAO;
import edu.dao.board.ApplicantReleaseDAO;
import edu.dto.application.ApplicantDTO;
import edu.dto.application.ApplicationInfoDTO;
import edu.dto.application.ResponseDTO;
import edu.dto.application.SscInfoDTO;
import edu.dto.board.ApplicantInfoBoardDTO;
import edu.dto.board.BoardDTO;
import edu.helpers.SmsSender;
import edu.utils.connection.ConnectionManager;


public class ApplicantInfo extends BaseAction{

	private static final long serialVersionUID = 2775177229066309566L;
	
	private String applicationID;
	private String eiinCode;
	
	
	private String applicantName;
	private String fatherName;
	private String sscRollNo;
	private String sscPassingYear;
	private String boardID;
	private String boardName;
	
	private String mobilenumber;
	
	private String registrationNumber;

	private String errorMessage, successMessage;
	private ApplicantDTO applicant;
	private String ssc_roll;
	private String ssc_board;
	private String ssc_year;
	private String ssc_reg;
	
	
	public String collegeApplicantInfo(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		/*System.out.println("Test...Applicant");*/
		
		ApplicantInfoDAO applicantInfoDAO = new ApplicantInfoDAO();
		ApplicantInfoBoardDTO applicantInfoDTO=applicantInfoDAO.getApplicantBasicInfo(applicationID);

	    request.setAttribute("applicationId",applicantInfoDTO.getApplicationID());
	    request.setAttribute("studentName",applicantInfoDTO.getApplicantName());
	    request.setAttribute("fatherName",applicantInfoDTO.getFatherName());
	    request.setAttribute("sscRollNo",applicantInfoDTO.getSscRollNo());
	    request.setAttribute("boardName",applicantInfoDTO.getBoardName());
	    request.setAttribute("passingYear",applicantInfoDTO.getSscPassingYear());

		
	    ApplicantInfoDAO applicantCollegeInfoDAO = new ApplicantInfoDAO();
		List<ApplicantInfoBoardDTO> applicantCollegeInfoList = applicantCollegeInfoDAO.getApplicantCollegeInfoList(applicationID,eiinCode);
		request.setAttribute("applicantCollegeInfoList", applicantCollegeInfoList);
				
		return "success";

			
		          
	
	}
	
	public String applicantQuotaEdit(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
							
		return "success";

			
		          
	
	}
	public String applicantrecovery(){
		

		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
				
//	    ApplicantInfoDAO applicantRecoverydataDAO = new ApplicantInfoDAO();
//		List<ApplicantInfoBoardDTO> applicantRecoverydataList = applicantRecoverydataDAO.getApplicantRecoverydata(userDto.getBoardUserId());
//		request.setAttribute("applicantRecoverydataList", applicantRecoverydataList);
		
		
		request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
	    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
				
		return "success";

			          
	
	}
	

	
	
	
	public String deleteAppPayment(){

		Gson gson = new Gson();
		ResponseDTO response=new ResponseDTO();
		
		ApplicantReleaseDAO deleteApplication = new ApplicantReleaseDAO();
		
		    response = deleteApplication.deletePayment (ssc_roll.trim(), ssc_board.trim(), ssc_year.trim(), ssc_reg.trim());
		    if(response.getStatus().equalsIgnoreCase("OK"))
		    {
		    	String   message="Your (Roll No:"+ssc_roll.trim()+ ") payment has been deleted. - Board Authority"; 
		    	SmsSender smsSender=new SmsSender();
		    	smsSender.setText(message);
				smsSender.setMobile(response.getMobile().trim());
				smsSender.setOperation("boardmessage");
				Thread thread = new Thread(smsSender);
				thread.start();
		    	
		    }
	
		String json = gson.toJson(response);
		setJsonResponse(json);
	    return null;
		}

	
	
public String deleteApplication_TT(){
	BoardDTO userDto = (BoardDTO) session.get("user");
	if(userDto == null){
		return "input";
	}
	
	Gson gson = new Gson();
	ResponseDTO response=new ResponseDTO();
	
	ApplicantReleaseDAO deleteApplication = new ApplicantReleaseDAO();
	if(RetrivedPinDAO.checMobile(mobilenumber.trim(),ssc_roll.trim(),ssc_reg.trim())==1){
		response.setStatus("ERROR");
    	response.setMessage("There is another applicant with this mobile number.");
	}
	else
	{
	    response = deleteApplication.delete_TT(ssc_roll.trim(), ssc_board.trim(), ssc_year.trim(), ssc_reg.trim(),mobilenumber.trim(), userDto.getBoardId());
	    if(response.getMessage().equalsIgnoreCase("Application Deleted Successfully ."))
	    {
	    	String   message="(Roll:"+ssc_roll.trim()+ ",Board:"+boardName.trim()+",Passing Year:"+ssc_year.trim()+") your choice list has been deleted. Add your college choice by the deadline.";    	
	    	message="Your ("+ssc_roll.trim()+ ") application has been cancelled. - Board Authority"; 
	    	SmsSender smsSender=new SmsSender();
	    	smsSender.setText(message);
			smsSender.setMobile(mobilenumber.trim());
			smsSender.setOperation("sms");
			Thread thread = new Thread(smsSender);
			thread.start();
	    	
	    }
	}
	String json = gson.toJson(response);
	setJsonResponse(json);
    return null;
}



public String updateMobile_TT(){
	BoardDTO userDto = (BoardDTO) session.get("user");
	if(userDto == null){
		return "input";
	}

	Gson gson = new Gson();
	ResponseDTO response=new ResponseDTO();
	
	if(RetrivedPinDAO.checMobile(mobilenumber.trim(),ssc_roll.trim(),ssc_reg.trim())==1){
		response.setStatus("ERROR");
    	response.setMessage("There is another applicant with this mobile number.");
	}
	else
	{
		ApplicantReleaseDAO updateMobile = new ApplicantReleaseDAO();
		ApplicantInfoDAO aidao = new ApplicantInfoDAO();
		String spcode = aidao.getSCode(ssc_roll.trim(),ssc_reg.trim());
	    response = updateMobile.updateMobile_TT(ssc_roll.trim(), ssc_board.trim(), ssc_year.trim(), ssc_reg.trim(),mobilenumber.trim(),spcode, userDto.getBoardId());
	    if(response.getMessage().equalsIgnoreCase("Mobile Number Updated Successfully ."))
	    {
	    	String   message="Your ("+ssc_roll.trim()+ ") mobile number has been changed to ("+mobilenumber.trim()+") and your security code is : "+spcode+" - Board Authority";    	
	    	SmsSender smsSender=new SmsSender();
	    	smsSender.setText(message);
			smsSender.setMobile(mobilenumber.trim());
			smsSender.setOperation("sms");
			Thread thread = new Thread(smsSender);
			thread.start();
	    	
	    }
	}
	String json = gson.toJson(response);
	setJsonResponse(json);
    return null;
}



	
	
	
	public String saveApplicantDataRecovery(){
		
		boolean resp=false;
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		ApplicantInfoDAO saveapplicantRecoverydataDAO = new ApplicantInfoDAO();
		/*System.out.println("registrationNumber:"+registrationNumber);*/
/*		if(registrationNumber.trim()=="")
		{
			registrationNumber="N/A";*/
		if(saveapplicantRecoverydataDAO.isnotDuplicate(sscRollNo,boardID,sscPassingYear,registrationNumber))
		{
		if(saveapplicantRecoverydataDAO.isValidate(sscRollNo,boardID,sscPassingYear,registrationNumber))
		{
		 resp=saveapplicantRecoverydataDAO.insertapplicantRecoverydata(sscRollNo,sscPassingYear,boardID,registrationNumber,mobilenumber,userDto.getBoardUserId());
		
		 if (resp == true) { 
			 successMessage = "Successfully Inserted";
		  }	
		  else
		  {
			  errorMessage = "Previously SMS has sent or 'Insert Problem..!' ";
		  }
		  	
		}
		
		else{
		//	System.out.println("Invalid Information...!!!");
			errorMessage = "Invalid Information";
		}
		
		
		}
		
		else{
				errorMessage = "You have saved this data previously.";
			}
		
		
		 /*		}*/
		
/*		else{
			resp=saveapplicantRecoverydataDAO.insertapplicantRecoverydata(sscRollNo,sscPassingYear,boardID,registrationNumber,mobilenumber,userDto.getBoardUserId());
			
		}*/
		 		
		return "success";

			
		          
	
	}
	
	
	public String deleteApplicantDataRecovery(){
		
		String[] ssc_roll=null;
	    String[] ssc_board=null;
	    String[] ssc_passing_year=null;
	    boolean resp=false;
	    
	    BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		
		String	applicationID[] =request.getParameterValues("applicationID");
		
		for(int i=0;i<applicationID.length;i++){
			
		    String serialNo=applicationID[i];
		    String[] parts = serialNo.split("#");
		    String string1 = parts[0]; 
		    String string2 = parts[1];
		    String string3 = parts[2];
		    
		    ssc_roll = new String[applicationID.length]; 
		    ssc_board = new String[applicationID.length] ;
		    ssc_passing_year = new String[applicationID.length] ;
		    
		    ssc_roll[i]=string1;
		    ssc_board[i]=string2;
		    ssc_passing_year[i]=string3;
		    
/*		System.out.println(ssc_roll[i]);
		System.out.println(ssc_board[i]);
		System.out.println(ssc_passing_year[i]);*/
		
		
	
		ApplicantInfoDAO applicantRecoverydataDAO = new ApplicantInfoDAO();

		 resp=applicantRecoverydataDAO.deleteApplicantRecoverydata(ssc_roll,ssc_board,ssc_passing_year);
		}
		
	
		  if (resp == true) {
			  
			request.setAttribute("successMessage", "Successfully Deleted");
  			request.setAttribute("errorMessage", "");
			  
		  }
				
		  else
		  {
			  request.setAttribute("successMessage", "Success ");
  			request.setAttribute("errorMessage", "");
		  }
		return "success";

			
		          
	
	}
	
public String smsSendRecoveryData(){
		
	    BoardDTO userDto = (BoardDTO) session.get("user");
	    ApplicantInfoDAO applicantRecoverydataDAO = new ApplicantInfoDAO();
	    String UPLOAD_ID=applicantRecoverydataDAO.getUploadID();
	    UPLOAD_ID=applicantRecoverydataDAO.UpdateDataID(UPLOAD_ID,userDto.getBoardUserId() );
	    UPLOAD_ID=applicantRecoverydataDAO.TransferDataByID(UPLOAD_ID);
	    applicantRecoverydataDAO.deletePreviousData(userDto.getBoardUserId());

	    sendSMSNOW(UPLOAD_ID);
	    	
		return "success";

			
		          
	
	}
	
public void sendSMSNOW(String UPLOAD_ID) {
	try {
	//	StringBuilder urlString = new StringBuilder("http://app.xiclassadmission.gov.bd/board/board/releaseApplicant.action?").append("uploadId=" +UPLOAD_ID );
		StringBuilder urlString = new StringBuilder("http://localhost:8080/board/board/releaseApplicant.action?").append("uploadId=" +UPLOAD_ID );
		URL smsUrl;
		smsUrl = new URL(urlString.toString());
		URLConnection urlConnector = smsUrl.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				urlConnector.getInputStream()));
		String inputLine;
		String inputLine1 = "";
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
			if (inputLine != null)
				inputLine1 += inputLine;
		}
		in.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}



private String getSecurityCode() 
{
    Random rand = new Random();
    int length = 6;//rand.nextInt(6) + 16;
    char[] password = new char[length];
    for (int x = 0; x < length; x++) {
      int randDecimalAsciiVal = 0;
      int cas = rand.nextInt(3);
      if (cas == 0)
        randDecimalAsciiVal = rand.nextInt(9) + 48;
      else if (cas == 1)
        randDecimalAsciiVal = rand.nextInt(26) + 65;
      else
        randDecimalAsciiVal = rand.nextInt(26) + 97;
      password[x] = String.valueOf(randDecimalAsciiVal/10).charAt(0);
    }
    String result = String.valueOf(password);
    return result;
}




	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	public String getEiinCode() {
		return eiinCode;
	}

	public void setEiinCode(String eiinCode) {
		this.eiinCode = eiinCode;
	}


	public String getApplicantName() {
		return applicantName;
	}


	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}


	public String getFatherName() {
		return fatherName;
	}


	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}


	public String getSscRollNo() {
		return sscRollNo;
	}


	public void setSscRollNo(String sscRollNo) {
		this.sscRollNo = sscRollNo;
	}


	public String getSscPassingYear() {
		return sscPassingYear;
	}


	public void setSscPassingYear(String sscPassingYear) {
		this.sscPassingYear = sscPassingYear;
	}


	public String getBoardID() {
		return boardID;
	}


	public void setBoardID(String boardID) {
		this.boardID = boardID;
	}


	public String getBoardName() {
		return boardName;
	}


	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}


	public String getMobilenumber() {
		return mobilenumber;
	}


	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}


	public String getRegistrationNumber() {
		return registrationNumber;
	}


	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
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


	public String getSsc_roll() {
		return ssc_roll;
	}


	public void setSsc_roll(String ssc_roll) {
		this.ssc_roll = ssc_roll;
	}


	public String getSsc_board() {
		return ssc_board;
	}


	public void setSsc_board(String ssc_board) {
		this.ssc_board = ssc_board;
	}


	public String getSsc_year() {
		return ssc_year;
	}


	public void setSsc_year(String ssc_year) {
		this.ssc_year = ssc_year;
	}


	public String getSsc_reg() {
		return ssc_reg;
	}


	public void setSsc_reg(String ssc_reg) {
		this.ssc_reg = ssc_reg;
	}
	

}
