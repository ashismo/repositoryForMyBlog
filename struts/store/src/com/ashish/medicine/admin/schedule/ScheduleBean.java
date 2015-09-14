package com.ashish.medicine.admin.schedule;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ashish.medicine.admin.base.MedicineBaseBean;
import com.ashish.medicine.common.bean.States;

public class ScheduleBean extends MedicineBaseBean implements Serializable {
	private int scheduleId;
	private Date scheduleDateActual;
	private String scheduleDate;
	private String scheduleDay;
	private int scheduleLookupId;
	private String scheduleTime;
	private String scheduleValue;
	private int doctorId;
	private String doctorName;
	private int wholesellerId;
	private String wholesellerName;
	private int medRepId;
	private String medRepIdName;
	private int customerId;
	private String customerIdName;
	private String assignedItems[];
	private String leftItemName;
	private String rightItemClass;
	private String msg;
	private List<States> states = new ArrayList<States>();
	
	private Map<String, String> searchMap = new HashMap<String, String>();
	private List<ScheduleBean> searchScheduleList = new ArrayList<ScheduleBean>();
	private List<ScheduleBean> scheduleListLeft = new ArrayList<ScheduleBean>();
	private List<ScheduleBean> scheduleListRight = new ArrayList<ScheduleBean>();
	private List<ScheduleBean> doctorList = new ArrayList<ScheduleBean>();
	private List<String> dayAndDatesList = new ArrayList<String>();
	
	// Pagination Parameters
	private int rows;
	private int page;
	private String order;
	private String sort;
	private int total;
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Date getScheduleDateActual() {
		return scheduleDateActual;
	}
	public void setScheduleDateActual(Date scheduleDateActual) {
		this.scheduleDateActual = scheduleDateActual;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getScheduleDay() {
		return scheduleDay;
	}
	public void setScheduleDay(String scheduleDay) {
		this.scheduleDay = scheduleDay;
	}
	public int getScheduleLookupId() {
		return scheduleLookupId;
	}
	public void setScheduleLookupId(int scheduleLookupId) {
		this.scheduleLookupId = scheduleLookupId;
	}
	public String getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	public String getScheduleValue() {
		return scheduleValue;
	}
	public void setScheduleValue(String scheduleValue) {
		this.scheduleValue = scheduleValue;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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
	public int getMedRepId() {
		return medRepId;
	}
	public void setMedRepId(int medRepId) {
		this.medRepId = medRepId;
	}
	public String getMedRepIdName() {
		return medRepIdName;
	}
	public void setMedRepIdName(String medRepIdName) {
		this.medRepIdName = medRepIdName;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerIdName() {
		return customerIdName;
	}
	public void setCustomerIdName(String customerIdName) {
		this.customerIdName = customerIdName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<States> getStates() {
		return states;
	}
	public void setStates(List<States> states) {
		this.states = states;
	}
	public Map<String, String> getSearchMap() {
		return searchMap;
	}
	public void setSearchMap(Map<String, String> searchMap) {
		this.searchMap = searchMap;
	}
	public List<ScheduleBean> getSearchScheduleList() {
		return searchScheduleList;
	}
	public void setSearchScheduleList(List<ScheduleBean> searchScheduleList) {
		this.searchScheduleList = searchScheduleList;
	}
	public List<ScheduleBean> getScheduleListLeft() {
		return scheduleListLeft;
	}
	public void setScheduleListLeft(List<ScheduleBean> scheduleListLeft) {
		this.scheduleListLeft = scheduleListLeft;
	}
	public List<ScheduleBean> getScheduleListRight() {
		return scheduleListRight;
	}
	public void setScheduleListRight(List<ScheduleBean> scheduleListRight) {
		this.scheduleListRight = scheduleListRight;
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
	public List<ScheduleBean> getDoctorList() {
		return doctorList;
	}
	public void setDoctorList(List<ScheduleBean> doctorList) {
		this.doctorList = doctorList;
	}
	public String[] getAssignedItems() {
		return assignedItems;
	}
	public void setAssignedItems(String[] assignedItems) {
		this.assignedItems = assignedItems;
	}
	public String getLeftItemName() {
		return leftItemName;
	}
	public void setLeftItemName(String leftItemName) {
		this.leftItemName = leftItemName;
	}
	public String getRightItemClass() {
		return rightItemClass;
	}
	public void setRightItemClass(String rightItemClass) {
		this.rightItemClass = rightItemClass;
	}
	public List<String> getDayAndDatesList() {
		return dayAndDatesList;
	}
	public void setDayAndDatesList(List<String> dayAndDatesList) {
		this.dayAndDatesList = dayAndDatesList;
	} 
}
