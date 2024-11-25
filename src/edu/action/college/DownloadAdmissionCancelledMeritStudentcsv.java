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



public class DownloadAdmissionCancelledMeritStudentcsv extends BaseAction{

	private static final long serialVersionUID = 2775177229066309566L;
		
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private  String meritType;
		 
	

	//CSV file header
	private static final String FILE_HEADER = "SSC ROLL,BOARD,YEAR,NAME,SHIFT,VERSION,GROUP,MERIT,STATUS,QUOTA";
	
	public String downloadAdmissionCancelledMeritStudentCSV(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		AdmissionDAO admissionCancelListDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> 	admissionCancelListOfMerit = admissionCancelListDAO.getCancelledStudentListOfMerit(userDto.getEiin());
		request.setAttribute("admissionCancelListOfMerit", admissionCancelListOfMerit);
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String datatime= dateFormat.format(date);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""+userDto.getCollege_name()+"_AdmissionCancelledMeritStudent_"+datatime+".csv\"");
		
		
/*		String header="Date: "+ddate+" To Date: "+todate+" Module: "+module+" Shift: "+shift+",,,,,";*/
		
		try {
/*			response.getOutputStream().write(header.toString().getBytes());			
			response.getOutputStream().write(NEW_LINE_SEPARATOR.toString().getBytes());*/
			 
	        response.getOutputStream().write(FILE_HEADER.toString().getBytes());			
			response.getOutputStream().write(NEW_LINE_SEPARATOR.toString().getBytes());
			
			StringBuffer line = new StringBuffer();

			ApplicantInfoDTO admissionCancelledStudentOfMeritDTO=new ApplicantInfoDTO();
			for(int i=0;i<admissionCancelListOfMerit.size();i++){
				
				
				admissionCancelledStudentOfMeritDTO = admissionCancelListOfMerit.get(i);
				
/*				line.append(admissionCancelledStudentOfMeritDTO.getApplicationID());
				line.append(COMMA_DELIMITER);*/
				
				line.append(admissionCancelledStudentOfMeritDTO.getSscRollNo());
				line.append(COMMA_DELIMITER);
				
				line.append(admissionCancelledStudentOfMeritDTO.getBoardName());
				line.append(COMMA_DELIMITER);
				
				line.append(admissionCancelledStudentOfMeritDTO.getSscPassingYear());
				line.append(COMMA_DELIMITER);
				
				line.append(admissionCancelledStudentOfMeritDTO.getApplicantName());
				line.append(COMMA_DELIMITER);
				
				line.append(admissionCancelledStudentOfMeritDTO.getShiftName());
				line.append(COMMA_DELIMITER);
				
				line.append(admissionCancelledStudentOfMeritDTO.getVersionName());
				line.append(COMMA_DELIMITER);
				
				line.append(admissionCancelledStudentOfMeritDTO.getGroupName());
				line.append(COMMA_DELIMITER);
				
				 meritType=admissionCancelledStudentOfMeritDTO.getMeritType();
				 String merit=null;
				 
					if(meritType.equalsIgnoreCase("1"))
					{
						merit="Merit List";
				    }
					else if(meritType.equalsIgnoreCase("2"))
					{
						merit="1st Waiting List";
				    }
					else if(meritType.equalsIgnoreCase("3"))
					{
						merit="2nd Waiting List";
				    }
					else if(meritType.equalsIgnoreCase("4"))
					{
						merit="Remaining Waiting List";
				    }
					else if(meritType.equalsIgnoreCase("5"))
					{
						merit="Manual Admission";
				    }
	/*				else if(meritType.equalsIgnoreCase("2"))
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
				    }*/
				
				line.append(merit);
                line.append(COMMA_DELIMITER);
				
				line.append(admissionCancelledStudentOfMeritDTO.getAdmitStatus());
				line.append(COMMA_DELIMITER);
				
				String quotaStat = null;
				
				if(admissionCancelledStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("GENERAL"))
					quotaStat = "GENERAL";				
				else if(admissionCancelledStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("FREEDOM"))
					quotaStat = "FREEDOM FIGHTER QUOTA";
				else if(admissionCancelledStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("EDUCATION"))
					quotaStat = "EDUCATION QUOTA";
				else if(admissionCancelledStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("DISTRICT"))
					quotaStat = "DIVISION/DISTRICT QUOTA";
				else if(admissionCancelledStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("FOREIGN"))
					quotaStat = "EXPATRIATE QUOTA";
				else if(admissionCancelledStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("BKSP"))
					quotaStat = "BKSP QUOTA";
				else if(admissionCancelledStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("SPECIAL"))
					quotaStat = "SPECIAL QUOTA";
				else if(admissionCancelledStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("OWN"))
					quotaStat = "OWN CATEGORY";
				
				
				line.append(quotaStat);
				line.append(NEW_LINE_SEPARATOR);
				
/*				line.append("M"+admissionCancelledStudentOfMeritDTO.getMobileNo());
				line.append(NEW_LINE_SEPARATOR);
				*/
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
