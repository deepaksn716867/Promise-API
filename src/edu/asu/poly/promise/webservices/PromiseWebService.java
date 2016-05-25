package edu.asu.poly.promise.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import edu.asu.poly.promise.services.PromiseServices;

@Path("/promis")
public class PromiseWebService {
	
	PromiseServices promiseservices=new PromiseServices();
    
    @Context
   private UriInfo context;
    
	@GET
    @Path("/checksurveys/{pin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checksurveys(@PathParam("pin") String pin) throws NumberFormatException, Exception
    {
		Response response = null;
    	//ArrayList<SrvyInstActivePatientSrvyTempJoin> result=new ArrayList<>;
        //function call to checksurvey DTO 
		String jsonstring=promiseservices.checksurveyservice(Integer.parseInt(pin));
        System.out.println("The request is received");        
        response = Response.status(Response.Status.OK).entity(jsonstring).build();
                
        return response;
    }
	
	@GET
    @Path("/getsurvey/{survey_instance_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getsurvey(@PathParam("survey_instance_id") String survey_instance_id) throws NumberFormatException, Exception
    {
		Response response=null;
		String jsonstring=promiseservices.getsurveyservice(Integer.parseInt(survey_instance_id));
		response = Response.status(Response.Status.OK).entity(jsonstring).build();
		
		return response;
    }

	@POST
	@Path("/submitsurvey")
    @Produces(MediaType.APPLICATION_JSON)
	public Response submitsurvey(String content) throws NumberFormatException, Exception
    {
		Response response=null;
		String jsonstring=promiseservices.submitsurveyservice(content);
		response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With").header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, PUT, POST").entity(jsonstring).build();		
		return response;
    }
	

    
    
}