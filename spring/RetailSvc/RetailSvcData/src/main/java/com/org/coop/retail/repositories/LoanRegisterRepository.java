package com.org.coop.retail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.retail.entities.LoanRegister;
import com.org.coop.retail.entities.CreditRegister;

public interface LoanRegisterRepository extends JpaRepository<LoanRegister, Integer> {
	
	
}
