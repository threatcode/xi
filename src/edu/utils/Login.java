package edu.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;

import edu.action.common.BaseAction;
import edu.dao.GroupDAO;
import edu.dao.UserDAO;
import edu.dao.application.ApplicationDAO;
import edu.dao.board.ApplicationStatBoardDAO;
import edu.dao.college.AdmissionDAO;
import edu.dao.interBoard.ApplicationStatBoardInterBoardDAO;
import edu.dto.GroupDTO;
import edu.dto.UserDTO;
import edu.dto.board.BoardDTO;
import edu.dto.board.DashBoardBoardDTO;
import edu.dto.college.CollegeCourseDTO;
import edu.dto.college.CollegeDTO;
import edu.dto.interBoard.DashBoardBoardInterBoardDTO;
import edu.dto.interBoard.InterBoardDTO;

public class Login extends BaseAction {

	private String eiin;
	public String getEiin() {
		return eiin;
	}

	public void setEiin(String eiin) {
		this.eiin = eiin;
	}

	public String execute()
	{	
			//generateGroupArray();
			//getGroupMapAsArray();
			String successResult="";
			String url = request.getServletPath();
			if(url.contains("college")){
				return "invalid";
			} else if(url.contains("board")){	
				return "invalid";
			} 
			else if(url.contains("admin")){
				return "invalid";
			}
			else if(url.contains("applicant")){
				return "invalid";
			}
			else {
				((SessionMap<String,Object>)session).invalidate();
				return SUCCESS;
			}
	}	
	
	public String applicantLogin()
	{
		String successResult="";
		if(session.get("errormessage")!=null)
		{
			request.setAttribute("errormessage",session.get("errormessage"));
			session.put("errormessage",null);
		}
		if(session.get("role")==null)
			return SUCCESS;
		else {
			String role = session.get("role").toString();
		  }
		return SUCCESS;
	}
	
	public void generateGroupArray(){

			GroupDAO gdao=new GroupDAO();
			ArrayList<GroupDTO> groupList=gdao.getAllGroup("SSC");
			HashMap<String,String> groupMap=new HashMap<String, String>();
			for(int i=0;i<groupList.size();i++){
				System.out.println(i);
				if(i==35)
				{
					System.out.println("abc");
				}
				groupMap.put(groupList.get(i).getGroup_id(), groupList.get(i).getGroup_name());
			}
			String totalString="";
			for(int i=0;i<=50;i++){
				if(groupMap.get(String.valueOf(i))==null)
					totalString+="\"\",";
				else
					totalString+="\""+groupMap.get(String.valueOf(i))+"\",";	
			}
			System.out.println(totalString);
			
		
	}
	
	public void getGroupMapAsArray(){

		GroupDAO gdao=new GroupDAO();
		Set<String> groupMapSet=gdao.getGroupMapSet();
		String totalString="";
		
		for (String s : groupMapSet) {
			totalString+="\""+s+"\",";
		}
		System.out.println(totalString);
	}	
	public String loginCollege()
	{
		return SUCCESS;
	}
	
	
}
