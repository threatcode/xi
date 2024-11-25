package edu.action.college;

import java.net.InetAddress;
import java.util.List;

import edu.action.common.BaseAction;
import edu.dao.college.ApplicantInfoDAO;
import edu.dao.college.AdmissionDAO;
import edu.dto.board.ApplicantInfoBoardDTO;
import edu.dto.board.BoardDTO;
import edu.dto.college.ApplicantInfoDTO;
import edu.dto.college.CollegeCourseDTO;
import edu.dto.college.CollegeDTO;

public class CollegeSVG  extends BaseAction {

	private String ssc_roll;
	
	private String ssc_board;
	private String ssc_year;
	private String ssc_reg;
	private String eiinCode;
	private String shift_id;
	private String version_id;
	private String group_id;
	private String new_group_id;
	private String application_id;
	private String boardName;
	private String dataString;
	
	private String mobilenumber;
	

	public String changeSVGHome(){
		return SUCCESS;
	}
	
public String changeSVGSearch(){
		
	CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		

		ApplicantInfoDAO appDAO=new ApplicantInfoDAO();
		ApplicantInfoBoardDTO admissionInfoDTO=appDAO.getApplicantInfo(ssc_roll,ssc_board,ssc_year,ssc_reg, userDto.getEiin());
		
		if(admissionInfoDTO!=null){
		AdmissionDAO approvedStudentListOfMeritDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> 	approvedStudentListOfMerit = approvedStudentListOfMeritDAO.getApprovedStudentListOfMeritByBoard(admissionInfoDTO.getShiftID(),admissionInfoDTO.getVersionID(),
				admissionInfoDTO.getGroupId(),admissionInfoDTO.getMerit(),admissionInfoDTO.getEiinCode(),admissionInfoDTO.getApplicationID());
		request.setAttribute("approvedStudentListOfMerit", approvedStudentListOfMerit);
		request.setAttribute("application_id", admissionInfoDTO.getApplicationID());
		request.setAttribute("app_eiin", admissionInfoDTO.getEiinCode());
		request.setAttribute("app_shift_id", admissionInfoDTO.getShiftID());
		request.setAttribute("app_version_id", admissionInfoDTO.getVersionID());
		request.setAttribute("app_group_id", admissionInfoDTO.getGroupId());
		
		}
		else
		{
			request.setAttribute("validation_mas", "Provided information is not valid for your college<br><br>");
		}
		request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
	    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
	    
		
		return "success";
	}


	public String changeSVGSubmit(){
		
	   CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		ApplicantInfoDAO appDAO=new ApplicantInfoDAO();
		String msg=appDAO.ChangeApplicantSVG(application_id, new_group_id, userDto.getEiin());
		if(msg.equals("YES"))
			request.setAttribute("msg", "Group change from "+getGroupName(group_id)+" to "+ getGroupName(new_group_id) +" has been done successfully");
		else
			request.setAttribute("msg", "Error!!!==>"+msg);
		
		return "success";
	}


	 public String getGroupName(String iGroup_id)
	 {
		 if(iGroup_id.equals("0")) return "Science";
		 if(iGroup_id.equals("2")) return "Humanities";
		 if(iGroup_id.equals("8")) return "Business Studies";
		 
		 if(iGroup_id.equals("4")) return "Agriculture";
		 if(iGroup_id.equals("5")) return "Home Science";
		 if(iGroup_id.equals("6")) return "Islamic Studies";
		 if(iGroup_id.equals("7")) return "Music";
		 if(iGroup_id.equals("9")) return "General";
		 if(iGroup_id.equals("10")) return "Muzzabid";
		 if(iGroup_id.equals("12")) return "Science (Madrasha)";
		 		 
		 return "";
	 }
//////////////////////////////////
public String getSsc_roll() {
	return ssc_roll;
}

public void setSsc_roll(String ssc_roll) {
	this.ssc_roll = ssc_roll;
}

public String getSsc_board() {
	return ssc_board;
}

public void setSsc_board(String ssc_board) {
	this.ssc_board = ssc_board;
}

public String getSsc_year() {
	return ssc_year;
}

public void setSsc_year(String ssc_year) {
	this.ssc_year = ssc_year;
}

public String getSsc_reg() {
	return ssc_reg;
}

public void setSsc_reg(String ssc_reg) {
	this.ssc_reg = ssc_reg;
}

public String getEiinCode() {
	return eiinCode;
}

public void setEiinCode(String eiinCode) {
	this.eiinCode = eiinCode;
}

public String getApplication_id() {
	return application_id;
}

public void setApplication_id(String application_id) {
	this.application_id = application_id;
}

public String getBoardName() {
	return boardName;
}

public void setBoardName(String boardName) {
	this.boardName = boardName;
}

public String getDataString() {
	return dataString;
}

public void setDataString(String dataString) {
	this.dataString = dataString;
}

public String getMobilenumber() {
	return mobilenumber;
}

public void setMobilenumber(String mobilenumber) {
	this.mobilenumber = mobilenumber;
}
public String getShift_id() {
	return shift_id;
}

public void setShift_id(String shift_id) {
	this.shift_id = shift_id;
}

public String getVersion_id() {
	return version_id;
}

public void setVersion_id(String version_id) {
	this.version_id = version_id;
}

public String getGroup_id() {
	return group_id;
}

public void setGroup_id(String group_id) {
	this.group_id = group_id;
}

public String getNew_group_id() {
	return new_group_id;
}

public void setNew_group_id(String new_group_id) {
	this.new_group_id = new_group_id;
}

//////////////////////////////
}
