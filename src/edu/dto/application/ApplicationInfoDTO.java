package edu.dto.application;

public class ApplicationInfoDTO {

	private String mobile_number;
	private String confirm_mobile_number;
	private String quota_yn;
	private String quota_ff;
	private String quota_eq;
	private String quota_bksp;
	private String quota_expatriate;
	private String editable;
	private String payment_status;
	private String nid_number;
	private String nidholder;
	
	

	public String getNid_number() {
		return nid_number;
	}
	public void setNid_number(String nid_number) {
		this.nid_number = nid_number;
	}
	public String getNidholder() {
		return nidholder;
	}
	public void setNidholder(String nidholder) {
		this.nidholder = nidholder;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobileNumber) {
		mobile_number = mobileNumber;
	}
	public String getConfirm_mobile_number() {
		return confirm_mobile_number;
	}
	public void setConfirm_mobile_number(String confirmMobileNumber) {
		confirm_mobile_number = confirmMobileNumber;
	}
	public String getQuota_yn() {
		return quota_yn;
	}
	public void setQuota_yn(String quotaYn) {
		quota_yn = quotaYn;
	}
	public String getQuota_ff() {
		return quota_ff;
	}
	public String getQuota_eq() {
		return quota_eq;
	}
	public void setQuota_eq(String quotaEq) {
		quota_eq = quotaEq;
	}
	public void setQuota_ff(String quotaFf) {
		quota_ff = quotaFf;
	}
	public String getEditable() {
		return editable;
	}
	public void setEditable(String editable) {
		this.editable = editable;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String paymentStatus) {
		payment_status = paymentStatus;
	}
	public String getQuota_bksp() {
		return quota_bksp;
	}
	public void setQuota_bksp(String quota_bksp) {
		this.quota_bksp = quota_bksp;
	}
	public String getQuota_expatriate() {
		return quota_expatriate;
	}
	public void setQuota_expatriate(String quota_expatriate) {
		this.quota_expatriate = quota_expatriate;
	}
	
}
