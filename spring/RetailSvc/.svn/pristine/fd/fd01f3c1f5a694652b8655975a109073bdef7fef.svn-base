package com.org.coop.retail.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.servicehelper.RetailAdminLoginServiceHelperImpl;

@Service
public class RetailAdminLoginServiceImpl {
	private static final Logger log = Logger.getLogger(RetailAdminLoginServiceImpl.class); 

	@Autowired
	private RetailAdminLoginServiceHelperImpl retailAdminServiceHelperImpl;
	
	
	/**
	 * This method verifies if the user is authenticated user
	 * @param username
	 */
	public boolean isUserAuthenticated(String username, String password) {
		return retailAdminServiceHelperImpl.isUserAuthenticated(username, password);
	}
	
	public UIModel getBranchConfig(String userName) {
		
		return retailAdminServiceHelperImpl.getBranchConfig(userName);
	}
}
