package com.ashish.medicine.admin.buy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ashish.medicine.admin.company.CompanyBean;

public class BuyBean {
	private int buyId;
	private String buyAddr1;
	private String buyAddr2;
	private String buyDesc;
	private String buyName;
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
	
	private List<BuyBean> searchBuyList = new ArrayList<BuyBean>();
	
	// Pagination Parameters
	private int rows;
	private int page;
	private String buy;
	private String sort;
	public int getBuyId() {
		return buyId;
	}
	public void setBuyId(int buyId) {
		this.buyId = buyId;
	}
	public String getBuyAddr1() {
		return buyAddr1;
	}
	public void setBuyAddr1(String buyAddr1) {
		this.buyAddr1 = buyAddr1;
	}
	public String getBuyAddr2() {
		return buyAddr2;
	}
	public void setBuyAddr2(String buyAddr2) {
		this.buyAddr2 = buyAddr2;
	}
	public String getBuyDesc() {
		return buyDesc;
	}
	public void setBuyDesc(String buyDesc) {
		this.buyDesc = buyDesc;
	}
	public String getBuyName() {
		return buyName;
	}
	public void setBuyName(String buyName) {
		this.buyName = buyName;
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
	public List<BuyBean> getSearchBuyList() {
		return searchBuyList;
	}
	public void setSearchBuyList(List<BuyBean> searchBuyList) {
		this.searchBuyList = searchBuyList;
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
	public String getBuy() {
		return buy;
	}
	public void setBuy(String buy) {
		this.buy = buy;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
