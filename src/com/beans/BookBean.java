package com.beans;

public class BookBean {
	private String isbn, title, edition;
	private int quantity, issued, pid;

	public BookBean(String isbn, String title, String author, String publisher, int quantity) {
		this.isbn = isbn;
		this.title = title;
		this.quantity = quantity;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getIssued() {
		return issued;
	}
	public void setIssued(int issued) {
		this.issued = issued;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}

}
