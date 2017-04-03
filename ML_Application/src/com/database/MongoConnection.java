package com.database;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;
import com.mongodb.MongoClient;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import static com.mongodb.client.model.Filters.eq;

public class MongoConnection {
	
	MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	   MongoDatabase database = mongoClient.getDatabase("test");
	   MongoCollection<Document> collection = database.getCollection("test");
	   
   public void insert(Document doc) {
	   System.out.println("running");
	   collection.insertOne(doc);
   }
   
   public boolean userAuth(Document doc) throws JSONException
   {
	   boolean auth=false;
	   
	   if(collection.count(doc)==1)
	   {
		   auth=true;
		   getDetails(doc);
	   }
	  
	   return auth;
   }
   
   private void getDetails(Document doc) throws JSONException
	{
	  List <Document> docq =(List<Document>) collection.find(doc).into(new ArrayList<Document>());
	   System.out.println(docq.get(0));
	   
	   
	}

   public boolean checkUserReg(Document doc) {
	//if(collection.find({"name":doc.getString("name")}))
	if(collection.count(doc)==0)
	{
		return true;
	}
	return false;
}
   
   
   
   
   
  
   
}




