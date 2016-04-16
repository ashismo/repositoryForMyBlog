package com.org.coop.canonical.retail.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//@JsonInclude(Include.NON_NULL)
public class MaterialGroupBean {
	protected int materialGrpId;
	protected int branchId;
	protected String groupDescription;
	protected String groupName;
	protected Date createDate;
	protected Date updateDate;
	protected String createUser;
	protected String updateUser;
	protected String deleteInd;
	protected String deleteReason;
	
	protected List<MaterialMasterBean> materials = new ArrayList<MaterialMasterBean>();
	
	public int getMaterialGrpId() {
		return materialGrpId;
	}
	public void setMaterialGrpId(int materialGrpId) {
		this.materialGrpId = materialGrpId;
	}
	public String getDeleteInd() {
		return deleteInd;
	}
	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}
	public String getGroupDescription() {
		return groupDescription;
	}
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
		MaterialGroupBean other = (MaterialGroupBean) obj;
		if (materialGrpId != other.materialGrpId)
			return false;
		return true;
	}
	public List<MaterialMasterBean> getMaterials() {
		return materials;
	}
	public void setMaterials(List<MaterialMasterBean> materials) {
		this.materials = materials;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getDeleteReason() {
		return deleteReason;
	}
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
}
