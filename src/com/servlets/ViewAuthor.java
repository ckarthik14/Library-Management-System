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

import com.beans.AuthorBean;
import com.dao.AuthorDao;
@WebServlet("/ViewAuthor")
public class ViewAuthor extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Author</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
		
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			out.println("<div class='container'>");
			
			List<AuthorBean> list=AuthorDao.view();
			
			
			out.println("<table class='table table-bordered table-striped'>");
			out.println("<tr><th>First Name</th><th>Last Name</th><th>Phone Number</th><th>Address</th><th>Edit</th><th>Delete</th></tr>");
			for(AuthorBean bean:list){
				out.println("<tr><td>"+bean.getFname()+"</td><td>"+bean.getLname()+"</td><td>"+bean.getPhone()+"</td><td>"+bean.getAddress()+"</td><td><a href='EditAuthorForm?aid="+bean.getAid()+"'>Edit</a></td><td><a href='DeleteAuthor?aid="+bean.getAid()+"'>Delete</a></td></tr>");
			}
			out.println("</table>");
			
			out.println("</div>");
		}
		
		else
		{
			new com.authfunctions.LibraryLogin(request,response,out);
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Course</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
		
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			out.println("<div class='container'>");
			
			List<AuthorBean> list=AuthorDao.view();
			
			
			out.println("<table class='table table-bordered table-striped'>");
			out.println("<tr><th>First Name</th><th>Last Name</th><th>Phone Number</th><th>Address</th><th>Edit</th><th>Delete</th></tr>");
			for(AuthorBean bean:list){
				out.println("<tr><td>"+bean.getFname()+"</td><td>"+bean.getLname()+"</td><td>"+bean.getPhone()+"</td><td>"+bean.getAddress()+"</td><td><a href='EditAuthorForm?aid="+bean.getAid()+"'>Edit</a></td><td><a href='DeleteAuthor?aid="+bean.getAid()+"'>Delete</a></td></tr>");
			}
			out.println("</table>");
			
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
