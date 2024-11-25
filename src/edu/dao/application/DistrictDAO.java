package edu.dao.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import edu.action.board.CollegeSVGDTO;
import edu.dto.application.DistrictDTO;
import edu.dto.application.ThanaDTO;
import edu.dto.college.CollegeDTO;
import edu.utils.connection.ConnectionManager;

public class DistrictDAO {

	public static ArrayList<DistrictDTO> getDistrictlist()
	{

		Connection conn = ConnectionManager.getReadConnection();
//		Connection conn = ConnectionManager.getConnectionStatic();
		String sql = "select dist_name, dist_id from MST_DISTRICT order by dist_name";
		Statement stmt = null;
		ResultSet r = null;
		DistrictDTO district = null;
		ArrayList<DistrictDTO> districtlist = new ArrayList<DistrictDTO>();
		try
		{
			stmt = conn.prepareStatement(sql);			
			r = stmt.executeQuery(sql);
			while (r.next())
			{
				district = new DistrictDTO();
				district.setDist_id(r.getString("dist_id"));
				district.setDist_name(r.getString("dist_name"));
				districtlist.add(district);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		

	
		return districtlist;
	}
	
	public static HashMap<String,ArrayList<ThanaDTO>> getThanalist()
	{

		Connection conn = ConnectionManager.getReadConnection();
//		Connection conn = ConnectionManager.getConnectionStatic();
		String sql = "select dist_id, thana_id, thana_name from MST_THANA order by dist_id, thana_name";
		Statement stmt = null;
		ResultSet r = null;
		ThanaDTO thana = null;
		HashMap<String,ArrayList<ThanaDTO>> thanaHashMap=new HashMap<String, ArrayList<ThanaDTO>>();
		ArrayList<ThanaDTO> thanaList = new ArrayList<ThanaDTO>();
		String compositKey="";
		try
		{
			stmt = conn.prepareStatement(sql);			
			r = stmt.executeQuery(sql);
			while (r.next())
			{
				if(!compositKey.equalsIgnoreCase(r.getString("dist_id")))
				{
					if(!compositKey.equalsIgnoreCase(""))
					{
						thanaHashMap.put(compositKey, thanaList);
						thanaList = new ArrayList<ThanaDTO>();
					}
				}
				compositKey=r.getString("dist_id");
				thana = new ThanaDTO();
				thana.setDist_id(r.getString("dist_id"));
				thana.setThana_id(r.getString("thana_id"));
				thana.setThana_name(r.getString("thana_name"));
				thanaList.add(thana);
			}
			thanaHashMap.put(compositKey, thanaList);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
		{e.printStackTrace();}stmt = null;conn = null;} 		

	
		return thanaHashMap;
	}
	
	
	
	
	
	
	
}
