package com.authfunctions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLogin {
	
	public AdminLogin(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws ServletException, IOException {

		request.getRequestDispatcher("navhome.html").include(request, response);
		out.println("<div class='container'>");
		request.getRequestDispatcher("adminloginform.html").include(request, response);
		out.println("</div>");
	}

}
