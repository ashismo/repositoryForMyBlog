package com.ashish.jwt.token.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.jwt.token.db.model.User;
import com.ashish.jwt.token.db.repositories.AppUserRepository;

@Service("AppUserService")
public class AppUserService {
	
	@Autowired
	private AppUserRepository userRepo;
	
	public User findByUsername(String username) {
	
		return userRepo.findByUsername(username);
	}

}
