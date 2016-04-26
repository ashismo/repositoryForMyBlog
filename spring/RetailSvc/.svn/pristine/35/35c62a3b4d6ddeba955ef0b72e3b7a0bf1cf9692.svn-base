package com.coop.org.exception;

public class CustomerAccountException extends RuntimeException {
	protected String message = "";
	protected String errorCode = "";
	
	public CustomerAccountException(){}
	public CustomerAccountException(String message) {
		this.message = message;
	}
	public CustomerAccountException(String message,String errorCode) {
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