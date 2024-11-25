package edu.action.application;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.utils.connection.ConnectionManager;

public class receiveWebApplication implements Runnable{
	
	private String ssc_roll;
	private String ssc_board;
	private String ssc_year;
	private String ssc_gpa;
	private String trackid;
	private String app_id;
	private String contact_no;
	
	static PreparedStatement stmtPaymentDataInsert3 = null;
	static String sqlPaymentDataInsert3 = "insert into APPLICATION_PAYMENT_WEB (ROLL_NO, BOARD_ID, PASSING_YEAR, GPA , TT_TRANS_NUMBER, " +
			"APPLICATION_ID, CONTACT_NO) values ( ?,?,?,?,?,?,?)";
	
	public void run(){
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

            stmtPaymentDataInsert3.executeUpdate();
            stmtPaymentDataInsert3.clearParameters();
        	
        
      } catch (SQLException e) {
        System.out.println(e.toString());
        stmtPaymentDataInsert3 = null;
        ConnectionManager.closeStatic();
    }	
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


	public String getSsc_gpa() {
		return ssc_gpa;
	}


	public void setSsc_gpa(String ssc_gpa) {
		this.ssc_gpa = ssc_gpa;
	}


	public String getTrackid() {
		return trackid;
	}


	public void setTrackid(String trackid) {
		this.trackid = trackid;
	}


	public String getApp_id() {
		return app_id;
	}


	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}


	public String getContact_no() {
		return contact_no;
	}


	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	
	
}
