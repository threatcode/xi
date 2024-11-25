package edu.dao.board;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;


import oracle.jdbc.OracleCallableStatement;
import edu.dao.application.ApplicationDAO;
import edu.dto.UserDTO;
import edu.dto.application.ResponseDTO;
import edu.utils.connection.ConnectionManager;
import edu.dao.applicant.*;

public class ApplicantReleaseDAO {

	public ResponseDTO delete_TT(String ssc_roll, String ssc_board, String ssc_year, String ssc_reg,String mobilenumber, String userid)
	{
		ResponseDTO response =new ResponseDTO();
		
		if(this.checkWebPayment(ssc_roll, ssc_board, ssc_year, ssc_reg, mobilenumber.trim().substring(mobilenumber.trim().length()-11))==1)
		{
			
//			if(sendOapplication(ssc_roll, ssc_reg, ssc_board, ssc_year, mobilenumber, "CAN").equalsIgnoreCase("no"))
//			{
//				response.setStatus("ERROR");
//				response.setMessage("Mobile number already used in another cancel application");
//				return response;
//			}
		
			Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
			OracleCallableStatement stmt = null;
			
			try {
				stmt = (OracleCallableStatement) conn.prepareCall("{ call DeleteApplication_TT(?,?,?,?,?,?,?,?)}");
	            
				stmt.setString(1, ssc_roll.trim());
				stmt.setString(2, ssc_board.trim());
				stmt.setString(3, ssc_year.trim());
				stmt.setString(4, ssc_reg.trim());
				stmt.setString(5, mobilenumber.trim());
				
	            stmt.registerOutParameter(6, java.sql.Types.INTEGER); 
	            stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
	            stmt.setString(8, userid);
	            stmt.executeUpdate();
	            
	            response.setStatus(stmt.getString(6));
	            response.setMessage(stmt.getString(7));
	            
	            if(stmt.getInt(6)==1){
	            	response.setStatus("OK");
	            }
	            else{
	            	response.setStatus("ERROR");
	            	response.setMessage(stmt.getString(7));
	            }
	         } 
			catch (Exception e){e.printStackTrace();
				response.setStatus("ERROR");
				response.setMessage(e.getMessage());
			}
			finally{try{stmt.close();
			ConnectionManager.closeConnection(conn);
			} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;} 
		}
		else
		{
			response.setStatus("ERROR");
			response.setMessage("Mobile number already used!!!");
		}
		
        return response;
	}
	
	
	public ResponseDTO deletePayment(String ssc_roll, String ssc_board, String ssc_year, String ssc_reg)
	{
		ResponseDTO response =new ResponseDTO();
		String appid = RetrivedPinDAO.getAppId(ssc_roll.trim(),ssc_reg.trim());
		if(appid.equalsIgnoreCase(""))
		{
			response.setStatus("ERROR");
        	response.setMessage("Please supply correct roll and reg");
        	return response;
		}
		
		
		Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		
		try {
			stmt = (OracleCallableStatement) conn.prepareCall("{ call DeleteAppPayment(?,?,?,?,?,?,?,?)}");
            
			stmt.setString(1, ssc_roll.trim());
			stmt.setString(2, ssc_board.trim());
			stmt.setString(3, ssc_year.trim());
			stmt.setString(4, ssc_reg.trim());
			
			
            stmt.registerOutParameter(5, java.sql.Types.INTEGER); 
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(7, java.sql.Types.VARCHAR); 
            stmt.registerOutParameter(8, java.sql.Types.VARCHAR); 
            stmt.executeUpdate();
            
/*            response.setStatus(stmt.getString(5));
            response.setMessage(stmt.getString(6));*/
            
            if(stmt.getInt(5)==1){
            	response.setStatus("OK");
            	appid = stmt.getString(7);
            	//response.setStatus(appid);
//            	sendOapplication(appid,getBoardName(ssc_board.trim()),ssc_roll.trim(),ssc_year.trim(),ssc_reg.trim(),mobilenumber.trim(),"CANCEL");
            	response.setMobile(stmt.getString(8));
            	response.setMessage(stmt.getString(6));

            }
            else{
            	response.setStatus("ERROR");
            	response.setMessage(stmt.getString(6));
            }
         } 
		catch (Exception e){e.printStackTrace();
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		finally{try{stmt.close();
		ConnectionManager.closeConnection(conn);
		} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 
		
		
		
		
		
		
		
		
		
		  return response;
	}
	
	
	
	public ResponseDTO updateMobile_TT(String ssc_roll, String ssc_board, String ssc_year, String ssc_reg,String mobilenumber,String scode, String userid)
	{
		ResponseDTO response =new ResponseDTO();
		if(this.checkWebPayment(ssc_roll, ssc_board, ssc_year, ssc_reg, mobilenumber.trim().substring(mobilenumber.trim().length()-11))==1)
		{
			
//			if(sendOapplication(ssc_roll, ssc_reg, ssc_board, ssc_year, mobilenumber, "CHA").equalsIgnoreCase("no"))
//			{
//				response.setStatus("ERROR");
//				response.setMessage("Mobile number already used in another change application");
//				return response;
//			}
			Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
			OracleCallableStatement stmt = null;
			
			try {
				stmt = (OracleCallableStatement) conn.prepareCall("{ call UPDATEMOBILE_TT(?,?,?,?,?,?,?,?,?)}");
	            
				stmt.setString(1, ssc_roll.trim());
				stmt.setString(2, ssc_board.trim());
				stmt.setString(3, ssc_year.trim());
				stmt.setString(4, ssc_reg.trim());
				stmt.setString(5, mobilenumber.trim());
				
	            stmt.registerOutParameter(6, java.sql.Types.INTEGER); 
	            stmt.registerOutParameter(7, java.sql.Types.VARCHAR); 
	            stmt.setString(8, scode);
	            stmt.setString(9, userid);
	            stmt.executeUpdate();
	            
	            response.setStatus(stmt.getString(6));
	            response.setMessage(stmt.getString(7));
	            
	           if(response.getStatus()=="1"){
	        	   response.setStatus("OK");
	        	   response.setMessage(stmt.getString(7));
	            }
	            else{
	            	response.setStatus("ERROR");
	            	response.setMessage(stmt.getString(7));
	            }
	         } 
			catch (Exception e){e.printStackTrace();
				response.setStatus("ERROR");
				response.setMessage(e.getMessage());
			}
			finally{try{stmt.close();
			ConnectionManager.closeConnection(conn);
			} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;} 		
		}
		else
		{
			response.setStatus("ERROR");
			response.setMessage("Applicant not found!!!");
		}
		
        return response;
        

	}
	private String sendOapplication(String roll, String reg, String board, String year, 
			String contactno, String op)
	{

		String tmp="no";
		try{
			String generatedJSONString = "{\"roll\" :\""+roll+"\",\"reg\" :\""+reg+"\",\"board\" :\""+getBoardName(board)+"\",\"year\" :\""+year
				+"\",\"contactno\" :\""+contactno+"\",\"op\" :\""+op+"\"}";
			String myUrlgoeshere = "http://103.230.104.217:9999/CAD/buetupTT.php";
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(myUrlgoeshere);
			StringEntity input = new StringEntity(generatedJSONString);
			input.setContentType("application/json;charset=UTF-8");
			postRequest.setEntity(input);
			input.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
			postRequest.setHeader("Accept", "application/json");
			postRequest.setEntity(input); 
			HttpResponse response = httpClient.execute(postRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output;
			String responseString = "";
			while ((output = br.readLine()) != null)
			{
				responseString=output;
			}
			httpClient.getConnectionManager().shutdown();
			JSONObject myResponse = new JSONObject(responseString.toString());
			System.out.println(myResponse.getString("status").toLowerCase());
			if(myResponse.getString("status").toLowerCase().contains(("yes")))
			{
				tmp = "yes";
			}
			if(myResponse.getString("status").toLowerCase().contains(("no data found.")))
			{
				tmp = "yes";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		tmp = "yes";
		return tmp;
//		String targetURL = "http://114.130.64.35:9999/CAD/buet2TTOapplication.php";
//		URL url;
//	    HttpURLConnection connection = null;  
//	    
//	    ApplicantInfoDAO aidao = new ApplicantInfoDAO();
//	    String tmp="no";
//		try
//		{
//			StringBuilder urlParameter = new StringBuilder();
//			urlParameter.append("app_id=").append(URLEncoder.encode(app_id, "UTF-8"));
//			urlParameter.append("&ssc_board=").append(URLEncoder.encode(ssc_board, "UTF-8"));
//			urlParameter.append("&ssc_roll=").append(URLEncoder.encode(ssc_roll, "UTF-8"));
//			urlParameter.append("&ssc_year=").append(URLEncoder.encode(ssc_year, "UTF-8"));
//			urlParameter.append("&ssc_regno=").append(URLEncoder.encode(ssc_regno, "UTF-8"));
//			urlParameter.append("&contact_no=").append(URLEncoder.encode(contact_no, "UTF-8"));
//			urlParameter.append("&op=").append(URLEncoder.encode(op, "UTF-8"));
//			
//			
//			System.out.println(urlParameter.toString());
//		      url = new URL(targetURL);
//		      connection = (HttpURLConnection)url.openConnection();
//		      connection.setRequestMethod("POST");
//		      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//		      connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameter.toString().getBytes().length));
//		      connection.setRequestProperty("Content-Language", "en-US");  
//					
//		      connection.setUseCaches (false);
//		      connection.setDoInput(true);
//		      connection.setDoOutput(true);
//
//		      //Send request
//		      DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
//		      wr.writeBytes (urlParameter.toString());
//		      wr.flush ();
//		      wr.close ();
//
//		      //Get Response	
//		      InputStream is = connection.getInputStream();
//		      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//		      String line;
//		      StringBuffer response = new StringBuffer(); 
//		      System.out.println("####################### TT Response ##############################");
//		      while((line = rd.readLine()) != null) {
//		        response.append(line);
//		        response.append('\r');
//		      }
//		      if(response.toString().startsWith("<reply>SUCCESS.</reply>"))
//		      {
//		    	  tmp = "yes";
//		    	  System.out.println("Mobile number changed :"+app_id +":"+contact_no);
//	    	  }
//		      if(response.toString().toLowerCase().contains("cancelled"))
//		      {
//		    	  tmp = "yes";
//		    	  System.out.println("Mobile number cancelled :"+app_id +":"+contact_no);
//	    	  }
//		      if(response.toString().contains(ssc_roll))
//		      {
//		    	  tmp = "yes";
//		      }
//
//		      
//		      System.out.println(response.toString());
//		      System.out.println("IICT send "+urlParameter.toString());
//		      System.out.println("####################### TT Response ##############################");
//		      rd.close();
//		    } catch (Exception e) {
//		      e.printStackTrace();
//		      return tmp;
//
//		    } finally {
//		      if(connection != null) {
//		        connection.disconnect(); 
//		      }
//		    }
//		  
//		return tmp;
	}
	
	public ResponseDTO cleanData(String uploadId)
	{
		ResponseDTO response =new ResponseDTO();
		Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		
		try {
            stmt = (OracleCallableStatement) conn.prepareCall("{ call RELEASEAPPLICANT(?,?,?)  }");
            
            stmt.setString(1, uploadId);
            stmt.registerOutParameter(2, java.sql.Types.VARCHAR); 
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR); 
            stmt.executeUpdate();
            
            response.setStatus(stmt.getString(2));
            response.setMessage(stmt.getString(3));
         } 
		catch (Exception e){e.printStackTrace();
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		finally{try{stmt.close();
		ConnectionManager.closeConnection(conn);
		} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
        
        return response;
        

	}
	
	
	
	public ArrayList<UserDTO> getApplicantListForSMS(String uploadId){
		
		UserDTO user = null;
		ArrayList<UserDTO> userList = null;
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "Select USER_ID,USER_PASSWORD,mobile_no from mst_user,xls_upload Where " +
		"mst_user.USER_ID=xls_upload.APPLICANT_ID " +
		"AND SMS_SEND='N' AND STATUS='C' AND UPLOAD_ID=? ";
		

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uploadId);
			r = stmt.executeQuery();
			
			userList = new ArrayList<UserDTO>();
			while(r.next())
			{
				user = new UserDTO();
				user.setUserid(r.getString("USER_ID"));
				user.setPassword(r.getString("USER_PASSWORD"));
				user.setMobile(r.getString("MOBILE_NO"));
				userList.add(user);

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
		
		return userList;
	}
	
	public void updateApplicantListForSMS(String[] applicationId){
		
		Connection conn = ConnectionManager.getWriteConnection();
		String sql = "Update xls_upload Set SMS_SEND='Y' where applicant_id=?";
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			for(int i=0; i < applicationId.length; i++){
				stmt.setString(1, applicationId[i]);
				r = stmt.executeQuery();
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
		
	}
	public String getBoardName(String id)
	{
		if(id.equalsIgnoreCase("10"))
			return "DHAKA";
		else if(id.equalsIgnoreCase("11"))
			return "COMILLA";
		else if(id.equalsIgnoreCase("12"))
			return "RAJSHAHI";
		else if(id.equalsIgnoreCase("13"))
			return "JESSORE";
		else if(id.equalsIgnoreCase("14"))
			return "CHITTAGONG";
		else if(id.equalsIgnoreCase("15"))
			return "BARISAL";
		else if(id.equalsIgnoreCase("16"))
			return "SYLHET";
		else if(id.equalsIgnoreCase("18"))
			return "MADRASAH";
		else if(id.equalsIgnoreCase("17"))
			return "DINAJPUR";
		else if(id.equalsIgnoreCase("19"))
			return "TEC";
		else if(id.equalsIgnoreCase("20"))
			return "BOU";
		return "";
	}
	
	
	static PreparedStatement stmtBoardData = null;
	static ResultSet rsBoardData = null;
	static String sqlBoardData = "select STUDENT_NAME studentname from BOARD_DATA_SSC where ROLL_NO=? and BOARD_ID=? and PASS_YEAR=? and REG_NO=?";

	static PreparedStatement stmtPaymentData = null;
	static ResultSet rsPaymentData = null;
//	static String sqlPaymentData = "select roll_no,board_id,pass_year,CONTACT_NO from PAYMENT_DETAILS where (ROLL_NO=? and BOARD_ID=? and PASS_YEAR=?" +
//			" and APP_TYPE='WEB') or CONTACT_NO=?";
	
//	static String sqlPaymentData = "select roll_no,board_id,pass_year,CONTACT_NO from APPLICANT_INFO where (ROLL_NO=? and BOARD_ID=? and PASS_YEAR=?" +
//	" and HAS_WEB_TRNS='Y') or CONTACT_NO=?";

	static String sqlPaymentData = "select count(*) cnt from APPLICANT_INFO where " +
			" CONTACT_NO=?";

	
	public static synchronized int checkWebPayment(String roll, String board, String year, String reg, String contactno)
    {
		//	0 = no ssc
		//	1 = no payment
		//	2 = has payment
		//	3 = duplicate contactno
		//	4 = different contactno
		
		int tmp = 0;
		try {
			if(stmtBoardData==null)
				stmtBoardData = ConnectionManager.getConnectionStatic().prepareStatement(sqlBoardData);
			int parameterIndex = 1;
			stmtBoardData.setString(parameterIndex++, roll);
			stmtBoardData.setInt(parameterIndex++, Integer.parseInt(board));
			stmtBoardData.setInt(parameterIndex++, Integer.parseInt(year));	 
			stmtBoardData.setString(parameterIndex++, reg);          
			rsBoardData = stmtBoardData.executeQuery();
			stmtBoardData.clearParameters();
			if(rsBoardData.next())
			{
				
			}
			else
				return tmp;
	            	            

			if(stmtPaymentData==null)
				stmtPaymentData = ConnectionManager.getConnectionStatic().prepareStatement(sqlPaymentData,ResultSet.TYPE_SCROLL_INSENSITIVE,
					    ResultSet.CONCUR_READ_ONLY);
			parameterIndex = 1;
			stmtPaymentData.setString(parameterIndex++, contactno);
			rsPaymentData = stmtPaymentData.executeQuery();
			stmtPaymentData.clearParameters();
			
			if(rsBoardData.next())
			{
				tmp=3;
			}
			else
			{
				tmp=1;
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			stmtBoardData = null;
			stmtPaymentData = null;
			ConnectionManager.closeStatic();
		}	
		return tmp;
    }
}
