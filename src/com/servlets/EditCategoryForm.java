package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.CategoryBean;
import com.dao.CategoryDao;

@WebServlet("/EditCategoryForm")
public class EditCategoryForm extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Edit Category Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
		
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			out.println("<div class='container'>");
			String lid=request.getParameter("cid");
			
			CategoryBean bean=CategoryDao.viewById(lid);
			
			out.print("<form action='EditCategory' method='post' style='width:300px'>");
			out.print("<div class='form-group'>");
			out.print("<input type='hidden' name='cid' value='"+bean.getCid()+"'/>");
			out.print("<label for='name1'>Name</label>");
			out.print("<input type='text' class='form-control' value='"+bean.getName()+"' name='name' id='name1' placeholder='Name'/>");
			out.print("</div>");
			out.print("<div class='form-group'>");
			out.print("<label for='description1'>Description</label>");
			out.print("<input type='description' class='form-control' value='"+bean.getDescription()+"' name='description' id='description1' placeholder='description'/>");
			out.print("</div>  ");
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
