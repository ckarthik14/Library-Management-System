package com.beans;

import java.sql.Date;

public class IssueBookBean {
private String isbn, sid, lid;
private Date doi, dor;
private Integer issueid;

public IssueBookBean() {}

public IssueBookBean(String isbn, String sid, String lid) {
	this.isbn = isbn;
	this.sid = sid;
	this.lid = lid;
}
public IssueBookBean(String isbn, String sid, String lid, Date doi, Date dor) {
	this.isbn = isbn;
	this.sid = sid;
	this.lid = lid;
	this.doi = doi;
	this.dor = dor;
}
public IssueBookBean(String isbn, String sid, String lid, Date doi, Date dor, Integer issueid) {
	this.isbn = isbn;
	this.sid = sid;
	this.lid = lid;
	this.doi = doi;
	this.dor = dor;
	this.issueid = issueid;
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

public String getLid() {
	return lid;
}
public void setLid(String lid) {
	this.lid = lid;
}

public Date getDoi() {
	return doi;
}
public void setDoi(Date doi) {
	this.doi = doi;
}

public Date getDor() {
	return dor;
}
public void setDor(Date dor) {
	this.dor = dor;
}

public Integer getIssueid() {
	return issueid;
}
public void setIssueid(Integer issueid) {
	this.issueid = issueid;
}

}