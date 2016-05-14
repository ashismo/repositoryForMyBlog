package com.org.coop.retail.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.servicehelper.SmsNotificationServiceHelperImpl;

@Service
public class SmsNotificationServiceImpl {

	private static final Logger log = Logger.getLogger(SmsNotificationServiceImpl.class); 
	
	@Autowired
	private SmsNotificationServiceHelperImpl smsNotificationServiceHelperImpl;
	
	public UIModel getSmsNotifications(int branchId, int customerId, int notificationId, String mobileNo, 
			Date startDate, Date endDate,int pageNo, int recordsPerPage) {
		return smsNotificationServiceHelperImpl.getSmsNotifications(branchId, customerId, notificationId, mobileNo, startDate, endDate, pageNo, recordsPerPage);
	}
	
	public UIModel saveSmsNotifications(UIModel uiModel) {
		return smsNotificationServiceHelperImpl.saveSmsNotifications(uiModel);
	}
	
	public UIModel deleteSmsNotifications(UIModel uiModel) {
		return smsNotificationServiceHelperImpl.deleteSmsNotifications(uiModel);
	}
}
