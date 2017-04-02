package com.main;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


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
	@Produces(MediaType.TEXT_PLAIN)
	  public String postData(String result) throws IOException {
		System.out.println(result);
		return result;
	}
	
	
}
