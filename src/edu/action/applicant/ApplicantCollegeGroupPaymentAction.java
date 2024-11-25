package edu.action.applicant;

import java.util.ArrayList;
import java.util.List;

import edu.action.common.BaseAction;
import edu.dao.applicant.ApplicantCollegeGroupPaymentDAO;
import edu.dto.UserDTO;
import edu.dto.applicant.ApplicantCollegeGroupPaymentDTO;

public class ApplicantCollegeGroupPaymentAction extends BaseAction{
	
	
	private static final long serialVersionUID = -817151534934913735L;
	
	ArrayList<ApplicantCollegeGroupPaymentDTO> priorityChangeByApplicantList;	

	public String applicantCollegeGroupPaymentinfo(){
		
		UserDTO userDto = (UserDTO) session.get("user");
		
		ApplicantCollegeGroupPaymentDAO applicantCollegeGroupPaymentDAO = new ApplicantCollegeGroupPaymentDAO();
		List<ApplicantCollegeGroupPaymentDTO> applicantCollegeGroupPaymentList = applicantCollegeGroupPaymentDAO.getApplicantCollegeGroupPayment(userDto.getUserid());
		request.setAttribute("applicantCollegeGroupPaymentList", applicantCollegeGroupPaymentList);
		request.setAttribute("userInfo", userDto);
		return "success";
		
	
	}
	
	
	public String priorityChangeByapplicant(){
		
		UserDTO userDto = (UserDTO) session.get("user");
		
		ApplicantCollegeGroupPaymentDAO applicantCollegeGroupPaymentDAO = new ApplicantCollegeGroupPaymentDAO();
		List<ApplicantCollegeGroupPaymentDTO> priorityChangeByApplicantList = applicantCollegeGroupPaymentDAO.getPriorityChangeByapplicant(userDto.getUserid());
		request.setAttribute("priorityChangeByApplicantList", priorityChangeByApplicantList);
		request.setAttribute("userInfo", userDto);
		return "success";
		
	
	}	
	
	public String updatepriorityByapplicant(){
		
		//UserDTO userDto = (UserDTO) session.get("user");
		
		ApplicantCollegeGroupPaymentDAO applicantCollegeGroupPaymentDAO = new ApplicantCollegeGroupPaymentDAO();

		boolean resp= applicantCollegeGroupPaymentDAO.updatePriorityByapplicant(priorityChangeByApplicantList);
	//	System.out.println(priorityChangeByApplicantList);
		if(resp == true)
		{
			System.out.println("Priority has changed Successfully......");
		}
		
		else{
			System.out.println("Priority Not Changed..!!!!");
		}
		return "success";
		
	
	}


	public ArrayList<ApplicantCollegeGroupPaymentDTO> getPriorityChangeByApplicantList() {
		return priorityChangeByApplicantList;
	}


	public void setPriorityChangeByApplicantList(
			ArrayList<ApplicantCollegeGroupPaymentDTO> priorityChangeByApplicantList) {
		this.priorityChangeByApplicantList = priorityChangeByApplicantList;
	}	


	
}
