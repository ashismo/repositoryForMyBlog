package com.ashish.medicine.admin.medicine;

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

import com.ashish.medicine.admin.base.MedicineBaseDaoImpl;
import com.ashish.medicine.admin.company.CompanyBean;
import com.ashish.medicine.admin.company.CompanyDaoImpl;
import com.ashish.medicine.admin.invoice.InvoiceBean;
import com.ashish.medicine.entity.Company;
import com.ashish.medicine.entity.MedRep;
import com.ashish.medicine.entity.Medicine;
import com.ashish.medicine.entity.MedicineDetail;
import com.ashish.medicine.entity.Order;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.hinernate.util.HibernateUtil;
import com.ashish.medicine.util.MedicineConstants;
import com.ashish.medicine.util.MedicineUtility;
import com.ashish.medicine.util.UtilServiceImpl;

public class MedicineDaoImpl extends MedicineBaseDaoImpl implements MedicineDao {

//	private Session s = HibernateUtil.getSessionFactory().openSession();
	public void addOrUpdateMedicine(MedicineBean medicineBean) throws AppException {
		Transaction tx = null;
		
		try {
			if(isMedicineExistsByNameAndCompany(medicineBean)) {
				medicineBean.setErrMsg("medicine exists under another manufacturer");
				return;
			}
			tx = s.beginTransaction();
			CompanyBean cBean = new CompanyBean();
			cBean.setCompanyId(medicineBean.getCompanyId());
			Company company = new CompanyDaoImpl().getCompanyByCompanyId(cBean);
			Medicine medicine = null;
			if(medicineBean.getMedicineName() != null && !medicineBean.getMedicineName().trim().equals("")) {
				medicine = getMedicineByNameExists(medicineBean);
				if(medicine != null && medicine.getMedicineId() > 0) {
					medicineBean.setMedicineId(medicine.getMedicineId());
				} else {
					// Insert medicine into medicine table
					medicine = new Medicine();
					BeanUtils.copyProperties(medicine, medicineBean);
					medicine.setCompany(company);
					medicine.setDbAddTs(new Timestamp(new Date().getTime()));
					medicine.setDbUpdTs(new Timestamp(new Date().getTime()));
					medicine.setDbAddUser(medicineBean.getDbAddUser());
					medicine.setDbUpdUser(medicineBean.getDbUpdUser());
					s.save(medicine);
					medicineBean.setMedicineId(medicine.getMedicineId());
				}
			} else {
				medicine = getMedicineByIdIfExists(medicineBean);
			}
			
			MedicineDetail md =  null;
			if(medicineBean.getMedicineDetailsId() > 0) {
				Query query = s.getNamedQuery("getMedicineDetailsByMedicineDetailsId");
				query.setInteger("medicineDetailsId", medicineBean.getMedicineDetailsId());
				List<MedicineDetail> medicineDetailsList = query.list();
				
				if(medicineDetailsList != null && medicineDetailsList.size() > 0) {
					md = medicineDetailsList.get(0);
					md.setDbUpdTs(new Timestamp(new Date().getTime()));
					md.setDbUpdUser(medicineBean.getDbUpdUser());
				}
			} else {
				md = new MedicineDetail();
				md.setDbAddTs(new Timestamp(new Date().getTime()));
				md.setDbUpdTs(new Timestamp(new Date().getTime()));
				md.setDbAddUser(medicineBean.getDbAddUser());
			}
			MedicineUtility mUtil = new MedicineUtility();
			if(medicineBean.getBatchName() == null || medicineBean.getBatchName().trim().equals("")) {
				md.setBatchName(medicineBean.getBatchId());
			} else {
				md.setBatchName(medicineBean.getBatchName());
			}
			md.setMfgDate(mUtil.getDateFromString(medicineBean.getMfgDate()));
			md.setExpDate(mUtil.getDateFromString(medicineBean.getExpDate()));
			md.setPurchaseDate(mUtil.getDateFromString(medicineBean.getPurchaseDate()));
			md.setStock(medicineBean.getStock());
			md.setMedicineDesc(medicineBean.getMedicineDesc());
			md.setUnitPrice(medicineBean.getUnitPrice());
			md.setSoldoutUnitPrice(medicineBean.getSoldoutUnitPrice());
			md.setMedicine(medicine);
			md.setMedDose(medicineBean.getMedDose());
			md.setMedType(medicineBean.getMedType());
			md.setMedWeight(medicineBean.getMedWeight());
			// update medical representative details
			if(medicineBean.getMedRepId() != 0) {
				Query query = s.getNamedQuery("getMedRepByMedRepId");
				query.setLong("medRepId", medicineBean.getMedRepId());
				List<MedRep> medRepList = query.list();
				if(medRepList != null && medRepList.size() > 0) {
					md.setMedRep(medRepList.get(0));
				}
			}
			
			s.saveOrUpdate(md);
			
			// Update medicine executed or not
			String medicineDetailsKey = md.getMedicine().getMedicineId() + "~" + md.getMedDose() + "~" + 
								md.getMedWeight() + "~" + md.getMedType();
			Query query = s.getNamedQuery("getOrderByMedicineDetails");
			query.setString("medicineDetails", medicineDetailsKey);
//			query.setString("orderStatus", null);
			List<Order> orderList = query.list();
			if(orderList != null && orderList.size() > 0) {
				Order o = orderList.get(0);
				o.setOrderExecutionDate(md.getPurchaseDate());
				o.setOrderStatus(MedicineConstants.ORDER_EXECUTION);
				s.update(o);
			}
			
//			medicineBean.setMedicineDetailsId(md.getMedicineDetailsId());
			
		} catch (Exception e) {
			if(medicineBean.getMsg() != null && "".equals(medicineBean.getMsg().trim())) {
				medicineBean.setMsg("Operation failed");
			}
			tx.rollback();
			e.printStackTrace();
			throw new AppException();
		} finally {
			try {
				if(tx != null)tx.commit();
			} catch (Exception e) {
				if(tx != null)tx.rollback();
				e.printStackTrace();
				medicineBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
		
		if(medicineBean.getMedicineDetailsId() == 0) {
			medicineBean.setMsg("Medicine Created successfully!!!!!");
		} else {
			medicineBean.setMsg("Medicine Updated successfully!!!!!");
		}
		
	}

	
	public void searchMedicineDetails(MedicineBean medicineBean) throws AppException {
		try {
			String batchName = "%";
			String companyName = "%";
			String medicineName = "%";
			String status = "0";
			String medType = "%";
			Date startDate = null;
			Date endDate = null;
			Date expiringAfter = null;
			
			// Search Companies by page
			int startIndex = (medicineBean.getPage() - 1) * medicineBean.getRows();
			int totalRecords = medicineBean.getRows();
			Query query = s.getNamedQuery("searchMedicines");
			if(medicineBean.getCompanyName() != null && !"".equals(medicineBean.getCompanyName().trim())) {
				companyName = companyName + medicineBean.getCompanyName().trim().toUpperCase() + "%";
			}
			if(medicineBean.getBatchName() != null && !"".equals(medicineBean.getBatchName().trim())) {
				batchName = medicineBean.getBatchName().trim().toUpperCase();
			}
			if(medicineBean.getMedicineName() != null && !"".equals(medicineBean.getMedicineName().trim())) {
				medicineName = medicineName + medicineBean.getMedicineName().trim().toUpperCase() + "%";
			}
			if(medicineBean.getStatus() != null && !"".equals(medicineBean.getStatus().trim())) {
				status = medicineBean.getStatus().trim();
			}
			if(medicineBean.getMedType() != null && !"".equals(medicineBean.getMedType().trim())) {
				medType = medicineBean.getMedType().trim();
				if("ALL".equalsIgnoreCase(medType)) {
					medType = "%";
				}
			}
			
			Date expDate1 = new Date();
			Date expDate2 = new Date();
			MedicineUtility mUtil = new MedicineUtility();
			
			// status == 0 means Not Expired radio button is selected from GUI
			// status == 1 means Expired radio button is selected from GUI
			// status == 2 means Both radio button is selected from GUI
			if("0".equals(status)) {
				// future date
				expDate2 = mUtil.getDateSubtractedByDays(-10000);
			} else if("1".equals(status)) {
				if(medicineBean.getExpiringAfter() != null && !medicineBean.getExpiringAfter().trim().equals("")) {
					expDate1 = mUtil.getDateFromString(medicineBean.getExpiringAfter());
				} else {
					// Past date
					expDate1 = mUtil.getDateSubtractedByDays(10000);
				}
				
				if(medicineBean.getExpiringBefore() != null && !medicineBean.getExpiringBefore().trim().equals("")) {
					expDate2 = mUtil.getDateFromString(medicineBean.getExpiringBefore());
				}
				
				// Set expDate2 as expiring medicines after this date
				
			} else if("2".equals(status)) {
				expDate1 = mUtil.getDateSubtractedByDays(10000);
				expDate2 = mUtil.getDateSubtractedByDays(-10000);
			}
			if(medicineBean.getStartDate() != null && !medicineBean.getStartDate().trim().equals("")) {
				startDate = mUtil.getDateFromString(medicineBean.getStartDate());
			} else {
				startDate = mUtil.getDateSubtractedByDays(10000);
			}
			if(medicineBean.getEndDate() != null && !medicineBean.getEndDate().trim().equals("")) {
				endDate = mUtil.getDateFromString(medicineBean.getEndDate());
			} else {
				endDate = mUtil.getDateSubtractedByDays(-10000);
			}
			
			query.setString("batchName", batchName);
			query.setString("companyName", companyName);
			query.setString("medicineName", medicineName);
			query.setDate("expDate1", expDate1);
			query.setDate("expDate2", expDate2);
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
			query.setString("medType", medType);
			query.setFirstResult(startIndex);
			query.setMaxResults(totalRecords);
			List<MedicineBean> medicineList = query.setResultTransformer(Transformers.aliasToBean(MedicineBean.class)).list();
			medicineBean.setSearchMedicineList(medicineList);
			
			// count medicines
			query = s.getNamedQuery("countMedicines");
			query.setString("batchName", batchName);
			query.setString("companyName", companyName);
			query.setString("medicineName", medicineName);
			query.setDate("expDate1", expDate1);
			query.setDate("expDate2", expDate2);
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
			List<Integer> countMedicines = query.list();
			if(countMedicines != null && countMedicines.size() > 0) {
				medicineBean.setTotal(Integer.parseInt(countMedicines.get(0) + ""));
			} else {
				medicineBean.setTotal(0);
			}
			
			
			// Page-wise total - Calculate total stock and total available stock to display on footer
			List<MedicineBean> footer = medicineBean.getFooter();
			int totalStock = 0;
			int totalAvailableStock = 0;
			for(MedicineBean mBean: medicineList) {
				totalStock = totalStock + mBean.getStock();
				totalAvailableStock = totalAvailableStock + mBean.getAvailableStock();
			}
			MedicineBean footerBean = new MedicineBean();
			footerBean.setStock(totalStock);
			footerBean.setAvailableStock(totalAvailableStock);
			footerBean.setCompanyName("Total Stock");
			footer.add(footerBean);
			
			// Grand total
			query = s.getNamedQuery("countTotalMedicineStock");
			query.setString("batchName", batchName);
			query.setString("companyName", companyName);
			query.setString("medicineName", medicineName);
			query.setDate("expDate1", expDate1);
			query.setDate("expDate2", expDate2);
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
			query.setString("medType", medType);
			List<MedicineBean> countTotalMedicineStock = query.setResultTransformer(Transformers.aliasToBean(MedicineBean.class)).list();
			footer.add(countTotalMedicineStock.get(0));
			if(countTotalMedicineStock.get(0) != null) {
				MedicineBean mBean = countTotalMedicineStock.get(0);
				mBean.setAvailableStock(Integer.parseInt(mBean.getTotalAvailableStock() + ""));
				mBean.setStock(Integer.parseInt(mBean.getTotalStock() + ""));
				mBean.setCompanyName("Grand Total Stock");
			}
			
			medicineBean.setFooter(footer);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} finally {
			s.close();
		}
	}

	public void viewMedicineDetails(MedicineBean medicineBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getMedicineByMedicineDetailsId");
			query.setLong("medicineDetailsId", medicineBean.getMedicineDetailsId());
			List<MedicineBean> medicineList = query.setResultTransformer(Transformers.aliasToBean(MedicineBean.class)).list();
			if(medicineList != null && medicineList.size() > 0) {
				try {
					MedicineUtility mUtil = new MedicineUtility();
					BeanUtils.copyProperties(medicineBean, medicineList.get(0));
					medicineBean.setMfgDate(mUtil.getDateStrFromDate(medicineBean.getMfgDateActual()));
					medicineBean.setExpDate(mUtil.getDateStrFromDate(medicineBean.getExpDateActual()));
					medicineBean.setPurchaseDate(mUtil.getDateStrFromDate(medicineBean.getPurchaseDateActual()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			
			getAllMedicineBatches(medicineBean);
			getMedicinesByCompanyId(medicineBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} finally {
			s.close();
		}
	}
	
	/**
	 * This method checks if medicine already exists
	 * @param medicineBean
	 * @return
	 * @throws AppException
	 */
	public Medicine getMedicineByIdIfExists(MedicineBean medicineBean) throws AppException {
		if((medicineBean.getMedicineName() != null && medicineBean.getMedicineId() > 0) || (medicineBean.getMedicineId() > 0)) {
			Query query = s.getNamedQuery("getMedicineByMedicineId");
			query.setInteger("medicineId", medicineBean.getMedicineId());
			List<Medicine> medicineList = query.list();
			
			if(medicineList != null && medicineList.size() > 0) {
				return medicineList.get(0);
			} else {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * This method checks if medicine already exists
	 * @param medicineBean
	 * @return
	 * @throws AppException
	 */
	public Medicine getMedicineByNameExists(MedicineBean medicineBean) throws AppException {
		if(medicineBean.getMedicineName() != null) {
			Query query = s.getNamedQuery("getMedicineByMedicineName");
			query.setString("medicineName", medicineBean.getMedicineName().toUpperCase());
			List<Medicine> medicineList = query.list();
			
			if(medicineList != null && medicineList.size() > 0) {
				return medicineList.get(0);
			} else {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * This method checks if medicine already exists in different company
	 * @param medicineBean
	 * @return
	 * @throws AppException
	 */
	public boolean isMedicineExistsByNameAndCompany(MedicineBean medicineBean) throws AppException {
		if(medicineBean.getMedicineName() != null) {
			Query query = s.getNamedQuery("getMedicineByMedicineNameAndCompany");
			query.setString("medicineName", medicineBean.getMedicineName().toUpperCase());
			query.setInteger("companyId", medicineBean.getCompanyId());
			List<Medicine> medicineList = query.list();
			
			if(medicineList != null && medicineList.size() > 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public void deleteMedicineDetails(MedicineBean medicineBean) throws AppException {
		if(medicineBean.getMedicineDetailsId() == 0) {
			medicineBean.setMsg("Medicine Details Does not Exists");
			throw new AppException();
		}
		Query query = s.getNamedQuery("getMedicineDetailsByMedicineDetailsId");
		query.setLong("medicineDetailsId", medicineBean.getMedicineDetailsId());
		List<MedicineDetail> medicineDetailsList = query.list();
		
		if(medicineDetailsList != null && medicineDetailsList.size() == 1) {
			Transaction tx = null;
			try {
				tx = s.beginTransaction();
				s.delete(medicineDetailsList.get(0));
				medicineBean.setMsg("Medicine Details Deleted Successfully");
			} catch (Exception e) {
				if(medicineBean.getMsg() != null && "".equals(medicineBean.getMsg().trim())) {
					medicineBean.setMsg("Operation failed");
				}
				e.printStackTrace();
				throw new AppException();
			} finally {
				try {
					if(tx != null)tx.commit();
				} catch (Exception e) {
					if(tx != null)tx.rollback();
					e.printStackTrace();
					medicineBean.setMsg("Operation failed. Unable to commit changes into database.");
				}
				if(s != null) s.close();
			}
		}
	}


	public void getAllMedicineBatches(MedicineBean medicineBean)
			throws AppException {
		try {
			Query query = s.getNamedQuery("getAllMedicineBatches");
			List<MedicineBean> batchList =
				query.setResultTransformer(Transformers.aliasToBean(MedicineBean.class)).list();
			for(MedicineBean mBean: batchList) {
				mBean.setBatchId(mBean.getBatchName());
			}
			MedicineBean batch = new MedicineBean();
			batch.setBatchId("-1");
			batch.setBatchName("Other");
			batchList.add(0, batch);
			medicineBean.setBatchList(batchList);
		} catch(Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch medicine batch");
		}
	}
	
	public void getMedicineDetailsByMedicineId(MedicineBean medicineBean) throws AppException {
		try {
			boolean isRetriveAllBatches = false;
			// Check if medicine name or number is passed
			if(medicineBean.getMedicineId() == 0 && (medicineBean.getMedicineName() != null && !medicineBean.getMedicineName().trim().equals(""))) {
				if(!new UtilServiceImpl().isNumber(medicineBean.getMedicineName())) {
					Query query = s.getNamedQuery("getMedicineByMedicineName");
					query.setString("medicineName", medicineBean.getMedicineName());
					query.setMaxResults(1);
					List<Medicine> medicineList = query.list();
					if(medicineList != null && medicineList.size() > 0) {
						medicineBean.setMedicineId(medicineList.get(0).getMedicineId());
					} else {
						isRetriveAllBatches = true;
					}
				}
			}

			Query query = null;
			if(isRetriveAllBatches) {
				query = s.getNamedQuery("getAllMedicineBatches");
			} else {
				query = s.getNamedQuery("getMedicineDetailsByMedicineId");
				query.setDate("today", new Date());
				query.setInteger("medicineId", medicineBean.getMedicineId());
			}
			List<MedicineBean> batchList =
				query.setResultTransformer(Transformers.aliasToBean(MedicineBean.class)).list();
			for(MedicineBean mBean: batchList) {
				mBean.setBatchId(mBean.getBatchName());
			}
			
			MedicineBean batch = new MedicineBean();
			batch.setBatchId("-1");
			batch.setBatchName("Other");
			batchList.add(0, batch);
			
			medicineBean.setBatchList(batchList);
		} catch(Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch medicine batch");
		}
		}

	public void getAllMedicines(MedicineBean medicineBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getAllMedicines");
			List<MedicineBean> medicineList =
				query.setResultTransformer(Transformers.aliasToBean(MedicineBean.class)).list();
			medicineBean.setMedicineList(medicineList);
		} catch(Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch medicine names");
		}
		
	}
	
	public void getMedicineByBatchAndMedicineId(MedicineBean medicineBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getMedicineDetailsByMedicineBatchAndMedicineId");
			query.setString("batchName", medicineBean.getBatchName());
			query.setInteger("medicineId", medicineBean.getMedicineId());
			List<MedicineBean> medicineDetailsList =
				query.setResultTransformer(Transformers.aliasToBean(MedicineBean.class)).list();
			if(medicineDetailsList != null && medicineDetailsList.size() == 1) {
				MedicineUtility mUtil = new MedicineUtility();
				BeanUtils.copyProperties(medicineBean, medicineDetailsList.get(0));
				medicineBean.setMfgDate(mUtil.getDateStrFromDate(medicineBean.getMfgDateActual()));
				medicineBean.setExpDate(mUtil.getDateStrFromDate(medicineBean.getExpDateActual()));
				medicineBean.setPurchaseDate(mUtil.getDateStrFromDate(medicineBean.getPurchaseDateActual()));
			}
			medicineBean.setSearchMedicineList(medicineDetailsList);
			medicineBean.setTotal(medicineDetailsList.size());
		} catch(Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch medicine names");
		}
		
	}
	
	public void getMedicinesByCompanyId(MedicineBean medicineBean)
			throws AppException {
		try {
			Query query = s.getNamedQuery("getMedicineByCompanyId");
			query.setInteger("companyId", medicineBean.getCompanyId());
			List<MedicineBean> medicineList =
				query.setResultTransformer(Transformers.aliasToBean(MedicineBean.class)).list();
			MedicineBean medicine = new MedicineBean();
			medicine.setMedicineId(-1);
			medicine.setMedicineName("Other");
			medicineList.add(0, medicine);
			medicineBean.setMedicineList(medicineList);
		} catch(Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch medicine batch");
		}
	}
	public void saveMedicineIntoInvoice(MedicineBean medicineBean) throws AppException {
		try {
			if("-1".equals(medicineBean.getBatchId()) || medicineBean.getMedicineId() == -1) {
//				// Revert the previous stock back
//				InvoiceBean invoiceBean = new InvoiceBean();
//				BeanUtils.copyProperties(invoiceBean, medicineBean);
				addOrUpdateMedicine(medicineBean);
			}
		} catch (Exception e) {
			if(medicineBean.getMsg() != null && "".equals(medicineBean.getMsg().trim())) {
				medicineBean.setMsg("Operation failed");
			}
			e.printStackTrace();
			throw new AppException();
		} finally {
		}
	}
}
