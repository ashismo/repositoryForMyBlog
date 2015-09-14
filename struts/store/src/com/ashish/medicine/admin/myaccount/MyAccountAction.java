package com.ashish.medicine.admin.myaccount;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.struts2.ServletActionContext;

import com.ashish.medicine.admin.base.MedicineBaseAction;
import com.ashish.medicine.admin.usermanagement.UserManagementBean;
import com.ashish.medicine.admin.usermanagement.UserManagementServiceImpl;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.login.LoginServiceImpl;
import com.ashish.medicine.login.UserBean;
import com.ashish.medicine.util.MedicineConstants;
import com.opensymphony.xwork2.ModelDriven;

public class MyAccountAction extends MedicineBaseAction implements ModelDriven<MyAccountBean> {
	private MyAccountBean myaccountBean = new MyAccountBean();
	private static final long serialVersionUID = -2613425890762568273L;
	private final String SUCCESS_MSG = "Successfully updated";
	private final String FAILED_MSG = "Operation failed!!!!!";
	private String msg;
	private String errMsg;
	
	private int ownerId;
	private String ownerAddr1;
	private String ownerAddr2;
	private String ownerDesc;
	private String ownerName;
	private Timestamp dbAddTs;
	private int dbAddUser;
	private Timestamp dbUpdTs;
	private int dbUpdUser;
	private String emailId;
	private String fax;
	private String mob1;
	private String mob2;
	private String phone1;
	private String phone2;
	private String pin;
	private String state;
	private String website;
	private String shopNo;
	private String licenceNo;
	private String babyFoodLcNo;
	private String shopName;

	//----- User Account attributes ------
	private int userId;
	private String description;
	private String name;
	private String mobile;
	private String phone;
	private String address;
	private String userName;
	private String role;
	private String password;
	private String newPassword;
	private String confirmPassword;
	
//	public MyAccountAction() {
//		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
//		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
//	}
	
	public String myAccount() {
		try {
			new MyAccountServiceImpl().myAccount(myaccountBean);
			BeanUtils.copyProperties(this, myaccountBean);
			setMsg(SUCCESS_MSG);
		} catch (Exception e) {
			setMsg("Unable to fetch account details");
			e.printStackTrace();
		}
		return "success";
	}
	
	public String account() {
		return "success";
	}
	
	public String userManagement() {
		return "success";
	}
	
	public String useraccount() {
		try {
			UserManagementBean userManagementBean = new UserManagementBean();
			new UserManagementServiceImpl().getAllSecurityQuestions(userManagementBean);
			populateDetailsFromUserSession();
			userManagementBean.setUserId(myaccountBean.getUserId());
			myaccountBean.setSecurityQuestionsList(userManagementBean.getSecurityQuestionsList());
			new UserManagementServiceImpl().getAllSecurityAnswers(userManagementBean);
		}catch(AppException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String validateUserCredential() {
		UserBean userBean = new UserBean();
		try {
//			populateDetailsFromUserSession();
			userBean.setUserName(myaccountBean.getUserName());
			userBean.setPassword(myaccountBean.getPassword());
			if(myaccountBean.getPassword() != null && !"".equals(myaccountBean.getPassword().trim())) {
				new LoginServiceImpl().validateLogin(userBean);
				if(!userBean.isLoginSuccessful()) {
					setErrMsg("Current password is wrong");
				}
			}
		} catch (AppException e) {
			setMsg("Unable to Retrieve user account details");
			e.printStackTrace();
		} catch (Exception e) {
			setMsg("Unable to Retrieve user account details");
			e.printStackTrace();
		} 
		return "success";
	}
	
	public String fetchUserAccount() {
		try {
			populateDetailsFromUserSession();
			new MyAccountServiceImpl().fetchUserAccount(myaccountBean);
			BeanUtils.copyProperties(this, myaccountBean);
			// do not display password
			this.setPassword("");
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			setMsg("Unable to Retrieve user account details");
			e.printStackTrace();
		} catch (Exception e) {
			setMsg("Unable to Retrieve user account details");
			e.printStackTrace();
		} finally {
			setMsg(myaccountBean.getMsg());
		}
		return "success";
	}
	
	public String updateUserAccount() {
		try {
			populateDetailsFromUserSession();
			new MyAccountServiceImpl().updateUserAccount(myaccountBean);
		} catch (AppException e) {
			setErrMsg(e.getMsg());
			e.printStackTrace();
		} finally {
			setMsg(myaccountBean.getMsg());
		}
		return "success";
	}

	private void populateDetailsFromUserSession() throws AppException {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request != null) {
			HttpSession session = request.getSession(false);
			if(session != null) {
				UserBean userBean = (UserBean)session.getAttribute(MedicineConstants.USER_PROFILE);
				myaccountBean.setUserName(userBean.getUserName());
				myaccountBean.setUserId(userBean.getUserId());
			} else {
				throw new AppException("Unable to Retrieve/Update user account details");
			}
		}
	}
	
	public String updateAccount() {
		try {
			new MyAccountServiceImpl().updateAccount(myaccountBean);
		} catch (AppException e) {
			setMsg("Unable to update account details");
			e.printStackTrace();
		} finally {
			setMsg(myaccountBean.getMsg());
		}
		return "success";
	}
	
	public MyAccountBean getModel() {
		updateUserDetailsFromSession(myaccountBean);
		return myaccountBean;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerAddr1() {
		return ownerAddr1;
	}

	public void setOwnerAddr1(String ownerAddr1) {
		this.ownerAddr1 = ownerAddr1;
	}

	public String getOwnerAddr2() {
		return ownerAddr2;
	}

	public void setOwnerAddr2(String ownerAddr2) {
		this.ownerAddr2 = ownerAddr2;
	}

	public String getOwnerDesc() {
		return ownerDesc;
	}

	public void setOwnerDesc(String ownerDesc) {
		this.ownerDesc = ownerDesc;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMob1() {
		return mob1;
	}

	public void setMob1(String mob1) {
		this.mob1 = mob1;
	}

	public String getMob2() {
		return mob2;
	}

	public void setMob2(String mob2) {
		this.mob2 = mob2;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getBabyFoodLcNo() {
		return babyFoodLcNo;
	}

	public void setBabyFoodLcNo(String babyFoodLcNo) {
		this.babyFoodLcNo = babyFoodLcNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public MyAccountBean getMyaccountBean() {
		return myaccountBean;
	}

	public void setMyaccountBean(MyAccountBean myaccountBean) {
		this.myaccountBean = myaccountBean;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getSUCCESS_MSG() {
		return SUCCESS_MSG;
	}

	public String getFAILED_MSG() {
		return FAILED_MSG;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
