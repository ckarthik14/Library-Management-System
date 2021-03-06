package com.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.StudentBean;
import com.dao.StudentDao;
@WebServlet("/EditStudentForm")
public class EditStudentForm extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Edit Student Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
		
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			out.println("<div class='container'>");
			String sid=request.getParameter("sid");
			
			StudentBean bean=StudentDao.viewById(sid);
			
			out.print("<form action='EditStudent' method='post' style='width:300px'>");
			out.print("<div class='form-group'>");
			out.print("<input type='hidden' name='sid' value='"+bean.getSid()+"'/>");
			out.print("<label for='name1'>Name</label>");
			out.print("<input type='text' class='form-control' value='"+bean.getName()+"' name='name' id='name1' placeholder='Name'/>");
			out.print("</div>");
			out.print("<div class='form-group'>");
			out.print("<label for='dob1'>DOB</label>");
			out.print("<input type='date' class='form-control' value='"+bean.getDob()+"' name='dob' id='dob1' placeholder='DOB'/>");
			out.print("</div>");
			out.print("<div class='form-group'>");
			out.print("<label for='password1'>Password</label>");
			out.print("<input type='password' class='form-control' name='password' id='password1' placeholder='Password'/>");
			out.print("</div>");
			out.print("<button type='submit' class='btn btn-primary'>Update</button>");
			out.print("</form>");
			
			out.println("</div>");
			
		}
		
		else
		{
			new com.authfunctions.LibraryLogin(request,response,out);	
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
		
	}
}
