package com.posts.exceptions;

public class ProfileDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfileDataException() {
		
	}
	
	public ProfileDataException(final String message) {
		
		super(message);
	}
	
	public ProfileDataException(Throwable cause) {
		
		super(cause);
	}
	
	public ProfileDataException(final String message, Throwable cause) {
		
		super(message, cause);
	}
}
