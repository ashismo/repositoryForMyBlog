package com.org.coop.retail.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.retail.entities.Transaction;

public interface TransactionsRepository extends JpaRepository<Transaction, Integer> {
	
	@Query(value="SELECT CONCAT(DATE_FORMAT(:actionDate, '%d%m%Y'), '/', COUNT(*)+1) AS TRAN_ID "
			+ "FROM transactions WHERE "
			+ "action_date = :actionDate", nativeQuery=true)
	public String getTransactionNumber(@Param("actionDate")Date actionDate);
}
