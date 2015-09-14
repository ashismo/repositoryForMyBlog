package com.ashish.medicine.admin.company;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ashish.medicine.admin.base.MedicineBaseBean;
import com.ashish.medicine.common.bean.States;

public class CompanyBean extends MedicineBaseBean implements Serializable {
	private int companyId;
	private String companyAddr1;
	private String companyAddr2;
	private String companyDesc;
	private String companyName;
	private String emailId;
	private String fax;
	private String mob1;
	private String mob2;
	private String phone1;
	private String phone2;
	private String pin;
	private String state;
	private String website;
	private String msg;
	private List<States> states = new ArrayList<States>();
	
	private Map<String, String> searchMap = new HashMap<String, String>();
	private List<CompanyBean> searchCompanyList = new ArrayList<CompanyBean>();
	private List<CompanyBean> companyList = new ArrayList<CompanyBean>();
	
	// Pagination Parameters
	private int rows;
	private int page;
	private String order;
	private String sort;
	private int total; 
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public List<States> getStates() {
		return states;
	}
	public void setStates(List<States> states) {
		this.states = states;
	}
	public String getCompanyAddr1() {
		return companyAddr1;
	}
	public void setCompanyAddr1(String companyAddr1) {
		this.companyAddr1 = companyAddr1;
	}
	public String getCompanyAddr2() {
		return companyAddr2;
	}
	public void setCompanyAddr2(String companyAddr2) {
		this.companyAddr2 = companyAddr2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
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
	public Map<String, String> getSearchMap() {
		return searchMap;
	}
	public void setSearchMap(Map<String, String> searchMap) {
		this.searchMap = searchMap;
	}
	public List<CompanyBean> getSearchCompanyList() {
		return searchCompanyList;
	}
	public void setSearchCompanyList(List<CompanyBean> searchCompanyList) {
		this.searchCompanyList = searchCompanyList;
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
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyDesc() {
		return companyDesc;
	}
	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
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
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<CompanyBean> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<CompanyBean> companyList) {
		this.companyList = companyList;
	}
	
}
