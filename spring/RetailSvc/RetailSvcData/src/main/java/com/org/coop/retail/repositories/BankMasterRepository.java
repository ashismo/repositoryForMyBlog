package com.org.coop.retail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.retail.entities.BankMaster;

public interface BankMasterRepository extends JpaRepository<BankMaster, Integer> {
	
	
}
