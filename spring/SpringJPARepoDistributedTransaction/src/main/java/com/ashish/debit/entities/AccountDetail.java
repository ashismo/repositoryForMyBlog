package com.ashish.debit.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the account_details database table.
 * 
 */
@Entity
@Table(name="account_details")
@NamedQuery(name="AccountDetail.findAll", query="SELECT a FROM AccountDetail a")
public class AccountDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACCOUNT_ID")
	private int accountId;

	@Column(name="CURRENT_BAL")
	private double currentBal;

	@Column(name="NEW_BAL")
	private double newBal;

	@Column(name="TRANSFER_AMT")
	private double transferAmt;

	//bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserDetail userDetail;

	public AccountDetail() {
	}

	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getCurrentBal() {
		return this.currentBal;
	}

	public void setCurrentBal(double currentBal) {
		this.currentBal = currentBal;
	}

	public double getNewBal() {
		return this.newBal;
	}

	public void setNewBal(double newBal) {
		this.newBal = newBal;
	}

	public double getTransferAmt() {
		return this.transferAmt;
	}

	public void setTransferAmt(double transferAmt) {
		this.transferAmt = transferAmt;
	}

	public UserDetail getUserDetail() {
		return this.userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

}