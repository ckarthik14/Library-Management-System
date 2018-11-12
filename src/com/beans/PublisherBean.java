package com.beans;

public class PublisherBean {
	private int pid;
	private String name, address, phone;
	
	public PublisherBean() {}
	
	public PublisherBean(String name, String phone, String address) {
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	public PublisherBean(int pid, String name, String phone, String address) {
		this.pid = pid;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}