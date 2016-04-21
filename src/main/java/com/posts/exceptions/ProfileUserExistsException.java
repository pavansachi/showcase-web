package com.posts.exceptions;

public class ProfileUserExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfileUserExistsException() {
		
	}
	
	public ProfileUserExistsException(final String message) {
		
		super(message);
	}
	
	public ProfileUserExistsException(Throwable cause) {
		
		super(cause);
	}
	
	public ProfileUserExistsException(final String message, Throwable cause) {
		
		super(message, cause);
	}
}
