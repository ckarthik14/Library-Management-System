package com.beans;

public class LibrarianBean {
	private int id;
	private String name,lid,password;
	
	public LibrarianBean() {}
	
	public LibrarianBean(String name, String lid, String password) {
		
		this.name = name;
		this.lid = lid;
		this.password = password;
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

}
