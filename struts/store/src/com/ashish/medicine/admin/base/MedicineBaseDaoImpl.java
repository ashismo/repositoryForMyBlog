package com.ashish.medicine.admin.base;

import org.hibernate.Session;

import com.ashish.medicine.hinernate.util.HibernateUtil;

public class MedicineBaseDaoImpl {
	protected Session s = null;
	public MedicineBaseDaoImpl() {
		try {
			s = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void getHibernateSession() {
		try {
			s = HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
