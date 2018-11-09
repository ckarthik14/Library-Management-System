package com.beans;

public class CategoryBean {
	private int cid;
	private String name, description;
	
	public CategoryBean() {}
	
	public CategoryBean(int cid, String name, String description) {
		this.cid = cid;
		this.setName(name);
		this.setDescription(description);
		
	}
	
	public int getCid()
	{
		return cid;
	}
	
	public void setCid(int cid)
	{
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
