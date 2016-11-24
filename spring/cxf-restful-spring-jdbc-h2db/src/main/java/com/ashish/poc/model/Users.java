package com.ashish.poc.model;

import java.sql.Timestamp;
import java.util.List;

public class Users {
	private int userId;
	private String username;
	private String password;
	private String name;
	private String email;
	private Integer roleId;
	private String createUser;
	private String updateUser;
	private Timestamp createDate;
	private Timestamp updateDate;
	
	private Role role;
	private List<UserUrlDtl> userUrlDtls;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public List<UserUrlDtl> getUserUrlDtls() {
		return userUrlDtls;
	}
	public void setUserUrlDtls(List<UserUrlDtl> userUrlDtls) {
		this.userUrlDtls = userUrlDtls;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username
				+ ", password=" + password + ", name=" + name + ", email="
				+ email + ", roleId=" + roleId + ", createUser=" + createUser
				+ ", updateUser=" + updateUser + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
}
