package com.ashish.debit.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_details database table.
 * 
 */
@Entity
@Table(name="user_details")
@NamedQuery(name="UserDetail.findAll", query="SELECT u FROM UserDetail u")
public class UserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private int userId;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="USER_NAME")
	private String userName;

	//bi-directional many-to-one association to AccountDetail
	@OneToMany(mappedBy="userDetail")
	private List<AccountDetail> accountDetails;

	public UserDetail() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<AccountDetail> getAccountDetails() {
		return this.accountDetails;
	}

	public void setAccountDetails(List<AccountDetail> accountDetails) {
		this.accountDetails = accountDetails;
	}

	public AccountDetail addAccountDetail(AccountDetail accountDetail) {
		getAccountDetails().add(accountDetail);
		accountDetail.setUserDetail(this);

		return accountDetail;
	}

	public AccountDetail removeAccountDetail(AccountDetail accountDetail) {
		getAccountDetails().remove(accountDetail);
		accountDetail.setUserDetail(null);

		return accountDetail;
	}

}