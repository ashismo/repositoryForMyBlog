package com.ashish.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "ALLOCATION")
public class AllocationEntity implements Serializable {
	private static final long serialVersionUID = -1798070786993154676L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer allocationId;
	@Column(name = "ALLOCATION_NAME", unique = true, nullable = false, length = 100)
	private String allocationName;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "employeeId")
	private Set<EmployeeEntity> employee = new HashSet<EmployeeEntity>(0);
	
	public AllocationEntity(String allocationName) {
		this.allocationName = allocationName;
	}
	/**
	 * @return the allocationId
	 */
	public Integer getAllocationId() {
		return allocationId;
	}

	/**
	 * @param allocationId
	 *            the allocationId to set
	 */
	public void setAllocationId(Integer allocationId) {
		this.allocationId = allocationId;
	}

	/**
	 * @return the allocationName
	 */
	public String getAllocationName() {
		return allocationName;
	}

	/**
	 * @param allocationName
	 *            the allocationName to set
	 */
	public void setAllocationName(String allocationName) {
		this.allocationName = allocationName;
	}
	public Set<EmployeeEntity> getEmployee() {
		return employee;
	}
	public void setEmployee(Set<EmployeeEntity> employee) {
		this.employee = employee;
	}
}