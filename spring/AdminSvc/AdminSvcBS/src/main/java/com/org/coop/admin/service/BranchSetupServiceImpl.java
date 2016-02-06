package com.org.coop.admin.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.coop.admin.servicehelper.BranchSetupServiceHelperImpl;
import com.org.coop.canonical.beans.UIModel;

@Service
public class BranchSetupServiceImpl {

	private static final Logger log = Logger.getLogger(BranchSetupServiceImpl.class); 
	
	@Autowired
	private BranchSetupServiceHelperImpl branchSetupServiceHelperImpl;
	
	
	public UIModel addOrUpdateBranch(UIModel uiModel) {
		return branchSetupServiceHelperImpl.addOrUpdateBranch(uiModel);
	}
	
	public UIModel getBranch(int branchId) {
		return branchSetupServiceHelperImpl.getBranch(branchId);
	}
}
