package edu.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import edu.utils.connection.ConnectionManager;

public class CollegeUploadDAO {
	
	public void addColleges(List EIIN, List COLLEGE_NAME, List MOBILE_NO, List EMAIL, List DIST_ID, List THANA_ID, List IS_METRO, List IS_ZILL_SADAR, List IS_GOVT, List BOARD_ID, List HELPER_BOARD_ID, List IS_ACTIVE, List IS_SQ_ELIGIBLE){
		Connection conn = ConnectionManager.getWriteConnection();
		PreparedStatement stmt = null;
		
		String sql = "insert into MST_COLLEGE_BK(" + 
				  "EIIN," +
				  " COLLEGE_NAME," +
				  " MOBILE_NO," +
				  " EMAIL," +
				  " DIST_ID," +
				  " THANA_ID," +
				  " IS_METRO," +
				  " IS_ZILL_SADAR," +
				  " IS_GOVT," +
				  " BOARD_ID," +
				  " HELPER_BOARD_ID," +
				  " IS_ACTIVE," +
				  " IS_SQ_ELIGIBLE)" +
				  " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for (int i = 1; i < EIIN.size(); i++){
				stmt.setString(1, ((String)EIIN.get(i)).trim());
				stmt.setString(2, ((String)COLLEGE_NAME.get(i)).trim());
				stmt.setString(3, ((String)MOBILE_NO.get(i)).trim());
				stmt.setString(4, ((String)EMAIL.get(i)).trim());
				stmt.setString(5, ((String)DIST_ID.get(i)).trim());
				stmt.setString(6, ((String)THANA_ID.get(i)).trim());
				stmt.setString(7, ((String)IS_METRO.get(i)).trim());
				stmt.setString(8, ((String)IS_ZILL_SADAR.get(i)).trim());
				stmt.setString(9, ((String)IS_GOVT.get(i)).trim());
				stmt.setString(10, ((String)BOARD_ID.get(i)).trim());
				stmt.setString(11, ((String)HELPER_BOARD_ID.get(i)).trim());
				stmt.setString(12, ((String)IS_ACTIVE.get(i)).trim());
				stmt.setString(13, (String)IS_SQ_ELIGIBLE.get(i));

				stmt.addBatch();
			}
			
			
			
			System.out.println(" Prepared statement= " + sql);
			
	
			stmt.executeBatch();
			conn.setAutoCommit(true);
			conn.commit();
		} catch (Exception e) {
			try {
				stmt.close();
				conn.rollback();
				ConnectionManager.closeConnection(conn);
				e.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}finally {
			try {
				stmt.close();
				ConnectionManager.closeConnection(conn);
			} catch (Exception e3) {
				e3.printStackTrace();	
			}
			stmt = null;
		}
		
		
	}

}
