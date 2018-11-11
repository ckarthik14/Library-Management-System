package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.CourseBean;
import com.dao.CourseDao;

@WebServlet("/EditCourse")
public class EditCourse extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		String title = request.getParameter("title");
		String department = request.getParameter("department");
		
		CourseBean bean = new CourseBean(cid, title, department);
		int i = CourseDao.update(bean);
		
		response.sendRedirect("ViewCourse");
		
	}

}
