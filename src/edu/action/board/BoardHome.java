package edu.action.board;

import edu.action.common.BaseAction;
import edu.dto.board.BoardDTO;



public class BoardHome extends BaseAction{
	
	
	

	public String applicantHome(){
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}

			return "success";
		          
	
	}
    

	}	
	

