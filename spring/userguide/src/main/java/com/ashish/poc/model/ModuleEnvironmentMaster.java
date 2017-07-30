package com.ashish.poc.model;

import java.sql.Timestamp;

public class ModuleEnvironmentMaster {
	private int moduleEnvId;
	private int moduleId;
	private int envId;
	private String moduleName;
	private String envName;
	private boolean deleteInd;
	private String createUser;
	private String updateUser;
	private Timestamp createDate;
	private Timestamp updateDate;
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public int getEnvId() {
		return envId;
	}
	public void setEnvId(int envId) {
		this.envId = envId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getEnvName() {
		return envName;
	}
	public void setEnvName(String envName) {
		this.envName = envName;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public int getModuleEnvId() {
		return moduleEnvId;
	}
	public void setModuleEnvId(int moduleEnvId) {
		this.moduleEnvId = moduleEnvId;
	}
	public boolean isDeleteInd() {
		return deleteInd;
	}
	public void setDeleteInd(boolean deleteInd) {
		this.deleteInd = deleteInd;
	}
}
