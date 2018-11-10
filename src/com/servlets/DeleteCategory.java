package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CategoryDao;

@WebServlet("/DeleteCategory")
public class DeleteCategory extends HttpServlet {
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
		
		if (session.getAttribute("librarianemail") != null)
		{
			CategoryDao.delete(request.getParameter("cid"));
			response.sendRedirect("ViewCategory");
		}
			
		else
		{
			new com.authfunctions.LibraryLogin(request,response,out);
		}		
		
		out.close();
		
	}

}
