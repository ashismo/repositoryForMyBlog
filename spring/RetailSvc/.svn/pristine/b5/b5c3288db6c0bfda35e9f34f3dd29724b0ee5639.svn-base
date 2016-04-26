package com.org.coop.retail.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.retail.entities.LedgerCodeRetailPurchase;

public interface LedgerCodeRetailPurchaseRepository extends JpaRepository<LedgerCodeRetailPurchase, Integer> {
	
	@Query("select lcr from LedgerCodeRetailPurchase lcr")
	public List<LedgerCodeRetailPurchase> findAllLedgerCodes(Pageable pagable);
	
	@Query("select lcr from LedgerCodeRetailPurchase lcr where lcr.materialGroup.materialGrpId = :materialGrpId")
	public List<LedgerCodeRetailPurchase> findRetailLedgerByMaterialGrpId(@Param("materialGrpId") int materialGrpId, Pageable pagable);
	
	@Query("select lcr from LedgerCodeRetailPurchase lcr where lcr.materialGroup.materialGrpId = :materialGrpId and lcr.vendorMaster.vendorId = :vendorId")
	public List<LedgerCodeRetailPurchase> findRetailLedgerByMaterialGrpIdAndVendorId(@Param("materialGrpId") int materialGrpId, @Param("vendorId") int vendorId, Pageable pagable);
	
	@Query("select lcr from LedgerCodeRetailPurchase lcr where lcr.vendorMaster.vendorId = :vendorId")
	public List<LedgerCodeRetailPurchase> findRetailLedgerByVendorId(@Param("vendorId") int vendorId, Pageable pagable);
	
}
