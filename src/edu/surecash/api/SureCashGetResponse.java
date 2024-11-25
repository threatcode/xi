/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.surecash.api;


import java.sql.SQLException;

import oracle.jdbc.OracleCallableStatement;

import org.json.JSONException;
import org.json.JSONObject;

import edu.utils.connection.ConnectionManager;

/**
 *
 * @author user
 */
public class SureCashGetResponse {
    String status;
    String statusCode;
    String applicantID;
    String description;
    String TXNID;
    public SureCashGetResponse(){}
    
    public SureCashGetResponse(String status, String statusCode,String applicantID,
            String description){
        this.status=status;
        this.statusCode=statusCode;
        this.applicantID=applicantID;
        this.description=description;
    }
    SureCashGetResponse(String applicantID, String TXNID ){   
        this.applicantID=applicantID;
        this.TXNID=TXNID;
    }
    
    JSONObject constructJson() throws JSONException{
        JSONObject jsonData= new JSONObject();
        jsonData.put("ApplicantID", this.applicantID);
        jsonData.put("StatusCode", this.statusCode);
        jsonData.put("Status", this.status);
        jsonData.put("Description", this.description);
        jsonData.put("TransactionID", this.TXNID);
        return jsonData;
    }
    
    JSONObject process() throws JSONException{
         	
    	    	
        if(this.TXNID==null||this.applicantID==null){
           return NotActive();
        }else if(this.TXNID.equals("")||this.applicantID.equals("")){
            return InvalidApplicant();
        }
        
        String saveSt= insertSCPayment(applicantID,TXNID);
    	
//        String[] parts = saveSt.split("###");
//        //System.out.print(parts.length+saveSt);
//        String mobile = parts[0]; 
//        String text = parts[1];
//        int i;
//        SMSResource sms = new SMSResource();
//    	
//    	if(mobile.startsWith("018")||mobile.startsWith("016"))
//    	{
//    		 i=sms.sendSMSRobi(mobile,text);
//    	}
//    	else if(mobile.startsWith("017"))
//    	{
//    		sms.sendSMSSSL(mobile,text);
//    	}
//    	else
//    	{
//    		sms.sendSMSSSL(mobile,text);
//    	}
        
        
        
        
        
//        if(!saveSt.equalsIgnoreCase("success"))
//        	return InvalidApplicant();
        
        // Database Query to set TXNID and return either success and failure
        return success();
    }
    
    JSONObject success() throws JSONException{
        this.statusCode="200"; this.status="Active";this.description="Payment Save";
        return constructJson();
    }
    
    
    
    JSONObject NotActive() throws JSONException{
        this.statusCode="9009"; this.status="Failed";this.description="Applicant not active";
        return constructJson();
    }
    
    JSONObject InvalidApplicant() throws JSONException{
        this.statusCode="9004"; this.status="Failed";this.description="Payment Not Save";
        return constructJson();
    }
    
    
    
    static OracleCallableStatement stmtSCPayment = null;
	public static synchronized String insertSCPayment(String appid, String trackid)
    {
		
		String temp="";
		try {
			if(stmtSCPayment==null)
				stmtSCPayment = (OracleCallableStatement)(ConnectionManager.getConnectionStatic()).prepareCall("{ call Reg_Payment_Save(?,?,?,?,?,?,?,?,?,?,?)}");;
			int parameterIndex = 1;
			stmtSCPayment.setString(parameterIndex++, appid);
			stmtSCPayment.setString(parameterIndex++, "");
			stmtSCPayment.setString(parameterIndex++, "");
			stmtSCPayment.setString(parameterIndex++, "");
			stmtSCPayment.setString(parameterIndex++, trackid);
			stmtSCPayment.setString(parameterIndex++, "");
			stmtSCPayment.setString(parameterIndex++, "SC");
			stmtSCPayment.setString(parameterIndex++, "");
			stmtSCPayment.setString(parameterIndex++, "");
			stmtSCPayment.setString(parameterIndex++, "");
			stmtSCPayment.registerOutParameter(parameterIndex++, java.sql.Types.VARCHAR); 
			stmtSCPayment.executeUpdate();
			
			temp = stmtSCPayment.getString(11);
			
			stmtSCPayment.clearParameters();
			
		} catch (SQLException e) {
			System.out.println(e.toString());
			stmtSCPayment = null;
			ConnectionManager.closeStatic();
		}	
		
		return temp;
    }	
	
	
    
    
    
    
    
}
