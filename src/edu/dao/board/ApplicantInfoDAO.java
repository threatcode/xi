package edu.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;


import edu.dto.IpAddressDTO;
import edu.dto.application.ChoiceDTO;
import edu.dto.application.ResponseDTO;
import edu.dto.board.ApplicantInfoBoardDTO;
import edu.utils.connection.ConnectionManager;

public class ApplicantInfoDAO {
	
	public ResponseDTO editQuota(String previous_quota_ff,String previous_quota_eq,String previous_quota_bksp,String previous_quota_expatriate,
			String quota_ff,String quota_eq,String quota_bksp,String quota_expatriate,String application_id,String userId,IpAddressDTO ipAddress)
	{
		ResponseDTO response =new ResponseDTO();
		Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		
		try {
			stmt = (OracleCallableStatement) conn.prepareCall("{ call update_quota(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
			stmt.setString(1, quota_ff);
		    stmt.setString(2, quota_eq);
		    stmt.setString(3, quota_bksp);
		    stmt.setString(4, quota_expatriate);
		    stmt.setString(5, application_id);
		    stmt.setString(6, userId);
		    stmt.setString(7, ipAddress.getxForward());
            stmt.setString(8, ipAddress.getVia());            
            stmt.setString(9, ipAddress.getRemoteAddress());
			
            stmt.registerOutParameter(10, java.sql.Types.INTEGER); 
            stmt.registerOutParameter(11, java.sql.Types.VARCHAR); 
            
        	stmt.setString(12, previous_quota_ff);
		    stmt.setString(13, previous_quota_eq);
		    stmt.setString(14, previous_quota_bksp);
		    stmt.setString(15, previous_quota_expatriate);
            
            stmt.executeUpdate();
            
/*          response.setStatus(stmt.getString(9));5-9\\ 6-10
            response.setMessage(stmt.getString(10));*/
            
               if(stmt.getInt(10)==1){
            	response.setStatus("OK");
            	response.setMessage(stmt.getString(11));

            }
            else{
            	response.setStatus("ERROR");
            	response.setMessage(stmt.getString(11));
            }
         } 
		catch (Exception e){e.printStackTrace();
			response.setStatus("ERROR");
			response.setMessage(e.getMessage());
		}
		finally{try{stmt.close();
		ConnectionManager.closeConnection(conn);
		} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
	
        return response;
        
	}
	
/*	public ResponseDTO editQuota(String quota_ff,String quota_eq,String quota_bksp,String quota_expatriate,String application_id,String userId,IpAddressDTO ipAddress){

		
		Connection conn = ConnectionManager.getWriteConnection();
		String sql= " Update APPLICATION_INFO set QUOTA_FF=?,QUOTA_EDUCATION=?,QUOTA_BKSP=?,QUOTA_EXPATRIATE=? Where APPLICATION_ID=?";
		
		   PreparedStatement stmt = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, quota_ff);
			    stmt.setString(2, quota_eq);
			    stmt.setString(3, quota_bksp);
			    stmt.setString(4, quota_expatriate);
			    stmt.setString(5, application_id);
				stmt.executeUpdate();
				response=true;
				
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
			return response;

	}*/
	
	
	public ApplicantInfoBoardDTO getApplicantAdmissionInfo(String ssc_roll,String ssc_board,String ssc_year,String ssc_reg){
		ApplicantInfoBoardDTO applicantInfoDTO = null;

		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="SELECT a.*" +
				"  FROM (SELECT *" +
				"          FROM board_result" +
				"         WHERE (APPLICATION_ID, merit_type) IN" +
				"                  (  SELECT APPLICATION_ID, MAX (merit_type)" +
				"                       FROM BOARD_RESULT" +
				"                   GROUP BY APPLICATION_ID)) a," +
				"       application_info b" +
				" WHERE     b.SSC_ROLL_NO = ?" +
				"       AND B.SSC_BOARD_ID = ?" +
				"       AND B.SSC_PASSING_YEAR = ?" +
				"       AND B.SSC_REG = ?" +
				"       AND A.COLLEGE_RECEIVE = 'College Received'" +
				"       AND A.APPLICATION_ID = B.APPLICATION_ID";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,ssc_roll.trim());
			stmt.setString(2,ssc_board.trim());
			stmt.setString(3,ssc_year.trim());
			stmt.setString(4,ssc_reg.trim());
			

			
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
	
	
	public ApplicantInfoBoardDTO getApplicantAdmissionCancelledInfo(String ssc_roll,String ssc_board,String ssc_year,String ssc_reg,String eiinCode){
		ApplicantInfoBoardDTO applicantInfoDTO = null;

		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="select a.* from board_result_merit a,application_info b\n" +
	              "where b.SSC_ROLL_NO=?\n" +
	              "and B.SSC_BOARD_ID=?\n" +
	              "and B.SSC_PASSING_YEAR=?\n" +
	              "and B.SSC_REG=?\n" +
	              "and a.EIIN=?\n" +
	              "and A.STATUS='Admission Cancelled'\n" +
	              "and A.APPLICATION_ID=B.APPLICATION_ID";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,ssc_roll.trim());
			stmt.setString(2,ssc_board.trim());
			stmt.setString(3,ssc_year.trim());
			stmt.setString(4,ssc_reg.trim());
			stmt.setString(5,eiinCode.trim());
			

			
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
	
	
	
	
	
	
	public String[] cancelAdmissionByBoard(String applicationID[], String ImeritType[],String Ishift_id[],String Iversion_id[],String Igroup_id[], String Ieiin_code[],String quota[],String Iuser_Id,IpAddressDTO ipAddress){

		Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		
		String[] tmp = null;
		int operation=0;
		try {
			
			ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
			
			ARRAY IappId=new ARRAY(arrString,conn,applicationID);
			ARRAY ImeritTyp=new ARRAY(arrString,conn,ImeritType);
			ARRAY Ishift=new ARRAY(arrString,conn,Ishift_id);
			ARRAY Iversion=new ARRAY(arrString,conn,Iversion_id);
			ARRAY Igroup=new ARRAY(arrString,conn,Igroup_id);
			ARRAY Ieiin=new ARRAY(arrString,conn,Ieiin_code);
			ARRAY Iquota=new ARRAY(arrString,conn,quota);

			
			
			String response = "";
            stmt = (OracleCallableStatement) conn.prepareCall("{ call CANCELADMISSIONBYBOARD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");
            
            stmt.setARRAY(1, IappId);      
            stmt.setARRAY(2, ImeritTyp);
            stmt.setARRAY(3, Ieiin);
            stmt.setString(4, "Admission Cancelled");
            stmt.setString(5, ipAddress.getxForward());
            stmt.setString(6, ipAddress.getVia());
            stmt.setString(7, ipAddress.getRemoteAddress());
            stmt.registerOutParameter(8, java.sql.Types.INTEGER);
            stmt.registerOutParameter(9, java.sql.Types.INTEGER);
            stmt.registerOutParameter(10, java.sql.Types.VARCHAR);
            stmt.setARRAY(11, Ishift);
            stmt.setARRAY(12, Iversion);
            stmt.setARRAY(13, Igroup);
            stmt.registerOutParameter(14, java.sql.Types.VARCHAR);
            stmt.setString(15, Iuser_Id);
            stmt.setARRAY(16, Iquota);
            
            stmt.executeUpdate();
            
            response=stmt.getString(14);
            
            operation=stmt.getInt(8);
            	
            tmp = response.split("###");
            
            
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
	
	
	
	
	
	
	public String[] reApproveAdmissionByBoard(String applicationID[], String ImeritType[],String Ishift_id[],String Iversion_id[],String Igroup_id[], String Ieiin_code[],String quota[],String Iuser_Id,IpAddressDTO ipAddress){

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
			ARRAY Ieiin=new ARRAY(arrString,conn,Ieiin_code);
			ARRAY Iquota=new ARRAY(arrString,conn,quota);

			
			
			String response = "";
            stmt = (OracleCallableStatement) conn.prepareCall("{ call RECEIVEADMISSIONBYBOARD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");
            
            stmt.setARRAY(1, IappId);      
            stmt.setARRAY(2, ImeritTyp);
            stmt.setARRAY(3, Ieiin);
            stmt.setString(4, "College Received");
            stmt.setString(5, ipAddress.getxForward());
            stmt.setString(6, ipAddress.getVia());
            stmt.setString(7, ipAddress.getRemoteAddress());
            stmt.registerOutParameter(8, java.sql.Types.INTEGER);
            stmt.registerOutParameter(9, java.sql.Types.INTEGER);
            stmt.registerOutParameter(10, java.sql.Types.VARCHAR);
            stmt.setARRAY(11, Ishift);
            stmt.setARRAY(12, Iversion);
            stmt.setARRAY(13, Igroup);
            stmt.registerOutParameter(14, java.sql.Types.VARCHAR);
            stmt.setString(15, Iuser_Id);
            stmt.setARRAY(16, Iquota);
            
            stmt.executeUpdate();
            
            response=stmt.getString(14);
            
            operation=stmt.getInt(8);
            	
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
	
	public List<ApplicantInfoBoardDTO> getApplicantRecoverydata(String uploadedBy){
		
		List<ApplicantInfoBoardDTO> applicantRecoverydataList= new ArrayList<ApplicantInfoBoardDTO>();
		ApplicantInfoBoardDTO applicantRecoveryDTO= null;
		
		Connection conn = ConnectionManager.getReadConnection();	
		
		String sql ="SELECT c.STUDENT_NAME,\n" +
		         "       b.SSC_ROLL,\n" +
		         "       b.SSC_BOARD,\n" +
		         "       a.BOARD_NAME_ENG,\n" +
		         "       b.SSC_PASSING_YEAR,\n" +
		         "       b.SSC_REG,\n" +
		         "       b.MOBILE_NO\n" +
		         "  FROM MST_EDU_BOARD a, XLS_UPLOAD_TEST b, BOARD_DATA_SSC c\n" +
		         " WHERE     b.UPLOADED_BY =?\n" +
		         "       AND b.SSC_BOARD = c.BOARD_ID(+)\n" +
		         "       AND b.SSC_PASSING_YEAR = c.PASSING_YEAR(+)\n" +
		         "       AND b.SSC_ROLL = c.ROLL_NO(+)\n" +
		         "       AND a.BOARD_ID = b.SSC_BOARD";
		
/*		String sql ="SELECT c.STUDENT_NAME,\n" +
		         "       b.SSC_ROLL,\n" +
		         "       b.SSC_BOARD,\n" +
		         "       a.BOARD_NAME_ENG,\n" +
		         "       b.SSC_PASSING_YEAR,\n" +
		         "       b.SSC_REG,\n" +
		         "       b.MOBILE_NO\n" +
		         "  FROM MST_EDU_BOARD a, XLS_UPLOAD_TEST b, BOARD_DATA_SSC c\n" +
		         " WHERE     b.UPLOADED_BY =?\n" +
		         "       AND b.SSC_BOARD(+) = c.BOARD_ID\n" +
		         "       AND b.SSC_PASSING_YEAR(+) = c.PASSING_YEAR\n" +
		         "       AND b.SSC_ROLL(+) = c.ROLL_NO\n" +
		         "       AND a.BOARD_ID = b.SSC_BOARD";*/
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,uploadedBy);
			r = stmt.executeQuery();
			while(r.next())
			{
				applicantRecoveryDTO = new ApplicantInfoBoardDTO();	
				
				applicantRecoveryDTO.setApplicantName(r.getString("STUDENT_NAME"));
				applicantRecoveryDTO.setSscRollNo(r.getString("SSC_ROLL"));
				applicantRecoveryDTO.setBoardID(r.getString("SSC_BOARD"));
				applicantRecoveryDTO.setBoardName(r.getString("BOARD_NAME_ENG"));
				applicantRecoveryDTO.setSscPassingYear(r.getString("SSC_PASSING_YEAR"));
				applicantRecoveryDTO.setRegistrationNumber(r.getString("SSC_REG"));
				applicantRecoveryDTO.setMobilenumber(r.getString("MOBILE_NO"));
				

				
				applicantRecoverydataList.add(applicantRecoveryDTO);
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
		
		return applicantRecoverydataList;
	}
	
	
	public boolean isnotDuplicate(String SSC_ROLL,String SSC_BOARD,String SSC_YEAR,String REG_NO)
	{
		Connection conn = ConnectionManager.getReadConnection();		
		String sql ="SELECT * from XLS_UPLOAD_TEST where SSC_ROLL='"+SSC_ROLL+"' and SSC_BOARD='"+SSC_BOARD+"' and SSC_PASSING_YEAR='"+SSC_YEAR+"' and SSC_REG='"+REG_NO+"'";
		
		boolean isValidate=true;
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while(r.next())
			{
				isValidate=false;
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
		
		
		return isValidate;
	}
	public boolean isValidate(String SSC_ROLL,String SSC_BOARD,String SSC_YEAR,String REG_NO)
	{
		Connection conn = ConnectionManager.getReadConnection();		
		String sql ="SELECT * from BOARD_DATA_SSC where ROLL_NO='"+SSC_ROLL+"' and BOARD_ID='"+SSC_BOARD+"' and PASSING_YEAR='"+SSC_YEAR+"' and REG_NO='"+REG_NO+"'";
		
		boolean isValidate=false;
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while(r.next())
			{
				isValidate=true;
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
		
		
		return isValidate;
	}
	public Boolean insertapplicantRecoverydata(String sscRollNo,String sscPassingYear,String boardID,String registrationNumber,String mobilenumber,String boardUserID)
	{
		Connection conn = ConnectionManager.getWriteConnection();
		
		
		String sql = "insert into XLS_UPLOAD_TEST (SSC_ROLL,SSC_BOARD,SSC_PASSING_YEAR,SSC_REG,MOBILE_NO,UPLOADED_BY,SMS_SEND) values(?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try
		{
			if(!isDuplicate(sscRollNo,boardID,sscPassingYear))
			{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sscRollNo);
			stmt.setString(2, boardID);
			stmt.setString(3, sscPassingYear);
			stmt.setString(4, registrationNumber);
			stmt.setString(5, mobilenumber);
			stmt.setString(6, boardUserID);
			stmt.setString(7, "N");
			stmt.executeUpdate();
			
			}
			
			else
			{
				return Boolean.FALSE;
				//System.out.println("Duplicate Entry");
			}
	
			
			
		} 
		catch (Exception e){
			e.printStackTrace();
			return Boolean.FALSE;
		}
		finally{
			try{
				if(stmt!= null){
				stmt.close();
				}
				conn.close();
				} 
			catch (Exception e){
					e.printStackTrace();
				}
			stmt = null;
			conn = null;
		}
		
		return Boolean.TRUE;
		
	}
	
	
	public Boolean deleteApplicantRecoverydata(String ssc_roll[],String ssc_board[],String ssc_passing_year[])
	{
		Connection conn = ConnectionManager.getWriteConnection();
		
		String sql = "DELETE FROM XLS_UPLOAD_TEST WHERE SSC_ROLL =? AND SSC_BOARD =? AND SSC_PASSING_YEAR =? ";
		PreparedStatement stmt = null;
		try
		{
			for(int i=0;i<ssc_roll.length;i++)
			{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ssc_roll[i]);
			stmt.setString(2, ssc_board[i]);
			stmt.setString(3, ssc_passing_year[i]);

			stmt.executeUpdate();
			
		} 
		}
		catch (Exception e){
			e.printStackTrace();
			return Boolean.FALSE;
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
		
		return Boolean.TRUE;
		
	}
	
	
	
	
	public List<ApplicantInfoBoardDTO> getApplicantBasicInfoNotSend(){
		List<ApplicantInfoBoardDTO> listAppliDTO = new ArrayList<ApplicantInfoBoardDTO>();
		ApplicantInfoBoardDTO tmpNew= new ApplicantInfoBoardDTO();
		Connection conn = ConnectionManager.getReadConnection();		
		String sql ="select * from (" +
				"  SELECT *" +
				"    FROM (SELECT T1.*,to_char(t1.applied_on,'dd/mm/yyyy') apptime, T2.NAME, T2.SSC_ROLL_NO" +
				"            FROM APPLICATION_SMS t1, application_info t2" +
				"           WHERE T1.APPLICATION_ID = T2.APPLICATION_ID AND application = 'N')" +// and t1.application_id='1189429')" +
				" ORDER BY application_id" +
				")";
		
		
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
				tmpNew.setApplicantName(r.getString("NAME"));
				tmpNew.setSscRollNo(r.getString("SSC_ROLL_NO"));
				tmpNew.setApptime(r.getString("APPTIME"));
				tmpNew.setMobilenumber(r.getString("MOBILE_NO"));
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
	
	public List<ApplicantInfoBoardDTO> getApplicantByBoardForResult(String boardid){
		List<ApplicantInfoBoardDTO> listAppliDTO = new ArrayList<ApplicantInfoBoardDTO>();
		ApplicantInfoBoardDTO tmpNew= new ApplicantInfoBoardDTO();
		Connection conn = ConnectionManager.getReadConnection();		
		String sql ="select * from SMS_RESULT where application_id="+boardid+" and rownum<5000";
		
		
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
				tmpNew.setSmstext(r.getString("SMSTEXT"));
				tmpNew.setMobilenumber(r.getString("MOBILE_NO"));
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
	
	public List<String> getAllNonPAidApplicant(){
		List<String> listAppliDTO = new ArrayList<String>();
		Connection conn = ConnectionManager.getReadConnection();		
		String sql =""+//select * from (" +
				"				SELECT distinct APPLICATION_ID from application_colleges ac " +
				"				                   WHERE  AC.PAYMENT_STATUS = 'N' and applied_on>to_date('22-06-15:00-00','dd-mm-yy:hh24-mi')";
		
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while(r.next())
			{
				listAppliDTO.add(r.getString("APPLICATION_ID"));
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
	
	public List<ApplicantInfoBoardDTO> getApplicantBasicInfoNotSend_All(){
		List<ApplicantInfoBoardDTO> listAppliDTO = new ArrayList<ApplicantInfoBoardDTO>();
		ApplicantInfoBoardDTO tmpNew= new ApplicantInfoBoardDTO();
		Connection conn = ConnectionManager.getReadConnection();		
		String sql =""+//select * from (" +
				"				SELECT distinct AI.APPLICATION_ID,AI.SSC_ROLL_NO,AI.SSC_BOARD_ID,AI.SSC_PASSING_YEAR,BS.GENDER," +
				"				                   BS.STUDENT_NAME,BS.CONTACT_NO FROM application_info ai, application_colleges ac, BOARD_DATA_SSC bs" +
				"				                   WHERE     AC.APPLICATION_TYPE='WEB' AND AC.TTSEND = 'N'" +
				"				                      AND AI.APPLICATION_ID = AC.APPLICATION_ID" +
				"				                      AND AI.SSC_ROLL_NO = BS.ROLL_NO" +
				"				                      AND AI.SSC_BOARD_ID = BS.BOARD_ID" +
				"				                       AND AI.SSC_PASSING_YEAR = BS.PASSING_YEAR" +
				"				order by AI.APPLICATION_ID desc";// +
//				" ) where rownum <51";
		
		
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
				tmpNew.setMobilenumber(r.getString("CONTACT_NO"));
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
				"   BS.STUDENT_NAME,BS.CONTACT_NO FROM application_info ai, application_colleges ac, BOARD_DATA_SSC bs" +
				"   WHERE     AI.APPLICATION_ID='" + app_id +"' " +
				"      AND AC.APPLICATION_TYPE='WEB' AND AC.TTSEND = 'N' " +
				"      AND AI.APPLICATION_ID = AC.APPLICATION_ID" +
				"      AND AI.SSC_ROLL_NO = BS.ROLL_NO" +
				"      AND AI.SSC_BOARD_ID = BS.BOARD_ID" +
				"       AND AI.SSC_PASSING_YEAR = BS.PASSING_YEAR";
//		if(app_id.startsWith("7"))
//			sql ="SELECT distinct AI.APPLICATION_ID,AI.SSC_ROLL_NO,AI.SSC_BOARD_ID,AI.SSC_PASSING_YEAR,BS.GENDER," +
//					"   BS.STUDENT_NAME,AI.MOBILE_NUMBER FROM application_info_new ai, application_colleges_new ac, BOARD_DATA_SSC bs" +
//					"   WHERE     AI.APPLICATION_ID='" + app_id +"' " +
//	//				"      AND AC.PAYMENT_STATUS = 'N'" +
//					"      AND AI.APPLICATION_ID = AC.APPLICATION_ID" +
//					"      AND AI.SSC_ROLL_NO = BS.ROLL_NO" +
//					"      AND AI.SSC_BOARD_ID = BS.BOARD_ID" +
//					"       AND AI.SSC_PASSING_YEAR = BS.PASSING_YEAR";
		
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
				tmpNew.setMobilenumber(r.getString("CONTACT_NO"));
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
				"                   BS.STUDENT_NAME,AI.MOBILE_NUMBER FROM application_info ai, application_colleges ac, BOARD_DATA_SSC bs" +
				"                   WHERE     AI.APPLICATION_ID in (select application_id from abc123)" +
				"                      AND AI.APPLICATION_ID = AC.APPLICATION_ID" +
				"                      AND AI.SSC_ROLL_NO = BS.ROLL_NO" +
				"                      AND AI.SSC_BOARD_ID = BS.BOARD_ID" +
				"                       AND AI.SSC_PASSING_YEAR = BS.PASSING_YEAR";
		
		
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
		String sql ="SELECT AI.APPLICATION_ID,AC.EIIN,AC.SHIFT_ID,AC.VERSION_ID,AC.GROUP_ID,AC.QUOTA_SPECIAL,AC.PRIORITY" +
				"  FROM application_info ai, application_colleges ac, BOARD_DATA_SSC bs  where " +
				"      AC.TTSEND = 'N'" +
				"       and AI.APPLICATION_ID=?" +
				"       AND AI.APPLICATION_ID = AC.APPLICATION_ID" +
				"       AND AI.SSC_ROLL_NO = BS.ROLL_NO" +
				"       AND AI.SSC_BOARD_ID = BS.BOARD_ID" +
				"       AND AI.SSC_PASSING_YEAR = BS.PASSING_YEAR";
		
		sql ="SELECT APPLICATION_ID,EIIN,SHIFT_ID,VERSION_ID,GROUP_ID,QUOTA_SPECIAL,PRIORITY" +
			"  FROM application_colleges where " +
			"      TTSEND = 'N' AND APPLICATION_TYPE='WEB' " +
			"       and APPLICATION_ID=?";
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
				tmpNew.setPriority(r.getString("PRIORITY"));
				tmpNew.setSpecial_quota(r.getString("QUOTA_SPECIAL"));
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
	public List<ChoiceDTO> getChoiceDTO_forced(String appid)
	{
		List<ChoiceDTO> cdto = new ArrayList<ChoiceDTO>();
		ChoiceDTO tmpNew= new ChoiceDTO();
		Connection conn = ConnectionManager.getReadConnection();		
		String sql ="SELECT AI.APPLICATION_ID,AC.EIIN,AC.SHIFT_ID,AC.VERSION_ID,AC.GROUP_ID,AC.SPECIAL_QUOTA,AC.PRIORITY_ORDER" +
				"  FROM application_info ai, application_colleges ac, BOARD_DATA_SSC bs  where " +
//				"      AC.TTSEND = 'N' and AC.PAYMENT_STATUS = 'N' " +
				"       AI.APPLICATION_ID=?" +
				"       AND AI.APPLICATION_ID = AC.APPLICATION_ID" +
				"       AND AI.SSC_ROLL_NO = BS.ROLL_NO" +
				"       AND AI.SSC_BOARD_ID = BS.BOARD_ID" +
				"       AND AI.SSC_PASSING_YEAR = BS.PASSING_YEAR";
		if(appid.startsWith("7"))
			sql ="SELECT AI.APPLICATION_ID,AC.EIIN,AC.SHIFT_ID,AC.VERSION_ID,AC.GROUP_ID,AC.SPECIAL_QUOTA,AC.PRIORITY_ORDER" +
				"  FROM application_info_new ai, application_colleges_new ac, BOARD_DATA_SSC bs  where " +
	//			"      AC.TTSEND = 'N' and AC.PAYMENT_STATUS = 'N' " +
				"       AI.APPLICATION_ID=?" +
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
	
/////////////////////////////////////////////////
	public void addXlsApplicantWithBatch( List SSC_ROLL,List SSC_BOARD,List SSC_PASSING_YEAR,List SSC_REG, List MOBILE_NO )
	{
		
		Connection conn = ConnectionManager.getWriteConnection();
		/*deletePreviousData();*/
		String UPLOAD_ID=getUploadID();
 		String sql="INSERT INTO XLS_UPLOAD_test ( " +
 		" UPLOAD_ID,  SSC_ROLL,SSC_BOARD,SSC_PASSING_YEAR, SSC_REG, MOBILE_NO,SMS_SEND ) " +
 		"VALUES (?, ?, ?, ?, ?,?,? ) ";
 		PreparedStatement stmt = null;
 		
 		try
		{
			//conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);		
			for(int i=0;i<SSC_ROLL.size();i++)
			{
				if(!isDuplicate( (String)SSC_ROLL.get(i) , (String)SSC_BOARD.get(i), (String)SSC_PASSING_YEAR.get(i)))
				{
				stmt.setString(1, UPLOAD_ID);
				stmt.setString(2,(String)SSC_ROLL.get(i) );
				stmt.setString(3,(String)SSC_BOARD.get(i) );
				stmt.setString(4,(String)SSC_PASSING_YEAR.get(i) );
				stmt.setString(5,(String)SSC_REG.get(i) );
				stmt.setString(6,(String)MOBILE_NO.get(i) );
				stmt.setString(7,"N" );
				stmt.addBatch();
				}
			}
			//statement.executeUpdate();
			stmt.executeBatch();
			
			//conn.setAutoCommit(true);
			conn.commit();
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
	
	public boolean isDuplicate(String SSC_ROLL,String SSC_BOARD,String SSC_YEAR)
	{
		Connection conn = ConnectionManager.getReadConnection();		
		String sql ="SELECT * from XLS_UPLOAD where SSC_ROLL='"+SSC_ROLL+"' and SSC_BOARD='"+SSC_BOARD+"' and SSC_PASSING_YEAR='"+SSC_YEAR+"'";
		
		boolean isDuplicateID=false;
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while(r.next())
			{
				isDuplicateID=true;
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
		
		
		return isDuplicateID;
	}
	public boolean isCancelled(String app_id)
	{
		Connection conn = ConnectionManager.getReadConnection();		
		String sql ="SELECT * from XLS_UPLOAD where APPLICANT_ID='"+app_id+"'";
		
		boolean isCancel = false;
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while(r.next())
			{
				isCancel=true;
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
		
		
		return isCancel;
	}
	public String getUploadID()
	{
		Connection conn = ConnectionManager.getReadConnection();		
		String sql ="SELECT (max(UPLOAD_ID)+1) UPLOAD_ID from XLS_UPLOAD ";
		
		String UPLOAD_ID="";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while(r.next())
			{
				UPLOAD_ID=r.getString("UPLOAD_ID");
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
		
		
		return UPLOAD_ID;
		
	}
	
	
	public String getSCode(String roll, String reg)
	{
		Connection conn = ConnectionManager.getReadConnection();		
		String sql ="SELECT scode from applicant_info where roll_no='"+roll+"'  and reg_no='"+reg+"'";
		
		String UPLOAD_ID="";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while(r.next())
			{
				UPLOAD_ID=r.getString("scode");
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
		
		
		return UPLOAD_ID;
		
	}
	public String UpdateDataID(String UPLOAD_ID,String uploaduser)
	{
		Connection conn = ConnectionManager.getWriteConnection();	
		String sql ="Update XLS_UPLOAD_test set UPLOAD_ID="+UPLOAD_ID +" Where UPLOADED_BY='"+uploaduser+"'";
		
		PreparedStatement stmt = null;

		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.executeQuery();
			
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
		
		
		return UPLOAD_ID;
		
	}
	
	public String TransferDataByID(String UPLOAD_ID)
	{
		Connection conn = ConnectionManager.getWriteConnection();	
		String sql ="insert into XLS_UPLOAD(UPLOAD_ID, SSC_ROLL, SSC_BOARD, "+
                    " SSC_PASSING_YEAR, SSC_REG, UPLOADED_ON, SMS_SEND, STATUS, APPLICANT_ID, "+
                    " MOBILE_NO) (SELECT UPLOAD_ID, SSC_ROLL, SSC_BOARD, "+
                    " SSC_PASSING_YEAR, SSC_REG, UPLOADED_ON, "+
                    " SMS_SEND, STATUS, APPLICANT_ID, MOBILE_NO "+
                    " FROM XLS_UPLOAD_TEST WHERE UPLOAD_ID="+UPLOAD_ID+")";
		
		PreparedStatement stmt = null;
		
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.executeQuery();
			
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
		
		
		return UPLOAD_ID;
		
	}
	
	public void deletePreviousData(String USER_ID)
	{
		Connection conn = ConnectionManager.getWriteConnection();	
		String sql ="DELETE  from XLS_UPLOAD_test where UPLOADED_BY='"+USER_ID+"' ";
		
		PreparedStatement stmt = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.executeQuery();
			
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
		
		
		return;
		
	}
	

}
