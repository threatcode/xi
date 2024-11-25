package edu.dto.interBoard;

import java.io.Serializable;

public class InterBoardDTO implements Serializable {
	
	private String eiin;
	private String dist_id; 
	private String college_name;
	private String board_id;
	private String helper_board_id;
	
	public String getEiin() {
		return eiin;
	}
	public void setEiin(String eiin) {
		this.eiin = eiin;
	}
	public String getCollege_name() {
		return college_name;
	}
	public void setCollege_name(String collegeName) {
		college_name = collegeName;
	}
	public String getDist_id() {
		return dist_id;
	}
	public void setDist_id(String distId) {
		dist_id = distId;
	}
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String boardId) {
		board_id = boardId;
	}
	public String getHelper_board_id() {
		return helper_board_id;
	}
	public void setHelper_board_id(String helperBoardId) {
		helper_board_id = helperBoardId;
	}
}
