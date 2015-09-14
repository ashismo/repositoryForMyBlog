package com.ashish.medicine.admin.wholeseller;

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
import com.ashish.medicine.admin.medicine.MedicineServiceImpl;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.util.UtilServiceImpl;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class WholeSellerAction extends MedicineBaseAction implements ModelDriven<WholeSellerBean> {
	private WholeSellerBean wholesellerBean = new WholeSellerBean();
	private static final long serialVersionUID = -2613425890762568273L;
	private int total;
	private List<WholeSellerBean> totalRecords; 
	private String msg;
	private final String SUCCESS_MSG = "Successfully updated";
	private final String FAILED_MSG = "Operation failed!!!!!";

	private int wholesellerId;
	private String wholesellerAddr1;
	private String wholesellerAddr2;
	private String wholesellerDesc;
	private String wholesellerName;
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
	
//	public WholeSellerAction() {
//		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
//		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
//	}
	
	public String wholesellerManagementDetails()
	{
		return "success";		
	}
	
	public String searchWholeSeller()
	{
		try {
			new WholeSellerServiceImpl().searchWholeSeller(wholesellerBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(wholesellerBean.getMsg());
		}
		totalRecords = wholesellerBean.getSearchWholeSellerList();
		total = wholesellerBean.getTotal();
		return Action.SUCCESS;		
	}
	
	public String viewWholeSeller()
	{
		try {
			new WholeSellerServiceImpl().viewWholeSeller(wholesellerBean);
			BeanUtils.copyProperties(this, wholesellerBean);
			setMsg(SUCCESS_MSG);
		} catch (Exception e) {
			setMsg("Unable to view wholeseller");
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String addWholeSellerForm() {
		return ActionSupport.SUCCESS;
	}
	
	public String addOrUpdateWholeSeller()
	{
		System.out.println("Create/Update a new WholeSeller");
		try {
			new WholeSellerServiceImpl().addOrUpdateWholeSeller(wholesellerBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(wholesellerBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String deleteWholeSeller()
	{
		System.out.println("Delete WholeSeller");
		try {
			new WholeSellerServiceImpl().deleteWholeSeller(wholesellerBean);
		} catch (Exception e) {
			e.printStackTrace();
			wholesellerBean.setMsg("Unable to delete - The Wholeseller may be referred by some MR");
		} finally {
			setMsg(wholesellerBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String allWholesellers()
	{
		try {
			new WholeSellerServiceImpl().getAllWholesellers(wholesellerBean);
			BeanUtils.copyProperties(this, wholesellerBean);
			setMsg(SUCCESS_MSG);
		} catch (Exception e) {
			setMsg("Unable to view company");
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String sellByMonths()
	{
		return "success";		
	}
	
	public WholeSellerBean getWholesellerBean() {
		return wholesellerBean;
	}

	public void setWholesellerBean(WholeSellerBean wholesellerBean) {
		this.wholesellerBean = wholesellerBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<WholeSellerBean> getTotalRecords() {
		return totalRecords;
	}

	public List<WholeSellerBean> getRows() {
		return totalRecords;
	}
	
	public void setTotalRecords(List<WholeSellerBean> totalRecords) {
		this.totalRecords = totalRecords;
	}

	public WholeSellerBean getModel() {
		updateUserDetailsFromSession(wholesellerBean);
		return wholesellerBean;
	}

	public String getWholesellerName() {
		return wholesellerName;
	}

	public void setWholesellerName(String wholesellerName) {
		this.wholesellerName = wholesellerName;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getWholesellerAddr1() {
		return wholesellerAddr1;
	}

	public void setWholesellerAddr1(String wholesellerAddr1) {
		this.wholesellerAddr1 = wholesellerAddr1;
	}

	public int getWholesellerId() {
		return wholesellerId;
	}

	public void setWholesellerId(int wholesellerId) {
		this.wholesellerId = wholesellerId;
	}

	public String getWholesellerAddr2() {
		return wholesellerAddr2;
	}

	public void setWholesellerAddr2(String wholesellerAddr2) {
		this.wholesellerAddr2 = wholesellerAddr2;
	}

	public String getWholesellerDesc() {
		return wholesellerDesc;
	}

	public void setWholesellerDesc(String wholesellerDesc) {
		this.wholesellerDesc = wholesellerDesc;
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
}
