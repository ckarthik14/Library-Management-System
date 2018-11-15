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
import java.sql.Date;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;

import com.beans.BookBean;
import com.beans.IssueBookBean;
import com.dao.BookDao;

@WebServlet("/ViewIssuedBook")
public class ViewIssuedBook extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Issued Books</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("librarianemail") != null)
		{
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			
			out.println("<div class='container'>");
			
			List<IssueBookBean> list=BookDao.viewIssuedBooks();
			
			out.println("<table class='table table-bordered table-striped'>");
			out.println("<tr><th>ISBN</th><th>Student USN</th><th>Librarian issued</th><th>Issued Date</th><th>Returned Date</th></tr>");
			for(IssueBookBean bean:list){
				out.println("<tr><td>"+bean.getIsbn()+"</td><td>"+bean.getSid()+"</td><td>"+bean.getLid()+"</td><td>"+bean.getDoi()+"</td><td>"+getReturnStatus(bean.getDor())+"</td></tr>");
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
	public static String getReturnStatus(Date dor)
	{
		if(dor != null) {
			 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	         String strDate = dateFormat.format(dor);  
			 return strDate; 
		}
		else {
			return "Not returned yet";
		}
	}
}