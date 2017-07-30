package com.ashish.poc.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ashish.poc.dao.UserDaoImpl;
import com.ashish.poc.model.UserDataModel;
import com.ashish.poc.model.Users;
import com.ashish.poc.util.PasswordEncodeDecodeUtil;

@Component
public class UserServices {
	
	private static final Logger log = Logger.getLogger(UserServices.class);
	
	@Autowired
	private UserDaoImpl userDaoImpl;
	
	@Autowired
	private PasswordEncodeDecodeUtil passwordEncodeDecodeUtil;
	
	@Transactional//(propagation=Propagation.REQUIRED,readOnly=false, rollbackFor=Exception.class)
	public void createOrUpdateUser(UserDataModel udm) throws Exception{
		if(udm.getUsers() == null ||udm.getUsers().size() == 0) {
			udm.setErrorMsg("Input to create/update user is incorrect");
			return;
		}
		Users user = udm.getUsers().get(0);
		
		if(user.getUserId() == 0) {
			Users u = userDaoImpl.findByUserName(user.getUsername());
			
			if(u == null) {
				log.debug("New user creation request");
				userDaoImpl.createUser(user);
			} else {
				udm.setErrorMsg("User already exists. Please try different user name");
			}
		} else {
			log.debug("Check if user already exists");
			Users u = userDaoImpl.findByUserId(user.getUserId());
			
			if(u != null) {
				log.debug("User already exists. Updating the user");
				if(user.getPassword() != null && user.getPassword().equals(passwordEncodeDecodeUtil.base64encode(u.getPassword()))) {
					user.setPassword(passwordEncodeDecodeUtil.base64decode(user.getPassword()));
				}
				userDaoImpl.updateUser(user);
			}
		}
		getUsers(udm);
	}
	
	public void getUsers(UserDataModel udm) throws Exception {
		if(udm.getUsers() == null || udm.getUsers().size() == 0) {
			udm.setErrorMsg("Input to retrieve user is incorrect");
			return;
		}
		
		Users user = udm.getUsers().get(0);
		if(user != null && !StringUtils.isEmpty(user.getUsername())) { // Get user by userid
			user = userDaoImpl.findByUserName(user.getUsername());
			if(user == null) {
				udm.setErrorMsg("User not found");
				return;
			}
			List<Users> users = new ArrayList<Users>();
			users.add(user);
			udm.setUsers(users);
		} else {
			List<Users> users = userDaoImpl.findAll();
			udm.setUsers(users);
		}
		
		if(udm!= null && udm.getUsers() != null && udm.getUsers().size() > 0) {
			for(Users u : udm.getUsers()) {
				u.setPassword(passwordEncodeDecodeUtil.base64encode(u.getPassword()));
			}
		}
		
	}
	
	
	public boolean isUserAuthenticated(String username, String password) throws Exception {
		if(username != null && !StringUtils.isEmpty(username)) { // Get user by userid
			Users u = userDaoImpl.findByUserName(username);
			if(u == null || u.getPassword() == null || !u.getPassword().equals(password)) {
				return false;
			}
		} else {
			return false;
		}
		return true;
		
	}
	
}
