package com.ashish.medicine.admin.medicine;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.struts2.ServletActionContext;

import com.ashish.medicine.admin.base.MedicineBaseAction;
import com.ashish.medicine.admin.company.CompanyBean;
import com.ashish.medicine.admin.company.CompanyServiceImpl;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.login.UserBean;
import com.ashish.medicine.util.MedicineConstants;
import com.ashish.medicine.util.MedicineUtility;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class MedicineAction extends MedicineBaseAction implements ModelDriven<MedicineBean> {
	private MedicineBean medicineBean = new MedicineBean();
	private static final long serialVersionUID = -2613425890762568273L;
	private int total;
	private String msg;

	private int medicineId;
	private int medicineDetailsId;
	private String medicineName;
	private String medicineDesc;
	private String medicineBatch;
	private String batchName;
	private int companyId;
	private String companyName;
	private String wholesellerName;
	private int wholesellerId;
	private String medRepName;
	private int medRepId;
	private int batchId;
	private String manufacturer;
	private double unitPrice;
	private double soldoutUnitPrice;
	private String mfgDate;
	private String expDate;
	private String medDose;
	private String medType;
	private String medWeight;
	private String purchaseDate;
	private List<CompanyBean> companyList = new ArrayList<CompanyBean>();
	private List<MedicineBean> medicineList = new ArrayList<MedicineBean>();
	private List<MedicineBean> batchList = new ArrayList<MedicineBean>();
	private List<MedicineBean> totalRecords; 
	private List<MedicineBean> footer;
	private int stock;
	private int availableStock;
	private int soldoutStock;
	private Date mfgDateActual;
	private Date expDateActual;
	private String expiringBefore;
	private Date expiringBeforeActual;
	private String expiringAfter;
	private Date expiringAfterActual;
	
	private Timestamp dbAddTs;
	private int dbAddUser;
	private Timestamp dbUpdTs;
	private int dbUpdUser;
	
	private final String SUCCESS_MSG = "Successfully updated";
	private final String FAILED_MSG = "Operation failed!!!!!";
	
	public MedicineAction() {
		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
		ConvertUtils.register(new DateConverter(null), Date.class);
	}
	
	public String medicineManagementDetails()
	{
		CompanyBean cBean = new CompanyBean();
		try {
			new CompanyServiceImpl().getAllCompanies(cBean);
			medicineBean.setCompanyList(cBean.getSearchCompanyList());
			
			// fetch all batches
			new MedicineServiceImpl().getAllMedicineBatches(medicineBean);
		} catch (AppException e) {
			e.printStackTrace();
		}
		
		return "success";		
	}
	
	public String medicinesByCompanyId()
	{
		try {
			new MedicineServiceImpl().getMedicinesByCompanyId(medicineBean);
		} catch (AppException e) {
			setMsg("Unable to fetch Medicines for the selected Company");
			e.printStackTrace();
		}
		return "success";
	}
	
	public String allMedicineBatches()
	{
		try {
			new MedicineServiceImpl().getAllMedicineBatches(medicineBean);
		} catch (AppException e) {
			setMsg("Unable to fetch Medicines for the selected Company");
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String medicineDetailsByMedicineId()
	{
		try {
			new MedicineServiceImpl().getMedicineDetailsByMedicineId(medicineBean);
		} catch (AppException e) {
			setMsg("Unable to fetch Medicines Batched for the selected Medicine");
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String searchMedicine()
	{
		try {
			new MedicineServiceImpl().searchMedicineDetails(medicineBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(medicineBean.getMsg());
		}
		totalRecords = medicineBean.getSearchMedicineList();
		total = medicineBean.getTotal();
		footer = medicineBean.getFooter();
		return Action.SUCCESS;		
	}
	
	public String medicineByBatchAndMedicineId()
	{
		try {
			new MedicineServiceImpl().getMedicineByBatchAndMedicineId(medicineBean);
//			BeanUtils.copyProperties(this, medicineBean);
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(medicineBean.getMsg());
		}
		if(medicineBean.getSearchMedicineList() != null && medicineBean.getSearchMedicineList().size()>1) {
			for(MedicineBean medicineDetails : medicineBean.getSearchMedicineList()) {
				MedicineUtility mUtil = new MedicineUtility();
				medicineDetails.setMfgDate(mUtil.getDateStrFromDate(medicineDetails.getMfgDateActual()));
				medicineDetails.setExpDate(mUtil.getDateStrFromDate(medicineDetails.getExpDateActual()));
				medicineDetails.setPurchaseDate(mUtil.getDateStrFromDate(medicineDetails.getPurchaseDateActual()));
			}
			
			totalRecords = medicineBean.getSearchMedicineList();
			total = medicineBean.getTotal();
		}
		return Action.SUCCESS;		
	}
	
	
	public String viewMedicine()
	{
		try {
			new MedicineServiceImpl().viewMedicineDetails(medicineBean);
			if(medicineBean.getMedType() == null) medicineBean.setMedType("Liquid");
			BeanUtils.copyProperties(this, medicineBean);
			setMsg(SUCCESS_MSG);
		} catch (Exception e) {
			setMsg("Unable to view company");
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String saveMedicineIntoInvoice()
	{
		try {
			new MedicineServiceImpl().saveMedicineIntoInvoice(medicineBean);
		} catch (Exception e) {
			setMsg("Unable to view company");
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String allMedicines()
	{
		try {
			new MedicineServiceImpl().getAllMedicines(medicineBean);
			BeanUtils.copyProperties(this, medicineBean);
			setMsg(SUCCESS_MSG);
		} catch (Exception e) {
			setMsg("Unable to view company");
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String deleteMedicineDetails()
	{
		System.out.println("Delete Medicine Details");
		try {
			new MedicineServiceImpl().deleteMedicineDetails(medicineBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(medicineBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String addOrUpdateMedicine()
	{
		System.out.println("Create/Update a new Company");
		try {
			new MedicineServiceImpl().addOrUpdateMedicine(medicineBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(medicineBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String addMedicineForm() {
		CompanyBean cBean = new CompanyBean();
		cBean.setCompanyId(10);
		cBean.setCompanyName("Ranbaxy");
		companyList.add(cBean);
		setMedType("Non-Liquid");
		return Action.SUCCESS;
	}
	
	public String sellByMonths()
	{
		return "success";		
	}
	
	public MedicineBean getMedicineBean() {
		return medicineBean;
	}

	public void setMedicineBean(MedicineBean medicineBean) {
		this.medicineBean = medicineBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<MedicineBean> getRows() {
		return totalRecords;
	}

	public List<MedicineBean> getFooter() {
		return footer;
	}

	public void setFooter(List<MedicineBean> footer) {
		this.footer = footer;
	}

	public MedicineBean getModel() {
		updateUserDetailsFromSession(medicineBean);
		return medicineBean;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
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

	public String getMedicineDesc() {
		return medicineDesc;
	}

	public void setMedicineDesc(String medicineDesc) {
		this.medicineDesc = medicineDesc;
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<MedicineBean> getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(List<MedicineBean> totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getSUCCESS_MSG() {
		return SUCCESS_MSG;
	}

	public String getFAILED_MSG() {
		return FAILED_MSG;
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

	public int getMedicineDetailsId() {
		return medicineDetailsId;
	}

	public void setMedicineDetailsId(int medicineDetailsId) {
		this.medicineDetailsId = medicineDetailsId;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getSoldoutUnitPrice() {
		return soldoutUnitPrice;
	}

	public void setSoldoutUnitPrice(double soldoutUnitPrice) {
		this.soldoutUnitPrice = soldoutUnitPrice;
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
