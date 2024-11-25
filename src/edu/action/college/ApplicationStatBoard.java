package edu.action.college;

import java.util.List;

import edu.action.common.BaseAction;
import edu.dao.board.ApplicationStatBoardDAO;
import edu.dao.college.ListOfStudentOfCollegeDAO;
import edu.dto.UserDTO;
import edu.dto.board.ApplicationStatBoardDTO;
import edu.dto.board.CollegeInfoDTO;
import edu.dto.college.ListOfStudentOfCollegeDTO;

public class ApplicationStatBoard extends BaseAction{
	
private String eiinCode;

private static final long serialVersionUID = 2417433175132213892L;


public String dateWiseApplicationStatBoard(){
	
	UserDTO userDto = (UserDTO) session.get("user");
	if(userDto == null){
		return "input";
	}

	ApplicationStatBoardDAO dateWiseApplicationStatBoardDAO = new ApplicationStatBoardDAO();
	List<ApplicationStatBoardDTO> dateWiseApplicationStatBoardList = dateWiseApplicationStatBoardDAO.getDateWiseApplicationStatBoard(userDto.getUserid());
	request.setAttribute("dateWiseApplicationStatBoardList", dateWiseApplicationStatBoardList);
	request.setAttribute("userInfo", userDto);
	return "success";

		
	          

}


public String collegeinfo(){
	
	UserDTO userDto = (UserDTO) session.get("user");
	if(userDto == null){
		return "input";
	}

	ApplicationStatBoardDAO collegeinfoDAO = new ApplicationStatBoardDAO();
	List<CollegeInfoDTO> collegeinfoList = collegeinfoDAO.getCollegeInfo(userDto.getUserid());
	request.setAttribute("collegeinfoList", collegeinfoList);
	request.setAttribute("userInfo", userDto);
	return "success";

}

public String shiftVersionGroupApplicationNumberOfCollege(){
	
	UserDTO userDto = (UserDTO) session.get("user");
	if(userDto == null){
		return "input";
	}

	ListOfStudentOfCollegeDAO shiftVersionGroupApplicationNumberOfCollegeDAO = new ListOfStudentOfCollegeDAO();
	List<ListOfStudentOfCollegeDTO> shiftVersionGroupApplicationNumberOfCollegeList = shiftVersionGroupApplicationNumberOfCollegeDAO.getlistOfShiftVersionGroupApplicationNumberOfCollege(eiinCode,userDto.getUserid());
	request.setAttribute("shiftVersionGroupApplicationNumberOfCollegeList", shiftVersionGroupApplicationNumberOfCollegeList);
	request.setAttribute("userInfo", userDto);
	return "success";

		
	          

}


public String getEiinCode() {
	return eiinCode;
}


public void setEiinCode(String eiinCode) {
	this.eiinCode = eiinCode;
}




}
