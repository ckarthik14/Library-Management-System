package com.dao;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.beans.AuthorBean;
import com.beans.AuthorRecommendBean;
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
			ps.setString(4,bean.getPhone());
			ps.setString(5,bean.getAddress());
			status=ps.executeUpdate();
			con.close();
			
		}
		catch (MySQLIntegrityConstraintViolationException e) {
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
			ps.setString(3,bean.getPhone());
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
				bean.setAddress(rs.getString("address"));
				bean.setPhone(rs.getString("phone"));
				list.add(bean);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
	public static AuthorBean viewById(int Aid){
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
				bean.setPhone(rs.getString(5));
				bean.setAddress(rs.getString(4));
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return bean;
	}
	public static int delete(int Aid){
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
	
	public static List<AuthorRecommendBean> retrieve(String cid){
		AuthorRecommendBean bean = new AuthorRecommendBean();
		List<AuthorRecommendBean> list = new ArrayList<AuthorRecommendBean>();
		
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select isbn, b.title, edition, fname, lname, quantity, issued from written as w, book as b, author as a where a.aid=w.aid and a.isbn=b.isbn and a.aid=?");
			ps.setString(1,cid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				bean.setIsbn(rs.getString(1));
				bean.setTitle(rs.getString(2));
				bean.setEdition(rs.getString(3));
				bean.setFname(rs.getString(4));
				bean.setLname(rs.getString(5));
				bean.setStock(rs.getInt(6) - rs.getInt(7));
				list.add(bean);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
}