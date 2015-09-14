package com.ashish.medicine.admin.usermanagement;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.ashish.medicine.admin.base.MedicineBaseAction;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.util.UtilServiceImpl;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserManagementAction extends MedicineBaseAction implements ModelDriven<UserManagementBean> {
	private UserManagementBean userManagementBean = new UserManagementBean();
	private static final long serialVersionUID = -2613425890762568273L;
	private int total;
	private List<UserManagementBean> totalRecords; 
	private String msg;
	private String errMsg;
	private final String SUCCESS_MSG = "Successfully updated";
	private final String FAILED_MSG = "Operation failed!!!!!";

	private int userId;
	private String address;
	private String description;
	private String emailId;
	private String mobile;
	private String name;
	private String password;
	private String confirmPassword;
	private String phone;
	private String role;
	private String userName;
	private Timestamp dbAddTs;
	private int dbAddUser;
	private Timestamp dbUpdTs;
	private int dbUpdUser;
	
//	public UserManagementAction() {
//		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
//		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
//	}
	
	public String userManagement()
	{
		try {
			new UserManagementServiceImpl().getAllSecurityQuestions(userManagementBean);
		}catch(AppException e) {
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String searchUser()
	{
		try {
			new UserManagementServiceImpl().searchUser(userManagementBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(userManagementBean.getMsg());
		}
		totalRecords = userManagementBean.getSearchUserList();
		total = userManagementBean.getTotal();
		return Action.SUCCESS;		
	}
	
	public String viewUser()
	{
		try {
			new UserManagementServiceImpl().viewUser(userManagementBean);
			BeanUtils.copyProperties(this, userManagementBean);
			
			setMsg(SUCCESS_MSG);
		} catch (Exception e) {
			setMsg("Unable to view user");
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String addUserForm() {
		return ActionSupport.SUCCESS;
	}
	
	public String addOrUpdateUser()
	{
		System.out.println("Create/Update a new User");
		try {
			new UserManagementServiceImpl().addOrUpdateUser(userManagementBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(userManagementBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String allUsers()
	{
		System.out.println("Fetch Users");
		try {
			new UserManagementServiceImpl().getAllUsers(userManagementBean);
			userManagementBean.setUserList(userManagementBean.getSearchUserList());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(userManagementBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String deleteUser()
	{
		System.out.println("Delete User");
		try {
			new UserManagementServiceImpl().deleteUser(userManagementBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(userManagementBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String sellByMonths()
	{
		return "success";		
	}
	
	public UserManagementBean getUserBean() {
		return userManagementBean;
	}

	public void setUserBean(UserManagementBean userManagementBean) {
		this.userManagementBean = userManagementBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<UserManagementBean> getTotalRecords() {
		return totalRecords;
	}

	public List<UserManagementBean> getRows() {
		return totalRecords;
	}
	
	public void setTotalRecords(List<UserManagementBean> totalRecords) {
		this.totalRecords = totalRecords;
	}

	public UserManagementBean getModel() {
		updateUserDetailsFromSession(userManagementBean);
		return userManagementBean;
	}


	public Timestamp getDbAddTs() {
		return dbAddTs;
	}

	public void setDbAddTs(Timestamp dbAddTs) {
		this.dbAddTs = dbAddTs;
	}

	public int getDbAddUser() {
		return dbAddUser;
	}

	public void setDbAddUser(int dbAddUser) {
		this.dbAddUser = dbAddUser;
	}

	public Timestamp getDbUpdTs() {
		return dbUpdTs;
	}

	public void setDbUpdTs(Timestamp dbUpdTs) {
		this.dbUpdTs = dbUpdTs;
	}

	public int getDbUpdUser() {
		return dbUpdUser;
	}

	public void setDbUpdUser(int dbUpdUser) {
		this.dbUpdUser = dbUpdUser;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSUCCESS_MSG() {
		return SUCCESS_MSG;
	}

	public String getFAILED_MSG() {
		return FAILED_MSG;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
