package com.org.coop.retail.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.retail.entities.StockReturn;

public interface RetailStockReturnRepository extends JpaRepository<StockReturn, Integer> {
	@Query("select sr from StockReturn sr where sr.returnDate between :startDate and :endDate")
	public List<StockReturn> findStocksByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);
	
	@Query("select count(sr) from StockReturn sr where sr.returnDate between :startDate and :endDate")
	public int countStocksByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query("select sr from StockReturn sr where sr.stockEntry.materialMaster.materialId = :materialId and sr.returnDate between :startDate and :endDate")
	public List<StockReturn> findStocksByMaterialId(@Param("materialId") int materialId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);
	
	@Query("select count(sr) from StockReturn sr where sr.stockEntry.materialMaster.materialId = :materialId and sr.returnDate between :startDate and :endDate")
	public int countStocksByMaterialId(@Param("materialId") int materialId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
}
