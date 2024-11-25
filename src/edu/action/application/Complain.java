package edu.action.application;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import edu.action.common.BaseAction;

public class Complain extends BaseAction {
	
	private String college_eiin;
	private String shift;
	private String Version;
	private String Group;
	private String correctSeatCount;
	private String correctGender;
	private String wrongExistingGroups;
	private String wrongNonExistingGroups;
	private String wrongExistingVersions;
	private String wrongNonExistingVersions;
	private String isPreferError;
	private String preferErrorDetails;
	private String isMobileError;
	private String mobileErrorDetails;
	private String isGenderError;
	private String isOtherError;
	private String otherErrorDetails;
	private String currentMobileNumber;
	
	public String getCollege_eiin() {
		return college_eiin;
	}
	public void setCollege_eiin(String college_eiin) {
		this.college_eiin = college_eiin;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getGroup() {
		return Group;
	}
	public void setGroup(String group) {
		Group = group;
	}
	public String getCorrectSeatCount() {
		return correctSeatCount;
	}
	public void setCorrectSeatCount(String correctSeatCount) {
		this.correctSeatCount = correctSeatCount;
	}
	public String getCorrectGender() {
		return correctGender;
	}
	public void setCorrectGender(String correctGender) {
		this.correctGender = correctGender;
	}
	public String getWrongExistingGroups() {
		return wrongExistingGroups;
	}
	public void setWrongExistingGroups(String wrongExistingGroups) {
		this.wrongExistingGroups = wrongExistingGroups;
	}
	public String getWrongNonExistingGroups() {
		return wrongNonExistingGroups;
	}
	public void setWrongNonExistingGroups(String wrongNonExistingGroups) {
		this.wrongNonExistingGroups = wrongNonExistingGroups;
	}
	public String getWrongExistingVersions() {
		return wrongExistingVersions;
	}
	public void setWrongExistingVersions(String wrongExistingVersions) {
		this.wrongExistingVersions = wrongExistingVersions;
	}
	public String getWrongNonExistingVersions() {
		return wrongNonExistingVersions;
	}
	public void setWrongNonExistingVersions(String wrongNonExistingVersions) {
		this.wrongNonExistingVersions = wrongNonExistingVersions;
	}

	
	
	
	public String wrongGroup()
	{
		HttpServletResponse response = ServletActionContext.getResponse();		
		try {
			if(edu.dao.application.Complain.wrongGroup(session.get("ssc_roll").toString(),session.get("ssc_board").toString(),
					session.get("ssc_year").toString(),college_eiin, wrongExistingGroups, wrongNonExistingGroups))
			{
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Yes");
				return null;
			}
			else
			{
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("No");
				return null;
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String wrongVersion()
	{
		HttpServletResponse response = ServletActionContext.getResponse();		
		try {
			if(edu.dao.application.Complain.wrongVersion(session.get("ssc_roll").toString(),session.get("ssc_board").toString(),
					session.get("ssc_year").toString(),college_eiin, Group, wrongExistingVersions, wrongNonExistingVersions))
			{
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Yes");
				return null;
			}
			else
			{
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("No");
				return null;
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String wrongSeatCount()
	{
		HttpServletResponse response = ServletActionContext.getResponse();		
		try {
			if(edu.dao.application.Complain.wrongSeatCount(session.get("ssc_roll").toString(),session.get("ssc_board").toString(),
					session.get("ssc_year").toString(),college_eiin, shift,Version,Group,correctSeatCount))
			{
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Yes");
				return null;
			}
			else
			{
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("No");
				return null;
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String wrongGender()
	{
		HttpServletResponse response = ServletActionContext.getResponse();		
		try {
			if(edu.dao.application.Complain.wrongGender(session.get("ssc_roll").toString(),session.get("ssc_board").toString(),
					session.get("ssc_year").toString(),college_eiin, shift,Version,Group,correctGender))
			{
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Yes");
				return null;
			}
			else
			{
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("No");
				return null;
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String complain_ap()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		if(currentMobileNumber==null)
		{
			try{
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("No");
				return null;							
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
		}
		else if(isPreferError==null && preferErrorDetails==null &&  isMobileError==null &&  mobileErrorDetails==null &&  
				isGenderError==null && isOtherError==null && otherErrorDetails==null)
		{
			try{
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("No");
				return null;							
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
		}
		try {
			if(edu.dao.application.Complain.complain_ap(session.get("ssc_roll").toString(), session.get("ssc_board").toString(), 
					session.get("ssc_year").toString(), isPreferError, preferErrorDetails, isMobileError, mobileErrorDetails, isGenderError, 
					isOtherError, otherErrorDetails, currentMobileNumber))
			{
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("Yes");
				return null;
			}
			else
			{
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("No");
				return null;
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getIsPreferError() {
		return isPreferError;
	}
	public void setIsPreferError(String isPreferError) {
		this.isPreferError = isPreferError;
	}
	public String getPreferErrorDetails() {
		return preferErrorDetails;
	}
	public void setPreferErrorDetails(String preferErrorDetails) {
		this.preferErrorDetails = preferErrorDetails;
	}
	public String getIsMobileError() {
		return isMobileError;
	}
	public void setIsMobileError(String isMobileError) {
		this.isMobileError = isMobileError;
	}
	public String getMobileErrorDetails() {
		return mobileErrorDetails;
	}
	public void setMobileErrorDetails(String mobileErrorDetails) {
		this.mobileErrorDetails = mobileErrorDetails;
	}
	public String getIsGenderError() {
		return isGenderError;
	}
	public void setIsGenderError(String isGenderError) {
		this.isGenderError = isGenderError;
	}
	public String getIsOtherError() {
		return isOtherError;
	}
	public void setIsOtherError(String isOtherError) {
		this.isOtherError = isOtherError;
	}
	public String getOtherErrorDetails() {
		return otherErrorDetails;
	}
	public void setOtherErrorDetails(String otherErrorDetails) {
		this.otherErrorDetails = otherErrorDetails;
	}
	public String getCurrentMobileNumber() {
		return currentMobileNumber;
	}
	public void setCurrentMobileNumber(String currentMobileNumber) {
		this.currentMobileNumber = currentMobileNumber;
	}
	

}
