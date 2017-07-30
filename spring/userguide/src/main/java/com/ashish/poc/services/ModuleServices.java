package com.ashish.poc.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ashish.poc.dao.ModuleMasterDaoImpl;
import com.ashish.poc.model.Module;
import com.ashish.poc.model.ModuleEnvironmentMaster;
import com.ashish.poc.model.UserDataModel;

@Component
public class ModuleServices {
	
	private static final Logger log = Logger.getLogger(ModuleServices.class);
	
	@Autowired
	private ModuleMasterDaoImpl moduleMasterDaoImpl;
	
	@Transactional//(propagation=Propagation.REQUIRED,readOnly=false, rollbackFor=Exception.class)
	public void createOrUpdateModules(UserDataModel udm) throws Exception{
		if(udm.getModules() == null || udm.getModules().size() == 0) {
			udm.setErrorMsg("Input to create/update module is incorrect");
			return;
		}
		Module moduleMaster = udm.getModules().get(0);
		if(moduleMaster.getModuleId() == 0) {
			Module env = moduleMasterDaoImpl.findByModuleName(moduleMaster.getModuleName());
			
			if(env == null) {
				log.debug("New Environment creation request");
				moduleMasterDaoImpl.createModule(moduleMaster);
			} else {
				udm.setErrorMsg("Module already exists. Please try different module name");
			}
		} else {
			log.debug("Check if Module already exists");
			Module env = moduleMasterDaoImpl.findByModuleId(moduleMaster.getModuleId());
			if(env != null) {
				log.debug("Module already exists. Updating the Module");
				moduleMasterDaoImpl.updateModule(moduleMaster);
			}
		}
		getModules(udm);
	}
	
	@Transactional//(propagation=Propagation.REQUIRED,readOnly=false, rollbackFor=Exception.class)
	public void attachOrDetachModuleAndEnv(UserDataModel udm) throws Exception{
		if(udm.getModuleEnvironments() == null || udm.getModuleEnvironments().size() == 0) {
			udm.setErrorMsg("Input to attach/detach module with environment is incorrect");
			return;
		}
		ModuleEnvironmentMaster moduleEnvMaster = udm.getModuleEnvironments().get(0);
		if(moduleEnvMaster.getModuleId() > 0 && moduleEnvMaster.getEnvId() > 0) {
			
			// Check if record exists
			try {
				if(!moduleEnvMaster.isDeleteInd()) {
					getModuleEnvironment(udm);
					if(StringUtils.isEmpty(udm.getErrorMsg())) {
						log.error("Module is already attached with environment");
						udm.setErrorMsg("Module is already attached with environment");
						return;
					}
				}
			} catch(Exception e) {
				log.error("Exception occured during data retrieval", e);
			}
			
			moduleMasterDaoImpl.attachModule(moduleEnvMaster);
			if(moduleEnvMaster.isDeleteInd()) {
				udm.setUserMsg("Record deleted successfully");
			} else {
				udm.setUserMsg("Module is attached with environment");
			}
		}
		if(!moduleEnvMaster.isDeleteInd()) {
			udm.setErrorMsg(""); // Resetting error message.
			getModuleEnvironment(udm);
		}
	}
	
	public void getModuleEnvironment(UserDataModel udm) throws Exception {
		if(udm.getModuleEnvironments() == null || udm.getModuleEnvironments().size() == 0) {
			udm.setErrorMsg("Invalid input. Unable to retrieve data");
			return;
		}
		
		ModuleEnvironmentMaster moduleEnvMaster = udm.getModuleEnvironments().get(0);
		List<ModuleEnvironmentMaster> moduleEnvironmentMasters = moduleMasterDaoImpl.getModuleEnv(moduleEnvMaster);
		if(moduleEnvironmentMasters != null && moduleEnvironmentMasters.size() == 0) {
			log.debug("No records found");
			udm.setErrorMsg("No records found");
		} else {
			udm.setModuleEnvironments(moduleEnvironmentMasters);
		}
		
	}
	
	public void getModules(UserDataModel udm) throws Exception {
		if(udm.getModules() == null || udm.getModules().size() == 0) {
			udm.setErrorMsg("Input to retrieve module details is incorrect");
			return;
		}
		
		Module envMaster = udm.getModules().get(0);
		if(envMaster != null && !StringUtils.isEmpty(envMaster.getModuleName())) { // Get user by module name
			envMaster = moduleMasterDaoImpl.findByModuleName(envMaster.getModuleName());
			if(envMaster == null) {
				udm.setErrorMsg("Module not found");
				return;
			}
			List<Module> environments = new ArrayList<Module>();
			environments.add(envMaster);
			udm.setModules(environments);
		} else {
			List<Module> users = moduleMasterDaoImpl.findAll();
			udm.setModules(users);
		}
		
	}
}
