package com.org.coop.bs.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.beans.RoleMasterBean;
import com.org.coop.canonical.beans.RolePermissionBean;
import com.org.coop.society.data.admin.entities.BranchMaster;
import com.org.coop.society.data.admin.entities.PermissionMaster;
import com.org.coop.society.data.admin.entities.RoleMaster;
import com.org.coop.society.data.admin.entities.RolePermission;
import com.org.coop.society.data.admin.repositories.BranchMasterRepository;
import com.org.coop.society.data.admin.repositories.PermissionMasterRepository;
import com.org.coop.society.data.admin.repositories.RoleMasterRepository;

@Component("branchRolePermissionsCC")
public class BranchRolePermissionsCC extends DozerConverter<RolePermission, RolePermissionBean> implements MapperAware, CustomConverter {
	
	@Autowired
	private RoleMasterRepository roleMasterRepository;
	
	@Autowired
	private PermissionMasterRepository permissionMasterRepository;
	
	public BranchRolePermissionsCC() {
		super(RolePermission.class, RolePermissionBean.class);
	}
	
	public BranchRolePermissionsCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RolePermission convertFrom(RolePermissionBean src, RolePermission dest) {
		if(src != null) {
				RoleMaster role = roleMasterRepository.findOne(src.getRoleId());
				PermissionMaster permission = permissionMasterRepository.findOne(src.getPermissionId());
				if(role != null) {
					dest.setRoleMaster(role);
					dest.setPermissionMaster(permission);
				}
			}
		return dest;
	}

	@Override
	public RolePermissionBean convertTo(RolePermission src, RolePermissionBean dest) {
		if(src != null) {
			dest.setRoleId(src.getRoleMaster().getRoleId());
		}
		return dest;
	}
}
