package com.coop.org.exception;

public class PaymentException extends RuntimeException {
	protected String message = "";
	protected String errorCode = "";
	
	public PaymentException(){}
	public PaymentException(String message) {
		this.message = message;
	}
	public PaymentException(String message,String errorCode) {
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