package com.org.coop.retail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.retail.entities.CreditRegister;

public interface CreditRegisterRepository extends JpaRepository<CreditRegister, Integer> {
	
	
}
