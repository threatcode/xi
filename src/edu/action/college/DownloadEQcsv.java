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



public class DownloadEQcsv extends BaseAction{

	private static final long serialVersionUID = 2775177229066309566L;
	
	
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	private static final String FILE_HEADER = "APPLICATION_ID,SSC_ROLL,YEAR,BOARD,NAME,FATHER_NAME,MOBILE,EQ_STATUS";
	
	
	public String downloadeqCSV(){
				
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		ListOfStudentOfCollegeDAO educationQuotaListOfStudentOfCollegeDAO = new ListOfStudentOfCollegeDAO();
		List<ListOfStudentOfCollegeDTO> educationQuotaStudentList = educationQuotaListOfStudentOfCollegeDAO.getlistOfStudentOfEducationQuota(userDto.getEiin());
		
		
/*		DowntimeDAO  dDAO = new DowntimeDAO();
		
		stophistoo=dDAO.fetchStopHist(ddate, todate, module, shift);*/
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String datatime= dateFormat.format(date);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""+userDto.getCollege_name()+"_EQ_"+datatime+".csv\"");
		
		
/*		String header="Date: "+ddate+" To Date: "+todate+" Module: "+module+" Shift: "+shift+",,,,,";*/
		
		try {
/*			response.getOutputStream().write(header.toString().getBytes());			
			response.getOutputStream().write(NEW_LINE_SEPARATOR.toString().getBytes());*/
			 
	        response.getOutputStream().write(FILE_HEADER.toString().getBytes());			
			response.getOutputStream().write(NEW_LINE_SEPARATOR.toString().getBytes());
			
			StringBuffer line = new StringBuffer();

			ListOfStudentOfCollegeDTO educationQuotaStudentDTO=new ListOfStudentOfCollegeDTO();
			for(int i=0;i<educationQuotaStudentList.size();i++){				
				educationQuotaStudentDTO = educationQuotaStudentList.get(i);
				
				line.append(educationQuotaStudentDTO.getApplicationID());
				line.append(COMMA_DELIMITER);
				
				line.append(educationQuotaStudentDTO.getSscRollNo());
				line.append(COMMA_DELIMITER);
				
				line.append(educationQuotaStudentDTO.getSscPassingYear());
				line.append(COMMA_DELIMITER);
				
				line.append(educationQuotaStudentDTO.getBoardName());
				line.append(COMMA_DELIMITER);
				
				line.append(educationQuotaStudentDTO.getApplicantName().replaceAll(",", " "));
				line.append(COMMA_DELIMITER);
				
				line.append(educationQuotaStudentDTO.getFatherName());
				line.append(COMMA_DELIMITER);
				
				line.append(educationQuotaStudentDTO.getApplicantMobileNumber());
				line.append(COMMA_DELIMITER);
				
				line.append(educationQuotaStudentDTO.getEducationQuotaGrant());
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
