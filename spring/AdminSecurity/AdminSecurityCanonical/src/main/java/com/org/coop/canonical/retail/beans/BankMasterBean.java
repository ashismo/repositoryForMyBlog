package com.org.coop.canonical.retail.beans;

import java.io.Serializable;
import java.util.Date;


public class BankMasterBean implements Serializable {
	protected static final long serialVersionUID = 1L;
	protected int bankId;
	protected int glMasCode;
	protected String glMasDesc;
	protected String bankAddress;
	protected String bankName;
	protected Date createDate;
	protected String createUser;
	protected String deleteInd;
	protected String deleteReason;
	protected String passingAuthInd;
	protected String passingAuthRemarks;
	protected String phone1;
	protected Date updateDate;
	protected String updateUser;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bankId;
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
		BankMasterBean other = (BankMasterBean) obj;
		if (bankId != other.bankId)
			return false;
		return true;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public int getGlMasCode() {
		return glMasCode;
	}
	public void setGlMasCode(int glMasCode) {
		this.glMasCode = glMasCode;
	}
	public String getGlMasDesc() {
		return glMasDesc;
	}
	public void setGlMasDesc(String glMasDesc) {
		this.glMasDesc = glMasDesc;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
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
	public String getPassingAuthRemarks() {
		return passingAuthRemarks;
	}
	public void setPassingAuthRemarks(String passingAuthRemarks) {
		this.passingAuthRemarks = passingAuthRemarks;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
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
	
	
}