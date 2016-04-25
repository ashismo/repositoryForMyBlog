package com.org.coop.canonical.account.beans;

import java.io.Serializable;
import java.util.Date;


public class LedgerCodePaymentBean implements Serializable {
	protected static final long serialVersionUID = 1L;
	protected int paymentLedgerId;
	protected Date createDate;
	protected String createUser;
	protected String deleteInd;
	protected String deleteReason;
	protected int glCode;
	protected String passingAuthInd;
	protected String passingAuthRemark;
	protected String paymentType;
	protected Date updateDate;
	protected String updateUser;
	
	public int getPaymentLedgerId() {
		return paymentLedgerId;
	}
	public void setPaymentLedgerId(int paymentLedgerId) {
		this.paymentLedgerId = paymentLedgerId;
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
	public int getGlCode() {
		return glCode;
	}
	public void setGlCode(int glCode) {
		this.glCode = glCode;
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
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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
		result = prime * result + paymentLedgerId;
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
		LedgerCodePaymentBean other = (LedgerCodePaymentBean) obj;
		if (paymentLedgerId != other.paymentLedgerId)
			return false;
		return true;
	}

	
}