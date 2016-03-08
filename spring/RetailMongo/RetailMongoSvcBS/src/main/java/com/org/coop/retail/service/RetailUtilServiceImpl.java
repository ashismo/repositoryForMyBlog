package com.org.coop.retail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.coop.retail.data.entities.RetailData;
import com.org.coop.retail.servicehelper.RetailUtilServiceHelperImpl;

@Service
public class RetailUtilServiceImpl {
	@Autowired 
	private RetailUtilServiceHelperImpl retailUtilServiceHelperImpl;
	   
	  public RetailData getNextSequence(String collectionName) {
		  return retailUtilServiceHelperImpl.getNextSequence(collectionName);
	  }
}
