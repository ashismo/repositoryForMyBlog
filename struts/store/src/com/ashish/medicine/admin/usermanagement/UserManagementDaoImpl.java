package com.ashish.medicine.admin.usermanagement;

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
import com.ashish.medicine.admin.myaccount.MyAccountBean;
import com.ashish.medicine.entity.Company;
import com.ashish.medicine.entity.User;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.hinernate.util.HibernateUtil;
import com.ashish.medicine.util.MedicineConstants;

public class UserManagementDaoImpl extends MedicineBaseDaoImpl implements UserManagementDao {

//	private Session s = null;
//	public UserManagementDaoImpl() {
//		try {
//			s = HibernateUtil.getSessionFactory().openSession();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	public void addOrUpdateUser(UserManagementBean userManagementBean) throws AppException {
		Transaction tx = null;
		User user = new User();
		try {
			if(checkDuplicateUserByUserName(userManagementBean)) {
				userManagementBean.setMsg("User Name Already Exists");
				throw new AppException();
			}
			if(userManagementBean.getUserId() == 0) {
				userManagementBean.setMsg("User Created");
			} else {
					// get user password from database
					Query query = s.getNamedQuery("getUserAccountDetails");
					query.setString("userName", userManagementBean.getUserName());
					List<MyAccountBean> myAccountList =
						query.setResultTransformer(Transformers.aliasToBean(MyAccountBean.class)).list();
					if(myAccountList != null && myAccountList.size() == 1) {
						if(userManagementBean.getPassword() == null || userManagementBean.getPassword().equals("")) {
							if(myAccountList.get(0).getPassword() != null && !myAccountList.get(0).getPassword().equals("")) {
								userManagementBean.setPassword(myAccountList.get(0).getPassword());
							}
						}
						if(myAccountList.get(0).getRole() != null && 
								myAccountList.get(0).getRole().equals(MedicineConstants.SUPER_ADMIN)) {
							if(!myAccountList.get(0).getRole().equals(userManagementBean.getRole())) {
								userManagementBean.setMsg("Super Admin Role can not be changed");
								throw new AppException();
							}
						}
					}
			}
			
			BeanUtils.copyProperties(user, userManagementBean);
			if(userManagementBean.getUserId() == 0) {
				user.setDbAddTs(new Timestamp(new Date().getTime()));
				user.setDbUpdTs(new Timestamp(new Date().getTime()));
				user.setDbAddUser(userManagementBean.getDbAddUser());
			} else {
				User tempUser = new User();
				Query query = s.getNamedQuery("getUserByUserId");
				query.setLong("userId", userManagementBean.getUserId());
				List<User> userList = query.list();
				
				if(userList != null && userList.size() == 1) {
					tempUser = userList.get(0);
				}
				user.setDbAddTs(tempUser.getDbAddTs());
				user.setDbAddUser(tempUser.getDbAddUser());
				user.setDbUpdTs(new Timestamp(new Date().getTime()));
				user.setDbUpdUser(userManagementBean.getDbUpdUser());
				s.close();
				getHibernateSession();
			}
			tx = s.beginTransaction();
			s.saveOrUpdate(user);
			
		} catch (Exception e) {
			if(userManagementBean.getMsg() != null && "".equals(userManagementBean.getMsg().trim())) {
				userManagementBean.setMsg("Operation failed");
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
				userManagementBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
		
		if(userManagementBean.getUserId() == 0) {
			userManagementBean.setMsg("User Created successfully!!!!!");
			userManagementBean.setUserId(user.getUserId());
		} else {
			userManagementBean.setMsg("User Updated successfully!!!!!");
		}
		
	}

	public void searchUser(UserManagementBean userManagementBean) throws AppException {
		try {
			String role = "%";
			String userName = "%";
			// Search Users by page
			int startIndex = (userManagementBean.getPage() - 1) * userManagementBean.getRows();
			int totalRecords = userManagementBean.getRows();
			Query query = s.getNamedQuery("searchUsers");
			if(userManagementBean.getUserName() != null && !"".equals(userManagementBean.getUserName().trim())) {
				userName = userName + userManagementBean.getUserName() + "%";
			}
			if(userManagementBean.getRole() != null && !"".equals(userManagementBean.getRole().trim())) {
				role = role + userManagementBean.getRole().toUpperCase() + "%";
			}
			query.setString("role", role);
			query.setString("userName", userName);
			
			query.setFirstResult(startIndex);
			query.setMaxResults(totalRecords);
			List<User> userList = query.list();
			List<UserManagementBean> uBeanList = new ArrayList<UserManagementBean>();
			for(User u: userList) {
				UserManagementBean uBean = new UserManagementBean();
				BeanUtils.copyProperties(uBean, u);
				uBean.setPassword("");
				uBeanList.add(uBean);
			}
			userManagementBean.setSearchUserList(uBeanList);
			
			if(uBeanList != null && uBeanList.size() > 0) {
				userManagementBean.setTotal(uBeanList.size());
			} else {
				userManagementBean.setTotal(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} 
	}

	public void viewUser(UserManagementBean userManagementBean) throws AppException {
		Query query = s.getNamedQuery("getUserByUserId");
		query.setLong("userId", userManagementBean.getUserId());
		List<User> userList = query.list();
		
		if(userList != null && userList.size() == 1) {
			try {
				BeanUtils.copyProperties(userManagementBean, userList.get(0));
//				userManagementBean.setPassword("");
				userManagementBean.setConfirmPassword(userManagementBean.getPassword());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			throw new AppException();
		}
	}
	public boolean checkDuplicateUserByUserName(UserManagementBean userManagementBean) throws AppException {
		if(userManagementBean.getUserName() != null) {
			Query query = s.getNamedQuery("getUserByUserName");
			query.setString("userName", userManagementBean.getUserName());
			List<User> userList = query.list();
			
			if(userList != null && userList.size() > 0) {
				if(userManagementBean.getUserId() == userList.get(0).getUserId()) {
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

	public void deleteUser(UserManagementBean userManagementBean) throws AppException {
		if(userManagementBean.getUserId() == 0) {
			userManagementBean.setMsg("Operation Failed...<br>User Does not Exists");
			throw new AppException();
		}
		
		Query query = s.getNamedQuery("getUserAccountDetails");
		query.setString("userName", userManagementBean.getUserName());
		List<MyAccountBean> myAccountList =
			query.setResultTransformer(Transformers.aliasToBean(MyAccountBean.class)).list();
		if(myAccountList != null && myAccountList.size() == 1) {
			if(myAccountList.get(0).getRole() != null && 
					myAccountList.get(0).getRole().equals(MedicineConstants.SUPER_ADMIN)) {
				userManagementBean.setMsg("Super Admin Role can not be deleted");
				throw new AppException();
			}
		}
		
		query = s.getNamedQuery("getUserByUserId");
		query.setLong("userId", userManagementBean.getUserId());
		List<User> userList = query.list();
		
		if(userList != null && userList.size() == 1) {
			Transaction tx = null;
			try {
				tx = s.beginTransaction();
				s.delete(userList.get(0));
				userManagementBean.setMsg("User Deleted Successfully");
			} catch (Exception e) {
				if(userManagementBean.getMsg() != null && "".equals(userManagementBean.getMsg().trim())) {
					userManagementBean.setMsg("Operation failed");
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
					userManagementBean.setMsg("Operation failed. Unable to commit changes into database.");
				}
				if(s != null) s.close();
			}
		}
	}
	

	public void getAllUsers(UserManagementBean userManagementBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getAllUsers");
			List<UserManagementBean> userList =
				query.setResultTransformer(Transformers.aliasToBean(UserManagementBean.class)).list();
			
			userManagementBean.setSearchUserList(userList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch all companies");
		} finally {
			s.close();
		}
	}
	
	public User getUserByUserId(UserManagementBean userManagementBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getUserByUserId");
			query.setLong("userId", userManagementBean.getUserId());
			List<User> userList = query.list();
			
			if(userList != null && userList.size() == 1) {
				return userList.get(0);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
		return null;
	}
	
	public void getAllSecurityQuestions(UserManagementBean userManagementBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getAllStandardSecurityQuestions");
			List<UserManagementBean> seqQuesList =
				query.setResultTransformer(Transformers.aliasToBean(UserManagementBean.class)).list();
			
			userManagementBean.setSecurityQuestionsList(seqQuesList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch Security Questions");
		} finally {
			s.close();
		}
	}
	
	public void getAllSecurityAnswers(UserManagementBean userManagementBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getAllSecurityAnswersByUserId");
			query.setLong("userId", userManagementBean.getUserId());
			List<UserManagementBean> secAnsList =
				query.setResultTransformer(Transformers.aliasToBean(UserManagementBean.class)).list();
			
			int count = 0;
			if(secAnsList != null && secAnsList.size() > 0) {
				for(UserManagementBean uMgmtBean : userManagementBean.getSecurityQuestionsList()) {
					uMgmtBean.setAnswer(secAnsList.get(count).getAnswer());
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch Security Questions");
		} finally {
			s.close();
		}
	}
	
	public void matchSecurityAnswers(UserManagementBean userManagementBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getUserDetailsByUserName");
			query.setString("userName", userManagementBean.getUserName());
			List<User> userList = query.list();
			if(userList != null && userList.size() == 1) {
				User user = userList.get(0);
				
				// fetch all security answer
				query = s.getNamedQuery("getAllSecurityAnswersByUserId");
				query.setLong("userId", user.getUserId());
				List<UserManagementBean> secAnsList =
					query.setResultTransformer(Transformers.aliasToBean(UserManagementBean.class)).list();
				
				int count = 0;
				int answerMatched = 0;
				if(secAnsList != null && secAnsList.size() > 0) {
					for(UserManagementBean uMgmtBean : userManagementBean.getSecurityQuestionsList()) {
						String answerInDb = secAnsList.get(count).getAnswer();
						if(answerInDb != null) {
							if(answerInDb.trim().equalsIgnoreCase(uMgmtBean.getAnswer())) {
								answerMatched++;
							}
						}
						count++;
					}
					
					// After minimum 2 successful match, set the password
					if(answerMatched >= 2) {
						userManagementBean.setPassword(user.getPassword());
					} else {
						throw new AppException("Minimum 2 Security Answers need to be answered properly");
					}
				} else {
					throw new AppException("Failed to fetch match Security Answers");
				}
			} else {
				throw new AppException("User Does not exist");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch match Security Answers");
		} finally {
			s.close();
		}
	}
}
