package com.posts.exceptions;

public class PostServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PostServiceException() {
		
	}
	
	public PostServiceException(final String message) {
		
		super(message);
	}
	
	public PostServiceException(Throwable cause) {
		
		super(cause);
	}
	
	public PostServiceException(final String message, Throwable cause) {
		
		super(message, cause);
	}
}
