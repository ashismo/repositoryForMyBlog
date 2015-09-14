package com.ashish.medicine.admin.transaction;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ashish.medicine.admin.base.MedicineBaseBean;
import com.ashish.medicine.admin.medRep.MedRepBean;
import com.ashish.medicine.admin.usermanagement.UserManagementBean;
import com.ashish.medicine.admin.wholeseller.WholeSellerBean;
import com.ashish.medicine.common.bean.States;

public class TransactionDetailsBean extends MedicineBaseBean implements Serializable {
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	private int transactionDetailsId;
	private InputStream inputStream;
	private String filename;
	private String contentType;
	private String msg;
	private String errMsg;
	
	private String income;
	private String expenditure;
	private String revenue;
	private String startDate;
	private Date startDateActual;
	private String endDate;
	private Date endDateActual;
	
	private String customerName;
	private String mob1;
	private int wholesellerId;
	private String wholesellerName;
	private int medRepId;
	private String medRepName;
	private int invoiceId;
	private String paymentDate;
	private Date paymentDateActual;
	private double totalPaidAmount;
	private double totalAmt;
	private double totalPaid;
	private double totalDueAmount;
	private String paymentMode;
	private String cardNumber;
	private String transactionDesc;
	private String buyOrSell;
	private int attachmentId;
	private byte[] attachedFile;
	private String attachmentDesc;
	
	private List<TransactionDetailsBean> searchTransactionDetailsList = new ArrayList<TransactionDetailsBean>();
	private List<TransactionDetailsBean> transactionDetailsList = new ArrayList<TransactionDetailsBean>();
	private List<WholeSellerBean> wholesellerList = new ArrayList<WholeSellerBean>();
	private List<UserManagementBean> usersList = new ArrayList<UserManagementBean>();
	
	private List<MedRepBean> medRepList = new ArrayList<MedRepBean>();
	
	// Pagination Parameters
	private int rows;
	private int page;
	private String order;
	private String sort;
	private int total;
	public int getTransactionDetailsId() {
		return transactionDetailsId;
	}
	public void setTransactionDetailsId(int transactionDetailsId) {
		this.transactionDetailsId = transactionDetailsId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Date getPaymentDateActual() {
		return paymentDateActual;
	}
	public void setPaymentDateActual(Date paymentDateActual) {
		this.paymentDateActual = paymentDateActual;
	}
	public double getTotalPaidAmount() {
		return totalPaidAmount;
	}
	public void setTotalPaidAmount(double totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
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
	public double getTotalDueAmount() {
		return totalDueAmount;
	}
	public void setTotalDueAmount(double totalDueAmount) {
		this.totalDueAmount = totalDueAmount;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getTransactionDesc() {
		return transactionDesc;
	}
	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}
	public List<TransactionDetailsBean> getSearchTransactionDetailsList() {
		return searchTransactionDetailsList;
	}
	public void setSearchTransactionDetailsList(
			List<TransactionDetailsBean> searchTransactionDetailsList) {
		this.searchTransactionDetailsList = searchTransactionDetailsList;
	}
	public List<TransactionDetailsBean> getTransactionDetailsList() {
		return transactionDetailsList;
	}
	public void setTransactionDetailsList(
			List<TransactionDetailsBean> transactionDetailsList) {
		this.transactionDetailsList = transactionDetailsList;
	}
	public List<WholeSellerBean> getWholesellerList() {
		return wholesellerList;
	}
	public void setWholesellerList(List<WholeSellerBean> wholesellerList) {
		this.wholesellerList = wholesellerList;
	}
	public List<UserManagementBean> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<UserManagementBean> usersList) {
		this.usersList = usersList;
	}
	public List<MedRepBean> getMedRepList() {
		return medRepList;
	}
	public void setMedRepList(List<MedRepBean> medRepList) {
		this.medRepList = medRepList;
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
	public int getMedRepId() {
		return medRepId;
	}
	public void setMedRepId(int medRepId) {
		this.medRepId = medRepId;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMob1() {
		return mob1;
	}
	public void setMob1(String mob1) {
		this.mob1 = mob1;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(String expenditure) {
		this.expenditure = expenditure;
	}
	public String getRevenue() {
		return revenue;
	}
	public void setRevenue(String revenue) {
		this.revenue = revenue;
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
	public String getMedRepName() {
		return medRepName;
	}
	public void setMedRepName(String medRepName) {
		this.medRepName = medRepName;
	}
	public String getBuyOrSell() {
		return buyOrSell;
	}
	public void setBuyOrSell(String buyOrSell) {
		this.buyOrSell = buyOrSell;
	}
	public int getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}
	public byte[] getAttachedFile() {
		return attachedFile;
	}
	public void setAttachedFile(byte[] attachedFile) {
		this.attachedFile = attachedFile;
	}
	public String getAttachmentDesc() {
		return attachmentDesc;
	}
	public void setAttachmentDesc(String attachmentDesc) {
		this.attachmentDesc = attachmentDesc;
	}
	public File getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileUploadContentType() {
		return fileUploadContentType;
	}
	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
	public String getFileUploadFileName() {
		return fileUploadFileName;
	}
	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
