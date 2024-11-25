package edu.action.board;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.interceptor.ServletRequestAware;

import edu.action.common.BaseAction;
import edu.dao.board.CollegeUploadDAO;

public class SVGuploadAction extends BaseAction implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8033302685724563793L;
	

	private File uploadFile;
	private String uploadFileContentType;
	private String uploadFileFileName;
	
	private HttpServletRequest servletRequest;
	
	private ServletContext servlet;

	String ErrorMassage="";
	

	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}



	public String getUploadFileContentType() {
		return uploadFileContentType;
	}
	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}



	public String getUploadFileFileName() {
		return uploadFileFileName;
	}
	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}
	
	
	
	
	public ServletContext getServlet() {
		return servlet;
	}
	public void setServlet(ServletContext servlet) {
		this.servlet = servlet;
	}
	
	
	public String execute()	{
		String filePath = "c:/Myuploads";
		
		try{
		  // Give your own path
		//String filePath = servlet.getRealPath("");
		//String filePath = servletRequest.getServletContext().getRealPath("/resources/excel");
        System.out.println("********************Server path:" + filePath); // test your path
        File fileToCreate = new File(filePath, uploadFileFileName);// Create file name  same as original
        FileUtils.copyFile(uploadFile, fileToCreate); // Just copy temp file content tos this file		
        //TimeUnit.SECONDS.sleep(30);		
		}catch(Exception e)
		{
			e.printStackTrace();
            addActionError(e.getMessage());
            return INPUT;
		}
		
		String fileParameter = filePath + "/" + uploadFileFileName;
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + fileParameter);
		
		
		
		boolean isOk = insertExcelToDB(fileParameter);
		
        if(!isOk){
        	addActionError(ErrorMassage);
        	return ERROR;
        }
		
		return SUCCESS;
	}
	
	
	
	public boolean insertExcelToDB(String fileName) {
		
		CollegeUploadDAO collegeUploadDao = new CollegeUploadDAO();
		
		try {
			
			List <String> EIIN = new ArrayList<String>();
			List <String> COLLEGE_NAME = new ArrayList<String>();
			List <String> MOBILE_NO = new ArrayList<String>();
			List <String> EMAIL = new ArrayList<String>();
			List <String> DIST_ID = new ArrayList<String>();
			List <String> THANA_ID = new ArrayList<String>();
			List <String> IS_METRO = new ArrayList<String>();
			List <String> IS_ZILL_SADAR = new ArrayList<String>();
			List <String> IS_GOVT = new ArrayList<String>();
			List <String> BOARD_ID = new ArrayList<String>();
			List <String> HELPER_BOARD_ID = new ArrayList<String>();
			List <String> IS_ACTIVE = new ArrayList<String>();
			List<String> IS_SQ_ELIGIBLE  = new ArrayList<String>();
			
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			
			//Workbook workbook = new XSSFWorkbook(excelFile);
			
			Workbook workbook = new HSSFWorkbook (excelFile);
			
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = datatypeSheet.iterator();
			
			int rowNum=-1;
			while (rowIterator.hasNext()){
				Row currentRow = rowIterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				rowNum=rowNum+1;
				int index=1;
				while (cellIterator.hasNext()){
					Cell currentCell = cellIterator.next();
					
					String cellValue= new String();
					switch(currentCell.getCellType()){
					case Cell.CELL_TYPE_NUMERIC:
						cellValue=String.valueOf(currentCell.getNumericCellValue());
						break;
					
					case Cell.CELL_TYPE_STRING:
						cellValue = currentCell.getStringCellValue();
						break;
					
					case Cell.CELL_TYPE_BLANK:
						cellValue = "";
						break;
						
					}
					
					if(cellValue==null) {
 						ErrorMassage="Please Check your Excel file at ROW No. "+(rowNum+1)+" and COLUMN No. "+index;
 						return false;
 					}
					
					System.out.println("______________Data Populating______________" + index);
					
					if(index ==1){
						EIIN.add(cellValue);
					}
					
					if(index ==2){
						COLLEGE_NAME.add(cellValue);
					}
					
					if(index ==3){
						MOBILE_NO.add(cellValue);
					}
					
					if(index ==4){
						EMAIL.add(cellValue);
					}
					
					if(index ==5){
						DIST_ID.add(cellValue);
					}
					
					if(index ==6){
						THANA_ID.add(cellValue);
					}
					
					if(index ==7){
						IS_METRO.add(cellValue);
					}
					
					if(index ==8){
						IS_ZILL_SADAR.add(cellValue);
					}
					
					if(index ==9){
						IS_GOVT.add(cellValue);
					}
					
				
					//System.out.println("cell value:" + cellValue);
				
					if(index ==10){
						//BOARD_ID.add(Integer.valueOf(cellValue));
						BOARD_ID.add(cellValue);
					}
					
					if(index ==11){
						
						//HELPER_BOARD_ID.add(Integer.valueOf(cellValue));
						HELPER_BOARD_ID.add(cellValue);
					}
					
					if(index ==12){
						//IS_ACTIVE.add(Integer.valueOf(cellValue));
						IS_ACTIVE.add(cellValue);
					}
					
					if(index ==13){
						//IS_SQ_ELIGIBLE.add(cellValue.charAt(0));
						IS_SQ_ELIGIBLE.add(cellValue);
					}
					
					index++;
					
				}
			}
			excelFile.close();
			collegeUploadDao.addColleges(EIIN, COLLEGE_NAME, MOBILE_NO, EMAIL, DIST_ID, THANA_ID, IS_METRO, IS_ZILL_SADAR, IS_GOVT, BOARD_ID, HELPER_BOARD_ID, IS_ACTIVE, IS_SQ_ELIGIBLE);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.servletRequest = servletRequest;
	}
	
	
	
}
