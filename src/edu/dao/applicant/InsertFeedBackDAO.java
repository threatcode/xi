package edu.dao.applicant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import edu.utils.connection.ConnectionManager;

public class InsertFeedBackDAO {
	
	
	public Boolean insertFeedBack(String ssc_roll,String ssc_board,String ssc_passing_year,String mobile_number,String message){
		
	

	//	System.out.println("ssc_roll:"+ssc_roll+"ssc_board:"+ssc_board+"ssc_passing_year:"+ssc_passing_year+"mobile_number:"+mobile_number);
	//	System.out.println("message:"+message);
//		Connection conn = ConnectionManager.getWriteConnection();
//		
//		String sql ="insert into FEED_BACK (SSC_ROLL,BOARD_ID,PASSING_YEAR,MOBILE_NUMBER,MESSAGE) VALUES (?,?,?,?,?)";
//		
//		
//		PreparedStatement stmt = null;
//
//		try
//		{
//			stmt = conn.prepareStatement(sql);
//			
//			stmt.setString(1,ssc_roll.trim());
//			stmt.setString(2,ssc_board);
//			stmt.setString(3,ssc_passing_year);
//			stmt.setString(4,mobile_number.trim());
//			stmt.setString(5,message.trim());
//			
//			stmt.executeUpdate();
//			
//			
//
//		} 
//		catch (Exception e)
//		{e.printStackTrace();
//		return Boolean.FALSE;}
//		finally{
//			try{
//				stmt.close();
//				conn.close();
//				} 
//			catch (Exception e){
//					e.printStackTrace();
//				}
//			stmt = null;
//			conn = null;
//		}
		
		return Boolean.TRUE;
	}
	
}
