package com.org.coop.canonical.account.beans;

import java.io.Serializable;
import java.util.Date;


public class GlSubHeaderBean implements Serializable {
	protected static final long serialVersionUID = 1L;
	private int glSubHeaderCode;
	protected int glHeaderCode;
	protected String glHeaderDesc;
	private String glSubHeaderDesc;
	private String deleteInd;
	private String deleteReason;
	private String passingAuthInd;
	private String passingAuthRemark;
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;
	public int getGlSubHeaderCode() {
		return glSubHeaderCode;
	}
	public void setGlSubHeaderCode(int glSubHeaderCode) {
		this.glSubHeaderCode = glSubHeaderCode;
	}
	public int getGlHeaderCode() {
		return glHeaderCode;
	}
	public void setGlHeaderCode(int glHeaderCode) {
		this.glHeaderCode = glHeaderCode;
	}
	public String getGlHeaderDesc() {
		return glHeaderDesc;
	}
	public void setGlHeaderDesc(String glHeaderDesc) {
		this.glHeaderDesc = glHeaderDesc;
	}
	public String getGlSubHeaderDesc() {
		return glSubHeaderDesc;
	}
	public void setGlSubHeaderDesc(String glSubHeaderDesc) {
		this.glSubHeaderDesc = glSubHeaderDesc;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + glSubHeaderCode;
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
		GlSubHeaderBean other = (GlSubHeaderBean) obj;
		if (glSubHeaderCode != other.glSubHeaderCode)
			return false;
		return true;
	}
}