package edu.asu.poly.promise.webservices;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import edu.asu.poly.promise.dao.CheckSurveyDAO;
import edu.asu.poly.promise.dao.DAOFactory;
import edu.asu.poly.promise.model.*;
import edu.asu.poly.promise.services.*;

@Path("/promise")
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
    public Response getsurvey(@PathParam("survey_instance_id") String pin) throws IOException
    {
		Response response=null;
		
		
		
		return response;

    }


    
    
}