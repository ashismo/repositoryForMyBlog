package com.ashish.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "EMPLOYEE_ALLOCATION", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ID") })
public class EmployeeAllocationEntity implements Serializable {
	private static final long serialVersionUID = -1798070786993154676L;
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Integer allocationId;
	@Column(name = "ALLOCATION_NAME", unique = true, nullable = false, length = 100)
	private String allocationName;
	@ManyToOne
	@JoinColumn(name="employeeId")
	private EmployeeEntity empEntity;
	
	public EmployeeAllocationEntity(int allocationId, String allocationName, EmployeeEntity emp) {
		this.allocationId = allocationId;
		this.allocationName = allocationName;
		this.empEntity = emp;
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
	/**
	 * @return the empEntity
	 */
	public EmployeeEntity getEmpEntity() {
		return empEntity;
	}
	/**
	 * @param empEntity the empEntity to set
	 */
	public void setEmpEntity(EmployeeEntity empEntity) {
		this.empEntity = empEntity;
	}
	
}