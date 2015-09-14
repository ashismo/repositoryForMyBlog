package com.ashish.medicine.login;

import com.ashish.medicine.exception.AppException;

public class LoginServiceHelperImpl implements LoginServiceHelper {

	public void validateLogin(UserBean userBean) throws AppException {
		new LoginDaoImpl().validateLogin(userBean);
	}

}
