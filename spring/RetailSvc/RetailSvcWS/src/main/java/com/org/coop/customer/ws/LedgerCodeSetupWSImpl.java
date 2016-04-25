package com.org.coop.customer.ws;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.service.LedgerCodeSetupServiceImpl;
import com.org.coop.retail.service.RetailVendorSetupServiceImpl;

@RestController
@RequestMapping("/rest")
public class LedgerCodeSetupWSImpl {
	
	private static final Logger log = Logger.getLogger(LedgerCodeSetupWSImpl.class); 
	
	@Autowired
	private LedgerCodeSetupServiceImpl retailLedgerCodeSetupServiceImpl;
	
	@RequestMapping(value = "/getRetailLedgerCode", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public UIModel getRetailLedgerCode(@RequestParam(value = "vendorId",required = true,defaultValue = "0") Integer vendorId,
							@RequestParam(value = "materialGrpId",required = true,defaultValue = "0") Integer materialGrpId) {
		UIModel uiModel = new UIModel();
		int pageNo = 1;
		int recordsPerPage = 10000;
		uiModel = retailLedgerCodeSetupServiceImpl.getRetailLedgerCode(vendorId, materialGrpId, pageNo, recordsPerPage);
		return uiModel;
	}
	
	@RequestMapping(value = "/saveRetailLedgerCode", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public UIModel saveRetailLedgerCode(@RequestBody UIModel uiModel) {
		uiModel = retailLedgerCodeSetupServiceImpl.saveRetailLedgerCode(uiModel);
		return uiModel;
	}
	
	@RequestMapping(value = "/deleteRetailLedgerCode", method = RequestMethod.DELETE, headers="Accept=application/json",produces="application/json")
	public UIModel deleteRetailLedgerCode(@RequestBody UIModel uiModel) {
		uiModel = retailLedgerCodeSetupServiceImpl.deleteRetailLedgerCode(uiModel);
		return uiModel;
	}
	
	@RequestMapping(value = "/getPaymentLedgerCode", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public UIModel getPaymentLedgerCode(@RequestParam(value = "vendorId",required = true,defaultValue = "0") Integer vendorId,
							@RequestParam(value = "materialGrpId",required = true,defaultValue = "0") Integer materialGrpId) {
		UIModel uiModel = new UIModel();
		uiModel = retailLedgerCodeSetupServiceImpl.getPaymentLedgerCode();
		return uiModel;
	}
	
	@RequestMapping(value = "/savePaymentLedgerCode", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public UIModel savePaymentLedgerCode(@RequestBody UIModel uiModel) {
		uiModel = retailLedgerCodeSetupServiceImpl.savePaymentLedgerCode(uiModel);
		return uiModel;
	}
	
}
