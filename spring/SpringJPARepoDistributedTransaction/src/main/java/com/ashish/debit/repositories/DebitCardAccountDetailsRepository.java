package com.ashish.debit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.debit.entities.AccountDetail;

public interface DebitCardAccountDetailsRepository extends JpaRepository<AccountDetail, Integer> {

}
