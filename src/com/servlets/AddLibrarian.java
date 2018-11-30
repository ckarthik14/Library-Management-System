package com.servlets;


import java.io.IOException;

import java.io.PrintWriter;

import javax.net.ssl.SSLEngineResult.Status;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.LibrarianBean;
import com.dao.LibrarianDao;

import com.password.SHA256;

@WebServlet("/AddLibrarian")
public class AddLibrarian extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add Librarian</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		String passhash = SHA256.getSHA(password);
		
		LibrarianBean bean=new LibrarianBean(name, email, passhash);
		int status = LibrarianDao.save(bean);
		
		if(status == 2)
		{
			out.print("<h4>Librarian already exists</h4>");
		}
		else
		{
			out.print("<h4>Librarian added successfully</h4>");
		}
		request.getRequestDispatcher("addlibrarianform.html").include(request, response);
		
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
