package edu.surecash.api;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import edu.action.common.BaseAction;

public class PayResource extends BaseAction  implements Runnable{

	String SureCashapiKey = "RandomText";
	private String TXN_ID;
    private String BILL_NO;
    private String PAYMENT_AMOUNT;
    private String PAYMENT_DATE;
    private String PAYER_MOBILE_NO;
    private String APP_ID;
    private String content;
    
	public void run() {
		// TODO Auto-generated method stub
		
	}

		
	public String SureCashPayment() 
	{
		 Gson gson = new Gson();
		 //String re="";
		 String resp="";
		
		 
		 if (!checkAuthentication(request)) {
	            
				try {
					resp = new Response("9001", "Unauthorized Access", "Use valid credentials").createJSONObject().toString();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            //log.fatal("FATAL: Unauthorized Access " + re);
	          //  return re;
	            
	            
	      }
		 else
		 {
		
		   SureCashGetResponse response = new SureCashGetResponse(APP_ID,TXN_ID);
	      
			try {
				resp = response.process().toString();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println(resp);
	       
	        /*
	         Database Query for finding applicant id's info
	         */
	        //  System.out.println(response.process().toString());
	       
		 }
		 
		 
		 
		/* 
		 try {        
	            JSONObject surecashData = new JSONObject(content);
	            SureCashData scd = new SureCashData();
	        
	        /***            
	            boolean succesfulParse=scd.ConstructSureCashDataFromJson(surecashData);
	            if(!succesfulParse){
	                return new Response("506", "Failed", "Error in json parsing").createJSONObject().toString() ;
	            }
	        ***/    
	          /*  
	            if (!scd.ConstructSureCashDataFromJson(surecashData)) {
	                String re=scd.ParameterMissing().toString();
	               /// log.info(re);
	                System.out.println(re);
	                return re;
	            }
	           
	            String statusResult = scd.process().toString();
	           // log.info("status result: "+statusResult);
	            System.out.println(statusResult);
	            return statusResult;
	            
	        } catch (Exception ex) {
	            //log.info("Exception: " + ex);
	            System.out.println(ex);
	        }
		 
		 */
		 
		 
		 
		 
		 
	    	
			String json = gson.toJson(resp);
			setJsonResponse(json);
		
		
		return null;
	}
	
	
	
	 boolean checkAuthentication( HttpServletRequest headers) {
	        String ipAddress = headers.getHeader("X-Forwarded-For");
	        
//	        String LocalIP= request.getHeader("X-Forwarded-For");
//	        String via = request.getHeader("Via");
//	        String remoteIP = request.getRemoteAddr();
//	        remoteIP=remoteIP.trim();
	        
	        
//	        118.179.212.139
//	        203.76.117.139
	        
	        
	        if (ipAddress == null) {
	            ipAddress = headers.getRemoteAddr();
	        }
	        // Hard Coded IP check
	        
	        if (ipAddress.equals("118.179.212.139")||ipAddress.equals("203.76.117.139")||ipAddress.equals("203.76.117.141")  ) {
	             return true;
	        }
	       
	        // Check APIKey
	       /* 
	        String authorizationHeader = headers.getHeader("Authorization");
	        if (authorizationHeader == null) {
	            return false;
	        }
	        String decoded = null;
	        if (!authorizationHeader.substring(0, 5).equals("Basic")) {
	            return false;
	        }
	        authorizationHeader = authorizationHeader.substring(6);
	      //  System.out.println(x);
	        decoded = new String(Base64.decode(authorizationHeader));
	        if (decoded.equals(SureCashapiKey)) {
	            return true;
	        }
	        
	        */
	        
	        
	        
	        return false;
	    }



	public String getSureCashapiKey() {
		return SureCashapiKey;
	}



	public void setSureCashapiKey(String sureCashapiKey) {
		SureCashapiKey = sureCashapiKey;
	}



	public String getTXN_ID() {
		return TXN_ID;
	}



	public void setTXN_ID(String tXN_ID) {
		TXN_ID = tXN_ID;
	}



	public String getBILL_NO() {
		return BILL_NO;
	}



	public void setBILL_NO(String bILL_NO) {
		BILL_NO = bILL_NO;
	}



	public String getPAYMENT_AMOUNT() {
		return PAYMENT_AMOUNT;
	}



	public void setPAYMENT_AMOUNT(String pAYMENT_AMOUNT) {
		PAYMENT_AMOUNT = pAYMENT_AMOUNT;
	}



	public String getPAYMENT_DATE() {
		return PAYMENT_DATE;
	}



	public void setPAYMENT_DATE(String pAYMENT_DATE) {
		PAYMENT_DATE = pAYMENT_DATE;
	}



	public String getPAYER_MOBILE_NO() {
		return PAYER_MOBILE_NO;
	}



	public void setPAYER_MOBILE_NO(String pAYER_MOBILE_NO) {
		PAYER_MOBILE_NO = pAYER_MOBILE_NO;
	}


	public String getAPP_ID() {
		return APP_ID;
	}


	public void setAPP_ID(String aPP_ID) {
		APP_ID = aPP_ID;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}



	
	
	
	
	
	

}
