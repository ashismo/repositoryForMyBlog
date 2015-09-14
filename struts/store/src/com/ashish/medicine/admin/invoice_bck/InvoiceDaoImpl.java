package com.ashish.medicine.admin.invoice_bck;

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

import com.ashish.medicine.entity.Invoice;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.hinernate.util.HibernateUtil;

public class InvoiceDaoImpl implements InvoiceDao {

	private Session s = null;
	public InvoiceDaoImpl() {
		try {
			s = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addOrUpdateInvoice(InvoiceBean invoiceBean) throws AppException {
		Transaction tx = null;
		Invoice c = new Invoice();
		try {
			if(invoiceBean.getInvoiceId() == 0) {
				if(checkDuplicateInvoiceByInvoiceName(invoiceBean)) {
					invoiceBean.setMsg("Invoice Name Already Exists");
					throw new AppException();
				} else {
					invoiceBean.setMsg("Invoice Created");
				}
			}
			
			BeanUtils.copyProperties(c, invoiceBean);
			c.setDbAddTs(new Timestamp(new Date().getTime()));
			c.setDbUpdTs(new Timestamp(new Date().getTime()));
			tx = s.beginTransaction();
			s.saveOrUpdate(c);
			
		} catch (Exception e) {
			if(invoiceBean.getMsg() != null && "".equals(invoiceBean.getMsg().trim())) {
				invoiceBean.setMsg("Operation failed");
			}
			e.printStackTrace();
			throw new AppException();
		} finally {
			try {
				if(tx != null)tx.commit();
			} catch (Exception e) {
				if(tx != null)tx.rollback();
				e.printStackTrace();
				invoiceBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
		
		if(invoiceBean.getInvoiceId() == 0) {
			invoiceBean.setMsg("Invoice Created successfully!!!!!");
			invoiceBean.setInvoiceId(c.getInvoiceId());
		} else {
			invoiceBean.setMsg("Invoice Updated successfully!!!!!");
		}
		
	}

	public void searchInvoice(InvoiceBean invoiceBean) throws AppException {
		try {
			String pin = "%";
			String state = "%";
			String invoiceName = "%";
			// Search Invoices by page
			int startIndex = (invoiceBean.getPage() - 1) * invoiceBean.getRows();
			int totalRecords = invoiceBean.getRows();
			Query query = s.getNamedQuery("searchInvoices");
//			if(invoiceBean.getInvoiceName() != null && !"".equals(invoiceBean.getInvoiceName().trim())) {
//				invoiceName = invoiceName + invoiceBean.getInvoiceName().toUpperCase() + "%";
//			}
			if(invoiceBean.getState() != null && !"".equals(invoiceBean.getState().trim())) {
				state = state + invoiceBean.getState().toUpperCase() + "%";
			}
			if(invoiceBean.getPin() != null && !"".equals(invoiceBean.getPin().trim())) {
				pin = pin + invoiceBean.getPin().toUpperCase() + "%";
			}
			query.setString("pin", pin);
			query.setString("state", state);
			query.setString("invoiceName", invoiceName);
			
			query.setFirstResult(startIndex);
			query.setMaxResults(totalRecords);
			List<Invoice> invoiceList = query.list();
			List<InvoiceBean> cBeanList = new ArrayList<InvoiceBean>();
			for(Invoice c: invoiceList) {
				InvoiceBean cBean = new InvoiceBean();
				BeanUtils.copyProperties(cBean, c);
				cBeanList.add(cBean);
			}
			invoiceBean.setSearchInvoiceList(cBeanList);
			
			// count companies
			query = s.getNamedQuery("countInvoices");
			query.setString("pin", pin);
			query.setString("state", state);
			query.setString("invoiceName", invoiceName);
			List<Integer> countInvoices = query.list();
			if(countInvoices != null && countInvoices.size() > 0) {
				invoiceBean.setTotal(Integer.parseInt(countInvoices.get(0) + ""));
			} else {
				invoiceBean.setTotal(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} 
	}

	public void viewInvoice(InvoiceBean invoiceBean) throws AppException {
		Query query = s.getNamedQuery("getInvoiceByInvoiceId");
		query.setLong("invoiceId", invoiceBean.getInvoiceId());
		List<Invoice> invoiceList = query.list();
		
		if(invoiceList != null && invoiceList.size() == 1) {
			try {
				BeanUtils.copyProperties(invoiceBean, invoiceList.get(0));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			throw new AppException();
		}
	}
	public boolean checkDuplicateInvoiceByInvoiceName(InvoiceBean invoiceBean) throws AppException {
//		if(invoiceBean.getInvoiceName() != null) {
//			Query query = s.getNamedQuery("getInvoiceByInvoiceName");
//			query.setString("invoiceName", invoiceBean.getInvoiceName().toUpperCase());
//			List<Invoice> invoiceList = query.list();
//			
//			if(invoiceList != null && invoiceList.size() > 0) {
//				return true;
//			} else {
//				return false;
//			}
//		}
		return false;
	}

	public void deleteInvoice(InvoiceBean invoiceBean) throws AppException {
		if(invoiceBean.getInvoiceId() == 0) {
			invoiceBean.setMsg("Operation Failed...<br>Invoice Does not Exists");
			throw new AppException();
		}
		Query query = s.getNamedQuery("getInvoiceByInvoiceId");
		query.setLong("invoiceId", invoiceBean.getInvoiceId());
		List<Invoice> invoiceList = query.list();
		
		if(invoiceList != null && invoiceList.size() == 1) {
			Transaction tx = null;
			try {
				tx = s.beginTransaction();
				s.delete(invoiceList.get(0));
				invoiceBean.setMsg("Invoice Deleted Successfully");
			} catch (Exception e) {
				if(invoiceBean.getMsg() != null && "".equals(invoiceBean.getMsg().trim())) {
					invoiceBean.setMsg("Operation failed");
				}
				e.printStackTrace();
				throw new AppException();
			} finally {
				try {
					if(tx != null)tx.commit();
				} catch (Exception e) {
					if(tx != null)tx.rollback();
					e.printStackTrace();
					invoiceBean.setMsg("Operation failed. Unable to commit changes into database.");
				}
				if(s != null) s.close();
			}
		}
	}

	public void getAllInvoices(InvoiceBean invoiceBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getAllInvoices");
			List<InvoiceBean> invoiceList =
				query.setResultTransformer(Transformers.aliasToBean(InvoiceBean.class)).list();
			
			invoiceBean.setSearchInvoiceList(invoiceList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch all companies");
		} finally {
			s.close();
		}
	}
	
	public Invoice getInvoiceByInvoiceId(InvoiceBean invoiceBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getInvoiceByInvoiceId");
			query.setLong("invoiceId", invoiceBean.getInvoiceId());
			List<Invoice> invoiceList = query.list();
			
			if(invoiceList != null && invoiceList.size() == 1) {
				return invoiceList.get(0);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
		return null;
	}
}
