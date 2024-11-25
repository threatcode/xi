package edu.action.interBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.dto.board.ApplicationStatBoardDTO;
import edu.dao.board.ApplicationStatBoardDAO;
import edu.dto.board.BoardDTO;
import edu.dto.board.CollegeInfoDTO;
import edu.dto.board.DistrictinfoDTO;
import edu.utils.connection.ConnectionManager;
import edu.dao.college.ListOfStudentOfCollegeDAO;
import edu.dto.college.ListOfStudentOfCollegeDTO;
import edu.dto.interBoard.InterBoardDTO;


import edu.action.common.BaseAction;

public class ApplicationStatBoard extends BaseAction{
	
private String eiinCode;
private String boardId;
private String districtID;


ArrayList<String> eiinCodeList = new ArrayList<String>();
ArrayList<String> collegeNamelist = new ArrayList<String>();


ArrayList<String> distIdList = new ArrayList<String>();
ArrayList<String> distNamelist = new ArrayList<String>();


private static final long serialVersionUID = 2417433175132213892L;


public String dateWiseApplicationStatInterBoard(){
	InterBoardDTO userDto = (InterBoardDTO) session.get("user");
	
	if(userDto == null){
		return "input";
	}

	ApplicationStatBoardDAO dateWiseApplicationStatBoardDAO = new ApplicationStatBoardDAO();
	List<ApplicationStatBoardDTO> dateWiseApplicationStatBoardList = dateWiseApplicationStatBoardDAO.getDateWiseApplicationStatInterBoard();
	request.setAttribute("dateWiseApplicationStatBoardList", dateWiseApplicationStatBoardList);

	return "success";

		
	          

}

public String dateWiseApplicationStatBoard(){
	
	BoardDTO userDto = (BoardDTO) session.get("user");

	ApplicationStatBoardDAO dateWiseApplicationStatBoardDAO = new ApplicationStatBoardDAO();
	List<ApplicationStatBoardDTO> dateWiseApplicationStatBoardList = dateWiseApplicationStatBoardDAO.getDateWiseApplicationStatBoard(userDto.getBoardId());
	request.setAttribute("dateWiseApplicationStatBoardList", dateWiseApplicationStatBoardList);
	request.setAttribute("userInfo", userDto);
	return "success";

		
	          

}

public String districtinfo(){
	InterBoardDTO userDto = (InterBoardDTO) session.get("user");
	
	if(userDto == null){
		return "input";
	}

	ApplicationStatBoardDAO districtinfoDAO = new ApplicationStatBoardDAO();
	List<DistrictinfoDTO> districtinfoList = districtinfoDAO.getDistrictinfo();
	request.setAttribute("districtinfoList", districtinfoList);

	return "success";

}



public String districtWisecollegeinfo(){
	
	BoardDTO userDto = (BoardDTO) session.get("user");

	ApplicationStatBoardDAO collegeinfoDAO = new ApplicationStatBoardDAO();
	List<CollegeInfoDTO> collegeinfoList = collegeinfoDAO.getCollegeInfo(userDto.getBoardId());
	request.setAttribute("collegeinfoList", collegeinfoList);
	request.setAttribute("userInfo", userDto);
	return "success";

}



/*

public String collegeinfo(){
	
	BoardDTO userDto = (BoardDTO) session.get("user");

	ApplicationStatBoardDAO collegeinfoDAO = new ApplicationStatBoardDAO();
	List<CollegeInfoDTO> collegeinfoList = collegeinfoDAO.getCollegeInfo(userDto.getBoardId());
	request.setAttribute("collegeinfoList", collegeinfoList);
	request.setAttribute("userInfo", userDto);
	return "success";

}*/

public String shiftVersionGroupApplicationNumberOfCollege(){
	InterBoardDTO userDto = (InterBoardDTO) session.get("user");
	
/*	if(userDto == null){
		return "input";
	}*/
	
	ListOfStudentOfCollegeDAO shiftVersionGroupApplicationNumberOfCollegeDAO = new ListOfStudentOfCollegeDAO();
	List<ListOfStudentOfCollegeDTO> shiftVersionGroupApplicationNumberOfCollegeList = shiftVersionGroupApplicationNumberOfCollegeDAO.getlistOfShiftVersionGroupApplicationNumberOfCollege(eiinCode,boardId);
	request.setAttribute("shiftVersionGroupApplicationNumberOfCollegeList", shiftVersionGroupApplicationNumberOfCollegeList);
	return "success";

		
	          

}

public String searchColleges(){
/*	System.out.println("HIT districtID:"+districtID);
	System.out.println("BOARD_ID:"+boardId);*/


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
		stmt.setString(1, boardId);
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


public String searchDist(){
/*	System.out.println("HIT districtID:"+districtID);
	System.out.println("BOARD_ID:"+boardId);*/


	Connection conn = ConnectionManager.getReadConnection();
	
	String sql = "select DIST_ID,DIST_NAME from mst_district  " +
			" where DIST_ID in(select distinct DIST_ID from mst_college where BOARD_ID=?) " +
			" order by DIST_NAME " ;

	PreparedStatement stmt = null;
	ResultSet r = null;
	try
	{
		stmt = conn.prepareStatement(sql);		
		stmt.setString(1, boardId);	
		
		r = stmt.executeQuery();
		
	
		while(r.next())
		{
			
			
			distIdList.add(r.getString("DIST_ID"));
			distNamelist.add(r.getString("DIST_NAME"));
			

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






public ArrayList<String> getDistIdList() {
	return distIdList;
}

public void setDistIdList(ArrayList<String> distIdList) {
	this.distIdList = distIdList;
}

public ArrayList<String> getDistNamelist() {
	return distNamelist;
}

public void setDistNamelist(ArrayList<String> distNamelist) {
	this.distNamelist = distNamelist;
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


public String getBoardId() {
	return boardId;
}

public void setBoardId(String boardId) {
	this.boardId = boardId;
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
