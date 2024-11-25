package edu.dao.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import edu.dto.application.ApplicantDTO;
import edu.dto.application.ApplicationInfoDTO;
import edu.dto.application.SscInfoDTO;
import edu.dto.board.BoardDTO;
import edu.helpers.SmsSender;
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
	
	public ApplicantDTO getApplication_TT(String sscRoll, String sscBoard, String sscYear,String sscReg){
		ApplicantDTO applicant = null;
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "SELECT tmp1.*," +
				"       application.APPLICANT_ID," +
				"       QUOTA_EDUCATION," +
				"       QUOTA_EXPATRIATE," +
				"       QUOTA_BKSP," +
				"       QUOTA_FF," +
				"       APPLIED_WEB,APPLIED_SMS,HAS_WEB_TRNS, IS_RUN_ELIGIBLE,CONTACT_NO,SCODE, no_of_update, is_active " +
				"  FROM (SELECT B.ROLL_NO," +
				"               B.REG_NO," +
				"               B.BOARD_ID," +
				"               '' TT_TRANS_NUMBER," +
				"               BOARD_NAME," +
				"               B.GROUP_ID," +
				"               GROUP_NAME," +
				"               PASS_YEAR," +
				"               B.STUDENT_NAME," +
				"               B.FATHER_NAME," +
				"               B.MOTHER_NAME," +
				"               TO_CHAR (B.BIRTH_DATE, 'DD-MM-YYYY') BIRTH_DATE," +
				"               DECODE (B.GENDER,  'M', 'Male',  'F', 'Female') GENDER_NAME," +
				"               GENDER GENDER_ID," +
				"               B.GPA," +
				"               '' GPA_EXC4TH," +
				"               SSC_EIIN," +
				"				MERIT_POS MPOS" +
				"          FROM BOARD_DATA_SSC B" +
				"         WHERE     B.ROLL_NO = ?" +
				"               AND B.BOARD_ID = ?" +
				"               AND B.PASS_YEAR = ?" +
				"               AND B.REG_NO = ?) tmp1" +
				"       LEFT OUTER JOIN" +
				"       APPLICANT_INFO application" +
				"          ON     tmp1.ROLL_NO = application.ROLL_NO" +
				"             AND tmp1.BOARD_ID = application.BOARD_ID" +
				"             AND tmp1.PASS_YEAR = application.PASS_YEAR";

		

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
				String appliedweb = r.getString("APPLIED_WEB");
				String appliedsms = r.getString("APPLIED_SMS");
				String appid = r.getString("APPLICANT_ID");
				String havewebtrans = r.getString("HAS_WEB_TRNS");
				String is_active=r.getString("IS_ACTIVE");

				if(is_active==null)
				{
					// applicant is not eligible for application
					applicant=new ApplicantDTO();
					applicant.setMsg("nopayment");
				}
				else if(is_active.equals("0"))
				{
					// applicant is not eligible for application
					applicant=new ApplicantDTO();
					applicant.setMsg("nossc");
				}				
				else if(havewebtrans.equalsIgnoreCase("Y") && appliedweb.equalsIgnoreCase("N") && appliedsms.equalsIgnoreCase("N"))
				{
					applicant=new ApplicantDTO();
					SscInfoDTO sscInfo = new SscInfoDTO();	
					sscInfo.setRoll(r.getString("CONTACT_NO"));
					sscInfo.setRoll(r.getString("ROLL_NO"));
					sscInfo.setReg_no(r.getString("REG_NO"));
					sscInfo.setBoard_id(r.getString("BOARD_ID"));
					sscInfo.setBoard_name(r.getString("BOARD_NAME"));
					sscInfo.setGroup_id(r.getString("GROUP_ID"));
					sscInfo.setGroup_name(r.getString("GROUP_NAME"));
					sscInfo.setPassing_year(r.getString("PASS_YEAR"));
					sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
					sscInfo.setFather_name(r.getString("FATHER_NAME"));
					sscInfo.setMother_name(r.getString("MOTHER_NAME"));
					sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
					sscInfo.setGender_id(r.getString("GENDER_ID"));
					sscInfo.setGender_name(r.getString("GENDER_NAME"));
					sscInfo.setGpa(r.getFloat("GPA"));
//					sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
					sscInfo.setEiin(r.getString("SSC_EIIN"));
					applicant.setSsc_info(sscInfo);
					applicant.setContactno(r.getString("CONTACT_NO"));
					ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
					applicant.setApplication_info(applicationInfo);
					applicant.setApplied(appliedweb);
					applicant.setApplication_id(r.getString("APPLICANT_ID"));
					if(r.getString("MPOS")!=null)
					{
						String tmp = r.getString("MPOS");
						String []positions=tmp.split(",");
						applicant.setMpos(positions[0]);												
						if(positions.length>1)
							applicant.setApos(positions[1]);
					}

					applicant.setScode(r.getString("SCODE"));
 					applicant.setIs_active(r.getString("is_active"));
					applicant.setMsg("newapplication");
				}
				else if(havewebtrans.equalsIgnoreCase("Y") && (appliedweb.equalsIgnoreCase("Y") || appliedsms.equalsIgnoreCase("Y")))  // no need to check SMS application....because we donot permit to edit SMS application
				{
					// have application in both WEB and SMS
					applicant=new ApplicantDTO();
					SscInfoDTO sscInfo = new SscInfoDTO();	
					sscInfo.setRoll(r.getString("CONTACT_NO"));
					sscInfo.setRoll(r.getString("ROLL_NO"));
					sscInfo.setReg_no(r.getString("REG_NO"));
					sscInfo.setBoard_id(r.getString("BOARD_ID"));
					sscInfo.setBoard_name(r.getString("BOARD_NAME"));
					sscInfo.setGroup_id(r.getString("GROUP_ID"));
					sscInfo.setGroup_name(r.getString("GROUP_NAME"));
					sscInfo.setPassing_year(r.getString("PASS_YEAR"));
					sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
					sscInfo.setFather_name(r.getString("FATHER_NAME"));
					sscInfo.setMother_name(r.getString("MOTHER_NAME"));
					sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
					sscInfo.setGender_id(r.getString("GENDER_ID"));
					sscInfo.setGender_name(r.getString("GENDER_NAME"));
					sscInfo.setGpa(r.getFloat("GPA"));
//					sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
					sscInfo.setEiin(r.getString("SSC_EIIN"));
					applicant.setSsc_info(sscInfo);
					applicant.setContactno(r.getString("CONTACT_NO"));
					ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
					applicant.setApplication_info(applicationInfo);
					applicant.setApplied(appliedweb);
					applicant.setApplication_id(r.getString("APPLICANT_ID"));
					if(r.getString("MPOS")!=null)
					{
						String tmp = r.getString("MPOS");
						String []positions=tmp.split(",");
						applicant.setMpos(positions[0]);												
						if(positions.length>1)
							applicant.setApos(positions[1]);
					}

					applicant.setScode(r.getString("SCODE"));
					applicant.setNo_of_update(r.getString("no_of_update"));
					applicant.setIs_active(r.getString("is_active"));
					applicant.setMsg("alreadyapplied");
				}								
				else if( havewebtrans.equalsIgnoreCase("N") && appliedweb.equalsIgnoreCase("N") && appliedsms.equalsIgnoreCase("Y") )
				{
					applicant=new ApplicantDTO();
					SscInfoDTO sscInfo = new SscInfoDTO();	
					sscInfo.setRoll(r.getString("CONTACT_NO"));
					sscInfo.setRoll(r.getString("ROLL_NO"));
					sscInfo.setReg_no(r.getString("REG_NO"));
					sscInfo.setBoard_id(r.getString("BOARD_ID"));
					sscInfo.setBoard_name(r.getString("BOARD_NAME"));
					sscInfo.setGroup_id(r.getString("GROUP_ID"));
					sscInfo.setGroup_name(r.getString("GROUP_NAME"));
					sscInfo.setPassing_year(r.getString("PASS_YEAR"));
					sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
					sscInfo.setFather_name(r.getString("FATHER_NAME"));
					sscInfo.setMother_name(r.getString("MOTHER_NAME"));
					sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
					sscInfo.setGender_id(r.getString("GENDER_ID"));
					sscInfo.setGender_name(r.getString("GENDER_NAME"));
					sscInfo.setGpa(r.getFloat("GPA"));
					sscInfo.setEiin(r.getString("SSC_EIIN"));
					applicant.setSsc_info(sscInfo);
					applicant.setContactno(r.getString("CONTACT_NO"));
					ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
					applicant.setApplication_info(applicationInfo);
					applicant.setApplied(appliedweb);
					applicant.setApplication_id(r.getString("APPLICANT_ID"));
					applicationInfo.setQuota_ff(r.getString("QUOTA_FF"));
					applicationInfo.setQuota_eq(r.getString("QUOTA_EDUCATION"));
					applicationInfo.setQuota_bksp(r.getString("QUOTA_BKSP"));
					applicationInfo.setQuota_expatriate(r.getString("QUOTA_EXPATRIATE"));
					if(r.getString("MPOS")!=null)
					{
						String tmp = r.getString("MPOS");
						String []positions=tmp.split(",");
						applicant.setMpos(positions[0]);												
						if(positions.length>1)
							applicant.setApos(positions[1]);
					}

					applicant.setScode(r.getString("SCODE"));
					applicant.setIs_active(r.getString("is_active"));
					applicant.setNo_of_update(r.getString("no_of_update"));
					applicant.setMsg("onlysmsapplication");
				}
				/*				
				else if( appliedsms.equalsIgnoreCase("Y") && havewebtrans.equalsIgnoreCase("N") )
				{
					// only sms application + no web payment
					applicant=new ApplicantDTO();
					applicant.setContactno(r.getString("CONTACT_NO"));
					applicant.setApplication_id(r.getString("APPLICANT_ID"));
					if(r.getString("MPOS")!=null)
					{
						String tmp = r.getString("MPOS");
						String []positions=tmp.split(",");
						applicant.setMpos(positions[0]);												
						if(positions.length>1)
							applicant.setApos(positions[1]);
					}

					applicant.setScode(r.getString("SCODE"));
					applicant.setMsg("onlysmsapplication");
				}
*/				
				

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
	
	
	public ApplicantDTO getApplication_New_TT(String sscRoll, String sscBoard, String sscYear,String sscReg){
		ApplicantDTO applicant = null;
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "SELECT tmp1.*," +
				"       application.APPLICANT_ID," +
				"       QUOTA_EDUCATION," +
				"       QUOTA_EXPATRIATE," +
				"       QUOTA_BKSP," +
				"       QUOTA_FF," +
				"       APPLIED_WEB,APPLIED_SMS,HAS_WEB_TRNS, IS_RUN_ELIGIBLE,CONTACT_NO,SCODE, no_of_update, application.is_active, is_active_ssc " +
				"  FROM (SELECT B.ROLL_NO," +
				"               B.REG_NO," +
				"               B.BOARD_ID," +
				"               '' TT_TRANS_NUMBER," +
				"               BOARD_NAME," +
				"               B.GROUP_ID," +
				"               GROUP_NAME," +
				"               PASS_YEAR," +
				"               B.STUDENT_NAME," +
				"               B.FATHER_NAME," +
				"               B.MOTHER_NAME," +
				"               TO_CHAR (B.BIRTH_DATE, 'DD-MM-YYYY') BIRTH_DATE," +
				"               DECODE (B.GENDER,  'M', 'Male',  'F', 'Female') GENDER_NAME," +
				"               GENDER GENDER_ID," +
				"               B.GPA," +
				"               '' GPA_EXC4TH," +
				"               SSC_EIIN," +
				"				MERIT_POS MPOS,is_active is_active_ssc" +
				"          FROM BOARD_DATA_SSC B" +
				"         WHERE     B.ROLL_NO = ?" +
				"               AND B.BOARD_ID = ?" +
				"               AND B.PASS_YEAR = ?" +
				"               AND B.REG_NO = ?) tmp1" +
				"       LEFT OUTER JOIN" +
				"       APPLICANT_INFO application" +
				"          ON     tmp1.ROLL_NO = application.ROLL_NO" +
				"             AND tmp1.BOARD_ID = application.BOARD_ID" +
				"             AND tmp1.PASS_YEAR = application.PASS_YEAR";

		

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
				String appliedweb = r.getString("APPLIED_WEB");
				String appliedsms = r.getString("APPLIED_SMS");
				String appid = r.getString("APPLICANT_ID");
				String havewebtrans = r.getString("HAS_WEB_TRNS");
				String is_active=r.getString("IS_ACTIVE");
				String is_active_ssc =r.getString("IS_ACTIVE_SSC");

				if(is_active==null)
				{
					// applicant is not eligible for application
					applicant=new ApplicantDTO();
					if(is_active_ssc.equalsIgnoreCase("1"))
						applicant.setMsg("TxInfo");
					else
						applicant.setMsg("nopayment");
				}
				else if(is_active_ssc.equalsIgnoreCase("1"))
				{
					if(havewebtrans.equalsIgnoreCase("Y") && appliedweb.equalsIgnoreCase("N") && appliedsms.equalsIgnoreCase("N"))
					{
						applicant=new ApplicantDTO();
						SscInfoDTO sscInfo = new SscInfoDTO();	
						sscInfo.setRoll(r.getString("CONTACT_NO"));
						sscInfo.setRoll(r.getString("ROLL_NO"));
						sscInfo.setReg_no(r.getString("REG_NO"));
						sscInfo.setBoard_id(r.getString("BOARD_ID"));
						sscInfo.setBoard_name(r.getString("BOARD_NAME"));
						sscInfo.setGroup_id(r.getString("GROUP_ID"));
						sscInfo.setGroup_name(r.getString("GROUP_NAME"));
						sscInfo.setPassing_year(r.getString("PASS_YEAR"));
						sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
						sscInfo.setFather_name(r.getString("FATHER_NAME"));
						sscInfo.setMother_name(r.getString("MOTHER_NAME"));
						sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
						sscInfo.setGender_id(r.getString("GENDER_ID"));
						sscInfo.setGender_name(r.getString("GENDER_NAME"));
						sscInfo.setGpa(r.getFloat("GPA"));
//						sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
						sscInfo.setEiin(r.getString("SSC_EIIN"));
						applicant.setSsc_info(sscInfo);
						applicant.setContactno(r.getString("CONTACT_NO"));
						ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
						applicant.setApplication_info(applicationInfo);
						applicant.setApplied(appliedweb);
						applicant.setApplication_id(r.getString("APPLICANT_ID"));
						if(r.getString("MPOS")!=null)
						{
							String tmp = r.getString("MPOS");
							String []positions=tmp.split(",");
							applicant.setMpos(positions[0]);												
							if(positions.length>1)
								applicant.setApos(positions[1]);
						}

						applicant.setScode(r.getString("SCODE"));
	 					applicant.setIs_active(r.getString("is_active"));
						applicant.setMsg("newapplication");
					}
					else if( havewebtrans.equalsIgnoreCase("N") && appliedweb.equalsIgnoreCase("N") && appliedsms.equalsIgnoreCase("Y") )
					{
						applicant=new ApplicantDTO();
						SscInfoDTO sscInfo = new SscInfoDTO();	
						sscInfo.setRoll(r.getString("CONTACT_NO"));
						sscInfo.setRoll(r.getString("ROLL_NO"));
						sscInfo.setReg_no(r.getString("REG_NO"));
						sscInfo.setBoard_id(r.getString("BOARD_ID"));
						sscInfo.setBoard_name(r.getString("BOARD_NAME"));
						sscInfo.setGroup_id(r.getString("GROUP_ID"));
						sscInfo.setGroup_name(r.getString("GROUP_NAME"));
						sscInfo.setPassing_year(r.getString("PASS_YEAR"));
						sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
						sscInfo.setFather_name(r.getString("FATHER_NAME"));
						sscInfo.setMother_name(r.getString("MOTHER_NAME"));
						sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
						sscInfo.setGender_id(r.getString("GENDER_ID"));
						sscInfo.setGender_name(r.getString("GENDER_NAME"));
						sscInfo.setGpa(r.getFloat("GPA"));
//						sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
						sscInfo.setEiin(r.getString("SSC_EIIN"));
						applicant.setSsc_info(sscInfo);
						applicant.setContactno(r.getString("CONTACT_NO"));
						ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
						applicant.setApplication_info(applicationInfo);
						applicant.setApplied(appliedweb);
						applicant.setApplication_id(r.getString("APPLICANT_ID"));
						applicationInfo.setQuota_ff(r.getString("QUOTA_FF"));
						applicationInfo.setQuota_eq(r.getString("QUOTA_EDUCATION"));
						applicationInfo.setQuota_bksp(r.getString("QUOTA_BKSP"));
						applicationInfo.setQuota_expatriate(r.getString("QUOTA_EXPATRIATE"));
						if(r.getString("MPOS")!=null)
						{
							String tmp = r.getString("MPOS");
							String []positions=tmp.split(",");
							applicant.setMpos(positions[0]);												
							if(positions.length>1)
								applicant.setApos(positions[1]);
						}

						applicant.setScode(r.getString("SCODE"));
						applicant.setIs_active(r.getString("is_active"));
						applicant.setNo_of_update(r.getString("no_of_update"));
						applicant.setMsg("onlysmsapplication");
					}
	// update module				
					else if(havewebtrans.equalsIgnoreCase("Y") && (appliedweb.equalsIgnoreCase("Y") || appliedsms.equalsIgnoreCase("Y")))  // no need to check SMS application....because we donot permit to edit SMS application
					{
						// have application in both WEB and SMS
						applicant=new ApplicantDTO();
						SscInfoDTO sscInfo = new SscInfoDTO();	
						sscInfo.setRoll(r.getString("CONTACT_NO"));
						sscInfo.setRoll(r.getString("ROLL_NO"));
						sscInfo.setReg_no(r.getString("REG_NO"));
						sscInfo.setBoard_id(r.getString("BOARD_ID"));
						sscInfo.setBoard_name(r.getString("BOARD_NAME"));
						sscInfo.setGroup_id(r.getString("GROUP_ID"));
						sscInfo.setGroup_name(r.getString("GROUP_NAME"));
						sscInfo.setPassing_year(r.getString("PASS_YEAR"));
						sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
						sscInfo.setFather_name(r.getString("FATHER_NAME"));
						sscInfo.setMother_name(r.getString("MOTHER_NAME"));
						sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
						sscInfo.setGender_id(r.getString("GENDER_ID"));
						sscInfo.setGender_name(r.getString("GENDER_NAME"));
						sscInfo.setGpa(r.getFloat("GPA"));
//						sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
						sscInfo.setEiin(r.getString("SSC_EIIN"));
						applicant.setSsc_info(sscInfo);
						applicant.setContactno(r.getString("CONTACT_NO"));
						ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
						applicant.setApplication_info(applicationInfo);
						applicant.setApplied(appliedweb);
						applicant.setApplication_id(r.getString("APPLICANT_ID"));
						if(r.getString("MPOS")!=null)
						{
							String tmp = r.getString("MPOS");
							String []positions=tmp.split(",");
							applicant.setMpos(positions[0]);												
							if(positions.length>1)
								applicant.setApos(positions[1]);
						}

						applicant.setScode(r.getString("SCODE"));
						applicant.setNo_of_update(r.getString("no_of_update"));
						applicant.setIs_active(r.getString("is_active"));
						applicant.setMsg("alreadyapplied");
					}
					
				}
				else if(havewebtrans.equalsIgnoreCase("Y") && appliedweb.equalsIgnoreCase("N") && appliedsms.equalsIgnoreCase("N"))
				{
					// payment but no application
					applicant=new ApplicantDTO();
					applicant.setMsg("p_no_application");
				}				
				else 
				{
					applicant=new ApplicantDTO();
					SscInfoDTO sscInfo = new SscInfoDTO();	
					sscInfo.setRoll(r.getString("CONTACT_NO"));
					sscInfo.setRoll(r.getString("ROLL_NO"));
					sscInfo.setReg_no(r.getString("REG_NO"));
					sscInfo.setBoard_id(r.getString("BOARD_ID"));
					sscInfo.setBoard_name(r.getString("BOARD_NAME"));
					sscInfo.setGroup_id(r.getString("GROUP_ID"));
					sscInfo.setGroup_name(r.getString("GROUP_NAME"));
					sscInfo.setPassing_year(r.getString("PASS_YEAR"));
					sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
					sscInfo.setFather_name(r.getString("FATHER_NAME"));
					sscInfo.setMother_name(r.getString("MOTHER_NAME"));
					sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
					sscInfo.setGender_id(r.getString("GENDER_ID"));
					sscInfo.setGender_name(r.getString("GENDER_NAME"));
					sscInfo.setGpa(r.getFloat("GPA"));
//					sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
					sscInfo.setEiin(r.getString("SSC_EIIN"));
					applicant.setSsc_info(sscInfo);
					applicant.setContactno(r.getString("CONTACT_NO"));
					ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
					applicant.setApplication_info(applicationInfo);
					applicant.setApplied(appliedweb);
					applicant.setApplication_id(r.getString("APPLICANT_ID"));
					applicationInfo.setQuota_ff(r.getString("QUOTA_FF"));
					applicationInfo.setQuota_eq(r.getString("QUOTA_EDUCATION"));
					applicationInfo.setQuota_bksp(r.getString("QUOTA_BKSP"));
					applicationInfo.setQuota_expatriate(r.getString("QUOTA_EXPATRIATE"));
					if(r.getString("MPOS")!=null)
					{
						String tmp = r.getString("MPOS");
						String []positions=tmp.split(",");
						applicant.setMpos(positions[0]);												
						if(positions.length>1)
							applicant.setApos(positions[1]);
					}

					applicant.setScode(r.getString("SCODE"));
					applicant.setIs_active(r.getString("is_active"));
					applicant.setNo_of_update(r.getString("no_of_update"));
					applicant.setMsg("readOnlyApplication");
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
	public boolean sentSecurityCode(String sscRoll, String sscBoard, String sscYear,String sscReg){
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = " Select APPLICATION_ID,STUDENT_NAME,scode,CONTACT_NO FROM " +
		" BOARD_DATA_SSC  " +
		" where ROLL_NO = ? AND BOARD_ID = ? AND PASSING_YEAR = ? AND REG_NO=? ";
		
		
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
				String scode = getSecurityCode();
				String mono = r.getString("CONTACT_NO");
				SmsSender smsSender=new SmsSender();
				smsSender.setMobile(mono.substring(mono.length()-11));
				smsSender.setAppid(r.getString("APPLICATION_ID"));
				smsSender.setName(r.getString("STUDENT_NAME"));
				smsSender.setOtp(scode);
				smsSender.setOperation("smsScode");
					
				Thread thread = new Thread(smsSender);
				thread.start();
				

			} 
		}
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return true;
	}
	
	public ApplicantDTO getApplication_Quota_TT(String sscRoll, String sscBoard, String sscYear,String sscReg){
		ApplicantDTO applicant = null;
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "SELECT tmp1.*,nid," +
				"       application.applicant_id APPLICATION_ID," +
				"       QUOTA_EDUCATION," +
				"       QUOTA_EXPATRIATE," +
				"       QUOTA_BKSP," +
				"       QUOTA_FF,CONTACT_NO,HAS_WEB_TRNS TT_TRANS_NUMBER" +
				"  FROM (SELECT B.ROLL_NO," +
				"               B.REG_NO," +
				"               B.BOARD_ID," +
				"               BOARD_NAME BOARD_NAME_ENG," +
				"               B.GROUP_ID," +
				"               GROUP_NAME," +
				"               B.PASS_YEAR PASSING_YEAR," +
				"               B.STUDENT_NAME," +
				"               B.FATHER_NAME," +
				"               B.MOTHER_NAME," +
				"               TO_CHAR (B.BIRTH_DATE, 'DD-MM-YYYY') BIRTH_DATE," +
				"               DECODE (B.GENDER,  'M', 'Male',  'F', 'Female') GENDER_NAME," +
				"               GENDER GENDER_ID," +
				"               B.GPA," +
				"               SSC_EIIN" +
				"          FROM BOARD_DATA_SSC B" +
				"         WHERE     B.ROLL_NO = ?" +
				"               AND B.BOARD_ID = ?" +
				"               AND B.PASS_YEAR = ?" +
				"               AND B.REG_NO = ?) tmp1" +
				"       LEFT OUTER JOIN" +
				"       APPLICANT_INFO application" +
				"          ON     tmp1.ROLL_NO = application.ROLL_NO" +
				"             AND tmp1.BOARD_ID = application.BOARD_ID" +
				"             AND tmp1.PASSING_YEAR = application.PASS_YEAR ";
		
		
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
//					sscInfo.setGpa_exc4th(r.getFloat("GPA_EXC4TH"));	
					sscInfo.setEiin(r.getString("SSC_EIIN"));
					applicant.setSsc_info(sscInfo);
					ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
					
					applicationInfo.setQuota_ff(r.getString("QUOTA_FF"));
					applicationInfo.setQuota_eq(r.getString("QUOTA_EDUCATION"));
					applicationInfo.setQuota_bksp(r.getString("QUOTA_BKSP"));
					applicationInfo.setQuota_expatriate(r.getString("QUOTA_EXPATRIATE"));
					applicationInfo.setMobile_number(r.getString("CONTACT_NO"));
					applicationInfo.setNid_number(r.getString("NID")==null?"Not Given":r.getString("NID"));
					
					applicant.setApplication_info(applicationInfo);
					if(r.getString("CONTACT_NO")==null)
						applicant.setMsg("noapp");

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
		" SELECT B.ROLL_NO, B.REG_NO, B.BOARD_ID,B.TT_TRANS_NUMBER,B.APPLIEDWEB,B.APPLIEDSMS, BOARD_NAME_ENG, " +
		" B.GROUP_ID, GROUP_NAME, B.PASSING_YEAR, B.STUDENT_NAME, " +
		" B.FATHER_NAME, B.MOTHER_NAME, to_char(B.BIRTH_DATE,'DD-MM-YYYY') BIRTH_DATE, " +
		" decode(B.GENDER,'M','Male','F','Female') GENDER_NAME,GENDER GENDER_ID, B.GPA, B.GPA_EXC4TH,SSC_EIIN,CONTACT_NO,mpos FROM " +
		" BOARD_DATA_SSC B " +
		" where B.ROLL_NO = ? AND B.BOARD_ID = ? AND B.PASSING_YEAR = ? AND B.REG_NO=?)tmp1 Left Outer Join APPLICATION_INFO application " +
		" on tmp1.ROLL_NO=application.SSC_ROLL_NO " +
		" AND tmp1.BOARD_ID=application.SSC_BOARD_ID " +
		" AND tmp1.PASSING_YEAR=application.SSC_PASSING_YEAR ";
		
		
		sql=" SELECT tmp1.*, "+
			" application.APPLICANT_ID APPLICATION_ID, application.CONTACT_NO, "+
				"         'test' TT_TRANS_NUMBER, "+
				"   QUOTA_EDUCATION, "+
				"   QUOTA_EXPATRIATE, "+
				"   QUOTA_BKSP, "+
				"   QUOTA_FF,application.* "+
				" FROM (SELECT B.ROLL_NO, "+
				"          B.REG_NO, "+
				"          B.BOARD_ID, "+
				"          BOARD_NAME BOARD_NAME_ENG, "+
				"          B.GROUP_ID, "+
				"           GROUP_NAME, "+
				"          B.PASS_YEAR PASSING_YEAR, "+
				"          B.STUDENT_NAME, "+
				"          B.FATHER_NAME, "+
				"          B.MOTHER_NAME, "+
				"          TO_CHAR (B.BIRTH_DATE, 'DD-MM-YYYY') BIRTH_DATE, "+
				"          DECODE (B.GENDER,  'M', 'Male',  'F', 'Female') GENDER_NAME, "+
				"          GENDER GENDER_ID, "+
				"          B.GPA, "+
				"          B.GPA_4TH_SUB GPA_EXC4TH, "+
				"          SSC_EIIN, "+
				"          MERIT_POS  mpos "+
				"     FROM BOARD_DATA_SSC B "+
				"    WHERE     B.ROLL_NO = ? "+
				"          AND B.BOARD_ID = ? "+
				"          AND B.PASS_YEAR = ? "+
				"          AND B.REG_NO = ?) tmp1 "+
				"  LEFT OUTER JOIN "+
				"  APPLICANT_INFO application "+
				"     ON     tmp1.ROLL_NO = application.ROLL_NO "+
				"        AND tmp1.BOARD_ID = application.BOARD_ID "+
				"        AND tmp1.PASSING_YEAR = application.PASS_YEAR " ;
		
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
				String applied = r.getString("APPLIED_WEB");
				String appliedsms = r.getString("APPLIED_SMS");
				/*
				if(transaction==null)
				{
					applicant=new ApplicantDTO();
					applicant.setMsg("nopayment");
				}
				
				*/
					applicant=new ApplicantDTO();
					applicant.setApplication_id(r.getString("APPLICANT_ID"));
					SscInfoDTO sscInfo = new SscInfoDTO();					
					sscInfo.setRoll(r.getString("ROLL_NO"));
					sscInfo.setReg_no(r.getString("REG_NO"));
					sscInfo.setBoard_id(r.getString("BOARD_ID"));
					sscInfo.setBoard_name(r.getString("BOARD_NAME_ENG"));
					sscInfo.setGroup_id(r.getString("GROUP_ID"));
					sscInfo.setGroup_name(r.getString("GROUP_NAME"));
					sscInfo.setPassing_year(r.getString("PASS_YEAR"));
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
					applicationInfo.setNid_number(r.getString("NID"));
					applicationInfo.setNidholder(r.getString("nidholder"));
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
					applicant.setAppliedsms(appliedsms);
					if(r.getString("MPOS")!=null)
					{
						String tmp = r.getString("MPOS");
						String []positions=tmp.split(",");
						applicant.setMpos(positions[0]);												
						if(positions.length>1)
							applicant.setApos(positions[1]);
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
		String sql = " SELECT B.ROLL_NO," +
				"       B.REG_NO," +
				"       B.BOARD_ID," +
				"       B.BOARD_NAME," +
				"       GROUP_ID," +
				"       GROUP_NAME," +
				"       B.PASS_YEAR," +
				"       B.STUDENT_NAME," +
				"       B.FATHER_NAME," +
				"       B.MOTHER_NAME," +
				"       TO_CHAR (B.BIRTH_DATE, 'DD-MM-YYYY') BIRTH_DATE," +
				"       DECODE (B.GENDER,  'M', 'Male',  'F', 'Female') GENDER_NAME," +
				"       B.GENDER GENDER_ID," +
				"       B.GPA," +
				"       b.SSC_EIIN," +
				"       a.Applicant_id," +
				"       QUOTA_FF," +
				"       QUOTA_EDUCATION," +
				"       QUOTA_BKSP," +
				"       QUOTA_EXPATRIATE," +
				"       CONTACT_NO,a.nid " +
				"  FROM APPLICANT_INFO a, BOARD_DATA_SSC B" +
				" WHERE     a.ROLL_NO = B.ROLL_NO" +
				"       AND a.BOARD_ID = B.BOARD_ID" +
				"       AND a.PASS_YEAR = B.PASS_YEAR" +
				"       AND A.Applicant_id = ?" ;
		
		
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
			r = stmt.executeQuery();
			if(r.next())
			{
				
				applicant=new ApplicantDTO();
				applicant.setApplication_id(r.getString("APPLICANT_ID"));
				SscInfoDTO sscInfo = new SscInfoDTO();
				
				sscInfo.setRoll(r.getString("ROLL_NO"));
				sscInfo.setReg_no(r.getString("REG_NO"));
				sscInfo.setBoard_id(r.getString("BOARD_ID"));
				sscInfo.setBoard_name(r.getString("BOARD_NAME"));
				sscInfo.setGroup_id(r.getString("GROUP_ID"));
				sscInfo.setGroup_name(r.getString("GROUP_NAME"));
				sscInfo.setPassing_year(r.getString("PASS_YEAR"));
				sscInfo.setStudent_name(r.getString("STUDENT_NAME"));
				sscInfo.setFather_name(r.getString("FATHER_NAME"));
				sscInfo.setMother_name(r.getString("MOTHER_NAME"));
				sscInfo.setBirth_date(r.getString("BIRTH_DATE"));
				sscInfo.setGender_id(r.getString("GENDER_ID"));
				sscInfo.setGender_name(r.getString("GENDER_NAME"));
				sscInfo.setGpa(r.getFloat("GPA"));
				sscInfo.setEiin(r.getString("SSC_EIIN"));
				
				applicant.setSsc_info(sscInfo);
				
				ApplicationInfoDTO applicationInfo = new ApplicationInfoDTO();
				applicationInfo.setNid_number(r.getString("nid"));
				applicationInfo.setMobile_number(r.getString("CONTACT_NO"));
				//applicationInfo.setQuota_yn(r.getString("QUOTA_YN"));
				if(r.getString("QUOTA_FF").equals("N"))
				  applicationInfo.setQuota_ff("N");
				else
				  applicationInfo.setQuota_ff("Y");
				
				if(r.getString("QUOTA_EDUCATION").equals("N"))
				   applicationInfo.setQuota_eq("N");
				else
					applicationInfo.setQuota_eq("Y");
				if(r.getString("QUOTA_BKSP").equals("N"))
				   applicationInfo.setQuota_bksp("N");
				else
					applicationInfo.setQuota_bksp("Y");
				if(r.getString("QUOTA_EXPATRIATE").equals("N"))
				   applicationInfo.setQuota_expatriate("N");
				else
					applicationInfo.setQuota_expatriate("Y");	
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

	
	private String getSecurityCode() 
	{
	    Random rand = new Random();
	    int length = 4;//rand.nextInt(6) + 16;
	    char[] password = new char[length];
	    for (int x = 0; x < length; x++) {
	      int randDecimalAsciiVal = 0;
	      int cas = rand.nextInt(3);
	      if (cas == 0)
	        randDecimalAsciiVal = rand.nextInt(9) + 48;
	      else if (cas == 1)
	        randDecimalAsciiVal = rand.nextInt(26) + 65;
	      else
	        randDecimalAsciiVal = rand.nextInt(26) + 97;
	      password[x] = String.valueOf(randDecimalAsciiVal/10).charAt(0);
	    }
	    String result = String.valueOf(password);
	    return result;
	}
}
