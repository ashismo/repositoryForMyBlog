package com.ashish.medicine.admin.schedule;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.ashish.medicine.admin.base.MedicineBaseDaoImpl;
import com.ashish.medicine.entity.Doctor;
import com.ashish.medicine.entity.Schedule;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.util.MedicineUtility;

public class ScheduleDaoImpl extends MedicineBaseDaoImpl implements ScheduleDao {

//	private Session s = null;
//	public ScheduleDaoImpl() {
//		try {
//			s = HibernateUtil.getSessionFactory().openSession();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void getHibernateSession() {
//		try {
//			s = HibernateUtil.getSessionFactory().openSession();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	public void addOrUpdateSchedule(ScheduleBean scheduleBean) throws AppException {
		Transaction tx = s.beginTransaction();
		Schedule schedule = new Schedule();
		Schedule tempSchedule = null;
		MedicineUtility mUtil = new MedicineUtility();
		Set<Integer> setOfScheduleIds = new HashSet<Integer>();
		try {
			for(String assignedItems : scheduleBean.getAssignedItems()) {
				Schedule sch = new Schedule();
				sch.setScheduleDate(mUtil.getDateFromString(scheduleBean.getScheduleDate()));
				sch.setScheduleLookupId(scheduleBean.getScheduleLookupId());
				if(scheduleBean.getScheduleLookupId() == 1) {
					String tempDoctorId = assignedItems.split("-")[3].split(",")[0];
					Query query = s.getNamedQuery("getDoctorDetailsByDoctorId");
					query.setLong("doctorId", Long.parseLong(tempDoctorId));
					List<Doctor> doctorList = query.list();
					if(doctorList != null && doctorList.size() == 1) {
						sch.setDoctor(doctorList.get(0));
					}
				}
				
				// Set day,time and value - 0800-Monday-Music
				String day = assignedItems.split("-")[1];
				String time = assignedItems.split("-")[0];
				String value = assignedItems.split("-")[3].split(",")[1];
				sch.setScheduleDay(day);
				sch.setScheduleTime(time);
				sch.setScheduleValue(value);
				sch.setDbAddTs(new Timestamp(new Date().getTime()));
				sch.setDbAddUser(scheduleBean.getDbAddUser());
				sch.setDbUpdTs(new Timestamp(new Date().getTime()));
				sch.setDbUpdUser(scheduleBean.getDbUpdUser());
				s.saveOrUpdate(sch);
				setOfScheduleIds.add(sch.getScheduleId());
			}
			// Delete entries which are not present in the set.
			Date scheduleStartDate = mUtil.getfirstDayOfWeek(mUtil.getDateFromString(scheduleBean.getScheduleDate()));
			Date scheduleEndDate =  mUtil.getLastDayOfWeek(mUtil.getDateFromString(scheduleBean.getScheduleDate()));
			Query query = s.getNamedQuery("getSchedulesByScheduleIds");
			query.setDate("scheduleStartDate", scheduleStartDate);
			query.setDate("scheduleEndDate", scheduleEndDate);
			List<Schedule> scheduleList = query.list();
			if(scheduleList != null && scheduleList.size() > 0) {
				for(Schedule sch : scheduleList) {
					if(!setOfScheduleIds.contains(sch.getScheduleId())) {
						s.delete(sch);
					}
				}
			}
		} catch (Exception e) {
			if(scheduleBean.getMsg() != null && "".equals(scheduleBean.getMsg().trim())) {
				scheduleBean.setMsg("Operation failed");
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
				scheduleBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
		
	}

	public void scheduleCriteriaForm(ScheduleBean scheduleBean) throws AppException {
		try {
			MedicineUtility mUtil = new MedicineUtility();
			Date scheduleDate = null;
			Date firstDayOfWeek = new Date();
			Date lastDayOfWeek = new Date();
			if(scheduleBean.getScheduleDate() == null || scheduleBean.getScheduleDate().trim().equals("")) {
				scheduleDate = new Date();
			} else {
				scheduleDate = mUtil.getDateFromString(scheduleBean.getScheduleDate());
			}
				firstDayOfWeek = mUtil.getfirstDayOfWeek(scheduleDate);
				lastDayOfWeek = mUtil.getLastDayOfWeek(scheduleDate);
				
				// find date to display for left side
				String namedQuery = "";
				if(scheduleBean.getScheduleLookupId() == 1) {
					namedQuery = "getScheduleLeftItemsByDoctors";
				}
				Query query = s.getNamedQuery(namedQuery);
				query.setDate("lastDayOfWeek", lastDayOfWeek);
				query.setDate("firstDayOfWeek", firstDayOfWeek);
				List<ScheduleBean> scheduleListLeft = query.setResultTransformer(Transformers.aliasToBean(ScheduleBean.class)).list();
				scheduleBean.setScheduleListLeft(scheduleListLeft);
				
				// Find data for right side table
				query = s.getNamedQuery("getScheduleOfAWeek");
				query.setDate("scheduleStartDate", firstDayOfWeek);
				query.setDate("scheduleEndDate", lastDayOfWeek);
				List<ScheduleBean> scheduleListRight = query.setResultTransformer(Transformers.aliasToBean(ScheduleBean.class)).list();
				scheduleBean.setScheduleListRight(scheduleListRight);
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} 
	}

	public void viewSchedule(ScheduleBean scheduleBean) throws AppException {
		scheduleCriteriaForm(scheduleBean);
		
	}
	public boolean checkDuplicateScheduleByScheduleName(ScheduleBean scheduleBean) throws AppException {
//		if(scheduleBean.getScheduleName() != null) {
//			Query query = s.getNamedQuery("getScheduleByScheduleName");
//			query.setString("scheduleName", scheduleBean.getScheduleName().toUpperCase());
//			List<Schedule> scheduleList = query.list();
//			
//			if(scheduleList != null && scheduleList.size() > 0) {
//				if(scheduleBean.getScheduleId() == scheduleList.get(0).getScheduleId()) {
//					return false;
//				} else {
//					return true;
//				}
//			} else {
//				return false;
//			}
//		}
		return false;
	}

	public void deleteSchedule(ScheduleBean scheduleBean) throws AppException {
		MedicineUtility mUtil = new MedicineUtility();
		Date scheduleDate = null;
		Date firstDayOfWeek = new Date();
		Date lastDayOfWeek = new Date();
		if(scheduleBean.getScheduleDate() == null || scheduleBean.getScheduleDate().trim().equals("")) {
			scheduleDate = new Date();
		} else {
			scheduleDate = mUtil.getDateFromString(scheduleBean.getScheduleDate());
		}
			firstDayOfWeek = mUtil.getfirstDayOfWeek(scheduleDate);
			lastDayOfWeek = mUtil.getLastDayOfWeek(scheduleDate);
			
		Query query = s.getNamedQuery("getScheduleEntitiesOfAWeek");
		query.setDate("scheduleStartDate", firstDayOfWeek);
		query.setDate("scheduleEndDate", lastDayOfWeek);
		List<Schedule> scheduleListToBeDelete = query.list();
		
		
		Transaction tx = s.beginTransaction();
		try {
			for(Schedule schedule : scheduleListToBeDelete) {
				s.delete(schedule);
			}
		} catch (Exception e) {
			if(scheduleBean.getMsg() != null && "".equals(scheduleBean.getMsg().trim())) {
				scheduleBean.setMsg("Operation failed");
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
				scheduleBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
		
	}

	public void getAllCompanies(ScheduleBean scheduleBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getAllCompanies");
			List<ScheduleBean> scheduleList =
				query.setResultTransformer(Transformers.aliasToBean(ScheduleBean.class)).list();
			
			scheduleBean.setSearchScheduleList(scheduleList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch all companies");
		} finally {
			s.close();
		}
	}
	
	public Schedule getScheduleByScheduleId(ScheduleBean scheduleBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getScheduleByScheduleId");
			query.setLong("scheduleId", scheduleBean.getScheduleId());
			List<Schedule> scheduleList = query.list();
			
			if(scheduleList != null && scheduleList.size() == 1) {
				return scheduleList.get(0);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
		return null;
	}
}
