package com.org.coop.customer.ws;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.service.FinancialYearCloseServiceImpl;

@Service
public class FinancialYearCloseWSImpl {

	private static final Logger log = Logger.getLogger(FinancialYearCloseWSImpl.class); 
	
	@Autowired
	private FinancialYearCloseServiceImpl financialYearCloseServiceImpl;
	
	@RequestMapping(value = "/saveFinancialYearData", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public UIModel saveFinancialYearData(@RequestBody UIModel uiModel) {
		try {
			uiModel = financialYearCloseServiceImpl.saveFinancialYearData(uiModel);
		} catch (Exception e) {
			log.error("Error while saving financial year data", e);
			uiModel.setErrorMsg("Error while saving financial year data for branch Id: " + uiModel.getBranchBean().getBranchId());
		}
		return uiModel;
	}
}
