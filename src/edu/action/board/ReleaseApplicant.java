package edu.action.board;

import java.util.ArrayList;

import edu.action.common.BaseAction;
import edu.dao.board.ApplicantReleaseDAO;
import edu.dto.UserDTO;
import edu.dto.application.ResponseDTO;
import edu.helpers.SmsSender;

public class ReleaseApplicant extends BaseAction {

	
	private static final long serialVersionUID = 1L;
	public String uploadId;

	public String releaseApplicantAndSendSms(){
		ApplicantReleaseDAO releaseDAO=new ApplicantReleaseDAO();
		ResponseDTO resp=releaseDAO.cleanData(this.uploadId);
		ArrayList<UserDTO> userList=null;
		if(resp.getStatus().equalsIgnoreCase("SUCCESS")){
			userList=new ArrayList<UserDTO>();
			userList=releaseDAO.getApplicantListForSMS(uploadId);
			String[] userIdArr=new String[userList.size()];
			for(int i=0;i<userList.size();i++)
			{
				if(i%50==0)
					try{Thread.sleep(2000);}catch(Exception e){e.printStackTrace();};
				UserDTO tmp = userList.get(i);
				userIdArr[i]=tmp.getUserid();
				SmsSender smsSender=new SmsSender();
				smsSender.setMobile(tmp.getMobile() );
				smsSender.setText("From Education Board:You can now apply online using Application ID ("+tmp.getUserid()+") and Password ("+tmp.getPassword()+").To add colleges, go to Choice List and then click Update." );
				smsSender.setOperation("textMSG");
				Thread thread = new Thread(smsSender);
				thread.start();
			}
			releaseDAO.updateApplicantListForSMS(userIdArr);
		}
		
		
		try{
	    	 response.setContentType("text/html");
	    	 response.getWriter().write(resp.getMessage());
	          }
	        catch(Exception e) {e.printStackTrace();}
		return null;
		
	}
	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}
		
}
