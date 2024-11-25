package edu.surecash.api;

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

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;




public class SMSResource {


	public int sendSMSRobi(String mobile, String mess)
	  {
		if(1==1) return 0;
	      try
	      {           
	          URL yahoo;
	          
	          
	          //https://bmpws.robi.com.bd/ApacheGearWS/SendTextMessage?Username=buetiict&Password=Buet@iict99&From=8801847050021&To=01739884023&Message=Test message from Nanosoft

	          yahoo = new URL("https://bmpws.robi.com.bd/ApacheGearWS/SendTextMessage?Username="+URLEncoder.encode("buetiict")+
	        		  "&Password="+URLEncoder.encode("Buet@iict99")+
	        		  "&From=8801847050021&To="+URLEncoder.encode("88"+mobile)+
	        		  "&Message="+URLEncoder.encode(mess) );
	          
	          
	          URLConnection yc = yahoo.openConnection();
	          BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	          String inputLine;
	          String inputLine1="";
	         
	          
	          
	          
	          while ((inputLine = in.readLine()) != null)
	          {
	              //System.out.println(inputLine);
	              if(inputLine!=null)
	                  inputLine1+=inputLine;
	          }
	          in.close();
	        	          
	          
	          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	          DocumentBuilder builder;
	          InputSource is;
	          try {
	              builder = factory.newDocumentBuilder();
	              is = new InputSource(new StringReader(inputLine1));
	              Document doc = builder.parse(is);
	              NodeList list = doc.getElementsByTagName("ErrorCode");
	              NodeList listC = doc.getElementsByTagName("CurrentCredit");
	              System.out.println(list.item(0).getTextContent());
	              System.out.println("CurrentCredit-->>"+listC.item(0).getTextContent());
	              if(list.item(0).getTextContent().equalsIgnoreCase("0"))
	            	  return 1;
	          } catch (ParserConfigurationException e) {
	          } catch (SAXException e) {
	          } catch (IOException e) {
	          }
	          
	          
	          
	          if(inputLine1.contains("<ErrorCode>0"))
	        	  return 1;
	      }
	      catch(Exception e)
	      {
	          e.printStackTrace();
	      }
	      return 0;
	  }
	
	
	
	 public void sendSMSSSL(String mobile, String mess) {
		 if(1==1) return;
		 String targetURL = "http://sms.sslwireless.com/pushapi/dynamic_buet/server.php";
	        URL url;
	        HttpURLConnection connection = null;
	        try {
	            StringBuilder urlParameter = new StringBuilder();
	            urlParameter.append("sid=").append(URLEncoder.encode("BUET", "UTF-8"));
	            urlParameter.append("&user=").append(URLEncoder.encode("buetiict", "UTF-8"));
	            urlParameter.append("&pass=").append(URLEncoder.encode("buet@123", "UTF-8"));
	            urlParameter.append("&sms[0][0]=").append(URLEncoder.encode("88" + mobile, "UTF-8"));
	            urlParameter.append("&sms[0][1]=").append(URLEncoder.encode(mess, "UTF-8"));
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
	            	//UserDAO.sentAppSMS(appid, this.mobile);
	            
	            rd.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (connection != null) {
	                connection.disconnect();
	            }
	        }
	    }
	
	
	 
	 public void sendSMSTT(String mobile, String mess) {
		 if(1==1) return;
		 try {
	           
	            StringBuilder urlString = new StringBuilder(
	                    "http://bulksms.teletalk.com.bd/link_sms_send.php?").append("op=" + URLEncoder.encode("SMS", "UTF-8")).append("&user=" + URLEncoder.encode("IICT", "UTF-8"))
	                    .append("&pass=" + URLEncoder.encode("iict369#^(buet", "UTF-8")).append("&mobile=" + URLEncoder.encode("88" + mobile, "UTF-8")).append("&charset=" + URLEncoder.encode("UTF-8", "UTF-8"))
	                    .append("&sms=" + URLEncoder.encode(mess, "UTF-8"));
	           
	            URL smsUrl;
	            smsUrl = new URL(urlString.toString());
	            URLConnection urlConnector = smsUrl.openConnection();
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                    urlConnector.getInputStream()));
	            String inputLine;
	            String inputLine1 = "";
	            while ((inputLine = in.readLine()) != null) {
	               // ApplicationDAO.ApplicationSMSUpdate(this.appid, this.mobile);
	            	//UserDAO.sentAppSMS(this.appid, this.mobile);
	               
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
	 
	 
	

}
