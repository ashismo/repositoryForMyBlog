package com.core.dao;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.core.entity.Test2;
import com.dao.util.HibernateUtil;

public class GroupDataDaoImpl {
	public void displayGroupDataByCompany() {
		Session s = HibernateUtil.getSessionFactory().openSession();
//		Session s = new AnnotationConfiguration().configure().buildSessionFactory().openSession();
		s.beginTransaction();
		Query q = s.getNamedQuery("getGrp");
		q.setInteger("col1", 1);
		
		System.out.println(q.list().size());
		
//		for(int i = 0; i < q.list().size(); i++) {
//			System.out.println("Group Name: " + ((Test2)q.list().get(i)).getGrpNm());
//		}
		
	}
	
	public void saveGrp() {
		Test2 tst = new Test2();
		tst.setCol1(2);
		tst.setCol2("1234");
		tst.setCol3(new Date());
//		grp.setGrpId(1);
//		grp.setGrpNm("Test1");
//		grp.setDbCrtDt(new Date());
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.save(tst);
		tx.commit();
		s.close();
	}
}
