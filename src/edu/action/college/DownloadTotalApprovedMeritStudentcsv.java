package edu.action.college;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import edu.action.common.BaseAction;
import edu.dao.college.AdmissionDAO;
import edu.dto.college.ApplicantInfoDTO;
import edu.dto.college.CollegeDTO;



public class DownloadTotalApprovedMeritStudentcsv extends BaseAction{

	private static final long serialVersionUID = 2775177229066309566L;
		
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private  String meritType;
		 
	

	//CSV file header
	private static final String FILE_HEADER = "SHIFT,VERSION,GROUP,QUOTA,MERIT,STATUS,NAME,SSC ROLL,BOARD,YEAR";
	
	
	public String downloadTotalApprovedMeritStudentCSV(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		AdmissionDAO approvedStudentListOfMeritDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> approvedStudentListOfMerit = approvedStudentListOfMeritDAO.getTotalApprovedStudentListOfMerit(userDto.getEiin());
		request.setAttribute("approvedStudentListOfMerit", approvedStudentListOfMerit);
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String datatime= dateFormat.format(date);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""+userDto.getCollege_name()+"_TotalApprovedMeritStudent_"+datatime+".csv\"");
		
		
/*		String header="Date: "+ddate+" To Date: "+todate+" Module: "+module+" Shift: "+shift+",,,,,";*/
		
		try {
/*			response.getOutputStream().write(header.toString().getBytes());			
			response.getOutputStream().write(NEW_LINE_SEPARATOR.toString().getBytes());*/
			 
	        response.getOutputStream().write(FILE_HEADER.toString().getBytes());			
			response.getOutputStream().write(NEW_LINE_SEPARATOR.toString().getBytes());
			
			StringBuffer line = new StringBuffer();

			ApplicantInfoDTO approvedStudentOfMeritDTO=new ApplicantInfoDTO();
			for(int i=0;i<approvedStudentListOfMerit.size();i++){
				
				
				approvedStudentOfMeritDTO = approvedStudentListOfMerit.get(i);
				
			/*	line.append(approvedStudentOfMeritDTO.getApplicationID());
				line.append(COMMA_DELIMITER);*/
								
				line.append(approvedStudentOfMeritDTO.getShiftName());
				line.append(COMMA_DELIMITER);
				
				line.append(approvedStudentOfMeritDTO.getVersionName());
				line.append(COMMA_DELIMITER);
				
				line.append(approvedStudentOfMeritDTO.getGroupName());
				line.append(COMMA_DELIMITER);
				
				
				
				String quotaStat = null;
				if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("GEN"))
					quotaStat = "General";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("D_G"))
					quotaStat = "General";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("E_G"))
					quotaStat = "General";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("F_G"))
					quotaStat = "General";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("S_G"))
					quotaStat = "General";
				
				
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("FQ"))
					quotaStat = "Freedom Fighter Quota";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("EQ"))
					quotaStat = "Education Quota";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("DQ"))
					quotaStat = "Division Quota";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("ZQ"))
					quotaStat = "Zilla Quota";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("SQ"))
					quotaStat = "Special Quota";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("OWN"))
					quotaStat = "OWN Category";
				
				line.append(quotaStat);
				line.append(COMMA_DELIMITER);
				
				 meritType=approvedStudentOfMeritDTO.getMeritType();
				 String merit=null;
				 
					if(meritType.equalsIgnoreCase("1"))
					{
						merit="1st Merit List";
				    }
					else if(meritType.equalsIgnoreCase("2"))
					{
						merit="1st Merit Migration[IN]";
				    }
					else if(meritType.equalsIgnoreCase("3"))
					{
						merit="2nd Merit List";
				    }
					else if(meritType.equalsIgnoreCase("4"))
					{
						merit="2nd Merit Migration[IN]";
				    }
					
					else if(meritType.equalsIgnoreCase("5"))
					{
						merit="1st Release Slip";
				    }
					
					else if(meritType.equalsIgnoreCase("9"))
					{
						merit="4th Phase";
				    }
					
				line.append(merit);
                line.append(COMMA_DELIMITER);
				
				line.append(approvedStudentOfMeritDTO.getAdmitStatus());
				line.append(COMMA_DELIMITER);
				
				line.append(approvedStudentOfMeritDTO.getApplicantName());
				line.append(COMMA_DELIMITER);
				
				line.append(approvedStudentOfMeritDTO.getSscRollNo());
				line.append(COMMA_DELIMITER);
				
				line.append(approvedStudentOfMeritDTO.getBoardName());
				line.append(COMMA_DELIMITER);
				
				line.append(approvedStudentOfMeritDTO.getSscPassingYear());
				line.append(NEW_LINE_SEPARATOR);
				
				
/*				
				
				line.append("M"+approvedStudentOfMeritDTO.getMobileNo());
				line.append(NEW_LINE_SEPARATOR);*/
				
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
