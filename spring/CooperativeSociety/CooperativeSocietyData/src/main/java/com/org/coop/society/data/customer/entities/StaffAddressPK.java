package com.org.coop.society.data.customer.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the staff_address database table.
 * 
 */
@Embeddable
public class StaffAddressPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="staff_id")
	private int staffId;

	@Column(name="address_id")
	private int addressId;

	public StaffAddressPK() {
	}
	public int getStaffId() {
		return this.staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public int getAddressId() {
		return this.addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StaffAddressPK)) {
			return false;
		}
		StaffAddressPK castOther = (StaffAddressPK)other;
		return 
			(this.staffId == castOther.staffId)
			&& (this.addressId == castOther.addressId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.staffId;
		hash = hash * prime + this.addressId;
		
		return hash;
	}
}