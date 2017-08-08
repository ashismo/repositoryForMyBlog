package com.ashish.learning.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("springJDBCServices") // Even if "springJDBCServices" name is not declared then also this works
public class SpringJDBCServices {
	@Autowired
	private SpringJDBCDao jdbcDao;
	
	public void displayUserDetailsByUsername(String username) {
		UserBean userBean = jdbcDao.getUserByUserName(username);
		
		System.out.println("username: " + userBean.getUsername());
		System.out.println("password: " + userBean.getPassword());
	}
	
	public void createUser(UserBean ub) {
		jdbcDao.createUser(ub);
		
		UserBean userBean = jdbcDao.getUserByUserName(ub.getUsername());
		System.out.println("username: " + userBean.getUsername());
		System.out.println("password: " + userBean.getPassword());
	}
}
