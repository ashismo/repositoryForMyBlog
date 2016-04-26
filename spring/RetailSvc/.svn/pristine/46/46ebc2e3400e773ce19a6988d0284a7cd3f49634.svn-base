package com.org.coop.retail.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.org.coop.retail.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>, QueryDslPredicateExecutor<Customer> {
	
	public Customer findByPanNo(String panNo);
	public Customer findByAadharNo(String aadharNo);
	public List<Customer> findByMobile1(String mobileNo);
	public List<Customer> findByMobile2(String mobileNo);
}
