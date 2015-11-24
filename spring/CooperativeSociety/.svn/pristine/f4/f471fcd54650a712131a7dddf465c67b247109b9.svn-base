package com.org.coop.society.data.customer.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the staff_address database table.
 * 
 */
@Entity
@Table(name="staff_address")
@NamedQuery(name="StaffAddress.findAll", query="SELECT s FROM StaffAddress s")
public class StaffAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StaffAddressPK id;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="update_date")
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="address_id", referencedColumnName="id", insertable=false, updatable=false)
	private Address address;

	//bi-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="staff_id", referencedColumnName="id", insertable=false, updatable=false)
	private Staff staff;

	public StaffAddress() {
	}

	public StaffAddressPK getId() {
		return this.id;
	}

	public void setId(StaffAddressPK id) {
		this.id = id;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}