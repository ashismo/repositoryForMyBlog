package com.ashish.medicine.admin.medicine;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ashish.medicine.admin.base.MedicineBaseBean;
import com.ashish.medicine.admin.company.CompanyBean;

public class MedicineBean extends MedicineBaseBean {
	private String status;
	private int medicineId;
	private int medicineDetailsId;
	private String medicineDesc;
	private String medicineName;
	private String medicineBatch;
	private String wholesellerName;
	private int wholesellerId;
	private String medRepName;
	private int medRepId;
	private String batchName;
	private String batchId;
	private int companyId;
	private String companyName;
	private int stock;
	// sum of all stocks
	private long totalStock;
	private int availableStock;
	//Sum of all stocks
	private long totalAvailableStock;
	private int soldoutStock;
	private double unitPrice;
	private double soldoutUnitPrice;
	private String medDose;
	private String medType;
	private String medWeight;
	private String mfgDate;
	private String msg;
	private String errMsg;
	private String expDate;
	private String purchaseDate;
	private Date mfgDateActual;
	private Date expDateActual;
	private Date purchaseDateActual;
	private String expiringBefore;
	private Date expiringBeforeActual;
	private String expiringAfter;
	private Date expiringAfterActual;
	private String schedule;
	
	private String startDate;
	private String endDate;
	
	private CompanyBean companyBean;
	private List<CompanyBean> companyList = new ArrayList<CompanyBean>();
	private List<MedicineBean> medicineList = new ArrayList<MedicineBean>();
	private List<MedicineBean> batchList = new ArrayList<MedicineBean>();
	
	
	private List<MedicineBean> searchMedicineList = new ArrayList<MedicineBean>();
	private List<MedicineBean> footer = new ArrayList<MedicineBean>();
	
	// Pagination Parameters
	private int rows;
	private int page;
	private String order;
	private String sort;
	private int total;
	
	
	public int getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}
	public String getMedicineDesc() {
		return medicineDesc;
	}
	public void setMedicineDesc(String medicineDesc) {
		this.medicineDesc = medicineDesc;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public List<MedicineBean> getSearchMedicineList() {
		return searchMedicineList;
	}
	public void setSearchMedicineList(List<MedicineBean> searchMedicineList) {
		this.searchMedicineList = searchMedicineList;
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
	public String getMedicineBatch() {
		return medicineBatch;
	}
	public void setMedicineBatch(String medicineBatch) {
		this.medicineBatch = medicineBatch;
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
	public List<CompanyBean> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<CompanyBean> companyList) {
		this.companyList = companyList;
	}
	public List<MedicineBean> getMedicineList() {
		return medicineList;
	}
	public void setMedicineList(List<MedicineBean> medicineList) {
		this.medicineList = medicineList;
	}
	public List<MedicineBean> getBatchList() {
		return batchList;
	}
	public void setBatchList(List<MedicineBean> batchList) {
		this.batchList = batchList;
	}
	
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public CompanyBean getCompanyBean() {
		return companyBean;
	}
	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getMedicineDetailsId() {
		return medicineDetailsId;
	}
	public void setMedicineDetailsId(int medicineDetailsId) {
		this.medicineDetailsId = medicineDetailsId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public String getMedDose() {
		return medDose;
	}
	public void setMedDose(String medDose) {
		this.medDose = medDose;
	}
	public String getMedType() {
		return medType;
	}
	public void setMedType(String medType) {
		this.medType = medType;
	}
	public String getMedWeight() {
		return medWeight;
	}
	public void setMedWeight(String medWeight) {
		this.medWeight = medWeight;
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
	public double getSoldoutUnitPrice() {
		return soldoutUnitPrice;
	}
	public void setSoldoutUnitPrice(double soldoutUnitPrice) {
		this.soldoutUnitPrice = soldoutUnitPrice;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getWholesellerName() {
		return wholesellerName;
	}
	public void setWholesellerName(String wholesellerName) {
		this.wholesellerName = wholesellerName;
	}
	public String getMedRepName() {
		return medRepName;
	}
	public void setMedRepName(String medRepName) {
		this.medRepName = medRepName;
	}
	public int getWholesellerId() {
		return wholesellerId;
	}
	public void setWholesellerId(int wholesellerId) {
		this.wholesellerId = wholesellerId;
	}
	public int getMedRepId() {
		return medRepId;
	}
	public void setMedRepId(int medRepId) {
		this.medRepId = medRepId;
	}
	public List<MedicineBean> getFooter() {
		return footer;
	}
	public void setFooter(List<MedicineBean> footer) {
		this.footer = footer;
	}
	public long getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(long totalStock) {
		this.totalStock = totalStock;
	}
	public long getTotalAvailableStock() {
		return totalAvailableStock;
	}
	public void setTotalAvailableStock(long totalAvailableStock) {
		this.totalAvailableStock = totalAvailableStock;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getExpiringBefore() {
		return expiringBefore;
	}
	public void setExpiringBefore(String expiringBefore) {
		this.expiringBefore = expiringBefore;
	}
	public Date getExpiringBeforeActual() {
		return expiringBeforeActual;
	}
	public void setExpiringBeforeActual(Date expiringBeforeActual) {
		this.expiringBeforeActual = expiringBeforeActual;
	}
	public String getExpiringAfter() {
		return expiringAfter;
	}
	public void setExpiringAfter(String expiringAfter) {
		this.expiringAfter = expiringAfter;
	}
	public Date getExpiringAfterActual() {
		return expiringAfterActual;
	}
	public void setExpiringAfterActual(Date expiringAfterActual) {
		this.expiringAfterActual = expiringAfterActual;
	}
}
