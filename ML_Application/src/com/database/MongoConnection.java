package com.database;


import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
	
	MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	   MongoDatabase database = mongoClient.getDatabase("test");
	   MongoCollection<Document> collection = database.getCollection("test");
	   
   public void insert(Document doc) {

	   
	 
		
       System.out.println("running");
	   collection.insertOne(doc);
   }
}


