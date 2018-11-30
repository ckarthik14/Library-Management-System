package com.servlets;


import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Date;

import javax.net.ssl.SSLEngineResult.Status;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.StudentBean;
import com.dao.StudentDao;

import com.password.SHA256;

@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add Student</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navlibrarian.html").include(request, response);
		out.println("<div class='container'>");
		
		String name=request.getParameter("name");
		String email=request.getParameter("sid");
		String password=request.getParameter("password");
		Date dob=Date.valueOf(request.getParameter("dob"));
		
		String passhash = SHA256.getSHA(password);
		
		StudentBean bean=new StudentBean(name, email, passhash, dob);
		int status = StudentDao.save(bean);
		
		if(status == 2)
		{
			out.print("<h4>Student already exists</h4>");
		}
		else
		{
			out.print("<h4>Student added successfully</h4>");
		}
		request.getRequestDispatcher("addstudentform.html").include(request, response);
		
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
