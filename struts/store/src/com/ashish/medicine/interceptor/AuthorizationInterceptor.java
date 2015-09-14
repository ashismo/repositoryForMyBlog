package com.ashish.medicine.interceptor;

import java.util.HashSet;
import java.util.Set;

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

public class AuthorizationInterceptor implements Interceptor {
	private Set<String> userAccessSet = new HashSet<String>();
	
	public AuthorizationInterceptor() {
		String superAdminExclusiveUrls[] = MedicineConstants.SUPER_ADMIN_EXCLUSIVE_URLS;
		for(String uri : superAdminExclusiveUrls) {
			uri = MedicineConstants.CONTEXT_PATH + uri;
			userAccessSet.add(uri);
		}
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String result = "success";
		try {
			preInvokeAction(request);
	
			result = invocation.invoke();
	 
		} catch (AppException e) {
			e.printStackTrace();
			result = "unauthorizedAccess";
		} catch (Exception e) {
			e.printStackTrace();
			result = "unauthorizedAccess";
		}
		return result;
	}

	private void preInvokeAction(HttpServletRequest request) throws AppException {
		HttpSession session = request.getSession(false);
		if(session != null && (UserBean)session.getAttribute(MedicineConstants.USER_PROFILE) != null) {
			UserBean uBean = (UserBean)session.getAttribute(MedicineConstants.USER_PROFILE);
			if(!(MedicineConstants.SUPER_ADMIN.equals(uBean.getUser().getRole())) && userAccessSet.contains(request.getRequestURI())) {
				throw new AppException("Unauthorized Access to this URL: " + request.getRequestURI());
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
