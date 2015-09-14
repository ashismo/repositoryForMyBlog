package com.ashish.medicine.admin.customer;

import com.ashish.medicine.admin.company.CompanyBean;
import com.ashish.medicine.exception.AppException;

public interface CustomerService {
	public void addOrUpdateCustomer(CustomerBean customerBean) throws AppException;
	public void getAllCustomers(CustomerBean customerBean) throws AppException;
	public void getCustomerByCustomerId(CustomerBean customerBean) throws AppException;
	public void searchCustomer(CustomerBean customerBean) throws AppException;
	public void viewCustomer(CustomerBean customerBean) throws AppException;
	public void deleteCustomer(CustomerBean customerBean) throws AppException;
}
