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

import com.authfunctions.*;
import com.beans.BookBean;
import com.beans.LibrarianBean;
import com.dao.BookDao;
import com.dao.LibrarianDao;
import com.dao.CategoryDao;
import com.dao.PublisherDao;

@WebServlet("/ViewBook")
public class ViewBook extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Book</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
		
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			
			out.println("<div class='container'>");
			
			List<BookBean> list=BookDao.view();
			
			out.println("<table class='table table-bordered table-striped'>");
			out.println("<tr><th>ISBN</th><th>Title</th><th>Edition</th><th>Quantity</th><th>Issued</th><th>Publisher</th><th>Category</th><th>Edit</th><th>Delete</th></tr>");
			for(BookBean bean:list){
				out.println("<tr><td>"+bean.getIsbn()+"</td><td>"+bean.getTitle()+"</td><td>"+bean.getEdition()+"</td><td>"+bean.getQuantity()+"</td><td>"+bean.getIssued()+"</td><td>"+PublisherDao.getNamebyId(bean.getPid())+"</td><td>"+CategoryDao.getNamebyId(bean.getCid())+"</td><td><a href='EditBookForm?isbn="+bean.getIsbn()+"'>Edit</a></td><td><a href='DeleteBook?callno="+bean.getIsbn()+"'>Delete</a></td></tr>");
			}
			out.println("</table>");
			
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
