package com.coop.org.exception;

public class SecurityQuestionNotSetException extends Exception {
	private String message = "";
	private String errorCode = "";
	
	public SecurityQuestionNotSetException(){}
	public SecurityQuestionNotSetException(String message) {
		this.message = message;
	}
	public SecurityQuestionNotSetException(String message,String errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
