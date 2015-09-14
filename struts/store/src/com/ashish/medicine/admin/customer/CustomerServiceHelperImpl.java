package com.ashish.medicine.admin.customer;

import com.ashish.medicine.admin.company.CompanyBean;
import com.ashish.medicine.admin.company.CompanyDaoImpl;
import com.ashish.medicine.exception.AppException;

public class CustomerServiceHelperImpl implements CustomerServiceHelper {

	public void addOrUpdateCustomer(CustomerBean customerBean) throws AppException {
		new CustomerDaoImpl().addOrUpdateCustomer(customerBean);
	}

	public void getAllCustomers(CustomerBean customerBean) throws AppException {
		new CustomerDaoImpl().getAllCustomers(customerBean);
	}

	public void getCustomerByCustomerId(CustomerBean customerBean)
			throws AppException {
		new CustomerDaoImpl().getCustomerByCustomerId(customerBean);
		
	}
	
	public void searchCustomer(CustomerBean customerBean)
		throws AppException {
		new CustomerDaoImpl().searchCustomer(customerBean);
	
	}
	
	public void viewCustomer(CustomerBean customerBean) throws AppException {
		new CustomerDaoImpl().viewCustomer(customerBean);
	}
	
	public void deleteCustomer(CustomerBean customerBean) throws AppException {
		new CustomerDaoImpl().deleteCustomer(customerBean);		
	}
}
