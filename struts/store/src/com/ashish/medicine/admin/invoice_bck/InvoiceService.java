package com.ashish.medicine.admin.invoice_bck;

import com.ashish.medicine.exception.AppException;

public interface InvoiceService {
	public void addOrUpdateInvoice(InvoiceBean invoiceBean) throws AppException;
	public void getAllInvoices(InvoiceBean invoiceBean) throws AppException;
	public void searchInvoice(InvoiceBean invoiceBean) throws AppException;
	public void viewInvoice(InvoiceBean invoiceBean) throws AppException;
	public void deleteInvoice(InvoiceBean invoiceBean) throws AppException;
}
