package edu.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import net.coobird.thumbnailator.Thumbnails;

import edu.dao.application.ApplicationDAO;


public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		for(int i=0;i<5000;i++)
//		{
//			if(i%100==0)
//				System.out.println(i);
//		}

		
		
		try
		{
			
			String word = "12201980157645";
			System.out.println(word.substring(6));
			
			
//			String generatedJSONString = "{\"apptype\" :\"web\",\"roll\" : \"313137\",\"reg\" : \"1310537004\",\"board\" : \"DHAKA\",\"year\" : \"2017\"}";
//			String myUrlgoeshere = "http://103.230.104.217:9999/CAD/buetfromTT.php";
//			HttpClient httpClient = new DefaultHttpClient();
//			HttpPost postRequest = new HttpPost(myUrlgoeshere);
//			StringEntity input = new StringEntity(generatedJSONString);
//			input.setContentType("application/json;charset=UTF-8");
//			postRequest.setEntity(input);
//			input.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
//			postRequest.setHeader("Accept", "application/json");
//			postRequest.setEntity(input); 
//			HttpResponse response = httpClient.execute(postRequest);
//			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
//			String output;
//			String responseString = "";
//			while ((output = br.readLine()) != null)
//			{
//				responseString=output;
//			}
//			httpClient.getConnectionManager().shutdown();
//			JSONObject myResponse = new JSONObject(responseString.toString());
//			System.out.println("result after Reading JSON Response");
//			System.out.println("status- "+myResponse.getString("status"));
//			System.out.println("apptype- "+myResponse.getString("apptype"));
//			System.out.println("roll- "+myResponse.getString("roll"));
//			System.out.println("reg- "+myResponse.getString("reg"));
//			System.out.println("board- "+myResponse.getString("board"));
//			System.out.println("year- "+myResponse.getString("year"));
//			System.out.println("contactno- "+myResponse.getString("contactno"));
//			System.out.println("trxid- "+myResponse.getString("trxid"));
//			System.out.println("paytime- "+myResponse.getString("paytime"));
//			System.out.println("paymobile- "+myResponse.getString("paymobile"));
//			System.out.println("organization- "+myResponse.getString("organization"));  
              
			
			
			//			String newstring = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss").format(System.currentTimeMillis());
//			System.out.println(newstring);
			
//		File imageFile = new File("E:\\abc\\chk_captcha.jpg");
//		Thumbnails.of(imageFile)
//		.size(120, 150)
//		.outputFormat("jpg")
//		.toFile(new File("E:\\abc\\chk_captcha_2.jpg"));
//		File compressedImageFile = new File("E:\\abc\\Sample_1.jpg");
		
		
//		InputStream is = new FileInputStream(imageFile);
//		OutputStream os = new FileOutputStream(compressedImageFile);
//		float quality = 0.5f;
//		BufferedImage image = ImageIO.read(is);
//		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");	
//		if (!writers.hasNext())
//			throw new IllegalStateException("No writers found");	
//		ImageWriter writer = (ImageWriter) writers.next();
//		ImageOutputStream ios = ImageIO.createImageOutputStream(os);
//		writer.setOutput(ios);	
//		ImageWriteParam param = writer.getDefaultWriteParam();
//		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//		param.setCompressionQuality(quality);
//		writer.write(null, new IIOImage(image, null, null), param);
//		is.close();
//		os.close();
//		ios.close();
//		writer.dispose();
//		
//			Thumbnails.of(compressedImageFile)
//	    		.size(120, 150)
//	    		.outputFormat("jpg")
//        		.toFile(new File("E:\\abc\\Chrysanthemum_2.jpg"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
//		String targetURL = "http://sms.sslwireless.com/pushapi/dynamic_buet/server.php";
//		URL url;
//	    HttpURLConnection connection = null;  
//		try
//		{
//			StringBuilder urlParameter = new StringBuilder();
//			urlParameter.append("sid=").append(URLEncoder.encode("buet", "UTF-8"));
//			urlParameter.append("&user=").append(URLEncoder.encode("buetiict", "UTF-8"));
//			urlParameter.append("&pass=").append(URLEncoder.encode("buetiict@ssl", "UTF-8"));
//			urlParameter.append("&sms[0][0]=").append(URLEncoder.encode("8801558568643", "UTF-8"));
//			urlParameter.append("&sms[0][1]=").append(URLEncoder.encode("test sms", "UTF-8"));
//			urlParameter.append("&sms[0][2]=").append(URLEncoder.encode("abc", "UTF-8"));
//			
//		      //Create connection
//		      url = new URL(targetURL);
//		      connection = (HttpURLConnection)url.openConnection();
//		      connection.setRequestMethod("GET");
//		      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//		      connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameter.toString().getBytes().length));
//		      connection.setRequestProperty("Content-Language", "en-US");  
//					
//		      connection.setUseCaches (false);
//		      connection.setDoInput(true);
//		      connection.setDoOutput(true);
//
//		      //Send request
//		      DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
//		      wr.writeBytes (urlParameter.toString());
//		      wr.flush ();
//		      wr.close ();
//
//		      //Get Response	
//		      InputStream is = connection.getInputStream();
//		      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//		      String line;
//		      StringBuffer response = new StringBuffer(); 
//		      System.out.println("####################### TT Response ##############################");
//		      while((line = rd.readLine()) != null) {
//		        response.append(line);
//		        response.append('\r');
//		      }
//
//		      
//		      System.out.println(response.toString());
//		      System.out.println("####################### TT Response ##############################");
//		      rd.close();
//		    } catch (Exception e) {
//		      e.printStackTrace();
//
//		    } finally {
//		      if(connection != null) {
//		        connection.disconnect(); 
//		      }
//		    }

	}

}

