package com.org.coop.bs.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.beans.RoleMasterBean;
import com.org.coop.canonical.beans.UserMasterBean;
import com.org.coop.society.data.admin.entities.BranchMaster;
import com.org.coop.society.data.admin.entities.RoleMaster;
import com.org.coop.society.data.admin.entities.User;
import com.org.coop.society.data.admin.repositories.BranchMasterRepository;

@Component("branchRolesCC")
public class BranchRolesCC extends DozerConverter<RoleMaster, RoleMasterBean> implements MapperAware, CustomConverter {
	
	@Autowired
	private BranchMasterRepository branchMasterRepository;
	
	public BranchRolesCC() {
		super(RoleMaster.class, RoleMasterBean.class);
	}
	
	public BranchRolesCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RoleMaster convertFrom(RoleMasterBean src, RoleMaster dest) {
		if(src != null) {
				BranchMaster branch = branchMasterRepository.findOne(src.getBranchId());
				if(branch != null) {
					dest.setBranchMaster(branch);
				}
			}
		return dest;
	}

	@Override
	public RoleMasterBean convertTo(RoleMaster src, RoleMasterBean dest) {
		if(src != null) {
			dest.setBranchId(src.getBranchMaster().getBranchId());
		}
		return dest;
	}
}
