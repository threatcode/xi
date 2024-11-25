package edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.action.board.CollegeSVGDTO;
import edu.dto.GroupDTO;
import edu.utils.connection.ConnectionManager;

public class GroupDAO {

	public ArrayList<GroupDTO> getAllGroup(String type){
		GroupDTO group = null;
		ArrayList<GroupDTO> groupList = null;
		Connection conn = ConnectionManager.getReadConnection();
		String sql="";
		if(type.equalsIgnoreCase("SSC"))
			sql = "Select * from Mst_Group Order by Group_Id";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			r = stmt.executeQuery();
			
			groupList = new ArrayList<GroupDTO>();
			while(r.next())
			{
				group = new GroupDTO();
				group.setGroup_id(r.getString("GROUP_ID"));
				group.setGroup_name(r.getString("GROUP_NAME"));
				groupList.add(group);
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
		
		return groupList;
	}
	
	
	public ArrayList<GroupDTO> getGroupMapList(){
		
		GroupDTO group = null;
		ArrayList<GroupDTO> groupList = null;
		Connection conn = ConnectionManager.getReadConnection();
		String sql = "Select * from MST_GROUP_MAPPING Order by SSC_GROUP";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			
			groupList = new ArrayList<GroupDTO>();
			while(r.next())
			{
				group = new GroupDTO();
				group.setSsc_group_id(r.getString("SSC_GROUP"));
				group.setHsc_group_id(r.getString("HSC_GROUP"));
				groupList.add(group);
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
		
		return groupList;
	}
	
	public static Set<String> getGroupMapSet(){
		
		Set<String> groupMapSet = new HashSet<String>();
		Connection conn = ConnectionManager.getReadConnection();
//		Connection conn = ConnectionManager.getConnectionStatic();
		String sql = "Select * from MST_GROUP_MAPPING Order by SSC_GROUP";
		//String sql = "Select * from MST_GROUP_MAPPING where ssc_group >700 Order by SSC_GROUP";
		
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();			
			while(r.next())
			{
				groupMapSet.add(r.getString("SSC_GROUP")+"|"+r.getString("HSC_GROUP"));
			}
		} 
		catch (Exception e){e.printStackTrace();}
		finally{
			try{
				stmt.close();
//				conn.close();
				} 
			catch (Exception e){
					e.printStackTrace();
				}
			stmt = null;
			conn = null;
		}
		
		return groupMapSet;
	}
	
	
	public static HashMap<String,ArrayList<CollegeSVGDTO>> getCSVGlist()
	{
		Connection conn = ConnectionManager.getReadConnection();
		String sql ="SELECT nvl(aa.board_id,0) board_id,DD.DIST_NAME,EE.THANA_NAME, aa.eiin,replace(aa.COLLEGE_NAME,',',' ') COLLEGE_NAME,  " +
				" decode(SHIFT_ID,1,'Morning',2,'Day',3,'Evening') SHIFT, decode(VERSION_ID,1,'Bangla','English') VERSION, GROUP_NAME , " +
				" DECODE(GENDER,'C','Co-Education','M','Male','F','Female') GENDER, " +
				" MINIMUM_GPA  ,OWN_GPA ,TOTAL_SEAT ,AVAILABLE_SEAT  " +
				" from mst_college aa, mst_college_groups bb, mst_group cc, " +
				" mst_district dd, mst_thana ee " +
				" where AA.EIIN=bb.eiin " +
				" and BB.GROUP_ID=CC.GROUP_ID " +
				" and AA.DIST_ID=DD.DIST_ID " +
				" and AA.THANA_ID=EE.THANA_ID " +
				" order by aa.board_id,DD.DIST_NAME,EE.THANA_NAME, aa.COLLEGE_NAME " ;
		Statement stmt = null;
		ResultSet r = null;
		CollegeSVGDTO svg = null;
		HashMap<String,ArrayList<CollegeSVGDTO>> svgHashMap=new HashMap<String, ArrayList<CollegeSVGDTO>>();
		ArrayList<CollegeSVGDTO> svgList = new ArrayList<CollegeSVGDTO>();
		String compositKey="";
		
		try
		{
			stmt = conn.prepareStatement(sql);			
			r = stmt.executeQuery(sql);
			compositKey="";
			
			while (r.next())
			{
				if(!compositKey.equalsIgnoreCase(r.getString("board_id")))
				{
					if(!compositKey.equalsIgnoreCase(""))
					{
						svgHashMap.put(compositKey, svgList);
						svgList = new ArrayList<CollegeSVGDTO>();
					}
				}
				compositKey=r.getString("board_id");
				svg = new CollegeSVGDTO();
				svg.setBoardId(r.getString("board_id"));
				svg.setDist(r.getString("DIST_NAME"));
				svg.setThana(r.getString("THANA_NAME"));
				svg.setEiin(r.getString("eiin"));
				svg.setCollegeName(r.getString("COLLEGE_NAME"));
				svg.setShiftName(r.getString("SHIFT"));
				svg.setVersionName(r.getString("VERSION"));
				svg.setGroupName(r.getString("GROUP_NAME"));
				svg.setGender(r.getString("GENDER"));
				svg.setMinGpa(r.getString("MINIMUM_GPA"));
				svg.setOwnGpa(r.getString("OWN_GPA"));
				svg.setTotalSeat(r.getString("TOTAL_SEAT"));
				svg.setAvlSeat(r.getString("AVAILABLE_SEAT"));				
								
				svgList.add(svg);
			}
			svgHashMap.put(compositKey, svgList);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		

	
		return svgHashMap;
		
		
		
	}
	
	
	
	
	
	
}
