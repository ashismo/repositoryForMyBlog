package com.ashish.poc.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ashish.poc.dao.RoleDaoImpl;
import com.ashish.poc.model.Role;
import com.ashish.poc.model.UserDataModel;
import com.ashish.poc.model.Users;

@Component
public class RoleServices {
	
	private static final Logger log = Logger.getLogger(RoleServices.class);
	
	@Autowired
	private RoleDaoImpl roleDaoImpl;
	
	@Transactional//(propagation=Propagation.REQUIRED,readOnly=false, rollbackFor=Exception.class)
	public void createOrUpdateRole(UserDataModel udm) throws Exception{
		if(udm.getRoles() == null ||udm.getRoles().size() == 0) {
			udm.setErrorMsg("Input to create/update role is incorrect");
			return;
		}
		Role role = udm.getRoles().get(0);
		if(role != null && !StringUtils.isEmpty(role.getRoleName())) {
			Role r = roleDaoImpl.findByRoleName(role.getRoleName());
			
			if(role.getRoleId() == 0) { // New role request
				if(r == null) {
					log.debug("New role creation request");
					roleDaoImpl.createRole(role);
				} else {
					udm.setErrorMsg("Role already exists. Please try different user name");
				}
			} else { // Update role request
				log.debug("Role already exists. Updating the role");
				roleDaoImpl.updateRole(role);
			}
			getRoles(udm);
		}
	}
	
	public void getRoles(UserDataModel udm) throws Exception {
		if(udm.getRoles() == null || udm.getRoles().size() == 0) {
			udm.setErrorMsg("Input to retrieve user is incorrect");
			return;
		}
		
		Role role = udm.getRoles().get(0);
		if(role != null && !StringUtils.isEmpty(role.getRoleName())) { // Get user by userid
			role = roleDaoImpl.findByRoleName(role.getRoleName());
			if(role == null) {
				udm.setErrorMsg("Role not found");
				return;
			}
			List<Role> roles = new ArrayList<Role>();
			roles.add(role);
			udm.setRoles(roles);
		} else {
			List<Role> roles = roleDaoImpl.findAll();
			udm.setRoles(roles);
		}
		
	}
}
