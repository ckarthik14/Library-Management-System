package com.servlets;

import java.io.*;


import java.net.*;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

import com.beans.GetSentiment;


import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.beans.AuthorBean;
import com.beans.Document;
import com.beans.Documents;
import com.beans.GetSentiment;
import com.dao.AuthorDao;
import com.dao.DBMongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.util.JSON;
import com.mongodb.BasicDBList;
import com.beans.ReviewBean;

@WebServlet("/CalculateReview")
public class CalculateReview extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String usn = (String) session.getAttribute("studentusn");
		
		DB database = DBMongo.getDB();
		
		DBCollection collection = database.getCollection("review");
		
		String isbn = request.getParameter("bookid");
		String librarian = request.getParameter("librarian");
		String issue = request.getParameter("issue");
		String book = request.getParameter("book");
		String webapp = request.getParameter("webapp");
		String content = request.getParameter("content");
		
		String[] reviews = new String[6];
		
		ReviewBean rBean = new ReviewBean();
		
		BasicDBObject document = new BasicDBObject();
		
		if(isbn != null)
		{
			Documents documents = new Documents();
			
			if(librarian != "")
			{	
				reviews[1] = librarian;
				documents.add("1", "en", librarian);	
			}
			if(issue != "")
			{	
				reviews[2] = issue;
				documents.add("2", "en", issue);	
			}
			if(book != "")
			{	
				reviews[3] = book;
				documents.add("3", "en", book);	
			}
			if(webapp != "")
			{	
				reviews[4] = webapp;
				documents.add("4", "en", webapp);	
			}
			if(content != "")
			{	
				reviews[5] = content;
				documents.add("5", "en", content);	
			}
			
			System.out.println(documents);
			
			String sentiment = null;
			try {
				sentiment = GetSentiment.GetSentiments(documents);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(1);
			
			JsonParser parser = new JsonParser();
			JsonObject sentobj = (JsonObject) parser.parse(sentiment);
			
			JsonArray docs = sentobj.getAsJsonArray("documents");
			
			int arraySize = docs.size();
			
			BasicDBList reviewsList = new BasicDBList();
			
			document.append("sid", usn).append("isbn", isbn);
			
			
			for(int i = 0; i < arraySize; i++)
			{
				JsonObject docobj = (JsonObject) docs.get(i);
				
				float score = docobj.get("score").getAsFloat();
				int id = docobj.get("id").getAsInt();
				
				BasicDBObject obj = new BasicDBObject();
				obj.append(rBean.reviews[id], reviews[id]).append("score", score);
				
				reviewsList.add(obj);
			}
			
			document.append("reviews", reviewsList);
		}
		
	    collection.insert(document);
		
		
		response.sendRedirect("GiveReviewForm");
	}

}