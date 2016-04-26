package com.org.coop.retail.bs.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.account.beans.GlLedgerDtlBean;
import com.org.coop.canonical.account.beans.GlLedgerHrdBean;
import com.org.coop.retail.entities.BranchMaster;
import com.org.coop.retail.entities.GlLedgerDtl;
import com.org.coop.retail.entities.GlLedgerHrd;
import com.org.coop.retail.entities.GlMaster;
import com.org.coop.retail.repositories.GlLedgerHeaderRepository;
import com.org.coop.retail.repositories.GlMasterRepository;
import com.org.coop.retail.repositories.RetailBranchMasterRepository;

@Component("glLedgerCC")
public class GlLedgerCC extends DozerConverter<Object, Object> implements MapperAware, CustomConverter {
	@Autowired
	private RetailBranchMasterRepository retailBranchMasterRepository;
	
	@Autowired
	private GlMasterRepository glMasterRepository;
	
	@Autowired
	private GlLedgerHeaderRepository glLedgerHeaderRepository;
	
	public GlLedgerCC() {
		super(Object.class, Object.class);
	}
	
	public GlLedgerCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object convertFrom(Object src, Object dest) {
		if(src != null) {
			int branchId = 0;
			if("GL_LEDGER_HRD".equalsIgnoreCase(getParameter())) {
				GlLedgerHrdBean glLedgerHrdBean = (GlLedgerHrdBean) src;
				GlLedgerHrd glLedgerHrd = (GlLedgerHrd) dest;
				branchId = glLedgerHrdBean.getBranchId();
				BranchMaster branch = retailBranchMasterRepository.findOne(branchId);
				glLedgerHrd.setBranchMaster(branch);
			} else if("GL_LEDGER_DTL".equalsIgnoreCase(getParameter())) {
				GlLedgerDtlBean glLedgerDtlBean = (GlLedgerDtlBean) src;
				GlLedgerDtl glLedgerDtl = (GlLedgerDtl) dest;
				branchId = glLedgerDtlBean.getBranchId();
				BranchMaster branch = retailBranchMasterRepository.findOne(branchId);
				GlMaster glMaster = glMasterRepository.findOne(glLedgerDtlBean.getGlMasCode());
				GlLedgerHrd glLedgerHrd = glLedgerHeaderRepository.findOne(glLedgerDtlBean.getGlTranId());
				glLedgerDtl.setBranchMaster(branch);
				glLedgerDtl.setGlMaster(glMaster);
				glLedgerDtl.setGlLedgerHrd(glLedgerHrd);
			} 
			
		}
		return dest;
	}
	
	@Override
	public Object convertTo(Object src, Object dest) {
		return null;
	}
}
