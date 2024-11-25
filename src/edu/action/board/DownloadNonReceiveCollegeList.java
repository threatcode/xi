package edu.action.board;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import edu.action.common.BaseAction;
import edu.dao.college.AdmissionDAO;
import edu.dto.board.BoardDTO;
import edu.dto.college.CollegeDTO;



public class DownloadNonReceiveCollegeList extends BaseAction{

	private static final long serialVersionUID = 2775177229066309566L;
		
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private  String meritType;
		 

	//CSV file header
	private static final String FILE_HEADER = "EIIN,COLLEGE,DIST,MOBILE";
	
	
	public String downloadNonReceiveCollegeListCSV(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		
	    AdmissionDAO admissionDAO = new AdmissionDAO();
		List<CollegeDTO> nonReceivedCollegeList = admissionDAO.getNonReceivedList(userDto.getBoardId());
		request.setAttribute("nonReceivedCollegeList", nonReceivedCollegeList);
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String datatime= dateFormat.format(date);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""+userDto.getBoardName()+"_NonReceiveColleges_"+datatime+".csv\"");
		
		
/*		String header="Date: "+ddate+" To Date: "+todate+" Module: "+module+" Shift: "+shift+",,,,,";*/
		
		try {
/*			response.getOutputStream().write(header.toString().getBytes());			
			response.getOutputStream().write(NEW_LINE_SEPARATOR.toString().getBytes());*/
			 
	        response.getOutputStream().write(FILE_HEADER.toString().getBytes());			
			response.getOutputStream().write(NEW_LINE_SEPARATOR.toString().getBytes());
			
			StringBuffer line = new StringBuffer();

			CollegeDTO collegeDTO = new CollegeDTO();
			
			for(int i=0;i<nonReceivedCollegeList.size();i++){
				
				
				collegeDTO = nonReceivedCollegeList.get(i);
				
				line.append(collegeDTO.getEiin());
				line.append(COMMA_DELIMITER);
				
				line.append(collegeDTO.getCollege_name().replaceAll(",", " "));
				line.append(COMMA_DELIMITER);
				
				line.append(collegeDTO.getDist_name());
				line.append(COMMA_DELIMITER);
				
			
				
				line.append("M"+collegeDTO.getCollegeMobile());
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
