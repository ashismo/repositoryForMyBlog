package com.org.coop.retail.bs.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.beans.CustomerNotificationBean;
import com.org.coop.canonical.beans.SmsNotificationBean;
import com.org.coop.retail.entities.BranchMaster;
import com.org.coop.retail.entities.Customer;
import com.org.coop.retail.entities.CustomerNotification;
import com.org.coop.retail.entities.SmsNotification;
import com.org.coop.retail.repositories.CustomerNotificationRepository;
import com.org.coop.retail.repositories.CustomerRepository;
import com.org.coop.retail.repositories.RetailBranchMasterRepository;
import com.org.coop.retail.repositories.SmsNotificationRepository;

@Component("retailNotificationCC")
public class RetailNotificationCC extends DozerConverter<Object, Object> implements MapperAware, CustomConverter {
	@Autowired
	private RetailBranchMasterRepository branchMasterRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerNotificationRepository customerNotificationRepository;
	
	@Autowired
	private SmsNotificationRepository smsNotificationRepository;
	
	public RetailNotificationCC() {
		super(Object.class, Object.class);
	}
	
	public RetailNotificationCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object convertFrom(Object src, Object dest) {
		if(src != null) {
			if("CUSTOMER_NOTIFICATION".equalsIgnoreCase(getParameter())) {
				CustomerNotificationBean customerNotificationBean = (CustomerNotificationBean) src;
				CustomerNotification customenrNotification = (CustomerNotification) dest;
				int notificationId = customerNotificationBean.getNotificationId();
				if(notificationId > 0) {
					customenrNotification = customerNotificationRepository.findOne(customerNotificationBean.getNotificationId());
				}
				BranchMaster branch = (notificationId > 0) ? customenrNotification.getBranchMaster() : branchMasterRepository.findOne(customerNotificationBean.getBranchId());
				Customer customer = (notificationId > 0) ? customenrNotification.getCustomer() : customerRepository.findOne(customerNotificationBean.getCustomerId());
				customenrNotification.setBranchMaster(branch);
				customenrNotification.setCustomer(customer);
			} else if("SMS_NOTIFICATION".equalsIgnoreCase(getParameter())) {
				SmsNotificationBean smsNotificationBean = (SmsNotificationBean) src;
				SmsNotification smsNotification = (SmsNotification) dest;
				
				int smsId = smsNotificationBean.getSmsId();
				if(smsId > 0) {
					smsNotification = smsNotificationRepository.findOne(smsNotificationBean.getSmsId());
				}
				BranchMaster branch = (smsId > 0) ? smsNotification.getBranchMaster() : branchMasterRepository.findOne(smsNotificationBean.getBranchId());
				Customer customer = (smsId > 0) ? smsNotification.getCustomer() : customerRepository.findOne(smsNotificationBean.getCustomerId());
				CustomerNotification customerNotification = (smsId > 0) ? smsNotification.getCustomerNotification() : customerNotificationRepository.findOne(smsNotificationBean.getNotificationId());
				smsNotification.setBranchMaster(branch);
				smsNotification.setCustomer(customer);
				smsNotification.setCustomerNotification(customerNotification);
			} 
		}
		return dest;
	}

	@Override
	public Object convertTo(Object src, Object dest) {
		return null;
	}
	
}