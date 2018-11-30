package com.servlets;


import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.LibrarianBean;
import com.dao.LibrarianDao;

import com.password.SHA256;

@WebServlet("/EditLibrarian")
public class EditLibrarian extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String email=request.getParameter("lid");
		String password=request.getParameter("password");
		
		String passhash = SHA256.getSHA(password);
		
		LibrarianBean bean = new LibrarianBean(name, email, passhash);
		LibrarianDao.update(bean);
		response.sendRedirect("ViewLibrarian");
	}

}
