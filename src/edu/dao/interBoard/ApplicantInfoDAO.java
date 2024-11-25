package edu.dao.interBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.struts2.ServletActionContext;


import edu.dto.application.ChoiceDTO;
import edu.dto.board.ApplicantInfoBoardDTO;
import edu.dto.interBoard.ApplicantInfoDTO;
import edu.utils.connection.ConnectionManager;

public class ApplicantInfoDAO {
	
		
	public ApplicantInfoDTO getAppInfo(String roll, String board, String pyear){
		ApplicantInfoDTO applicantInfoDTO = null;

		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="select REG_NO, STUDENT_NAME, FATHER_NAME, MOTHER_NAME, to_char(BIRTH_DATE,'DD-MM-RRRR') BIRTH_DATE, GPA, GENDER, GROUP_NAME" +			
				" from board_data_ssc " +
				" where ROLL_NO=? " +
				" and BOARD_ID=? " +
				" and PASS_YEAR=? " ;
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,roll);
			stmt.setString(2,board);
			stmt.setString(3,pyear);
			
			r = stmt.executeQuery();
			
			
			while(r.next())
			{
				applicantInfoDTO = new ApplicantInfoDTO();
				
				applicantInfoDTO.setSscRegNo(r.getString("REG_NO"));
				applicantInfoDTO.setApplicantName(r.getString("STUDENT_NAME"));
				applicantInfoDTO.setFatherName(r.getString("FATHER_NAME"));
				applicantInfoDTO.setMotherName(r.getString("MOTHER_NAME"));
				applicantInfoDTO.setDob(r.getString("BIRTH_DATE"));
				applicantInfoDTO.setGpa(r.getString("GPA"));
				applicantInfoDTO.setGender(r.getString("GENDER"));
				applicantInfoDTO.setGroupName(r.getString("GROUP_NAME"));
				
				/*applicantInfoDTO.setMobilenumber(r.getString("CONTACT_NO"));
				applicantInfoDTO.setScode(r.getString("SCODE"));*/

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
	
	
	
	public ApplicantInfoDTO getPaymentInfo(String transId, String oprator){
		ApplicantInfoDTO applicantInfoDTO = null;

		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="select ROLL_NO,BOARD_NAME,PASS_YEAR,CONTACT_NO,PAY_TIME from PAYMENT_DETAILS aa, mst_edu_board bb " +
				" where aa.BOARD_ID=bb.BOARD_ID " +
				" and upper(aa.TRANS_ID)=? " +
				" and aa.PAY_COMP=? " ;
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,transId);
			stmt.setString(2,oprator);
						
			r = stmt.executeQuery();
			
			
			while(r.next())
			{
				applicantInfoDTO = new ApplicantInfoDTO();
				
				applicantInfoDTO.setSscRollNo(r.getString("ROLL_NO"));
				applicantInfoDTO.setBoardName(r.getString("BOARD_NAME"));
				applicantInfoDTO.setSscPassingYear(r.getString("PASS_YEAR"));				
				applicantInfoDTO.setMobilenumber(r.getString("CONTACT_NO"));
				applicantInfoDTO.setDob(r.getString("PAY_TIME"));		

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
	
	
	
	
	public ApplicantInfoDTO getAppInfoRank(String roll, String board, String pyear){
		ApplicantInfoDTO applicantInfoDTO = null;

		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="select ss.REG_NO, ss.STUDENT_NAME, ss.FATHER_NAME, ss.MOTHER_NAME, ss.SRANK, ss.ARANK, ss.SSC_EIIN,CONTACT_NO, SCODE " +
				" from board_data_ssc ss, APPLICANT_INFO ii " +
				" where ss.ROLL_NO=II.ROLL_NO  " +
				" and ss.BOARD_ID=II.BOARD_ID  " +
				" and ss.PASS_YEAR=II.PASS_YEAR  " +
				" and ss.ROLL_NO=?  " +
				" and ss.BOARD_ID=?  " +
				" and ss.PASS_YEAR=?  "  ;
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,roll);
			stmt.setString(2,board);
			stmt.setString(3,pyear);
			
			r = stmt.executeQuery();
			
			
			while(r.next())
			{
				applicantInfoDTO = new ApplicantInfoDTO();
				
				applicantInfoDTO.setSscRegNo(r.getString("REG_NO"));
				applicantInfoDTO.setApplicantName(r.getString("STUDENT_NAME"));
				applicantInfoDTO.setFatherName(r.getString("FATHER_NAME"));
				applicantInfoDTO.setMotherName(r.getString("MOTHER_NAME"));				
				applicantInfoDTO.setEiinCode(r.getString("SSC_EIIN"));
				applicantInfoDTO.setSrank(r.getString("SRANK"));
				applicantInfoDTO.setArank(r.getString("ARANK"));
				
				applicantInfoDTO.setMobilenumber(r.getString("CONTACT_NO"));
				applicantInfoDTO.setScode(r.getString("SCODE"));

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
	
	
	public ApplicantInfoDTO getAppInfoSC(String roll, String board, String pyear){
		ApplicantInfoDTO applicantInfoDTO = null;

		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="select pi.CONTACT_NO, pd.PAY_COMP||'-'||pd.TRANS_ID TRANS_ID, pi.SCODE," +
				" (select count(*) from APPLICATION_COLLEGES where APPLICANT_ID=pi.APPLICANT_ID) cnt " +
				" from APPLICANT_INFO pi, PAYMENT_DETAILS pd " +
				" where pi.ROLL_NO=pd.ROLL_NO " +
				" and pi.BOARD_ID=pd.BOARD_ID " +
				" and pi.PASS_YEAR=pd.PASS_YEAR " +
				" and pd.IS_ACTIVE='Y' " +
				" and pi.ROLL_NO=? " +
				" and pi.BOARD_ID=? " +
				" and pi.PASS_YEAR=? "  ;
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,roll);
			stmt.setString(2,board);
			stmt.setString(3,pyear);
			
			r = stmt.executeQuery();
			
			
			while(r.next())
			{
				applicantInfoDTO = new ApplicantInfoDTO();
				
								
			applicantInfoDTO.setMobilenumber(r.getString("CONTACT_NO"));
				applicantInfoDTO.setScode(r.getString("SCODE"));
				applicantInfoDTO.setPaymentStatus(r.getString("TRANS_ID"));
				applicantInfoDTO.setAppCnt(r.getInt("cnt"));
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
	
	
	
	
	public ApplicantInfoBoardDTO getApplicantBasicInfo(String applicationID){
		ApplicantInfoBoardDTO applicantInfoDTO = null;

		
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
				applicantInfoDTO = new ApplicantInfoBoardDTO();
				
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
	
	
	
	
	public ArrayList<ChoiceDTO> getSvgInfo(String roll, String board, String pyear){
		ArrayList<ChoiceDTO> svgList = new ArrayList<ChoiceDTO>();
		ChoiceDTO tmpNew = null;		
		Connection conn = ConnectionManager.getReadConnection();
		
		String appid =board+pyear+roll;
		
		/*String sql ="select CC.STUDENT_NAME, aa.COLLEGE_NAME,decode(aa.SHIFT_ID,1,'Morning',2,'Day','Evening') shift,  " +
				" decode(aa.version_id, 1, 'Bangla', 2, 'English') version, decode(aa.group_id, 0, 'Sci', 2, 'Hum', 8, 'Business','Others') group_id,  " +
				" BB.PRIORITY, AA.MAX_GEN, AA.MAX_GEN_TOTAL, CC.MERIT_POS1, BB.EIIN, CC.SSC_EIIN " +
				" from ESVG_ALL_STATS aa, APPLICATION_COLLEGES bb, board_data_ssc cc " +
				" where AA.EIIN=BB.EIIN " +
				" and AA.SHIFT_ID=BB.SHIFT_ID " +
				" and AA.VERSION_ID=BB.VERSION_ID " +
				" and AA.GROUP_ID=BB.GROUP_ID " +
				" and BB.APPLICANT_ID=CC.BOARD_ID||CC.PASS_YEAR||CC.ROLL_NO " +
				" and BB.APPLICANT_ID=? " +
				" order by PRIORITY "   ;*/
		
		
		String sql="select CC.STUDENT_NAME, aa.COLLEGE_NAME,decode(bb.SHIFT_ID,1,'Morning',2,'Day',11,'Morning',12,'Day','Evening') shift,   " +
				" decode(bb.version_id, 1, 'Bangla', 2, 'English') version, decode(bb.group_id, 0, 'Sci', 2, 'Hum', 8, 'Business','Others') group_id,   " +
				" BB.PRIORITY,  CC.MERIT_POS1, BB.EIIN, CC.SSC_EIIN  " +
				" from mst_college aa, APPLICATION_COLLEGES bb, board_data_ssc cc  " +
				" where AA.EIIN=BB.EIIN  " +
				" and BB.APPLICANT_ID=CC.BOARD_ID||CC.PASS_YEAR||CC.ROLL_NO  " +
				" and BB.APPLICANT_ID=? " +
				" order by PRIORITY  " ;

		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,appid);
			
			
			r = stmt.executeQuery();
			
			
			while(r.next())
			{
				
				tmpNew = new ChoiceDTO();		
				tmpNew.setCollege_name(r.getString("COLLEGE_NAME"));
				tmpNew.setEiin(r.getString("EIIN"));
				tmpNew.setShift_id(r.getString("shift"));
				tmpNew.setVersion_id(r.getString("version"));
				tmpNew.setGroup_id(r.getString("group_id"));
				tmpNew.setPriority(r.getString("PRIORITY"));				
				//tmpNew.setMaxrank(r.getString("MAX_GEN"));
				svgList.add(tmpNew);
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
		
		return svgList;
	}
	
	
	
	
	
	public List<ApplicantInfoBoardDTO> getApplicantBasicInfoNotSend(){
		List<ApplicantInfoBoardDTO> listAppliDTO = new ArrayList<ApplicantInfoBoardDTO>();
		ApplicantInfoBoardDTO tmpNew= new ApplicantInfoBoardDTO();
		Connection conn = ConnectionManager.getReadConnection();		
		String sql ="SELECT distinct AI.APPLICATION_ID,AI.SSC_ROLL_NO,AI.SSC_BOARD_ID,AI.SSC_PASSING_YEAR,BS.GENDER," +
				"   BS.STUDENT_NAME,AI.MOBILE_NUMBER FROM application_info ai, application_colleges ac, BOARD_DATA_SSC bs" +
				"   WHERE     AC.TTSEND = 'N'" +
				"      AND AC.PAYMENT_STATUS = 'N'" +
				"      AND AI.APPLICATION_ID = AC.APPLICATION_ID" +
				"      AND AI.SSC_ROLL_NO = BS.ROLL_NO" +
				"      AND AI.SSC_BOARD_ID = BS.BOARD_ID" +
				"       AND AI.SSC_PASSING_YEAR = BS.PASSING_YEAR";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while(r.next())
			{
				tmpNew = new ApplicantInfoBoardDTO();		
				tmpNew.setApplicationID(r.getString("APPLICATION_ID"));
				tmpNew.setApplicantName(r.getString("STUDENT_NAME"));
				tmpNew.setSscRollNo(r.getString("SSC_ROLL_NO"));
				tmpNew.setBoardID(r.getString("SSC_BOARD_ID"));
				tmpNew.setSscPassingYear(r.getString("SSC_PASSING_YEAR"));
				tmpNew.setGender(r.getString("GENDER"));
				tmpNew.setMobilenumber(r.getString("MOBILE_NUMBER"));
				listAppliDTO.add(tmpNew);
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
		
		return listAppliDTO;
	}
	public List<ApplicantInfoBoardDTO> getApplicantBasicInfoNotSend(String app_id){
		List<ApplicantInfoBoardDTO> listAppliDTO = new ArrayList<ApplicantInfoBoardDTO>();
		ApplicantInfoBoardDTO tmpNew= new ApplicantInfoBoardDTO();
		Connection conn = ConnectionManager.getReadConnection();		
		String sql ="SELECT distinct AI.APPLICATION_ID,AI.SSC_ROLL_NO,AI.SSC_BOARD_ID,AI.SSC_PASSING_YEAR,BS.GENDER," +
				"   BS.STUDENT_NAME,AI.MOBILE_NUMBER FROM application_info ai, application_colleges ac, BOARD_DATA_SSC bs" +
				"   WHERE     AC.TTSEND = 'N' and AI.APPLICATION_ID='" + app_id +"' " +
				"      AND AC.PAYMENT_STATUS = 'N'" +
				"      AND AI.APPLICATION_ID = AC.APPLICATION_ID" +
				"      AND AI.SSC_ROLL_NO = BS.ROLL_NO" +
				"      AND AI.SSC_BOARD_ID = BS.BOARD_ID" +
				"       AND AI.SSC_PASSING_YEAR = BS.PASSING_YEAR";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while(r.next())
			{
				tmpNew = new ApplicantInfoBoardDTO();		
				tmpNew.setApplicationID(r.getString("APPLICATION_ID"));
				tmpNew.setApplicantName(r.getString("STUDENT_NAME"));
				tmpNew.setSscRollNo(r.getString("SSC_ROLL_NO"));
				tmpNew.setBoardID(r.getString("SSC_BOARD_ID"));
				tmpNew.setSscPassingYear(r.getString("SSC_PASSING_YEAR"));
				tmpNew.setGender(r.getString("GENDER"));
				tmpNew.setMobilenumber(r.getString("MOBILE_NUMBER"));
				listAppliDTO.add(tmpNew);
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
		
		return listAppliDTO;
	}
	public List<ApplicantInfoBoardDTO> getAppBasicInfoNotSendFT(){
		List<ApplicantInfoBoardDTO> listAppliDTO = new ArrayList<ApplicantInfoBoardDTO>();
		ApplicantInfoBoardDTO tmpNew= new ApplicantInfoBoardDTO();
		Connection conn = ConnectionManager.getReadConnection();		
		String sql ="SELECT distinct AI.APPLICATION_ID,AI.SSC_ROLL_NO,AI.SSC_BOARD_ID,AI.SSC_PASSING_YEAR,BS.GENDER," +
				"   BS.STUDENT_NAME,AI.MOBILE_NUMBER FROM application_info ai, application_colleges ac, BOARD_DATA_SSC bs" +
				"   WHERE     AC.TTSEND = 'N'" +
				"      AND AC.PAYMENT_STATUS = 'N'  and AI.APPLICATION_ID in (select APP_ID from TT_NOT_RECEIVED_08_06) " +
				"      AND AI.APPLICATION_ID = AC.APPLICATION_ID" +
				"      AND AI.SSC_ROLL_NO = BS.ROLL_NO" +
				"      AND AI.SSC_BOARD_ID = BS.BOARD_ID" +
				"       AND AI.SSC_PASSING_YEAR = BS.PASSING_YEAR";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while(r.next())
			{
				tmpNew = new ApplicantInfoBoardDTO();		
				tmpNew.setApplicationID(r.getString("APPLICATION_ID"));
				tmpNew.setApplicantName(r.getString("STUDENT_NAME"));
				tmpNew.setSscRollNo(r.getString("SSC_ROLL_NO"));
				tmpNew.setBoardID(r.getString("SSC_BOARD_ID"));
				tmpNew.setSscPassingYear(r.getString("SSC_PASSING_YEAR"));
				tmpNew.setGender(r.getString("GENDER"));
				tmpNew.setMobilenumber(r.getString("MOBILE_NUMBER"));
				listAppliDTO.add(tmpNew);
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
		
		return listAppliDTO;
	}	
	public List<ChoiceDTO> getChoiceDTO(String appid)
	{
		List<ChoiceDTO> cdto = new ArrayList<ChoiceDTO>();
		ChoiceDTO tmpNew= new ChoiceDTO();
		Connection conn = ConnectionManager.getReadConnection();		
		String sql ="SELECT AI.APPLICATION_ID,AC.EIIN,AC.SHIFT_ID,AC.VERSION_ID,AC.GROUP_ID,AC.SPECIAL_QUOTA,AC.PRIORITY_ORDER" +
				"  FROM application_info ai, application_colleges ac, BOARD_DATA_SSC bs" +
				" WHERE     AC.TTSEND = 'N'" +
				"       AND AC.PAYMENT_STATUS = 'N' and AI.APPLICATION_ID=?" +
				"       AND AI.APPLICATION_ID = AC.APPLICATION_ID" +
				"       AND AI.SSC_ROLL_NO = BS.ROLL_NO" +
				"       AND AI.SSC_BOARD_ID = BS.BOARD_ID" +
				"       AND AI.SSC_PASSING_YEAR = BS.PASSING_YEAR";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, appid);
			r = stmt.executeQuery();
			while(r.next())
			{
				tmpNew = new ChoiceDTO();		
				tmpNew.setApplication_id(r.getString("APPLICATION_ID"));
				tmpNew.setEiin(r.getString("EIIN"));
				tmpNew.setShift_id(r.getString("SHIFT_ID"));
				tmpNew.setVersion_id(r.getString("VERSION_ID"));
				tmpNew.setGroup_id(r.getString("GROUP_ID"));
				tmpNew.setPriority(r.getString("PRIORITY_ORDER"));
				tmpNew.setSpecial_quota(r.getString("SPECIAL_QUOTA"));
				cdto.add(tmpNew);
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
		
		return cdto;
		
	}
	public List<ApplicantInfoBoardDTO> getApplicantCollegeInfoList(String applicationID,String eiinCode){
		ApplicantInfoBoardDTO applicantCollegeInfoDTO = null;
		List<ApplicantInfoBoardDTO> applicantCollegeInfoList = null;
		
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
			
			stmt.setString(1, applicationID);
			stmt.setString(2, eiinCode);
			
			r = stmt.executeQuery();
			
			applicantCollegeInfoList = new ArrayList<ApplicantInfoBoardDTO>();
			while(r.next())
			{
				applicantCollegeInfoDTO = new ApplicantInfoBoardDTO();
				
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
			System.out.println("applicantCollegeInfoList.size="+applicantCollegeInfoList.size());
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

}
