package com.org.coop.retail.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.servicehelper.RetailRateChartSetupServiceHelperImpl;
import com.org.coop.retail.servicehelper.RetailVendorSetupServiceHelperImpl;

@Service
public class RetailRateChartSetupServiceImpl {

	private static final Logger log = Logger.getLogger(RetailRateChartSetupServiceImpl.class); 
	
	@Autowired
	private RetailRateChartSetupServiceHelperImpl retailVendorSetupServiceHelperImpl;
	
	
	public UIModel getRateChart(int rateChartId, int materialId) {
		return retailVendorSetupServiceHelperImpl.getRateChart(rateChartId, materialId);
	}
	
	public UIModel saveRateChart(UIModel uiModel) {
		uiModel = retailVendorSetupServiceHelperImpl.saveRateChart(uiModel);
		if(uiModel.getErrorMsg() != null) {
			return uiModel;
		}
		int rateChartId = uiModel.getBranchBean().getRetailRateCharts().get(0).getRateId();
		return retailVendorSetupServiceHelperImpl.getRateChart(rateChartId, uiModel.getBranchBean().getRetailRateCharts().get(0).getMaterialId());
	}
	
}
