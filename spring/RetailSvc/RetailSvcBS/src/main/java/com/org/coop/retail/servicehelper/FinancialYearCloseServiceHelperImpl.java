package com.org.coop.retail.servicehelper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.entities.FyClose;
import com.org.coop.retail.repositories.FyCloseRepository;

@Service
public class FinancialYearCloseServiceHelperImpl {

	private static final Logger log = Logger.getLogger(FinancialYearCloseServiceHelperImpl.class); 
	
	@Autowired
	private FyCloseRepository fyCloseRepository;
	
	
	@Transactional(value="retailTransactionManager")
	public boolean isFinancialYearClosed(int branchId, String financialYear) {
		FyClose fyClose = fyCloseRepository.isFinancialYearClosed(branchId, financialYear);
		byte isFyClosed = (fyClose == null) ? 0 :fyClose.getIsClosed();
		
		if(isFyClosed == 0) {
			return false;
		}
		return true;
	}
	
	@Transactional(value="retailTransactionManager")
	public UIModel saveFinancialYearData(UIModel uiModel) {
		
		return uiModel;
	}
	
}
