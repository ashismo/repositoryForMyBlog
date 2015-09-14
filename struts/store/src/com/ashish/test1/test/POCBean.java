package com.ashish.test1.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ashish.medicine.common.bean.States;

public class POCBean implements Serializable {
	private long companyId;
	private String companyName;
	private String companyAddr1;
	private String companyAddr2;
	private String state;
	private String pin;
	private String mobile1;
	private String mobile2;
	private String phone1;
	private String phone2;
	private List<States> states = new ArrayList<States>();
	
	private Map<String, String> searchMap = new HashMap<String, String>();
	private List<POCBean> searchCompanyList = new ArrayList<POCBean>();
	
	// Pagination Parameters
	private int rows;
	private int page;
	private String order;
	private String sort;
	
	
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
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
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
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
	public List<POCBean> getSearchCompanyList() {
		return searchCompanyList;
	}
	public void setSearchCompanyList(List<POCBean> searchCompanyList) {
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
}
