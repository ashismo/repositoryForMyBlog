package com.org.coop.admin.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.coop.bs.mapper.MasterDataMappingImpl;
import com.org.coop.canonical.beans.BranchBean;
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.society.data.admin.entities.BranchMaster;
import com.org.coop.society.data.admin.entities.User;
import com.org.coop.society.data.admin.repositories.BranchMasterRepository;
import com.org.coop.society.data.admin.repositories.UserAdminRepository;

@Service
public class CustomerAdminLoginServiceImpl implements CustomerAdminLoginService {
	private static final Logger log = Logger.getLogger(CustomerAdminLoginServiceImpl.class); 

	@Autowired
	private BranchMasterRepository branchMasterRepository;
	
	@Autowired
	private UserAdminRepository userAdminRepository;
	
	@Autowired
	private MasterDataMappingImpl branchMappingImpl;
	
	/**
	 * This method verifies if the user is authenticated user
	 * @param username
	 */
	@Transactional(value="adminTransactionManager")
	public boolean isUserAuthenticated(String username, String password) {
		if(username == null || password == null) {
			log.debug("Username or password is null: " + username);
			return false;
		}
		User user = userAdminRepository.findByUserName(username);
		if(user == null) {
			log.debug("User name doesn't exist in our record: " + username);
			return false;
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(passwordEncoder.matches(password, user.getUserCredential().getPassword())) {
			log.debug("User is authenticated: " + username);
			return true;
		}
		return false;
	}
	
	@Transactional(value="adminTransactionManager")
	public UIModel getBranchConfig(String userName) {
		UIModel uiModel = new UIModel();

		User user = userAdminRepository.findByUserName(userName);
		if(user == null) {
			uiModel.setErrorMsg("User, " + userName + " does not exists in the database");
			return uiModel;
		}
		int branchId = user.getBranchMaster().getBranchId();
		
		BranchMaster branch = branchMasterRepository.findOne(branchId);
		if(branch == null) {
			uiModel.setErrorMsg("Branch does not exist for Branch Id: " + branchId);
			return uiModel;
		}
		
		BranchBean branchBean = new BranchBean();
		branchMappingImpl.mapBean(branch, branchBean);
		
		uiModel.setBranchBean(branchBean);
		
		log.debug("Branch details has been retrieved from database for branchId: " + branchId);
		return uiModel;
	}
}
