package com.beans;

import java.sql.Date;

public class PriorityBean {
private String isbn, sid;
private Date date;

public PriorityBean() {}

public PriorityBean(String isbn, String sid, Date date) {
	this.isbn = isbn;
	this.sid = sid;
	this.date = date;
}

public String getIsbn() {
	return isbn;
}
public void setIsbn(String isbn) {
	this.isbn = isbn;
}

public String getSid() {
	return sid;
}
public void setSid(String sid) {
	this.sid = sid;
}

public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

}