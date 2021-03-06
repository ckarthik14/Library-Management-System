package com.servlets;


import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.StudentBean;
import com.dao.StudentDao;
import com.password.SHA256;

@WebServlet("/EditStudent")
public class EditStudent extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String sid=request.getParameter("sid");
		String password=request.getParameter("password");
		
		String passhash = SHA256.getSHA(password);
		
		Date dob=Date.valueOf(request.getParameter("dob"));
		StudentBean bean = new StudentBean(name, sid, passhash, dob);
		StudentDao.update(bean);
		response.sendRedirect("ViewStudent");
	}

}
