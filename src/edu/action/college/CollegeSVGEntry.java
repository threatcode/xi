package edu.action.college;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.action.common.BaseAction;
import edu.dao.board.ApplicationStatBoardDAO;
import edu.dao.college.CollegeCourseDAO;
import edu.dao.college.ListOfStudentOfCollegeDAO;
import edu.dto.board.BoardDTO;
import edu.dto.board.DistrictinfoDTO;
import edu.dto.college.CollegeCourseDTO;
import edu.dto.college.CollegeDTO;
import edu.dto.college.ListOfStudentOfCollegeDTO;

public class CollegeSVGEntry  extends BaseAction {
	private static final long serialVersionUID = -3308886200897622656L;
	
	private Map<Integer,String> shift = new TreeMap<Integer,String>();  
	private Map<Integer,String> versi = new TreeMap<Integer,String>(); 
	private Map<Integer,String> group = new TreeMap<Integer,String>();
	
	private String eiinCode;
	private String districtID;
	
	
//	public String execute1()
//	{
//		CollegeDTO userDto = (CollegeDTO) session.get("user");
//		if(userDto == null){
//			return "input";
//		}
//		
//		CollegeCourseDAO collegeCourseDAO=new CollegeCourseDAO();
//		
//		List<CollegeCourseDTO> courseList = collegeCourseDAO.getCollegeCourseList(userDto.getEiin(),userDto.getBoard_id());
//		request.setAttribute("courseList", courseList);
//		
//				
//		return "success";
//	}
	
	public String districtinfo(){
		
		
		ApplicationStatBoardDAO districtinfoDAO = new ApplicationStatBoardDAO();
		List<DistrictinfoDTO> districtinfoList = districtinfoDAO.getDistrictinfo();
		request.setAttribute("districtinfoList", districtinfoList);

		return "success";

	}
	
	
	
	public String svgShowCollege() {
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}

		ListOfStudentOfCollegeDAO svgDAO = new ListOfStudentOfCollegeDAO();
		List<ListOfStudentOfCollegeDTO> svgList = svgDAO.getSVGCollege(eiinCode);
		shift=svgDAO.getShift(eiinCode);
		versi=svgDAO.getVersion();
		group=svgDAO.getGroups(Integer.parseInt(userDto.getBoardId()));
		
		request.setAttribute("svgList", svgList);
		request.setAttribute("userInfo", userDto);
		
				
		return "success";

		
	}

	
	
	
	public Map<Integer, String> getShift() {
		return shift;
	}

	public void setShift(Map<Integer, String> shift) {
		this.shift = shift;
	}

	public Map<Integer, String> getVersi() {
		return versi;
	}

	public void setVersi(Map<Integer, String> versi) {
		this.versi = versi;
	}

	public Map<Integer, String> getGroup() {
		return group;
	}

	public void setGroup(Map<Integer, String> group) {
		this.group = group;
	}

	public String getEiinCode() {
		return eiinCode;
	}

	public void setEiinCode(String eiinCode) {
		this.eiinCode = eiinCode;
	}

	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}
	
	
	
	

}
