package com.ashish.medicine.admin.usermanagement;

import com.ashish.medicine.entity.Company;
import com.ashish.medicine.entity.User;
import com.ashish.medicine.exception.AppException;

public interface UserManagementDao {
	public void addOrUpdateUser(UserManagementBean userManagementBean) throws AppException;
	public void getAllUsers(UserManagementBean userManagementBean) throws AppException;
	public void searchUser(UserManagementBean userManagementBean) throws AppException;
	public void viewUser(UserManagementBean userManagementBean) throws AppException;
	public void deleteUser(UserManagementBean userManagementBean) throws AppException;
	public User getUserByUserId(UserManagementBean userManagementBean) throws AppException;
	public void getAllSecurityQuestions(UserManagementBean userManagementBean) throws AppException;
	public void getAllSecurityAnswers(UserManagementBean userManagementBean) throws AppException;
	public void matchSecurityAnswers(UserManagementBean userManagementBean) throws AppException;
}
