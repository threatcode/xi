package edu.action.college;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import edu.action.common.BaseAction;
import edu.dto.college.CollegeDTO;
import edu.dto.college.ListOfStudentOfCollegeDTO;
import edu.dao.college.ListOfStudentOfCollegeDAO;


import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;



public class DownloadSQcsv extends BaseAction{

	private static final long serialVersionUID = 2775177229066309566L;
		
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	private static final String FILE_HEADER = "SSC_ROLL,YEAR,BOARD,NAME,FATHER_NAME,SQ_STATUS";
	
	
	public String downloadsqCSV(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		ListOfStudentOfCollegeDAO specialQuotaListOfStudentOfCollegeDAO = new ListOfStudentOfCollegeDAO();
		List<ListOfStudentOfCollegeDTO> specialQuotaStudentList = specialQuotaListOfStudentOfCollegeDAO.getlistOfStudentOfSpecialQuota(userDto.getEiin());
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String datatime= dateFormat.format(date);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""+userDto.getCollege_name()+"_SQ_"+datatime+".csv\"");
		
		
/*		String header="Date: "+ddate+" To Date: "+todate+" Module: "+module+" Shift: "+shift+",,,,,";*/
		
		try {
/*			response.getOutputStream().write(header.toString().getBytes());			
			response.getOutputStream().write(NEW_LINE_SEPARATOR.toString().getBytes());*/
			 
	        response.getOutputStream().write(FILE_HEADER.toString().getBytes());			
			response.getOutputStream().write(NEW_LINE_SEPARATOR.toString().getBytes());
			
			StringBuffer line = new StringBuffer();
			
			String status="";
			String finalStatus="";

			ListOfStudentOfCollegeDTO specialQuotaStudentDTO=new ListOfStudentOfCollegeDTO();
			for(int i=0;i<specialQuotaStudentList.size();i++){				
				specialQuotaStudentDTO = specialQuotaStudentList.get(i);
				
			/*	line.append(specialQuotaStudentDTO.getApplicationID());
				line.append(COMMA_DELIMITER);*/
				
				
				line.append(specialQuotaStudentDTO.getSscRollNo());
				line.append(COMMA_DELIMITER);
				
				line.append(specialQuotaStudentDTO.getSscPassingYear());
				line.append(COMMA_DELIMITER);
				
				line.append(specialQuotaStudentDTO.getBoardName());
				line.append(COMMA_DELIMITER);
				
				line.append(specialQuotaStudentDTO.getApplicantName().replaceAll(",", " "));
				line.append(COMMA_DELIMITER);
				
				line.append(specialQuotaStudentDTO.getFatherName());
				line.append(COMMA_DELIMITER);
				
/*				line.append(specialQuotaStudentDTO.getApplicantMobileNumber().toString());
				line.append(COMMA_DELIMITER);*/
				
				status=specialQuotaStudentDTO.getSpecialQuotaGrant();
				if(status.equalsIgnoreCase("Y"))
				{
					finalStatus="Approved";
				}
				else if(status.equalsIgnoreCase("N"))
				{
					
					finalStatus="Pending";
				}
				
				
				line.append(finalStatus);
				line.append(NEW_LINE_SEPARATOR);
				
			}
			response.getOutputStream().write(line.toString().getBytes());
			response.getOutputStream().flush();
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		}
			
		return null;
	
	}
}
