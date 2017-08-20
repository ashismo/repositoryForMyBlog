package com.ashish.learning.v4.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("springJDBCServices") // Even if "springJDBCServices" name is not declared then also this works
public class SpringJDBCServices {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringJDBCServices.class);
	
	@Autowired
	private SpringJDBCDao jdbcDao;
	
	public void displayUserDetailsByUsername(String username) {
		logger.info(">>>>>>>>>>displayUserDetailsByUsername STARTS>>>>>>>>>>>>>>>>>>>");
		UserBean userBean = jdbcDao.getUserByUserName(username);
		
		if(userBean != null) {
			System.out.println("displayUserDetailsByUsername username: " + userBean.getUsername());
			System.out.println("displayUserDetailsByUsername password: " + userBean.getPassword());
		}
		logger.info(">>>>>>>>>>displayUserDetailsByUsername ENDS>>>>>>>>>>>>>>>>>>>");
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
