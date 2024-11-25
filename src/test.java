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

import net.coobird.thumbnailator.Thumbnails;

import edu.dao.application.ApplicationDAO;
import edu.helpers.SmsSender;


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

		File fileUpload= new File("E:\\projects\\Primary\\DPE_2019\\AdmitCardSystem\\DPE Logo.jpg");
		File newFile= new File("E:\\projects\\Primary\\DPE_2019\\AdmitCardSystem\\DPE_Logo_Delwar.jpg");
		try
		{
			Thumbnails.of(fileUpload).size(120, 150).toFile(newFile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}			    	  
		try
		{
			
//           	java.util.Date now = new java.util.Date();
//        	SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//			SmsSender smsSender=new SmsSender();
//			smsSender.setMobile("01511662266");
//			smsSender.setAppid("asdasd");
//			smsSender.setRoll("asdasda");
//			smsSender.setName("Name");
//			smsSender.setApptime(format.format(now));
//			smsSender.setOtp("546235");
//			smsSender.setOperation("smsWFirst");
//			
//			Thread thread = new Thread(smsSender);
//			thread.start();
			
//			String abc = "257219din2018";
//			System.out.println(abc.substring(0,6));
//			System.out.println(abc.substring(6,9));
//			System.out.println(abc.substring(9));
			
			
//		File imageFile = new File("E:\\Tulips.jpg");
//		File compressedImageFile = new File("E:\\1377171310226875.jpg");
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
		
//			Thumbnails.of(compressedImageFile)
//	    		.size(120, 150)
//	    		.outputFormat("jpg")
//        		.toFile(new File("E:\\Tulips_2.jpg"));
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
