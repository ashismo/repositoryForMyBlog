package com.ashish.medicine.admin.invoice_bck;

import com.ashish.medicine.exception.AppException;

public class InvoiceServiceHelperImpl implements InvoiceServiceHelper {

	public void addOrUpdateInvoice(InvoiceBean invoiceBean) throws AppException {
		new InvoiceDaoImpl().addOrUpdateInvoice(invoiceBean);
	}

	public void searchInvoice(InvoiceBean invoiceBean) throws AppException {
		new InvoiceDaoImpl().searchInvoice(invoiceBean);
	}

	public void viewInvoice(InvoiceBean invoiceBean) throws AppException {
		new InvoiceDaoImpl().viewInvoice(invoiceBean);
	}

	public void deleteInvoice(InvoiceBean invoiceBean) throws AppException {
		new InvoiceDaoImpl().deleteInvoice(invoiceBean);		
	}

	public void getAllInvoices(InvoiceBean invoiceBean) throws AppException {
		new InvoiceDaoImpl().getAllInvoices(invoiceBean);		
	}
}
