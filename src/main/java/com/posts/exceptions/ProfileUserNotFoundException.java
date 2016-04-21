package com.posts.exceptions;

public class ProfileUserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfileUserNotFoundException() {
		
	}
	
	public ProfileUserNotFoundException(final String message) {
		
		super(message);
	}
	
	public ProfileUserNotFoundException(Throwable cause) {
		
		super(cause);
	}
	
	public ProfileUserNotFoundException(final String message, Throwable cause) {
		
		super(message, cause);
	}
}
