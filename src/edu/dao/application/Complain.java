package edu.dao.application;

import java.sql.Connection;
import java.sql.PreparedStatement;

import edu.utils.connection.ConnectionManager;

public class Complain {
	
	public static synchronized boolean wrongGroup(String ssc_roll, String board_id, String ssc_year, String eiin
			, String wrongExistingGroups, String wrongNonExistingGroups)
	{
		boolean tmp = true;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql = "insert into complain (ssc_roll, board_id, ssc_year, eiin, problemType, problemTypeSQN, wrongExistingGroup, wrongNonExistingGroup)" +
				" values (?,?,?,?,?,?,?,?) ";
		String[] wrongExistingGroup = wrongExistingGroups.split("###");
		String[] wrongNonExistingGroup = wrongNonExistingGroups.split("###");
		PreparedStatement stmt = null;
		int i = 0;
		int counter = 1;
		int updateCount = 0;
		try{
			stmt = conn.prepareStatement(sql);
			if(eiin!=null && !(eiin.equalsIgnoreCase("")))
			{
				for(i=0;i<wrongExistingGroup.length;i++)
				{
					if(wrongExistingGroup[i]!=null && !(wrongExistingGroup[i].equalsIgnoreCase("")))
					{
						stmt.clearParameters();
						int parameterIndex = 1;
						stmt.setString(parameterIndex++, ssc_roll);
						stmt.setInt(parameterIndex++, Integer.parseInt(board_id));
						stmt.setInt(parameterIndex++, Integer.parseInt(ssc_year));
						stmt.setString(parameterIndex++, eiin);
						stmt.setString(parameterIndex++, "wrongGroup");
						stmt.setInt(parameterIndex++, counter);
						stmt.setString(parameterIndex++, wrongExistingGroup[i]);
						stmt.setString(parameterIndex++, "");
						stmt.executeUpdate();
						updateCount++;
						counter++;
					}
				}
				for(i=0;i<wrongNonExistingGroup.length;i++)
				{
					if(wrongNonExistingGroup[i]!=null && !(wrongNonExistingGroup[i].equalsIgnoreCase("")))
					{
						stmt.clearParameters();
						int parameterIndex = 1;
						stmt.setString(parameterIndex++, ssc_roll);
						stmt.setInt(parameterIndex++, Integer.parseInt(board_id));
						stmt.setInt(parameterIndex++, Integer.parseInt(ssc_year));
						stmt.setString(parameterIndex++, eiin);
						stmt.setString(parameterIndex++, "wrongGroup");
						stmt.setInt(parameterIndex++, counter);
						stmt.setString(parameterIndex++, "");
						stmt.setString(parameterIndex++, wrongNonExistingGroup[i]);
						stmt.executeUpdate();
						updateCount++;
						counter++;
					}
				}
			}
			if(updateCount==0)
				tmp=false;
		}catch (Exception e){
			e.printStackTrace();
			tmp=false;
		}
		finally{
			try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e){e.printStackTrace();}
			stmt = null;conn = null;
			
		}

		return tmp;
	}

	public static synchronized boolean wrongVersion(String ssc_roll, String board_id, String ssc_year, String eiin, String Group, String wrongExistingVersions, String wrongNonExistingVersions)
	{
		boolean tmp = true;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql = "insert into complain (ssc_roll, board_id, ssc_year, eiin, Groups, problemType, problemTypeSQN,  wrongExistingVersion, wrongNonExistingVersion)" +
				" values (?,?,?,?,?,?,?,?,?) ";
		String[] wrongExistingVersion = wrongExistingVersions.split("###");
		String[] wrongNonExistingVersion = wrongNonExistingVersions.split("###");
		PreparedStatement stmt = null;
		int i = 0;
		int counter = 1;
		int updateCount = 0;
		try{
			stmt = conn.prepareStatement(sql);
			if(eiin!=null && !(eiin.equalsIgnoreCase("")) && Group!=null && !(Group.equalsIgnoreCase("")))
			{
				for(i=0;i<wrongExistingVersion.length;i++)
				{
					if(wrongExistingVersion[i]!=null && !(wrongExistingVersion[i].equalsIgnoreCase("")))
					{
						stmt.clearParameters();
						int parameterIndex = 1;
						stmt.setString(parameterIndex++, ssc_roll);
						stmt.setInt(parameterIndex++, Integer.parseInt(board_id));
						stmt.setInt(parameterIndex++, Integer.parseInt(ssc_year));
						stmt.setString(parameterIndex++, eiin);
						stmt.setString(parameterIndex++, Group);
						stmt.setString(parameterIndex++, "wrongVersion");
						stmt.setInt(parameterIndex++, counter);
						stmt.setString(parameterIndex++, wrongExistingVersion[i]);
						stmt.setString(parameterIndex++, "");
						stmt.executeUpdate();
						updateCount++;
						counter++;
					}
				}
				for(i=0;i<wrongNonExistingVersion.length;i++)
				{
					if(wrongNonExistingVersion[i]!=null && !(wrongNonExistingVersion[i].equalsIgnoreCase("")))
					{
						stmt.clearParameters();
						int parameterIndex = 1;
						stmt.setString(parameterIndex++, ssc_roll);
						stmt.setInt(parameterIndex++, Integer.parseInt(board_id));
						stmt.setInt(parameterIndex++, Integer.parseInt(ssc_year));
						stmt.setString(parameterIndex++, eiin);
						stmt.setString(parameterIndex++, Group);
						stmt.setString(parameterIndex++, "wrongVersion");
						stmt.setInt(parameterIndex++, counter);
						stmt.setString(parameterIndex++, "");
						stmt.setString(parameterIndex++, wrongNonExistingVersion[i]);
						stmt.executeUpdate();
						updateCount++;
						counter++;
					}
				}
			}
			if(updateCount==0)
				tmp=false;
		}catch (Exception e){
			e.printStackTrace();
			tmp=false;
		}
		finally{
			try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e){e.printStackTrace();}
			stmt = null;conn = null;
			
		}

		return tmp;
	}	
	
	public static synchronized boolean wrongSeatCount(String ssc_roll, String board_id, String ssc_year, String eiin
			, String shift, String Version, String Group, String correctSeatCount)
	{
		boolean tmp = true;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql = "insert into complain (ssc_roll, board_id, ssc_year, eiin, problemType, problemTypeSQN, shift, Version, Groups, correctSeatCount)" +
				" values (?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement stmt = null;
		int i = 0;
		try{
			if(eiin!=null && !(eiin.equalsIgnoreCase("")) && shift!=null && !(shift.equalsIgnoreCase("")) && Version!=null && !(Version.equalsIgnoreCase("")) && Group!=null && !(Group.equalsIgnoreCase("")) && correctSeatCount!=null && !(correctSeatCount.equalsIgnoreCase("")))
			{
				stmt = conn.prepareStatement(sql);
				int parameterIndex = 1;
				stmt.setString(parameterIndex++, ssc_roll);
				stmt.setInt(parameterIndex++, Integer.parseInt(board_id));
				stmt.setInt(parameterIndex++, Integer.parseInt(ssc_year));
				stmt.setString(parameterIndex++, eiin);
				stmt.setString(parameterIndex++, "wrongSeatCount");
				stmt.setInt(parameterIndex++, 1);
				stmt.setString(parameterIndex++, shift);
				stmt.setString(parameterIndex++, Version);
				stmt.setString(parameterIndex++, Group);
				stmt.setString(parameterIndex++, correctSeatCount);
				stmt.executeUpdate();
			}
			else
				tmp=false;
		}catch (Exception e){
			e.printStackTrace();
			tmp=false;
		}
		finally{
			try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e){e.printStackTrace();}
			stmt = null;conn = null;
			
		}

		return tmp;
	}
	
	
	public static synchronized boolean wrongGender(String ssc_roll, String board_id, String ssc_year, String eiin
			, String shift, String Version, String Group, String correctGender)
	{
		boolean tmp = true;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql = "insert into complain (ssc_roll, board_id, ssc_year, eiin, problemType, problemTypeSQN, shift, Version, Groups, correctGender)" +
				" values (?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement stmt = null;
		int i = 0;
		try{
			if(eiin!=null && !(eiin.equalsIgnoreCase("")) && shift!=null && !(shift.equalsIgnoreCase("")) && Version!=null && !(Version.equalsIgnoreCase("")) && Group!=null && !(Group.equalsIgnoreCase("")) && correctGender!=null && !(correctGender.equalsIgnoreCase("")))
			{
				stmt = conn.prepareStatement(sql);
				int parameterIndex = 1;
				stmt.setString(parameterIndex++, ssc_roll);
				stmt.setInt(parameterIndex++, Integer.parseInt(board_id));
				stmt.setInt(parameterIndex++, Integer.parseInt(ssc_year));
				stmt.setString(parameterIndex++, eiin);
				stmt.setString(parameterIndex++, "wrongGender");
				stmt.setInt(parameterIndex++, 1);
				stmt.setString(parameterIndex++, shift);
				stmt.setString(parameterIndex++, Version);
				stmt.setString(parameterIndex++, Group);
				stmt.setString(parameterIndex++, correctGender);
				stmt.executeUpdate();
			}
			else
				tmp=false;
			
		}catch (Exception e){
			e.printStackTrace();
			tmp=false;
		}
		finally{
			try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e){e.printStackTrace();}
			stmt = null;conn = null;
			
		}

		return tmp;
	}

	public static synchronized boolean complain_ap(String ssc_roll, String board_id, String ssc_year, String isPreferError, String preferErrorDetails,
			String isMobileError, String mobileErrorDetails, String isGenderError, String isOtherError, String otherErrorDetails, String currentMobileNumber)
	{
		boolean tmp = true;
		Connection conn = ConnectionManager.getWriteConnection();
		String sql = "insert into COMPLAIN_AP (SSC_ROLL, BOARD_ID, SSC_YEAR, isPreferError, preferErrorDetails, isMobileError," +
				"  mobileErrorDetails, isGenderError, isOtherError, otherErrorDetails,MOBILENUMBER)" +
				" values (?,?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.clearParameters();
			int parameterIndex = 1;
			stmt.setString(parameterIndex++, ssc_roll);
			stmt.setInt(parameterIndex++, Integer.parseInt(board_id));
			stmt.setInt(parameterIndex++, Integer.parseInt(ssc_year));
			stmt.setString(parameterIndex++, isPreferError);
			stmt.setString(parameterIndex++, preferErrorDetails);
			stmt.setString(parameterIndex++, isMobileError);
			stmt.setString(parameterIndex++, mobileErrorDetails);
			stmt.setString(parameterIndex++, isGenderError);
			stmt.setString(parameterIndex++, isOtherError);
			stmt.setString(parameterIndex++, otherErrorDetails);
			stmt.setString(parameterIndex++, currentMobileNumber);
			//System.out.println(sql);
			if(stmt.executeUpdate()>0)
				tmp = true;

		}catch (Exception e){
			e.printStackTrace();
			tmp=false;
		}
		finally{
			try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e){e.printStackTrace();}
			stmt = null;conn = null;
			
		}

		return tmp;
	}

	
}
