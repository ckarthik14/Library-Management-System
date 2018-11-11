package com.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.beans.AuthorBean;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class AuthorDao {

	public static int save(AuthorBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("insert into author (aid, fname, lname, phone, address) values(?,?,?,?,?)");
			ps.setInt(1,bean.getAid());
			ps.setString(2,bean.getFname());
			ps.setString(3,bean.getLname());
			ps.setInt(4,bean.getPhone());
			ps.setString(5,bean.getAddress());
			status=ps.executeUpdate();
			con.close();
			
		}
		catch(MySQLIntegrityConstraintViolationException e) {
			status = 2;
		}
		catch(Exception e){System.out.println(e);}
		
		
		return status;
	}
	public static int update(AuthorBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("update author set fname=?, lname=?, phone=?, address=? where aid=?");
			ps.setString(1,bean.getFname());
			ps.setString(2,bean.getLname());
			ps.setInt(3,bean.getPhone());
			ps.setString(4,bean.getAddress());
			ps.setInt(5,bean.getAid());
			status=ps.executeUpdate();
			con.close();
			
		}
		catch(MySQLIntegrityConstraintViolationException e) {
			status = 2;
		}
		catch(Exception e){System.out.println(e);}
		
		status = 2;
		return status;
	}
	public static List<AuthorBean> view(){
		List<AuthorBean> list=new ArrayList<AuthorBean>();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from author");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				AuthorBean bean=new AuthorBean();
				bean.setAid(rs.getInt("aid"));
				bean.setFname(rs.getString("fname"));
				bean.setLname(rs.getString("lname"));
				bean.setPhone(rs.getInt("phone"));
				bean.setAddress(rs.getString("address"));
				list.add(bean);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
	public static AuthorBean viewById(Integer Aid){
		AuthorBean bean = new AuthorBean();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from author where aid=?");
			ps.setInt(1,Aid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				bean.setAid(rs.getInt(1));
				bean.setFname(rs.getString(2));
				bean.setLname(rs.getString(3));
				bean.setPhone(rs.getInt(4));
				bean.setAddress(rs.getString(5));
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return bean;
	}
	public static int delete(Integer Aid){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("delete from author where aid=?");
			ps.setInt(1,Aid);
			status=ps.executeUpdate();
			con.close();
			
		}
		catch(MySQLIntegrityConstraintViolationException e) {
			status = 2;
		}
		catch(Exception e){System.out.println(e);}
		
		status = 2;
		return status;
	}
}