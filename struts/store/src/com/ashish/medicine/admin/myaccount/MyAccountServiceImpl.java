package com.ashish.medicine.admin.myaccount;

import com.ashish.medicine.exception.AppException;

public class MyAccountServiceImpl implements MyAccountService {

	public void myAccount(MyAccountBean myaccountBean) throws AppException {
		new MyAccountServiceHelperImpl().myAccount(myaccountBean);
		
	}
	
	public void updateAccount(MyAccountBean myaccountBean) throws AppException {
		new MyAccountServiceHelperImpl().updateAccount(myaccountBean);
		
	}
	
	
	public void fetchUserAccount(MyAccountBean myaccountBean) throws AppException {
		new MyAccountServiceHelperImpl().fetchUserAccount(myaccountBean);
		
	}
	
	public void updateUserAccount(MyAccountBean myaccountBean) throws AppException {
		new MyAccountServiceHelperImpl().updateUserAccount(myaccountBean);
		
	}

}
