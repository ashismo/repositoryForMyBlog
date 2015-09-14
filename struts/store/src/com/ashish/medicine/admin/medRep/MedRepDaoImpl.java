package com.ashish.medicine.admin.medRep;

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
import com.ashish.medicine.admin.medicine.MedicineBean;
import com.ashish.medicine.admin.wholeseller.WholeSellerBean;
import com.ashish.medicine.admin.wholeseller.WholeSellerDaoImpl;
import com.ashish.medicine.entity.Company;
import com.ashish.medicine.entity.MedRep;
import com.ashish.medicine.entity.WholeSeller;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.hinernate.util.HibernateUtil;
import com.ashish.medicine.util.MedicineUtility;

public class MedRepDaoImpl extends MedicineBaseDaoImpl implements MedRepDao {

//	private Session s = HibernateUtil.getSessionFactory().openSession();
	public void addOrUpdateMedRep(MedRepBean medRepBean) throws AppException {
		Transaction tx = null;
		MedRep mr = new MedRep();
		try {
			if(checkDuplicateMedRepByMedRepName(medRepBean)) {
				medRepBean.setMsg("Medical Representative Name Already Exists");
				throw new AppException();
			}
			if(medRepBean.getMedRepId() == 0) {
				medRepBean.setMsg("Medical Representative Created");
			}
			
			BeanUtils.copyProperties(mr, medRepBean);
			if(medRepBean.getWholesellerId() != 0) {
				Query query = s.getNamedQuery("getWholeSellerByWholeSellerId");
				query.setInteger("wholesellerId", medRepBean.getWholesellerId());
				List<WholeSeller> wholeSellerList = query.list();
				
				if(wholeSellerList != null && wholeSellerList.size() > 0) {
					mr.setWholeSeller(wholeSellerList.get(0));
				}
			}
			MedicineUtility mUtil = new MedicineUtility();
			mr.setDateOfAssociation(mUtil.getDateFromString(medRepBean.getDateOfAssociation()));

			if(medRepBean.getWholesellerId() == 0) {
				mr.setDbAddTs(new Timestamp(new Date().getTime()));
				mr.setDbUpdTs(new Timestamp(new Date().getTime()));
				mr.setDbAddUser(medRepBean.getDbAddUser());
			} else {
				MedRep tempMedRep = new MedRep();
				Query query = s.getNamedQuery("getMedRepByMedRepId");
				query.setLong("medRepId", medRepBean.getMedRepId());
				List<MedRep> medRepList = query.list();
				
				if(medRepList != null && medRepList.size() == 1) {
					tempMedRep = medRepList.get(0);
				} else {
					tempMedRep.setDbAddTs(new Timestamp(new Date().getTime()));
					tempMedRep.setDbAddUser(medRepBean.getDbAddUser());
				}
				mr.setDbAddTs(tempMedRep.getDbAddTs());
				mr.setDbAddUser(tempMedRep.getDbAddUser());
				mr.setDbUpdTs(new Timestamp(new Date().getTime()));
				mr.setDbUpdUser(medRepBean.getDbUpdUser());
				s.close();
				getHibernateSession();
			}
			
			tx = s.beginTransaction();
			s.saveOrUpdate(mr);
			
		} catch (Exception e) {
			if(medRepBean.getMsg() != null && "".equals(medRepBean.getMsg().trim())) {
				medRepBean.setMsg("Operation failed");
			}
			e.printStackTrace();
			throw new AppException();
		} finally {
			try {
				if(tx != null)tx.commit();
			} catch (Exception e) {
				if(tx != null)tx.rollback();
				e.printStackTrace();
				medRepBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
		
		if(medRepBean.getMedRepId() == 0) {
			medRepBean.setMsg("Medical Representative Created successfully!!!!!");
			medRepBean.setMedRepId(mr.getMedRepId());
		} else {
			medRepBean.setMsg("Medical Representative Updated successfully!!!!!");
		}
		
	}

	public void searchMedRep(MedRepBean medRepBean) throws AppException {
		try {
			String wholesellerName = "%";
			String medRepName = "%";
			// Search Companies by page
			int startIndex = (medRepBean.getPage() - 1) * medRepBean.getRows();
			int totalRecords = medRepBean.getRows();
			Query query = s.getNamedQuery("searchMedReps");
			if(medRepBean.getMedRepName() != null && !"".equals(medRepBean.getMedRepName().trim())) {
				medRepName = medRepName + medRepBean.getMedRepName().toUpperCase() + "%";
			}
			if(medRepBean.getWholesellerName() != null && !"".equals(medRepBean.getWholesellerName().trim())) {
				wholesellerName = wholesellerName + medRepBean.getWholesellerName().toUpperCase() + "%";
			}
			query.setString("wholesellerName", wholesellerName);
			query.setString("medRepName", medRepName);
			
			query.setFirstResult(startIndex);
			query.setMaxResults(totalRecords);
			List<MedRep> medRepList = query.list();
			List<MedRepBean> mBeanList = new ArrayList<MedRepBean>();
			for(MedRep mr: medRepList) {
				MedRepBean mBean = new MedRepBean();
				BeanUtils.copyProperties(mBean, mr);
				if(mr.getWholeSeller() != null) {
					mBean.setWholesellerName(mr.getWholeSeller().getWholesellerName());
				}
				mBeanList.add(mBean);
			}
			medRepBean.setSearchMedRepList(mBeanList);
			
			// count medical reps
			query = s.getNamedQuery("countMedReps");
			query.setString("wholesellerName", wholesellerName);
			query.setString("medRepName", medRepName);
			List<Integer> countCompanies = query.list();
			if(countCompanies != null && countCompanies.size() > 0) {
				medRepBean.setTotal(Integer.parseInt(countCompanies.get(0) + ""));
			} else {
				medRepBean.setTotal(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} 
	}

	public void viewMedRep(MedRepBean medRepBean) throws AppException {
		Query query = s.getNamedQuery("viewMedRepByMedRepId");
		query.setLong("medRepId", medRepBean.getMedRepId());
		List<MedRepBean> medRepList = query.setResultTransformer(Transformers.aliasToBean(MedRepBean.class)).list();
		if(medRepList != null && medRepList.size() > 0) {
			try {
				MedicineUtility mUtil = new MedicineUtility();
				BeanUtils.copyProperties(medRepBean, medRepList.get(0));
				medRepBean.setDateOfAssociation(mUtil.getDateStrFromDate(medRepBean.getDateOfAssociationActual()));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				medRepBean.setErrMsg("Unable to fetch MR details");
				throw new AppException();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				medRepBean.setErrMsg("Unable to fetch MR details");
				throw new AppException();
			}
		}
	}
	public boolean checkDuplicateMedRepByMedRepName(MedRepBean medRepBean) throws AppException {
		if(medRepBean.getMedRepName() != null) {
			Query query = s.getNamedQuery("getMedRepByMedRepName");
			query.setString("medRepName", medRepBean.getMedRepName().toUpperCase());
			List<MedRep> medRepList = query.list();
			
			if(medRepList != null && medRepList.size() > 0) {
				if(medRepBean.getMedRepId() == medRepList.get(0).getMedRepId()) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	public void deleteMedRep(MedRepBean medRepBean) throws AppException {
		if(medRepBean.getMedRepId() == 0) {
			medRepBean.setMsg("Operation Failed...<br>Medical Representative Does not Exists");
			throw new AppException();
		}
		Query query = s.getNamedQuery("getMedRepByMedRepId");
		query.setLong("medRepId", medRepBean.getMedRepId());
		List<MedRep> medRepList = query.list();
		
		if(medRepList != null && medRepList.size() == 1) {
			Transaction tx = null;
			try {
				tx = s.beginTransaction();
				s.delete(medRepList.get(0));
				medRepBean.setMsg("Medical Representative Deleted Successfully");
			} catch (Exception e) {
				if(medRepBean.getMsg() != null && "".equals(medRepBean.getMsg().trim())) {
					medRepBean.setMsg("Operation failed");
				}
				e.printStackTrace();
				throw new AppException();
			} finally {
				try {
					if(tx != null)tx.commit();
				} catch (Exception e) {
					if(tx != null)tx.rollback();
					e.printStackTrace();
					medRepBean.setMsg("Operation failed. Unable to commit changes into database.");
				}
				if(s != null) s.close();
			}
		}
	}

	public void getMedRepByWholeSellerId(MedRepBean medRepBean)
			throws AppException {
		try {
			Query query = s.getNamedQuery("getMedRepByWholeSellerId");
			query.setInteger("wholesellerId", medRepBean.getWholesellerId());
			List<MedRepBean> medRepList = query.setResultTransformer(Transformers.aliasToBean(MedRepBean.class)).list();
			medRepBean.setMedRepList(medRepList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} 
		
	}
}
