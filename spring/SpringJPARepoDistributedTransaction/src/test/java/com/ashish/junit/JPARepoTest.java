package com.ashish.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ashish.appConfig.AppConfig;
import com.ashish.beans.CreditCardBean;
import com.ashish.beans.DebitCardBean;
import com.ashish.business.CreditCardManagement;
import com.ashish.business.DebitCardManagement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
public class JPARepoTest {
	
	@Autowired
	private CreditCardManagement creditCardManagement;
	
	@Autowired
	private DebitCardManagement debitCardManagement;
	
//	@Test
	public void addCreditcardUser() {
		CreditCardBean ccBean = new CreditCardBean();
		ccBean.setFirstName("Ashish");
		ccBean.setLastName("M");
		ccBean.setUserName("ashismo");
		ccBean.setPaid(0);
		ccBean.setOutstanding(200);
		
		creditCardManagement.addCrCardCustomerData(ccBean);
	}
	
//	@Test
	public void addDebitcardUser() {
		DebitCardBean dcBean = new DebitCardBean();
		dcBean.setFirstName("Ashish");
		dcBean.setLastName("Mondal");
		dcBean.setUserName("ujanmo");
		dcBean.setCurrentBal(500);
		dcBean.setTransferAmt(0);
		
		debitCardManagement.addDebitCardCustomerData(dcBean);
	}
	
	@Test
	public void payCreditCardBill() {
		int PAID_AMT = 10;
		CreditCardBean ccBean = new CreditCardBean();
		ccBean.setUserId(42);
		ccBean.setPaid(PAID_AMT);
		
		DebitCardBean dcBean = new DebitCardBean();
		dcBean.setUserId(14);
		dcBean.setTransferAmt(PAID_AMT);
		
		creditCardManagement.payCreditCardBill(ccBean);
		debitCardManagement.payCreditCardBill(dcBean);
	}
}
