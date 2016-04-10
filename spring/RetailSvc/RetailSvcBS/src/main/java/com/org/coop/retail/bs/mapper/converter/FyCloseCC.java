package com.org.coop.retail.bs.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.beans.FyCloseBean;
import com.org.coop.retail.entities.BranchMaster;
import com.org.coop.retail.entities.FyClose;
import com.org.coop.retail.repositories.RetailBranchMasterRepository;

@Component("fyCloseCC")
public class FyCloseCC extends DozerConverter<FyCloseBean, FyClose> implements MapperAware, CustomConverter {
	@Autowired
	private RetailBranchMasterRepository branchMasterRepository;
	
	public FyCloseCC() {
		super(FyCloseBean.class, FyClose.class);
	}
	
	public FyCloseCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FyCloseBean convertFrom(FyClose src, FyCloseBean dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FyClose convertTo(FyCloseBean src, FyClose dest) {
		// TODO Auto-generated method stub
		if(src != null) {
			BranchMaster branch = branchMasterRepository.findOne(src.getBranchId());
			dest.setBranchMaster(branch);
		}
		return dest;
	}
}
