package com.ashish.medicine.admin.medRep;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ashish.medicine.admin.base.MedicineBaseBean;
import com.ashish.medicine.common.bean.States;

public class MedRepBean extends MedicineBaseBean implements Serializable {
	private int medRepId;
	private String medRepAddr1;
	private String medRepAddr2;
	private String medRepDesc;
	private String medRepName;
	private String email;
	private String fax;
	private String mob1;
	private String mob2;
	private String phone1;
	private String phone2;
	private String pin;
	private String state;
	private String panNo;
	private String voterIdNo;
	private String website;
	private String msg;
	private String errMsg;
	private int wholesellerId;
	private String wholesellerName;
	private String dateOfAssociation;
	private Date dateOfAssociationActual;
	private List<States> states = new ArrayList<States>();
	
	private Map<String, String> searchMap = new HashMap<String, String>();
	private List<MedRepBean> searchMedRepList = new ArrayList<MedRepBean>();
	private List<MedRepBean> medRepList = new ArrayList<MedRepBean>();
	
	// Pagination Parameters
	private int rows;
	private int page;
	private String order;
	private String sort;
	private int total; 
	
	public String getMedRepName() {
		return medRepName;
	}
	public void setMedRepName(String medRepName) {
		this.medRepName = medRepName;
	}
	public List<States> getStates() {
		return states;
	}
	public void setStates(List<States> states) {
		this.states = states;
	}
	public String getMedRepAddr1() {
		return medRepAddr1;
	}
	public void setMedRepAddr1(String medRepAddr1) {
		this.medRepAddr1 = medRepAddr1;
	}
	public String getMedRepAddr2() {
		return medRepAddr2;
	}
	public void setMedRepAddr2(String medRepAddr2) {
		this.medRepAddr2 = medRepAddr2;
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
	public List<MedRepBean> getSearchMedRepList() {
		return searchMedRepList;
	}
	public void setSearchMedRepList(List<MedRepBean> searchMedRepList) {
		this.searchMedRepList = searchMedRepList;
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
	public int getMedRepId() {
		return medRepId;
	}
	public void setMedRepId(int medRepId) {
		this.medRepId = medRepId;
	}
	public String getMedRepDesc() {
		return medRepDesc;
	}
	public void setMedRepDesc(String medRepDesc) {
		this.medRepDesc = medRepDesc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public int getWholesellerId() {
		return wholesellerId;
	}
	public void setWholesellerId(int wholesellerId) {
		this.wholesellerId = wholesellerId;
	}
	public String getWholesellerName() {
		return wholesellerName;
	}
	public void setWholesellerName(String wholesellerName) {
		this.wholesellerName = wholesellerName;
	}
	public String getDateOfAssociation() {
		return dateOfAssociation;
	}
	public void setDateOfAssociation(String dateOfAssociation) {
		this.dateOfAssociation = dateOfAssociation;
	}
	public Date getDateOfAssociationActual() {
		return dateOfAssociationActual;
	}
	public void setDateOfAssociationActual(Date dateOfAssociationActual) {
		this.dateOfAssociationActual = dateOfAssociationActual;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getVoterIdNo() {
		return voterIdNo;
	}
	public void setVoterIdNo(String voterIdNo) {
		this.voterIdNo = voterIdNo;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public List<MedRepBean> getMedRepList() {
		return medRepList;
	}
	public void setMedRepList(List<MedRepBean> medRepList) {
		this.medRepList = medRepList;
	}
}
