package edu.action.applicant;

public class ApplicatInfoDTO {

	private String mobile_number;
	private String confirm_mobile_number;
	private String quota_yn;
	private String quota_ff;
	private String quota_eq;
	
	
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
	
}
