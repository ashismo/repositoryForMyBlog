package com.ashish.medicine.admin.invoice_bck;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ashish.medicine.admin.customer.CustomerBean;
import com.ashish.medicine.admin.doctor.DoctorBean;
import com.ashish.medicine.admin.medicine.MedicineBean;
import com.ashish.medicine.admin.owner.OwnerBean;
import com.ashish.medicine.common.bean.States;

public class InvoiceBean implements Serializable {
	private int invoiceId;
	private int slNo;
	private int invoiceDetailsId;
	private int invoiceDetailsIdArr[];
	private int medicineDetailsId;
	private int medicineId;
	private String medicineName;
	private String medicineNameArr[];
	private String schedule;
	private String scheduleArr[];
	private String batchId;
	private String batchName;
	private String batchNameArr[];
	private int companyId;
	private String companyName;
	private String companyNameArr[];
	private int stock;
	private int availableStock;
	private int soldoutStock;
	private int soldoutStockArr[];
	private double unitPrice;
	private double totalPrice;
	private double totalPriceArr[];
	private double totalAmt;
	private double totalPaid;
	private String paymentMode;
	
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
	private String doctorAddr1;
	private String doctorMob1;
	private int customerId;
	private String customerAddr1;
	private String customerAddr2;
	private String customerName;
	private String customerMob1;
	
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
	private String msg;
	private String purchaseDate;
	private Date purchaseDateActual;
	private List<States> states = new ArrayList<States>();
	private OwnerBean owner = new OwnerBean();
	private DoctorBean doctor = new DoctorBean();
	private CustomerBean customer = new CustomerBean();
	private MedicineBean medicine = new MedicineBean();
	
	private Map<String, String> searchMap = new HashMap<String, String>();
	private List<InvoiceBean> searchInvoiceList = new ArrayList<InvoiceBean>();
	private List<MedicineBean> medicineList = new ArrayList<MedicineBean>();
	
	// Pagination Parameters
	private int rows;
	private int page;
	private String order;
	private String sort;
	private int total; 
	
	
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public List<States> getStates() {
		return states;
	}
	public void setStates(List<States> states) {
		this.states = states;
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
	public List<InvoiceBean> getSearchInvoiceList() {
		return searchInvoiceList;
	}
	public void setSearchInvoiceList(List<InvoiceBean> searchInvoiceList) {
		this.searchInvoiceList = searchInvoiceList;
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
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
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
	public OwnerBean getOwner() {
		return owner;
	}
	public void setOwner(OwnerBean owner) {
		this.owner = owner;
	}
	public DoctorBean getDoctor() {
		return doctor;
	}
	public void setDoctor(DoctorBean doctor) {
		this.doctor = doctor;
	}
	public CustomerBean getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}
	public MedicineBean getMedicine() {
		return medicine;
	}
	public void setMedicine(MedicineBean medicine) {
		this.medicine = medicine;
	}
	public List<MedicineBean> getMedicineList() {
		return medicineList;
	}
	public void setMedicineList(List<MedicineBean> medicineList) {
		this.medicineList = medicineList;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
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
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public int[] getInvoiceDetailsIdArr() {
		return invoiceDetailsIdArr;
	}
	public void setInvoiceDetailsIdArr(int[] invoiceDetailsIdArr) {
		this.invoiceDetailsIdArr = invoiceDetailsIdArr;
	}
	public int[] getSoldoutStockArr() {
		return soldoutStockArr;
	}
	public void setSoldoutStockArr(int[] soldoutStockArr) {
		this.soldoutStockArr = soldoutStockArr;
	}
	public double[] getTotalPriceArr() {
		return totalPriceArr;
	}
	public void setTotalPriceArr(double[] totalPriceArr) {
		this.totalPriceArr = totalPriceArr;
	}
	public String[] getMedicineNameArr() {
		return medicineNameArr;
	}
	public void setMedicineNameArr(String[] medicineNameArr) {
		this.medicineNameArr = medicineNameArr;
	}
	public String[] getScheduleArr() {
		return scheduleArr;
	}
	public void setScheduleArr(String[] scheduleArr) {
		this.scheduleArr = scheduleArr;
	}
	public String[] getCompanyNameArr() {
		return companyNameArr;
	}
	public void setCompanyNameArr(String[] companyNameArr) {
		this.companyNameArr = companyNameArr;
	}
	public String[] getBatchNameArr() {
		return batchNameArr;
	}
	public void setBatchNameArr(String[] batchNameArr) {
		this.batchNameArr = batchNameArr;
	}
	public String getDoctorAddr1() {
		return doctorAddr1;
	}
	public void setDoctorAddr1(String doctorAddr1) {
		this.doctorAddr1 = doctorAddr1;
	}
	public String getDoctorMob1() {
		return doctorMob1;
	}
	public void setDoctorMob1(String doctorMob1) {
		this.doctorMob1 = doctorMob1;
	}
	public String getCustomerMob1() {
		return customerMob1;
	}
	public void setCustomerMob1(String customerMob1) {
		this.customerMob1 = customerMob1;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getPurchaseDateActual() {
		return purchaseDateActual;
	}
	public void setPurchaseDateActual(Date purchaseDateActual) {
		this.purchaseDateActual = purchaseDateActual;
	}
}
