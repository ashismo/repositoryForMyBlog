package com.org.coop.retail.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.servicehelper.LedgerCodeSetupServiceHelperImpl;

@Service
public class LedgerCodeSetupServiceImpl {

	private static final Logger log = Logger.getLogger(LedgerCodeSetupServiceImpl.class); 
	
	@Autowired
	private LedgerCodeSetupServiceHelperImpl retailLedgerCodeSetupServiceHelperImpl;
	
	public UIModel getRetailLedgerCode(int vendorId, int materialGrpId, int pageNo, int recordsPerPage) {
		return retailLedgerCodeSetupServiceHelperImpl.getRetailLedgerCode(vendorId,materialGrpId, pageNo, recordsPerPage);
	}
	
	public UIModel saveRetailLedgerCode(UIModel uiModel) {
		uiModel = retailLedgerCodeSetupServiceHelperImpl.saveRetailLedgerCode(uiModel);
		if(uiModel.getErrorMsg() != null) {
			return uiModel;
		}
		return retailLedgerCodeSetupServiceHelperImpl.getRetailLedgerCode(0, 0, 1, 100000);
	}
	
	public UIModel deleteRetailLedgerCode(UIModel uiModel) {
		uiModel = retailLedgerCodeSetupServiceHelperImpl.deleteVendor(uiModel);
		if(uiModel.getErrorMsg() != null) {
			return uiModel;
		}
		int vendorId = uiModel.getBranchBean().getRetailVendors().get(0).getVendorId();
		return retailLedgerCodeSetupServiceHelperImpl.getRetailLedgerCode(0, 0, 1, 100000);
	}

	public UIModel getPaymentLedgerCode() {
		return retailLedgerCodeSetupServiceHelperImpl.getPaymentLedgerCode();
	}
	
	public UIModel savePaymentLedgerCode(UIModel uiModel) {
		uiModel = retailLedgerCodeSetupServiceHelperImpl.savePaymentLedgerCode(uiModel);
		if(uiModel.getErrorMsg() != null) {
			return uiModel;
		}
		return retailLedgerCodeSetupServiceHelperImpl.getPaymentLedgerCode();
	}
}
