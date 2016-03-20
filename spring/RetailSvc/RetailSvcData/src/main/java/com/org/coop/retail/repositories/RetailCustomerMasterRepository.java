package com.org.coop.retail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.retail.entities.RetailCustomerMaster;
import com.org.coop.retail.entities.RetailMaster;
import com.org.coop.retail.entities.VendorMaster;

public interface RetailCustomerMasterRepository extends JpaRepository<RetailCustomerMaster, Integer> {
}
