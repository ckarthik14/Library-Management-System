package com.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.beans.StudentBean;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class StudentDao {

	public static int save(StudentBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("insert into student(sid, name, dob, password) values(?, ?,?,?)");
			ps.setString(1,bean.getSid());
			ps.setString(2,bean.getName());
			ps.setDate(3,bean.getDob());
			ps.setString(4,bean.getPassword());
			status=ps.executeUpdate();
			con.close();
			
		}catch (MySQLIntegrityConstraintViolationException e) {
			status = 2;
		}
		
		catch(Exception e){System.out.println(e);}
		
		return status;
	}
	public static int update(StudentBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("update student set name=?, password=?, dob=? where sid=?");
			ps.setString(1,bean.getName());
			ps.setString(2,bean.getPassword());
			ps.setDate(3,bean.getDob());
			ps.setString(4,bean.getSid());
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	public static List<StudentBean> view(){
		List<StudentBean> list=new ArrayList<StudentBean>();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from student");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				StudentBean bean=new StudentBean();
				bean.setSid(rs.getString("sid"));
				bean.setName(rs.getString("name"));
				bean.setPassword(rs.getString("password"));
				bean.setDob(rs.getDate("dob"));				
				list.add(bean);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
	public static StudentBean viewById(String sid){
		StudentBean bean = new StudentBean();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from student where sid=?");
			ps.setString(1,sid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				bean.setSid(rs.getString(1));
				bean.setName(rs.getString(2));
				bean.setDob(rs.getDate(3));
				bean.setPassword(rs.getString(4));
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return bean;
	}
	public static int delete(String sid){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("delete from student where sid=?");
			ps.setString(1,sid);
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}

	public static boolean authenticate(String sid,String password){
		boolean status=false;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from student where sid=? and password=?");
			ps.setString(1,sid);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				status=true;
			}
			
			System.out.println(status);
			
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	public static boolean checkUsn(String sid) {
		boolean i=false;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from student where sid=?");
			ps.setString(1,sid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				i = true;
			}
			else {
				i = false;
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return i;
	}
	public static boolean checkUsnissue(String sid) {
		boolean i=false;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from issuebook where sid=?");
			ps.setString(1,sid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				i = true;
			}
			else {
				i = false;
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return i;
	}
}