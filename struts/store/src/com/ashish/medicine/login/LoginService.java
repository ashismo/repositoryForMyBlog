package com.ashish.medicine.login;

import com.ashish.medicine.exception.AppException;

public interface LoginService {
	public void validateLogin(UserBean userBean) throws AppException;
}
