package com.org.coop.customer.ws;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.service.RetailVendorSetupServiceImpl;

@RestController
@RequestMapping("/rest")
public class RetailVendorSetupWSImpl {
	
	private static final Logger log = Logger.getLogger(RetailVendorSetupWSImpl.class); 
	
	@Autowired
	private RetailVendorSetupServiceImpl retailVendorSetupServiceImpl;
	
	@RequestMapping(value = "/getVendor", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public UIModel getVendor(@RequestParam(value = "vendorId",required = true,defaultValue = "0") Integer vendorId) {
		UIModel uiModel = new UIModel();
		try {
			uiModel = retailVendorSetupServiceImpl.getVendor(vendorId);
		} catch (Exception e) {
			log.error("Error Retrieving Vendor by vendor Id", e);
			uiModel.setErrorMsg("Error Retrieving vendor by vendor Id: " + vendorId);
		}
		return uiModel;
	}
	
	@RequestMapping(value = "/saveVendor", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public UIModel saveVendor(@RequestBody UIModel uiModel) {
		try {
			uiModel = retailVendorSetupServiceImpl.saveVendor(uiModel);
		} catch (Exception e) {
			log.error("Error while adding vendor", e);
			uiModel.setErrorMsg("Error while adding vendor for a branch: " + uiModel.getBranchBean().getBranchId());
		}
		return uiModel;
	}
	
}
