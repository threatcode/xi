package edu.surecash.api;

import org.apache.log4j.Priority;
import org.json.JSONException;
import org.json.JSONObject;
//import surecashapicall.SslWireless;


public class SureCashData {
    
    /** mandatory **/
    
    String organizationName;
    String applicantID;
    String accountNo;
    double amount;
    String TXNID;
    String mobileNo;
    
    /** mandatory **/
    
    /** optional **/
    String userName;
    String pass;
    /** optional **/
    
    /** our response **/
    String statusCode;
    String status;
    String description;
    /** our response **/
    
    private static String ORGName="";
    private static int SUCCESS=1;
    private static int DUPLICATEAPPLCANT=9;
    private static int MobileAccountMismatch=100;
    private static int AMOUNTTOBEPAID=500;
    
    
    SureCashData(){
    
    }

    public String getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(String applicantID) {
        this.applicantID = applicantID;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getTXNID() {
        return TXNID;
    }

    public void setTXNID(String TXNID) {
        this.TXNID = TXNID;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    
    public SureCashData(String organizationName,String applicantID,
            Integer amount,String TXNID){
        this.applicantID=applicantID;
        this.TXNID=TXNID;
    //    this.accountNo=accountNo;
        this.amount=amount;
        this.organizationName=organizationName;
    }
    
    public SureCashData(String organizationName,String applicantID,
            Integer amount,String accountNo,String TXNID){
        this.applicantID=applicantID;
        this.TXNID=TXNID;
        this.accountNo=accountNo;
        this.amount=amount;
        this.organizationName=organizationName;
    }
    
    public SureCashData(String organizationName,
        String applicantID,
        String accountNo,
        Integer amount,
        String monthStart,
        String monthEnd,
        String userName,
        String pass,
        String TXNID)
    {
        this.applicantID=applicantID;
        this.accountNo=accountNo;
        this.TXNID=TXNID;
        this.amount=amount;
       
        this.userName=userName;
        this.pass=pass;
        this.organizationName=organizationName;
    }
    
    public boolean ConstructSureCashDataFromJson(JSONObject js){
        System.out.println(js);
        try{
            
            this.applicantID=js.getString("ApplicantID");
            this.accountNo=js.getString("AccountNo");
            this.amount=js.getDouble("CollectedAmount");   
            this.TXNID=js.getString("TransactionID");
            this.organizationName=js.getString("OrganizationName");
            
            
           
            if(js.has("UserName")){
                this.userName= js.getString("UserName");
            }
            if(js.has("Passwd")){
                this.pass= js.getString("Passwd");
            }
            if(js.has("MobileNo")){
                this.mobileNo= js.getString("MobileNo");
            }
            
            return true;
        }catch(JSONException ex){
            ex.printStackTrace();
            return false;
        }
        
    }

    public JSONObject successful() throws JSONException{
        this.statusCode="200";
        this.status="Success";
        this.description="Applicant activated";
        return constructJson();
    }
    
    public JSONObject applicantIdMissing() throws JSONException{
        this.statusCode="9003";
        this.status="Failed";
        this.description="Applicant ID missing";
        return constructJson();
    }
    
    public JSONObject invalidApplicantID() throws JSONException{
        this.statusCode="9004";
        this.status="Failed";
        this.description="Invalid ApplicantID";
        return constructJson();
    }
    
    public JSONObject duplicateApplicantID() throws JSONException{
        this.statusCode="9005";
        this.status="Failed";
        this.description="Applicant already activated";
        return constructJson();
    }
    
    public JSONObject amountMismatch() throws JSONException{
        this.statusCode="9006";
        this.status="Failed";
        this.description="Paid amount is less than required";
        return constructJson();
    }
    public JSONObject organizationMismatch() throws JSONException{
        this.statusCode="9007";
        this.status="Failed";
        this.description="Organization name mismatch";
        return constructJson();
    }
    
    public JSONObject ParameterMissing() throws JSONException{
        this.statusCode="9000";
        this.status="Failed";
        this.description="Parameter missing";
        return constructJson();
    }
    
    public JSONObject MobileAccountMismatch() throws JSONException{
        this.statusCode="9008";
        this.status="Failed";
        this.description="Mobile No and Account ID mismatch";
        return constructJson();
    }
    
    public JSONObject InvalidParameter() throws JSONException{
        this.statusCode="9009";
        this.status="Failed";
        this.description="One or more parameter invalid";
        return constructJson();
    }
    
    
    JSONObject constructJson() throws JSONException{
        JSONObject jsonData= new JSONObject();
    //  jsonData.put("UserName",this.userName);
    //  jsonData.put("PassWd",this.pass);
        jsonData.put("OrganizationName",this.organizationName);
        jsonData.put("ApplicantID", this.applicantID);
    //  jsonData.put("customerId",this.customerId);
    //   jsonData.put("MonthStart",this.monthStart);
    //   jsonData.put("MonthEnd",this.monthEnd);
        jsonData.put("CollectedAmount", String.valueOf(this.amount));
        jsonData.put("AccountNo", this.accountNo);
        jsonData.put("TransactionID", this.TXNID);
        
        jsonData.put("StatusCode", this.statusCode);
        jsonData.put("Status", this.status);
        jsonData.put("Description", this.description);
        jsonData.put("MobileNo", this.mobileNo);
        return jsonData;
    }
    
    // To Do
    
    JSONObject process() throws JSONException{
        if(!this.organizationName.equals(ORGName)){
            return organizationMismatch();
        }
        else if(this.applicantID==null){
            return applicantIdMissing();
        }
        else if(mobileNo==null){
            return InvalidParameter();
        }
        else if(accountNo.length()!=12 || mobileNo.length()!=11){
            return InvalidParameter();
        }
        else if(this.amount<AMOUNTTOBEPAID) return amountMismatch();
        
        if(this.mobileNo.startsWith("880")){
            this.mobileNo= this.mobileNo.substring(2) ;
        }else if(!this.mobileNo.startsWith("0")){
            this.mobileNo="0"+this.mobileNo;
        }
        int res=0;
        
        // checkvalidity, res contain status of validation
        
//        DBConnector db = new DBConnector();
//        int res = db.checkValidity(Integer.valueOf(this.applicantID), this.mobileNo);
//        
        if(res ==0 || res==99){
            return invalidApplicantID();
        }else if(res == SUCCESS){
            
            // update application
            
            // if successful then return, otherwise dont return now
            return successful();
        }else if(res == DUPLICATEAPPLCANT){
            return duplicateApplicantID();
        }else if(res == MobileAccountMismatch){
            return MobileAccountMismatch();
        }
        //else if(this.app.)
        
        // check invalid applicant 
        // check duplicate applicant
        // check mobile+account mismatch
        return new Response("505", "Failed", "Internal Error").createJSONObject();
    }
    
}
