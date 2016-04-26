package com.org.coop.canonical.account.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class DenominationRegisterBean implements Serializable {
	protected int denoId;
	protected int branchId;
	protected int branchName;
	protected int cashId;
	protected int denomination;
	protected String noteCoin;
	protected BigDecimal noteCoinAmount;
	protected int noteCoinCount;
	protected String deleteInd;
	protected String deleteReason;
	protected String passingAuthInd;
	protected String passingAuthRemark;
	protected Date createDate;
	protected String createUser;
	protected Date updateDate;
	protected String updateUser;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cashId;
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
		DenominationRegisterBean other = (DenominationRegisterBean) obj;
		if (cashId != other.cashId)
			return false;
		return true;
	}
	public int getDenoId() {
		return denoId;
	}
	public void setDenoId(int denoId) {
		this.denoId = denoId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getBranchName() {
		return branchName;
	}
	public void setBranchName(int branchName) {
		this.branchName = branchName;
	}
	public int getCashId() {
		return cashId;
	}
	public void setCashId(int cashId) {
		this.cashId = cashId;
	}
	public int getDenomination() {
		return denomination;
	}
	public void setDenomination(int denomination) {
		this.denomination = denomination;
	}
	public String getNoteCoin() {
		return noteCoin;
	}
	public void setNoteCoin(String noteCoin) {
		this.noteCoin = noteCoin;
	}
	public BigDecimal getNoteCoinAmount() {
		return noteCoinAmount;
	}
	public void setNoteCoinAmount(BigDecimal noteCoinAmount) {
		this.noteCoinAmount = noteCoinAmount;
	}
	public int getNoteCoinCount() {
		return noteCoinCount;
	}
	public void setNoteCoinCount(int noteCoinCount) {
		this.noteCoinCount = noteCoinCount;
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
}