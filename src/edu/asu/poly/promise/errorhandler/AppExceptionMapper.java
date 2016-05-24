package edu.asu.poly.promise.errorhandler;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AppExceptionMapper implements ExceptionMapper {

	@Override
	public Response toResponse(Throwable ex) {
		// TODO Auto-generated method stub
		 Response.StatusType type = getStatusType(ex);

	        AppException error = new AppException(
	                type.getStatusCode(),
	                type.getReasonPhrase(),
	                ex.getLocalizedMessage());

	        return Response.status(error.getStatusCode())
	                .entity(error)
	                .type(MediaType.APPLICATION_JSON)
	                .build();
	    }

	    private Response.StatusType getStatusType(Throwable ex) 
	    {
	        if (ex instanceof WebApplicationException) 
	        {
	            return((WebApplicationException)ex).getResponse().getStatusInfo();
	        } 
	        else 
	        {
	            return Response.Status.INTERNAL_SERVER_ERROR;
	        }
	    }
	}

