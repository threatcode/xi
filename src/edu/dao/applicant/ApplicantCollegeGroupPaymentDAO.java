package edu.dao.applicant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.dto.applicant.ApplicantCollegeGroupPaymentDTO;
import edu.utils.connection.ConnectionManager;



public class ApplicantCollegeGroupPaymentDAO {
	
	public List<ApplicantCollegeGroupPaymentDTO> getApplicantCollegeGroupPayment(String userId){
		ApplicantCollegeGroupPaymentDTO applicantCollegeGroupPaymentDTO = null;
		List<ApplicantCollegeGroupPaymentDTO> applicantCollegeGroupPaymentList = null;
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "SELECT t1.PRIORITY_ORDER,\n" +
                "       t1.PIN,\n" +
                "       t1.COLLEGE_NAME,\n" +
                "       t1.DIST_NAME,\n" +
                "       t1.SHIFT_NAME,\n" +
                "       t1.VERSION_NAME,\n" +
                "       t1.GROUP_NAME,\n" +
                "       t2.PAYMENT_TYPE,\n" +
                "       t2.PAYMENT_STATUS\n" +
                "       FROM (  SELECT a.PIN,\n" +
                "                 B.COLLEGE_NAME,\n" +
                "                 G.DIST_NAME,\n" +
                "                 D.SHIFT_NAME,\n" +
                "                 E.VERSION_NAME,\n" +
                "                 F.GROUP_NAME,\n" +
                "                 a.PRIORITY_ORDER       \n" +
                "            FROM APPLICATION_COLLEGES a,\n" +
                "                 MST_COLLEGE b,\n" +
                "                 MST_SHIFT d,\n" +
                "                 MST_VERSION e,\n" +
                "                 MST_GROUP f,\n" +
                "                 MST_DISTRICT g\n" +
                "           WHERE     a.APPLICATION_ID =?\n" +
                "                 AND A.EIIN = B.EIIN\n" +
                "                 AND A.SHIFT_ID = D.SHIFT_ID\n" +
                "                 AND A.VERSION_ID = E.VERSION_ID\n" +
                "                 AND A.GROUP_ID = F.GROUP_ID\n" +
                "                 AND B.DIST_ID = G.DIST_ID\n" +
                "        ORDER BY a.PRIORITY_ORDER) t1,\n" +
                "       (SELECT PIN, PAYMENT_TYPE, PAYMENT_STATUS\n" +
                "          FROM APPLICATION_PAYMENT\n" +
                "         WHERE pin IN (SELECT DISTINCT pin\n" +
                "                         FROM APPLICATION_COLLEGES\n" +
                "                        WHERE APPLICATION_ID =?)) t2\n" +
                " WHERE t1.pin = t2.pin";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			String uid="3000001";
			stmt.setString(1,uid);
			stmt.setString(2,uid);
			//stmt.setString(1, userId);
			//stmt.setString(1, userId);
			r = stmt.executeQuery();
			
			applicantCollegeGroupPaymentList = new ArrayList<ApplicantCollegeGroupPaymentDTO>();
			while(r.next())
			{
				applicantCollegeGroupPaymentDTO = new ApplicantCollegeGroupPaymentDTO();

				
				applicantCollegeGroupPaymentDTO.setPriorityOrder(r.getString("PRIORITY_ORDER"));
				applicantCollegeGroupPaymentDTO.setPin(r.getString("PIN"));
				applicantCollegeGroupPaymentDTO.setCollegeName(r.getString("COLLEGE_NAME"));
				applicantCollegeGroupPaymentDTO.setDistrictName(r.getString("DIST_NAME"));
				applicantCollegeGroupPaymentDTO.setShiftName(r.getString("SHIFT_NAME"));
				applicantCollegeGroupPaymentDTO.setVersionName(r.getString("VERSION_NAME"));
				applicantCollegeGroupPaymentDTO.setGroupName(r.getString("GROUP_NAME"));
				applicantCollegeGroupPaymentDTO.setApplicationType(r.getString("PAYMENT_TYPE"));
				applicantCollegeGroupPaymentDTO.setPaymentStatus(r.getString("PAYMENT_STATUS"));
				
				applicantCollegeGroupPaymentList.add(applicantCollegeGroupPaymentDTO);
			//	System.out.println(applicantCollegeGroupPaymentList.size());

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
		
		return applicantCollegeGroupPaymentList;
	}
	
	
	public List<ApplicantCollegeGroupPaymentDTO> getPriorityChangeByapplicant(String userId){
		ApplicantCollegeGroupPaymentDTO applicantCollegeGroupPaymentDTO = null;
		List<ApplicantCollegeGroupPaymentDTO> priorityChangeByApplicantList = null;
		Connection conn = ConnectionManager.getReadConnection();
		
		String sql = "SELECT t1.APPLICATION_ID,\n" +
                "       t1.PIN,\n" +
                "       t1.PRIORITY_ORDER,\n" +
                "       t1.COLLEGE_NAME,\n" +
                "       t1.DIST_NAME,\n" +
                "       t1.SHIFT_NAME,\n" +
                "       t1.VERSION_NAME,\n" +
                "       t1.GROUP_NAME,\n" +
                "       t2.PAYMENT_TYPE,\n" +
                "       t2.PAYMENT_STATUS\n" +
                "       FROM (  SELECT a.APPLICATION_ID,\n" +
                "                 a.PIN,\n" +
                "                 B.COLLEGE_NAME,\n" +
                "                 G.DIST_NAME,\n" +
                "                 D.SHIFT_NAME,\n" +
                "                 E.VERSION_NAME,\n" +
                "                 F.GROUP_NAME,\n" +
                "                 a.PRIORITY_ORDER       \n" +
                "            FROM APPLICATION_COLLEGES a,\n" +
                "                 MST_COLLEGE b,\n" +
                "                 MST_SHIFT d,\n" +
                "                 MST_VERSION e,\n" +
                "                 MST_GROUP f,\n" +
                "                 MST_DISTRICT g\n" +
                "           WHERE     a.APPLICATION_ID =?\n" +
                "                 AND A.EIIN = B.EIIN\n" +
                "                 AND A.SHIFT_ID = D.SHIFT_ID\n" +
                "                 AND A.VERSION_ID = E.VERSION_ID\n" +
                "                 AND A.GROUP_ID = F.GROUP_ID\n" +
                "                 AND B.DIST_ID = G.DIST_ID\n" +
                "        ORDER BY a.PRIORITY_ORDER) t1,\n" +
                "       (SELECT PIN, PAYMENT_TYPE, PAYMENT_STATUS\n" +
                "          FROM APPLICATION_PAYMENT\n" +
                "         WHERE pin IN (SELECT DISTINCT pin\n" +
                "                         FROM APPLICATION_COLLEGES\n" +
                "                        WHERE APPLICATION_ID =?)) t2\n" +
                " WHERE t1.pin = t2.pin";

		PreparedStatement stmt = null;
		ResultSet r = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			
			String uid="3000001";
			stmt.setString(1,uid);
			stmt.setString(2,uid);
			//stmt.setString(1, userId);
			//stmt.setString(1, userId);
			r = stmt.executeQuery();
			
			priorityChangeByApplicantList = new ArrayList<ApplicantCollegeGroupPaymentDTO>();
			while(r.next())
			{
				applicantCollegeGroupPaymentDTO = new ApplicantCollegeGroupPaymentDTO();

				applicantCollegeGroupPaymentDTO.setApplicationId(r.getString("APPLICATION_ID"));
				applicantCollegeGroupPaymentDTO.setPriorityOrder(r.getString("PRIORITY_ORDER"));
				applicantCollegeGroupPaymentDTO.setNewpriorityOrder(r.getString("PRIORITY_ORDER"));
				applicantCollegeGroupPaymentDTO.setPin(r.getString("PIN"));
				applicantCollegeGroupPaymentDTO.setCollegeName(r.getString("COLLEGE_NAME"));
				applicantCollegeGroupPaymentDTO.setDistrictName(r.getString("DIST_NAME"));
				applicantCollegeGroupPaymentDTO.setShiftName(r.getString("SHIFT_NAME"));
				applicantCollegeGroupPaymentDTO.setVersionName(r.getString("VERSION_NAME"));
				applicantCollegeGroupPaymentDTO.setGroupName(r.getString("GROUP_NAME"));
				applicantCollegeGroupPaymentDTO.setApplicationType(r.getString("PAYMENT_TYPE"));
				applicantCollegeGroupPaymentDTO.setPaymentStatus(r.getString("PAYMENT_STATUS"));
				
				priorityChangeByApplicantList.add(applicantCollegeGroupPaymentDTO);
			//	System.out.println(priorityChangeByApplicantList.size());

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
		
		return priorityChangeByApplicantList;
	}
	
	
public Boolean updatePriorityByapplicant(ArrayList<ApplicantCollegeGroupPaymentDTO> priorityChangeByApplicantList){
	
		//ApplicantCollegeGroupPaymentDTO applicantCollegeGroupPaymentDTO = null;
		Connection conn = ConnectionManager.getWriteConnection();
		
	    String updateSql = "UPDATE APPLICATION_COLLEGES\n" +
                "   SET PRIORITY_ORDER =?\n" +
                " WHERE APPLICATION_ID =? AND PIN =? AND PRIORITY_ORDER =?";

		PreparedStatement stmt = null;
		
	//	System.out.println(priorityChangeByApplicantList.size());

		try
		{
			stmt = conn.prepareStatement(updateSql);
			
            for (ApplicantCollegeGroupPaymentDTO applicantCollegeGroupPaymentDTO : priorityChangeByApplicantList) {

                stmt.setString(1,applicantCollegeGroupPaymentDTO.getNewpriorityOrder());
                stmt.setString(2,applicantCollegeGroupPaymentDTO.getApplicationId());
                stmt.setString(3,applicantCollegeGroupPaymentDTO.getPin());
                stmt.setString(4,applicantCollegeGroupPaymentDTO.getOldPriorityOrder());
                
           //     System.out.println(applicantCollegeGroupPaymentDTO.getOldPriorityOrder());
        //        System.out.println(applicantCollegeGroupPaymentDTO.getNewpriorityOrder());


                stmt.addBatch();
            }
            
            int noOfRowsArray[]=stmt.executeBatch();
			

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
		
		return Boolean.TRUE;
	}
	

}
