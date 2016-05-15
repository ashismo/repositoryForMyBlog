/**
 * This class expose a service to get next available bill number during retail sale
 */

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
import com.org.coop.retail.service.SmsNotificationServiceImpl;
import com.org.coop.retail.service.TransactionsServiceImpl;

@RestController
@RequestMapping("/rest")
public class TransactionWSImpl {

	private static final Logger log = Logger.getLogger(TransactionWSImpl.class); 
	
	@Autowired
	private TransactionsServiceImpl transactionsServiceImpl;
	
	@RequestMapping(value = "/getNextBillNumber", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public String getNextBillNumber(@RequestParam(value = "branchId",required = true,defaultValue = "0") Integer branchId,
								@RequestParam(value = "user",required = true,defaultValue = "") String user,
								@RequestParam(value = "purpose",required = true,defaultValue = "") String purpose,
								@DateTimeFormat(pattern="dd/MM/yyyy")@Param("actionDate") Date actionDate) {
		return transactionsServiceImpl.getNextBillNumber(branchId, purpose, user, actionDate);
	}
	
}
