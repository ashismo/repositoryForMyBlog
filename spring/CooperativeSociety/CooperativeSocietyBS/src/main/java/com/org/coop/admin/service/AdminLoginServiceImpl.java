package com.org.coop.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.org.coop.society.data.admin.entities.User;
import com.org.coop.society.data.admin.entities.UserRole;
import com.org.coop.society.data.admin.repositories.UserAdminRepository;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {
	private static final Logger log = Logger.getLogger(AdminLoginServiceImpl.class); 

	@Autowired
	private UserAdminRepository userAdminRepository;
	
	@Transactional(value="adminTransactionManager", propagation=Propagation.REQUIRED, readOnly=false)
	public List<String> getRole(String username) {
		List<String> roleList = new ArrayList<String>();
		User user = userAdminRepository.findByUserName(username);
		if(user != null) {
			for(UserRole userRole : user.getUserRoles()) {
				roleList.add(userRole.getRoleMaster().getRoleName().toUpperCase());
			}
		}
		return roleList;
	}
}
