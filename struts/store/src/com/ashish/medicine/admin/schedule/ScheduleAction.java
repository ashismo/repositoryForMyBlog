package com.ashish.medicine.admin.schedule;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import com.ashish.medicine.util.MedicineUtility;
import com.ashish.medicine.util.UtilServiceImpl;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ScheduleAction extends MedicineBaseAction implements ModelDriven<ScheduleBean> {
	private ScheduleBean scheduleBean = new ScheduleBean();
	private static final long serialVersionUID = -2613425890762568273L;
	private int total;
	private List<ScheduleBean> totalRecords; 
	private String msg;
	private final String SUCCESS_MSG = "Successfully updated";
	private final String FAILED_MSG = "Operation failed!!!!!";

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
	private String leftItemName;
	private List<String> dayAndDatesList = new ArrayList<String>();
	private Timestamp dbAddTs;
	private int dbAddUser;
	private Timestamp dbUpdTs;
	private int dbUpdUser;
	
//	public ScheduleAction() {
//		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
//		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
//	}
	
	public String scheduleManagementDetails()
	{
		MedicineUtility mUtil = new MedicineUtility();
		if(scheduleBean.getScheduleDate() == null || scheduleBean.getScheduleDate().trim().equals("")) {
			scheduleBean.setScheduleDateActual(new Date());
		} else {
			scheduleBean.setScheduleDateActual(mUtil.getDateFromString(scheduleBean.getScheduleDate()));
		}
		scheduleBean.setScheduleLookupId(1);
		viewSchedule();
		return "success";		
	}
	
	public String dayAndDates() {
		MedicineUtility mUtil = new MedicineUtility();
		Date scheduleDate = null;
		if(scheduleBean.getScheduleDate() == null || scheduleBean.getScheduleDate().trim().equals("")) {
			scheduleDate = new Date();
		} else {
			scheduleDate = mUtil.getDateFromString(scheduleBean.getScheduleDate());
		}
		
		dayAndDatesList = mUtil.getDayAndDatesOfAWeek(scheduleDate);
		for(String dayAndDate : dayAndDatesList) {
			dayAndDate = dayAndDate.replace("-", "\n");
		}
		scheduleBean.setDayAndDatesList(dayAndDatesList);
		
		return Action.SUCCESS;	
	}
	public String scheduleCriteriaForm() {
		try {
			new ScheduleServiceImpl().scheduleCriteriaForm(scheduleBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(scheduleBean.getMsg());
		}
		return Action.SUCCESS;		
	}
	
	public String viewSchedule()
	{
		try {
			new ScheduleServiceImpl().viewSchedule(scheduleBean);
			BeanUtils.copyProperties(this, scheduleBean);
			setMsg(SUCCESS_MSG);
		} catch (Exception e) {
			setMsg("Unable to view schedule");
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String addOrUpdateSchedule()
	{
		System.out.println("Create/Update a new Schedule");
		try {
			new ScheduleServiceImpl().addOrUpdateSchedule(scheduleBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(scheduleBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String deleteSchedule()
	{
		System.out.println("Delete Schedule");
		try {
			new ScheduleServiceImpl().deleteSchedule(scheduleBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(scheduleBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	
	public ScheduleBean getScheduleBean() {
		return scheduleBean;
	}

	public void setScheduleBean(ScheduleBean scheduleBean) {
		this.scheduleBean = scheduleBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<ScheduleBean> getTotalRecords() {
		return totalRecords;
	}

	public List<ScheduleBean> getRows() {
		return totalRecords;
	}
	
	public void setTotalRecords(List<ScheduleBean> totalRecords) {
		this.totalRecords = totalRecords;
	}

	public ScheduleBean getModel() {
		updateUserDetailsFromSession(scheduleBean);
		return scheduleBean;
	}


	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
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

	public String getLeftItemName() {
		return leftItemName;
	}

	public void setLeftItemName(String leftItemName) {
		this.leftItemName = leftItemName;
	}

	public List<String> getDayAndDatesList() {
		return dayAndDatesList;
	}

	public void setDayAndDatesList(List<String> dayAndDatesList) {
		this.dayAndDatesList = dayAndDatesList;
	}
}
