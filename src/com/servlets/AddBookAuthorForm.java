package com.servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.BookBean;
import com.beans.CategoryBean;
import com.beans.PublisherBean;
import com.dao.BookDao;
import com.dao.CategoryDao;
import com.dao.PublisherDao;
import com.beans.AuthorBean;
import com.dao.AuthorDao;

@WebServlet("/AddBookAuthorForm")
public class AddBookAuthorForm extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add Books for Author</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
			
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			
			out.println("<div class='container'>");
			
			List<BookBean> listBook=BookDao.view();
			List<AuthorBean> listAuthor=AuthorDao.view();
			request.getRequestDispatcher("addbookauthorform.html").include(request, response);
			out.println("<div class=\"form-group\">"
					+ "<label for=\"book1\">Book</label>");
					
					out.println("<select required name=\"isbn\">");
					for(BookBean bean:listBook){
						out.println("<option value=\""+bean.getIsbn()+"\">"+bean.getTitle()+"</option>");
					}
					out.println("</select>");
					out.println("</div>");
					
			out.println("<div class=\"form-group\">"
					+ "<label for=\"author1\">Author</label>");
					
					out.println("<select required name=\"aid\">");
					for(AuthorBean bean:listAuthor){
						out.println("<option value=\""+bean.getAid()+"\">"+bean.getFname()+" "+bean.getLname()+"</option>");
					}
					out.println("</select>");
					out.println("</div>");
					
					out.println("<button type=\"submit\" class=\"btn btn-primary\">Save</button>"
					+ "</form>");
			
			out.println("</div>");
					
		}
			
		else
		{
			new com.authfunctions.LibraryLogin(request,response,out);
		}
	
		request.getRequestDispatcher("footer.html").include(request, response);
		
		out.close();
	}

}