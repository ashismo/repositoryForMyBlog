package com.ashish.learning.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("springJDBCServices") // Even if "springJDBCServices" name is not declared then also this works
public class SpringJDBCServices {
	@Autowired
	private SpringJDBCDao jdbcDao;
	
	public void displayUserDetailsByUsername(String username) {
		UserBean userBean = jdbcDao.getUserByUserName(username);
		
		if(userBean != null) {
			System.out.println("displayUserDetailsByUsername username: " + userBean.getUsername());
			System.out.println("displayUserDetailsByUsername password: " + userBean.getPassword());
		}
	}
	
	public void createUser(UserBean ub) {
		jdbcDao.createUser(ub);
	}
	
	@Transactional(rollbackFor={Exception.class}, propagation=Propagation.REQUIRED)
	public void transactionManagement(UserBean ub, UserBean ub1, boolean makeFailed) throws Exception {
		try {
			
			jdbcDao.createUser(ub);
			jdbcDao.createUser(ub1);
			
			if(makeFailed) {
				String nullPointer = null;
				nullPointer.charAt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
