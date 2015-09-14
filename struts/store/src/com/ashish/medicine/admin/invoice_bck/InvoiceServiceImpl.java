package com.ashish.medicine.admin.invoice_bck;

import com.ashish.medicine.exception.AppException;

public class InvoiceServiceImpl implements InvoiceService {

	public void addOrUpdateInvoice(InvoiceBean invoiceBean) throws AppException {
		new InvoiceServiceHelperImpl().addOrUpdateInvoice(invoiceBean);
	}

	public void searchInvoice(InvoiceBean invoiceBean) throws AppException {
		new InvoiceServiceHelperImpl().searchInvoice(invoiceBean);
	}

	public void viewInvoice(InvoiceBean invoiceBean) throws AppException {
		new InvoiceServiceHelperImpl().viewInvoice(invoiceBean);
	}

	public void deleteInvoice(InvoiceBean invoiceBean) throws AppException {
		new InvoiceServiceHelperImpl().deleteInvoice(invoiceBean);
	}

	public void getAllInvoices(InvoiceBean invoiceBean) throws AppException {
		new InvoiceServiceHelperImpl().getAllInvoices(invoiceBean);
	}
}
