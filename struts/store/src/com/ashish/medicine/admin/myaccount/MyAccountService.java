package com.ashish.medicine.admin.myaccount;

import com.ashish.medicine.exception.AppException;

public interface MyAccountService {
	public void myAccount(MyAccountBean myaccountBean) throws AppException;
	public void updateAccount(MyAccountBean myaccountBean) throws AppException;
	
	public void fetchUserAccount(MyAccountBean myaccountBean) throws AppException;
	public void updateUserAccount(MyAccountBean myaccountBean) throws AppException;
}
