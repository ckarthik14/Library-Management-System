package com.dao;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.beans.WrittenBean;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class WrittenDao {

	public static int save(WrittenBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("insert into written_by(isbn, aid) values(?,?)");
			ps.setString(1,bean.getIsbn());
			ps.setInt(2,bean.getAid());
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
	public static int update(WrittenBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("update written_by set aid=? where isbn=?, aid=?");
			ps.setInt(1,bean.getAid());
			ps.setString(2,bean.getIsbn());
			ps.setInt(3,bean.getAid());
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
	public static List<WrittenBean> view(){
		List<WrittenBean> list=new ArrayList<WrittenBean>();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from written_by");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				WrittenBean bean=new WrittenBean();
				bean.setAid(rs.getInt("aid"));
				bean.setIsbn(rs.getString("isbn"));
				list.add(bean);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
	public static WrittenBean viewById(String isbn){
		WrittenBean bean = new WrittenBean();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from written_by where isbn=?");
			ps.setString(1,isbn);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				bean.setIsbn(rs.getString(1));
				bean.setAid(rs.getInt(2));
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return bean;
	}
	public static int delete(String aid, String isbn){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("delete from written_by where aid=? and isbn=?");
			ps.setString(1,aid);
			ps.setString(1,isbn);
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
}