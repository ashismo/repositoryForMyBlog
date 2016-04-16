package com.org.coop.retail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.retail.entities.StockSell;

public interface RetailStockSellRepository extends JpaRepository<StockSell, Integer> {
	
}
