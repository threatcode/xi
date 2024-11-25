package edu.action.applicant;

import edu.action.common.BaseAction;
import edu.dao.applicant.InsertFeedBackDAO;


public class InsetFeedBack extends BaseAction{

	private static final long serialVersionUID = -1479737896297674123L;
	private String ssc_roll;
	private String ssc_board;
	private String ssc_passing_year;
	private String mobile_number;
	private String message;
	
	public String insertFeedBack(){
				
		InsertFeedBackDAO insertFeedBackDAO = new InsertFeedBackDAO();

		boolean resp=insertFeedBackDAO.insertFeedBack(ssc_roll, ssc_board, ssc_passing_year, mobile_number, message);
		 if (resp == true) {
			request.setAttribute("successMessage","Your Message has been successfully sent.");
		} 
		else {
			request.setAttribute("successMessage","Your Message has not been sent ....!!");
		   
		}
		
		return "success";

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
	public String getSsc_passing_year() {
		return ssc_passing_year;
	}
	public void setSsc_passing_year(String ssc_passing_year) {
		this.ssc_passing_year = ssc_passing_year;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
