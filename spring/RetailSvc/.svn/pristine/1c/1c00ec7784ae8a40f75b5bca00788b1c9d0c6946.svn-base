package com.org.coop.customer.ws;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.service.RetailStockManagementServiceImpl;
import com.org.coop.retail.service.RetailTransactionManagementServiceImpl;

@RestController
@RequestMapping("/rest")
public class RetailTransactionManagementWSImpl {
	
	private static final Logger log = Logger.getLogger(RetailTransactionManagementWSImpl.class); 
	
	@Autowired
	private RetailTransactionManagementServiceImpl retailTransactionManagementServiceImpl;
	
	@RequestMapping(value = "/getStockTransaction", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public UIModel getStockTransaction(@RequestParam(value = "branchId",required = true,defaultValue = "0") Integer branchId,
							@RequestParam(value = "customerId",required = true,defaultValue = "0") Integer customerId,
							@RequestParam(value = "tranId",required = true,defaultValue = "0") Integer tranId,
							@RequestParam(value = "tranNo",required = true,defaultValue = "0") String tranNo,
							@RequestParam(value = "pageNo",required = true,defaultValue = "0") Integer pageNo,
							@RequestParam(value = "recordsPerPage",required = true,defaultValue = "0") Integer recordsPerPage,
							@DateTimeFormat(pattern="dd/MM/yyyy")@Param("startDate") Date startDate,
							@DateTimeFormat(pattern="dd/MM/yyyy")@Param("endDate") Date endDate) {
		UIModel uiModel = new UIModel();
		try {
			uiModel = retailTransactionManagementServiceImpl.getStockTransaction(branchId, customerId, tranId, tranNo, pageNo, recordsPerPage, startDate, endDate);
		} catch (Exception e) {
			log.error("Error Retrieving stock Transactions", e);
			uiModel.setErrorMsg("Error Retrieving Transactions: ");
		}
		return uiModel;
	}
	
	@RequestMapping(value = "/saveStockTransaction", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public UIModel saveStockTransaction(@RequestBody UIModel uiModel) {
		try {
			uiModel = retailTransactionManagementServiceImpl.saveStockTransaction(uiModel);
		} catch (Exception e) {
			log.error("Error while saving Transaction", e);
			uiModel.setErrorMsg("Error while saving stock Transaction: ");
		}
		return uiModel;
	}
	
	@RequestMapping(value = "/deleteStockTransaction", method = RequestMethod.DELETE, headers="Accept=application/json",produces="application/json")
	public UIModel deleteStockTransaction(@RequestBody UIModel uiModel) {
		try {
			uiModel = retailTransactionManagementServiceImpl.deleteStockTransaction(uiModel);
		} catch (Exception e) {
			log.error("Error while deleting stock entries", e);
			uiModel.setErrorMsg("Error while deleting stock entries a material: " + uiModel.getBranchBean().getStockEntries().get(0).getMaterialId());
		}
		return uiModel;
	}
	
}
