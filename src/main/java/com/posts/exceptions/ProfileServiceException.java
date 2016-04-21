package com.posts.exceptions;

public class ProfileServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfileServiceException() {
		
	}
	
	public ProfileServiceException(final String message) {
		
		super(message);
	}
	
	public ProfileServiceException(Throwable cause) {
		
		super(cause);
	}
	
	public ProfileServiceException(final String message, Throwable cause) {
		
		super(message, cause);
	}
}
