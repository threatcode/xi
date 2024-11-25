package edu.utils.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;

public class ConnectionManager {

	private static DataSource dsRead = null;
	private static DataSource dsWrite = null;
	private static DataSource dsProc = null;
	
	private static String username = "xiclass";
	private static String password = "xiclass369#";
	private static String url = "jdbc:oracle:thin:@118.67.215.239:1431:xidb5";
	
	static MemcachedClient memClient = null;
	
	private ConnectionManager() {
	}

	public static Connection getReadConnection() {

		Connection conn = null;
		try {
			if (dsRead == null) {
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext
						.lookup("java:/comp/env");
				dsRead = (DataSource) envContext.lookup("jdbc/BoardDbReadConn");
			}
			conn = dsRead.getConnection();
		} catch (Exception e) {
			try {
				//Thread.currentThread();
				Thread.sleep(2000);
				conn = dsRead.getConnection();
			} catch (Exception ex) {
				try {
					//Thread.currentThread();
					Thread.sleep(2000);
					conn = dsRead.getConnection();
				} catch (Exception ex1) {
					System.out.println("Error in Get Database Read Connection Third Time.");
					ex1.printStackTrace();
				}
				System.out.println("Error in Get Database Read Connection Second Time.");
			}
			System.out.println("Error in Get Database Read Connection First Time.");
			e.printStackTrace();
		}

		return conn;
	}

	public static Connection getWriteConnection() {

		Connection conn = null;
		try {
			if (dsWrite == null) {
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext
						.lookup("java:/comp/env");
				dsWrite = (DataSource) envContext.lookup("jdbc/BoardDbWriteConn");
			}
			conn = dsWrite.getConnection();
		} catch (Exception e) {
			try {
				//Thread.currentThread();
				Thread.sleep(2000);
				conn = dsWrite.getConnection();
			} catch (Exception ex) {
				try {
					//Thread.currentThread();
					Thread.sleep(2000);
					conn = dsWrite.getConnection();
				} catch (Exception ex1) {
					System.out.println("Error in Get Database Write Connection Third Time.");
				}
				System.out.println("Error in Get Database Write Connection Second Time.");
			}
			System.out.println("Error in Get Database Write Connection First Time.");
		}

		return conn;
	}

	public static Connection getProcConnection() {

		Connection conn = null;
		try {
			if (dsProc == null) {
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext
						.lookup("java:/comp/env");
				dsProc = (DataSource) envContext.lookup("jdbc/BoardProcConn");
			}
			conn = dsProc.getConnection();
		} catch (Exception e) {
			try {
				//Thread.currentThread();
				Thread.sleep(2000);
				conn = dsProc.getConnection();
			} catch (Exception ex) {
				try {
					//Thread.currentThread();
					Thread.sleep(2000);
					conn = dsProc.getConnection();
				} catch (Exception ex1) {
					System.out.println("Error in Get Database Proc Connection Third Time.");
				}
				System.out.println("Error in Get Database Proc Connection Second Time.");
			}
			System.out.println("Error in Get Database Proc Connection First Time.");
		}

		return conn;
	}
	
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			connection = null;
		}
	}

	public static void main(String[] args) {
		System.out.println("abc");
	}
	
	

    static Connection connection = null;
    public static Connection getConnectionStatic() {
        try {
        	//Class.forName("oracle.jdbc.driver.OracleDriver");
        	Class.forName("oracle.jdbc.OracleDriver");
        	String url = "jdbc:oracle:thin:@"+getStaticIP()+":"+getStaticport()+":"+getStaticdb()+"";
            if(connection==null || connection.isClosed()) 
            	connection = DriverManager.getConnection(url, getStaticuser(), getStaticpass());
        } catch (Exception e) {
            e.printStackTrace();
            closeStatic();
        }
        return connection;
//    	return getReadConnection();
    }
    public static void closeStatic() {
    	connection = null;
    }
    
    static Connection connection1 = null;
    public static Connection getConnectionStatic1() {
        try {
        	//Class.forName("oracle.jdbc.driver.OracleDriver");
        	Class.forName("oracle.jdbc.OracleDriver");
            if(connection1==null || connection1.isClosed()) 
            	connection1 = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection1;
//    	return getReadConnection();
    }
    public static void closeStatic1() {
    	connection1 = null;
    }
    
    
    static String staticport="";
    public static String getStaticport() {
    	if(staticport.equalsIgnoreCase(""))
    	{
    	    try {
    	      Context ctx = new InitialContext();
    	      Context envContext = (Context) ctx.lookup("java:/comp/env");
    	      staticport = (String) envContext.lookup("staticport");
    	    }
    	    catch (Exception e) {
    	    	e.printStackTrace();
    	    }
    	}
    	return staticport;
    }
    static String staticIP="";
    public static String getStaticIP() {
    	if(staticIP.equalsIgnoreCase(""))
    	{
    	    try {
    	      Context ctx = new InitialContext();
    	      Context envContext = (Context) ctx.lookup("java:/comp/env");
    	      staticIP = (String) envContext.lookup("staticIP");
    	    }
    	    catch (Exception e) {
    	    	e.printStackTrace();
    	    }
    	}
    	return staticIP;
    }
    static String staticdb="";
    public static String getStaticdb() {
    	if(staticdb.equalsIgnoreCase(""))
    	{
    	    try {
    	      Context ctx = new InitialContext();
    	      Context envContext = (Context) ctx.lookup("java:/comp/env");
    	      staticdb = (String) envContext.lookup("staticdb");
    	    }
    	    catch (Exception e) {
    	    	e.printStackTrace();
    	    }
    	}
    	return staticdb;
    }
    static String staticuser="";
    public static String getStaticuser() {
    	if(staticuser.equalsIgnoreCase(""))
    	{
    	    try {
    	      Context ctx = new InitialContext();
    	      Context envContext = (Context) ctx.lookup("java:/comp/env");
    	      staticuser = (String) envContext.lookup("staticuser");
    	    }
    	    catch (Exception e) {
    	    	e.printStackTrace();
    	    }
    	}
    	return staticuser;
    }    
    static String staticpass="";
    public static String getStaticpass() {
    	if(staticpass.equalsIgnoreCase(""))
    	{
    	    try {
    	      Context ctx = new InitialContext();
    	      Context envContext = (Context) ctx.lookup("java:/comp/env");
    	      staticpass = (String) envContext.lookup("staticpass");
    	    }
    	    catch (Exception e) {
    	    	e.printStackTrace();
    	    }
    	}
    	return staticpass;
    }    
    
    public static Connection getConnection() {
      try {
          if(connection==null || connection.isClosed()) 
        	  //Class.forName("oracle.jdbc.driver.OracleDriver");
          	Class.forName("oracle.jdbc.OracleDriver");
          		connection = DriverManager.getConnection(url, username, password);
      } catch (Exception e) {
          e.printStackTrace();
      }
      return connection;
  }
	public static MemcachedClient createMemcachedClient() {
        try {
        	if(memClient == null)
        		memClient = new MemcachedClient(new BinaryConnectionFactory(),AddrUtil.getAddresses("localhost:11211"));                  //new InetSocketAddress("localhost", 11211));
//        		memClient = new MemcachedClient(new BinaryConnectionFactory(),AddrUtil.getAddresses("cachecluster1.lddis8.cfg.usw2.cache.amazonaws.com:11211"));                  //new InetSocketAddress("localhost", 11211));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return memClient;
    }
}
