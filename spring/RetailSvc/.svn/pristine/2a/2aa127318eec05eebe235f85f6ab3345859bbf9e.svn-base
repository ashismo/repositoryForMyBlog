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
import com.org.coop.retail.service.PaymentServiceImpl;
import com.org.coop.retail.service.RetailStockManagementServiceImpl;
import com.org.coop.retail.service.RetailTransactionManagementServiceImpl;

@RestController
@RequestMapping("/rest")
public class PaymentManagemenWSImpl {
	
	private static final Logger log = Logger.getLogger(PaymentManagemenWSImpl.class); 
	
	@Autowired
	private PaymentServiceImpl paymentServiceImpl;
	
	@RequestMapping(value = "/getPayment", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public UIModel getPayment(@RequestParam(value = "branchId",required = true,defaultValue = "0") Integer branchId,
							@RequestParam(value = "glTranId",required = true,defaultValue = "0") Integer glTranId,
							@DateTimeFormat(pattern="dd/MM/yyyy")@Param("startDate") Date startDate,
							@DateTimeFormat(pattern="dd/MM/yyyy")@Param("endDate") Date endDate,
							@RequestParam(value = "pageNo",required = true,defaultValue = "0") Integer pageNo,
							@RequestParam(value = "recordsPerPage",required = true,defaultValue = "0") Integer recordsPerPage) {
		
		UIModel uiModel =  paymentServiceImpl.getPayment(branchId, glTranId, startDate, endDate, pageNo, recordsPerPage);
		
		return uiModel;
	}
	
	@RequestMapping(value = "/makePayment", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public UIModel makePayment(@RequestBody UIModel uiModel) {
		uiModel = paymentServiceImpl.makePayment(uiModel);
		return uiModel;
	}
	
	@RequestMapping(value = "/deletePayment", method = RequestMethod.DELETE, headers="Accept=application/json",produces="application/json")
	public UIModel deletePayment(@RequestBody UIModel uiModel) {
		uiModel = paymentServiceImpl.deletePayment(uiModel);
		return uiModel;
	}
	
}
