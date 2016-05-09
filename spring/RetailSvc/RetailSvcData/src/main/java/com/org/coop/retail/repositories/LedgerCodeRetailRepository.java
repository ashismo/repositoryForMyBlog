package com.org.coop.retail.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.retail.entities.LedgerCodeRetail;

public interface LedgerCodeRetailRepository extends JpaRepository<LedgerCodeRetail, Integer> {
	
	@Query("select lcr from LedgerCodeRetail lcr")
	public List<LedgerCodeRetail> findAllLedgerCodes(Pageable pagable);
	
	@Query("select lcr from LedgerCodeRetail lcr where lcr.materialGroup.materialGrpId = :materialGrpId")
	public List<LedgerCodeRetail> findRetailLedgerByMaterialGrpId(@Param("materialGrpId") int materialGrpId, Pageable pagable);
	
	@Query("select lcr from LedgerCodeRetail lcr where lcr.materialGroup.materialGrpId = :materialGrpId and lcr.vendorMaster.vendorId = :vendorId")
	public List<LedgerCodeRetail> findRetailLedgerByMaterialGrpIdAndVendorId(@Param("materialGrpId") int materialGrpId, @Param("vendorId") int vendorId, Pageable pagable);
	
	@Query("select lcr from LedgerCodeRetail lcr where lcr.vendorMaster.vendorId = :vendorId")
	public List<LedgerCodeRetail> findRetailLedgerByVendorId(@Param("vendorId") int vendorId, Pageable pagable);
	
	public List<LedgerCodeRetail> findByPurchaseSale(String purchaseSale);
	
	public List<LedgerCodeRetail> findByCustomerType(String customerType);
}
