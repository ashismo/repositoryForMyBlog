package com.ashish.medicine.interceptor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.login.LoginServiceImpl;
import com.ashish.medicine.login.UserBean;
import com.ashish.medicine.util.MedicineConstants;
import com.ashish.medicine.util.UserProfile;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SessionInterceptor implements Interceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String result = "success";
		try {
			checkApplicationValidity();
			preInvokeAction(request);
	
			result = invocation.invoke();
		} catch (AppException e) {
			if(MedicineConstants.VALIDITY_EXPIRED.equals(e.getMsg())) {
				result = "applicationExpired";
			} else {
				e.printStackTrace();
				result = "loginFailure";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "loginFailure";
		}
		return result;
	}

	private void checkApplicationValidity() throws AppException {
		try {
			if(("-1".hashCode() + "").equals(MedicineConstants.VALID_TILL)) {
				return;
			}
			Date validTill = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH).parse(MedicineConstants.VALID_TILL);
			Date today = new Date();
			if(today.after(validTill)){
				throw new AppException(MedicineConstants.VALIDITY_EXPIRED);
        	}
			if(today.before(validTill)){
				return;
        	}
		} catch (ParseException e) {
			throw new AppException(MedicineConstants.VALIDITY_EXPIRED);
		} catch (AppException e) {
			throw e;
		}
	}
	private void preInvokeAction(HttpServletRequest request) throws AppException {
		
		HttpSession session = null;
		if(request != null) {
			session = request.getSession(false);
			if(session == null) {
				session = request.getSession(true);
				session.setMaxInactiveInterval(1800); // keep active for 30mins
			}
			if(session != null && session.isNew()) {
				//System.out.println("Redirect to login page");
				// find user name and password
				String username = request.getParameter("userName");
				String password = request.getParameter("password");
				// validate credential.
				UserBean userBean = new UserBean();
				userBean.setUserName(username);
				userBean.setPassword(password);
				new LoginServiceImpl().validateLogin(userBean);
				// invalidate session and redirect to login page
				if(!userBean.isLoginSuccessful()) {
					session.invalidate();
					throw new AppException("Login unsuccessful");
				} else {
					session.setAttribute(MedicineConstants.LOGIN_SUCCESSFUL, true);
					session.setAttribute(MedicineConstants.USER_PROFILE, userBean);
				}
			}
			if(session.getAttribute(MedicineConstants.LOGIN_SUCCESSFUL) == null || 
					!(Boolean)session.getAttribute(MedicineConstants.LOGIN_SUCCESSFUL)) {
				if(session != null) session.invalidate();
				throw new AppException("Login unsuccessful");
			}
			
			// Check if userprofile is found in the session
			UserBean userBean = (UserBean)session.getAttribute(MedicineConstants.USER_PROFILE);
			if(userBean != null && userBean.getUserName() != null) {
				// Login is fine
			} else {
				session.removeAttribute(MedicineConstants.LOGIN_SUCCESSFUL);
				if(session != null) session.invalidate();
				throw new AppException("Login unsuccessful");
			}
		}
	}
	@Override
	public void destroy() {
		System.out.println("CustomInterceptor destroy() is called...");
		
	}

	@Override
	public void init() {
		System.out.println("CustomInterceptor init() is called...");
	}
	
}
