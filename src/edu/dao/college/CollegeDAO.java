package edu.dao.college;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts2.ServletActionContext;

import net.spy.memcached.MemcachedClient;


import oracle.jdbc.OracleCallableStatement;

import edu.dto.IpAddressDTO;
import edu.dto.application.ChoiceDTO;
import edu.dto.application.CollegeEligibilityDTO;
import edu.dto.application.SVGDTO;
import edu.dto.college.CollegeDTO;
import edu.utils.connection.ConnectionManager;

public class CollegeDAO {

		public static HashMap<String, CollegeEligibilityDTO> getCollegeEligibilityList(){
		Connection conn = ConnectionManager.getReadConnection();
//		Connection conn = ConnectionManager.getConnectionStatic();
		
		String sql = "Select * From MST_COLLEGE_GROUPS Order by EIIN";
		PreparedStatement stmt = null;
		ResultSet r = null;
		HashMap<String, CollegeEligibilityDTO> eligibilityList=new HashMap<String, CollegeEligibilityDTO>();
		StringBuilder sBuilder=new StringBuilder();
		
		try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					sBuilder=new StringBuilder(); 
					if(r.getString("GENDER")!=null){
						if(r.getString("GENDER").equalsIgnoreCase("C")){
							sBuilder.append(r.getString("EIIN")).append(r.getString("SHIFT_ID")).append(r.getString("VERSION_ID")).append(r.getString("GROUP_ID")).append("M");
							eligibilityList.put(sBuilder.toString(),new CollegeEligibilityDTO(r.getString("EIIN"),r.getFloat("MINIMUM_GPA"),r.getFloat("OWN_GPA"),r.getFloat("SQ_MIN_GPA"),r.getString("IS_SQ"),r.getInt("TOTAL_SEAT")));
//if(r.getString("EIIN").equalsIgnoreCase("114833"))
//	System.out.println(r.getInt("TOTAL_SEAT"));
							
							sBuilder=new StringBuilder();
							sBuilder.append(r.getString("EIIN")).append(r.getString("SHIFT_ID")).append(r.getString("VERSION_ID")).append(r.getString("GROUP_ID")).append("F");
							eligibilityList.put(sBuilder.toString(),new CollegeEligibilityDTO(r.getString("EIIN"),r.getFloat("MINIMUM_GPA"),r.getFloat("OWN_GPA"),r.getFloat("SQ_MIN_GPA"),r.getString("IS_SQ"),r.getInt("TOTAL_SEAT")));
						}
						else{
							sBuilder.append(r.getString("EIIN")).append(r.getString("SHIFT_ID")).append(r.getString("VERSION_ID")).append(r.getString("GROUP_ID")).append(r.getString("GENDER"));
							eligibilityList.put(sBuilder.toString(),new CollegeEligibilityDTO(r.getString("EIIN"),r.getFloat("MINIMUM_GPA"),r.getFloat("OWN_GPA"),r.getFloat("SQ_MIN_GPA"),r.getString("IS_SQ"),r.getInt("TOTAL_SEAT")));
						}
					}
				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return eligibilityList;
	}
	
	public static HashMap<String, String> getCollegeEligibility()
	{
		Connection conn = ConnectionManager.getReadConnection();
//		Connection conn = ConnectionManager.getConnectionStatic();
		String sql = "Select * From MST_COLLEGE_GROUPS Order by EIIN";
		PreparedStatement stmt = null;
		ResultSet r = null;
		HashMap<String, String> eligibilityList=new HashMap<String, String>();
		StringBuilder key=new StringBuilder();
		StringBuilder eligibility=new StringBuilder();
		String pEiin="";


		try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					key.setLength(0);
					key.append(r.getString("SHIFT_ID"))
					   .append(r.getString("VERSION_ID"))
					   .append(r.getString("GROUP_ID"))
					   .append(r.getString("GENDER"));	
					
					if(pEiin.equalsIgnoreCase(r.getString("EIIN"))){
				
						eligibility.append("{")
						   .append("\"key\":\"").append(key).append("\"").append(",")
						   .append("\"gpa\":\"").append(r.getFloat("MINIMUM_GPA")).append("\"").append(",")
						   .append("\"own_gpa\":\"").append(r.getFloat("OWN_GPA")+"\"").append(",")
						   .append("\"spc_gpa\":\"").append(r.getFloat("SQ_MIN_GPA")+"\"").append(",")
						   .append("\"spc_yn\":\"").append(r.getString("IS_SQ")+"\"")
						   .append("}")
						   .append(",");
					}
					else{
						if(pEiin.equalsIgnoreCase("")){
						}
						else{
							eligibility.delete(eligibility.length()-1, eligibility.length());
							eligibilityList.put(pEiin, eligibility.toString());
						}
						eligibility.setLength(0);
						eligibility.append("{")
								   .append("\"key\":\"").append(key).append("\"").append(",")
								   .append("\"gpa\":\"").append(r.getFloat("MINIMUM_GPA")).append("\"").append(",")
								   .append("\"own_gpa\":\"").append(r.getFloat("OWN_GPA")+"\"").append(",")
								   .append("\"spc_gpa\":\"").append(r.getFloat("SQ_MIN_GPA")+"\"").append(",")
								   .append("\"spc_yn\":\"").append(r.getString("IS_SQ")+"\"")
								   .append("}")
								   .append(",");
						
					}
					pEiin=r.getString("EIIN");
				}
				eligibility.delete(eligibility.length()-1, eligibility.length());
				eligibilityList.put(pEiin, eligibility.toString());
		}
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return eligibilityList;
	}
	
	public static HashMap<String, SVGDTO> getSVGlist()
	{
		Connection conn = ConnectionManager.getReadConnection();
//		Connection conn = ConnectionManager.getConnectionStatic();
		String sql = "Select Distinct EIIN,Shift_Id,Version_Id,Group_Id,GENDER,IS_SQ,NVL(TOTAL_SEAT,0) TOTAL_SEAT,NVL(AVAILABLE_SEAT,0) AVAILABLE_SEAT from MST_COLLEGE_GROUPS where NVL(AVAILABLE_SEAT,0)!=0 order by EIIN,SHIFT_ID,VERSION_ID,GROUP_ID";
		PreparedStatement stmt = null;
		ResultSet r = null;
		HashMap<String, SVGDTO> svgList=new HashMap<String, SVGDTO>();
		String eiin="";
		String shift="";
		String version="";
		String group="";
		String gender="";
		String sqYN="";
		int totalSeat=0;
		int availAbleSeat=0;
		SVGDTO svg=null;
		try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					eiin=r.getString("EIIN").toString();
					shift=r.getString("Shift_Id").toString();
					version=r.getString("Version_Id").toString();
					group=r.getString("Group_Id").toString();
					gender=r.getString("gender").toString();
					sqYN=r.getString("IS_SQ")==null?"N":r.getString("IS_SQ").toString();
					totalSeat=r.getInt("TOTAL_SEAT");
					availAbleSeat=r.getInt("AVAILABLE_SEAT");
					
					svg=svgList.get(eiin)==null?new SVGDTO():svgList.get(eiin);
					svg.getShiftList().add(shift);
					svg.getShiftVersionList().put(shift, version);
					svg.getVersionGroupList().put((shift+","+version), group);
					svg.getGroupEQList().put((shift+","+version+","+group), sqYN);
					svg.getSvgAvailableSeat().put((shift+","+version+","+group), String.valueOf(availAbleSeat));
					svg.getSvgTotalSeat().put((shift+","+version+","+group+','+gender), String.valueOf(totalSeat));
					svg.getSvgTotalSeat1().put((shift+","+version+","+group), String.valueOf(totalSeat));
					svgList.put(eiin,svg);

				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return svgList;
	}
	
	public static ArrayList<String> hasManualEntrylist()
	{
		Connection conn = ConnectionManager.getReadConnection();
//		Connection conn = ConnectionManager.getConnectionStatic();
		String sql = "Select Distinct EIIN from COLLEGE3DATAENTRY";
		PreparedStatement stmt = null;
		ResultSet r = null;
		ArrayList<String> lstHasManualEntry=new ArrayList<String>();
		try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					lstHasManualEntry.add(r.getString("EIIN"));

				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return lstHasManualEntry;
	}
	

	/**
	 * @author nasir
	 * @param eiin
	 * @return
	 */
	public  static ArrayList<CollegeDTO> getCollegeSVGList(String board_id)
	{
		Connection conn = ConnectionManager.getReadConnection();
		//String sql = "Select EIIN,COLLEGE_NAME,DIST_ID From MST_COLLEGE where board_id=? ORDER BY EIIN";
		String sql = "  SELECT DD.DIST_NAME, " +
				"         EE.THANA_NAME, " +
				"         aa.eiin, " +
				"         REPLACE (aa.COLLEGE_NAME, ',', ' ') College_Name, " +
				"         DECODE (SHIFT_ID,  1, 'Morning',  2, 'Day', 11, 'Morning Male',  12, 'Day Male',  3, 'Evening') SHIFT, " +
//				"         DECODE (SHIFT_ID,  1, 'Morning',  2, 'Day',  3, 'Evening') SHIFT, " +
				"         DECODE (VERSION_ID, 1, 'Bangla', 'English') VERSION, " +
				"         GROUP_NAME, " +
				"         DECODE (GENDER,  'C', 'Co-Education',  'M', 'Male',  'F', 'Female') " +
				"            GENDER, " +
				"         TO_CHAR (NVL (MINIMUM_GPA, 0), '0.00') MINIMUM_GPA, " +
				"         TO_CHAR (NVL (OWN_GPA, 0), '0.00') OWN_GPA, " +
				"         Total_Seat, " +
				"         Available_Seat " +
				"    FROM mst_college aa, " +
				"         mst_college_groups bb, " +
				"         mst_group cc, " +
				"         mst_district dd, " +
				"         mst_thana ee " +
				"   WHERE AA.IS_ACTIVE = 1" +
				"		  AND AA.EIIN = bb.eiin " +
				"         AND BB.GROUP_ID = CC.GROUP_ID " +
				"         AND AA.DIST_ID = DD.DIST_ID " +
				"         AND AA.THANA_ID = EE.THANA_ID " +
				"         AND aa.board_id = ? " +
				"ORDER BY aa.board_id, " +
				"         DD.DIST_NAME, " +
				"         EE.THANA_NAME, " +
				"         aa.COLLEGE_NAME " ;

		PreparedStatement stmt = null;
		ResultSet r = null;
		ArrayList<CollegeDTO> collegeList=new ArrayList<CollegeDTO>();
		CollegeDTO college=null;
		
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, board_id);
				r = stmt.executeQuery();				
				while (r.next())
				{
					college=new CollegeDTO();
					college.setDist_name(r.getString("DIST_NAME"));
					college.setThana_name(r.getString("THANA_NAME"));
					college.setEiin(r.getString("EIIN"));
					college.setCollege_name(r.getString("COLLEGE_NAME").replaceAll("'", ""));
					college.setShift(r.getString("SHIFT"));
					college.setVersion(r.getString("VERSION"));
					college.setGroup(r.getString("GROUP_NAME"));
					college.setGender(r.getString("GENDER"));
					college.setMinGpa(r.getString("MINIMUM_GPA"));
					college.setOwnGpa(r.getString("OWN_GPA"));
					college.setTotalSeat(r.getString("Total_Seat"));
					college.setAvailSeat(r.getString("Available_Seat"));
					//college.setDist_id(r.getString("DIST_ID"));
					collegeList.add(college);
				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return collegeList;
	}
	
	public  static ArrayList<CollegeDTO> getCollegeList()
	{
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "Select * From MST_COLLEGE ORDER BY EIIN";
		PreparedStatement stmt = null;
		ResultSet r = null;
		ArrayList<CollegeDTO> collegeList=new ArrayList<CollegeDTO>();
		CollegeDTO college=null;
		
		try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();				
				while (r.next())
				{
					college=new CollegeDTO();
					college.setEiin(r.getString("EIIN"));
					college.setCollege_name(r.getString("COLLEGE_NAME"));					
					college.setDist_id(r.getString("DIST_ID"));
					collegeList.add(college);
				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return collegeList;
	}
	
	public  static ArrayList<CollegeDTO> getCollegeList(String board_id)
	{
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "Select EIIN,COLLEGE_NAME,DIST_ID From MST_COLLEGE where board_id=? ORDER BY EIIN";
		PreparedStatement stmt = null;
		ResultSet r = null;
		ArrayList<CollegeDTO> collegeList=new ArrayList<CollegeDTO>();
		CollegeDTO college=null;
		
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, board_id);
				r = stmt.executeQuery();				
				while (r.next())
				{
					college=new CollegeDTO();
					college.setEiin(r.getString("EIIN"));
					college.setCollege_name(r.getString("COLLEGE_NAME").replaceAll("'", ""));					
					college.setDist_id(r.getString("DIST_ID"));
					collegeList.add(college);
				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return collegeList;
	}
	
	public  static ArrayList<CollegeDTO> getCollegeSubjectList(String eiin)
	{
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "select eiin,t1.sub_id,sub_name from mst_sub_college t1,mst_subject t2 where t1.sub_id=t2.sub_id and eiin=? order by sub_name";
		PreparedStatement stmt = null;
		ResultSet r = null;
		ArrayList<CollegeDTO> collegeList=new ArrayList<CollegeDTO>();
		CollegeDTO college=null;
		
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, eiin);
				r = stmt.executeQuery();				
				while (r.next())
				{
					college=new CollegeDTO();
					college.setEiin(r.getString("EIIN"));
					college.setSub_id(r.getString("sub_id"));					
					college.setSub_name(r.getString("sub_name"));
					collegeList.add(college);
				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return collegeList;
	}
	
	public  static ArrayList<CollegeDTO> getOtherSubjectList(String eiin)
	{
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "select * from MST_SUBJECT" +
				" where sub_id in (" +
				" select sub_id from MST_SUBJECT" +
				" minus" +
				" SELECT t1.sub_id" +
				"    FROM mst_sub_college t1, mst_subject t2" +
				"   WHERE t1.sub_id = t2.sub_id AND eiin = ?" +
				" ) ORDER BY sub_id";
		PreparedStatement stmt = null;
		ResultSet r = null;
		ArrayList<CollegeDTO> collegeList=new ArrayList<CollegeDTO>();
		CollegeDTO college=null;
		
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, eiin);
				r = stmt.executeQuery();				
				while (r.next())
				{
					college=new CollegeDTO();
					college.setEiin(r.getString("EIIN"));
					college.setSub_id(r.getString("sub_id"));					
					college.setSub_name(r.getString("sub_name"));
					collegeList.add(college);
				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return collegeList;
	}
	
	public  static boolean deleteSubject(String eiin, String sub_id)throws Exception
	{
		boolean tmp = false;
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "delete from mst_sub_college where eiin=? and sub_id=?";
		PreparedStatement stmt = null;
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, eiin);
				stmt.setString(2, sub_id);
				if(stmt.executeUpdate()>0)	
					tmp = true;
			} 
		catch (Exception e){e.printStackTrace();throw e;}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return tmp;
	}
	public  static boolean addSubject(String eiin, String sub_id)throws Exception
	{
		boolean tmp = false;
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "insert into mst_sub_college (eiin,sub_id) values(?,?)";
		PreparedStatement stmt = null;
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, eiin);
				stmt.setString(2, sub_id);
				if(stmt.executeUpdate()>0)	
					tmp = true;
			} 
		catch (Exception e){e.printStackTrace();throw e;}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return tmp;
	}
	
	public  static String getSubjectEiin(String eiin)throws Exception
	{
		String tmp = "";
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "select * from MST_SUBJECT where sub_id not in (select sub_id from mst_sub_college where eiin=?)" +
				" order by sub_id";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, eiin);
				r = stmt.executeQuery();
				while(r.next())
				{
					tmp += r.getString("sub_id")+"###"+r.getString("sub_name")+"###" ;
				}
			} 
		catch (Exception e){e.printStackTrace();throw e;}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return tmp;
	}
	
	public  static HashMap<String,ArrayList<CollegeDTO>> getAllColleges()
	{
		Connection conn = ConnectionManager.getReadConnection();
//		Connection conn = ConnectionManager.getConnectionStatic();
//		String sql = "Select * From MST_COLLEGE where eiin in (Select Distinct EIIN from MST_COLLEGE_GROUPS where NVL(AVAILABLE_SEAT,0)!=0) ORDER BY THANA_ID,HELPER_BOARD_ID,COLLEGE_NAME";
		String sql = "Select * From MST_COLLEGE ORDER BY THANA_ID,HELPER_BOARD_ID,COLLEGE_NAME";
		PreparedStatement stmt = null;
		ResultSet r = null;
		HashMap<String,ArrayList<CollegeDTO>> collegeHashMap=new HashMap<String, ArrayList<CollegeDTO>>();		
		ArrayList<CollegeDTO> collegeList=new ArrayList<CollegeDTO>();
		CollegeDTO college=null;
		String pDistBoardId="";
		String compositKey="";
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while (r.next())
			{
				compositKey=r.getString("THANA_ID")+r.getString("HELPER_BOARD_ID");
				if(pDistBoardId.equalsIgnoreCase(compositKey)){
			
					college=new CollegeDTO();
					college.setCollege_name(r.getString("COLLEGE_NAME")+" ["+r.getString("EIIN")+"]");
					college.setEiin(r.getString("EIIN"));
					
					
					collegeList.add(college);
				}
				else{
					if(pDistBoardId.equalsIgnoreCase("")){}
					else{
						collegeHashMap.put(pDistBoardId,collegeList);
						collegeList=new ArrayList<CollegeDTO>();
					}
					college=new CollegeDTO();
					college.setCollege_name(r.getString("COLLEGE_NAME")+" ["+r.getString("EIIN")+"]");
					college.setEiin(r.getString("EIIN"));
					collegeList.add(college);
					
				}
				pDistBoardId=compositKey;
			}
			
			collegeHashMap.put(pDistBoardId,collegeList);
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return collegeHashMap;
	}
	public  static HashMap<String,ArrayList<CollegeDTO>> getAllColleges_OpenAdmission()
	{
		Connection conn = ConnectionManager.getReadConnection();
//		Connection conn = ConnectionManager.getConnectionStatic();
		String sql = "Select * From MST_COLLEGE ORDER BY THANA_ID,HELPER_BOARD_ID,COLLEGE_NAME";
		PreparedStatement stmt = null;
		ResultSet r = null;
		HashMap<String,ArrayList<CollegeDTO>> collegeHashMap=new HashMap<String, ArrayList<CollegeDTO>>();		
		ArrayList<CollegeDTO> collegeList=new ArrayList<CollegeDTO>();
		CollegeDTO college=null;
		String pDistBoardId="";
		String compositKey="";
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while (r.next())
			{
				compositKey=r.getString("EIIN")+r.getString("THANA_ID")+r.getString("HELPER_BOARD_ID");
				if(pDistBoardId.equalsIgnoreCase(compositKey)){
			
					college=new CollegeDTO();
					college.setCollege_name(r.getString("COLLEGE_NAME")+" ["+r.getString("EIIN")+"]");
					college.setEiin(r.getString("EIIN"));
					
					
					collegeList.add(college);
				}
				else{
					if(pDistBoardId.equalsIgnoreCase("")){}
					else{
						collegeHashMap.put(pDistBoardId,collegeList);
						collegeList=new ArrayList<CollegeDTO>();
					}
					college=new CollegeDTO();
					college.setCollege_name(r.getString("COLLEGE_NAME")+" ["+r.getString("EIIN")+"]");
					college.setEiin(r.getString("EIIN"));
					collegeList.add(college);
					
				}
				pDistBoardId=compositKey;
			}
			
			collegeHashMap.put(pDistBoardId,collegeList);
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return collegeHashMap;
	}
	public  static HashMap<String,ArrayList<CollegeDTO>> getAllCollegesDist()
	{
		Connection conn = ConnectionManager.getReadConnection();
//		Connection conn = ConnectionManager.getConnectionStatic();
		String sql = "Select * From MST_COLLEGE ORDER BY DIST_ID,HELPER_BOARD_ID,COLLEGE_NAME";
		PreparedStatement stmt = null;
		ResultSet r = null;
		HashMap<String,ArrayList<CollegeDTO>> collegeHashMap=new HashMap<String, ArrayList<CollegeDTO>>();		
		ArrayList<CollegeDTO> collegeList=new ArrayList<CollegeDTO>();
		CollegeDTO college=null;
		String pDistBoardId="";
		String compositKey="";
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while (r.next())
			{
				compositKey=r.getString("DIST_ID")+r.getString("HELPER_BOARD_ID");
				if(pDistBoardId.equalsIgnoreCase(compositKey)){
			
					college=new CollegeDTO();
					college.setCollege_name(r.getString("COLLEGE_NAME")+" ["+r.getString("EIIN")+"]");
					college.setEiin(r.getString("EIIN"));
					
					
					collegeList.add(college);
				}
				else{
					if(pDistBoardId.equalsIgnoreCase("")){}
					else{
						collegeHashMap.put(pDistBoardId,collegeList);
						collegeList=new ArrayList<CollegeDTO>();
					}
					college=new CollegeDTO();
					college.setCollege_name(r.getString("COLLEGE_NAME")+" ["+r.getString("EIIN")+"]");
					college.setEiin(r.getString("EIIN"));
					collegeList.add(college);
					
				}
				pDistBoardId=compositKey;
			}
			
			collegeHashMap.put(pDistBoardId,collegeList);
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return collegeHashMap;
	}	
	public  static HashMap<String,ArrayList<CollegeDTO>> getAllCollegesDistBOARD()
	{
		Connection conn = ConnectionManager.getReadConnection();
//		Connection conn = ConnectionManager.getConnectionStatic();
		String sql = "Select t1.*,T2.THANA_NAME From MST_COLLEGE t1, mst_thana t2" +
				" where T1.THANA_ID=T2.THANA_ID ORDER BY t1.DIST_ID,BOARD_ID,COLLEGE_NAME";
		PreparedStatement stmt = null;
		ResultSet r = null;
		HashMap<String,ArrayList<CollegeDTO>> collegeHashMap=new HashMap<String, ArrayList<CollegeDTO>>();		
		ArrayList<CollegeDTO> collegeList=new ArrayList<CollegeDTO>();
		CollegeDTO college=null;
		String pDistBoardId="";
		String compositKey="";
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while (r.next())
			{
				compositKey=r.getString("DIST_ID")+r.getString("BOARD_ID");
				if(pDistBoardId.equalsIgnoreCase(compositKey)){
			
					college=new CollegeDTO();
					college.setCollege_name(r.getString("COLLEGE_NAME")+" ["+r.getString("EIIN")+"]");
					college.setEiin(r.getString("EIIN"));
					college.setThana_name(r.getString("THANA_NAME"));
					
					
					collegeList.add(college);
				}
				else{
					if(pDistBoardId.equalsIgnoreCase("")){}
					else{
						collegeHashMap.put(pDistBoardId,collegeList);
						collegeList=new ArrayList<CollegeDTO>();
					}
					college=new CollegeDTO();
					college.setCollege_name(r.getString("COLLEGE_NAME")+" ["+r.getString("EIIN")+"]");
					college.setEiin(r.getString("EIIN"));
					college.setThana_name(r.getString("THANA_NAME"));
					collegeList.add(college);
					
				}
				pDistBoardId=compositKey;
			}
			
			collegeHashMap.put(pDistBoardId,collegeList);
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return collegeHashMap;
	}
	public  static HashMap<String,ArrayList<CollegeDTO>> getAllCollegesDist_OpenAdmission()
	{
		Connection conn = ConnectionManager.getReadConnection();
//		Connection conn = ConnectionManager.getConnectionStatic();
		String sql = "Select * From MST_COLLEGE ORDER BY DIST_ID,HELPER_BOARD_ID,COLLEGE_NAME";
		PreparedStatement stmt = null;
		ResultSet r = null;
		HashMap<String,ArrayList<CollegeDTO>> collegeHashMap=new HashMap<String, ArrayList<CollegeDTO>>();		
		ArrayList<CollegeDTO> collegeList=new ArrayList<CollegeDTO>();
		CollegeDTO college=null;
		String pDistBoardId="";
		String compositKey="";
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while (r.next())
			{
				compositKey=r.getString("EIIN")+r.getString("DIST_ID")+r.getString("HELPER_BOARD_ID");
				if(pDistBoardId.equalsIgnoreCase(compositKey)){
			
					college=new CollegeDTO();
					college.setCollege_name(r.getString("COLLEGE_NAME")+" ["+r.getString("EIIN")+"]");
					college.setEiin(r.getString("EIIN"));
					
					
					collegeList.add(college);
				}
				else{
					if(pDistBoardId.equalsIgnoreCase("")){}
					else{
						collegeHashMap.put(pDistBoardId,collegeList);
						collegeList=new ArrayList<CollegeDTO>();
					}
					college=new CollegeDTO();
					college.setCollege_name(r.getString("COLLEGE_NAME")+" ["+r.getString("EIIN")+"]");
					college.setEiin(r.getString("EIIN"));
					collegeList.add(college);
					
				}
				pDistBoardId=compositKey;
			}
			
			collegeHashMap.put(pDistBoardId,collegeList);
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return collegeHashMap;
	}
	private static int userTTL = 3600;
	public static CollegeDTO getCollege(String eiin)
	{
		
//		MemcachedClient mc;
//		try
//		{
//			mc= ConnectionManager.createMemcachedClient();
//		}
//		catch(Exception e)
//		{
//			mc= ConnectionManager.createMemcachedClient();
//		}
//		Object tmpObject = mc.get("ALL_COLLEGE");
//		HashMap<String, CollegeDTO> tmpCollege;
//		if(tmpObject!=null)
//			tmpCollege = (HashMap<String, CollegeDTO>)tmpObject;
//		else
//			tmpCollege=getEiinCollegeMap();
//		return tmpCollege.get(eiin);
		HashMap<String, CollegeDTO> cMap=(HashMap<String, CollegeDTO>)ServletActionContext.getServletContext().getAttribute("EIIN_COLLEGE_MAP");
		return cMap.get(eiin);
	}
	public static HashMap<String, CollegeDTO> getEiinCollegeMap()
	{
		Connection conn = ConnectionManager.getConnectionStatic();
//		Connection conn = ConnectionManager.getConnectionStatic();
		String sql = "Select * From MST_COLLEGE Order by eiin";
		PreparedStatement stmt = null;
		ResultSet r = null;
		HashMap<String, CollegeDTO> eiinCollegeMap=new HashMap<String, CollegeDTO>();
		CollegeDTO college=null;
		try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					college=new CollegeDTO();
					college.setCollege_name(r.getString("College_name"));
					college.setMobileVerifiedYN(r.getString("ISVALID_MOBILE_YN"));
					college.setMobile(r.getString("MOBILE_NO"));
//					college.setDist_id(r.getString("DIST_ID"));
//					college.setHelper_board_id(r.getString("HELPER_BOARD_ID"));
					eiinCollegeMap.put(r.getString("EIIN"),college);
				}
//				MemcachedClient mc= ConnectionManager.createMemcachedClient();
//				mc.set("ALL_COLLEGE", userTTL, (Object)eiinCollegeMap);
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeStatic();} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return eiinCollegeMap;
	}
	public static CollegeDTO getEiinCollegeInfo(String eiin)
	{
		CollegeDTO college = new CollegeDTO();
		Connection conn = ConnectionManager.getConnectionStatic();
//		Connection conn = ConnectionManager.getConnectionStatic();
		String sql = "Select * From MST_COLLEGE where eiin='"+eiin+"'";
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
			{
				stmt = conn.prepareStatement(sql);
				r = stmt.executeQuery();
				while (r.next())
				{
					college=new CollegeDTO();
					college.setCollege_name(r.getString("College_name"));
					college.setMobileVerifiedYN(r.getString("ISVALID_MOBILE_YN"));
					college.setMobile(r.getString("MOBILE_NO"));
				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeStatic();} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return college;
	}
	
	public boolean editQuota(String quota_ff,String quota_eq,String quota_bksp,String quota_expatriate,String application_id){

		boolean response=false;
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

	}
	
	public ArrayList<ChoiceDTO> getChoiceList_TT(String user_id)
	{
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "SELECT   application_colleges.*, college_name, mst_shift.shift_name, " +
		"         mst_version.version_name, mst_group.group_name, priority, MST_COLLEGE_GROUPS.AVAILABLE_SEAT " +
		"    FROM APPLICATION_COLLEGES application_colleges, mst_college, mst_shift, mst_version, mst_group, MST_COLLEGE_GROUPS " +
		"   WHERE application_colleges.APPLICANT_ID= ? " +
		"     AND mst_college.eiin = application_colleges.eiin " +
		"     AND application_colleges.shift_id = mst_shift.shift_id " +
		"     AND application_colleges.version_id = mst_version.version_id " +
		"     AND application_colleges.GROUP_ID = mst_group.GROUP_ID " +

		"     AND mst_college.eiin = MST_COLLEGE_GROUPS.eiin " +
		"     AND application_colleges.shift_id = MST_COLLEGE_GROUPS.shift_id " +
		"     AND application_colleges.version_id = MST_COLLEGE_GROUPS.version_id " +
		"     AND application_colleges.GROUP_ID = MST_COLLEGE_GROUPS.GROUP_ID " +

		"ORDER BY to_number(application_colleges.priority) ";
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		ArrayList<ChoiceDTO> choiceList=new ArrayList<ChoiceDTO>();
		ChoiceDTO choice=null;
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, user_id);
				r = stmt.executeQuery();
				while (r.next())
				{
					choice=new ChoiceDTO();
					choice.setChoice_id(r.getString("CHOICE_ID"));
					choice.setApplication_id(r.getString("APPLICANT_ID"));
					choice.setEiin(r.getString("EIIN"));
					choice.setCollege_name(r.getString("COLLEGE_NAME"));
					choice.setShift_id(r.getString("SHIFT_ID"));
					choice.setShift_name(r.getString("SHIFT_NAME"));
					choice.setVersion_id(r.getString("VERSION_ID"));
					choice.setVersion_name(r.getString("VERSION_NAME"));
					choice.setGroup_id(r.getString("GROUP_ID"));
					choice.setGroup_name(r.getString("GROUP_NAME"));
//					choice.setMobile_no(r.getString("CONTACT_NO"));
					//choice.setVia(r.getString("APPLICATION_TYPE"));
					
					choice.setPriority(r.getString("PRIORITY"));
					choice.setSpecial_quota(r.getString("QUOTA_SPECIAL"));
				 	choice.setGb_quota(r.getString("QUOTA_GB"));
					
					
					choice.setApplication_type(r.getString("APP_TYPE"));
				//	choice.setPayment_status(r.getString("PAYMENT_STATUS"));
					choice.setAvailable_seat(r.getString("AVAILABLE_SEAT"));
					choiceList.add(choice);
				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return choiceList;
	}
	
	public ArrayList<ChoiceDTO> getChoiceList(String user_id)
	{
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "SELECT   application_colleges.*, college_name, mst_shift.shift_name, " +
		"         mst_version.version_name, mst_group.group_name " +
		"    FROM application_colleges, mst_college, mst_shift, mst_version, mst_group, priority " +
		"   WHERE application_colleges.application_id = ? " +
		"     AND mst_college.eiin = application_colleges.eiin " +
		"     AND application_colleges.shift_id = mst_shift.shift_id " +
		"     AND application_colleges.version_id = mst_version.version_id " +
		"     AND application_colleges.GROUP_ID = mst_group.GROUP_ID " +
		"ORDER BY to_number(CHOICE_ID) ";
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		ArrayList<ChoiceDTO> choiceList=new ArrayList<ChoiceDTO>();
		ChoiceDTO choice=null;
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, user_id);
				r = stmt.executeQuery();
				while (r.next())
				{
					choice=new ChoiceDTO();
					choice.setChoice_id(r.getString("CHOICE_ID"));
					choice.setApplication_id(r.getString("APPLICATION_ID"));
					choice.setEiin(r.getString("EIIN"));
					choice.setCollege_name(r.getString("COLLEGE_NAME"));
					choice.setShift_id(r.getString("SHIFT_ID"));
					choice.setShift_name(r.getString("SHIFT_NAME"));
					choice.setVersion_id(r.getString("VERSION_ID"));
					choice.setVersion_name(r.getString("VERSION_NAME"));
					choice.setGroup_id(r.getString("GROUP_ID"));
					choice.setGroup_name(r.getString("GROUP_NAME"));
					choice.setVia(r.getString("APPLICATION_TYPE"));
					
					choice.setPriority(r.getString("CHOICE_ID"));
					choice.setSpecial_quota(r.getString("QUOTA_SPECIAL"));
//					choice.setEducation_quota(r.getString("education_quota"));
					
					
					choice.setApplication_type(r.getString("APPLICATION_TYPE"));
//					choice.setPayment_status(r.getString("PAYMENT_STATUS"));
					
					choice.setPriority(r.getString("priority"));
					
					choiceList.add(choice);
				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return choiceList;
	}
	
	public ArrayList<ChoiceDTO> getNewChoiceList(String user_id)
	{
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "SELECT application_colleges.*," +
				"         college_name," +
				"         dist_name," +
				"         mst_shift.shift_name," +
				"         mst_version.version_name," +
				"         mst_group.group_name" +
				"    FROM application_colleges," +
				"         mst_college," +
				"         mst_shift," +
				"         mst_version," +
				"         mst_group," +
				"         MST_DISTRICT" +
				"   WHERE     application_colleges.APPLICANT_ID = ?" +
				"         AND mst_district.dist_id = mst_college.dist_id" +
				"         AND mst_college.eiin = application_colleges.eiin" +
				"         AND application_colleges.shift_id = mst_shift.shift_id" +
				"         AND application_colleges.version_id = mst_version.version_id" +
				"         AND application_colleges.GROUP_ID = mst_group.GROUP_ID" +
				" ORDER BY TO_NUMBER (priority) ";
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		ArrayList<ChoiceDTO> choiceList=new ArrayList<ChoiceDTO>();
		ChoiceDTO choice=null;
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, user_id);
				r = stmt.executeQuery();
				while (r.next())
				{
					choice=new ChoiceDTO();
					choice.setChoice_id(r.getString("CHOICE_ID"));
					choice.setApplication_id(r.getString("APPLICANT_ID"));
					choice.setDist_name(r.getString("DIST_NAME"));
					choice.setEiin(r.getString("EIIN"));
					choice.setCollege_name(r.getString("COLLEGE_NAME"));
					choice.setShift_id(r.getString("SHIFT_ID"));
					choice.setShift_name(r.getString("SHIFT_NAME"));
					choice.setVersion_id(r.getString("VERSION_ID"));
					choice.setVersion_name(r.getString("VERSION_NAME"));
					choice.setGroup_id(r.getString("GROUP_ID"));
					choice.setGroup_name(r.getString("GROUP_NAME"));
					choice.setVia(r.getString("APP_TYPE"));
					
					choice.setPriority(r.getString("PRIORITY"));
					choice.setSpecial_quota(r.getString("QUOTA_SPECIAL"));
 					choice.setGb_quota(r.getString("QUOTA_GB"));
					
					
					choice.setApplication_type(r.getString("APP_TYPE"));
//					choice.setPayment_status(r.getString("PAYMENT_STATUS"));
//					choice.setPriority(r.getString("priority"));
					choiceList.add(choice);
				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return choiceList;
	}
	
	public ArrayList<ChoiceDTO> getReleaseSlipChoiceList(String user_id)
	{
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "SELECT rs_colleges.*, college_name, dist_name, mst_shift.shift_name, " +
		"         mst_version.version_name, mst_group.group_name " +
		"    FROM RELEASE_SLIP_COLLEGES rs_colleges, mst_college, MST_DISTRICT, mst_shift, mst_version, mst_group " +
		"   WHERE rs_colleges.application_id = ? " +
		"	  AND mst_district.dist_id = mst_college.dist_id " +
		"     AND mst_college.eiin = rs_colleges.eiin " +
		"     AND rs_colleges.shift_id = mst_shift.shift_id " +
		"     AND rs_colleges.version_id = mst_version.version_id " +
		"     AND rs_colleges.GROUP_ID = mst_group.GROUP_ID " +
		"ORDER BY to_number(priority_order) ";
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		ArrayList<ChoiceDTO> choiceList=new ArrayList<ChoiceDTO>();
		ChoiceDTO choice=null;
		try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, user_id);
				r = stmt.executeQuery();
				while (r.next())
				{
					choice=new ChoiceDTO();
					choice.setChoice_id(r.getString("CHOICE_ID"));
					choice.setApplication_id(r.getString("APPLICATION_ID"));
					choice.setEiin(r.getString("EIIN"));
					choice.setCollege_name(r.getString("COLLEGE_NAME"));
					choice.setDist_name(r.getString("DIST_NAME"));
					choice.setShift_id(r.getString("SHIFT_ID"));
					choice.setShift_name(r.getString("SHIFT_NAME"));
					choice.setVersion_id(r.getString("VERSION_ID"));
					choice.setVersion_name(r.getString("VERSION_NAME"));
					choice.setGroup_id(r.getString("GROUP_ID"));
					choice.setGroup_name(r.getString("GROUP_NAME"));
					choice.setPriority(r.getString("PRIORITY_ORDER"));
					choice.setSpecial_quota(r.getString("special_quota"));
					choice.setEducation_quota(r.getString("education_quota"));					
					choiceList.add(choice);
				}
			} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;}
		
		return choiceList;
	}
	
	public String generateOtp(String IpEiin,IpAddressDTO ipAddress){

		Connection conn = ConnectionManager.getProcConnection();//ConnectionManager.getConnectionStatic();//ConnectionManager.getProcConnection();
		OracleCallableStatement stmt = null;
		String otp=null;
		try {
			
            stmt = (OracleCallableStatement) conn.prepareCall("{ call GENERATECOLLEGEOTP(?,?,?,?,?)  }");
            
            stmt.setString(1, IpEiin);      
            stmt.setString(2, ipAddress.getxForward());
            stmt.setString(3, ipAddress.getVia());
            stmt.setString(4, ipAddress.getRemoteAddress());
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            
            stmt.executeUpdate();
            otp=stmt.getString(5);
            
		} 
		catch (Exception e){e.printStackTrace();
		}
		finally{try{stmt.close();
		ConnectionManager.closeConnection(conn);
		} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		
        
        return otp;
                
	}
	
	public boolean changeOtpStatus(String IpEiin,String IpSecureCode,String IpStatus){

		boolean response=false;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql= " Update OTP_COLLEGE set USED_YN=? Where eiin=? and secure_code=?";
		if(IpStatus.equalsIgnoreCase("Y"))
			sql= " Update OTP_COLLEGE set USED_YN=?,used=used+1 Where eiin=? and secure_code=?";
		
		   PreparedStatement stmt = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, IpStatus);
			    stmt.setString(2, IpEiin);
			    stmt.setString(3, IpSecureCode);
				stmt.executeUpdate();
				response=true;
				
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		return response;

	}
	
	public boolean isValidOtp(String IpEiin,String IpSecureCode){

		int total=0;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql= " Select count(SECURE_CODE) TOTAL from OTP_COLLEGE  Where EIIN=? and Secure_Code=? ";//and used_YN='N'";

		   PreparedStatement stmt = null;
		   ResultSet r = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
			    stmt.setString(1, IpEiin);
			    stmt.setString(2, IpSecureCode);
				r = stmt.executeQuery();
				if (r.next())
				{

					total=r.getInt("TOTAL");
				}
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		if(total>0)
			return true;
		else
			return false;

	}
	
	public boolean changeMobileVerificationStatus(String IpEiin,String IpStatus){

		boolean response=false;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql= " Update MST_COLLEGE set ISVALID_MOBILE_YN=? Where EIIN=?";

		   PreparedStatement stmt = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, IpStatus);
			    stmt.setString(2, IpEiin);
			    stmt.executeUpdate();
				response=true;
				
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		return response;

	}
	
	public boolean changeMobileNo(String IpEiin,String IpMobile){

		boolean response=false;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql= " Update MST_COLLEGE set MOBILE_NO=? Where EIIN=?";

		   PreparedStatement stmt = null;
		   
			try
			{
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, IpMobile);
			    stmt.setString(2, IpEiin);
			    stmt.executeUpdate();
				response=true;
				
			} 
			catch (Exception e){e.printStackTrace();}
	 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
				{e.printStackTrace();}stmt = null;conn = null;}
		
		return response;

	}
	
	public String getMobileNo(String IpEiin){

		String response="";
		Connection conn = ConnectionManager.getWriteConnection();
		String sql= " select MOBILE_NO from MST_COLLEGE Where EIIN=?";
		PreparedStatement stmt = null;
		ResultSet r = null;   
		try
		{
			stmt = conn.prepareStatement(sql);
		    stmt.setString(1, IpEiin);
		    r = stmt.executeQuery();
			if (r.next())
			{

				response = r.getString("MOBILE_NO");
			}
			
		} 
		catch (Exception e){e.printStackTrace();}
 		finally{try{r.close();} catch (Exception e){}try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}
		
		return response;

	}
			
}
