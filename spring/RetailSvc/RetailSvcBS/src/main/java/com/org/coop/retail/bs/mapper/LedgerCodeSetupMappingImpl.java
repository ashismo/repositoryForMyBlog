package com.org.coop.retail.bs.mapper;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.retail.bs.config.RetailDozerConfig;

@Component
public class LedgerCodeSetupMappingImpl {
	@Autowired
	private RetailDozerConfig retailDozerConfig;
	
	public void mapRetailLedgerCodePurchaseBean(Object src, Object dest) {
		Mapper dozerBeanMapper = (Mapper) retailDozerConfig.dozerBean();
		dozerBeanMapper.map(src, dest, "ledgerCodeRetailPurchaseMap");
	}
	
	public void mapRetailLedgerCodeSaleBean(Object src, Object dest) {
		Mapper dozerBeanMapper = (Mapper) retailDozerConfig.dozerBean();
		dozerBeanMapper.map(src, dest, "ledgerCodeRetailSaleMap");
	}
	
	public void mapPaymentLedgerCodeBean(Object src, Object dest) {
		Mapper dozerBeanMapper = (Mapper) retailDozerConfig.dozerBean();
		dozerBeanMapper.map(src, dest, "paymentLedgerCodeMap");
	}
}