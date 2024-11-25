package edu.action.interBoard;

import edu.action.common.BaseAction;
import edu.dto.UserDTO;
import edu.dto.board.BoardDTO;
import edu.dto.interBoard.InterBoardDTO;

public class PinRetrivalByCC extends BaseAction{
	
	private static final long serialVersionUID = 2775177229066309566L;
	
	
	public String  execute()
	{

		BoardDTO  userDto = (BoardDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		return "success";
		
	}
	
	public String  resultSta()
	{

		InterBoardDTO  userDto = (InterBoardDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		return "success";
		
	}
	
	
	public String iictTransRetrival(){
		UserDTO userDto = (UserDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		return "success";
		
	}
	
	
	
	/**
	 * @author nasir
	 * @return
	 */
	public String iictPinRetrival(){
		UserDTO userDto = (UserDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		return "success";
		
	}
	

}
