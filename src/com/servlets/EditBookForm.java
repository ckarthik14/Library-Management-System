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
import com.dao.BookDao;

import com.beans.PublisherBean;
import com.dao.PublisherDao;
import com.beans.CategoryBean;
import com.dao.CategoryDao;
@WebServlet("/EditBookForm")
public class EditBookForm extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Edit Book Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
		
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			out.println("<div class='container'>");
			String isbn=request.getParameter("isbn");
			
			BookBean bean=BookDao.viewById(isbn);
			
			out.print("<form action='EditBook' method='post' style='width:300px'>");
			out.print("<div class='form-group'>");
			out.print("<input type='hidden' name='isbn' value='"+bean.getIsbn()+"'/>");
			out.print("<label for='title'>Title</label>");
			out.print("<input type='text' class='form-control' value='"+bean.getTitle()+"' name='title' id='title' placeholder='Title'/>");
			out.print("</div>");
			out.print("<div class='form-group'>");
			out.print("<label for='Edition'>Edition</label>");
			out.print("<input type='text' class='form-control' value='"+bean.getEdition()+"' name='edition' id='edition' placeholder='Edition'/>");
			out.print("</div>");
			out.print("<div class='form-group'>");
			out.print("<label for='quantity'>Quantity</label>");
			out.print("<input type='number' class='form-control' value='"+bean.getQuantity()+"' name='quantity' id='quantity' placeholder='Quantity'/>");
			out.print("</div>");
			
			List<PublisherBean> list=PublisherDao.view();
			
			out.println("<div class=\"form-group\">"
					+ "<label for=\"pid\">Publisher</label>");
					
					out.println("<select name=\"pid\">");
					for(PublisherBean bean2:list){
						out.println("<option value=\""+bean2.getPid()+"\">"+bean2.getName()+"</option>");
					}
					out.println("</select>");
					out.println("</div>");
			
			List<CategoryBean> list2=CategoryDao.view();
					
			out.println("<div class=\"form-group\">"
					+ "<label for=\"cid\">Category</label>");
					
					out.println("<select name=\"cid\">");
					for(CategoryBean bean3:list2){
						out.println("<option value=\""+bean3.getCid()+"\">"+bean3.getName()+"</option>");
					}
					out.println("</select>");
					out.println("</div>");
			
			out.print("<button type='submit' class='btn btn-primary'>Update</button>");
			out.print("</form>");
			
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