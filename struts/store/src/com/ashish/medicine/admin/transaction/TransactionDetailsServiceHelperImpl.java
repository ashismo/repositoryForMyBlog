package com.ashish.medicine.admin.transaction;

import com.ashish.medicine.exception.AppException;

public class TransactionDetailsServiceHelperImpl implements TransactionDetailsServiceHelper {

	public void addOrUpdateTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsDaoImpl().addOrUpdateTransactionDetails(transactionDetailsBean);
	}

	public void searchTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsDaoImpl().searchTransactionDetails(transactionDetailsBean);
	}

	public void searchRevenueDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsDaoImpl().searchRevenueDetails(transactionDetailsBean);
	}
	
	public void viewTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsDaoImpl().viewTransactionDetails(transactionDetailsBean);
	}

	public void deleteTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsDaoImpl().deleteTransactionDetails(transactionDetailsBean);		
	}

	public void getAllCompanies(TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsDaoImpl().getAllCompanies(transactionDetailsBean);		
	}

	public void getTransactionDetailsByInvoiceId(
			TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsDaoImpl().getTransactionDetailsByInvoiceId(transactionDetailsBean);
	}

	public void downloadAttachment(TransactionDetailsBean transactionDetailsBean)
			throws AppException {
		new TransactionDetailsDaoImpl().downloadAttachment(transactionDetailsBean);
	}

	public void uploadAttachment(TransactionDetailsBean transactionDetailsBean)
			throws AppException {
		new TransactionDetailsDaoImpl().uploadAttachment(transactionDetailsBean);
	}

	public void updateAttachmentDetails(
			TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsDaoImpl().updateAttachmentDetails(transactionDetailsBean);
		
	}

	public void viewAttachmentDetails(
			TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsDaoImpl().viewAttachmentDetails(transactionDetailsBean);
		
	}

	@Override
	public void deleteAttachmentDetails(
			TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsDaoImpl().deleteAttachmentDetails(transactionDetailsBean);
	}
}
