package edu.action.college;

import java.util.List;

import edu.action.common.BaseAction;
import edu.dto.college.CollegeCourseDTO;
import edu.dto.college.CollegeDTO;
import edu.dao.college.CollegeCourseDAO;

public class CollegeCourseAction extends BaseAction {
	private static final long serialVersionUID = -3308886200897622656L;
	

	public String showCollegeCourse(){

		CollegeDTO userDto = (CollegeDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		CollegeCourseDAO collegeCourseDAO=new CollegeCourseDAO();
		
		List<CollegeCourseDTO> courseList = collegeCourseDAO.getCollegeCourseList(userDto.getEiin(),userDto.getBoard_id());
		request.setAttribute("courseList", courseList);

		return "success";
	}
	

	

	

	}

	
	


