package com.org.coop.customer.ws;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.service.RetailStockManagementServiceImpl;

@RestController
@RequestMapping("/rest")
public class RetailStockManagementWSImpl {
	
	private static final Logger log = Logger.getLogger(RetailStockManagementWSImpl.class); 
	
	@Autowired
	private RetailStockManagementServiceImpl retailStockManagementServiceImpl;
	
	@RequestMapping(value = "/getStockEntries", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public UIModel getStockEntries(@RequestParam(value = "vendorId",required = true,defaultValue = "0") Integer vendorId) {
		UIModel uiModel = new UIModel();
		try {
			uiModel = retailStockManagementServiceImpl.getStockEntries(vendorId);
		} catch (Exception e) {
			log.error("Error Retrieving stock entries", e);
			uiModel.setErrorMsg("Error Retrieving stock entries: " + vendorId);
		}
		return uiModel;
	}
	
	@RequestMapping(value = "/saveStockEntries", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public UIModel saveStockEntries(@RequestBody UIModel uiModel) {
		try {
			uiModel = retailStockManagementServiceImpl.saveStockEntries(uiModel);
		} catch (Exception e) {
			log.error("Error while adding stock entries", e);
			uiModel.setErrorMsg("Error while adding stock entries a material: " + uiModel.getBranchBean().getStockEntries().get(0).getMaterialId());
		}
		return uiModel;
	}
	
}
