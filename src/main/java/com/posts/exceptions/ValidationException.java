package com.posts.exceptions;

public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException() {
		
	}
	
	public ValidationException(final String message) {
		
		super(message);
	}
	
	public ValidationException(Throwable cause) {
		
		super(cause);
	}
	
	public ValidationException(final String message, Throwable cause) {
		
		super(message, cause);
	}
}
