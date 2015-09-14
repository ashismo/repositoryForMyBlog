package com.ashish.medicine.admin.medRep;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.ashish.medicine.admin.base.MedicineBaseAction;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.util.UtilServiceImpl;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MedRepAction extends MedicineBaseAction implements ModelDriven<MedRepBean> {
	private MedRepBean medRepBean = new MedRepBean();
	private static final long serialVersionUID = -2613425890762568273L;
	private int total;
	private List<MedRepBean> totalRecords; 
	private String msg;
	private final String SUCCESS_MSG = "Successfully updated";
	private final String FAILED_MSG = "Operation failed!!!!!";

	private int medRepId;
	private String medRepAddr1;
	private String medRepAddr2;
	private String medRepDesc;
	private String medRepName;
	private Timestamp dbAddTs;
	private int dbAddUser;
	private Timestamp dbUpdTs;
	private int dbUpdUser;
	private String email;
	private String fax;
	private String mob1;
	private String mob2;
	private String phone1;
	private String phone2;
	private String pin;
	private String state;
	private String website;
	private String desc;
	private String panNo;
	private int wholesellerId;
	private String wholesellerName;
	private String dateOfAssociation;
	private Date dateOfAssociationActual;
	private String voterIdNo;
	
//	public MedRepAction() {
//		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
//		ConvertUtils.register(new DateConverter(null), Date.class);
//	}
	
	public String medRepManagementDetails()
	{
		return "success";		
	}
	
	public String searchMedRep()
	{
		try {
			new MedRepServiceImpl().searchMedRep(medRepBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(medRepBean.getMsg());
		}
//		for(int i = medRepBean.getPage() * medRepBean.getRows(); i < (medRepBean.getPage() + 1) * medRepBean.getRows(); i++) {
//			cBean.setMedRepId(i);
//			searchList.add(cBean);
//		}
		totalRecords = medRepBean.getSearchMedRepList();
		total = medRepBean.getTotal();
		return Action.SUCCESS;		
	}
	
	public String medRepByWholeSellerId()
	{
		try {
			new MedRepServiceImpl().getMedRepByWholeSellerId(medRepBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(medRepBean.getMsg());
		}
		return Action.SUCCESS;		
	}
	
	public String viewMedRep()
	{
		try {
			new MedRepServiceImpl().viewMedRep(medRepBean);
			BeanUtils.copyProperties(this, medRepBean);
		} catch (Exception e) {
			medRepBean.setErrMsg("Unable to view medRep");
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String addMedRepForm() {
		return ActionSupport.SUCCESS;
	}
	
	public String addOrUpdateMedRep()
	{
		System.out.println("Create/Update a new MedRep");
		try {
			new MedRepServiceImpl().addOrUpdateMedRep(medRepBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(medRepBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String deleteMedRep()
	{
		System.out.println("Delete MedRep");
		try {
			new MedRepServiceImpl().deleteMedRep(medRepBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(medRepBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String sellByMonths()
	{
		return "success";		
	}
	
	public MedRepBean getMedRepBean() {
		return medRepBean;
	}

	public void setMedRepBean(MedRepBean medRepBean) {
		this.medRepBean = medRepBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<MedRepBean> getTotalRecords() {
		return totalRecords;
	}

	public List<MedRepBean> getRows() {
		return totalRecords;
	}
	
	public void setTotalRecords(List<MedRepBean> totalRecords) {
		this.totalRecords = totalRecords;
	}

	public MedRepBean getModel() {
		updateUserDetailsFromSession(medRepBean);
		return medRepBean;
	}

	public String getMedRepName() {
		return medRepName;
	}

	public void setMedRepName(String medRepName) {
		this.medRepName = medRepName;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getMedRepAddr1() {
		return medRepAddr1;
	}

	public void setMedRepAddr1(String medRepAddr1) {
		this.medRepAddr1 = medRepAddr1;
	}

	public int getMedRepId() {
		return medRepId;
	}

	public void setMedRepId(int medRepId) {
		this.medRepId = medRepId;
	}

	public String getMedRepAddr2() {
		return medRepAddr2;
	}

	public void setMedRepAddr2(String medRepAddr2) {
		this.medRepAddr2 = medRepAddr2;
	}

	public String getMedRepDesc() {
		return medRepDesc;
	}

	public void setMedRepDesc(String medRepDesc) {
		this.medRepDesc = medRepDesc;
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

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
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

	public String getVoterIdNo() {
		return voterIdNo;
	}

	public void setVoterIdNo(String voterIdNo) {
		this.voterIdNo = voterIdNo;
	}
}
