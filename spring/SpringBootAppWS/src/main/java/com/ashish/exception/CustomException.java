package com.ashish.exception;

public class CustomException extends Exception {
	private String message;
	
	public CustomException(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
