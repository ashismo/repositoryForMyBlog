package com.org.coop.retail.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.coop.retail.servicehelper.TransactionsServiceHelperImpl;

@Service
public class TransactionsServiceImpl {

	private static final Logger log = Logger.getLogger(TransactionsServiceImpl.class); 
	
	@Autowired
	private TransactionsServiceHelperImpl transactionsServiceHelperImpl;
	
	public String getNextBillNumber(int branchId, String purpose, String user, Date actionDate) {
		return transactionsServiceHelperImpl.getNextBillNumber(branchId, purpose, user, actionDate);
	}
}
