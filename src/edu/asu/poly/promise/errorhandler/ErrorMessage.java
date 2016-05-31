package edu.asu.poly.promise.errorhandler;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

/**
 * This class is the POJO for the error message.
 * @author Deepak S N
 */
	public class ErrorMessage {
		
		/** contains the same HTTP Status */
		int errorCode;
		
		/** message describing the error*/
		String message;
			
		public int getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(int code) {
			this.errorCode = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		public ErrorMessage(int errorCode,String errorMessage)
		{
				this.errorCode = errorCode;
				this.message = errorMessage;
		}
		
		public ErrorMessage(NotFoundException ex){
			this.errorCode = Response.Status.NOT_FOUND.getStatusCode();
			this.message = ex.getMessage();
			//this.link = "https://jersey.java.net/apidocs/2.8/jersey/javax/ws/rs/NotFoundException.html";		
		}

		public ErrorMessage() {}
	}
