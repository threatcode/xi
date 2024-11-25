package edu.action.college;

import java.net.InetAddress;
import java.util.List;

import edu.action.common.BaseAction;
import edu.dao.college.AdmissionDAO;
import edu.dao.college.CollegeDAO;
import edu.dto.college.CollegeCourseDTO;
import edu.dto.college.CollegeDTO;
import edu.helpers.SmsSender;

public class College extends BaseAction {
	
	private static final long serialVersionUID = -6433034297040474223L;
	private String otp;
	private String mobile;
	private String password;
	private String cpassword;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public String getCmobile() {
		return cmobile;
	}
	public void setCmobile(String cmobile) {
		this.cmobile = cmobile;
	}
	private String cmobile;
	
	public String collegeOtpForm(){
		return SUCCESS;
	}
	public String collegeHome(){
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		AdmissionDAO svgLoadDAO = new AdmissionDAO();
		List<CollegeCourseDTO> shiftList;
		List<CollegeCourseDTO> versionList;
		List<CollegeCourseDTO> groupList;
		if(session.get("shiftList")==null)
		{
		 shiftList= svgLoadDAO.getShiftList(userDto.getEiin());
		 session.put("shiftList", shiftList);
		}
		request.setAttribute("shiftList", session.get("shiftList"));
		
		if(session.get("versionList")==null)
		{
		 versionList = svgLoadDAO.getVersionList(userDto.getEiin());
		 session.put("versionList", versionList);
		}
		request.setAttribute("versionList", session.get("versionList"));
		if(session.get("groupList")==null)
		{
		groupList = svgLoadDAO.getGroupList(userDto.getEiin());
		session.put("groupList", groupList);
		}
		request.setAttribute("groupList", session.get("groupList"));
		try
		{
			String IP=InetAddress.getLocalHost().getHostAddress();
			request.setAttribute("IP", IP);
		}catch(Exception ex){ex.printStackTrace();}
		return SUCCESS;
	}
	
	
	
	public String collegeresult(){
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		AdmissionDAO svgLoadDAO = new AdmissionDAO();
		List<CollegeCourseDTO> shiftList;
		List<CollegeCourseDTO> versionList;
		List<CollegeCourseDTO> groupList;
		if(session.get("shiftList")==null)
		{
		 shiftList= svgLoadDAO.getShiftList(userDto.getEiin());
		 session.put("shiftList", shiftList);
		}
		request.setAttribute("shiftList", session.get("shiftList"));
		
		if(session.get("versionList")==null)
		{
		 versionList = svgLoadDAO.getVersionList(userDto.getEiin());
		 session.put("versionList", versionList);
		}
		request.setAttribute("versionList", session.get("versionList"));
		if(session.get("groupList")==null)
		{
		groupList = svgLoadDAO.getGroupList(userDto.getEiin());
		session.put("groupList", groupList);
		}
		request.setAttribute("groupList", session.get("groupList"));
		try
		{
			String IP=InetAddress.getLocalHost().getHostAddress();
			request.setAttribute("IP", IP);
		}catch(Exception ex){ex.printStackTrace();}
		return SUCCESS;
	}
	
	
	public String sendNewOtp(){
		String responseMsg="";
		if(session.get("isOtpRquestedInthisSession")!=null){
			responseMsg="You have already requested for one OTP(One Time Password). You will receive this OTP very soon in your mobile number. " +
					" \n\nIf you still want to get another OTP, Please logout and then request for new OTP.";
		}
		else{			
			
				CollegeDTO collegeDto = (CollegeDTO) session.get("user");
				CollegeDAO collegeDAO=new CollegeDAO();
				String otp=collegeDAO.generateOtp(collegeDto.getEiin(),getIpAddressDTO());
				session.put("otp_validation",false);
				session.put("isOtpRquestedInthisSession","true");
				responseMsg="An OTP has been sent to your Mobile Number :"+collegeDto.getCollegeMobile()+". Please write down the code in the textbox above and verify it.";
				SmsSender smsSender=new SmsSender();
				smsSender.setOtp(otp);
				smsSender.setMobile(collegeDto.getCollegeMobile());
				smsSender.setOperation("otp");
				Thread thread = new Thread(smsSender);
				thread.start();
		}
		try{
	    	 response.setContentType("text/html");
	    	 response.getWriter().write(responseMsg);
	          }
	        catch(Exception e) {e.printStackTrace();}
		return null;
	}
	public String validateOtp(){
		CollegeDTO collegeDto = (CollegeDTO) session.get("user");
		CollegeDAO collegeDAO=new CollegeDAO();
		boolean isValid=collegeDAO.isValidOtp(collegeDto.getEiin(), otp);
		String responseMsg="";
		if(isValid==true){
			responseMsg="valid";
			session.put("otp_validation",true);
			session.put("user_otp",otp);
			collegeDAO.changeOtpStatus(collegeDto.getEiin(), otp, "Y");
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
	
	public String saveMobileSendNewOtp(){
		String responseMsg="";
		if(session.get("isOtpRquestedInthisSession")!=null){
			responseMsg="You have already requested for one OTP(One Time Password). You will receive this OTP very soon in your mobile number. " +
					" \n\nIf you still want to get another OTP, Please logout and then request for new OTP.";
		}
		else{			
			
				CollegeDTO collegeDto = (CollegeDTO) session.get("user");
				CollegeDAO collegeDAO=new CollegeDAO();
				String otp=collegeDAO.generateOtp(collegeDto.getEiin(),getIpAddressDTO());
//				if(collegeDAO.changeMobileNo(collegeDto.getEiin(), mobile)){
					session.put("otp_validation",false);
					session.put("isOtpRquestedInthisSession","true");
					responseMsg="An OTP has been sent to your Mobile Number :"+mobile+". Please write down the OTP in the textbox below and verify it.";
					SmsSender smsSender=new SmsSender();
					smsSender.setText("Your otp is "+ otp);
					smsSender.setMobile(mobile);
					smsSender.setOperation("otp");
					Thread thread = new Thread(smsSender);
					thread.start();
//				} else 
//					responseMsg = "Could not update mobile no.";
		}
		try{
	    	 response.setContentType("text/html");
	    	 response.getWriter().write(responseMsg);
	    }
	        catch(Exception e) {e.printStackTrace();}
		return null;
	}
	
	public String verifyCollegeMobileValidateOtp(){
		String responseMsg="";
		if(!password.equalsIgnoreCase(cpassword))
		{
			responseMsg = "Password and re-type password donot match!!!";
		}
		CollegeDTO collegeDto = (CollegeDTO) session.get("user");
		CollegeDAO collegeDAO=new CollegeDAO();
		boolean isValid=collegeDAO.isValidOtp(collegeDto.getEiin(), otp);
		if(isValid==true){
			boolean isUpdated = collegeDAO.changeMobileVerificationStatus(collegeDto.getEiin(), "Y");
			if(isUpdated == true){
				collegeDto.setCollegeMobile(mobile);
				collegeDto.setMobileVerifiedYN("Y");
				session.put("user", collegeDto);
				responseMsg="valid";
				session.put("otp_validation",true);
				session.put("user_otp",otp);
				collegeDAO.changeOtpStatus(collegeDto.getEiin(), otp, "Y");
			} else
				responseMsg = "Could not verify mobile";
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
	
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
			
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
