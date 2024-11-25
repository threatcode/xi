package edu.dao.college;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;


import edu.dto.board.ApplicantInfoBoardDTO;
import edu.dto.college.ApplicantInfoDTO;
import edu.utils.connection.ConnectionManager;

public class ApplicantInfoDAO {
	
	
	public ApplicantInfoDTO getApplicantBasicInfo(String applicationID){
		ApplicantInfoDTO applicantInfoDTO = null;

		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="SELECT a.APPLICATION_ID,\n" +
		         "       b.STUDENT_NAME,\n" +
		         "       b.FATHER_NAME,\n" +
		         "       a.SSC_ROLL_NO,\n" +
		         "       c.BOARD_NAME_ENG,\n" +
		         "       b.PASSING_YEAR\n" +
		         "  FROM MST_EDU_BOARD c,APPLICATION_INFO a, BOARD_DATA_SSC b \n" +
		         " WHERE     a.SSC_ROLL_NO = b.ROLL_NO\n" +
		         "       AND A.SSC_BOARD_ID = b.BOARD_ID\n" +
		         "       AND a.SSC_PASSING_YEAR = b.PASSING_YEAR\n" +
		         "       AND A.SSC_BOARD_ID = C.BOARD_ID\n" +
		         "       AND a.APPLICATION_ID =?";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,applicationID);
			

			
			r = stmt.executeQuery();
			
			
			while(r.next())
			{
				applicantInfoDTO = new ApplicantInfoDTO();
				
				applicantInfoDTO.setApplicationID(r.getString("APPLICATION_ID"));
				applicantInfoDTO.setApplicantName(r.getString("STUDENT_NAME"));
				applicantInfoDTO.setFatherName(r.getString("FATHER_NAME"));
				applicantInfoDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				applicantInfoDTO.setBoardName(r.getString("BOARD_NAME_ENG"));
				applicantInfoDTO.setSscPassingYear(r.getString("PASSING_YEAR"));


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
		
		return applicantInfoDTO;
	}
	
	public List<ApplicantInfoDTO> getApplicantCollegeInfoList(String applicationID,String userId){
		ApplicantInfoDTO applicantCollegeInfoDTO = null;
		List<ApplicantInfoDTO> applicantCollegeInfoList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql =" SELECT G.DIST_NAME COLLEGE_DISTRICT,\n" +
		         "         B.COLLEGE_NAME,\n" +
		         "         D.SHIFT_NAME,\n" +
		         "         E.VERSION_NAME,\n" +
		         "         F.GROUP_NAME, \n" +
		         "         A.APPLICATION_TYPE,\n" +
		         "         A.PAYMENT_STATUS,\n" +
		         "         a.PRIORITY_ORDER\n" +
		         "    FROM MST_SHIFT d,\n" +
		         "         MST_VERSION e,\n" +
		         "         MST_GROUP f,\n" +
		         "         MST_DISTRICT g,\n" +
		         "         MST_COLLEGE b,\n" +
		         "         APPLICATION_COLLEGES a\n" +
		         "   WHERE     a.APPLICATION_ID =?\n" +
		         "         AND A.EIIN =?\n" +
		         "         AND A.EIIN = B.EIIN\n" +
		         "         AND A.SHIFT_ID = D.SHIFT_ID\n" +
		         "         AND A.VERSION_ID = E.VERSION_ID\n" +
		         "         AND A.GROUP_ID = F.GROUP_ID\n" +
		         "         AND B.DIST_ID = G.DIST_ID\n" +
		         "ORDER BY a.PRIORITY_ORDER";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
		//	String uid="103023";
		//	stmt.setString(1,uid);
			
			stmt.setString(1, applicationID);
			stmt.setString(2, userId);
			
			r = stmt.executeQuery();
			
			applicantCollegeInfoList = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				applicantCollegeInfoDTO = new ApplicantInfoDTO();
				
				applicantCollegeInfoDTO.setDistrictName(r.getString("COLLEGE_DISTRICT"));
				applicantCollegeInfoDTO.setCollegeName(r.getString("COLLEGE_NAME"));
				applicantCollegeInfoDTO.setShiftName(r.getString("SHIFT_NAME"));
				applicantCollegeInfoDTO.setVersionName(r.getString("VERSION_NAME"));
				applicantCollegeInfoDTO.setGroupName(r.getString("GROUP_NAME"));
				applicantCollegeInfoDTO.setApplicationType(r.getString("APPLICATION_TYPE"));
				applicantCollegeInfoDTO.setPaymentStatus(r.getString("PAYMENT_STATUS"));
				applicantCollegeInfoDTO.setPriorityOrder(r.getString("PRIORITY_ORDER"));
				
				applicantCollegeInfoList.add(applicantCollegeInfoDTO);
				

			}
			/*System.out.println("applicantCollegeInfoList.size="+applicantCollegeInfoList.size());*/
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
		
		return applicantCollegeInfoList;
	}
	
	//////////////////////////
	public ApplicantInfoBoardDTO getApplicantInfo(String ssc_roll,String ssc_board,String ssc_year,String ssc_reg, String eiin){
		ApplicantInfoBoardDTO applicantInfoDTO = null;

		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="select RE.APPLICATION_ID, re.eiin, re.shift_id, re.version_id, re.group_id , bb.ROLL_NO, bb.BOARD_NAME_ENG BOARD_NAME,  "
			+" bb.PASSING_YEAR,bb.STUDENT_NAME NAME , re.MERIT_TYPE, decode(is_paid,'YES', 'CONFIRMED', 'NOT CONFIRMED' ) status, re.QUOTA_TYPE "
			+" from BOARD_RESULT re , BOARD_DATA_SSC bb  "
			+" where RE.APPLICATION_ID=bb.APPLICATION_ID " 
			+" AND bb.ROLL_NO = ? "
			+" AND bb.BOARD_ID = ? "
			+" AND bb.PASSING_YEAR =?  "
			+" AND bb.REG_NO = ?  "
			+" AND re.eiin = ?  "
			+" AND re.COLLEGE_RECEIVE = 'College Received'  "
			+" AND RE.MERIT_TYPE=( select max(MERIT_TYPE) from BOARD_RESULT x where x.APPLICATION_ID=re.APPLICATION_ID) ";
					
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,ssc_roll.trim());
			stmt.setString(2,ssc_board.trim());
			stmt.setString(3,ssc_year.trim());
			stmt.setString(4,ssc_reg.trim());
			stmt.setString(5,eiin.trim());
			

			
			r = stmt.executeQuery();
			
			
			while(r.next())
			{
				applicantInfoDTO = new ApplicantInfoBoardDTO();
				
				applicantInfoDTO.setApplicationID(r.getString("APPLICATION_ID"));
				applicantInfoDTO.setMerit(r.getString("MERIT_TYPE"));
				applicantInfoDTO.setEiinCode(r.getString("EIIN"));
				applicantInfoDTO.setShiftID(r.getString("SHIFT_ID"));
				applicantInfoDTO.setVersionID(r.getString("VERSION_ID"));
				applicantInfoDTO.setGroupId(r.getString("GROUP_ID"));

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
		
		return applicantInfoDTO;
	}
/////////////////////////////////////
	public String ChangeApplicantSVG(String application_id, String new_group_id, String user_eiin)
	{

		Connection conn = ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		String msg=null;
		try {
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call AppicantSVGchange(?,?,?,?)  }");
            
            stmt.setString(1, application_id);      
            stmt.setString(2, new_group_id);
            stmt.setString(3, user_eiin);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            
            stmt.executeUpdate();
            msg=stmt.getString(4);
            
		} 
		catch (Exception e){e.printStackTrace();
		}
		finally{try{stmt.close();
		ConnectionManager.closeConnection(conn);
		} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
        
        return msg;
	}

}
