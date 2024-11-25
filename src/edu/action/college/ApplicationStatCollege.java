package edu.action.college;

import java.util.List;

import edu.action.common.BaseAction;
import edu.dao.college.ApplicationStatCollegeDAO;
import edu.dao.college.ListOfStudentOfCollegeDAO;
import edu.dto.college.ApplicationStatCollegeDTO;
import edu.dto.college.CollegeDTO;
import edu.dto.college.ListOfStudentOfCollegeDTO;

public class ApplicationStatCollege extends BaseAction{

private static final long serialVersionUID = 2417433175132213892L;

public String dateWiseApplicationStatCollege(){
	
	
	
	CollegeDTO userDto = (CollegeDTO) session.get("user");
	if(userDto == null){
		return "input";
	}

	ApplicationStatCollegeDAO dateWiseApplicationStatCollegeDAO = new ApplicationStatCollegeDAO();
	List<ApplicationStatCollegeDTO> dateWiseApplicationStatCollegeList = dateWiseApplicationStatCollegeDAO.getDateWiseApplicationStatCollege(userDto.getEiin());
	request.setAttribute("dateWiseApplicationStatCollegeList", dateWiseApplicationStatCollegeList);
	request.setAttribute("userInfo", userDto);
	return "success";

		
	          

}

public String shiftVersionGroupApplicationNumberOfCollege(){
	
	CollegeDTO userDto = (CollegeDTO) session.get("user");
	if(userDto == null){
		return "input";
	}

	ListOfStudentOfCollegeDAO shiftVersionGroupApplicationNumberOfCollegeDAO = new ListOfStudentOfCollegeDAO();
	List<ListOfStudentOfCollegeDTO> shiftVersionGroupApplicationNumberOfCollegeList = shiftVersionGroupApplicationNumberOfCollegeDAO.getlistOfShiftVersionGroupApplicationNumberOfCollege(userDto.getEiin(),userDto.getBoard_id());
	request.setAttribute("shiftVersionGroupApplicationNumberOfCollegeList", shiftVersionGroupApplicationNumberOfCollegeList);
	request.setAttribute("userInfo", userDto);
	return "success";

		
	          

}


}
