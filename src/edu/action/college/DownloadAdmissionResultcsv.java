package edu.action.college;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import edu.action.common.BaseAction;
import edu.dao.college.AdmissionDAO;

import edu.dto.college.CollegeDTO;
import edu.dto.college.ResultDTO;



public class DownloadAdmissionResultcsv extends BaseAction{

	private static final long serialVersionUID = 2775177229066309566L;
		
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private String shiftIdCSV;
	private String versionIdCSV;
	private String groupIdCSV;
	private String meritTypeCSV;
	private String groupNameCSV;
		 
	

	//CSV file header
	
	private static final String FILE_HEADER = "SSC ROLL,BOARD,YEAR,NAME,SHIFT,VERSION,GROUP,MERIT,SELECTION STATUS,WAITING POSITION";
	
	public String downloadResultCSV(){
		
		CollegeDTO useresultDTO = (CollegeDTO) session.get("user");
		
/*		AdmissionDAO admissionCancelListDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> 	admissionCancelListOfMerit = admissionCancelListDAO.getCancelledStudentListOfMerit(useresultDTO.getEiin());
		request.setAttribute("admissionCancelListOfMerit", admissionCancelListOfMerit);*/
		
		AdmissionDAO admDao = new AdmissionDAO();
	  	List<ResultDTO> resultList = null;
	  	resultList = admDao.getResultData(useresultDTO.getEiin(),shiftIdCSV, versionIdCSV, groupIdCSV, meritTypeCSV);
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String datatime= dateFormat.format(date);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("application/octet-stream");
		if(meritTypeCSV.equalsIgnoreCase("1"))
  		{
		response.setHeader("Content-Disposition", "attachment; filename=\""+useresultDTO.getCollege_name()+"_AdmissionResult(Merit)_"+datatime+".csv\"");
  		}
		else if(meritTypeCSV.equalsIgnoreCase("2"))
		{
			
			response.setHeader("Content-Disposition", "attachment; filename=\""+useresultDTO.getCollege_name()+"_Admission_Result(1st Waiting)_"+datatime+".csv\"");	
		}	
		
		else if(meritTypeCSV.equalsIgnoreCase("3"))
		{
			
			response.setHeader("Content-Disposition", "attachment; filename=\""+useresultDTO.getCollege_name()+"_Admission_Result(Waiting)_"+datatime+".csv\"");	
		}
		
		String shift="";
		String version="";
		
	    if(shiftIdCSV.equalsIgnoreCase("1"))
	    	shift = "Morning";
	    else if(shiftIdCSV.equalsIgnoreCase("2"))
	    	shift = "Day";
	    else if(shiftIdCSV.equalsIgnoreCase("3"))
	    	shift = "Evening";
	    
	    if(versionIdCSV.equalsIgnoreCase("1"))
	    	version = "Bangla";
	    else if(versionIdCSV.equalsIgnoreCase("2"))
	    	version = "English";
		
/*		String header="Date: "+ddate+" To Date: "+todate+" Module: "+module+" Shift: "+shift+",,,,,";*/
		
		try {
/*			response.getOutputStream().write(header.toString().getBytes());			
			response.getOutputStream().write(NEW_LINE_SEPARATOR.toString().getBytes());*/
			 
	        response.getOutputStream().write(FILE_HEADER.toString().getBytes());			
			response.getOutputStream().write(NEW_LINE_SEPARATOR.toString().getBytes());
			
			StringBuffer line = new StringBuffer();

			ResultDTO resultDTO=new ResultDTO();
			for(int i=0;i<resultList.size();i++){
				
				
				resultDTO = resultList.get(i);
				
/*				line.append(admissionCancelledStudentOfMeritDTO.getApplicationID());
				line.append(COMMA_DELIMITER);*/
				
				line.append(resultDTO.getSscRoll());
				line.append(COMMA_DELIMITER);
				
				line.append(resultDTO.getSscBoard());
				line.append(COMMA_DELIMITER);
				
				line.append(resultDTO.getSscYear());
				line.append(COMMA_DELIMITER);
				
				line.append(resultDTO.getApplicantName());
				line.append(COMMA_DELIMITER);
				
				
				
				line.append(shift);
				line.append(COMMA_DELIMITER);
				
				line.append(version);
				line.append(COMMA_DELIMITER);
				
				line.append(resultDTO.getGroupName());
				line.append(COMMA_DELIMITER);
				
				 String merit=null;
				 
					if(meritTypeCSV.equalsIgnoreCase("1"))
					{
						merit="Merit List";
				    }
					else if(meritTypeCSV.equalsIgnoreCase("2"))
					{
						merit="1st Waiting List";
				    }
					else if(meritTypeCSV.equalsIgnoreCase("3"))
					{
						merit="2nd Waiting List";
				    }
					else if(meritTypeCSV.equalsIgnoreCase("4"))
					{
						merit="Remaining Waiting List";
				    }
					else if(meritTypeCSV.equalsIgnoreCase("5"))
					{
						merit="Manual Admission";
				    }
				
				line.append(merit);
                line.append(COMMA_DELIMITER);
								
				String quotaStat = null;
				if(meritTypeCSV.equalsIgnoreCase("1"))
		  		{
					
				if(resultDTO.getApplicantQuota().equalsIgnoreCase("GENERAL"))
					quotaStat = "GENERAL";				
				else if(resultDTO.getApplicantQuota().equalsIgnoreCase("FREEDOM"))
					quotaStat = "FREEDOM FIGHTER QUOTA";
				else if(resultDTO.getApplicantQuota().equalsIgnoreCase("EDUCATION"))
					quotaStat = "EDUCATION QUOTA";
				else if(resultDTO.getApplicantQuota().equalsIgnoreCase("DISTRICT"))
					quotaStat = "DIVISION/DISTRICT QUOTA";
				else if(resultDTO.getApplicantQuota().equalsIgnoreCase("FOREIGN"))
					quotaStat = "EXPATRIATE QUOTA";
				else if(resultDTO.getApplicantQuota().equalsIgnoreCase("BKSP"))
					quotaStat = "BKSP QUOTA";
				else if(resultDTO.getApplicantQuota().equalsIgnoreCase("SPECIAL"))
					quotaStat = "SPECIAL QUOTA";
				else if(resultDTO.getApplicantQuota().equalsIgnoreCase("OWN"))
					quotaStat = "OWN CATEGORY";
		  		}
				
				
				else if(meritTypeCSV.equalsIgnoreCase("2"))
		  		{
					
				if(resultDTO.getApplicantQuota().equalsIgnoreCase("GENERAL"))
					quotaStat = "GENERAL";				
				else if(resultDTO.getApplicantQuota().equalsIgnoreCase("FREEDOM"))
					quotaStat = "FREEDOM FIGHTER QUOTA ; GENERAL";
				else if(resultDTO.getApplicantQuota().equalsIgnoreCase("EDUCATION"))
					quotaStat = "EDUCATION QUOTA ; GENERAL";
				else if(resultDTO.getApplicantQuota().equalsIgnoreCase("DISTRICT"))
					quotaStat = "DIVISION/DISTRICT QUOTA ; GENERAL";
				else if(resultDTO.getApplicantQuota().equalsIgnoreCase("FOREIGN"))
					quotaStat = "EXPATRIATE QUOTA ; GENERAL";
				else if(resultDTO.getApplicantQuota().equalsIgnoreCase("BKSP"))
					quotaStat = "BKSP QUOTA ; GENERAL";
				else if(resultDTO.getApplicantQuota().equalsIgnoreCase("SPECIAL"))
					quotaStat = "SPECIAL QUOTA ; GENERAL";
				else if(resultDTO.getApplicantQuota().equalsIgnoreCase("OWN"))
					quotaStat = "OWN CATEGORY ; GENERAL";
		  		}
				
				else if(meritTypeCSV.equalsIgnoreCase("3"))
		  		{
					
		  				quotaStat="GEN";	
			  			  			
		  			  if(resultDTO.getBq().equalsIgnoreCase("Y"))
			  		{
		  				quotaStat=quotaStat+";BQ";	
			  		}
		  			 if(resultDTO.getDq().equalsIgnoreCase("Y"))
			  		{
		  				quotaStat=quotaStat+";DQ";	
			  		}
		  			 if(resultDTO.getEq().equalsIgnoreCase("Y"))
			  		{
		  				quotaStat=quotaStat+";EQ";	
			  		}
		  			 if(resultDTO.getFq().equalsIgnoreCase("Y"))
			  		{
		  				quotaStat=quotaStat+";FQ";	
			  		}
		  			 if(resultDTO.getOq().equalsIgnoreCase("Y"))
				  	{
			  			quotaStat=quotaStat+";OQ";	
				  	}
		  			 if(resultDTO.getPq().equalsIgnoreCase("Y"))
			  		{
		  				quotaStat=quotaStat+";PQ";	
			  		}
		  			 if(resultDTO.getSq().equalsIgnoreCase("Y"))
			  		{
		  				quotaStat=quotaStat+";SQ";	
			  		}
		  			
					
		  		}
				
				
				line.append(quotaStat);
				line.append(COMMA_DELIMITER);
				
				if(meritTypeCSV.equalsIgnoreCase("1"))
				{
					line.append("N/A");
			    }
				else if(meritTypeCSV.equalsIgnoreCase("2"))
				{
					line.append(resultDTO.getMeritPosition());
			    }
				else if(meritTypeCSV.equalsIgnoreCase("3"))
				{
					line.append(resultDTO.getMeritPosition());
			    }
				else if(meritTypeCSV.equalsIgnoreCase("4"))
				{
					line.append("N/A");
			    }
				else if(meritTypeCSV.equalsIgnoreCase("5"))
				{
					line.append("N/A");
			    }
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

	public String getShiftIdCSV() {
		return shiftIdCSV;
	}

	public void setShiftIdCSV(String shiftIdCSV) {
		this.shiftIdCSV = shiftIdCSV;
	}

	public String getVersionIdCSV() {
		return versionIdCSV;
	}

	public void setVersionIdCSV(String versionIdCSV) {
		this.versionIdCSV = versionIdCSV;
	}

	public String getGroupIdCSV() {
		return groupIdCSV;
	}

	public void setGroupIdCSV(String groupIdCSV) {
		this.groupIdCSV = groupIdCSV;
	}

	public String getMeritTypeCSV() {
		return meritTypeCSV;
	}

	public void setMeritTypeCSV(String meritTypeCSV) {
		this.meritTypeCSV = meritTypeCSV;
	}

	public String getGroupNameCSV() {
		return groupNameCSV;
	}

	public void setGroupNameCSV(String groupNameCSV) {
		this.groupNameCSV = groupNameCSV;
	}
	
	
	
}
