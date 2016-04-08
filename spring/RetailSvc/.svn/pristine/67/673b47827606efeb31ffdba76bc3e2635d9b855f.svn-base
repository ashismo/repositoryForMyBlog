package com.org.coop.retail.servicehelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.coop.canonical.beans.BranchBean;
import com.org.coop.canonical.beans.RetailStockEntryBean;
import com.org.coop.canonical.beans.RetailStockReturnBean;
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.bs.mapper.RetailStockEntryMappingImpl;
import com.org.coop.retail.bs.mapper.RetailStockReturnMappingImpl;
import com.org.coop.retail.entities.StockEntry;
import com.org.coop.retail.entities.StockReturn;
import com.org.coop.retail.repositories.RetailStockEntryRepository;
import com.org.coop.retail.repositories.RetailStockReturnRepository;

@Service
public class RetailTransactionManagementServiceHelperImpl {

	private static final Logger log = Logger.getLogger(RetailTransactionManagementServiceHelperImpl.class); 
	
	@Autowired
	private RetailStockEntryRepository retailStockEntryRepository;
	
	@Autowired
	private RetailStockEntryMappingImpl retailStockEntryMappingImpl;
	
	@Autowired
	private RetailStockReturnRepository retailStockReturnRepository;
	
	@Autowired
	private RetailStockReturnMappingImpl retailStockReturnMappingImpl;
	
	@Transactional(value="retailTransactionManager")
	public UIModel getStockTransaction(Integer branchId, Integer customerId, Integer tranId,
			String tranNo, Integer pageNo, Integer recordsPerPage, Date startDate, Date endDate) {
		UIModel uiModel = new UIModel();
		List<StockEntry> stocks = null;
		
		if(pageNo == 0) pageNo = 1;
		if(recordsPerPage == 0) recordsPerPage = 1000000;
		
		PageRequest pageRequest = new PageRequest(pageNo - 1, recordsPerPage, Sort.Direction.DESC, "entryDate");
		int recordCount = 0;
		return uiModel;
	}
	
	@Transactional(value="retailTransactionManager")
	public UIModel saveStockTransaction(UIModel uiModel) {
		if(uiModel.getBranchBean().getStockEntries() != null && uiModel.getBranchBean().getStockEntries().size() > 0 ) {
			
		}		
		return uiModel;
	}
	
	@Transactional(value="retailTransactionManager")
	public UIModel deleteStockEntries(UIModel uiModel) {
		if(uiModel.getBranchBean().getStockEntries() != null && uiModel.getBranchBean().getStockEntries().size() > 0 ) {
			
		}		
		return uiModel;
	}
	
}
