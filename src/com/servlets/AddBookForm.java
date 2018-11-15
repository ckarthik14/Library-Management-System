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

import com.beans.PublisherBean;
import com.dao.PublisherDao;
import com.beans.CategoryBean;
import com.dao.CategoryDao;
import com.dao.LibrarianDao;

@WebServlet("/AddBookForm")
public class AddBookForm extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>New Arrivals</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
			
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			
			out.println("<div class='container'>");
			
			List<PublisherBean> list=PublisherDao.view();
			
			request.getRequestDispatcher("addbookform.html").include(request, response);
			
			out.println("<br /><div class='form-group'>"
			+ "<label for='publisher1'>Publisher</label>");
			
			out.println("<select class='form-control' name='publisher'>");
			out.println("<option disabled selected value=''></option>");
			for(PublisherBean bean:list){
				out.println("<option value='"+bean.getPid()+"'>"+bean.getName()+"</option>");
			}
			out.println("</select>");
			out.println("</div>");
			
			List<CategoryBean> list2=CategoryDao.view();
			
			out.println("<div class='form-group'>"
			+ "<label for='category1'>Category</label>");
			
			out.println("<select class='form-control' name='category'>");
			out.println("<option disabled selected value=''></option>");
			for(CategoryBean bean:list2){
				out.println("<option value='"+bean.getCid()+"'>"+bean.getName()+"</option>");
			}
			out.println("</select>");
			out.println("</div>");
			
			out.println("<button type='submit' class='btn btn-primary'>Save Book</button>"
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