package com.org.coop.society.data.customer.transaction.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class CustomerRoutingDataSource extends AbstractRoutingDataSource {

	   @Override
	   protected Object determineCurrentLookupKey() {
	      return CustomerContextHolder.getCustomerType();
	   }

}
