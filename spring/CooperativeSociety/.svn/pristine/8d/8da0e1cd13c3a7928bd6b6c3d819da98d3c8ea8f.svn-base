package com.org.coop.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.coop.canonical.beans.UserProfile;
import com.org.coop.society.data.admin.entities.User;
import com.org.coop.society.data.admin.repositories.UserAdminRepository;

@Service
public class UserProfileAdminServiceImpl {
	
	@Autowired
	private UserAdminRepository userAdminRepository;
	
	public UserProfile getUserProfile(String userName) {
		UserProfile userProfile = new UserProfile();
		User user = userAdminRepository.findByUserName(userName);
		return userProfile;
	}
}
