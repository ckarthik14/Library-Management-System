package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.PublisherBean;
import com.dao.PublisherDao;

@WebServlet("/EditPublisher")
public class EditPublisher extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid = Integer.parseInt(request.getParameter("pid"));
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		PublisherBean bean = new PublisherBean(pid, name, phone, address);
		int i = PublisherDao.update(bean);
		
		response.sendRedirect("ViewPublisher");
		
	}

}
