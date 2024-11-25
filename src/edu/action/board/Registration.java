package edu.action.board;

import java.util.ArrayList;
import java.util.List;

import edu.action.common.BaseAction;
import edu.dao.college.AdmissionDAO;
import edu.dao.college.CollegeDAO;
import edu.dto.board.BoardDTO;
import edu.dto.college.ApplicantInfoDTO;
import edu.dto.college.CollegeDTO;
import edu.dto.college.CollegeSubject;

public class Registration extends BaseAction {
	private String eiin;
	private String sub_id;
	public String getEiin() {
		return eiin;
	}
	public void setEiin(String eiin) {
		this.eiin = eiin;
	}
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}

	
	public String eiinLoad(){
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		
		
		CollegeDAO cdao = new CollegeDAO();
		ArrayList<CollegeDTO> lstCDTO = cdao.getCollegeList(userDto.getBoardId());
		request.setAttribute("lstCDTO", lstCDTO);
		
		return "success";
	}
	
	public String getSubjectforEiin(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		CollegeDAO cdao = new CollegeDAO();
		ArrayList<CollegeDTO> lstCDTO = cdao.getCollegeSubjectList(eiin);
		request.setAttribute("lstCDTO", lstCDTO);
		request.setAttribute("subjectEiin", eiin);
		
		
		request.setAttribute("lstCDTO", lstCDTO);
		
		request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
	    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
	    
		return "success";

				
	}
	
	public String deleteSubject(){
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		CollegeDAO cdao = new CollegeDAO();
		try
		{
			if(cdao.deleteSubject(eiin,sub_id))
			{
				response.setContentType("text/html");
				response.getWriter().write("Subject deleted");
				return null;
			}
			else
			{
				try
				{
					response.setContentType("text/html");
					response.getWriter().write("Subject cannot deleted");
					return null;
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
			try
			{
				response.setContentType("text/html");
				if(e.getMessage().contains("THIS SUBJECT HAS REGISTRATION!!!"))
					response.getWriter().write("THIS SUBJECT HAS REGISTRATION!!!");
				else
					response.getWriter().write(e.getMessage());
				e.printStackTrace();
				return null;
			}
			catch(Exception e1)
			{}
			
		}
		
		return null;
	}
	public String addSubject(){
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		CollegeDAO cdao = new CollegeDAO();
		try
		{
			if(cdao.addSubject(eiin,sub_id))
			{
				response.setContentType("text/html");
				response.getWriter().write("Subject added");
				return null;
			}
			else
			{
				try
				{
					response.setContentType("text/html");
					response.getWriter().write("Subject cannot added");
					return null;
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
			try
			{
				response.setContentType("text/html");
				response.getWriter().write(e.getMessage());
				e.printStackTrace();
				return null;
			}
			catch(Exception e1)
			{}
			
		}
		
		return null;
	}
	public String getSubjectEiin(){
//		BoardDTO userDto = (BoardDTO) session.get("user");
//		if(userDto == null){
//			return "input";
//		}
		CollegeDAO cdao = new CollegeDAO();
		try
		{
			response.setContentType("text/html");
			response.getWriter().write(cdao.getSubjectEiin(eiin));
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	public String getAllSubject()
	{
		try
		{
			BoardDTO userDto = (BoardDTO) session.get("user");
			if(userDto == null){
				return "input";
			}
			CollegeDAO cdao = new CollegeDAO();
			ArrayList<CollegeDTO> lstCDTO = cdao.getCollegeSubjectList(eiin);
			request.setAttribute("lstCDTO", lstCDTO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
