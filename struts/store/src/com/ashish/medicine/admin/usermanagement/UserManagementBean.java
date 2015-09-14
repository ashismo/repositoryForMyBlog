package com.ashish.medicine.admin.usermanagement;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ashish.medicine.admin.base.MedicineBaseBean;
import com.ashish.medicine.common.bean.States;

public class UserManagementBean extends MedicineBaseBean implements Serializable {
	private int userId;
	private String address;
	private String description;
	private String emailId;
	private String mobile;
	private String name;
	private String password;
	private String confirmPassword;
	private String phone;
	private String role;
	private String userName;
	private String msg;
	private String errMsg;
	private List<States> states = new ArrayList<States>();
	
	private int questionId;
	private String question;
	private String answer;
	private List<UserManagementBean> securityQuestionsList = new ArrayList<UserManagementBean>();
	
	private Map<String, String> searchMap = new HashMap<String, String>();
	private List<UserManagementBean> searchUserList = new ArrayList<UserManagementBean>();
	private List<UserManagementBean> userList = new ArrayList<UserManagementBean>();
	
	// Pagination Parameters
	private int rows;
	private int page;
	private String order;
	private String sort;
	private int total; 
	
	public List<States> getStates() {
		return states;
	}
	public void setStates(List<States> states) {
		this.states = states;
	}
	public Map<String, String> getSearchMap() {
		return searchMap;
	}
	public void setSearchMap(Map<String, String> searchMap) {
		this.searchMap = searchMap;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<UserManagementBean> getSearchUserList() {
		return searchUserList;
	}
	public void setSearchUserList(List<UserManagementBean> searchUserList) {
		this.searchUserList = searchUserList;
	}
	public List<UserManagementBean> getUserList() {
		return userList;
	}
	public void setUserList(List<UserManagementBean> userList) {
		this.userList = userList;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<UserManagementBean> getSecurityQuestionsList() {
		return securityQuestionsList;
	}
	public void setSecurityQuestionsList(
			List<UserManagementBean> securityQuestionsList) {
		this.securityQuestionsList = securityQuestionsList;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
