package edu.asu.poly.promise.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/promise")
public class PromiseWebService {
    
    @Context
   private UriInfo context;
    
    @GET
    @Path("/checksurveys/{pin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checksurveys(@PathParam("pin") String pin)
    {
        Response response = null;
        //function call to checksurvey DTO 
        //String jsonstring=check_survey_dto(Integer.parseInt(pin));
        String jsonstring="Hello";
        System.out.println("The request is received");
       response = Response.status(Response.Status.OK).entity(jsonstring).build();
        
        return response;
    }

    
    
}