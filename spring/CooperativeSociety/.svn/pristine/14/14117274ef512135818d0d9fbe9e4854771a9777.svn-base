package com.org.coop.society.data.customer.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_address database table.
 * 
 */
@Embeddable
public class UserAddressPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private int userId;

	@Column(name="address_id")
	private int addressId;

	public UserAddressPK() {
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
		if (!(other instanceof UserAddressPK)) {
			return false;
		}
		UserAddressPK castOther = (UserAddressPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.addressId == castOther.addressId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.addressId;
		
		return hash;
	}
}