package com.servlets;


import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.IssueBookBean;
import com.dao.BookDao;
import com.dao.StudentDao;

@WebServlet("/ReturnBook")
public class ReturnBook extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Return Book Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navlibrarian.html").include(request, response);
		
		out.println("<div class='container'>");
		String isbn=request.getParameter("isbn");
		String sid=request.getParameter("usn");
		
		boolean i=BookDao.checkIsbnissue(isbn);
		if(i == false) {
			out.println("<h3>Invalid ISBN.</h3><p>Please check again.</p>");
		}
		else {
			i=StudentDao.checkUsnissue(sid);
			if(i == false) {
				out.println("<h3>Invalid Student USN.</h3><p>Please check again.</p>");
			}
			else {
				int j=BookDao.returnBook(isbn,sid);
				if(j>0){
					out.println("<h3>Book returned successfully</h3>");
				}else{
					out.println("<h3>Sorry, unable to return book.</h3><p>Please try again.</p>");
				}
			}
		}
		
		out.println("</div>");
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
