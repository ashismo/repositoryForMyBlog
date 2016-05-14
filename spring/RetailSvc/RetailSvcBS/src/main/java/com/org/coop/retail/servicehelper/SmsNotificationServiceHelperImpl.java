package com.org.coop.retail.servicehelper;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coop.org.exception.RetailException;
import com.org.coop.bs.util.CommonValidationUtils;
import com.org.coop.bs.util.RetailBusinessConstants;
import com.org.coop.canonical.beans.SmsNotificationBean;
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.bs.mapper.SmsNotificationMappingImpl;
import com.org.coop.retail.entities.SmsNotification;
import com.org.coop.retail.repositories.RetailBranchMasterRepository;
import com.org.coop.retail.repositories.SmsNotificationRepository;

@Service
public class SmsNotificationServiceHelperImpl {

	private static final Logger log = Logger.getLogger(SmsNotificationServiceHelperImpl.class); 
	
	@Autowired
	private RetailBranchMasterRepository branchMasterRepository;
	
	@Autowired
	private SmsNotificationMappingImpl smsNotificationMappingImpl;
	
	@Autowired
	private SmsNotificationRepository smsNotificationRepository;
	
	@Autowired
	private CommonValidationUtils commonValidationUtils;
	
	@Transactional(value="retailTransactionManager")
	public UIModel getSmsNotifications(int branchId, int customerId, int notificationId, String mobileNo, 
							Date startDate, Date endDate,int pageNo, int recordsPerPage) {
		UIModel uiModel = new UIModel();
		return uiModel;
	}
	
	@Transactional(value="retailTransactionManager")
	public UIModel saveSmsNotifications(UIModel uiModel) {
		if(uiModel.getBranchBean().getCustomers() != null && uiModel.getBranchBean().getCustomers().size() > 0 &&
				uiModel.getBranchBean().getCustomers().get(0).getSmsNotifications() != null && uiModel.getBranchBean().getCustomers().get(0).getSmsNotifications().size() > 0) {
			for(SmsNotificationBean smsNotificationBean : uiModel.getBranchBean().getCustomers().get(0).getSmsNotifications()) {
				SmsNotification smsNotification = new SmsNotification();
				smsNotificationMappingImpl.mapSmsNotificationBean(smsNotificationBean, smsNotification);
				
				validateInputForSMSNotification(smsNotificationBean, smsNotification);
				
				smsNotificationRepository.saveAndFlush(smsNotification);
			}
		}
		return uiModel;
	}

	private void validateInputForSMSNotification(SmsNotificationBean smsNotificationBean,
			SmsNotification smsNotification) {
		//**************************************
		// VALIDATION 1: Mobile number must not be blank
		//*************************************
		String mobile = smsNotificationBean.getMobileNo();
		if(StringUtils.isBlank(mobile)) {
			mobile = smsNotification.getCustomer().getMobile1();
			if(StringUtils.isBlank(mobile)) {
				mobile = smsNotification.getCustomer().getMobile2();
			}
		}
		
		if(StringUtils.isBlank(mobile)) {
			String errorMsg = "Mobile number can not be null or blank";
			log.error(errorMsg);
			throw new RetailException(errorMsg,RetailBusinessConstants.EXCEPTION_RETAIL_NOTIFICATION);
		}
		
		commonValidationUtils.validatePhoneNumber(mobile);
		
		smsNotification.setMobileNo(mobile);
		
		//**************************************
		// VALIDATION 2: SMS Purpose must not be blank
		//*************************************
		String smsPurpose = smsNotificationBean.getSmsPurpose();
		if(StringUtils.isBlank(smsPurpose)) {
			smsPurpose = smsNotification.getCustomerNotification().getNotificationType();
		}
		
		if(StringUtils.isBlank(smsPurpose)) {
			String errorMsg = "SMS purpose can not be null or blank";
			log.error(errorMsg);
			throw new RetailException(errorMsg,RetailBusinessConstants.EXCEPTION_RETAIL_NOTIFICATION);
		}
		smsNotification.setSmsPurpose(smsPurpose);
		
		//**************************************
		// VALIDATION 3: SMS Subject must not be blank
		//*************************************
		String smsSubject = smsNotificationBean.getSmsSubject();
		if(StringUtils.isBlank(smsSubject)) {
			smsSubject = smsNotification.getCustomerNotification().getNotificationDetail();
			smsSubject = "Your " + smsSubject + " is expiring on " + smsNotification.getCustomerNotification().getEndDate();
		}
		
		if(StringUtils.isBlank(smsSubject)) {
			String errorMsg = "SMS Subject can not be null or blank";
			log.error(errorMsg);
			throw new RetailException(errorMsg,RetailBusinessConstants.EXCEPTION_RETAIL_NOTIFICATION);
		}
		smsNotification.setSmsSubject(smsSubject);
	}
	
	@Transactional(value="retailTransactionManager")
	public UIModel deleteSmsNotifications(UIModel uiModel) {
		return uiModel;
	}
}
