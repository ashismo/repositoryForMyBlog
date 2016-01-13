package com.org.coop.canonical.master.beans;

import java.util.HashSet;
import java.util.Set;

public class StateMasterBean {
	private int stateId;
	private String stateName;
	private String stateCode;
	private String countryCode;
	
	private Set<DistrictMasterBean> districts = new HashSet<DistrictMasterBean>();

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + stateId;
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
		StateMasterBean other = (StateMasterBean) obj;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (stateId != other.stateId)
			return false;
		return true;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Set<DistrictMasterBean> getDistricts() {
		return districts;
	}

	public void setDistricts(Set<DistrictMasterBean> districts) {
		this.districts = districts;
	}
	
	
}
