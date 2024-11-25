package edu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;

import edu.dto.application.ResponseDTO;
import edu.helpers.SmsSender;
import edu.utils.connection.ConnectionManager;

public class PaymentDAO {
	
	

	
	public boolean DDBL_Payment(String TXN_ID,String PAYER_MOBILE_NO ,String PAYMENT_DATE,String PAYMENT_AMOUNT,String BILL_NO,String BILLER_ID)
	{
		//rollBoardYYSecurityCode
		HashMap<String,String> hmBoard=new HashMap<String,String>();  
		hmBoard.put("DHA", "10");hmBoard.put("COM", "11");hmBoard.put("RAJ", "12");hmBoard.put("JES", "13");
		hmBoard.put("CHI", "14");hmBoard.put("BAR", "15");hmBoard.put("SYL", "16");hmBoard.put("DIN", "17");
		hmBoard.put("MAD", "18");hmBoard.put("TEC", "19");hmBoard.put("BOU", "20");
		
		System.out.println(TXN_ID+" - "+PAYER_MOBILE_NO+" - "+PAYMENT_DATE+" - "+BILL_NO);
		String SSC_ROLL_NO="";
		 String SSC_BOARD_ID ="";
		 String SSC_PASSING_YEAR="";
		 String scode="";
        if(BILL_NO.length()==17)
        {
          SSC_BOARD_ID =BILL_NO.substring(0,3);
          SSC_PASSING_YEAR="20"+BILL_NO.substring(3, 5);;
		  SSC_ROLL_NO=BILL_NO.substring(5,11);
		  scode=BILL_NO.substring(11);;
        }
        else
        {
        	SSC_BOARD_ID =BILL_NO.substring(0,3);
            SSC_PASSING_YEAR="20"+BILL_NO.substring(3, 5);;
  		  SSC_ROLL_NO=BILL_NO.substring(5,16);
  		  scode=BILL_NO.substring(16);;
        }
		SSC_BOARD_ID=hmBoard.get(SSC_BOARD_ID);
		
		if(SSC_ROLL_NO==null || SSC_ROLL_NO.length()<1)SSC_ROLL_NO="0000";
		if(SSC_BOARD_ID==null || SSC_BOARD_ID.length()<1)SSC_BOARD_ID="0000";
		
		
		boolean tmp = false;
		ResponseDTO response =new ResponseDTO();
		Connection conn = ConnectionManager.getProcConnection();
		oracle.jdbc.OracleCallableStatement stmt = null;
		
		
		try
		{
		stmt = (OracleCallableStatement)conn.prepareCall("{ call Reg_Payment_Save(?,?,?,?,?,?,?,?,?,?,?)}");
		
		int ucount = 0;
		
			stmt.setString(1, "NA");
			stmt.setString(2, SSC_ROLL_NO);
			stmt.setString(3, SSC_BOARD_ID);
			stmt.setString(4, SSC_PASSING_YEAR);
			stmt.setString(5, TXN_ID);
			stmt.setString(6, PAYER_MOBILE_NO);
			stmt.setString(7, BILLER_ID);
			stmt.setString(8, PAYMENT_DATE);
			stmt.setString(9, BILL_NO);
			stmt.setString(10, "ROCKET");
			
		    stmt.registerOutParameter(11, java.sql.Types.VARCHAR); 
			
		    stmt.executeUpdate();
			 if(!stmt.getString(11).equals("")){
	            	tmp = true;
	            	String [] data=stmt.getString(11).split("###");
	            	String ContactNo = data[0];
	                String SMStext = data[1];
	            	SmsSender smsSender=new SmsSender();
					smsSender.setMobile(ContactNo);
					smsSender.setText(SMStext);
					smsSender.setOperation("RegConfirmSMS");
					Thread thread = new Thread(smsSender);
					thread.start();
			 }
			
		} 
		catch (Exception e){e.printStackTrace();}
		finally{
			try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null; 		
		}
		
		return tmp;
		
	}
	
	public boolean DDBL_Registration_Payment(String TXN_ID,String PAYER_MOBILE_NO ,String PAYMENT_DATE,String PAYMENT_AMOUNT,String BILL_NO,String BILLER_ID)
	{
		System.out.println(TXN_ID+" - "+PAYER_MOBILE_NO+" - "+PAYMENT_DATE+" - "+BILL_NO);

		
		boolean tmp = false;
		Connection conn = ConnectionManager.getWriteConnection();
		//String sql = "insert into payment_info values(?,?,?,?,?)";
		String sql = "insert into REG_PAYMENT_INFO(APPLICATION_ID ,  TRANSACTION_ID,  PAYER_MOBILE_NO, PAYMENT_DATE, PAYMENT_AMOUNT, BILL_NO, BILLER_ID) values(?,?,?,?,?,?,?)";
		
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		int ucount = 0;
		try
		{
			String APPLICATION_ID=BILL_NO;
			stmt = conn.prepareStatement(sql);
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, APPLICATION_ID);
			stmt.setString(parameterIndex++, TXN_ID);
			stmt.setString(parameterIndex++, PAYER_MOBILE_NO);
			stmt.setString(parameterIndex++, PAYMENT_DATE);
			stmt.setString(parameterIndex++, PAYMENT_AMOUNT);
			stmt.setString(parameterIndex++, BILL_NO);
			stmt.setString(parameterIndex++, BILLER_ID);
			
			ucount = stmt.executeUpdate();
			if(ucount>0)
			{
				try{
				 sql="update BTEB_RESULT_MERIT set STATUS='Approved' where APPLICATION_ID='"+BILL_NO+"'";
				 stmt1 = conn.prepareStatement(sql);
				 stmt1.executeUpdate();
				}catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
			}
			tmp = true;
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return tmp;
		
	}
	
	/*
	public boolean DDBL_Payment(String transaction_id, String sfrom, String sdate, String samount, String bill_no)
	{
		System.out.println(transaction_id+""+sfrom+""+sdate+""+samount+""+bill_no);
		boolean tmp = false;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql = "insert into payment_info values(?,?,?,?,?)";
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		int ucount = 0;
		try
		{
			stmt = conn.prepareStatement(sql);
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, transaction_id);
			stmt.setString(parameterIndex++, sfrom);
			stmt.setString(parameterIndex++, sdate);
			stmt.setString(parameterIndex++, samount);
			stmt.setString(parameterIndex++, bill_no);
			ucount = stmt.executeUpdate();			
			if(ucount>0)
			{
				try{
					if(bill_no.startsWith("1"))
						sql="update APPLICATION_INFO set PAYMENT='Y' where APPLICATION_ID='"+bill_no+"'";
					else if(bill_no.startsWith("2"))
						sql="update GOVT_APPLICATION set PAYMENT_STATUS='Y' where APPLICATION_ID='"+bill_no+"'";
					else 
						sql="update BOARD_BTEB.APPLICATION_COLLEGES set PAYMENT_STATUS='Y' where APPLICATION_ID='"+bill_no+"'";
					stmt1 = conn.prepareStatement(sql);
					stmt1.executeUpdate();
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
				tmp = true;
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();stmt1.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return tmp;
		
	}
	
	*/
	
	public boolean DDBLCheckBillNo(String BILL_NO)
	{
		
		System.out.println("::::::::::Bill NO CHECK:::::::::::" +BILL_NO );
		
		HashMap<String,String> hmBoard=new HashMap<String,String>();  
		hmBoard.put("DHA", "10");hmBoard.put("COM", "11");hmBoard.put("RAJ", "12");hmBoard.put("JES", "13");
		hmBoard.put("CHI", "14");hmBoard.put("BAR", "15");hmBoard.put("SYL", "16");hmBoard.put("DIN", "17");
		hmBoard.put("MAD", "18");hmBoard.put("BTE", "19");hmBoard.put("BOU", "19");
		
		
		
		String SSC_PASSING_YEAR=BILL_NO.substring(0,4);
		String SSC_BOARD_ID =BILL_NO.substring(4,7);
		String SSC_ROLL_NO=BILL_NO.substring(7);;
		
		SSC_BOARD_ID=hmBoard.get(SSC_BOARD_ID);
		
		if(SSC_ROLL_NO==null || SSC_ROLL_NO.length()<1)SSC_ROLL_NO="0000";
		if(SSC_BOARD_ID==null || SSC_BOARD_ID.length()<1)SSC_BOARD_ID="0000";
		
		
		boolean tmp = false;
		Connection conn = ConnectionManager.getWriteConnection();
		//String sql = "insert into payment_info values(?,?,?,?,?)";
		String sql = "SELECT ROLL_NO, BOARD_ID, PASSING_YEAR FROM BOARD_DATA_SSC WHERE ROLL_NO= ? and  BOARD_ID=? and  PASSING_YEAR=?";
		
		PreparedStatement stmt = null;
		int ucount = 0;
		try
		{
			stmt = conn.prepareStatement(sql);
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, SSC_ROLL_NO);
			stmt.setString(parameterIndex++, SSC_BOARD_ID);
			stmt.setString(parameterIndex++, SSC_PASSING_YEAR);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
			 tmp = true;
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return tmp;
		
	}
	
	
	public boolean DDBLCheckBillNoAsAppid(String BILL_NO, String PAYMENT_AMOUNT)
	{
		
		System.out.println("Bill NO CHECK:" +BILL_NO +" Amount :"+ PAYMENT_AMOUNT);
		
		String APPLICATION_ID=BILL_NO;
		int amount=Integer.parseInt(PAYMENT_AMOUNT);
		
		boolean tmp = false;
		Connection conn = ConnectionManager.getWriteConnection();
		//String sql = "insert into payment_info values(?,?,?,?,?)";
		String sql = "SELECT ai.APPLICATION_ID, ai.PROGRAM_ID FROM APPLICATION_INFO ai, BTEB_RESULT_MERIT me  WHERE me.APPLICATION_ID=ai.APPLICATION_ID and me.APPLICATION_ID= ? ";
		
		PreparedStatement stmt = null;
		int ucount = 0;
		try
		{
			stmt = conn.prepareStatement(sql);
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, APPLICATION_ID);

			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
			 if(rs.getString("PROGRAM_ID").equals("30"))
			 {
				 if(amount==192)
					 tmp=true;
			 }
			 else
			 {
				 if(amount==235)
					 tmp=true;
			 }
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return tmp;
		
	}
	
	public boolean DDBL_Send(String bill_no)
	{
		boolean tmp = false;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql = "";
		PreparedStatement stmt = null;
		int ucount = 0;
		try
		{
			if(bill_no.startsWith("1"))
				sql="update APPLICATION_INFO set dbblsend='Y' where APPLICATION_ID='"+bill_no+"'";
			else if(bill_no.startsWith("2"))
				sql="update GOVT_APPLICATION set dbblsend='Y' where APPLICATION_ID='"+bill_no+"'";
			else 
				sql="update BOARD_BTEB.APPLICATION_COLLEGES set ttsend='Y' where APPLICATION_ID='"+bill_no+"'";
			stmt = conn.prepareStatement(sql);
			ucount = stmt.executeUpdate();			
			if(ucount>0)
			{
				tmp = true;
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return tmp;
		
	}
	
	public List<String> getApplicantIdNotSend(){
		
		List<String> listAppliDTO = new ArrayList<String>();
		Connection conn = ConnectionManager.getReadConnection();		
		String sql ="select application_id from (" +
				" select application_id from APPLICATION_INFO where dbblsend='N'" +
				" union" +
				" select application_id from GOVT_APPLICATION where dbblsend='N'" +
				" union" +
				" select application_id from BOARD_BTEB.APPLICATION_COLLEGES where ttsend='N'" +
				")";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while(r.next())
			{
				listAppliDTO.add(r.getString("APPLICATION_ID"));
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{
			try{
				stmt.close();
				conn.close();
				} 
			catch (Exception e){
					e.printStackTrace();
				}
			stmt = null;
			conn = null;
		}
		
		return listAppliDTO;
	}
	
	//////////////////////////////////
	public boolean DDBLCheckBillNoInsert(String BILL_NO)
	{
		
		System.out.println("::::::::::Bill NO CHECK:::::::::::" +BILL_NO );
		
		HashMap<String,String> hmBoard=new HashMap<String,String>();  
		hmBoard.put("DHA", "10");hmBoard.put("COM", "11");hmBoard.put("RAJ", "12");hmBoard.put("JES", "13");
		hmBoard.put("CHI", "14");hmBoard.put("BAR", "15");hmBoard.put("SYL", "16");hmBoard.put("DIN", "17");
		hmBoard.put("MAD", "18");hmBoard.put("BTE", "19");hmBoard.put("BOU", "19");
		
		
		
		String SSC_PASSING_YEAR=BILL_NO.substring(0,4);
		String SSC_BOARD_ID =BILL_NO.substring(4,7);
		String SSC_ROLL_NO=BILL_NO.substring(7);;
		
		SSC_BOARD_ID=hmBoard.get("DHA");
		
		if(SSC_ROLL_NO==null || SSC_ROLL_NO.length()<1)SSC_ROLL_NO="0000";
		if(SSC_BOARD_ID==null || SSC_BOARD_ID.length()<1)SSC_BOARD_ID="0000";
		
		
		boolean tmp = false;
		Connection conn = ConnectionManager.getWriteConnection();
		//String sql = "insert into payment_info values(?,?,?,?,?)";
		String sql = "SELECT ROLL_NO, BOARD_ID, PASSING_YEAR FROM BOARD_DATA_SSC WHERE ROLL_NO= ? and  BOARD_ID=? and  PASSING_YEAR=?";
		
		PreparedStatement stmt = null;
		int ucount = 0;
		try
		{
			stmt = conn.prepareStatement(sql);
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, SSC_ROLL_NO);
			stmt.setString(parameterIndex++, SSC_BOARD_ID);
			stmt.setString(parameterIndex++, SSC_PASSING_YEAR);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
			 tmp = true;
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return tmp;
		
	}
	//////////////////////////////////

}
