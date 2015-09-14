package com.ashish.medicine.admin.usermanagement;

import com.ashish.medicine.exception.AppException;

public class UserManagementServiceHelperImpl implements UserManagementServiceHelper {

	public void addOrUpdateUser(UserManagementBean userManagementBean) throws AppException {
		new UserManagementDaoImpl().addOrUpdateUser(userManagementBean);
	}

	public void searchUser(UserManagementBean userManagementBean) throws AppException {
		new UserManagementDaoImpl().searchUser(userManagementBean);
	}

	public void viewUser(UserManagementBean userManagementBean) throws AppException {
		new UserManagementDaoImpl().viewUser(userManagementBean);
	}

	public void deleteUser(UserManagementBean userManagementBean) throws AppException {
		new UserManagementDaoImpl().deleteUser(userManagementBean);		
	}

	public void getAllUsers(UserManagementBean userManagementBean) throws AppException {
		new UserManagementDaoImpl().getAllUsers(userManagementBean);		
	}
	
	public void getAllSecurityQuestions(UserManagementBean userManagementBean) throws AppException {
		new UserManagementDaoImpl().getAllSecurityQuestions(userManagementBean);
	}
	
	public void getAllSecurityAnswers(UserManagementBean userManagementBean) throws AppException {
		new UserManagementDaoImpl().getAllSecurityAnswers(userManagementBean);
	}
	
	public void matchSecurityAnswers(UserManagementBean userManagementBean) throws AppException {
		new UserManagementDaoImpl().matchSecurityAnswers(userManagementBean);
	}
}
