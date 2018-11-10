package com.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.beans.LibrarianBean;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class LibrarianDao {

	public static int save(LibrarianBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("insert into librarian(lid, name, password) values(?,?,?)");
			ps.setString(1,bean.getLid());
			ps.setString(2,bean.getName());
			ps.setString(3,bean.getPassword());
			status=ps.executeUpdate();
			con.close();
			
		}catch (MySQLIntegrityConstraintViolationException e) {
			status = 2;
		}
		
		catch(Exception e){System.out.println(e);}
		
		return status;
	}
	public static int update(LibrarianBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("update librarian set name=?, password=? where lid=?");
			ps.setString(1,bean.getName());
			ps.setString(2,bean.getPassword());
			ps.setString(3,bean.getLid());
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	public static List<LibrarianBean> view(){
		List<LibrarianBean> list=new ArrayList<LibrarianBean>();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from librarian");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				LibrarianBean bean=new LibrarianBean();
				bean.setLid(rs.getString("lid"));
				bean.setName(rs.getString("name"));
				bean.setPassword(rs.getString("password"));
				list.add(bean);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
	public static LibrarianBean viewById(String Lid){
		LibrarianBean bean = new LibrarianBean();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from librarian where lid=?");
			ps.setString(1,Lid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				bean.setLid(rs.getString(1));
				bean.setName(rs.getString(2));
				bean.setPassword(rs.getString(3));
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return bean;
	}
	public static int delete(String Lid){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("delete from librarian where lid=?");
			ps.setString(1,Lid);
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}

	public static boolean authenticate(String email,String password){
		boolean status=false;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from librarian where lid=? and password=?");
			ps.setString(1,email);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				status=true;
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
}
