package com.ashish.medicine.admin.invoice_bck;

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

import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.util.UtilServiceImpl;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class InvoiceAction implements ModelDriven<InvoiceBean> {
	private InvoiceBean invoiceBean = new InvoiceBean();
	private static final long serialVersionUID = -2613425890762568273L;
	private int total;
	private List<InvoiceBean> totalRecords; 
	private String msg;
	private final String SUCCESS_MSG = "Successfully updated";
	private final String FAILED_MSG = "Operation failed!!!!!";

	private int invoiceId;
	private String schedule;
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
	private double totalPrice;
	private double totalAmt;
	private double totalPaid;
	private String paymentMode;
	
	private int invoiceDetailsId;
	private int medicineDetailsId;
	private int medicineId;
	private String batchId;
	private String batchName;
	private int companyId;
	private String companyName;
	private int stock;
	private int availableStock;
	private int soldoutStock;
	private double unitPrice;
	private String mfgDate;
	private String expDate;
	private Date mfgDateActual;
	private Date expDateActual;
	
	private int ownerId;
	private String ownerAddr1;
	private String ownerAddr2;
	private String ownerDesc;
	private String ownerName;
	private int doctorId;
	private String doctorName;
	private int customerId;
	private String customerAddr1;
	private String customerAddr2;
	private String customerName;
	
	public InvoiceAction() {
		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
	}
	
	public String invoiceManagementDetails()
	{
		return "success";		
	}
	
	public String invoiceItems()
	{
		List<InvoiceBean> searchList = invoiceBean.getSearchInvoiceList();
		InvoiceBean invoiceDetails = new InvoiceBean();
		invoiceDetails.setMedicineName("Test1");
		invoiceDetails.setExpDate("20/07/2012");
		invoiceDetails.setPhone1("9830525559");
		invoiceDetails.setPhone2("9830625559");
		invoiceDetails.setMob1("9830625559");
//		try {
//			new InvoiceServiceImpl().searchInvoice(invoiceBean);
//			setMsg(SUCCESS_MSG);
//		} catch (AppException e) {
//			e.printStackTrace();
//			setMsg(invoiceBean.getMsg());
//		}
		setInvoiceId(1);
		for(int i = invoiceBean.getPage() * invoiceBean.getRows(); i < (invoiceBean.getPage() + 1) * invoiceBean.getRows(); i++) {
			invoiceDetails.setInvoiceDetailsId(i);
			searchList.add(invoiceDetails);
		}
		totalRecords = invoiceBean.getSearchInvoiceList();
//		total = invoiceBean.getTotal();
		
		total = 4;
		
		
		return Action.SUCCESS;		
	}
	
	public String searchInvoice()
	{
		List<InvoiceBean> searchList = invoiceBean.getSearchInvoiceList();
		InvoiceBean cBean = new InvoiceBean();
		cBean.setMedicineName("Test1");
		cBean.setExpDate("20/07/2012");
		cBean.setPhone1("9830525559");
		cBean.setPhone2("9830625559");
		cBean.setMob1("9830625559");
//		try {
//			new InvoiceServiceImpl().searchInvoice(invoiceBean);
//			setMsg(SUCCESS_MSG);
//		} catch (AppException e) {
//			e.printStackTrace();
//			setMsg(invoiceBean.getMsg());
//		}
		for(int i = invoiceBean.getPage() * invoiceBean.getRows(); i < (invoiceBean.getPage() + 1) * invoiceBean.getRows(); i++) {
			cBean.setInvoiceId(i);
			searchList.add(cBean);
		}
		totalRecords = invoiceBean.getSearchInvoiceList();
//		total = invoiceBean.getTotal();
		
		total = 20;
		
		
		return Action.SUCCESS;		
	}
	
	public String viewInvoice()
	{
		try {
//			new InvoiceServiceImpl().viewInvoice(invoiceBean);
//			BeanUtils.copyProperties(this, invoiceBean);
			List<InvoiceBean> searchList = invoiceBean.getSearchInvoiceList();
			for(int i = 0; i < 5; i++) {
				InvoiceBean invoiceDetails = new InvoiceBean();
				invoiceDetails.setInvoiceDetailsId(i);
				invoiceDetails.setSlNo(i);
				invoiceDetails.setSoldoutStock(5);
				invoiceDetails.setMedicineName("ABC");
				invoiceDetails.setMedicineId(16);
				invoiceDetails.setSchedule("3times/day- after food");
				invoiceDetails.setBatchName("2012");
				invoiceDetails.setMfgDate("20/07/2012");
				invoiceDetails.setExpDate("20/07/2014");
				invoiceDetails.setTotalPrice(12.5);
				invoiceDetails.setInvoiceId(1);
				searchList.add(invoiceDetails);
			}
			totalRecords = searchList;
//			total = invoiceBean.getTotal();
			
			total = 5;
			setMsg(SUCCESS_MSG);
		} catch (Exception e) {
			setMsg("Unable to view invoice");
			e.printStackTrace();
		}
//		setInvoiceAddr1("Kolkata");
//		setInvoiceAddr2("Kolkata1");
//		setPhone1("8830525559");
//		setPhone2("9830625559");
//		setMob1("7830625559");
//		setMob2("NA");
//		setInvoiceName("Test2");
//		setInvoiceId(11);
//		setState("WB");
//		setPin("712304");
//		setWebsite("www.google.com");
//		setDesc("TEST DESC");
		return "success";		
	}
	
	public String addInvoiceForm() {
		return ActionSupport.SUCCESS;
	}
	
	public String addOrUpdateInvoice()
	{
		System.out.println("Create/Update a new Invoice");
		try {
			new InvoiceServiceImpl().addOrUpdateInvoice(invoiceBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(invoiceBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String deleteInvoice()
	{
		System.out.println("Delete Invoice");
		try {
			new InvoiceServiceImpl().deleteInvoice(invoiceBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(invoiceBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String sellByMonths()
	{
		return "success";		
	}
	
	public InvoiceBean getInvoiceBean() {
		return invoiceBean;
	}

	public void setInvoiceBean(InvoiceBean invoiceBean) {
		this.invoiceBean = invoiceBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<InvoiceBean> getTotalRecords() {
		return totalRecords;
	}

	public List<InvoiceBean> getRows() {
		return totalRecords;
	}
	
	public void setTotalRecords(List<InvoiceBean> totalRecords) {
		this.totalRecords = totalRecords;
	}

	public InvoiceBean getModel() {
		return invoiceBean;
	}


	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}


	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
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

	public int getInvoiceDetailsId() {
		return invoiceDetailsId;
	}

	public void setInvoiceDetailsId(int invoiceDetailsId) {
		this.invoiceDetailsId = invoiceDetailsId;
	}

	public int getMedicineDetailsId() {
		return medicineDetailsId;
	}

	public void setMedicineDetailsId(int medicineDetailsId) {
		this.medicineDetailsId = medicineDetailsId;
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}

	public int getSoldoutStock() {
		return soldoutStock;
	}

	public void setSoldoutStock(int soldoutStock) {
		this.soldoutStock = soldoutStock;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(String mfgDate) {
		this.mfgDate = mfgDate;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public Date getMfgDateActual() {
		return mfgDateActual;
	}

	public void setMfgDateActual(Date mfgDateActual) {
		this.mfgDateActual = mfgDateActual;
	}

	public Date getExpDateActual() {
		return expDateActual;
	}

	public void setExpDateActual(Date expDateActual) {
		this.expDateActual = expDateActual;
	}

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

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerAddr1() {
		return customerAddr1;
	}

	public void setCustomerAddr1(String customerAddr1) {
		this.customerAddr1 = customerAddr1;
	}

	public String getCustomerAddr2() {
		return customerAddr2;
	}

	public void setCustomerAddr2(String customerAddr2) {
		this.customerAddr2 = customerAddr2;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSUCCESS_MSG() {
		return SUCCESS_MSG;
	}

	public String getFAILED_MSG() {
		return FAILED_MSG;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public double getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
}
