package com.org.coop.canonical.master.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ModuleMasterBean {
	private int moduleId;
	private String moduleName;
	private Date startDate;
	private Date endDate;
	private String createUser;
	private String updateUser;
	
	private Set<ModulePermissionMasterBean> modulePermissions = new HashSet<ModulePermissionMasterBean>();
	private Set<RuleMasterBean> moduleRules = new HashSet<RuleMasterBean>();
	
	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Set<ModulePermissionMasterBean> getModulePermissions() {
		return modulePermissions;
	}

	public void setModulePermissions(
			Set<ModulePermissionMasterBean> modulePermissions) {
		this.modulePermissions = modulePermissions;
	}

	public Set<RuleMasterBean> getModuleRules() {
		return moduleRules;
	}

	public void setModuleRules(Set<RuleMasterBean> moduleRules) {
		this.moduleRules = moduleRules;
	}

}
