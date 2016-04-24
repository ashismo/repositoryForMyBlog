package com.org.coop.customer.ws;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.service.RetailMasterDataSetupServiceImpl;
import com.org.coop.retail.service.RetailVendorSetupServiceImpl;

@RestController
@RequestMapping("/rest")
public class RetailMasterDataSetupWSImpl {
	
	private static final Logger log = Logger.getLogger(RetailMasterDataSetupWSImpl.class); 
	
	@Autowired
	private RetailVendorSetupServiceImpl retailVendorSetupServiceImpl;
	
	@Autowired
	private RetailMasterDataSetupServiceImpl retailMasterDataSetupServiceImpl;
	
	@RequestMapping(value = "/getRetailMasterData", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public UIModel getRetailMasterData(@RequestParam(value = "vendorId",required = true,defaultValue = "0") Integer vendorId) {
		UIModel uiModel = new UIModel();
		uiModel = retailMasterDataSetupServiceImpl.getRetailMasterData(vendorId, vendorId);
		return uiModel;
	}
	
	@RequestMapping(value = "/saveRetailMasterData", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public UIModel saveRetailMasterData(@RequestBody UIModel uiModel) {
		uiModel = retailMasterDataSetupServiceImpl.saveRetailMasterData(uiModel);
		return uiModel;
	}
	
	@RequestMapping(value = "/deleteRetailMasterData", method = RequestMethod.DELETE, headers="Accept=application/json",produces="application/json")
	public UIModel deleteRetailMasterData(@RequestBody UIModel uiModel) {
		try {
			uiModel = retailVendorSetupServiceImpl.deleteVendor(uiModel);
		} catch (Exception e) {
			log.error("Error while deleting vendor", e);
			uiModel.setErrorMsg("Error while deleting vendor for a branch: " + uiModel.getBranchBean().getBranchId());
		}
		return uiModel;
	}
	
	@RequestMapping(value = "/saveGlLedgers", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public UIModel saveGlLedgers(@RequestBody UIModel uiModel) {
		uiModel = retailMasterDataSetupServiceImpl.saveGlLedgers(uiModel);
		return uiModel;
	}
	
}
