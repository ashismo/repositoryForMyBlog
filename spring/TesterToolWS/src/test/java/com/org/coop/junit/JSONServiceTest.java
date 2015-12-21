package com.org.coop.junit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.org.coop.canonical.beans.AddressBean;
import com.org.coop.canonical.beans.BranchBean;
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.config.WebConfig;

@RunWith(JUnit4ClassRunner.class)
//@ContextConfiguration
public class JSONServiceTest {
	private static final Logger logger = Logger.getLogger(JSONServiceTest.class);
	
	@Test
	public void createNewBranchWithNewAddress() {
		UIModel ui = new UIModel();
		String date = "18-12-2015";
		BranchBean bb = new BranchBean();
		ui.setBranchBean(bb);
		bb.setBranchId(25);
		bb.setParentId(0);
		bb.setBankName("Test Bank");
		bb.setBranchName("Branch1");
		bb.setEmail1("ashismo@gmail.com");
		bb.setIfscCode("12345678");
		bb.setMicrCode("87654321");
		bb.setPhone1("9830625559");
		bb.setStartDate(new Date());
		bb.setCreateUser("ashish");
		
		List<AddressBean> addressBeanList = new ArrayList<AddressBean>();
		bb.setAddresses(addressBeanList);
		AddressBean address = new AddressBean();
//		addressBeanList.add(address);
//		address.setAddressId(26);
//		address.setAddressName("Test Address1");
//		address.setAddressLine1("Kalipur1");
//		address.setPin("712708");
//		address.setPhoneNo1("9830525559");
//		address.setDistId(1);
//		address.setStartDate(new Date());
//		address.setCreateUser("ashish");
		
		address = new AddressBean();
		addressBeanList.add(address);
		address.setAddressId(30);
		address.setAddressName("Test Address3");
		address.setAddressLine1("Naldighi17");
		address.setPin("712304");
		address.setDistId(1);
		address.setPhoneNo1("9830525559");
		address.setStartDate(new Date());
		address.setCreateUser("ashish");
		
		Gson gson = new Gson();
		String json = gson.toJson(ui);
		
		System.out.println(json);
		
	}
}
