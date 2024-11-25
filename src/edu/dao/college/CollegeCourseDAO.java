package edu.dao.college;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import edu.dto.college.CollegeCourseDTO;
import edu.utils.connection.ConnectionManager;

public class CollegeCourseDAO {
	
	

	
	public List<CollegeCourseDTO> getCollegeCourseList(String eiinCode, String boardId){
		CollegeCourseDTO collegeCourseDTO = null;
		List<CollegeCourseDTO> courseList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="SELECT B.EIIN,\n" +
                "       S.SHIFT_NAME,\n" +
                "       V.VERSION_NAME,\n" +
                "       G.GROUP_NAME,\n" +
                "       DECODE(B.GENDER,'M','Male','F','Female','C','Co-Education') GENDER,\n" +
                "       B.TOTAL_SEAT,\n" +
                "       B.MINIMUM_GPA,\n" +
                "       B.SPC_QUOTA_YN,\n" +
                "       B.SPC_QUOTA_SEAT_ALLOC,\n" +
                "       B.SPC_QUOTA_MIN_GPA,\n" +
                "       B.OWN_GPA\n" +
                "  FROM MST_COLLEGE_GROUPS B,\n" +
                "       MST_SHIFT S,\n" +
                "       MST_VERSION V,\n" +
                "       MST_GROUP G,\n" +
                "       MST_COLLEGE C\n" +
                " WHERE     B.SHIFT_ID = S.SHIFT_ID\n" +
                "       AND B.VERSION_ID = V.VERSION_ID\n" +
                "       AND B.GROUP_ID = G.GROUP_ID\n" +
                "       AND B.EIIN = C.EIIN\n" +
                "       AND C.BOARD_ID =?\n" +
                "       AND B.EIIN =?";
		
	/*	String sql ="SELECT B.EIIN_CODE, S.SHIFT_NAME, M.MEDIUM_NAME," +
				" G.GROUP_NAME, B.GENDER, getTotalSet(B.EIIN_CODE, B.SHIFT_ID, B.MEDIUM_ID, B.GROUP_ID) TOTAL_SEAT," +
				" B.MINIMUM_GPA, B.SPECIAL_QOUTA, B.QUOTA_PERCENT," +
				" B.QUOTA_GPA, B.MIN_GPA_OWN, B.UNIQ" +
				" FROM BOARD_COLLEGE_COURSES B," +
				" MST_SHIFT S, MST_MEDIUM M, MST_GROUP_OLD G" +
				" where B.SHIFT_ID = S.SHIFT_ID" +
				" and B.MEDIUM_ID = M.MEDIUM_ID" +
				" and B.GROUP_ID = G.GROUP_ID" +
				" and G.BOARD_ID = ?" +
				" and B.EIIN_CODE = ?";
		*/
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			

			
			stmt.setString(1, boardId);
			stmt.setString(2, eiinCode);
			
			r = stmt.executeQuery();
			
			courseList = new ArrayList<CollegeCourseDTO>();
			while(r.next())
			{
				collegeCourseDTO = new CollegeCourseDTO();
				
				collegeCourseDTO.setEiinCode(r.getString("EIIN"));
				collegeCourseDTO.setShift(r.getString("SHIFT_NAME"));
				collegeCourseDTO.setMedium(r.getString("VERSION_NAME"));
				collegeCourseDTO.setGroupName(r.getString("GROUP_NAME"));
				collegeCourseDTO.setGender(r.getString("GENDER"));
				collegeCourseDTO.setTotalSeat(r.getInt("TOTAL_SEAT"));
				collegeCourseDTO.setMinGpa(r.getFloat("MINIMUM_GPA"));
				collegeCourseDTO.setSpcQuota(r.getString("SPC_QUOTA_YN"));
				collegeCourseDTO.setQuotaPercent(r.getInt("SPC_QUOTA_SEAT_ALLOC"));
				collegeCourseDTO.setQuotaGpa(r.getFloat("SPC_QUOTA_MIN_GPA"));
				collegeCourseDTO.setOwnGpa(r.getFloat("OWN_GPA"));
			//	collegeCourseDTO.setUniq(r.getString("UNIQ"));
				
				courseList.add(collegeCourseDTO);
				

			}
			/*System.out.println("courseList.size="+courseList.size());*/
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
		
		return courseList;
	}

}
