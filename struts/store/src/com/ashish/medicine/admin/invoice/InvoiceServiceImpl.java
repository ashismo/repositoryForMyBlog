package com.ashish.medicine.admin.invoice;

import java.io.IOException;
import java.net.MalformedURLException;

import com.ashish.medicine.exception.AppException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class InvoiceServiceImpl implements InvoiceService {

	public void addOrUpdateInvoice(InvoiceBean invoiceBean) throws AppException {
		new InvoiceServiceHelperImpl().addOrUpdateInvoice(invoiceBean);
	}
	
	public void addOrUpdateInvoiceDetails(InvoiceBean invoiceBean) throws AppException {
		new InvoiceServiceHelperImpl().addOrUpdateInvoiceDetails(invoiceBean);
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
	
	public void deleteMedicineIntoInvoice(InvoiceBean invoiceBean) throws AppException {
		new InvoiceServiceHelperImpl().deleteMedicineIntoInvoice(invoiceBean);
	}
	
	public void getAllInvoices(InvoiceBean invoiceBean) throws AppException {
		new InvoiceServiceHelperImpl().getAllInvoices(invoiceBean);
	}
	
	public void revertPrevStockBack(InvoiceBean invoiceBean) throws AppException {
		new InvoiceServiceHelperImpl().revertPrevStockBack(invoiceBean);
	}
	
	public void printInvoice(InvoiceBean invoiceBean) throws AppException {
		new InvoiceServiceHelperImpl().printInvoice(invoiceBean);
	}
	
}
