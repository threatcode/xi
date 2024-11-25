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



public class DownloadNonApprovedMeritStudentcsv extends BaseAction{

	private static final long serialVersionUID = 2775177229066309566L;
		
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private  String meritType;
	private  String hiddenShiftIDCSV;
	private  String hiddenVersionIDCSV;
	private  String hiddenGroupIDCSV;
	private  String hiddenMeritCSV;
	private  String pRoll;
		 
	

	//CSV file header
	private static final String FILE_HEADER = "SSC ROLL,BOARD,YEAR,NAME,SHIFT,VERSION,GROUP,MERIT,STATUS,QUOTA";
	
	
	public String downloadNonApprovedMeritStudentCSV(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		AdmissionDAO nonApprovedStudentListOfMeritDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> nonApprovedStudentListOfMerit = nonApprovedStudentListOfMeritDAO.getNonApprovedStudentListOfMerit1(hiddenShiftIDCSV,hiddenVersionIDCSV,hiddenGroupIDCSV,hiddenMeritCSV,userDto.getEiin(),pRoll);
		request.setAttribute("nonApprovedStudentListOfMerit", nonApprovedStudentListOfMerit);
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String datatime= dateFormat.format(date);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""+userDto.getCollege_name()+"_NonApprovedMeritStudent_"+datatime+".csv\"");
		
		
/*		String header="Date: "+ddate+" To Date: "+todate+" Module: "+module+" Shift: "+shift+",,,,,";*/
		
		try {
/*			response.getOutputStream().write(header.toString().getBytes());			
			response.getOutputStream().write(NEW_LINE_SEPARATOR.toString().getBytes());*/
			 
	        response.getOutputStream().write(FILE_HEADER.toString().getBytes());			
			response.getOutputStream().write(NEW_LINE_SEPARATOR.toString().getBytes());
			
			StringBuffer line = new StringBuffer();

			ApplicantInfoDTO nonApprovedStudentOfMeritDTO=new ApplicantInfoDTO();
			for(int i=0;i<nonApprovedStudentListOfMerit.size();i++){
				
				
				nonApprovedStudentOfMeritDTO = nonApprovedStudentListOfMerit.get(i);
				
				/*line.append(nonApprovedStudentOfMeritDTO.getApplicationID());
				line.append(COMMA_DELIMITER);*/
				
				line.append(nonApprovedStudentOfMeritDTO.getSscRollNo());
				line.append(COMMA_DELIMITER);
				
				line.append(nonApprovedStudentOfMeritDTO.getBoardName());
				line.append(COMMA_DELIMITER);
				
				line.append(nonApprovedStudentOfMeritDTO.getSscPassingYear());
				line.append(COMMA_DELIMITER);
				
				line.append(nonApprovedStudentOfMeritDTO.getApplicantName());
				line.append(COMMA_DELIMITER);
				
				line.append(nonApprovedStudentOfMeritDTO.getShiftName());
				line.append(COMMA_DELIMITER);
				
				line.append(nonApprovedStudentOfMeritDTO.getVersionName());
				line.append(COMMA_DELIMITER);
				
				line.append(nonApprovedStudentOfMeritDTO.getGroupName());
				line.append(COMMA_DELIMITER);
				
				 meritType=nonApprovedStudentOfMeritDTO.getMeritType();
				 String merit=null;
				 
				if(meritType.equalsIgnoreCase("1"))
				{
					merit="First Merit List";
			    }
				else if(meritType.equalsIgnoreCase("2"))
				{
					merit="Second Merit List";
			    }
				else if(meritType.equalsIgnoreCase("3"))
				{
					merit="Third Merit List";
			    }
				else if(meritType.equalsIgnoreCase("4"))
				{
					merit="Fourth Merit List";
			    }
				else if(meritType.equalsIgnoreCase("5"))
				{
					merit="Fifth Merit List";
			    }
				else if(meritType.equalsIgnoreCase("6"))
				{
					merit="Sixth Merit List";
			    }
			
				line.append(merit);
                line.append(COMMA_DELIMITER);
				
				line.append(nonApprovedStudentOfMeritDTO.getAdmitStatus());
				line.append(COMMA_DELIMITER);
				
				String quotaStat = nonApprovedStudentOfMeritDTO.getAssignedQuota();
				
				
				
				line.append(quotaStat);
				line.append(NEW_LINE_SEPARATOR);
				
/*				line.append("M"+nonApprovedStudentOfMeritDTO.getMobileNo());
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


	public String getMeritType() {
		return meritType;
	}


	public void setMeritType(String meritType) {
		this.meritType = meritType;
	}


	public String getHiddenShiftIDCSV() {
		return hiddenShiftIDCSV;
	}


	public void setHiddenShiftIDCSV(String hiddenShiftIDCSV) {
		this.hiddenShiftIDCSV = hiddenShiftIDCSV;
	}


	public String getHiddenVersionIDCSV() {
		return hiddenVersionIDCSV;
	}


	public void setHiddenVersionIDCSV(String hiddenVersionIDCSV) {
		this.hiddenVersionIDCSV = hiddenVersionIDCSV;
	}


	public String getHiddenGroupIDCSV() {
		return hiddenGroupIDCSV;
	}


	public void setHiddenGroupIDCSV(String hiddenGroupIDCSV) {
		this.hiddenGroupIDCSV = hiddenGroupIDCSV;
	}


	public String getHiddenMeritCSV() {
		return hiddenMeritCSV;
	}


	public void setHiddenMeritCSV(String hiddenMeritCSV) {
		this.hiddenMeritCSV = hiddenMeritCSV;
	}


	public String getpRoll() {
		return pRoll;
	}


	public void setpRoll(String pRoll) {
		this.pRoll = pRoll;
	}
	
	
	
}
