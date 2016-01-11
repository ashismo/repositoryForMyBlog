package com.org.coop.canonical.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BranchLicenseDtlsBean {
	private int branchId;
	private int branchLicenseId;
	private Date startDate;
	private Date endDate;
	private int graceDay;
	private String createUser;
	private String updateUser;
	
	private List<AddressBean> addresses = new ArrayList<AddressBean>();

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getBranchLicenseId() {
		return branchLicenseId;
	}

	public void setBranchLicenseId(int branchLicenseId) {
		this.branchLicenseId = branchLicenseId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getGraceDay() {
		return graceDay;
	}

	public void setGraceDay(int graceDay) {
		this.graceDay = graceDay;
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

	public List<AddressBean> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressBean> addresses) {
		this.addresses = addresses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + branchLicenseId;
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
		BranchLicenseDtlsBean other = (BranchLicenseDtlsBean) obj;
		if (branchLicenseId != other.branchLicenseId)
			return false;
		return true;
	}

	
}
