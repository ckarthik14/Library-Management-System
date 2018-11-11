package com.beans;

import java.sql.Date;

public class StudentBean {
	private String name,sid,password;
	private Date dob;
	
	public StudentBean() {}
	
	public StudentBean(String name, String sid, String password, Date dob) {
		
		this.name = name;
		this.sid = sid;
		this.password = password;
		this.dob = dob;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
