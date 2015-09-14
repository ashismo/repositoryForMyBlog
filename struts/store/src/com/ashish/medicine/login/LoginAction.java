package com.ashish.medicine.login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ashish.medicine.admin.base.MedicineBaseAction;
import com.ashish.medicine.admin.usermanagement.UserManagementBean;
import com.ashish.medicine.admin.usermanagement.UserManagementServiceImpl;
import com.ashish.medicine.exception.AppException;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends MedicineBaseAction implements ModelDriven<UserBean> {
	private static final long serialVersionUID = -2613425890762568273L;
	private UserBean userBean = new UserBean();
	private String msg;
	private String errMsg;

	public String home() {
		return "home";		
	}
	
	public String renewSession() {
		return "success";
	}
	
	public String logout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request != null && request.getSession(false) != null) {
			request.getSession(false).invalidate();
		}
		return "logout";		
	}
	
	public String forgotPassword() {
		try {
			UserManagementBean userManagementBean = new UserManagementBean();
			new UserManagementServiceImpl().getAllSecurityQuestions(userManagementBean);
			userBean.setSecurityQuestionsList(userManagementBean.getSecurityQuestionsList());
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String displayPassword() {
		String ans[] = userBean.getAnswer();
		int qId[] = userBean.getQuestionId();
		List<UserManagementBean> securityAnswerList = new ArrayList<UserManagementBean>();
		for(int i = 0; i < qId.length; i++) {
			UserManagementBean userMgmtBean = new UserManagementBean();
			userMgmtBean.setQuestionId(qId[i]);
			userMgmtBean.setAnswer(ans[i]);
			securityAnswerList.add(userMgmtBean);
		}
		
		UserManagementBean userManagementBean = new UserManagementBean();
		userManagementBean.setSecurityQuestionsList(securityAnswerList);
		userManagementBean.setUserName(userBean.getUserName());
		try {
			new UserManagementServiceImpl().matchSecurityAnswers(userManagementBean);
			userBean.setMsg("Your pssword is = " + userManagementBean.getPassword());
		} catch (AppException e) {
			userBean.setMsg(e.getMsg());
			e.printStackTrace();
		}
		return "success";
	}
	public UserBean getModel() {
		return userBean;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
