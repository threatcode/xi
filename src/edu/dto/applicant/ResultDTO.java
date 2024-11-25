package edu.dto.applicant;

import java.io.Serializable;

public class ResultDTO implements Serializable {

	private String application_id;	
	private String eiin;
	private String college_name;
	private String shift_id;
	private String shift_name;
	private String version_id;
	private String version_name;
	private String group_id;
	private String group_name;
	private String quota_type;
	private String merit_type;
	private String priority_order;
	private String tmp_status;
	private String auto_migration;
	private String approvedstatus;
	private String paid;
	private String collegereceive;
	
	
	public String getApprovedstatus() {
		return approvedstatus;
	}
	public void setApprovedstatus(String approvedstatus) {
		this.approvedstatus = approvedstatus;
	}
	public String getApplication_id() {
		return application_id;
	}
	public void setApplication_id(String applicationId) {
		application_id = applicationId;
	}
	public String getEiin() {
		return eiin;
	}
	public void setEiin(String eiin) {
		this.eiin = eiin;
	}
	public String getCollege_name() {
		return college_name;
	}
	public void setCollege_name(String collegeName) {
		college_name = collegeName;
	}
	public String getShift_id() {
		return shift_id;
	}
	public void setShift_id(String shiftId) {
		shift_id = shiftId;
	}
	public String getShift_name() {
		return shift_name;
	}
	public void setShift_name(String shiftName) {
		shift_name = shiftName;
	}
	public String getVersion_id() {
		return version_id;
	}
	public void setVersion_id(String versionId) {
		version_id = versionId;
	}
	public String getVersion_name() {
		return version_name;
	}
	public void setVersion_name(String versionName) {
		version_name = versionName;
	}
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
	public String getQuota_type() {
		return quota_type;
	}
	public void setQuota_type(String quotaType) {
		quota_type = quotaType;
	}
	public String getMerit_type() {
		return merit_type;
	}
	public void setMerit_type(String meritType) {
		merit_type = meritType;
	}
	public String getTmp_status() {
		return tmp_status;
	}
	public void setTmp_status(String tmpStatus) {
		tmp_status = tmpStatus;
	}
	public String getPriority_order() {
		return priority_order;
	}
	public void setPriority_order(String priorityOrder) {
		priority_order = priorityOrder;
	}
	public String getAuto_migration() {
		return auto_migration;
	}
	public void setAuto_migration(String autoMigration) {
		auto_migration = autoMigration;
	}
	public String getPaid() {
		return paid;
	}
	public void setPaid(String paid) {
		this.paid = paid;
	}
	public String getCollegereceive() {
		return collegereceive;
	}
	public void setCollegereceive(String collegereceive) {
		this.collegereceive = collegereceive;
	}	
		
}
