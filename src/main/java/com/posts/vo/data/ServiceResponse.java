package com.posts.vo.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceResponse {

	private Object data = null;
	
	private String message = null;
	
	private List<String> errors;

	private Date eventDate;

	public ServiceResponse() {
		this.eventDate = new Date();
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public ServiceResponse(final String message) {
		this.message = message;
		this.eventDate = new Date();
		this.errors = new ArrayList<String>();
	}
	
	public <T> ServiceResponse(final String message, final T data) {
		this.message = message;
		this.data = data;
		this.eventDate = new Date();
		this.errors = new ArrayList<String>();
	}
	
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}


}
