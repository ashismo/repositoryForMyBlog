package com.ashish.medicine.admin.transaction;

import com.ashish.medicine.exception.AppException;

public interface TransactionDetailsServiceHelper {
	public void addOrUpdateTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException;
	public void getAllCompanies(TransactionDetailsBean transactionDetailsBean) throws AppException;
	public void searchTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException;
	public void searchRevenueDetails(TransactionDetailsBean transactionDetailsBean) throws AppException;
	public void getTransactionDetailsByInvoiceId(TransactionDetailsBean transactionDetailsBean) throws AppException;
	public void viewTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException;
	public void deleteTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException;
	public void uploadAttachment(TransactionDetailsBean transactionDetailsBean) throws AppException;
	public void downloadAttachment(TransactionDetailsBean transactionDetailsBean) throws AppException;
	public void viewAttachmentDetails(TransactionDetailsBean transactionDetailsBean) throws AppException;
	public void updateAttachmentDetails(TransactionDetailsBean transactionDetailsBean) throws AppException;
	public void deleteAttachmentDetails(TransactionDetailsBean transactionDetailsBean) throws AppException;
}
