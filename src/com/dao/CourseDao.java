package com.dao;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.beans.CourseBean;
import com.beans.CourseRecommendBean;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class CourseDao {

	public static int save(CourseBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("insert into course(title, department) values(?,?)");
			ps.setString(1,bean.getTitle());
			ps.setString(2,bean.getDepartment());
			status=ps.executeUpdate();
			con.close();
			
		}
		catch (MySQLIntegrityConstraintViolationException e) {
			status = 2;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println(status);
		
		return status;
	}
	public static int update(CourseBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("update course set title=?, department=? where cid=?");
			ps.setString(1,bean.getTitle());
			ps.setString(2,bean.getDepartment());
			ps.setInt(3,bean.getCid());
			status=ps.executeUpdate();
			con.close();
			
		}
		catch(MySQLIntegrityConstraintViolationException e)
		{status = 2;}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	public static List<CourseBean> view(){
		List<CourseBean> list=new ArrayList<CourseBean>();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from course");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				CourseBean bean=new CourseBean();
				bean.setCid(rs.getInt("cid"));
				bean.setTitle(rs.getString("title"));
				bean.setDepartment(rs.getString("department"));
				list.add(bean);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
	public static CourseBean viewById(String cid){
		CourseBean bean = new CourseBean();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from course where cid=?");
			ps.setString(1,cid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				bean.setCid(rs.getInt(1));
				bean.setTitle(rs.getString(2));
				bean.setDepartment(rs.getString(3));
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return bean;
	}
	public static int delete(String cid){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("delete from course where cid=?");
			ps.setString(1,cid);
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	
	public static CourseRecommendBean retrieve(String cid){
		CourseRecommendBean bean = new CourseRecommendBean();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select isbn, b.title, edition, c.title, quantity, issued from prescribes as p, book as b, course as c where c.cid=p.cid and p.isbn=b.isbn and c.cid=?");
			ps.setString(1,cid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				bean.setIsbn(rs.getString(1));
				bean.setTitle(rs.getString(2));
				bean.setEdition(rs.getString(3));
				bean.setCourse(rs.getString(4));
				bean.setStock(rs.getInt(5) - rs.getInt(6));
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return bean;
	}
}

