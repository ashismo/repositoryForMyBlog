package com.org.coop.bs.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coop.org.exception.RetailException;
import com.org.coop.retail.entities.BranchMaster;
import com.org.coop.retail.entities.MaterialMaster;
import com.org.coop.retail.entities.StockEntry;
import com.org.coop.retail.repositories.RetailBranchMasterRepository;
import com.org.coop.retail.repositories.RetailMaterialMasterRepository;
import com.org.coop.retail.repositories.RetailStockEntryRepository;

@Service
public class CommonValidationUtils {
	
	private static final Logger log = Logger.getLogger(CommonValidationUtils.class); 
	
	@Autowired
	protected RetailMaterialMasterRepository retailMaterialMasterRepository;
	
	@Autowired
	protected RetailBranchMasterRepository retailBranchMasterRepository;
	
	@Autowired
	protected RetailStockEntryRepository retailStockEntryRepository;
	
	public MaterialMaster validateMaterial(int materialId) {
		MaterialMaster materialMaster = null;
		if(materialId > 0) {
			materialMaster = retailMaterialMasterRepository.findOne(materialId);
		}
		if(materialMaster == null || materialId == 0) {
			String errorMsg = "Material does not exists in our record for material Id: " + materialId;
			log.error(errorMsg);
			throw new RetailException(errorMsg, RetailBusinessConstants.EXCEPTION_RETAIL_STOCK_SALE);
		}
		return materialMaster;
	}
	
	public BranchMaster validateBranch(int branchId) {
		BranchMaster branch = null;
		if(branchId > 0) {
			branch = retailBranchMasterRepository.findOne(branchId);
		}
		if(branch == null || branchId == 0) {
			String errorMsg = "Branch does not exists in our record for branch Id: " + branchId;
			log.error(errorMsg);
			throw new RetailException(errorMsg, RetailBusinessConstants.EXCEPTION_RETAIL_STOCK_SALE);
		}
		return branch;
	}
	
	public StockEntry validateStock(int stockId) {
		StockEntry stockEntry = null;
		if(stockId > 0) {
			stockEntry = retailStockEntryRepository.findOne(stockId);
		}
		if(stockEntry == null || stockId == 0) {
			String errorMsg = "Stock does not exists in our record for stock Id: " + stockId;
			log.error(errorMsg);
			throw new RetailException(errorMsg, RetailBusinessConstants.EXCEPTION_RETAIL_STOCK_SALE);
		}
		
		return stockEntry;
	}
}
