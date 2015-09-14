package com.ashish.medicine.login;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.ashish.medicine.admin.base.MedicineBaseDaoImpl;
import com.ashish.medicine.entity.Owner;
import com.ashish.medicine.entity.User;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.hinernate.util.HibernateUtil;

public class LoginDaoImpl extends MedicineBaseDaoImpl implements LoginDao {
//	private Session s = null;
//	public LoginDaoImpl() {
//		try {
//			s = HibernateUtil.getSessionFactory().openSession();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	public void validateLogin(UserBean userBean) throws AppException {
		Query query = s.getNamedQuery("getUserDetailsByUserName");
		query.setString("userName", userBean.getUserName());
		List<User> userList = query.list();
		
		if(userList != null && userList.size() == 1) {
			userBean.setUser(userList.get(0));
			try {
				BeanUtils.copyProperties(userBean, userList.get(0));
				query = s.getNamedQuery("getAccountEntityDetails");
				List<Owner> ownerList = query.list();
				if(ownerList != null && ownerList.size() > 0) {
					BeanUtils.copyProperties(userBean, ownerList.get(0));
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			throw new AppException("User not found");
		}
	}
	
}
