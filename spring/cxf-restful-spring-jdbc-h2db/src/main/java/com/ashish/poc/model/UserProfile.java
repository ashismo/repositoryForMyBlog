package com.ashish.poc.model;

public class UserProfile {
	protected String userName;
	protected String password;
	protected String role;
	protected String errorMsg;
	protected boolean isUserAuthenticated;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public boolean isUserAuthenticated() {
		return isUserAuthenticated;
	}
	public void setUserAuthenticated(boolean isUserAuthenticated) {
		this.isUserAuthenticated = isUserAuthenticated;
	}
	
	
}
