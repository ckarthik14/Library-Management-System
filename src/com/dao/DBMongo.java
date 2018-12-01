package com.dao;

import com.mongodb.MongoClient; 
import com.mongodb.DBCollection;

import com.mongodb.MongoCredential;
import com.mongodb.DB;
import com.mongodb.BasicDBObject;

import java.net.UnknownHostException;

public class DBMongo {
	
	public static DB getDB() {
		
	MongoClient mongo = null;
	DB database = null;
	
	try {
		mongo = new MongoClient( "127.0.0.1" , 27017 );
		
		MongoCredential credential = MongoCredential.createMongoCRCredential("karthik", "library", "password".toCharArray());
	    
	    database = mongo.getDB("library");
	    
	} catch (UnknownHostException e) {
		e.printStackTrace();
	}
	
	return database;
 }	
}