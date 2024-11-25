package edu.utils.sms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.utils.connection.ConnectionManager;

public class SMS {
	public static void main(String[] args)
	{
		//ConnectionManager cm = new ConnectionManager();
		Connection conn = ConnectionManager.getConnection();
		int total=0;
		//Connection conn = ConnectionManager.getConnection();
		String sql= " select APPLICANT_ID, MOBILE, TEXT from APPLICANT_MESS where STATUS='N' and board_id=13 order by APPLICANT_ID";
		
		//String sql= " select APPLICANT_ID, MOBILE, TEXT from APPLICANT_MESS where STATUS='M' order by APPLICANT_ID";
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		   
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			SmsSenderExtra smsSender=new SmsSenderExtra();
			while (r.next())
			{
				
				smsSender.setAppid(r.getString("APPLICANT_ID"));
				smsSender.setMobile(r.getString("MOBILE"));
				smsSender.setText(r.getString("TEXT"));			
				smsSender.sendSMSSSL_p();
//				Thread thread = new Thread(smsSender);
//				thread.start();
				total++;
				if(total==500)
				{
					total=0;
					try{Thread.sleep(5000);}catch(Exception e){e.printStackTrace();};
				}
			}
		} 
		catch (Exception e){e.printStackTrace();}
 		finally{try{stmt.close();conn.close();} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}
	}	
	

}
