package com.ashish.credit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.credit.entities.Transaction;

public interface CreditCardTransactionRepository extends JpaRepository<Transaction, Long> {

}
