package edu.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.dto.board.ApplicationStatBoardDTO;
import edu.dto.board.CollegeInfoDTO;
import edu.dto.board.DistrictinfoDTO;
import edu.utils.connection.ConnectionManager;
import edu.dto.board.DashBoardBoardDTO;



public class ApplicationStatBoardDAO {
	
	
	public DashBoardBoardDTO getBoardDashBoard(String userId){
		   DashBoardBoardDTO dashBoardDTO = null;

		
		   Connection conn = ConnectionManager.getReadConnection();
		   
		String sql = "SELECT TOTAL_APPLICATION, " +
				"       TOTAL_APPLICANT, " +
				"       WEB_APPLICATION, " +
				"       WEB_APPLICANT, " +
//				"       SMS_APPLICATION, " +
//				"       SMS_APPLICANT," +
				"       received, not_received  " +
				"  FROM (SELECT COUNT (APPLICANT_ID) TOTAL_APPLICATION, " +
				"               COUNT (DISTINCT APPLICANT_ID) TOTAL_APPLICANT " +
				"          FROM APPLICATION_COLLEGES " +
				"         WHERE EIIN IN (SELECT EIIN " +
				"                          FROM MST_COLLEGE " +
				"                         WHERE BOARD_ID =?)) t1, " +
				"       (SELECT COUNT (APPLICANT_ID) WEB_APPLICATION, " +
				"               COUNT (DISTINCT APPLICANT_ID) WEB_APPLICANT " +
				"          FROM APPLICATION_COLLEGES " +
				"         WHERE     EIIN IN (SELECT EIIN " +
				"                              FROM MST_COLLEGE " +
				"                             WHERE BOARD_ID =?) " +
				"               AND APP_TYPE = 'WEB') t2, " +
//				"       (SELECT COUNT (APPLICANT_ID) SMS_APPLICATION, " +
//				"               COUNT (DISTINCT APPLICANT_ID) SMS_APPLICANT " +
//				"          FROM APPLICATION_COLLEGES " +
//				"         WHERE     EIIN IN (SELECT EIIN " +
//				"                              FROM MST_COLLEGE " +
//				"                             WHERE BOARD_ID =?) " +
//				"               AND APP_TYPE = 'SMS') t3, " +
				"      (SELECT COUNT (DISTINCT eiin) received " +
				"          FROM board_result " +
				"         WHERE     status = 'College Received' " +
				"               AND eiin IN (SELECT eiin " +
				"                              FROM mst_college " +
				"                             WHERE board_id =?)) t4, " +
				"       (SELECT COUNT (DISTINCT eiin) not_received " +
				"          FROM board_result " +
				"         WHERE     status = 'Not Approved' " +
				"               AND eiin IN (SELECT eiin " +
				"                              FROM mst_college " +
				"                             WHERE board_id =?) " +
				"               AND eiin NOT IN (SELECT DISTINCT eiin " +
				"                                  FROM board_result " +
				"                                 WHERE     status = 'College Received' " +
				"                                       AND eiin IN (SELECT eiin " +
				"                                                      FROM mst_college " +
				"                                                     WHERE board_id =?))) t5 "  ;
		   
		   
/*		   String sql="SELECT TOTAL_APPLICATION,\n" +
                   "       TOTAL_APPLICANT,\n" +
                   "       WEB_APPLICATION,\n" +
                   "       WEB_APPLICANT,\n" +
                   "       SMS_APPLICATION,\n" +
                   "       SMS_APPLICANT,\n" +
                   "       received,\n" +
                   "       not_received\n" +
                   "  FROM (SELECT COUNT (APPLICATION_ID) TOTAL_APPLICATION,\n" +
                   "               COUNT (DISTINCT APPLICATION_ID) TOTAL_APPLICANT\n" +
                   "          FROM APPLICATION_COLLEGES\n" +
                   "         WHERE EIIN IN (SELECT EIIN\n" +
                   "                          FROM MST_COLLEGE\n" +
                   "                         WHERE BOARD_ID =?)) t1,\n" +
                   "       (SELECT COUNT (APPLICATION_ID) WEB_APPLICATION,\n" +
                   "               COUNT (DISTINCT APPLICATION_ID) WEB_APPLICANT\n" +
                   "          FROM APPLICATION_COLLEGES\n" +
                   "         WHERE     EIIN IN (SELECT EIIN\n" +
                   "                              FROM MST_COLLEGE\n" +
                   "                             WHERE BOARD_ID =?)\n" +
                   "               AND APPLICATION_TYPE = 'W') t2,\n" +
                   "       (SELECT COUNT (APPLICATION_ID) SMS_APPLICATION,\n" +
                   "               COUNT (DISTINCT APPLICATION_ID) SMS_APPLICANT\n" +
                   "          FROM APPLICATION_COLLEGES\n" +
                   "         WHERE     EIIN IN (SELECT EIIN\n" +
                   "                              FROM MST_COLLEGE\n" +
                   "                             WHERE BOARD_ID =?)\n" +
                   "               AND APPLICATION_TYPE = 'S') t3,\n" +
                   "       (SELECT COUNT (DISTINCT eiin) received\n" +
                   "          FROM board_result\n" +
                   "         WHERE     status = 'College Received'\n" +
                   "               AND eiin IN (SELECT eiin\n" +
                   "                              FROM mst_college\n" +
                   "                             WHERE board_id =?)) t4,\n" +
                   "       (SELECT COUNT (DISTINCT eiin) not_received\n" +
                   "          FROM board_result\n" +
                   "         WHERE     status = 'Not Approved'\n" +
                   "               AND eiin IN (SELECT eiin\n" +
                   "                              FROM mst_college\n" +
                   "                             WHERE board_id =?)\n" +
                   "               AND eiin NOT IN (SELECT DISTINCT eiin\n" +
                   "                                  FROM board_result\n" +
                   "                                 WHERE     status = 'College Received'\n" +
                   "                                       AND eiin IN (SELECT eiin\n" +
                   "                                                      FROM mst_college\n" +
                   "                                                     WHERE board_id =?))) t5";*/
		   
		   
/*		   String sql="SELECT TOTAL_APPLICATION,\n" +
			         "       TOTAL_APPLICANT,\n" +
			         "       WEB_APPLICATION,\n" +
			         "       WEB_APPLICANT,\n" +
			         "       SMS_APPLICATION,\n" +
			         "       SMS_APPLICANT, received, not_received\n" +
			         "  FROM (SELECT COUNT (APPLICATION_ID) TOTAL_APPLICATION,\n" +
			         "               COUNT (DISTINCT APPLICATION_ID) TOTAL_APPLICANT\n" +
			         "          FROM APPLICATION_COLLEGES\n" +
			         "         WHERE EIIN IN (SELECT EIIN\n" +
			         "                          FROM MST_COLLEGE\n" +
			         "                         WHERE BOARD_ID =?)) t1,\n" +
			         "       (SELECT COUNT (APPLICATION_ID) WEB_APPLICATION,\n" +
			         "               COUNT (DISTINCT APPLICATION_ID) WEB_APPLICANT\n" +
			         "          FROM APPLICATION_COLLEGES\n" +
			         "         WHERE     EIIN IN (SELECT EIIN\n" +
			         "                              FROM MST_COLLEGE\n" +
			         "                             WHERE BOARD_ID =?)\n" +
			         "               AND APPLICATION_TYPE = 'W') t2,\n" +
			         "       (SELECT COUNT (APPLICATION_ID) SMS_APPLICATION,\n" +
			         "               COUNT (DISTINCT APPLICATION_ID) SMS_APPLICANT\n" +
			         "          FROM APPLICATION_COLLEGES\n" +
			         "         WHERE     EIIN IN (SELECT EIIN\n" +
			         "                              FROM MST_COLLEGE\n" +
			         "                             WHERE BOARD_ID =?)\n" +
			         "               AND APPLICATION_TYPE = 'S') t3,\n" +
			         "(select count(distinct eiin) received from board_result where status = 'College Received' and eiin in (select eiin from mst_college where board_id = ?)) t4,\n" +
			         "(select count(distinct eiin) not_received from board_result where status = 'Not Approved' and eiin in (select eiin from mst_college where board_id = ?)) t5"; */
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,userId);
			stmt.setString(2,userId);
			stmt.setString(3,userId);
			stmt.setString(4,userId);
			stmt.setString(5,userId);
//			stmt.setString(6,userId);
			r = stmt.executeQuery();
			
			
			while(r.next())
			{
				dashBoardDTO = new DashBoardBoardDTO();
				
				dashBoardDTO.setTotalApplicant(Integer.parseInt(r.getString("TOTAL_APPLICANT") == null ? "0" : r.getString("TOTAL_APPLICANT")));
				dashBoardDTO.setTotalApplication(Integer.parseInt(r.getString("TOTAL_APPLICATION") == null ? "0" : r.getString("TOTAL_APPLICATION")));
				dashBoardDTO.setWebApplicant(Integer.parseInt(r.getString("WEB_APPLICANT") == null ? "0" : r.getString("WEB_APPLICANT")));
				dashBoardDTO.setWebApplication(Integer.parseInt(r.getString("WEB_APPLICATION") == null ? "0" : r.getString("WEB_APPLICATION")));
//				dashBoardDTO.setSmsApplicant(Integer.parseInt(r.getString("SMS_APPLICANT") == null ? "0" : r.getString("SMS_APPLICANT")));
//				dashBoardDTO.setSmsApplication(Integer.parseInt(r.getString("SMS_APPLICATION") == null ? "0" : r.getString("SMS_APPLICATION")));
				dashBoardDTO.setCollegeAdmReceiveCount(Integer.parseInt(r.getString("received") == null ? "0" : r.getString("received")));
				dashBoardDTO.setCollegeAdmNotReceiveCount(Integer.parseInt(r.getString("not_received") == null ? "0" : r.getString("not_received")));

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
	
	
	
	public List<ApplicationStatBoardDTO> getDateWiseApplicationStatInterBoard(){
		ApplicationStatBoardDTO dateWiseApplicationStatBoardDTO = null;
		List<ApplicationStatBoardDTO> dateWiseApplicationStatBoardList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="  SELECT t1.APP_DATE, t1.TOTAL_APPLICATION, t1.TOTAL_APPLICANT " +
				"    FROM (  SELECT TO_CHAR (applied_on, 'dd, MON') APP_DATE, " +
				"                   COUNT (APPLICANT_ID) TOTAL_APPLICATION, " +
				"                   COUNT (DISTINCT APPLICANT_ID) TOTAL_APPLICANT " +
				"              FROM APPLICATION_COLLEGES " +
				"          GROUP BY TO_CHAR (applied_on, 'dd, MON')) t1 " +
				" ORDER BY TO_DATE (t1.APP_DATE, 'dd, MON') " ;

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
            r = stmt.executeQuery();
			
			dateWiseApplicationStatBoardList = new ArrayList<ApplicationStatBoardDTO>();
			while(r.next())
			{
				dateWiseApplicationStatBoardDTO = new ApplicationStatBoardDTO();

				dateWiseApplicationStatBoardDTO.setAppDate(r.getString("APP_DATE"));
				dateWiseApplicationStatBoardDTO.setTotalApplication(Integer.parseInt(r.getString("TOTAL_APPLICATION") == null ? "0" : r.getString("TOTAL_APPLICATION")));
				dateWiseApplicationStatBoardDTO.setTotalApplicant(Integer.parseInt(r.getString("TOTAL_APPLICANT") == null ? "0" : r.getString("TOTAL_APPLICANT")));

				dateWiseApplicationStatBoardList.add(dateWiseApplicationStatBoardDTO);
				

			}
			/*System.out.println("dateWiseApplicationStatBoardList.size="+dateWiseApplicationStatBoardList.size());*/
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
		
		return dateWiseApplicationStatBoardList;
	}
	
	
	public List<ApplicationStatBoardDTO> getDateWiseApplicationStatBoard(String userId){
		ApplicationStatBoardDTO dateWiseApplicationStatBoardDTO = null;
		List<ApplicationStatBoardDTO> dateWiseApplicationStatBoardList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="  SELECT t1.APP_DATE, t1.TOTAL_APPLICATION, t1.TOTAL_APPLICANT\n" +
	            "    FROM (  SELECT TO_CHAR (applied_on, 'dd, MON') APP_DATE,\n" +
	            "                   COUNT (APPLICANT_ID) TOTAL_APPLICATION,\n" +
	            "                   COUNT (DISTINCT APPLICANT_ID) TOTAL_APPLICANT\n" +
	            "              FROM APPLICATION_COLLEGES\n" +
	            "             WHERE EIIN IN (SELECT EIIN\n" +
	            "                              FROM MST_COLLEGE\n" +
	            "                             WHERE BOARD_ID =?)\n" +
	            "          GROUP BY TO_CHAR (applied_on, 'dd, MON')) t1\n" +
	            "ORDER BY TO_DATE (t1.APP_DATE, 'dd, MON')";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			//String uid="15";
			//stmt.setString(1,uid);
			
			stmt.setString(1, userId);
			
			r = stmt.executeQuery();
			
			dateWiseApplicationStatBoardList = new ArrayList<ApplicationStatBoardDTO>();
			while(r.next())
			{
				dateWiseApplicationStatBoardDTO = new ApplicationStatBoardDTO();

				dateWiseApplicationStatBoardDTO.setAppDate(r.getString("APP_DATE"));
				dateWiseApplicationStatBoardDTO.setTotalApplication(Integer.parseInt(r.getString("TOTAL_APPLICATION") == null ? "0" : r.getString("TOTAL_APPLICATION")));
				dateWiseApplicationStatBoardDTO.setTotalApplicant(Integer.parseInt(r.getString("TOTAL_APPLICANT") == null ? "0" : r.getString("TOTAL_APPLICANT")));

				dateWiseApplicationStatBoardList.add(dateWiseApplicationStatBoardDTO);
				

			}
			/*System.out.println("dateWiseApplicationStatBoardList.size="+dateWiseApplicationStatBoardList.size());*/
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
		
		return dateWiseApplicationStatBoardList;
	}
	
	
	public List<DistrictinfoDTO> getDistrictinfo(){
		DistrictinfoDTO districtinfoDTO = null;
		List<DistrictinfoDTO> districtinfoList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql =" SELECT DIST_ID, DIST_NAME\n" +
		         "    FROM MST_DISTRICT\n" +
		         "ORDER BY DIST_NAME";
		

		
		

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
						
			r = stmt.executeQuery();
			
			districtinfoList = new ArrayList<DistrictinfoDTO>();
			while(r.next())
			{
				districtinfoDTO = new DistrictinfoDTO();

				districtinfoDTO.setDistrictID(r.getString("DIST_ID"));
				districtinfoDTO.setDistrictName(r.getString("DIST_NAME"));
				
				districtinfoList.add(districtinfoDTO);
				

			}
		//	System.out.println("districtinfoList.size="+districtinfoList.size());
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
		
		return districtinfoList;
	}		
	
	public List<DistrictinfoDTO> getBoardDistrictinfo(String board_id){
		DistrictinfoDTO districtinfoDTO = null;
		List<DistrictinfoDTO> districtinfoList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql =" SELECT distinct t1.DIST_ID, t1.DIST_NAME FROM MST_DISTRICT t1, MST_COLLEGE t2" +
				" where T1.DIST_ID=T2.DIST_ID and T2.BOARD_ID='"+board_id+"' ORDER BY DIST_NAME";
		

		
		

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
						
			r = stmt.executeQuery();
			
			districtinfoList = new ArrayList<DistrictinfoDTO>();
			while(r.next())
			{
				districtinfoDTO = new DistrictinfoDTO();

				districtinfoDTO.setDistrictID(r.getString("DIST_ID"));
				districtinfoDTO.setDistrictName(r.getString("DIST_NAME"));
				
				districtinfoList.add(districtinfoDTO);
				

			}
		//	System.out.println("districtinfoList.size="+districtinfoList.size());
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
		
		return districtinfoList;
	}	
	
	
	public List<CollegeInfoDTO> getCollegeInfo(String userId){
		CollegeInfoDTO collegeInfoDTO = null;
		List<CollegeInfoDTO> collegeInfoList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "SELECT EIIN, COLLEGE_NAME FROM MST_COLLEGE WHERE BOARD_ID =? ORDER BY COLLEGE_NAME";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			
			r = stmt.executeQuery();
			
			collegeInfoList = new ArrayList<CollegeInfoDTO>();
			while(r.next())
			{
				collegeInfoDTO = new CollegeInfoDTO();

				
				collegeInfoDTO.setEiinCode(r.getString("EIIN"));
				collegeInfoDTO.setCollegeName(r.getString("COLLEGE_NAME"));
				
				collegeInfoList.add(collegeInfoDTO);
				

			}
			/*System.out.println("collegeInfoList.size="+collegeInfoList.size());*/
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
		
		return collegeInfoList;
	}
	
	/**
	 * @author nasir
	 * @param boardId
	 * @param distId
	 * @return
	 */
	public List<CollegeInfoDTO> getCollegeByDistrict(String boardId, String distId){
		CollegeInfoDTO collegeInfoDTO = null;
		List<CollegeInfoDTO> collegeInfoList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		

		String sql = "SELECT EIIN, COLLEGE_NAME FROM MST_COLLEGE WHERE BOARD_ID =? AND DIST_ID = ? ORDER BY COLLEGE_NAME";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, boardId);
			stmt.setString(2, distId);
			
			r = stmt.executeQuery();
			
			collegeInfoList = new ArrayList<CollegeInfoDTO>();
			while(r.next())
			{
				collegeInfoDTO = new CollegeInfoDTO();
				
				collegeInfoDTO.setEiinCode(r.getString("EIIN"));
				collegeInfoDTO.setCollegeName(r.getString("COLLEGE_NAME"));
				
				collegeInfoList.add(collegeInfoDTO);
				

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
		
		return collegeInfoList;
	}	

}
