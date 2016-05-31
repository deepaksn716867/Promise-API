package edu.asu.poly.promise.errorhandler;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
/**
 * This class is the Exception handling class for Bad Request(400). 
 * @author Deepak S N
 */
@Provider
public class BadRequestCustomException extends WebApplicationException  implements ExceptionMapper<BadRequestCustomException> 
{
	private static final long serialVersionUID = 1L;
	
	public BadRequestCustomException() {
		super("Bad Request !!");
	}

	public BadRequestCustomException(String errorJSON) {
		super(Response.status(400).entity(errorJSON).type("application/json").build());
	}

	@Override
	public Response toResponse(BadRequestCustomException exception) 
	{
		return Response.status(400).entity(exception.getMessage())
				.type("application/json").build();
	} 
}
