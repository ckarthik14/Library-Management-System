package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.CourseBean;
import com.dao.CourseDao;

@WebServlet("/EditCourseForm")
public class EditCourseForm extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Edit Course Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
		
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			out.println("<div class='container'>");
			String lid=request.getParameter("cid");
			
			CourseBean bean=CourseDao.viewById(lid);
			
			out.print("<form action='EditCourse' method='post' style='width:300px'>");
			out.print("<div class='form-group'>");
			out.print("<input type='hidden' name='cid' value='"+bean.getCid()+"'/>");
			out.print("<label for='title1'>Title</label>");
			out.print("<input type='text' class='form-control' value='"+bean.getTitle()+"' name='title' id='title1' placeholder='Title'/>");
			out.print("</div>");
			out.print("<div class='form-group'>");
			out.print("<label for='department1'>Department</label>");
			out.print("<input type='department' class='form-control' value='"+bean.getDepartment()+"' name='department' id='department1' placeholder='Department'/>");
			out.print("</div>  ");
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
