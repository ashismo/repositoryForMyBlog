package com.ashish.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ashish.beans.DebitCardBean;
import com.ashish.debit.entities.AccountDetail;
import com.ashish.debit.entities.UserDetail;
import com.ashish.debit.repositories.DebitCardAccountDetailsRepository;
import com.ashish.debit.repositories.DebitUserDetailRepository;

@Component
public class DebitCardManagementImpl implements DebitCardManagement {

	@Autowired
	private DebitUserDetailRepository debitUserDetailRepository;
	
	@Autowired
	private DebitCardAccountDetailsRepository debitCardAccountDetailsRepository;
	
	@Transactional(value="debitTransactionManager", propagation=Propagation.REQUIRED, readOnly=false)
	public void addDebitCardCustomerData(DebitCardBean dcBean) {
		//Add user details
		UserDetail user = addDebitCardUser(dcBean);
		
		// Add transaction details
		manageDebitCardAccountDetails(dcBean, user);
	}

	@Transactional(value="debitTransactionManager", propagation=Propagation.REQUIRED, readOnly=false)
	private UserDetail addDebitCardUser(DebitCardBean dcBean) {
		//Add user details
		UserDetail user = new UserDetail();
		user.setFirstName(dcBean.getFirstName());
		user.setLastName(dcBean.getLastName());
		user.setUserName(dcBean.getUserName());
		debitUserDetailRepository.save(user);
		
		List<UserDetail> ud = debitUserDetailRepository.findAll();
		System.out.println("------------->" + ud.size());
		
		return user;
	}

	@Transactional(value="debitTransactionManager", propagation=Propagation.REQUIRED, readOnly=false)
	private void manageDebitCardAccountDetails(DebitCardBean dcBean,
			UserDetail user) {
		// Add transaction details
		AccountDetail accountDetails = new AccountDetail();
		accountDetails.setCurrentBal(dcBean.getCurrentBal());
		accountDetails.setTransferAmt(dcBean.getTransferAmt());
		accountDetails.setNewBal(dcBean.getCurrentBal()-dcBean.getTransferAmt());
		accountDetails.setUserDetail(user);
		debitCardAccountDetailsRepository.save(accountDetails);
	}

	@Transactional(value="debitTransactionManager", propagation=Propagation.REQUIRED, readOnly=false)
	public void payCreditCardBill(DebitCardBean dcBean) {
		AccountDetail accDtls = debitCardAccountDetailsRepository.findOne(dcBean.getUserId());
		
		accDtls.setNewBal(accDtls.getNewBal() - dcBean.getTransferAmt());
		accDtls.setTransferAmt(dcBean.getTransferAmt());
		
		debitCardAccountDetailsRepository.save(accDtls);
	}
}
