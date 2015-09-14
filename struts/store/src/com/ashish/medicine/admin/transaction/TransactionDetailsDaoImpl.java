package com.ashish.medicine.admin.transaction;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.ashish.medicine.admin.base.MedicineBaseDaoImpl;
import com.ashish.medicine.admin.company.CompanyBean;
import com.ashish.medicine.admin.invoice.InvoiceBean;
import com.ashish.medicine.admin.medRep.MedRepBean;
import com.ashish.medicine.admin.medRep.MedRepDaoImpl;
import com.ashish.medicine.admin.wholeseller.WholeSellerBean;
import com.ashish.medicine.admin.wholeseller.WholeSellerDaoImpl;
import com.ashish.medicine.entity.Attachment;
import com.ashish.medicine.entity.Company;
import com.ashish.medicine.entity.Invoice;
import com.ashish.medicine.entity.MedRep;
import com.ashish.medicine.entity.TransactionDetails;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.hinernate.util.HibernateUtil;
import com.ashish.medicine.util.MedicineUtility;

public class TransactionDetailsDaoImpl extends MedicineBaseDaoImpl implements TransactionDetailsDao {

//	private Session s = null;
	private MedicineUtility mUtil = new MedicineUtility();
	
//	public TransactionDetailsDaoImpl() {
//		try {
//			s = HibernateUtil.getSessionFactory().openSession();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	public void addOrUpdateTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		Transaction tx = null;
		TransactionDetails trx = new TransactionDetails();
		TransactionDetails tempTrx = new TransactionDetails();
		Invoice invoice = null;
		try {
			BeanUtils.copyProperties(trx, transactionDetailsBean);
			if(trx.getTransactionDetailsId() != 0) {
				Query query = s.getNamedQuery("getTransactionDetailsByTransactionDetailsId");
				query.setLong("transactionDetailsId", transactionDetailsBean.getTransactionDetailsId());
				List<TransactionDetails> transactionDetailsList = query.list();
				
				if(transactionDetailsList != null && transactionDetailsList.size() == 1) {
					tempTrx = transactionDetailsList.get(0);
					tempTrx.setDbUpdUser(transactionDetailsBean.getDbUpdUser());
					tempTrx.setDbUpdTs(new Timestamp(new Date().getTime()));
					if(s != null) s.close();
					getHibernateSession();
				}
			} else {
				tempTrx.setDbAddUser(transactionDetailsBean.getDbAddUser());
				tempTrx.setDbAddTs(new Timestamp(new Date().getTime()));
				tempTrx.setDbUpdTs(new Timestamp(new Date().getTime()));
			}
			
			if(transactionDetailsBean.getPaymentDate() != null && !transactionDetailsBean.getPaymentDate().trim().equals("")) {
				trx.setPaymentDate(mUtil.getDateFromString(transactionDetailsBean.getPaymentDate()));
			}
			if(transactionDetailsBean.getInvoiceId() == 0) {
				// Fetch medicine representative id
				if(transactionDetailsBean.getMedRepId() != 0) {
					Query query = s.getNamedQuery("getMedRepByMedRepId");
					query.setLong("medRepId", transactionDetailsBean.getMedRepId());
					List<MedRep> medRepList = query.list();
					
					if(medRepList != null && medRepList.size() == 1) {
						if(trx != null) { 
							trx.setMedRep(medRepList.get(0));
							trx.setBuyOrSell("BUY");
						}
					}
					
				}
			} else {
				// Fetch invoice entity
				if(transactionDetailsBean.getInvoiceId() != 0) {
					Query query = s.getNamedQuery("getInvoiceByInvoiceId");
					query.setLong("invoiceId", transactionDetailsBean.getInvoiceId());
					List<Invoice> trxList = query.list();
					
					if(trxList != null && trxList.size() == 1) {
						if(trx != null) { 
							trx.setInvoice(trxList.get(0));
							trx.setBuyOrSell("SELL");
						}
						invoice = trxList.get(0);
					}
				}
			}
			
			trx.setDbAddTs(tempTrx.getDbAddTs());
			trx.setDbUpdTs(tempTrx.getDbUpdTs());
			trx.setDbAddUser(tempTrx.getDbAddUser());
			trx.setDbUpdUser(tempTrx.getDbUpdUser());
			tx = s.beginTransaction();
			s.saveOrUpdate(trx);
			transactionDetailsBean.setTransactionDetailsId(trx.getTransactionDetailsId());
			if(invoice != null) {
				// Update invoice table by the amount paid
				Query query = s.getNamedQuery("getTransactionAmtByInvoiceId");
				query.setLong("invoiceId", transactionDetailsBean.getInvoiceId());
				List<TransactionDetailsBean> txList = 
							query.setResultTransformer(Transformers.aliasToBean(TransactionDetailsBean.class)).list();
				for(TransactionDetailsBean t: txList) {
					transactionDetailsBean.setTotalPaid(t.getTotalPaid());
				}
				invoice.setTotalPaid(transactionDetailsBean.getTotalPaid());
				invoice.setDbUpdUser(transactionDetailsBean.getDbUpdUser());
				invoice.setDbUpdTs(new Timestamp(new Date().getTime()));
				s.saveOrUpdate(invoice);
			}
			transactionDetailsBean.setMsg("Transaction Saved successfully!!!!!");
		} catch (Exception e) {
			transactionDetailsBean.setTransactionDetailsId(0);
			if(transactionDetailsBean.getMsg() != null && "".equals(transactionDetailsBean.getMsg().trim())) {
				transactionDetailsBean.setMsg("Operation failed");
			}
			tx.rollback();
			e.printStackTrace();
			throw new AppException();
		} finally {
			try {
				if(tx != null)tx.commit();
			} catch (Exception e) {
				if(tx != null)tx.rollback();
				e.printStackTrace();
				transactionDetailsBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
		
	}

	public void searchTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		try {
			
			Date startDate = null;
			Date endDate = null;
			String paymentMode = "%";
			if(transactionDetailsBean.getStartDate() != null && !transactionDetailsBean.getStartDate().trim().equals("")) {
				startDate = mUtil.getDateFromString(transactionDetailsBean.getStartDate());
			} else {
				startDate = mUtil.getDateSubtractedByDays(30);
			}
			if(transactionDetailsBean.getEndDate() != null && !transactionDetailsBean.getEndDate().trim().equals("")) {
				endDate = mUtil.getDateFromString(transactionDetailsBean.getEndDate());
			} else {
				endDate = new Date();
			}
			if(transactionDetailsBean.getPaymentMode() != null && !transactionDetailsBean.getPaymentMode().trim().equals("")) {
				paymentMode = transactionDetailsBean.getPaymentMode();
			}
			
			// Search Companies by page
			int startIndex = (transactionDetailsBean.getPage() - 1) * transactionDetailsBean.getRows();
			int totalRecords = transactionDetailsBean.getRows();
			String queryName = null;
			Query query = null;
			if(transactionDetailsBean.getIncome() != null) {
				queryName = "searchTransactionsIncome";
				query = s.getNamedQuery(queryName);
				String customerName = transactionDetailsBean.getCustomerName();
				String customerMob = transactionDetailsBean.getMob1();
				if(customerName == null || "".equals(customerName.trim())) {
					customerName = "%";
				} else {
					customerName = "%" + customerName + "%";
				}
				
				if(customerMob == null || "".equals(customerMob.trim())) {
					customerMob = "%";
				}
				
				query.setString("customerName", customerName);
				query.setString("customerMob", customerMob);
			} else if(transactionDetailsBean.getExpenditure() != null) {
				queryName = "searchTransactionsExpenditure";
				query = s.getNamedQuery(queryName);
				query.setInteger("wholesellerId", transactionDetailsBean.getWholesellerId());
				query.setInteger("medRepId", transactionDetailsBean.getMedRepId());
			}
			query.setString("paymentMode", paymentMode);
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
			
			List<TransactionDetailsBean> transactionCountList = 
				query.setResultTransformer(Transformers.aliasToBean(TransactionDetailsBean.class)).list();
			
			query.setFirstResult(startIndex);
			query.setMaxResults(totalRecords);
			List<TransactionDetailsBean> transactionDetailsList = 
							query.setResultTransformer(Transformers.aliasToBean(TransactionDetailsBean.class)).list();
			for(TransactionDetailsBean tx: transactionDetailsList) {
				tx.setPaymentDate(mUtil.getDateStrFromDate(tx.getPaymentDateActual()));
				transactionDetailsBean.setTotalPaid(transactionDetailsBean.getTotalPaid() + tx.getTotalPaid());
				transactionDetailsBean.setTotalAmt(transactionDetailsBean.getTotalAmt() + tx.getTotalAmt());
				transactionDetailsBean.setTotalDueAmount(transactionDetailsBean.getTotalDueAmount() + tx.getTotalDueAmount());
			}
			transactionDetailsBean.setSearchTransactionDetailsList(transactionDetailsList);
			
			// count companies
			transactionDetailsBean.setTotal(transactionCountList.size());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} 
	}

	public void searchRevenueDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		try {
			
			Date startDate = null;
			Date endDate = null;
			int userId = -1;
			String paymentMode = "%";
			
			if(transactionDetailsBean.getStartDate() != null && !transactionDetailsBean.getStartDate().trim().equals("")) {
				startDate = mUtil.getDateFromString(transactionDetailsBean.getStartDate());
			} else {
				startDate = mUtil.getDateSubtractedByDays(30);
			}
			if(transactionDetailsBean.getEndDate() != null && !transactionDetailsBean.getEndDate().trim().equals("")) {
				endDate = mUtil.getDateFromString(transactionDetailsBean.getEndDate());
			} else {
				endDate = new Date();
			}
			if(transactionDetailsBean.getUserId() != 0) {
				userId = transactionDetailsBean.getUserId();
			}
			
			if(transactionDetailsBean.getPaymentMode() != null && !transactionDetailsBean.getPaymentMode().trim().equals("")) {
				paymentMode = transactionDetailsBean.getPaymentMode();
			}
			
			List<TransactionDetailsBean>revenueList = new ArrayList<TransactionDetailsBean>();
			Query query = s.getNamedQuery("searchRevenueDetails");
			query.setString("buyOrSell", "BUY");
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
			query.setInteger("userId", userId);
			query.setString("paymentMode", paymentMode);
			
			List<TransactionDetailsBean> transactionDetailsList = 
							query.setResultTransformer(Transformers.aliasToBean(TransactionDetailsBean.class)).list();
			TransactionDetailsBean finalTx = new TransactionDetailsBean();
			if("%".equals(paymentMode)) {
				finalTx.setPaymentMode("Card/Cheque/Cash");
			} else {
				finalTx.setPaymentMode(paymentMode);
			}
			finalTx.setTransactionDesc("Expenses");
			finalTx.setStartDate(mUtil.getDateStrFromDate(startDate));
			finalTx.setEndDate(mUtil.getDateStrFromDate(endDate));
			for(TransactionDetailsBean tx: transactionDetailsList) {
				finalTx.setTotalAmt(finalTx.getTotalAmt() + tx.getTotalAmt());
				finalTx.setTotalPaid(finalTx.getTotalPaid() + tx.getTotalPaid());
				finalTx.setTotalDueAmount(finalTx.getTotalDueAmount() + (tx.getTotalAmt() - tx.getTotalPaid()));
			}
			
			revenueList.add(finalTx);
			
			query.setString("buyOrSell", "SELL");
			transactionDetailsList = 
				query.setResultTransformer(Transformers.aliasToBean(TransactionDetailsBean.class)).list();
			finalTx = new TransactionDetailsBean();
			if("%".equals(paymentMode)) {
				finalTx.setPaymentMode("Card/Cheque/Cash");
			} else {
				finalTx.setPaymentMode(paymentMode);
			}
			finalTx.setTransactionDesc("Income");
			finalTx.setStartDate(mUtil.getDateStrFromDate(startDate));
			finalTx.setEndDate(mUtil.getDateStrFromDate(endDate));
			for(TransactionDetailsBean tx: transactionDetailsList) {
//				tx.setTotalDueAmount(tx.getTotalAmt() - tx.getTotalPaid());
				// In case of selling medicine the total Amount = totalPaid amount and there is no due amount.
				finalTx.setTotalAmt(finalTx.getTotalAmt() + tx.getTotalPaid());
				finalTx.setTotalPaid(0);
				finalTx.setTotalDueAmount(0);
			}
			revenueList.add(finalTx);

			transactionDetailsBean.setSearchTransactionDetailsList(revenueList);
			
			// count trx
			transactionDetailsBean.setTotal(revenueList.size());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} 
	}

	
	public void viewTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		Query query = s.getNamedQuery("getTransactionDetailsByTransactionDetailsId");
		query.setLong("transactionDetailsId", transactionDetailsBean.getTransactionDetailsId());
		List<TransactionDetails> transactionDetailsList = query.list();
		
		if(transactionDetailsList != null && transactionDetailsList.size() == 1) {
			TransactionDetails tx = transactionDetailsList.get(0);
			String queryName = null;
			if(tx != null && tx.getInvoice() == null) {
				// get expenditure details
				queryName = "viewTransactionsExpenditure";
			} else if(tx != null && tx.getInvoice() != null) {
				// get income details 
			}
			try {
				query = s.getNamedQuery(queryName);
				query.setInteger("transactionDetailsId", transactionDetailsBean.getTransactionDetailsId());
				List<TransactionDetailsBean> txList = 
								query.setResultTransformer(Transformers.aliasToBean(TransactionDetailsBean.class)).list();
				for(TransactionDetailsBean tnx: txList) {
					MedRepBean mrb = new MedRepBean();
					mrb.setWholesellerId(transactionDetailsBean.getWholesellerId());
					new MedRepDaoImpl().getMedRepByWholeSellerId(mrb);
					tnx.setMedRepList(mrb.getMedRepList());
					tnx.setPaymentDate(mUtil.getDateStrFromDate(tnx.getPaymentDateActual()));
					BeanUtils.copyProperties(transactionDetailsBean, tnx);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new AppException();
		}
	}

	public void deleteTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		if(transactionDetailsBean.getTransactionDetailsId() == 0) {
			transactionDetailsBean.setMsg("Operation Failed...<br>Transaction Details Does not Exists");
			throw new AppException();
		}
		Query query = s.getNamedQuery("getTransactionDetailsByTransactionDetailsId");
		query.setLong("transactionDetailsId", transactionDetailsBean.getTransactionDetailsId());
		List<TransactionDetails> transactionDetailsList = query.list();
		
		if(transactionDetailsList != null && transactionDetailsList.size() == 1) {
			Transaction tx = null;
			try {
				tx = s.beginTransaction();
				// Business Logic: Delete from attachment table first
				query = s.getNamedQuery("getAttachmentDetailsByTransactionDetailsId");
				query.setInteger("transactionDetailsId", transactionDetailsList.get(0).getTransactionDetailsId());
				List<Attachment> attachmentLists = query.list();
				for(Attachment attachment : attachmentLists) {
					s.delete(attachment);
				}
				s.delete(transactionDetailsList.get(0));
				transactionDetailsBean.setMsg("Transaction Details Deleted Successfully");
			} catch (Exception e) {
				if(transactionDetailsBean.getMsg() != null && "".equals(transactionDetailsBean.getMsg().trim())) {
					transactionDetailsBean.setMsg("Operation failed");
				}
				e.printStackTrace();
				if(tx != null)tx.rollback();
				throw new AppException();
			} finally {
				try {
					if(tx != null)tx.commit();
				} catch (Exception e) {
					if(tx != null)tx.rollback();
					e.printStackTrace();
					transactionDetailsBean.setMsg("Operation failed. Unable to commit changes into database.");
				}
				if(s != null) s.close();
			}
		}
	}

	public void getAllCompanies(TransactionDetailsBean transactionDetailsBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getAllCompanies");
			List<TransactionDetailsBean> transactionDetailsList =
				query.setResultTransformer(Transformers.aliasToBean(TransactionDetailsBean.class)).list();
			
			transactionDetailsBean.setSearchTransactionDetailsList(transactionDetailsList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch all companies");
		} finally {
			s.close();
		}
	}
	
	public TransactionDetails getTransactionDetailsByTransactionDetailsId(TransactionDetailsBean transactionDetailsBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getTransactionDetailsByTransactionDetailsId");
			query.setLong("transactionDetailsId", transactionDetailsBean.getTransactionDetailsId());
			List<TransactionDetails> transactionDetailsList = query.list();
			
			if(transactionDetailsList != null && transactionDetailsList.size() == 1) {
				return transactionDetailsList.get(0);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
		return null;
	}

	public void getTransactionDetailsByInvoiceId(
			TransactionDetailsBean transactionDetailsBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getTransactionDetailsByInvoiceId");
			query.setLong("invoiceId", transactionDetailsBean.getInvoiceId());
			List<TransactionDetailsBean> txList = 
						query.setResultTransformer(Transformers.aliasToBean(TransactionDetailsBean.class)).list();
			for(TransactionDetailsBean tx: txList) {
				tx.setPaymentDate(mUtil.getDateStrFromDate(tx.getPaymentDateActual()));
				transactionDetailsBean.setTotalPaid(transactionDetailsBean.getTotalPaid() + tx.getTotalPaid());
			}
			transactionDetailsBean.setSearchTransactionDetailsList(txList);
			if(txList != null) {
				transactionDetailsBean.setTotal(txList.size());
			} else {
				transactionDetailsBean.setTotal(0);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
	}

	public void downloadAttachment(TransactionDetailsBean transactionDetailsBean)
			throws AppException {
		Query query = s.getNamedQuery("getAttachmentDetailsByAttachmentId");
		query.setInteger("attachmentId", transactionDetailsBean.getAttachmentId());
		List<Attachment> attachmentLists = query.list();
		if(attachmentLists != null && attachmentLists.size() == 1) {
			Attachment attachment = attachmentLists.get(0);
			if(attachment.getAttachedFile() != null) {
				byte[] attachedFile = attachment.getAttachedFile();
				transactionDetailsBean.setInputStream(new ByteArrayInputStream(attachedFile));
				transactionDetailsBean.setFilename(attachment.getFileName());
				transactionDetailsBean.setContentType(attachment.getFileContentType());
			} else {
				transactionDetailsBean.setMsg("File not found in the database");
			}
		} else {
			transactionDetailsBean.setMsg("File not found in the database");
		}
		
	}

	public void uploadAttachment(TransactionDetailsBean transactionDetailsBean)
			throws AppException {
		Transaction tx = s.beginTransaction();
		try {
			Attachment attachment = new Attachment();
			attachment.setAttachedFile(transactionDetailsBean.getAttachedFile());
			if(transactionDetailsBean.getTransactionDetailsId() != 0) {
				Query query = s.getNamedQuery("getTransactionDetailsByTransactionDetailsId");
				query.setLong("transactionDetailsId", transactionDetailsBean.getTransactionDetailsId());
				List<TransactionDetails> txList = query.list();
				if(txList != null && txList.size() > 0) {
					attachment.setTransactionDetail(txList.get(0));
				}
				attachment.setFileName(transactionDetailsBean.getFileUploadFileName());
				attachment.setFileContentType(transactionDetailsBean.getFileUploadContentType());
				attachment.setDbAddTs(new Timestamp(new Date().getTime()));
				attachment.setDbAddUser(transactionDetailsBean.getDbUpdUser());
			} else {
				attachment.setDbAddUser(transactionDetailsBean.getDbAddUser());
				attachment.setDbAddTs(new Timestamp(new Date().getTime()));
				attachment.setDbUpdTs(new Timestamp(new Date().getTime()));
			}
			
			attachment.setAttachmentDesc(transactionDetailsBean.getAttachmentDesc());
			s.saveOrUpdate(attachment);
		}  catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new AppException("Operation failed. Unable to upload file into database.");
		} finally {
			try {
				if(tx != null)tx.commit();
			} catch (Exception e) {
				if(tx != null)tx.rollback();
				e.printStackTrace();
				if(s != null) s.close();
				throw new AppException("Operation failed. Unable to upload file into database.");
			}
			if(s != null) s.close();
		}
	}

	public void updateAttachmentDetails(
			TransactionDetailsBean transactionDetailsBean) throws AppException {
		Query query = s.getNamedQuery("getAttachmentDetailsByAttachmentId");
		query.setInteger("attachmentId", transactionDetailsBean.getAttachmentId());
		List<Attachment> attachmentLists = query.list();
		if(attachmentLists != null && attachmentLists.size() == 1) {
			Transaction tx = null;
			try {
				Attachment attachment = attachmentLists.get(0);
				attachment.setAttachmentDesc(transactionDetailsBean.getAttachmentDesc());
				tx = s.beginTransaction();
				s.update(attachment);
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
				throw new AppException("Operation failed. Unable to update the record into database.");
			} finally {
				try {
					if(tx != null)tx.commit();
				} catch (Exception e) {
					if(tx != null)tx.rollback();
					e.printStackTrace();
					if(s != null) s.close();
					throw new AppException("Operation failed. Unable to update the record into database.");
				}
				if(s != null) s.close();
			}
			
			transactionDetailsBean.setMsg("Record updated into database");
		} else {
			transactionDetailsBean.setMsg("No record found in the database");
		}
	}

	public void deleteAttachmentDetails(
			TransactionDetailsBean transactionDetailsBean) throws AppException {
		Query query = s.getNamedQuery("getAttachmentDetailsByAttachmentId");
		query.setInteger("attachmentId", transactionDetailsBean.getAttachmentId());
		List<Attachment> attachmentLists = query.list();
		if(attachmentLists != null && attachmentLists.size() == 1) {
			Transaction tx = null;
			try {
				Attachment attachment = attachmentLists.get(0);
				tx = s.beginTransaction();
				s.delete(attachment);
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
				throw new AppException("Operation failed. Unable to delete the record from database.");
			} finally {
				try {
					if(tx != null)tx.commit();
				} catch (Exception e) {
					if(tx != null)tx.rollback();
					e.printStackTrace();
					if(s != null) s.close();
					throw new AppException("Operation failed. Unable to delete the record from database.");
				}
				if(s != null) s.close();
			}
			
			transactionDetailsBean.setMsg("Attachment deleted from database");
		} else {
			transactionDetailsBean.setMsg("No record found in the database");
		}
	}
	
	public void viewAttachmentDetails(
			TransactionDetailsBean transactionDetailsBean) throws AppException {
		try {
			// Search Attachments by page
			int startIndex = (transactionDetailsBean.getPage() - 1) * transactionDetailsBean.getRows();
			int totalRecords = transactionDetailsBean.getRows();
			Query query = s.getNamedQuery("viewAttachmentDetails");
			query.setInteger("transactionDetailsId", transactionDetailsBean.getTransactionDetailsId());
			List<TransactionDetailsBean> countAttachments = query.setResultTransformer(Transformers.aliasToBean(TransactionDetailsBean.class)).list();
//			List<Integer> countAttachments = query.list();
			if(countAttachments != null && countAttachments.size() > 0) {
				transactionDetailsBean.setTotal(countAttachments.size());
			} else {
				transactionDetailsBean.setTotal(0);
			}
			
			query.setFirstResult(startIndex);
			query.setMaxResults(totalRecords);
//			List<Attachment> attachmentList = query.list();
			List<TransactionDetailsBean> attachmentList = query.setResultTransformer(Transformers.aliasToBean(TransactionDetailsBean.class)).list();
//			List<TransactionDetailsBean> attachmentBeanList = new ArrayList<TransactionDetailsBean>();
//			for(Attachment c: attachmentList) {
//				TransactionDetailsBean txBean = new TransactionDetailsBean();
//				BeanUtils.copyProperties(txBean, c);
//				attachmentBeanList.add(txBean);
//			}
			transactionDetailsBean.setSearchTransactionDetailsList(attachmentList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} 
		
	}
}
