package com.org.coop.retail.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.servicehelper.RetailStockManagementServiceHelperImpl;

@Service
public class RetailStockManagementServiceImpl {

	private static final Logger log = Logger.getLogger(RetailStockManagementServiceImpl.class); 
	
	@Autowired
	private RetailStockManagementServiceHelperImpl retailStockManagementServiceHelperImpl;
	
	
	public UIModel getStockEntries(int vendorId) {
		return retailStockManagementServiceHelperImpl.getStockEntries(vendorId);
	}
	
	public UIModel saveStockEntries(UIModel uiModel) {
		uiModel = retailStockManagementServiceHelperImpl.saveStockEntries(uiModel);
		if(uiModel.getErrorMsg() != null) {
			return uiModel;
		}
		int vendorId = uiModel.getBranchBean().getRetailVendors().get(0).getVendorId();
		return retailStockManagementServiceHelperImpl.getStockEntries(vendorId);
	}
	
}
