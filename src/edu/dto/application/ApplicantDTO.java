package edu.dto.application;

import java.io.Serializable;

public class ApplicantDTO implements Serializable {

	private String application_id="";
	private SscInfoDTO ssc_info;
	private ApplicationInfoDTO application_info;
	
	private String student_name;
	private String eiin;
	private String merit;
	private String merit_type;
	
	private String applied="N";
	private String appliedsms="N";
	
	private String msg = "";
	
	private String collegeName = "";
	private String shift = "";
	private String version = "";
	private String group = "";
	
	private String mpos="";
	private String apos="";
	private String scode="";
	private String no_of_update="";
	private String is_active;
	
	
	private String contactno;
	
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMerit_type() {
		return merit_type;
	}
	public void setMerit_type(String merit_type) {
		this.merit_type = merit_type;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getEiin() {
		return eiin;
	}
	public void setEiin(String eiin) {
		this.eiin = eiin;
	}
	public String getMerit() {
		return merit;
	}
	public void setMerit(String merit) {
		this.merit = merit;
	}
	public String getApplication_id() {
		return application_id;
	}
	public void setApplication_id(String applicationId) {
		application_id = applicationId;
	}
	public SscInfoDTO getSsc_info() {
		return ssc_info;
	}
	public void setSsc_info(SscInfoDTO sscInfo) {
		ssc_info = sscInfo;
	}
	public ApplicationInfoDTO getApplication_info() {
		return application_info;
	}
	public void setApplication_info(ApplicationInfoDTO applicationInfo) {
		application_info = applicationInfo;
	}
	public String getApplied() {
		return applied;
	}
	public void setApplied(String applied) {
		this.applied = applied;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getAppliedsms() {
		return appliedsms;
	}
	public void setAppliedsms(String appliedsms) {
		this.appliedsms = appliedsms;
	}
	public String getMpos() {
		return mpos;
	}
	public void setMpos(String mpos) {
		this.mpos = mpos;
	}
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public String getApos() {
		return apos;
	}
	public void setApos(String apos) {
		this.apos = apos;
	}
	
	public String getNo_of_update() {
		return no_of_update;
	}
	public void setNo_of_update(String no_of_update) {
		this.no_of_update = no_of_update;
	}
	

	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	
}
