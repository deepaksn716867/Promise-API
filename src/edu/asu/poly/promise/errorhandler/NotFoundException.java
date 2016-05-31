package edu.asu.poly.promise.errorhandler;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * This class is the Exception handling class for Not Found(404). 
 * @author Deepak S N
 */

public class NotFoundException extends WebApplicationException  implements ExceptionMapper<NotFoundException>{

private static final long serialVersionUID = 1L;
	
	public NotFoundException() {
		super("Not Found");
	}

	public NotFoundException(String errorJSON) {
		super(Response.status(404).entity(errorJSON).type("application/json").build());
	}

	@Override
	public Response toResponse(NotFoundException exception) 
	{
		return Response.status(404).entity(exception.getMessage())
				.type("application/json").build();
	} 

}
