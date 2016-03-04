package com.org.coop.retail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.coop.bs.mapper.MasterDataMappingImpl;
import com.org.coop.retail.data.entities.RetailData;

@Service
public class RetailMasterDataSetupServiceImpl {
	
	@Autowired
	private MasterDataMappingImpl masterData;
	
	public RetailData saveMasterData(RetailData retailData) {
		
		return retailData;
	}
}
