package com.org.coop.retail.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the material_group database table.
 * 
 */
@Entity
@Table(name="material_group")
@NamedQuery(name="MaterialGroup.findAll", query="SELECT m FROM MaterialGroup m")
public class MaterialGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="material_grp_id")
	private int materialGrpId;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	@Column(name="delete_ind")
	private String deleteInd;

	@Column(name="group_description")
	private String groupDescription;

	@Column(name="group_name")
	private String groupName;

	@Column(name="update_date")
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to BranchMaster
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchMaster branchMaster;

	//bi-directional many-to-one association to MaterialMaster
	@OneToMany(mappedBy="materialGroup", fetch = FetchType.LAZY, cascade={CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH})
	private List<MaterialMaster> materialMasters;

	public MaterialGroup() {
	}

	public int getMaterialGrpId() {
		return this.materialGrpId;
	}

	public void setMaterialGrpId(int materialGrpId) {
		this.materialGrpId = materialGrpId;
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

	public String getDeleteInd() {
		return this.deleteInd;
	}

	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}

	public String getGroupDescription() {
		return this.groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public BranchMaster getBranchMaster() {
		return this.branchMaster;
	}

	public void setBranchMaster(BranchMaster branchMaster) {
		this.branchMaster = branchMaster;
	}

	public List<MaterialMaster> getMaterialMasters() {
		return this.materialMasters;
	}

	public void setMaterialMasters(List<MaterialMaster> materialMasters) {
		this.materialMasters = materialMasters;
	}

	public MaterialMaster addMaterialMaster(MaterialMaster materialMaster) {
		getMaterialMasters().add(materialMaster);
		materialMaster.setMaterialGroup(this);

		return materialMaster;
	}

	public MaterialMaster removeMaterialMaster(MaterialMaster materialMaster) {
		getMaterialMasters().remove(materialMaster);
		materialMaster.setMaterialGroup(null);

		return materialMaster;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + materialGrpId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaterialGroup other = (MaterialGroup) obj;
		if (materialGrpId != other.materialGrpId)
			return false;
		return true;
	}

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
	    updateDate = new Timestamp(System.currentTimeMillis());
	    if (createDate == null) {
	    	createDate = new Timestamp(System.currentTimeMillis());
	    }
	}
}