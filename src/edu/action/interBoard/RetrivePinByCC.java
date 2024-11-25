package edu.action.interBoard;

import edu.action.common.BaseAction;
import edu.dao.interBoard.ApplicantInfoDAO;
import edu.dto.UserDTO;
import edu.dto.board.BoardDTO;
import edu.dto.interBoard.ApplicantInfoDTO;

public class RetrivePinByCC extends BaseAction{
	
	private static final long serialVersionUID = 2775177229066309566L;
	private String ssc_roll;
	private String ssc_reg;
	private String ssc_board;
	private String ssc_passing_year;
	private String given_mobile_number;
	
	private String st_name;
	private String f_name;
	private String m_name;
	private String dob;
	private String gpa;
	
	
	private String trans_id;
	private String operator;
	
		
	public String  execute()
	{
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		
		ApplicantInfoDAO appDAO = new ApplicantInfoDAO();
		
		ApplicantInfoDTO appInfo = appDAO.getAppInfo(ssc_roll,ssc_board,ssc_passing_year);
				
		ApplicantInfoDTO appInfo2 = appDAO.getAppInfoSC(ssc_roll,ssc_board,ssc_passing_year);
		
		
		
		if(!(appInfo2==null)){
			appInfo.setMobilenumber(appInfo2.getMobilenumber());
			appInfo.setScode(appInfo2.getScode());
			appInfo.setPaymentStatus(appInfo2.getPaymentStatus());
		}
		else
		{
			appInfo.setScode("Payment is not done yet");
		}
		
		
		
		
		
		request.setAttribute("appInfo",appInfo);
		
				
		return "success";
	}



	public String  getPinUdc()
	{
		
		UserDTO userDto = (UserDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		
		ApplicantInfoDAO appDAO = new ApplicantInfoDAO();
		
		ApplicantInfoDTO appInfo = appDAO.getAppInfo(ssc_roll,ssc_board,ssc_passing_year);

		ApplicantInfoDTO appInfo2 = appDAO.getAppInfoSC(ssc_roll,ssc_board,ssc_passing_year);
		
	
		if(appInfo2 != null){
			appInfo.setMobilenumber(appInfo2.getMobilenumber());
			appInfo.setScode(appInfo2.getScode());
			appInfo.setPaymentStatus(appInfo2.getPaymentStatus());
			appInfo.setAppCnt(appInfo2.getAppCnt());
			if(appInfo2.getAppCnt()>0)
				appInfo.setApplicationType("Completed ("+appInfo2.getAppCnt()+") ");
			else
				appInfo.setApplicationType("Not Completed");
			
		}
		else
		{
			appInfo.setScode("Payment is not done yet");
			appInfo.setApplicationType("Not Completed");
		}

		request.setAttribute("appInfo",appInfo);
		
				
		return "success";
	}
	
	public String  getTransUdc()
	{
		
		UserDTO userDto = (UserDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		
		ApplicantInfoDAO appDAO = new ApplicantInfoDAO();
		
		ApplicantInfoDTO appInfo = appDAO.getPaymentInfo(trans_id.toUpperCase(),operator);

			
		/*if(appInfo == null){
			
			appInfo.set("Payment is not done yet");
			appInfo.setApplicationType("Not Completed");
		}*/

		request.setAttribute("appInfo",appInfo);
		
				
		return "success";
	}
	
	
	
	
	
	
	
	
	
	public String getTrans_id() {
		return trans_id;
	}



	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}



	public String getOperator() {
		return operator;
	}



	public void setOperator(String operator) {
		this.operator = operator;
	}



	public String getGpa() {
		return gpa;
	}



	public void setGpa(String gpa) {
		this.gpa = gpa;
	}



	public String getSsc_roll() {
		return ssc_roll;
	}



	public void setSsc_roll(String ssc_roll) {
		this.ssc_roll = ssc_roll;
	}



	public String getSsc_reg() {
		return ssc_reg;
	}



	public void setSsc_reg(String ssc_reg) {
		this.ssc_reg = ssc_reg;
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



	public String getGiven_mobile_number() {
		return given_mobile_number;
	}



	public void setGiven_mobile_number(String given_mobile_number) {
		this.given_mobile_number = given_mobile_number;
	}



	public String getSt_name() {
		return st_name;
	}



	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}



	public String getF_name() {
		return f_name;
	}



	public void setF_name(String f_name) {
		this.f_name = f_name;
	}



	public String getM_name() {
		return m_name;
	}



	public void setM_name(String m_name) {
		this.m_name = m_name;
	}



	public String getDob() {
		return dob;
	}



	public void setDob(String dob) {
		this.dob = dob;
	}
	
	

}
