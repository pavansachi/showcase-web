package com.posts.exceptions;

public class JPADataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPADataException() {
		
	}
	
	public JPADataException(final String message) {
		
		super(message);
	}
	
	public JPADataException(Throwable cause) {
		
		super(cause);
	}
	
	public JPADataException(final String message, Throwable cause) {
		
		super(message, cause);
	}
}
