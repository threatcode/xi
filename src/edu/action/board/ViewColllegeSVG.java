package edu.action.board;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts2.ServletActionContext;

import edu.action.common.BaseAction;
import edu.dto.application.ThanaDTO;

public class ViewColllegeSVG extends BaseAction{
	
	
	private String boardId;
	private ArrayList<CollegeSVGDTO> svgList;
	
	
	public String viewSVGByBoard() 
	{
				
		HashMap<String, ArrayList<CollegeSVGDTO>> SvgMap=(HashMap<String, ArrayList<CollegeSVGDTO>>)ServletActionContext.getServletContext().getAttribute("SVG_LIST");	
		
		//svgList=SvgMap.get(boardId);
				
		setSvgList(SvgMap.get(boardId));
				
		return "success";
	}
	
	
	

	public ArrayList<CollegeSVGDTO> getSvgList() {
		return svgList;
	}




	public void setSvgList(ArrayList<CollegeSVGDTO> svgList) {
		this.svgList = svgList;
	}




	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	
	
	
	
	
	
	
	

}
