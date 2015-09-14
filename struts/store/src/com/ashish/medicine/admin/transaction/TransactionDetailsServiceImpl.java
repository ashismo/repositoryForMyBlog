package com.ashish.medicine.admin.transaction;

import com.ashish.medicine.exception.AppException;

public class TransactionDetailsServiceImpl implements TransactionDetailsService {

	public void addOrUpdateTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsServiceHelperImpl().addOrUpdateTransactionDetails(transactionDetailsBean);
	}

	public void searchTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsServiceHelperImpl().searchTransactionDetails(transactionDetailsBean);
	}
	
	public void searchRevenueDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsServiceHelperImpl().searchRevenueDetails(transactionDetailsBean);
	}

	public void viewTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsServiceHelperImpl().viewTransactionDetails(transactionDetailsBean);
	}

	public void deleteTransactionDetails(TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsServiceHelperImpl().deleteTransactionDetails(transactionDetailsBean);
	}

	public void getAllCompanies(TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsServiceHelperImpl().getAllCompanies(transactionDetailsBean);
	}

	public void getTransactionDetailsByInvoiceId(
			TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsServiceHelperImpl().getTransactionDetailsByInvoiceId(transactionDetailsBean);
	}

	public void downloadAttachment(TransactionDetailsBean transactionDetailsBean)
			throws AppException {
		new TransactionDetailsServiceHelperImpl().downloadAttachment(transactionDetailsBean);
		
	}

	public void uploadAttachment(TransactionDetailsBean transactionDetailsBean)
			throws AppException {
		new TransactionDetailsServiceHelperImpl().uploadAttachment(transactionDetailsBean);
		
	}

	public void updateAttachmentDetails(
			TransactionDetailsBean transactionDetailsBean) throws AppException {
		 new TransactionDetailsServiceHelperImpl().updateAttachmentDetails(transactionDetailsBean);
		
	}

	public void viewAttachmentDetails(
			TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsServiceHelperImpl().viewAttachmentDetails(transactionDetailsBean);
		
	}

	@Override
	public void deleteAttachmentDetails(
			TransactionDetailsBean transactionDetailsBean) throws AppException {
		new TransactionDetailsServiceHelperImpl().deleteAttachmentDetails(transactionDetailsBean);
	}

}
