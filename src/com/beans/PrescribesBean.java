package com.beans;

public class PrescribesBean {
	private int cid;
	private String isbn;
	
	public PrescribesBean() {
		 
	}
	
	public PrescribesBean(String isbn, int cid) {
		this.setIsbn(isbn);
		this.setCid(cid);
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


}
