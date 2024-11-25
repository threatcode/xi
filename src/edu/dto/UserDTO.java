package edu.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
	
	private String userid;
	
	private String password;
	private String roleid;
	private String is_active;
	private String mobile;
	private String name;
	private String photoUrl;
	private String ssc_roll;
	private String ssc_board;
	private String ssc_year;
	private String ssc_reg;
	private String scode;
	
	
	
	private String remote_address;
	
	public String getRemote_address() {
		return remote_address;
	}
	public void setRemote_address(String remote_address) {
		this.remote_address = remote_address;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String isActive) {
		is_active = isActive;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getSsc_roll() {
		return ssc_roll;
	}
	public void setSsc_roll(String ssc_roll) {
		this.ssc_roll = ssc_roll;
	}
	public String getSsc_board() {
		return ssc_board;
	}
	public void setSsc_board(String ssc_board) {
		this.ssc_board = ssc_board;
	}
	public String getSsc_year() {
		return ssc_year;
	}
	public void setSsc_year(String ssc_year) {
		this.ssc_year = ssc_year;
	}
	public String getSsc_reg() {
		return ssc_reg;
	}
	public void setSsc_reg(String ssc_reg) {
		this.ssc_reg = ssc_reg;
	}

	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
}
