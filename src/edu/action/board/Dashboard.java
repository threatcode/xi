package edu.action.board;



import edu.action.common.BaseAction;
import edu.dao.board.ApplicationStatBoardDAO;
import edu.dao.college.ApplicationStatCollegeDAO;
import edu.dto.board.BoardDTO;
import edu.dto.board.DashBoardBoardDTO;
import edu.dto.college.DashBoardCollegeDTO;

public class Dashboard extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -532536617639123087L;
	
	private String eiinCode;
	
	public String totalAppliationTotalApplicant(){
		
	/*	System.out.println("Test....board");*/
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		
		
		ApplicationStatBoardDAO dashBoardDAO = new ApplicationStatBoardDAO();
		DashBoardBoardDTO dashBoardDTO=dashBoardDAO.getBoardDashBoard(userDto.getBoardId());
	
		request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
		request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
	    request.setAttribute("webApplicant",dashBoardDTO.getWebApplicant());
	    request.setAttribute("webApplication",dashBoardDTO.getWebApplication());
//	    request.setAttribute("smsApplicant",dashBoardDTO.getSmsApplicant());
//	    request.setAttribute("smsApplication",dashBoardDTO.getSmsApplication());
	    request.setAttribute("collegeReceived",dashBoardDTO.getCollegeAdmReceiveCount());
	    request.setAttribute("collegeNotReceived",dashBoardDTO.getCollegeAdmNotReceiveCount());
	    
	    
	    
//		request.setAttribute("totalApplicant",0);
//		request.setAttribute("totalApplication",0);
//	    request.setAttribute("webApplicant",0);
//	    request.setAttribute("webApplication",0);
//	    request.setAttribute("smsApplicant",0);
//	    request.setAttribute("smsApplication",0);
//	    request.setAttribute("collegeReceived",0);
//	    request.setAttribute("collegeNotReceived",0);
	    
	    
		return "success";	
		        	
	}

	public String totalAppliationTotalApplicantCollegeWise(){
		
		
		/*System.out.println("Test...board_college");*/
		

		
		BoardDTO userDto = (BoardDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		
		ApplicationStatCollegeDAO dashBoardDAO = new ApplicationStatCollegeDAO();
		DashBoardCollegeDTO dashBoardDTO=dashBoardDAO.getCollegeDashBoard(eiinCode,userDto.getBoardId());

		request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
		request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
	    request.setAttribute("webApplicant",dashBoardDTO.getWebApplicant());
	    request.setAttribute("webApplication",dashBoardDTO.getWebApplication());
	    request.setAttribute("smsApplicant",dashBoardDTO.getSmsApplicant());
	    request.setAttribute("smsApplication",dashBoardDTO.getSmsApplication());

		
	    
	    
	    
//	    request.setAttribute("totalApplicant",0);
//		request.setAttribute("totalApplication",0);
//	    request.setAttribute("webApplicant",0);
//	    request.setAttribute("webApplication",0);
//	    request.setAttribute("smsApplicant",0);
//	    request.setAttribute("smsApplication",0);
	    
	    
	    

		return "success";
	          
	
	}
	
public String totalRegCollegeWise(){
		
		
		/*System.out.println("Test...board_college");*/
		

		
		BoardDTO userDto = (BoardDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		
		ApplicationStatCollegeDAO dashBoardDAO = new ApplicationStatCollegeDAO();
		DashBoardCollegeDTO dashBoardDTO=dashBoardDAO.getRegDashBoard(eiinCode,userDto.getBoardId());

		request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
		request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
	    request.setAttribute("webApplicant",dashBoardDTO.getWebApplicant());
	    request.setAttribute("webApplication",dashBoardDTO.getWebApplication());
	    request.setAttribute("smsApplicant",dashBoardDTO.getSmsApplicant());
	    request.setAttribute("smsApplication",dashBoardDTO.getSmsApplication());

		

	    
	       
	    

		return "success";
	          
	
	}
	
	


	public String getEiinCode() {
		return eiinCode;
	}

	public void setEiinCode(String eiinCode) {
		this.eiinCode = eiinCode;
	}
    

}
