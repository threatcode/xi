package edu.dto.application;

public class ResponseDTO {
	private String status;
	private String message;
	private String application_id;
	private String password;
	private String payment_status;
	private SscInfoDTO sscInfo = new SscInfoDTO();
	private ApplicationInfoDTO application_info = new ApplicationInfoDTO();
	
	private String total_seat;
	private String available_seat;
	private String scode;
	private String sms="no";
	private String mobile;
	
	private String roll;
	private String reg;
	private String board;
	private String year;
	private String contactno;
	private String organization;
	private String trxid;
	private String paytime;
	private String paymobile;
	
	private String nid;

	
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getTrxid() {
		return trxid;
	}
	public void setTrxid(String trxid) {
		this.trxid = trxid;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	public String getPaymobile() {
		return paymobile;
	}
	public void setPaymobile(String paymobile) {
		this.paymobile = paymobile;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSms() {
		return sms;
	}
	public void setSms(String sms) {
		this.sms = sms;
	}
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public ApplicationInfoDTO getApplication_info() {
		return application_info;
	}
	public void setApplication_info(ApplicationInfoDTO application_info) {
		this.application_info = application_info;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getApplication_id() {
		return application_id;
	}
	public void setApplication_id(String applicationId) {
		application_id = applicationId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public SscInfoDTO getSscInfo() {
		return sscInfo;
	}
	public void setSscInfo(SscInfoDTO sscInfo) {
		this.sscInfo = sscInfo;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String paymentStatus) {
		payment_status = paymentStatus;
	}
	public String getTotal_seat() {
		return total_seat;
	}
	public void setTotal_seat(String total_seat) {
		this.total_seat = total_seat;
	}
	public String getAvailable_seat() {
		return available_seat;
	}
	public void setAvailable_seat(String available_seat) {
		this.available_seat = available_seat;
	}
	
	
}
