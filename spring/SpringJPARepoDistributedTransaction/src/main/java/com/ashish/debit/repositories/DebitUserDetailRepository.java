package com.ashish.debit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.debit.entities.UserDetail;

public interface DebitUserDetailRepository extends JpaRepository<UserDetail, Integer> {

}
