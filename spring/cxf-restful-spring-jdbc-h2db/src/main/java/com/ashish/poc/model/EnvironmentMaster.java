package com.ashish.poc.model;

import java.sql.Timestamp;

public class EnvironmentMaster {
	private int envId;
	private String envName;
	private String envDesc;
	public int getEnvId() {
		return envId;
	}
	public void setEnvId(int envId) {
		this.envId = envId;
	}
	public String getEnvName() {
		return envName;
	}
	public void setEnvName(String envName) {
		this.envName = envName;
	}
	public String getEnvDesc() {
		return envDesc;
	}
	public void setEnvDesc(String envDesc) {
		this.envDesc = envDesc;
	}

}
