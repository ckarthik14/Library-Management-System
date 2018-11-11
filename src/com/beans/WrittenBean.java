package com.beans;

public class WrittenBean {
	private int aid;
	private String isbn;
	
	public WrittenBean() {
		 
	}
	
	public WrittenBean(String isbn, int aid) {
		this.setIsbn(isbn);
		this.setAid(aid);
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


}
