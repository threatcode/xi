package com.cache.rmi;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import com.cache.dto.*;


	
public class Server implements Hello {
	
    public Server(String board_id) 
    {
    	getAllColleges(board_id);
    	System.out.println("Data Load Completed");
    }

    public synchronized Result sayHello(String prefix) 
    {
    	Result tmp = tmpHashMap.get(prefix);
//    	System.out.println(prefix);
//    	System.out.println(tmpHashMap.size());
    	return tmp;
    }
	
    public static void main(String args[]) {
//    	if (System.getSecurityManager() == null) 
//        System.setSecurityManager(new RMISecurityManager());
	try {
		System.out.println(args[0]);
		int port = 1099;
		if(args[0].equalsIgnoreCase("10"))port = 2010;
		if(args[0].equalsIgnoreCase("11"))port = 2011;
		if(args[0].equalsIgnoreCase("12"))port = 2012;
		if(args[0].equalsIgnoreCase("13"))port = 2013;
		if(args[0].equalsIgnoreCase("14"))port = 2014;
		if(args[0].equalsIgnoreCase("15"))port = 2015;
		if(args[0].equalsIgnoreCase("16"))port = 2016;
		if(args[0].equalsIgnoreCase("17"))port = 2017;
		if(args[0].equalsIgnoreCase("18"))port = 2018;
		if(args[0].equalsIgnoreCase("19"))port = 2019;
		if(args[0].equalsIgnoreCase("20"))port = 2020;
		
	    Server obj = new Server(args[0]);
	    Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

	    // Bind the remote object's stub in the registry
	    Registry registry = LocateRegistry.getRegistry("127.0.0.1",port);
	    try {
			registry.bind("Hello", stub);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		System.err.println("Server ready");
	} catch (Exception e) {
	    System.err.println("Server exception: " + e.toString());
	    e.printStackTrace();
	}
    }
    
    
    
//    public static HashMap<String, HashMap<String, Result>> ResultHashMap=new HashMap<String, HashMap<String, Result>>();	
	private static HashMap<String, Result> tmpHashMap=new HashMap<String, Result>();	
	public static void getAllColleges(String board_id)
	{
		Connection conn = getConnectionStatic();
		String sql = "select T1.APPLICATION_ID, T1.MERIT_TYPE, T1.MERIT_POSITTION , T2.SSC_ROLL_NO,T2.SSC_BOARD_ID,T2.SSC_PASSING_YEAR,T2.SSC_REG," +
				"T3.EIIN,T3.COLLEGE_NAME,T1.SHIFT_ID,T1.VERSION_ID,T4.GROUP_ID,T4.GROUP_NAME,t1.QUOTA_TYPE from BOARD_RESULT_MERIT t1,application_info t2,mst_college t3, " +
				"mst_group t4 where T1.APPLICATION_ID=T2.APPLICATION_ID and T1.EIIN=T3.EIIN and T1.GROUP_ID=T4.GROUP_ID and SSC_BOARD_ID=?" +
//				" and T1.APPLICATION_ID in ('1474769','1131653')" +
				" order by T2.SSC_BOARD_ID,T2.SSC_PASSING_YEAR,T2.SSC_ROLL_NO";
		sql = "select * from (" +
				" select t2.NAME,T1.APPLICATION_ID, T1.MERIT_TYPE, T1.MERIT_POSITTION , T2.SSC_ROLL_NO,T2.SSC_BOARD_ID,T2.SSC_PASSING_YEAR,T2.SSC_REG," +
				" T3.EIIN,T3.COLLEGE_NAME,T1.SHIFT_ID,T1.VERSION_ID,T4.GROUP_ID,T4.GROUP_NAME,t1.QUOTA_TYPE,'mtable' tab from BOARD_RESULT_MERIT t1,application_info t2,mst_college t3," +
				" mst_group t4 where T1.APPLICATION_ID=T2.APPLICATION_ID and T1.EIIN=T3.EIIN and T1.GROUP_ID=T4.GROUP_ID and SSC_BOARD_ID=?" +
				" union" +
				" select t2.NAME,T1.APPLICATION_ID, T1.MERIT_TYPE, T1.MERIT_POSITTION , T2.SSC_ROLL_NO,T2.SSC_BOARD_ID,T2.SSC_PASSING_YEAR,T2.SSC_REG," +
				" T3.EIIN,T3.COLLEGE_NAME,T1.SHIFT_ID,T1.VERSION_ID,T4.GROUP_ID,T4.GROUP_NAME," +
				" decode(FQ,'Y','Freedom Fighter, ')||decode(EQ,'Y','Education, ')||decode(PQ,'Y','Expatriate, ')||decode(BQ,'Y','BKSP, ')" +
				" ||decode(SQ,'Y','Special, ')||decode(DQ,'Y','Division or District, ')||decode(OQ,'Y','Own')  QUOTA_TYPE,'wtable' tab from BOARD_RESULT_Waiting t1,application_info t2,mst_college t3," +
				" mst_group t4 where T1.APPLICATION_ID=T2.APPLICATION_ID and T1.EIIN=T3.EIIN and T1.GROUP_ID=T4.GROUP_ID and SSC_BOARD_ID=?" +
				" ) order by APPLICATION_ID,tab,eiin";
		PreparedStatement stmt = null;
		ResultSet r = null;
			
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, board_id);
			stmt.setString(2, board_id);
			r = stmt.executeQuery();
			String prefix = "";
			
			Result tmpResult = new Result();
			while (r.next())
			{
				if(prefix.equalsIgnoreCase(""))
				{
					prefix = r.getString("SSC_ROLL_NO")+r.getString("SSC_BOARD_ID")+r.getString("SSC_PASSING_YEAR")+r.getString("SSC_REG");
//					System.out.println("1");
				}
				if(!prefix.equalsIgnoreCase(r.getString("SSC_ROLL_NO")+r.getString("SSC_BOARD_ID")+r.getString("SSC_PASSING_YEAR")+r.getString("SSC_REG")))
				{
//					System.out.println("2");
					tmpHashMap.put(prefix, tmpResult);
					tmpResult = new Result();
					prefix = r.getString("SSC_ROLL_NO")+r.getString("SSC_BOARD_ID")+r.getString("SSC_PASSING_YEAR")+r.getString("SSC_REG");
				}
//				else if(!(r.getString("SSC_ROLL_NO")+r.getString("SSC_BOARD_ID")+r.getString("SSC_PASSING_YEAR")+r.getString("SSC_REG")).
//						equalsIgnoreCase(prefix))
//				{
//					prefix = r.getString("SSC_ROLL_NO")+r.getString("SSC_BOARD_ID")+r.getString("SSC_PASSING_YEAR")+r.getString("SSC_REG");
//				}
				String tmpQuota = "";
				if(r.getString("quota_type") == null)
					tmpQuota=".";
				else if(r.getString("quota_type").trim().equalsIgnoreCase("GENERAL"))
					tmpQuota = "Merit" ;
				else
					tmpQuota = r.getString("quota_type");
				
				
				tmpResult.setAPPLICATION_ID(r.getString("APPLICATION_ID"));
				tmpResult.setName(r.getString("NAME"));
				tmpResult.setSSC_ROLL(r.getString("SSC_ROLL_NO"));
				tmpResult.setSSC_BOARD(r.getString("SSC_BOARD_ID"));
				tmpResult.setSSC_PASSING_YEAR(r.getString("SSC_PASSING_YEAR"));
				tmpResult.setSSC_REG(r.getString("SSC_REG"));
				tmpResult.setQUOTA_TYPE(tmpResult.getQUOTA_TYPE()+tmpQuota+"##");
				tmpResult.setMerit_type(tmpResult.getMerit_type()+r.getString("MERIT_TYPE")+"##");
				tmpResult.setMerit_position(tmpResult.getMerit_position()+r.getString("MERIT_POSITTION")+"##");
				tmpResult.setEIIN(tmpResult.getEIIN()+r.getString("EIIN")+"##");
				tmpResult.setCOLLEGE_NAME(tmpResult.getCOLLEGE_NAME()+r.getString("COLLEGE_NAME")+"##");
				tmpResult.setSHIFT_ID(tmpResult.getSHIFT_ID()+r.getString("SHIFT_ID")+"##");
				tmpResult.setVERSION_ID(tmpResult.getVERSION_ID()+r.getString("VERSION_ID")+"##");
				tmpResult.setGROUP_ID(tmpResult.getGROUP_ID()+r.getString("GROUP_ID")+"##");
				tmpResult.setGROUP_NAME(tmpResult.getGROUP_NAME()+r.getString("GROUP_NAME")+"##");
				tmpResult.setTables(tmpResult.getTables()+r.getString("TAB")+"##");
				
//				tmpResult.getMerit_type().add(r.getString("MERIT_TYPE"));
//				tmpResult.getMerit_position().add(r.getString("MERIT_POSITTION"));
//				tmpResult.getEIIN().add(r.getString("EIIN"));
//				tmpResult.getCOLLEGE_NAME().add(r.getString("COLLEGE_NAME"));
//				tmpResult.getSHIFT_ID().add(r.getString("SHIFT_ID"));
//				tmpResult.getVERSION_ID().add(r.getString("VERSION_ID"));
//				tmpResult.getGROUP_ID().add(r.getString("GROUP_ID"));
//				tmpResult.getGROUP_NAME().add(r.getString("GROUP_NAME"));
				
			}
//			System.out.println("3");
			tmpHashMap.put(prefix, tmpResult);
			System.out.println(tmpHashMap.size());
		} 
		catch (Exception e){e.printStackTrace();}
		finally{try{stmt.close();} catch (Exception e)
		{e.printStackTrace();}stmt = null;
		try{conn.close();}catch(Exception e){e.printStackTrace();}
		conn = null;}
		
		System.out.println("Data Fetch Complete");
	}
	
//	private static String username = "BOARD_DB";
//	private static String password = "board2015";
//	private static String url = "jdbc:oracle:thin:@123.49.42.205:1521:nuadmission";
//    
// 
//
//    private static Connection getConnectionStatic() {
//    	Connection connection = null;
//        try {
//        	Class.forName("oracle.jdbc.driver.OracleDriver");
//            if(connection==null || connection.isClosed()) 
//            	connection = DriverManager.getConnection(url, username, password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
    
	private static String username = "BOARD_DB";
	private static String password = "board2015";
	private static String url = "jdbc:oracle:thin:@172.16.1.18:1535:db1";
    
 

    private static Connection getConnectionStatic() {
    	Connection connection = null;
        try {
        	//Class.forName("oracle.jdbc.driver.OracleDriver");
        	Class.forName("oracle.jdbc.OracleDriver");
            if(connection==null || connection.isClosed()) 
            	connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    
}