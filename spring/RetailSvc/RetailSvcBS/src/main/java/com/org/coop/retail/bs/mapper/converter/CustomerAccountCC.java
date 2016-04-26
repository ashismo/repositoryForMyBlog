package com.org.coop.retail.bs.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.beans.AccountBean;
import com.org.coop.canonical.beans.CustomerAccountBean;
import com.org.coop.canonical.beans.CustomerBean;
import com.org.coop.retail.entities.Account;
import com.org.coop.retail.entities.BranchMaster;
import com.org.coop.retail.entities.Customer;
import com.org.coop.retail.entities.CustomerAccount;
import com.org.coop.retail.repositories.AccountRepository;
import com.org.coop.retail.repositories.CustomerRepository;
import com.org.coop.retail.repositories.RetailBranchMasterRepository;

@Component("customerAccountCC")
public class CustomerAccountCC extends DozerConverter<Object, Object> implements MapperAware, CustomConverter {
	@Autowired
	private RetailBranchMasterRepository retailBranchMasterRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public CustomerAccountCC() {
		super(Object.class, Object.class);
	}
	
	public CustomerAccountCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object convertFrom(Object src, Object dest) {
		if(src != null) {
			int branchId = 0;
			if("CUSTOMER".equalsIgnoreCase(getParameter())) {
				CustomerBean customerBean = (CustomerBean) src;
				Customer customer = (Customer) dest;
				branchId = customerBean.getBranchId();
				BranchMaster branch = retailBranchMasterRepository.findOne(branchId);
				customer.setBranchMaster(branch);
			} else if("ACCOUNT".equalsIgnoreCase(getParameter())) {
				AccountBean accountBean = (AccountBean) src;
				Account account = (Account) dest;
				branchId = accountBean.getBranchId();
				BranchMaster branch = retailBranchMasterRepository.findOne(branchId);
				account.setBranchMaster(branch);
			} else if("CUSTOMER_ACCOUNT".equalsIgnoreCase(getParameter())) {
				CustomerAccountBean customerAccountBean = (CustomerAccountBean) src;
				CustomerAccount customerAccount = (CustomerAccount) dest;
				branchId = customerAccountBean.getBranchId();
				int customerId = customerAccountBean.getCustomerId();
				int accountId = customerAccountBean.getAccountId();
				BranchMaster branch = retailBranchMasterRepository.findOne(branchId);
				Account account = accountRepository.findOne(accountId);
				Customer customer = customerRepository.findOne(customerId);
				customerAccount.setBranchMaster(branch);
				customerAccount.setCustomer(customer);
				customerAccount.setAccount(account);
			}
			
		}
		return dest;
	}

	@Override
	public Object convertTo(Object src, Object dest) {
		
		return null;
	}
}
