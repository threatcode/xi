package edu.action.open;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;

import com.google.gson.Gson;

import edu.action.common.BaseAction;
import edu.dao.college.ListOfStudentOfCollegeDAO;
import edu.dto.application.SVGDTO;
import edu.dto.college.CollegeDTO;
import edu.dto.college.ListOfStudentOfCollegeDTO;

public class SVG extends BaseAction  {
	private String college_search_type;
	private String district_id;
	private String board_id;
	private String thana_id;
	private String thana_name;
	private String eiinCode;
	private String user_captcha;
	public String getUser_captcha() {
		return user_captcha;
	}


	public void setUser_captcha(String user_captcha) {
		this.user_captcha = user_captcha;
	}


	CollegeDTO collegeDTO;
	Map<Integer,String> shift = new TreeMap<Integer,String>();  
	Map<Integer,String> versi = new TreeMap<Integer,String>(); 
	Map<Integer,String> group = new TreeMap<Integer,String>();		
	List<ListOfStudentOfCollegeDTO> svgList = new ArrayList<ListOfStudentOfCollegeDTO>() ;

	
	
	public String getCollege_search_type() {
		return college_search_type;
	}


	public void setCollege_search_type(String college_search_type) {
		this.college_search_type = college_search_type;
	}


	public String getDistrict_id() {
		return district_id;
	}


	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}


	public String getBoard_id() {
		return board_id;
	}


	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}


	public String getThana_id() {
		return thana_id;
	}


	public void setThana_id(String thana_id) {
		this.thana_id = thana_id;
	}


	public String getThana_name() {
		return thana_name;
	}


	public void setThana_name(String thana_name) {
		this.thana_name = thana_name;
	}


	public String getEiinCode() {
		return eiinCode;
	}


	public void setEiinCode(String eiinCode) {
		this.eiinCode = eiinCode;
	}


	public CollegeDTO getCollegeDTO() {
		return collegeDTO;
	}


	public void setCollegeDTO(CollegeDTO collegeDTO) {
		this.collegeDTO = collegeDTO;
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


	public List<ListOfStudentOfCollegeDTO> getSvgList() {
		return svgList;
	}


	public void setSvgList(List<ListOfStudentOfCollegeDTO> svgList) {
		this.svgList = svgList;
	}


	
	
	public String getColleges(){
		String checkSum=(String)session.get("CorrectAnswer");
		boolean checkCaptcha = false; 
		checkCaptcha = user_captcha.equalsIgnoreCase(checkSum);
		if(!checkCaptcha){
			try {
				response.setContentType("text/html");
				response.getWriter().write("ce");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		((SessionMap<String, Object>) session).invalidate();
		HashMap<String, ArrayList<CollegeDTO>> collegeMapDist=(HashMap<String, ArrayList<CollegeDTO>>)ServletActionContext.getServletContext().getAttribute("ALL_COLLEGE_MAP_DIST_BOARD");
		Gson gson;
		String json = null;
		if(college_search_type.equalsIgnoreCase("by_district")){
			ArrayList<CollegeDTO> collegeList=collegeMapDist.get(district_id+board_id);
			gson = new Gson();
			json = gson.toJson(collegeList);
			json="{\"colleges\":"+json+",\"district_id\" :\""+district_id+"\",\"board_id\":\""+board_id+"\"}";
		}
		
		setJsonResponse(json);
        return null;

        
	}	
	public String loadApplication() {
		((SessionMap<String, Object>) session).invalidate();
		return SUCCESS;
	}
	
	
	public String getCollegeSVGOpen(){
		String checkSum=(String)session.get("CorrectAnswer");
		boolean checkCaptcha = false; 
		checkCaptcha = user_captcha.equalsIgnoreCase(checkSum);
		if(!checkCaptcha){
			try {
				response.setContentType("text/html");
				response.getWriter().write("ce");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		((SessionMap<String, Object>) session).invalidate();
		System.out.println(" Session Activity " + session.get("ssc_roll"));
		HashMap<String, SVGDTO> svgList=(HashMap<String, SVGDTO>)ServletActionContext.getServletContext().getAttribute("SVG_MAP");
		SVGDTO svg=svgList.get(eiinCode);
		
		String shift="\"shift\":"+ svg.getShiftList();
		String version= "\"version\":"+svg.getShiftVersionList().toString().replaceAll("=", ":");
		version=version.replaceAll("\\[", "\"");
		version=version.replaceAll("\\]", "\"");
		version=version.replaceAll(":\\{", ":\\{\"");
		version=version.replaceAll(":\"", "\":\"");
		version=version.replaceAll("\", ", "\", \"");
		
		String group="\"group\":"+svg.getVersionGroupList().toString().replaceAll("=", ":");
		group=group.replaceAll("\\[", "\"");
		group=group.replaceAll("\\]", "\"");
		group=group.replaceAll(":\\{", ":\\{\"");
		group=group.replaceAll(":\"", "\":\"");
		group=group.replaceAll("\", ", "\", \"");
		
		String sqYN="\"special_quota\":"+svg.getGroupEQList().toString().replaceAll("=", ":");
		sqYN=sqYN.replaceAll("\\[", "\"");
		sqYN=sqYN.replaceAll("\\]", "\"");
		sqYN=sqYN.replaceAll(":\\{", ":\\{\"");
		sqYN=sqYN.replaceAll(":\"", "\":\"");
		sqYN=sqYN.replaceAll("\", ", "\", \"");
		
		String totalSeat="\"total_seat\":"+svg.getSvgTotalSeat().toString().replaceAll("=", ":");
		totalSeat=totalSeat.replaceAll("\\[", "\"");
		totalSeat=totalSeat.replaceAll("\\]", "\"");
		totalSeat=totalSeat.replaceAll(":\\{", ":\\{\"");
		totalSeat=totalSeat.replaceAll(":\"", "\":\"");
		totalSeat=totalSeat.replaceAll("\", ", "\", \"");
		
		String availableSeat="\"available_seat\":"+svg.getSvgAvailableSeat().toString().replaceAll("=", ":");
		availableSeat=availableSeat.replaceAll("\\[", "\"");
		availableSeat=availableSeat.replaceAll("\\]", "\"");
		availableSeat=availableSeat.replaceAll(":\\{", ":\\{\"");
		availableSeat=availableSeat.replaceAll(":\"", "\":\"");
		availableSeat=availableSeat.replaceAll("\", ", "\", \"");
		
		
		
		HashMap<String, String> college_eligiblity=(HashMap<String, String>)ServletActionContext.getServletContext().getAttribute("COLLEGE_ELIGIBILITY");
		String eligibility="\"eligibility\": ["+college_eligiblity.get(eiinCode)+"]";
		String svgString="{"+shift+","+version+","+group+","+sqYN+","+totalSeat+","+availableSeat+"}";
		//String svgString="{"+shift+","+version+","+group+","+sqYN+","+availableSeat+","+eligibility+"}";
		
		
		
		
		System.out.println("JSON is here = "+svgString);
		
		
		
		setJsonResponse(svgString);
		return null;
	}

}
