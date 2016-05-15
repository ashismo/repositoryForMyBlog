package com.coop.org.exception;

public class AdminSvcCommonException extends RuntimeException {
	protected String message = "";
	protected String errorCode = "";
	
	public AdminSvcCommonException(){}
	public AdminSvcCommonException(String message) {
		this.message = message;
	}
	public AdminSvcCommonException(String message,String errorCode) {
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