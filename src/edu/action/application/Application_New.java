package edu.action.application;

import java.util.List;

import org.apache.struts2.dispatcher.SessionMap;

import edu.action.common.BaseAction;
import edu.dao.application.SscDataDAO;
import edu.dto.application.ApplicantDTO;
import edu.dto.application.ChoiceDTO;

public class Application_New extends BaseAction {
	private String trxid;
	private String ssc_roll;
	private String ssc_board;
	private String ssc_year;
	private String ssc_reg;
	private String user_captcha;
	private String tx_captcha;
	private String java_captcha;
	private String ssc_mother;
	private String eiin;
	private String district_id;
	private String thana_id;
	private String helper_board_id;
	private List<ChoiceDTO> choice;
	private ApplicantDTO applicant;
	private String college_search_type;
	private String security_code;
	private String application_id;
	private String merit_type;
	private String confirm_mobile_number;
	
	private String quota_ff;
	private String quota_eq;
	private String quota_bksp;
	private String quota_expatriate;
	private String contactno;
	private String updateCount;
	private String organization;	
	
	
	public String getTrxid() {
		return trxid;
	}


	public void setTrxid(String trxid) {
		this.trxid = trxid;
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


	public String getUser_captcha() {
		return user_captcha;
	}


	public void setUser_captcha(String user_captcha) {
		this.user_captcha = user_captcha;
	}


	public String getTx_captcha() {
		return tx_captcha;
	}


	public void setTx_captcha(String tx_captcha) {
		this.tx_captcha = tx_captcha;
	}


	public String getJava_captcha() {
		return java_captcha;
	}


	public void setJava_captcha(String java_captcha) {
		this.java_captcha = java_captcha;
	}


	public String getSsc_mother() {
		return ssc_mother;
	}


	public void setSsc_mother(String ssc_mother) {
		this.ssc_mother = ssc_mother;
	}


	public String getEiin() {
		return eiin;
	}


	public void setEiin(String eiin) {
		this.eiin = eiin;
	}


	public String getDistrict_id() {
		return district_id;
	}


	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}


	public String getThana_id() {
		return thana_id;
	}


	public void setThana_id(String thana_id) {
		this.thana_id = thana_id;
	}


	public String getHelper_board_id() {
		return helper_board_id;
	}


	public void setHelper_board_id(String helper_board_id) {
		this.helper_board_id = helper_board_id;
	}


	public List<ChoiceDTO> getChoice() {
		return choice;
	}


	public void setChoice(List<ChoiceDTO> choice) {
		this.choice = choice;
	}


	public ApplicantDTO getApplicant() {
		return applicant;
	}


	public void setApplicant(ApplicantDTO applicant) {
		this.applicant = applicant;
	}


	public String getCollege_search_type() {
		return college_search_type;
	}


	public void setCollege_search_type(String college_search_type) {
		this.college_search_type = college_search_type;
	}


	public String getSecurity_code() {
		return security_code;
	}


	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}


	public String getApplication_id() {
		return application_id;
	}


	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}


	public String getMerit_type() {
		return merit_type;
	}


	public void setMerit_type(String merit_type) {
		this.merit_type = merit_type;
	}


	public String getConfirm_mobile_number() {
		return confirm_mobile_number;
	}


	public void setConfirm_mobile_number(String confirm_mobile_number) {
		this.confirm_mobile_number = confirm_mobile_number;
	}


	public String getQuota_ff() {
		return quota_ff;
	}


	public void setQuota_ff(String quota_ff) {
		this.quota_ff = quota_ff;
	}


	public String getQuota_eq() {
		return quota_eq;
	}


	public void setQuota_eq(String quota_eq) {
		this.quota_eq = quota_eq;
	}


	public String getQuota_bksp() {
		return quota_bksp;
	}


	public void setQuota_bksp(String quota_bksp) {
		this.quota_bksp = quota_bksp;
	}


	public String getQuota_expatriate() {
		return quota_expatriate;
	}


	public void setQuota_expatriate(String quota_expatriate) {
		this.quota_expatriate = quota_expatriate;
	}


	public String getContactno() {
		return contactno;
	}


	public void setContactno(String contactno) {
		this.contactno = contactno;
	}


	public String getUpdateCount() {
		return updateCount;
	}


	public void setUpdateCount(String updateCount) {
		this.updateCount = updateCount;
	}


	public String getOrganization() {
		return organization;
	}


	public void setOrganization(String organization) {
		this.organization = organization;
	}


	public String applicationInfoCheck_TT()
	{
		 
		String checkSum=(String)session.get("CorrectAnswer");
		boolean checkCaptcha = false; 
		checkCaptcha = user_captcha.equalsIgnoreCase(checkSum);
		if(!checkCaptcha){
			try {
				response.setContentType("text/html");
				response.getWriter().write("ce");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		if(checkCaptcha){
			((SessionMap<String,Object>)session).invalidate();
			session.put("CorrectAnswer",user_captcha);
			SscDataDAO sscDAO=new SscDataDAO();
			applicant=sscDAO.getApplication_New_TT(ssc_roll, ssc_board, ssc_year,ssc_reg);	
			session.put("is_active",applicant.getIs_active());
			if(applicant.getMsg().equalsIgnoreCase("nossc"))
			{
				request.setAttribute("error", "SSC");
				return "invalid_info";
			}
			else if(applicant.getMsg().equalsIgnoreCase("nopayment"))
			{
				session.put("ssc_roll", ssc_roll);
				session.put("ssc_board", ssc_board);
				session.put("ssc_year", ssc_year);
				session.put("ssc_reg", ssc_reg);
				return "nopayment";
			}
			else if(applicant.getMsg().equalsIgnoreCase("TxInfo"))
			{
				session.put("ssc_roll", ssc_roll);
				session.put("ssc_board", ssc_board);
				session.put("ssc_year", ssc_year);
				session.put("ssc_reg", ssc_reg);
				return "TxInfo";
			}
			else if(applicant.getMsg().equalsIgnoreCase("newapplication"))
			{			
				session.put("ssc_roll", ssc_roll);
				session.put("ssc_board", ssc_board);
				session.put("ssc_year", ssc_year);
				session.put("ssc_reg", ssc_reg);
				session.put("contactno", applicant.getContactno());
				if(applicant.getIs_active().equalsIgnoreCase("1"))
				 return SUCCESS;
				else
				 return "np";
			}
			else if(applicant.getMsg().equalsIgnoreCase("alreadyapplied"))
			{
				request.setAttribute("ssc_roll_app", ssc_roll);
				request.setAttribute("ssc_board_app", ssc_board);
				request.setAttribute("ssc_year_app", ssc_year);
				request.setAttribute("ssc_reg_app", ssc_reg);
				session.put("ssc_roll", ssc_roll);
				session.put("ssc_board", ssc_board);
				session.put("ssc_year", ssc_year);
				session.put("ssc_reg", ssc_reg);				
				//return "already_applied";       //here should applicant choices
				//change by joy
				//return "newappWithWEB";//"newappWithSMS";       //here should applicant choices 
				session.put("scode", applicant.getScode());
				session.put("no_of_update", applicant.getNo_of_update());
				session.put("contactno", applicant.getContactno());
				if(applicant.getIs_active().equalsIgnoreCase("1"))
				  return "updateEditApplication"; 
				else
					 return "readOnlyApplication";
			}
			else if(applicant.getMsg().equalsIgnoreCase("onlysmsapplication"))
			{
				request.setAttribute("ssc_roll_app", ssc_roll);
				request.setAttribute("ssc_board_app", ssc_board);
				request.setAttribute("ssc_year_app", ssc_year);
				request.setAttribute("ssc_reg_app", ssc_reg);
				session.put("ssc_roll", ssc_roll);
				session.put("ssc_board", ssc_board);
				session.put("ssc_year", ssc_year);
				session.put("ssc_reg", ssc_reg);
				session.put("appid", applicant.getApplication_id());
				session.put("webpayment", "no");
				session.put("no_of_update", applicant.getNo_of_update());
				session.put("contactno", applicant.getContactno());
				session.put("scode", applicant.getScode());
				return "onlysmsapplication";       //here should applicant choices 
			}
			else if(applicant.getMsg().equalsIgnoreCase("p_no_application"))
			{			
				session.put("ssc_roll", ssc_roll);
				session.put("ssc_board", ssc_board);
				session.put("ssc_year", ssc_year);
				session.put("ssc_reg", ssc_reg);
				session.put("contactno", applicant.getContactno());
				return "p_no_application";
			}
			if(applicant.getMsg().equalsIgnoreCase("readOnlyApplication"))
			{
//				request.setAttribute("ssc_roll_app", ssc_roll);
//				request.setAttribute("ssc_board_app", ssc_board);
//				request.setAttribute("ssc_year_app", ssc_year);
//				request.setAttribute("ssc_reg_app", ssc_reg);
//				session.put("ssc_roll", ssc_roll);
//				session.put("ssc_board", ssc_board);
//				session.put("ssc_year", ssc_year);
//				session.put("ssc_reg", ssc_reg);	
//				session.put("smsapplication", String.valueOf(choice.size()) );
//				return "readOnlyApplication";
				
				
				
				
				request.setAttribute("ssc_roll_app", ssc_roll);
				request.setAttribute("ssc_board_app", ssc_board);
				request.setAttribute("ssc_year_app", ssc_year);
				request.setAttribute("ssc_reg_app", ssc_reg);
				session.put("ssc_roll", ssc_roll);
				session.put("ssc_board", ssc_board);
				session.put("ssc_year", ssc_year);
				session.put("ssc_reg", ssc_reg);
				session.put("appid", applicant.getApplication_id());
				session.put("webpayment", "no");
				session.put("no_of_update", applicant.getNo_of_update());
				session.put("contactno", applicant.getContactno());
				session.put("scode", applicant.getScode());
				return "onlysmsapplication"; 
			}
			else return "invalid_info";
		}
		else return "invalid_info";
		
	}	

}
