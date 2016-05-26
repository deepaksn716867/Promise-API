package edu.asu.poly.promise.helper;

public class APIConstants {
	
	public enum SrvyState
	{
		PENDING("pending"),
		IN_PROGRESS("in progress"),
		COMPLETED("completed"),
		EXPIRED("expired"),
		INVALID("invalid"),
		CANCELLED("cancelled");
		
		private final String value;
		
		SrvyState(String stateVal)
		{
			this.value = stateVal;
		}
			
		public String getValue()
		{
			return value;
		}
	}

}
