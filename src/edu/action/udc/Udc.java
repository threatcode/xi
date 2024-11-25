package edu.action.udc;

import java.util.ArrayList;

import com.opensymphony.xwork2.Result;

import edu.action.common.BaseAction;
import edu.dao.applicant.ResultDAO;
import edu.dao.application.ApplicationDAO;
import edu.dao.application.SscDataDAO;
import edu.dao.college.CollegeDAO;
import edu.dto.applicant.ResultDTO;
import edu.dto.application.ApplicantDTO;
import edu.dto.application.ChoiceDTO;

public class Udc extends BaseAction {

	private static final long serialVersionUID = -6461802013670599090L;
	private String ssc_roll;
	private String ssc_board;
	private String ssc_year;
	private String ssc_reg;
	private String application_id;
	private ArrayList<ChoiceDTO> choiceList=new ArrayList<ChoiceDTO>();
	private ApplicantDTO applicant=new ApplicantDTO();
	
	private ResultDTO admission_result;
	
	public String showChoiceList(){
		CollegeDAO collegeDAO=new CollegeDAO();
		SscDataDAO sscDAO=new SscDataDAO();
		ApplicationDAO applicationDAO=new ApplicationDAO();
		String applicationId=applicationDAO.getApplicationId(ssc_roll, ssc_board, ssc_year, ssc_reg);
		applicant=sscDAO.getApplication(applicationId);
		choiceList=collegeDAO.getChoiceList(applicationId);	
		return SUCCESS;
	
	}
	public String showResult(){
		ResultDAO resultDAO=new ResultDAO();
		admission_result=resultDAO.getResult("");
		session.put("admissionResult", admission_result);
		return SUCCESS;
	
	}	
	
	public String choiceListReport(){
		
		SscDataDAO sscDAO=new SscDataDAO();
		CollegeDAO collegeDAO=new CollegeDAO();
		applicant=sscDAO.getApplication(application_id);
		choiceList=collegeDAO.getChoiceList(application_id);
		return SUCCESS;
		
	}

	public String viewResult()
	{
//		ResultDAO resultDAO=new ResultDAO();
//		admission_result=resultDAO.getResult(ssc_roll, ssc_board, ssc_year);
		admission_result = ResultDAO.getStaticResult(ssc_roll, ssc_board, ssc_year);
		return SUCCESS;

	}
	
	public String getSsc_roll() {
		return ssc_roll;
	}
	public void setSsc_roll(String sscRoll) {
		ssc_roll = sscRoll;
	}
	public String getSsc_board() {
		return ssc_board;
	}
	public void setSsc_board(String sscBoard) {
		ssc_board = sscBoard;
	}
	public String getSsc_year() {
		return ssc_year;
	}
	public void setSsc_year(String sscYear) {
		ssc_year = sscYear;
	}
	public String getSsc_reg() {
		return ssc_reg;
	}
	public void setSsc_reg(String sscReg) {
		ssc_reg = sscReg;
	}


	public ApplicantDTO getApplicant() {
		return applicant;
	}


	public void setApplicant(ApplicantDTO applicant) {
		this.applicant = applicant;
	}


	public ArrayList<ChoiceDTO> getChoiceList() {
		return choiceList;
	}


	public void setChoiceList(ArrayList<ChoiceDTO> choiceList) {
		this.choiceList = choiceList;
	}

	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String applicationId) {
		application_id = applicationId;
	}
	public ResultDTO getAdmission_result() {
		return admission_result;
	}
	public void setAdmission_result(ResultDTO admissionResult) {
		admission_result = admissionResult;
	}


}
