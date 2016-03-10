package com.org.coop.retail.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.servicehelper.RetailBranchSetupServiceHelperImpl;

@Service
public class RetailBranchSetupServiceImpl {

	private static final Logger log = Logger.getLogger(RetailBranchSetupServiceImpl.class); 
	
	@Autowired
	private RetailBranchSetupServiceHelperImpl branchSetupServiceHelperImpl;
	
	
	public UIModel getBranch(int branchId) {
		return branchSetupServiceHelperImpl.getBranch(branchId);
	}
	
}
