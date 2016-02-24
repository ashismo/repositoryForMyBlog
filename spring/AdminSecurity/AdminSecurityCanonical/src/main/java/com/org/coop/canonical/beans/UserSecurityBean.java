package com.org.coop.canonical.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserSecurityBean {
	private int userSecId;
	private int userId;
	private int questionId;
	private String answer;
	private String deleteInd;
	private Date startDate;
	private Date endDate;
	private String createUser;
	private String updateUser;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userSecId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSecurityBean other = (UserSecurityBean) obj;
		if (userSecId != other.userSecId)
			return false;
		return true;
	}
	public int getUserSecId() {
		return userSecId;
	}
	public void setUserSecId(int userSecId) {
		this.userSecId = userSecId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getDeleteInd() {
		return deleteInd;
	}
	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
