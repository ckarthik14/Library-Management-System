package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.AuthorBean;
import com.dao.AuthorDao;

@WebServlet("/EditAuthor")
public class EditAuthor extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int aid = Integer.parseInt(request.getParameter("aid"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		AuthorBean bean = new AuthorBean(aid, fname, lname, phone, address);
		int i = AuthorDao.update(bean);
		
		response.sendRedirect("ViewAuthor");
		
	}

}
