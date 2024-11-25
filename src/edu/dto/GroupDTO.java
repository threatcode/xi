package edu.dto;

public class GroupDTO {

	private String group_id;
	private String group_name;
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String groupId) {
		group_id = groupId;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String groupName) {
		group_name = groupName;
	}
	
	private String ssc_group_id;
	private String hsc_group_id;
	
	public String getSsc_group_id() {
		return ssc_group_id;
	}
	public void setSsc_group_id(String sscGroupId) {
		ssc_group_id = sscGroupId;
	}
	public String getHsc_group_id() {
		return hsc_group_id;
	}
	public void setHsc_group_id(String hscGroupId) {
		hsc_group_id = hscGroupId;
	}
	
	
	
}
