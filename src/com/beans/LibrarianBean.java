package com.beans;

public class LibrarianBean {
private int id;
private String name,lid,password;
private String mobile;

public LibrarianBean() {}

public LibrarianBean(String name, String lid, String password, String mobile) {
	
	this.name = name;
	this.lid = lid;
	this.password = password;
	this.mobile = mobile;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getLid() {
	return lid;
}
public void setLid(String lid) {
	this.lid = lid;
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

}
