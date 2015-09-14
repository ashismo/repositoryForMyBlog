package com.ashish.medicine.admin.myaccount;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.ashish.medicine.admin.base.MedicineBaseDaoImpl;
import com.ashish.medicine.entity.Owner;
import com.ashish.medicine.entity.SequrityQuestion;
import com.ashish.medicine.entity.User;
import com.ashish.medicine.entity.UserSecurity;
import com.ashish.medicine.exception.AppException;

public class MyAccountDaoImpl extends MedicineBaseDaoImpl implements MyAccountDao {

//	private Session s = HibernateUtil.getSessionFactory().openSession();
	
	public void myAccount(MyAccountBean myaccountBean) throws AppException {
		Query query = s.getNamedQuery("getAccountDetails");
		List<MyAccountBean> myAccountList =
			query.setResultTransformer(Transformers.aliasToBean(MyAccountBean.class)).list();
		if(myAccountList != null && myAccountList.size() == 1) {
			try {
//				MedicineUtility mUtil = new MedicineUtility();
				BeanUtils.copyProperties(myaccountBean, myAccountList.get(0));
//				myaccountBean.setMfgDate(mUtil.getDateStrFromDate(myaccountBean.getMfgDateActual()));
//				myaccountBean.setExpDate(mUtil.getDateStrFromDate(myaccountBean.getExpDateActual()));
//				myaccountBean.setPurchaseDate(mUtil.getDateStrFromDate(myaccountBean.getPurchaseDateActual()));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateAccount(MyAccountBean myaccountBean) throws AppException {
		boolean isAccountExists = false;
		Owner owner = new Owner();
		Query query = s.getNamedQuery("getAccountEntityDetails");
		List<Owner> myAccountList = query.list();
		MyAccountBean maBean = new MyAccountBean();
		if(myAccountList != null && myAccountList.size() == 1) {
			isAccountExists = true;
			owner = myAccountList.get(0);
			myaccountBean.setOwnerId(owner.getOwnerId());
			myaccountBean.setDbUpdTs(new Timestamp(new Date().getTime()));
			owner.setDbUpdUser(myaccountBean.getDbUpdUser());
		}
		Transaction tx = null;
		
		try {
			BeanUtils.copyProperties(owner, myaccountBean);
			
			if(!isAccountExists) {
				owner.setOwnerId(0);
				owner.setDbAddTs(new Timestamp(new Date().getTime()));
				owner.setDbUpdTs(new Timestamp(new Date().getTime()));
				owner.setDbAddUser(myaccountBean.getDbAddUser());
			} 
			tx = s.beginTransaction();
			s.saveOrUpdate(owner);
			myaccountBean.setMsg("Account Updated successfully!!!!!");
		} catch (Exception e) {
			if(myaccountBean.getErrMsg() != null && "".equals(myaccountBean.getErrMsg().trim())) {
				myaccountBean.setErrMsg("Unable to update My Account");
			}
			e.printStackTrace();
			throw new AppException();
		} finally {
			try {
				if(tx != null)tx.commit();
			} catch (Exception e) {
				if(tx != null)tx.rollback();
				e.printStackTrace();
				myaccountBean.setErrMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
		
	}
	
	public void fetchUserAccount(MyAccountBean myaccountBean) throws AppException {
		Query query = s.getNamedQuery("getUserAccountDetails");
		query.setString("userName", myaccountBean.getUserName());
		List<MyAccountBean> myAccountList =
			query.setResultTransformer(Transformers.aliasToBean(MyAccountBean.class)).list();
		if(myAccountList != null && myAccountList.size() == 1) {
			try {
				BeanUtils.copyProperties(myaccountBean, myAccountList.get(0));
				myaccountBean.setMsg("Data retrieved...");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateUserAccount(MyAccountBean myaccountBean) throws AppException {
		// Atleast two security answers to be provided
		String secAns[] = myaccountBean.getAnswer();
		int noOfSecAns = 0;
		for(String ans : secAns) {
			if(ans != null && !ans.trim().equals("")) {
				noOfSecAns++;
			}
		}
		if(noOfSecAns < 1) {
			myaccountBean.setErrMsg("Two security questions must be answered");
			throw new AppException(myaccountBean.getErrMsg());
		}
		
		boolean isAccountExists = false;
		User user = new User();
		Query query = s.getNamedQuery("getUserDetailsByUserName");
		query.setString("userName", myaccountBean.getUserName());
		List<User> userAccountList = query.list();
		MyAccountBean maBean = new MyAccountBean();
		if(userAccountList != null && userAccountList.size() == 1) {
			user = userAccountList.get(0);
			// verify current password field.
			if(!"".equals(myaccountBean.getPassword()) && !myaccountBean.getPassword().equals(user.getPassword())) {
				myaccountBean.setErrMsg("Current password does not match");
				if(s != null) s.close();
				throw new AppException(myaccountBean.getErrMsg());
			}
			
			//password = newPassword if newPassword field is not empty 
			if(!"".equals(myaccountBean.getNewPassword())) {
				myaccountBean.setPassword(myaccountBean.getNewPassword());
			}
			isAccountExists = true;
			myaccountBean.setDbAddTs(user.getDbAddTs());
			myaccountBean.setDbUpdTs(user.getDbUpdTs());
			myaccountBean.setDbAddUser(user.getDbAddUser());
//			myaccountBean.setDbUpdUser(user.getDbUpdUser());
			myaccountBean.setUserId(user.getUserId());
			
			query = s.getNamedQuery("getUserAccountDetails");
			query.setString("userName", myaccountBean.getUserName());
			List<MyAccountBean> myAccountList =
				query.setResultTransformer(Transformers.aliasToBean(MyAccountBean.class)).list();
			if(myAccountList != null && myAccountList.size() == 1) {
				if(myaccountBean.getNewPassword() != null && myaccountBean.getNewPassword().trim().equals("") ) {
					myaccountBean.setPassword(myAccountList.get(0).getPassword());
				}
				myaccountBean.setRole(myAccountList.get(0).getRole());
			}
		}
		Transaction tx = null;
		
		try {
			BeanUtils.copyProperties(user, myaccountBean);
			
			if(!isAccountExists) {
				user.setUserId(0);
				user.setDbAddTs(new Timestamp(new Date().getTime()));
			} else {
				query = s.getNamedQuery("getUserByUserId");
				query.setInteger("userId", myaccountBean.getUserId());
				List<User> userList = query.list();
				user.setDbUpdTs(new Timestamp(new Date().getTime()));
				user.setDbUpdUser(userList.get(0).getDbUpdUser());
				if(userList != null && userList.size() == 1) {
					user.setDbAddTs(userList.get(0).getDbAddTs());
					user.setDbAddUser(userList.get(0).getDbAddUser());
				}
				if(s != null) s.close();
				getHibernateSession();
			}
			
			tx = s.beginTransaction();
			s.saveOrUpdate(user);
			if(tx != null)tx.commit();
			
			// Update security questions
			
			// Fetch all Security questions
			query = s.getNamedQuery("getAllSecurityQuestionsEntity");
			List<SequrityQuestion> secQuestionList = query.list();
			
			// fetch all security answers
			query = s.getNamedQuery("getAllSecurityAnswersEntityByUserId");
			query.setInteger("userId", myaccountBean.getUserId());
			List<UserSecurity> uSecAnsList = query.list();
			
			// Fetch User 
			query = s.getNamedQuery("getUserByUserId");
			query.setInteger("userId", myaccountBean.getUserId());
			List<User> userList = query.list();
			
			for(int i = 0; i < myaccountBean.getQuestionId().length; i++) {
				if(s != null) s.close();
				getHibernateSession();
				tx = s.beginTransaction();
				
				SequrityQuestion secQuestion = secQuestionList.get(i);
				
				UserSecurity uSec = null;
				if(uSecAnsList != null && uSecAnsList.size() == 0) {
					uSec = new UserSecurity();
					uSec.setDbAddTs(new Timestamp(new Date().getTime()));
					uSec.setDbAddUser(myaccountBean.getUserId());
				} else {
					uSec = uSecAnsList.get(i);
					uSec.setDbUpdTs(new Timestamp(new Date().getTime()));
					uSec.setDbUpdUser(myaccountBean.getUserId());
				}
				uSec.setSequrityQuestion(secQuestion);
				uSec.setQuestion(secQuestion.getQuestion());
				uSec.setAnswer(myaccountBean.getAnswer()[i]);
				if(userList != null && userList.size() == 1) {
					uSec.setUser(userList.get(0));
				}
				
				s.saveOrUpdate(uSec);
				if(tx != null)tx.commit();
			}
			
			myaccountBean.setMsg("User Account Updated successfully!!!!!");
		} catch (Exception e) {
//			if(myaccountBean.getErrMsg() != null && "".equals(myaccountBean.getErrMsg().trim())) {
//				myaccountBean.setErrMsg("Unable to update User Account");
//			}
			myaccountBean.setErrMsg("Unable to update User Account");
			if(tx != null)tx.rollback();
			e.printStackTrace();
			myaccountBean.setErrMsg("Operation failed. Unable to commit changes into database.");
			throw new AppException();
		} finally {
//			try {
//				if(tx != null)tx.commit();
//			} catch (Exception e) {
//				if(tx != null)tx.rollback();
//				e.printStackTrace();
//				myaccountBean.setErrMsg("Operation failed. Unable to commit changes into database.");
//			}
			if(s != null) s.close();
		}
		
	}
}
