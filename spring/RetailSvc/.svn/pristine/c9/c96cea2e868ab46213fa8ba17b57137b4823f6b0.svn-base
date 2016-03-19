package com.org.coop.customer.ws;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.service.RetailRateChartSetupServiceImpl;
import com.org.coop.retail.service.RetailVendorSetupServiceImpl;

@RestController
@RequestMapping("/rest")
public class RetailRateChartSetupWSImpl {
	
	private static final Logger log = Logger.getLogger(RetailRateChartSetupWSImpl.class); 
	
	@Autowired
	private RetailRateChartSetupServiceImpl retailRateChartSetupServiceImpl;
	
	@RequestMapping(value = "/getRateChart", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public UIModel getRateChart(@RequestParam(value = "rateChartId",required = true,defaultValue = "0") Integer rateChartId,
								@RequestParam(value = "materialId",required = true,defaultValue = "0") Integer materialId) {
		UIModel uiModel = new UIModel();
		try {
			uiModel = retailRateChartSetupServiceImpl.getRateChart(rateChartId, materialId);
		} catch (Exception e) {
			log.error("Error Retrieving Retail rate chart by rate chart Id", e);
			uiModel.setErrorMsg("Error Retrieving Retail rate chart by rate chart Id: " + rateChartId);
		}
		return uiModel;
	}
	
	@RequestMapping(value = "/saveRateChart", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public UIModel saveRateChart(@RequestBody UIModel uiModel) {
		try {
			uiModel = retailRateChartSetupServiceImpl.saveRateChart(uiModel);
		} catch (Exception e) {
			log.error("Error while adding retail rate chart", e);
			uiModel.setErrorMsg("Error while adding retail rate chart for a branch: " + uiModel.getBranchBean().getBranchId());
		}
		return uiModel;
	}
	
}
