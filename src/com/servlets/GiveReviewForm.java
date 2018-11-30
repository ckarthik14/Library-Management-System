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

import com.beans.AuthorRecommendBean;
import com.beans.IssueBookBean;
import com.dao.AuthorDao;
import com.dao.BookDao;
import com.dao.PublisherDao;
@WebServlet("/GiveReviewForm")
public class GiveReviewForm extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Give Review</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		String usn = (String) session.getAttribute("studentusn");
		
		if (usn != null)
		{
			
			request.getRequestDispatcher("navstudent.html").include(request, response);
			
			out.println("<div class='container'>");
			
			out.println("<h3>Give Review</h3>");
			out.println("<br><form action='CalculateReview' method='post' style='width:300px'>");
			out.println("<div class='form-group'><label for='book'>Select Book</label>");
			out.println("<select required class='form-control' name='bookid' id='book'/>");
			
			List<IssueBookBean> list=BookDao.viewDistinctIssuedBooksforStudent(usn);
			
			out.print("<option disabled selected value=''></option>");
			
			for(IssueBookBean bean: list) {
				out.print("<option value='" + bean.getIsbn() + "'>" + BookDao.getTitlebyId(bean.getIsbn()) + "</option>");
			}
			
			out.println("</select></div>");
			
			request.getRequestDispatcher("givereviewform.html").include(request, response);
			out.println("</div>");
					
		}
			
		else
		{
			new com.authfunctions.StudentLogin(request,response,out);
		}
	
		request.getRequestDispatcher("footer.html").include(request, response);
		
		out.close();
	}
}