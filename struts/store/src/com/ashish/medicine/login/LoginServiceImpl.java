package com.ashish.medicine.login;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

import com.ashish.medicine.exception.AppException;

public class LoginServiceImpl implements LoginService {
	public LoginServiceImpl() {
		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
	}
	public void validateLogin(UserBean userBean) throws AppException {
		try {
			String enteredPassword = userBean.getPassword();
			new LoginServiceHelperImpl().validateLogin(userBean);
			// Validate credential
			if(enteredPassword != null && userBean.getUser() != null &&
					enteredPassword.equals(userBean.getUser().getPassword())) {
				userBean.setLoginSuccessful(true);
			} else {
				userBean.setLoginSuccessful(false);
			}
		} catch (AppException e) {
			userBean.setLoginSuccessful(false);
		}
	}

}
