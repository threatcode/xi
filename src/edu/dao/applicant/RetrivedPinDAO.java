package edu.dao.applicant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleCallableStatement;



import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



import edu.dto.applicant.MobChangeDTO;
import edu.dto.applicant.PinRetrivalDTO;
import edu.utils.connection.ConnectionManager;

public class RetrivedPinDAO {
	
	
	public PinRetrivalDTO getRetrivedPin(String ssc_roll,String ssc_reg,String ssc_board,String ssc_passing_year,String given_mobile_number, String mname, String trn_no){
		
		PinRetrivalDTO pinRetrivalDTO = null;

		//System.out.println("ssc_roll:"+ssc_roll+"ssc_reg:"+ssc_reg+"ssc_board:"+ssc_board+"ssc_passing_year:"+ssc_passing_year+"given_mobile_number:"+given_mobile_number);
		
		Connection conn = ConnectionManager.getReadConnection();
		
		/*String sql ="SELECT distinct app.application_id APPLICATION_ID, app.PIN_NUMBER PIN\n" +
	              "  FROM APPLICATION_INFO app, APPLICATION_COLLEGES col,board_data_ssc board\n" +
	              "  WHERE  APP.SSC_ROLL_NO =?\n" +
	              "      AND  APP.SSC_REG =?\n" +
	              "      AND  APP.SSC_BOARD_ID =?\n" +
	              "      AND  APP.SSC_PASSING_YEAR =?\n" +
	              "      AND  app.application_id = col.application_id\n" +
	              "      AND  APP.application_id =board.application_id\n" +
	              "      AND  col.application_id =board.application_id\n" +
	              "  	 AND  APP.SSC_ROLL_NO =BOARD.ROLL_NO\n" +
	              "      AND  APP.SSC_REG =BOARD.REG_NO\n" +
	              "      AND  APP.SSC_BOARD_ID =BOARD.BOARD_ID\n" +
	              "      AND  APP.SSC_PASSING_YEAR =BOARD.PASSING_YEAR\n" +
	              "      AND (col.MOBILE_NO in (?) OR  board.contact_no=?)";*/
		
		
		String sql ="";
				
			/*	"select APPLICANT_ID, SCODE, CONTACT_NO  " +
				" from APPLICANT_INFO ai, board_data_ssc bs    " +
				" where bs.ROLL_NO=?  " +
				" and bs.REG_NO=?      " +
				" and bs.BOARD_ID=?  " +
				" and bs.PASS_YEAR=?  " +
				" and ai.CONTACT_NO=?  " +
				" and replace(lower(bs.MOTHER_NAME),' ','') = replace(lower(?),' ','') " +
				" and ai.SCODE is not null " +
				" and ai.ROLL_NO=bs.ROLL_NO " +
				" and ai.BOARD_ID=bs.BOARD_ID " +
				" and ai.PASS_YEAR=bs.PASS_YEAR " ;*/

		
		sql="select APPLICANT_ID, SCODE, ai.CONTACT_NO  " +
				" from APPLICANT_INFO ai, board_data_ssc bs, PAYMENT_DETAILS pd    " +
				" where bs.ROLL_NO=? " +
				" and bs.REG_NO=?  " +
				" and bs.BOARD_ID=?  " +
				" and bs.PASS_YEAR=? " +
				" and ai.CONTACT_NO=? " +
				" and replace(lower(bs.MOTHER_NAME),' ','') = replace(lower(?),' ','') " +
				" and ai.SCODE is not null " +
				" and ai.ROLL_NO=bs.ROLL_NO " +
				" and ai.BOARD_ID=bs.BOARD_ID " +
				" and ai.PASS_YEAR=bs.PASS_YEAR " +
				" and ai.ROLL_NO=pd.ROLL_NO " +
				" and ai.BOARD_ID=pd.BOARD_ID " +
				" and ai.PASS_YEAR=pd.PASS_YEAR " +
				" and PD.TRANS_ID=? " ;

		
		
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,ssc_roll.trim());
			stmt.setString(2,ssc_reg.trim());
			stmt.setString(3,ssc_board);
			stmt.setString(4,ssc_passing_year);
			stmt.setString(5,given_mobile_number.trim());
			stmt.setString(6,mname.trim());
			stmt.setString(7,trn_no.trim());
			r = stmt.executeQuery();
			
			
			while(r.next())
			{
				pinRetrivalDTO = new PinRetrivalDTO();
				
			//	pinRetrivalDTO.setApplicantName(r.getString("APPLICATION_ID"));
				pinRetrivalDTO.setApplicationID(r.getString("APPLICANT_ID"));
				pinRetrivalDTO.setPIN(r.getString("SCODE"));
				pinRetrivalDTO.setMobile(r.getString("CONTACT_NO"));

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
		
		return pinRetrivalDTO;
	}
	
	public PinRetrivalDTO getRetrivedPinByBoard(String ssc_roll,String ssc_reg,String ssc_board,String ssc_passing_year){
		
		PinRetrivalDTO pinRetrivalDTO = null;

		//System.out.println("ssc_roll:"+ssc_roll+"ssc_reg:"+ssc_reg+"ssc_board:"+ssc_board+"ssc_passing_year:"+ssc_passing_year+"given_mobile_number:"+given_mobile_number);
		
		Connection conn = ConnectionManager.getReadConnection();
		
		/*String sql ="SELECT distinct app.application_id APPLICATION_ID, app.PIN_NUMBER PIN\n" +
	              "  FROM APPLICATION_INFO app, APPLICATION_COLLEGES col,board_data_ssc board\n" +
	              "  WHERE  APP.SSC_ROLL_NO =?\n" +
	              "      AND  APP.SSC_REG =?\n" +
	              "      AND  APP.SSC_BOARD_ID =?\n" +
	              "      AND  APP.SSC_PASSING_YEAR =?\n" +
	              "      AND  app.application_id = col.application_id\n" +
	              "      AND  APP.application_id =board.application_id\n" +
	              "      AND  col.application_id =board.application_id\n" +
	              "  	 AND  APP.SSC_ROLL_NO =BOARD.ROLL_NO\n" +
	              "      AND  APP.SSC_REG =BOARD.REG_NO\n" +
	              "      AND  APP.SSC_BOARD_ID =BOARD.BOARD_ID\n" +
	              "      AND  APP.SSC_PASSING_YEAR =BOARD.PASSING_YEAR";*/

		String sql ="SELECT board.APPLICANT_ID,CONTACT_NO , board.scode PIN " +
				"  FROM APPLICANT_INFO board " +
				"  WHERE  board.ROLL_NO =? " +
				"      AND  board.REG_NO =? " +
				"      AND  board.BOARD_ID =? " +
				"      AND  board.PASS_YEAR =? "  ;
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,ssc_roll.trim());
			stmt.setString(2,ssc_reg.trim());
			stmt.setString(3,ssc_board);
			stmt.setString(4,ssc_passing_year);
			
			r = stmt.executeQuery();
			
			
			while(r.next())
			{
				pinRetrivalDTO = new PinRetrivalDTO();
				
				//pinRetrivalDTO.setApplicantName(r.getString("STUDENT_NAME"));
				pinRetrivalDTO.setApplicationID(r.getString("APPLICANT_ID"));
				pinRetrivalDTO.setPIN(r.getString("PIN"));
				pinRetrivalDTO.setMobile(r.getString("CONTACT_NO"));
				

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
		
		return pinRetrivalDTO;
	}
	
  public boolean sendSCode(String mobile, String scode, String roll)
  {
      try
      {           
          URL yahoo;
          
          
          //https://bmpws.robi.com.bd/ApacheGearWS/SendTextMessage?Username=buetiict&Password=Buet@iict99&From=8801847050021&To=01739884023&Message=Test message from Nanosoft

          yahoo = new URL("https://bmpws.robi.com.bd/ApacheGearWS/SendTextMessage?Username="+URLEncoder.encode("buetiict")+
        		  "&Password="+URLEncoder.encode("Buet@iict99")+
        		  "&From=8801847050021&To="+URLEncoder.encode("88"+mobile)+
        		  "&Message="+URLEncoder.encode("Your ("+roll+") online application has been submitted successfully. Your Security Code: "+scode+" - Board Authority.") );
          
          
          URLConnection yc = yahoo.openConnection();
          BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
          String inputLine;
          String inputLine1="";
          while ((inputLine = in.readLine()) != null)
          {
              //System.out.println(inputLine);
              if(inputLine!=null)
                  inputLine1+=inputLine;
          }
          in.close();
         // System.out.println("SMS=="+inputLine1);
//          if(inputLine1.startsWith("<br><br>Received"))
//              return true;
          if(inputLine1.contains("<ErrorCode>0"))
        	  return true;
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      return false;
  }
  
  
  
  public int sendMessMChange(String mobile, String mess)
  {
      try
      {           
          URL yahoo;
          
          
          //https://bmpws.robi.com.bd/ApacheGearWS/SendTextMessage?Username=buetiict&Password=Buet@iict99&From=8801847050021&To=01739884023&Message=Test message from Nanosoft

          yahoo = new URL("https://bmpws.robi.com.bd/ApacheGearWS/SendTextMessage?Username="+URLEncoder.encode("buetiict")+
        		  "&Password="+URLEncoder.encode("Buet@iict99")+
        		  "&From=8801847050021&To="+URLEncoder.encode("88"+mobile)+
        		  "&Message="+URLEncoder.encode(mess) );
          
          
          URLConnection yc = yahoo.openConnection();
          BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
          String inputLine;
          String inputLine1="";
         
          
          
          
          while ((inputLine = in.readLine()) != null)
          {
              //System.out.println(inputLine);
              if(inputLine!=null)
                  inputLine1+=inputLine;
          }
          in.close();
         // System.out.println("SMS=="+inputLine1);
//          if(inputLine1.startsWith("<br><br>Received"))
//              return true;
          
          
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
          DocumentBuilder builder;
          InputSource is;
          try {
              builder = factory.newDocumentBuilder();
              is = new InputSource(new StringReader(inputLine1));
              Document doc = builder.parse(is);
              NodeList list = doc.getElementsByTagName("ErrorCode");
              NodeList listC = doc.getElementsByTagName("CurrentCredit");
              System.out.println(list.item(0).getTextContent());
              System.out.println("CurrentCredit-->>"+listC.item(0).getTextContent());
              if(list.item(0).getTextContent().equalsIgnoreCase("0"))
            	  return 1;
          } catch (ParserConfigurationException e) {
          } catch (SAXException e) {
          } catch (IOException e) {
          }
          
          
          
          
          
          
          if(inputLine1.contains("<ErrorCode>0"))
        	  return 1;
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      return 0;
  }
  
  
  
  
  public boolean isValidSms(String mobile){

		int total=0;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql= " select count(*) cn from SECURITY_SMS where MOBILE_NO=? ";
		
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, mobile);			    
				r = stmt.executeQuery();
				if (r.next())
				{

					total=r.getInt("cn");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(total>0)
			return true;
		else
			return false;

	}
  
  
  
	
  static PreparedStatement stmtSMS = null;	
  static String sqlSMS = "insert into Security_sms (application_id,mobile_no,application,sendtime) values (?,?,'Y',sysdate)";
  
  public static synchronized int AppSMSUpdate(String app_id,String mobile_no)
  {
		int tmp = 0;
		try {
			if(stmtSMS==null)
            	stmtSMS = ConnectionManager.getConnectionStatic().prepareStatement(sqlSMS);
            int parameterIndex = 1;
            stmtSMS.setString(parameterIndex++, app_id);
            stmtSMS.setString(parameterIndex++, mobile_no);

            tmp = stmtSMS.executeUpdate();
            stmtSMS.clearParameters();
			
			
		} catch (SQLException e) {
            System.out.println(e.toString());           
            stmtSMS = null;
            ConnectionManager.closeStatic();
        }	
				
		return tmp;
  }
	
  
  /////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////
	
  
  public MobChangeDTO getMCInfo(String ssc_roll,String ssc_reg,String ssc_board,String ssc_passing_year, String mother_name){
		
	  MobChangeDTO mcDTO = null;

		
		Connection conn = ConnectionManager.getReadConnection();
		
	
		/*
		String sql ="select APPLICANT_ID,HAS_WEB_TRNS,APPLIED_WEB,CONTACT_NO,SCODE,APPLIED_SMS, " +
				" (select  BOARD_NAME  from mst_edu_board where BOARD_ID=? ) BOARD_NAME " +
				" from APPLICANT_INFO   " +
				" where ROLL_NO=?  " +
				//" and REG_NO=?  " +
				" and BOARD_ID=?   " +
				" and PASS_YEAR=?  "  ;*/

		
		String sql= "select APPLICANT_ID,HAS_WEB_TRNS,APPLIED_WEB,CONTACT_NO,SCODE,APPLIED_SMS, BOARD_NAME " +
				" from APPLICANT_INFO ai, board_data_ssc ss " +
				" where AI.ROLL_NO=SS.ROLL_NO " +
				" and AI.BOARD_ID=SS.BOARD_ID " +
				" and AI.PASS_YEAR=SS.PASS_YEAR  " +
				" and  AI.ROLL_NO=?   " +
				" and  SS.REG_NO=?  " +
				" and  AI.BOARD_ID=?    " +
				" and  AI.PASS_YEAR=?   " +
				" and upper(trim(SS.MOTHER_NAME))=? " ;

		
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);			
			stmt.setString(1,ssc_roll.trim());
			stmt.setString(2,ssc_reg.trim());
			stmt.setString(3,ssc_board);
			stmt.setString(4,ssc_passing_year);
			stmt.setString(5,mother_name.toUpperCase());
			r = stmt.executeQuery();
			
			
			while(r.next())
			{
				mcDTO = new MobChangeDTO();				
			
				mcDTO.setApplicationID(r.getString("APPLICANT_ID"));
				mcDTO.setSCode(r.getString("SCODE"));
				mcDTO.setMobile(r.getString("CONTACT_NO"));
				mcDTO.setTTNumber(r.getString("HAS_WEB_TRNS"));
				mcDTO.setAppWeb(r.getString("APPLIED_WEB"));
				mcDTO.setAppSms(r.getString("APPLIED_SMS"));
				mcDTO.setBoardName(r.getString("BOARD_NAME"));
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
		
		return mcDTO;
	}
  
  
  static PreparedStatement stmtMob = null;	
  static String sqlMob = "select count(*) cnt  from APPLICATION_COLLEGES  where APPLICANT_ID=? and APP_TYPE='SMS'";
  
  public static synchronized int GetSmsCount(String app_id)
  {
		int tmp = 0;
		ResultSet r = null;
		try {
			if(stmtMob==null)
            	stmtMob = ConnectionManager.getConnectionStatic().prepareStatement(sqlMob);
            int parameterIndex = 1;
            stmtMob.setString(parameterIndex++, app_id);
            r = stmtMob.executeQuery();
            while(r.next())
            	tmp=r.getInt("cnt");
            stmtMob.clearParameters();
		} catch (SQLException e) {
            System.out.println(e.toString());           
            stmtMob = null;
            ConnectionManager.closeStatic();
        }	
		return tmp;
  }
  static PreparedStatement stmtCheckMob = null;	
  static String sqlCheckMob = "select count(*) cnt  from APPLICANT_INFO  where contact_no=?";
  
  public static synchronized int checMobile(String mobile)
  {
		int tmp = 0;
		ResultSet r = null;
		try {
			if(stmtCheckMob==null)
				stmtCheckMob = ConnectionManager.getConnectionStatic().prepareStatement(sqlCheckMob);
            int parameterIndex = 1;
            stmtCheckMob.setString(parameterIndex++, mobile);
            r = stmtCheckMob.executeQuery();
            while(r.next())
            	tmp=r.getInt("cnt");
            stmtCheckMob.clearParameters();
		} catch (SQLException e) {
            System.out.println(e.toString());           
            stmtCheckMob = null;
            ConnectionManager.closeStatic();
        }	
		return tmp;
  }
  
  static PreparedStatement stmtCheckMob1 = null;	
  static String sqlCheckMob1 = "select roll_no,REG_NO  from APPLICANT_INFO  where contact_no=?";

  public static synchronized int checMobile(String mobile, String ssc_roll, String ssc_reg)
  {
		int tmp = 2;
		String tmp_roll = "";
		String tmp_reg = "";
		ResultSet r = null;
		try {
			if(stmtCheckMob1==null)
				stmtCheckMob1 = ConnectionManager.getConnectionStatic().prepareStatement(sqlCheckMob1);
            int parameterIndex = 1;
            stmtCheckMob1.setString(parameterIndex++, mobile);
            r = stmtCheckMob1.executeQuery();
            if(r.next())
            {
            	tmp_roll = r.getString("roll_no");
            	tmp_reg = r.getString("reg_no");
            	if(tmp_roll.equalsIgnoreCase(ssc_roll) && tmp_reg.equalsIgnoreCase(ssc_reg))
            		tmp = 0;
            	else
            		tmp = 1;
            }
            stmtCheckMob1.clearParameters();
		} catch (SQLException e) {
            System.out.println(e.toString());           
            stmtCheckMob1 = null;
            ConnectionManager.closeStatic();
        }	
		return tmp;
  }
  
  
  static PreparedStatement stmtGetAppId = null;	
  static String sqlGetAppId = "select applicant_id cnt  from applicant_info  where roll_no=? and reg_no=?";

  public static synchronized String getAppId(String ssc_roll, String ssc_reg)
  {
		String tmp = "";
		ResultSet r = null;
		try {
			if(stmtGetAppId==null)
				stmtGetAppId = ConnectionManager.getConnectionStatic().prepareStatement(sqlGetAppId);
            int parameterIndex = 1;
            stmtGetAppId.setString(parameterIndex++, ssc_roll);
            stmtGetAppId.setString(parameterIndex++, ssc_reg);
            r = stmtGetAppId.executeQuery();
            while(r.next())
            	tmp=r.getString("cnt");
            stmtGetAppId.clearParameters();
		} catch (SQLException e) {
            System.out.println(e.toString());           
            stmtGetAppId = null;
            ConnectionManager.closeStatic();
        }	
		return tmp;
  }
  
  
  public boolean isValidMobUpdate(String app_id){

		int total=0;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql= " select count(*) cn from APP_MOB_CHANGE where APPLICATION_ID=? ";
		
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, app_id);			    
				r = stmt.executeQuery();
				if (r.next())
				{

					total=r.getInt("cn");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(total>0)
			return true;
		else
			return false;

	}
  
  
  
  public boolean isValidMobDate(String roll, String board, String year){

		int total=0;
		Connection conn = ConnectionManager.getWriteConnection();
		//String sql= "select floor(sysdate - to_date(PAYTIME,'YYYY-MM-DD HH24:MI:SS')) cn from APPLICANT_PAYMENT where app_id=? ";
		
				String	sql="select floor(sysdate - pay) cn  from(  " +
						" select max(to_date(PAY_TIME,'YYYY-MM-DD HH24:MI:SS')) pay  " +
						" from PAYMENT_DETAILS where ROLL_NO=? and BOARD_ID=? and PASS_YEAR=?)  "  ;

		
		
		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, roll);
			    stmt.setString(2, board);
			    stmt.setString(3, year);
				r = stmt.executeQuery();
				if (r.next())
				{

					total=r.getInt("cn");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(total>0)
			return false;
		else
			return true;

	}
  
  
  public int updateAppMobile(String application_id,String new_mob, String old_mob)
	{
		int tmp=0;
		Connection conn = ConnectionManager.getWriteConnection();
		OracleCallableStatement stmt = null;
		try {
			stmt = (OracleCallableStatement) conn.prepareCall("{ call update_mobile(?,?,?,?,?)  }");
			stmt.setString(1, application_id);
			stmt.setString(2, new_mob);
			stmt.setString(3, old_mob);
			stmt.registerOutParameter(4, java.sql.Types.INTEGER); 
			stmt.registerOutParameter(5, java.sql.Types.VARCHAR); 
					
			stmt.executeUpdate();
			tmp = stmt.getInt(4);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 	
		return tmp;
	}
	
  public int insertAllAppMobile(String roll,String reg, String board, String passyear, String mobile, String status)
 	{
 		int tmp=0;
 		Connection conn = ConnectionManager.getWriteConnection();
 		OracleCallableStatement stmt = null;
 		try {
 			stmt = (OracleCallableStatement) conn.prepareCall("{ call insert_All_mobile(?,?,?,?,?,?,?,?)  }");
 			stmt.setString(1, roll);
 			stmt.setString(2, reg);
 			stmt.setString(3, board);
 			stmt.setString(4, passyear);
 			stmt.setString(5, mobile);
 			stmt.setString(6, status);			
 			
 			stmt.registerOutParameter(7, java.sql.Types.INTEGER); 
 			stmt.registerOutParameter(8, java.sql.Types.VARCHAR); 
 					
 			stmt.executeUpdate();
 			tmp = stmt.getInt(7);
 		}
 		catch (Exception e){
 			e.printStackTrace();
 		}
 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
 		{e.printStackTrace();}stmt = null;conn = null;} 	
 		return tmp;
 	}
  
  
  
    static PreparedStatement stmtASUpdate = null;
	static String sqlASUpdate = "update APPLICANT_MESS set status='Y', SENDTIME=sysdate where APPLICANT_ID=? and MOBILE=?";
	
	public static synchronized int sentAppSMS(String app_id,String mob)
  {
		int tmp = 0;
         try {
	            
	            if(stmtASUpdate==null)
	            	stmtASUpdate = ConnectionManager.getConnection().prepareStatement(sqlASUpdate);
	            int parameterIndex = 1;	            
	            stmtASUpdate.setString(parameterIndex++, app_id);
	            stmtASUpdate.setString(parameterIndex++, mob);

	            tmp = stmtASUpdate.executeUpdate();
	            stmtASUpdate.clearParameters();
	            

	          } catch (SQLException e) {
	            System.out.println(e.toString());
	            stmtASUpdate = null;
	            ConnectionManager.closeStatic();
	        }	
	          return tmp;
  }
	
  
  
	
	 static PreparedStatement stmtASUpdateb = null;
		static String sqlASUpdateb = "update APPLICANT_MESS set status='Y', SENDTIME=sysdate where STUDENT_ID=? and MOBILE=?";
		
		public static synchronized int sentAppSMSb(String app_id,String mob)
	  {
			int tmp = 0;
	         try {
		            
		            if(stmtASUpdateb==null)
		            	stmtASUpdateb = ConnectionManager.getConnection().prepareStatement(sqlASUpdate);
		            int parameterIndex = 1;	            
		            stmtASUpdateb.setString(parameterIndex++, app_id);
		            stmtASUpdateb.setString(parameterIndex++, mob);

		            tmp = stmtASUpdateb.executeUpdate();
		            stmtASUpdateb.clearParameters();
		            

		          } catch (SQLException e) {
		            System.out.println(e.toString());
		            stmtASUpdateb = null;
		            ConnectionManager.closeStatic();
		        }	
		          return tmp;
	  }
	
	
	
	
	
  
	
}
