package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.BookBean;
import com.dao.BookDao;

@WebServlet("/EditBook")
public class EditBook extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String edition = request.getParameter("edition");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		
		BookBean bean = new BookBean(isbn, title, edition, quantity, pid, cid);
		int i = BookDao.update(bean);
		
		response.sendRedirect("ViewBook");
		
	}

}
