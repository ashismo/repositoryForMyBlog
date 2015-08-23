package com.ashish.beans;

public class DebitCardBean {
	private String userName;
	private int userId;
	private String firstName;
	private String lastName;
	private double currentBal;
	private double transferAmt;
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
	public double getCurrentBal() {
		return currentBal;
	}
	public void setCurrentBal(double currentBal) {
		this.currentBal = currentBal;
	}
	public double getTransferAmt() {
		return transferAmt;
	}
	public void setTransferAmt(double transferAmt) {
		this.transferAmt = transferAmt;
	}
}
