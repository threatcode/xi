package edu.dto.application;

public class CollegeEligibilityDTO {

	public CollegeEligibilityDTO(String eiin,float minimum_gpa,float own_gpa,float sq_gpa,String sq_yn,int available_seat){
		
		this.eiin=eiin;
		this.minimum_gpa=minimum_gpa;
		this.own_gpa=own_gpa;
		this.sq_gpa=sq_gpa;
		this.sq_yn=sq_yn;
		this.available_seat=available_seat;
	}
	
	private String eiin;
	private String shift_id;
	private String version_id;
	private String group_id;
	private String gender_id;
	private float minimum_gpa;
	private float own_gpa;
	private float sq_gpa;
	private String sq_yn;
	private int available_seat;
	
	public String getEiin() {
		return eiin;
	}
	public void setEiin(String eiin) {
		this.eiin = eiin;
	}
	public String getShift_id() {
		return shift_id;
	}
	public void setShift_id(String shiftId) {
		shift_id = shiftId;
	}
	public String getVersion_id() {
		return version_id;
	}
	public void setVersion_id(String versionId) {
		version_id = versionId;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String groupId) {
		group_id = groupId;
	}
	public String getGender_id() {
		return gender_id;
	}
	public void setGender_id(String genderId) {
		gender_id = genderId;
	}
	public float getMinimum_gpa() {
		return minimum_gpa;
	}
	public void setMinimum_gpa(float minimumGpa) {
		minimum_gpa = minimumGpa;
	}
	public float getOwn_gpa() {
		return own_gpa;
	}
	public void setOwn_gpa(float ownGpa) {
		own_gpa = ownGpa;
	}
	public float getSq_gpa() {
		return sq_gpa;
	}
	public void setSq_gpa(float sqGpa) {
		sq_gpa = sqGpa;
	}
	public String getSq_yn() {
		return sq_yn;
	}
	public void setSq_yn(String sqYn) {
		sq_yn = sqYn;
	}
	public int getAvailable_seat() {
		return available_seat;
	}
	public void setAvailable_seat(int availableSeat) {
		available_seat = availableSeat;
	}
	
	
}
