package com.ashish.medicine.admin.doctor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.ashish.medicine.admin.base.MedicineBaseDaoImpl;
import com.ashish.medicine.admin.customer.CustomerBean;
import com.ashish.medicine.admin.invoice.InvoiceBean;
import com.ashish.medicine.entity.Company;
import com.ashish.medicine.entity.Doctor;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.hinernate.util.HibernateUtil;
import com.ashish.medicine.util.MedicineUtility;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

public class DoctorDaoImpl extends MedicineBaseDaoImpl implements DoctorDao {

//	private Session s = HibernateUtil.getSessionFactory().openSession();
	public void addOrUpdateDoctor(DoctorBean doctorBean) throws AppException {
		Transaction tx = null;
		Doctor d = new Doctor();
		try {
			if(checkDuplicateDoctorByDoctorName(doctorBean)) {
				doctorBean.setMsg("Doctor Name Already Exists");
				throw new AppException();
			}
			if(doctorBean.getDoctorId() == 0) {
				doctorBean.setMsg("Doctor Created");
			}
			
			BeanUtils.copyProperties(d, doctorBean);
			d.setIsActive("I");
			if(doctorBean.getDoctorId() == 0) {
				d.setDbAddTs(new Timestamp(new Date().getTime()));
				d.setDbUpdTs(new Timestamp(new Date().getTime()));
				d.setDbAddUser(doctorBean.getDbAddUser());
			} else {
				Doctor tempDoc = null;
				Query query = s.getNamedQuery("getDoctorDetailsByDoctorId");
				query.setLong("doctorId", doctorBean.getDoctorId());
				List<Doctor> doctorList = query.list();
				
				if(doctorList != null && doctorList.size() == 1) {
					tempDoc = doctorList.get(0);
				}
				d.setDbAddTs(tempDoc.getDbAddTs());
				d.setDbAddUser(tempDoc.getDbAddUser());
				d.setDbUpdTs(new Timestamp(new Date().getTime()));
				d.setDbUpdUser(doctorBean.getDbUpdUser());
				s.close();
				getHibernateSession();
			}
			MedicineUtility mUtil = new MedicineUtility();
			if(!"".equals(doctorBean.getDateOfAssociation())) {
				d.setDateOfAssociation(mUtil.getDateFromString(doctorBean.getDateOfAssociation()));
			}
			if(!"".equals(doctorBean.getDateOfRelease())) {
				d.setDateOfRelease(mUtil.getDateFromString(doctorBean.getDateOfRelease()));
				Date today = new Date();
				if(today.before(d.getDateOfRelease())) {
					d.setIsActive("A");
				}
			}
			
			if(!"".equals(doctorBean.getDateOfAssociation()) && 
					(doctorBean.getDateOfRelease() == null || "".equals(doctorBean.getDateOfRelease()))) {
				d.setIsActive("A");
			}
			
			tx = s.beginTransaction();
			s.saveOrUpdate(d);
			
		} catch (Exception e) {
			if(doctorBean.getMsg() != null && "".equals(doctorBean.getMsg().trim())) {
				doctorBean.setMsg("Operation failed");
			}
			e.printStackTrace();
			throw new AppException();
		} finally {
			try {
				if(tx != null)tx.commit();
			} catch (Exception e) {
				if(tx != null)tx.rollback();
				e.printStackTrace();
				doctorBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
		
		if(doctorBean.getDoctorId() == 0) {
			doctorBean.setMsg("Doctor Created successfully!!!!!");
			doctorBean.setDoctorId(d.getDoctorId());
		} else {
			doctorBean.setMsg("Doctor Updated successfully!!!!!");
		}
		
	}

	public void searchDoctor(DoctorBean doctorBean) throws AppException {
		Transaction tx = null;
		try {
			String pin = "%";
			String state = "%";
			String doctorName = "%";
			tx = s.beginTransaction();
			// Search Doctors by page
			int startIndex = (doctorBean.getPage() - 1) * doctorBean.getRows();
			int totalRecords = doctorBean.getRows();
			Query query = s.getNamedQuery("searchDoctors");
			if(doctorBean.getDoctorName() != null && !"".equals(doctorBean.getDoctorName().trim())) {
				doctorName = doctorName + doctorBean.getDoctorName().toUpperCase() + "%";
			}
			if(doctorBean.getState() != null && !"".equals(doctorBean.getState().trim())) {
				state = state + doctorBean.getState().toUpperCase() + "%";
			}
			if(doctorBean.getPin() != null && !"".equals(doctorBean.getPin().trim())) {
				pin = pin + doctorBean.getPin().toUpperCase() + "%";
			}
			query.setString("doctorName", doctorName);
			
			query.setFirstResult(startIndex);
			query.setMaxResults(totalRecords);
			List<DoctorBean> doctorList = query.setResultTransformer(Transformers.aliasToBean(DoctorBean.class)).list();
			for(DoctorBean dBean: doctorList) {
				MedicineUtility mUtil = new MedicineUtility();
				dBean.setDateOfAssociation(mUtil.getDateStrFromDate(dBean.getDateOfAssociationActual()));
				dBean.setDateOfRelease(mUtil.getDateStrFromDate(dBean.getDateOfReleaseActual()));
				if(dBean.getDateOfReleaseActual() != null && dBean.getDateOfReleaseActual().before(new Date())) {
					// Update doctor table
					query = s.getNamedQuery("getDoctorDetailsByDoctorId");
					query.setInteger("doctorId", dBean.getDoctorId());
					List<Doctor> doctorToBeUpdated = query.list();
					if(doctorToBeUpdated != null && doctorToBeUpdated.size() == 1) {
						Doctor d = doctorToBeUpdated.get(0);
						d.setIsActive("I");
						s.update(d);
					}
					
					dBean.setIsActive("I");
				}
			}
			doctorBean.setSearchDoctorList(doctorList);
			
			query = s.getNamedQuery("countDoctors");
			query.setString("doctorName", doctorName);
			List<Integer> countDoctors = query.list();
			
			if(countDoctors != null && countDoctors.size() > 0) {
				doctorBean.setTotal(Integer.parseInt(countDoctors.get(0) + ""));
			} else {
				doctorBean.setTotal(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null) tx.rollback();
			throw new AppException();
		} finally {
			try {
				if(tx != null)tx.commit();
			} catch (Exception e) {
				if(tx != null)tx.rollback();
				e.printStackTrace();
				doctorBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
	}

	public void viewDoctor(DoctorBean doctorBean) throws AppException {
		Query query = s.getNamedQuery("getDoctorDetailsByDoctorId1");
		query.setLong("doctorId", doctorBean.getDoctorId());
		List<DoctorBean> doctorList = query.setResultTransformer(Transformers.aliasToBean(DoctorBean.class)).list();
		
		if(doctorList != null && doctorList.size() == 1) {
			try {
				MedicineUtility mUtil = new MedicineUtility();
				BeanUtils.copyProperties(doctorBean, doctorList.get(0));
				doctorBean.setDateOfAssociation(mUtil.getDateStrFromDate(doctorList.get(0).getDateOfAssociationActual()));
				doctorBean.setDateOfRelease(mUtil.getDateStrFromDate(doctorList.get(0).getDateOfReleaseActual()));
//				doctorBean = doctorList.get(0);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			throw new AppException();
		}
	}
	public boolean checkDuplicateDoctorByDoctorName(DoctorBean doctorBean) throws AppException {
		if(doctorBean.getDoctorName() != null) {
			Query query = s.getNamedQuery("getDoctorByDoctorName");
			query.setString("doctorName", doctorBean.getDoctorName().toUpperCase());
			List<Doctor> doctorList = query.list();
			
			if(doctorList != null && doctorList.size() > 0) {
				if(doctorBean.getDoctorId() == doctorList.get(0).getDoctorId()) {
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

	public void deleteDoctor(DoctorBean doctorBean) throws AppException {
		if(doctorBean.getDoctorId() == 0) {
			doctorBean.setMsg("Operation Failed...<br>Doctor Does not Exists");
			throw new AppException();
		}
		Query query = s.getNamedQuery("getDoctorDetailsByDoctorId");
		query.setLong("doctorId", doctorBean.getDoctorId());
		List<Doctor> doctorList = query.list();
		
		if(doctorList != null && doctorList.size() == 1) {
			Transaction tx = null;
			try {
				tx = s.beginTransaction();
				s.delete(doctorList.get(0));
				doctorBean.setMsg("Doctor Deleted Successfully");
			} catch (Exception e) {
				if(doctorBean.getMsg() != null && "".equals(doctorBean.getMsg().trim())) {
					doctorBean.setMsg("Operation failed");
				}
				e.printStackTrace();
				throw new AppException();
			} finally {
				try {
					if(tx != null)tx.commit();
				} catch (Exception e) {
					if(tx != null)tx.rollback();
					e.printStackTrace();
					doctorBean.setMsg("Operation failed. Doctor id is in use.");
				}
				if(s != null) s.close();
			}
		}
	}
	
	public void getAllDoctors(DoctorBean doctorBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getAllDoctors");
			List<DoctorBean> doctorList =
				query.setResultTransformer(Transformers.aliasToBean(DoctorBean.class)).list();
			DoctorBean doctor = new DoctorBean();
			doctor.setDoctorId(-1);
			doctor.setDoctorName("Other");
			doctorList.add(0, doctor);
			doctorBean.setDoctorList(doctorList);
		} catch(Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch doctor's list");
		}
	}
	
	public void getDoctorByDoctorId(DoctorBean doctorBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getDoctorByDoctorId");
			query.setInteger("doctorId", doctorBean.getDoctorId());
			List<DoctorBean> doctorList =
				query.setResultTransformer(Transformers.aliasToBean(DoctorBean.class)).list();
			if(doctorList != null && doctorList.size() > 0) {
				BeanUtils.copyProperties(doctorBean, doctorList.get(0));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch customer list");
		}
	}
}
