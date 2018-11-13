package com.servlets;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.BookBean;
import com.dao.BookDao;

@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add Book Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navlibrarian.html").include(request, response);
		
		out.println("<div class='container'>");
		String isbn=request.getParameter("ISBN");
		String name=request.getParameter("name");
		String edition=request.getParameter("edition");
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		int pid=Integer.parseInt(request.getParameter("publisher"));
		int cid=Integer.parseInt(request.getParameter("category"));
		System.out.println(isbn + name + edition + quantity + pid + cid);
		
		BookBean bean=new BookBean(isbn,name,edition,quantity,pid,cid);
		int i=BookDao.save(bean);
		if(i==0){
			out.println("<h3>Book saved successfully</h3>");
		}
		else if(i==2) {
			out.println("<h3>Book with ISBN already exists</h3>");
		}
		request.getRequestDispatcher("addbookform.html").include(request, response);
		out.println("</div>");
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
