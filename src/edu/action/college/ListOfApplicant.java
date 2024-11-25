package edu.action.college;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;

import edu.action.common.BaseAction;
import edu.dao.college.ListOfStudentOfCollegeDAO;
import edu.dto.college.CollegeDTO;
import edu.dto.college.ListOfStudentOfCollegeDTO;




public class ListOfApplicant extends BaseAction{
	
	private static final long serialVersionUID = -6516848499230321618L;
	private String from_where;
	private String eiinCode;
	private String applicationID;
	private String errorMessage; 
	private String successMessage;
	
	
	   private File myFile;
	   private String myFileContentType;
	   private String myFileFileName;
	   private String destPath;
	   private String ssc_roll;
	   private String ssc_board;
	   private String ssc_year;
	   private String ssc_reg;
	   private String grant;
	   
	public String getGrant() {
		return grant;
	}

	public void setGrant(String grant) {
		this.grant = grant;
	}

	public String execute(){
		
		return SUCCESS;
	}

	public String listOfApplicant(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}

		ListOfStudentOfCollegeDAO listOfStudentOfCollegeDAO = new ListOfStudentOfCollegeDAO();
		List<ListOfStudentOfCollegeDTO> studentList = listOfStudentOfCollegeDAO.getlistOfStudentOfCollege(userDto.getEiin());
		request.setAttribute("studentList", studentList);
		request.setAttribute("userInfo", userDto);
		return "success";

			
		          
	
	}
	
	public String specialQuotaList(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		this.from_where="specialQuotaList";
		//OTP Checking
/*		Boolean otpCheck = (Boolean)session.get("otp_validation");
	    if(otpCheck == null || otpCheck == false)
	    	return "optform";*/
	    	    
		ListOfStudentOfCollegeDAO specialQuotaListOfStudentOfCollegeDAO = new ListOfStudentOfCollegeDAO();
		List<ListOfStudentOfCollegeDTO> specialQuotaStudentList = specialQuotaListOfStudentOfCollegeDAO.getlistOfStudentOfSpecialQuota(userDto.getEiin());
		request.setAttribute("specialQuotaStudentList", specialQuotaStudentList);
		request.setAttribute("userInfo", userDto);
		

		request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
	    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
	    
	    return "success";
	}
	
	public String newspecialQuotaStudent(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}

		ListOfStudentOfCollegeDAO newspecialQuotaStudentinfoOfCollegeDAO = new ListOfStudentOfCollegeDAO();
		List<ListOfStudentOfCollegeDTO> newspecialQuotaStudentinfoList = newspecialQuotaStudentinfoOfCollegeDAO.getnewStudentOfSpecialQuota(ssc_roll,ssc_board,ssc_year,ssc_reg,userDto.getEiin());
		request.setAttribute("newspecialQuotaStudentinfoList", newspecialQuotaStudentinfoList);
		request.setAttribute("userInfo", userDto);
		return "success";          	
	}
	
	String ErrorMassage="";
public String uploadXLSapplicant(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		

		destPath = servlet.getRealPath("");

        try{
       	 System.out.println("Src File name: " + myFile);
       	 System.out.println("Dst File name: " + myFileFileName);
       	    	 
       	 if(myFileFileName==null)
       		 {
       		  ErrorMassage=ErrorMassage+" Please Upload Excel file";
       		  request.setAttribute("ErrorMassage", ErrorMassage);
       		  return "success";      
       		 }
       	 
       	 File destFile  = new File(destPath, myFileFileName);
       	
      	 FileUtils.copyFile(myFile, destFile);
    
        }catch(IOException e){
           e.printStackTrace();
           return ERROR;
        }
        
         List<String> lsAppID=  new ArrayList<String>();
		 List<String> lsSSC_ROLL=  new ArrayList<String>();
		 
		 
        boolean isOk=readUploadedExcel(lsAppID,lsSSC_ROLL,destPath+"/"+myFileFileName);
        if(!isOk){
        	addActionError("Unable to read Excel file");
        	return ERROR;
        }
        ListOfStudentOfCollegeDAO SQStdCollegeDAO = new ListOfStudentOfCollegeDAO();
        List<String> ValidAppID=  new ArrayList<String>();
        ErrorMassage="Invalid Application No.:";
        for(int App=0;App<lsAppID.size();App++)
        {
        	String appID=(String)lsAppID.get(App);
        	String ssc_roll=(String)lsSSC_ROLL.get(App);
        	if(SQStdCollegeDAO.isValidApp(appID,ssc_roll,userDto.getEiin()))
        	{
        		ValidAppID.add(appID);
        	}
        	else
        	{
        		ErrorMassage=ErrorMassage+appID +" ("+ssc_roll+")  ";
        	}
        }
        applicationID="";
        for(int App=0;App<ValidAppID.size();App++)
        {
        	if(App!=ValidAppID.size()-1)
        	 applicationID=applicationID+"'"+ValidAppID.get(App)+"',";
        	else
        		 applicationID=applicationID+"'"+ValidAppID.get(App)+"'";
        }
        
		List<ListOfStudentOfCollegeDTO> newspecialQuotaStudentinfoList = SQStdCollegeDAO.getUploadedSQStdList(applicationID,userDto.getEiin());
		request.setAttribute("newspecialQuotaStudentinfoList", newspecialQuotaStudentinfoList);
		request.setAttribute("ErrorMassage", ErrorMassage);
		request.setAttribute("userInfo", userDto);
		
		request.setAttribute("successMessage", "");
	    request.setAttribute("errorMessage", "");
	    
		return "success";          	
	}
	
private boolean readUploadedExcel(List lsAppID,List lsSSC_ROLL,String fileName)
{
	 System.setProperty("file.encoding", "UTF-8");
	 Charset utf8charset = Charset.forName("UTF-8");
	 System.out.println("Default Charset=" + Charset.defaultCharset());

	 try
	{

		 
		FileInputStream file = new FileInputStream(new File(fileName));

		//Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		//Get first/desired sheet from the workbook
		XSSFSheet sheet = workbook.getSheetAt(0);

		//Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		int rowNum=-1;
		while (rowIterator.hasNext()) 
		{
			Row row = rowIterator.next();
			//For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();
			rowNum=rowNum+1;
			if(rowNum!=0)
			{
			int index=1;
			if(rowNum>500)break;
			while (cellIterator.hasNext()) 
			{
				
				Cell cell = cellIterator.next();
				String cellValue= new String();
				switch (cell.getCellType()) 
				  {
				    case Cell.CELL_TYPE_NUMERIC:
				    	cellValue=String.valueOf(cell.getNumericCellValue());
				    	cellValue=cellValue.replace(".0", "");
						break;
					case Cell.CELL_TYPE_STRING:
						cellValue=cell.getStringCellValue();
					   	break;
					case Cell.CELL_TYPE_BLANK:
						cellValue="";
					   	break;
				   }
				cellValue=cellValue.trim();
				cellValue=cellValue.replace("-", "");
				System.out.print(cellValue + "\t");
				if((cellValue==null||cellValue.equals(""))&& rowNum!=500)
				{
					ErrorMassage="Please Check your Excel file at ROW No. "+(rowNum+1)+" and COLUMN No. "+index;
					//return false;
				}
				else
				{
					ErrorMassage="You have successfully uploaded";
				}
				
				if(index==1)
				{
					lsAppID.add(cellValue);
				}
				else if(index==2)
					lsSSC_ROLL.add(cellValue);
				
				index++;
				
			}
		 }
			System.out.println("");
		}
		file.close();
		
		
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	} 
	
	return true;
}

	public String grantSpecialQuota(){
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		String[] appid = applicationID.split("###");
		
		for(int i=0;i<appid.length;i++)
		{
			ListOfStudentOfCollegeDAO.updateSQGrant(appid[i], userDto.getEiin(), grant);
		}
		Gson gson = new Gson();
		String json = "{\"result\":\"yes\"}";
		setJsonResponse(json);
		
//		boolean resp = false;
//		String	applicationID[] =request.getParameterValues("applicationID");
//		String action = request.getParameter("formAction");
//		CollegeDTO userDto = (CollegeDTO) session.get("user");
//		if(userDto == null){
//			return "input";
//		}
//		if(applicationID!=null) {		
//	    ListOfStudentOfCollegeDAO grantSpecialQuotaDAO=new ListOfStudentOfCollegeDAO();
//		if(action.equalsIgnoreCase("approve")){
//			resp =grantSpecialQuotaDAO.updateSpecialQuotaGrant(applicationID,userDto.getEiin(),"Y");
//			if (resp == true)
//				successMessage = "Special Quota is Approved Successfully";
//			else
//				errorMessage = "Somthing Wrong !!!..Try again.";
//		} else if(action.equalsIgnoreCase("disapprove")){
//			resp =grantSpecialQuotaDAO.updateSpecialQuotaGrant(applicationID,userDto.getEiin(),"N");
//			if (resp == true)
//				successMessage = "Special Quota is Approval Revoked Successfully";
//			else
//				errorMessage = "Somthing Wrong !!!..Try again.";
//		}
//		
//		}
		
		return null;
        
	}
	
public String approveSpecialQuotaIndividualy(){
	
	CollegeDTO userDto = (CollegeDTO) session.get("user");
	if(userDto == null){
		return "input";
	}
		
	String[] application_ID=null;
    String[] eiin=null;	
    boolean resp=false;
	
		String	applicationID[] =request.getParameterValues("applicationID");	
		if(applicationID!=null) {
	for(int i=0;i<applicationID.length;i++){
		
	    String serialNo=applicationID[i];
	    String[] parts = serialNo.split("#");
	    String string1 = parts[0]; 
	    String string2 = parts[1];
	    
	    
	    application_ID = new String[applicationID.length]; 
	    eiin = new String[applicationID.length] ;
	    
	    
	    application_ID[i]=string1;
	    eiin[i]=string2;
	    
	    
	    ListOfStudentOfCollegeDAO grantSpecialQuotaDAO=new ListOfStudentOfCollegeDAO();
		resp=grantSpecialQuotaDAO.approveSpecialQuotaIndividualy(application_ID,eiin);
	}
	
	ListOfStudentOfCollegeDAO specialQuotaListOfStudentOfCollegeDAO = new ListOfStudentOfCollegeDAO();
	List<ListOfStudentOfCollegeDTO> specialQuotaStudentList = specialQuotaListOfStudentOfCollegeDAO.getlistOfStudentOfSpecialQuota(userDto.getEiin());
	request.setAttribute("specialQuotaStudentList", specialQuotaStudentList);
	
	if (resp == true) {
		successMessage = "Special Quota is Approved SuccessFully";
		errorMessage=" ";
		
	}
	
	else{
		errorMessage = "Somthing Wrong !!!..Try again.";
		
	    }
	
		}
		
		return "success";
	          
	
}

public String educationQuotaList(){
	
	CollegeDTO userDto = (CollegeDTO) session.get("user");
	if(userDto == null){
		return "input";
	}
	this.from_where="educationalQuotaList";
	//OTP Checking
	Boolean otpCheck = (Boolean)session.get("otp_validation");
	if(otpCheck == null || otpCheck == false)
    	return "optform";
    
	ListOfStudentOfCollegeDAO educationQuotaListOfStudentOfCollegeDAO = new ListOfStudentOfCollegeDAO();
	List<ListOfStudentOfCollegeDTO> educationQuotaStudentList = educationQuotaListOfStudentOfCollegeDAO.getlistOfStudentOfEducationQuota(userDto.getEiin());
	request.setAttribute("educationQuotaStudentList", educationQuotaStudentList);
	request.setAttribute("userInfo", userDto);
	
	request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
	    
	return "success";

		
	          

}

public String newEducationQuotaStudent(){
	
	CollegeDTO userDto = (CollegeDTO) session.get("user");
	if(userDto == null){
		return "input";
	}
	ListOfStudentOfCollegeDAO neweducationQuotaStudentinfoOfCollegeDAO = new ListOfStudentOfCollegeDAO();
	List<ListOfStudentOfCollegeDTO> neweducationQuotaStudentinfoList = neweducationQuotaStudentinfoOfCollegeDAO.getnewStudentOfEducationQuota(applicationID,userDto.getEiin());
	request.setAttribute("neweducationQuotaStudentinfoList", neweducationQuotaStudentinfoList);
	request.setAttribute("userInfo", userDto);
	return "success";

		
	          

}

public String grantEducationQuota(){
	
	String action = request.getParameter("formAction");	
	String	applicationID[] =request.getParameterValues("applicationID");
	CollegeDTO userDto = (CollegeDTO) session.get("user");
	if(userDto == null){
		return "input";
	}
	if(applicationID!=null) {
		ListOfStudentOfCollegeDAO grantEducationQuotaDAO=new ListOfStudentOfCollegeDAO();
		if(action.equalsIgnoreCase("approve")){
			boolean resp=grantEducationQuotaDAO.updateEducationQuotaGrant(applicationID,userDto.getEiin(), "Approved");
			if (resp == true)
				successMessage = "EQ is Approved Successfully";			
			else
				errorMessage = "Somthing Wrong !!!..Try again.";
		} else if(action.equalsIgnoreCase("disApprove")){
			boolean resp=grantEducationQuotaDAO.updateEducationQuotaGrant(applicationID,userDto.getEiin(), "Pending");
			if (resp == true)
				successMessage = "EQ Approval Revoked Successfully";			
			else
				errorMessage = "Somthing Wrong !!!..Try again.";
		}
	}
	return "success";

}

public String approveEducationQuotaIndividualy(){
	
String[] application_ID=null;
String[] eiin=null;	
boolean resp=false;

CollegeDTO userDto = (CollegeDTO) session.get("user");
if(userDto == null){
	return "input";
}

	String	applicationID[] =request.getParameterValues("applicationID");	
	if(applicationID!=null) {
for(int i=0;i<applicationID.length;i++){
	
    String serialNo=applicationID[i];
    String[] parts = serialNo.split("#");
    String string1 = parts[0]; 
    String string2 = parts[1];
    
    
    application_ID = new String[applicationID.length]; 
    eiin = new String[applicationID.length] ;
    
    
    application_ID[i]=string1;
    eiin[i]=string2;
    
    
    ListOfStudentOfCollegeDAO grantEducationQuotaDAO=new ListOfStudentOfCollegeDAO();
	resp=grantEducationQuotaDAO.approveEducationQuotaIndividualy(application_ID,eiin);
}
	
if (resp == true) {
	
	successMessage = "EQ is Approved Successfully";		
}

else{
	errorMessage = "Somthing Wrong !!!..Try again.";
	
    }
	}

	
	return "success";

		
	          

}
	
	
	
	public String listOfApplicantOfCollegeSearchByBoard(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}

		
		ListOfStudentOfCollegeDAO listOfStudentOfCollegeDAO = new ListOfStudentOfCollegeDAO();
		List<ListOfStudentOfCollegeDTO> studentList = listOfStudentOfCollegeDAO.getlistOfStudentOfCollege(eiinCode);
		request.setAttribute("studentList", studentList);
		return "success";

			
		          
	
	}


	
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getEiinCode() {
		return eiinCode;
	}

	public void setEiinCode(String eiinCode) {
		this.eiinCode = eiinCode;
	}

	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	public ServletContext getServlet() {
		return servlet;
	}

	public void setServlet(ServletContext servlet) {
		this.servlet = servlet;
	}


	private ServletContext servlet;
	public ServletContext getServletContext()
	{
		return servlet;
	}

	public void setServletContext(ServletContext servlet)
	{
		this.servlet = servlet;
	}
	
	public File getMyFile() {
	      return myFile;
	   }
	   public void setMyFile(File myFile) {
	      this.myFile = myFile;
	   }
	   public String getMyFileContentType() {
	      return myFileContentType;
	   }
	   public void setMyFileContentType(String myFileContentType) {
	      this.myFileContentType = myFileContentType;
	   }
	   public String getMyFileFileName() {
	      return myFileFileName;
	   }
	   public void setMyFileFileName(String myFileFileName) {
	      this.myFileFileName = myFileFileName;
	   }

	public String getFrom_where() {
		return from_where;
	}

	public void setFrom_where(String fromWhere) {
		from_where = fromWhere;
	}

	public String getSsc_roll() {
		return ssc_roll;
	}

	public void setSsc_roll(String ssc_roll) {
		this.ssc_roll = ssc_roll;
	}

	public String getSsc_board() {
		return ssc_board;
	}

	public void setSsc_board(String ssc_board) {
		this.ssc_board = ssc_board;
	}

	public String getSsc_year() {
		return ssc_year;
	}

	public void setSsc_year(String ssc_year) {
		this.ssc_year = ssc_year;
	}

	public String getSsc_reg() {
		return ssc_reg;
	}

	public void setSsc_reg(String ssc_reg) {
		this.ssc_reg = ssc_reg;
	}
	
	
	
	   

}	
	

