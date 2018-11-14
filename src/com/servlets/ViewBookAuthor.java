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
import com.dao.WrittenDao;
import com.beans.WrittenBean;
import com.dao.AuthorDao;
import com.beans.AuthorBean;

@WebServlet("/ViewBookAuthor")
public class ViewBookAuthor extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Books for Authors</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
		
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			out.println("<div class='container'>");
			
			List<WrittenBean> list=WrittenDao.view();
			
			out.println("<table class='table table-bordered table-striped'>");
			out.println("<tr><th>ISBN</th><th>Title</th><th>Author</th></tr>");
			for(WrittenBean bean:list){
				out.println("<tr><td>"+bean.getIsbn()+"</td><td>"+BookDao.getTitlebyId(bean.getIsbn())+"</td><td>"+AuthorDao.getNamebyId(bean.getAid())+"</td></tr>");
			}
			out.println("</table>");
			
			out.println("</div>");
		}
		
		else
		{
			new com.authfunctions.AdminLogin(request,response,out);
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Books for Authors</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
		
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			
			out.println("<div class='container'>");
			
			List<WrittenBean> list=WrittenDao.view();
			
			out.println("<table class='table table-bordered table-striped'>");
			out.println("<tr><th>ISBN</th><th>Title</th><th>Author</th></tr>");
			for(WrittenBean bean:list){
				out.println("<tr><td>"+bean.getIsbn()+"</td><td>"+BookDao.getTitlebyId(bean.getIsbn())+"</td><td>"+AuthorDao.getNamebyId(bean.getAid())+"</td></tr>");
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
