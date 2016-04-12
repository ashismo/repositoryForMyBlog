package com.coop.org.exception;

public class RetailException extends RuntimeException {
	protected String message = "";
	protected String errorCode = "";
	
	public RetailException(){}
	public RetailException(String message) {
		this.message = message;
	}
	public RetailException(String message,String errorCode) {
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