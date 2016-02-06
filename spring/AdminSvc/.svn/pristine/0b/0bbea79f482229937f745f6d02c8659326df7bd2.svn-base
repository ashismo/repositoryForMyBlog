package com.org.coop.admin.servicehelper;

import java.util.List;
import java.util.Map;

import com.coop.org.exception.SecurityQuestionNotSetException;
import com.org.coop.canonical.beans.SecurityQnA;

public interface AdminLoginServiceHelper {
	/**
	 * This method returns list of roles for a given user name
	 */
	public List<String> getRole(String username);
	
	/**
	 * This method returns list of permissions for a given user name
	 */
	public List<String> getRolePermissions(String username);
	
	/**
	 * Once login is successful this method resets the counter
	 * @param username
	 */
	public void successfulLogin(String username);
	
	/**
	 * Once login is successful this method resets the counter
	 * @param username
	 */
	public void unsuccessfulLogin(String username);
	
	/**
	 * Once login is successful this method will bring OTP or Security question
	 * @param username
	 */
	public void setOTP(String username);
	
	
	/**
	 * Once login is successful this method will validate entered OTP
	 * @param username
	 */
	public boolean isOTPMatched(String username, String otp);
	
	/**
	 * OTP will be resent based on the maximum threshold value subscribed by the branch. 
	 * If it reaches threshold value then it will return false
	 * @param username
	 */
	public boolean resendOTP(String username);
	
	/**
	 * Once user is logged in then expire the OTP
	 * @param username
	 */
	public void expireOTP(String username);
	
	/**
	 * This method decides if single step login/otp based/security based question is subscribed for the branch 
	 * @param username
	 */
	public String checkOTPOrSecurityQuestion(String username) throws SecurityQuestionNotSetException;
	
	/**
	 * Once login is successful this method will validate entered Security questions
	 * @param username
	 */
	public boolean isSecurityQuestionMatched(String username, List<SecurityQnA> securityQnAList);
	
	/**
	 * Once login is successful this method will get random question from the questions have been set
	 * @param username
	 */
	public List<SecurityQnA> getRandomSecurityQuestion(String username);
	
	/**
	 * This method checks if the account is locked
	 * @param username
	 */
	public boolean isAccountLocked(String username);
	
	/**
	 * This method verifies if the user is authenticated user
	 * @param username
	 */
	public boolean isUserAuthenticated(String username, String password);
}
