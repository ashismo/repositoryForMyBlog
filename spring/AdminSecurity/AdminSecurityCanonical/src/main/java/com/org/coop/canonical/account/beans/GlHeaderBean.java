package com.org.coop.canonical.account.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class GlHeaderBean implements Serializable {
	protected static final long serialVersionUID = 1L;
	protected int glHeaderCode;
	protected String glHeaderDesc;
	protected String status;
	protected String deleteInd;
	protected String deleteReason;
	protected String passingAuthInd;
	protected String passingAuthRemark;
	protected Date updateDate;
	protected String updateUser;
	protected Date createDate;
	protected String createUser;
	
	protected List<GlSubHeaderBean> glSubHeaders;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + glHeaderCode;
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
		GlHeaderBean other = (GlHeaderBean) obj;
		if (glHeaderCode != other.glHeaderCode)
			return false;
		return true;
	}
	public List<GlSubHeaderBean> getGlSubHeaders() {
		return glSubHeaders;
	}
	public void setGlSubHeaders(List<GlSubHeaderBean> glSubHeaders) {
		this.glSubHeaders = glSubHeaders;
	}
}