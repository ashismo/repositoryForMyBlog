package com.ashish.medicine.admin.buy;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ashish.medicine.entity.Company;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.hinernate.util.HibernateUtil;

public class BuyDaoImpl implements BuyDao {

	public void addBuy(BuyBean buyBean) throws AppException {
		if(buyBean.getBuyId() == 0) {
			Company c = new Company();
			try {
				BeanUtils.copyProperties(c, buyBean);
				c.setDbAddTs(new Timestamp(new Date().getTime()));
				c.setDbUpdTs(new Timestamp(new Date().getTime()));
				Session s = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = s.beginTransaction();
				s.save(c);
				tx.commit();
				s.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new AppException();
			} 
		}
		
	}

	

}
