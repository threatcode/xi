package edu.dao.applicant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.dto.IpAddressDTO;
import edu.dto.applicant.ResultDTO;
import edu.utils.connection.ConnectionManager;

public class ResultDAO {

public ResultDTO getResult(String application_id){
		
		ResultDTO result =new ResultDTO();
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql =" Select MST_COLLEGE.COLLEGE_NAME,MST_COLLEGE.EIIN, SHIFT_NAME,VERSION_NAME,GROUP_NAME, AUTO_MIGRATION," +
		" DECODE(MERIT_TYPE,1,'First Run',2,'Second Run',3,'3rd Run',4,'4th Run',5,'5th Run') MERIT_TYPE, PRIORITY_ORDER,status,"+
		" DECODE(QUOTA_TYPE,'N','','OWN','(Same School and College)','DQ','(Divisional Quota)','ZQ','(Zilla Quota)','FFQ','(Freedom Fighter Quota)','EQ','(Edu. Quota)','SQ','(Special Quota)','FRQ','()','General') QUOTA_TYPE " +
		" from BOARD_RESULT,MST_COLLEGE,MST_SHIFT,MST_VERSION,MST_GROUP " +
		" WHERE BOARD_RESULT.EIIN=MST_COLLEGE.EIIN " +
		" AND BOARD_RESULT.SHIFT_ID=MST_SHIFT.SHIFT_ID " +
		" AND BOARD_RESULT.VERSION_ID=MST_VERSION.VERSION_ID " +
		" AND BOARD_RESULT.GROUP_ID=MST_GROUP.GROUP_ID " +
		" AND Application_Id=?  and MERIT_TYPE IN (SELECT MAX(MERIT_TYPE) FROM BOARD_RESULT WHERE Application_Id=?)";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,application_id);
			stmt.setString(2,application_id);
			
			r = stmt.executeQuery();
			
			if(r.next())
			{
				
				result.setCollege_name(r.getString("COLLEGE_NAME"));
				result.setEiin(r.getString("EIIN"));
				result.setShift_name(r.getString("SHIFT_NAME"));
				result.setVersion_name(r.getString("VERSION_NAME"));
				result.setGroup_name(r.getString("GROUP_NAME"));
				result.setQuota_type(r.getString("QUOTA_TYPE"));
				result.setMerit_type(r.getString("MERIT_TYPE"));
				result.setTmp_status("OK");	
				result.setPriority_order(r.getString("PRIORITY_ORDER"));
				result.setAuto_migration(r.getString("AUTO_MIGRATION"));
				result.setApprovedstatus(r.getString("STATUS"));
				
			}
			else
				result.setTmp_status("KO");

		} 
		catch (Exception e){e.printStackTrace();result.setTmp_status("DB_CONNECTION_ISSUE");}
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
		
		return result;
	}

public ResultDTO getResult(String roll, String board, String year){
	
	ResultDTO result =new ResultDTO();
	Connection conn = ConnectionManager.getReadConnection();
	
	String sql ="Select rsult.application_id, MST_COLLEGE.COLLEGE_NAME,SHIFT_NAME,VERSION_NAME,GROUP_NAME, AUTO_MIGRATION,\n" +
	    " DECODE(MERIT_TYPE,1,'First Merit List',MERIT_TYPE) MERIT_TYPE, PRIORITY_ORDER,\n" +
	    " DECODE(QUOTA_TYPE,'N','','OWN','(Same School & College)','DQ','(Divisional Quota)','ZQ','(Zilla Quota)','FQ','(Freedom Fighter Quota)','EQ','(Edu. Quota)','SQ','(Special Quota)',QUOTA_TYPE) QUOTA_TYPE \n" +
	    " from BOARD_RESULT rsult,MST_COLLEGE,MST_SHIFT,MST_VERSION,MST_GROUP, APPLICATION_INFO app\n" +
	    "WHERE rsult.EIIN=MST_COLLEGE.EIIN \n" +
	    "AND rsult.SHIFT_ID=MST_SHIFT.SHIFT_ID \n" +
	    "AND rsult.VERSION_ID=MST_VERSION.VERSION_ID \n" +
	    "AND rsult.GROUP_ID=MST_GROUP.GROUP_ID\n" +
	    "AND rsult.application_id = app.application_id\n" +
//	    "AND MERIT_TYPE IN (SELECT MAX(MERIT_TYPE) FROM BOARD_RESULT WHERE Application_Id=app.application_id)\n" +
	    "and app.SSC_ROLL_NO = ?\n" +
	    "and app.SSC_BOARD_ID = ?\n" +
	    "and app.SSC_PASSING_YEAR = ?";
	
	
	PreparedStatement stmt = null;
	ResultSet r = null;
	try
	{
		stmt = conn.prepareStatement(sql);
		
		stmt.setString(1,roll);
		stmt.setString(2,board);
		stmt.setString(3,year);
		
		r = stmt.executeQuery();
		
		if(r.next())
		{
			
			result.setApplication_id(r.getString("APPLICATION_ID"));
			result.setCollege_name(r.getString("COLLEGE_NAME"));
			result.setShift_name(r.getString("SHIFT_NAME"));
			result.setVersion_name(r.getString("VERSION_NAME"));
			result.setGroup_name(r.getString("GROUP_NAME"));
			result.setQuota_type(r.getString("QUOTA_TYPE"));
			result.setMerit_type(r.getString("MERIT_TYPE"));
			result.setTmp_status("OK");	
			result.setPriority_order(r.getString("PRIORITY_ORDER"));
			result.setAuto_migration(r.getString("AUTO_MIGRATION"));
			
		}
		else
			result.setTmp_status("KO");

	} 
	catch (Exception e){e.printStackTrace();result.setTmp_status("DB_CONNECTION_ISSUE");}
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
	
	return result;
	}

	static Connection connection = null;
	static PreparedStatement stmt = null;
	static String sql ="Select rsult.application_id, MST_COLLEGE.COLLEGE_NAME,SHIFT_NAME,VERSION_NAME,GROUP_NAME, AUTO_MIGRATION,\n" +
    " DECODE(MERIT_TYPE,1,'First Merit List',MERIT_TYPE) MERIT_TYPE, PRIORITY_ORDER,\n" +
    " DECODE(QUOTA_TYPE,'N','','OWN','(Same School & College)','DQ','(Divisional Quota)','ZQ','(Zilla Quota)','FQ','(Freedom Fighter Quota)','EQ','(Edu. Quota)','SQ','(Special Quota)',QUOTA_TYPE) QUOTA_TYPE \n" +
    " from BOARD_RESULT rsult,MST_COLLEGE,MST_SHIFT,MST_VERSION,MST_GROUP, APPLICATION_INFO app\n" +
    "WHERE rsult.EIIN=MST_COLLEGE.EIIN \n" +
    "AND rsult.SHIFT_ID=MST_SHIFT.SHIFT_ID \n" +
    "AND rsult.VERSION_ID=MST_VERSION.VERSION_ID \n" +
    "AND rsult.GROUP_ID=MST_GROUP.GROUP_ID\n" +
    "AND rsult.application_id = app.application_id\n" +
//    "AND MERIT_TYPE IN (SELECT MAX(MERIT_TYPE) FROM BOARD_RESULT WHERE Application_Id=app.application_id)\n" +
    "and app.SSC_ROLL_NO = ?\n" +
    "and app.SSC_BOARD_ID = ?\n" +
    "and app.SSC_PASSING_YEAR = ?";

public static synchronized ResultDTO getStaticResult(String roll, String board, String year){
	
	ResultDTO result =new ResultDTO();
	ResultSet r = null;
	try
	{
        if(connection==null || connection.isClosed())
            connection = ConnectionManager.getConnectionStatic();
        if(stmt==null)
            stmt = connection.prepareStatement(sql);
		
		stmt.setString(1,roll);
		stmt.setString(2,board);
		stmt.setString(3,year);
		
		r = stmt.executeQuery();
		
		if(r.next())
		{
			
			result.setApplication_id(r.getString("APPLICATION_ID"));
			result.setCollege_name(r.getString("COLLEGE_NAME"));
			result.setShift_name(r.getString("SHIFT_NAME"));
			result.setVersion_name(r.getString("VERSION_NAME"));
			result.setGroup_name(r.getString("GROUP_NAME"));
			result.setQuota_type(r.getString("QUOTA_TYPE"));
			result.setMerit_type(r.getString("MERIT_TYPE"));
			result.setTmp_status("OK");	
			result.setPriority_order(r.getString("PRIORITY_ORDER"));
			result.setAuto_migration(r.getString("AUTO_MIGRATION"));
			
		}
		else
			result.setTmp_status("KO");

	} 
	catch (Exception e){
		
	}
	finally{
		try{
			stmt.clearParameters();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		try{
			r.close();
		} 
		catch (Exception e){
				e.printStackTrace();
		}
		r = null;
	}
	
	return result;
}
	
	public Boolean updateAutoMigration(String IapplicationId, String Istatus, IpAddressDTO ipAdress, String Iotp){
		int a = 0, b = 0, total=0;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql= "update board_result set AUTO_MIGRATION = ? where APPLICATION_ID = ?";
		String sql1= "INSERT INTO BOARD_ADMISSION_LOG (APPLICATION_ID, XFORWARD, VIA," +
				"   REMOTE_ADDRESS, ACTION, USER_EIIN) VALUES (?,?,?,?,?,?)";
		String sql2 = "Update OTP set USED_YN='Y' Where application_id=? and secure_code=?";
		PreparedStatement stmt = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, Istatus);
			    stmt.setString(2, IapplicationId);
			    a = stmt.executeUpdate();
			    
			    stmt = conn.prepareStatement(sql1);
			    stmt.setString(1, IapplicationId);
			    stmt.setString(2, ipAdress.getxForward());
			    stmt.setString(3, ipAdress.getVia());
			    stmt.setString(4, ipAdress.getRemoteAddress());
			    stmt.setString(5, "Auto Migration Request");
			    stmt.setString(6, Istatus);
			    b = stmt.executeUpdate();
			    
			    stmt = conn.prepareStatement(sql2);
			    stmt.setString(1, IapplicationId);
			    stmt.setString(2, Iotp);
			    stmt.executeUpdate();
			    
			    if(a > 0 && b > 0){
			    	total = 1;
			    }
			    
			} 
			catch (Exception e){
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	 		finally{
	 			try{
	 				stmt.close();
	 				ConnectionManager.closeConnection(conn);
	 			} catch (Exception e){
	 				e.printStackTrace();
	 			}
	 		}
		
		if(total>0)
			return true;
		else
			return false;
	}
	
}
