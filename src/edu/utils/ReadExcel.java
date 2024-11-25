package edu.utils;

import com.opensymphony.xwork2.ActionSupport;

import edu.dao.board.ApplicantInfoDAO;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;



import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class ReadExcel extends ActionSupport implements ServletContextAware{
	   private File myFile;
	   private String myFileContentType;
	   private String myFileFileName;
	   private String destPath;
	   

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
	
	String ErrorMassage="";
	public String execute() throws Exception {
			        /* Copy file to a safe location */
			        destPath = servlet.getRealPath("");

			        try{
			       	 System.out.println("Src File name: " + myFile);
			       	 System.out.println("Dst File name: " + myFileFileName);
			       	    	 
			       	 if(myFileFileName==null)
			       		 {
			       		  addActionError("Please Select a File!");
			       		  return ERROR;
			       		 }
			       	 
			       	 File destFile  = new File(destPath, myFileFileName);
			       	
			      	 FileUtils.copyFile(myFile, destFile);
			    
			        }catch(IOException e){
			           e.printStackTrace();
			           return ERROR;
			        }
			        
			        
			        boolean isOk=readUploadedExcel(destPath+"/"+myFileFileName);
			        if(!isOk){
			        	addActionError(ErrorMassage);
			        	return ERROR;
			        }
			        else
			        {
			        	addActionMessage("You have successfully uploaded");
			        }
			        return SUCCESS;
	}
     private boolean readUploadedExcel(String fileName)
     {
    	 //ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
    	 //ServletActionContext. .setCharacterEncoding("UTF-8");
    	 //request.setCharacterEncoding("UTF-8");
    	 //System.out.println("file.encoding=" + System.getProperty("file.encoding"));
    	 //System.setProperty("Charset", "UTF-8");
    	 System.setProperty("file.encoding", "UTF-8");
    	 //System.out.println("file.encoding=" + System.getProperty("file.encoding"));
    	 
    	 Charset utf8charset = Charset.forName("UTF-8");
    	 System.out.println("Default Charset=" + Charset.defaultCharset());
    	 
    	 ApplicantInfoDAO objApplicantinfoDAO= new ApplicantInfoDAO();
    	 try
 		{
    		 List<String> SSC_ROLL=  new ArrayList<String>();
    		 List<String> SSC_BOARD=  new ArrayList<String>();
    		 List<String> SSC_PASSING_YEAR=  new ArrayList<String>();
    		 List<String> SSC_REG=  new ArrayList<String>();
    		 List<String> MOBILE_NO=  new ArrayList<String>();
    		 
    		 
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
 				if(rowNum>80)break;
 				while (cellIterator.hasNext()) 
 				{
 					
 					Cell cell = cellIterator.next();
 					//int type=cell.getCellType();
 					//System.out.print(Integer.toString(type) +cell.getStringCellValue()+"-"+ "\t");
 					String cellValue= new String();
 					switch (cell.getCellType()) 
					  {
					    case Cell.CELL_TYPE_NUMERIC:
					    	cellValue=String.valueOf(cell.getNumericCellValue());
					    	cellValue=cellValue.replace(".0", "");
							//System.out.print(cell.getNumericCellValue() + "\t");
						    break;
						case Cell.CELL_TYPE_STRING:
							cellValue=cell.getStringCellValue();
						   	//System.out.print(cell.getStringCellValue() + "\t");
							break;
						case Cell.CELL_TYPE_BLANK:
							cellValue="";
						   	//System.out.print(cell.getStringCellValue() + "\t");
							break;
					   }
 					cellValue=cellValue.trim();
 					cellValue=cellValue.replace("-", "");
 					System.out.print(cellValue + "\t");
 					//Check the cell type and format accordingly
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
 						//Q_ID.add(cell.getStringCellValue());
 					  //switch (cell.getCellType()) 
 					  //{
 						//case Cell.CELL_TYPE_NUMERIC:
 						//	System.out.print(cell.getNumericCellValue() + "\t");
 						//	break;
 						//case Cell.CELL_TYPE_STRING:
 						//	Q_ID.add(cell.getStringCellValue());
 						//	break;
 					  //}
 					}
 					if(index==2)
 						SSC_ROLL.add(cellValue);
 					//if(index==3)
 						//SUBJ.add(cellValue);
 					if(index==3)
 						SSC_BOARD.add(cellValue);
 					if(index==4)
 						SSC_PASSING_YEAR.add(cellValue);
 					if(index==5)
 						SSC_REG.add(cellValue);
 					if(index==6)
 					{
 						String Mtest=cellValue.substring(0,2);
 						if(!Mtest.equals("01"))cellValue="0"+cellValue;
 						MOBILE_NO.add(cellValue);
 					}
 					//if(index==8)
 					//	OPTION4.add(cellValue);
 					//if(index==9)
 					//	ANS.add(cell.getStringCellValue());
 					
 					index++;
 					
 				}
 			 }
 				System.out.println("");
 			}
 			file.close();
 			
 			objApplicantinfoDAO.addXlsApplicantWithBatch(SSC_ROLL,SSC_BOARD,SSC_PASSING_YEAR,SSC_REG, MOBILE_NO);
 			
 		} 
 		catch (Exception e) 
 		{
 			e.printStackTrace();
 		} 
 		
 		return true;
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
}
