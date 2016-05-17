package edu.asu.poly.promise.webservices;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

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
import org.json.simple.JSONObject;

import edu.asu.poly.promise.model.*;

@Path("/promise")
public class PromiseWebService {
    
    @Context
   private UriInfo context;
    
	@GET
    @Path("/checksurveys/{pin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checksurveys(@PathParam("pin") String pin) throws IOException
    {
    	Response response = null;
        //function call to checksurvey DTO 
        SrvyInstActivePatientSrvyTempJoin result=check_survey_dto(Integer.parseInt(pin));
        SurveyInstance surveyinstance=result.getSurveyInstance();
        SurveyTemplate surveytemplate=result.getSurveyTemplate();
        
        
        JSONObject obj = new JSONObject();
        obj.put("SurveyTitle", surveytemplate.getName());
        obj.put("SurveyInstanceID",surveyinstance.getId());
        obj.put("NextDueAt",surveyinstance.getStartTime());
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        if(timeStamp.compareTo(surveyinstance.getStartTime())>0 && timeStamp.compareTo(surveyinstance.getEndTime())<0)
        obj.put("okayToStart",new Boolean(true));
        else
        obj.put("okayToStart",new Boolean(false));
	

        StringWriter out = new StringWriter();
        obj.writeJSONString(out);
        
        String jsonstring = out.toString();
        
        System.out.println("The request is received");
        System.out.println(jsonstring);
        response = Response.status(Response.Status.OK).entity(jsonstring).build();
        
        
        return response;
    }

    
    
}