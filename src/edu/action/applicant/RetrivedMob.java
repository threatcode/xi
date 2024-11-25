package edu.action.applicant;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.struts2.dispatcher.SessionMap;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import edu.action.common.BaseAction;
import edu.dao.applicant.RetrivedPinDAO;
import edu.dao.board.ApplicantInfoDAO;
import edu.dto.applicant.MobChangeDTO;
import edu.dto.applicant.PinRetrivalDTO;
import edu.helpers.SmsSender;

public class RetrivedMob extends BaseAction{
	
	private static final long serialVersionUID = 5427516708608248271L;
	private String ssc_roll;
	private String ssc_reg;
	private String ssc_board;
	private String ssc_passing_year;
	private String given_mobile_number;
	private String mother_name;
	private String captchaText;
	
	
	public String getCaptchaText() {
		return captchaText;
	}

	public void setCaptchaText(String captchaText) {
		this.captchaText = captchaText;
	}

	public String retrivedMob(){
		String checkSum=(String)session.get("CorrectAnswer");
		if(!captchaText.equalsIgnoreCase(checkSum)){
			try {
				response.setContentType("text/html");
				response.getWriter().write("Captcha Error");
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}
		((SessionMap<String,Object>)session).invalidate();
		
		String mess="";
     	RetrivedPinDAO retrivedPinDAO = new RetrivedPinDAO();
     	
		//PinRetrivalDTO pinRetrivalDTO=retrivedPinDAO.getRetrivedPin(ssc_roll,ssc_reg,ssc_board,ssc_passing_year,given_mobile_number);

		PinRetrivalDTO pinRetrivalDTO=null;
	
		MobChangeDTO   mobDTO =retrivedPinDAO.getMCInfo(ssc_roll,ssc_reg,ssc_board,ssc_passing_year,mother_name);
		
		int all=0;	
		
		if(mobDTO==null){
			
			all=retrivedPinDAO.insertAllAppMobile(ssc_roll, ssc_reg, ssc_board, ssc_passing_year, given_mobile_number, "Data not match");
			mess="&#x9AA;&#x9CD;&#x9B0;&#x9A6;&#x9A4;&#x9CD;&#x9A4;&#x20;&#x9A4;&#x9A5;&#x9CD;&#x9AF;&#x20;&#x9B8;&#x9A0;&#x9BF;&#x995;&#x20;&#x9A8;&#x9DF;&#x964;&#x20;&#x9B8;&#x9A0;&#x9BF;&#x995;&#x20;&#x9A4;&#x9A5;&#x9CD;&#x9AF;&#x20;&#x9AA;&#x9CD;&#x9B0;&#x9A6;&#x9BE;&#x9A8;&#x20;&#x995;&#x9B0;&#x964;";
		}
		else{
			if(RetrivedPinDAO.checMobile(given_mobile_number)>0){
				//check mobile unigeness
				all=retrivedPinDAO.insertAllAppMobile(ssc_roll, ssc_reg, ssc_board, ssc_passing_year, given_mobile_number, "Given Mob exists");
				mess = "&#2468;&#2507;&#2478;&#2494;&#2480; &#2478;&#2507;&#2476;&#2494;&#2439;&#2482; &#2472;&#2478;&#2509;&#2476;&#2480;&#2463;&#2495; &#2437;&#2472;&#2509;&#2479; &#2438;&#2476;&#2503;&#2470;&#2472;&#2503;&#2480; &#2460;&#2472;&#2509;&#2479; &#2476;&#2509;&#2479;&#2476;&#2489;&#2499;&#2468; &#2489;&#2527;&#2503;&#2459;&#2503;&#2404; &#2468;&#2507;&#2478;&#2494;&#2453;&#2503; &#2447;&#2439; &#2474;&#2480;&#2495;&#2476;&#2480;&#2509;&#2468;&#2472;&#2503;&#2480; &#2460;&#2472;&#2509;&#2479; &#2474;&#2509;&#2480;&#2527;&#2507;&#2460;&#2472;&#2496;&#2527; &#2453;&#2494;&#2455;&#2460;&#2474;&#2468;&#2509;&#2480;&#2488;&#2489; &#2472;&#2495;&#2460; &#2476;&#2507;&#2480;&#2509;&#2465;&#2503; &#2479;&#2507;&#2455;&#2494;&#2479;&#2507;&#2455; &#2453;&#2480;&#2468;&#2503; &#2489;&#2476;&#2503;&#2404;";
			}
			else if(mobDTO.getApplicationID()==null){
				//payment not found
				all=retrivedPinDAO.insertAllAppMobile(ssc_roll, ssc_reg, ssc_board, ssc_passing_year, given_mobile_number, "Payment not found");
				mess="&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x9B0;&#x20;&#x50;&#x61;&#x79;&#x6D;&#x65;&#x6E;&#x74;&#x20;&#x2013;&#x98F;&#x9B0;&#x20;&#x9A4;&#x9A5;&#x9CD;&#x9AF;&#x20;&#x9AA;&#x9BE;&#x993;&#x9DF;&#x9BE;&#x20;&#x9AF;&#x9BE;&#x9DF;&#x20;&#x9A8;&#x9BF;&#x964;";
			}
			else if(mobDTO.getAppWeb().equalsIgnoreCase("Y")){
				//Already  applied web
				all=retrivedPinDAO.insertAllAppMobile(ssc_roll, ssc_reg, ssc_board, ssc_passing_year, given_mobile_number, "Web yes");
				mess="&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x9B0;&#x20;&#x9AD;&#x9C1;&#x9B2;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x2E;&#x20;&#x20;&#x9A6;&#x9BF;&#x9DF;&#x9C7;&#x987;&#x20;&#x9A4;&#x9C1;&#x9AE;&#x9BF;&#x20;&#x57;&#x65;&#x62;&#x2D;&#x98F;&#x20;&#x986;&#x9AC;&#x9C7;&#x9A6;&#x9A8;&#x20;&#x995;&#x9B0;&#x9C7;&#x99B;&#x20;&#x9AC;&#x9B2;&#x9C7;&#x20;&#x4F;&#x6E;&#x6C;&#x69;&#x6E;&#x65;&#x2D;&#x98F;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x2E;&#x20;&#x20;&#x9AA;&#x9B0;&#x9BF;&#x9AC;&#x9B0;&#x9CD;&#x9A4;&#x9A8;&#x20;&#x995;&#x9B0;&#x9BE;&#x20;&#x9B8;&#x9AE;&#x9CD;&#x9AD;&#x9AC;&#x20;&#x9A8;&#x9DF;&#x964;&#x20;&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x995;&#x9C7;&#x20;&#x98F;&#x987;&#x20;&#x9AA;&#x9B0;&#x9BF;&#x9AC;&#x9B0;&#x9CD;&#x9A4;&#x9A8;&#x9C7;&#x9B0;&#x20;&#x99C;&#x9A8;&#x9CD;&#x9AF;&#x20;&#x9AA;&#x9CD;&#x9B0;&#x9DF;&#x9CB;&#x99C;&#x9A8;&#x9C0;&#x9DF;&#x20;&#x995;&#x9BE;&#x997;&#x99C;&#x9AA;&#x9A4;&#x9CD;&#x9B0;&#x9B8;&#x9B9;&#x20;&#x9A8;&#x9BF;&#x99C;&#x20;&#x9AC;&#x9CB;&#x9B0;&#x9CD;&#x9A1;&#x9C7;&#x20;&#x9AF;&#x9CB;&#x997;&#x9BE;&#x9AF;&#x9CB;&#x997;&#x20;&#x995;&#x9B0;&#x9A4;&#x9C7;&#x20;&#x9B9;&#x9AC;&#x9C7;&#x964;";				
			}
			else if(mobDTO.getAppSms().equalsIgnoreCase("Y") && RetrivedPinDAO.GetSmsCount(mobDTO.getApplicationID())>1 ){
					//SMS more than one	
				all=retrivedPinDAO.insertAllAppMobile(ssc_roll, ssc_reg, ssc_board, ssc_passing_year, given_mobile_number, "SMS more than one");
				mess="&#x9A4;&#x9C1;&#x9AE;&#x9BF;&#x20;&#x9AD;&#x9C1;&#x9B2;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x2E;&#x20;&#x20;&#x9A6;&#x9BF;&#x9DF;&#x9C7;&#x20;&#x53;&#x4D;&#x53;&#x2D;&#x98F;&#x20;&#x98F;&#x995;&#x9C7;&#x9B0;&#x20;&#x985;&#x9A7;&#x9BF;&#x995;&#x20;&#x986;&#x9AC;&#x9C7;&#x9A6;&#x9A8;&#x20;&#x995;&#x9B0;&#x9C7;&#x99B;&#x20;&#x9AC;&#x9B2;&#x9C7;&#x20;&#x4F;&#x6E;&#x6C;&#x69;&#x6E;&#x65;&#x2D;&#x98F;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x2E;&#x20;&#x20;&#x9AA;&#x9B0;&#x9BF;&#x9AC;&#x9B0;&#x9CD;&#x9A4;&#x9A8;&#x20;&#x995;&#x9B0;&#x9BE;&#x20;&#x9B8;&#x9AE;&#x9CD;&#x9AD;&#x9AC;&#x20;&#x9A8;&#x9DF;&#x964;&#x20;&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x995;&#x9C7;&#x20;&#x98F;&#x987;&#x20;&#x9AA;&#x9B0;&#x9BF;&#x9AC;&#x9B0;&#x9CD;&#x9A4;&#x9A8;&#x9C7;&#x9B0;&#x20;&#x99C;&#x9A8;&#x9CD;&#x9AF;&#x20;&#x9AA;&#x9CD;&#x9B0;&#x9DF;&#x9CB;&#x99C;&#x9A8;&#x9C0;&#x9DF;&#x20;&#x995;&#x9BE;&#x997;&#x99C;&#x9AA;&#x9A4;&#x9CD;&#x9B0;&#x9B8;&#x9B9;&#x20;&#x9A8;&#x9BF;&#x99C;&#x20;&#x9AC;&#x9CB;&#x9B0;&#x9CD;&#x9A1;&#x9C7;&#x20;&#x9AF;&#x9CB;&#x997;&#x9BE;&#x9AF;&#x9CB;&#x997;&#x20;&#x995;&#x9B0;&#x9A4;&#x9C7;&#x20;&#x9B9;&#x9AC;&#x9C7;&#x964;";				
			}
			else if(mobDTO.getAppSms().equalsIgnoreCase("Y") && RetrivedPinDAO.GetSmsCount(mobDTO.getApplicationID())==1 && mobDTO.getTTNumber().equalsIgnoreCase("Y") ){
				//SMS one+ wempayment yes		
				all=retrivedPinDAO.insertAllAppMobile(ssc_roll, ssc_reg, ssc_board, ssc_passing_year, given_mobile_number, "Sms Web pay");
				mess="&#x9A4;&#x9C1;&#x9AE;&#x9BF;&#x20;&#x9AD;&#x9C1;&#x9B2;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x2E;&#x20;&#x20;&#x9A6;&#x9BF;&#x9DF;&#x9C7;&#x20;&#x53;&#x4D;&#x53;&#x2D;&#x98F;&#x20;&#x986;&#x9AC;&#x9C7;&#x9A6;&#x9A8;&#x20;&#x995;&#x9B0;&#x9C7;&#x99B;&#x20;&#x98F;&#x9AC;&#x982;&#x20;&#x98F;&#x995;&#x987;&#x20;&#x9A8;&#x9AE;&#x9CD;&#x9AC;&#x9B0;&#x20;&#x9A6;&#x9BF;&#x9DF;&#x9C7;&#x20;&#x985;&#x9A8;&#x9B2;&#x9BE;&#x987;&#x9A8;&#x9C7;&#x20;&#x986;&#x9AC;&#x9C7;&#x9A6;&#x9A8;&#x9C7;&#x9B0;&#x20;&#x99C;&#x9A8;&#x9CD;&#x9AF;&#x20;&#x50;&#x61;&#x79;&#x6D;&#x65;&#x6E;&#x74;&#x20;&#x995;&#x9B0;&#x9C7;&#x99B;&#x20;&#x9AC;&#x9B2;&#x9C7;&#x20;&#x4F;&#x6E;&#x6C;&#x69;&#x6E;&#x65;&#x2D;&#x98F;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x2E;&#x20;&#x20;&#x9AA;&#x9B0;&#x9BF;&#x9AC;&#x9B0;&#x9CD;&#x9A4;&#x9A8;&#x20;&#x995;&#x9B0;&#x9BE;&#x20;&#x9B8;&#x9AE;&#x9CD;&#x9AD;&#x9AC;&#x20;&#x9A8;&#x9DF;&#x964;&#x20;&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x995;&#x9C7;&#x20;&#x98F;&#x987;&#x20;&#x9AA;&#x9B0;&#x9BF;&#x9AC;&#x9B0;&#x9CD;&#x9A4;&#x9A8;&#x9C7;&#x9B0;&#x20;&#x99C;&#x9A8;&#x9CD;&#x9AF;&#x20;&#x9AA;&#x9CD;&#x9B0;&#x9DF;&#x9CB;&#x99C;&#x9A8;&#x9C0;&#x9DF;&#x20;&#x995;&#x9BE;&#x997;&#x99C;&#x9AA;&#x9A4;&#x9CD;&#x9B0;&#x9B8;&#x9B9;&#x20;&#x9A8;&#x9BF;&#x99C;&#x20;&#x9AC;&#x9CB;&#x9B0;&#x9CD;&#x9A1;&#x9C7;&#x20;&#x9AF;&#x9CB;&#x997;&#x9BE;&#x9AF;&#x9CB;&#x997;&#x20;&#x995;&#x9B0;&#x9A4;&#x9C7;&#x20;&#x9B9;&#x9AC;&#x9C7;&#x964;";				
			}
			else if(retrivedPinDAO.isValidMobUpdate(mobDTO.getApplicationID()))
			{
				// Already mob changed
				all=retrivedPinDAO.insertAllAppMobile(ssc_roll, ssc_reg, ssc_board, ssc_passing_year, given_mobile_number, "Already changed");
				mess="&#x9A4;&#x9C1;&#x9AE;&#x9BF;&#x20;&#x4F;&#x6E;&#x6C;&#x69;&#x6E;&#x65;&#x2D;&#x98F;&#x20;&#x987;&#x9A4;&#x9BF;&#x9AE;&#x9A7;&#x9CD;&#x9AF;&#x9C7;&#x20;&#x98F;&#x995;&#x9AC;&#x9BE;&#x9B0;&#x20;&#x9AA;&#x9B0;&#x9BF;&#x9AC;&#x9B0;&#x9CD;&#x9A4;&#x9A8;&#x20;&#x995;&#x9B0;&#x9C7;&#x99B;&#x964;&#x20;&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x9B0;&#x20;&#x4F;&#x6E;&#x6C;&#x69;&#x6E;&#x65;&#x2D;&#x98F;&#x20;&#x9AA;&#x9B0;&#x9BF;&#x9AC;&#x9B0;&#x9CD;&#x9A4;&#x9A8;&#x9C7;&#x9B0;&#x20;&#x986;&#x9B0;&#x20;&#x9B8;&#x9C1;&#x9AF;&#x9CB;&#x997;&#x20;&#x9A8;&#x9C7;&#x987;&#x964;&#x20;&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x995;&#x9C7;&#x20;&#x9AA;&#x9C1;&#x9A8;&#x9B0;&#x9BE;&#x9DF;&#x20;&#x98F;&#x987;&#x20;&#x9AA;&#x9B0;&#x9BF;&#x9AC;&#x9B0;&#x9CD;&#x9A4;&#x9A8;&#x9C7;&#x9B0;&#x20;&#x99C;&#x9A8;&#x9CD;&#x9AF;&#x20;&#x9AA;&#x9CD;&#x9B0;&#x9DF;&#x9CB;&#x99C;&#x9A8;&#x9C0;&#x9DF;&#x20;&#x995;&#x9BE;&#x997;&#x99C;&#x9AA;&#x9A4;&#x9CD;&#x9B0;&#x9B8;&#x9B9;&#x20;&#x9A8;&#x9BF;&#x99C;&#x20;&#x9AC;&#x9CB;&#x9B0;&#x9CD;&#x9A1;&#x9C7;&#x20;&#x9AF;&#x9CB;&#x997;&#x9BE;&#x9AF;&#x9CB;&#x997;&#x20;&#x995;&#x9B0;&#x9A4;&#x9C7;&#x20;&#x9B9;&#x9AC;&#x9C7;&#x964;";
			}
			else if(retrivedPinDAO.isValidMobDate(ssc_roll,ssc_board,ssc_passing_year))
			{
				all=retrivedPinDAO.insertAllAppMobile(ssc_roll, ssc_reg, ssc_board, ssc_passing_year, given_mobile_number, "24 hours");
				mess="&#x986;&#x9AA;&#x9A8;&#x9BE;&#x9B0;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x20;&#x99F;&#x9BF;&#x20;&#x985;&#x9B0;&#x9CD;&#x9A5;&#x20;&#x9AA;&#x9B0;&#x9BF;&#x9B6;&#x9CB;&#x9A7;&#x9C7;&#x9B0;&#x20;&#x9E8;&#x9EA;&#x20;&#x998;&#x9A8;&#x9CD;&#x99F;&#x9BE;&#x20;&#x9AA;&#x9B0;&#x9C7;&#x20;&#x9AA;&#x9B0;&#x9BF;&#x9AC;&#x9B0;&#x9CD;&#x9A4;&#x9A8;&#x20;&#x995;&#x9B0;&#x9BE;&#x20;&#x9AF;&#x9BE;&#x9AC;&#x9C7;&#x964;&#x20;&#x9AA;&#x9B0;&#x9AC;&#x9B0;&#x9CD;&#x9A4;&#x9C0;&#x9A4;&#x9C7;&#x20;&#x99A;&#x9C7;&#x9B7;&#x9CD;&#x99F;&#x9BE;&#x20;&#x995;&#x9B0;&#x9C1;&#x9A8;&#x964;&#x20;";
			}
			else
			{
				
				String lcs = LCS.LCSAlgorithm(given_mobile_number, mobDTO.getMobile());
				//String lcs2 = LCS.LCSAlgorithm2(given_mobile_number, mobDTO.getMobile());
				
				
				
				if(lcs.length()==11)
				{
					all=retrivedPinDAO.insertAllAppMobile(ssc_roll, ssc_reg, ssc_board, ssc_passing_year, given_mobile_number, "Same Mobile");
					mess="&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x9B0;&#x20;&#x98F;&#x996;&#x9A8;&#x20;&#x9AA;&#x9CD;&#x9B0;&#x9A6;&#x9A4;&#x9CD;&#x9A4;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x20;&#x98F;&#x9AC;&#x982;&#x20;&#x9AA;&#x9C2;&#x9B0;&#x9CD;&#x9AC;&#x9C7;&#x20;&#x9AA;&#x9CD;&#x9B0;&#x9A6;&#x9A4;&#x9CD;&#x9A4;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x20;&#x98F;&#x995;&#x987;&#x964;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x20;&#x9AA;&#x9B0;&#x9BF;&#x9AC;&#x9B0;&#x9CD;&#x9A4;&#x9A8;&#x9C7;&#x9B0;&#x20;&#x9AA;&#x9CD;&#x9B0;&#x9DF;&#x9CB;&#x99C;&#x9A8;&#x20;&#x9A8;&#x9C7;&#x987;&#x964;&#x20;&#x985;&#x9A8;&#x9CD;&#x9AF;&#x20;&#x9AF;&#x9C7;&#x20;&#x995;&#x9CB;&#x9A8;&#x20;&#x9B8;&#x9AE;&#x9B8;&#x9CD;&#x9AF;&#x9BE;&#x9B0;&#x20;&#x99C;&#x9A8;&#x9CD;&#x9AF;&#x20;&#x9AC;&#x9CB;&#x9B0;&#x9CD;&#x9A1;&#x9C7;&#x20;&#x9AF;&#x9CB;&#x997;&#x9BE;&#x9AF;&#x9CB;&#x997;&#x20;&#x995;&#x9B0;&#x964;";
				}				
				else if(lcs.length()>7)
				{
					
					int cnt = retrivedPinDAO.updateAppMobile(mobDTO.getApplicationID(), given_mobile_number, mobDTO.getMobile());
					if(cnt==1)
					{
						
						//sendOapplication(mobDTO.getApplicationID(),mobDTO.getBoardName(),ssc_roll.trim(),ssc_passing_year.trim(),ssc_reg.trim(),given_mobile_number.trim(),"CHANGE");
		            	
			//			sendOapplication(ssc_roll.trim(), ssc_reg.trim(), mobDTO.getBoardName(), ssc_passing_year.trim(), given_mobile_number.trim(),"CHANGE");						
						
						
						
					
						/*int smss=sendMessMChangeGreen(given_mobile_number,"Your ("+ssc_roll+") contact number has been changed successfully. - Board Authority.");
						
						int smss1=sendMessMChangeGreen(mobDTO.getMobile(),"Your ("+ssc_roll+") contact number has been changed to a new number. If this change is unwanted to you, please contact to your board authority.");
						*/
						
						
						SmsSender smsSender=new SmsSender();
						smsSender.setOperation("sms");
						smsSender.setMobile(given_mobile_number);
						smsSender.setText("Your ("+ssc_roll+") contact number has been changed successfully. - Board Authority.");
						Thread thread = new Thread(smsSender);
						thread.start();
						
						smsSender=new SmsSender();
						smsSender.setOperation("sms");
						smsSender.setMobile(mobDTO.getMobile());
						smsSender.setText("Your ("+ssc_roll+") contact number has been changed to a new number. If this change is unwanted to you, please contact to your board authority.");
						thread = new Thread(smsSender);
						thread.start();
						
						
					
						
						
						
						// Successfully
						all=retrivedPinDAO.insertAllAppMobile(ssc_roll, ssc_reg, ssc_board, ssc_passing_year, given_mobile_number, "SUSSESS");
						mess="&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x9B0;&#x20;&#x9A8;&#x9A4;&#x9C1;&#x9A8;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x2E;&#x20;&#x99F;&#x9BF;&#x20;&#x9B8;&#x982;&#x9B0;&#x995;&#x9CD;&#x9B7;&#x9A3;&#x20;&#x995;&#x9B0;&#x9BE;&#x20;&#x9B9;&#x9DF;&#x9C7;&#x99B;&#x9C7;&#x964;&#x20;&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x9B0;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x2E;&#x20;&#x2013;&#x98F;&#x20;&#x98F;&#x995;&#x99F;&#x9BF;&#x20;&#x53;&#x4D;&#x53;&#x20;&#x9AA;&#x9BE;&#x9A0;&#x9BE;&#x9A8;&#x9CB;&#x20;&#x9B9;&#x9B2;&#x964;";
					}						
					else //
						mess="Change not done";	
					
				}			
				else 
				{//You can't change ur Contact No.
					all=retrivedPinDAO.insertAllAppMobile(ssc_roll, ssc_reg, ssc_board, ssc_passing_year, given_mobile_number, "LCS");
					mess="&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x9B0;&#x20;&#x9AD;&#x9C1;&#x9B2;&#x20;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x2E;&#x20;&#x20;&#x20;&#x98F;&#x9AC;&#x982;&#x20;&#x9AA;&#x9CD;&#x9B0;&#x9A6;&#x9A4;&#x9CD;&#x9A4;&#x20;&#x9B8;&#x9A0;&#x9BF;&#x995;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x2E;&#x2D;&#x98F;&#x9B0;&#x20;&#x9AE;&#x9A7;&#x9CD;&#x9AF;&#x9C7;&#x20;&#x9AA;&#x9B0;&#x9CD;&#x9AF;&#x9BE;&#x9AA;&#x9CD;&#x9A4;&#x20;&#x9AE;&#x9BF;&#x9B2;&#x20;&#x9AA;&#x9BE;&#x993;&#x9DF;&#x9BE;&#x20;&#x9AF;&#x9BE;&#x9DF;&#x20;&#x9A8;&#x9BF;&#x20;&#x9AC;&#x9B2;&#x9C7;&#x20;&#x20;&#x20;&#x43;&#x6F;&#x6E;&#x74;&#x61;&#x63;&#x74;&#x20;&#x4E;&#x6F;&#x2E;&#x20;&#x20;&#x20;&#x9B8;&#x982;&#x9B6;&#x9CB;&#x9A7;&#x9A8;&#x20;&#x9B8;&#x9AE;&#x9CD;&#x9AD;&#x9AC;&#x20;&#x9B9;&#x9B2;&#x20;&#x9A8;&#x9BE;&#x964;&#x20;&#x9A4;&#x9CB;&#x9AE;&#x9BE;&#x995;&#x9C7;&#x20;&#x98F;&#x987;&#x20;&#x9AA;&#x9B0;&#x9BF;&#x9AC;&#x9B0;&#x9CD;&#x9A4;&#x9A8;&#x9C7;&#x9B0;&#x20;&#x99C;&#x9A8;&#x9CD;&#x9AF;&#x20;&#x9AA;&#x9CD;&#x9B0;&#x9DF;&#x9CB;&#x99C;&#x9A8;&#x9C0;&#x9DF;&#x20;&#x995;&#x9BE;&#x997;&#x99C;&#x9AA;&#x9A4;&#x9CD;&#x9B0;&#x9B8;&#x9B9;&#x20;&#x9A8;&#x9BF;&#x99C;&#x20;&#x9AC;&#x9CB;&#x9B0;&#x9CD;&#x9A1;&#x9C7;&#x20;&#x9AF;&#x9CB;&#x997;&#x9BE;&#x9AF;&#x9CB;&#x997;&#x20;&#x995;&#x9B0;&#x9A4;&#x9C7;&#x20;&#x9B9;&#x9AC;&#x9C7;&#x964;";				
				}
			}
			
					
		}
				
		
		request.setAttribute("mess",mess);
		
		return "success";

	}
	
	public String getSsc_roll() {
		return ssc_roll;
	}
	public void setSsc_roll(String ssc_roll) {
		this.ssc_roll = ssc_roll;
	}
	
	public String getSsc_reg() {
		return ssc_reg;
	}

	public void setSsc_reg(String ssc_reg) {
		this.ssc_reg = ssc_reg;
	}

	public String getSsc_board() {
		return ssc_board;
	}
	public void setSsc_board(String ssc_board) {
		this.ssc_board = ssc_board;
	}
	public String getSsc_passing_year() {
		return ssc_passing_year;
	}
	public void setSsc_passing_year(String ssc_passing_year) {
		this.ssc_passing_year = ssc_passing_year;
	}
	public String getGiven_mobile_number() {
		return given_mobile_number;
	}
	public void setGiven_mobile_number(String given_mobile_number) {
		this.given_mobile_number = given_mobile_number;
	}
	
	
	public String getMother_name() {
		return mother_name;
	}

	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}

	private int sendMessMChangeGreen(String mobile, String mess)
	  {
	     
		
		
		try {        	
        	
       	   
            String token = "511640334b82da497dec66ce2be74b29"; //generate token from the control panel
           
            //String token = "69113704e9d1537d58c104b512db94f9";
            //String token = "7f101db62ae55bef458cb7619ea1171d";
            //String token = "7d21b957bcf61f4329185d22553b038f";
       	
       	
           StringBuilder urlString = new StringBuilder(
                   "http://sms.greenweb.com.bd/api.php?").append("token=" + URLEncoder.encode(token, "UTF-8")).append("&to=" + URLEncoder.encode(mobile, "UTF-8"))
                   .append("&message=" + URLEncoder.encode(mess, "UTF-8"));
                   
          
           URL smsUrl;
           smsUrl = new URL(urlString.toString());
           URLConnection urlConnector = smsUrl.openConnection();
           BufferedReader in = new BufferedReader(new InputStreamReader(
                   urlConnector.getInputStream()));
           String inputLine;
           String inputLine1 = "";
           while ((inputLine = in.readLine()) != null) {
        	   // ApplicationDAO.ApplicationSMSUpdate(this.appid, this.mobile);
           	//RetrivedPinDAO.sentAppSMS(this.appid, this.mobile);
              
               System.out.println(inputLine);
               if (inputLine != null)
                   inputLine1 += inputLine;
           }
           in.close();
           System.out.println("SMS==" + inputLine1);
           
           
           if(inputLine1.contains("Ok"))
	        	  return 1;
           
       } catch (Exception e) {
           e.printStackTrace();
       }	                   
	      
	      return 0;
	  }
	
	
	
		
	private String sendOapplication(String roll, String reg, String board, String year, 
			String contactno, String op)
	{

		String tmp="no";
		try{
			String generatedJSONString = "{\"roll\" :\""+roll+"\",\"reg\" :\""+reg+"\",\"board\" :\""+board+"\",\"year\" :\""+year
				+"\",\"contactno\" :\""+contactno+"\",\"op\" :\""+op+"\"}";
			String myUrlgoeshere = "http://103.230.104.217:9999/CAD/buetupTT.php";
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(myUrlgoeshere);
			StringEntity input = new StringEntity(generatedJSONString);
			input.setContentType("application/json;charset=UTF-8");
			postRequest.setEntity(input);
			input.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
			postRequest.setHeader("Accept", "application/json");
			postRequest.setEntity(input); 
			HttpResponse response = httpClient.execute(postRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output;
			String responseString = "";
			while ((output = br.readLine()) != null)
			{
				responseString=output;
			}
			httpClient.getConnectionManager().shutdown();
			JSONObject myResponse = new JSONObject(responseString.toString());
			System.out.println(myResponse.getString("status").toLowerCase());
			if(myResponse.getString("status").toLowerCase().contains(("yes")))
			{
				tmp = "yes";
			}
			if(myResponse.getString("status").toLowerCase().contains(("no data found.")))
			{
				tmp = "yes";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return tmp;

	}
	
	
	
	public String getBoardName(String id)
	{
		if(id.equalsIgnoreCase("10"))
			return "DHAKA";
		else if(id.equalsIgnoreCase("11"))
			return "CUMILLA";
		else if(id.equalsIgnoreCase("12"))
			return "RAJSHAHI";
		else if(id.equalsIgnoreCase("13"))
			return "JESSORE";
		else if(id.equalsIgnoreCase("14"))
			return "CHITTAGONG";
		else if(id.equalsIgnoreCase("15"))
			return "BARISAL";
		else if(id.equalsIgnoreCase("16"))
			return "SYLHET";
		else if(id.equalsIgnoreCase("18"))
			return "MADRASAH";
		else if(id.equalsIgnoreCase("17"))
			return "DINAJPUR";
		else if(id.equalsIgnoreCase("19"))
			return "TEC";
		else if(id.equalsIgnoreCase("20"))
			return "BOU";
		else if(id.equalsIgnoreCase("21"))
			return "MYMENSINGH";
		return "";
	}
	
	
	/*private String sendOapplication(String app_id, String ssc_board, String ssc_roll, String ssc_year, 
			String ssc_regno, String contact_no, String op)
	{
		String targetURL = "http://114.130.64.35:9999/CAD/buet2TTOapplication.php";
		URL url;
	    HttpURLConnection connection = null;  
	    
	    ApplicantInfoDAO aidao = new ApplicantInfoDAO();
	    
		try
		{
			StringBuilder urlParameter = new StringBuilder();
			urlParameter.append("app_id=").append(URLEncoder.encode(app_id, "UTF-8"));
			urlParameter.append("&ssc_board=").append(URLEncoder.encode(ssc_board, "UTF-8"));
			urlParameter.append("&ssc_roll=").append(URLEncoder.encode(ssc_roll, "UTF-8"));
			urlParameter.append("&ssc_year=").append(URLEncoder.encode(ssc_year, "UTF-8"));
			urlParameter.append("&ssc_regno=").append(URLEncoder.encode(ssc_regno, "UTF-8"));
			urlParameter.append("&contact_no=").append(URLEncoder.encode(contact_no, "UTF-8"));
			urlParameter.append("&op=").append(URLEncoder.encode(op, "UTF-8"));
			
			
			System.out.println(urlParameter.toString());
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
//		    	  ApplicationDAO adao = new ApplicationDAO();
//		    	  adao.setttsend(app_id);
		    	  System.out.println("Mobile number changed :"+app_id +":"+contact_no);
		    	  }

		      
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
	}*/
	
}
