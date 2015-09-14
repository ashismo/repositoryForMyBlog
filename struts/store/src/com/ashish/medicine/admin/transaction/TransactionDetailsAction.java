package com.ashish.medicine.admin.transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.ashish.medicine.admin.base.MedicineBaseAction;
import com.ashish.medicine.admin.medRep.MedRepBean;
import com.ashish.medicine.admin.usermanagement.UserManagementBean;
import com.ashish.medicine.admin.usermanagement.UserManagementServiceImpl;
import com.ashish.medicine.admin.wholeseller.WholeSellerBean;
import com.ashish.medicine.admin.wholeseller.WholeSellerServiceImpl;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.util.MedicineUtility;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TransactionDetailsAction extends MedicineBaseAction implements ModelDriven<TransactionDetailsBean> {
	private static final long serialVersionUID = -2613425890762568273L;
	private TransactionDetailsBean transactionDetailsBean = new TransactionDetailsBean();
	private InputStream inputStream;
	private String filename;
	private String contentType;
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	
	private int total;
	private List<TransactionDetailsBean> totalRecords; 
	private List<TransactionDetailsBean> footer;
	private String msg;
	private String errMsg;
	private final String SUCCESS_MSG = "Successfully updated";
	private final String FAILED_MSG = "Operation failed!!!!!";

	private int transactionDetailsId;
	private Timestamp dbAddTs;
	private int dbAddUser;
	private Timestamp dbUpdTs;
	private int dbUpdUser;
	
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
	private int attachmentId;
	private byte[] attachedFile;
	private String attachmentDesc;
	
	private List<TransactionDetailsBean> searchTransactionDetailsList = new ArrayList<TransactionDetailsBean>();
	private List<TransactionDetailsBean> transactionDetailsList = new ArrayList<TransactionDetailsBean>();
	private List<WholeSellerBean> wholesellerList = new ArrayList<WholeSellerBean>();
	private List<MedRepBean> medRepList = new ArrayList<MedRepBean>();
	
//	public TransactionDetailsAction() {
//		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
//		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
//	}
	
	public String transactionManagementDetails()
	{
		
		try {
			WholeSellerBean wholesellerBean = new WholeSellerBean();
			new WholeSellerServiceImpl().getAllWholesellers(wholesellerBean);
			transactionDetailsBean.setWholesellerList(wholesellerBean.getWholeSellerList());
			
			UserManagementBean userManagementBean = new UserManagementBean();
			new UserManagementServiceImpl().getAllUsers(userManagementBean);
			List<UserManagementBean> userMgmtList = userManagementBean.getSearchUserList();
			if(userMgmtList != null && userMgmtList.size() > 0) {
				UserManagementBean uBean = new UserManagementBean();
				uBean.setUserId(-1);
				uBean.setUserName("All");
				userMgmtList.add(0, uBean);
			}
			transactionDetailsBean.setUsersList(userMgmtList);
		} catch (AppException e) {
			e.printStackTrace();
		}
		return "success";		
	}
	public String searchRevenueDetails()
	{
		try {
			new TransactionDetailsServiceImpl().searchRevenueDetails(transactionDetailsBean);
			
			// Calculate Profit
			List<TransactionDetailsBean> incomeAndExpList =  transactionDetailsBean.getSearchTransactionDetailsList();
			double totalExp = 0;
			double totalIncome = 0;
			String profit = "";
			if(incomeAndExpList != null && incomeAndExpList.size() == 2) {
				totalExp = incomeAndExpList.get(0).getTotalAmt();
				totalIncome = incomeAndExpList.get(1).getTotalAmt();
			}
			MedicineUtility mUtil = new MedicineUtility();
			profit = mUtil.getFormattedAmount(totalIncome - totalExp);
			List<TransactionDetailsBean> footerList = new ArrayList<TransactionDetailsBean>();
			TransactionDetailsBean tx = new TransactionDetailsBean();
			tx.setPaymentMode("Total Profit");
			tx.setTotalAmt(Double.parseDouble(profit));
			footerList.add(tx);

			footer = footerList;
			
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(transactionDetailsBean.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(transactionDetailsBean.getMsg());
		}
		totalRecords = transactionDetailsBean.getSearchTransactionDetailsList();
		total = transactionDetailsBean.getTotal();
		return Action.SUCCESS;		
	}
	
	public String searchTransactionDetails()
	{
		try {
			List<TransactionDetailsBean> footerList = new ArrayList<TransactionDetailsBean>();
			new TransactionDetailsServiceImpl().searchTransactionDetails(transactionDetailsBean);
			TransactionDetailsBean tx = new TransactionDetailsBean();
			tx.setCardNumber("Total Amount");
			tx.setTotalAmt(transactionDetailsBean.getTotalAmt());
			tx.setTotalPaid(transactionDetailsBean.getTotalPaid());
			tx.setTotalDueAmount(transactionDetailsBean.getTotalDueAmount());
			footerList.add(tx);
			tx = new TransactionDetailsBean();
			BeanUtils.copyProperties(tx, transactionDetailsBean);
			tx.setPage(1);
			tx.setRows(100000);
			tx.setTotalAmt(0);
			tx.setTotalPaid(0);
			tx.setTotalDueAmount(0);
			new TransactionDetailsServiceImpl().searchTransactionDetails(tx);
			tx.setCardNumber("Grand total");
			footerList.add(tx);

			footer = footerList;
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(transactionDetailsBean.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(transactionDetailsBean.getMsg());
		}
		totalRecords = transactionDetailsBean.getSearchTransactionDetailsList();
		total = transactionDetailsBean.getTotal();
		return Action.SUCCESS;		
	}
	
	public String viewTransactionDetails()
	{
		try {
			new TransactionDetailsServiceImpl().viewTransactionDetails(transactionDetailsBean);
			BeanUtils.copyProperties(this, transactionDetailsBean);
			setMsg(SUCCESS_MSG);
		} catch (Exception e) {
			setMsg("Unable to view transactionDetails");
			e.printStackTrace();
		}
		return "success";		
	}
	
	public String attachFileDetails() {
		try {
//			byte[] file = FileUtils.readFileToByteArray(fileUpload);
			FileInputStream fin = new FileInputStream(transactionDetailsBean.getFileUpload());
			byte[] file = new byte[(int)transactionDetailsBean.getFileUpload().length()];
			fin.read(file);
			transactionDetailsBean.setAttachedFile(file);
			new TransactionDetailsServiceImpl().uploadAttachment(transactionDetailsBean);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "failure";
		} catch (IOException e) {
			e.printStackTrace();
			return "failure";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
		return "success";
	}
	
	public String updateAttachmentDetails() {
		try {
			new TransactionDetailsServiceImpl().updateAttachmentDetails(transactionDetailsBean);
			setMsg(transactionDetailsBean.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			setErrMsg("Unable to update the record");
		}
		return "success";
	}
	
	public String deleteAttachmentDetails() {
		try {
			new TransactionDetailsServiceImpl().deleteAttachmentDetails(transactionDetailsBean);
			setMsg(transactionDetailsBean.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			setErrMsg("Unable to update the record");
		}
		return "success";
	}
	
	public String downloadAttachment() {
		try {
			new TransactionDetailsServiceImpl().downloadAttachment(transactionDetailsBean);
			inputStream = transactionDetailsBean.getInputStream();
			filename = transactionDetailsBean.getFilename();
			contentType = transactionDetailsBean.getContentType();
			if(inputStream == null || filename == null || contentType == null) {
				throw new AppException("Unable to download the attached file");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setErrMsg("Unable to download the attached file");
		}
		return "success";
	}
	
	public String deleteAttachment() {
		try {
			new TransactionDetailsServiceImpl().deleteAttachmentDetails(transactionDetailsBean);
		} catch (Exception e) {
			e.printStackTrace();
			setErrMsg("Unable to delete the attached file");
		}
		return "success";
	}
	
	public String viewAttachmentDetails() {
		try {
			new TransactionDetailsServiceImpl().viewAttachmentDetails(transactionDetailsBean);
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(transactionDetailsBean.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(transactionDetailsBean.getMsg());
		}
		totalRecords = transactionDetailsBean.getSearchTransactionDetailsList();
		total = transactionDetailsBean.getTotal();
		return Action.SUCCESS;
	}
	public String transactionDetailsByInvoiceId()
	{
		try {
			new TransactionDetailsServiceImpl().getTransactionDetailsByInvoiceId(transactionDetailsBean);
			List<TransactionDetailsBean> footerList = new ArrayList<TransactionDetailsBean>();
			TransactionDetailsBean tx = new TransactionDetailsBean();
			tx.setPaymentDate("Total Amount");
			tx.setTotalPaid(transactionDetailsBean.getTotalPaid());
			footerList.add(tx);

			footer = footerList;
			setMsg(SUCCESS_MSG);
		} catch (AppException e) {
			e.printStackTrace();
			setMsg(transactionDetailsBean.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(transactionDetailsBean.getMsg());
		}
		totalRecords = transactionDetailsBean.getSearchTransactionDetailsList();
		total = transactionDetailsBean.getTotal();
		return "success";		
	}
	
	public String addTransactionDetailsForm() {
		return ActionSupport.SUCCESS;
	}
	
	public String addOrUpdateTransactionDetails()
	{
		System.out.println("Create/Update a new TransactionDetails");
		try {
//			transactionDetailsBean.setMedRepId(Integer.parseInt(transactionDetailsBean.getMedRepName()));
			new TransactionDetailsServiceImpl().addOrUpdateTransactionDetails(transactionDetailsBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(transactionDetailsBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String allCompanies()
	{
		System.out.println("Fetch Companies");
		try {
			new TransactionDetailsServiceImpl().getAllCompanies(transactionDetailsBean);
			transactionDetailsBean.setTransactionDetailsList(transactionDetailsBean.getSearchTransactionDetailsList());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(transactionDetailsBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String deleteTransactionDetails()
	{
		System.out.println("Delete TransactionDetails");
		try {
			new TransactionDetailsServiceImpl().deleteTransactionDetails(transactionDetailsBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			setMsg(transactionDetailsBean.getMsg());
		}
		
		return Action.SUCCESS;		
	}
	
	public String sellByMonths()
	{
		return "success";		
	}
	
	public TransactionDetailsBean getTransactionDetailsBean() {
		return transactionDetailsBean;
	}

	public void setTransactionDetailsBean(TransactionDetailsBean transactionDetailsBean) {
		this.transactionDetailsBean = transactionDetailsBean;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<TransactionDetailsBean> getTotalRecords() {
		return totalRecords;
	}

	public List<TransactionDetailsBean> getRows() {
		return totalRecords;
	}
	
	public void setTotalRecords(List<TransactionDetailsBean> totalRecords) {
		this.totalRecords = totalRecords;
	}

	public TransactionDetailsBean getModel() {
		updateUserDetailsFromSession(transactionDetailsBean);
		return transactionDetailsBean;
	}

	public int getTransactionDetailsId() {
		return transactionDetailsId;
	}

	public void setTransactionDetailsId(int transactionDetailsId) {
		this.transactionDetailsId = transactionDetailsId;
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

	public List<MedRepBean> getMedRepList() {
		return medRepList;
	}

	public void setMedRepList(List<MedRepBean> medRepList) {
		this.medRepList = medRepList;
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

	public int getWholesellerId() {
		return wholesellerId;
	}

	public void setWholesellerId(int wholesellerId) {
		this.wholesellerId = wholesellerId;
	}

	public String getMedRepName() {
		return medRepName;
	}

	public void setMedRepName(String medRepName) {
		this.medRepName = medRepName;
	}

	public String getWholesellerName() {
		return wholesellerName;
	}

	public void setWholesellerName(String wholesellerName) {
		this.wholesellerName = wholesellerName;
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

	public List<TransactionDetailsBean> getFooter() {
		return footer;
	}

	public void setFooter(List<TransactionDetailsBean> footer) {
		this.footer = footer;
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
