package com.ashish.medicine.login;

import com.ashish.medicine.exception.AppException;

public interface LoginServiceHelper {
	public void validateLogin(UserBean userBean) throws AppException;
}
