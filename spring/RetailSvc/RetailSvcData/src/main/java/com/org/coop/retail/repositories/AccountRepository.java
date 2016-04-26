package com.org.coop.retail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.org.coop.retail.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> , QueryDslPredicateExecutor<Account> {
	
	
}
