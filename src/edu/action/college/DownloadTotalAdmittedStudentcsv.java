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



public class DownloadTotalAdmittedStudentcsv extends BaseAction{

	private static final long serialVersionUID = 2775177229066309566L;
		
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private  String meritType;
	
	private String shiftId;
	private String versionId;
	private String groupId;
	
		 
	

	//CSV file header
	private static final String FILE_HEADER = "SHIFT,VERSION,GROUP,NAME,SSC ROLL,BOARD,YEAR,QUOTA,MERIT";
	
	
	public String downloadTotalAdmittedStudentcsv(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		AdmissionDAO approvedStudentListOfMeritDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> approvedStudentListOfMerit = approvedStudentListOfMeritDAO.getTotalAdmittedStudentSVGWise(shiftId,versionId,groupId,userDto.getEiin());
		request.setAttribute("approvedStudentListOfMerit", approvedStudentListOfMerit);
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String datatime= dateFormat.format(date);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""+userDto.getCollege_name()+"_AdmittedStudent_"+datatime+".csv\"");
		
		
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
				
			
								
				line.append(approvedStudentOfMeritDTO.getShiftName());
				line.append(COMMA_DELIMITER);
				
				line.append(approvedStudentOfMeritDTO.getVersionName());
				line.append(COMMA_DELIMITER);
				
				line.append(approvedStudentOfMeritDTO.getGroupName());
				line.append(COMMA_DELIMITER);
				
				
				
				
				
/*            	line.append(approvedStudentOfMeritDTO.getApplicationID());
				line.append(COMMA_DELIMITER);*/
				
				line.append(approvedStudentOfMeritDTO.getApplicantName());
				line.append(COMMA_DELIMITER);
				
				line.append(approvedStudentOfMeritDTO.getSscRollNo());
				line.append(COMMA_DELIMITER);
				
				line.append(approvedStudentOfMeritDTO.getBoardName());
				line.append(COMMA_DELIMITER);
				
				line.append(approvedStudentOfMeritDTO.getSscPassingYear());
				line.append(COMMA_DELIMITER);
				
				
				
				
	/*			line.append("M"+approvedStudentOfMeritDTO.getMobileNo());
				line.append(COMMA_DELIMITER);*/
				 meritType=approvedStudentOfMeritDTO.getMeritType();
				 String merit=null;
				 
				String quotaStat = null;
				if(meritType.equalsIgnoreCase("1"))
		  		{

				if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("GENERAL"))
					quotaStat = "GENERAL";				
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("FREEDOM"))
					quotaStat = "FREEDOM FIGHTER QUOTA";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("EDUCATION"))
					quotaStat = "EDUCATION QUOTA";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("DISTRICT"))
					quotaStat = "DIVISION/DISTRICT QUOTA";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("FOREIGN"))
					quotaStat = "EXPATRIATE QUOTA";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("BKSP"))
					quotaStat = "BKSP QUOTA";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("SPECIAL"))
					quotaStat = "SPECIAL QUOTA";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("OWN"))
					quotaStat = "OWN CATEGORY";
				
		  		}
				else if(meritType.equalsIgnoreCase("2")||meritType.equalsIgnoreCase("3")||meritType.equalsIgnoreCase("4"))
		  		{

				if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("GENERAL"))
					quotaStat = "GENERAL";				
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("FREEDOM"))
					quotaStat = "FREEDOM FIGHTER QUOTA";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("EDUCATION"))
					quotaStat = "EDUCATION QUOTA";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("DISTRICT"))
					quotaStat = "DIVISION/DISTRICT QUOTA";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("FOREIGN"))
					quotaStat = "EXPATRIATE QUOTA";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("BKSP"))
					quotaStat = "BKSP QUOTA";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("SPECIAL"))
					quotaStat = "SPECIAL QUOTA";
				else if(approvedStudentOfMeritDTO.getAssignedQuota().equalsIgnoreCase("OWN"))
					quotaStat = "OWN CATEGORY";
				
		  		}
				
				else if(meritType.equalsIgnoreCase("5"))
		  		{
					quotaStat=" ";
		  		}
				
				line.append(quotaStat);
				line.append(COMMA_DELIMITER);
				

				 
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
					
				line.append(merit);
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


	public String getShiftId() {
		return shiftId;
	}


	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}


	public String getVersionId() {
		return versionId;
	}


	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}


	public String getGroupId() {
		return groupId;
	}


	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
}
