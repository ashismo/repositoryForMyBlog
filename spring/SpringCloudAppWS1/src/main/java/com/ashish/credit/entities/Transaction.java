package com.ashish.credit.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the transactions database table.
 * 
 */
@Entity
@Table(name="transactions")
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TX_ID")
	private int txId;

	private double balance;

	private double outstanding;

	private double paid;

	//bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserDetail userDetail;

	public Transaction() {
	}

	public int getTxId() {
		return this.txId;
	}

	public void setTxId(int txId) {
		this.txId = txId;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getOutstanding() {
		return this.outstanding;
	}

	public void setOutstanding(double outstanding) {
		this.outstanding = outstanding;
	}

	public double getPaid() {
		return this.paid;
	}

	public void setPaid(double paid) {
		this.paid = paid;
	}

	public UserDetail getUserDetail() {
		return this.userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

}