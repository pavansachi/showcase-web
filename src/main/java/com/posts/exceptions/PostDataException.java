package com.posts.exceptions;

public class PostDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PostDataException() {
		
	}
	
	public PostDataException(final String message) {
		
		super(message);
	}
	
	public PostDataException(Throwable cause) {
		
		super(cause);
	}
	
	public PostDataException(final String message, Throwable cause) {
		
		super(message, cause);
	}
}
