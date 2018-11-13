package com.beans;

public class BookBean {
	private String isbn, title, edition;
	private int quantity, issued, pid, cid;

	public BookBean(String isbn, String title, String edition, int quantity, int pid, int cid)
	{
		this.isbn = isbn;
		this.title = title;
		this.edition = edition;
		this.pid = pid;
		this.cid = cid;
		this.quantity = quantity;
	}
	public BookBean()
	{
		
	}
	public BookBean(String isbn, String title, String edition, int quantity, int issued, int pid, int cid)
	{
		this.isbn = isbn;
		this.title = title;
		this.edition = edition;
		this.issued = issued;
		this.pid = pid;
		this.cid = cid;
		this.quantity = quantity;
	}
	public String getIsbn()
	{
		return isbn;
	}
	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	public int getIssued()
	{
		return issued;
	}
	public void setIssued(int issued)
	{
		this.issued = issued;
	}
	public String getEdition()
	{
		return edition;
	}
	public void setEdition(String edition)
	{
		this.edition = edition;
	}
	public int getPid()
	{
		return pid;
	}
	public void setPid(int pid)
	{
		this.pid = pid;
	}
	public int getCid()
	{
		return cid;
	}
	public void setCid(int cid)
	{
		this.cid = cid;
	}

}
