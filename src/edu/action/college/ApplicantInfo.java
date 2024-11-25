package edu.action.college;

import java.util.List;

import edu.action.common.BaseAction;
import edu.dto.college.ApplicantInfoDTO;
import edu.dto.college.CollegeDTO;
import edu.dao.college.ApplicantInfoDAO;


public class ApplicantInfo extends BaseAction{

	private static final long serialVersionUID = 2775177229066309566L;
	
	private String applicationID;

	public String collegeApplicantInfo(){
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}

		/*System.out.println("Test...Applicant");*/
		
		ApplicantInfoDAO applicantInfoDAO = new ApplicantInfoDAO();
		ApplicantInfoDTO applicantInfoDTO=applicantInfoDAO.getApplicantBasicInfo(applicationID);

	    request.setAttribute("applicationId",applicantInfoDTO.getApplicationID());
	    request.setAttribute("studentName",applicantInfoDTO.getApplicantName());
	    request.setAttribute("fatherName",applicantInfoDTO.getFatherName());
	    request.setAttribute("sscRollNo",applicantInfoDTO.getSscRollNo());
	    request.setAttribute("boardName",applicantInfoDTO.getBoardName());
	    request.setAttribute("passingYear",applicantInfoDTO.getSscPassingYear());

		
	    ApplicantInfoDAO applicantCollegeInfoDAO = new ApplicantInfoDAO();
		List<ApplicantInfoDTO> applicantCollegeInfoList = applicantCollegeInfoDAO.getApplicantCollegeInfoList(applicationID,userDto.getEiin());
		request.setAttribute("applicantCollegeInfoList", applicantCollegeInfoList);
		
		request.setAttribute("userInfo", userDto);
		
		return "success";

			
		          
	
	}

	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	
	
	
	
}
