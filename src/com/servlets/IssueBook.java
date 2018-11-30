package com.servlets;


import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.BookBean;
import com.beans.IssueBookBean;
import com.dao.BookDao;
import com.dao.StudentDao;

@WebServlet("/IssueBook")
public class IssueBook extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Issue Book Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navlibrarian.html").include(request, response);
		
		out.println("<div class='container'>");
		HttpSession session = request.getSession();
		String isbn=request.getParameter("isbn");
		String sid=request.getParameter("usn");
		String lid =session.getAttribute("librarianemail").toString();
		
		boolean i=BookDao.checkIsbn(isbn);
		if(i == false) {
			out.println("<h3>Invalid ISBN.</h3><p>Please check again.</p>");
		}
		else {
			
			i=StudentDao.checkUsn(sid);
			if(i == false) {
				out.println("<h3>Invalid Student USN.</h3><p>Please check again.</p>");
			}
			else {
				i=BookDao.checkRedundancy(isbn,sid);
				if(i == true) {
					out.println("<h3>Student has already issued this book and has not returned it yet.</h3><p>Please check again.</p>");
				}
				else {
					IssueBookBean bean=new IssueBookBean(isbn,sid,lid);
					int j=BookDao.issueBook(bean);
					if(j>0){
						out.println("<h3>Book issued successfully</h3>");
					}else{
						out.println("<h3>Sorry, unable to issue book.</h3><p>We may have shortage of books. Kindly visit later.</p>");
					}
				}
			}
		}
		
		out.println("</div>");
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
