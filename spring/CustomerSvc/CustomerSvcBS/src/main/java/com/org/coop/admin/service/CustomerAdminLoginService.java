package com.org.coop.admin.service;

import com.org.coop.canonical.beans.UIModel;


public interface CustomerAdminLoginService {
	/**
	 * This method verifies if the user is authenticated user
	 * @param username
	 */
	public boolean isUserAuthenticated(String username, String password);
	
	/**
	 * Returns branch details for a given user
	 * @param userName
	 * @return
	 */
	public UIModel getBranchConfig(String userName);
}
