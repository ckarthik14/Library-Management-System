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

import com.beans.PublisherBean;
import com.dao.PublisherDao;
@WebServlet("/ViewPublisher")
public class ViewPublisher extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Publisher</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
		
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			out.println("<div class='container'>");
			
			List<PublisherBean> list=PublisherDao.view();
			
			
			out.println("<table class='table table-bordered table-striped'>");
			out.println("<tr><th>Name</th><th>Phone Number</th><th>Address</th><th>Edit</th><th>Delete</th></tr>");
			for(PublisherBean bean:list){
				out.println("<tr><td>"+bean.getName()+"</td><td>"+bean.getPhone()+"</td><td>"+bean.getAddress()+"</td><td><a href='EditPublisherForm?pid="+bean.getPid()+"'>Edit</a></td><td><a href='DeletePublisher?pid="+bean.getPid()+"'>Delete</a></td></tr>");
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
			
			List<PublisherBean> list=PublisherDao.view();
			
			
			out.println("<table class='table table-bordered table-striped'>");
			out.println("<tr><th>Name</th><th>Phone Number</th><th>Address</th><th>Edit</th><th>Delete</th></tr>");
			for(PublisherBean bean:list){
				out.println("<tr><td>"+bean.getName()+"</td><td>"+bean.getPhone()+"</td><td>"+bean.getAddress()+"</td><td><a href='EditPublisherForm?pid="+bean.getPid()+"'>Edit</a></td><td><a href='DeletePublisher?pid="+bean.getPid()+"'>Delete</a></td></tr>");
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
