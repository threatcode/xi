package edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.dto.UserDTO;
import edu.dto.board.BoardDTO;
import edu.dto.college.CollegeDTO;
import edu.dto.college.ListOfStudentOfCollegeDTO;
import edu.dto.interBoard.InterBoardDTO;
import edu.utils.connection.ConnectionManager;

public class UserDAO {
	
	
	
	public String MyEnc(String str)
	{
		String answer = "";
		int len = str.length(), i;
		int p = 11, q = 23;
		for (i = 0; i < len; i++)
		{
			int val = out(p, q, str.charAt(i));
			answer += val + "-";
		}
		return answer;
	}
	int out(int p, int q, int M)
	{
		int N = p * q;
		int phi = (p - 1) * (q - 1);
		int e = rel_prime(phi);
		int c = encrypt(N, e, M);
		return c;
		// int d = calculate_d(phi, e);
		// decrypt(c,d,N);
	}
	int rel_prime(int phi)
	{
		int rel = 5;

		while (gcd(phi, rel) != 1)
			rel++;
		return rel;
	}
	int gcd(int a, int b)
	{
		int r;
		while (b > 0)
		{
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
	int encrypt(int N, int e, int M)
	{
		int r, i = 0, prod = 1, rem_mod = 0;
		while (e > 0)
		{
			r = e % 2;
			if (i++ == 0)
				rem_mod = M % N;
			else
				rem_mod = power(rem_mod, 2) % N;
			if (r == 1)
			{
				prod *= rem_mod;
				prod = prod % N;
			}
			e = e / 2;
		}
		return prod;
	}	
	
	int power(int a, int b)
	{
		int temp = 1, i;
		for (i = 1; i <= b; i++)
			temp *= a;
		return temp;
	}
	
	
	

	public UserDTO validateLogin(String userId,String password)
	{	
		//String encrypted = MyEnc(password);
		UserDTO user=null;		
		Connection conn = ConnectionManager.getReadConnection();
		String sql= " Select * from MST_USER Where USER_ID=? AND ENCRYPTED=? and IS_ACTIVE='Y'";

		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, userId);
			    stmt.setString(2, password);
				r = stmt.executeQuery();
				if (r.next())
				{

					user=new UserDTO();
					user.setUserid(r.getString("USER_ID"));
					user.setPassword(r.getString("ENCRYPTED"));
					user.setRoleid(r.getString("USER_ROLE"));
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return user;
	}
	
	public UserDTO validateApplicantLogin(String ssc_roll,String ssc_board,String ssc_year,String ssc_reg,String mobile,String scode)
	{
		UserDTO user=null;		
		Connection conn = ConnectionManager.getReadConnection();
		String sql= " SELECT  ROLL_NO, REG_NO, BOARD_ID, GROUP_ID, PASSING_YEAR, STUDENT_NAME, "+
                    " FATHER_NAME, MOTHER_NAME, BIRTH_DATE,   GENDER, GPA, GPA_EXC4TH, SSC_EIIN, BOARD_NAME_ENG, GROUP_NAME, "+
                    " TT_TRANS_NUMBER, APPLIEDWEB, APPLICATION_ID,   CONTACT_NO, INSERTED, SCODE,  APPLIEDSMS, MPOS, NO_OF_UPDATE "+
                    " FROM BOARD_DATA_SSC "+
                    " WhERE ROLL_NO=? AND BOARD_ID=? AND PASSING_YEAR=? AND REG_NO=? AND CONTACT_NO=? AND SCODE=?" ;

		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, ssc_roll);
			    stmt.setString(2, ssc_board);
			    stmt.setString(3, ssc_year);
			    stmt.setString(4, ssc_reg);
			    stmt.setString(5, mobile);
			    stmt.setString(6, scode);
				r = stmt.executeQuery();
				if (r.next())
				{

					user=new UserDTO();
					user.setUserid(r.getString("APPLICATION_ID"));
					//user.setPassword(r.getString("USER_PASSWORD"));
					user.setRoleid("applicant");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return user;
	}
	public BoardDTO getBoardUser(String userId)
	{	
		BoardDTO user=null;		
		Connection conn = ConnectionManager.getReadConnection();
		String sql= "Select * from MST_USER_BOARD Where USER_ID = ?";

		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, userId);
			    r = stmt.executeQuery();
				if (r.next())
				{

					user=new BoardDTO();
					//user.setBoardUserId(r.getString("USER_ID"));
					user.setBoardUserId(userId);
					user.setBoardId(r.getString("BOARD_ID"));
					user.setBoardName(r.getString("BOARD_NAME"));
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return user;
	}
	
	public CollegeDTO getCollegeUser(String userId)
	{	
		CollegeDTO user=null;		
		Connection conn = ConnectionManager.getReadConnection();
		String sql= "Select coll.*, BOARD.BOARD_NAME BOARD_NAME_ENG from MST_COLLEGE coll, MST_EDU_BOARD board Where coll.board_id = board.board_id and coll.EIIN=?";

		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, userId);
			    r = stmt.executeQuery();
				if (r.next())
				{

					user=new CollegeDTO();
					user.setEiin(r.getString("EIIN"));
					user.setCollege_name(r.getString("COLLEGE_NAME"));
					user.setDist_id(r.getString("DIST_ID"));
					user.setBoard_id(r.getString("BOARD_ID"));
					user.setBoard_name(r.getString("BOARD_NAME_ENG"));
					user.setHelper_board_id(r.getString("HELPER_BOARD_ID"));
					user.setCollegeMobile(r.getString("MOBILE_NO")==null?"Not Available":r.getString("MOBILE_NO"));
					user.setCollegeEmail(r.getString("EMAIL")==null?"":r.getString("EMAIL"));
					user.setMobileVerifiedYN(r.getString("ISVALID_MOBILE_YN"));
					user.setSqEligible(r.getString("IS_SQ_ELIGIBLE"));
					user.setRecEligible(r.getString("IS_REC"));
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return user;
	}
	public InterBoardDTO getAdminUser(String userId)
	{	
		InterBoardDTO user=null;		
		Connection conn = ConnectionManager.getReadConnection();
		//String sql= "Select * from MST_COLLEGE Where EIIN=?";
		String sql= " Select * from MST_USER Where USER_ID=?";

		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, userId);
			    r = stmt.executeQuery();
				if (r.next())
				{

					user=new InterBoardDTO();
					user.setEiin(r.getString("USER_ID"));
					user.setCollege_name("Inter Board Admin");
					user.setDist_id("30");
					user.setBoard_id("10");
					user.setHelper_board_id("1");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return user;
	}
	
	/**
	 * @author nasir
	 * @param userId
	 * @return
	 */

	public UserDTO getAdminUserIict(String userId)
	{	
		UserDTO user=null;		
		Connection conn = ConnectionManager.getReadConnection();
		String sql= " Select * from MST_USER Where USER_ID=?";

		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, userId);
//			    stmt.setString(2, password);
				r = stmt.executeQuery();
				if (r.next())
				{

					user=new UserDTO();
					user.setUserid(r.getString("USER_ID"));
					user.setPassword(r.getString("USER_PASSWORD"));
					user.setRoleid(r.getString("USER_ROLE"));
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return user;
		
		
	}
	public UserDTO getApplicantInformation(String userId)
	{	
		UserDTO user=null;		
		Connection conn = ConnectionManager.getReadConnection();
		String sql= "SELECT U.USER_PASSWORD, D.STUDENT_NAME, A.MOBILE_NUMBER" +
				"  FROM MST_USER u, APPLICATION_INFO a, BOARD_DATA_SSC d" +
				" WHERE     USER_ID = ?" +
				"       AND U.USER_ID = A.APPLICATION_ID" +
				"       AND A.SSC_ROLL_NO = D.ROLL_NO" +
				"       AND A.SSC_BOARD_ID = D.BOARD_ID" +
				"       AND A.SSC_PASSING_YEAR = D.PASSING_YEAR";

		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, userId);
				r = stmt.executeQuery();
				if (r.next())
				{
					user=new UserDTO();
					user.setMobile(r.getString("MOBILE_NUMBER"));
					user.setPassword(r.getString("USER_PASSWORD"));
					user.setName(r.getString("STUDENT_NAME"));
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return user;
	}
	public UserDTO getApplicantContactNo(String appId)
	{	
		UserDTO tmp = new UserDTO();
		Connection conn = ConnectionManager.getReadConnection();
		String sql= "SELECT roll_no,CONTACT_NO  FROM APPLICANT_INFO WHERE APPLICANT_ID = ?";

		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, appId);
				r = stmt.executeQuery();
				if (r.next())
				{
					tmp.setSsc_roll(r.getString("roll_no"));
					tmp.setMobile(r.getString("CONTACT_NO"));
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		
		return tmp;
	}
	
	
	public List<UserDTO> getUserList(String roll){
		UserDTO listDTO = null;
		List<UserDTO> userList = null;
		
		Connection conn = ConnectionManager.getConnectionStatic1();
		

		//String	sql="select USER_ID,USER_PASSWORD from mst_user where ENCRYPTED is null and USER_ROLE=?";
		String	sql="select USER_ID,USER_PASSWORD from mst_user where  USER_ROLE=? and ENCRYPTED is null";
		

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, roll);
			r = stmt.executeQuery();
			
			userList = new ArrayList<UserDTO>();
			while(r.next())
			{
				listDTO = new UserDTO();
				listDTO.setUserid(r.getString("USER_ID"));
				listDTO.setPassword(r.getString("USER_PASSWORD"));
				userList.add(listDTO);
				

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
		
		return userList;
	}
	
	
	
	static PreparedStatement stmtUpdate = null;
	static String sqlUpdate = "update MST_USER set ENCRYPTED = ? where  USER_ID =?";
	public static synchronized int updateUser(String userId,String encrypted)
    {
		int tmp = 0;
        try {
            
            if(stmtUpdate==null)
            	stmtUpdate = ConnectionManager.getConnectionStatic1().prepareStatement(sqlUpdate);
            int parameterIndex = 1;           
            stmtUpdate.setString(parameterIndex++, encrypted);	            
            stmtUpdate.setString(parameterIndex++, userId);

            tmp = stmtUpdate.executeUpdate();
            stmtUpdate.clearParameters();
            
          } catch (SQLException e) {
            System.out.println(e.toString());
            stmtUpdate = null;
            ConnectionManager.closeStatic1();
        }
		
		return tmp;
    }
	
	
	
	
	
}
