package com.ashish.medicine.admin.base;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.FileConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.struts2.ServletActionContext;

import com.ashish.medicine.login.UserBean;
import com.ashish.medicine.util.MedicineConstants;

public abstract class MedicineBaseAction {
	
	public MedicineBaseAction() {
		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		ConvertUtils.register(new FileConverter(null), File.class);
	}
	protected void updateUserDetailsFromSession(MedicineBaseBean objBean) {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request != null) {
			HttpSession session = request.getSession(false);
			if(session != null) {
				UserBean uBean = (UserBean)session.getAttribute(MedicineConstants.USER_PROFILE);
				objBean.setDbAddUser(uBean.getUserId());
				objBean.setDbUpdUser(uBean.getUserId());
			}
		}
	}
}
