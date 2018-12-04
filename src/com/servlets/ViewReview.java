package com.servlets;
import com.beans.AuthorRecommendBean;
import com.beans.BookBean;

import com.beans.RoundOff;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.CourseBean;
import com.beans.IssueBookBean;
import com.dao.AuthorDao;
import com.dao.BookDao;
import com.dao.CourseDao;
import com.dao.DBMongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

@WebServlet("/ViewReview")
public class ViewReview extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Review</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("studentusn") != null)
		{
			DB database = DBMongo.getDB();
			DBCollection collection = database.getCollection("review");
			String isbn = request.getParameter("bookid");
			DBCursor cursor = collection.find();
			request.getRequestDispatcher("navstudent.html").include(request, response);
			out.println("<h3 align='center'>" + BookDao.getTitlebyId(isbn) + "</h3>");
			out.println("<h3 align='center'>ISBN: " + isbn + "</h3><br />");
			out.println("<div class='container'><br />");
			int j = 1;
			while (cursor.hasNext()) {
				cursor.next();
				String curIsbn = (String) cursor.curr().get("isbn");
				if(isbn.equals(curIsbn)) {
					out.println("<h4>" + j + ".</h4>");
					out.println("<h4>Student USN: " + cursor.curr().get("sid") + "</h4>");
					out.println("<h4>Date: " + cursor.curr().get("date") + "</h4>");
					
					out.println("<table class='table table-bordered table-striped'>");
					out.println("<tr><th>Parameter</th><th>Comments</th><th>User Feeling</th></tr>");
					
					@SuppressWarnings("unchecked")
					List<BasicDBObject> reviews = (List<BasicDBObject>) cursor.curr().get("reviews");

					int iter = 0;
					int l = reviews.size();
					int i = 1;
					BasicDBObject review = (BasicDBObject) reviews.get(0);
					while(iter < l) {
						review = reviews.get(iter);

						switch(i) {
						case 1:
							String parameter = review.getString("libreview");
							float score = Float.parseFloat(review.getString("score")) * 100;
							
							BigDecimal bdScore = RoundOff.round(score, 2);
							if(parameter != null) {
								out.println("<tr><td>Librarian Review</td><td>" + parameter + "</td><td>" + bdScore + "%</td></tr>");
								iter++;
							}
							break;
						case 2:
							String parameter2 = review.getString("issueexperience");
							float score2 = Float.parseFloat(review.getString("score")) * 100;
							
							BigDecimal bdScore2 = RoundOff.round(score2, 2);
							if(parameter2 != null) {
								out.println("<tr><td>Issue Experience</td><td>" + parameter2 + "</td><td>" + bdScore2 + "%</td></tr>");
								iter++;
							}
							break;
						case 3:
							String parameter3 = review.getString("bookquality");
							float score3 = Float.parseFloat(review.getString("score")) * 100;
							
							BigDecimal bdScore3 = RoundOff.round(score3, 2);
							if(parameter3 != null) {
								out.println("<tr><td>Book Quality</td><td>" + parameter3 + "</td><td>" + bdScore3 + "%</td></tr>");
								iter++;
							}
							break;
						case 4:
							String parameter4 = review.getString("webapp");
							float score4 = Float.parseFloat(review.getString("score")) * 100;
							
							BigDecimal bdScore4 = RoundOff.round(score4, 2);
							if(parameter4 != null) {
								out.println("<tr><td>Web app feedback</td><td>" + parameter4 + "</td><td>" + bdScore4 + "%</td></tr>");
								iter++;
							}
							break;
						case 5:
							String parameter5 = review.getString("bookcontent");
							float score5 = Float.parseFloat(review.getString("score")) * 100;
							
							BigDecimal bdScore5 = RoundOff.round(score5, 2);
							if(parameter5 != null) {
								out.println("<tr><td>Book Content</td><td>" + parameter5 + "</td><td>" + bdScore5 + "%</td></tr>");
								iter++;
							}
							break;
						}
						i++;
					}
					out.println("</table></ br>");
					j = j + 1;
					out.println("<br />");
				}
				
			 }
			out.println("</div>");
		}
		
		else
		{
			new com.authfunctions.StudentLogin(request,response,out);
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
		out.println("<title>View Review</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session = request.getSession();
		String usn = (String) session.getAttribute("studentusn");
		
		if (usn != null)
		{
		
			request.getRequestDispatcher("navstudent.html").include(request, response);
			
			out.println("<div class='container'>");
			
			out.println("<h3>View Reviews</h3>");
			out.println("<br><form action='ViewReview' method='post' style='width:300px'>");
			out.println("<div class='form-group'><label for='book'>Select Book</label>");
			out.println("<select required class='form-control' name='bookid' id='book'/>");
			
			List<BookBean> list=BookDao.view();
			
			out.print("<option disabled selected value=''></option>");
			
			for(BookBean bean: list) {
				out.print("<option value='" + bean.getIsbn() + "'>" + BookDao.getTitlebyId(bean.getIsbn()) + "</option>");
			}
			
			out.println("</select></div>");
			
			out.println("<button type=\"submit\" class=\"btn btn-primary\">View Reviews</button>\r\n" + 
					"</form>\r\n" + 
					"<br />");
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
