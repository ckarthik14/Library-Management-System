package com.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/IssueBookForm")
public class IssueBookForm extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Issue Book Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		

		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			
			out.println("<div class='container'>");
			request.getRequestDispatcher("issuebookform.html").include(request, response);
			out.println("</div>");
		}
		
		else
		{
			request.getRequestDispatcher("navhome.html").include(request, response);
			out.println("<div class='container'>");
			request.getRequestDispatcher("librarianloginform.html").include(request, response);
			out.println("</div>");
				
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
