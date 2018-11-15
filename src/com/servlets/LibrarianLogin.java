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
import com.dao.LibrarianDao;

import sun.util.logging.resources.logging_de;
@WebServlet("/LibrarianLogin")
public class LibrarianLogin extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Librarian Section</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='index.css'/>");
		out.println("</head>");
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		if(LibrarianDao.authenticate(email, password))
		{
			HttpSession session=request.getSession();
			session.setAttribute("librarianemail",email);
			
			request.getRequestDispatcher("navlibrarianname.html").include(request, response);
			
			String lid = (String) session.getAttribute("librarianemail");
			
			LibrarianBean bean = LibrarianDao.viewById(lid);
			
			out.print("<li><a href='#'>" + bean.getName() + "</a></li></ul>");
			
			request.getRequestDispatcher("navfooter.html").include(request, response);
			request.getRequestDispatcher("librariancarousel.html").include(request, response);
			
		}
		
		else
		{
			request.getRequestDispatcher("navhome.html").include(request, response);
			out.println("<div class='container'>");
			out.println("<h3 class='text_format'>Username or password error</h3>");
			request.getRequestDispatcher("librarianloginform.html").include(request, response);
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
		out.println("<title>Librarian Section</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='index.css'/>");
		out.println("</head>");
		
		System.out.println(request.getSession());
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null) {
		
		request.getRequestDispatcher("navlibrarianname.html").include(request, response);
		
		String lid = (String) session.getAttribute("librarianemail");
		
		LibrarianBean bean = LibrarianDao.viewById(lid);
		
		out.print("<li><a href='#'>" + bean.getName() + "</a></li></ul>");
		
		request.getRequestDispatcher("navfooter.html").include(request, response);
		request.getRequestDispatcher("librariancarousel.html").include(request, response);
		
		}
		
		else {
		
		request.getRequestDispatcher("navhome.html").include(request, response);
		out.println("<div class='container'>");
		request.getRequestDispatcher("librarianloginform.html").include(request, response);
		out.println("</div>");
		
		}
		
	}

}
