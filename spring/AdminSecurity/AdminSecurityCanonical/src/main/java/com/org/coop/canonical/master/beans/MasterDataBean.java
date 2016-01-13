package com.org.coop.canonical.master.beans;

import java.util.HashSet;
import java.util.Set;

public class MasterDataBean {
	private Set<CountryMasterBean> countries = new HashSet<CountryMasterBean>();
	private String errorMsg;
	public Set<CountryMasterBean> getCountries() {
		return countries;
	}
	public void setCountries(Set<CountryMasterBean> countries) {
		this.countries = countries;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
}
