package com.fooding.payment.db;

import java.security.Timestamp;

public class MemberDTO {
	private int member_id;
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private java.sql.Timestamp regdate;
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.sql.Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(java.sql.Timestamp regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "MemberDTO [member_id=" + member_id + ", id=" + id + ", pw=" + pw + ", name=" + name + ", phone=" + phone
				+ ", email=" + email + ", regdate=" + regdate + "]";
	}
	
	
}
