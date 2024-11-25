package edu.utils;

import java.util.*; 

public class UserActionManager {
	
	HashMap<String,String> hmUserAction=new HashMap<String,String>();

	

	public HashMap<String, String> getHmUserAction() {
		
	    String ActionsAdmin="dashboard,fileupload,readexcel,viewuser,changePasswd1," +
		"getNewPassword,UserManager,QuesController,etcLoad," +
		"validateSecureKey,getQuestionStep1,sendSKeyDC,getQuestionStep2," +
		"setQuestion,makeQSet,LockSet,setAuth,setOTP,sendKey," +
		"skreq,getKey,createAndSendKey,examInit,viewExam,SendToAccept,examInitSave,getAdminQuestionStep1,getAdminQuestionStep2,";	
		hmUserAction.put("admin", ActionsAdmin);
		
		String ActionsDG="dashboard,fileupload,readexcel,viewuser,changePasswd1," +
		"getNewPassword,UserManager,QuesController,etcLoad," +
		"validateSecureKey,getQuestionStep1,sendSKeyDC,getQuestionStep2," +
		"setQuestion,makeQSet,LockSet,setAuth,setOTP,sendKey," +
		"skreq,getKey,createAndSendKey,examInit,viewExam,SendToAccept,examInitSave,ExamInitiateEdit," +
		"setCertPassStep1,setCertPassStep2";  
	    hmUserAction.put("dg", ActionsDG);
	    
	    String ActionsADG="dashboard,fileupload,readexcel,viewuser,changePasswd1," +
		"getNewPassword,UserManager,QuesController,etcLoad," +
		"validateSecureKey,getQuestionStep1,sendSKeyDC,getQuestionStep2," +
		"setQuestion,makeQSet,LockSet,setAuth,setOTP,sendKey," +
		"skreq,getKey,createAndSendKey,examInit,viewExam,SendToAccept,examInitSave,ExamInitiateEdit";
	    hmUserAction.put("adg", ActionsADG);
	    
	    String ActionsDC="dashboard,fileupload,readexcel,viewuser,changePasswd1," +
		"getNewPassword,UserManager,QuesController,etcLoad," +
		"validateSecureKey,getQuestionStep1,sendSKeyDC,getQuestionStep2," +
		"setQuestion,makeQSet,LockSet,setAuth,setOTP,sendKey," +
		"skreq,getKey,createAndSendKey,examInit,viewExam,SendToAccept,examInitSave";
	    hmUserAction.put("dc", ActionsDC);


		return hmUserAction;
	}

	public void setHmUserAction(HashMap<String, String> hmUserAction) {
		this.hmUserAction = hmUserAction;
	}



	public boolean isValidAction(String roleid,String actionName)
	{
		HashMap<String,String> hmUserAction=getHmUserAction();
		String [] Actions=hmUserAction.get(roleid).split(",");
		for(int actionN=0;actionN<=Actions.length;actionN++)
		{
			if(actionName.equalsIgnoreCase(Actions[actionN]))
				return true;
		}
		return false;
	}
	
}
