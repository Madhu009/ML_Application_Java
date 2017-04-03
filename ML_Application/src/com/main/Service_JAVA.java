package com.main;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.database.ImageDataOperations;
import com.database.MongoConnection;


@Path("/Service")
public class Service_JAVA {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	  public String textPath() {
		System.out.println("test");
	    return "Hello path";
	}
	
	@POST
	@Path("/reg")
	@Produces(MediaType.APPLICATION_JSON)
	  public String postData(String result) throws IOException, JSONException {
		System.out.println(result);
		JSONObject obj=new JSONObject(result);
		
		String name=obj.getString("name");
		String pwd=obj.getString("pwd");
		Document doc = new Document("name", name);
		
		MongoConnection conn=new MongoConnection();
		boolean status=false;
		status=conn.checkUserReg(doc);
			
		if(status)
		{
			doc.append("pwd", pwd);
			conn.insert(doc);
			return "yes";
		}
		else
			return "User already exists";
	}
		  
		
		
	
	//Login check 
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkLogin(String result) throws JSONException
	{
		String res="";
		JSONObject obj=new JSONObject(result);
		
		String name=obj.getString("name");
		String pwd=obj.getString("pwd");
		Document doc = new Document("name", name)
  				.append("pwd",pwd);
		MongoConnection conn=new MongoConnection();
		boolean auth=conn.userAuth(doc);
		
		if(auth)
		{
			System.out.println("User existed");
			res="yes";
		}
		else
			System.out.println("User not existed");
		return res;
	}
	
	@POST
	@Path("/upload")
	@Produces(MediaType.APPLICATION_JSON)
	public String test(InputStream image) throws JSONException, FileNotFoundException
	{
		ImageDataOperations IDO=new ImageDataOperations();
		IDO.upload(image, "filename");
		return "yes";
		
	}
	
	
	
}
