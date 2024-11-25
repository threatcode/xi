package edu.helpers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONObject;

import edu.dao.application.ApplicationDAO;

public class SmsSender implements Runnable {

	private String mobile;
	private String text;
	private String operation;

	public void run() {
		if (operation.equalsIgnoreCase("sms"))
			sendSMS();
	}

	
	public void sendSMS() {
		if(this.mobile.substring(this.mobile.length()-11).substring(0, 3).equalsIgnoreCase("017") || this.mobile.substring(this.mobile.length()-11).substring(0, 3).equalsIgnoreCase("013"))
			sendSSLSMS();     ///   will be grameen phone
		else if(this.mobile.substring(this.mobile.length()-11).substring(0, 3).equalsIgnoreCase("018") || this.mobile.substring(this.mobile.length()-11).substring(0, 3).equalsIgnoreCase("016"))
			sendROBISMS();
		else if(this.mobile.substring(this.mobile.length()-11).substring(0, 3).equalsIgnoreCase("019") || this.mobile.substring(this.mobile.length()-11).substring(0, 3).equalsIgnoreCase("014"))
			sendSSLSMS();
		else
			sendSSLSMS();
	}	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}




	private void sendSSLSMS()
	{
		String targetURL = "http://sms.sslwireless.com/pushapi/dynamic_buet/server.php";
		URL url;
		HttpURLConnection connection = null;
		try {
			StringBuilder urlParameter = new StringBuilder();
			urlParameter.append("sid=").append(URLEncoder.encode("BUET", "UTF-8"));
			urlParameter.append("&user=").append(URLEncoder.encode("buetiict", "UTF-8"));
			urlParameter.append("&pass=").append(URLEncoder.encode("buet@123", "UTF-8"));
			urlParameter.append("&sms[0][0]=").append(URLEncoder.encode("88" + mobile, "UTF-8"));
			urlParameter.append("&sms[0][1]=").append(URLEncoder.encode(text, "UTF-8"));
			urlParameter.append("&sms[0][2]=").append(URLEncoder.encode("abc", "UTF-8"));
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length","" + Integer.toString(urlParameter.toString().getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameter.toString());
			wr.flush();
			wr.close();
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}			

	}
	private void sendROBISMS()
	{
		try {
			StringBuilder urlString = new StringBuilder(
					"https://api.mobireach.com.bd/SendTextMessage?").append("Username=" + URLEncoder.encode("iictbuet", "UTF-8")).append("&Password=" + URLEncoder.encode("Airtel@123", "UTF-8"))
					.append("&From=" + URLEncoder.encode("8801847121242", "UTF-8")).append("&To=" + URLEncoder.encode("88" + mobile, "UTF-8")).append("&charset=" + URLEncoder.encode("UTF-8", "UTF-8"))
					.append("&Message=" + URLEncoder.encode(text, "UTF-8"));
			URL smsUrl;
			smsUrl = new URL(urlString.toString());
			URLConnection urlConnector = smsUrl.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnector.getInputStream()));
			String inputLine;
			String inputLine1 = "";
			while ((inputLine = in.readLine()) != null) {
				if (inputLine != null)
					inputLine1 += inputLine;
			}
			in.close();
			System.out.println("SMS==" + "Hi delwar");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args)
	{
		try {
			String queryParams = "";
			String authorizationHeader = null;

			String baseUrl = "https://gpcmp.grameenphone.com/ecmapigw/webresources/ecmapigw.v2?username="+URLEncoder.encode("Dhakaboard226")+
			"&password="+URLEncoder.encode("buet1#IICT")+
			"&apicode="+URLEncoder.encode("1")+
			"&msisdn="+URLEncoder.encode("01511662266")+
			"&countrycode="+URLEncoder.encode("880")+
			"&cli="+URLEncoder.encode("INTERBOARD")+
			"&messagetype="+URLEncoder.encode("1")+
			"&message="+URLEncoder.encode("Test Message")+
			"&messageid=="+URLEncoder.encode("0");


			Object Data = ApiSSL.callByHttpGet(baseUrl, authorizationHeader,
			queryParams, "application/text");
			System.out.println("Result:\n" + Data);
			
//			StringBuilder urlString = new StringBuilder(
//					"https://cmp.grameenphone.com/gpcmpapi/messageplatform/controller.home?");
////			.append("username=" + URLEncoder.encode("Dhakaboard226", "UTF-8")).append("&password=" + URLEncoder.encode("$485TEs%$wer", "UTF-8"))
////					.append("&apicode=" + URLEncoder.encode("1", "UTF-8")).append("&msisdn=" + URLEncoder.encode( "01511662266", "UTF-8")).append("&countrycode=" + URLEncoder.encode("880", "UTF-8"))
////					.append("&cli=" + URLEncoder.encode("INTERBOARD", "UTF-8")).append("&messagetype=" + URLEncoder.encode("1", "UTF-8")).append("&message=" + URLEncoder.encode("test msg", "UTF-8"))
////					.append("&messageid=" + URLEncoder.encode("0", "UTF-8"));
//			URL smsUrl;
//			smsUrl = new URL(urlString.toString());
//			URLConnection urlConnector = smsUrl.openConnection();
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					urlConnector.getInputStream()));
//			String inputLine;
//			String inputLine1 = "";
//			while ((inputLine = in.readLine()) != null) {
//				if (inputLine != null)
//					inputLine1 += inputLine;
//			}
//			in.close();
//			System.out.println("SMS==" + "Hi delwar");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
