package com.database;
import static com.mongodb.client.model.Filters.eq;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import org.bson.Document;
import org.bson.types.ObjectId;
	 
import com.mongodb.Block;
import com.mongodb.MongoClient;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
	 
public class ImageDataOperations {

	
	public static void main(String[] args) 
	{
	
	  Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
	  mongoLogger.setLevel(Level.SEVERE); 
	  
	  ImageDataOperations gridFs = new ImageDataOperations();
	//  ObjectId babyBoyObjectId = gridFs.upload("C:/Users/Madhu/Desktop/IMG_9034.JPG","baby-boy");
	  //ObjectId babyGirlObjectId = gridFs.upload("C:/Users/Madhu/Desktop/IMG_9046.JPG","baby-girl");
	//  gridFs.findAll();
	  //gridFs.find(babyGirlObjectId);
	  gridFs.download("madhu");
	  //gridFs.rename(babyBoyObjectId,"new-baby-image");
	  //gridFs.delete(babyBoyObjectId);
	 // gridFs.findAll();
	 }
	 
	    // Upload File
	 public ObjectId upload(InputStream imginput,String fileName)
	 {
		 System.out.println("Calling upload...");
		 MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
		 ObjectId fileId = null;
		 try 
		 {
			 MongoDatabase database = mongoClient.getDatabase("test2");
			 GridFSBucket gridBucket = GridFSBuckets.create(database);
			
			 // Create some custom options
			 GridFSUploadOptions uploadOptions = new GridFSUploadOptions().chunkSizeBytes(1024)
					 		 .metadata(new Document("type", "image")
							 .append("upload_date", format.parse("2016-09-01T00:00:00Z"))
							 .append("content_type", "image/jpg"));
			 fileId = gridBucket.uploadFromStream(fileName, imginput, uploadOptions);
	 
		 } 
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally 
		 {
			 mongoClient.close();
		 }
	 
		 System.out.println(fileId);
	  return fileId;
	 }
	 
	    
	    // Find All
	  public void findAll() 
	  {
		  System.out.println("Calling findAll...");
		  MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	 
		  try 
		  {
			  MongoDatabase database = mongoClient.getDatabase("test1");
			  GridFSBucket gridBucket = GridFSBuckets.create(database);
	 
			  gridBucket.find().forEach(new Block<GridFSFile>() {
				  @Override
				  public void apply(final GridFSFile gridFSFile) {
					  System.out.println("File Name:- " + gridFSFile.getFilename());
					  System.out.println("Meta Data:- " + gridFSFile.getMetadata());
				  }
			  });
	 
		  }
		  catch (Exception e)
		  {
			  e.printStackTrace();
		  } finally 	
		  {
			  mongoClient.close();
		  }
	    }
	    
	    // Find by Id
	  public void find(ObjectId objectId)
	  {
		  System.out.println("Calling find...");
		  MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	 
		  try 
		  {
			  MongoDatabase database = mongoClient.getDatabase("test1");
			  GridFSBucket gridBucket = GridFSBuckets.create(database);
	 
			  GridFSFile gridFSFile = gridBucket.find(eq("_id",objectId)).first();
			  System.out.println("File Name:- " + gridFSFile.getFilename());
			  System.out.println("Meta Data:- " + gridFSFile.getMetadata());
	 
		  }
		  catch (Exception e) 
		  {
			  e.printStackTrace();
		  }
		  finally 
		  {
			  mongoClient.close();
		  }
	  }
	    
	    // Download File
	  public void download(String fileName) 
	  {
		  System.out.println("Calling download...");
		  MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	 
		  try 
		  {
			  MongoDatabase database = mongoClient.getDatabase("test2");
			  GridFSBucket gridBucket = GridFSBuckets.create(database);
			  FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/Madhu/Desktop/MongoDB/a.jpg");
			  gridBucket.downloadToStream(fileName, fileOutputStream);
			  fileOutputStream.close();
	 
		  } catch (Exception e) 
		  {
			  e.printStackTrace();
		  } 
		  finally	
		  {
			  mongoClient.close();
		  }
	  }
	    
	    //Rename File 
	  public void rename(ObjectId objectId,String newFileName) 
	  {
		  
		  System.out.println("Calling rename...");
		  MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		  try
		  {
			  MongoDatabase database = mongoClient.getDatabase("test1");
			  GridFSBucket gridBucket = GridFSBuckets.create(database);
			  gridBucket.rename(objectId, newFileName);
		  }
		  catch (Exception e) 
		  {
			  e.printStackTrace();
		  }
		  finally 
		  {
			  mongoClient.close();
		  }
	  }
	 
	    //Delete File
	  public void delete(ObjectId objectId) 
	  {
		   System.out.println("Calling delete...");
		   MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		   try 
		   {
			   MongoDatabase database = mongoClient.getDatabase("test1");
			   GridFSBucket gridBucket = GridFSBuckets.create(database);
			   gridBucket.delete(objectId);
		   }
		   catch (Exception e) 
		   {
			   e.printStackTrace();
		   } 
		   finally
		   {
			   mongoClient.close();
		   }
	    }
	}
	

