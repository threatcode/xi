package edu.action.board;

import java.util.ArrayList;
import java.util.List;

import edu.action.common.BaseAction;
import edu.dao.college.AdmissionDAO;
import edu.dao.college.CollegeDAO;
import edu.dto.board.BoardDTO;
import edu.dto.college.ApplicantInfoDTO;
import edu.dto.college.CollegeCourseDTO;
import edu.dto.college.CollegeDTO;

public class ManualEntry extends BaseAction {
	private String eiin;
	private String roll_no;
	private String reg_no;
	private String board_id;
	private String passing_year;
	private String shift_id; 
	private String version_id;
	private String group_id;
	private String mnumber;
	public String getMnumber() {
		return mnumber;
	}
	public void setMnumber(String mnumber) {
		this.mnumber = mnumber;
	}
	public String getEiin() {
		return eiin;
	}
	public void setEiin(String eiin) {
		this.eiin = eiin;
	}
	public String getRoll_no() {
		return roll_no;
	}
	public void setRoll_no(String roll_no) {
		this.roll_no = roll_no;
	}
	public String getReg_no() {
		return reg_no;
	}
	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getPassing_year() {
		return passing_year;
	}
	public void setPassing_year(String passing_year) {
		this.passing_year = passing_year;
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

	
	
	public String eiinLoad(){
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		CollegeDAO cdao = new CollegeDAO();
		ArrayList<CollegeDTO> lstCDTO = cdao.getCollegeList(userDto.getBoardId());
		request.setAttribute("lstCDTO", lstCDTO);
		
		return "success";
	}
	
	public String getSVGforEiin(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		
		AdmissionDAO svgLoadDAO = new AdmissionDAO();
		List<CollegeCourseDTO> shiftList = svgLoadDAO.getShiftList(eiin);
		request.setAttribute("shiftList", shiftList);
		List<CollegeCourseDTO> versionList = svgLoadDAO.getVersionList(eiin);
		request.setAttribute("versionList", versionList);
		List<CollegeCourseDTO> groupList = svgLoadDAO.getGroupList(eiin);
		request.setAttribute("groupList", groupList);
		
		
	    
		return "success";

				
	}
	
	public String addStudent(){	
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		AdmissionDAO nonApprovedStudentListOfMeritDAO = new AdmissionDAO();
		int tmp = nonApprovedStudentListOfMeritDAO.addStudentBoard(eiin, Integer.parseInt(shift_id),Integer.parseInt(version_id),
				Integer.parseInt(group_id),userDto.getBoardUserId(),roll_no,Integer.parseInt(board_id),Integer.parseInt(passing_year),mnumber);
		if(tmp>0)
		{
			try{
				String probs = "";
				if(tmp==123)
				{
					probs="This Student Already Inserted!!!";response.setContentType("text/html");
					response.getWriter().write("<font color='red' size='5'>"+probs+"</font>");
				}
				else if(tmp==124)
				{
					probs="THIS APPLICANT HAS ANOTHER COLLEGE APPROVAL AT ...."+nonApprovedStudentListOfMeritDAO.getCollegeName(roll_no, board_id, passing_year)+"!!!";
					response.setContentType("text/html");
					response.getWriter().write("<font color='red' size='5'>"+probs+"</font>");
				}
				else if(tmp==125)
				{
					probs="THIS COLLEGE HAS NO SEAT FOR THIS SVG!!!";response.setContentType("text/html");
					response.getWriter().write("<font color='red' size='5'>"+probs+"</font>");
				}
				else if(tmp==126)
				{
					probs="Not valid SVG!!!";response.setContentType("text/html");
					response.getWriter().write("<font color='red' size='5'>"+probs+"</font>");
				}
				else if(tmp==127)
				{
					probs="Gender mismatch";response.setContentType("text/html");
					response.getWriter().write("<font color='red' size='5'>"+probs+"</font>");
				}
				else
				{
					response.setContentType("text/html");
					response.getWriter().write("<font color='green' size='5'>Data Insert Succesfull!!!</font>");
				}
				if(tmp!=1)
					nonApprovedStudentListOfMeritDAO.addStudentBoardLog(eiin, Integer.parseInt(shift_id),Integer.parseInt(version_id),
						Integer.parseInt(group_id),userDto.getBoardUserId(),roll_no,Integer.parseInt(board_id),Integer.parseInt(passing_year),mnumber,probs);
			}
			catch(Exception e) {e.printStackTrace();}
		}
		else
		{
			try{
		    	 response.setContentType("text/html");
		    	 response.getWriter().write("<font color='red' size='5'>Data Insert Error!!!</font>");
			}
			catch(Exception e) {e.printStackTrace();}
		}
		
		return null;
	}
	
	public String alreadyAdded(){	
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		AdmissionDAO nonApprovedStudentListOfMeritDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> lstAlreadyAdded = nonApprovedStudentListOfMeritDAO.getAlreadyAddred(Integer.parseInt(shift_id),Integer.parseInt(version_id),
				Integer.parseInt(group_id),eiin);
		request.setAttribute("lstAlreadyAdded", lstAlreadyAdded);
		request.setAttribute("userInfo", userDto);
		return "success";
	}
	
}
