package com.ashish.medicine.admin.order;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

import com.ashish.medicine.admin.base.MedicineBaseAction;
import com.ashish.medicine.admin.medicine.MedicineBean;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.util.MedicineUtility;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends MedicineBaseAction implements ModelDriven<OrderBean> {
	private OrderBean orderBean = new OrderBean();
	MedicineUtility mUtil = new MedicineUtility();
	private static final long serialVersionUID = -2613425890762568273L;
	private final String SUCCESS_MSG = "Successfully updated";
	private final String FAILED_MSG = "Operation failed!!!!!";
	private int total;
	private List<OrderBean> rows; 
	private String msg;
	private String errorMsg;

	
	private String medDose;
	private String medType;
	private String medWeight;
	private Integer availableStock;
	private String batchName;
	private String medicineName;
	private int medicineId;
	private String companyName;
	private int companyId;
	private Date mfgDateActual;
	private Date expDateActual;
	private String mfgDate;
	private String expDate;
	private String medicineDetails;
	
	private Integer orderId;
	private String lowStock;
	private String recentNotes;
	private String notesCriteria;
	private String executedOrder;
	private String orderDesc;
	private String orderDate;
	private String orderExecutionDate;
	private Date orderDateActual;
	private Date orderExecutionDateActual;
	private String startDate;
	private Date startDateActual;
	private String endDate;
	private Date endDateActual;
	private String orderStatus;
	private MedicineBean medicineBean;
	private List<OrderBean> totalRecords; 
	
//	public OrderAction() {
//		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
//		ConvertUtils.register(new DateConverter(null), Date.class);
//	}
	
	public String orderManagementDetails()
	{
		return "success";		
	}
	
	public String searchOrderDetails()
	{
		try {
			new OrderServiceImpl().searchOrderDetails(orderBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(medicineBean.getMsg());
		}
		totalRecords = orderBean.getSearchOrderList();
		total = orderBean.getTotal();
		return Action.SUCCESS;		
	}
	
	public String deleteOrder()
	{
		try {
			new OrderServiceImpl().deleteOrder(orderBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(orderBean.getMsg());
		}
		return Action.SUCCESS;		
	}
	
	public String addOrUpdateOrder()
	{
		System.out.println("Create Order");
		try {
//			BeanUtils.copyProperties(orderBean, this);
			new OrderServiceImpl().addOrUpdateOrder(orderBean);
			setMsg("Successfully updated");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorMsg(orderBean.getErrorMsg());
		}
		
		return Action.SUCCESS;		
	}

	public String deleteNotes()
	{
		try {
			new OrderServiceImpl().deleteNotes(orderBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(orderBean.getMsg());
		}
		return Action.SUCCESS;		
	}

	
	public String addOrUpdateNotes()
	{
		System.out.println("Create Order");
		try {
			new OrderServiceImpl().addOrUpdateNotes(orderBean);
			setMsg("Successfully updated");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorMsg(orderBean.getErrorMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public OrderBean getOrderBean() {
		return orderBean;
	}

	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<OrderBean> getRows() {
		return totalRecords;
	}

	public OrderBean getModel() {
		updateUserDetailsFromSession(orderBean);
		return orderBean;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getLowStock() {
		return lowStock;
	}

	public void setLowStock(String lowStock) {
		this.lowStock = lowStock;
	}

	public String getRecentNotes() {
		return recentNotes;
	}

	public void setRecentNotes(String recentNotes) {
		this.recentNotes = recentNotes;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public MedicineBean getMedicineBean() {
		return medicineBean;
	}

	public void setMedicineBean(MedicineBean medicineBean) {
		this.medicineBean = medicineBean;
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderExecutionDate() {
		return orderExecutionDate;
	}

	public void setOrderExecutionDate(String orderExecutionDate) {
		this.orderExecutionDate = orderExecutionDate;
	}

	public Date getOrderDateActual() {
		return orderDateActual;
	}

	public void setOrderDateActual(Date orderDateActual) {
		this.orderDateActual = orderDateActual;
	}

	public Date getOrderExecutionDateActual() {
		return orderExecutionDateActual;
	}

	public void setOrderExecutionDateActual(Date orderExecutionDateActual) {
		this.orderExecutionDateActual = orderExecutionDateActual;
	}

	public List<OrderBean> getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(List<OrderBean> totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getExecutedOrder() {
		return executedOrder;
	}

	public void setExecutedOrder(String executedOrder) {
		this.executedOrder = executedOrder;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Date getStartDateActual() {
		return startDateActual;
	}

	public void setStartDateActual(Date startDateActual) {
		this.startDateActual = startDateActual;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Date getEndDateActual() {
		return endDateActual;
	}

	public void setEndDateActual(Date endDateActual) {
		this.endDateActual = endDateActual;
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


	public Integer getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(Integer availableStock) {
		this.availableStock = availableStock;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Date getExpDateActual() {
		return expDateActual;
	}

	public void setExpDateActual(Date expDateActual) {
		this.expDateActual = expDateActual;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getMedicineDetails() {
		return medicineDetails;
	}

	public void setMedicineDetails(String medicineDetails) {
		this.medicineDetails = medicineDetails;
	}

	public Date getMfgDateActual() {
		return mfgDateActual;
	}

	public void setMfgDateActual(Date mfgDateActual) {
		this.mfgDateActual = mfgDateActual;
	}

	public String getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(String mfgDate) {
		this.mfgDate = mfgDate;
	}

	public String getNotesCriteria() {
		return notesCriteria;
	}

	public void setNotesCriteria(String notesCriteria) {
		this.notesCriteria = notesCriteria;
	}
	
}
