package com.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.beans.PublisherBean;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class PublisherDao {

	public static int save(PublisherBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("insert into publisher (pid, pname, phone, address) values(?,?,?,?)");
			ps.setInt(1,bean.getPid());
			ps.setString(2,bean.getName());
			ps.setInt(3,bean.getPhone());
			ps.setString(4,bean.getAddress());
			status=ps.executeUpdate();
			con.close();
			
		}
		catch(MySQLIntegrityConstraintViolationException e) {
			status = 2;
		}
		catch(Exception e){System.out.println(e);}
		
		
		return status;
	}
	public static int update(PublisherBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("update publisher set pname=?, phone=?, address=? where pid=?");
			ps.setString(1,bean.getName());
			ps.setInt(2,bean.getPhone());
			ps.setString(3,bean.getAddress());
			ps.setInt(4,bean.getPid());
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
	public static List<PublisherBean> view(){
		List<PublisherBean> list=new ArrayList<PublisherBean>();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from publisher");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				PublisherBean bean=new PublisherBean();
				bean.setPid(rs.getInt("pid"));
				bean.setName(rs.getString("pname"));
				bean.setPhone(rs.getInt("phone"));
				bean.setAddress(rs.getString("address"));
				list.add(bean);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
	public static PublisherBean viewById(Integer Pid){
		PublisherBean bean = new PublisherBean();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from publisher where pid=?");
			ps.setInt(1,Pid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				bean.setPid(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setPhone(rs.getInt(3));
				bean.setAddress(rs.getString(4));
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return bean;
	}
	public static int delete(Integer Pid){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("delete from publisher where pid=?");
			ps.setInt(1,Pid);
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