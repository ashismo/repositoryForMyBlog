package com.org.coop.retail.bs.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.account.beans.GlLedgerHrdBean;
import com.org.coop.retail.entities.BranchMaster;
import com.org.coop.retail.entities.GlLedgerHrd;
import com.org.coop.retail.repositories.RetailBranchMasterRepository;

@Component("glLedgerHrdCC")
public class GlLedgerHrdCC extends DozerConverter<GlLedgerHrdBean, GlLedgerHrd> implements MapperAware, CustomConverter {
	@Autowired
	private RetailBranchMasterRepository retailBranchMasterRepository;
	
	public GlLedgerHrdCC() {
		super(GlLedgerHrdBean.class, GlLedgerHrd.class);
	}
	
	public GlLedgerHrdCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GlLedgerHrdBean convertFrom(GlLedgerHrd src, GlLedgerHrdBean dest) {
		return null;
	}

	@Override
	public GlLedgerHrd convertTo(GlLedgerHrdBean src, GlLedgerHrd dest) {
		if(src != null) {
			BranchMaster branch = retailBranchMasterRepository.findOne(src.getBranchId());
			dest.setBranchMaster(branch);
		}
		return dest;
	}
}
