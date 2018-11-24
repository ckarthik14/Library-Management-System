package com.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.beans.LibrarianBean;
import com.beans.StudentBean;
import com.dao.LibrarianDao;
import com.dao.StudentDao;

@WebServlet("/StudentLogin")
public class StudentLogin extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Student Section</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='index.css'/>");
		out.println("</head>");
		
		String usn=request.getParameter("sid");
		String password=request.getParameter("password");
		
		if(StudentDao.authenticate(usn, password))
		{
			HttpSession session=request.getSession();
			session.setAttribute("studentusn",usn);
			
			request.getRequestDispatcher("navstudentname.html").include(request, response);

			String studusn = (String) session.getAttribute("studentusn");
			
			StudentBean bean = StudentDao.viewById(studusn);
			
			out.print("<li><a href='#'>" + bean.getName() + "</a></li></ul>");
			
			request.getRequestDispatcher("navfooter.html").include(request, response);
			
			request.getRequestDispatcher("studentcarousel.html").include(request, response);
			
		}
		
		else
		{
			request.getRequestDispatcher("navhome.html").include(request, response);
			out.println("<div class='container'>");
			out.println("<h3 class='text_format'>Username or password error</h3>");
			request.getRequestDispatcher("studentloginform.html").include(request, response);
			out.println("</div>");
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
		out.println("<title>student Section</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='index.css'/>");
		out.println("</head>");
		
		System.out.println(request.getSession());
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("studentusn") != null) {
		
		request.getRequestDispatcher("navstudentname.html").include(request, response);
		String studusn = (String) session.getAttribute("studentusn");
		
		StudentBean bean = StudentDao.viewById(studusn);
		
		out.print("<li><a href='#'>" + bean.getName() + "</a></li></ul>");
		
		request.getRequestDispatcher("navfooter.html").include(request, response);
		request.getRequestDispatcher("studentcarousel.html").include(request, response);
		
		}
		
		else {
		
		request.getRequestDispatcher("navhome.html").include(request, response);
		out.println("<div class='container'>");
		request.getRequestDispatcher("studentloginform.html").include(request, response);
		out.println("</div>");
		
		}
		
	}

}
