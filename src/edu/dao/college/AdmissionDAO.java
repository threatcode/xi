package edu.dao.college;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import oracle.jdbc.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import edu.dto.IpAddressDTO;
import edu.dto.application.SscInfoDTO;
import edu.dto.board.ApplicantInfoBoardDTO;
import edu.dto.college.ApplicantInfoDTO;
import edu.dto.college.CollegeCourseDTO;
import edu.dto.college.CollegeDTO;
import edu.dto.college.CollegeSubject;
import edu.dto.college.ResultDTO;
import edu.utils.connection.ConnectionManager;

public class AdmissionDAO {
	
	
	public ApplicantInfoDTO getSeatInfo(String shift_id,String version_id,String group_id,String eiin){
		ApplicantInfoDTO seatInfo = null;

		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="SELECT TOTAL_SEAT,\n" +
		         "       AVAILABLE_SEAT\n" +
		         "  FROM MST_COLLEGE_GROUPS \n" +
		         " WHERE     EIIN = ?\n" +
		         "       AND SHIFT_ID=?\n" +
		         "       AND VERSION_ID=?\n" +
		         "       AND GROUP_ID =?";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,eiin);
			stmt.setString(2,shift_id);
			stmt.setString(3,version_id);
			stmt.setString(4,group_id);
			
			r = stmt.executeQuery();
			String totalSeat=" ";
			String availableSeat=" ";
			
			while(r.next())
			{
				seatInfo = new ApplicantInfoDTO();
				totalSeat=r.getString("TOTAL_SEAT");
				availableSeat=r.getString("AVAILABLE_SEAT");
				
				seatInfo.setTotalSeat(totalSeat);
				seatInfo.setAvailableSeat(availableSeat);



			}
			
			if(totalSeat==null || totalSeat=="")
			{
				totalSeat="No available data";
				seatInfo.setTotalSeat(totalSeat);
			}
			if(availableSeat==null||availableSeat=="")
			{
				availableSeat="No available data";
				seatInfo.setAvailableSeat(availableSeat);
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
		
		return seatInfo;
	}
	
	
	public List<ApplicantInfoDTO> getNonApprovedStudentListOfMerit1(String shift_id,String version_id,String group_id,
		String merit,String userId,String pRoll){
		ApplicantInfoDTO nonApprovedStudentOfMeritDTO = null;
		List<ApplicantInfoDTO> nonApprovedStudentListOfMerit = null;
		
		Connection conn = ConnectionManager.getWriteConnection();
		
		
		String sql ="SELECT A.EIIN," +
				"       A.APPLICATION_ID," +
				"       B.SHIFT_ID," +
				"       B.SHIFT_NAME," +
				"       C.VERSION_ID," +
				"       C.VERSION_NAME," +
				"       D.GROUP_ID," +
				"       D.GROUP_NAME," +
				"       A.MERIT_TYPE," +
				"       A.MERIT_POSITTION," +
				"       A.COLLEGE_RECEIVE," +
				"       QUOTA_TYPE,E.NAME," +
				"       E.SSC_ROLL_NO," +
				"       E.SSC_BOARD_ID," +
				"       E.BOARD_NAME," +
				"       E.SSC_PASSING_YEAR" +
				"  FROM BOARD_RESULT a," +
				"       MST_SHIFT b," +
				"       MST_VERSION c," +
				"       MST_GROUP d," +
				"       APPLICATION_INFO e" +
				" WHERE     A.EIIN = ? AND COLLEGE_RECEIVE = 'Not Received'" +
				"       AND a.SHIFT_ID = ?" +
				"       AND A.VERSION_ID = ?" +
				"       AND A.GROUP_ID = ?" +
				"       AND a.SHIFT_ID = b.SHIFT_ID" +
				"       AND A.VERSION_ID = C.VERSION_ID" +
				"       AND A.GROUP_ID = D.GROUP_ID" +
				"       AND A.APPLICATION_ID = E.APPLICATION_ID ";
		if(pRoll!=null)
			sql += "	AND E.SSC_ROLL_NO = ? ";

        sql += " ORDER BY A.MERIT_TYPE,B.SHIFT_NAME," +
                "         C.VERSION_NAME," +
                "         D.GROUP_NAME," +
                "         decode(A.QUOTA_TYPE ,'OWN','0','GEN','1','SQ','2','FFQ','3','DQ','4','EQ','5','BKSP','6','FRQ','7',A.QUOTA_TYPE)," +
                "         A.APPLICATION_ID";
		
		
//		System.out.println("delwar:"+sql);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setString(2, shift_id);
			stmt.setString(3, version_id);
			stmt.setString(4, group_id);
			if(pRoll!=null)
				stmt.setString(5, pRoll);
			r = stmt.executeQuery();
			
			nonApprovedStudentListOfMerit = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				nonApprovedStudentOfMeritDTO = new ApplicantInfoDTO();
				
				nonApprovedStudentOfMeritDTO.setEiinCode(r.getString("EIIN"));
				nonApprovedStudentOfMeritDTO.setApplicationID(r.getString("APPLICATION_ID"));
				
				nonApprovedStudentOfMeritDTO.setShiftID(r.getString("SHIFT_ID"));
				nonApprovedStudentOfMeritDTO.setShiftName(r.getString("SHIFT_NAME"));
				
				nonApprovedStudentOfMeritDTO.setVersionID(r.getString("VERSION_ID"));
				nonApprovedStudentOfMeritDTO.setVersionName(r.getString("VERSION_NAME"));
				
				nonApprovedStudentOfMeritDTO.setGroupId(r.getString("GROUP_ID"));
				nonApprovedStudentOfMeritDTO.setGroupName(r.getString("GROUP_NAME"));
				
				nonApprovedStudentOfMeritDTO.setMeritType(r.getString("MERIT_TYPE"));
				nonApprovedStudentOfMeritDTO.setPriorityOrder(r.getString("MERIT_POSITTION"));
				nonApprovedStudentOfMeritDTO.setAdmitStatus(r.getString("COLLEGE_RECEIVE"));
				nonApprovedStudentOfMeritDTO.setAssignedQuota(r.getString("QUOTA_TYPE"));
		//		nonApprovedStudentOfMeritDTO.setMobileNo(r.getString("MOBILE_NUMBER"));
				
				nonApprovedStudentOfMeritDTO.setApplicantName(r.getString("NAME"));
				nonApprovedStudentOfMeritDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				nonApprovedStudentOfMeritDTO.setBoardID(r.getString("SSC_BOARD_ID"));
				nonApprovedStudentOfMeritDTO.setBoardName(r.getString("BOARD_NAME"));
				nonApprovedStudentOfMeritDTO.setSscPassingYear(r.getString("SSC_PASSING_YEAR"));
				
				
				nonApprovedStudentListOfMerit.add(nonApprovedStudentOfMeritDTO);
				

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
		
		return nonApprovedStudentListOfMerit;
	}	
	public List<ApplicantInfoDTO> getNonApprovedStudentListOfMerit1(String shift_id,String version_id,String group_id,String userId){
		ApplicantInfoDTO nonApprovedStudentOfMeritDTO = null;
		List<ApplicantInfoDTO> nonApprovedStudentListOfMerit = null;
		
		Connection conn = ConnectionManager.getWriteConnection();
		
		
		String sql ="  SELECT T2.STUDENT_NAME," +
				"         T1.APPLICANT_ID," +
				"         T1.MERIT_TYPE," +
				"         0 MERIT_POSITTION," +
				"         T2.ROLL_NO," +
				"         T2.BOARD_ID," +
				"         T2.PASS_YEAR," +
				"         T2.REG_NO," +
				"         T1.EIIN," +
				"         T1.SHIFT_ID," +
				"         T1.VERSION_ID," +
				"         T1.GROUP_ID," +
				"         t1.QUOTA_TYPE," +
				"         t1.AUTO_MIGRATION," +
				"         t1.status," +
				"         DECODE (t1.status, 'MIGRATED', 'YES', t1.is_paid) is_paid," +
				"         college_receive," +
				"         T3.SHIFT_NAME," +
				"         T4.VERSION_NAME," +
				"         T5.BOARD_NAME," +
				"         t6.group_name" +
				"    FROM (SELECT *" +
				"            FROM (SELECT *" +
				"                    FROM BOARD_RESULT" +
				"                   WHERE (APPLICANT_ID, merit_type) IN" +
				"                            (  SELECT APPLICANT_ID, MAX (merit_type)" +
				"                                 FROM BOARD_RESULT" +
				"                             GROUP BY APPLICANT_ID))" +
				"           WHERE     eiin = ?" +
				"                 AND SHIFT_ID = ?" +
				"                 AND VERSION_ID = ?" +
				"                 AND GROUP_ID = ?" +
				"                 AND COLLEGE_RECEIVE = 'Not Received') t1," +
				"         board_data_ssc t2," +
				"         mst_shift t3," +
				"         mst_version t4," +
				"         mst_edu_board t5," +
				"         mst_group t6" +
				"   WHERE     substr(t1.applicant_id,1,2)=t2.board_id" +
				"            AND substr(t1.applicant_id,3,4)=t2.pass_year" +
				"            AND substr(t1.applicant_id,7)=t2.roll_no" +
				"         AND T1.SHIFT_ID = T3.SHIFT_ID" +
				"         AND T1.VERSION_ID = T4.VERSION_ID" +
				"         AND T2.BOARD_ID = T5.BOARD_ID" +
				"         AND t1.GROUP_ID = t6.GROUP_ID" +
				" ORDER BY DECODE (t1.QUOTA_TYPE," +
				"                 'OWN', '0'," +
				"                 'GEN', '1'," +
				"                 'SQ', '2'," +
				"                 'FFQ', '3'," +
				"                 'DQ', '4'," +
				"                 'EQ', '5'," +
				"                 'BKSP', '6'," +
				"                 'FRQ', '7'," +
				"                 t1.QUOTA_TYPE)";
		//		System.out.println("delwar:"+sql);
        
        
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setString(2, shift_id);
			stmt.setString(3, version_id);
			stmt.setString(4, group_id);
			r = stmt.executeQuery();
			
			nonApprovedStudentListOfMerit = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				nonApprovedStudentOfMeritDTO = new ApplicantInfoDTO();
				
				nonApprovedStudentOfMeritDTO.setEiinCode(r.getString("EIIN"));
				nonApprovedStudentOfMeritDTO.setApplicationID(r.getString("APPLICANT_ID"));
				
				nonApprovedStudentOfMeritDTO.setShiftID(r.getString("SHIFT_ID"));
				nonApprovedStudentOfMeritDTO.setShiftName(r.getString("SHIFT_NAME"));
				
				nonApprovedStudentOfMeritDTO.setVersionID(r.getString("VERSION_ID"));
				nonApprovedStudentOfMeritDTO.setVersionName(r.getString("VERSION_NAME"));
				
				nonApprovedStudentOfMeritDTO.setGroupId(r.getString("GROUP_ID"));
				nonApprovedStudentOfMeritDTO.setGroupName(r.getString("GROUP_NAME"));
				
				nonApprovedStudentOfMeritDTO.setMeritType(r.getString("MERIT_TYPE"));
				nonApprovedStudentOfMeritDTO.setPriorityOrder(r.getString("MERIT_POSITTION"));
				nonApprovedStudentOfMeritDTO.setAdmitStatus(r.getString("STATUS"));
				nonApprovedStudentOfMeritDTO.setAssignedQuota(r.getString("QUOTA_TYPE"));
//				nonApprovedStudentOfMeritDTO.setPinNumber(r.getString("PIN_NUMBER"));
		//		nonApprovedStudentOfMeritDTO.setMobileNo(r.getString("MOBILE_NUMBER"));
				
				nonApprovedStudentOfMeritDTO.setApplicantName(r.getString("STUDENT_NAME"));
				
				nonApprovedStudentOfMeritDTO.setSscRollNo(r.getString("ROLL_NO"));
				nonApprovedStudentOfMeritDTO.setBoardID(r.getString("BOARD_ID"));
				nonApprovedStudentOfMeritDTO.setBoardName(r.getString("BOARD_NAME"));
				nonApprovedStudentOfMeritDTO.setSscPassingYear(r.getString("PASS_YEAR"));
				nonApprovedStudentOfMeritDTO.setIspaid(r.getString("IS_PAID"));
				
				
				nonApprovedStudentListOfMerit.add(nonApprovedStudentOfMeritDTO);
				

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
		
		return nonApprovedStudentListOfMerit;
	}	
	
	public List<ApplicantInfoDTO> getAlreadyAddred(int shift_id,int version_id,int group_id,String userId){
		ApplicantInfoDTO alreadyAddedDTO = null;
		List<ApplicantInfoDTO> lstAlreadyAdded = null;
		
		Connection conn = ConnectionManager.getWriteConnection();
		
		
		String sql ="  SELECT t1.*, student_name, t3.board_name" +
				"    FROM college3dataentry t1, board_data_ssc t2, mst_edu_board t3" +
				"   WHERE     t1.roll_no = t2.roll_no" +
				"         AND t1.board_id = t2.board_id" +
				"         AND t1.passing_year = t2.pass_year" +
				"         AND t1.board_id = t3.board_id" +
				"         AND t1.eiin = ?" +
				"         AND shift_id = ?" +
				"         AND version_id = ?" +
				"         AND t1.GROUP_ID = ?" +
				" ORDER BY t3.board_name, t1.roll_no";
		
		
//		System.out.println("delwar:"+sql);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setInt(2, shift_id);
			stmt.setInt(3, version_id);
			stmt.setInt(4, group_id);
			r = stmt.executeQuery();
			
			lstAlreadyAdded = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				alreadyAddedDTO = new ApplicantInfoDTO();
				
				alreadyAddedDTO.setEiinCode(r.getString("EIIN"));
				alreadyAddedDTO.setShiftID(r.getString("SHIFT_ID"));
//				alreadyAddedDTO.setShiftName(r.getString("SHIFT_NAME"));
				alreadyAddedDTO.setVersionID(r.getString("VERSION_ID"));
//				alreadyAddedDTO.setVersionName(r.getString("VERSION_NAME"));
				alreadyAddedDTO.setGroupId(r.getString("GROUP_ID"));
//				alreadyAddedDTO.setGroupName(r.getString("GROUP_NAME"));
				alreadyAddedDTO.setApplicantName(r.getString("STUDENT_NAME"));
				alreadyAddedDTO.setSscRollNo(r.getString("ROLL_NO"));
				alreadyAddedDTO.setBoardID(r.getString("BOARD_ID"));
				alreadyAddedDTO.setBoardName(r.getString("board_name"));
				alreadyAddedDTO.setSscPassingYear(r.getString("PASSING_YEAR"));
				alreadyAddedDTO.setApplicationID(r.getString("APPID"));
				lstAlreadyAdded.add(alreadyAddedDTO);
				

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
		
		return lstAlreadyAdded;
	}	
	
	public SscInfoDTO showStudent(String roll_no, int board_id, int passing_year, String reg_no){
		SscInfoDTO studentDTO = null;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="select * from board_data_ssc where roll_no=? and board_id=? and pass_year=? and reg_no=?";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roll_no);
			stmt.setInt(2, board_id);
			stmt.setInt(3, passing_year);
			stmt.setString(4, reg_no);
			r = stmt.executeQuery();
			while(r.next())
			{
				studentDTO = new SscInfoDTO();
//				studentDTO.setRoll(r.getString("EIIN"));
//				studentDTO.setReg_no(r.getString("SHIFT_ID"));
//				studentDTO.setPassing_year(r.getString("VERSION_ID"));
				studentDTO.setBoard_id(String.valueOf(board_id));
				studentDTO.setBoard_name(r.getString("BOARD_NAME"));
				studentDTO.setStudent_name(r.getString("STUDENT_NAME"));
				studentDTO.setFather_name(r.getString("FATHER_NAME"));
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
		
		return studentDTO;
	}	
	
	public int addStudent(int shift_id,int version_id,int group_id,String userId,String roll_no, int board_id, int passing_year) 
	{
		int tmp = 0;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="insert into college3dataentry (EIIN, SHIFT_ID, VERSION_ID, GROUP_ID, ROLL_NO, BOARD_ID, PASSING_YEAR, APPID) values " +
				"(?,?,?,?,?,?,?,"+board_id+""+passing_year+roll_no+")";//SQN_MANUAL_ENTRY.nextval)";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, userId);
			stmt.setInt(parameterIndex++, shift_id);
			stmt.setInt(parameterIndex++, version_id);
			stmt.setInt(parameterIndex++, group_id);
			stmt.setString(parameterIndex++, roll_no);
			stmt.setInt(parameterIndex++, board_id);
			stmt.setInt(parameterIndex++, passing_year);
			tmp = stmt.executeUpdate();
		} 
		catch (Exception e){
//			e.printStackTrace();
			if(e.getMessage().contains("COLLEGE3DATAENTRY_UQ1"))
			{
				tmp = 123;
				System.out.println("COLLEGE3DATAENTRY_UQ1");
			}
			else if(e.getMessage().contains("THIS APPLICANT HAS ANOTHER COLLEGE APPROVAL!!!"))
			{
				tmp = 124;
			}
			
		}
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
		
		return tmp;
	}	
	public int addStudentBoard(String eiin, int shift_id,int version_id,int group_id,String userId,String roll_no, int board_id, int passing_year
			, String mnumber) 
	{
		int tmp = 0;
			int checkAdm = checkAdmitted(eiin,shift_id,version_id,group_id);
			if(checkAdm<=0 && !(checkAdm==-9999 || checkAdm==-8888))
			{
				tmp = 125;
				return tmp;
			}
			else if(checkAdm==-9999)
			{
				tmp = 126;
				return tmp;
			}
			int checkGen = checGender(eiin, shift_id, version_id, group_id, roll_no, board_id, passing_year) ;
			if(checkGen!=1)
			{
				tmp = 127;
				return tmp;
			}
		
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="insert into college3dataentry (EIIN, SHIFT_ID, VERSION_ID, GROUP_ID, ROLL_NO, BOARD_ID, PASSING_YEAR, APPID, mnumber, userId) values (?,?,?,?,?,?,?,SQN_MANUAL_ENTRY.nextval,?,?)";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, eiin);
			stmt.setInt(parameterIndex++, shift_id);
			stmt.setInt(parameterIndex++, version_id);
			stmt.setInt(parameterIndex++, group_id);
			stmt.setString(parameterIndex++, roll_no);
			stmt.setInt(parameterIndex++, board_id);
			stmt.setInt(parameterIndex++, passing_year);
			stmt.setString(parameterIndex++, mnumber);
			stmt.setString(parameterIndex++, userId);
			tmp = stmt.executeUpdate();
		} 
		catch (Exception e){
			e.printStackTrace();
			if(e.getMessage().contains("COLLEGE3DATAENTRY_UQ1"))
			{
				tmp = 123;
				return tmp;
			}
			else if(e.getMessage().contains("THIS APPLICANT HAS ANOTHER COLLEGE APPROVAL!!!"))
			{
				tmp = 124;
				return tmp;
			}
			
		}
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
		
		return tmp;
	}
	public int checkAdmitted(String eiin, int shift_id, int version_id, int group_id)
	{
		int tmp = 0;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="select T1.TOTAL_SEAT-(admi+(select count(*) from COLLEGE3DATAENTRY where eiin=? and shift_id=? and version_id=? and group_id=?)) " +
				" restseat from mst_college_groups t1,(select eiin,shift_id,version_id,group_id,count(*) admi from " +
				" board_result where eiin=? and shift_id=? and version_id=? and group_id=? and COLLEGE_RECEIVE='College Received'" +
				" group by eiin,shift_id,version_id,group_id) t2 where t1.eiin=t2.eiin and t1.shift_id=t2.shift_id and t1.version_id=t2.version_id " +
				" and t1.group_id=t2.group_id";
		String sql1 = "select count(*) counts from board_result where eiin=? and shift_id=? and version_id=? and group_id=?";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, eiin);
			stmt.setInt(parameterIndex++, shift_id);
			stmt.setInt(parameterIndex++, version_id);
			stmt.setInt(parameterIndex++, group_id);
			stmt.setString(parameterIndex++, eiin);
			stmt.setInt(parameterIndex++, shift_id);
			stmt.setInt(parameterIndex++, version_id);
			stmt.setInt(parameterIndex++, group_id);
			r = stmt.executeQuery();
			if(r.next())
				tmp = r.getInt("restseat");
			else
			{
				stmt = conn.prepareStatement(sql1);
				stmt.setString(parameterIndex++, eiin);
				stmt.setInt(parameterIndex++, shift_id);
				stmt.setInt(parameterIndex++, version_id);
				stmt.setInt(parameterIndex++, group_id);
				r = stmt.executeQuery();
				if(r.next())
				{
					if(r.getInt("counts")==0)
						tmp = -8888;
					else
						tmp = -9999;
				}
				else
					tmp = -9999;
			}
				
		} 
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try{r.close();
				stmt.close();
				conn.close();
				} 
			catch (Exception e){
					e.printStackTrace();
				}
			r =null;
			stmt = null;
			conn = null;
		}
		return tmp;
	}	
	public int checGender(String eiin, int shift_id,int version_id,int group_id, String roll_no, int board_id, int passing_year)
	{
		int tmp = 0;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="select substr(t1.gender,1,1) appgender,t2.gender cgender from board_data_ssc t1,mst_college_groups t2 where " +
				" roll_no=? and board_id=? and passing_year=? and t2.eiin=? and shift_id=? and version_id=? and t2.group_id=?";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, roll_no);
			stmt.setInt(parameterIndex++, board_id);
			stmt.setInt(parameterIndex++, passing_year);
			stmt.setString(parameterIndex++, eiin);
			stmt.setInt(parameterIndex++, shift_id);
			stmt.setInt(parameterIndex++, version_id);
			stmt.setInt(parameterIndex++, group_id);
			r = stmt.executeQuery();
			if(r.next())
			{
				if (r.getString("cgender").equalsIgnoreCase("C"))
					tmp=1;
				else if (r.getString("cgender").equalsIgnoreCase("M") && r.getString("appgender").equalsIgnoreCase("M"))
					tmp=1;
				else if (r.getString("cgender").equalsIgnoreCase("F") && r.getString("appgender").equalsIgnoreCase("F"))
					tmp=1;
			}
			else
			{
				tmp = -9999;
			}
				
		} 
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try{r.close();
				stmt.close();
				conn.close();
				} 
			catch (Exception e){
					e.printStackTrace();
				}
			r =null;
			stmt = null;
			conn = null;
		}
		return tmp;
	}
	
	public int checAdmission(String roll_no, int board_id, int passing_year)
	{
		int tmp = 0;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="select count(*) cnt from board_result where applicant_id=?";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			int parameterIndex = 1;
//System.out.println(board_id+""+passing_year+roll_no);			
			stmt.setString(parameterIndex++, board_id+""+passing_year+roll_no);
			r = stmt.executeQuery();
			if(r.next())
			{
				if (r.getInt("cnt")>0)
					tmp=1;
			}
				
		} 
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try{r.close();
				stmt.close();
				conn.close();
				} 
			catch (Exception e){
					e.printStackTrace();
				}
			r =null;
			stmt = null;
			conn = null;
		}
		return tmp;
	}
	public String getCollegeName( String roll_no, String board_id, String passing_year)
	{
		String tmp = "";
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="select T3.COLLEGE_NAME,T4.DIST_NAME from BOARD_DATA_SSC t1,board_result t2, mst_college t3, mst_district t4 where " +
				" roll_no=? and t1.board_id=? and t1.passing_year=? and T1.APPLICATION_ID = T2.APPLICATION_ID and t2.eiin=T3.EIIN and " +
				" T3.DIST_ID=T4.DIST_ID";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, roll_no);
			stmt.setString(parameterIndex++, board_id);
			stmt.setString(parameterIndex++, passing_year);
			r = stmt.executeQuery();
			if(r.next())
			{
				tmp = r.getString("COLLEGE_NAME") + ", " + r.getString("DIST_NAME");
			}
		} 
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try{r.close();
				stmt.close();
				conn.close();
				} 
			catch (Exception e){
					e.printStackTrace();
				}
			r =null;
			stmt = null;
			conn = null;
		}
		return tmp;
	}
	public void addStudentBoardLog(String eiin, int shift_id,int version_id,int group_id,String userId,String roll_no, int board_id, int passing_year
			, String mnumber, String probs) 
	{

		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="insert into COLLEGE3DATAENTRY_log (EIIN, SHIFT_ID, VERSION_ID, GROUP_ID, ROLL_NO, BOARD_ID, PASSING_YEAR, probs, mnumber, userId) values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, eiin);
			stmt.setInt(parameterIndex++, shift_id);
			stmt.setInt(parameterIndex++, version_id);
			stmt.setInt(parameterIndex++, group_id);
			stmt.setString(parameterIndex++, roll_no);
			stmt.setInt(parameterIndex++, board_id);
			stmt.setInt(parameterIndex++, passing_year);
			stmt.setString(parameterIndex++, probs);
			stmt.setString(parameterIndex++, mnumber);
			stmt.setString(parameterIndex++, userId);
			stmt.executeUpdate();
		} 
		catch (Exception e){
			e.printStackTrace();
			
		}
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

	}
	
	public int deleteAdded(String applicationID) throws Exception
	{
		int tmp = 0;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="delete from college3dataentry where appid=?";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, applicationID);
			tmp = stmt.executeUpdate();
		} 
		catch (Exception e){
			e.printStackTrace();
			throw e;
		}
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
		
		return tmp;
	}	
	
	
	public List<ApplicantInfoDTO> getStudentInformation(int shift_id,int version_id,int group_id,String userId){
		ApplicantInfoDTO alreadyAddedDTO = null;
		List<ApplicantInfoDTO> lstAlreadyAdded = null;
		
		Connection conn = ConnectionManager.getWriteConnection();
		
		
		String sql ="select t1.*,student_name,t3.board_name_eng from college3dataentry t1, board_data_ssc t2,mst_edu_board t3 where " +
				" t1.roll_no=t2.roll_no and t1.board_id=t2.board_id and t1.passing_year=t2.passing_year and t1.board_id=t3.board_id and " +
				" t1.eiin=? and shift_id=? and version_id=? and t1.group_id=? order by t3.board_name_eng,t1.roll_no";
		
		
//		System.out.println("delwar:"+sql);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setInt(2, shift_id);
			stmt.setInt(3, version_id);
			stmt.setInt(4, group_id);
			r = stmt.executeQuery();
			
			lstAlreadyAdded = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				alreadyAddedDTO = new ApplicantInfoDTO();
				
				alreadyAddedDTO.setEiinCode(r.getString("EIIN"));
				alreadyAddedDTO.setShiftID(r.getString("SHIFT_ID"));
//				alreadyAddedDTO.setShiftName(r.getString("SHIFT_NAME"));
				alreadyAddedDTO.setVersionID(r.getString("VERSION_ID"));
//				alreadyAddedDTO.setVersionName(r.getString("VERSION_NAME"));
				alreadyAddedDTO.setGroupId(r.getString("GROUP_ID"));
//				alreadyAddedDTO.setGroupName(r.getString("GROUP_NAME"));
				alreadyAddedDTO.setApplicantName(r.getString("STUDENT_NAME"));
				alreadyAddedDTO.setSscRollNo(r.getString("ROLL_NO"));
				alreadyAddedDTO.setBoardID(r.getString("BOARD_ID"));
				alreadyAddedDTO.setBoardName(r.getString("board_name_eng"));
				alreadyAddedDTO.setSscPassingYear(r.getString("PASSING_YEAR"));
				lstAlreadyAdded.add(alreadyAddedDTO);
				

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
		
		return lstAlreadyAdded;
	}	
	public List<ApplicantInfoDTO> showStudent(int shift_id,int version_id,int group_id,String userId){
		ApplicantInfoDTO alreadyAddedDTO = null;
		List<ApplicantInfoDTO> lstAlreadyAdded = null;
		
		Connection conn = ConnectionManager.getWriteConnection();
		
		
		String sql ="select t1.*,student_name,t3.board_name_eng from college3dataentry t1, board_data_ssc t2,mst_edu_board t3 where " +
				" t1.roll_no=t2.roll_no and t1.board_id=t2.board_id and t1.passing_year=t2.passing_year and t1.board_id=t3.board_id and " +
				" t1.eiin=? and shift_id=? and version_id=? and t1.group_id=? order by t3.board_name_eng,t1.roll_no";
		
		
//		System.out.println("delwar:"+sql);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setInt(2, shift_id);
			stmt.setInt(3, version_id);
			stmt.setInt(4, group_id);
			r = stmt.executeQuery();
			
			lstAlreadyAdded = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				alreadyAddedDTO = new ApplicantInfoDTO();
				
				alreadyAddedDTO.setEiinCode(r.getString("EIIN"));
				alreadyAddedDTO.setShiftID(r.getString("SHIFT_ID"));
//				alreadyAddedDTO.setShiftName(r.getString("SHIFT_NAME"));
				alreadyAddedDTO.setVersionID(r.getString("VERSION_ID"));
//				alreadyAddedDTO.setVersionName(r.getString("VERSION_NAME"));
				alreadyAddedDTO.setGroupId(r.getString("GROUP_ID"));
//				alreadyAddedDTO.setGroupName(r.getString("GROUP_NAME"));
				alreadyAddedDTO.setApplicantName(r.getString("STUDENT_NAME"));
				alreadyAddedDTO.setSscRollNo(r.getString("ROLL_NO"));
				alreadyAddedDTO.setBoardID(r.getString("BOARD_ID"));
				alreadyAddedDTO.setBoardName(r.getString("board_name_eng"));
				alreadyAddedDTO.setSscPassingYear(r.getString("PASSING_YEAR"));
				lstAlreadyAdded.add(alreadyAddedDTO);
				

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
		
		return lstAlreadyAdded;
	}
	
	public List<ApplicantInfoDTO> getNonApprovedStudentListOfMerit(String userId){
		ApplicantInfoDTO nonApprovedStudentOfMeritDTO = null;
		List<ApplicantInfoDTO> nonApprovedStudentListOfMerit = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		
		String sql ="SELECT A.EIIN,\n" +
                "       A.APPLICATION_ID,\n" +
                "       B.SHIFT_ID,\n" +
                "       B.SHIFT_NAME,\n" +
                "       C.VERSION_ID,\n" +
                "       C.VERSION_NAME,\n" +
                "       D.GROUP_ID,\n" +
                "       D.GROUP_NAME,\n" +
                "       A.MERIT_TYPE,\n" +
                "       A.MERIT_POSITTION,\n" +
                "       A.STATUS,\n" +
                "       A.QUOTA_TYPE, " +
                "       A.PIN_NUMBER, " +
                "       E.NAME,\n" +
                "       E.SSC_ROLL_NO,\n" +
                "       E.SSC_BOARD_ID,\n" +
                "       E.BOARD_NAME,\n" +
                "       E.SSC_PASSING_YEAR\n" +
   //             "		E.MOBILE_NUMBER \n" +
                "  FROM BOARD_RESULT a,\n" +
                "       MST_SHIFT b,\n" +
                "       MST_VERSION c,\n" +
                "       MST_GROUP d,\n" +
                "       APPLICATION_INFO e\n" +
                " WHERE     A.EIIN = ? AND STATUS in('Not Approved') AND MERIT_TYPE IN ('1','2') AND E.PIN_NUMBER_USED_YN='N'\n" +
                "       AND a.SHIFT_ID = b.SHIFT_ID\n" +
                "       AND A.VERSION_ID = C.VERSION_ID\n" +
                "       AND A.GROUP_ID = D.GROUP_ID\n" +
                "       AND A.APPLICATION_ID = E.APPLICATION_ID\n" +
                "ORDER BY A.MERIT_TYPE,B.SHIFT_NAME,\n" +
                "         C.VERSION_NAME,\n" +
                "         D.GROUP_NAME,\n" +
                "         decode(a.QUOTA_TYPE,'GEN','1','D_G','2','E_G','3','F_G','4','S_G','5',a.QUOTA_TYPE),\n" +
                "         A.APPLICATION_ID";
		
/*		String sql ="SELECT A.EIIN,\n" +
                "       A.APPLICATION_ID,\n" +
                "       B.SHIFT_NAME,\n" +
                "       C.VERSION_NAME,\n" +
                "       D.GROUP_NAME,\n" +
                "       A.MERIT_TYPE,\n" +
                "       A.PRIORITY_ORDER,\n" +
                "       A.STATUS,\n" +
                "       A.QUOTA_TYPE, " +
                "		E.MOBILE_NUMBER \n" +
                "  FROM BOARD_RESULT a,\n" +
                "       MST_SHIFT b,\n" +
                "       MST_VERSION c,\n" +
                "       MST_GROUP d,\n" +
                "       APPLICATION_INFO e\n" +
                " WHERE     A.EIIN = ? AND STATUS='Not Approved'\n" +
                "       AND a.SHIFT_ID = b.SHIFT_ID\n" +
                "       AND A.VERSION_ID = C.VERSION_ID\n" +
                "       AND A.GROUP_ID = D.GROUP_ID\n" +
                "       AND A.APPLICATION_ID = E.APPLICATION_ID order by A.APPLICATION_ID";   */       // and rownum <101
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			
			r = stmt.executeQuery();
			
			nonApprovedStudentListOfMerit = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				nonApprovedStudentOfMeritDTO = new ApplicantInfoDTO();
				
				nonApprovedStudentOfMeritDTO.setEiinCode(r.getString("EIIN"));
				nonApprovedStudentOfMeritDTO.setApplicationID(r.getString("APPLICATION_ID"));
				
				nonApprovedStudentOfMeritDTO.setShiftID(r.getString("SHIFT_ID"));
				nonApprovedStudentOfMeritDTO.setShiftName(r.getString("SHIFT_NAME"));
				
				nonApprovedStudentOfMeritDTO.setVersionID(r.getString("VERSION_ID"));
				nonApprovedStudentOfMeritDTO.setVersionName(r.getString("VERSION_NAME"));
				
				nonApprovedStudentOfMeritDTO.setGroupId(r.getString("GROUP_ID"));
				nonApprovedStudentOfMeritDTO.setGroupName(r.getString("GROUP_NAME"));
				
				nonApprovedStudentOfMeritDTO.setMeritType(r.getString("MERIT_TYPE"));
				nonApprovedStudentOfMeritDTO.setPriorityOrder(r.getString("MERIT_POSITTION"));
				nonApprovedStudentOfMeritDTO.setAdmitStatus(r.getString("STATUS"));
				nonApprovedStudentOfMeritDTO.setAssignedQuota(r.getString("QUOTA_TYPE"));
				nonApprovedStudentOfMeritDTO.setPinNumber(r.getString("PIN_NUMBER"));
		//		nonApprovedStudentOfMeritDTO.setMobileNo(r.getString("MOBILE_NUMBER"));
				
				nonApprovedStudentOfMeritDTO.setApplicantName(r.getString("NAME"));
				nonApprovedStudentOfMeritDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				nonApprovedStudentOfMeritDTO.setBoardID(r.getString("SSC_BOARD_ID"));
				nonApprovedStudentOfMeritDTO.setBoardName(r.getString("BOARD_NAME"));
				nonApprovedStudentOfMeritDTO.setSscPassingYear(r.getString("SSC_PASSING_YEAR"));
				
				
				nonApprovedStudentListOfMerit.add(nonApprovedStudentOfMeritDTO);
				

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
		
		return nonApprovedStudentListOfMerit;
	}

	
	public List<ApplicantInfoDTO> getApprovedStudentListOfMerit(String shift_id,String version_id,String group_id,String merit,String userId){
			ApplicantInfoDTO nonApprovedStudentOfMeritDTO = null;
			List<ApplicantInfoDTO> nonApprovedStudentListOfMerit = null;
			
			Connection conn = ConnectionManager.getWriteConnection();
			
			
			String sql ="  SELECT A.EIIN," +
					"         A.APPLICANT_ID," +
					"         B.SHIFT_ID," +
					"         B.SHIFT_NAME," +
					"         C.VERSION_ID," +
					"         C.VERSION_NAME," +
					"         D.GROUP_ID," +
					"         D.GROUP_NAME," +
					"         A.MERIT_TYPE," +
					"         A.MERIT_POSITTION," +
					"         A.COLLEGE_RECEIVE," +
					"         QUOTA_TYPE," +
					"         E.STUDENT_NAME ," +
					"         E.ROLL_NO," +
					"         E.BOARD_ID," +
					"         E.BOARD_NAME," +
					"         E.PASS_YEAR" +
					"    FROM BOARD_RESULT a," +
					"         MST_SHIFT b," +
					"         MST_VERSION c," +
					"         MST_GROUP d," +
					"         board_data_ssc e" +
					"   WHERE     A.eiin = ?" +
					"             AND A.SHIFT_ID = ?" +
					"             AND A.VERSION_ID = ?" +
					"             AND A.GROUP_ID = ?" +
					"         AND COLLEGE_RECEIVE = 'College Received'" +
					"         AND a.SHIFT_ID = b.SHIFT_ID" +
					"         AND A.VERSION_ID = C.VERSION_ID" +
					"         AND A.GROUP_ID = D.GROUP_ID" +
					"         AND substr(A.applicant_id,1,2)=E.board_id" +
					"            AND substr(A.applicant_id,3,4)=E.pass_year" +
					"            AND substr(A.applicant_id,7)=E.roll_no" +
					" ORDER BY A.MERIT_TYPE," +
					"         B.SHIFT_NAME," +
					"         C.VERSION_NAME," +
					"         D.GROUP_NAME," +
					"         DECODE (A.QUOTA_TYPE," +
					"                 'OWN', '0'," +
					"                 'GEN', '1'," +
					"                 'SQ', '2'," +
					"                 'FFQ', '3'," +
					"                 'DQ', '4'," +
					"                 'EQ', '5'," +
					"                 'BKSP', '6'," +
					"                 'FRQ', '7'," +
					"                 A.QUOTA_TYPE)," +
					"         A.APPLICANT_ID";
			
			
//			System.out.println("delwar:"+sql);
			
			PreparedStatement stmt = null;
			ResultSet r = null;
			try
			{
				stmt = conn.prepareStatement(sql);

				stmt.setString(1, userId);
				stmt.setString(2, shift_id);
				stmt.setString(3, version_id);
				stmt.setString(4, group_id);
				r = stmt.executeQuery();
				
				nonApprovedStudentListOfMerit = new ArrayList<ApplicantInfoDTO>();
				while(r.next())
				{
					nonApprovedStudentOfMeritDTO = new ApplicantInfoDTO();
					
					nonApprovedStudentOfMeritDTO.setEiinCode(r.getString("EIIN"));
					nonApprovedStudentOfMeritDTO.setApplicationID(r.getString("APPLICANT_ID"));
					
					nonApprovedStudentOfMeritDTO.setShiftID(r.getString("SHIFT_ID"));
					nonApprovedStudentOfMeritDTO.setShiftName(r.getString("SHIFT_NAME"));
					
					nonApprovedStudentOfMeritDTO.setVersionID(r.getString("VERSION_ID"));
					nonApprovedStudentOfMeritDTO.setVersionName(r.getString("VERSION_NAME"));
					
					nonApprovedStudentOfMeritDTO.setGroupId(r.getString("GROUP_ID"));
					nonApprovedStudentOfMeritDTO.setGroupName(r.getString("GROUP_NAME"));
					
					nonApprovedStudentOfMeritDTO.setMeritType(r.getString("MERIT_TYPE"));
					nonApprovedStudentOfMeritDTO.setPriorityOrder(r.getString("MERIT_POSITTION"));
					nonApprovedStudentOfMeritDTO.setAdmitStatus(r.getString("COLLEGE_RECEIVE"));
					nonApprovedStudentOfMeritDTO.setAssignedQuota(r.getString("QUOTA_TYPE"));
			//		nonApprovedStudentOfMeritDTO.setMobileNo(r.getString("MOBILE_NUMBER"));
					
					nonApprovedStudentOfMeritDTO.setApplicantName(r.getString("STUDENT_NAME"));
					nonApprovedStudentOfMeritDTO.setSscRollNo(r.getString("ROLL_NO"));
					nonApprovedStudentOfMeritDTO.setBoardID(r.getString("BOARD_ID"));
					nonApprovedStudentOfMeritDTO.setBoardName(r.getString("BOARD_NAME"));
					nonApprovedStudentOfMeritDTO.setSscPassingYear(r.getString("PASS_YEAR"));
					
					
					nonApprovedStudentListOfMerit.add(nonApprovedStudentOfMeritDTO);
					

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
			
			return nonApprovedStudentListOfMerit;
	}
	
	public List<ApplicantInfoDTO> getApprovedStudentListOfMeritByBoard(String shift_id,String version_id,String group_id,String merit,String userId,String applicationID){
		ApplicantInfoDTO approvedStudentOfMeritDTO = null;
		List<ApplicantInfoDTO> approvedStudentListOfMerit = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		
		
		String sql="  SELECT A.EIIN,\n" +
				"       F.COLLEGE_NAME,\n" +
                "       A.APPLICATION_ID,\n" +
                "       B.SHIFT_ID,\n" +
                "       B.SHIFT_NAME,\n" +
                "       C.VERSION_ID,\n" +
                "       C.VERSION_NAME,\n" +
                "       D.GROUP_ID,\n" +
                "       D.GROUP_NAME,\n" +
                "         A.MERIT_TYPE,\n" +
                "         A.MERIT_POSITTION,\n" +
                "         A.STATUS,\n" +
                "         A.QUOTA_TYPE,\n" +
                "         E.NAME,\n" +
                "         E.SSC_ROLL_NO,\n" +
                "         E.SSC_BOARD_ID,\n" +
                "         E.BOARD_NAME,\n" +
                "         E.SSC_PASSING_YEAR\n" +
                "    FROM (SELECT *" +
                "            FROM board_result" +
                "           WHERE (APPLICATION_ID, merit_type) IN" +
                "                    (  SELECT APPLICATION_ID, MAX (merit_type)" +
                "                         FROM BOARD_RESULT" +
                "                     GROUP BY APPLICATION_ID)) a,\n" +
                "         MST_SHIFT b,\n" +
                "         MST_VERSION c,\n" +
                "         MST_GROUP d,\n" +
                "         APPLICATION_INFO e,\n" +
                "         MST_COLLEGE f\n" +
                "   WHERE     A.EIIN =?\n" +
                "         AND COLLEGE_RECEIVE = 'College Received' \n" +
                "         AND a.SHIFT_ID = ?\n" +
                "         AND A.VERSION_ID = ?\n" +
                "         AND A.GROUP_ID = ?\n" +
                "         AND A.APPLICATION_ID = ?\n" +
                "         AND a.SHIFT_ID = b.SHIFT_ID\n" +
                "         AND A.VERSION_ID = C.VERSION_ID\n" +
                "         AND A.GROUP_ID = D.GROUP_ID\n" +
                "         AND A.APPLICATION_ID = E.APPLICATION_ID\n" +
                "         AND A.EIIN  = F.EIIN\n" +
                "ORDER BY A.MERIT_TYPE,B.SHIFT_NAME,\n" +
                "         C.VERSION_NAME,\n" +
                "         D.GROUP_NAME,\n" +
                "         decode(a.QUOTA_TYPE,'GEN','1','D_G','2','E_G','3','F_G','4','S_G','5',a.QUOTA_TYPE),\n" +
                "         A.APPLICATION_ID";
		
	

		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setString(2, shift_id);
			stmt.setString(3, version_id);
			stmt.setString(4, group_id);
			stmt.setString(5, applicationID);
			
			r = stmt.executeQuery();
			
			approvedStudentListOfMerit = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				approvedStudentOfMeritDTO = new ApplicantInfoDTO();
				
				approvedStudentOfMeritDTO.setEiinCode(r.getString("EIIN"));
				approvedStudentOfMeritDTO.setCollegeName(r.getString("COLLEGE_NAME"));
				
				approvedStudentOfMeritDTO.setApplicationID(r.getString("APPLICATION_ID"));
				
				approvedStudentOfMeritDTO.setShiftID(r.getString("SHIFT_ID"));
				approvedStudentOfMeritDTO.setShiftName(r.getString("SHIFT_NAME"));
				
				approvedStudentOfMeritDTO.setVersionID(r.getString("VERSION_ID"));
				approvedStudentOfMeritDTO.setVersionName(r.getString("VERSION_NAME"));
				
				approvedStudentOfMeritDTO.setGroupId(r.getString("GROUP_ID"));
				approvedStudentOfMeritDTO.setGroupName(r.getString("GROUP_NAME"));
				
/*				
				approvedStudentOfMeritDTO.setShiftName(r.getString("SHIFT_NAME"));
				approvedStudentOfMeritDTO.setVersionName(r.getString("VERSION_NAME"));
				approvedStudentOfMeritDTO.setGroupName(r.getString("GROUP_NAME"));*/
				approvedStudentOfMeritDTO.setMeritType(r.getString("MERIT_TYPE"));
				approvedStudentOfMeritDTO.setPriorityOrder(r.getString("MERIT_POSITTION"));
				approvedStudentOfMeritDTO.setAdmitStatus(r.getString("STATUS"));
				approvedStudentOfMeritDTO.setAssignedQuota(r.getString("QUOTA_TYPE"));
		//		approvedStudentOfMeritDTO.setMobileNo(r.getString("MOBILE_NUMBER"));
				
				approvedStudentOfMeritDTO.setApplicantName(r.getString("NAME"));
				approvedStudentOfMeritDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				approvedStudentOfMeritDTO.setBoardID(r.getString("SSC_BOARD_ID"));
				approvedStudentOfMeritDTO.setBoardName(r.getString("BOARD_NAME"));
				approvedStudentOfMeritDTO.setSscPassingYear(r.getString("SSC_PASSING_YEAR"));

				approvedStudentListOfMerit.add(approvedStudentOfMeritDTO);
				

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
		
		return approvedStudentListOfMerit;
	}
	
	public List<ApplicantInfoDTO> getAdmissionCancelledStudentListOfMeritByBoard(String shift_id,String version_id,String group_id,String merit,String userId,String applicationID){
		ApplicantInfoDTO admissionCancelledStudentOfMeritDTO = null;
		List<ApplicantInfoDTO> admissionCancelledStudentListOfMerit = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		
		
		String sql="  SELECT A.EIIN,\n" +
				"       F.COLLEGE_NAME,\n" +
                "       A.APPLICATION_ID,\n" +
                "       B.SHIFT_ID,\n" +
                "       B.SHIFT_NAME,\n" +
                "       C.VERSION_ID,\n" +
                "       C.VERSION_NAME,\n" +
                "       D.GROUP_ID,\n" +
                "       D.GROUP_NAME,\n" +
                "         A.MERIT_TYPE,\n" +
                "         A.MERIT_POSITTION,\n" +
                "         A.STATUS,\n" +
                "         A.QUOTA_TYPE,\n" +
                "         E.NAME,\n" +
                "         E.SSC_ROLL_NO,\n" +
                "         E.SSC_BOARD_ID,\n" +
                "         E.BOARD_NAME,\n" +
                "         E.SSC_PASSING_YEAR\n" +
                "    FROM BOARD_RESULT_MERIT a,\n" +
                "         MST_SHIFT b,\n" +
                "         MST_VERSION c,\n" +
                "         MST_GROUP d,\n" +
                "         APPLICATION_INFO e,\n" +
                "         MST_COLLEGE f\n" +
                "   WHERE     A.EIIN =?\n" +
                "         AND STATUS = 'Admission Cancelled' AND MERIT_TYPE IN (?)\n" +
                "         AND a.SHIFT_ID = ?\n" +
                "         AND A.VERSION_ID = ?\n" +
                "         AND A.GROUP_ID = ?\n" +
                "         AND A.APPLICATION_ID = ?\n" +
                "         AND a.SHIFT_ID = b.SHIFT_ID\n" +
                "         AND A.VERSION_ID = C.VERSION_ID\n" +
                "         AND A.GROUP_ID = D.GROUP_ID\n" +
                "         AND A.APPLICATION_ID = E.APPLICATION_ID\n" +
                "         AND A.EIIN  = F.EIIN\n" +
                "ORDER BY A.MERIT_TYPE,B.SHIFT_NAME,\n" +
                "         C.VERSION_NAME,\n" +
                "         D.GROUP_NAME,\n" +
                "         decode(a.QUOTA_TYPE,'GEN','1','D_G','2','E_G','3','F_G','4','S_G','5',a.QUOTA_TYPE),\n" +
                "         A.APPLICATION_ID";
		
	

		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setString(2, merit);
			stmt.setString(3, shift_id);
			stmt.setString(4, version_id);
			stmt.setString(5, group_id);
			stmt.setString(6, applicationID);
			
			r = stmt.executeQuery();
			
			admissionCancelledStudentListOfMerit = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				admissionCancelledStudentOfMeritDTO = new ApplicantInfoDTO();
				
				admissionCancelledStudentOfMeritDTO.setEiinCode(r.getString("EIIN"));
				admissionCancelledStudentOfMeritDTO.setCollegeName(r.getString("COLLEGE_NAME"));
				
				admissionCancelledStudentOfMeritDTO.setApplicationID(r.getString("APPLICATION_ID"));
				
				admissionCancelledStudentOfMeritDTO.setShiftID(r.getString("SHIFT_ID"));
				admissionCancelledStudentOfMeritDTO.setShiftName(r.getString("SHIFT_NAME"));
				
				admissionCancelledStudentOfMeritDTO.setVersionID(r.getString("VERSION_ID"));
				admissionCancelledStudentOfMeritDTO.setVersionName(r.getString("VERSION_NAME"));
				
				admissionCancelledStudentOfMeritDTO.setGroupId(r.getString("GROUP_ID"));
				admissionCancelledStudentOfMeritDTO.setGroupName(r.getString("GROUP_NAME"));
				
/*				
				approvedStudentOfMeritDTO.setShiftName(r.getString("SHIFT_NAME"));
				approvedStudentOfMeritDTO.setVersionName(r.getString("VERSION_NAME"));
				approvedStudentOfMeritDTO.setGroupName(r.getString("GROUP_NAME"));*/
				admissionCancelledStudentOfMeritDTO.setMeritType(r.getString("MERIT_TYPE"));
				admissionCancelledStudentOfMeritDTO.setPriorityOrder(r.getString("MERIT_POSITTION"));
				admissionCancelledStudentOfMeritDTO.setAdmitStatus(r.getString("STATUS"));
				admissionCancelledStudentOfMeritDTO.setAssignedQuota(r.getString("QUOTA_TYPE"));
		//		approvedStudentOfMeritDTO.setMobileNo(r.getString("MOBILE_NUMBER"));
				
				admissionCancelledStudentOfMeritDTO.setApplicantName(r.getString("NAME"));
				admissionCancelledStudentOfMeritDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				admissionCancelledStudentOfMeritDTO.setBoardID(r.getString("SSC_BOARD_ID"));
				admissionCancelledStudentOfMeritDTO.setBoardName(r.getString("BOARD_NAME"));
				admissionCancelledStudentOfMeritDTO.setSscPassingYear(r.getString("SSC_PASSING_YEAR"));

				admissionCancelledStudentListOfMerit.add(admissionCancelledStudentOfMeritDTO);
				

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
		
		return admissionCancelledStudentListOfMerit;
	}
	
	
	public List<ApplicantInfoDTO> getTotalApprovedStudentListOfMerit(String userId){
		ApplicantInfoDTO totalApprovedStudentOfMeritDTO = null;
		List<ApplicantInfoDTO> totalApprovedStudentListOfMerit = null;
		
		Connection conn = ConnectionManager.getReadConnection();
	
		
		String sql="  SELECT *\n" +
                "    FROM (SELECT A.EIIN,\n" +
                "                 A.APPLICATION_ID,\n" +
                "                 B.SHIFT_NAME,\n" +
                "                 C.VERSION_NAME,\n" +
                "                 D.GROUP_NAME,\n" +
                "                 A.MERIT_TYPE,\n" +
                "                 A.MERIT_POSITTION,\n" +
                "                 A.STATUS,\n" +
                "                 A.QUOTA_TYPE,\n" +
                "                 E.NAME,\n" +
                "                 E.SSC_ROLL_NO,\n" +
                "                 E.SSC_BOARD_ID,\n" +
                "                 E.BOARD_NAME,\n" +
                "                 E.SSC_PASSING_YEAR\n" +
    //            "                 E.MOBILE_NUMBER\n" +
                "            FROM BOARD_RESULT a,\n" +
                "                 MST_SHIFT b,\n" +
                "                 MST_VERSION c,\n" +
                "                 MST_GROUP d,\n" +
                "                 APPLICATION_INFO e\n" +
                "           WHERE     A.EIIN =?\n" +
                "                 AND STATUS = 'College Received'\n" +
                "                 AND a.SHIFT_ID = b.SHIFT_ID\n" +
                "                 AND A.VERSION_ID = C.VERSION_ID\n" +
                "                 AND A.GROUP_ID = D.GROUP_ID\n" +
                "                 AND A.APPLICATION_ID = E.APPLICATION_ID\n" +
                "          UNION\n" +
                "          SELECT A.EIIN,\n" +
                "                 A.APPLICATION_ID,\n" +
                "                 B.SHIFT_NAME,\n" +
                "                 C.VERSION_NAME,\n" +
                "                 D.GROUP_NAME,\n" +
                "                 A.MERIT_TYPE,\n" +
                "                 A.MERIT_POSITTION,\n" +
                "                 A.STATUS,\n" +
                "                 A.QUOTA_TYPE,\n" +
                "                 E.STUDENT_NAME,\n" +
                "                 E.SSC_ROLL_NO,\n" +
                "                 E.SSC_BOARD_ID,\n" +
                "                 E.SSC_BOARD_NAME,\n" +
                "                 E.SSC_PASSING_YEAR\n" +
          //      "                 E.MOBILE_NUMBER\n" +
                "            FROM BOARD_RESULT a,\n" +
                "                 MST_SHIFT b,\n" +
                "                 MST_VERSION c,\n" +
                "                 MST_GROUP d,\n" +
                "                 APPLICATION_INFO_NEW e\n" +
                "           WHERE     A.EIIN =?\n" +
                "                 AND STATUS = 'College Received'\n" +
                "                 AND a.SHIFT_ID = b.SHIFT_ID\n" +
                "                 AND A.VERSION_ID = C.VERSION_ID\n" +
                "                 AND A.GROUP_ID = D.GROUP_ID\n" +
                "                 AND A.APPLICATION_ID = E.APPLICATION_ID)\n" +
                "ORDER BY MERIT_TYPE,\n" +
                "         SHIFT_NAME,\n" +
                "         VERSION_NAME,\n" +
                "         GROUP_NAME,\n" +
                "         DECODE (QUOTA_TYPE,\n" +
                "                 'GEN', '1',\n" +
                "                 'D_G', '2',\n" +
                "                 'E_G', '3',\n" +
                "                 'F_G', '4',\n" +
                "                 'S_G', '5',\n" +
                "                 QUOTA_TYPE),\n" +
                "         APPLICATION_ID";
                
		
		
		
/*		String sql="  SELECT A.EIIN,\n" +
                "         A.APPLICATION_ID,\n" +
                "         B.SHIFT_NAME,\n" +
                "         C.VERSION_NAME,\n" +
                "         D.GROUP_NAME,\n" +
                "         A.MERIT_TYPE,\n" +
                "         A.PRIORITY_ORDER,\n" +
                "         A.STATUS,\n" +
                "         A.QUOTA_TYPE,\n" +
                "         E.STUDENT_NAME,\n" +
                "         E.SSC_ROLL_NO,\n" +
                "         E.SSC_BOARD_ID,\n" +
                "         E.SSC_BOARD_NAME,\n" +
                "         E.SSC_PASSING_YEAR,\n" +
                "         E.MOBILE_NUMBER\n" +
                "    FROM BOARD_RESULT a,\n" +
                "         MST_SHIFT b,\n" +
                "         MST_VERSION c,\n" +
                "         MST_GROUP d,\n" +
                "         APPLICATION_INFO e\n" +
                "   WHERE     A.EIIN =?\n" +
                "         AND STATUS = 'College Received' \n" +
                "         AND a.SHIFT_ID = b.SHIFT_ID\n" +
                "         AND A.VERSION_ID = C.VERSION_ID\n" +
                "         AND A.GROUP_ID = D.GROUP_ID\n" +
                "         AND A.APPLICATION_ID = E.APPLICATION_ID\n" +
                "ORDER BY A.MERIT_TYPE,B.SHIFT_NAME,\n" +
                "         C.VERSION_NAME,\n" +
                "         D.GROUP_NAME,\n" +
                "         decode(a.QUOTA_TYPE,'GEN','1','D_G','2','E_G','3','F_G','4','S_G','5',a.QUOTA_TYPE),\n" +
                "         A.APPLICATION_ID";*/
		
	

		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setString(2, userId);
			
			r = stmt.executeQuery();
			
			totalApprovedStudentListOfMerit = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				totalApprovedStudentOfMeritDTO = new ApplicantInfoDTO();
				
				totalApprovedStudentOfMeritDTO.setEiinCode(r.getString("EIIN"));
				totalApprovedStudentOfMeritDTO.setApplicationID(r.getString("APPLICATION_ID"));
				totalApprovedStudentOfMeritDTO.setShiftName(r.getString("SHIFT_NAME"));
				totalApprovedStudentOfMeritDTO.setVersionName(r.getString("VERSION_NAME"));
				totalApprovedStudentOfMeritDTO.setGroupName(r.getString("GROUP_NAME"));
				totalApprovedStudentOfMeritDTO.setMeritType(r.getString("MERIT_TYPE"));
				totalApprovedStudentOfMeritDTO.setPriorityOrder(r.getString("MERIT_POSITTION"));
				totalApprovedStudentOfMeritDTO.setAdmitStatus(r.getString("STATUS"));
				totalApprovedStudentOfMeritDTO.setAssignedQuota(r.getString("QUOTA_TYPE"));
		//		totalApprovedStudentOfMeritDTO.setMobileNo(r.getString("MOBILE_NUMBER"));
				
				totalApprovedStudentOfMeritDTO.setApplicantName(r.getString("NAME"));
				totalApprovedStudentOfMeritDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				totalApprovedStudentOfMeritDTO.setBoardID(r.getString("SSC_BOARD_ID"));
				totalApprovedStudentOfMeritDTO.setBoardName(r.getString("BOARD_NAME"));
				totalApprovedStudentOfMeritDTO.setSscPassingYear(r.getString("SSC_PASSING_YEAR"));

				totalApprovedStudentListOfMerit.add(totalApprovedStudentOfMeritDTO);
				

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
		
		return totalApprovedStudentListOfMerit;
	}

	public String[] receiveAddmission(String applicationID[], String ImeritType[], String Ishift_id[], String Iversion_id[],
			String Igroup_id[],String quota[], String eiin, IpAddressDTO ipAddress){

		String[] tmp = null;
		Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		try {
			ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
			ARRAY IappId=new ARRAY(arrString,conn,applicationID);
			ARRAY ImeritTyp=new ARRAY(arrString,conn,ImeritType);

			ARRAY Ishift=new ARRAY(arrString,conn,Ishift_id);
			ARRAY Iversion=new ARRAY(arrString,conn,Iversion_id);
			ARRAY Igroup=new ARRAY(arrString,conn,Igroup_id);
			ARRAY Iquota=new ARRAY(arrString,conn,quota);
			String response = "";
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call receiveAdmission_Priority(?,?,?,?,?,?,?,?,?,?,?)  }");
            stmt.setARRAY(1, IappId);      
            stmt.setARRAY(2, ImeritTyp);
            stmt.setString(3, eiin);
            stmt.setString(4, "College Received");
            stmt.setString(5, ipAddress.getxForward());
            stmt.setString(6, ipAddress.getVia());
            stmt.setString(7, ipAddress.getRemoteAddress());
            stmt.registerOutParameter(8, java.sql.Types.VARCHAR);            
            stmt.setARRAY(9, Ishift);
            stmt.setARRAY(10, Iversion);
            stmt.setARRAY(11, Igroup);
            stmt.executeUpdate();
            response=stmt.getString(8);
            tmp = response.split("###");
		} 
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				stmt.close();
				ConnectionManager.closeConnection(conn);
			} catch (Exception e)
				{e.printStackTrace();}
		} 		
        
		return tmp;
        
	}
	
	public String[] cancelAddmission(String applicationID[], String ImeritType[],String Ishift_id[],String Iversion_id[],String Igroup_id[],String quota[],String eiin, IpAddressDTO ipAddress){

		Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		
		String[] tmp = null;
		int operation=0;
		try {
			
			conn.setAutoCommit(false);
			ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
			
			ARRAY IappId=new ARRAY(arrString,conn,applicationID);
			ARRAY ImeritTyp=new ARRAY(arrString,conn,ImeritType);
			ARRAY Ishift=new ARRAY(arrString,conn,Ishift_id);
			ARRAY Iversion=new ARRAY(arrString,conn,Iversion_id);
			ARRAY Igroup=new ARRAY(arrString,conn,Igroup_id);
			ARRAY Iquota=new ARRAY(arrString,conn,quota);
			
			String response = "";
//            stmt = (OracleCallableStatement) conn.prepareCall("{ call cancelAdmission(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");
//            stmt.setARRAY(1, IappId);      
//            stmt.setARRAY(2, ImeritTyp);
//            stmt.setString(3, eiin);
//            stmt.setString(4, "Admission Cancelled");
//            stmt.setString(5, ipAddress.getxForward());
//            stmt.setString(6, ipAddress.getVia());
//            stmt.setString(7, ipAddress.getRemoteAddress());
//            stmt.registerOutParameter(8, java.sql.Types.INTEGER);
//            stmt.registerOutParameter(9, java.sql.Types.INTEGER);
//            stmt.registerOutParameter(10, java.sql.Types.VARCHAR);
//            stmt.setARRAY(11, Ishift);
//            stmt.setARRAY(12, Iversion);
//            stmt.setARRAY(13, Igroup);
//            stmt.registerOutParameter(14, java.sql.Types.VARCHAR);
//            stmt.setARRAY(15, Iquota);
//            stmt.executeUpdate();
//            response=stmt.getString(14);
//            operation=stmt.getInt(8);

            stmt = (OracleCallableStatement) conn.prepareCall("{ call CANCELADMISSION_Priority(?,?,?,?,?,?,?,?,?,?,?)  }");
            stmt.setARRAY(1, IappId);      
            stmt.setARRAY(2, ImeritTyp);
            stmt.setString(3, eiin);
            stmt.setString(4, "Not Received");
            stmt.setString(5, ipAddress.getxForward());
            stmt.setString(6, ipAddress.getVia());
            stmt.setString(7, ipAddress.getRemoteAddress());
            stmt.registerOutParameter(8, java.sql.Types.VARCHAR);            
            stmt.setARRAY(9, Ishift);
            stmt.setARRAY(10, Iversion);
            stmt.setARRAY(11, Igroup);
            stmt.executeUpdate();
            response=stmt.getString(8);            
            
            
            tmp = response.split("###");
            
            
           // conn.commit();
            //System.out.println(stmt.getString(10));

		} 
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				stmt.close();
				ConnectionManager.closeConnection(conn);
			} catch (Exception e)
				{e.printStackTrace();}
		} 		
        
		return tmp;
        
	}
	
	public List<ApplicantInfoDTO> getCancelledStudentListOfMerit(String userId){
		ApplicantInfoDTO admissionCancelStudentOfMeritDTO = null;
		List<ApplicantInfoDTO> admissionCancelListOfMerit = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
/*		String sql="  SELECT *\n" +
                "    FROM (SELECT A.EIIN,\n" +
                "                 A.APPLICATION_ID,\n" +
                "                 B.SHIFT_NAME,\n" +
                "                 C.VERSION_NAME,\n" +
                "                 D.GROUP_NAME,\n" +
                "                 A.MERIT_TYPE,\n" +
                "                 A.PRIORITY_ORDER,\n" +
                "                 A.STATUS,\n" +
                "                 A.QUOTA_TYPE,\n" +
                "                 E.NAME,\n" +
                "                 E.SSC_ROLL_NO,\n" +
                "                 E.SSC_BOARD_ID,\n" +
                "                 E.BOARD_NAME,\n" +
                "                 E.SSC_PASSING_YEAR\n" +
     //           "                 E.MOBILE_NUMBER\n" +
                "            FROM BOARD_RESULT a,\n" +
                "                 MST_SHIFT b,\n" +
                "                 MST_VERSION c,\n" +
                "                 MST_GROUP d,\n" +
                "                 APPLICATION_INFO e\n" +
                "           WHERE     A.EIIN =?\n" +
                "                 AND STATUS = 'Admission Cancelled'\n" +
                "                 AND a.SHIFT_ID = b.SHIFT_ID\n" +
                "                 AND A.VERSION_ID = C.VERSION_ID\n" +
                "                 AND A.GROUP_ID = D.GROUP_ID\n" +
                "                 AND A.APPLICATION_ID = E.APPLICATION_ID\n" +
                "          UNION\n" +
                "          SELECT A.EIIN,\n" +
                "                 A.APPLICATION_ID,\n" +
                "                 B.SHIFT_NAME,\n" +
                "                 C.VERSION_NAME,\n" +
                "                 D.GROUP_NAME,\n" +
                "                 A.MERIT_TYPE,\n" +
                "                 A.PRIORITY_ORDER,\n" +
                "                 A.STATUS,\n" +
                "                 A.QUOTA_TYPE,\n" +
                "                 E.NAME,\n" +
                "                 E.SSC_ROLL_NO,\n" +
                "                 E.SSC_BOARD_ID,\n" +
                "                 E.BOARD_NAME,\n" +
                "                 E.SSC_PASSING_YEAR\n" +
     //           "                 E.MOBILE_NUMBER\n" +
                "            FROM BOARD_RESULT a,\n" +
                "                 MST_SHIFT b,\n" +
                "                 MST_VERSION c,\n" +
                "                 MST_GROUP d,\n" +
                "                 APPLICATION_INFO e\n" +
                "           WHERE     A.EIIN =?\n" +
                "                 AND STATUS = 'Admission Cancelled'\n" +
                "                 AND a.SHIFT_ID = b.SHIFT_ID\n" +
                "                 AND A.VERSION_ID = C.VERSION_ID\n" +
                "                 AND A.GROUP_ID = D.GROUP_ID\n" +
                "                 AND A.APPLICATION_ID = E.APPLICATION_ID)\n" +
                "ORDER BY MERIT_TYPE,\n" +
                "         SHIFT_NAME,\n" +
                "         VERSION_NAME,\n" +
                "         GROUP_NAME,\n" +
                "         DECODE (QUOTA_TYPE,\n" +
                "                 'GEN', '1',\n" +
                "                 'D_G', '2',\n" +
                "                 'E_G', '3',\n" +
                "                 'F_G', '4',\n" +
                "                 'S_G', '5',\n" +
                "                 QUOTA_TYPE),\n" +
                "         APPLICATION_ID";	*/
		
		String sql="  SELECT A.EIIN,\n" +
                "         A.APPLICATION_ID,\n" +
                "         B.SHIFT_NAME,\n" +
                "         C.VERSION_NAME,\n" +
                "         D.GROUP_NAME,\n" +
                "         A.MERIT_TYPE,\n" +
                "         A.MERIT_POSITTION,\n" +
                "         A.STATUS,\n" +
                "         A.QUOTA_TYPE,\n" +
                "         E.NAME,\n" +
                "         E.SSC_ROLL_NO,\n" +
                "         E.SSC_BOARD_ID,\n" +
                "         E.BOARD_NAME,\n" +
                "         E.SSC_PASSING_YEAR\n" +
        //        "         E.MOBILE_NUMBER\n" +
                "    FROM BOARD_RESULT_MERIT a,\n" +
                "         MST_SHIFT b,\n" +
                "         MST_VERSION c,\n" +
                "         MST_GROUP d,\n" +
                "         APPLICATION_INFO e\n" +
                "   WHERE     A.EIIN =?\n" +
                "         AND STATUS = 'Admission Cancelled'\n" +
                "         AND a.SHIFT_ID = b.SHIFT_ID\n" +
                "         AND A.VERSION_ID = C.VERSION_ID\n" +
                "         AND A.GROUP_ID = D.GROUP_ID\n" +
                "         AND A.APPLICATION_ID = E.APPLICATION_ID\n" +
                "ORDER BY A.MERIT_TYPE,B.SHIFT_NAME,\n" +
                "         C.VERSION_NAME,\n" +
                "         D.GROUP_NAME,\n" +
                "         decode(a.QUOTA_TYPE,'GEN','1','D_G','2','E_G','3','F_G','4','S_G','5',a.QUOTA_TYPE),\n" +
                "         A.APPLICATION_ID";
		
	

		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
		//	stmt.setString(2, userId);
			
			r = stmt.executeQuery();
			
			admissionCancelListOfMerit = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				admissionCancelStudentOfMeritDTO = new ApplicantInfoDTO();
				
				admissionCancelStudentOfMeritDTO.setEiinCode(r.getString("EIIN"));
				admissionCancelStudentOfMeritDTO.setApplicationID(r.getString("APPLICATION_ID"));
				admissionCancelStudentOfMeritDTO.setShiftName(r.getString("SHIFT_NAME"));
				admissionCancelStudentOfMeritDTO.setVersionName(r.getString("VERSION_NAME"));
				admissionCancelStudentOfMeritDTO.setGroupName(r.getString("GROUP_NAME"));
				admissionCancelStudentOfMeritDTO.setMeritType(r.getString("MERIT_TYPE"));
				admissionCancelStudentOfMeritDTO.setPriorityOrder(r.getString("MERIT_POSITTION"));
				admissionCancelStudentOfMeritDTO.setAdmitStatus(r.getString("STATUS"));
				admissionCancelStudentOfMeritDTO.setAssignedQuota(r.getString("QUOTA_TYPE"));
		//		admissionCancelStudentOfMeritDTO.setMobileNo(r.getString("MOBILE_NUMBER"));
				
				admissionCancelStudentOfMeritDTO.setApplicantName(r.getString("NAME"));
				admissionCancelStudentOfMeritDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				admissionCancelStudentOfMeritDTO.setBoardID(r.getString("SSC_BOARD_ID"));
				admissionCancelStudentOfMeritDTO.setBoardName(r.getString("BOARD_NAME"));
				admissionCancelStudentOfMeritDTO.setSscPassingYear(r.getString("SSC_PASSING_YEAR"));

				admissionCancelListOfMerit.add(admissionCancelStudentOfMeritDTO);
				

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
		
		return admissionCancelListOfMerit;
	}
	
	
	
	public List<CollegeCourseDTO> getShiftList(String userId){
		CollegeCourseDTO shiftDTO = null;
		List<CollegeCourseDTO> shiftList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "SELECT DISTINCT a.shift_id, A.SHIFT_NAME\n" +
                "    FROM MST_SHIFT a, MST_COLLEGE_GROUPS b\n" +
                "   WHERE b.eiin =? AND a.shift_id = b.shift_id\n" +
                "ORDER BY a.shift_id";
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			
			r = stmt.executeQuery();
			
			shiftList = new ArrayList<CollegeCourseDTO>();
			while(r.next())
			{
				shiftDTO = new CollegeCourseDTO();
				
				shiftDTO.setShiftId(r.getString("shift_id"));
				shiftDTO.setShiftName(r.getString("SHIFT_NAME"));
				
				shiftList.add(shiftDTO);
				

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
		
		return shiftList;
	}
	
	public List<CollegeCourseDTO> getVersionList(String userId){
		CollegeCourseDTO versionDTO = null;
		List<CollegeCourseDTO> versionList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="  SELECT DISTINCT a.version_id, A.VERSION_NAME\n" +
                "    FROM MST_VERSION a, MST_COLLEGE_GROUPS b\n" +
                "   WHERE b.eiin =? AND a.version_id = b.version_id\n" +
                "ORDER BY a.version_id";
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			
			r = stmt.executeQuery();
			
			versionList = new ArrayList<CollegeCourseDTO>();
			while(r.next())
			{
				versionDTO = new CollegeCourseDTO();
				
				versionDTO.setVersionId(r.getString("version_id"));
				versionDTO.setVersionName(r.getString("VERSION_NAME"));
	
				versionList.add(versionDTO);
				

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
		
		return versionList;
	}
	
	public List<CollegeCourseDTO> getGroupList(String userId){
		CollegeCourseDTO groupDTO = null;
		List<CollegeCourseDTO> grouptList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="  SELECT DISTINCT a.GROUP_ID, A.GROUP_NAME\n" +
                "    FROM MST_GROUP a, MST_COLLEGE_GROUPS b\n" +
                "   WHERE b.eiin =? AND a.GROUP_ID = b.GROUP_ID\n" +
                "ORDER BY a.GROUP_ID";
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			
			r = stmt.executeQuery();
			
			grouptList = new ArrayList<CollegeCourseDTO>();
			while(r.next())
			{
				groupDTO = new CollegeCourseDTO();
				
				groupDTO.setGroupId(r.getString("GROUP_ID"));
				groupDTO.setGroupName(r.getString("GROUP_NAME"));
								
				grouptList.add(groupDTO);
				

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
		
		return grouptList;
	}
	
	
	
	
	
	public List<CollegeCourseDTO> getShiftList3(String userId){
		CollegeCourseDTO shiftDTO = null;
		List<CollegeCourseDTO> shiftList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "SELECT DISTINCT a.shift_id, A.SHIFT_NAME\n" +
                "    FROM MST_SHIFT a, MST_COLLEGE_GROUPS b\n" +
                "   WHERE b.eiin =? AND a.shift_id = b.shift_id\n" +
                "ORDER BY a.shift_id";
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			
			r = stmt.executeQuery();
			
			shiftList = new ArrayList<CollegeCourseDTO>();
			while(r.next())
			{
				shiftDTO = new CollegeCourseDTO();
				
				shiftDTO.setShiftId(r.getString("shift_id"));
				shiftDTO.setShiftName(r.getString("SHIFT_NAME"));
				
				shiftList.add(shiftDTO);
				

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
		
		return shiftList;
	}
	
	public List<CollegeCourseDTO> getVersionList3(String userId){
		CollegeCourseDTO versionDTO = null;
		List<CollegeCourseDTO> versionList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="  SELECT DISTINCT a.version_id, A.VERSION_NAME\n" +
                "    FROM MST_VERSION a, MST_COLLEGE_GROUPS b\n" +
                "   WHERE b.eiin =? AND a.version_id = b.version_id\n" +
                "ORDER BY a.version_id";
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			
			r = stmt.executeQuery();
			
			versionList = new ArrayList<CollegeCourseDTO>();
			while(r.next())
			{
				versionDTO = new CollegeCourseDTO();
				
				versionDTO.setVersionId(r.getString("version_id"));
				versionDTO.setVersionName(r.getString("VERSION_NAME"));
	
				versionList.add(versionDTO);
				

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
		
		return versionList;
	}
	
	public List<CollegeCourseDTO> getGroupList3(String userId){
		CollegeCourseDTO groupDTO = null;
		List<CollegeCourseDTO> grouptList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="  SELECT DISTINCT a.GROUP_ID, A.GROUP_NAME\n" +
                "    FROM MST_GROUP a, MST_COLLEGE_GROUPS b\n" +
                "   WHERE b.eiin =? AND a.GROUP_ID = b.GROUP_ID\n" +
                "ORDER BY a.GROUP_ID";
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			
			r = stmt.executeQuery();
			
			grouptList = new ArrayList<CollegeCourseDTO>();
			while(r.next())
			{
				groupDTO = new CollegeCourseDTO();
				
				groupDTO.setGroupId(r.getString("GROUP_ID"));
				groupDTO.setGroupName(r.getString("GROUP_NAME"));
								
				grouptList.add(groupDTO);
				

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
		
		return grouptList;
	}
	
	
	
	
	
	
	
	
	
	
	
	public List<ResultDTO> getResultData(String Ieiin, String Ishift, String Iversion, String Igroup, String ImeritType){
		ResultDTO resultDTO = null;
		List<ResultDTO> resultList = null;
		String sql="";
		
		Connection conn = ConnectionManager.getReadConnection();
		
				
		if(ImeritType.equalsIgnoreCase("1-OUT")||ImeritType.equalsIgnoreCase("3-OUT"))
		{
			String[] parts = ImeritType.split("-");
			String string1 = parts[0];
			int migrated_from = Integer.parseInt(string1);
			int migrated_to=migrated_from+1;  
			
			 sql="select app.APPLICATION_ID, app.SSC_ROLL_NO, board.BOARD_NAME_ENG, app.SSC_PASSING_YEAR, \n" +
		                "                ssc.STUDENT_NAME, ssc.GPA, result.MERIT_TYPE, result.QUOTA_TYPE, \n" +
		                "                result.EIIN, result.SHIFT_ID, result.VERSION_ID, grp.GROUP_NAME\n" +
		                "                from board_result result, application_info app, board_data_ssc ssc, mst_edu_board board, mst_group grp\n" +
		                "                where app.APPLICATION_ID = result.APPLICATION_ID\n" +
		                "                and app.SSC_ROLL_NO = ssc.ROLL_NO\n" +
		                "                and app.SSC_BOARD_ID = ssc.BOARD_ID\n" +
		                "                and app.SSC_PASSING_YEAR = ssc.PASSING_YEAR\n" +
		                "                and app.SSC_BOARD_ID = board.BOARD_ID\n" +
		                "                and result.GROUP_ID = grp.GROUP_ID\n" +
		                "                and result.application_id in (select application_id from board_result where merit_type =?)\n" +
		                "                and result.SHIFT_ID =?\n" +
		                "                and result.VERSION_ID =?\n" +
		                "                and result.GROUP_ID =?\n" +
		                "                and result.EIIN =?\n" +
		                "                and result.MERIT_TYPE =?\n" +
		                "                order by decode(result.QUOTA_TYPE,'OWN','1','SPECIAL','2','FREEDOM','3','EDUCATION','4'" +
		                "				 ,'DISTRICT','5','FOREIGN','6','BKSP','7','GENERAL','8',result.QUOTA_TYPE),app.application_id";
			 
				PreparedStatement stmt = null;
				ResultSet r = null;
				try
				{
					stmt = conn.prepareStatement(sql);

					stmt.setInt(1, migrated_to);
					stmt.setString(2, Ishift);
					stmt.setString(3, Iversion);
					stmt.setString(4, Igroup);
					stmt.setString(5, Ieiin);
					stmt.setInt(6, migrated_from);
					
					r = stmt.executeQuery();
					
					resultList = new ArrayList<ResultDTO>();
					while(r.next())
					{
						resultDTO = new ResultDTO();
						
						resultDTO.setEiinCode(r.getString("EIIN"));
						resultDTO.setApplicantMerit(r.getString("MERIT_TYPE"));
						resultDTO.setApplicantQuota(r.getString("QUOTA_TYPE"));
						resultDTO.setApplicationId(r.getString("APPLICATION_ID"));
						resultDTO.setSscRoll(r.getString("SSC_ROLL_NO"));
						resultDTO.setSscBoard(r.getString("BOARD_NAME_ENG"));
						resultDTO.setSscYear(r.getString("SSC_PASSING_YEAR"));
						resultDTO.setApplicantName(r.getString("STUDENT_NAME"));
						resultDTO.setApplicantGpa(r.getString("GPA"));
						resultDTO.setGroupName(r.getString("GROUP_NAME"));
						resultList.add(resultDTO);				

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
			
		}
		
		else if(ImeritType.equalsIgnoreCase("1"))
		{

			
			   sql ="select app.APPLICATION_ID, app.SSC_ROLL_NO, board.BOARD_NAME_ENG, app.SSC_PASSING_YEAR, \n" +
				        "ssc.STUDENT_NAME, ssc.GPA, result.MERIT_TYPE, result.QUOTA_TYPE, \n" +
				        "result.EIIN, result.SHIFT_ID, result.VERSION_ID, grp.GROUP_NAME\n" +
				        "from BOARD_RESULT_MERIT result, application_info app, board_data_ssc ssc, mst_edu_board board, mst_group grp\n" +
				        "where app.APPLICATION_ID = result.APPLICATION_ID\n" +
				        "and app.SSC_ROLL_NO = ssc.ROLL_NO\n" +
				        "and app.SSC_BOARD_ID = ssc.BOARD_ID\n" +
				        "and app.SSC_PASSING_YEAR = ssc.PASSING_YEAR\n" +
				        "and app.SSC_BOARD_ID = board.BOARD_ID\n" +
				        "and result.GROUP_ID = grp.GROUP_ID\n" +
				        "and result.SHIFT_ID = ?\n" +
				        "and result.VERSION_ID = ?\n" +
				        "and result.GROUP_ID = ?\n" +
				        "and result.EIIN = ?\n" +
				        "and result.MERIT_TYPE = ?" +
		                "                order by decode(result.QUOTA_TYPE,'OWN','1','SPECIAL','2','FREEDOM','3','EDUCATION','4'" +
		                "				 ,'DISTRICT','5','FOREIGN','6','BKSP','7','GENERAL','8',result.QUOTA_TYPE),app.SSC_ROLL_NO";
			 
				PreparedStatement stmt = null;
				ResultSet r = null;
				try
				{
					stmt = conn.prepareStatement(sql);

					stmt.setString(1, Ishift);
					stmt.setString(2, Iversion);
					stmt.setString(3, Igroup);
					stmt.setString(4, Ieiin);
					stmt.setString(5, ImeritType);
					
					r = stmt.executeQuery();
					
					resultList = new ArrayList<ResultDTO>();
					while(r.next())
					{
						resultDTO = new ResultDTO();
						
						resultDTO.setEiinCode(r.getString("EIIN"));
						resultDTO.setApplicantMerit(r.getString("MERIT_TYPE"));
						resultDTO.setApplicantQuota(r.getString("QUOTA_TYPE"));
						resultDTO.setApplicationId(r.getString("APPLICATION_ID"));
						resultDTO.setSscRoll(r.getString("SSC_ROLL_NO"));
						resultDTO.setSscBoard(r.getString("BOARD_NAME_ENG"));
						resultDTO.setSscYear(r.getString("SSC_PASSING_YEAR"));
						resultDTO.setApplicantName(r.getString("STUDENT_NAME"));
						resultDTO.setApplicantGpa(r.getString("GPA"));
						resultDTO.setGroupName(r.getString("GROUP_NAME"));
						resultList.add(resultDTO);				

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
			
		}
		
		else if(ImeritType.equalsIgnoreCase("2") || ImeritType.equalsIgnoreCase("3") || ImeritType.equalsIgnoreCase("4"))
		{
			   sql ="select app.APPLICATION_ID, app.SSC_ROLL_NO, board.BOARD_NAME_ENG, app.SSC_PASSING_YEAR, \n" +
				        "ssc.STUDENT_NAME, ssc.GPA, result.MERIT_TYPE,result.MERIT_POSITTION, result.QUOTA_TYPE, \n" +
				        "result.EIIN, result.SHIFT_ID, result.VERSION_ID, grp.GROUP_NAME\n" +
				        "from BOARD_RESULT_MERIT result, application_info app, board_data_ssc ssc, mst_edu_board board, mst_group grp\n" +
				        "where app.APPLICATION_ID = result.APPLICATION_ID\n" +
				        "and app.SSC_ROLL_NO = ssc.ROLL_NO\n" +
				        "and app.SSC_BOARD_ID = ssc.BOARD_ID\n" +
				        "and app.SSC_PASSING_YEAR = ssc.PASSING_YEAR\n" +
				        "and app.SSC_BOARD_ID = board.BOARD_ID\n" +
				        "and result.GROUP_ID = grp.GROUP_ID\n" +
				        "and result.SHIFT_ID = ?\n" +
				        "and result.VERSION_ID = ?\n" +
				        "and result.GROUP_ID = ?\n" +
				        "and result.EIIN = ?\n" +
				        "and result.MERIT_TYPE = ?" +
		                "                order by decode(result.QUOTA_TYPE,'OWN','1','SPECIAL','2','FREEDOM','3','EDUCATION','4'" +
		                "				 ,'DISTRICT','5','FOREIGN','6','BKSP','7','GENERAL','8',result.QUOTA_TYPE),result.MERIT_POSITTION,app.application_id";
			 
				PreparedStatement stmt = null;
				ResultSet r = null;
				try
				{
					stmt = conn.prepareStatement(sql);

					stmt.setString(1, Ishift);
					stmt.setString(2, Iversion);
					stmt.setString(3, Igroup);
					stmt.setString(4, Ieiin);
					stmt.setString(5, ImeritType);
					
					r = stmt.executeQuery();
					
					resultList = new ArrayList<ResultDTO>();
					while(r.next())
					{
						resultDTO = new ResultDTO();
						
						resultDTO.setEiinCode(r.getString("EIIN"));
						resultDTO.setApplicantMerit(r.getString("MERIT_TYPE"));
						resultDTO.setApplicantQuota(r.getString("QUOTA_TYPE"));
						resultDTO.setApplicationId(r.getString("APPLICATION_ID"));
						resultDTO.setSscRoll(r.getString("SSC_ROLL_NO"));
						resultDTO.setSscBoard(r.getString("BOARD_NAME_ENG"));
						resultDTO.setSscYear(r.getString("SSC_PASSING_YEAR"));
						resultDTO.setApplicantName(r.getString("STUDENT_NAME"));
						resultDTO.setMeritPosition(r.getString("MERIT_POSITTION"));
						//resultDTO.setApplicantGpa(r.getString("GPA"));
						resultDTO.setGroupName(r.getString("GROUP_NAME"));
						resultList.add(resultDTO);				

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
			
		}
		else 
		{
		   sql ="select app.APPLICATION_ID, app.SSC_ROLL_NO, board.BOARD_NAME_ENG, app.SSC_PASSING_YEAR, \n" +
		        "ssc.STUDENT_NAME, ssc.GPA, result.MERIT_TYPE, result.QUOTA_TYPE, \n" +
		        "result.EIIN, result.SHIFT_ID, result.VERSION_ID, grp.GROUP_NAME\n" +
		        "from board_result result, application_info app, board_data_ssc ssc, mst_edu_board board, mst_group grp\n" +
		        "where app.APPLICATION_ID = result.APPLICATION_ID\n" +
		        "and app.SSC_ROLL_NO = ssc.ROLL_NO\n" +
		        "and app.SSC_BOARD_ID = ssc.BOARD_ID\n" +
		        "and app.SSC_PASSING_YEAR = ssc.PASSING_YEAR\n" +
		        "and app.SSC_BOARD_ID = board.BOARD_ID\n" +
		        "and result.GROUP_ID = grp.GROUP_ID\n" +
		        "and result.SHIFT_ID = ?\n" +
		        "and result.VERSION_ID = ?\n" +
		        "and result.GROUP_ID = ?\n" +
		        "and result.EIIN = ?\n" +
		        "and result.MERIT_TYPE = ?" +
		        "order by decode(result.QUOTA_TYPE,'GEN','1','D_G','2','E_G','3','F_G','4','S_G','5',result.QUOTA_TYPE),app.application_id";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, Ishift);
			stmt.setString(2, Iversion);
			stmt.setString(3, Igroup);
			stmt.setString(4, Ieiin);
			stmt.setString(5, ImeritType);
			
			r = stmt.executeQuery();
			
			resultList = new ArrayList<ResultDTO>();
			while(r.next())
			{
				resultDTO = new ResultDTO();
				
				resultDTO.setEiinCode(r.getString("EIIN"));
				resultDTO.setApplicantMerit(r.getString("MERIT_TYPE"));
				resultDTO.setApplicantQuota(r.getString("QUOTA_TYPE"));
				resultDTO.setApplicationId(r.getString("APPLICATION_ID"));
				resultDTO.setSscRoll(r.getString("SSC_ROLL_NO"));
				resultDTO.setSscBoard(r.getString("BOARD_NAME_ENG"));
				resultDTO.setSscYear(r.getString("SSC_PASSING_YEAR"));
				resultDTO.setApplicantName(r.getString("STUDENT_NAME"));
				resultDTO.setApplicantGpa(r.getString("GPA"));
				resultDTO.setGroupName(r.getString("GROUP_NAME"));
				resultList.add(resultDTO);				

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
	}
		

		
		return resultList;
	}
	
	public Boolean isValidApp(String appID,String ssc_roll,String eiin)
	{
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="SELECT A.EIIN,\n" +
                "       A.APPLICATION_ID,\n" +
                "       B.SHIFT_NAME,\n" +
                "       C.VERSION_NAME,\n" +
                "       D.GROUP_NAME,\n" +
                "       A.MERIT_TYPE,\n" +
                "       A.PRIORITY_ORDER,\n" +
                "       A.STATUS,\n" +
                "       A.QUOTA_TYPE, " +
                "       E.STUDENT_NAME,\n" +
                "       E.SSC_ROLL_NO,\n" +
                "       E.SSC_BOARD_ID,\n" +
                "       E.SSC_BOARD_NAME,\n" +
                "       E.SSC_PASSING_YEAR,\n" +
                "		E.MOBILE_NUMBER \n" +
                "  FROM BOARD_RESULT a,\n" +
                "       MST_SHIFT b,\n" +
                "       MST_VERSION c,\n" +
                "       MST_GROUP d,\n" +
                "       APPLICATION_INFO e\n" +
                " WHERE  A.APPLICATION_ID in (?) AND E.SSC_ROLL_NO =? AND  A.EIIN = ? AND STATUS='Not Approved'  AND MERIT_TYPE IN ('1')\n" +
                "       AND a.SHIFT_ID = b.SHIFT_ID\n" +
                "       AND A.VERSION_ID = C.VERSION_ID\n" +
                "       AND A.GROUP_ID = D.GROUP_ID\n" +
                "       AND A.APPLICATION_ID = E.APPLICATION_ID order by A.APPLICATION_ID";
		

				PreparedStatement stmt = null;
				ResultSet r = null;
				try
				{
					stmt = conn.prepareStatement(sql);
					
					stmt.setString(1, appID);
					stmt.setString(2, ssc_roll);
					stmt.setString(3, eiin);
					
					r = stmt.executeQuery();
					
					while(r.next())
					{
						return true;
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
				
	    return false;	
	}
	
	public List<ApplicantInfoDTO> getNonApprovedStudentListOfMeritFromXLS(String applicationID,String userId){
		ApplicantInfoDTO nonApprovedStudentOfMeritDTO = null;
		List<ApplicantInfoDTO> showNonApproveStudentOfMeritList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		
		String sql ="SELECT A.EIIN,\n" +
                "       A.APPLICATION_ID,\n" +
                "       B.SHIFT_NAME,\n" +
                "       C.VERSION_NAME,\n" +
                "       D.GROUP_NAME,\n" +
                "       A.MERIT_TYPE,\n" +
                "       A.PRIORITY_ORDER,\n" +
                "       A.STATUS,\n" +
                "       A.QUOTA_TYPE, " +
                "       E.STUDENT_NAME,\n" +
                "       E.SSC_ROLL_NO,\n" +
                "       E.SSC_BOARD_ID,\n" +
                "       E.SSC_BOARD_NAME,\n" +
                "       E.SSC_PASSING_YEAR,\n" +
                "		E.MOBILE_NUMBER \n" +
                "  FROM BOARD_RESULT a,\n" +
                "       MST_SHIFT b,\n" +
                "       MST_VERSION c,\n" +
                "       MST_GROUP d,\n" +
                "       APPLICATION_INFO e\n" +
                " WHERE    a.APPLICATION_ID in ("+applicationID+") AND A.EIIN = ? AND STATUS='Not Approved' AND MERIT_TYPE IN ('1')\n" +
                "       AND a.SHIFT_ID = b.SHIFT_ID\n" +
                "       AND A.VERSION_ID = C.VERSION_ID\n" +
                "       AND A.GROUP_ID = D.GROUP_ID\n" +
                "       AND A.APPLICATION_ID = E.APPLICATION_ID order by A.APPLICATION_ID"; 
		

		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			
			r = stmt.executeQuery();
			
			showNonApproveStudentOfMeritList = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				nonApprovedStudentOfMeritDTO = new ApplicantInfoDTO();
				
				nonApprovedStudentOfMeritDTO.setEiinCode(r.getString("EIIN"));
				nonApprovedStudentOfMeritDTO.setApplicationID(r.getString("APPLICATION_ID"));
				nonApprovedStudentOfMeritDTO.setShiftName(r.getString("SHIFT_NAME"));
				nonApprovedStudentOfMeritDTO.setVersionName(r.getString("VERSION_NAME"));
				nonApprovedStudentOfMeritDTO.setGroupName(r.getString("GROUP_NAME"));
				nonApprovedStudentOfMeritDTO.setMeritType(r.getString("MERIT_TYPE"));
				nonApprovedStudentOfMeritDTO.setPriorityOrder(r.getString("PRIORITY_ORDER"));
				nonApprovedStudentOfMeritDTO.setAdmitStatus(r.getString("STATUS"));
				nonApprovedStudentOfMeritDTO.setAssignedQuota(r.getString("QUOTA_TYPE"));
				nonApprovedStudentOfMeritDTO.setMobileNo(r.getString("MOBILE_NUMBER"));
				
				nonApprovedStudentOfMeritDTO.setApplicantName(r.getString("STUDENT_NAME"));
				nonApprovedStudentOfMeritDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				nonApprovedStudentOfMeritDTO.setBoardID(r.getString("SSC_BOARD_ID"));
				nonApprovedStudentOfMeritDTO.setBoardName(r.getString("SSC_BOARD_NAME"));
				nonApprovedStudentOfMeritDTO.setSscPassingYear(r.getString("SSC_PASSING_YEAR"));
				
				
				showNonApproveStudentOfMeritList.add(nonApprovedStudentOfMeritDTO);
				

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
		
		return showNonApproveStudentOfMeritList;
	}
	
	
	public List<ApplicantInfoDTO> getTotalAdmittedStudentSVGWise(String shiftId,String versionId,String groupId,String userId){
		ApplicantInfoDTO totalApprovedStudentOfMeritDTO = null;
		List<ApplicantInfoDTO> totalApprovedStudentListOfMerit = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql="  SELECT *\n" +
	             "    FROM (SELECT app.APPLICATION_ID,\n" +
	             "                 app.SSC_ROLL_NO,\n" +
	             "                 board.BOARD_NAME_ENG,\n" +
	             "                 app.SSC_PASSING_YEAR,\n" +
	             "                 ssc.STUDENT_NAME,\n" +
	             "                 ssc.GPA,\n" +
	             "                 result.MERIT_TYPE,\n" +
	             "                 result.QUOTA_TYPE,\n" +
	             "                 result.EIIN,\n" +
	             "                 result.SHIFT_ID,\n" +
	             "                 result.VERSION_ID,\n" +
	             "                 grp.GROUP_NAME,\n" +
	             "                 DECODE (result.SHIFT_ID,\n" +
	             "                         1, 'Morning',\n" +
	             "                         2, 'Day',\n" +
	             "                         3, 'Evening')\n" +
	             "                    shift_name,\n" +
	             "                 DECODE (result.VERSION_ID,  1, 'Bangla',  2, 'English')\n" +
	             "                    version_name\n" +
	             "            FROM board_result_merit result,\n" +
	             "                 application_info app,\n" +
	             "                 board_data_ssc ssc,\n" +
	             "                 mst_edu_board board,\n" +
	             "                 mst_group grp\n" +
	             "           WHERE     app.APPLICATION_ID = result.APPLICATION_ID\n" +
	             "                 AND app.SSC_ROLL_NO = ssc.ROLL_NO\n" +
	             "                 AND app.SSC_BOARD_ID = ssc.BOARD_ID\n" +
	             "                 AND app.SSC_PASSING_YEAR = ssc.PASSING_YEAR\n" +
	             "                 AND app.SSC_BOARD_ID = board.BOARD_ID\n" +
	             "                 AND result.GROUP_ID = grp.GROUP_ID\n" +
	             "                 AND RESULT.STATUS = 'College Received'\n" +
	             "                 AND result.SHIFT_ID =?\n" +
	             "                 AND result.VERSION_ID =?\n" +
	             "                 AND result.GROUP_ID =?\n" +
	             "                 AND result.EIIN =?)" +
/*	             "          UNION\n" +
	             "          SELECT app.APPLICATION_ID,\n" +
	             "                 app.SSC_ROLL_NO,\n" +
	             "                 board.BOARD_NAME_ENG,\n" +
	             "                 app.SSC_PASSING_YEAR,\n" +
	             "                 ssc.STUDENT_NAME,\n" +
	             "                 ssc.GPA,\n" +
	             "                 result.MERIT_TYPE,\n" +
	             "                 result.QUOTA_TYPE,\n" +
	             "                 result.EIIN,\n" +
	             "                 result.SHIFT_ID,\n" +
	             "                 result.VERSION_ID,\n" +
	             "                 grp.GROUP_NAME,\n" +
	             "                 DECODE (result.SHIFT_ID,\n" +
	             "                         1, 'Morning',\n" +
	             "                         2, 'Day',\n" +
	             "                         3, 'Evening')\n" +
	             "                    shift_name,\n" +
	             "                 DECODE (result.VERSION_ID,  1, 'Bangla',  2, 'English')\n" +
	             "                    version_name\n" +
	             "            FROM board_result_waiting result,\n" +
	             "                 application_info_new app,\n" +
	             "                 board_data_ssc ssc,\n" +
	             "                 mst_edu_board board,\n" +
	             "                 mst_group grp\n" +
	             "           WHERE     app.APPLICATION_ID = result.APPLICATION_ID\n" +
	             "                 AND app.SSC_ROLL_NO = ssc.ROLL_NO\n" +
	             "                 AND app.SSC_BOARD_ID = ssc.BOARD_ID\n" +
	             "                 AND app.SSC_PASSING_YEAR = ssc.PASSING_YEAR\n" +
	             "                 AND app.SSC_BOARD_ID = board.BOARD_ID\n" +
	             "                 AND result.GROUP_ID = grp.GROUP_ID\n" +
	             "                 AND RESULT.STATUS = 'College Received'\n" +
	             "                 AND result.SHIFT_ID =?\n" +
	             "                 AND result.VERSION_ID = ?\n" +
	             "                 AND result.GROUP_ID = ?\n" +
	             "                 AND result.EIIN =?)\n" +*/
	             "order by MERIT_TYPE,decode(QUOTA_TYPE,'GENERAL','1',QUOTA_TYPE),SSC_ROLL_NO";
		

		
/*		String sql="  SELECT *\n" +
	             "    FROM (SELECT app.APPLICATION_ID,\n" +
	             "                 app.SSC_ROLL_NO,\n" +
	             "                 board.BOARD_NAME_ENG,\n" +
	             "                 app.SSC_PASSING_YEAR,\n" +
	             "                 ssc.STUDENT_NAME,\n" +
	             "                 ssc.GPA,\n" +
	             "                 result.MERIT_TYPE,\n" +
	             "                 result.QUOTA_TYPE,\n" +
	             "                 result.EIIN,\n" +
	             "                 result.SHIFT_ID,\n" +
	             "                 result.VERSION_ID,\n" +
	             "                 grp.GROUP_NAME,\n" +
	             "                 DECODE (result.SHIFT_ID,\n" +
	             "                         1, 'Morning',\n" +
	             "                         2, 'Day',\n" +
	             "                         3, 'Evening')\n" +
	             "                    shift_name,\n" +
	             "                 DECODE (result.VERSION_ID,  1, 'Bangla',  2, 'English')\n" +
	             "                    version_name\n" +
	 //            "                 app.MOBILE_NUMBER\n" +
	             "            FROM board_result_merit result,\n" +
	             "                 application_info app,\n" +
	             "                 board_data_ssc ssc,\n" +
	             "                 mst_edu_board board,\n" +
	             "                 mst_group grp\n" +
	             "           WHERE     app.APPLICATION_ID = result.APPLICATION_ID\n" +
	             "                 AND app.SSC_ROLL_NO = ssc.ROLL_NO\n" +
	             "                 AND app.SSC_BOARD_ID = ssc.BOARD_ID\n" +
	             "                 AND app.SSC_PASSING_YEAR = ssc.PASSING_YEAR\n" +
	             "                 AND app.SSC_BOARD_ID = board.BOARD_ID\n" +
	             "                 AND result.GROUP_ID = grp.GROUP_ID\n" +
	             "                 AND (result.application_id, RESULT.MERIT_TYPE) IN\n" +
	             "                        (  SELECT application_id, MAX (MERIT_TYPE)\n" +
	             "                             FROM board_result\n" +
	             "                         GROUP BY application_id)\n" +
	             "                 AND RESULT.STATUS = 'College Received'\n" +
	             "                 AND result.SHIFT_ID =?\n" +
	             "                 AND result.VERSION_ID =?\n" +
	             "                 AND result.GROUP_ID =?\n" +
	             "                 AND result.EIIN =?\n" +
	             "          UNION\n" +
	             "          SELECT app.APPLICATION_ID,\n" +
	             "                 app.SSC_ROLL_NO,\n" +
	             "                 board.BOARD_NAME_ENG,\n" +
	             "                 app.SSC_PASSING_YEAR,\n" +
	             "                 ssc.STUDENT_NAME,\n" +
	             "                 ssc.GPA,\n" +
	             "                 result.MERIT_TYPE,\n" +
	             "                 result.QUOTA_TYPE,\n" +
	             "                 result.EIIN,\n" +
	             "                 result.SHIFT_ID,\n" +
	             "                 result.VERSION_ID,\n" +
	             "                 grp.GROUP_NAME,\n" +
	             "                 DECODE (result.SHIFT_ID,\n" +
	             "                         1, 'Morning',\n" +
	             "                         2, 'Day',\n" +
	             "                         3, 'Evening')\n" +
	             "                    shift_name,\n" +
	             "                 DECODE (result.VERSION_ID,  1, 'Bangla',  2, 'English')\n" +
	             "                    version_name\n" +
//	             "                 app.MOBILE_NUMBER\n" +
	             "            FROM board_result result,\n" +
	             "                 application_info_new app,\n" +
	             "                 board_data_ssc ssc,\n" +
	             "                 mst_edu_board board,\n" +
	             "                 mst_group grp\n" +
	             "           WHERE     app.APPLICATION_ID = result.APPLICATION_ID\n" +
	             "                 AND app.SSC_ROLL_NO = ssc.ROLL_NO\n" +
	             "                 AND app.SSC_BOARD_ID = ssc.BOARD_ID\n" +
	             "                 AND app.SSC_PASSING_YEAR = ssc.PASSING_YEAR\n" +
	             "                 AND app.SSC_BOARD_ID = board.BOARD_ID\n" +
	             "                 AND result.GROUP_ID = grp.GROUP_ID\n" +
	             "                 AND RESULT.STATUS = 'College Received'\n" +
	             "                 AND result.SHIFT_ID =?\n" +
	             "                 AND result.VERSION_ID = ?\n" +
	             "                 AND result.GROUP_ID = ?\n" +
	             "                 AND result.EIIN =?)\n" +
	             "ORDER BY MERIT_TYPE,\n" +
	             "         DECODE (QUOTA_TYPE,\n" +
	             "                 'GEN', '1',\n" +
	             "                 'D_G', '2',\n" +
	             "                 'E_G', '3',\n" +
	             "                 'F_G', '4',\n" +
	             "                 'S_G', '5',\n" +
	             "                 QUOTA_TYPE),\n" +
	             "         APPLICATION_ID";*/
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, shiftId);
			stmt.setString(2, versionId);
			stmt.setString(3, groupId);
			stmt.setString(4, userId);
			
/*			stmt.setString(5, shiftId);
			stmt.setString(6, versionId);
			stmt.setString(7, groupId);
			stmt.setString(8, userId);*/
			
			r = stmt.executeQuery();
			
			totalApprovedStudentListOfMerit = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				totalApprovedStudentOfMeritDTO = new ApplicantInfoDTO();
				
				totalApprovedStudentOfMeritDTO.setEiinCode(r.getString("EIIN"));
				totalApprovedStudentOfMeritDTO.setApplicationID(r.getString("APPLICATION_ID"));
				totalApprovedStudentOfMeritDTO.setShiftName(r.getString("SHIFT_NAME"));
				totalApprovedStudentOfMeritDTO.setVersionName(r.getString("VERSION_NAME"));
				totalApprovedStudentOfMeritDTO.setGroupName(r.getString("GROUP_NAME"));
				totalApprovedStudentOfMeritDTO.setMeritType(r.getString("MERIT_TYPE"));			
				totalApprovedStudentOfMeritDTO.setAssignedQuota(r.getString("QUOTA_TYPE"));
		//		totalApprovedStudentOfMeritDTO.setMobileNo(r.getString("MOBILE_NUMBER"));				
				totalApprovedStudentOfMeritDTO.setApplicantName(r.getString("STUDENT_NAME"));
				totalApprovedStudentOfMeritDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				totalApprovedStudentOfMeritDTO.setBoardName(r.getString("BOARD_NAME_ENG"));
				totalApprovedStudentOfMeritDTO.setSscPassingYear(r.getString("SSC_PASSING_YEAR"));

				totalApprovedStudentListOfMerit.add(totalApprovedStudentOfMeritDTO);
				

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
		
		return totalApprovedStudentListOfMerit;
	}
	
	
	public List<CollegeDTO> getNonReceivedList(String userId){
		CollegeDTO collegeDTO = null;
		List<CollegeDTO> collegeList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql="  SELECT DISTINCT result.eiin,\n" +
                "                  coll.COLLEGE_NAME,\n" +
                "                  coll.MOBILE_NO,\n" +
                "                  dist.DIST_NAME\n" +
                "    FROM mst_college coll, mst_district dist, board_result_merit result\n" +
                "   WHERE     status = 'Not Approved'\n" +
                "         AND coll.DIST_ID = dist.DIST_ID\n" +
                "         AND result.EIIN = coll.EIIN\n" +
                "         AND result.eiin IN (SELECT eiin\n" +
                "                               FROM mst_college\n" +
                "                              WHERE board_id =?)\n" +
                "         AND result.eiin NOT IN (SELECT DISTINCT eiin\n" +
                "                                   FROM board_result_merit\n" +
                "                                  WHERE     status = 'College Received'\n" +
                "                                        AND eiin IN (SELECT eiin\n" +
                "                                                       FROM mst_college\n" +
                "                                                      WHERE board_id =?))\n" +
                "ORDER BY dist.DIST_NAME";
		
/*		String sql ="select coll.EIIN, coll.COLLEGE_NAME, coll.MOBILE_NO, dist.DIST_NAME \n" +
				"from mst_college coll, mst_district dist \n" +
				" where coll.DIST_ID = dist.DIST_ID and coll.BOARD_ID = ?";*/
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setString(2, userId);
			
			r = stmt.executeQuery();
			
			collegeList = new ArrayList<CollegeDTO>();
			while(r.next())
			{
				collegeDTO = new CollegeDTO();
				
				collegeDTO.setEiin(r.getString("EIIN"));
				collegeDTO.setCollege_name(r.getString("COLLEGE_NAME"));
				collegeDTO.setCollegeMobile(r.getString("MOBILE_NO") == null ? " " : r.getString("MOBILE_NO"));
				collegeDTO.setDist_name(r.getString("DIST_NAME"));
				collegeList.add(collegeDTO);
				

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
		
		return collegeList;
	}
	
	
	
	public List<CollegeDTO> getReceivedList(String userId){
		CollegeDTO collegeDTO = null;
		List<CollegeDTO> collegeList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql="select t1.eiin, coll.COLLEGE_NAME, DECODE(coll.MOBILE_NO, NULL, '-', coll.MOBILE_NO) MOBILE_NO,dist.DIST_NAME,\n" +
                "DECODE(TOTAL_RESULT, NULL, 0, TOTAL_RESULT) TOTAL_RESULT, \n" +
                "DECODE(NOT_APPROVED, NULL, 0, NOT_APPROVED) NOT_APPROVED, \n" +
                "DECODE(ADMISSION_CANCELLED, NULL, 0, ADMISSION_CANCELLED) ADMISSION_CANCELLED, \n" +
                "DECODE(COLLEGE_RECEIVED, NULL, 0, COLLEGE_RECEIVED) COLLEGE_RECEIVED from (\n" +
                "select * from (\n" +
                "select eiin, status, count(*) appcount from board_result_merit group by eiin, status order by eiin\n" +
                ") PIVOT (\n" +
                "  max(appcount)\n" +
                "  FOR status IN ('Not Approved' not_approved,'College Received' College_Received,'Admission Cancelled' ADMISSION_CANCELLED)\n" +
                ")) t1, mst_college coll,mst_district dist, (select eiin, count(*) total_result from board_result_merit group by eiin) t2\n" +
                "where t1.eiin = coll.eiin\n" +
                "and t1.eiin = t2.eiin\n" +
                "AND coll.DIST_ID = dist.DIST_ID\n" +
                "AND COLLEGE_RECEIVED > 0\n" +
                "AND COLL.BOARD_ID =?\n" +
                "ORDER BY dist.DIST_NAME,coll.COLLEGE_NAME";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			
			
			r = stmt.executeQuery();
			
			collegeList = new ArrayList<CollegeDTO>();
			while(r.next())
			{
				collegeDTO = new CollegeDTO();
				
				collegeDTO.setEiin(r.getString("eiin"));
				collegeDTO.setCollege_name(r.getString("COLLEGE_NAME"));
				collegeDTO.setCollegeMobile(r.getString("MOBILE_NO") == null ? " " : r.getString("MOBILE_NO"));
				collegeDTO.setDist_name(r.getString("DIST_NAME"));
				
				collegeDTO.setTotalResult(r.getString("TOTAL_RESULT"));
				collegeDTO.setNotApproved(r.getString("NOT_APPROVED"));
				collegeDTO.setCollegeRceived(r.getString("COLLEGE_RECEIVED"));
				collegeDTO.setAdmissionCancelled(r.getString("ADMISSION_CANCELLED"));
				collegeList.add(collegeDTO);
				

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
		
		return collegeList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<ApplicantInfoDTO> getDataForRegistration(String shift_id,String version_id,String group_id,String userId){
		ApplicantInfoDTO nonRegistered = null;
		List<ApplicantInfoDTO> lstNonRegistered = null;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="  SELECT A.EIIN," +
				"         A.APPLICANT_ID," +
				"         B.SHIFT_ID," +
				"         B.SHIFT_NAME," +
				"         C.VERSION_ID," +
				"         C.VERSION_NAME," +
				"         D.GROUP_ID," +
				"         D.GROUP_NAME," +
				"         A.MERIT_TYPE," +
				"         A.MERIT_POSITTION," +
				"         A.STATUS," +
				"         QUOTA_TYPE," +
				"         A.PIN_NUMBER," +
				"         E.REG_NO," +
				"         E.STUDENT_NAME," +
				"         E.ROLL_NO," +
				"         E.BOARD_ID," +
				"         E.BOARD_NAME," +
				"         E.PASS_YEAR," +
				"         A.registered" +
				"    FROM BOARD_RESULT a," +
				"         MST_SHIFT b," +
				"         MST_VERSION c," +
				"         MST_GROUP d," +
				"         board_data_ssc e" +
				"   WHERE     A.EIIN = ?" +
				"         AND COLLEGE_RECEIVE = 'College Received'" +
				"         AND a.SHIFT_ID = ?" +
				"         AND A.VERSION_ID = ?" +
				"         AND A.GROUP_ID = ?" +
				"         AND A.registered = 'N'" +
				"         AND a.SHIFT_ID = b.SHIFT_ID" +
				"         AND A.VERSION_ID = C.VERSION_ID" +
				"         AND A.GROUP_ID = D.GROUP_ID" +
				"         and A.APPLICANT_ID=E.BOARD_ID||E.pass_year||E.ROLL_NO" +
				" ORDER BY A.MERIT_TYPE," +
				"         B.SHIFT_NAME," +
				"         C.VERSION_NAME," +
				"         D.GROUP_NAME," +
				"         E.BOARD_ID," +
				"         E.PASS_YEAR," +
				"         E.ROLL_NO";
				
		
		
//		System.out.println("delwar:"+sql);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setString(2, shift_id);
			stmt.setString(3, version_id);
			stmt.setString(4, group_id);
			r = stmt.executeQuery();
			lstNonRegistered = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				nonRegistered = new ApplicantInfoDTO();
				
				nonRegistered.setEiinCode(r.getString("EIIN"));
				nonRegistered.setApplicationID(r.getString("APPLICANt_ID"));
				
				nonRegistered.setShiftID(r.getString("SHIFT_ID"));
				nonRegistered.setShiftName(r.getString("SHIFT_NAME"));
				
				nonRegistered.setVersionID(r.getString("VERSION_ID"));
				nonRegistered.setVersionName(r.getString("VERSION_NAME"));
				
				nonRegistered.setGroupId(r.getString("GROUP_ID"));
				nonRegistered.setGroupName(r.getString("GROUP_NAME"));
				
				nonRegistered.setMeritType(r.getString("MERIT_TYPE"));
				nonRegistered.setPriorityOrder(r.getString("MERIT_POSITTION"));
				nonRegistered.setAdmitStatus(r.getString("STATUS"));
				nonRegistered.setAssignedQuota(r.getString("QUOTA_TYPE"));
				nonRegistered.setPinNumber(r.getString("PIN_NUMBER"));
		//		nonApprovedStudentOfMeritDTO.setMobileNo(r.getString("MOBILE_NUMBER"));
				
				nonRegistered.setApplicantName(r.getString("STUDENT_NAME"));
				nonRegistered.setSscRollNo(r.getString("ROLL_NO"));
				nonRegistered.setBoardID(r.getString("BOARD_ID"));
				nonRegistered.setBoardName(r.getString("BOARD_NAME"));
				nonRegistered.setSscPassingYear(r.getString("PASS_YEAR"));
				nonRegistered.setRegistered(r.getString("REGISTERED"));
				nonRegistered.setSscReg(r.getString("REG_NO"));
				
				lstNonRegistered.add(nonRegistered);
				

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
		
		return lstNonRegistered;
	}
	public List<ApplicantInfoDTO> getRegisteredData(String shift_id,String version_id,String group_id,String userId, String sboardid){
		ApplicantInfoDTO nonRegistered = null;
		List<ApplicantInfoDTO> lstNonRegistered = null;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="";
        
		if(!sboardid.equalsIgnoreCase("18"))
			sql = "select T1.ESIF,T2.STUDENT_NAME,T2.STUDENT_NAME||'<br>'||T2.FATHER_NAME||'<br>'||T2.MOTHER_NAME STUDENT_NAME1,T1.CLASS_ROLL,T1.SECTIONS,T1.SECTIONS||'<br>'|| T1.CLASS_ROLL   " +
					" SECTIONS1,T2.BIRTH_DATE,T2.GENDER,T2.GENDER||'<br>'||to_char(T2.BIRTH_DATE,'DDMMYY') GENDER1," +
					" CASE t1.GROUP_ID when 0 then DECODE(LENGTH(SUB7), 3 ,to_char(to_number(SUB7)) ,to_char(to_number(substr(SUB7,1,3)))) else to_char(to_number(T1.SUB7)) end ||'<br>'||" +
					" CASE t1.GROUP_ID when 0 then DECODE(LENGTH(SUB7), 3 ,to_char(to_number(SUB7)+1) ,to_char(to_number(substr(SUB7,5,3)))) else to_char(to_number(T1.SUB7)+1) end optsubject," +
					" T2.BOARD_NAME,T2.ROLL_NO, T2.PASS_YEAR,T2.REG_NO,T2.PASS_YEAR||'<br>'||T2.ROLL_NO||'<br>'||T2.BOARD_NAME||'<br>'||T2.REG_NO PASSING_YEAR1,T2.BOARD_ID,T1.EIIN,T1.applicant_id,registered," +
					" '101'||','||'102'||','||'107'||','||'108'||','||'275'||','||" +
					" CASE t1.GROUP_ID when 0 then '174' when 8 then '277' when 5 then '279' when 6 then '249' when 7 then '216' else to_char(to_number(T1.SUB4)) end ||','||" +
					" CASE t1.GROUP_ID when 0 then '175' when 8 then '278' when 5 then '280' when 6 then '250' when 7 then '217' else to_char(to_number(T1.SUB4)+1) end ||',<br>'||" +
					" CASE t1.GROUP_ID when 0 then '176' when 8 then '253' when 5 then '282' when 6 then '267' when 7 then '218' else to_char(to_number(T1.SUB5)) end ||','||" +
					" CASE t1.GROUP_ID when 0 then '177' when 8 then '254' when 5 then '283' when 6 then '268' when 7 then '219' else to_char(to_number(T1.SUB5)+1) end ||','||" +
					" CASE t1.GROUP_ID when 5 then '298' when 6 then '133' else to_char(to_number(T1.SUB6)) end ||','||" +
					" CASE t1.GROUP_ID when 5 then '299' when 6 then '134' else to_char(to_number(T1.SUB6)+1) end ||','||" +
					" CASE t1.GROUP_ID when 0 then DECODE(LENGTH(SUB7), 3 ,to_char(to_number(SUB7)) ,to_char(to_number(substr(SUB7,1,3)))) else to_char(to_number(T1.SUB7)) end ||','||" +
					" CASE t1.GROUP_ID when 0 then DECODE(LENGTH(SUB7), 3 ,to_char(to_number(SUB7)+1) ,to_char(to_number(substr(SUB7,5,3)))) else to_char(to_number(T1.SUB7)+1) end allsubject" +
					" from board_registration t1, board_data_ssc t2, BOARD_RESULT T3 where T1.applicant_id = T3.applicant_id" +
					" AND T2.BOARD_ID||T2.pass_year||T2.ROLL_NO = T1.applicant_id and T1.EIIN=T3.EIIN AND T1.EIIN=? AND T3.SHIFT_ID = ? AND T3.VERSION_ID = ? AND T3.GROUP_ID = ?" +
					" order by T1.ESIF, T1.CLASS_ROLL";
		else
			sql = "select T1.ESIF,T2.STUDENT_NAME,T2.STUDENT_NAME||'<br>'||T2.FATHER_NAME||'<br>'||T2.MOTHER_NAME STUDENT_NAME1,T1.CLASS_ROLL,T1.SECTIONS,T1.SECTIONS||'<br>'|| T1.CLASS_ROLL   " +
					" SECTIONS1,T2.BIRTH_DATE,T2.GENDER,T2.GENDER||'<br>'||to_char(T2.BIRTH_DATE,'DDMMYY') GENDER1," +
					" to_char(to_number(T1.SUB7)+1) optsubject," +
					" T2.BOARD_NAME,T2.ROLL_NO, T2.PASS_YEAR,T2.REG_NO,T2.PASS_YEAR||'<br>'||T2.ROLL_NO||'<br>'||T2.BOARD_NAME||'<br>'||T2.REG_NO PASSING_YEAR1,T2.BOARD_ID,T1.EIIN,T1.applicant_id,registered," +
					" CASE t1.GROUP_ID when 9 then '236,237,238,239,240,201,202<br>,203,204,205,206,209,210,'||T1.SUB7" +
					" when 10 then '236,237,238,239,240,201,202<br>,203,223,232,233,234,235,'||T1.SUB7" +
					" else '236,237,238,239,201,202,203<br>,223,224,225,226,227'||T1.SUB6||T1.SUB7 end allsubject" +
					" from board_registration t1, board_data_ssc t2, BOARD_RESULT T3 where T1.applicant_id = T3.applicant_id" +
					" AND T2.BOARD_ID||T2.pass_year||T2.ROLL_NO = T1.applicant_id and T1.EIIN=T3.EIIN AND T1.EIIN=? AND T3.SHIFT_ID = ? AND T3.VERSION_ID = ? AND T3.GROUP_ID = ?" +
				" order by T1.ESIF, T1.CLASS_ROLL";
			
		//		System.out.println("delwar:"+sql);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
//System.out.println(userId+":"+shift_id+":"+version_id+":"+group_id+":");
			stmt.setString(1, userId);
			stmt.setString(2, shift_id);
			stmt.setString(3, version_id);
			stmt.setString(4, group_id);
				r = stmt.executeQuery();
			lstNonRegistered = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				nonRegistered = new ApplicantInfoDTO();
			
				nonRegistered.setEiinCode(r.getString("EIIN"));
				nonRegistered.setEsif(r.getString("esif"));
				nonRegistered.setApplicationID(r.getString("applicant_id"));
				
			
				nonRegistered.setApplicantName(r.getString("STUDENT_NAME"));
				nonRegistered.setDob(r.getString("BIRTH_DATE"));
				nonRegistered.setGender(r.getString("gender"));
				
				nonRegistered.setBoardID(r.getString("BOARD_ID"));
				
				nonRegistered.setSscReg(r.getString("REG_NO"));
				
				nonRegistered.setBoardName(r.getString("PASS_YEAR"));
				nonRegistered.setSscRollNo(r.getString("PASS_YEAR"));
				nonRegistered.setSscPassingYear(r.getString("PASS_YEAR"));
				nonRegistered.setRegistered(r.getString("REGISTERED"));
				nonRegistered.setAllsubject(r.getString("allsubject"));
				nonRegistered.setOptsubject(r.getString("optsubject"));
				nonRegistered.setClass_roll(r.getString("class_roll"));
				nonRegistered.setSections(r.getString("sections"));
				
				
				nonRegistered.setApplicantName1(r.getString("STUDENT_NAME1"));
				nonRegistered.setSections1(r.getString("sections1"));
				nonRegistered.setGender1(r.getString("gender1"));
				nonRegistered.setSscPassingYear1(r.getString("PASSING_YEAR1"));
				
				lstNonRegistered.add(nonRegistered);
				

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
		
		return lstNonRegistered;
	}	
	
	public ApplicantInfoDTO getIndividualData(String appId){
		ApplicantInfoDTO nonRegistered = null;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="select FATHER_NAME,MOTHER_NAME,to_char(BIRTH_DATE,'dd/mm/yyyy') BIRTH_DATE,GENDER,TO_CHAR(gpa, '9.99') GPA,T3.GROUP_NAME,T4.VERSION_NAME," +
				" T5.SHIFT_NAME,merit_type,sessions from board_data_ssc t1,board_result t2," +
				" mst_group t3,mst_version t4,mst_shift t5 where t2.applicant_id=?" +
				" AND T1.BOARD_ID = substr(T2.applicant_id,1,2)" +
				"         and T1.pass_year = substr(T2.applicant_id,3,4)" +
				"         and T1.ROLL_NO = substr(T2.applicant_id,7) and T2.GROUP_ID=T3.GROUP_ID and" +
				" T2.VERSION_ID=T4.VERSION_ID and T2.SHIFT_ID=T5.SHIFT_ID and" +
				" COLLEGE_RECEIVE='College Received' order by merit_type desc";
		
		
//		System.out.println("delwar:"+appId);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, appId);
			r = stmt.executeQuery();
			if(r.next())
			{
				nonRegistered = new ApplicantInfoDTO();
				nonRegistered.setShiftName(r.getString("SHIFT_NAME"));
				nonRegistered.setVersionName(r.getString("VERSION_NAME"));
				nonRegistered.setGroupName(r.getString("GROUP_NAME"));
				nonRegistered.setFatherName(r.getString("FATHER_NAME"));
				nonRegistered.setMotherName(r.getString("MOTHER_NAME"));
				nonRegistered.setDob(r.getString("BIRTH_DATE"));
				nonRegistered.setGender(r.getString("GENDER"));
				nonRegistered.setGpa(r.getString("GPA"));
				nonRegistered.setSessions(r.getString("sessions"));
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
		
		return nonRegistered;
	}	
	
	public List<ApplicantInfoDTO> getManualDataForRegUpdate(String shift_id,String version_id,String group_id,String userId, String sboardid){
		ApplicantInfoDTO nonRegistered = null;
		List<ApplicantInfoDTO> lstNonRegistered = null;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="";
 		if(sboardid!="18")
			sql = "select T1.ESIF,T2.STUDENT_NAME,T2.STUDENT_NAME||'<br>'||T2.FATHER_NAME||'<br>'||T2.MOTHER_NAME STUDENT_NAME1,T1.CLASS_ROLL,T1.SECTIONS,T1.SECTIONS||'<br>'|| T1.CLASS_ROLL  " +
					" SECTIONS1,T2.BIRTH_DATE,T2.GENDER,T2.GENDER||'<br>'||to_char(T2.BIRTH_DATE,'DDMMYY') GENDER1," +
					" CASE t1.GROUP_ID when 0 then DECODE(LENGTH(SUB7), 3 ,to_char(to_number(SUB7)) ,to_char(to_number(substr(SUB7,1,3)))) else to_char(to_number(T1.SUB7)) end ||'<br>'||" +
					" CASE t1.GROUP_ID when 0 then DECODE(LENGTH(SUB7), 3 ,to_char(to_number(SUB7)+1) ,to_char(to_number(substr(SUB7,5,3)))) else to_char(to_number(T1.SUB7)+1) end optsubject," +
					" T2.BOARD_NAME_ENG,T2.ROLL_NO, T2.PASSING_YEAR,T2.REG_NO,T2.PASSING_YEAR||'<br>'||T2.ROLL_NO||'<br>'||T2.BOARD_NAME_ENG||'<br>'||T2.REG_NO PASSING_YEAR1,T2.BOARD_ID,T1.EIIN,T1.APPLICATION_ID,registered," +
				"'101'||','||'102'||','||'107'||','||'108'||','||'275'||','||" +
				"CASE t1.GROUP_ID when 0 then '174' when 8 then '277' when 5 then '279' when 6 then '249' when 7 then '216' else to_char(to_number(T1.SUB4)) end ||',<br>'||" +
				"CASE t1.GROUP_ID when 0 then '175' when 8 then '278' when 5 then '280' when 6 then '250' when 7 then '217' else to_char(to_number(T1.SUB4)+1) end ||','||" +
				"CASE t1.GROUP_ID when 0 then '176' when 8 then '253' when 5 then '282' when 6 then '267' when 7 then '218' else to_char(to_number(T1.SUB5)) end ||','||" +
				"CASE t1.GROUP_ID when 0 then '177' when 8 then '254' when 5 then '283' when 6 then '268' when 7 then '219' else to_char(to_number(T1.SUB5)+1) end ||','||" +
				"CASE t1.GROUP_ID when 5 then '298' when 6 then '133' else to_char(to_number(T1.SUB6)) end ||','||" +
				"CASE t1.GROUP_ID when 5 then '299' when 6 then '134' else to_char(to_number(T1.SUB6)+1) end ||','||" +
				"CASE t1.GROUP_ID when 0 then DECODE(LENGTH(SUB7), 3 ,to_char(to_number(SUB7)) ,to_char(to_number(substr(SUB7,1,3)))) else to_char(to_number(T1.SUB7)) end ||','||" +
				"CASE t1.GROUP_ID when 0 then DECODE(LENGTH(SUB7), 3 ,to_char(to_number(SUB7)+1) ,to_char(to_number(substr(SUB7,5,3)))) else to_char(to_number(T1.SUB7)+1) end allsubject" +
				"  from board_registration t1, board_data_ssc t2, COLLEGE3DATAENTRY T3 where T2.roll_no = T3.roll_no and T2.board_id = T3.board_id and T2.passing_year = T3.passing_year" +
				"  AND T1.APPLICATION_ID=T3.APPID and T1.EIIN=T3.EIIN AND T1.EIIN=? AND T3.SHIFT_ID = ? AND T3.VERSION_ID = ? AND T3.GROUP_ID = ?" +
				" order by T1.ESIF";
		else
			sql = "select T1.ESIF,T2.STUDENT_NAME,T2.STUDENT_NAME||'<br>'||T2.FATHER_NAME||'<br>'||T2.MOTHER_NAME STUDENT_NAME1,T1.CLASS_ROLL,T1.SECTIONS,T1.SECTIONS||'<br>'|| T1.CLASS_ROLL  " +
					" SECTIONS1,T2.BIRTH_DATE,T2.GENDER,T2.GENDER||'<br>'||to_char(T2.BIRTH_DATE,'DDMMYY') GENDER1," +
			" to_char(to_number(T1.SUB7)+1) optsubject," +
			" T2.BOARD_NAME_ENG,T2.ROLL_NO, T2.PASSING_YEAR,T2.REG_NO,T2.PASSING_YEAR||'<br>'||T2.ROLL_NO||'<br>'||T2.BOARD_NAME_ENG||'<br>'||T2.REG_NO PASSING_YEAR1,T2.BOARD_ID,T1.EIIN,T1.APPLICATION_ID,registered," +
				"CASE t1.GROUP_ID when 9 then '236,237,238,239,240,201,202,203,204,205,206,209,210,'||T1.SUB7" +
				"                 when 10 then '236,237,238,239,240,201,202,203,223,232,233,234,235,'||T1.SUB7" +
				"                 else '236,237,238,239,201,202,203,223,224,225,226,227'||T1.SUB6||T1.SUB7 end allsubject" +
				"  from board_registration t1, board_data_ssc t2, BOARD_RESULT T3 where T1.APPLICATION_ID=T2.APPLICATION_ID " +
				" AND T2.APPLICATION_ID=T3.APPLICATION_ID and T1.EIIN=T3.EIIN AND T1.EIIN=? AND T3.SHIFT_ID = ? AND T3.VERSION_ID = ? AND T3.GROUP_ID = ?" +
				" order by T1.ESIF";
			
//				System.out.println("delwar:"+sql);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setString(2, shift_id);
			stmt.setString(3, version_id);
			stmt.setString(4, group_id);
			r = stmt.executeQuery();
			lstNonRegistered = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				nonRegistered = new ApplicantInfoDTO();
// applicantName,sscRollNo,boardName,sscPassingYear,eiinCode,boardID,sscReg,applicationID				
				nonRegistered.setEiinCode(r.getString("EIIN"));
				nonRegistered.setEsif(r.getString("esif"));
				nonRegistered.setApplicationID(r.getString("APPLICATION_ID"));
				
//				nonRegistered.setShiftID(r.getString("SHIFT_ID"));
//				nonRegistered.setShiftName(r.getString("SHIFT_NAME"));
				
//				nonRegistered.setVersionID(r.getString("VERSION_ID"));
//				nonRegistered.setVersionName(r.getString("VERSION_NAME"));
				
//				nonRegistered.setGroupId(r.getString("GROUP_ID"));
//				nonRegistered.setGroupName(r.getString("GROUP_NAME"));
				
//				nonRegistered.setMeritType(r.getString("MERIT_TYPE"));
//				nonRegistered.setPriorityOrder(r.getString("MERIT_POSITTION"));
//				nonRegistered.setAdmitStatus(r.getString("STATUS"));
//				nonRegistered.setAssignedQuota(r.getString("QUOTA_TYPE"));
//				nonRegistered.setPinNumber(r.getString("PIN_NUMBER"));
		//		nonApprovedStudentOfMeritDTO.setMobileNo(r.getString("MOBILE_NUMBER"));
				
				nonRegistered.setApplicantName(r.getString("STUDENT_NAME"));
				nonRegistered.setSscRollNo(r.getString("ROLL_NO"));
				nonRegistered.setSscReg(r.getString("REG_NO"));
				nonRegistered.setBoardID(r.getString("BOARD_ID"));
				nonRegistered.setBoardName(r.getString("BOARD_NAME_ENG"));
				nonRegistered.setSscPassingYear(r.getString("PASSING_YEAR"));
				nonRegistered.setRegistered(r.getString("REGISTERED"));
				nonRegistered.setAllsubject(r.getString("allsubject"));
				nonRegistered.setOptsubject(r.getString("optsubject"));
				
				nonRegistered.setApplicantName1(r.getString("STUDENT_NAME1"));
				nonRegistered.setSections1(r.getString("sections1"));
				nonRegistered.setGender1(r.getString("gender1"));
				nonRegistered.setSscPassingYear1(r.getString("PASSING_YEAR1"));
				
				lstNonRegistered.add(nonRegistered);
				

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
		
		return lstNonRegistered;
	}	
	public List<ApplicantInfoDTO> printManualDataFinal(String shift_id,String version_id,String group_id,String userId, String sboardid){
		ApplicantInfoDTO nonRegistered = null;
		List<ApplicantInfoDTO> lstNonRegistered = null;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="";
 		if(sboardid!="18")
			sql = "select T1.ESIF,T2.STUDENT_NAME,initcap(T2.STUDENT_NAME||'\n'||T2.FATHER_NAME||'\n'||T2.MOTHER_NAME) STUDENT_NAME1,T1.CLASS_ROLL,T1.SECTIONS,T1.SECTIONS||'\n'|| T1.CLASS_ROLL  " +
					" SECTIONS1,T2.BIRTH_DATE,T2.GENDER,T2.GENDER||'\n'||to_char(T2.BIRTH_DATE,'DDMMYY') GENDER1," +
					" CASE t1.GROUP_ID when 0 then DECODE(LENGTH(SUB7), 3 ,to_char(to_number(SUB7)) ,to_char(to_number(substr(SUB7,1,3)))) else to_char(to_number(T1.SUB7)) end ||'\n'||" +
					" CASE t1.GROUP_ID when 0 then DECODE(LENGTH(SUB7), 3 ,to_char(to_number(SUB7)+1) ,to_char(to_number(substr(SUB7,5,3)))) else to_char(to_number(T1.SUB7)+1) end optsubject," +
					" T2.BOARD_NAME_ENG,T2.ROLL_NO, T2.PASSING_YEAR,T2.REG_NO,T2.PASSING_YEAR||'\n'||T2.ROLL_NO||'\n'||T2.BOARD_NAME_ENG||'\n'||T2.REG_NO PASSING_YEAR1,T2.BOARD_ID,T1.EIIN,T1.APPLICATION_ID,registered," +
				"'101'||','||'102'||','||'107'||','||'108'||','||'275'||','||" +
				"CASE t1.GROUP_ID when 0 then '174' when 8 then '277' when 5 then '279' when 6 then '249' when 7 then '216' else to_char(to_number(T1.SUB4)) end ||','||" +
				"CASE t1.GROUP_ID when 0 then '175' when 8 then '278' when 5 then '280' when 6 then '250' when 7 then '217' else to_char(to_number(T1.SUB4)+1) end ||','||" +
				"CASE t1.GROUP_ID when 0 then '176' when 8 then '253' when 5 then '282' when 6 then '267' when 7 then '218' else to_char(to_number(T1.SUB5)) end ||','||" +
				"CASE t1.GROUP_ID when 0 then '177' when 8 then '254' when 5 then '283' when 6 then '268' when 7 then '219' else to_char(to_number(T1.SUB5)+1) end ||','||" +
				"CASE t1.GROUP_ID when 5 then '298' when 6 then '133' else to_char(to_number(T1.SUB6)) end ||','||" +
				"CASE t1.GROUP_ID when 5 then '299' when 6 then '134' else to_char(to_number(T1.SUB6)+1) end ||','||" +
				"CASE t1.GROUP_ID when 0 then DECODE(LENGTH(SUB7), 3 ,to_char(to_number(SUB7)) ,to_char(to_number(substr(SUB7,1,3)))) else to_char(to_number(T1.SUB7)) end ||','||" +
				"CASE t1.GROUP_ID when 0 then DECODE(LENGTH(SUB7), 3 ,to_char(to_number(SUB7)+1) ,to_char(to_number(substr(SUB7,5,3)))) else to_char(to_number(T1.SUB7)+1) end allsubject" +
				"  from board_registration t1, board_data_ssc t2, COLLEGE3DATAENTRY T3 where T2.roll_no = T3.roll_no and T2.board_id = T3.board_id and T2.passing_year = T3.passing_year" +
				"  AND T1.APPLICATION_ID=T3.APPID and T1.EIIN=T3.EIIN AND T1.EIIN=? AND T3.SHIFT_ID = ? AND T3.VERSION_ID = ? AND T3.GROUP_ID = ?" +
				" order by T1.ESIF";
		else
			sql = "select T1.ESIF,T2.STUDENT_NAME,initcap(T2.STUDENT_NAME||'\n'||T2.FATHER_NAME||'\n'||T2.MOTHER_NAME) STUDENT_NAME1,T1.CLASS_ROLL,T1.SECTIONS,T1.SECTIONS||'\n'|| T1.CLASS_ROLL  " +
					" SECTIONS1,T2.BIRTH_DATE,T2.GENDER,T2.GENDER||'\n'||to_char(T2.BIRTH_DATE,'DDMMYY') GENDER1," +
			" to_char(to_number(T1.SUB7)+1) optsubject," +
			" T2.BOARD_NAME_ENG,T2.ROLL_NO, T2.PASSING_YEAR,T2.REG_NO,T2.PASSING_YEAR||'\n'||T2.ROLL_NO||'\n'||T2.BOARD_NAME_ENG||'\n'||T2.REG_NO PASSING_YEAR1,T2.BOARD_ID,T1.EIIN,T1.APPLICATION_ID,registered," +
				"CASE t1.GROUP_ID when 9 then '236,237,238,239,240,201,202,203,204,205,206,209,210,'||T1.SUB7" +
				"                 when 10 then '236,237,238,239,240,201,202,203,223,232,233,234,235,'||T1.SUB7" +
				"                 else '236,237,238,239,201,202,203,223,224,225,226,227'||T1.SUB6||T1.SUB7 end allsubject" +
				"  from board_registration t1, board_data_ssc t2, BOARD_RESULT T3 where T1.APPLICATION_ID=T2.APPLICATION_ID " +
				" AND T2.APPLICATION_ID=T3.APPLICATION_ID and T1.EIIN=T3.EIIN AND T1.EIIN=? AND T3.SHIFT_ID = ? AND T3.VERSION_ID = ? AND T3.GROUP_ID = ?" +
				" order by T1.ESIF";
			
//				System.out.println("delwar:"+sql);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setString(2, shift_id);
			stmt.setString(3, version_id);
			stmt.setString(4, group_id);
			r = stmt.executeQuery();
			lstNonRegistered = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				nonRegistered = new ApplicantInfoDTO();
// applicantName,sscRollNo,boardName,sscPassingYear,eiinCode,boardID,sscReg,applicationID				
				nonRegistered.setEiinCode(r.getString("EIIN"));
				nonRegistered.setEsif(r.getString("esif"));
				nonRegistered.setApplicationID(r.getString("APPLICATION_ID"));
				
//				nonRegistered.setShiftID(r.getString("SHIFT_ID"));
//				nonRegistered.setShiftName(r.getString("SHIFT_NAME"));
				
//				nonRegistered.setVersionID(r.getString("VERSION_ID"));
//				nonRegistered.setVersionName(r.getString("VERSION_NAME"));
				
//				nonRegistered.setGroupId(r.getString("GROUP_ID"));
//				nonRegistered.setGroupName(r.getString("GROUP_NAME"));
				
//				nonRegistered.setMeritType(r.getString("MERIT_TYPE"));
//				nonRegistered.setPriorityOrder(r.getString("MERIT_POSITTION"));
//				nonRegistered.setAdmitStatus(r.getString("STATUS"));
//				nonRegistered.setAssignedQuota(r.getString("QUOTA_TYPE"));
//				nonRegistered.setPinNumber(r.getString("PIN_NUMBER"));
		//		nonApprovedStudentOfMeritDTO.setMobileNo(r.getString("MOBILE_NUMBER"));
				
				nonRegistered.setApplicantName(r.getString("STUDENT_NAME"));
				nonRegistered.setSscRollNo(r.getString("ROLL_NO"));
				nonRegistered.setSscReg(r.getString("REG_NO"));
				nonRegistered.setBoardID(r.getString("BOARD_ID"));
				nonRegistered.setBoardName(r.getString("BOARD_NAME_ENG"));
				nonRegistered.setSscPassingYear(r.getString("PASSING_YEAR"));
				nonRegistered.setRegistered(r.getString("REGISTERED"));
				nonRegistered.setAllsubject(r.getString("allsubject"));
				nonRegistered.setOptsubject(r.getString("optsubject"));
				
				nonRegistered.setApplicantName1(r.getString("STUDENT_NAME1"));
				nonRegistered.setSections1(r.getString("sections1"));
				nonRegistered.setGender1(r.getString("gender1"));
				nonRegistered.setSscPassingYear1(r.getString("PASSING_YEAR1"));
				
				lstNonRegistered.add(nonRegistered);
				

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
		
		return lstNonRegistered;
	}
	
	public ApplicantInfoDTO getIndManualDataUpdate(String appId){
		ApplicantInfoDTO nonRegistered = null;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="select FATHER_NAME,MOTHER_NAME,BIRTH_DATE,GENDER,GPA,GROUP_NAME,VERSION_NAME,SHIFT_NAME,tab1.eiin," +
				"    SUB4,SUB5,SUB6,SUB7,ESIF,CLASS_ROLL,sections,NATIONALITY,HDISTRICT,RELIGION,sessions " +
				" from BOARD_REGISTRATION tab1,(" +
				"  SELECT FATHER_NAME," +
				"         MOTHER_NAME," +
				"         TO_CHAR (BIRTH_DATE, 'dd/mm/yyyy') BIRTH_DATE," +
				"         GENDER," +
				"         TO_CHAR (gpa, '9.99') GPA," +
				"         T3.GROUP_NAME," +
				"         T4.VERSION_NAME," +
				"         T5.SHIFT_NAME," +
				"         eiin,t2.appid application_id,sessions" +
				"    FROM board_data_ssc t1," +
				"         COLLEGE3DATAENTRY t2," +
				"         mst_group t3," +
				"        mst_version t4," +
				"         mst_shift t5" +
				"   WHERE     t2.appid = ?" +
				"         AND T1.roll_no = T2.roll_no and T1.board_id = T2.board_id and T1.passing_year = T2.passing_year" +
				"         AND T2.GROUP_ID = T3.GROUP_ID" +
				"         AND T2.VERSION_ID = T4.VERSION_ID" +
				"         AND T2.SHIFT_ID = T5.SHIFT_ID" +
				") tab2 where tab1.application_id=tab2.application_id and tab1.eiin=tab2.eiin";
		
		
		
//		System.out.println("delwar:"+appId);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, appId);
			r = stmt.executeQuery();
			if(r.next())
			{
				nonRegistered = new ApplicantInfoDTO();
				nonRegistered.setShiftName(r.getString("SHIFT_NAME"));
				nonRegistered.setVersionName(r.getString("VERSION_NAME"));
				nonRegistered.setGroupName(r.getString("GROUP_NAME"));
				nonRegistered.setFatherName(r.getString("FATHER_NAME"));
				nonRegistered.setMotherName(r.getString("MOTHER_NAME"));
				nonRegistered.setDob(r.getString("BIRTH_DATE"));
				nonRegistered.setGender(r.getString("GENDER"));
				nonRegistered.setGpa(r.getString("GPA"));
				nonRegistered.setSub4(r.getString("SUB4"));
				nonRegistered.setSub5(r.getString("SUB5"));
				nonRegistered.setSub6(r.getString("SUB6"));
				nonRegistered.setSub7(r.getString("SUB7"));
				nonRegistered.setEsif(r.getString("ESIF"));
				nonRegistered.setClass_roll(r.getString("CLASS_ROLL"));
				nonRegistered.setSections(r.getString("SECTIONS"));
				nonRegistered.setNationality(r.getString("NATIONALITY"));
				nonRegistered.setHdistrict(r.getString("HDISTRICT"));
				nonRegistered.setReligion(r.getString("RELIGION"));
				nonRegistered.setSessions(r.getString("sessions"));
				
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
		
		return nonRegistered;
	}	
	
	
	public ApplicantInfoDTO getRegisteredIndData(String appId){
		ApplicantInfoDTO nonRegistered = null;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="SELECT FATHER_NAME," +
				"       MOTHER_NAME," +
				"       BIRTH_DATE," +
				"       GENDER," +
				"       GPA," +
				"       GROUP_NAME," +
				"       VERSION_NAME," +
				"       SHIFT_NAME," +
				"       tab1.eiin," +
				"       SUB4," +
				"       SUB5," +
				"       SUB6," +
				"       SUB7," +
				"       ESIF," +
				"       CLASS_ROLL," +
				"       sections," +
				"       NATIONALITY," +
				"       HDISTRICT," +
				"       RELIGION," +
				"       sessions" +
				"  FROM BOARD_REGISTRATION tab1," +
				"       (SELECT FATHER_NAME," +
				"               MOTHER_NAME," +
				"               TO_CHAR (BIRTH_DATE, 'dd/mm/yyyy') BIRTH_DATE," +
				"               GENDER," +
				"               TO_CHAR (gpa, '9.99') GPA," +
				"               T3.GROUP_NAME," +
				"               T4.VERSION_NAME," +
				"               T5.SHIFT_NAME," +
				"               merit_type," +
				"               eiin," +
				"               t2.applicant_id," +
				"               sessions" +
				"          FROM board_data_ssc t1," +
				"               board_result t2," +
				"               mst_group t3," +
				"               mst_version t4," +
				"               mst_shift t5" +
				"         WHERE     t2.applicant_id = ?" +
				"               AND T1.BOARD_ID||T1.pass_year||T1.ROLL_NO = T2.applicant_id" +
				"               AND T2.GROUP_ID = T3.GROUP_ID" +
				"               AND T2.VERSION_ID = T4.VERSION_ID" +
				"               AND T2.SHIFT_ID = T5.SHIFT_ID" +
				"               AND COLLEGE_RECEIVE = 'College Received') tab2" +
				" WHERE tab1.applicant_id = tab2.applicant_id AND tab1.eiin = tab2.eiin";
				
		
		
//		System.out.println("delwar:"+appId);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, appId);
			r = stmt.executeQuery();
			if(r.next())
			{
				nonRegistered = new ApplicantInfoDTO();
				nonRegistered.setShiftName(r.getString("SHIFT_NAME"));
				nonRegistered.setVersionName(r.getString("VERSION_NAME"));
				nonRegistered.setGroupName(r.getString("GROUP_NAME"));
				nonRegistered.setFatherName(r.getString("FATHER_NAME"));
				nonRegistered.setMotherName(r.getString("MOTHER_NAME"));
				nonRegistered.setDob(r.getString("BIRTH_DATE"));
				nonRegistered.setGender(r.getString("GENDER"));
				nonRegistered.setGpa(r.getString("GPA"));
				nonRegistered.setSub4(r.getString("SUB4"));
				nonRegistered.setSub5(r.getString("SUB5"));
				nonRegistered.setSub6(r.getString("SUB6"));
				nonRegistered.setSub7(r.getString("SUB7"));
				nonRegistered.setEsif(r.getString("ESIF"));
				nonRegistered.setClass_roll(r.getString("CLASS_ROLL"));
				nonRegistered.setSections(r.getString("SECTIONS"));
				nonRegistered.setNationality(r.getString("NATIONALITY"));
				nonRegistered.setHdistrict(r.getString("HDISTRICT"));
				nonRegistered.setReligion(r.getString("RELIGION"));
				nonRegistered.setSessions(r.getString("sessions"));
				
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
		
		return nonRegistered;
	}
	
	public List<CollegeSubject> getCollegeSubject(String eiin ,int board_id){
		CollegeSubject cs = null;
		List<CollegeSubject> lstCS = null;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="select * from MST_SUB_COLLEGE_type where eiin=? ";
		
		if(board_id==18)
			sql = " select eiin,tab2.* from MST_SUB_COLLEGE tab1,(" +
				" select t1.*,status, group_id from MST_SUBJECT_mad t1, MST_SUB_TYPE t2 where T1.SUB_ID=T2.SUB_ID and status!='COM' " +
				" ) tab2 where TAB1.SUB_ID=tab2.sub_id and eiin=?";
		else
			sql = " select eiin,tab2.* from MST_SUB_COLLEGE tab1,(" +
			" select t1.*,status, group_id from MST_SUBJECT t1, MST_SUB_TYPE t2 where T1.SUB_ID=T2.SUB_ID and status!='COM' " +
			" ) tab2 where TAB1.SUB_ID=tab2.sub_id and eiin=?";
		
		
//		System.out.println("delwar:"+sql);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, eiin);
			r = stmt.executeQuery();
			lstCS = new ArrayList<CollegeSubject>();
			while(r.next())
			{
				cs = new CollegeSubject();
				
				cs.setEiin(r.getString("EIIN"));
				cs.setSub_id(r.getString("SUB_ID"));
				
				cs.setSub_name(r.getString("SUB_NAME") +" (" + r.getString("SUB_ID") + "," + (Integer.parseInt(r.getString("SUB_ID")) + 1) + ")" );
				cs.setStatus(r.getString("STATUS"));
				
				cs.setGroup_id(r.getString("GROUP_ID"));
				lstCS.add(cs);
				

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
		
		return lstCS;
	}
	
	
	static PreparedStatement stmtRegistrationDataInsert = null;
	static String sqlRegistrationDataInsert = "insert into board_registration (esif,eiin,APPLICANT_ID, ROLL_NO, BOARD_ID, PASSING_YEAR, GROUP_ID, sub4, sub5," +
	" sub6, sub7, class_roll, sections , hdistrict, Religion, ipaddress) values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";
	static PreparedStatement stmtRegStatus = null;
	static String sqlRegStatus = "update BOARD_RESULT set registered='Y' where APPLICANT_ID=?";
	
	
	public static synchronized int RregistrationInsert(int esif, String eiin,String app_id,String ssc_roll,int ssc_board,int ssc_year,int ssc_group,
			String sub4,String sub5,String sub6,String sub7,String class_roll, String sections,String Nationality,String hdistrict
			 ,String Religion, IpAddressDTO ipAdress)throws Exception
    {
		int tmp = 0;
           try {
	            
	            if(stmtRegistrationDataInsert==null)
	            	stmtRegistrationDataInsert = ConnectionManager.getConnectionStatic().prepareStatement(sqlRegistrationDataInsert);
	            int parameterIndex = 1;
	            stmtRegistrationDataInsert.setInt(parameterIndex++, esif);
	            stmtRegistrationDataInsert.setString(parameterIndex++, eiin);
	            stmtRegistrationDataInsert.setString(parameterIndex++, app_id);
	            stmtRegistrationDataInsert.setString(parameterIndex++, ssc_roll);
	            stmtRegistrationDataInsert.setInt(parameterIndex++, ssc_board);	            
	            stmtRegistrationDataInsert.setInt(parameterIndex++, ssc_year);
	            stmtRegistrationDataInsert.setInt(parameterIndex++, ssc_group);
	            stmtRegistrationDataInsert.setString(parameterIndex++, sub4);
	            stmtRegistrationDataInsert.setString(parameterIndex++, sub5);
	            stmtRegistrationDataInsert.setString(parameterIndex++, sub6);
	            stmtRegistrationDataInsert.setString(parameterIndex++, sub7);
	            stmtRegistrationDataInsert.setString(parameterIndex++, class_roll);
	            stmtRegistrationDataInsert.setString(parameterIndex++, sections);
//	            stmtRegistrationDataInsert.setString(parameterIndex++, Nationality);
	            stmtRegistrationDataInsert.setString(parameterIndex++, hdistrict);
	            stmtRegistrationDataInsert.setString(parameterIndex++, Religion);
	            stmtRegistrationDataInsert.setString(parameterIndex++, ipAdress.getRemoteAddress());
	            
	            tmp = stmtRegistrationDataInsert.executeUpdate();
	            stmtRegistrationDataInsert.clearParameters();
	            if(tmp>0)
	            {
		            if(stmtRegStatus==null)
		            	stmtRegStatus = ConnectionManager.getConnectionStatic().prepareStatement(sqlRegStatus);
		            parameterIndex = 1;
		            stmtRegStatus.setString(parameterIndex++, app_id);
	            

		            tmp = stmtRegStatus.executeUpdate();
		            stmtRegStatus.clearParameters();
	            	
	            }

	          } catch (Exception e) {
	        	  e.printStackTrace();
	            //System.out.println(e.toString());
	            stmtRegistrationDataInsert = null;
	            stmtRegStatus = null;
	            ConnectionManager.closeStatic();
	            throw e;
	        }	
            return tmp;
    }
	
	static PreparedStatement stmtRegistrationDataUpdate = null;
	static String sqlRegistrationDataUpdate = "update board_registration set esif=?,eiin=?,ROLL_NO=?, BOARD_ID=?, PASSING_YEAR=?, GROUP_ID=?" +
			", sub4=?, sub5=?, sub6=?, sub7=?, class_roll=?, sections=?, hdistrict=?, Religion=?, ipaddress=? where applicant_id=?";

	
	
	public static synchronized int RregistrationUpdate(int esif, String eiin,String app_id,String ssc_roll,int ssc_board,int ssc_year,int ssc_group,
			String sub4,String sub5,String sub6,String sub7,String class_roll,String sections,String Nationality,String hdistrict
			 ,String Religion, IpAddressDTO ipAdress)throws Exception
    {
		int tmp = 0;
           try {
	            
	            if(stmtRegistrationDataUpdate==null)
	            	stmtRegistrationDataUpdate = ConnectionManager.getConnectionStatic().prepareStatement(sqlRegistrationDataUpdate);
	            int parameterIndex = 1;
	            stmtRegistrationDataUpdate.setInt(parameterIndex++, esif);
	            stmtRegistrationDataUpdate.setString(parameterIndex++, eiin);
	            stmtRegistrationDataUpdate.setString(parameterIndex++, ssc_roll);
	            stmtRegistrationDataUpdate.setInt(parameterIndex++, ssc_board);	            
	            stmtRegistrationDataUpdate.setInt(parameterIndex++, ssc_year);
	            stmtRegistrationDataUpdate.setInt(parameterIndex++, ssc_group);
	            stmtRegistrationDataUpdate.setString(parameterIndex++, sub4);
	            stmtRegistrationDataUpdate.setString(parameterIndex++, sub5);
	            stmtRegistrationDataUpdate.setString(parameterIndex++, sub6);
	            stmtRegistrationDataUpdate.setString(parameterIndex++, sub7);
	            stmtRegistrationDataUpdate.setString(parameterIndex++, class_roll);
	            stmtRegistrationDataUpdate.setString(parameterIndex++, sections);
//	            stmtRegistrationDataUpdate.setString(parameterIndex++, Nationality);
	            stmtRegistrationDataUpdate.setString(parameterIndex++, hdistrict);
	            stmtRegistrationDataUpdate.setString(parameterIndex++, Religion);
	            stmtRegistrationDataUpdate.setString(parameterIndex++, ipAdress.getRemoteAddress());
	            stmtRegistrationDataUpdate.setString(parameterIndex++, app_id);
	            
	            tmp = stmtRegistrationDataUpdate.executeUpdate();
	            stmtRegistrationDataUpdate.clearParameters();
	            
	          } catch (SQLException e) {
	            System.out.println(e.toString());
	            stmtRegistrationDataUpdate = null;
	            ConnectionManager.closeStatic();
	            throw e;
	        }	
            return tmp;
    }
	
	static PreparedStatement stmtRegInsertManual = null;
	static String sqlRegInsertManual = "insert into board_registration (esif,eiin,APPLICATION_ID, ROLL_NO, BOARD_ID, PASSING_YEAR, GROUP_ID, sub4, sub5," +
	" sub6, sub7, class_roll, sections , hdistrict, Religion, ipaddress) values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";
	static PreparedStatement stmtRegManual = null;
	static String sqlRegManual = "update COLLEGE3DATAENTRY set registered='Y' where APPID=?";
	
	
	public static synchronized int RegInsertManual(int esif, String eiin,String app_id,String ssc_roll,int ssc_board,int ssc_year,int ssc_group,
			String sub4,String sub5,String sub6,String sub7,String class_roll, String sections,String Nationality,String hdistrict
			 ,String Religion, IpAddressDTO ipAdress)throws Exception
    {
		int tmp = 0;
           try {
	            
	            if(stmtRegInsertManual==null)
	            	stmtRegInsertManual = ConnectionManager.getConnectionStatic().prepareStatement(sqlRegInsertManual);
	            int parameterIndex = 1;
	            stmtRegInsertManual.setInt(parameterIndex++, esif);
	            stmtRegInsertManual.setString(parameterIndex++, eiin);
	            stmtRegInsertManual.setString(parameterIndex++, app_id);
	            stmtRegInsertManual.setString(parameterIndex++, ssc_roll);
	            stmtRegInsertManual.setInt(parameterIndex++, ssc_board);	            
	            stmtRegInsertManual.setInt(parameterIndex++, ssc_year);
	            stmtRegInsertManual.setInt(parameterIndex++, ssc_group);
	            stmtRegInsertManual.setString(parameterIndex++, sub4);
	            stmtRegInsertManual.setString(parameterIndex++, sub5);
	            stmtRegInsertManual.setString(parameterIndex++, sub6);
	            stmtRegInsertManual.setString(parameterIndex++, sub7);
	            stmtRegInsertManual.setString(parameterIndex++, class_roll);
	            stmtRegInsertManual.setString(parameterIndex++, sections);
//	            stmtRegInsertManual.setString(parameterIndex++, Nationality);
	            stmtRegInsertManual.setString(parameterIndex++, hdistrict);
	            stmtRegInsertManual.setString(parameterIndex++, Religion);
	            stmtRegInsertManual.setString(parameterIndex++, ipAdress.getRemoteAddress());
	            
	            tmp = stmtRegInsertManual.executeUpdate();
	            stmtRegInsertManual.clearParameters();
	            if(tmp>0)
	            {
		            if(stmtRegManual==null)
		            	stmtRegManual = ConnectionManager.getConnectionStatic().prepareStatement(sqlRegManual);
		            parameterIndex = 1;
		            stmtRegManual.setString(parameterIndex++, app_id);
	            

		            tmp = stmtRegManual.executeUpdate();
		            stmtRegManual.clearParameters();
	            	
	            }

	          } catch (Exception e) {
	        	  e.printStackTrace();
	            //System.out.println(e.toString());
	            stmtRegistrationDataInsert = null;
	            stmtRegStatus = null;
	            ConnectionManager.closeStatic();
	            throw e;
	        }	
            return tmp;
    }
	static PreparedStatement stmtProblemPhoto = null;
	static String sqlProblemPhoto = "insert into problemPhoto (application_id) values ( ? )";
	
	public static synchronized void ProblemPhotoInsert(String application_id)
    {
           try {
	            
	            if(stmtProblemPhoto==null)
	            	stmtProblemPhoto = ConnectionManager.getConnectionStatic().prepareStatement(sqlProblemPhoto);
	            int parameterIndex = 1;
	            stmtProblemPhoto.setString(parameterIndex++, application_id);
	            stmtProblemPhoto.executeUpdate();
	            stmtProblemPhoto.clearParameters();
	            
	          } catch (Exception e) {
	        	  e.printStackTrace();
	        	  stmtProblemPhoto = null;
	        	  ConnectionManager.closeStatic();
	        }	

    }
	
	
	public void savePhoto(String starttime, String endtime){
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="select t1.application_id, T4.BOARD_NAME_ENG, T1.EIIN, '17'||T2.BOARD_ID||'_'||T1.EIIN||T2.REG_NO||'.jpg' filename from " +
				" BOARD_Registration t1,board_data_ssc t2,mst_college t3,mst_edu_board t4 where sdate > to_date(?,'ddmmyyhh24miss')" +
				" and sdate <= to_date(?,'ddmmyyhh24miss')" +
				" and T1.APPLICATION_ID=T2.APPLICATION_ID and T1.EIIN=T3.EIIN and T3.BOARD_ID=T4.BOARD_ID";
		
		
//		System.out.println("delwar:"+appId);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		String rootDir = "/home/photo" ;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, starttime);
			stmt.setString(2, endtime);
			r = stmt.executeQuery();
			while(r.next())
			{
				try
				{
					File a1 = new File(rootDir+"/"+r.getString("BOARD_NAME_ENG"));
					if(!a1.exists())a1.mkdir();
					a1 = new File(rootDir+"/"+r.getString("BOARD_NAME_ENG")+"/"+r.getString("EIIN"));
					if(!a1.exists())a1.mkdir();
					
					URL url = new URL("http://118.67.221.84:8082/applicantPhoto/"+r.getString("BOARD_NAME_ENG")+"/"+r.getString("EIIN")+
							"/"+r.getString("filename"));
					BufferedImage image = ImageIO.read(url);  
					ImageIO.write(image, "jpg",new File(rootDir+"/"+r.getString("BOARD_NAME_ENG")+"/"+r.getString("EIIN")+"/"+r.getString("filename")));
					Thread.sleep(20);
				} 
				catch (Exception e){
					AdmissionDAO.ProblemPhotoInsert(r.getString("application_id"));
				}
				
			}

		} 
		catch (Exception e){}
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

	}
	public List<ApplicantInfoDTO> getDataForRegistrationManual(String shift_id,String version_id,String group_id,String userId){
		ApplicantInfoDTO nonRegistered = null;
		List<ApplicantInfoDTO> lstNonRegistered = null;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="SELECT A.EIIN," +
				"       A.appID," +
				"       B.SHIFT_ID," +
				"       B.SHIFT_NAME," +
				"       C.VERSION_ID," +
				"       C.VERSION_NAME," +
				"       D.GROUP_ID," +
				"       D.GROUP_NAME," +
				"       E.REG_NO ,       E.STUDENT_NAME ," +
				"       E.ROLL_NO ," +
				"       E.BOARD_ID," +
				"       E.BOARD_NAME_ENG ," +
				"       E.PASSING_YEAR," +
				"        A.registered" +
				"  FROM COLLEGE3DATAENTRY a," +
				"       MST_SHIFT b," +
				"       MST_VERSION c," +
				"       MST_GROUP d," +
				"       BOARD_DATA_SSC e" +
				" WHERE     A.EIIN = ?" +
				"       AND a.SHIFT_ID = ?" +
				"       AND A.VERSION_ID = ?" +
				"       AND A.GROUP_ID = ?" +
				"       AND A.registered = 'N'" +
				"       AND a.SHIFT_ID = b.SHIFT_ID" +
				"       AND A.VERSION_ID = C.VERSION_ID" +
				"       AND A.GROUP_ID = D.GROUP_ID" +
				"       AND A.ROLL_NO = E.ROLL_NO AND A.BOARD_ID = E.BOARD_ID AND A.PASSING_YEAR = E.PASSING_YEAR" +
				" ORDER BY B.SHIFT_NAME," +
				"         C.VERSION_NAME," +
				"         D.GROUP_NAME," +
				"         E.BOARD_ID,E.PASSING_YEAR,E.ROLL_NO ";
		
		
//		System.out.println("delwar:"+sql);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setString(2, shift_id);
			stmt.setString(3, version_id);
			stmt.setString(4, group_id);
			r = stmt.executeQuery();
			lstNonRegistered = new ArrayList<ApplicantInfoDTO>();
			while(r.next())
			{
				nonRegistered = new ApplicantInfoDTO();
				
				nonRegistered.setEiinCode(r.getString("EIIN"));
				nonRegistered.setApplicationID(r.getString("APPID"));
				
				nonRegistered.setShiftID(r.getString("SHIFT_ID"));
				nonRegistered.setShiftName(r.getString("SHIFT_NAME"));
				
				nonRegistered.setVersionID(r.getString("VERSION_ID"));
				nonRegistered.setVersionName(r.getString("VERSION_NAME"));
				
				nonRegistered.setGroupId(r.getString("GROUP_ID"));
				nonRegistered.setGroupName(r.getString("GROUP_NAME"));
				
				nonRegistered.setApplicantName(r.getString("STUDENT_NAME"));
				nonRegistered.setSscRollNo(r.getString("ROLL_NO"));
				nonRegistered.setBoardID(r.getString("BOARD_ID"));
				nonRegistered.setBoardName(r.getString("BOARD_NAME_ENG"));
				nonRegistered.setSscPassingYear(r.getString("PASSING_YEAR"));
				nonRegistered.setRegistered(r.getString("REGISTERED"));
				nonRegistered.setSscReg(r.getString("REG_NO"));
				
				lstNonRegistered.add(nonRegistered);
				

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
		
		return lstNonRegistered;
	}
	public ApplicantInfoDTO getIndividualDataManual(String appId){
		ApplicantInfoDTO nonRegistered = null;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql ="  SELECT FATHER_NAME," +
				"         MOTHER_NAME," +
				"         TO_CHAR (BIRTH_DATE, 'dd/mm/yyyy') BIRTH_DATE," +
				"         GENDER," +
				"         TO_CHAR (gpa, '9.99') GPA," +
				"         T3.GROUP_NAME," +
				"         T4.VERSION_NAME," +
				"         T5.SHIFT_NAME," +
				"         sessions" +
				"    FROM board_data_ssc t1," +
				"         COLLEGE3DATAENTRY t2," +
				"         mst_group t3," +
				"         mst_version t4," +
				"         mst_shift t5" +
				"   WHERE     t2.appid = ?" +
				"         AND t1.ROLL_NO = t2.ROLL_NO AND t1.BOARD_ID = t2.BOARD_ID AND t1.PASSING_YEAR = t2.PASSING_YEAR" +
				"         AND T2.GROUP_ID = T3.GROUP_ID" +
				"         AND T2.VERSION_ID = T4.VERSION_ID" +
				"         AND T2.SHIFT_ID = T5.SHIFT_ID";
		
		
//		System.out.println("delwar:"+appId);
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, appId);
			r = stmt.executeQuery();
			if(r.next())
			{
				nonRegistered = new ApplicantInfoDTO();
				nonRegistered.setShiftName(r.getString("SHIFT_NAME"));
				nonRegistered.setVersionName(r.getString("VERSION_NAME"));
				nonRegistered.setGroupName(r.getString("GROUP_NAME"));
				nonRegistered.setFatherName(r.getString("FATHER_NAME"));
				nonRegistered.setMotherName(r.getString("MOTHER_NAME"));
				nonRegistered.setDob(r.getString("BIRTH_DATE"));
				nonRegistered.setGender(r.getString("GENDER"));
				nonRegistered.setGpa(r.getString("GPA"));
				nonRegistered.setSessions(r.getString("sessions"));
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
		
		return nonRegistered;
	}			
	
	
	
}
