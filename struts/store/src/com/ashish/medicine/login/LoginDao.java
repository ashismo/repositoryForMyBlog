package com.ashish.medicine.login;

import com.ashish.medicine.exception.AppException;

public interface LoginDao {
	public void validateLogin(UserBean userBean) throws AppException;
}
