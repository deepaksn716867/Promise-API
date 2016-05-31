package edu.asu.poly.promise.errorhandler;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is the Generic Exception handling class. 
 * @author Deepak S N
 */
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	 

		@Override
		public Response toResponse(Throwable ex) {
			
			ErrorMessage errorMessage = new ErrorMessage();		
			setHttpStatus(ex, errorMessage);
			//errorMessage.setErrorCode(500);
			errorMessage.setMessage(ex.getMessage());
			StringWriter errorStackTrace = new StringWriter();
			ex.printStackTrace(new PrintWriter(errorStackTrace));
			ObjectMapper mapper = new ObjectMapper();
			String JSONErrorMessage = null;
			try
			{
				JSONErrorMessage = mapper.writeValueAsString(errorMessage);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				try 
				{
					throw e;
				} catch (Exception e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
					
			return Response.status(errorMessage.getErrorCode())
					.entity(JSONErrorMessage)
					.type(MediaType.APPLICATION_JSON)
					.build();	
		}

		private void setHttpStatus(Throwable ex, ErrorMessage errorMessage) {
			if(ex instanceof WebApplicationException ) { 
				errorMessage.setErrorCode(((WebApplicationException)ex).getResponse().getStatus());
			} else {
				errorMessage.setErrorCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()); 
			}
		}
	}

