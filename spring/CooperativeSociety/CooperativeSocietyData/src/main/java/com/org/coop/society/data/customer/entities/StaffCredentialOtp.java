package com.org.coop.society.data.customer.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the staff_credential_otp database table.
 * 
 */
@Entity
@Table(name="staff_credential_otp")
@NamedQuery(name="StaffCredentialOtp.findAll", query="SELECT s FROM StaffCredentialOtp s")
public class StaffCredentialOtp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="end_date")
	private Timestamp endDate;

	private String otp;

	@Column(name="start_date")
	private Timestamp startDate;

	@Column(name="user_name")
	private String userName;

	public StaffCredentialOtp() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public String getOtp() {
		return this.otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}