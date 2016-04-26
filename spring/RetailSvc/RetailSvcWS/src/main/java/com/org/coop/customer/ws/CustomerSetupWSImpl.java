package com.org.coop.customer.ws;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.service.CustomerSetupServiceImpl;

@RestController
@RequestMapping("/rest")
public class CustomerSetupWSImpl {

	private static final Logger log = Logger.getLogger(CustomerSetupWSImpl.class); 
	
	@Autowired
	private CustomerSetupServiceImpl customerSetupServiceImpl;
	
	@RequestMapping(value = "/getCustomerAccounts", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public UIModel getCustomerAccounts(@RequestParam(value = "branchId",required = true,defaultValue = "0") Integer branchId,
								@RequestParam(value = "customerId",required = true,defaultValue = "0") Integer customerId,
								@RequestParam(value = "accountId",required = true,defaultValue = "0") Integer accountId,
								@RequestParam(value = "panNo",required = true,defaultValue = "") String panNo,
								@RequestParam(value = "aadharNo",required = true,defaultValue = "") String aadharNo,
								@RequestParam(value = "mobileNo",required = true,defaultValue = "") String mobileNo,
								@RequestParam(value = "pageNo",required = true,defaultValue = "1") Integer pageNo,
								@RequestParam(value = "recordsPerPage",required = true,defaultValue = "100000") Integer recordsPerPage,
								@DateTimeFormat(pattern="dd/MM/yyyy")@Param("startDate") Date startDate,
								@DateTimeFormat(pattern="dd/MM/yyyy")@Param("endDate") Date endDate) {
		return customerSetupServiceImpl.getCustomerAccounts(branchId, customerId, accountId, panNo, aadharNo, mobileNo, startDate, endDate, pageNo, recordsPerPage);
	}
	
	@RequestMapping(value = "/saveCustomerAccounts", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json", consumes="application/json")
	public UIModel saveCustomerAccounts(@RequestBody UIModel uiModel) {
		return customerSetupServiceImpl.saveCustomerAccounts(uiModel);
	}
	
	@RequestMapping(value = "/deleteCustomerAccounts", method = RequestMethod.DELETE, headers="Accept=application/json",produces="application/json", consumes="application/json")
	public UIModel deleteCustomerAccounts(@RequestBody UIModel uiModel) {
		return customerSetupServiceImpl.deleteCustomerAccounts(uiModel);
	}
}
