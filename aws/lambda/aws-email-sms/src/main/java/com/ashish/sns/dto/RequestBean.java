package com.ashish.sns.dto;

public class RequestBean {
	private String recipient;
	private String message;
	private String senderId;
	private String subject;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	@Override
	public String toString() {
		return "RequestBean [recipient=" + recipient + ", message=" + message + ", senderId=" + senderId + ", subject="
				+ subject + "]";
	}
}
