import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/Service")
public class Service_JAVA {

	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	  public String text() {
	    return "Hello Jersey";
	}

	
	
	
}
