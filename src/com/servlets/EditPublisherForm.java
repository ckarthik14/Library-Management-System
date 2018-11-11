package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.PublisherBean;
import com.dao.PublisherDao;

@WebServlet("/EditPublisherForm")
public class EditPublisherForm extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Edit Publisher Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
		
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			out.println("<div class='container'>");
			Integer lid=Integer.parseInt(request.getParameter("pid"));
			
			PublisherBean bean=PublisherDao.viewById(lid);
			
			out.print("<form action='EditPublisher' method='post' style='width:300px'>");
			out.print("<div class='form-group'>");
			out.print("<input type='hidden' name='pid' value='"+bean.getPid()+"'/>");
			out.print("<label for='name'>Name</label>");
			out.print("<input type='text' class='form-control' value='"+bean.getName()+"' name='name' id='name' placeholder='Name'/>");
			out.print("</div>");
			out.print("<div class='form-group'>");
			out.print("<label for='phone'>Phone Number</label>");
			out.print("<input type='number' class='form-control' value='"+bean.getPhone()+"' name='phone' id='phone' placeholder='Phone number'/>");
			out.print("</div>");
			out.print("<div class='form-group'>");
			out.print("<label for='address'>Address</label>");
			out.print("<input type='text' class='form-control' value='"+bean.getAddress()+"' name='address' id='address' placeholder='Address'/>");
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