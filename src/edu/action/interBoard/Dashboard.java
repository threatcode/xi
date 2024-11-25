package edu.action.interBoard;
import edu.action.common.BaseAction;
import edu.dao.college.ApplicationStatCollegeDAO;
import edu.dao.interBoard.ApplicationStatBoardInterBoardDAO;
import edu.dto.college.DashBoardCollegeDTO;
import edu.dto.interBoard.DashBoardBoardInterBoardDTO;
import edu.dto.interBoard.InterBoardDTO;

public class Dashboard extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -532536617639123087L;
	
	private String boardId;
	private String eiinCode;
	
	
	public String totalAppliationTotalApplicantInterBoard(){
		
	/*	System.out.println("Test....InterBoard");*/
		
		InterBoardDTO userDto = (InterBoardDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
				
		ApplicationStatBoardInterBoardDAO dashBoardDAO = new ApplicationStatBoardInterBoardDAO();
		DashBoardBoardInterBoardDTO dashBoardDTO=dashBoardDAO.getInterBoardDashBoard();

		request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
		request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
	    
		
		request.setAttribute("totalpayment",dashBoardDTO.getTotalpayment());
		request.setAttribute("teletalk",dashBoardDTO.getTeletalk());
	    request.setAttribute("bkash",dashBoardDTO.getBkash());
	    request.setAttribute("nagad",dashBoardDTO.getNagad());
	    request.setAttribute("surecash",dashBoardDTO.getSurecash());
	    request.setAttribute("sbl",dashBoardDTO.getSbl());
	    request.setAttribute("rocket",dashBoardDTO.getRocket());

	    
	    
	    
	    
	    
	    
	    
	    /*request.setAttribute("resultTotal",dashBoardDTO.getResult());
	    request.setAttribute("regPaid",dashBoardDTO.getRegpaid());	    
	    request.setAttribute("collConfirm",dashBoardDTO.getCollconfirm());*/
	    
	    
	    

		return "success";

	
		        	
	}
	
	
	public String totalAppliationTotalApplicantInterBoardPayment(){
		
		/*	System.out.println("Test....InterBoard");*/
			
			InterBoardDTO userDto = (InterBoardDTO) session.get("user");
			
			/*if(userDto == null){
				return "input";
			}*/
					
			ApplicationStatBoardInterBoardDAO dashBoardDAO = new ApplicationStatBoardInterBoardDAO();
			DashBoardBoardInterBoardDTO dashBoardDTO=dashBoardDAO.getInterBoardDashBoardPayment();
			
			ApplicationStatBoardInterBoardDAO dashBoardDAO1 = new ApplicationStatBoardInterBoardDAO();
			//DashBoardBoardInterBoardDTO dashBoardDTO1=dashBoardDAO1.getInterBoardDashBoardPaymentBoard();
			
			

			request.setAttribute("totalweb",dashBoardDTO.getWebApplicant());
			request.setAttribute("totalsms",dashBoardDTO.getSmsApplicant());			
			request.setAttribute("totalTTweb",dashBoardDTO.getTotalTeleTalk());			
			request.setAttribute("totalSC",dashBoardDTO.getTotalSureCash());			
		    request.setAttribute("totalBkash",dashBoardDTO.getTotalBkash());
		    request.setAttribute("totalGP",dashBoardDTO.getTotalGP());
		    
		    
		    
		    /*request.setAttribute("allboard",dashBoardDTO1.getAllBoard());
		    request.setAttribute("dhaka",dashBoardDTO1.getDhaka());
		    request.setAttribute("comilla",dashBoardDTO1.getComilla());
		    request.setAttribute("rajshahi",dashBoardDTO1.getRajshahi());
		    request.setAttribute("jessore",dashBoardDTO1.getJessore());
		    request.setAttribute("chittagong",dashBoardDTO1.getChittagong());
		    request.setAttribute("barisal",dashBoardDTO1.getBarisal());
		    request.setAttribute("sylhet",dashBoardDTO1.getSylhet());
		    request.setAttribute("dinajpur",dashBoardDTO1.getDinajpur());
		    request.setAttribute("madrasha",dashBoardDTO1.getMadrasha());
		    request.setAttribute("bteb",dashBoardDTO1.getBteb());*/
		    


			return "success";

		
			        	
		}
	
	public String TotalApplicantstotalAppliationsBoardWise(){
		
		/*System.out.println("Test...Board");*/
		InterBoardDTO userDto = (InterBoardDTO) session.get("user");
		
/*		if(userDto == null){
			return "input";
		}*/
				
		ApplicationStatBoardInterBoardDAO dashBoardDAO = new ApplicationStatBoardInterBoardDAO();
		DashBoardBoardInterBoardDTO dashBoardDTO=dashBoardDAO.getBoardDashBoard(boardId);

	    request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
		request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
	    request.setAttribute("webApplicant",dashBoardDTO.getWebApplicant());
	    request.setAttribute("webApplication",dashBoardDTO.getWebApplication());
	    request.setAttribute("smsApplicant",dashBoardDTO.getSmsApplicant());
	    request.setAttribute("smsApplication",dashBoardDTO.getSmsApplication());

		return "success";

	
		        	
	}

	public String totalAppliationTotalApplicantCollegeWise(){
		
		
		/*System.out.println("Test...board_college");*/
		InterBoardDTO userDto = (InterBoardDTO) session.get("user");
		
/*		if(userDto == null){
			return "input";
		}*/
		
		ApplicationStatCollegeDAO dashBoardDAO = new ApplicationStatCollegeDAO();
		DashBoardCollegeDTO dashBoardDTO=dashBoardDAO.getCollegeDashBoard(eiinCode,boardId);

		request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
		request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
	    request.setAttribute("webApplicant",dashBoardDTO.getWebApplicant());
	    request.setAttribute("webApplication",dashBoardDTO.getWebApplication());
	    request.setAttribute("smsApplicant",dashBoardDTO.getSmsApplicant());
	    request.setAttribute("smsApplication",dashBoardDTO.getSmsApplication());

		

		return "success";
	          
	
	}
	
	public String boardDashBoard(){
		
		/*System.out.println("search board-Dash Board");*/
		InterBoardDTO userDto = (InterBoardDTO) session.get("user");
		
		if(userDto == null){
			return "input";
		}
	
		return "success";

	
		        	
	}
	


	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getEiinCode() {
		return eiinCode;
	}

	public void setEiinCode(String eiinCode) {
		this.eiinCode = eiinCode;
	}
    

}
