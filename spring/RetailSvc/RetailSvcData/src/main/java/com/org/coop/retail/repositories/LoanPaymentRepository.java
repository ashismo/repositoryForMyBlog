package com.org.coop.retail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.retail.entities.CardRegister;
import com.org.coop.retail.entities.GlLedgerHrd;
import com.org.coop.retail.entities.LoanPayment;
import com.org.coop.retail.entities.RetailGlLedger;
import com.org.coop.retail.entities.StockEntry;

public interface LoanPaymentRepository extends JpaRepository<LoanPayment, Integer> {
	
	
}
