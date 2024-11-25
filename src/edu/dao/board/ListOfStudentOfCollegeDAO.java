package edu.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.utils.connection.ConnectionManager;
import edu.dto.board.ListOfStudentOfCollegeDTO;



public class ListOfStudentOfCollegeDAO {
	
	public List<ListOfStudentOfCollegeDTO> getlistOfStudentOfCollege(String eiinCode){
		ListOfStudentOfCollegeDTO listOfStudentOfCollegeDTO = null;
		List<ListOfStudentOfCollegeDTO> studentList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String	sql="SELECT a.APPLICATION_ID,\n" +
                "       b.STUDENT_NAME,\n" +
                "       b.FATHER_NAME,\n" +
                "       a.SSC_ROLL_NO,\n" +
                "       c.BOARD_NAME_ENG,\n" +
                "       b.PASSING_YEAR,\n" +
                "       B.BIRTH_DATE,\n" +
                "       "+eiinCode+" EIIN\n" +
                "  FROM MST_EDU_BOARD c,APPLICATION_INFO a, BOARD_DATA_SSC b \n" +
                " WHERE     a.SSC_ROLL_NO = b.ROLL_NO\n" +
                "       AND A.SSC_BOARD_ID = b.BOARD_ID\n" +
                "       AND a.SSC_PASSING_YEAR = b.PASSING_YEAR\n" +
                "       AND A.SSC_BOARD_ID = C.BOARD_ID\n" +
                "       AND a.APPLICATION_ID IN (SELECT DISTINCT APPLICATION_ID\n" +
                "                                  FROM APPLICATION_COLLEGES\n" +
                "                                 WHERE EIIN =?)";
		

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, eiinCode);
			r = stmt.executeQuery();
			
			studentList = new ArrayList<ListOfStudentOfCollegeDTO>();
			while(r.next())
			{
				listOfStudentOfCollegeDTO = new ListOfStudentOfCollegeDTO();
				listOfStudentOfCollegeDTO.setApplicationID(r.getString("APPLICATION_ID"));
				listOfStudentOfCollegeDTO.setApplicantName(r.getString("STUDENT_NAME"));
				listOfStudentOfCollegeDTO.setFatherName(r.getString("FATHER_NAME"));
				listOfStudentOfCollegeDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				listOfStudentOfCollegeDTO.setBoardName(r.getString("BOARD_NAME_ENG"));
				listOfStudentOfCollegeDTO.setSscPassingYear(r.getString("PASSING_YEAR"));
				listOfStudentOfCollegeDTO.setDateOfBirth(r.getDate("BIRTH_DATE"));
				listOfStudentOfCollegeDTO.setEiinCode(r.getString("EIIN"));
			//	listOfStudentOfCollegeDTO.setPaymentType(r.getString("PAYMENT_TYPE"));
			//	listOfStudentOfCollegeDTO.setPaymentStatus(r.getString("PAYMENT_STATUS"));

				studentList.add(listOfStudentOfCollegeDTO);
				//System.out.println(studentList.size());

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
		
		return studentList;
	}
	
	
	public List<ListOfStudentOfCollegeDTO> getlistOfShiftVersionGroupApplicationNumberOfCollege(String eiinCode,String BoardId){
		ListOfStudentOfCollegeDTO shiftVersionGroupApplicationNumberOfCollegeDTO = null;
		List<ListOfStudentOfCollegeDTO> shiftVersionGroupApplicationNumberOfCollegeList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "SELECT A.SHIFT_ID,\n" +
                "         C.SHIFT_NAME,\n" +
                "         A.VERSION_ID,\n" +
                "         D.VERSION_NAME,\n" +
                "         A.GROUP_ID,\n" +
                "         E.GROUP_NAME,\n" +
                "         COUNT (DISTINCT A.APPLICATION_ID) APPLICATION_NUMBER\n" +
                "    FROM MST_SHIFT c,\n" +
                "         MST_VERSION d,\n" +
                "         MST_GROUP e,\n" +
                "         MST_COLLEGE b,\n" +
                "         APPLICATION_COLLEGES a\n" +
                "   WHERE     A.EIIN =?\n" +
                "         AND B.BOARD_ID =?\n" +
                "         AND A.EIIN = B.EIIN\n" +
                "         AND A.SHIFT_ID = C.SHIFT_ID\n" +
                "         AND A.VERSION_ID = D.VERSION_ID\n" +
                "         AND A.GROUP_ID = E.GROUP_ID\n" +
                "GROUP BY A.SHIFT_ID,\n" +
                "         C.SHIFT_NAME,\n" +
                "         A.VERSION_ID,\n" +
                "         D.VERSION_NAME,\n" +
                "         A.GROUP_ID,\n" +
                "         E.GROUP_NAME\n" +
                "ORDER BY A.SHIFT_ID, A.VERSION_ID, A.GROUP_ID";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
	//		 BoardId="15";
			
			stmt.setString(1, eiinCode);
			stmt.setString(2, BoardId);
			r = stmt.executeQuery();
			
			shiftVersionGroupApplicationNumberOfCollegeList= new ArrayList<ListOfStudentOfCollegeDTO>();
			while(r.next())
			{
				shiftVersionGroupApplicationNumberOfCollegeDTO = new ListOfStudentOfCollegeDTO();
			
				shiftVersionGroupApplicationNumberOfCollegeDTO.setShiftID(r.getString("SHIFT_ID"));
				shiftVersionGroupApplicationNumberOfCollegeDTO.setShiftName(r.getString("SHIFT_NAME"));
				shiftVersionGroupApplicationNumberOfCollegeDTO.setVersionID(r.getString("VERSION_ID"));
				shiftVersionGroupApplicationNumberOfCollegeDTO.setVersionName(r.getString("VERSION_NAME"));
				shiftVersionGroupApplicationNumberOfCollegeDTO.setGroupId(r.getString("GROUP_ID"));
				shiftVersionGroupApplicationNumberOfCollegeDTO.setGroupName(r.getString("GROUP_NAME"));
				shiftVersionGroupApplicationNumberOfCollegeDTO.setApplicationNunber(r.getString("APPLICATION_NUMBER"));


				shiftVersionGroupApplicationNumberOfCollegeList.add(shiftVersionGroupApplicationNumberOfCollegeDTO);
				

			}
			
/*			System.out.println("shiftVersionGroupApplicationNumberOfCollegeList.size="+shiftVersionGroupApplicationNumberOfCollegeList.size());*/
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
		
		return shiftVersionGroupApplicationNumberOfCollegeList;
	}
	

}
