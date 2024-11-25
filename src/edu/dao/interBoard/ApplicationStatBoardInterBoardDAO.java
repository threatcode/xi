package edu.dao.interBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.dto.interBoard.ApplicationStatBoardDTO;
import edu.dto.interBoard.CollegeInfoDTO;
import edu.dto.interBoard.DistrictinfoDTO;
import edu.utils.connection.ConnectionManager;
import edu.dto.interBoard.DashBoardBoardInterBoardDTO;



public class ApplicationStatBoardInterBoardDAO {
	

	public DashBoardBoardInterBoardDTO getInterBoardDashBoard(){
		   DashBoardBoardInterBoardDTO dashBoardDTO = null;

		
		   Connection conn = ConnectionManager.getReadConnection();
		
		  /* String sql="SELECT TOTAL_APPLICATION, " +
				   "       TOTAL_APPLICANT, " +
				   "       WEB_APPLICATION, " +
				   "       WEB_APPLICANT, " +
				   "       SMS_APPLICATION, " +
				   "       SMS_APPLICANT, " +
				   "       total_result, " +
				   "       reg_paid, " +
				   "       coll_confirm " +
				   "  FROM (SELECT COUNT (APPLICANT_ID) TOTAL_APPLICATION, " +
				   "               COUNT (DISTINCT APPLICANT_ID) TOTAL_APPLICANT " +
				   "          FROM APPLICATION_COLLEGES) t1, " +
				   "       (SELECT COUNT (APPLICANT_ID) WEB_APPLICATION, " +
				   "               COUNT (DISTINCT APPLICANT_ID) WEB_APPLICANT " +
				   "          FROM APPLICATION_COLLEGES " +
				   "         WHERE APP_TYPE = 'WEB') t2, " +
				   "       (SELECT COUNT (APPLICANT_ID) SMS_APPLICATION, " +
				   "               COUNT (DISTINCT APPLICANT_ID) SMS_APPLICANT " +
				   "          FROM APPLICATION_COLLEGES " +
				   "         WHERE APP_TYPE = 'SMS') t3, " +
				   "         (select count(APPLICANT_ID) total_result " +
				   "         from BOARD_RESULT) t4, " +
				   "          (select count(APPLICANT_ID) reg_paid " +
				   "         from BOARD_RESULT where IS_PAID='YES') t5, " +
				   "          (select count(APPLICANT_ID) coll_confirm " +
				   "         from BOARD_RESULT where COLLEGE_RECEIVE='College Received') t6 " ;*/
		   
		   
		String sql ="select * from  " +
				" (SELECT COUNT (APPLICANT_ID) TOTAL_APPLICATION,  " +
				"				                 COUNT (DISTINCT APPLICANT_ID) TOTAL_APPLICANT  " +
				"				             FROM APPLICATION_COLLEGES) t1,				              " +
				" (select count(*) totalPay  " +
				"				  from PAYMENT_DETAILS where APP_TYPE='WEB') t2,			              " +
				" (select count(*) teletalk " +
				"				  from PAYMENT_DETAILS where PAY_COMP='TT') t3,			   " +
				" (select count(*) sbl " +
				"				  from PAYMENT_DETAILS where PAY_COMP='SBL') t4,			   " +
				" (select count(*) bkash " +
				"				  from PAYMENT_DETAILS where PAY_COMP='BKash') t5,				   " +
				" (select count(*) nagad " +
				"				  from PAYMENT_DETAILS where PAY_COMP='NAGAD') t6,				   " +
				" (select count(*) surecash " +
				"				  from PAYMENT_DETAILS where PAY_COMP='SC') t7,				   " +
				" (select count(*) rocket " +
				"				  from PAYMENT_DETAILS where PAY_COMP='Rocket') t8 " ;


		   
		   
		/*String sql="SELECT  " +
				"       total_reg,  " +
				"       reg_paid,  " +
				"       coll_confirm  " +
				"  FROM    " +
				"         (select count(APPLICANT_ID) total_reg " +
				"         from BOARD_REGISTRATION) t4,  " +
				"          (select count(APPLICANT_ID) reg_paid  " +
				"         from BOARD_RESULT where IS_PAID='YES') t5,  " +
				"          (select count(APPLICANT_ID) coll_confirm  " +
				"         from BOARD_RESULT where COLLEGE_RECEIVE='College Received') t6  " ;*/
   
		   
		   
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
		    r = stmt.executeQuery();
			
			
			while(r.next())
			{
				dashBoardDTO = new DashBoardBoardInterBoardDTO();
				
				/*dashBoardDTO.setTotalApplicant(Integer.parseInt(r.getString("TOTAL_APPLICANT") == null ? "0" : r.getString("TOTAL_APPLICANT")));
				dashBoardDTO.setTotalApplication(Integer.parseInt(r.getString("TOTAL_APPLICATION") == null ? "0" : r.getString("TOTAL_APPLICATION")));
				dashBoardDTO.setWebApplicant(Integer.parseInt(r.getString("WEB_APPLICANT") == null ? "0" : r.getString("WEB_APPLICANT")));
				dashBoardDTO.setWebApplication(Integer.parseInt(r.getString("WEB_APPLICATION") == null ? "0" : r.getString("WEB_APPLICATION")));
				dashBoardDTO.setSmsApplicant(Integer.parseInt(r.getString("SMS_APPLICANT") == null ? "0" : r.getString("SMS_APPLICANT")));
				dashBoardDTO.setSmsApplication(Integer.parseInt(r.getString("SMS_APPLICATION") == null ? "0" : r.getString("SMS_APPLICATION")));*/
				
				/*dashBoardDTO.setResult(Integer.parseInt(r.getString("total_reg") == null ? "0" : r.getString("total_reg")));
				dashBoardDTO.setRegpaid(Integer.parseInt(r.getString("reg_paid") == null ? "0" : r.getString("reg_paid")));
				dashBoardDTO.setCollconfirm(Integer.parseInt(r.getString("coll_confirm") == null ? "0" : r.getString("coll_confirm")));*/
				
				dashBoardDTO.setTotalApplicant(Integer.parseInt(r.getString("TOTAL_APPLICANT") == null ? "0" : r.getString("TOTAL_APPLICANT")));
				dashBoardDTO.setTotalApplication(Integer.parseInt(r.getString("TOTAL_APPLICATION") == null ? "0" : r.getString("TOTAL_APPLICATION")));
				
				
				dashBoardDTO.setTotalpayment(Integer.parseInt(r.getString("totalPay")== null ? "0": r.getString("totalPay")));
				
				dashBoardDTO.setTeletalk(Integer.parseInt(r.getString("teletalk")== null ? "0": r.getString("teletalk")));
				dashBoardDTO.setSbl(Integer.parseInt(r.getString("sbl")== null ? "0": r.getString("sbl")));
				dashBoardDTO.setBkash(Integer.parseInt(r.getString("bkash")== null ? "0": r.getString("bkash")));
				dashBoardDTO.setNagad(Integer.parseInt(r.getString("nagad")== null ? "0": r.getString("nagad")));
				dashBoardDTO.setSurecash(Integer.parseInt(r.getString("surecash")== null ? "0": r.getString("surecash")));
				dashBoardDTO.setRocket(Integer.parseInt(r.getString("rocket")== null ? "0": r.getString("rocket")));
				
				
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
		
		return dashBoardDTO;
	}
	
	
	public DashBoardBoardInterBoardDTO getInterBoardDashBoardPayment(){
		   DashBoardBoardInterBoardDTO dashBoardDTO = null;

		   Connection conn = ConnectionManager.getReadConnection();
		
		  String sql=  "select totalweb, totalsms, totalTTweb, totalSC,totalBkash,totalGP " +
				  " from  " +
				  " (select count(*) totalweb " +
				  " from PAYMENT_DETAILS where APP_TYPE='WEB') t1,  " +
				  " ( select count(*) totalsms " +
				  " from PAYMENT_DETAILS where APP_TYPE='SMS') t2,  " +
				  " (select count(*) totalTTweb " +
				  " from PAYMENT_DETAILS where PAY_COMP='TT' and APP_TYPE='WEB') t3,  " +
				  " (select count(*) totalSC " +
				  " from PAYMENT_DETAILS where PAY_COMP='SC') t4,  " +
				  " (select count(*) totalBkash " +
				  " from PAYMENT_DETAILS where PAY_COMP='BKash') t5,  " +
				  " (select count(*) totalGP " +
				  " from PAYMENT_DETAILS where PAY_COMP='GP') t6 " ;
		  
		  

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
		    r = stmt.executeQuery();
			
			
			while(r.next())
			{
				dashBoardDTO = new DashBoardBoardInterBoardDTO();
				
				dashBoardDTO.setWebApplicant(Integer.parseInt(r.getString("totalweb") == null ? "0" : r.getString("totalweb")));
				dashBoardDTO.setSmsApplicant(Integer.parseInt(r.getString("totalsms") == null ? "0" : r.getString("totalsms")));
				dashBoardDTO.setTotalTeleTalk(Integer.parseInt(r.getString("totalTTweb") == null ? "0" : r.getString("totalTTweb")));
				dashBoardDTO.setTotalSureCash(Integer.parseInt(r.getString("totalSC") == null ? "0" : r.getString("totalSC")));
				dashBoardDTO.setTotalBkash(Integer.parseInt(r.getString("totalBkash") == null ? "0" : r.getString("totalBkash")));
				dashBoardDTO.setTotalGP(Integer.parseInt(r.getString("totalGP") == null ? "0" : r.getString("totalGP")));
				
								
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
		
		return dashBoardDTO;
	}
	
	
	public DashBoardBoardInterBoardDTO getInterBoardDashBoardPaymentBoard(){
		   DashBoardBoardInterBoardDTO dashBoardDTO = null;

		   Connection conn = ConnectionManager.getReadConnection();
		
		  String sql=   
				  " select ALL_BOARD,DHAKA,COMILLA,RAJSHAHI,JESSORE,CHITTAGONG,BARISAL,SYLHET,DINAJPUR,MADRASHA,BTEB"+
				  " from"+
				  " (select count(br.eiin) ALL_BOARD"+
				  " from BOARD_RESULT br, MST_COLLEGE mc"+
				  " where BR.EIIN=MC.EIIN and BR.IS_PAID = 'YES'"+
				  " )t1,"+
				  " (select count(br.eiin) DHAKA"+
				  " from BOARD_RESULT br, MST_COLLEGE mc"+
				  " where BR.EIIN=MC.EIIN and BR.IS_PAID = 'YES'"+
				  " and MC.BOARD_ID='10')t2,"+
				  " (select count(br.eiin) COMILLA"+
				  " from BOARD_RESULT br, MST_COLLEGE mc"+
				  " where BR.EIIN=MC.EIIN and BR.IS_PAID = 'YES'"+
				  " and MC.BOARD_ID='11')t3,"+
				  " (select count(br.eiin) RAJSHAHI"+
				  " from BOARD_RESULT br, MST_COLLEGE mc"+
				  " where BR.EIIN=MC.EIIN and BR.IS_PAID = 'YES'"+
				  " and MC.BOARD_ID='12')t4,"+
				  " (select count(br.eiin) JESSORE"+
				  " from BOARD_RESULT br, MST_COLLEGE mc"+
				  " where BR.EIIN=MC.EIIN and BR.IS_PAID = 'YES'"+
				  " and MC.BOARD_ID='13')t5,"+
				  " (select count(br.eiin) CHITTAGONG"+
				  " from BOARD_RESULT br, MST_COLLEGE mc"+
				  " where BR.EIIN=MC.EIIN and BR.IS_PAID = 'YES'"+
				  " and MC.BOARD_ID='14')t6,"+
				  " (select count(br.eiin) BARISAL"+
				  " from BOARD_RESULT br, MST_COLLEGE mc"+
				  " where BR.EIIN=MC.EIIN and BR.IS_PAID = 'YES'"+
				  " and MC.BOARD_ID='15')t7,"+
				  " (select count(br.eiin) SYLHET"+
				  " from BOARD_RESULT br, MST_COLLEGE mc"+
				  " where BR.EIIN=MC.EIIN and BR.IS_PAID = 'YES'"+
				  " and MC.BOARD_ID='16')t8,"+
				  " (select count(br.eiin) DINAJPUR"+
				  " from BOARD_RESULT br, MST_COLLEGE mc"+
				  " where BR.EIIN=MC.EIIN and BR.IS_PAID = 'YES'"+
				  " and MC.BOARD_ID='17')t9,"+
				  " (select count(br.eiin) MADRASHA"+
				  " from BOARD_RESULT br, MST_COLLEGE mc"+
				  " where BR.EIIN=MC.EIIN and BR.IS_PAID = 'YES'"+
				  " and MC.BOARD_ID='18')t10,"+
				  " (select count(br.eiin) BTEB"+
				  " from BOARD_RESULT br, MST_COLLEGE mc"+
				  " where BR.EIIN=MC.EIIN and BR.IS_PAID = 'YES'"+
				  " and MC.BOARD_ID='19')t10";




		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
		    r = stmt.executeQuery();
			
			
			while(r.next())
			{
				dashBoardDTO = new DashBoardBoardInterBoardDTO();
				
				dashBoardDTO.setAllBoard(Integer.parseInt(r.getString("ALL_BOARD") == null ? "0" : r.getString("ALL_BOARD")));
				dashBoardDTO.setDhaka(Integer.parseInt(r.getString("DHAKA") == null ? "0" : r.getString("DHAKA")));
				dashBoardDTO.setComilla(Integer.parseInt(r.getString("COMILLA") == null ? "0" : r.getString("COMILLA")));
				dashBoardDTO.setRajshahi(Integer.parseInt(r.getString("RAJSHAHI") == null ? "0" : r.getString("RAJSHAHI")));
				dashBoardDTO.setJessore(Integer.parseInt(r.getString("JESSORE") == null ? "0" : r.getString("JESSORE")));
				dashBoardDTO.setChittagong(Integer.parseInt(r.getString("CHITTAGONG") == null ? "0" : r.getString("CHITTAGONG")));
				dashBoardDTO.setBarisal(Integer.parseInt(r.getString("BARISAL") == null ? "0" : r.getString("BARISAL")));
				dashBoardDTO.setSylhet(Integer.parseInt(r.getString("SYLHET") == null ? "0" : r.getString("SYLHET")));
				dashBoardDTO.setDinajpur(Integer.parseInt(r.getString("DINAJPUR") == null ? "0" : r.getString("DINAJPUR")));
				dashBoardDTO.setMadrasha(Integer.parseInt(r.getString("MADRASHA") == null ? "0" : r.getString("MADRASHA")));
				dashBoardDTO.setBteb(Integer.parseInt(r.getString("BTEB") == null ? "0" : r.getString("BTEB")));
				
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
		
		return dashBoardDTO;
	}
	
	
	public DashBoardBoardInterBoardDTO getBoardDashBoard(String boardId){
		   DashBoardBoardInterBoardDTO dashBoardDTO = null;

		
		   Connection conn = ConnectionManager.getReadConnection();
		   
		   
		   String sql="SELECT TOTAL_APPLICATION, " +
				   "       TOTAL_APPLICANT, " +
				   "       WEB_APPLICATION, " +
				   "       WEB_APPLICANT, " +
				   "       SMS_APPLICATION, " +
				   "       SMS_APPLICANT " +
				   "  FROM (SELECT COUNT (APPLICANT_ID) TOTAL_APPLICATION, " +
				   "               COUNT (DISTINCT APPLICANT_ID) TOTAL_APPLICANT " +
				   "          FROM APPLICATION_COLLEGES " +
				   "         WHERE EIIN IN (SELECT EIIN " +
				   "                          FROM MST_COLLEGE " +
				   "                         WHERE BOARD_ID =?)) t1, " +
				   "       (SELECT COUNT (APPLICANT_ID) WEB_APPLICATION, " +
				   "               COUNT (DISTINCT APPLICANT_ID) WEB_APPLICANT " +
				   "          FROM APPLICATION_COLLEGES " +
				   "         WHERE     EIIN IN (SELECT EIIN " +
				   "                              FROM MST_COLLEGE " +
				   "                             WHERE BOARD_ID =?) " +
				   "               AND APP_TYPE = 'WEB') t2, " +
				   "       (SELECT COUNT (APPLICANT_ID) SMS_APPLICATION, " +
				   "               COUNT (DISTINCT APPLICANT_ID) SMS_APPLICANT " +
				   "          FROM APPLICATION_COLLEGES " +
				   "         WHERE     EIIN IN (SELECT EIIN " +
				   "                              FROM MST_COLLEGE " +
				   "                             WHERE BOARD_ID =?) " +
				   "               AND APP_TYPE = 'SMS') t3 " ; 
		
/*		String sql ="SELECT COUNT (APPLICATION_ID) TOTAL_APPLICATION,\n" +
                "       COUNT (DISTINCT APPLICATION_ID) TOTAL_APPLICANT\n" +
                "  FROM APPLICATION_COLLEGES\n" +
                " WHERE  EIIN IN (SELECT EIIN\n" +
                "                      FROM MST_COLLEGE\n" +
                "                     WHERE BOARD_ID =?)";*/
		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,boardId);
			stmt.setString(2,boardId);
			stmt.setString(3,boardId);
			r = stmt.executeQuery();
			
			
			while(r.next())
			{
				dashBoardDTO = new DashBoardBoardInterBoardDTO();
				
				dashBoardDTO.setTotalApplicant(Integer.parseInt(r.getString("TOTAL_APPLICANT") == null ? "0" : r.getString("TOTAL_APPLICANT")));
				dashBoardDTO.setTotalApplication(Integer.parseInt(r.getString("TOTAL_APPLICATION") == null ? "0" : r.getString("TOTAL_APPLICATION")));
				dashBoardDTO.setWebApplicant(Integer.parseInt(r.getString("WEB_APPLICANT") == null ? "0" : r.getString("WEB_APPLICANT")));
				dashBoardDTO.setWebApplication(Integer.parseInt(r.getString("WEB_APPLICATION") == null ? "0" : r.getString("WEB_APPLICATION")));
				dashBoardDTO.setSmsApplicant(Integer.parseInt(r.getString("SMS_APPLICANT") == null ? "0" : r.getString("SMS_APPLICANT")));
				dashBoardDTO.setSmsApplication(Integer.parseInt(r.getString("SMS_APPLICATION") == null ? "0" : r.getString("SMS_APPLICATION")));
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
		
		return dashBoardDTO;
	}
	
	public List<ApplicationStatBoardDTO> getDateWiseApplicationStatBoard(String userId){
		ApplicationStatBoardDTO dateWiseApplicationStatBoardDTO = null;
		List<ApplicationStatBoardDTO> dateWiseApplicationStatBoardList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql ="  SELECT t1.APP_DATE, t1.TOTAL_APPLICATION, t1.TOTAL_APPLICANT\n" +
	            "    FROM (  SELECT TO_CHAR (applied_on, 'dd, MON') APP_DATE,\n" +
	            "                   COUNT (APPLICATION_ID) TOTAL_APPLICATION,\n" +
	            "                   COUNT (DISTINCT APPLICATION_ID) TOTAL_APPLICANT\n" +
	            "              FROM APPLICATION_COLLEGES\n" +
	            "             WHERE EIIN IN (SELECT EIIN\n" +
	            "                              FROM MST_COLLEGE\n" +
	            "                             WHERE BOARD_ID =?)\n" +
	            "          GROUP BY TO_CHAR (applied_on, 'dd, MON')) t1\n" +
	            "ORDER BY TO_DATE (t1.APP_DATE, 'dd, MON')";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			//String uid="15";
			//stmt.setString(1,uid);
			
			stmt.setString(1, userId);
			
			r = stmt.executeQuery();
			
			dateWiseApplicationStatBoardList = new ArrayList<ApplicationStatBoardDTO>();
			while(r.next())
			{
				dateWiseApplicationStatBoardDTO = new ApplicationStatBoardDTO();

				dateWiseApplicationStatBoardDTO.setAppDate(r.getString("APP_DATE"));
				dateWiseApplicationStatBoardDTO.setTotalApplication(Integer.parseInt(r.getString("TOTAL_APPLICATION") == null ? "0" : r.getString("TOTAL_APPLICATION")));
				dateWiseApplicationStatBoardDTO.setTotalApplicant(Integer.parseInt(r.getString("TOTAL_APPLICANT") == null ? "0" : r.getString("TOTAL_APPLICANT")));

				dateWiseApplicationStatBoardList.add(dateWiseApplicationStatBoardDTO);
				

			}
			/*System.out.println("dateWiseApplicationStatBoardList.size="+dateWiseApplicationStatBoardList.size());*/
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
		
		return dateWiseApplicationStatBoardList;
	}
	
	
	public List<DistrictinfoDTO> getDistrictinfo(String userId){
		DistrictinfoDTO districtinfoDTO = null;
		List<DistrictinfoDTO> districtinfoList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql =" SELECT DIST_ID, DIST_NAME\n" +
		         "    FROM MST_DISTRICT\n" +
		         "ORDER BY DIST_NAME";
		

		
		

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
						
			r = stmt.executeQuery();
			
			districtinfoList = new ArrayList<DistrictinfoDTO>();
			while(r.next())
			{
				districtinfoDTO = new DistrictinfoDTO();

				districtinfoDTO.setDistrictID(r.getString("DIST_ID"));
				districtinfoDTO.setDistrictName(r.getString("DIST_NAME"));
				
				districtinfoList.add(districtinfoDTO);
				

			}
			/*System.out.println("districtinfoList.size="+districtinfoList.size());*/
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
		
		return districtinfoList;
	}		
	
	
	public List<CollegeInfoDTO> getCollegeInfo(String userId){
		CollegeInfoDTO collegeInfoDTO = null;
		List<CollegeInfoDTO> collegeInfoList = null;
		
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "SELECT EIIN, COLLEGE_NAME FROM MST_COLLEGE WHERE BOARD_ID =? ORDER BY COLLEGE_NAME";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			
			r = stmt.executeQuery();
			
			collegeInfoList = new ArrayList<CollegeInfoDTO>();
			while(r.next())
			{
				collegeInfoDTO = new CollegeInfoDTO();

				
				collegeInfoDTO.setEiinCode(r.getString("EIIN"));
				collegeInfoDTO.setCollegeName(r.getString("COLLEGE_NAME"));
				
				collegeInfoList.add(collegeInfoDTO);
				

			}
			/*System.out.println("collegeInfoList.size="+collegeInfoList.size());*/
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
		
		return collegeInfoList;
	}	

}
