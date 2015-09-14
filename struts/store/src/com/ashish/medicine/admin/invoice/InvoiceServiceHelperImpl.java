package com.ashish.medicine.admin.invoice;

import com.ashish.medicine.exception.AppException;

public class InvoiceServiceHelperImpl implements InvoiceServiceHelper {

	public void addOrUpdateInvoice(InvoiceBean invoiceBean) throws AppException {
		new InvoiceDaoImpl().addOrUpdateInvoice(invoiceBean);
	}

	public void addOrUpdateInvoiceDetails(InvoiceBean invoiceBean) throws AppException {
		new InvoiceDaoImpl().addOrUpdateInvoiceDetails(invoiceBean);
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
	
	public void deleteMedicineIntoInvoice(InvoiceBean invoiceBean) throws AppException {
		new InvoiceDaoImpl().deleteMedicineIntoInvoice(invoiceBean);		
	}
	
	public void getAllInvoices(InvoiceBean invoiceBean) throws AppException {
		new InvoiceDaoImpl().getAllInvoices(invoiceBean);		
	}
	
	public void revertPrevStockBack(InvoiceBean invoiceBean) throws AppException {
		new InvoiceDaoImpl().revertPrevStockBack(invoiceBean);		
	}
	
	public void printInvoice(InvoiceBean invoiceBean) throws AppException {
		new InvoiceDaoImpl().printInvoice(invoiceBean);
	}
}
