package com.ashish.medicine.admin.customer;

import com.ashish.medicine.exception.AppException;

public class CustomerServiceImpl implements CustomerService {

	public void addOrUpdateCustomer(CustomerBean customerBean) throws AppException {
		new CustomerServiceHelperImpl().addOrUpdateCustomer(customerBean);
	}

	public void getAllCustomers(CustomerBean customerBean) throws AppException {
		new CustomerServiceHelperImpl().getAllCustomers(customerBean);
	}

	public void getCustomerByCustomerId(CustomerBean customerBean)
			throws AppException {
		new CustomerServiceHelperImpl().getCustomerByCustomerId(customerBean);
		
	}
	
	public void searchCustomer(CustomerBean customerBean)
		throws AppException {
		new CustomerServiceHelperImpl().searchCustomer(customerBean);
	
	}
	public void viewCustomer(CustomerBean customerBean) throws AppException {
		new CustomerServiceHelperImpl().viewCustomer(customerBean);
	}
	
	public void deleteCustomer(CustomerBean customerBean) throws AppException {
		new CustomerServiceHelperImpl().deleteCustomer(customerBean);		
	}
}
