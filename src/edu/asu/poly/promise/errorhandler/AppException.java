package edu.asu.poly.promise.errorhandler;

/**
 * This is the Custom Error Object class for handling errors  
 * @author Deepak S N
 */
public class AppException {
	
	private int statusCode;
    private String statusDescription;
    private String errorMessage;
    
    public AppException(int statusCode, String statusDescription, String errorMessage) 
    {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.errorMessage = errorMessage;
    }
    
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
    

}
