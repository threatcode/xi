package edu.action.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.dispatcher.SessionMap;
import org.json.JSONObject;

import com.google.gson.Gson;

import edu.action.common.BaseAction;
import edu.dao.UserDAO;
import edu.dao.applicant.RetrivedPinDAO;
import edu.dao.application.SscDataDAO;
import edu.dao.board.ApplicantInfoDAO;
import edu.dao.college.AdmissionDAO;
import edu.dao.college.CollegeDAO;
import edu.dto.UserDTO;
import edu.dto.applicant.PinRetrivalDTO;
import edu.dto.application.ApplicantDTO;
import edu.dto.application.ResponseDTO;
import edu.dto.board.BoardDTO;
import edu.dto.board.ApplicantInfoBoardDTO;
import edu.dto.college.ApplicantInfoDTO;
import edu.dto.college.CollegeDTO;
import edu.helpers.SmsSender;

public class Board extends BaseAction {
	
	private static final long serialVersionUID = 485287688957309308L;
	
	private String previous_quota_ff;
	private String previous_quota_eq;
	private String previous_quota_bksp;
	private String previous_quota_expatriate;
	
	
	private String quota_ff;
	private String quota_eq;
	private String quota_bksp;
	private String quota_expatriate;
	
	private String ssc_roll;
	private String ssc_board;
	private String ssc_year;
	private String ssc_reg;
	private String eiinCode;
	private String application_id;
	private String boardName;
	private String dataString;
	
	private String mobilenumber;
	private ApplicantDTO applicant;
	
	public String boardHome(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * @author nasir
	 * @return
	 */
	public String udcHome(){
		
		UserDTO userDto = (UserDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		
		System.out.println(userDto.getUserid());
		return SUCCESS;
	}
	
	
	public String notReceivedAdmissionCollegeList(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
				
	    AdmissionDAO admissionDAO = new AdmissionDAO();
		List<CollegeDTO> nonReceivedCollegeList = admissionDAO.getNonReceivedList(userDto.getBoardId());
		request.setAttribute("nonReceivedCollegeList", nonReceivedCollegeList);
				
		return "success";
	
	}
	
	
	public String receivedAdmissionCollegeList(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
				
	    AdmissionDAO admissionDAO = new AdmissionDAO();
		List<CollegeDTO> receivedCollegeList = admissionDAO.getReceivedList(userDto.getBoardId());
		request.setAttribute("receivedCollegeList", receivedCollegeList);
				
		return "success";
	
	}
	
	public String showPersonalInfo_TT()
	{
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		
		SscDataDAO sscDAO=new SscDataDAO();
		applicant=sscDAO.getApplication_Quota_TT(ssc_roll, ssc_board, ssc_year,ssc_reg);
		if(applicant.getMsg().equals("nossc")){
			return "nossc";
		}
		else if(applicant.getMsg().equals("noapp")){
			return "noapp";
		}
		else{
			
			return SUCCESS;
		}
		
	}
	
	public String editQuota_TT()
	{
		//boolean response=false;
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		
		ResponseDTO response=new ResponseDTO();
		Gson gson = new Gson();
		
		
		ApplicantInfoDAO boardDAO=new ApplicantInfoDAO();

		response=boardDAO.editQuota(previous_quota_ff,previous_quota_eq,previous_quota_bksp,previous_quota_expatriate,quota_ff, quota_eq, quota_bksp,quota_expatriate,application_id,userDto.getBoardUserId(),getIpAddressDTO());
		
	    if(response.getMessage().equalsIgnoreCase("Quota Choice Changed Successfully ."))
	    {
	    	String   message="(Roll:"+ssc_roll.trim()+ ",Board:"+boardName.trim()+",Passing Year:"+ssc_year.trim()+"),Your quota information has been changed.";    	
	    	
	    	if(!mobilenumber.trim().equalsIgnoreCase(""))
	    	{
	    	SmsSender smsSender=new SmsSender();
	    	smsSender.setText(message);
			smsSender.setMobile(mobilenumber.trim());
			smsSender.setOperation("boardmessage");
			Thread thread = new Thread(smsSender);
			thread.start();
	    	}
	    	
	    }
		
		
		String json = gson.toJson(response);
		setJsonResponse(json);
		
       // return null;
        
        return SUCCESS;
		
	}

	
	
	public String retrivedPin(){

		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
				
     	RetrivedPinDAO retrivedPinDAO = new RetrivedPinDAO();
		PinRetrivalDTO pinRetrivalDTO=retrivedPinDAO.getRetrivedPinByBoard(ssc_roll,ssc_reg,ssc_board,ssc_year);

		if(pinRetrivalDTO==null){
	    /*request.setAttribute("applicantName",pinRetrivalDTO.getApplicantName());*/
			request.setAttribute("applicationID"," ");
		    request.setAttribute("pin"," ");
	    
		} else {
			request.setAttribute("applicationID",pinRetrivalDTO.getApplicationID());
			if(pinRetrivalDTO.getPIN()!=null)
			{
			    	String   message = "Your ("+ssc_roll.trim()+ ") security code for online application is "+pinRetrivalDTO.getPIN()+" - Board Authority.";    	
			    	
			    	SmsSender smsSender=new SmsSender();
			    	smsSender.setText(message);
					smsSender.setMobile(pinRetrivalDTO.getMobile().trim());
					smsSender.setOperation("boardmessage");
					Thread thread = new Thread(smsSender);
					thread.start();

					request.setAttribute("pin","Security code of <span style=\"color: red\">"+
							"</span> is : <span style=\"color: blue\">"+pinRetrivalDTO.getPIN()+"<br> This code sent to "+pinRetrivalDTO.getMobile().trim()+
							"</span>");
			}
			else
				request.setAttribute("pin","<span style=\"color: red\">Applicant didn't apply via web</span>");
		}
		
		return "success";

	}
	
	
	public String approvedStudentListOfMerit(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		

		ApplicantInfoDAO boardDAO=new ApplicantInfoDAO();
		ApplicantInfoBoardDTO admissionInfoDTO=boardDAO.getApplicantAdmissionInfo(ssc_roll,ssc_board,ssc_year,ssc_reg);
		
		if(admissionInfoDTO!=null){
		AdmissionDAO approvedStudentListOfMeritDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> 	approvedStudentListOfMerit = approvedStudentListOfMeritDAO.getApprovedStudentListOfMeritByBoard(admissionInfoDTO.getShiftID(),admissionInfoDTO.getVersionID(),
				admissionInfoDTO.getGroupId(),admissionInfoDTO.getMerit(),admissionInfoDTO.getEiinCode(),admissionInfoDTO.getApplicationID());
		request.setAttribute("approvedStudentListOfMerit", approvedStudentListOfMerit);
		request.setAttribute("hiddenShiftID", admissionInfoDTO.getShiftID());
		request.setAttribute("hiddenVersionID", admissionInfoDTO.getVersionID());
		request.setAttribute("hiddenGroupID", admissionInfoDTO.getGroupId());
		request.setAttribute("hiddenMerit", admissionInfoDTO.getMerit());
		}
		
		request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
	    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
	    
		
		return "success";

				
	}

	public String cancelApproveStudentOfMerit(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}	
		String[] applicants = dataString.split("###"); 
		ArrayList<JSONObject> listResponseJSON = new ArrayList<JSONObject>();
		
		JSONObject jsonObj = new JSONObject();
		try {
			String[] response = new String[applicants.length]; 
			String[] application_ID =  new String[applicants.length];
			String[] merit_type =  new String[applicants.length];
			String[] shift_id =  new String[applicants.length];
			String[] version_id =  new String[applicants.length];
			String[] group_id =  new String[applicants.length];
			String[] eiin_code =  new String[applicants.length];
			String[] quota =  new String[applicants.length];

			for (int i = 0; i < applicants.length; i++) {
				String[] applicantData = applicants[i].split("#");
				application_ID[i] = applicantData[0];
				merit_type[i] = applicantData[1];
				shift_id[i] = applicantData[2];
				version_id[i] = applicantData[3];
				group_id[i] = applicantData[4];
				eiin_code[i] = applicantData[5];
				quota[i] = applicantData[7];

			}
			
			ApplicantInfoDAO cancelAdmissionDAO=new ApplicantInfoDAO();
			response = cancelAdmissionDAO.cancelAdmissionByBoard(application_ID, merit_type, shift_id, version_id,
					group_id, eiin_code,quota,userDto.getBoardUserId(), getIpAddressDTO());
			
			for (int i = 0; i < applicants.length; i++) {
				String[] applicantData = applicants[i].split("#");
				JSONObject tmp = new JSONObject();
				tmp.put("appid", applicantData[0]);
				tmp.put("appname", applicantData[8]);
				tmp.put("rowIndex", applicantData[9]);
				tmp.put("message", response[i]);
				listResponseJSON.add(tmp);
				
				
				if(response[i].equalsIgnoreCase("Successfully cancelled."))
				{
					UserDAO udao = new UserDAO();
					UserDTO tmpUDTO = udao.getApplicantContactNo(applicantData[0]);
					
					CollegeDAO cdao = new CollegeDAO();
					String cMobile = cdao.getMobileNo(eiin_code[i]);
					
	            	SmsSender smsSender=new SmsSender();
					smsSender.setMobile(tmpUDTO.getMobile());
					smsSender.setText("Your (Roll:"+tmpUDTO.getSsc_roll()+") admission at "+eiin_code[i] +" has been cancelled - Board Authority");
					smsSender.setOperation("CancelledSMS");
					Thread thread = new Thread(smsSender);
					thread.start();

	            	smsSender=new SmsSender();
					smsSender.setMobile(cMobile);
					smsSender.setText(" Roll:"+tmpUDTO.getSsc_roll()+" admission has been cancelled from your ("+eiin_code[i]+
							") institute - Board Authority");
					smsSender.setOperation("CancelledSMS");
					thread = new Thread(smsSender);
					thread.start();

					
				}
			}

			
			
			jsonObj.put("cancelled", listResponseJSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setJsonResponse(jsonObj.toString());
		return null;		          

		}
	
	public String admissionCancelledStudentListOfMerit(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}
		

		ApplicantInfoDAO boardDAO=new ApplicantInfoDAO();
		ApplicantInfoBoardDTO admissionInfoDTO=boardDAO.getApplicantAdmissionCancelledInfo(ssc_roll,ssc_board,ssc_year,ssc_reg,eiinCode);
		
		if(admissionInfoDTO!=null){
		AdmissionDAO admissionCancelledStudentListOfMeritDAO = new AdmissionDAO();
		List<ApplicantInfoDTO> 	admissionCancelledStudentListOfMerit = admissionCancelledStudentListOfMeritDAO.getAdmissionCancelledStudentListOfMeritByBoard(admissionInfoDTO.getShiftID(),admissionInfoDTO.getVersionID(),
				admissionInfoDTO.getGroupId(),admissionInfoDTO.getMerit(),admissionInfoDTO.getEiinCode(),admissionInfoDTO.getApplicationID());
		request.setAttribute("admissionCancelledStudentListOfMerit", admissionCancelledStudentListOfMerit);
		request.setAttribute("hiddenShiftID", admissionInfoDTO.getShiftID());
		request.setAttribute("hiddenVersionID", admissionInfoDTO.getVersionID());
		request.setAttribute("hiddenGroupID", admissionInfoDTO.getGroupId());
		request.setAttribute("hiddenMerit", admissionInfoDTO.getMerit());
		}
		
		request.setAttribute("successMessage", request.getAttribute("successMessage") == null ? "" : request.getAttribute("successMessage"));
	    request.setAttribute("errorMessage", request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage"));
	    
		
		return "success";

				
	}	
	
	public String reApproveStudentOfMeritByBoard(){
		
		BoardDTO userDto = (BoardDTO) session.get("user");
		if(userDto == null){
			return "input";
		}	
		String[] applicants = dataString.split("###"); 
		ArrayList<JSONObject> listResponseJSON = new ArrayList<JSONObject>();
		
		JSONObject jsonObj = new JSONObject();
		try {
			String[] response = new String[applicants.length]; 
			String[] application_ID =  new String[applicants.length];
			String[] merit_type =  new String[applicants.length];
			String[] shift_id =  new String[applicants.length];
			String[] version_id =  new String[applicants.length];
			String[] group_id =  new String[applicants.length];
			String[] eiin_code =  new String[applicants.length];
			String[] quota =  new String[applicants.length];

			for (int i = 0; i < applicants.length; i++) {
				String[] applicantData = applicants[i].split("#");
				application_ID[i] = applicantData[0];
				merit_type[i] = applicantData[1];
				shift_id[i] = applicantData[2];
				version_id[i] = applicantData[3];
				group_id[i] = applicantData[4];
				eiin_code[i] = applicantData[5];
				quota[i] = applicantData[7];

			}
			
			ApplicantInfoDAO cancelAdmissionDAO=new ApplicantInfoDAO();
			response = cancelAdmissionDAO.reApproveAdmissionByBoard(application_ID, merit_type, shift_id, version_id,
					group_id, eiin_code,quota,userDto.getBoardUserId(), getIpAddressDTO());
			
			for (int i = 0; i < applicants.length; i++) {
				String[] applicantData = applicants[i].split("#");
				JSONObject tmp = new JSONObject();
				tmp.put("appid", applicantData[0]);
				tmp.put("appname", applicantData[8]);
				tmp.put("rowIndex", applicantData[9]);
				tmp.put("message", response[i]);
				listResponseJSON.add(tmp);
			}

			
			
			jsonObj.put("received", listResponseJSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setJsonResponse(jsonObj.toString());
		return null;		          

		}

	public String getEiinCode() {
		return eiinCode;
	}

	public void setEiinCode(String eiinCode) {
		this.eiinCode = eiinCode;
	}

	public String getPrevious_quota_ff() {
		return previous_quota_ff;
	}


	public void setPrevious_quota_ff(String previous_quota_ff) {
		this.previous_quota_ff = previous_quota_ff;
	}


	public String getPrevious_quota_eq() {
		return previous_quota_eq;
	}


	public void setPrevious_quota_eq(String previous_quota_eq) {
		this.previous_quota_eq = previous_quota_eq;
	}


	public String getPrevious_quota_bksp() {
		return previous_quota_bksp;
	}


	public void setPrevious_quota_bksp(String previous_quota_bksp) {
		this.previous_quota_bksp = previous_quota_bksp;
	}


	public String getPrevious_quota_expatriate() {
		return previous_quota_expatriate;
	}


	public void setPrevious_quota_expatriate(String previous_quota_expatriate) {
		this.previous_quota_expatriate = previous_quota_expatriate;
	}


	public String getQuota_ff() {
		return quota_ff;
	}


	public void setQuota_ff(String quota_ff) {
		this.quota_ff = quota_ff;
	}


	public String getQuota_eq() {
		return quota_eq;
	}


	public void setQuota_eq(String quota_eq) {
		this.quota_eq = quota_eq;
	}


	public String getQuota_bksp() {
		return quota_bksp;
	}


	public void setQuota_bksp(String quota_bksp) {
		this.quota_bksp = quota_bksp;
	}


	public String getQuota_expatriate() {
		return quota_expatriate;
	}


	public void setQuota_expatriate(String quota_expatriate) {
		this.quota_expatriate = quota_expatriate;
	}


	public String getSsc_roll() {
		return ssc_roll;
	}


	public void setSsc_roll(String ssc_roll) {
		this.ssc_roll = ssc_roll;
	}


	public String getSsc_board() {
		return ssc_board;
	}


	public void setSsc_board(String ssc_board) {
		this.ssc_board = ssc_board;
	}


	public String getSsc_year() {
		return ssc_year;
	}


	public void setSsc_year(String ssc_year) {
		this.ssc_year = ssc_year;
	}


	public String getSsc_reg() {
		return ssc_reg;
	}


	public void setSsc_reg(String ssc_reg) {
		this.ssc_reg = ssc_reg;
	}


	public String getApplication_id() {
		return application_id;
	}


	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}


	public ApplicantDTO getApplicant() {
		return applicant;
	}


	public void setApplicant(ApplicantDTO applicant) {
		this.applicant = applicant;
	}


	public String getBoardName() {
		return boardName;
	}


	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}


	public String getMobilenumber() {
		return mobilenumber;
	}


	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getDataString() {
		return dataString;
	}

	public void setDataString(String dataString) {
		this.dataString = dataString;
	}
	
	

}
