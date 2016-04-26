package com.org.coop.canonical.account.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


public class GlLedgerDtlBean implements Serializable {
	protected static final long serialVersionUID = 1L;
	protected int glTranDtlId;
	protected int glTranId;
	protected int accountId;
	protected int branchId;
	protected String branchName;
	protected int glMasCode;
	protected String glMasDesc;
	protected int accountNo;
	protected BigDecimal amount; 
	protected Date createDate;
	protected String createUser;
	protected String deleteInd;
	protected String deleteReason;
	protected String drCr;
	protected String passinAuthRemarks;
	protected String passingAuthInd;
	protected String remarks;
	protected Date updateDate;
	protected String updateUser;
	public int getGlTranDtlId() {
		return glTranDtlId;
	}
	public void setGlTranDtlId(int glTranDtlId) {
		this.glTranDtlId = glTranDtlId;
	}
	public int getGlTranId() {
		return glTranId;
	}
	public void setGlTranId(int glTranId) {
		this.glTranId = glTranId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
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
	public String getDrCr() {
		return drCr;
	}
	public void setDrCr(String drCr) {
		this.drCr = drCr;
	}
	public String getPassinAuthRemarks() {
		return passinAuthRemarks;
	}
	public void setPassinAuthRemarks(String passinAuthRemarks) {
		this.passinAuthRemarks = passinAuthRemarks;
	}
	public String getPassingAuthInd() {
		return passingAuthInd;
	}
	public void setPassingAuthInd(String passingAuthInd) {
		this.passingAuthInd = passingAuthInd;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
		result = prime * result + glTranDtlId;
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
		GlLedgerDtlBean other = (GlLedgerDtlBean) obj;
		if (glTranDtlId != other.glTranDtlId)
			return false;
		return true;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}