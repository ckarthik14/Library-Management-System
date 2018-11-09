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

import com.dao.LibrarianDao;
@WebServlet("/LibrarianLoginHome")
public class LibrarianLoginHome extends HttpServlet {
	
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
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null) {
		
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			request.getRequestDispatcher("librarianloginhome.html").include(request, response);
			
		}
		
		else
		{
			new com.authfunctions.LibraryLogin(request, response, out);
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		
		out.close();
		
	}

}

