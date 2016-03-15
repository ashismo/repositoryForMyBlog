package com.org.coop.retail.servicehelper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.coop.admin.service.BranchSetupServiceImpl;
import com.org.coop.canonical.beans.BranchBean;
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.bs.mapper.RetailBranchMappingImpl;
import com.org.coop.retail.entities.BranchMaster;
import com.org.coop.retail.repositories.RetailBranchMasterRepository;

@Service
public class MaterialSetupServiceHelperImpl {

	private static final Logger log = Logger.getLogger(MaterialSetupServiceHelperImpl.class); 
	
	@Autowired
	private RetailBranchMasterRepository branchMasterRepository;
	
	@Autowired
	private RetailBranchMappingImpl retailBranchMappingImpl;
	
	@Autowired
	private BranchSetupServiceImpl branchSetupServiceImpl;
	
	@Transactional(value="retailTransactionManager")
	public UIModel getMaterial(int materialId) {
		UIModel uiModel = new UIModel();
		// Check if the branch already exists
		BranchMaster branch = branchMasterRepository.findOne(materialId);
		if(branch == null) {
			uiModel.setErrorMsg("Branch does not exist for Branch Id: " + materialId);
			return uiModel;
		}
		BranchBean branchBean = new BranchBean();
		retailBranchMappingImpl.mapBean(branch, branchBean);
		uiModel.setBranchBean(branchBean);
		
		log.debug("Branch details has been retrieved from database for branchId: " + materialId);
		return uiModel;
	}
	
	@Transactional(value="retailTransactionManager")
	public UIModel saveMaterial(UIModel uiModel) {
		int branchId = uiModel.getBranchBean().getBranchId();
		// Check if the record exists in the master database
		uiModel = branchSetupServiceImpl.getBranch(branchId);
		if(uiModel.getErrorMsg() != null) {
			if(log.isDebugEnabled()) {
				log.debug("Error while retriving branch from master database");
			}
			return uiModel;
		}
		
		// Check if the branch already exists
		BranchMaster branch = branchMasterRepository.findOne(branchId);
		if(branch == null) {
			branch = new BranchMaster();
		}
		
		retailBranchMappingImpl.mapBean(uiModel.getBranchBean(), branch);
		
		branchMasterRepository.saveAndFlush(branch);
		
		log.debug("Branch details has been updated from the master database for branchId: " + branchId);
		return uiModel;
	}
	
}
