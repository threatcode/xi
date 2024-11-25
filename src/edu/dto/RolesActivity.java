package edu.dto;

import java.io.Serializable;
import java.util.List;


public class RolesActivity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String activityName;
	private String jspPage;
	private String roleId;
	private Integer serialno;
	private String mainGroup;
	private Integer mainGroupID;
	private String css_class;
	public String getCss_class() {
		return css_class;
	}
	public void setCss_class(String cssClass) {
		css_class = cssClass;
	}
	private List<RolesActivity> subList;
	
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getJspPage() {
		return jspPage;
	}
	public void setJspPage(String jspPage) {
		this.jspPage = jspPage;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public List<RolesActivity> getSubList() {
		return subList;
	}
	public void setSubList(List<RolesActivity> subList) {
		this.subList = subList;
	}
	public Integer getSerialno() {
		return serialno;
	}
	public void setSerialno(Integer serialno) {
		this.serialno = serialno;
	}
	public String getMainGroup() {
		return mainGroup;
	}
	public void setMainGroup(String mainGroup) {
		this.mainGroup = mainGroup;
	}
	public Integer getMainGroupID() {
		return mainGroupID;
	}
	public void setMainGroupID(Integer mainGroupID) {
		this.mainGroupID = mainGroupID;
	}
	
	

}