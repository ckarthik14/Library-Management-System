package com.beans;

public class AuthorBean {
	private int aid, phone;
	private String fname, lname, address;
	
	public AuthorBean() {}
	
	public AuthorBean(String fname, int phone, String address) {
		this.fname = fname;
		this.phone = phone;
		this.address = address;
	}
	public AuthorBean(String fname, String lname, int phone, String address) {
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.address = address;
	}
	public AuthorBean(Integer aid, String fname, String lname, int phone, String address) {
		this.aid = aid;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.address = address;
	}
	
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String phone) {
		this.address = address;
	}
}