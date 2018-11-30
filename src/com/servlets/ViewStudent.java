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

@WebServlet("/ViewStudent")
public class ViewStudent extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Student</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
		
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			out.println("<div class='container'>");
			
			List<StudentBean> list=StudentDao.view();
			
			
			out.println("<table class='table table-bordered table-striped'>");
			out.println("<tr><th>Name</th><th>USN</th><th>DOB</th><th>Edit</th><th>Delete</th></tr>");
			for(StudentBean bean:list){
				out.println("<tr><td>"+bean.getName()+"</td><td>"+bean.getSid()+"</td><td>"+bean.getDob()+"</td><td><a href='EditStudentForm?sid="+bean.getSid()+"'>Edit</a></td><td><a href='DeleteStudent?sid="+bean.getSid()+"'>Delete</a></td></tr>");
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
