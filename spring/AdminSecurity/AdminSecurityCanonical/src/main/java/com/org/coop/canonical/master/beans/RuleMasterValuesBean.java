package com.org.coop.canonical.master.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class RuleMasterValuesBean {
	private int ruleValueId;
	private String moduleName;
	private String ruleName;
	private String ruleDescription;
	private String ruleValue;
	private String ruleValueDescription;
	private Date startDate;
	private Date endDate;
	private String createUser;
	private String updateUser;
	


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

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	public int getRuleValueId() {
		return ruleValueId;
	}

	public void setRuleValueId(int ruleValueId) {
		this.ruleValueId = ruleValueId;
	}

	public String getRuleValue() {
		return ruleValue;
	}

	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}

	public String getRuleValueDescription() {
		return ruleValueDescription;
	}

	public void setRuleValueDescription(String ruleValueDescription) {
		this.ruleValueDescription = ruleValueDescription;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}
