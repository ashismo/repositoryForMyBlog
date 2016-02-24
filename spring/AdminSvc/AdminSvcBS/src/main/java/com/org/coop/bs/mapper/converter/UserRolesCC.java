package com.org.coop.bs.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.beans.UserRoleBean;
import com.org.coop.society.data.admin.entities.RoleMaster;
import com.org.coop.society.data.admin.entities.User;
import com.org.coop.society.data.admin.entities.UserRole;
import com.org.coop.society.data.admin.repositories.RoleMasterRepository;
import com.org.coop.society.data.admin.repositories.UserAdminRepository;

@Component("userRolesCC")
public class UserRolesCC extends DozerConverter<UserRole, UserRoleBean> implements MapperAware, CustomConverter {
	
	@Autowired
	private UserAdminRepository userRepository;
	
	@Autowired
	private RoleMasterRepository roleMasterRepository;
	
	public UserRolesCC() {
		super(UserRole.class, UserRoleBean.class);
	}
	
	public UserRolesCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserRole convertFrom(UserRoleBean src, UserRole dest) {
		if(src != null) {
				User user = userRepository.findOne(src.getUserId());
				RoleMaster role = roleMasterRepository.findOne(src.getRoleId());
				if(user != null && role != null) {
					dest.setUser(user);
					dest.setRoleMaster(role);
				}
			}
		return dest;
	}

	@Override
	public UserRoleBean convertTo(UserRole src, UserRoleBean dest) {
		if(src != null) {
			dest.setUserId(src.getUser().getUserId());
			dest.setRoleId(src.getRoleMaster().getRoleId());
		}
		return dest;
	}
}
