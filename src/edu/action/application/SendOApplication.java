package edu.action.application;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.dao.ResultLog;
import edu.dao.application.ApplicationDAO;
import edu.dao.board.ApplicantInfoDAO;
import edu.dto.application.ApplicantDTO;
import edu.dto.application.ChoiceDTO;
import edu.dto.board.ApplicantInfoBoardDTO;
import edu.dto.board.ApplicantInfoDTO;
import edu.helpers.SmsSender;
import edu.utils.connection.ConnectionManager;

public class SendOApplication  extends ActionSupport  implements Runnable  {
	
	public void run(){
    	sendOapplication(app_id,applicant.getSsc_info().getStudent_name() ,applicant.getSsc_info().getGender_id(),"quota",
			applicant.getSsc_info().getBoard_name(),applicant.getSsc_info().getRoll(),
			applicant.getSsc_info().getPassing_year(),"regno",applicant.getApplication_info().getMobile_number(),
			StringUtils.join(eiinList , "|"),StringUtils.join(groupList , "|"),StringUtils.join(shiftList , "|"),StringUtils.join(versionList , "|"),
			StringUtils.join(priorityList , "|"),String.valueOf(priorityList.length));
	}
	public String sendNotSendApplication()
	{
		ApplicantInfoDAO aidao = new ApplicantInfoDAO();
		List<ApplicantInfoBoardDTO> listAppliDTO = aidao.getApplicantBasicInfoNotSend();
		for(int i=0;i<listAppliDTO.size();i++)
		{
			if(i%300==0)
				try{Thread.sleep(5000);}catch(Exception e){e.printStackTrace();};
				ApplicantInfoBoardDTO appDTO = listAppliDTO.get(i);
			
			SmsSender smsSender=new SmsSender();
			smsSender.setMobile(appDTO.getMobilenumber().substring(appDTO.getMobilenumber().length()-11));
			smsSender.setAppid(appDTO.getApplicationID());
			smsSender.setRoll(appDTO.getSscRollNo());
			smsSender.setName(appDTO.getApplicantName());
			smsSender.setApptime(appDTO.getApptime());
			smsSender.setOperation("smsWFirst");
			
			Thread thread = new Thread(smsSender);
			thread.start();
		}
		return null;
	}
	
	public String sendResult()
	{
		ApplicantInfoDAO aidao = new ApplicantInfoDAO();
//		List<ApplicantInfoDTO> listAppliDTO = aidao.getApplicantByBoardForResult(ssc_board);
		List<ApplicantInfoBoardDTO> listAppliDTO = aidao.getApplicantByBoardForResult(app_id);
		for(int i=0;i<listAppliDTO.size();i++)
		{
			if(i%300==0)
				try{Thread.sleep(5000);}catch(Exception e){e.printStackTrace();};
			ApplicantInfoBoardDTO appDTO = listAppliDTO.get(i);
			
			SmsSender smsSender=new SmsSender();
			smsSender.setMobile(appDTO.getMobilenumber());
			smsSender.setAppid(appDTO.getApplicationID());
			smsSender.setText(appDTO.getSmstext());
			if(operator.equalsIgnoreCase("TT"))
				smsSender.setOperation("textMSGTT");
			if(operator.equalsIgnoreCase("SSL"))
				smsSender.setOperation("textMSG");
			
			Thread thread = new Thread(smsSender);
			thread.start();
		}
		return null;
	}
	
	public String changeStatusPaidApplication()
	{
		ApplicantInfoDAO aidao = new ApplicantInfoDAO();
		List<String> listAppliDTO = aidao.getAllNonPAidApplicant();
//		listAppliDTO.add("7000044");
		System.out.println("Not Paid Start " +listAppliDTO.size());
		for(int i=0;i<listAppliDTO.size();i++)
		{
			try{
				String urlParameters =
			        "app_id=" + URLEncoder.encode(listAppliDTO.get(i), "UTF-8");
				listAppliDTO.get(i);
				String targetURL = "http://123.49.43.141:9999/buetpaymentcheck.php";
				URL url;
				HttpURLConnection connection = null;
				url = new URL(targetURL);
				connection = (HttpURLConnection)url.openConnection();
				connection.setRequestMethod("POST");
			      connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			      connection.setRequestProperty("Content-Length", "" + 
			               Integer.toString(urlParameters.getBytes().length));
			      connection.setRequestProperty("Content-Language", "en-US");  
						
			      connection.setUseCaches (false);
			      connection.setDoInput(true);
			      connection.setDoOutput(true);
		      //Send request
		      DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
		      wr.writeBytes (urlParameters);
		      wr.flush ();
		      wr.close ();
	
		      //Get Response	
		      InputStream is = connection.getInputStream();
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		      String line;
		      StringBuffer response = new StringBuffer(); 
		      System.out.println("####################### TT Response ##############################");
		      while((line = rd.readLine()) != null) {
		        response.append(line);
		        response.append('\r');
		      }
		      if(response.toString().startsWith("</reply>YES.</reply>"))
		      {
		    	  ApplicationDAO adao = new ApplicationDAO();
		    	  adao.setPayment(listAppliDTO.get(i));
		      }
		      else if(response.toString().startsWith("</reply>NO.</reply>"))
		      {
		    	  System.out.println("Not Paid");
		      }
		      else
		    	  System.out.println("Something Wrong");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	public String sendNotSendApplication_All()
	{
		ApplicantInfoDAO aidao = new ApplicantInfoDAO();
		List<ApplicantInfoBoardDTO> listAppliDTO = aidao.getApplicantBasicInfoNotSend_All();
		for(int i=0;i<listAppliDTO.size();i++)
		{
			if(i%100==0)
				try{Thread.sleep(5000);}catch(Exception e){e.printStackTrace();};
			ApplicantInfoBoardDTO appDTO = listAppliDTO.get(i);
			app_id = appDTO.getApplicationID();
			sendNotSendById();
		}
		return null;
	}
	public String sendNotSendById()
	{
//		System.out.println("sendNotSendById 1");
		ApplicantInfoDAO aidao = new ApplicantInfoDAO();
		List<ApplicantInfoBoardDTO> listAppliDTO = aidao.getApplicantBasicInfoNotSend(app_id);
		for(int i=0;i<listAppliDTO.size();i++)
		{
			ApplicantInfoBoardDTO appDTO = listAppliDTO.get(i);
			List<ChoiceDTO> cdto = aidao.getChoiceDTO(appDTO.getApplicationID());
			String[] choiceIdList = new String[cdto.size()];
			String[] eiinList = new String[cdto.size()];
			String[] shiftList = new String[cdto.size()];
			String[] versionList = new String[cdto.size()];
			String[] groupList = new String[cdto.size()];
			String[] sQuotaList = new String[cdto.size()];
			String[] priorityList	 = new String[cdto.size()];
			String[] viaList	 = new String[cdto.size()];

			for (i = 0; i < cdto.size(); i++)
			{
				ChoiceDTO choice = cdto.get(i);		
				choiceIdList[i] 	  		=  choice.getChoice_id();
				eiinList[i] 	  		=  choice.getEiin();
				shiftList[i]  	=  choice.getShift_id();
				versionList[i] 	=  choice.getVersion_id();
				groupList[i]=  choice.getGroup_id();
				sQuotaList[i]= choice.getSpecial_quota();
				priorityList[i] 		  	=  choice.getPriority();
				viaList[i] 		  	=  choice.getVia();
			}
//			System.out.println("sendNotSendById 2");
			op = "NEW";
//			NotSend = true;
			
			sendOapplication(app_id,appDTO.getApplicantName(),appDTO.getGender(),"quota",appDTO.getBoardID(),
					appDTO.getSscRollNo(),appDTO.getSscPassingYear(),"regno",appDTO.getMobilenumber(),
					StringUtils.join(eiinList , "|"),StringUtils.join(groupList , "|"),StringUtils.join(shiftList , "|"),StringUtils.join(versionList , "|"),
					StringUtils.join(priorityList , "|"),String.valueOf(priorityList.length));
//			System.out.println("sendNotSendById 3");
//			try{Thread.sleep(5000);}catch(Exception e){e.printStackTrace();};
			
		}
		
		return null;
	}
	public String sendNotSendFromTable()
	{
		ApplicantInfoDAO aidao = new ApplicantInfoDAO();
		List<ApplicantInfoBoardDTO> listAppliDTO = aidao.getAppBasicInfoNotSendFT();
		for(int i=0;i<listAppliDTO.size();i++)
		{
			if(i%100==0)
				try{Thread.sleep(5000);}catch(Exception e){e.printStackTrace();};
			ApplicantInfoBoardDTO appDTO = listAppliDTO.get(i);
			app_id = appDTO.getApplicationID();
			sendNotSendById();
		}
		
		return null;
	}
	private String sendOapplication(String app_id, String name, String gender, String quota, String ssc_board, String ssc_roll, String ssc_year, 
			String ssc_regno, String contact_no, String eiin, String group, String shift, String medium, String priority, String no_of_app)
	{
		String targetURL = "http://114.130.64.35:9999/CAD/buet2TTOapplication.php";
		URL url;
	    HttpURLConnection connection = null;  
	    
	    ApplicantInfoDAO aidao = new ApplicantInfoDAO();
	    String tmpFake="N";
	    /*
	     // Fake checking
	    try{
	    	if(aidao.isCancelled(app_id))
	    		tmpFake="Y";
	    }catch(Exception e)
	    {
	    	System.out.println("sorry");
	    }
	    */
		try
		{
			StringBuilder urlParameter = new StringBuilder();
			urlParameter.append("app_id=").append(URLEncoder.encode(app_id, "UTF-8"));
			urlParameter.append("&name=").append(URLEncoder.encode(name, "UTF-8"));
			urlParameter.append("&gender=").append(URLEncoder.encode(gender, "UTF-8"));
			urlParameter.append("&quota=").append(URLEncoder.encode(quota, "UTF-8"));
			urlParameter.append("&ssc_board=").append(URLEncoder.encode(ssc_board, "UTF-8"));
			urlParameter.append("&ssc_roll=").append(URLEncoder.encode(ssc_roll, "UTF-8"));
			urlParameter.append("&ssc_year=").append(URLEncoder.encode(ssc_year, "UTF-8"));
			urlParameter.append("&ssc_regno=").append(URLEncoder.encode(ssc_regno, "UTF-8"));
			urlParameter.append("&contact_no=").append(URLEncoder.encode(contact_no, "UTF-8"));
			urlParameter.append("&eiin=").append(URLEncoder.encode(eiin, "UTF-8"));
			urlParameter.append("&group=").append(URLEncoder.encode(group, "UTF-8"));
			urlParameter.append("&shift=").append(URLEncoder.encode(shift, "UTF-8"));
			urlParameter.append("&medium=").append(URLEncoder.encode(medium, "UTF-8"));
			urlParameter.append("&priority=").append(URLEncoder.encode(priority, "UTF-8"));
			urlParameter.append("&no_of_app=").append(URLEncoder.encode(no_of_app, "UTF-8"));
			urlParameter.append("&op=").append(URLEncoder.encode(op, "UTF-8"));
			urlParameter.append("&fake=").append(URLEncoder.encode(tmpFake, "UTF-8"));
			
//			String urlParameters =
//		        "app_id=" + URLEncoder.encode(app_id, "UTF-8") +
//		        "&name=" + URLEncoder.encode(name, "UTF-8") +
//		        "&gender=" + URLEncoder.encode(gender, "UTF-8") +
//		        "&quota=" + URLEncoder.encode(quota, "UTF-8") +
//		        "&ssc_board=" + URLEncoder.encode(ssc_board, "UTF-8") +
//		        "&ssc_roll=" + URLEncoder.encode(ssc_roll, "UTF-8") +
//		        "&ssc_year=" + URLEncoder.encode(ssc_year, "UTF-8") +
//		        "&ssc_regno=" + URLEncoder.encode(ssc_regno, "UTF-8") +
//		        "&contact_no=" + URLEncoder.encode(contact_no, "UTF-8") +
//		        "&eiin=" + URLEncoder.encode(eiin, "UTF-8") +
//		        "&group=" + URLEncoder.encode(group, "UTF-8") +
//		        "&shift=" + URLEncoder.encode(shift, "UTF-8") +
//		        "&medium=" + URLEncoder.encode(medium, "UTF-8") +
//		        "&priority=" + URLEncoder.encode(priority, "UTF-8") +
//		        "&no_of_app=" + URLEncoder.encode(no_of_app, "UTF-8") +
//		        "&op=" + URLEncoder.encode("NEW", "UTF-8");
			
			System.out.println(urlParameter.toString());
		      //Create connection
		      url = new URL(targetURL);
		      connection = (HttpURLConnection)url.openConnection();
		      connection.setRequestMethod("POST");
		      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		      connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameter.toString().getBytes().length));
		      connection.setRequestProperty("Content-Language", "en-US");  
					
		      connection.setUseCaches (false);
		      connection.setDoInput(true);
		      connection.setDoOutput(true);

		      //Send request
		      DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
		      wr.writeBytes (urlParameter.toString());
		      wr.flush ();
		      wr.close ();

		      //Get Response	
		      InputStream is = connection.getInputStream();
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		      String line;
		      StringBuffer response = new StringBuffer(); 
		      System.out.println("####################### TT Response ##############################");
		      while((line = rd.readLine()) != null) {
		        response.append(line);
		        response.append('\r');
		      }
		      if(response.toString().startsWith("<reply>SUCCESS.</reply>"))
		      {
		    	  ApplicationDAO adao = new ApplicationDAO();
		    	  adao.setttsend(app_id);
		      }
		      else if(response.toString().startsWith("<reply>NOT INSERTED.</reply>"))
		      {
		    	  ApplicationDAO adao = new ApplicationDAO();
		    	  adao.setttsend(app_id);
		      }
		      else if(response.toString().startsWith("<reply>DUPLICATE ID.</reply>"))
		      {
		    	  ApplicationDAO adao = new ApplicationDAO();
		    	  adao.setttsend(app_id);
		    	  System.out.println("Duplicate Found");
		      }
		      else if(response.toString().startsWith("<reply>IP is not authenticated.</reply>"))
		    	  System.out.println("IP is not authenticated");
		      else if(response.toString().startsWith("<reply>App ID is empty.</reply>"))
		    	  System.out.println("App Id is empty");
		      else
		    	  System.out.println("Something Wrong");
		      
		      System.out.println(response.toString());
		      System.out.println("IICT send "+urlParameter.toString());
		      System.out.println("####################### TT Response ##############################");
		      rd.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		      return null;

		    } finally {
		      if(connection != null) {
		        connection.disconnect(); 
		      }
		    }
		  
		return null;
	}
	private String sendOapplication_all(String app_id1, String name1, String gender1, String quota1, String ssc_board1, String ssc_roll1, String ssc_year1, 
			String ssc_regno1, String contact_no1, String eiin1, String group1, String shift1, String medium1, String priority1, String no_of_app1)
	{
		String targetURL = "http://123.49.43.141:9999/buet2TTOapplication.php";
		URL url;
	    HttpURLConnection connection = null;  
		try
		{
			StringBuilder urlParameter = new StringBuilder();
			urlParameter.append("app_id=").append(URLEncoder.encode(app_id1, "UTF-8"));
			urlParameter.append("&name=").append(URLEncoder.encode(name1, "UTF-8"));
			urlParameter.append("&gender=").append(URLEncoder.encode(gender1, "UTF-8"));
			urlParameter.append("&quota=").append(URLEncoder.encode(quota1, "UTF-8"));
			urlParameter.append("&ssc_board=").append(URLEncoder.encode(ssc_board1, "UTF-8"));
			urlParameter.append("&ssc_roll=").append(URLEncoder.encode(ssc_roll1, "UTF-8"));
			urlParameter.append("&ssc_year=").append(URLEncoder.encode(ssc_year1, "UTF-8"));
			urlParameter.append("&ssc_regno=").append(URLEncoder.encode(ssc_regno1, "UTF-8"));
			urlParameter.append("&contact_no=").append(URLEncoder.encode(contact_no1, "UTF-8"));
			urlParameter.append("&eiin=").append(URLEncoder.encode(eiin1, "UTF-8"));
			urlParameter.append("&group=").append(URLEncoder.encode(group1, "UTF-8"));
			urlParameter.append("&shift=").append(URLEncoder.encode(shift1, "UTF-8"));
			urlParameter.append("&medium=").append(URLEncoder.encode(medium1, "UTF-8"));
			urlParameter.append("&priority=").append(URLEncoder.encode(priority1, "UTF-8"));
			urlParameter.append("&no_of_app=").append(URLEncoder.encode(no_of_app1, "UTF-8"));
			urlParameter.append("&op=").append(URLEncoder.encode(op, "UTF-8"));
			
//			String urlParameters =
//		        "app_id=" + URLEncoder.encode(app_id, "UTF-8") +
//		        "&name=" + URLEncoder.encode(name, "UTF-8") +
//		        "&gender=" + URLEncoder.encode(gender, "UTF-8") +
//		        "&quota=" + URLEncoder.encode(quota, "UTF-8") +
//		        "&ssc_board=" + URLEncoder.encode(ssc_board, "UTF-8") +
//		        "&ssc_roll=" + URLEncoder.encode(ssc_roll, "UTF-8") +
//		        "&ssc_year=" + URLEncoder.encode(ssc_year, "UTF-8") +
//		        "&ssc_regno=" + URLEncoder.encode(ssc_regno, "UTF-8") +
//		        "&contact_no=" + URLEncoder.encode(contact_no, "UTF-8") +
//		        "&eiin=" + URLEncoder.encode(eiin, "UTF-8") +
//		        "&group=" + URLEncoder.encode(group, "UTF-8") +
//		        "&shift=" + URLEncoder.encode(shift, "UTF-8") +
//		        "&medium=" + URLEncoder.encode(medium, "UTF-8") +
//		        "&priority=" + URLEncoder.encode(priority, "UTF-8") +
//		        "&no_of_app=" + URLEncoder.encode(no_of_app, "UTF-8") +
//		        "&op=" + URLEncoder.encode("NEW", "UTF-8");
			
		      //Create connection
		      url = new URL(targetURL);
		      connection = (HttpURLConnection)url.openConnection();
		      connection.setRequestMethod("POST");
		      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		      connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameter.toString().getBytes().length));
		      connection.setRequestProperty("Content-Language", "en-US");  
					
		      connection.setUseCaches (false);
		      connection.setDoInput(true);
		      connection.setDoOutput(true);

		      //Send request
		      DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
		      wr.writeBytes (urlParameter.toString());
		      wr.flush ();
		      wr.close ();

		      //Get Response	
		      InputStream is = connection.getInputStream();
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		      String line;
		      StringBuffer response = new StringBuffer(); 
		      System.out.println("####################### TT Response ##############################");
		      while((line = rd.readLine()) != null) {
		        response.append(line);
		        response.append('\r');
		      }
		      if(response.toString().startsWith("</reply>SUCCESS.</reply>"))
		      {
		    	  ApplicationDAO adao = new ApplicationDAO();
		    	  adao.setttsend(app_id);
		      }
		      else if(response.toString().startsWith("</reply>DUPLICATE FOUND.</reply>"))
		      {
		    	  System.out.println("Duplicate Found");
		    	  if(NotSend)
		    	  {
		    		  ApplicationDAO adao = new ApplicationDAO();
			    	  adao.setttsend(app_id);
		    	  }
		      }
		      else if(response.toString().startsWith("</reply>IP is not authenticated.</reply>"))
		    	  System.out.println("IP is not authenticated");
		      else if(response.toString().startsWith("</reply>App ID is empty.</reply>"))
		    	  System.out.println("App Id is empty");
		      else
		    	  System.out.println("Something Wrong");
		      
		      System.out.println(response.toString());
		      System.out.println("IICT send "+urlParameter.toString());
		      System.out.println("####################### TT Response ##############################");
		      rd.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		      return null;

		    } finally {
		      if(connection != null) {
		        connection.disconnect(); 
		      }
		    }
		  
		return null;
	}	
	
	public String sendOapplication()
	{
		String targetURL = "http://123.49.43.141:9999/buet2TTOapplication.php";
		URL url;
	    HttpURLConnection connection = null;  
		try
		{
			String urlParameters =
		        "app_id=" + URLEncoder.encode("1235", "UTF-8") +
		        "&name=" + URLEncoder.encode("Delwar", "UTF-8") +
		        "&gender=" + URLEncoder.encode("Male", "UTF-8") +
		        "&quota=" + URLEncoder.encode("FQ|SQ", "UTF-8") +
		        "&ssc_board=" + URLEncoder.encode("dhaka", "UTF-8") +
		        "&ssc_roll=" + URLEncoder.encode("123456", "UTF-8") +
		        "&ssc_year=" + URLEncoder.encode("2015", "UTF-8") +
		        "&ssc_regno=" + URLEncoder.encode("88888888", "UTF-8") +
		        "&contact_no=" + URLEncoder.encode("01550155151", "UTF-8") +
		        "&eiin=" + URLEncoder.encode("1231232|234242", "UTF-8") +
		        "&group=" + URLEncoder.encode("0|2", "UTF-8") +
		        "&shift=" + URLEncoder.encode("D|D", "UTF-8") +
		        "&medium=" + URLEncoder.encode("B|B", "UTF-8") +
		        "&priority=" + URLEncoder.encode("1|2", "UTF-8") +
		        "&no_of_app=" + URLEncoder.encode("2", "UTF-8");
		      //Create connection
		      url = new URL(targetURL);
		      connection = (HttpURLConnection)url.openConnection();
		      connection.setRequestMethod("POST");
		      connection.setRequestProperty("Content-Type", 
		           "application/x-www-form-urlencoded");
					
		      connection.setRequestProperty("Content-Length", "" + 
		               Integer.toString(urlParameters.getBytes().length));
		      connection.setRequestProperty("Content-Language", "en-US");  
					
		      connection.setUseCaches (false);
		      connection.setDoInput(true);
		      connection.setDoOutput(true);

		      //Send request
		      DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
		      wr.writeBytes (urlParameters);
		      wr.flush ();
		      wr.close ();

		      //Get Response	
		      InputStream is = connection.getInputStream();
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		      String line;
		      StringBuffer response = new StringBuffer(); 
		      System.out.println("####################### TT Response ##############################");
		      while((line = rd.readLine()) != null) {
		        response.append(line);
		        response.append('\r');
		      }
		      if(response.toString().equals("</reply>SUCCESS.</reply>"))
		    	  System.out.println("Success");
		      else if(response.toString().equals("</reply>DUPLICATE FOUND.</reply>"))
		    	  System.out.println("Duplicate Found");
		      else if(response.toString().equals("</reply>IP is not authenticated.</reply>"))
		    	  System.out.println("IP is not authenticated");
		      else if(response.toString().equals("</reply>App ID is empty.</reply>"))
		    	  System.out.println("App Id is empty");
		      else
		    	  System.out.println("Something Wrong");
		      
		      System.out.println(response.toString());
		      System.out.println("####################### TT Response ##############################");
		      rd.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		      return null;

		    } finally {
		      if(connection != null) {
		        connection.disconnect(); 
		      }
		    }
		  
		return null;
	}
	public String TT2buetSMSapplication()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		String xForward=ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via");
		String remoteAddress=ServletActionContext.getRequest().getRemoteAddr();
		
		System.out.println("####################### TT TT2buetSMSapplication ##############################");
//		if(!( (xForward!=null && xForward.equalsIgnoreCase("114.130.64.36")) || 
//				(via!=null && via.equalsIgnoreCase("114.130.64.36")) || 
//				(remoteAddress!=null && remoteAddress.equalsIgnoreCase("114.130.64.36")) ))
//		{
//			try{response.getOutputStream().write("<reply>0</reply>".getBytes());}
//			catch(Exception e){e.printStackTrace();}
//			System.out.println("Wrong IP");
//			System.out.println("####################### TT TT2buetSMSapplication ##############################");
//			return null;
//		}

		System.out.println(
							app_id + "##" + 
							ssc_roll + "##" + 
							ssc_board + "##" + 
							ssc_year + "##" + 
							ssc_regno + "##" + 
							
							ssc_group +"##" +       // I will provide table
							ssc_gpa +"##" +           //SSC gpa in numeric
							ssc_eiin + "##" +						
							ssc_name + "##" + 
							ssc_father + "##" + 
							ssc_mother + "##" + 
							gender +"##" +           // M or F
							ssc_board_name + "##" +        // I will provide table
							
						    contact_no + "##" + 
							pin + "##" + 
						    trackid + "##" + 
						    paytime + "##" + 
							paymobile + "##" + 

							quota + "##" + 						//EQ,FQ,SQ           iquotaeq,iquotafq,iquotasq,iquotayn
							eiin + "##" + 
							shift + "##" + 
							medium + "##" + 
							group + "##" +
							
		
		"delwar");
		try
		{
			if(app_id!=null && ssc_roll!=null && ssc_board!=null && ssc_year!=null && ssc_regno!=null && ssc_group!=null && ssc_gpa!=null
					 && ssc_eiin!=null && ssc_name!=null && ssc_father!=null && ssc_mother!=null && gender!=null && ssc_board_name!=null
					 && quota!=null && eiin!=null && shift!=null && medium!=null
					 && group!=null && trackid!=null && contact_no!=null && pin!=null && paytime!=null && paymobile!=null)
			{

//				if(ssc_board_name.equalsIgnoreCase("tec"))
//					ssc_board_name="BTEB";
				ApplicationDAO adao = new ApplicationDAO();
				if(adao.InsertSMSapplication(app_id,ssc_roll,ssc_board,ssc_year,ssc_regno,ssc_group,ssc_gpa
						,ssc_eiin,ssc_name,ssc_father,ssc_mother,gender,ssc_board_name
						 ,trackid,contact_no,quota,eiin,shift,medium,group,pin,paytime,paymobile ))
				{
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write("<reply>SUCCESS.</reply>");
					System.out.println("####################### TT TT2buetSMSapplication ##############################");
					return null;
				}
				else
				{
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write("</reply>NOT SUCCESS.</reply>");
					System.out.println("####################### TT TT2buetSMSapplication ##############################");
					return null;
				}
				
			}
			else
			{

				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("</reply>NOT SUCCESS.</reply>");
				return null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public String PaymentOapplication()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		String xForward=ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via");
		String remoteAddress=ServletActionContext.getRequest().getRemoteAddr();
		
		System.out.println("####################### TT PaymentOapplication ##############################");
		if(!( (xForward!=null && xForward.startsWith("103.230.104.217")) || 
				(via!=null && via.startsWith("103.230.104.217")) || 
				(remoteAddress!=null && remoteAddress.startsWith("103.230.104.217")) ))
		{
			System.out.println("Wrong IP :"+remoteAddress+":"+via+":"+xForward);
			System.out.println("####################### TT PaymentOapplication ##############################");
			try{response.getOutputStream().write("<reply>0</reply>".getBytes());}
			catch(Exception e){e.printStackTrace();}
			return null;
		}
		
		contact_no = contact_no.substring(contact_no.length()-11);
		System.out.println(

							app_id + "##" + 
						    ssc_board + "##" + 
						    ssc_roll + "##" + 
						    ssc_year + "##" + 
						    ssc_regno + "##" + 
							
							ssc_group + "##" + 
							ssc_gpa + "##" + 
							ssc_eiin + "##" + 
							ssc_name + "##" + 
							ssc_father + "##" + 
							ssc_mother + "##" + 
							gender + "##" + 
							ssc_board_name + "##" + 
													
						    contact_no + "##" + 
							pin + "##" + 
						    trackid + "##" + 
						    paytime + "##" + 
							paymobile + "##" + 

		"delwar");

//							paytime.substring(0,17)+"delwar");
		
		try
		{
			if(app_id!=null && ssc_roll!=null && ssc_board!=null && ssc_year!=null && ssc_regno!=null && ssc_group!=null && ssc_gpa!=null
					 && ssc_eiin!=null && ssc_name!=null && ssc_father!=null && ssc_mother!=null && gender!=null && ssc_board_name!=null
					 && trackid!=null && contact_no!=null && pin!=null && paytime!=null && paymobile!=null)
			{
				System.out.println("####################### TT Web Payment ##############################");

//				receiveWebApplication abc= new receiveWebApplication();
//				abc.setApp_id(app_id);
//				abc.setContact_no(contact_no);
//				abc.setSsc_board(ssc_board);
//				abc.setSsc_gpa(ssc_gpa);
//				abc.setSsc_roll(ssc_roll);
//				abc.setSsc_year(ssc_year);
//				abc.setTrackid(trackid);
//				Thread thread = new Thread(abc);
//				thread.start();
//				response.setCharacterEncoding("UTF-8");
//				response.getWriter().write("<reply>SUCCESS.</reply>");
//				System.out.println("####################### TT Web Payment ##############################");
//				return null;
				
				
				
				ApplicationDAO adao = new ApplicationDAO();
				if(adao.PaymentOapplication2(app_id,ssc_roll,ssc_board,ssc_year,ssc_regno,ssc_group,ssc_gpa
						,ssc_eiin,ssc_name,ssc_father,ssc_mother,gender,ssc_board_name
						 ,trackid,contact_no,pin,paytime,paymobile)>0)
				{
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write("<reply>SUCCESS.</reply>");
					System.out.println("####################### TT Web Payment ##############################");
					return null;
				}
				else
				{
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write("<reply>NOT SUCCESS.</reply>");
					System.out.println("####################### TT Web Payment ##############################");
					return null;
				}

			}
			else
			{

				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("<reply>NOT SUCCESS.</reply>");
			}
		}
		catch(Exception e)
		{
			try{
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("</reply>NOT SUCCESS.</reply>");
			}catch(Exception e1){e1.printStackTrace();}
			e.printStackTrace();
		}		
		
		return null;
	}
	public String conPayment()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		String xForward=ServletActionContext.getRequest().getHeader("X-Forwarded-For");
		String via=ServletActionContext.getRequest().getHeader("Via");
		String remoteAddress=ServletActionContext.getRequest().getRemoteAddr();
		
		System.out.println("####################### TT Con Payment ##############################");
		if(!( (xForward!=null && xForward.startsWith("103.230.104.217")) || 
				(via!=null && via.startsWith("103.230.104.217")) || 
				(remoteAddress!=null && remoteAddress.startsWith("103.230.104.217")) ))//103.230.104.206
		{
			System.out.println("Wrong IP :"+remoteAddress+":"+via+":"+xForward);
			System.out.println("####################### TT Con Payment ##############################");
			try{response.getOutputStream().write("<reply>0</reply>".getBytes());}
			catch(Exception e){e.printStackTrace();}
			return null;
		}
		
		paymobile = paymobile.substring(paymobile.length()-11);
		System.out.println(

							app_id + "##" + 
						    trackid + "##" + 
						    paytime + "##" + 
							paymobile + "##" + 

		"delwar");

//							paytime.substring(0,17)+"delwar");
		
		try
		{
			if(app_id!=null && trackid!=null && paytime!=null && paymobile!=null)
			{
				ResultLog rl = new ResultLog();
				rl.setAppid(app_id);
				rl.setTrackid(trackid);
				rl.setPaytime(paytime);    	
				rl.setPaymobile(paymobile);
				rl.setOp("ConPayment");
		    	Thread thread = new Thread(rl);
				thread.start();	
				
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("<reply>SUCCESS.</reply>");
				System.out.println("####################### TT Con Payment ##############################");
				return null;
				

			}
			else
			{

				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("<reply>NOT SUCCESS.</reply>");
			}
		}
		catch(Exception e)
		{
			try{
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("</reply>NOT SUCCESS.</reply>");
			}catch(Exception e1){e1.printStackTrace();}
			e.printStackTrace();
		}		
		
		return null;
	}
	
	
	public String ListApplication()
	{
		System.out.println("####################### TT ListApplication ##############################");
		System.out.println(app_id + "##" );		
		System.out.println("####################### TT ListApplication ##############################");
		try
		{
//			if(trackid!=null && app_id1!=null && paytime!=null)
//			{
//				ApplicationDAO adao = new ApplicationDAO();
//				adao.PaymentOapplication(app_id1, trackid, paytime);
//				HttpServletResponse response = ServletActionContext.getResponse();
//				response.setCharacterEncoding("UTF-8");
//				response.getWriter().write("</reply>SUCCESS.</reply>");
//			}
//			else
//			{
//				HttpServletResponse response = ServletActionContext.getResponse();
//				response.setCharacterEncoding("UTF-8");
//				response.getWriter().write("</reply>NOT SUCCESS.</reply>");
//			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("</reply>API hit</reply>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		
		return null;
	}
	public String P1Application()
	{
		System.out.println("####################### TT P1Application ##############################");
		System.out.println( app_id + "##" + priority );		
		System.out.println("####################### TT P1Application ##############################");
		try
		{
//			if(trackid!=null && app_id1!=null && paytime!=null)
//			{
//				ApplicationDAO adao = new ApplicationDAO();
//				adao.PaymentOapplication(app_id1, trackid, paytime);
//				HttpServletResponse response = ServletActionContext.getResponse();
//				response.setCharacterEncoding("UTF-8");
//				response.getWriter().write("</reply>SUCCESS.</reply>");
//			}
//			else
//			{
//				HttpServletResponse response = ServletActionContext.getResponse();
//				response.setCharacterEncoding("UTF-8");
//				response.getWriter().write("</reply>NOT SUCCESS.</reply>");
//			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("</reply>API hit</reply>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String trackid;
	private String pin;
	private String paymobile;
	private String name;
	private String gender;
	private String quota;
	private String ssc_board;
	private String ssc_roll;
	private String ssc_year;
	private String ssc_regno;
	private String ssc_group;
	private String ssc_name;
	private String ssc_father;
	private String ssc_mother;
	private String ssc_gpa;
	private String ssc_eiin;
	private String ssc_board_name;
	private String contact_no;
	private String eiin;
	private String group;
	private String shift;
	private String medium;
	private String priority;
	private String paytime;
	private String operator;
	
	private String app_id;
	private String app_id1;	
	private String op;
	private boolean NotSend = false;
	
	
	private ApplicantDTO applicant;
	private String[] eiinList;
	private String[] groupList;
	private String[] shiftList;
	private String[] versionList;
	private String[] priorityList;
	

	public String[] getEiinList() {
		return eiinList;
	}

	public void setEiinList(String[] eiinList) {
		this.eiinList = eiinList;
	}

	public String[] getGroupList() {
		return groupList;
	}

	public void setGroupList(String[] groupList) {
		this.groupList = groupList;
	}

	public String[] getShiftList() {
		return shiftList;
	}

	public void setShiftList(String[] shiftList) {
		this.shiftList = shiftList;
	}

	public String[] getVersionList() {
		return versionList;
	}

	public void setVersionList(String[] versionList) {
		this.versionList = versionList;
	}

	public String[] getPriorityList() {
		return priorityList;
	}

	public void setPriorityList(String[] priorityList) {
		this.priorityList = priorityList;
	}

	public ApplicantDTO getApplicant() {
		return applicant;
	}

	public void setApplicant(ApplicantDTO applicant) {
		this.applicant = applicant;
	}

	public String getTrackid() {
		return trackid;
	}
	public void setTrackid(String trackid) {
		this.trackid = trackid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getQuota() {
		return quota;
	}
	public void setQuota(String quota) {
		this.quota = quota;
	}
	public String getSsc_board() {
		return ssc_board;
	}
	public void setSsc_board(String ssc_board) {
		this.ssc_board = ssc_board;
	}
	public String getSsc_roll() {
		return ssc_roll;
	}
	public void setSsc_roll(String ssc_roll) {
		this.ssc_roll = ssc_roll;
	}
	public String getSsc_year() {
		return ssc_year;
	}
	public void setSsc_year(String ssc_year) {
		this.ssc_year = ssc_year;
	}
	public String getSsc_regno() {
		return ssc_regno;
	}
	public void setSsc_regno(String ssc_regno) {
		this.ssc_regno = ssc_regno;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public String getEiin() {
		return eiin;
	}
	public void setEiin(String eiin) {
		this.eiin = eiin;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getApp_id1() {
		return app_id1;
	}
	public void setApp_id1(String app_id1) {
		this.app_id1 = app_id1;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
	
	public String staticSMS()
	{
		ApplicationDAO.sendSMS();
//		int total=0;
//		Connection conn = ConnectionManager.getReadConnection();
//		String sql= " Select mobile, text from SENDSMS order by mobile";
//		PreparedStatement stmt = null;
//		ResultSet r = null;
//		   
//		try
//		{
//			stmt = conn.prepareStatement(sql);
//			r = stmt.executeQuery();
//			while (r.next())
//			{
//				SmsSender smsSender=new SmsSender();
//				smsSender.setMobile(r.getString("MOBILE"));
//				smsSender.setText(r.getString("TEXT"));
//				smsSender.setOperation("textMSG");
//				Thread thread = new Thread(smsSender);
//				thread.start();
//				total++;
//				if(total==100)
//				{
//					total=0;
//					try{Thread.sleep(2000);}catch(Exception e){e.printStackTrace();};
//				}
//			}
//		} 
//		catch (Exception e){e.printStackTrace();}
// 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
//			{e.printStackTrace();}stmt = null;conn = null;}
		
		return null;
	}
	
	public String staticSMSPopulate()
	{
		ApplicationDAO.sendSMSPopulate();
		return null;
	}
	public static void main(String[] args)
	{
		staticSMSTT();
	}
	public static String staticSMSTT()
	{
		int total=0;
		Connection conn = ConnectionManager.getConnection();
		String sql= " Select mobile, text, application_id from SENDSMSTT";
		PreparedStatement stmt = null;
		ResultSet r = null;
		   
		try
		{
			stmt = conn.prepareStatement(sql);
			r = stmt.executeQuery();
			while (r.next())
			{
				SmsSender smsSender=new SmsSender();
				smsSender.setAppid(r.getString("application_id"));
				smsSender.setMobile(r.getString("MOBILE"));
				smsSender.setText(r.getString("TEXT"));
				smsSender.setOperation("sendResult_TT");
				Thread thread = new Thread(smsSender);
				thread.start();
				total++;
				if(total==50)
				{
					total=0;
					try{Thread.sleep(5000);}catch(Exception e){e.printStackTrace();};
				}
			}
		} 
		catch (Exception e){e.printStackTrace();}
 		finally{try{stmt.close();ConnectionManager.closeConnection(conn);} catch (Exception e)
			{e.printStackTrace();}stmt = null;conn = null;}
		
		return null;
	}
	public String getSsc_group() {
		return ssc_group;
	}
	public void setSsc_group(String ssc_group) {
		this.ssc_group = ssc_group;
	}
	public String getSsc_name() {
		return ssc_name;
	}
	public void setSsc_name(String ssc_name) {
		this.ssc_name = ssc_name;
	}
	public String getSsc_father() {
		return ssc_father;
	}
	public void setSsc_father(String ssc_father) {
		this.ssc_father = ssc_father;
	}
	public String getSsc_mother() {
		return ssc_mother;
	}
	public void setSsc_mother(String ssc_mother) {
		this.ssc_mother = ssc_mother;
	}
	public String getSsc_gpa() {
		return ssc_gpa;
	}
	public void setSsc_gpa(String ssc_gpa) {
		this.ssc_gpa = ssc_gpa;
	}
	public String getSsc_eiin() {
		return ssc_eiin;
	}
	public void setSsc_eiin(String ssc_eiin) {
		this.ssc_eiin = ssc_eiin;
	}
	public String getSsc_board_name() {
		return ssc_board_name;
	}
	public void setSsc_board_name(String ssc_board_name) {
		this.ssc_board_name = ssc_board_name;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getPaymobile() {
		return paymobile;
	}
	public void setPaymobile(String paymobile) {
		this.paymobile = paymobile;
	}


	
	
	
	
	
	
	
	
	
}
