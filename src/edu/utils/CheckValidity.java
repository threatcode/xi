package edu.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;

import edu.action.common.BaseAction;
import edu.dao.UserDAO;
import edu.dao.application.ApplicationDAO;
import edu.dao.board.ApplicationStatBoardDAO;
import edu.dao.interBoard.ApplicationStatBoardInterBoardDAO;
import edu.dao.college.AdmissionDAO;
import edu.dao.college.ApplicationStatCollegeDAO;
import edu.dao.college.CollegeDAO;
import edu.dto.UserDTO;
import edu.dto.board.BoardDTO;
import edu.dto.board.DashBoardBoardDTO;
import edu.dto.college.CollegeCourseDTO;
import edu.dto.college.CollegeDTO;
import edu.dto.college.DashBoardCollegeDTO;
import edu.dto.interBoard.DashBoardBoardInterBoardDTO;
import edu.dto.interBoard.InterBoardDTO;

public class CheckValidity extends BaseAction {

	private static final long serialVersionUID = -3942507757291865362L;
	private static final String failedResult="failed";
	private String successResult;
	
	private UserDTO user;
	private UserDAO userDAO;
	private String user_captcha;
   
	private String cacheName;
	
	
    public String getCacheName() {
		return cacheName;
	}



	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}



	public String deleteCache()
    {
		ServletActionContext.getServletContext().setAttribute(cacheName, null);
    	return null;
    }
	


	public String execute()
	{		
//		session.put("role",null);
		userDAO=new UserDAO();
		if(session.get("role")==null)
		{
			if(this.isPostRequest() && user.getUserid()!=null && user.getPassword()!=null)
			{
				
				UserDTO loggedInUser=userDAO.validateLogin(user.getUserid(), user.getPassword());
				
				if(loggedInUser!=null)
				{
					System.gc();
					if(session instanceof SessionMap)
					{
						((SessionMap<String,Object>)session).invalidate();
						session=ActionContext.getContext().getSession();
					}
					if(loggedInUser.getRoleid().equalsIgnoreCase("badmin")){
						BoardDTO userDto = userDAO.getBoardUser(user.getUserid());
						session.put("user", userDto);
						session.put("role", "badmin");
						ApplicationStatBoardDAO dashBoardDAO = new ApplicationStatBoardDAO();
						DashBoardBoardDTO dashBoardDTO=dashBoardDAO.getBoardDashBoard(userDto.getBoardId());
						request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
						request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
					    request.setAttribute("webApplicant",dashBoardDTO.getWebApplicant());
					    request.setAttribute("webApplication",dashBoardDTO.getWebApplication());
					    request.setAttribute("smsApplicant",dashBoardDTO.getSmsApplicant());
					    request.setAttribute("smsApplication",dashBoardDTO.getSmsApplication());
					    request.setAttribute("collegeReceived",dashBoardDTO.getCollegeAdmReceiveCount());
					    request.setAttribute("collegeNotReceived",dashBoardDTO.getCollegeAdmNotReceiveCount());
						successResult = "badmin";
					}
					else if(loggedInUser.getRoleid().equalsIgnoreCase("bgeneral")){
						BoardDTO userDto = userDAO.getBoardUser(user.getUserid());
						session.put("user", userDto);
						session.put("role", "bgeneral");
						ApplicationStatBoardDAO dashBoardDAO = new ApplicationStatBoardDAO();
						DashBoardBoardDTO dashBoardDTO=dashBoardDAO.getBoardDashBoard(userDto.getBoardId());
						request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
						request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
					    request.setAttribute("webApplicant",dashBoardDTO.getWebApplicant());
					    request.setAttribute("webApplication",dashBoardDTO.getWebApplication());
					    request.setAttribute("smsApplicant",dashBoardDTO.getSmsApplicant());
					    request.setAttribute("smsApplication",dashBoardDTO.getSmsApplication());
					    request.setAttribute("collegeReceived",dashBoardDTO.getCollegeAdmReceiveCount());
					    request.setAttribute("collegeNotReceived",dashBoardDTO.getCollegeAdmNotReceiveCount());
						successResult = "bgeneral";
					}
					else if(loggedInUser.getRoleid().equalsIgnoreCase("board")){
						BoardDTO userDto = userDAO.getBoardUser(user.getUserid());
						session.put("user", userDto);
						session.put("role", "board");
						ApplicationStatBoardDAO dashBoardDAO = new ApplicationStatBoardDAO();
						DashBoardBoardDTO dashBoardDTO=dashBoardDAO.getBoardDashBoard(userDto.getBoardId());

						
						request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
						request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
					    request.setAttribute("webApplicant",dashBoardDTO.getWebApplicant());
					    request.setAttribute("webApplication",dashBoardDTO.getWebApplication());
					    request.setAttribute("smsApplicant",dashBoardDTO.getSmsApplicant());
					    request.setAttribute("smsApplication",dashBoardDTO.getSmsApplication());
					    request.setAttribute("collegeReceived",dashBoardDTO.getCollegeAdmReceiveCount());
					    request.setAttribute("collegeNotReceived",dashBoardDTO.getCollegeAdmNotReceiveCount());
						
						
						
						successResult = "board";
						
					}
					else if(loggedInUser.getRoleid().equalsIgnoreCase("boardadmin")){
						BoardDTO bDto = userDAO.getBoardUser(user.getUserid());
						session.put("user", bDto);
						session.put("role", "boardadmin");
						session.put("userName", bDto.getBoardName());
						
						ApplicationStatBoardDAO dashBoardDAO = new ApplicationStatBoardDAO();
						DashBoardBoardDTO dashBoardDTO=dashBoardDAO.getBoardDashBoard(bDto.getBoardId());

						request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
						request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
						//successResult = "boardadmin";
						
						 
						
						successResult = "boardadmin";
						
						
						
					}
					else if(loggedInUser.getRoleid().equalsIgnoreCase("boardentry")){
						BoardDTO bDto = userDAO.getBoardUser(user.getUserid());
						session.put("user", bDto);
						session.put("role", "boardentry");
						session.put("userName", bDto.getBoardName());
						successResult = "boardentry";
					}
					
					else if(loggedInUser.getRoleid().equalsIgnoreCase("college")){
						CollegeDTO cDto = userDAO.getCollegeUser(user.getUserid());
						session.put("user", cDto);
						session.put("role", "college");
						session.put("hasManualEntry", "No");
						CollegeDTO userDto = (CollegeDTO) session.get("user");
						
//						AdmissionDAO svgLoadDAO = new AdmissionDAO();
//						List<CollegeCourseDTO> shiftList = svgLoadDAO.getShiftList(userDto.getEiin());
//						request.setAttribute("shiftList", shiftList);
//						List<CollegeCourseDTO> versionList = svgLoadDAO.getVersionList(userDto.getEiin());
//						request.setAttribute("versionList", versionList);
//						List<CollegeCourseDTO> groupList = svgLoadDAO.getGroupList(userDto.getEiin());
//						request.setAttribute("groupList", groupList);
						
						ApplicationStatCollegeDAO dashBoardDAO = new ApplicationStatCollegeDAO();
						DashBoardCollegeDTO dashBoardDTO=dashBoardDAO.getCollegeDashBoard(userDto.getEiin(), userDto.getBoard_id());

						request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
						request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
					    request.setAttribute("webApplicant",dashBoardDTO.getWebApplicant());
					    request.setAttribute("webApplication",dashBoardDTO.getWebApplication());
					    request.setAttribute("smsApplicant",dashBoardDTO.getSmsApplicant());
					    request.setAttribute("smsApplication",dashBoardDTO.getSmsApplication());
						
						request.setAttribute("userInfo", userDto);
						
						successResult = "college";
						
					}
					else if(loggedInUser.getRoleid().equalsIgnoreCase("college3")){
						CollegeDTO cDto = userDAO.getCollegeUser(user.getUserid());
						session.put("user", cDto);
						session.put("role", "college3");
						CollegeDTO userDto = (CollegeDTO) session.get("user");

						AdmissionDAO svgLoadDAO = new AdmissionDAO();
						List<CollegeCourseDTO> shiftList = svgLoadDAO.getShiftList3(userDto.getEiin());
						request.setAttribute("shiftList", shiftList);
						List<CollegeCourseDTO> versionList = svgLoadDAO.getVersionList3(userDto.getEiin());
						request.setAttribute("versionList", versionList);
						List<CollegeCourseDTO> groupList = svgLoadDAO.getGroupList3(userDto.getEiin());
						request.setAttribute("groupList", groupList);
						
						request.setAttribute("userInfo", userDto);
						
						successResult = "college3";
						
					} else if(loggedInUser.getRoleid().equalsIgnoreCase("admin")){
						InterBoardDTO aDto = userDAO.getAdminUser(user.getUserid());
						session.put("user", aDto);
						session.put("role", "admin");
						
											
						
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
						
						successResult = "admin";
						
					} else if(loggedInUser.getRoleid().equalsIgnoreCase("udc")){
						UserDTO aDto = userDAO.getAdminUserIict(user.getUserid());
//						InterBoardDTO aDto = userDAO.getAdminUser(user.getUserid());
						session.put("user", aDto);
						session.put("role", "admin");
						
						
						/*ApplicationStatBoardInterBoardDAO dashBoardDAO = new ApplicationStatBoardInterBoardDAO();
						DashBoardBoardInterBoardDTO dashBoardDTO=dashBoardDAO.getInterBoardDashBoard();
						request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
						request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
					    request.setAttribute("webApplicant",dashBoardDTO.getWebApplicant());
					    request.setAttribute("webApplication",dashBoardDTO.getWebApplication());
					    request.setAttribute("smsApplicant",dashBoardDTO.getSmsApplicant());
					    request.setAttribute("smsApplication",dashBoardDTO.getSmsApplication());*/
						
						//successResult = "admin";
						
						//session.put("user", loggedInUser);
						successResult = "udc";
					} else if(loggedInUser.getRoleid().equalsIgnoreCase("applicant")){
						session.put("user", loggedInUser);
						ApplicationDAO applicationDAO=new ApplicationDAO();
						session.put("totalOtpCount", applicationDAO.getChoiceOtpCount(user.getUserid()));
						session.put("releaseSlipEligibility", applicationDAO.isEligibleForReleaseSlip(user.getUserid()));
						//session.put("isEligibleForBTEBApplication", applicationDAO.isEligibleForBTEBApplication(user.getUserid()));
						Configuration configuration = Configuration.getInstance();
						
						loggedInUser.setPhotoUrl(configuration.getConfigurationMap().get(configuration.getEnvironment() + ".image.provider.url")
		                        + "applicantPhoto/" + user.getUserid() + ".jpg");
						successResult = "applicant";
					} else if(loggedInUser.getRoleid().equalsIgnoreCase("napplicant")){
						session.put("user", loggedInUser);
						ApplicationDAO applicationDAO=new ApplicationDAO();
						session.put("totalOtpCount", applicationDAO.getChoiceOtpCount(user.getUserid()));
						//session.put("releaseSlipEligibility", applicationDAO.isEligibleForReleaseSlip(user.getUserid()));
						
						successResult = "napplicant";
					}
					
					session.put("authenticated", new Boolean(true));
					//session.put("user", loggedInUser);
					session.put("role", loggedInUser.getRoleid());					
					return successResult;
				}
			}

			session.put("error","Invalid Userid or Password.");
//			addFieldError( "user.userid", "Invalid Userid or Password." );
			return "failure";
		}
		else {
			String role = session.get("role").toString();	
			if(role.equalsIgnoreCase("badmin")){
				BoardDTO userDto = userDAO.getBoardUser(user.getUserid());
				session.put("user", userDto);
				session.put("role", "badmin");
//				ApplicationStatBoardDAO dashBoardDAO = new ApplicationStatBoardDAO();
//				DashBoardBoardDTO dashBoardDTO=dashBoardDAO.getBoardDashBoard(userDto.getBoardId());
//				request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
//				request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
//			    request.setAttribute("webApplicant",dashBoardDTO.getWebApplicant());
//			    request.setAttribute("webApplication",dashBoardDTO.getWebApplication());
//			    request.setAttribute("smsApplicant",dashBoardDTO.getSmsApplicant());
//			    request.setAttribute("smsApplication",dashBoardDTO.getSmsApplication());
//			    request.setAttribute("collegeReceived",dashBoardDTO.getCollegeAdmReceiveCount());
//			    request.setAttribute("collegeNotReceived",dashBoardDTO.getCollegeAdmNotReceiveCount());
				successResult = "badmin";
			}
			else if(role.equalsIgnoreCase("bgeneral")){
				BoardDTO userDto = userDAO.getBoardUser(user.getUserid());
				session.put("user", userDto);
				session.put("role", "bgeneral");
				ApplicationStatBoardDAO dashBoardDAO = new ApplicationStatBoardDAO();
				DashBoardBoardDTO dashBoardDTO=dashBoardDAO.getBoardDashBoard(userDto.getBoardId());
				request.setAttribute("totalApplicant",dashBoardDTO.getTotalApplicant());
				request.setAttribute("totalApplication",dashBoardDTO.getTotalApplication());
			    request.setAttribute("webApplicant",dashBoardDTO.getWebApplicant());
			    request.setAttribute("webApplication",dashBoardDTO.getWebApplication());
			    request.setAttribute("smsApplicant",dashBoardDTO.getSmsApplicant());
			    request.setAttribute("smsApplication",dashBoardDTO.getSmsApplication());
			    request.setAttribute("collegeReceived",dashBoardDTO.getCollegeAdmReceiveCount());
			    request.setAttribute("collegeNotReceived",dashBoardDTO.getCollegeAdmNotReceiveCount());
				successResult = "bgeneral";
			}
			else if(role.equalsIgnoreCase("board")){
				BoardDTO userDto = (BoardDTO)session.get("user");
			    request.setAttribute("totalApplicant",0);
				request.setAttribute("totalApplication",0);
			    request.setAttribute("webApplicant",0);
			    request.setAttribute("webApplication",0);
			    request.setAttribute("smsApplicant",0);
			    request.setAttribute("smsApplication",0);
			    request.setAttribute("collegeReceived",0);
			    request.setAttribute("collegeNotReceived",0);
				successResult = "board";
			} 
			else if(role.equalsIgnoreCase("boardadmin")){
				BoardDTO userDto = (BoardDTO)session.get("user");
				successResult = "boardadmin";
			} 
			else if(role.equalsIgnoreCase("boardentry")){
				BoardDTO userDto = (BoardDTO)session.get("user");
				successResult = "boardentry";
			} 
			else if(role.equalsIgnoreCase("college")){
				CollegeDTO userDto = (CollegeDTO)session.get("user");
				AdmissionDAO svgLoadDAO = new AdmissionDAO();
				List<CollegeCourseDTO> shiftList = svgLoadDAO.getShiftList(userDto.getEiin());
				request.setAttribute("shiftList", shiftList);
				List<CollegeCourseDTO> versionList = svgLoadDAO.getVersionList(userDto.getEiin());
				request.setAttribute("versionList", versionList);
				List<CollegeCourseDTO> groupList = svgLoadDAO.getGroupList(userDto.getEiin());
				request.setAttribute("groupList", groupList);
				successResult = "college";
			} 
			else if(role.equalsIgnoreCase("admin")){
				InterBoardDTO userDto = (InterBoardDTO)session.get("user");
				successResult = "admin";
			}
			else {
				((SessionMap<String,Object>)session).invalidate();
				successResult = "success";
			}
		}
		return successResult;
	}
	
	
	public String applicantCheckValidity()
	{	
		String checkSum=(String)session.get("CorrectAnswer");
		if(!user_captcha.equalsIgnoreCase(checkSum))
		{
			//request.setAttribute("errormessage", "captchaError");
			session.put("errormessage", "captchaError");
			return failedResult;
		}
		if(session.get("role")==null)
		{
			if(this.isPostRequest() && user.getSsc_roll()!=null && user.getSsc_board()!=null 
					&& user.getSsc_year()!=null&& user.getSsc_reg()!=null
					&& user.getMobile()!=null && user.getScode()!=null)
			{
				userDAO=new UserDAO();
				UserDTO loggedInUser=userDAO.validateApplicantLogin(user.getSsc_roll(),user.getSsc_board(),user.getSsc_year(),user.getSsc_reg(),
						user.getMobile(), user.getScode());
				
				if(loggedInUser!=null)
				{
					System.gc();
					if(session instanceof SessionMap)
					{
						((SessionMap<String,Object>)session).invalidate();
						session=ActionContext.getContext().getSession();
					}
					if(loggedInUser.getRoleid().equalsIgnoreCase("applicant")){
						session.put("user", loggedInUser);
						ApplicationDAO applicationDAO=new ApplicationDAO();
						//session.put("totalOtpCount", applicationDAO.getChoiceOtpCount(user.getUserid()));
						//session.put("releaseSlipEligibility", applicationDAO.isEligibleForReleaseSlip(loggedInUser.getUserid()));
						session.put("isMigrationEligible", applicationDAO.isEligibleForMigration(loggedInUser.getUserid()));
						//session.put("isEligibleForBTEBApplication", applicationDAO.isEligibleForBTEBApplication(user.getUserid()));
						Configuration configuration = Configuration.getInstance();
						loggedInUser.setPhotoUrl(configuration.getConfigurationMap().get(configuration.getEnvironment() + ".image.provider.url")
		                        + "applicantPhoto/" + user.getUserid() + ".jpg");
		                        
						successResult = "applicant";
					} 
					session.put("authenticated", new Boolean(true));
					//session.put("user", loggedInUser);
					session.put("role", loggedInUser.getRoleid());					
					return "applicant";
				}
			}

			
			addFieldError( "err_login", "Invalid Userid or Password." );
			//request.setAttribute("errormessage", "infoError");
			session.put("errormessage", "infoError");
			
			return failedResult;
		}
		else {
			String role = session.get("role").toString();	
		}
		//return successResult;
		return "applicant";
	}
	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public String getUser_captcha() {
		return user_captcha;
	}


	public void setUser_captcha(String user_captcha) {
		this.user_captcha = user_captcha;
	}
}
