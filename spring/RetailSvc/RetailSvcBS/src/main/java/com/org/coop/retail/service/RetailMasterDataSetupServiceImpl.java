package com.org.coop.retail.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.servicehelper.RetailMasterDataSetupServiceHelperImpl;

@Service
public class RetailMasterDataSetupServiceImpl {

	private static final Logger log = Logger.getLogger(RetailMasterDataSetupServiceImpl.class); 
	
	
	@Autowired
	private RetailMasterDataSetupServiceHelperImpl retailMasterDataSetupServiceHelperImpl;
	
	
	public UIModel getRetailMasterData(int rateChartId, int materialId) {
		return retailMasterDataSetupServiceHelperImpl.getRetailMasterData(rateChartId, materialId);
	}
	
	public UIModel saveRetailMasterData(UIModel uiModel) {
		uiModel = retailMasterDataSetupServiceHelperImpl.saveRetailMasterData(uiModel);
		return uiModel;
	}
	
	public UIModel saveGlLedgers(UIModel uiModel) {
		uiModel = retailMasterDataSetupServiceHelperImpl.saveGlLedgers(uiModel);
		return uiModel;
	}
	
	
	
}
