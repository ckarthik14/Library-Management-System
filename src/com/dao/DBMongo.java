package com.dao;

import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;
import com.mongodb.DB;

import java.net.UnknownHostException;

public class DBMongo {
	
	public static void main(String[] args) {
		
	MongoClient mongo = null;
	try {
		mongo = new MongoClient( "127.0.0.2" , 27017 );
		
		MongoCredential credential = MongoCredential.createMongoCRCredential("karthik", "library", "password".toCharArray());
	    
	    DB database = mongo.getDB("library"); 
	    System.out.println("Credentials ::"+ credential);  
	    
	    System.out.println("Connected to the database successfully");
	    
	    
	} catch (UnknownHostException e) {
		e.printStackTrace();
	}
	
	}
	
}