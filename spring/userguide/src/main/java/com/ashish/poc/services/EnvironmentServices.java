package com.ashish.poc.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ashish.poc.dao.EnvironmentMasterDaoImpl;
import com.ashish.poc.model.EnvironmentMaster;
import com.ashish.poc.model.UserDataModel;

@Component
public class EnvironmentServices {
	
	private static final Logger log = Logger.getLogger(EnvironmentServices.class);
	
	@Autowired
	private EnvironmentMasterDaoImpl envMasterDaoImpl;
	
	@Transactional//(propagation=Propagation.REQUIRED,readOnly=false, rollbackFor=Exception.class)
	public void createOrUpdateEnvironments(UserDataModel udm) throws Exception{
		if(udm.getEnvironments() == null || udm.getEnvironments().size() == 0) {
			udm.setErrorMsg("Input to create/update environment is incorrect");
			return;
		}
		EnvironmentMaster envMaster = udm.getEnvironments().get(0);
		if(envMaster.getEnvId() == 0) {
			EnvironmentMaster env = envMasterDaoImpl.findByEnvName(envMaster.getEnvName());
			
			if(env == null) {
				log.debug("New Environment creation request");
				envMasterDaoImpl.createEnvironment(envMaster);
			} else {
				udm.setErrorMsg("Environment already exists. Please try different environment name");
			}
		} else {
			log.debug("Check if Environment already exists");
			EnvironmentMaster env = envMasterDaoImpl.findByEnvId(envMaster.getEnvId());
			if(env != null) {
				log.debug("Environment already exists. Updating the environment");
				envMasterDaoImpl.updateEnvironment(envMaster);
			}
		}
		getEnvironments(udm);
	}
	
	public void getEnvironments(UserDataModel udm) throws Exception {
		if(udm.getEnvironments() == null || udm.getEnvironments().size() == 0) {
			udm.setErrorMsg("Input to retrieve environment details is incorrect");
			return;
		}
		
		EnvironmentMaster envMaster = udm.getEnvironments().get(0);
		if(envMaster != null && !StringUtils.isEmpty(envMaster.getEnvName())) { // Get user by userid
			envMaster = envMasterDaoImpl.findByEnvName(envMaster.getEnvName());
			if(envMaster == null) {
				udm.setErrorMsg("Environment not found");
				return;
			}
			List<EnvironmentMaster> environments = new ArrayList<EnvironmentMaster>();
			environments.add(envMaster);
			udm.setEnvironments(environments);
		} else {
			List<EnvironmentMaster> users = envMasterDaoImpl.findAll();
			udm.setEnvironments(users);
		}
		
	}
}
