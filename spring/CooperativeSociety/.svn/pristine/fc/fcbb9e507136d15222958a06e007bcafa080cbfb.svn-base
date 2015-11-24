package com.org.coop.society.data.customer.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the staff_role database table.
 * 
 */
@Embeddable
public class StaffRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="staff_id")
	private int staffId;

	@Column(name="role_id")
	private int roleId;

	public StaffRolePK() {
	}
	public int getStaffId() {
		return this.staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public int getRoleId() {
		return this.roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StaffRolePK)) {
			return false;
		}
		StaffRolePK castOther = (StaffRolePK)other;
		return 
			(this.staffId == castOther.staffId)
			&& (this.roleId == castOther.roleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.staffId;
		hash = hash * prime + this.roleId;
		
		return hash;
	}
}