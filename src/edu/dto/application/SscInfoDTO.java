package edu.dto.application;

public class SscInfoDTO {
	private String roll;
	private String board_id;
	private String board_name;
	private String passing_year;
	private String reg_no;
	private String group_id;
	private String group_name;
	private String student_name;
	private String father_name;
	private String mother_name;
	private String birth_date;
	private String gender_id;
	private String gender_name;
	private float gpa;
	private float gpa_exc4th;
	private String eiin;
	
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String boardId) {
		board_id = boardId;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String boardName) {
		board_name = boardName;
	}
	public String getPassing_year() {
		return passing_year;
	}
	public void setPassing_year(String passingYear) {
		passing_year = passingYear;
	}
	public String getReg_no() {
		return reg_no;
	}
	public void setReg_no(String regNo) {
		reg_no = regNo;
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
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String studentName) {
		student_name = studentName;
	}
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String fatherName) {
		father_name = fatherName;
	}
	public String getMother_name() {
		return mother_name;
	}
	public void setMother_name(String motherName) {
		mother_name = motherName;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birthDate) {
		birth_date = birthDate;
	}
	
	public String getGender_id() {
		return gender_id;
	}
	public void setGender_id(String genderId) {
		gender_id = genderId;
	}
	public String getGender_name() {
		return gender_name;
	}
	public void setGender_name(String genderName) {
		gender_name = genderName;
	}
	public float getGpa() {
		return gpa;
	}
	public void setGpa(float gpa) {
		this.gpa = gpa;
	}
	public float getGpa_exc4th() {
		return gpa_exc4th;
	}
	public void setGpa_exc4th(float gpaExc4th) {
		gpa_exc4th = gpaExc4th;
	}
	public String getEiin() {
		return eiin;
	}
	public void setEiin(String eiin) {
		if(eiin == null){
			this.eiin = "";
		} else {
			this.eiin = eiin;
		}
	}
	
}
