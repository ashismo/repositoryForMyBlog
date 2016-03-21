package com.org.coop.society.data.admin.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


/**
 * The persistent class for the branch_master database table.
 * 
 */
@Entity
@NamedNativeQueries(
		@NamedNativeQuery(name="CustomBranchModules.branchModules", 
								query="select distinct mm.module_id moduleId, mm.module_name moduleName, " +
												"mm.start_date startDate, mm.end_date endDate, br.branch_id branchId " +
												"from branch_rule br " +
												"inner join rule_master rm on br.rule_id=rm.rule_id " +
												"inner join module_master mm on mm.module_id = rm.module_id " +
											"where br.branch_id = :branchId", 
		resultClass=CustomBranchModule.class)
)
public class CustomBranchModule implements Serializable {
	@Id
	protected int moduleId;
	protected int branchId;
	protected String moduleName;
	protected Date startDate;
	protected Date endDate;
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + moduleId;
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
		CustomBranchModule other = (CustomBranchModule) obj;
		if (moduleId != other.moduleId)
			return false;
		return true;
	}
	
}