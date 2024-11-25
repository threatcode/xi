package edu.dao.college;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.utils.connection.ConnectionManager;
import edu.dto.college.DashBoardCollegeDTO;
import edu.dto.college.ApplicationStatCollegeDTO;



public class ApplicationStatCollegeDAO {
	
	public DashBoardCollegeDTO getRegDashBoard(String eiin,String boardID){
		DashBoardCollegeDTO dashBoardDTO = null;

		
		Connection conn = ConnectionManager.getReadConnection();
		
		 
		String sql = "select admit,reg from " +
				" (select count(*) admit " +
				" from BOARD_RESULT where eiin=?) t1,  " +
				" (select count(*) reg " +
				" from BOARD_REGISTRATION where eiin=?)t2 " ;
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,eiin);
			stmt.setString(2,eiin);

			
			r = stmt.executeQuery();
			
			
			while(r.next())
			{
				dashBoardDTO = new DashBoardCollegeDTO();
				
				dashBoardDTO.setTotalApplicant(Integer.parseInt(r.getString("admit") == null ? "0" : r.getString("admit")));
				dashBoardDTO.setTotalApplication(Integer.parseInt(r.getString("reg") == null ? "0" : r.getString("reg")));
				

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
		
		return dashBoardDTO;
	}
	
	
	public DashBoardCollegeDTO getCollegeDashBoard(String userId,String boardID){
		DashBoardCollegeDTO dashBoardDTO = null;

		
		Connection conn = ConnectionManager.getReadConnection();
		
		   String sql="SELECT TOTAL_APPLICATION, " +
				   "       TOTAL_APPLICANT, " +
				   "       WEB_APPLICATION, " +
				   "       WEB_APPLICANT, " +
				   "       SMS_APPLICATION, " +
				   "       SMS_APPLICANT " +
				   "  FROM (SELECT COUNT (APPLICANT_ID) TOTAL_APPLICATION, " +
				   "               COUNT (DISTINCT APPLICANT_ID) TOTAL_APPLICANT " +
				   "          FROM APPLICATION_COLLEGES " +
				   "         WHERE EIIN =? AND EIIN IN (SELECT EIIN " +
				   "                          FROM MST_COLLEGE " +
				   "                         WHERE BOARD_ID =?)) t1, " +
				   "       (SELECT COUNT (APPLICANT_ID) WEB_APPLICATION, " +
				   "               COUNT (DISTINCT APPLICANT_ID) WEB_APPLICANT " +
				   "          FROM APPLICATION_COLLEGES " +
				   "         WHERE  EIIN =? AND  EIIN IN (SELECT EIIN " +
				   "                              FROM MST_COLLEGE " +
				   "                             WHERE BOARD_ID =?) " +
				   "               AND APP_TYPE = 'WEB') t2, " +
				   "       (SELECT COUNT (APPLICANT_ID) SMS_APPLICATION, " +
				   "               COUNT (DISTINCT APPLICANT_ID) SMS_APPLICANT " +
				   "          FROM APPLICATION_COLLEGES " +
				   "         WHERE  EIIN =? AND  EIIN IN (SELECT EIIN " +
				   "                              FROM MST_COLLEGE " +
				   "                             WHERE BOARD_ID =?) " +
				   "               AND APP_TYPE = 'SMS') t3 " ; 
		
		/*String sql = "SELECT COUNT (APPLICATION_ID) TOTAL_APPLICATION,\n" +
                "       COUNT (DISTINCT APPLICATION_ID) TOTAL_APPLICANT\n" +
                "  FROM APPLICATION_COLLEGES\n" +
                " WHERE     EIIN =?\n" +
                "       AND EIIN IN (SELECT EIIN\n" +
                "                      FROM MST_COLLEGE\n" +
                "                     WHERE BOARD_ID =?)";*/
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,userId);
			stmt.setString(2,boardID);
			stmt.setString(3,userId);
			stmt.setString(4,boardID);
			stmt.setString(5,userId);
			stmt.setString(6,boardID);

			
			r = stmt.executeQuery();
			
			
			while(r.next())
			{
				dashBoardDTO = new DashBoardCollegeDTO();
				
				dashBoardDTO.setTotalApplicant(Integer.parseInt(r.getString("TOTAL_APPLICANT") == null ? "0" : r.getString("TOTAL_APPLICANT")));
				dashBoardDTO.setTotalApplication(Integer.parseInt(r.getString("TOTAL_APPLICATION") == null ? "0" : r.getString("TOTAL_APPLICATION")));
				dashBoardDTO.setWebApplicant(Integer.parseInt(r.getString("WEB_APPLICANT") == null ? "0" : r.getString("WEB_APPLICANT")));
				dashBoardDTO.setWebApplication(Integer.parseInt(r.getString("WEB_APPLICATION") == null ? "0" : r.getString("WEB_APPLICATION")));
				dashBoardDTO.setSmsApplicant(Integer.parseInt(r.getString("SMS_APPLICANT") == null ? "0" : r.getString("SMS_APPLICANT")));
				dashBoardDTO.setSmsApplication(Integer.parseInt(r.getString("SMS_APPLICATION") == null ? "0" : r.getString("SMS_APPLICATION")));

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
		
		return dashBoardDTO;
	}
	
	
	public List<ApplicationStatCollegeDTO> getDateWiseApplicationStatCollege(String userId){
		ApplicationStatCollegeDTO dateWiseApplicationStatCollegeDTO = null;
		List<ApplicationStatCollegeDTO> dateWiseApplicationStatCollegeList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="  SELECT t1.APP_DATE, t1.TOTAL_APPLICATION, t1.TOTAL_APPLICANT\n" +
	            "    FROM (  SELECT TO_CHAR (applied_on, 'dd, MON') app_date,\n" +
	            "                   COUNT (APPLICATION_ID) total_application,\n" +
	            "                   COUNT (DISTINCT APPLICATION_ID) total_applicant\n" +
	            "              FROM APPLICATION_COLLEGES\n" +
	            "             WHERE EIIN =?\n" +
	            "          GROUP BY TO_CHAR (applied_on, 'dd, MON')) t1\n" +
	            "ORDER BY TO_DATE (t1.APP_DATE, 'dd, MON')";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
		//	String uid="103023";
		//	stmt.setString(1,uid);
			
			stmt.setString(1, userId);
			
			r = stmt.executeQuery();
			
			dateWiseApplicationStatCollegeList = new ArrayList<ApplicationStatCollegeDTO>();
			while(r.next())
			{
				dateWiseApplicationStatCollegeDTO = new ApplicationStatCollegeDTO();

				dateWiseApplicationStatCollegeDTO.setAppDate(r.getString("APP_DATE"));
				dateWiseApplicationStatCollegeDTO.setTotalApplication(Integer.parseInt(r.getString("TOTAL_APPLICATION") == null ? "0" : r.getString("TOTAL_APPLICATION")));
				dateWiseApplicationStatCollegeDTO.setTotalApplicant(Integer.parseInt(r.getString("TOTAL_APPLICANT") == null ? "0" : r.getString("TOTAL_APPLICANT")));

				dateWiseApplicationStatCollegeList.add(dateWiseApplicationStatCollegeDTO);
				

			}
			/*System.out.println("dateWiseApplicationStatCollegeList.size="+dateWiseApplicationStatCollegeList.size());*/
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
		
		return dateWiseApplicationStatCollegeList;
	}	

}
