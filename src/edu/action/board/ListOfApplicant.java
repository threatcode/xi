package edu.action.board;

import java.util.List;

import edu.action.common.BaseAction;
import edu.dao.board.ListOfStudentOfCollegeDAO;
import edu.dto.board.BoardDTO;
import edu.dto.board.ListOfStudentOfCollegeDTO;




public class ListOfApplicant extends BaseAction{

	private static final long serialVersionUID = -6516848499230321618L;
	
	private String eiinCode;
	
	public String listOfApplicantOfCollegeSearchByBoard(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}


		ListOfStudentOfCollegeDAO listOfStudentOfCollegeDAO = new ListOfStudentOfCollegeDAO();
		List<ListOfStudentOfCollegeDTO> studentList = listOfStudentOfCollegeDAO.getlistOfStudentOfCollege(eiinCode);
		request.setAttribute("studentList", studentList);
		
		return "success";

			
		         	
	}

	public String getEiinCode() {
		return eiinCode;
	}

	public void setEiinCode(String eiinCode) {
		this.eiinCode = eiinCode;
	}
    
	
	

	}	
	

