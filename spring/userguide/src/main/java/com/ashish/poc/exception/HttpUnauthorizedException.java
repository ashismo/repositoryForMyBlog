package com.ashish.poc.exception;
public class HttpUnauthorizedException extends RuntimeException {
	private String message = "";
	private String errorCode = "";
	
	public HttpUnauthorizedException(){}
	public HttpUnauthorizedException(String message) {
		this.message = message;
	}
	public HttpUnauthorizedException(String message,String errorCode) {
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