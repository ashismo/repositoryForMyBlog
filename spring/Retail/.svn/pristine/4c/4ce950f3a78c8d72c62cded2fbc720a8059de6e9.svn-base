package com.org.coop.retail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.coop.retail.data.entities.RetailData;
import com.org.coop.retail.servicehelper.RetailMasterDataSetupServiceHelperImpl;

@Service
public class RetailMasterDataSetupServiceImpl {
	
	@Autowired
	private RetailMasterDataSetupServiceHelperImpl retailMasterDataSetupServiceHelperImpl;
	
	public RetailData saveMasterData(RetailData retailData) {
		return retailMasterDataSetupServiceHelperImpl.saveMasterData(retailData);
	}
	
	public RetailData getMasterData(RetailData retailData) {
		return retailMasterDataSetupServiceHelperImpl.getMasterData(retailData);
	}
}
