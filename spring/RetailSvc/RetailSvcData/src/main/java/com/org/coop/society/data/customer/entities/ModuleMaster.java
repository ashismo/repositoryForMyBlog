package com.org.coop.society.data.customer.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the module_master database table.
 * 
 */
@Entity
@Table(name="module_master")
@NamedQuery(name="ModuleMaster.findAll", query="SELECT m FROM ModuleMaster m")
public class ModuleMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="module_id")
	private int moduleId;

	@Column(name="create_date", updatable=false)
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="module_name")
	private String moduleName;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="update_date", insertable=false, updatable=false)
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to PermissionMaster
	@OneToMany(mappedBy="moduleMaster")
	private List<PermissionMaster> permissionMasters;

	//bi-directional many-to-one association to RuleMaster
	@OneToMany(mappedBy="moduleMaster")
	private List<RuleMaster> ruleMasters;

	public ModuleMaster() {
	}

	public int getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public List<PermissionMaster> getPermissionMasters() {
		return this.permissionMasters;
	}

	public void setPermissionMasters(List<PermissionMaster> permissionMasters) {
		this.permissionMasters = permissionMasters;
	}

	public PermissionMaster addPermissionMaster(PermissionMaster permissionMaster) {
		getPermissionMasters().add(permissionMaster);
		permissionMaster.setModuleMaster(this);

		return permissionMaster;
	}

	public PermissionMaster removePermissionMaster(PermissionMaster permissionMaster) {
		getPermissionMasters().remove(permissionMaster);
		permissionMaster.setModuleMaster(null);

		return permissionMaster;
	}

	public List<RuleMaster> getRuleMasters() {
		return this.ruleMasters;
	}

	public void setRuleMasters(List<RuleMaster> ruleMasters) {
		this.ruleMasters = ruleMasters;
	}

	public RuleMaster addRuleMaster(RuleMaster ruleMaster) {
		getRuleMasters().add(ruleMaster);
		ruleMaster.setModuleMaster(this);

		return ruleMaster;
	}

	public RuleMaster removeRuleMaster(RuleMaster ruleMaster) {
		getRuleMasters().remove(ruleMaster);
		ruleMaster.setModuleMaster(null);

		return ruleMaster;
	}

}