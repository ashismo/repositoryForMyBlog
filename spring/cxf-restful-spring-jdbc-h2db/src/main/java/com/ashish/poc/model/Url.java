package com.ashish.poc.model;

import java.sql.Timestamp;
import java.util.List;

public class Url {
	private int urlId;
	private String name;
	private String description;
	private String url;
	private String username;
	private String password;
	private Integer moduleEnvId;
	private Integer roleId;
	private boolean visible;
	private String email;
	private String moduleName;
	private String envName;
	private String roleName;
	private String createUser;
	private String updateUser;
	private Timestamp createDate;
	private Timestamp updateDate;
	
	private ModuleEnvironmentMaster moduleEnvMaster;
	private Role role;
	private List<UserUrlDtl> userUrlDtls;
	
	public int getUrlId() {
		return urlId;
	}
	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public List<UserUrlDtl> getUserUrlDtls() {
		return userUrlDtls;
	}
	public void setUserUrlDtls(List<UserUrlDtl> userUrlDtls) {
		this.userUrlDtls = userUrlDtls;
	}
	
	public Integer getModuleEnvId() {
		return moduleEnvId;
	}
	public void setModuleEnvId(Integer moduleEnvId) {
		this.moduleEnvId = moduleEnvId;
	}
	public ModuleEnvironmentMaster getModuleEnvMaster() {
		return moduleEnvMaster;
	}
	public void setModuleEnvMaster(ModuleEnvironmentMaster moduleEnvMaster) {
		this.moduleEnvMaster = moduleEnvMaster;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
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
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
