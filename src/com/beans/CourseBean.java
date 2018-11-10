package com.beans;

public class CourseBean {
	private int cid;
	private String title, department;
	
	public CourseBean() {
		// 
	}
	
	public CourseBean(String title, String department) {
		this.title = title;
		this.department = department;
	}
	
	public CourseBean(int cid, String title, String department) {
		this.cid = cid;
		this.title = title;
		this.department = department;
	}
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

}
