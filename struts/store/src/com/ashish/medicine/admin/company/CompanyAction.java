package com.ashish.medicine.admin.company;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.struts2.ServletActionContext;

import com.ashish.medicine.admin.base.MedicineBaseAction;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.login.UserBean;
import com.ashish.medicine.util.MedicineConstants;
import com.ashish.medicine.util.UtilServiceImpl;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CompanyAction extends MedicineBaseAction implements ModelDriven<CompanyBean> {
	private CompanyBean companyBean = new CompanyBean();
	private static final long serialVersionUID = -2613425890762568273L;
	private int total;
	private List<CompanyBean> totalRecords; 
	private String msg;
	private final String SUCCESS_MSG = "Successfully updated";
	private final String FAILED_MSG = "Operation failed!!!!!";

	private int companyId;
	private String companyAddr1;
	private String companyAddr2;
	private String companyDesc;
	private String companyName;
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
	
//	public CompanyAction() {
//		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
//		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
//	}
	
	public String companyManagementDetails()
	{
		return "success";		
	}
	
	public String searchCompany()
	{
		try {
			new CompanyServiceImpl().searchCompany(companyBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(companyBean.getMsg());
		}
		totalRecords = companyBean.getSearchCompanyList();
		total = companyBean.getTotal();
		return Action.SUCCESS;		
	}
	
	public String viewCompany()
	{
		try {
			new CompanyServiceImpl().viewCompany(companyBean);
			BeanUtils.copyProperties(this, companyBean);
			setMsg(SUCCESS_MSG);
		} catch (Exception e) {
			setMsg("Unable to view company");
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String addCompanyForm() {
		return ActionSupport.SUCCESS;
	}
	
	public String addOrUpdateCompany()
	{
		System.out.println("Create/Update a new Company");
		try {
			new CompanyServiceImpl().addOrUpdateCompany(companyBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(companyBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String allCompanies()
	{
		System.out.println("Fetch Companies");
		try {
			new CompanyServiceImpl().getAllCompanies(companyBean);
			companyBean.setCompanyList(companyBean.getSearchCompanyList());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(companyBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String deleteCompany()
	{
		System.out.println("Delete Company");
		try {
			new CompanyServiceImpl().deleteCompany(companyBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(companyBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String sellByMonths()
	{
		return "success";		
	}
	
	public CompanyBean getCompanyBean() {
		return companyBean;
	}

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<CompanyBean> getTotalRecords() {
		return totalRecords;
	}

	public List<CompanyBean> getRows() {
		return totalRecords;
	}
	
	public void setTotalRecords(List<CompanyBean> totalRecords) {
		this.totalRecords = totalRecords;
	}

	public CompanyBean getModel() {
		updateUserDetailsFromSession(companyBean);
		return companyBean;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getCompanyAddr1() {
		return companyAddr1;
	}

	public void setCompanyAddr1(String companyAddr1) {
		this.companyAddr1 = companyAddr1;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyAddr2() {
		return companyAddr2;
	}

	public void setCompanyAddr2(String companyAddr2) {
		this.companyAddr2 = companyAddr2;
	}

	public String getCompanyDesc() {
		return companyDesc;
	}

	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
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
