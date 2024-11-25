package edu.dao.college;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import edu.dto.application.ApplicantDTO;
import edu.dto.application.ApplicationInfoDTO;
import edu.dto.application.SscInfoDTO;
import edu.dto.board.BoardDTO;
import edu.utils.connection.ConnectionManager;


public class SscDataDAO {
	
	public ArrayList<BoardDTO> getBoardInfo(){
		BoardDTO boardDto = null;
		ArrayList<BoardDTO> boardList = null;
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "select board_id, board_name_eng from mst_edu_board where is_active_yn = 'Y' order by view_order";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			r = stmt.executeQuery();
			
			boardList = new ArrayList<BoardDTO>();
			while(r.next())
			{
				boardDto = new BoardDTO();
				boardDto.setBoardId(r.getString("BOARD_ID"));
				boardDto.setBoardName(r.getString("BOARD_NAME_ENG"));
				boardList.add(boardDto);

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
		
		return boardList;
	}
	
	public ApplicantDTO getApplication(String sscRoll, String sscBoard, String sscYear,String sscReg){
		ApplicantDTO applicant = null;
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = " Select tmp1.*,application.APPLICATION_ID,MOBILE_NUMBER,QUOTA_YN,QUOTA_FF,QUOTA_EQ From (  " +
		" SELECT B.ROLL_NO, B.REG_NO, B.BOARD_ID, BOARD_NAME_ENG, " +
		" B.GROUP_ID, GROUP_NAME, B.PASSING_YEAR, B.STUDENT_NAME, " +
		" B.FATHER_NAME, B.MOTHER_NAME, to_char(B.BIRTH_DATE,'DD-MM-YYYY') BIRTH_DATE, " +
		" decode(B.GENDER,'M','Male','F','Female') GENDER_NAME,GENDER GENDER_ID, B.GPA, B.GPA_EXC4TH,SSC_EIIN FROM " +
		" BOARD_DATA_SSC B " +
		" where B.ROLL_NO = ? AND B.BOARD_ID = ? AND B.PASSING_YEAR = ? AND B.REG_NO=?)tmp1 Left Outer Join APPLICATION_INFO application " +
		" on tmp1.ROLL_NO=application.SSC_ROLL_NO " +
		" AND tmp1.BOARD_ID=application.SSC_BOARD_ID " +
		" AND tmp1.PASSING_YEAR=application.SSC_PASSING_YEAR ";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sscRoll);
			stmt.setString(2, sscBoard);
			stmt.setString(3, sscYear);
			stmt.setString(4, sscReg);
			r = stmt.executeQuery();
			if(r.next())
			{
					
					applicant=new ApplicantDTO();
					applicant.setApplication_id(r.getString("APPLICATION_ID"));
					SscInfoDTO sscInfo = new SscInfoDTO();
					
					sscInfo.setRoll(r.getString("ROLL_NO"));
					sscInfo.setReg_no(r.getString("REG_NO"));
					sscInfo.setBoard_id(r.getString("BOARD_ID"));
					sscInfo.setBoard_name(r.getString("BOARD_NAME_ENG"));
					sscInfo.setGroup_id(r.getString("GROUP_ID"));
					sscInfo.setGroup_name(r.getString("GROUP_NAME"));
					sscInfo.setPassing_year(r.getString("PASSING_YEAR"));
					sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
					sscInfo.setFather_name(r.getString("FATHER_NAME"));
					sscInfo.setMother_name(r.getString("MOTHER_NAME"));
					sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
					sscInfo.setGender_id(r.getString("GENDER_ID"));
					sscInfo.setGender_name(r.getString("GENDER_NAME"));
					sscInfo.setGpa(r.getFloat("GPA"));
					sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
					sscInfo.setEiin(r.getString("SSC_EIIN"));
					
					applicant.setSsc_info(sscInfo);
					
					ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
					applicationInfo.setMobile_number(r.getString("MOBILE_NUMBER"));
					applicationInfo.setQuota_yn(r.getString("QUOTA_YN"));
					applicationInfo.setQuota_ff(r.getString("QUOTA_FF"));
					applicationInfo.setQuota_eq(r.getString("QUOTA_EQ"));
					applicant.setApplication_info(applicationInfo);
				
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return applicant;
	}
	
	public ApplicantDTO getAdmittedInformation(String appid){
		ApplicantDTO applicant1 = null;
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "SELECT brm.application_id,\n" +
             "       AI.NAME,\n" +
             "       brm.eiin,\n" +
             "       MC.COLLEGE_NAME,\n" +
             "       MS.SHIFT_NAME,\n" +
             "       MV.VERSION_NAME,\n" +
             "       MG.GROUP_NAME,\n" +
             "       status\n" +
             "  FROM board_result_merit brm,\n" +
             "       mst_college mc,\n" +
             "       mst_shift ms,\n" +
             "       mst_version mv,\n" +
             "       mst_group mg,\n" +
             "       application_info ai\n" +
             " WHERE     AI.APPLICATION_ID = BRM.APPLICATION_ID\n" +
             "       AND MC.EIIN = brm.EIIN\n" +
             "       AND MS.SHIFT_ID = BRM.SHIFT_ID\n" +
             "       AND MV.VERSION_ID = BRM.VERSION_ID\n" +
             "       AND MG.GROUP_ID = BRM.GROUP_ID\n" +
             "       AND brm.application_id = ? \n" +
             "       AND STATUS = 'College Received'";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, appid);
			
			r = stmt.executeQuery();
			if(r.next())
			{
					
					applicant1=new ApplicantDTO();
					//applicant.setApplication_id(r.getString("APPLICATION_ID"));
					applicant1.setStudent_name(r.getString("name"));
					applicant1.setCollegeName(r.getString("college_name"));
					
/*					System.out.println(r.getString("college_name"));
					System.out.println(r.getString("name"));
					System.out.println(">>>"+applicant1.getCollegeName());*/
					
					applicant1.setShift(r.getString("shift_name"));
					applicant1.setVersion(r.getString("version_name"));
					applicant1.setGroup(r.getString("group_name"));
					applicant1.setEiin(r.getString("eiin"));
/*					System.out.println(">>>"+applicant1.getEiin());
					System.out.println(">>>"+applicant1.getGroup());*/
					

				
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return applicant1;
	}
	
	public ApplicantDTO getApplication_TT(String sscRoll, String sscBoard, String sscYear,String sscReg){
		ApplicantDTO applicant = null;
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = " Select tmp1.*,application.APPLICATION_ID,application.PIN_NUMBER_USED_YN,QUOTA_EDUCATION,QUOTA_EXPATRIATE,QUOTA_BKSP,QUOTA_FF From (  " +
		" SELECT B.ROLL_NO, B.REG_NO, B.BOARD_ID,B.TT_TRANS_NUMBER,B.APPLIED, BOARD_NAME_ENG, " +
		" B.GROUP_ID, GROUP_NAME, B.PASSING_YEAR, B.STUDENT_NAME, " +
		" B.FATHER_NAME, B.MOTHER_NAME, to_char(B.BIRTH_DATE,'DD-MM-YYYY') BIRTH_DATE, " +
		" decode(B.GENDER,'M','Male','F','Female') GENDER_NAME,GENDER GENDER_ID, B.GPA, B.GPA_EXC4TH,SSC_EIIN FROM " +
		" BOARD_DATA_SSC B " +
		" where B.ROLL_NO = ? AND B.BOARD_ID = ? AND B.PASSING_YEAR = ? AND B.REG_NO=?)tmp1 Left Outer Join APPLICATION_INFO application " +
		" on tmp1.ROLL_NO=application.SSC_ROLL_NO " +
		" AND tmp1.BOARD_ID=application.SSC_BOARD_ID " +
		" AND tmp1.PASSING_YEAR=application.SSC_PASSING_YEAR ";
		
/*		sql = " SELECT B.APPLICATION_ID, B.ROLL_NO, B.REG_NO, B.BOARD_ID,B.TT_TRANS_NUMBER,B.APPLIED, BOARD_NAME_ENG, " +
		" B.GROUP_ID, GROUP_NAME, B.PASSING_YEAR, B.STUDENT_NAME, " +
		" B.FATHER_NAME, B.MOTHER_NAME, to_char(B.BIRTH_DATE,'DD-MM-YYYY') BIRTH_DATE, " +
		" decode(B.GENDER,'M','Male','F','Female') GENDER_NAME,GENDER GENDER_ID, B.GPA, B.GPA_EXC4TH,SSC_EIIN FROM " +
		" BOARD_DATA_SSC B " +
		" where B.ROLL_NO = ? AND B.BOARD_ID = ? AND B.PASSING_YEAR = ? AND B.REG_NO=? ";*/
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sscRoll);
			stmt.setString(2, sscBoard);
			stmt.setString(3, sscYear);
			stmt.setString(4, sscReg);
			r = stmt.executeQuery();
			if(r.next())
			{
				String transaction = r.getString("TT_TRANS_NUMBER");
				String applied = r.getString("APPLIED");
				String pinUsedYN = r.getString("PIN_NUMBER_USED_YN");
				
				String appid = r.getString("APPLICATION_ID");
				

				 if(appid ==null)
				{
					applicant=new ApplicantDTO();
					SscInfoDTO sscInfo = new SscInfoDTO();					
					sscInfo.setRoll(r.getString("ROLL_NO"));
					sscInfo.setReg_no(r.getString("REG_NO"));
					sscInfo.setBoard_id(r.getString("BOARD_ID"));
					sscInfo.setBoard_name(r.getString("BOARD_NAME_ENG"));
					sscInfo.setGroup_id(r.getString("GROUP_ID"));
					sscInfo.setGroup_name(r.getString("GROUP_NAME"));
					sscInfo.setPassing_year(r.getString("PASSING_YEAR"));
					sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
					sscInfo.setFather_name(r.getString("FATHER_NAME"));
					sscInfo.setMother_name(r.getString("MOTHER_NAME"));
					sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
					sscInfo.setGender_id(r.getString("GENDER_ID"));
					sscInfo.setGender_name(r.getString("GENDER_NAME"));
					sscInfo.setGpa(r.getFloat("GPA"));
					sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
					sscInfo.setEiin(r.getString("SSC_EIIN"));
					applicant.setSsc_info(sscInfo);
					ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
					applicant.setApplication_info(applicationInfo);
					applicant.setApplied(applied);
					applicant.setApplication_id(r.getString("APPLICATION_ID"));
					applicant.setMsg("newapplication");
				}
				 else if(appid!=null && pinUsedYN.equalsIgnoreCase("N"))
					{
					 applicant=new ApplicantDTO();
						SscInfoDTO sscInfo = new SscInfoDTO();					
						sscInfo.setRoll(r.getString("ROLL_NO"));
						sscInfo.setReg_no(r.getString("REG_NO"));
						sscInfo.setBoard_id(r.getString("BOARD_ID"));
						sscInfo.setBoard_name(r.getString("BOARD_NAME_ENG"));
						sscInfo.setGroup_id(r.getString("GROUP_ID"));
						sscInfo.setGroup_name(r.getString("GROUP_NAME"));
						sscInfo.setPassing_year(r.getString("PASSING_YEAR"));
						sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
						sscInfo.setFather_name(r.getString("FATHER_NAME"));
						sscInfo.setMother_name(r.getString("MOTHER_NAME"));
						sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
						sscInfo.setGender_id(r.getString("GENDER_ID"));
						sscInfo.setGender_name(r.getString("GENDER_NAME"));
						sscInfo.setGpa(r.getFloat("GPA"));
						sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
						sscInfo.setEiin(r.getString("SSC_EIIN"));
						applicant.setSsc_info(sscInfo);
						ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
						applicant.setApplication_info(applicationInfo);
						applicant.setApplied(applied);
						applicant.setApplication_id(r.getString("APPLICATION_ID"));
						applicant.setMsg("notAdmitted");
					}
				else if( appid!=null && pinUsedYN.equalsIgnoreCase("Y"))
				{
					applicant=new ApplicantDTO();
					applicant.setApplication_id(r.getString("APPLICATION_ID"));
					applicant.setApplied(applied);
					applicant.setMsg("alreadyAdmitted");
				}
			}
			else
			{
				applicant=new ApplicantDTO();
				applicant.setMsg("nossc");
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return applicant;
	}
	
	public ApplicantDTO getApplication_Quota_TT(String sscRoll, String sscBoard, String sscYear,String sscReg){
		ApplicantDTO applicant = null;
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = " Select tmp1.*,application.APPLICATION_ID,QUOTA_EDUCATION,QUOTA_EXPATRIATE,QUOTA_BKSP,QUOTA_FF From (  " +
		" SELECT B.ROLL_NO, B.REG_NO, B.BOARD_ID,B.TT_TRANS_NUMBER,B.APPLIED, BOARD_NAME_ENG, " +
		" B.GROUP_ID, GROUP_NAME, B.PASSING_YEAR, B.STUDENT_NAME, " +
		" B.FATHER_NAME, B.MOTHER_NAME, to_char(B.BIRTH_DATE,'DD-MM-YYYY') BIRTH_DATE, " +
		" decode(B.GENDER,'M','Male','F','Female') GENDER_NAME,GENDER GENDER_ID, B.GPA, B.GPA_EXC4TH,SSC_EIIN,CONTACT_NO FROM " +
		" BOARD_DATA_SSC B " +
		" where B.ROLL_NO = ? AND B.BOARD_ID = ? AND B.PASSING_YEAR = ? AND B.REG_NO=?)tmp1 Left Outer Join APPLICATION_INFO application " +
		" on tmp1.ROLL_NO=application.SSC_ROLL_NO " +
		" AND tmp1.BOARD_ID=application.SSC_BOARD_ID " +
		" AND tmp1.PASSING_YEAR=application.SSC_PASSING_YEAR ";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sscRoll);
			stmt.setString(2, sscBoard);
			stmt.setString(3, sscYear);
			stmt.setString(4, sscReg);
			r = stmt.executeQuery();
			if(r.next())
			{
				
					applicant=new ApplicantDTO();
					applicant.setApplication_id(r.getString("APPLICATION_ID"));
					SscInfoDTO sscInfo = new SscInfoDTO();					
					sscInfo.setRoll(r.getString("ROLL_NO"));
					sscInfo.setReg_no(r.getString("REG_NO"));
					sscInfo.setBoard_id(r.getString("BOARD_ID"));
					sscInfo.setBoard_name(r.getString("BOARD_NAME_ENG"));
					sscInfo.setGroup_id(r.getString("GROUP_ID"));
					sscInfo.setGroup_name(r.getString("GROUP_NAME"));
					sscInfo.setPassing_year(r.getString("PASSING_YEAR"));
					sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
					sscInfo.setFather_name(r.getString("FATHER_NAME"));
					sscInfo.setMother_name(r.getString("MOTHER_NAME"));
					sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
					sscInfo.setGender_id(r.getString("GENDER_ID"));
					sscInfo.setGender_name(r.getString("GENDER_NAME"));
					sscInfo.setGpa(r.getFloat("GPA"));
					sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
					sscInfo.setEiin(r.getString("SSC_EIIN"));
					applicant.setSsc_info(sscInfo);
					ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
					
					applicationInfo.setQuota_ff(r.getString("QUOTA_FF"));
					applicationInfo.setQuota_eq(r.getString("QUOTA_EDUCATION"));
					applicationInfo.setQuota_bksp(r.getString("QUOTA_BKSP"));
					applicationInfo.setQuota_expatriate(r.getString("QUOTA_EXPATRIATE"));
					applicationInfo.setMobile_number(r.getString("CONTACT_NO"));
					
					applicant.setApplication_info(applicationInfo);
					

			}
			else
			{
				applicant=new ApplicantDTO();
				applicant.setMsg("nossc");
				
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return applicant;
	}
	
	public ApplicantDTO getApplicationWId_TT(String sscRoll, String sscBoard, String sscYear,String sscReg){
		ApplicantDTO applicant = null;
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = " Select tmp1.*,application.APPLICATION_ID,QUOTA_EDUCATION,QUOTA_EXPATRIATE,QUOTA_BKSP,QUOTA_FF From (  " +
		" SELECT B.ROLL_NO, B.REG_NO, B.BOARD_ID,B.TT_TRANS_NUMBER,B.APPLIED, BOARD_NAME_ENG, " +
		" B.GROUP_ID, GROUP_NAME, B.PASSING_YEAR, B.STUDENT_NAME, " +
		" B.FATHER_NAME, B.MOTHER_NAME, to_char(B.BIRTH_DATE,'DD-MM-YYYY') BIRTH_DATE, " +
		" decode(B.GENDER,'M','Male','F','Female') GENDER_NAME,GENDER GENDER_ID, B.GPA, B.GPA_EXC4TH,SSC_EIIN,CONTACT_NO FROM " +
		" BOARD_DATA_SSC B " +
		" where B.ROLL_NO = ? AND B.BOARD_ID = ? AND B.PASSING_YEAR = ? AND B.REG_NO=?)tmp1 Left Outer Join APPLICATION_INFO application " +
		" on tmp1.ROLL_NO=application.SSC_ROLL_NO " +
		" AND tmp1.BOARD_ID=application.SSC_BOARD_ID " +
		" AND tmp1.PASSING_YEAR=application.SSC_PASSING_YEAR ";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sscRoll);
			stmt.setString(2, sscBoard);
			stmt.setString(3, sscYear);
			stmt.setString(4, sscReg);
			r = stmt.executeQuery();
			if(r.next())
			{
				String transaction = r.getString("TT_TRANS_NUMBER");
				String applied = r.getString("APPLIED");
				
				if(transaction==null)
				{
					applicant=new ApplicantDTO();
					applicant.setMsg("nopayment");
				}
				
				
					applicant=new ApplicantDTO();
					applicant.setApplication_id(r.getString("APPLICATION_ID"));
					SscInfoDTO sscInfo = new SscInfoDTO();					
					sscInfo.setRoll(r.getString("ROLL_NO"));
					sscInfo.setReg_no(r.getString("REG_NO"));
					sscInfo.setBoard_id(r.getString("BOARD_ID"));
					sscInfo.setBoard_name(r.getString("BOARD_NAME_ENG"));
					sscInfo.setGroup_id(r.getString("GROUP_ID"));
					sscInfo.setGroup_name(r.getString("GROUP_NAME"));
					sscInfo.setPassing_year(r.getString("PASSING_YEAR"));
					sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
					sscInfo.setFather_name(r.getString("FATHER_NAME"));
					sscInfo.setMother_name(r.getString("MOTHER_NAME"));
					sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
					sscInfo.setGender_id(r.getString("GENDER_ID"));
					sscInfo.setGender_name(r.getString("GENDER_NAME"));
					sscInfo.setGpa(r.getFloat("GPA"));
					sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
					sscInfo.setEiin(r.getString("SSC_EIIN"));
					applicant.setSsc_info(sscInfo);
					ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
					if(r.getString("QUOTA_FF").equalsIgnoreCase("Y"))
						applicationInfo.setQuota_ff("Yes");
					else
						applicationInfo.setQuota_ff("No");

					if(r.getString("QUOTA_EDUCATION").equalsIgnoreCase("Y"))
						applicationInfo.setQuota_eq("Yes");
					else
						applicationInfo.setQuota_eq("No");
					if(r.getString("QUOTA_BKSP").equalsIgnoreCase("Y"))
						applicationInfo.setQuota_bksp("Yes");
					else
						applicationInfo.setQuota_bksp("No");
					if(r.getString("QUOTA_EXPATRIATE").equalsIgnoreCase("Y"))
						applicationInfo.setQuota_expatriate("Yes");
					else
						applicationInfo.setQuota_expatriate("No");

					applicationInfo.setMobile_number(r.getString("CONTACT_NO"));
					
					applicant.setApplication_info(applicationInfo);
					
					applicant.setApplied(applied);
			}
			else
			{
				applicant=new ApplicantDTO();
				applicant.setMsg("nossc");
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return applicant;
	}
	
	public ApplicantDTO getApplicationTx_TT(String tx_captcha,String sscRoll, String sscBoard, String sscYear,String sscReg){
		ApplicantDTO applicant = null;
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = " SELECT B.ROLL_NO, B.REG_NO, B.BOARD_ID,B.TT_TRANS_NUMBER,B.APPLIED, BOARD_NAME_ENG, " +
		" B.GROUP_ID, GROUP_NAME, B.PASSING_YEAR, B.STUDENT_NAME, " +
		" B.FATHER_NAME, B.MOTHER_NAME, to_char(B.BIRTH_DATE,'DD-MM-YYYY') BIRTH_DATE, " +
		" decode(B.GENDER,'M','Male','F','Female') GENDER_NAME,GENDER GENDER_ID, B.GPA, B.GPA_EXC4TH,SSC_EIIN FROM " +
		" BOARD_DATA_SSC B " +
		" where TT_TRANS_NUMBER=? and ROLL_NO = ? AND BOARD_ID = ? AND PASSING_YEAR = ? AND REG_NO=?";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tx_captcha);
			stmt.setString(2, sscRoll);
			stmt.setString(3, sscBoard);
			stmt.setString(4, sscYear);
			stmt.setString(5, sscReg);
			r = stmt.executeQuery();
			if(r.next())
			{
				String transaction = r.getString("TT_TRANS_NUMBER");
				String applied = r.getString("APPLIED");
				
				if (transaction!=null && applied.equals("N")) {
					
					applicant=new ApplicantDTO();
					SscInfoDTO sscInfo = new SscInfoDTO();
					
					sscInfo.setRoll(r.getString("ROLL_NO"));
					sscInfo.setReg_no(r.getString("REG_NO"));
					sscInfo.setBoard_id(r.getString("BOARD_ID"));
					sscInfo.setBoard_name(r.getString("BOARD_NAME_ENG"));
					sscInfo.setGroup_id(r.getString("GROUP_ID"));
					sscInfo.setGroup_name(r.getString("GROUP_NAME"));
					sscInfo.setPassing_year(r.getString("PASSING_YEAR"));
					sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
					sscInfo.setFather_name(r.getString("FATHER_NAME"));
					sscInfo.setMother_name(r.getString("MOTHER_NAME"));
					sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
					sscInfo.setGender_id(r.getString("GENDER_ID"));
					sscInfo.setGender_name(r.getString("GENDER_NAME"));
					sscInfo.setGpa(r.getFloat("GPA"));
					sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
					sscInfo.setEiin(r.getString("SSC_EIIN"));
					
					applicant.setSsc_info(sscInfo);
					
					ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
					//applicationInfo.setMobile_number(r.getString("MOBILE_NUMBER"));
					//applicationInfo.setQuota_yn(r.getString("QUOTA_YN"));
					//applicationInfo.setQuota_ff(r.getString("QUOTA_FF"));
					//applicationInfo.setQuota_eq(r.getString("QUOTA_EQ"));
					applicant.setApplication_info(applicationInfo);
					
				} else if(transaction!=null && applied.equals("Y")) {
				//	applicant=new ApplicantDTO();
				//	applicant.setApplication_id(r.getString("APPLICATION_ID"));
					
					
					applicant=new ApplicantDTO();
					applicant.setApplication_id(r.getString("APPLICATION_ID"));
					SscInfoDTO sscInfo = new SscInfoDTO();
					
					sscInfo.setRoll(r.getString("ROLL_NO"));
					sscInfo.setReg_no(r.getString("REG_NO"));
					sscInfo.setBoard_id(r.getString("BOARD_ID"));
					sscInfo.setBoard_name(r.getString("BOARD_NAME_ENG"));
					sscInfo.setGroup_id(r.getString("GROUP_ID"));
					sscInfo.setGroup_name(r.getString("GROUP_NAME"));
					sscInfo.setPassing_year(r.getString("PASSING_YEAR"));
					sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
					sscInfo.setFather_name(r.getString("FATHER_NAME"));
					sscInfo.setMother_name(r.getString("MOTHER_NAME"));
					sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
					sscInfo.setGender_id(r.getString("GENDER_ID"));
					sscInfo.setGender_name(r.getString("GENDER_NAME"));
					sscInfo.setGpa(r.getFloat("GPA"));
					sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
					sscInfo.setEiin(r.getString("SSC_EIIN"));
					
					applicant.setSsc_info(sscInfo);
					
					ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
					
					applicationInfo.setQuota_ff(r.getString("QUOTA_FF"));
					applicationInfo.setQuota_eq(r.getString("QUOTA_EDUCATION"));
					applicationInfo.setQuota_bksp(r.getString("QUOTA_BKSP"));
					applicationInfo.setQuota_expatriate(r.getString("QUOTA_EXPATRIATE"));
					
					//applicationInfo.setMobile_number(r.getString("MOBILE_NUMBER"));
					//applicationInfo.setQuota_yn(r.getString("QUOTA_YN"));
					//applicationInfo.setQuota_ff(r.getString("QUOTA_FF"));
					//applicationInfo.setQuota_eq(r.getString("QUOTA_EQ"));
					applicant.setApplication_info(applicationInfo);
					
					
					
					
					return applicant;

				}
				
				
				
			}
			else
			{
				applicant=new ApplicantDTO();
				applicant.setMsg("notxid");
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return applicant;
	}	
	
	
	
	public ApplicantDTO getApplicantForCancel(String sscRoll, String sscBoard, String sscYear){
		ApplicantDTO applicant = null;
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = " select AI.STUDENT_NAME,tmp1.* from (select application_id,SSC_ROLL_NO,SSC_BOARD_ID,SSC_PASSING_YEAR,STUDENT_NAME from application_info" +
				"             union" +
				"            select application_id,SSC_ROLL_NO,SSC_BOARD_ID,SSC_PASSING_YEAR,STUDENT_NAME from application_info_new) ai, (" +
				" select application_id,eiin,max_merit_type,decode(max_merit_type,1,'First Merit',2,'First Merit Migration',3,'Second Merit',5,'Release Slip Merit',9,'4th Phase Merit')" +
				" merit from ( select * from (" +
				" SELECT board_result.*, MAX (merit_type) OVER (PARTITION BY application_id) max_merit_type" +
				"                                      FROM board_result) WHERE status = 'College Received'" +
				"                                      and merit_type=max_merit_type)) tmp1" +
				" where ai.application_id=tmp1.application_id and SSC_ROLL_NO =? AND SSC_BOARD_ID =? AND SSC_PASSING_YEAR =?  ";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sscRoll);
			stmt.setString(2, sscBoard);
			stmt.setString(3, sscYear);
			r = stmt.executeQuery();
			if(r.next())
			{
				
				applicant=new ApplicantDTO();
				applicant.setApplication_id(r.getString("APPLICATION_ID"));
				applicant.setStudent_name(r.getString("STUDENT_NAME"));
				applicant.setEiin(r.getString("EIIN"));
				applicant.setMerit_type(r.getString("max_merit_type"));
				applicant.setMerit(r.getString("MERIT"));
				
				
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return applicant;
	}
	
	public ApplicantDTO getApplicationNew(String sscRoll, String sscBoard, String sscYear,String sscReg,String motherName){
		
		String eligible=isEli4SecondPhaseApply(sscRoll,sscReg,sscBoard,sscYear,"");
		if(!eligible.equalsIgnoreCase("Y")){
			return null;
		}
		ApplicantDTO applicant = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		
		String sql = " Select tmp1.*,application.APPLICATION_ID,MOBILE_NUMBER,QUOTA_YN,QUOTA_FF,QUOTA_EQ From (  " +
		" SELECT B.ROLL_NO, B.REG_NO, B.BOARD_ID, BOARD_NAME_ENG, " +
		" B.GROUP_ID, GROUP_NAME, B.PASSING_YEAR, B.STUDENT_NAME, " +
		" B.FATHER_NAME, B.MOTHER_NAME, to_char(B.BIRTH_DATE,'DD-MM-YYYY') BIRTH_DATE, " +
		" decode(B.GENDER,'M','Male','F','Female') GENDER_NAME,GENDER GENDER_ID, B.GPA, B.GPA_EXC4TH,SSC_EIIN FROM " +
		" BOARD_DATA_SSC B " +
		" where B.ROLL_NO = ? AND B.BOARD_ID = ? AND B.PASSING_YEAR = ? AND B.REG_NO=?)tmp1 Left Outer Join APPLICATION_INFO_NEW application " +
		" on tmp1.ROLL_NO=application.SSC_ROLL_NO " +
		" AND tmp1.BOARD_ID=application.SSC_BOARD_ID " +
		" AND tmp1.PASSING_YEAR=application.SSC_PASSING_YEAR ";
		

		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sscRoll);
			stmt.setString(2, sscBoard);
			stmt.setString(3, sscYear);
			stmt.setString(4, sscReg);
			r = stmt.executeQuery();
			if(r.next())
			{
				
				applicant=new ApplicantDTO();
				applicant.setApplication_id(r.getString("APPLICATION_ID"));
				SscInfoDTO sscInfo = new SscInfoDTO();
				
				sscInfo.setRoll(r.getString("ROLL_NO"));
				sscInfo.setReg_no(r.getString("REG_NO"));
				sscInfo.setBoard_id(r.getString("BOARD_ID"));
				sscInfo.setBoard_name(r.getString("BOARD_NAME_ENG"));
				sscInfo.setGroup_id(r.getString("GROUP_ID"));
				sscInfo.setGroup_name(r.getString("GROUP_NAME"));
				sscInfo.setPassing_year(r.getString("PASSING_YEAR"));
				sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
				sscInfo.setFather_name(r.getString("FATHER_NAME"));
				sscInfo.setMother_name(r.getString("MOTHER_NAME"));
				sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
				sscInfo.setGender_id(r.getString("GENDER_ID"));
				sscInfo.setGender_name(r.getString("GENDER_NAME"));
				sscInfo.setGpa(r.getFloat("GPA"));
				sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
				sscInfo.setEiin(r.getString("SSC_EIIN"));
				
				applicant.setSsc_info(sscInfo);
				
				ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
				applicationInfo.setMobile_number(r.getString("MOBILE_NUMBER"));
				applicationInfo.setQuota_yn(r.getString("QUOTA_YN"));
				applicationInfo.setQuota_ff(r.getString("QUOTA_FF"));
				applicationInfo.setQuota_eq(r.getString("QUOTA_EQ"));
				//applicationInfo.setPayment_status(r.getString("PAYMENT_STATUS"));
				applicant.setApplication_info(applicationInfo);
				
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return applicant;
	}
	public String isEli4SecondPhaseApply(String sscRoll,String sscReg, String sscBoard, String sscYear,String motherName){
		String resp="";
		Connection conn = ConnectionManager.getReadConnection();
		String sql = " Select isEli4SecondPhaseApply(?,?,?,?)  ELI from dual";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sscRoll);
			stmt.setString(2, sscReg);
			stmt.setString(3, sscBoard);
			stmt.setString(4, sscYear);
			r = stmt.executeQuery();
			if(r.next())
			{				
				resp=r.getString("ELI");					
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return resp;
		
	}
	public ApplicantDTO getApplication(String application_id){
		ApplicantDTO applicant = null;
		Connection conn = ConnectionManager.getReadConnection();
		String sql = " SELECT B.ROLL_NO, B.REG_NO, B.BOARD_ID, B.BOARD_NAME_ENG, GROUP_ID, GROUP_NAME, B.PASSING_YEAR, B.STUDENT_NAME," +
				" B.FATHER_NAME, B.MOTHER_NAME, TO_CHAR (B.BIRTH_DATE, 'DD-MM-YYYY') BIRTH_DATE, DECODE (B.GENDER,  'M', 'Male',  'F', 'Female') GENDER_NAME," +
				" a.GENDER GENDER_ID, B.GPA, B.GPA_EXC4TH, b.SSC_EIIN, a.APPLICATION_ID, QUOTA_FF, QUOTA_EDUCATION, QUOTA_BKSP, QUOTA_EXPATRIATE" +
				"  FROM APPLICATION_INFO a, BOARD_DATA_SSC B" +
				" WHERE     a.SSC_ROLL_NO = B.ROLL_NO" +
				"       AND a.SSC_BOARD_ID = B.BOARD_ID" +
				"       AND a.SSC_PASSING_YEAR = B.PASSING_YEAR" +
				"       AND A.Application_id=? and B.Application_id=?";
		
		
/*		String sql = " SELECT B.ROLL_NO, B.REG_NO, B.BOARD_ID, BOARD_NAME_ENG, " +
		" GROUP_ID, GROUP_NAME, B.PASSING_YEAR, B.STUDENT_NAME, " +
		" B.FATHER_NAME, B.MOTHER_NAME, to_char(B.BIRTH_DATE,'DD-MM-YYYY') BIRTH_DATE, " +
		" decode(B.GENDER,'M','Male','F','Female') GENDER_NAME,GENDER GENDER_ID, B.GPA, B.GPA_EXC4TH,SSC_EIIN, " +
		" application.APPLICATION_ID,MOBILE_NUMBER,QUOTA_YN,QUOTA_FF,QUOTA_EQ,EDITABLE From APPLICATION_INFO application,BOARD_DATA_SSC B " +
		" Where application.SSC_ROLL_NO=B.ROLL_NO " +
		" AND application.SSC_BOARD_ID=B.BOARD_ID " +
		" AND application.SSC_PASSING_YEAR=B.PASSING_YEAR  " +
		" And Application_id=?";*/

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, application_id);
			stmt.setString(2, application_id);
			r = stmt.executeQuery();
			if(r.next())
			{
				
				applicant=new ApplicantDTO();
				applicant.setApplication_id(r.getString("APPLICATION_ID"));
				SscInfoDTO sscInfo = new SscInfoDTO();
				
				sscInfo.setRoll(r.getString("ROLL_NO"));
				sscInfo.setReg_no(r.getString("REG_NO"));
				sscInfo.setBoard_id(r.getString("BOARD_ID"));
				sscInfo.setBoard_name(r.getString("BOARD_NAME_ENG"));
				sscInfo.setGroup_id(r.getString("GROUP_ID"));
				sscInfo.setGroup_name(r.getString("GROUP_NAME"));
				sscInfo.setPassing_year(r.getString("PASSING_YEAR"));
				sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
				sscInfo.setFather_name(r.getString("FATHER_NAME"));
				sscInfo.setMother_name(r.getString("MOTHER_NAME"));
				sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
				sscInfo.setGender_id(r.getString("GENDER_ID"));
				sscInfo.setGender_name(r.getString("GENDER_NAME"));
				sscInfo.setGpa(r.getFloat("GPA"));
				sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
				sscInfo.setEiin(r.getString("SSC_EIIN"));
				
				applicant.setSsc_info(sscInfo);
				
				ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
			//	applicationInfo.setMobile_number(r.getString("MOBILE_NUMBER"));
				//applicationInfo.setQuota_yn(r.getString("QUOTA_YN"));
				applicationInfo.setQuota_ff(r.getString("QUOTA_FF"));
				applicationInfo.setQuota_eq(r.getString("QUOTA_EDUCATION"));
				applicationInfo.setQuota_bksp(r.getString("QUOTA_BKSP"));
				applicationInfo.setQuota_expatriate(r.getString("QUOTA_EXPATRIATE"));
				//applicationInfo.setEditable(r.getString("EDITABLE"));
				
				applicant.setApplication_info(applicationInfo);
				
				
				
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return applicant;
	}
	
	public ApplicantDTO getNewApplication(String application_id){
		ApplicantDTO applicant = null;
		Connection conn = ConnectionManager.getReadConnection();
		String sql = " SELECT B.ROLL_NO, B.REG_NO, B.BOARD_ID, BOARD_NAME_ENG, " +
		" GROUP_ID, GROUP_NAME, B.PASSING_YEAR, B.STUDENT_NAME, " +
		" B.FATHER_NAME, B.MOTHER_NAME, to_char(B.BIRTH_DATE,'DD-MM-YYYY') BIRTH_DATE, " +
		" decode(B.GENDER,'M','Male','F','Female') GENDER_NAME,GENDER GENDER_ID, B.GPA, B.GPA_EXC4TH,SSC_EIIN, " +
		" application.APPLICATION_ID,MOBILE_NUMBER,QUOTA_YN,QUOTA_FF,QUOTA_EQ,EDITABLE From APPLICATION_INFO_NEW application,BOARD_DATA_SSC B " +
		" Where application.SSC_ROLL_NO=B.ROLL_NO " +
		" AND application.SSC_BOARD_ID=B.BOARD_ID " +
		" AND application.SSC_PASSING_YEAR=B.PASSING_YEAR  " +
		" And Application_id=?";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, application_id);
			r = stmt.executeQuery();
			if(r.next())
			{
				
				applicant=new ApplicantDTO();
				applicant.setApplication_id(r.getString("APPLICATION_ID"));
				SscInfoDTO sscInfo = new SscInfoDTO();
				
				sscInfo.setRoll(r.getString("ROLL_NO"));
				sscInfo.setReg_no(r.getString("REG_NO"));
				sscInfo.setBoard_id(r.getString("BOARD_ID"));
				sscInfo.setBoard_name(r.getString("BOARD_NAME_ENG"));
				sscInfo.setGroup_id(r.getString("GROUP_ID"));
				sscInfo.setGroup_name(r.getString("GROUP_NAME"));
				sscInfo.setPassing_year(r.getString("PASSING_YEAR"));
				sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
				sscInfo.setFather_name(r.getString("FATHER_NAME"));
				sscInfo.setMother_name(r.getString("MOTHER_NAME"));
				sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
				sscInfo.setGender_id(r.getString("GENDER_ID"));
				sscInfo.setGender_name(r.getString("GENDER_NAME"));
				sscInfo.setGpa(r.getFloat("GPA"));
				sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
				sscInfo.setEiin(r.getString("SSC_EIIN"));
				
				applicant.setSsc_info(sscInfo);
				
				ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
				applicationInfo.setMobile_number(r.getString("MOBILE_NUMBER"));
				applicationInfo.setQuota_yn(r.getString("QUOTA_YN"));
				applicationInfo.setQuota_ff(r.getString("QUOTA_FF"));
				applicationInfo.setQuota_eq(r.getString("QUOTA_EQ"));
				applicationInfo.setEditable(r.getString("EDITABLE"));
				
				applicant.setApplication_info(applicationInfo);
				
				
				
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return applicant;
	}

}
