package edu.action.interBoard;

import java.util.List;

import edu.action.common.BaseAction;
import edu.dto.board.ApplicantInfoBoardDTO;
import edu.dto.interBoard.InterBoardDTO;
import edu.dao.board.ApplicantInfoDAO;


public class ApplicantInfo extends BaseAction{

	private static final long serialVersionUID = 2775177229066309566L;
	
	private String applicationID;
	private String eiinCode;

	public String collegeApplicantInfo(){
		

		/*System.out.println("Test...Applicant");*/
		InterBoardDTO userDto = (InterBoardDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		ApplicantInfoDAO applicantInfoDAO = new ApplicantInfoDAO();
		ApplicantInfoBoardDTO applicantInfoDTO=applicantInfoDAO.getApplicantBasicInfo(applicationID);

	    request.setAttribute("applicationId",applicantInfoDTO.getApplicationID());
	    request.setAttribute("studentName",applicantInfoDTO.getApplicantName());
	    request.setAttribute("fatherName",applicantInfoDTO.getFatherName());
	    request.setAttribute("sscRollNo",applicantInfoDTO.getSscRollNo());
	    request.setAttribute("boardName",applicantInfoDTO.getBoardName());
	    request.setAttribute("passingYear",applicantInfoDTO.getSscPassingYear());

		
	    ApplicantInfoDAO applicantCollegeInfoDAO = new ApplicantInfoDAO();
		List<ApplicantInfoBoardDTO> applicantCollegeInfoList = applicantCollegeInfoDAO.getApplicantCollegeInfoList(applicationID,eiinCode);
		request.setAttribute("applicantCollegeInfoList", applicantCollegeInfoList);
				
		return "success";

			
		          
	
	}

	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	public String getEiinCode() {
		return eiinCode;
	}

	public void setEiinCode(String eiinCode) {
		this.eiinCode = eiinCode;
	}
	
	
	
	
}
