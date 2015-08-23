package com.ashish.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.JpaRepositoriesAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ashish.beans.CreditCardBean;
import com.ashish.credit.entities.Transaction;
import com.ashish.credit.entities.UserDetail;
import com.ashish.credit.repositories.CreditCardTransactionRepository;
import com.ashish.credit.repositories.CreditUserDetailRepository;

@Component
public class CreditCardManagementImpl implements CreditCardManagement {

	@Autowired
	private CreditUserDetailRepository creditUserDetailRepository;
	
	@Autowired
	private CreditCardTransactionRepository creditCardTransactionRepository;
	
	@Transactional(value="creditTransactionManager", propagation=Propagation.REQUIRED, readOnly=false)
	public void addCrCardCustomerData(CreditCardBean ccBean) {
		//Add user details
		UserDetail user = addCreditCardUser(ccBean);
		
		// Add transaction details
		manageCreditCardTransaction(ccBean, user);
	}

	@Transactional(value="creditTransactionManager", propagation=Propagation.REQUIRED, readOnly=false)
	private UserDetail addCreditCardUser(CreditCardBean ccBean) {
		//Add user details
		UserDetail user = new UserDetail();
		user.setFirstName(ccBean.getFirstName());
		user.setLastName(ccBean.getLastName());
		user.setUserName(ccBean.getUserName());
		creditUserDetailRepository.save(user);
		creditUserDetailRepository.flush();
		
		List<UserDetail> ud = creditUserDetailRepository.findAll();
		System.out.println("xyz------------->" + ud.size());
		
		return user;
	}

	@Transactional(value="creditTransactionManager", propagation=Propagation.REQUIRED, readOnly=false)
	private void manageCreditCardTransaction(CreditCardBean ccBean,
			UserDetail user) {
		// Add transaction details
		Transaction tx = new Transaction();
		tx.setOutstanding(ccBean.getOutstanding());
		tx.setPaid(ccBean.getPaid());
		tx.setBalance(ccBean.getOutstanding()-ccBean.getPaid());
		tx.setUserDetail(user);
		creditCardTransactionRepository.save(tx);
	}
}
