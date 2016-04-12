package com.coop.org.exception;

public class RetailStockEntryException extends RetailException {

	public RetailStockEntryException(){}
	public RetailStockEntryException(String message) {
		this.message = message;
	}
	public RetailStockEntryException(String message,String errorCode) {
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