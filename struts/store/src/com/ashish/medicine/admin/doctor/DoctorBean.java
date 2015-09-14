package com.ashish.medicine.admin.doctor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ashish.medicine.admin.base.MedicineBaseBean;
import com.ashish.medicine.common.bean.States;

public class DoctorBean extends MedicineBaseBean implements Serializable {
	private int doctorId;
	private String doctorAddr1;
	private String doctorAddr2;
	private String doctorDesc;
	private String doctorName;
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
	private String qualification;
	private String speciality;
	private Date dateOfAssociationActual;
	private Date dateOfReleaseActual;
	private String dateOfAssociation;
	private String dateOfRelease;
	private String isActive;
	private List<States> states = new ArrayList<States>();
	
	private Map<String, String> searchMap = new HashMap<String, String>();
	private List<DoctorBean> searchDoctorList = new ArrayList<DoctorBean>();
	private List<DoctorBean> doctorList = new ArrayList<DoctorBean>();
	
	// Pagination Parameters
	private int rows;
	private int page;
	private String order;
	private String sort;
	private int total; 
	
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public List<States> getStates() {
		return states;
	}
	public void setStates(List<States> states) {
		this.states = states;
	}
	public String getDoctorAddr1() {
		return doctorAddr1;
	}
	public void setDoctorAddr1(String doctorAddr1) {
		this.doctorAddr1 = doctorAddr1;
	}
	public String getDoctorAddr2() {
		return doctorAddr2;
	}
	public void setDoctorAddr2(String doctorAddr2) {
		this.doctorAddr2 = doctorAddr2;
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
	public List<DoctorBean> getSearchDoctorList() {
		return searchDoctorList;
	}
	public void setSearchDoctorList(List<DoctorBean> searchDoctorList) {
		this.searchDoctorList = searchDoctorList;
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
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorDesc() {
		return doctorDesc;
	}
	public void setDoctorDesc(String doctorDesc) {
		this.doctorDesc = doctorDesc;
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
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public List<DoctorBean> getDoctorList() {
		return doctorList;
	}
	public void setDoctorList(List<DoctorBean> doctorList) {
		this.doctorList = doctorList;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Date getDateOfAssociationActual() {
		return dateOfAssociationActual;
	}
	public void setDateOfAssociationActual(Date dateOfAssociationActual) {
		this.dateOfAssociationActual = dateOfAssociationActual;
	}
	public Date getDateOfReleaseActual() {
		return dateOfReleaseActual;
	}
	public void setDateOfReleaseActual(Date dateOfReleaseActual) {
		this.dateOfReleaseActual = dateOfReleaseActual;
	}
	public String getDateOfAssociation() {
		return dateOfAssociation;
	}
	public void setDateOfAssociation(String dateOfAssociation) {
		this.dateOfAssociation = dateOfAssociation;
	}
	public String getDateOfRelease() {
		return dateOfRelease;
	}
	public void setDateOfRelease(String dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}
}
