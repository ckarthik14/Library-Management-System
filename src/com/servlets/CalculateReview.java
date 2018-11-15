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

@WebServlet("/CalculateReview")
public class CalculateReview extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String usn = (String) session.getAttribute("studentusn");
		
		DB database = DBMongo.getDB();
		
		DBCollection collection = database.getCollection("review");
		
		String bookid = request.getParameter("bookid");
		String librarian = request.getParameter("librarian");
		String issue = request.getParameter("issue");
		String book = request.getParameter("book");
		String webapp = request.getParameter("webapp");
		String content = request.getParameter("content");
		
		BasicDBObject document = new BasicDBObject();
		
		if(bookid != null)
		{
			if(librarian != "")
			{
				Documents documents = new Documents();
				
				documents.add("1", "en", librarian);
						
				String sentiment = null;
				try {
					sentiment = GetSentiment.GetSentiment(documents);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.println(sentiment);
				
				JsonParser parser = new JsonParser();
				JsonObject sent = (JsonObject) parser.parse(sentiment);
				
				JsonArray docs = sent.getAsJsonArray("documents");
				JsonObject scoreobj = (JsonObject) docs.get(0);
				
				float score = scoreobj.get("score").getAsFloat();
				
				System.out.println(score);
			}
		}
		
	    collection.insert(document);
		
		
		response.sendRedirect("GiveReviewForm");
	}

}