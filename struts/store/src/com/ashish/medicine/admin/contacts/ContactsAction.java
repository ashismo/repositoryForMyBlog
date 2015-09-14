package com.ashish.medicine.admin.contacts;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.ashish.medicine.admin.base.MedicineBaseAction;
import com.ashish.medicine.exception.AppException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ContactsAction extends MedicineBaseAction implements ModelDriven<ContactsBean> {
	private ContactsBean contactsBean = new ContactsBean();
	private static final long serialVersionUID = -2613425890762568273L;
	private int total;
	private List<ContactsBean> totalRecords; 
	private String msg;
	private final String SUCCESS_MSG = "Successfully updated";
	private final String FAILED_MSG = "Operation failed!!!!!";

	private Timestamp dbAddTs;
	private int dbAddUser;
	private Timestamp dbUpdTs;
	private int dbUpdUser;

	private int contactId;
	private String contactDesc;
	private String contactName;
	private String contactEmail;
	private String contactFax;
	private String contactNo1;
	private String contactNo2;
	private String contactDate;
	private Date contactDateActual;
	
//	public ContactsAction() {
//		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
//		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
//	}
	
	public String contactsManagementDetails()
	{
		return "success";		
	}
	
	public String searchContacts()
	{
		try {
			new ContactsServiceImpl().searchContacts(contactsBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(contactsBean.getMsg());
		}
		totalRecords = contactsBean.getSearchContactsList();
		total = contactsBean.getTotal();
		return Action.SUCCESS;		
	}
	
	public String viewContacts()
	{
		try {
			new ContactsServiceImpl().viewContacts(contactsBean);
			BeanUtils.copyProperties(this, contactsBean);
			setMsg(SUCCESS_MSG);
		} catch (Exception e) {
			setMsg("Unable to view contacts");
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String addContactsForm() {
		return ActionSupport.SUCCESS;
	}
	
	public String addOrUpdateContacts()
	{
		System.out.println("Create/Update a new Contacts");
		try {
			new ContactsServiceImpl().addOrUpdateContacts(contactsBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(contactsBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String allCompanies()
	{
		System.out.println("Fetch Companies");
		try {
			new ContactsServiceImpl().getAllCompanies(contactsBean);
			contactsBean.setContactsList(contactsBean.getSearchContactsList());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(contactsBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String deleteContacts()
	{
		System.out.println("Delete Contacts");
		try {
			new ContactsServiceImpl().deleteContacts(contactsBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(contactsBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String sellByMonths()
	{
		return "success";		
	}
	
	public ContactsBean getContactsBean() {
		return contactsBean;
	}

	public void setContactsBean(ContactsBean contactsBean) {
		this.contactsBean = contactsBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<ContactsBean> getTotalRecords() {
		return totalRecords;
	}

	public List<ContactsBean> getRows() {
		return totalRecords;
	}
	
	public void setTotalRecords(List<ContactsBean> totalRecords) {
		this.totalRecords = totalRecords;
	}

	public ContactsBean getModel() {
		updateUserDetailsFromSession(contactsBean);
		return contactsBean;
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getContactDesc() {
		return contactDesc;
	}

	public void setContactDesc(String contactDesc) {
		this.contactDesc = contactDesc;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactFax() {
		return contactFax;
	}

	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}

	public String getContactDate() {
		return contactDate;
	}

	public void setContactDate(String contactDate) {
		this.contactDate = contactDate;
	}

	public Date getContactDateActual() {
		return contactDateActual;
	}

	public void setContactDateActual(Date contactDateActual) {
		this.contactDateActual = contactDateActual;
	}

	public String getContactNo1() {
		return contactNo1;
	}

	public void setContactNo1(String contactNo1) {
		this.contactNo1 = contactNo1;
	}

	public String getContactNo2() {
		return contactNo2;
	}

	public void setContactNo2(String contactNo2) {
		this.contactNo2 = contactNo2;
	}
}
