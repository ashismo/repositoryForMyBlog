package com.ashish.medicine.admin.myaccount;

import com.ashish.medicine.exception.AppException;

public class MyAccountServiceHelperImpl implements MyAccountServiceHelper {

	public void myAccount(MyAccountBean myaccountBean) throws AppException {
		new MyAccountDaoImpl().myAccount(myaccountBean);
		
	}
	
	public void updateAccount(MyAccountBean myaccountBean) throws AppException {
		new MyAccountDaoImpl().updateAccount(myaccountBean);
		
	}
	
	public void fetchUserAccount(MyAccountBean myaccountBean) throws AppException {
		new MyAccountDaoImpl().fetchUserAccount(myaccountBean);
		
	}
	
	public void updateUserAccount(MyAccountBean myaccountBean) throws AppException {
		new MyAccountDaoImpl().updateUserAccount(myaccountBean);
		
	}
}
