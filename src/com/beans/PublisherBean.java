package com.beans;

public class PublisherBean {
	private int pid, phone;
	private String name, address;
	
	public PublisherBean() {}
	
	public PublisherBean(String name, int phone, String address) {
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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