package com.servlets;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.BookBean;
import com.beans.CategoryBean;
import com.beans.PublisherBean;
import com.dao.BookDao;
import com.dao.CategoryDao;

import com.beans.PublisherBean;
import com.dao.PublisherDao;
import com.beans.CategoryBean;
import com.dao.CategoryDao;
import com.dao.LibrarianDao;

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
		
		BookBean bean=new BookBean(isbn,name,edition,quantity,pid,cid);
		int i=BookDao.save(bean);
		if(i==0){
			out.println("<h3>Book saved successfully</h3>");
		}
		else if(i==2) {
			out.println("<h3>Book with ISBN already exists</h3>");
		}
		List<PublisherBean> list=PublisherDao.view();
		request.getRequestDispatcher("addbookform.html").include(request, response);
		out.println("<div class=\"form-group\">"
				+ "<label for=\"publisher1\">Publisher</label>");
				
				out.println("<select name=\"publisher\">");
				for(PublisherBean bean2:list){
					out.println("<option value=\""+bean2.getPid()+"\">"+bean2.getName()+"</option>");
				}
				out.println("</select>");
				out.println("</div>");
				
				List<CategoryBean> list2=CategoryDao.view();
				
				out.println("<div class=\"form-group\">"
				+ "<label for=\"category1\">Category</label>");
				
				out.println("<select name=\"category\">");
				for(CategoryBean bean3:list2){
					out.println("<option value=\""+bean3.getCid()+"\">"+bean3.getName()+"</option>");
				}
				out.println("</select>");
				out.println("</div>");
				
				out.println("<button type=\"submit\" class=\"btn btn-primary\">Save Book</button>"
				+ "</form>");
				out.println("</div>");
		
		out.println("</div>");
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
