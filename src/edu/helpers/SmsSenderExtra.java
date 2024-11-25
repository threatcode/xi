package edu.helpers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import edu.dao.application.ApplicationDAO;

public class SmsSenderExtra implements Runnable {

	private String mobile;
	private String otp;
	private String appid;
	private String apptime;
	private String roll;
	private String pass;
	private String name;
	private String operation;
	
	private String text;


	public void run() {
		
		/*if (operation.equalsIgnoreCase("boardmessage"))
			sendBoardMessage_TT();
				
		           //TT
		if (operation.equalsIgnoreCase("textMSGTT"))
			sendTextMSG_TT();                 //TT
		if (operation.equalsIgnoreCase("sendResult_TT"))
			sendResult_TT();                 //TT
		*/

	}


	public void sendTextMSG_TT() {
		if(1==1) return;
		try {
			StringBuilder urlString = new StringBuilder(
					"http://123.49.3.58:8081/web_send_sms.php?")
					.append("ms="+ URLEncoder.encode("88" + this.mobile,"UTF-8"))
					.append("&txt="+ URLEncoder.encode(this.text,"UTF-8"))
					.append("&username=" + URLEncoder.encode("IICT", "UTF-8"))
					.append("&password="+ URLEncoder.encode("iict2341", "UTF-8"));
			URL smsUrl;
			smsUrl = new URL(urlString.toString());
			URLConnection urlConnector = smsUrl.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnector.getInputStream()));
			String inputLine;
			String inputLine1 = "";
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				if (inputLine != null)
					inputLine1 += inputLine;
			}
			in.close();
			ApplicationDAO.sentAppSMS(this.appid, this.mobile);
			
			
			System.out.println("SMS==" + inputLine1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendResult_TT() {
		if(1==1) return;
		try {
			this.mobile = ApplicationDAO.getMobileNo(this.appid);
			StringBuilder urlString = new StringBuilder(
					"http://123.49.3.58:8081/web_send_sms.php?")
					.append("ms="+ URLEncoder.encode("88" + this.mobile,"UTF-8"))
					.append("&txt="+ URLEncoder.encode(this.text,"UTF-8"))
					.append("&username=" + URLEncoder.encode("IICT", "UTF-8"))
					.append("&password="+ URLEncoder.encode("iict2341", "UTF-8"));
			URL smsUrl;
			smsUrl = new URL(urlString.toString());
			URLConnection urlConnector = smsUrl.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnector.getInputStream()));
			String inputLine;
			String inputLine1 = "";
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				if (inputLine != null)
					inputLine1 += inputLine;
			}
			in.close();
			ApplicationDAO.sentAppSMS(this.appid, this.mobile);
			System.out.println("SMS==" + inputLine1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	
	public void sendBoardMessage_TT() {
		if(1==1) return;
		try {
			/*StringBuilder urlString = new StringBuilder(
					"http://123.49.3.58:8081/web_send_sms.php?").append("ms=" + URLEncoder.encode("88" + this.mobile, "UTF-8"))
					.append("&txt=" + URLEncoder.encode("Your OTP is : " + this.otp, "UTF-8")).append("&username=" + URLEncoder.encode("IICT", "UTF-8"))
					.append("&password=" + URLEncoder.encode("iict2341", "UTF-8"));*/
			StringBuilder urlString = new StringBuilder(
					"http://bulksms.teletalk.com.bd/link_sms_send.php?").append("op=" + URLEncoder.encode("SMS", "UTF-8")).append("&user=" + URLEncoder.encode("IICT", "UTF-8"))
					.append("&pass=" + URLEncoder.encode("iict369#^(buet", "UTF-8")).append("&mobile=" + URLEncoder.encode("88" + this.mobile, "UTF-8")).append("&charset=" + URLEncoder.encode("UTF-8", "UTF-8"))
					.append("&sms=" + URLEncoder.encode(this.text, "UTF-8"));
			
			URL smsUrl;
			smsUrl = new URL(urlString.toString());
			URLConnection urlConnector = smsUrl.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnector.getInputStream()));
			String inputLine;
			String inputLine1 = "";
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				if (inputLine != null)
					inputLine1 += inputLine;
			}
			in.close();
			System.out.println("SMS==" + inputLine1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public String getApptime() {
		return apptime;
	}

	public void setApptime(String apptime) {
		this.apptime = apptime;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
