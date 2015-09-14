package com.ashish.medicine.admin.usermanagement;

import com.ashish.medicine.exception.AppException;

public class UserManagementServiceImpl implements UserManagementService {

	public void addOrUpdateUser(UserManagementBean userManagementBean) throws AppException {
		new UserManagementServiceHelperImpl().addOrUpdateUser(userManagementBean);
	}

	public void searchUser(UserManagementBean userManagementBean) throws AppException {
		new UserManagementServiceHelperImpl().searchUser(userManagementBean);
	}

	public void viewUser(UserManagementBean userManagementBean) throws AppException {
		new UserManagementServiceHelperImpl().viewUser(userManagementBean);
	}

	public void deleteUser(UserManagementBean userManagementBean) throws AppException {
		new UserManagementServiceHelperImpl().deleteUser(userManagementBean);
	}

	public void getAllUsers(UserManagementBean userManagementBean) throws AppException {
		new UserManagementServiceHelperImpl().getAllUsers(userManagementBean);
	}
	
	public void getAllSecurityQuestions(UserManagementBean userManagementBean) throws AppException {
		new UserManagementServiceHelperImpl().getAllSecurityQuestions(userManagementBean);
	}
	
	public void getAllSecurityAnswers(UserManagementBean userManagementBean) throws AppException {
		new UserManagementServiceHelperImpl().getAllSecurityAnswers(userManagementBean);
	}
	public void matchSecurityAnswers(UserManagementBean userManagementBean) throws AppException {
		new UserManagementServiceHelperImpl().matchSecurityAnswers(userManagementBean);
	}
}
