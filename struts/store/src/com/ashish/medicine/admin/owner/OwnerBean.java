package com.ashish.medicine.admin.owner;

import java.util.ArrayList;
import java.util.List;

import com.ashish.medicine.admin.base.MedicineBaseBean;

public class OwnerBean extends MedicineBaseBean {
	private int ownerId;
	private String ownerAddr1;
	private String ownerAddr2;
	private String ownerDesc;
	private String ownerName;
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
	private String shopName;
	private String licenceNo;
	private String babyFoodLcNo;
	
	private List<OwnerBean> searchOwnerList = new ArrayList<OwnerBean>();
	
	// Pagination Parameters
	private int rows;
	private int page;
	private String order;
	private String sort;
	
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
	public List<OwnerBean> getSearchOwnerList() {
		return searchOwnerList;
	}
	public void setSearchOwnerList(List<OwnerBean> searchOwnerList) {
		this.searchOwnerList = searchOwnerList;
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
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
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
}
