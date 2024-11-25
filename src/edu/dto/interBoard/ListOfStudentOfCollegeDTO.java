package edu.dto.interBoard;

import java.sql.Date;

public class ListOfStudentOfCollegeDTO

{	
	private String eiinCode;
	private String boardID;
	private String boardName;
	private String shiftID;
	private String shiftName;
	private String versionID;
	private String versionName;
	private String groupId;
	private String groupName;
	private String applicationNunber;
		
	private Integer totalSeat;
	private String applicationID;
	private String applicantName;
	private String fatherName;
	private String sscRollNo;
	private String sscPassingYear;
	private Date dateOfBirth;
	private String paymentType;
	private String paymentStatus;

	
	public String getEiinCode() {
		return eiinCode;
	}
	public void setEiinCode(String eiinCode) {
		this.eiinCode = eiinCode;
	}

	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getTotalSeat() {
		return totalSeat;
	}
	public void setTotalSeat(Integer totalSeat) {
		this.totalSeat = totalSeat;
	}
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getSscRollNo() {
		return sscRollNo;
	}
	public void setSscRollNo(String sscRollNo) {
		this.sscRollNo = sscRollNo;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public String getSscPassingYear() {
		return sscPassingYear;
	}
	public void setSscPassingYear(String sscPassingYear) {
		this.sscPassingYear = sscPassingYear;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getBoardID() {
		return boardID;
	}
	public void setBoardID(String boardID) {
		this.boardID = boardID;
	}
	public String getShiftID() {
		return shiftID;
	}
	public void setShiftID(String shiftID) {
		this.shiftID = shiftID;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	public String getVersionID() {
		return versionID;
	}
	public void setVersionID(String versionID) {
		this.versionID = versionID;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getApplicationNunber() {
		return applicationNunber;
	}
	public void setApplicationNunber(String applicationNunber) {
		this.applicationNunber = applicationNunber;
	}
	

}
