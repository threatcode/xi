package edu.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import oracle.jdbc.OracleCallableStatement;

import edu.surecash.api.SMSResource;
import edu.utils.connection.ConnectionManager;

public class ResultLog implements Runnable{
	private String op = "";
	private String xForward = "";
	private String via = "";
	private String remoteAddress = "";
	private String roll = "";
	private String board = "";
	private String pyear = "";
	private String reg = "";
	private double rtime = 0;
	private String resultlocation = "";
	
	
	private String appid = "";
	private String trackid = "";
	private String paymobile = "";
	private String paytime = "";
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public String getPyear() {
		return pyear;
	}
	public void setPyear(String pyear) {
		this.pyear = pyear;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getXForward() {
		return xForward;
	}
	public void setXForward(String forward) {
		xForward = forward;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getRemoteAddress() {
		return remoteAddress;
	}
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}
	public void run(){
		if(op.equalsIgnoreCase("first")){
			insertFirstLog(xForward, via, remoteAddress);
		}
		if(op.equalsIgnoreCase("second")){
			insertSecondLog(xForward, via, remoteAddress, roll, board, pyear, reg, rtime, resultlocation);
		}
		if(op.equalsIgnoreCase("ConPayment")){
			insertConPayment(appid, trackid, paymobile, paytime);
		}
	}
	
	
	static PreparedStatement stmtFirstLogInsert = null;
	static String sqlFirstLogInsert = "insert into resultFirstLog (xForward, via, remoteAddress) values ( ?,?,? )";
	public static synchronized void insertFirstLog(String xForward, String via, String remoteAddress)
    {
		try {
			if(stmtFirstLogInsert==null)
				stmtFirstLogInsert = ConnectionManager.getConnectionStatic().prepareStatement(sqlFirstLogInsert);
			int parameterIndex = 1;
			stmtFirstLogInsert.setString(parameterIndex++, xForward);
			stmtFirstLogInsert.setString(parameterIndex++, via);
			stmtFirstLogInsert.setString(parameterIndex++, remoteAddress);
			stmtFirstLogInsert.executeUpdate();
			stmtFirstLogInsert.clearParameters();
		} catch (SQLException e) {
			System.out.println(e.toString());
			stmtFirstLogInsert = null;
			ConnectionManager.closeStatic1();
		}	
    }
	

	
	static PreparedStatement stmtSecondLogInsert = null;
	static String sqlSecondLogInsert = "insert into resultSecondLog (xForward, via, remoteAddress, roll, board, year, rtime, resultlocation) values " +
			"( ?,?,?,?,?,?,?,? )";
	public static synchronized void insertSecondLog(String xForward, String via, String remoteAddress,
			String roll, String board, String pyear, String reg, double rtime, String resultlocation)
    {
		try {
			if(stmtSecondLogInsert==null)
				stmtSecondLogInsert = ConnectionManager.getConnectionStatic().prepareStatement(sqlSecondLogInsert);
			int parameterIndex = 1;
			stmtSecondLogInsert.setString(parameterIndex++, xForward);
			stmtSecondLogInsert.setString(parameterIndex++, via);
			stmtSecondLogInsert.setString(parameterIndex++, remoteAddress);
			stmtSecondLogInsert.setString(parameterIndex++, roll);
			stmtSecondLogInsert.setString(parameterIndex++, board);
			stmtSecondLogInsert.setString(parameterIndex++, pyear);
			stmtSecondLogInsert.setDouble(parameterIndex++, rtime);
			stmtSecondLogInsert.setString(parameterIndex++, resultlocation);
			stmtSecondLogInsert.executeUpdate();
			stmtSecondLogInsert.clearParameters();
		} catch (SQLException e) {
			System.out.println(e.toString());
			stmtSecondLogInsert = null;
			ConnectionManager.closeStatic1();
		}	
    }
	
	
	
	static OracleCallableStatement stmtConPayment = null;
	public static synchronized void insertConPayment(String appid, String trackid, String paymobile, String paytime)
    {
		try {
			if(stmtConPayment==null)
				stmtConPayment = (OracleCallableStatement)(ConnectionManager.getConnectionStatic()).prepareCall("{ call Reg_Payment_Save(?,?,?,?,?,?,?,?,?,?,?)}");;
			int parameterIndex = 1;
			stmtConPayment.setString(parameterIndex++, appid);
			stmtConPayment.setString(parameterIndex++, "");
			stmtConPayment.setString(parameterIndex++, "");
			stmtConPayment.setString(parameterIndex++, "");
			stmtConPayment.setString(parameterIndex++, trackid);
			stmtConPayment.setString(parameterIndex++, paymobile);
			stmtConPayment.setString(parameterIndex++, "TT");
			stmtConPayment.setString(parameterIndex++, paytime);
			stmtConPayment.setString(parameterIndex++, "");
			stmtConPayment.setString(parameterIndex++, "");
			stmtConPayment.registerOutParameter(parameterIndex, java.sql.Types.VARCHAR); 
			stmtConPayment.executeUpdate();
			
//			String SMSText = stmtConPayment.getString(parameterIndex);
//			String[] parts = SMSText.split("###");
//	        String mobile = parts[0]; 
//	        String text = parts[1];
//	        int i;
//	        SMSResource sms = new SMSResource();
	    	
//	    	if(mobile.startsWith("018")||mobile.startsWith("016"))
//	    		sms.sendSMSSSL(mobile,text);
//	    	else if(mobile.startsWith("017"))
//	    		i=sms.sendSMSRobi(mobile,text);
//	    	else
//	    		sms.sendSMSSSL(mobile,text);
		
			
			stmtConPayment.clearParameters();
		} catch (SQLException e) {
			System.out.println(e.toString());
			stmtConPayment = null;
			ConnectionManager.closeStatic();
		}	
    }	
	
	
	
	public double getRtime() {
		return rtime;
	}
	public void setRtime(double rtime) {
		this.rtime = rtime;
	}
	public String getResultlocation() {
		return resultlocation;
	}
	public void setResultlocation(String resultlocation) {
		this.resultlocation = resultlocation;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getTrackid() {
		return trackid;
	}
	public void setTrackid(String trackid) {
		this.trackid = trackid;
	}
	public String getPaymobile() {
		return paymobile;
	}
	public void setPaymobile(String paymobile) {
		this.paymobile = paymobile;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	
	
	

}
