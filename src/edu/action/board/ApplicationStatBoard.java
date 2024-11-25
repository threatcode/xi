package edu.action.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.action.common.BaseAction;
import edu.dao.board.ApplicationStatBoardDAO;
import edu.dao.college.ListOfStudentOfCollegeDAO;
import edu.dto.board.ApplicationStatBoardDTO;
import edu.dto.board.BoardDTO;
import edu.dto.board.CollegeInfoDTO;
import edu.dto.board.DistrictinfoDTO;
import edu.dto.college.ListOfStudentOfCollegeDTO;
import edu.utils.connection.ConnectionManager;

public class ApplicationStatBoard extends BaseAction{
	
private String eiinCode;
private String districtID;

ArrayList<String> eiinCodeList = new ArrayList<String>();
ArrayList<String> collegeNamelist = new ArrayList<String>();

private static final long serialVersionUID = 2417433175132213892L;


public String dateWiseApplicationStatBoard(){
	
	BoardDTO userDto = (BoardDTO) session.get("user");
	if(userDto == null){
		return "input";
	}
	ApplicationStatBoardDAO dateWiseApplicationStatBoardDAO = new ApplicationStatBoardDAO();
	List<ApplicationStatBoardDTO> dateWiseApplicationStatBoardList = dateWiseApplicationStatBoardDAO.getDateWiseApplicationStatBoard(userDto.getBoardId());
	request.setAttribute("dateWiseApplicationStatBoardList", dateWiseApplicationStatBoardList);
	request.setAttribute("userInfo", userDto);
	return "success";

		
	          

}

public String districtinfo(){
	
	BoardDTO userDto = (BoardDTO) session.get("user");
	ApplicationStatBoardDAO districtinfoDAO = new ApplicationStatBoardDAO();
	List<DistrictinfoDTO> districtinfoList = districtinfoDAO.getBoardDistrictinfo(userDto.getBoardId());
	request.setAttribute("districtinfoList", districtinfoList);

	return "success";

}



public String districtWisecollegeinfo(){
	
	BoardDTO userDto = (BoardDTO) session.get("user");
	
	if(userDto == null){
		return "input";
	}

	ApplicationStatBoardDAO collegeinfoDAO = new ApplicationStatBoardDAO();
	List<CollegeInfoDTO> collegeinfoList = collegeinfoDAO.getCollegeInfo(userDto.getBoardId());
	request.setAttribute("collegeinfoList", collegeinfoList);
	request.setAttribute("userInfo", userDto);
	return "success";

}





public String collegeinfo(){
	
	BoardDTO userDto = (BoardDTO) session.get("user");
	if(userDto == null){
		return "input";
	}

	ApplicationStatBoardDAO collegeinfoDAO = new ApplicationStatBoardDAO();
	List<CollegeInfoDTO> collegeinfoList = collegeinfoDAO.getCollegeInfo(userDto.getBoardId());
	request.setAttribute("collegeinfoList", collegeinfoList);
	request.setAttribute("userInfo", userDto);
	return "success";

}

public String shiftVersionGroupApplicationNumberOfCollege(){
	
	BoardDTO userDto = (BoardDTO) session.get("user");
	if(userDto == null){
		return "input";
	}

	ListOfStudentOfCollegeDAO shiftVersionGroupApplicationNumberOfCollegeDAO = new ListOfStudentOfCollegeDAO();
	List<ListOfStudentOfCollegeDTO> shiftVersionGroupApplicationNumberOfCollegeList = shiftVersionGroupApplicationNumberOfCollegeDAO.getlistOfShiftVersionGroupApplicationNumberOfCollege(eiinCode,userDto.getBoardId());
	request.setAttribute("shiftVersionGroupApplicationNumberOfCollegeList", shiftVersionGroupApplicationNumberOfCollegeList);
	request.setAttribute("userInfo", userDto);
	return "success";

		
	          

}

public String searchColleges(){
/*	System.out.println("HIT districtID:"+districtID);*/
	BoardDTO userDto = (BoardDTO) session.get("user");
	if(userDto == null){
		return "input";
	}

	Connection conn = ConnectionManager.getReadConnection();
	
	String sql = "  SELECT EIIN, COLLEGE_NAME\n" +
            "    FROM MST_COLLEGE\n" +
            "   WHERE board_id =? AND dist_id =?\n" +
            "ORDER BY COLLEGE_NAME";

	PreparedStatement stmt = null;
	ResultSet r = null;
	try
	{
		stmt = conn.prepareStatement(sql);
		
		//String uid="15";
		//stmt.setString(1,uid);
		/*System.out.println("BOARD_ID:"+userDto.getBoardId());*/
		
		stmt.setString(1, userDto.getBoardId());
		stmt.setString(2, districtID);
		
		r = stmt.executeQuery();
		
	
		while(r.next())
		{
			
			
			eiinCodeList.add(r.getString("EIIN"));
			collegeNamelist.add(r.getString("COLLEGE_NAME"));
			

		}
/*		System.out.println("eiinCodeList.size="+eiinCodeList.size());
		System.out.println("collegeNamelist.size="+collegeNamelist.size());*/
	} 
	catch (Exception e){e.printStackTrace();}
	finally{
		try{
			stmt.close();
			conn.close();
			} 
		catch (Exception e){
				e.printStackTrace();
			}
		stmt = null;
		conn = null;
	}
	
	return "success";

}


public String getDistrictID() {
	return districtID;
}

public void setDistrictID(String districtID) {
	this.districtID = districtID;
}

public String getEiinCode() {
	return eiinCode;
}


public void setEiinCode(String eiinCode) {
	this.eiinCode = eiinCode;
}

public ArrayList<String> getEiinCodeList() {
	return eiinCodeList;
}

public void setEiinCodeList(ArrayList<String> eiinCodeList) {
	this.eiinCodeList = eiinCodeList;
}

public ArrayList<String> getCollegeNamelist() {
	return collegeNamelist;
}

public void setCollegeNamelist(ArrayList<String> collegeNamelist) {
	this.collegeNamelist = collegeNamelist;
}




}
