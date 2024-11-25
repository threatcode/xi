package edu.dao.college;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import oracle.jdbc.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import edu.utils.connection.ConnectionManager;

import edu.action.board.SaveCollegeSVG;
import edu.dto.college.CollegeDTO;
import edu.dto.college.ListOfStudentOfCollegeDTO;



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
                "       B.BIRTH_DATE\n" +
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
	
	
	
	public Map<Integer, String> getGroups(int boardId)
	{
		
		int hbi=0;
		if(boardId==19)
			hbi=3;
		else if(boardId==18)
			hbi=2;
		else
			hbi=1;
		
		String sql="select GROUP_ID, GROUP_NAME  from MST_GROUP where HELPER_BOARD_ID="+hbi+" order by GROUP_ID";
		Connection conn = ConnectionManager.getReadConnection();
		Statement stmt = null;
		ResultSet r = null;
		Map<Integer,String> mapgroup = new TreeMap<Integer,String>();
		
		try {
		stmt = conn.createStatement();
		r = stmt.executeQuery(sql);

		while (r.next()) {
			mapgroup.put(r.getInt("GROUP_ID"), r.getString("GROUP_NAME"));		
			
		}
	
		} catch (Exception e){e.printStackTrace();}
			finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
			
		
		
		return mapgroup;
	}
	
	
	
	public Map<String, String> getGender()
	{
		Map<String,String> mapgender = new TreeMap<String,String>();
		
		mapgender.put("F", "Female");
		mapgender.put("M", "Male");
		mapgender.put("C", "Coeducation");
		
		return mapgender;
		
	}
	
	
	
	
	public Map<Integer, String> getVersion()
	{
		
		Map<Integer,String> mapversion = new TreeMap<Integer,String>(); 
		
	
		mapversion.put(1, "Bangla");
		mapversion.put(2, "English");
		
		
		return mapversion;
	}
	
	
	public Map<Integer,String> getShift(String eiin) {
		

		Map<Integer,String> mapshift = new TreeMap<Integer,String>(); 
		
			
		
		if(eiin.equalsIgnoreCase("132107"))
		{
			mapshift.put(1, "Morning(Female)");	
			mapshift.put(11, "Morning(Male)");	
			mapshift.put(2, "Day(Female)");	
			mapshift.put(12, "Day(Female)");	
		}
		else if(eiin.equalsIgnoreCase("100875"))
		{
			mapshift.put(2, "Day(Female)");	
			mapshift.put(12, "Day(Male)");
		}
		else
		{
			mapshift.put(2, "Day");	
			mapshift.put(1, "Morning");	
			mapshift.put(3, "Evening");	
		}
		
		return mapshift;
	}
	
	
	
	public  CollegeDTO getCollegeDTO(String eiinCode)
	{
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "SELECT  " +
				" EIIN, COLLEGE_NAME, MOBILE_NO,  " +
				" DIST_ID, THANA_ID,  " +
				"   IS_METRO, IS_ZILL_SADAR, nvl(IS_GOVT,'N') IS_GOVT,  " +
				"   BOARD_ID, IS_ACTIVE, IS_SQ_ELIGIBLE, IS_REC  " +				
				" FROM MST_COLLEGE where EIIN=? " ;
		PreparedStatement stmt = null;
		ResultSet r = null;
		//ArrayList<CollegeDTO> collegeList=new ArrayList<CollegeDTO>();
		CollegeDTO college=null;
		
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, eiinCode);
				r = stmt.executeQuery();				
				while (r.next())
				{
					college=new CollegeDTO();
					college.setEiin(r.getString("EIIN"));
					college.setCollege_name(r.getString("COLLEGE_NAME").replaceAll("'", ""));					
					college.setDist_id(r.getString("DIST_ID"));
					college.setCollegeMobile(r.getString("MOBILE_NO"));
					college.setThana_id(r.getString("THANA_ID"));
					college.setMetro(r.getString("IS_METRO"));
					college.setZilasader(r.getString("IS_ZILL_SADAR"));
					college.setGovt(r.getString("IS_GOVT"));
					college.setBoard_id(r.getString("BOARD_ID"));
					college.setActive(r.getString("IS_ACTIVE"));
					college.setSqEligible(r.getString("IS_SQ_ELIGIBLE"));
					college.setIsRecive(r.getString("IS_REC"));
					
				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return college;
	}
	
	
	//public int saveCollegeSVG(String  eiin,String collegeName,String districtID,String thanaID,String metro,String zilasader,String govt,String active,String sqelig,String mobile,
			//	String shiftid[],String verid[],String groupid[],String gender[],String seat[],String gpa[],String ownGpa[],String spQuota[],String spGpa[],String reserve[],
				//String Iuser_Id,IpAddressDTO ipAddress, String boardid, String helpBoard)
		
		

	/**
	 * @author nasir
	 * @param eiinCode
	 * @return
	 */
	public List<ListOfStudentOfCollegeDTO> getSVGCollegeForHtmlView(String eiinCode){
		ListOfStudentOfCollegeDTO svgDTO = null;
		List<ListOfStudentOfCollegeDTO> svgList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "SELECT A.SHIFT_ID, B.SHIFT_NAME, A.VERSION_ID, C.VERSION_NAME,A.GROUP_ID,D.GROUP_NAME, " +
				" DECODE (GENDER,  'C', 'Co-Education',  'M', 'Male',  'F', 'Female') GENDER,A.TOTAL_SEAT,A.MINIMUM_GPA,A.OWN_GPA,A.SQ_PCT,A.SQ_MIN_GPA,A.RESERVE " +
				"    FROM MST_COLLEGE_GROUPS a, " +
				"         MST_SHIFT b, " +
				"         MST_VERSION c, " +
				"         MST_GROUP d " +
				"   WHERE A.SHIFT_ID=B.SHIFT_ID " +
				"   and A.VERSION_ID=C.VERSION_ID " +
				"   and A.GROUP_ID=D.GROUP_ID " +
				"   and A.EIIN=?  " +
				"   order by A.SHIFT_ID, A.VERSION_ID,A.GROUP_ID  "; 


		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
		//	 BoardId="15";
			
			stmt.setString(1, eiinCode);			
			r = stmt.executeQuery();
			
			svgList= new ArrayList<ListOfStudentOfCollegeDTO>();
			while(r.next())
			{
				svgDTO = new ListOfStudentOfCollegeDTO();
			
				svgDTO.setShiftID(r.getString("SHIFT_ID"));
				svgDTO.setShiftName(r.getString("SHIFT_NAME"));
				svgDTO.setVersionID(r.getString("VERSION_ID"));
				svgDTO.setVersionName(r.getString("VERSION_NAME"));
				svgDTO.setGroupId(r.getString("GROUP_ID"));
				svgDTO.setGroupName(r.getString("GROUP_NAME"));
				svgDTO.setGender(r.getString("GENDER"));
				svgDTO.setTotalSeat(r.getInt("TOTAL_SEAT"));
				svgDTO.setGpa(r.getFloat("MINIMUM_GPA"));
				svgDTO.setOwnGpa(r.getFloat("OWN_GPA"));
				svgDTO.setSpQuota(r.getInt("SQ_PCT"));
				svgDTO.setSpGpa(r.getFloat("SQ_MIN_GPA"));
				svgDTO.setReserve(r.getInt("RESERVE"));
				
				
				svgList.add(svgDTO);
				

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
		
		return svgList;
	}
	
	/**
	 * @author nasir
	 * @param eiinCode
	 * @return
	 */
	public  CollegeDTO getCollegeDTOforHtmlSvg(String eiinCode)
	{
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "SELECT mc.EIIN EIIN, " +
				"       mc.COLLEGE_NAME COLLEGE_NAME, " +
				"       md.DIST_NAME DIST_NAME, " +
				"       mt.THANA_NAME THANA_NAME " +
				"  FROM MST_COLLEGE mc, MST_DISTRICT md, MST_THANA mt " +
				" WHERE     mc.EIIN = ? " +
				"       AND md.DIST_ID = mc.DIST_ID " +
				"       AND MT.THANA_ID = MC.THANA_ID ";
		PreparedStatement stmt = null;
		ResultSet r = null;
		//ArrayList<CollegeDTO> collegeList=new ArrayList<CollegeDTO>();
		CollegeDTO college=null;
		
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, eiinCode);
				r = stmt.executeQuery();				
				while (r.next())
				{
					college=new CollegeDTO();
					college.setEiin(r.getString("EIIN"));
					college.setCollege_name(r.getString("COLLEGE_NAME").replaceAll("'", ""));
					college.setDist_name(r.getString("DIST_NAME"));
					//college.setDist_id(r.getString("DIST_ID"));
					//college.setCollegeMobile(r.getString("MOBILE_NO"));
					//college.setThana_id(r.getString("THANA_ID"));
					college.setThana_name(r.getString("THANA_NAME"));
//					college.setMetro(r.getString("IS_METRO"));
//					college.setZilasader(r.getString("IS_ZILL_SADAR"));
//					college.setGovt(r.getString("IS_GOVT"));
//					college.setBoard_id(r.getString("BOARD_ID"));
//					college.setActive(r.getString("IS_ACTIVE"));
//					college.setSqEligible(r.getString("IS_SQ_ELIGIBLE"));
					
				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return college;
	}
	
	
	
	public Boolean isValidDistThana(String districtID,String thanaID)
	{
		Connection conn = ConnectionManager.getReadConnection();
		
		  String sql=" select THANA_NAME from MST_THANA where THANA_ID=? and DIST_ID=? ";


				PreparedStatement stmt = null;
				ResultSet r = null;
				try
				{
					stmt = conn.prepareStatement(sql);
					
					stmt.setString(1, thanaID);
					stmt.setString(2, districtID);
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

	
	
	
	

		public int saveSVGCollege(SaveCollegeSVG coll)
		{
			
			Connection conn = ConnectionManager.getProcConnection();
			OracleCallableStatement stmt = null;
			int res=0;
			String tnp="";
			try {
				
				ArrayDescriptor arrString = new ArrayDescriptor("VARCHARARRAY", conn);
				
				ARRAY ashift=new ARRAY(arrString,conn,coll.getShiftid());
				ARRAY aversion=new ARRAY(arrString,conn,coll.getVerid());
				ARRAY agroup=new ARRAY(arrString,conn,coll.getGroupid());
				ARRAY agender=new ARRAY(arrString,conn,coll.getGender());
				ARRAY aseat=new ARRAY(arrString,conn,coll.getSeat());
				ARRAY agpa=new ARRAY(arrString,conn,coll.getGpa());			
				ARRAY aowngpa=new ARRAY(arrString,conn,coll.getOwnGpa());
				ARRAY asq=new ARRAY(arrString,conn,coll.getSpQuota());
				ARRAY asqgpa=new ARRAY(arrString,conn,coll.getSpGpa());
				ARRAY areserve=new ARRAY(arrString,conn,coll.getReserve());
			
				
				 stmt = (OracleCallableStatement) conn.prepareCall("{ call UPDATECOLLEGESVG_B(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");
				
				
				 
				 stmt.setString(1, coll.getEiin());
				 stmt.setString(2, coll.getCollegeName());
				 stmt.setString(3, coll.getDistrictID());
				 stmt.setString(4, coll.getThanaID());
				 stmt.setString(5, coll.getBoardId());
				 stmt.setString(6, coll.getHelpBoard());
				 stmt.setString(7, coll.getMetro());
				 stmt.setString(8, coll.getZilasader());
				 stmt.setString(9, coll.getGovt());
				 stmt.setString(10, coll.getActive());
				 stmt.setString(11, coll.getSqelig());
				 stmt.setString(12, coll.getMobile());
				 

				 stmt.setARRAY(13, ashift); 
				 stmt.setARRAY(14, aversion); 
				 stmt.setARRAY(15, agroup); 
				 stmt.setARRAY(16, agender); 
				 stmt.setARRAY(17, aseat); 
				 stmt.setARRAY(18, agpa); 
				 stmt.setARRAY(19, aowngpa); 
				 stmt.setARRAY(20, asq); 
				 stmt.setARRAY(21, asqgpa); 
				 stmt.setARRAY(22, areserve); 
				 
				 
				 stmt.setString(23, coll.getUserId());
				 stmt.setString(24, coll.getIpAddressDTO().getxForward());
				 stmt.setString(25, coll.getIpAddressDTO().getVia());
				 stmt.setString(26, coll.getIpAddressDTO().getRemoteAddress());
				 
				 stmt.setString(27, coll.getReceive());
				 
				 
		            stmt.registerOutParameter(28, java.sql.Types.INTEGER);
		            stmt.registerOutParameter(29, java.sql.Types.VARCHAR);
		            
		            stmt.executeUpdate();
		            
		            res=stmt.getInt(28);
		            
		            tnp=stmt.getString(29);
				 
				 
				 
				
				 
				 
				
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
			
			
			
			
			
		
			return res;
		}
		
	
	
	public List<ListOfStudentOfCollegeDTO> getSVGCollege(String eiinCode){
		ListOfStudentOfCollegeDTO svgDTO = null;
		List<ListOfStudentOfCollegeDTO> svgList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "SELECT A.SHIFT_ID, B.SHIFT_NAME, A.VERSION_ID, C.VERSION_NAME,A.GROUP_ID,D.GROUP_NAME, " +
				" A.GENDER,A.TOTAL_SEAT,A.MINIMUM_GPA,A.OWN_GPA,A.SQ_PCT,A.SQ_MIN_GPA,A.RESERVE " +
				"    FROM MST_COLLEGE_GROUPS a, " +
				"         MST_SHIFT b, " +
				"         MST_VERSION c, " +
				"         MST_GROUP d " +
				"   WHERE A.SHIFT_ID=B.SHIFT_ID " +
				"   and A.VERSION_ID=C.VERSION_ID " +
				"   and A.GROUP_ID=D.GROUP_ID " +
				"   and A.EIIN=?  " +
				"   order by A.SHIFT_ID, A.VERSION_ID,A.GROUP_ID  "; 


		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
		//	 BoardId="15";
			
			stmt.setString(1, eiinCode);			
			r = stmt.executeQuery();
			
			svgList= new ArrayList<ListOfStudentOfCollegeDTO>();
			while(r.next())
			{
				svgDTO = new ListOfStudentOfCollegeDTO();
			
				svgDTO.setShiftID(r.getString("SHIFT_ID"));
				svgDTO.setShiftName(r.getString("SHIFT_NAME"));
				svgDTO.setVersionID(r.getString("VERSION_ID"));
				svgDTO.setVersionName(r.getString("VERSION_NAME"));
				svgDTO.setGroupId(r.getString("GROUP_ID"));
				svgDTO.setGroupName(r.getString("GROUP_NAME"));
				svgDTO.setGender(r.getString("GENDER"));
				svgDTO.setTotalSeat(r.getInt("TOTAL_SEAT"));
				svgDTO.setGpa(r.getFloat("MINIMUM_GPA"));
				svgDTO.setOwnGpa(r.getFloat("OWN_GPA"));
				svgDTO.setSpQuota(r.getInt("SQ_PCT"));
				svgDTO.setSpGpa(r.getFloat("SQ_MIN_GPA"));
				svgDTO.setReserve(r.getInt("RESERVE"));
				
				
				svgList.add(svgDTO);
				

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
		
		return svgList;
	}
	
	
	public List<ListOfStudentOfCollegeDTO> getlistOfShiftVersionGroupApplicationNumberOfCollege(String eiinCode,String BoardId){
		ListOfStudentOfCollegeDTO shiftVersionGroupApplicationNumberOfCollegeDTO = null;
		List<ListOfStudentOfCollegeDTO> shiftVersionGroupApplicationNumberOfCollegeList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "  SELECT A.SHIFT_ID," +
				"         C.SHIFT_NAME," +
				"         A.VERSION_ID," +
				"         D.VERSION_NAME," +
				"         A.GROUP_ID," +
				"         E.GROUP_NAME," +
				"         COUNT (DISTINCT A.APPLICANT_ID) APPLICATION_NUMBER" +
				"    FROM APPLICATION_COLLEGES a," +
				"         MST_COLLEGE b," +
				"         MST_SHIFT c," +
				"         MST_VERSION d," +
				"         MST_GROUP e" +
				"   WHERE     A.EIIN = ?" +
				"         AND B.BOARD_ID = ?" +
				"         AND A.EIIN = B.EIIN" +
				"         AND A.SHIFT_ID = C.SHIFT_ID" +
				"         AND A.VERSION_ID = D.VERSION_ID" +
				"         AND A.GROUP_ID = E.GROUP_ID" +
				" GROUP BY A.SHIFT_ID," +
				"         C.SHIFT_NAME," +
				"         A.VERSION_ID," +
				"         D.VERSION_NAME," +
				"         A.GROUP_ID," +
				"         E.GROUP_NAME" +
				" ORDER BY A.SHIFT_ID, A.VERSION_ID, A.GROUP_ID " ;

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
		//	 BoardId="15";
			
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
	
	public List<ListOfStudentOfCollegeDTO> getlistOfStudentOfSpecialQuota(String eiinCode){
		ListOfStudentOfCollegeDTO listOfStudentOfCollegeDTO = null;
		List<ListOfStudentOfCollegeDTO> specialQuotaStudentList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql=""; 
		
/*		String sql="  SELECT a.APPLICATION_ID,\n" +
				//  "         a.MOBILE_NUMBER,\n" +
		          "         b.STUDENT_NAME,\n" +
		          "         b.FATHER_NAME,\n" +
		          "         a.SSC_ROLL_NO,\n" +
		          "         c.BOARD_NAME_ENG,\n" +
		          "         b.PASSING_YEAR,\n" +
		          "         d.QUOTA_SPECIAL_GRANT,\n" +	                
		          "         d.EIIN\n" + 
		          "    FROM MST_EDU_BOARD c,\n" +
		          "         APPLICATION_INFO a,\n" +
		          "         BOARD_DATA_SSC b,\n" +
		          "         (SELECT DISTINCT APPLICATION_ID,EIIN, QUOTA_SPECIAL_GRANT\n" +
		          "            FROM APPLICATION_COLLEGES\n" +
		          "           WHERE QUOTA_SPECIAL = 'Y' AND EIIN =?) d\n" +
		          "   WHERE     a.SSC_ROLL_NO = b.ROLL_NO\n" +
		          "         AND A.SSC_BOARD_ID = b.BOARD_ID\n" +
		          "         AND a.SSC_PASSING_YEAR = b.PASSING_YEAR\n" +
		          "         AND A.SSC_BOARD_ID = C.BOARD_ID\n" +
		          "         AND a.APPLICATION_ID = d.APPLICATION_ID\n" +
		          "UNION\n" +
		          "SELECT distinct a.APPLICATION_ID,\n" +
		          //  "       a.MOBILE_NUMBER,\n" +
	                "       b.STUDENT_NAME,\n" +
	                "       b.FATHER_NAME,\n" +
	                "       a.SSC_ROLL_NO,\n" +
	                "       c.BOARD_NAME_ENG,\n" +
	                "       b.PASSING_YEAR,\n" +
	                "       d.QUOTA_SPECIAL_GRANT,\n" +
	                "       d.EIIN\n" +
	                "  FROM MST_EDU_BOARD c,\n" +
	                "       APPLICATION_INFO a,\n" +
	                "       BOARD_DATA_SSC b,\n" +
	                "       APPLICATION_COLLEGES d\n" +
	                " WHERE     d.EIIN = ?\n" +
	                "       and d.QUOTA_SPECIAL_GRANT='Y' \n" +
	                "       AND a.APPLICATION_ID = d.APPLICATION_ID\n" +
	                "       AND A.SSC_BOARD_ID = C.BOARD_ID\n" +
	                "       AND A.SSC_BOARD_ID = b.BOARD_ID\n" +
	                "       AND a.SSC_PASSING_YEAR = b.PASSING_YEAR\n" +
	                "       AND a.SSC_ROLL_NO = b.ROLL_NO";*/
		
  /*String sql="  SELECT a.APPLICATION_ID,\n" +
          "         b.STUDENT_NAME,\n" +
          "         b.FATHER_NAME,\n" +
          "         a.SSC_ROLL_NO,\n" +
          "         c.BOARD_NAME_ENG,\n" +
          "         b.PASSING_YEAR,\n" +
          "         B.BIRTH_DATE,\n" +
          "         d.QUOTA_SPECIAL_GRANT\n" +
          "    FROM MST_EDU_BOARD c,\n" +
          "         APPLICATION_INFO a,\n" +
          "         BOARD_DATA_SSC b,\n" +
          "         (SELECT DISTINCT APPLICATION_ID, QUOTA_SPECIAL_GRANT\n" +
          "            FROM APPLICATION_COLLEGES\n" +
          "           WHERE SPECIAL_QUOTA = 'Y' AND EIIN =?) d\n" +
          "   WHERE     a.SSC_ROLL_NO = b.ROLL_NO\n" +
          "         AND A.SSC_BOARD_ID = b.BOARD_ID\n" +
          "         AND a.SSC_PASSING_YEAR = b.PASSING_YEAR\n" +
          "         AND A.SSC_BOARD_ID = C.BOARD_ID\n" +
          "         AND a.APPLICATION_ID = d.APPLICATION_ID\n" +
          "ORDER BY d.QUOTA_SPECIAL_GRANT DESC";*/
		
		sql = "SELECT DISTINCT applicant_id APPLICATION_ID," +
				"                roll_no SSC_ROLL_NO," +
				"                pass_year SSC_PASSING_YEAR," +
				"                T2.STUDENT_NAME NAME," +
				"                T2.FATHER_NAME FATHER," +
				"                BOARD_NAME," +
				"                QUOTA_SPECIAL_GRANT" +
				"  FROM application_colleges t1,board_data_ssc t2" +
				"  WHERE   T2.BOARD_ID||T2.PASS_YEAR||T2.ROLL_NO=T1.APPLICANT_ID and ( QUOTA_SPECIAL = 'Y' or QUOTA_SPECIAL_GRANT = 'Y') " +
				"       AND EIIN = ?";


		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, eiinCode);
//			stmt.setString(2, eiinCode);
			r = stmt.executeQuery();
			
			specialQuotaStudentList = new ArrayList<ListOfStudentOfCollegeDTO>();
			while(r.next())
			{
				listOfStudentOfCollegeDTO = new ListOfStudentOfCollegeDTO();
				listOfStudentOfCollegeDTO.setApplicationID(r.getString("APPLICATION_ID"));
				//listOfStudentOfCollegeDTO.setApplicantMobileNumber(r.getString("MOBILE_NUMBER"));
				listOfStudentOfCollegeDTO.setApplicantName(r.getString("NAME"));
				listOfStudentOfCollegeDTO.setFatherName(r.getString("FATHER"));
				listOfStudentOfCollegeDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				listOfStudentOfCollegeDTO.setBoardName(r.getString("BOARD_NAME"));
				listOfStudentOfCollegeDTO.setSscPassingYear(r.getString("SSC_PASSING_YEAR"));
			//	listOfStudentOfCollegeDTO.setDateOfBirth(r.getDate("BIRTH_DATE"));
				listOfStudentOfCollegeDTO.setSpecialQuotaGrant(r.getString("QUOTA_SPECIAL_GRANT"));
			//	listOfStudentOfCollegeDTO.setPaymentStatus(r.getString("PAYMENT_STATUS"));

				specialQuotaStudentList.add(listOfStudentOfCollegeDTO);
				

			}
			/*System.out.println("specialQuotaStudentList.size="+specialQuotaStudentList.size());*/
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
		
		return specialQuotaStudentList;
	}
	
	public List<ListOfStudentOfCollegeDTO> getlistOfStudentOfEducationQuota(String eiinCode){
		ListOfStudentOfCollegeDTO listOfStudentOfCollegeDTO = null;
		List<ListOfStudentOfCollegeDTO> educationQuotaStudentList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
	
		String sql="SELECT a.APPLICATION_ID,\n" +
				"         a.MOBILE_NUMBER,\n" +
                "       b.STUDENT_NAME,\n" +
                "       b.FATHER_NAME,\n" +
                "       a.SSC_ROLL_NO,\n" +
                "       c.BOARD_NAME_ENG,\n" +
                "       b.PASSING_YEAR,\n" +
                "       a.QUOTA_EQ,\n" +
                "       d.EQ_APPROVAL,\n" +
                "       d.EIIN\n" +
                "  FROM MST_EDU_BOARD c,\n" +
                "       APPLICATION_INFO a,\n" +
                "       BOARD_DATA_SSC b,\n" +
                "       (SELECT DISTINCT APPLICATION_ID, EIIN,EQ_APPROVAL\n" +
                "          FROM APPLICATION_COLLEGES\n" +
                "         WHERE EIIN = ?) d\n" +
                " WHERE     a.QUOTA_EQ = 'Y'\n" +
                "       AND a.APPLICATION_ID = d.APPLICATION_ID\n" +
                "       AND A.SSC_BOARD_ID = C.BOARD_ID\n" +
                "       AND A.SSC_BOARD_ID = b.BOARD_ID\n" +
                "       AND a.SSC_PASSING_YEAR = b.PASSING_YEAR\n" +
                "       AND a.SSC_ROLL_NO = b.ROLL_NO\n" +
                "UNION\n" +
                "SELECT distinct a.APPLICATION_ID,\n" +
                "         a.MOBILE_NUMBER,\n" +
                "       b.STUDENT_NAME,\n" +
                "       b.FATHER_NAME,\n" +
                "       a.SSC_ROLL_NO,\n" +
                "       c.BOARD_NAME_ENG,\n" +
                "       b.PASSING_YEAR,\n" +
                "       a.QUOTA_EQ,\n" +
                "       d.EQ_APPROVAL,\n" +
                "       d.EIIN\n" +
                "  FROM MST_EDU_BOARD c,\n" +
                "       APPLICATION_INFO a,\n" +
                "       BOARD_DATA_SSC b,\n" +
                "       APPLICATION_COLLEGES d\n" +
                " WHERE     d.EIIN = ?\n" +
                "       and d.EQ_APPROVAL='Y' \n" +
                "       AND a.APPLICATION_ID = d.APPLICATION_ID\n" +
                "       AND A.SSC_BOARD_ID = C.BOARD_ID\n" +
                "       AND A.SSC_BOARD_ID = b.BOARD_ID\n" +
                "       AND a.SSC_PASSING_YEAR = b.PASSING_YEAR\n" +
                "       AND a.SSC_ROLL_NO = b.ROLL_NO";
		
/*  String sql="SELECT a.APPLICATION_ID,\n" +
          "       b.STUDENT_NAME,\n" +
          "       b.FATHER_NAME,\n" +
          "       a.SSC_ROLL_NO,\n" +
          "       c.BOARD_NAME_ENG,\n" +
          "       b.PASSING_YEAR,\n" +
          "       a.QUOTA_EQ,\n" +
          "       a.EQ_APPROVAL,\n" +
          "       d.EIIN\n" +
          "  FROM MST_EDU_BOARD c,\n" +
          "       APPLICATION_INFO a,\n" +
          "       BOARD_DATA_SSC b,\n" +
          "       (SELECT DISTINCT APPLICATION_ID, EIIN\n" +
          "          FROM APPLICATION_COLLEGES\n" +
          "         WHERE EIIN =?) d\n" +
          " WHERE     a.QUOTA_EQ = 'Y'\n" +
          "       AND a.APPLICATION_ID = d.APPLICATION_ID\n" +
          "       AND A.SSC_BOARD_ID = C.BOARD_ID\n" +
          "       AND A.SSC_BOARD_ID = b.BOARD_ID\n" +
          "       AND a.SSC_PASSING_YEAR = b.PASSING_YEAR\n" +
          "       AND a.SSC_ROLL_NO = b.ROLL_NO";*/


		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, eiinCode);
			stmt.setString(2, eiinCode);
			r = stmt.executeQuery();
			
			educationQuotaStudentList = new ArrayList<ListOfStudentOfCollegeDTO>();
			while(r.next())
			{
				listOfStudentOfCollegeDTO = new ListOfStudentOfCollegeDTO();
				listOfStudentOfCollegeDTO.setApplicationID(r.getString("APPLICATION_ID"));
				listOfStudentOfCollegeDTO.setApplicantName(r.getString("STUDENT_NAME"));
				listOfStudentOfCollegeDTO.setApplicantMobileNumber(r.getString("MOBILE_NUMBER"));
				listOfStudentOfCollegeDTO.setFatherName(r.getString("FATHER_NAME"));
				listOfStudentOfCollegeDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				listOfStudentOfCollegeDTO.setBoardName(r.getString("BOARD_NAME_ENG"));
				listOfStudentOfCollegeDTO.setSscPassingYear(r.getString("PASSING_YEAR"));
				listOfStudentOfCollegeDTO.setEducationQuotaStatus(r.getString("QUOTA_EQ"));
				listOfStudentOfCollegeDTO.setEducationQuotaGrant(r.getString("EQ_APPROVAL"));
				listOfStudentOfCollegeDTO.setEiinCode(r.getString("EIIN"));
				

				educationQuotaStudentList.add(listOfStudentOfCollegeDTO);
				

			}
			/*System.out.println("specialQuotaStudentList.size="+specialQuotaStudentList.size());*/
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
		
		return educationQuotaStudentList;
	}
	
	
	public List<ListOfStudentOfCollegeDTO> getnewStudentOfSpecialQuota(String ssc_roll,String ssc_board,String ssc_year,String ssc_reg,String eiinCode){
		ListOfStudentOfCollegeDTO listOfStudentOfCollegeDTO = null;
		List<ListOfStudentOfCollegeDTO> newspecialQuotaStudentinfoList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
	
		String sql="  SELECT distinct a.applicant_id APPLICATION_ID," +
				"         b.STUDENT_NAME," +
				"         b.FATHER_NAME," +
				"         A.ROLL_NO SSC_ROLL_NO," +
				"         B.BOARD_NAME BOARD_NAME_ENG," +
				"         B.PASS_YEAR PASSING_YEAR," +
				"         d.EIIN," +
				"" +
				"         d.QUOTA_SPECIAL_GRANT" +
				"    FROM MST_EDU_BOARD c," +
				"         APPLICANT_INFO a," +
				"         APPLICATION_COLLEGES d," +
				"         BOARD_DATA_SSC b" +
				"   WHERE     a.ROLL_NO in (?)" +
				"         AND a.REG_NO =?" +
				"         AND a.BOARD_ID =?" +
				"         AND a.PASS_YEAR =?" +
				"         AND d.EIIN =?" +
				"         AND a.ROLL_NO = b.ROLL_NO" +
				"         AND A.BOARD_ID = b.BOARD_ID" +
				"         AND a.PASS_YEAR = b.PASS_YEAR" +
				"         AND A.BOARD_ID = C.BOARD_ID" +
				"         AND a.applicant_id = d.applicant_id";
		


		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, ssc_roll);
			stmt.setString(2, ssc_reg);
			stmt.setString(3, ssc_board);
			stmt.setString(4, ssc_year);
			stmt.setString(5, eiinCode);
			r = stmt.executeQuery();
			
			newspecialQuotaStudentinfoList = new ArrayList<ListOfStudentOfCollegeDTO>();
			while(r.next())
			{
				listOfStudentOfCollegeDTO = new ListOfStudentOfCollegeDTO();
				listOfStudentOfCollegeDTO.setApplicationID(r.getString("APPLICATION_ID"));
				listOfStudentOfCollegeDTO.setApplicantName(r.getString("STUDENT_NAME"));
				listOfStudentOfCollegeDTO.setFatherName(r.getString("FATHER_NAME"));
				listOfStudentOfCollegeDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				listOfStudentOfCollegeDTO.setBoardName(r.getString("BOARD_NAME_ENG"));
				listOfStudentOfCollegeDTO.setSscPassingYear(r.getString("PASSING_YEAR"));
				listOfStudentOfCollegeDTO.setEiinCode(r.getString("EIIN"));
/*				listOfStudentOfCollegeDTO.setShiftID(r.getString("SHIFT_ID"));
				listOfStudentOfCollegeDTO.setShiftName(r.getString("SHIFT_NAME"));
				listOfStudentOfCollegeDTO.setVersionID(r.getString("VERSION_ID"));
				listOfStudentOfCollegeDTO.setVersionName(r.getString("VERSION_NAME"));
				listOfStudentOfCollegeDTO.setGroupId(r.getString("GROUP_ID"));
				listOfStudentOfCollegeDTO.setGroupName(r.getString("GROUP_NAME"));
				listOfStudentOfCollegeDTO.setSpecialQuotaStatus(r.getString("SPECIAL_QUOTA"));*/
				listOfStudentOfCollegeDTO.setSpecialQuotaGrant(r.getString("QUOTA_SPECIAL_GRANT"));
			

				newspecialQuotaStudentinfoList.add(listOfStudentOfCollegeDTO);
				

			}
	//		System.out.println("newspecialQuotaStudentinfoList.size="+newspecialQuotaStudentinfoList.size());
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
		
		return newspecialQuotaStudentinfoList;
	}
	

	public List<ListOfStudentOfCollegeDTO> getnewStudentOfEducationQuota(String applicationID,String eiinCode){
		ListOfStudentOfCollegeDTO listOfStudentOfCollegeDTO = null;
		List<ListOfStudentOfCollegeDTO> newEducationQuotaStudentinfoList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
	
		String sql="SELECT DISTINCT a.APPLICATION_ID,\n" +
                "                b.STUDENT_NAME,\n" +
                "                b.FATHER_NAME,\n" +
                "                a.SSC_ROLL_NO,\n" +
                "                c.BOARD_NAME_ENG,\n" +
                "                b.PASSING_YEAR,\n" +
                "                a.QUOTA_EQ,\n" +
                "                d.EQ_APPROVAL,\n" +
                "                d.EIIN\n" +
                "  FROM MST_EDU_BOARD c,\n" +
                "       APPLICATION_INFO a,\n" +
                "       BOARD_DATA_SSC b,\n" +
                "       APPLICATION_COLLEGES d\n" +
                " WHERE     a.APPLICATION_ID =?\n" +
                "       AND d.EIIN =?\n" +
                "       AND a.APPLICATION_ID = d.APPLICATION_ID\n" +
                "       AND A.SSC_BOARD_ID = C.BOARD_ID\n" +
                "       AND A.SSC_BOARD_ID = b.BOARD_ID\n" +
                "       AND a.SSC_PASSING_YEAR = b.PASSING_YEAR\n" +
                "       AND a.SSC_ROLL_NO = b.ROLL_NO";
		

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, applicationID);
			stmt.setString(2, eiinCode);
			r = stmt.executeQuery();
			
			newEducationQuotaStudentinfoList = new ArrayList<ListOfStudentOfCollegeDTO>();
			while(r.next())
			{
				listOfStudentOfCollegeDTO = new ListOfStudentOfCollegeDTO();
				listOfStudentOfCollegeDTO.setApplicationID(r.getString("APPLICATION_ID"));
				listOfStudentOfCollegeDTO.setApplicantName(r.getString("STUDENT_NAME"));
				listOfStudentOfCollegeDTO.setFatherName(r.getString("FATHER_NAME"));
				listOfStudentOfCollegeDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				listOfStudentOfCollegeDTO.setBoardName(r.getString("BOARD_NAME_ENG"));
				listOfStudentOfCollegeDTO.setSscPassingYear(r.getString("PASSING_YEAR"));
				listOfStudentOfCollegeDTO.setEducationQuotaStatus(r.getString("QUOTA_EQ"));
				listOfStudentOfCollegeDTO.setEducationQuotaGrant(r.getString("EQ_APPROVAL"));
				listOfStudentOfCollegeDTO.setEiinCode(r.getString("EIIN"));

				newEducationQuotaStudentinfoList.add(listOfStudentOfCollegeDTO);
				

			}
	//		System.out.println("newEducationQuotaStudentinfoList.size="+newEducationQuotaStudentinfoList.size());
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
		
		return newEducationQuotaStudentinfoList;
	}
	

	
	public List<ListOfStudentOfCollegeDTO> getUploadedSQStdList(String applicationID,String eiinCode){
		ListOfStudentOfCollegeDTO listOfStudentOfCollegeDTO = null;
		List<ListOfStudentOfCollegeDTO> newspecialQuotaStudentinfoList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
	
		String sql=" SELECT distinct a.APPLICATION_ID,\n" +
                "         b.STUDENT_NAME,\n" +
                "         b.FATHER_NAME,\n" +
                "         a.SSC_ROLL_NO,\n" +
                "         c.BOARD_NAME_ENG,\n" +
                "         b.PASSING_YEAR,\n" +
                "         d.EIIN, \n" +
                "         d.QUOTA_SPECIAL_GRANT\n" +
                "    FROM MST_EDU_BOARD c,\n" +
                "         APPLICATION_INFO a,\n" +
                "         APPLICATION_COLLEGES d,\n" +
                "         BOARD_DATA_SSC b\n" +
                "   WHERE     a.APPLICATION_ID in ("+applicationID+")\n" +
                "         AND d.EIIN =?\n" +
                "         AND a.SSC_ROLL_NO = b.ROLL_NO\n" +
                "         AND A.SSC_BOARD_ID = b.BOARD_ID\n" +
                "         AND a.SSC_PASSING_YEAR = b.PASSING_YEAR\n" +
                "         AND A.SSC_BOARD_ID = C.BOARD_ID\n" +
                "         AND a.APPLICATION_ID = d.APPLICATION_ID";
	

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, eiinCode);
			r = stmt.executeQuery();
			
			newspecialQuotaStudentinfoList = new ArrayList<ListOfStudentOfCollegeDTO>();
			while(r.next())
			{
				listOfStudentOfCollegeDTO = new ListOfStudentOfCollegeDTO();
				listOfStudentOfCollegeDTO.setApplicationID(r.getString("APPLICATION_ID"));
				listOfStudentOfCollegeDTO.setApplicantName(r.getString("STUDENT_NAME"));
				listOfStudentOfCollegeDTO.setFatherName(r.getString("FATHER_NAME"));
				listOfStudentOfCollegeDTO.setSscRollNo(r.getString("SSC_ROLL_NO"));
				listOfStudentOfCollegeDTO.setBoardName(r.getString("BOARD_NAME_ENG"));
				listOfStudentOfCollegeDTO.setSscPassingYear(r.getString("PASSING_YEAR"));
				listOfStudentOfCollegeDTO.setEiinCode(r.getString("EIIN"));
/*				listOfStudentOfCollegeDTO.setShiftID(r.getString("SHIFT_ID"));
				listOfStudentOfCollegeDTO.setShiftName(r.getString("SHIFT_NAME"));
				listOfStudentOfCollegeDTO.setVersionID(r.getString("VERSION_ID"));
				listOfStudentOfCollegeDTO.setVersionName(r.getString("VERSION_NAME"));
				listOfStudentOfCollegeDTO.setGroupId(r.getString("GROUP_ID"));
				listOfStudentOfCollegeDTO.setGroupName(r.getString("GROUP_NAME"));
				listOfStudentOfCollegeDTO.setSpecialQuotaStatus(r.getString("SPECIAL_QUOTA"));*/
				listOfStudentOfCollegeDTO.setSpecialQuotaGrant(r.getString("QUOTA_SPECIAL_GRANT"));
			

				newspecialQuotaStudentinfoList.add(listOfStudentOfCollegeDTO);
				

			}
			System.out.println("newspecialQuotaStudentinfoList.size="+newspecialQuotaStudentinfoList.size());
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
		
		return newspecialQuotaStudentinfoList;
	}
	
	public Boolean isValidApp(String appID,String ssc_roll,String eiin)
	{
		Connection conn = ConnectionManager.getReadConnection();
		
		  String sql=" SELECT distinct a.APPLICATION_ID,\n" +
          "         b.STUDENT_NAME,\n" +
          "         b.FATHER_NAME,\n" +
          "         a.SSC_ROLL_NO,\n" +
          "         c.BOARD_NAME_ENG,\n" +
          "         b.PASSING_YEAR,\n" +
          "         d.EIIN, \n" +
          "         d.QUOTA_SPECIAL_GRANT\n" +
          "    FROM MST_EDU_BOARD c,\n" +
          "         APPLICATION_INFO a,\n" +
          "         APPLICATION_COLLEGES d,\n" +
          "         BOARD_DATA_SSC b\n" +
          "   WHERE     a.APPLICATION_ID in (?)\n" +
          "         AND d.EIIN =?\n" +
          "         AND a.SSC_ROLL_NO =?\n" +
          "         AND a.SSC_ROLL_NO = b.ROLL_NO\n" +
          "         AND A.SSC_BOARD_ID = b.BOARD_ID\n" +
          "         AND a.SSC_PASSING_YEAR = b.PASSING_YEAR\n" +
          "         AND A.SSC_BOARD_ID = C.BOARD_ID\n" +
          "         AND a.APPLICATION_ID = d.APPLICATION_ID";


				PreparedStatement stmt = null;
				ResultSet r = null;
				try
				{
					stmt = conn.prepareStatement(sql);
					
					stmt.setString(1, appID);
					stmt.setString(2, eiin);
					stmt.setString(3, ssc_roll);
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

	public Boolean updateSpecialQuotaGrant(String applicationID[],String eiin, String status)
	{
		
		Connection conn = ConnectionManager.getWriteConnection();
		
		String sql ="UPDATE APPLICATION_COLLEGES\n" +
                "   SET QUOTA_SPECIAL_GRANT = ?\n" +
                " WHERE APPLICATION_ID =?  AND EIIN=?";
		PreparedStatement stmt = null;
		try
		{
			if(applicationID!=null) { 
			for(int i=0;i<applicationID.length;i++)
			{
		    
		    stmt = conn.prepareStatement(sql);
		    stmt.setString(1, status);
			stmt.setString(2, applicationID[i]);
			stmt.setString(3, eiin);

			stmt.executeUpdate();
			}		
			}
		} 
		catch (Exception e){
			e.printStackTrace();
			return Boolean.FALSE;
		}
		finally{
			try{
				 if (stmt!= null) {
					 stmt.close();
			        }
				/*stmt.close();*/
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
	
	static PreparedStatement stmtUpdateSQ = null;
	static String sqlUpdateSQ = "UPDATE APPLICATION_COLLEGES SET QUOTA_SPECIAL_GRANT = ? WHERE APPLICANT_ID =?  AND EIIN=?";
	public static synchronized int updateSQGrant(String app_id,String eiin,String status)
    {
		int tmp = 0;
        try {
            
            if(stmtUpdateSQ==null)
            	stmtUpdateSQ = ConnectionManager.getConnectionStatic().prepareStatement(sqlUpdateSQ);
            int parameterIndex = 1;
            stmtUpdateSQ.setString(parameterIndex++, status.equalsIgnoreCase("approve")?"Y":"N");
            stmtUpdateSQ.setString(parameterIndex++, app_id);	            
            stmtUpdateSQ.setString(parameterIndex++, eiin);

            tmp = stmtUpdateSQ.executeUpdate();
            stmtUpdateSQ.clearParameters();
            
          } catch (SQLException e) {
            System.out.println(e.toString());
            stmtUpdateSQ = null;
            ConnectionManager.closeStatic();
        }
		
		return tmp;
    }
	
	public Boolean updateEducationQuotaGrant(String applicationID[],String eiin, String status)
	{
		
		Connection conn = ConnectionManager.getWriteConnection();
		
		String sql ="UPDATE APPLICATION_COLLEGES\n" +
                "   SET  EQ_APPROVAL= ?\n" +
                " WHERE APPLICATION_ID = ? AND EIIN = ?";
		
		PreparedStatement stmt = null;
		try
		{
			if(applicationID!=null) { 
			for(int i=0;i<applicationID.length;i++)
			{
		    
		    stmt = conn.prepareStatement(sql);
		    stmt.setString(1, status);
			stmt.setString(2, applicationID[i]);
			stmt.setString(3, eiin);

			stmt.executeUpdate();
			}		
			}
		} 
		catch (Exception e){
			e.printStackTrace();
			return Boolean.FALSE;
		}
		finally{
			try{
				 if (stmt!= null) {
					 stmt.close();
			        }
				/*stmt.close();*/
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
	
	public Boolean approveSpecialQuotaIndividualy(String applicationID[],String eiin[])
	{
		
		Connection conn = ConnectionManager.getWriteConnection();
		
		String sql ="UPDATE APPLICATION_COLLEGES\n" +
                "   SET QUOTA_SPECIAL_GRANT = 'Y'\n" +
                " WHERE APPLICANT_ID =?  AND EIIN=?";
		PreparedStatement stmt = null;
		try
		{
			if(applicationID!=null) { 
			for(int i=0;i<applicationID.length;i++)
			{
	//	    System.out.println("Application_ID="+applicationID[i]);
	//	    System.out.println("eiin="+eiin[i]); 
		    
		    stmt = conn.prepareStatement(sql);
			stmt.setString(1, applicationID[i]);
			stmt.setString(2, eiin[i]);

			stmt.executeUpdate();
	//		System.out.println("Success:"+i);
			}		
			}
		} 
		catch (Exception e){
			e.printStackTrace();
			return Boolean.FALSE;
		}
		finally{
			try{
				 if (stmt!= null) {
					 stmt.close();
			        }
				/*stmt.close();*/
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
	
	public Boolean approveEducationQuotaIndividualy(String applicationID[],String eiin[])
	{
		
		Connection conn = ConnectionManager.getWriteConnection();
		
		String sql ="UPDATE APPLICATION_COLLEGES\n" +
                "   SET EQ_APPROVAL= 'Y'\n" +
                " WHERE APPLICATION_ID =?  AND EIIN=?";
		PreparedStatement stmt = null;
		try
		{
			if(applicationID!=null) { 
			for(int i=0;i<applicationID.length;i++)
			{
	//	    System.out.println("Application_ID="+applicationID[i]);
	//	    System.out.println("eiin="+eiin[i]); 
		    
		    stmt = conn.prepareStatement(sql);
			stmt.setString(1, applicationID[i]);
			stmt.setString(2, eiin[i]);

			stmt.executeUpdate();
	//		System.out.println("Success:"+i);
			}		
			}
		} 
		catch (Exception e){
			e.printStackTrace();
			return Boolean.FALSE;
		}
		finally{
			try{
				 if (stmt!= null) {
					 stmt.close();
			        }
				/*stmt.close();*/
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
	

}
