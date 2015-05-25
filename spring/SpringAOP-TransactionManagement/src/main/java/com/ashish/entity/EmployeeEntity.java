package com.ashish.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "EMPLOYEE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ID"),
		@UniqueConstraint(columnNames = "EMAIL") })
public class EmployeeEntity implements Serializable {
	private static final long serialVersionUID = -1798070786993154676L;
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Integer employeeId;
	@Column(name = "EMAIL", unique = true, nullable = false, length = 100)
	private String email;
	@Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
	private String firstName;
	@Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
	private String lastName;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "allocationId")
	private Set<EmployeeAllocationEntity> empAllocations = new HashSet<EmployeeAllocationEntity>();

	public EmployeeEntity(){
		super();
	}
	public EmployeeEntity(int empId, String firstName, String lastName, String emailId) {
		this.employeeId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = emailId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<EmployeeAllocationEntity> getEmpAllocations() {
		return empAllocations;
	}

	public void setEmpAllocations(EmployeeAllocationEntity empAllocation) {
		if(this.empAllocations == null) {
			this.empAllocations = new HashSet<EmployeeAllocationEntity>();
		}
		this.empAllocations.add(empAllocation);
	}

}