package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.CourseBean;
import com.dao.CourseDao;

@WebServlet("/AddCourse")
public class AddCourse extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add Course Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navlibrarian.html").include(request, response);
		
		out.println("<div class='container'>");
		String name=request.getParameter("title");
		String description=request.getParameter("department");
		
		CourseBean bean=new CourseBean(name, description);
		
		int i=CourseDao.save(bean);
		
		if(i==1){
			out.println("<h3>Course saved successfully</h3>");
		}
		else if(i==2) {
			out.println("<h3>Course already exists</h3>");
		}
		
		request.getRequestDispatcher("addcourseform.html").include(request, response);
		out.println("</div>");
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
	

}
