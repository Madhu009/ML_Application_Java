package com.main;
import java.io.IOException;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

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
	@Path("/path")
	@Produces(MediaType.APPLICATION_JSON)
	  public String postData(String result) throws IOException, JSONException {
		System.out.println(result);
		JSONObject obj=new JSONObject(result);
		
		String name=obj.getString("name");
		String pwd=obj.getString("pwd");
		  Document doc = new Document("name", name)
	  				.append("pwd",pwd);
		//System.out.println(doc);
		MongoConnection conn=new MongoConnection();
		conn.insert(doc);
		
		
		return result;
	}
	
	
}
