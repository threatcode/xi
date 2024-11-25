package edu.action.open;

import com.google.gson.Gson;

import edu.action.common.BaseAction;
import edu.dao.college.CollegeDAO;
import edu.dto.college.CollegeDTO;

public class College extends BaseAction {
	
	private CollegeDTO collegeDTO;
	private String eiin;



	public CollegeDTO getCollegeDTO() {
		return collegeDTO;
	}



	public void setCollegeDTO(CollegeDTO collegeDTO) {
		this.collegeDTO = collegeDTO;
	}



	public String getEiin() {
		return eiin;
	}



	public void setEiin(String eiin) {
		this.eiin = eiin;
	}



	public String getCollege()
	{
//		collegeDTO=CollegeDAO.getCollege(eiin);
//		return SUCCESS;
		Gson gson = new Gson();
		String json = gson.toJson(CollegeDAO.getCollege(eiin));
		setJsonResponse(json);
		return null;
	}
}
