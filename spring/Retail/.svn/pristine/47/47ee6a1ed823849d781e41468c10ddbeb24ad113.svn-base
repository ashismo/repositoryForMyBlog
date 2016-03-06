package com.org.coop.customer.ws;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.coop.retail.data.entities.RetailData;
import com.org.coop.retail.service.RetailUtilServiceImpl;

@RestController
@RequestMapping("/rest")
public class RetailUtilServiceWSImpl {
	
	private static final Logger log = Logger.getLogger(RetailUtilServiceWSImpl.class); 
	
	@Autowired
	private RetailUtilServiceImpl retailUtilServiceImpl;
	
	@RequestMapping(value = "/sequence", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public RetailData getBranch(@RequestParam(value = "document",required = true,defaultValue = "0") String document) {
		RetailData retailData = new RetailData();
		try {
			retailData = retailUtilServiceImpl.getNextSequence(document);
		} catch (Exception e) {
			log.error("Error Retrieving sequence", e);
			retailData.setErrorMsg("Error Retrieving sequence for document: " + document);
		}
		return retailData;
	}
	
}
