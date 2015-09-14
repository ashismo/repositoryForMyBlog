package com.ashish.medicine.admin.invoice;

import com.ashish.medicine.entity.Invoice;
import com.ashish.medicine.exception.AppException;

public interface InvoiceDao {
	public void addOrUpdateInvoice(InvoiceBean invoiceBean) throws AppException;
	public void addOrUpdateInvoiceDetails(InvoiceBean invoiceBean) throws AppException;
	public void getAllInvoices(InvoiceBean invoiceBean) throws AppException;
	public void searchInvoice(InvoiceBean invoiceBean) throws AppException;
	public void viewInvoice(InvoiceBean invoiceBean) throws AppException;
	public void deleteInvoice(InvoiceBean invoiceBean) throws AppException;
	public Invoice getInvoiceByInvoiceId(InvoiceBean invoiceBean) throws AppException;
	public void revertPrevStockBack(InvoiceBean invoiceBean) throws AppException;
	public void printInvoice(InvoiceBean invoiceBean) throws AppException;
}
