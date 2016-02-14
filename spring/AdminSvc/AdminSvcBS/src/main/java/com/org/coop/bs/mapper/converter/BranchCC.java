package com.org.coop.bs.mapper.converter;

import java.util.List;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.beans.AddressBean;
import com.org.coop.canonical.beans.BranchBean;
import com.org.coop.society.data.admin.entities.Address;
import com.org.coop.society.data.admin.entities.BranchAddress;
import com.org.coop.society.data.admin.entities.BranchMaster;
import com.org.coop.society.data.admin.entities.DistrictMaster;
import com.org.coop.society.data.admin.repositories.BranchAddressRepository;
import com.org.coop.society.data.admin.repositories.BranchMasterRepository;
import com.org.coop.society.data.admin.repositories.DistrictMasterRepository;

@Component("branchCC")
public class BranchCC extends DozerConverter<BranchBean, List> implements MapperAware, CustomConverter {
	@Autowired
	private BranchMasterRepository branchMasterRepository;
	
	public BranchCC() {
		super(BranchBean.class, List.class);
	}
	
	public BranchCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BranchBean convertFrom(List src, BranchBean dest) {
		return dest;
	}

	@Override
	public List convertTo(BranchBean src, List dest) {
		if("addBranchMastersIntoBranchAddress".equals(getParameter())) {
			List<BranchAddress> branchAddresses = (List<BranchAddress>)dest;
			BranchMaster branch = branchMasterRepository.findByBranchId(src.getBranchId());
			for(BranchAddress ba : branchAddresses) {
				ba.setBranchMaster(branch);
			}
		}
		return dest;
	}
}
