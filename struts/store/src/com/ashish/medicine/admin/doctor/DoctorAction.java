package com.ashish.medicine.admin.doctor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

import com.ashish.medicine.admin.base.MedicineBaseAction;
import com.ashish.medicine.admin.customer.CustomerServiceImpl;
import com.ashish.medicine.exception.AppException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DoctorAction extends MedicineBaseAction implements ModelDriven<DoctorBean> {
	private DoctorBean doctorBean = new DoctorBean();
	private static final long serialVersionUID = -2613425890762568273L;
	private int total;
	private List<DoctorBean> totalRecords; 
	private String msg;
	private final String SUCCESS_MSG = "Successfully updated";
	private final String FAILED_MSG = "Operation failed!!!!!";

	private int doctorId;
	private String doctorAddr1;
	private String doctorAddr2;
	private String doctorDesc;
	private String doctorName;
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
	private String desc;
	private String qualification;
	private String speciality;
	private Date dateOfAssociationActual;
	private Date dateOfReleaseActual;
	private String dateOfAssociation;
	private String dateOfRelease;
	private String isActive;
	
	public String doctorManagementDetails()
	{
		return "success";		
	}
	
	public String allDoctors()
	{
		try {
			new DoctorServiceImpl().getAllDoctors(doctorBean);
		} catch (AppException e) {
			setMsg("Unable to fetch Doctors Name");
			e.printStackTrace();
		}
		return "success";
	}
	
	public String doctorByDoctorId()
	{
		try {
			new DoctorServiceImpl().getDoctorByDoctorId(doctorBean);
		} catch (AppException e) {
			setMsg("Unable to fetch Doctors Details");
			e.printStackTrace();
		}
		return "success";
	}
	
	public String searchDoctor()
	{
		try {
			new DoctorServiceImpl().searchDoctor(doctorBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(doctorBean.getMsg());
		}
		totalRecords = doctorBean.getSearchDoctorList();
		total = doctorBean.getTotal();
		return Action.SUCCESS;		
	}
	
	public String viewDoctor()
	{
		try {
			new DoctorServiceImpl().viewDoctor(doctorBean);
			BeanUtils.copyProperties(this, doctorBean);
			setMsg(SUCCESS_MSG);
		} catch (Exception e) {
			setMsg("Unable to view doctor");
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String addDoctorForm() {
		return ActionSupport.SUCCESS;
	}
	
	public String addOrUpdateDoctor()
	{
		System.out.println("Create/Update a new Doctor");
		try {
			new DoctorServiceImpl().addOrUpdateDoctor(doctorBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(doctorBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String deleteDoctor()
	{
		System.out.println("Delete Doctor");
		try {
			new DoctorServiceImpl().deleteDoctor(doctorBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(doctorBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String sellByMonths()
	{
		return "success";		
	}
	
	public DoctorBean getDoctorBean() {
		return doctorBean;
	}

	public void setDoctorBean(DoctorBean doctorBean) {
		this.doctorBean = doctorBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<DoctorBean> getTotalRecords() {
		return totalRecords;
	}

	public List<DoctorBean> getRows() {
		return totalRecords;
	}
	
	public void setTotalRecords(List<DoctorBean> totalRecords) {
		this.totalRecords = totalRecords;
	}

	public DoctorBean getModel() {
		updateUserDetailsFromSession(doctorBean);
		return doctorBean;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getDoctorAddr1() {
		return doctorAddr1;
	}

	public void setDoctorAddr1(String doctorAddr1) {
		this.doctorAddr1 = doctorAddr1;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorAddr2() {
		return doctorAddr2;
	}

	public void setDoctorAddr2(String doctorAddr2) {
		this.doctorAddr2 = doctorAddr2;
	}

	public String getDoctorDesc() {
		return doctorDesc;
	}

	public void setDoctorDesc(String doctorDesc) {
		this.doctorDesc = doctorDesc;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
