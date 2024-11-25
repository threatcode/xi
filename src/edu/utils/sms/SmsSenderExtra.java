package edu.utils.sms;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



import edu.dao.applicant.RetrivedPinDAO;






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
				
		
		
//		if(this.mobile.startsWith("016"))
//    	{
//			sendTextMSG_Robi();
//    	}
//    	else if(this.mobile.startsWith("017")||this.mobile.startsWith("018"))
//    	{
//    		 sendSMSSSL();
//    	}
//    	else if(this.mobile.startsWith("015")||this.mobile.startsWith("019")||this.mobile.startsWith("011"))
//    	{
//    		sendSMSTT();
//    	}
		
	/*	
	 * 
	
	
	 sendSMSSSL_p();
	
	 
	 
	 
	 * */
		
		//sendRobi();

	}

	
	
	
	public void sendSMSSSL_p() {
		String targetURL = "http://sms.sslwireless.com/pushapi/dynamic_buet/server.php";
		URL url;
		HttpURLConnection connection = null;
		try {
			StringBuilder urlParameter = new StringBuilder();
			urlParameter.append("sid=").append(URLEncoder.encode("BUET", "UTF-8"));
			urlParameter.append("&user=").append(URLEncoder.encode("buetiict", "UTF-8"));
			urlParameter.append("&pass=").append(URLEncoder.encode("buet@123", "UTF-8"));
			urlParameter.append("&sms[0][0]=").append(URLEncoder.encode("88" + this.mobile, "UTF-8"));
			urlParameter.append("&sms[0][1]=")
					.append(
							URLEncoder
									.encode(
											this.text,
									"UTF-8"));
			urlParameter.append("&sms[0][2]=").append(URLEncoder.encode("abc", "UTF-8"));

			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length",
					""
							+ Integer.toString(urlParameter.toString()
									.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(connection
					.getOutputStream());
			wr.writeBytes(urlParameter.toString());
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			System.out.println(response.toString());
					
				
			if(response.toString().contains("<REPLY><PARAMETER>OK</PARAMETER><LOGIN>SUCCESSFULL</LOGIN>"))					  
					RetrivedPinDAO.sentAppSMS(this.appid, this.mobile);
				//update table
			
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}	
 
	
	public void sendRobi(){
		
		try {
			StringBuilder urlString = new StringBuilder(
					"https://api.mobireach.com.bd/SendTextMessage?").append("Username=" + URLEncoder.encode("iictbuet", "UTF-8")).append("&Password=" + URLEncoder.encode("Airtel@123", "UTF-8"))
					.append("&From=" + URLEncoder.encode("8801847170701", "UTF-8")).append("&To=" + URLEncoder.encode("88" + this.mobile, "UTF-8")).append("&charset=" + URLEncoder.encode("UTF-8", "UTF-8"))
					.append("&Message=" + URLEncoder.encode(text, "UTF-8"));
			URL smsUrl;
			smsUrl = new URL(urlString.toString());
			URLConnection urlConnector = smsUrl.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnector.getInputStream()));
			String inputLine;
			String inputLine1 = "";
			while ((inputLine = in.readLine()) != null) {
				RetrivedPinDAO.sentAppSMS(this.appid, this.mobile);
				if (inputLine != null)
					inputLine1 += inputLine;
			}
			in.close();
			System.out.println("SMS==" + inputLine1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 
	      
		
	}
	
	
	 public void sendSMSTT() {
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
	               // ApplicationDAO.ApplicationSMSUpdate(this.appid, this.mobile);
	            	RetrivedPinDAO.sentAppSMS(this.appid, this.mobile);
	               
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
	
	
	 public void sendTextMSG_Robi()
	  {
		 
		 	 
		 try {
				//this.mobile = ApplicationDAO.getMobileNo(this.appid);
				StringBuilder urlString = new StringBuilder(
						"https://bmpws.robi.com.bd/ApacheGearWS/SendTextMessage?")
				.append("Username=" + URLEncoder.encode("buetiict", "UTF-8"))
				.append("&Password="+ URLEncoder.encode("Buet@iict99", "UTF-8"))
				.append("&From=8801847050021")
				.append("&To="+ URLEncoder.encode("88"+this.mobile, "UTF-8"))	
				.append("&Message="+ URLEncoder.encode(this.text,"UTF-8"));
						
						
				URL smsUrl;
				smsUrl = new URL(urlString.toString());
				URLConnection urlConnector = smsUrl.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						urlConnector.getInputStream()));
				String inputLine;
				String inputLine1 = "";
				while ((inputLine = in.readLine()) != null) {
					//System.out.println(inputLine);
					if (inputLine != null)
						inputLine1 += inputLine;
				}
				in.close();
				if(inputLine1.contains("<ErrorCode>0"))
					RetrivedPinDAO.sentAppSMS(this.appid, this.mobile);
				
				
				System.out.println("SMS==" + inputLine1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
		 
		 
	  }
	 
	
	  
	 public void sendSMSSSL() {
	        String targetURL = "http://sms.sslwireless.com/pushapi/dynamic_buet/server.php";
	        URL url;
	        HttpURLConnection connection = null;
	        try {
	            StringBuilder urlParameter = new StringBuilder();
	            urlParameter.append("sid=").append(URLEncoder.encode("BUET", "UTF-8"));
	            urlParameter.append("&user=").append(URLEncoder.encode("buetiict", "UTF-8"));
	            urlParameter.append("&pass=").append(URLEncoder.encode("buet@123", "UTF-8"));
	            urlParameter.append("&sms[0][0]=").append(URLEncoder.encode("88" + this.mobile, "UTF-8"));
	            urlParameter.append("&sms[0][1]=").append(URLEncoder.encode(this.text, "UTF-8"));
	            urlParameter.append("&sms[0][2]=").append(URLEncoder.encode("abc", "UTF-8"));
	            url = new URL(targetURL);
	            connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");
	            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	            connection.setRequestProperty("Content-Length",""
	                            + Integer.toString(urlParameter.toString()
	                                    .getBytes().length));
	            connection.setRequestProperty("Content-Language", "en-US");

	            connection.setUseCaches(false);
	            connection.setDoInput(true);
	            connection.setDoOutput(true);

	            // Send request
	            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
	            wr.writeBytes(urlParameter.toString());
	            wr.flush();
	            wr.close();
	           // ApplicationDAO.sendTextMSG_TT(this.appid, this.mobile);
	            InputStream is = connection.getInputStream();
	            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	            String line;
	            StringBuffer response = new StringBuffer();
	            while ((line = rd.readLine()) != null) {
	                response.append(line);
	                response.append('\r');
	            }
	            System.out.println(response.toString());
	            if(response.toString().contains("REFERENCEID"))
	            	RetrivedPinDAO.sentAppSMS(this.appid, this.mobile);
	            
	            rd.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (connection != null) {
	                connection.disconnect();
	            }
	        }
	    }
	
	 public void sendSMSSSL1() {
			String targetURL = "http://sms.sslwireless.com/pushapi/dynamic_buet/server.php";
			URL url;
			HttpURLConnection connection = null;
			try {
				StringBuilder urlParameter = new StringBuilder();
				urlParameter.append("sid=").append(URLEncoder.encode("BUET", "UTF-8"));
				urlParameter.append("&user=").append(URLEncoder.encode("buetiict", "UTF-8"));
				urlParameter.append("&pass=").append(URLEncoder.encode("buet@123", "UTF-8"));
				urlParameter.append("&sms[0][0]=").append(URLEncoder.encode("88" + this.mobile, "UTF-8"));
				urlParameter.append("&sms[0][1]=")
						.append(
								URLEncoder
										.encode(
												this.text,
										"UTF-8"));
				urlParameter.append("&sms[0][2]=").append(URLEncoder.encode("abc", "UTF-8"));

				// Create connection
				url = new URL(targetURL);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				connection.setRequestProperty("Content-Length",
						""
								+ Integer.toString(urlParameter.toString()
										.getBytes().length));
				connection.setRequestProperty("Content-Language", "en-US");

				connection.setUseCaches(false);
				connection.setDoInput(true);
				connection.setDoOutput(true);

				// Send request
				DataOutputStream wr = new DataOutputStream(connection
						.getOutputStream());
				wr.writeBytes(urlParameter.toString());
				wr.flush();
				wr.close();

				// Get Response
				InputStream is = connection.getInputStream();
				BufferedReader rd = new BufferedReader(new InputStreamReader(is));
				String line;
				StringBuffer response = new StringBuffer();
				System.out
						.println("####################### TT Response ##############################");
				while ((line = rd.readLine()) != null) {
					response.append(line);
					response.append('\r');
				}
				System.out.println(response.toString());
				System.out
						.println("####################### TT Response ##############################");
				if(line!=null)
					RetrivedPinDAO.sentAppSMS(this.appid, this.mobile);
				
				rd.close();
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				if (connection != null) {
					connection.disconnect();
				}
			}
		}	
	 

//	public void sendTextMSG_TT() {
//		try {
//			StringBuilder urlString = new StringBuilder(
//					"http://123.49.3.58:8081/web_send_sms.php?")
//					.append("ms="+ URLEncoder.encode("88" + this.mobile,"UTF-8"))
//					.append("&txt="+ URLEncoder.encode(this.text,"UTF-8"))
//					.append("&username=" + URLEncoder.encode("IICT", "UTF-8"))
//					.append("&password="+ URLEncoder.encode("iict2341", "UTF-8"));
//			URL smsUrl;
//			smsUrl = new URL(urlString.toString());
//			URLConnection urlConnector = smsUrl.openConnection();
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					urlConnector.getInputStream()));
//			String inputLine;
//			String inputLine1 = "";
//			while ((inputLine = in.readLine()) != null) {
//				System.out.println(inputLine);
//				if (inputLine != null)
//					inputLine1 += inputLine;
//			}
//			in.close();
//			//ApplicationDAO.sendTextMSG_TT(this.appid, this.mobile);
//			
//			UserDAO.sentAppSMS(this.appid, this.mobile);
//			
//			System.out.println("SMS==" + inputLine1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void sendResult_TT() {
//		try {
//			//this.mobile = ApplicationDAO.getMobileNo(this.appid);
//			StringBuilder urlString = new StringBuilder(
//					"http://123.49.3.58:8081/web_send_sms.php?")
//					.append("ms="+ URLEncoder.encode("88" + this.mobile,"UTF-8"))
//					.append("&txt="+ URLEncoder.encode(this.text,"UTF-8"))
//					.append("&username=" + URLEncoder.encode("IICT", "UTF-8"))
//					.append("&password="+ URLEncoder.encode("iict2341", "UTF-8"));
//			URL smsUrl;
//			smsUrl = new URL(urlString.toString());
//			URLConnection urlConnector = smsUrl.openConnection();
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					urlConnector.getInputStream()));
//			String inputLine;
//			String inputLine1 = "";
//			while ((inputLine = in.readLine()) != null) {
//				System.out.println(inputLine);
//				if (inputLine != null)
//					inputLine1 += inputLine;
//			}
//			in.close();
//			//ApplicationDAO.sendReceiveResultUpdate(this.appid, this.mobile);
//			UserDAO.sentAppSMS(this.appid, this.mobile);
//			System.out.println("SMS==" + inputLine1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	

	 
	

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
