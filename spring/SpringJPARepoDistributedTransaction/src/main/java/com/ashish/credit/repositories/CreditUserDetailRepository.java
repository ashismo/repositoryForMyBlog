package com.ashish.credit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.credit.entities.UserDetail;

public interface CreditUserDetailRepository extends JpaRepository<UserDetail, Long> {

}
