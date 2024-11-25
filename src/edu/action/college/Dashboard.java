package edu.action.college;



import edu.action.common.BaseAction;
import edu.dao.college.ApplicationStatCollegeDAO;
import edu.dto.college.CollegeDTO;
import edu.dto.college.DashBoardCollegeDTO;

public class Dashboard extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -532536617639123087L;

	public String totalAppliationTotalApplicant(){
		
		/*System.out.println("Test..College...DashBoard");*/
		
		CollegeDTO userDto = (CollegeDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
		
		ApplicationStatCollegeDAO dashBoardDAO = new ApplicationStatCollegeDAO();
		DashBoardCollegeDTO dashBoardDTO=dashBoardDAO.getCollegeDashBoard(userDto.getEiin(), userDto.getBoard_id());

		request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
		request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
	    request.setAttribute("webApplicant",dashBoardDTO.getWebApplicant());
	    request.setAttribute("webApplication",dashBoardDTO.getWebApplication());
	    request.setAttribute("smsApplicant",dashBoardDTO.getSmsApplicant());
	    request.setAttribute("smsApplication",dashBoardDTO.getSmsApplication());

		return "success";
	
	}
    

}
