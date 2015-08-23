package com.ashish.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ashish.appConfig.AppConfig;
import com.ashish.beans.CreditCardBean;
import com.ashish.business.CreditCardManagement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
public class JPARepoTest {
	
	@Autowired
	private CreditCardManagement creditCardManagement;
	
	@Test
	public void addCreditcardUser() {
		CreditCardBean ccBean = new CreditCardBean();
		ccBean.setFirstName("Ashish");
		ccBean.setLastName("Mondal");
		ccBean.setUserName("ashismo");
		ccBean.setPaid(100);
		ccBean.setOutstanding(200);
		
		creditCardManagement.addCrCardCustomerData(ccBean);
	}
}
