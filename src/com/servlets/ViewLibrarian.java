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

import com.beans.LibrarianBean;
import com.dao.LibrarianDao;
@WebServlet("/ViewLibrarian")
public class ViewLibrarian extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Librarian</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("admin") == "true")
		{
		
			request.getRequestDispatcher("navadmin.html").include(request, response);
			out.println("<div class='container'>");
			
			List<LibrarianBean> list=LibrarianDao.view();
			
			
			out.println("<table class='table table-bordered table-striped'>");
			out.println("<tr><th>Name</th><th>Email</th><th>Password</th><th>Mobile</th><th>Edit</th><th>Delete</th></tr>");
			for(LibrarianBean bean:list){
				out.println("<tr><td>"+bean.getName()+"</td><td>"+bean.getLid()+"</td><td>"+bean.getPassword()+"</td><td>"+bean.getMobile()+"</td><td><a href='EditLibrarianForm?lid="+bean.getLid()+"'>Edit</a></td><td><a href='DeleteLibrarian?lid="+bean.getLid()+"'>Delete</a></td></tr>");
			}
			out.println("</table>");
			
			out.println("</div>");
		}
		
		else
		{
			new com.authfunctions.AdminLogin(request,response,out);
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
		
	}
}
