package com.ashish.medicine.exception;

public class AppException extends Exception {
	public String msg;
	public AppException() {}
	public AppException(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
