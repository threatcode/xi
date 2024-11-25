package edu.dao.interBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.jdbc.OracleCallableStatement;
import edu.dto.UserDTO;
import edu.dto.application.ResponseDTO;
import edu.utils.connection.ConnectionManager;

public class ApplicantReleaseDAO {

	
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
	
}
