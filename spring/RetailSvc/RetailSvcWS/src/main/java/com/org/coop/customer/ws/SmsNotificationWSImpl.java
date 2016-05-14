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

@RestController
@RequestMapping("/rest")
public class SmsNotificationWSImpl {

	private static final Logger log = Logger.getLogger(SmsNotificationWSImpl.class); 
	
	@Autowired
	private SmsNotificationServiceImpl smsNotificationServiceImpl;
	
	@RequestMapping(value = "/getSmsNotifications", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public UIModel getSmsNotifications(@RequestParam(value = "branchId",required = true,defaultValue = "0") Integer branchId,
								@RequestParam(value = "customerId",required = true,defaultValue = "0") Integer customerId,
								@RequestParam(value = "notificationId",required = true,defaultValue = "0") Integer notificationId,
								@RequestParam(value = "mobileNo",required = true,defaultValue = "") String mobileNo,
								@RequestParam(value = "pageNo",required = true,defaultValue = "1") Integer pageNo,
								@RequestParam(value = "recordsPerPage",required = true,defaultValue = "100000") Integer recordsPerPage,
								@DateTimeFormat(pattern="dd/MM/yyyy")@Param("startDate") Date startDate,
								@DateTimeFormat(pattern="dd/MM/yyyy")@Param("endDate") Date endDate) {
		return smsNotificationServiceImpl.getSmsNotifications(branchId, customerId, notificationId, mobileNo, startDate, endDate, pageNo, recordsPerPage);
	}
	
	@RequestMapping(value = "/saveSmsNotifications", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json", consumes="application/json")
	public UIModel saveSmsNotifications(@RequestBody UIModel uiModel) {
		return smsNotificationServiceImpl.saveSmsNotifications(uiModel);
	}
	
	@RequestMapping(value = "/deleteSmsNotifications", method = RequestMethod.DELETE, headers="Accept=application/json",produces="application/json", consumes="application/json")
	public UIModel deleteSmsNotifications(@RequestBody UIModel uiModel) {
		return smsNotificationServiceImpl.deleteSmsNotifications(uiModel);
	}
}
