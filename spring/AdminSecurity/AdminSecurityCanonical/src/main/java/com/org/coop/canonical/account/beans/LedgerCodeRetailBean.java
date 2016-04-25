package com.org.coop.canonical.account.beans;

import java.io.Serializable;
import java.util.Date;


public class LedgerCodeRetailBean implements Serializable {
	protected static final long serialVersionUID = 1L;
	protected int retailLedgerCodeId;
	protected int materialGrpId;
	protected String groupName;
	protected int vendorId;
	protected String vendorName;
	protected Date createDate;
	protected String createUser;
	protected String deleteInd;
	protected String deleteReason;
	protected String passingAuthInd;
	protected String passingAuthRemark;
	protected int purchaseCode;
	protected int sellCode;
	protected Date updateDate;
	protected String updateUser;
	public int getRetailLedgerCodeId() {
		return retailLedgerCodeId;
	}
	public void setRetailLedgerCodeId(int retailLedgerCodeId) {
		this.retailLedgerCodeId = retailLedgerCodeId;
	}
	public int getMaterialGrpId() {
		return materialGrpId;
	}
	public void setMaterialGrpId(int materialGrpId) {
		this.materialGrpId = materialGrpId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getDeleteInd() {
		return deleteInd;
	}
	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}
	public String getDeleteReason() {
		return deleteReason;
	}
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	public String getPassingAuthInd() {
		return passingAuthInd;
	}
	public void setPassingAuthInd(String passingAuthInd) {
		this.passingAuthInd = passingAuthInd;
	}
	public String getPassingAuthRemark() {
		return passingAuthRemark;
	}
	public void setPassingAuthRemark(String passingAuthRemark) {
		this.passingAuthRemark = passingAuthRemark;
	}
	public int getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(int purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public int getSellCode() {
		return sellCode;
	}
	public void setSellCode(int sellCode) {
		this.sellCode = sellCode;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + retailLedgerCodeId;
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
		LedgerCodeRetailBean other = (LedgerCodeRetailBean) obj;
		if (retailLedgerCodeId != other.retailLedgerCodeId)
			return false;
		return true;
	}

}