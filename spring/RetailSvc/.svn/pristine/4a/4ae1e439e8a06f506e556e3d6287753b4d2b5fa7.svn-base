package com.org.coop.retail.bs.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.beans.RetailStockEntryBean;
import com.org.coop.retail.entities.BranchMaster;
import com.org.coop.retail.entities.MaterialMaster;
import com.org.coop.retail.entities.StockEntry;
import com.org.coop.retail.repositories.RetailBranchMasterRepository;
import com.org.coop.retail.repositories.RetailMaterialMasterRepository;

@Component("retailStockEntryCC")
public class RetailStockEntryCC extends DozerConverter<RetailStockEntryBean, StockEntry> implements MapperAware, CustomConverter {
	@Autowired
	private RetailBranchMasterRepository retailBranchMasterRepository;
	
	@Autowired
	private RetailMaterialMasterRepository retailMaterialMasterRepository;
	
	public RetailStockEntryCC() {
		super(RetailStockEntryBean.class, StockEntry.class);
	}
	
	public RetailStockEntryCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RetailStockEntryBean convertFrom(StockEntry src, RetailStockEntryBean dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockEntry convertTo(RetailStockEntryBean src, StockEntry dest) {
		// TODO Auto-generated method stub
		if(src != null) {
			BranchMaster branch = retailBranchMasterRepository.findOne(src.getBranchId());
			MaterialMaster material = retailMaterialMasterRepository.findOne(src.getMaterialId());
			
			dest.setBranchMaster(branch);
			dest.setMaterialMaster(material);
		}
		return dest;
	}
}
