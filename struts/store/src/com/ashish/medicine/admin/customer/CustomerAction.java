package com.ashish.medicine.admin.customer;

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

public class CustomerAction extends MedicineBaseAction implements ModelDriven<CustomerBean> {
	private CustomerBean customerBean = new CustomerBean();
	private static final long serialVersionUID = -2613425890762568273L;
	private int total;
	private List<CustomerBean> totalRecords; 
	private String msg;
	private final String SUCCESS_MSG = "Successfully updated";
	private final String FAILED_MSG = "Operation failed!!!!!";

	private int customerId;
	private String customerAddr1;
	private String customerAddr2;
	private String customerDesc;
	private String customerName;
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
	private String desc;
	private String errorMsg;
	
//	public CustomerAction() {
//		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
//		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
//	}
	
	public String customerManagementDetails()
	{
		return "success";		
	}
	
	public String searchCustomer()
	{
		try {
			new CustomerServiceImpl().searchCustomer(customerBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(customerBean.getMsg());
		}
		totalRecords = customerBean.getSearchCustomerList();
		total = customerBean.getTotal();
		return Action.SUCCESS;		
	}
	
	public String viewCustomer()
	{
		try {
			new CustomerServiceImpl().viewCustomer(customerBean);
			BeanUtils.copyProperties(this, customerBean);
			setMsg(SUCCESS_MSG);
		} catch (Exception e) {
			setMsg("Unable to view customer");
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String addCustomerForm() {
		return ActionSupport.SUCCESS;
	}
	
	public String addOrUpdateCustomer()
	{
		System.out.println("Create/Update a new Customer");
		try {
			new CustomerServiceImpl().addOrUpdateCustomer(customerBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(customerBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String allCustomers()
	{
		System.out.println("Fetch Customers");
		try {
			new CustomerServiceImpl().getAllCustomers(customerBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(customerBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String deleteCustomer()
	{
		System.out.println("Delete Customer");
		try {
			new CustomerServiceImpl().deleteCustomer(customerBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(customerBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String customerByCustomerId()
	{
		System.out.println("Customer By Customer Id");
		try {
			new CustomerServiceImpl().getCustomerByCustomerId(customerBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(customerBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String sellByMonths()
	{
		return "success";		
	}
	
	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<CustomerBean> getTotalRecords() {
		return totalRecords;
	}

	public List<CustomerBean> getRows() {
		return totalRecords;
	}
	
	public void setTotalRecords(List<CustomerBean> totalRecords) {
		this.totalRecords = totalRecords;
	}

	public CustomerBean getModel() {
		updateUserDetailsFromSession(customerBean);
		return customerBean;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getCustomerAddr1() {
		return customerAddr1;
	}

	public void setCustomerAddr1(String customerAddr1) {
		this.customerAddr1 = customerAddr1;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerAddr2() {
		return customerAddr2;
	}

	public void setCustomerAddr2(String customerAddr2) {
		this.customerAddr2 = customerAddr2;
	}

	public String getCustomerDesc() {
		return customerDesc;
	}

	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
