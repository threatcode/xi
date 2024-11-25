package edu.action.interBoard;

import java.util.ArrayList;

import edu.action.board.CollegeSVGDTO;
import edu.action.common.BaseAction;
import edu.dao.interBoard.ApplicantInfoDAO;
import edu.dto.UserDTO;
import edu.dto.application.ChoiceDTO;
import edu.dto.board.BoardDTO;
import edu.dto.interBoard.ApplicantInfoDTO;
import edu.dto.interBoard.InterBoardDTO;

public class RetriveResSt extends BaseAction{
	
	private static final long serialVersionUID = 2775177229066309566L;
	private String ssc_roll;
	private String ssc_reg;
	private String ssc_board;
	private String ssc_passing_year;
	
	private String st_name;
	private String ssceiin;
		
		
	public String  execute()
	{
		
		InterBoardDTO userDto = (InterBoardDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		
		ApplicantInfoDAO appDAO = new ApplicantInfoDAO();
		
		ApplicantInfoDTO appInfo = appDAO.getAppInfoRank(ssc_roll,ssc_board,ssc_passing_year);
		
		ArrayList<ChoiceDTO> svgList = new ArrayList<ChoiceDTO>();
		
				
		svgList = appDAO.getSvgInfo(ssc_roll,ssc_board,ssc_passing_year);
		
				
		request.setAttribute("appInfo",appInfo);
		request.setAttribute("svgList",svgList);
		
				
		return "success";
	}


	public String getSsc_roll() {
		return ssc_roll;
	}


	public void setSsc_roll(String ssc_roll) {
		this.ssc_roll = ssc_roll;
	}


	public String getSsc_reg() {
		return ssc_reg;
	}


	public void setSsc_reg(String ssc_reg) {
		this.ssc_reg = ssc_reg;
	}


	public String getSsc_board() {
		return ssc_board;
	}


	public void setSsc_board(String ssc_board) {
		this.ssc_board = ssc_board;
	}


	public String getSsc_passing_year() {
		return ssc_passing_year;
	}


	public void setSsc_passing_year(String ssc_passing_year) {
		this.ssc_passing_year = ssc_passing_year;
	}


	public String getSt_name() {
		return st_name;
	}


	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}


	public String getSsceiin() {
		return ssceiin;
	}


	public void setSsceiin(String ssceiin) {
		this.ssceiin = ssceiin;
	}



}
