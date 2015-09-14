package com.ashish.medicine.admin.contacts;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ashish.medicine.admin.base.MedicineBaseBean;
import com.ashish.medicine.common.bean.States;

public class ContactsBean extends MedicineBaseBean implements Serializable {
	private int contactId;
	private String contactDesc;
	private String contactName;
	private String contactEmail;
	private String contactFax;
	private String contactNo1;
	private String contactNo2;
	private String contactDate;
	private Date contactDateActual;
	private String msg;
	private String errMsg;
	
	private Map<String, String> searchMap = new HashMap<String, String>();
	private List<ContactsBean> searchContactsList = new ArrayList<ContactsBean>();
	private List<ContactsBean> contactsList = new ArrayList<ContactsBean>();
	
	// Pagination Parameters
	private int rows;
	private int page;
	private String order;
	private String sort;
	private int total;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, String> getSearchMap() {
		return searchMap;
	}
	public void setSearchMap(Map<String, String> searchMap) {
		this.searchMap = searchMap;
	}
	public List<ContactsBean> getSearchContactsList() {
		return searchContactsList;
	}
	public void setSearchContactsList(List<ContactsBean> searchContactsList) {
		this.searchContactsList = searchContactsList;
	}
	public List<ContactsBean> getContactsList() {
		return contactsList;
	}
	public void setContactsList(List<ContactsBean> contactsList) {
		this.contactsList = contactsList;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	} 
}
