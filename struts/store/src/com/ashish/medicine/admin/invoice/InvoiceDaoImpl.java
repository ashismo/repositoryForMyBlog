package com.ashish.medicine.admin.invoice;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.ashish.medicine.admin.base.MedicineBaseDaoImpl;
import com.ashish.medicine.entity.Customer;
import com.ashish.medicine.entity.Doctor;
import com.ashish.medicine.entity.Invoice;
import com.ashish.medicine.entity.InvoiceDetail;
import com.ashish.medicine.entity.MedicineDetail;
import com.ashish.medicine.entity.TransactionDetails;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.hinernate.util.HibernateUtil;
import com.ashish.medicine.util.MedicineUtility;

public class InvoiceDaoImpl extends MedicineBaseDaoImpl implements InvoiceDao {

//	private Session s = null;
//	public InvoiceDaoImpl() {
//		try {
//			s = HibernateUtil.getSessionFactory().openSession();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	public void addOrUpdateInvoice(InvoiceBean invoiceBean) throws AppException {
		Transaction tx = null;
		Invoice invoice = new Invoice();
		try {
			tx = s.beginTransaction();
			if(invoiceBean.getInvoiceId() > 0) {
				Query query = s.getNamedQuery("getInvoiceByInvoiceId");
				query.setLong("invoiceId", invoiceBean.getInvoiceId());
				List<Invoice> invoiceList = query.list();
				if(invoiceList != null && invoiceList.size() > 0) {
					invoice = invoiceList.get(0);
					invoice.setDbUpdTs(new Timestamp(new Date().getTime()));
					invoice.setDbUpdUser(invoiceBean.getDbUpdUser());
				}
			} else {
				invoice.setBillNo(0);
				invoice.setPrintIndicator("N");
				invoice.setDbAddTs(new Timestamp(new Date().getTime()));
				invoice.setDbUpdTs(new Timestamp(new Date().getTime()));
				invoice.setDbAddUser(invoiceBean.getDbAddUser());
			}
			
			if(invoiceBean.getInvoiceFormPart1() == 1) {
				// Create Invoice
				Customer cust = null;
				Doctor doctor = null;
				if(invoiceBean.getCustomerId() == -1) {
					// Insert customer into database
					Query query = s.getNamedQuery("getCustomerByCustomerName");
					query.setString("customerName", invoiceBean.getCustomerName().toUpperCase());
					List<Customer> custList = query.list();
					
					if(custList != null && custList.size() > 0) {
						cust = custList.get(0);
						cust.setDbUpdTs(new Timestamp(new Date().getTime()));
						cust.setDbUpdUser(invoiceBean.getDbUpdUser());
					} else {
						cust = new Customer();
						cust.setDbUpdTs(new Timestamp(new Date().getTime()));
						cust.setDbAddTs(new Timestamp(new Date().getTime()));
						cust.setDbAddUser(invoiceBean.getDbAddUser());
					}
					
					cust.setCustomerName(invoiceBean.getCustomerName());
					cust.setCustomerAddr1(invoiceBean.getCustomerAddr1());
					cust.setMob1(invoiceBean.getCustomerMob1());
					s.save(cust);
				} else {
					// fetch customer from database
					Query query = s.getNamedQuery("getCustomerDetailsByCustomerId");
					query.setLong("customerId", invoiceBean.getCustomerId());
					List<Customer> customerList = query.list();
					if(customerList != null && customerList.size() > 0) {
						cust = customerList.get(0);
					}
				}
				
				if(invoiceBean.getDoctorId() == -1) {
					// Insert doctor into database
					Query query = s.getNamedQuery("getDoctorByDoctorName");
					query.setString("doctorName", invoiceBean.getDoctorName().toUpperCase());
					List<Doctor> doctorList = query.list();
					
					if(doctorList != null && doctorList.size() > 0) {
						doctor = doctorList.get(0);
						doctor.setDbUpdTs(new Timestamp(new Date().getTime()));
						doctor.setDbUpdUser(invoiceBean.getDbUpdUser());
					} else {
						doctor = new Doctor();
						doctor.setDbUpdTs(new Timestamp(new Date().getTime()));
						doctor.setDbAddTs(new Timestamp(new Date().getTime()));
						doctor.setDbAddUser(invoiceBean.getDbAddUser());
					}
					doctor.setDoctorName(invoiceBean.getDoctorName());
					doctor.setDoctorAddr1(invoiceBean.getDoctorAddr1());
					doctor.setMob1(invoiceBean.getDoctorMob1());
					
					s.save(doctor);
				} else {
					// fetch doctor from database
					Query query = s.getNamedQuery("getDoctorDetailsByDoctorId");
					query.setLong("doctorId", invoiceBean.getDoctorId());
					List<Doctor> doctorList = query.list();
					if(doctorList != null && doctorList.size() > 0) {
						doctor = doctorList.get(0);
					}
				}
				invoice.setDoctor(doctor);
				invoice.setCustomer(cust);
				MedicineUtility mUtil = new MedicineUtility();
				// Workaround - sometimes the incoming purchase date is coming as empty string from the request
				if(invoiceBean.getPurchaseDate() != null && !"".equals(invoiceBean.getPurchaseDate().trim())) {
					invoice.setPurchaseDate(mUtil.getDateFromString(invoiceBean.getPurchaseDate()));
				}
				invoice.setPaymentMode(invoiceBean.getPaymentMode());
				invoice.setVat(0);
				invoice.setTotalAmt(0);
				invoice.setTotalPaid(0);
				s.saveOrUpdate(invoice);
			} else if(invoiceBean.getInvoiceFormPart2() == 1) {
				if(invoice != null) {
					invoice.setPaymentMode(invoiceBean.getPaymentMode());
					invoice.setVat(invoiceBean.getVat());
					invoice.setDiscount(invoiceBean.getDiscount());
					invoice.setCardNumber(invoiceBean.getCardNumber());
					invoice.setTotalPaid(invoiceBean.getTotalPaid());
					invoice.setTotalAmt(invoiceBean.getTotalAmt());
					s.saveOrUpdate(invoice);
				}
			}
			
		} catch (Exception e) {
			if(invoiceBean.getMsg() != null && "".equals(invoiceBean.getMsg().trim())) {
				invoiceBean.setMsg("Operation failed");
			}
			if(tx != null)tx.rollback();
			if(s != null) s.close();
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
			invoiceBean.setInvoiceId(invoice.getInvoiceId());
		} else {
			invoiceBean.setMsg("Invoice Updated successfully!!!!!");
		}
		
	}
	
	public void addOrUpdateInvoiceDetails(InvoiceBean invoiceBean) throws AppException {
		Transaction tx = null;
		InvoiceDetail invoiceDetails = null;
		try {
			tx = s.beginTransaction();
			if(invoiceBean.getInvoiceId() != 0) {
				// Fetch Invoice
				Invoice invoice = null;
				Query query = s.getNamedQuery("getInvoiceByInvoiceId");
				query.setInteger("invoiceId", invoiceBean.getInvoiceId());
				List<Invoice> invoiceList = query.list();
				if(invoiceList != null && invoiceList.size() > 0) {
					invoice = invoiceList.get(0);
					invoice.setDbUpdTs(new Timestamp(new Date().getTime()));
					invoice.setDbUpdUser(invoiceBean.getDbUpdUser());
				}
				
				// Fetch medicine details
				MedicineDetail medicineDetail = null;
				query = s.getNamedQuery("getMedicineDetailsByMedicineDetailsId");
				query.setInteger("medicineDetailsId", invoiceBean.getMedicineDetailsId());
				List<MedicineDetail> medicineDetailList = query.list();
				if(medicineDetailList != null && medicineDetailList.size() > 0) {
					medicineDetail = medicineDetailList.get(0);
				}
				
				if(invoiceBean.getInvoiceDetailsId() == 0) {
					// Check same medicine details exists or not
					query = s.getNamedQuery("getInvoiceDetailsByInvoiceId");
					query.setLong("invoiceId", invoiceBean.getInvoiceId());
					List<InvoiceBean> invoiceDetailsList = query.setResultTransformer(Transformers.aliasToBean(InvoiceBean.class)).list();
					if(invoiceDetailsList != null && invoiceDetailsList.size() > 0) {
						for(InvoiceBean iBean : invoiceDetailsList) {
							if(iBean.getMedicineDetailsId() == invoiceBean.getMedicineDetailsId()) {
								invoiceBean.setErrMsg("Same medicine already exists. Please update the existing record.");
								return;
							}
						}
					}
					// Insert Invoice details into database
					invoiceDetails = new InvoiceDetail();
					invoiceDetails.setDbAddTs(new Timestamp(new Date().getTime()));
					invoiceDetails.setDbUpdTs(new Timestamp(new Date().getTime()));
					invoiceDetails.setDbAddUser(invoiceBean.getDbAddUser());
				} else {
					// update invoice details from database
					query = s.getNamedQuery("getInvoiceDetailsByInvoiceDetailsId");
					query.setLong("invoiceDetailsId", invoiceBean.getInvoiceDetailsId());
					List<InvoiceDetail> invoiceDetailsList = query.list();
					if(invoiceDetailsList != null && invoiceDetailsList.size() > 0) {
						invoiceDetails = invoiceDetailsList.get(0);
					} else {
						invoiceBean.setErrMsg("Medicine can not be added into invoice.");
						return;
					}
					invoiceDetails.setDbUpdTs(new Timestamp(new Date().getTime()));
					invoiceDetails.setDbUpdUser(invoiceBean.getDbUpdUser());
				}
				if(invoice != null && medicineDetail != null) {
					invoiceDetails.setInvoice(invoice);
					invoiceDetails.setMedicineDetail(medicineDetail);
					invoiceDetails.setSchedule(invoiceBean.getSchedule());
					invoiceDetails.setSoldoutStock(invoiceBean.getSoldoutStock());
//					invoiceDetails.setDbAddTs(new Timestamp(new Date().getTime()));
//					invoiceDetails.setDbUpdTs(new Timestamp(new Date().getTime()));
				} else {
					invoiceBean.setErrMsg("Medicine can not be added into invoice.");
					return;
				}
				s.saveOrUpdate(invoiceDetails);
				invoiceBean.setInvoiceDetailsId(invoiceDetails.getInvoiceDetailsId());
				invoiceBean.setInvoiceId(invoiceDetails.getInvoice().getInvoiceId());
				// update soldout stock into medicine details table
				if(medicineDetail != null) {
					medicineDetail.setSoldoutStock(getTotalSoldoutMedicineByMedicineDetailsId(invoiceBean.getMedicineDetailsId()));
					medicineDetail.setDbUpdTs(new Timestamp(new Date().getTime()));
					medicineDetail.setDbAddUser(invoiceBean.getDbAddUser());
					s.update(medicineDetail);
				}
				// update current price into invoice table.
				if(invoice != null) {
					invoice.setTotalAmt(invoice.getTotalAmt() + (invoiceBean.getSoldoutStock() * invoiceBean.getSoldoutUnitPrice()));
					s.update(invoice);
				}
			}
			
		} catch (Exception e) {
			if(invoiceBean.getMsg() != null && "".equals(invoiceBean.getMsg().trim())) {
				invoiceBean.setMsg("Operation failed");
			}
			if(tx != null)tx.rollback();
			if(s != null) s.close();
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

	public void searchInvoice(InvoiceBean invoiceBean) throws AppException {
		try {
			String customerName = "%";
			String customerMob1 = "%";
			String doctorName = "%";
			Date startDate = null;
			Date endDate = null;
			MedicineUtility mUtil = new MedicineUtility();
			// Search Invoices by page
			int startIndex = (invoiceBean.getPage() - 1) * invoiceBean.getRows();
			int totalRecords = invoiceBean.getRows();
			Query query = s.getNamedQuery("searchInvoices");
			if(invoiceBean.getDoctorName() != null && !"".equals(invoiceBean.getDoctorName().trim())) {
				doctorName = doctorName + invoiceBean.getDoctorName().toUpperCase() + "%";
			}
			if(invoiceBean.getCustomerName() != null && !"".equals(invoiceBean.getCustomerName().trim())) {
				customerName = customerName + invoiceBean.getCustomerName().toUpperCase() + "%";
			}
			if(invoiceBean.getCustomerMob1() != null && !"".equals(invoiceBean.getCustomerMob1().trim())) {
				customerMob1 = customerMob1 + invoiceBean.getCustomerMob1().toUpperCase() + "%";
			}
			if(invoiceBean.getStartDate() != null && !invoiceBean.getStartDate().trim().equals("")) {
				startDate = mUtil.getDateFromString(invoiceBean.getStartDate());
			} else {
				startDate = mUtil.getDateSubtractedByDays(10000);
			}
			if(invoiceBean.getEndDate() != null && !invoiceBean.getEndDate().trim().equals("")) {
				endDate = mUtil.getDateFromString(invoiceBean.getEndDate());
			} else {
				endDate = mUtil.getDateSubtractedByDays(-10000);
			}
			
			query.setString("doctorName", doctorName);
			query.setString("customerName", customerName);
			query.setString("customerMob1", customerMob1);
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
			List<InvoiceBean> invoiceCountList = query.setResultTransformer(Transformers.aliasToBean(InvoiceBean.class)).list();
			
			query.setFirstResult(startIndex);
			query.setMaxResults(totalRecords);
			List<InvoiceBean> invoiceBeanList = query.setResultTransformer(Transformers.aliasToBean(InvoiceBean.class)).list();
			for(InvoiceBean iBean: invoiceBeanList) {
				iBean.setPurchaseDate(mUtil.getDateStrFromDate(iBean.getPurchaseDateActual()));
			}
			invoiceBean.setSearchInvoiceList(invoiceBeanList);
			
			// count invoices
			if(invoiceCountList != null) {
				invoiceBean.setTotal(invoiceCountList.size());
			} else {
				invoiceBean.setTotal(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} 
	}

	private int getTotalSoldoutMedicineByMedicineDetailsId(int medicineDetailsId) throws AppException {
		Query query = s.getNamedQuery("getSoldoutStockByMedicineDetailsId");
		query.setInteger("medicineDetailsId", medicineDetailsId);
		List<Long> soldoutStockList = query.list();
		int soldoutStock = 0;

		if(soldoutStockList != null && soldoutStockList.size() > 0) {
			soldoutStock = Integer.parseInt(soldoutStockList.get(0) + "");
		}
		return soldoutStock;
	}
	public void viewInvoice(InvoiceBean invoiceBean) throws AppException {
		
		Query query = s.getNamedQuery("getInvoiceDetailsByInvoiceId");
		query.setLong("invoiceId", invoiceBean.getInvoiceId());
		List<InvoiceBean> invoiceList = query.setResultTransformer(Transformers.aliasToBean(InvoiceBean.class)).list();
		// Convert date format
		MedicineUtility mUtil = new MedicineUtility();
		for(InvoiceBean invoice: invoiceList) {
			invoice.setMfgDate(mUtil.getDateStrFromDate(invoice.getMfgDateActual()));
			invoice.setExpDate(mUtil.getDateStrFromDate(invoice.getExpDateActual()));
			invoice.setMfgDate(mUtil.getMfgAndExpDateFormat(invoice.getMfgDate()));
			invoice.setExpDate(mUtil.getMfgAndExpDateFormat(invoice.getExpDate()));
			invoice.setBillDate(mUtil.getDateStrFromDate(invoice.getBillDateActual()));
			invoice.setPurchaseDate(mUtil.getDateStrFromDate(invoice.getPurchaseDateActual()));
			int soldoutStock = getTotalSoldoutMedicineByMedicineDetailsId(invoice.getMedicineDetailsId());
			int totalSoldoutStock = (soldoutStock - invoice.getSoldoutStock());
			invoice.setAvailableStock(invoice.getStock() - totalSoldoutStock);
			invoice.setTotalPrice(Double.parseDouble(mUtil.getFormattedAmount(invoice.getTotalPrice())));
			invoiceBean.setGrandTotalPrice(invoiceBean.getGrandTotalPrice() + invoice.getTotalPrice());
		}
		invoiceBean.setSearchInvoiceList(invoiceList);
		
	}

	public void deleteInvoice(InvoiceBean invoiceBean) throws AppException {
		if(invoiceBean.getInvoiceId() == 0) {
			invoiceBean.setMsg("Operation Failed...Invoice Does not Exists");
			throw new AppException();
		}
		Query query = s.getNamedQuery("getInvoiceByInvoiceId");
		query.setLong("invoiceId", invoiceBean.getInvoiceId());
		List<Invoice> invoiceList = query.list();
		
		if(invoiceList != null && invoiceList.size() == 1) {
			Transaction tx = null;
			try {
				tx = s.beginTransaction();
				// Important Business logic: Delete by invoice details id then delete by invoice id
				query = s.getNamedQuery("getAllInvoiceDetailsByInvoiceId");
				query.setLong("invoiceId", invoiceBean.getInvoiceId());
				List<InvoiceDetail> invoiceDetailsList = query.list();
				
				if(invoiceDetailsList != null && invoiceDetailsList.size() > 0) {
					for(InvoiceDetail invoiceDetails: invoiceDetailsList) {
						// Important Business logic: update medicine stock in medicine_details table as invoice is getting deleted
						if(invoiceDetails.getMedicineDetail() != null) {
							int medicineDetailsId = invoiceDetails.getMedicineDetail().getMedicineDetailsId();
							query = s.getNamedQuery("getMedicineDetailsByMedicineDetailsId");
							query.setLong("medicineDetailsId", medicineDetailsId);
							List<MedicineDetail> medicineDetailsList = query.list();
							
							if(medicineDetailsList != null && medicineDetailsList.size() == 1) {
								MedicineDetail medicineDetails = medicineDetailsList.get(0);
								int soldoutStock = medicineDetails.getSoldoutStock() - invoiceDetails.getSoldoutStock();
								if(soldoutStock >= 0) {
									medicineDetails.setSoldoutStock(soldoutStock);
									s.update(medicineDetails);
								}
							}
							
						}
						s.delete(invoiceDetails);
					}
				}
				
				// Important Business logic: delete transaction details by invoice id
				query = s.getNamedQuery("getTransactionDetailsEntityByInvoiceId");
				query.setLong("invoiceId", invoiceBean.getInvoiceId());
				List<TransactionDetails> trxDetailsList = query.list();
				
				if(trxDetailsList != null && trxDetailsList.size() > 0) {
					for(TransactionDetails trxDetails: trxDetailsList) {
						s.delete(trxDetails);
					}
				}
				
				// Important Business logic: Finally delete the invoice
				s.delete(invoiceList.get(0));
				invoiceBean.setMsg("Invoice Deleted Successfully");
			} catch (Exception e) {
				if(invoiceBean.getMsg() != null && "".equals(invoiceBean.getMsg().trim())) {
					invoiceBean.setMsg("Operation failed");
				}
				if(tx != null)tx.rollback();
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

	public void deleteMedicineIntoInvoice(InvoiceBean invoiceBean) throws AppException {
		Query query = s.getNamedQuery("getInvoiceDetailsByInvoiceDetailsId");
		query.setLong("invoiceDetailsId", invoiceBean.getInvoiceDetailsId());
		query.setMaxResults(1);
		List<InvoiceDetail> invoiceDetailsList = query.list();
		
		if(invoiceDetailsList != null && invoiceDetailsList.size() == 1) {
			Transaction tx = null;
			try {
				tx = s.beginTransaction();
				InvoiceDetail invoiceDetails = invoiceDetailsList.get(0);
				
				// update stock into medicine details table
				query = s.getNamedQuery("getInvoiceDetailsByInvoiceDetailsId");
				query.setLong("invoiceDetailsId", invoiceBean.getInvoiceDetailsId());
				List<InvoiceDetail> iDetailsList = query.list();
				
				// update soldout stock into medicine details table
				if(iDetailsList != null && iDetailsList.size() > 0) {
					MedicineDetail medicineDetail = iDetailsList.get(0).getMedicineDetail();
					if(medicineDetail != null) {
						medicineDetail.setSoldoutStock(medicineDetail.getSoldoutStock() - invoiceBean.getSoldoutStock());
						medicineDetail.setDbUpdTs(new Timestamp(new Date().getTime()));
						medicineDetail.setDbAddUser(invoiceBean.getDbAddUser());
						s.update(medicineDetail);
					}
				}
				
				s.delete(invoiceDetails);
				invoiceBean.setMsg("Medicine Deleted Successfully");
			} catch (Exception e) {
				if(invoiceBean.getMsg() != null && "".equals(invoiceBean.getMsg().trim())) {
					invoiceBean.setMsg("Operation failed");
				}
				if(tx != null)tx.rollback();
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
	
	public void revertPrevStockBack(InvoiceBean invoiceBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getInvoiceByInvoiceId");
			query.setLong("invoiceId", invoiceBean.getInvoiceId());
			List<Invoice> invoiceList = query.list();
			
			if(invoiceList != null && invoiceList.size() == 1) {
//				return invoiceList.get(0);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
//		return null;
	}
	
	public void printInvoice(InvoiceBean invoiceBean) throws AppException {
			Query query = s.getNamedQuery("getInvoiceByInvoiceId");
			query.setLong("invoiceId", invoiceBean.getInvoiceId());
			List<Invoice> invoiceList = query.list();
			
			if(invoiceList != null && invoiceList.size() == 1) {
				Invoice invoice = invoiceList.get(0);
				if(invoice != null && !"Y".equals(invoice.getPrintIndicator())) {
					Transaction tx = s.beginTransaction();
					try {
						invoice.setPrintIndicator("Y");
						
						query = s.getNamedQuery("getMaxBillNoByInvoiceId");
						List<InvoiceBean> billList = query.setResultTransformer(Transformers.aliasToBean(InvoiceBean.class)).list();
						
						if(billList != null && billList.size() > 0) {
							int maxBillNo = billList.get(0).getBillNo();
							if(maxBillNo == 0) {
								maxBillNo = 1;
							} else {
								maxBillNo = maxBillNo + 1;
							}
							invoice.setBillNo(maxBillNo);
						}
						invoice.setDbUpdTs(new Timestamp(new Date().getTime()));
						invoice.setDbAddUser(invoiceBean.getDbAddUser());
						s.update(invoice);
					}  catch (Exception e) {
						if(invoiceBean.getMsg() != null && "".equals(invoiceBean.getMsg().trim())) {
							invoiceBean.setMsg("Operation failed");
						}
						if(tx != null)tx.rollback();
						if(s != null) s.close();
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
	}
}
