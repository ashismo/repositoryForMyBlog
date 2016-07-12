package com.ashish.beans;

public class CreditCardBean {
	private String userName;
	private int userId;
	private String firstName;
	private String lastName;
	private double outstanding;
	private double paid;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(double outstanding) {
		this.outstanding = outstanding;
	}
	public double getPaid() {
		return paid;
	}
	public void setPaid(double paid) {
		this.paid = paid;
	}
	
	
}
