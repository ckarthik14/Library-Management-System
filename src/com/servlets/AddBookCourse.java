package com.servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.AuthorBean;
import com.beans.BookBean;
import com.beans.WrittenBean;
import com.dao.AuthorDao;
import com.dao.BookDao;
import com.dao.WrittenDao;
import com.dao.BookDao;
import com.beans.BookBean;
import com.beans.PrescribesBean;
import com.dao.PrescribesDao;
import com.beans.CourseBean;
import com.dao.CourseDao;

@WebServlet("/AddBookCourse")
public class AddBookCourse extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add Books for Course Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navlibrarian.html").include(request, response);
		
		out.println("<div class='container'>");

		String isbn=request.getParameter("isbn");
		Integer cid=Integer.parseInt(request.getParameter("cid"));
		
		PrescribesBean bean=new PrescribesBean(isbn,cid);
		int i=PrescribesDao.save(bean);
		System.out.println(i);
		
		if(i==1){
			out.println("<h3>Book for Course saved successfully</h3>");
		}
		else if(i==0) {
			out.println("<h3>Book for Course already exists</h3>");
		}
		
		List<BookBean> listBook=BookDao.view();
		List<CourseBean> listCourse=CourseDao.view();
		
		request.getRequestDispatcher("addbookcourseform.html").include(request, response);
		out.println("<div class=\"form-group\">"
				+ "<label for=\"book1\">Book</label>");
				
				out.println("<select name=\"isbn\">");
				for(BookBean bean1:listBook){
					out.println("<option value=\""+bean1.getIsbn()+"\">"+bean1.getTitle()+"</option>");
				}
				out.println("</select>");
				out.println("</div>");
				
		out.println("<div class=\"form-group\">"
				+ "<label for=\"course1\">Course</label>");
				
				out.println("<select name=\"cid\">");
				for(CourseBean bean2:listCourse){
					out.println("<option value=\""+bean2.getCid()+"\">"+bean2.getTitle()+"</option>");
				}
				out.println("</select>");
				out.println("</div>");
				
				out.println("<button type=\"submit\" class=\"btn btn-primary\">Save</button>"
				+ "</form>");
		
		out.println("</div>");
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}