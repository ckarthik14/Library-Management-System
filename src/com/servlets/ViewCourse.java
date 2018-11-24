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

import com.beans.CourseBean;
import com.dao.CourseDao;
@WebServlet("/ViewCourse")
public class ViewCourse extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			
			List<CourseBean> list=CourseDao.view();
			
			
			out.println("<table class='table table-bordered table-striped'>");
			out.println("<tr><th>Title</th><th>Department</th><th>Edit</th><th>Delete</th></tr>");
			for(CourseBean bean:list){
				out.println("<tr><td>"+bean.getTitle()+"</td><td>"+bean.getDepartment()+"</td><td><a href='EditCourseForm?cid="+bean.getCid()+"'>Edit</a></td><td><a href='DeleteCourse?cid="+bean.getCid()+"'>Delete</a></td></tr>");
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
			
			List<CourseBean> list=CourseDao.view();
			
			
			out.println("<table class='table table-bordered table-striped'>");
			out.println("<tr><th>Title</th><th>Department</th><th>Edit</th><th>Delete</th></tr>");
			for(CourseBean bean:list){
				out.println("<tr><td>"+bean.getTitle()+"</td><td>"+bean.getDepartment()+"</td><td><a href='EditCourseForm?cid="+bean.getCid()+"'>Edit</a></td><td><a href='DeleteCourse?cid="+bean.getCid()+"'>Delete</a></td></tr>");
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
