package com.dao;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.beans.PrescribesBean;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class PrescribesDao {

	public static int save(PrescribesBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("insert into prescribes(isbn, cid) values(?,?)");
			ps.setString(1,bean.getIsbn());
			ps.setInt(2,bean.getCid());
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
	public static int update(PrescribesBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("update prescribes set cid=? where isbn=?, cid=?");
			ps.setInt(1,bean.getCid());
			ps.setString(2,bean.getIsbn());
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
	public static List<PrescribesBean> view(){
		List<PrescribesBean> list=new ArrayList<PrescribesBean>();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from prescribes");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				PrescribesBean bean=new PrescribesBean();
				bean.setCid(rs.getInt("cid"));
				bean.setIsbn(rs.getString("isbn"));
				list.add(bean);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
	public static PrescribesBean viewById(String isbn){
		PrescribesBean bean = new PrescribesBean();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from prescribes where isbn=?");
			ps.setString(1,isbn);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				bean.setIsbn(rs.getString(1));
				bean.setCid(rs.getInt(2));
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return bean;
	}
	public static int delete(String cid, String isbn){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("delete from prescribes where cid=? and isbn=?");
			ps.setString(1,cid);
			ps.setString(1,isbn);
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
}

