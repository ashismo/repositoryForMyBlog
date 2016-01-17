package com.org.coop.bs.mapper.converter;

import org.apache.log4j.Logger;
import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.master.beans.ModulePermissionMasterBean;
import com.org.coop.canonical.master.beans.RuleMasterBean;
import com.org.coop.canonical.master.beans.RuleMasterValuesBean;
import com.org.coop.society.data.admin.entities.ModuleMaster;
import com.org.coop.society.data.admin.entities.RuleMaster;
import com.org.coop.society.data.admin.repositories.ModuleMasterRepository;
import com.org.coop.society.data.admin.repositories.RuleMasterRepository;

@Component("moduleRulePermissionCC")
public class ModuleRulePermissionCC extends DozerConverter<Object, Object> implements MapperAware, CustomConverter {
	private static final Logger log = Logger.getLogger(ModuleRulePermissionCC.class); 
	
	@Autowired
	private ModuleMasterRepository moduleMasterRepository;
	
	@Autowired
	private RuleMasterRepository ruleMasterRepository;
	
	public ModuleRulePermissionCC() {
		super(Object.class, Object.class);
	}
	
	public ModuleRulePermissionCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object convertFrom(Object src, Object dest) {
		if(log.isDebugEnabled()) {
			log.debug("Passed parameter from the mapping xml file: " + getParameter());
		}
		if("permissionToModuleCopy".equalsIgnoreCase(getParameter())) {
			// Populate country from state bean
			ModulePermissionMasterBean modulePermission = (ModulePermissionMasterBean)src;
			ModuleMaster module = moduleMasterRepository.findByModuleName(modulePermission.getModuleName());
			dest = module;
		} else if("ruleToModuleCopy".equalsIgnoreCase(getParameter())) {
			// Populate country from district bean
			RuleMasterBean ruleBean = (RuleMasterBean)src;
			ModuleMaster module = moduleMasterRepository.findByModuleName(ruleBean.getModuleName());
			dest = module;
		} else if("ruleValueToRuleCopy".equalsIgnoreCase(getParameter())) {
			// Populate country from district bean
			RuleMasterValuesBean ruleValue = (RuleMasterValuesBean)src;
			RuleMaster rule = ruleMasterRepository.findByRuleNameAndModuleName(ruleValue.getRuleName(), ruleValue.getModuleName());
			dest = rule;
		}
		return dest;
	}

	@Override
	public Object convertTo(Object src, Object dest) {
//		System.out.println("----" + getParameter());
		return dest;
	}
}
