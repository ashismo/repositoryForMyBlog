package com.ashish.poc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ashish.poc.dao.UserDaoImpl;
import com.ashish.poc.model.UserDataModel;
import com.ashish.poc.model.Users;

@Component
public class UserServices {
	@Autowired
	private UserDaoImpl userDaoImpl;
	
	@Transactional//(propagation=Propagation.REQUIRED,readOnly=false, rollbackFor=Exception.class)
	public void createOrUpdateUser(UserDataModel udm) throws Exception{
		if(udm.getUsers() == null ||udm.getUsers().size() == 0) {
			udm.setErrorMsg("Input to create user is incorrect");
		}
		
		
		Users user = udm.getUsers().get(0);
		if(user.getUserId() == null || user.getUserId() == 0) {
			System.out.println("New user creation request");
			
			
			Users u = userDaoImpl.findByUserId(1);
			u.setName("Junk");
			userDaoImpl.updateUser(u);
			
			
			userDaoImpl.createUser(user);
		} else {
			System.out.println("Check if user already exists");
			Users u = userDaoImpl.findByUserId(user.getUserId());
			if(u != null) {
				System.out.println("User already exists. Updating the user");
				userDaoImpl.updateUser(user);
			}
		}
	}
}
