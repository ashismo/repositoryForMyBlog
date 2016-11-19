package com.ashish.poc.model;

import java.sql.Timestamp;
import java.util.List;

public class Module {
	private int moduleId;
	private String moduleName;
	private String description;
	private Integer envId;
	private String createUser;
	private String updateUser;
	private Timestamp createDate;
	private Timestamp updateDate;
	
	private EnvironmentMaster environmentMaster;
	private List<Url> urls;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getEnvId() {
		return envId;
	}
	public void setEnvId(Integer envId) {
		this.envId = envId;
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
	public List<Url> getUrls() {
		return urls;
	}
	public void setUrls(List<Url> urls) {
		this.urls = urls;
	}
}