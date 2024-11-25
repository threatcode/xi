package edu.action.application;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

import com.surecash.cad.CadPaymentVerifyReq;
import com.surecash.cad.CadPaymentVerifyResp;
import com.surecash.cad.CadService;

import edu.dao.application.ApplicationDAO;
import edu.dto.application.ResponseDTO;

public class PaymentTEST {

	/**
	 * @param args
	 */
	 // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		PaymentTEST obj=new PaymentTEST();
		 
		//String value=obj.SBL();
		
		 //obj.HttpsTEST();
		 //obj.HttpsTEST_ROCKET();
		 //obj.Bkash();
		 obj.HttpsTEST_SureCash();
		 //obj.HttpsTEST_NAGAD();
		try {
            System.out.println("Testing 1 - Send Http GET request");
            //obj.sendGet();

            System.out.println("Testing 2 - Send Http POST request");
            obj.sendPost();
        } finally {
            obj.close();
        }
	}
	
	private void close() throws IOException {
        httpClient.close();
    }
	private void sendGet() throws Exception {

        HttpGet request = new HttpGet("https://www.google.com/search?q=mkyong");

        // add request headers
        request.addHeader("custom-key", "mkyong");
        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        }

    }

////////////////////////////////////////////
	
	 private void sendPost() throws Exception {

	        HttpPost post = new HttpPost("https://sbl.com.bd:7070/api/sblservice/getWebPayment/");

	        // add request parameter, form parameters
	        
	        
	        List<NameValuePair> urlParameters = new ArrayList<>();
	        urlParameters.add(new BasicNameValuePair("Username", "SBL"));
	        urlParameters.add(new BasicNameValuePair("Password", "SBL#2020@B1LLS"));
	        urlParameters.add(new BasicNameValuePair("trxid", "AA000024"));
	        urlParameters.add(new BasicNameValuePair("organization", "SBL"));
	         
	        post.setEntity(new UrlEncodedFormEntity(urlParameters));

	        try (CloseableHttpClient httpClient = HttpClients.createDefault();
	             CloseableHttpResponse response = httpClient.execute(post)) {

	            System.out.println(EntityUtils.toString(response.getEntity()));
	        }

	    }
	/////////////////////////////////////////////
	public String SBL()
	{
		 
	    
		try {
            URL url = new URL ("https://sbl.com.bd:7070/api/sblservice/getWebPayment/");
//            
            
            String encoding = new String(Base64.encodeBase64(("SBL:SBL#2020@B1LLS").getBytes()));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            //connection.setRequestProperty  ("host", "10.10.200.142");
            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   = 
                new BufferedReader (new InputStreamReader (content));
            String line;
            String line1="";
            while ((line = in.readLine()) != null) {
            	line1=line1+line;
            }
            String[] parts = line1.split("\\|");
            System.out.println(line1);
         
			 return "wait";
			 
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
		 return "wait";
	}
	
	public void Bkash()
	{
		String trxid="7GQ5U8PGWT";
		try{
			String generatedJSONString = "{\"username\" :\"eduboard@20\",\"password\" :\"eduboard@20#\",\"trxid\" : \""+trxid+"\",\"organization\" : \"BKash\"}";
			//String myUrlgoeshere = "http://118.67.219.193/bkash_live/edu_board/VERIFY_PAYMENT.php";
			String myUrlgoeshere = "http://118.67.219.193/bkash_live/edu_board/getWebPayment.php";
			
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
//			JSONObject myResponse = new JSONObject(responseString.toString());
//			System.out.println(myResponse.getString("status").toLowerCase());
			

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String ROCKET_OLD()
	{
	  String trxid="23458343";
		try {
            URL url = new URL ("http://mbsrv.dutchbanglabank.com/BillPayGW/BillInfoService?shortcode=515&userid=BUETEDUBOARD101&password=j874hejduierunaigt&opcode=GT&txnid="+trxid);
//            URL url = new URL ("http://mbsrv.dutchbanglabank.com/BillPayGWT/BillInfoService?shortcode=100&userid=BUET&password=1234&opcode=GT&txnid=23013124");
            
            
            String encoding = new String(Base64.encodeBase64(("dbill:dBILL!23").getBytes()));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("host", "10.10.200.142");
            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   = 
                new BufferedReader (new InputStreamReader (content));
            String line;
            String line1="";
            while ((line = in.readLine()) != null) {
            	line1=line1+line;
            }
            String[] parts = line1.split("\\|");
            System.out.println(line1);
            ResponseDTO applicant = new ResponseDTO();
//            ssc_board = parts[1].substring(0,3);
             
				return "wait";
			 
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
		return "wait";
	}
	
	public void SureCash_old()
	{
		String trxid="";
		try{

			CadService cadService = new CadService("https://sckuet.surecashbd.com/surecash-pps-v1/api/cad/verifyPayment/","Cat","cAd@BUET");
	        CadPaymentVerifyReq cadPaymentVerifyReq = CadPaymentVerifyReq.builder()
	                .txnId(trxid)
	                .organization("SC")
	                .appType("web")
	                .build();
	        CadPaymentVerifyResp cadPaymentVerifyResp = cadService.verifyPayment(cadPaymentVerifyReq);

			
			System.out.println(cadPaymentVerifyResp.getStatus());
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void HttpsTEST_ROCKET () throws JSONException
	{
		String baseUrl="https://mbsrv.dutchbanglabank.com";
        String paymentUrl="/BillPayGW/getWebPayment";
 

        JSONObject requestBody=new JSONObject();
        requestBody.put("trxid","23457808");
        //requestBody.put("billercode",new Integer(12000));
        requestBody.put("billercode","515");
        requestBody.put("organization","Rocket");
        requestBody.put("userid","bebbuet2020rocket");
        requestBody.put("password","dBbLR0ck3TbU3Tf0rb3B");

        System.out.print("Body for post: "+requestBody+"\n");

        String authorizationHeader="bebbuet2020rocket:dBbLR0ck3TbU3Tf0rb3B";
        ClientService ClientService= new ClientService();

        Object  Data= ClientService.callByHttpPost(baseUrl+ paymentUrl,authorizationHeader,requestBody.toString(),"application/Json");
        System.out.println("Result:\n"+Data);
	}
	
	 
	public void HttpsTEST_SBL () throws JSONException
	{
		String baseUrl="https://sbl.com.bd:7070";
        String paymentUrl="/api/sblservice/getWebPayment/";
 
 
        JSONObject requestBody=new JSONObject();
        requestBody.put("trxid","AA000024");
        //requestBody.put("billercode",new Integer(12000));
        requestBody.put("organization","SBL");


        System.out.print("Body for post: "+requestBody+"\n");

        String authorizationHeader="SBL:SBL#2020@B1LLS";
        ClientService ClientService= new ClientService();

        Object  Data= ClientService.callByHttpPost(baseUrl+ paymentUrl,authorizationHeader,requestBody.toString(),"application/Json");
        System.out.println("Result:\n"+Data);
	}
	
	//////////////////////////////////////
	public void HttpsTEST_SureCash () throws JSONException
	{
		String baseUrl="https://pps.surecashbd.com";
        String paymentUrl="/surecash-pps/api/cad/getWebPayment/";
 
 
        JSONObject requestBody=new JSONObject();
        requestBody.put("trxid","RB00486108515");
        //requestBody.put("billercode",new Integer(12000));
        requestBody.put("organization","SC");


        System.out.print("Body for post: "+requestBody+"\n");

        String authorizationHeader="cat:cAd@BUET";
        ClientService ClientService= new ClientService();

        Object  Data= ClientService.callByHttpPost(baseUrl+ paymentUrl,authorizationHeader,requestBody.toString(),"application/Json");
        System.out.println("Result:\n"+Data);
	}
	
	public void HttpsTEST_NAGAD () throws JSONException
	{
		String baseUrl="https://ws-external.mynagad.com:8162/eduBoard/api/v1/getWebPayment";
		 String queryParams = "trxid=70M7BFII&organization=NAGAD"; 
        String authorizationHeader = "Buet@123:Buet#123";
        ClientService ClientService= new ClientService();

        Object Data = ClientService.callByHttpGet(baseUrl, authorizationHeader, queryParams, "application/Json");
        System.out.println("Result:\n" + Data);
	}
	
	
////////////////////////////////
}
