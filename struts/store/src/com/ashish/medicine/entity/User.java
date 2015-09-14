package com.ashish.medicine.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQueries ({
	@NamedQuery(name="getUserDetailsByUserName",
			query="select u from User u " +
					"where u.userName=:userName"
	),
	@NamedQuery(name="getUserAccountDetails",
			query="select u.userId as userId, u.emailId as emailId, " +
					"u.userName as userName, u.password as password, " +
					"u.address as address, u.mobile as mobile," +
					"u.phone as phone, u.description as description, " +
					"u.name as name, u.password as password, u.role as role " +
					"from User u where u.userName = :userName"
	),
	@NamedQuery(name="searchUsers",
			query="select u from User u " +
					"where u.userName like :userName and " +
					"upper(u.role) like :role"
	),
	@NamedQuery(name="getUserByUserId",
			query="select u from User u where u.userId = :userId"
	),
	@NamedQuery(name="getAllUsers",
			query="select u.userId as userId, u.userName as userName from User u order by u.userName"
	),
	@NamedQuery(name="getUserByUserName",
			query="select u from User u where u.userName = :userName"
	),
	@NamedQuery(name="getAllStandardSecurityQuestions",
			query="select sec.questionId as questionId, " +
					"sec.question as question from SequrityQuestion sec order by sec.questionId"
	),
	@NamedQuery(name="getSecurityQuestionsByQuestionId",
			query="select sec from SequrityQuestion sec " +
					"where sec.questionId= :questionId"
	),
	@NamedQuery(name="getAllSecurityQuestionsEntity",
			query="select sec from SequrityQuestion sec order by sec.questionId"
	),
	@NamedQuery(name="getAllSecurityAnswersByUserId",
			query="select uSec.answer as answer from UserSecurity uSec " +
					"where uSec.user.userId =:userId order by uSec.sequrityQuestion.questionId"
	),
	@NamedQuery(name="getAllSecurityAnswersEntityByUserId",
			query="select uSec from UserSecurity uSec " +
					"where uSec.user.userId =:userId order by uSec.sequrityQuestion.questionId"
	)
})

@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private int userId;

	@Column(name="ADDRESS")
	private String address;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="EMAIL_ID")
	private String emailId;

	@Column(name="MOBILE")
	private String mobile;

	@Column(name="NAME")
	private String name;

	@Column(name="PASSWORD")
	private String password;

	@Column(name="PHONE")
	private String phone;

	@Column(name="ROLE")
	private String role;

	@Column(name="USER_NAME")
	private String userName;

	//bi-directional many-to-one association to UserSecurity
	@OneToMany(mappedBy="user")
	private Set<UserSecurity> userSecurities;

    public User() {
    }

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getDbAddTs() {
		return this.dbAddTs;
	}

	public void setDbAddTs(Timestamp dbAddTs) {
		this.dbAddTs = dbAddTs;
	}

	public int getDbAddUser() {
		return this.dbAddUser;
	}

	public void setDbAddUser(int dbAddUser) {
		this.dbAddUser = dbAddUser;
	}

	public Timestamp getDbUpdTs() {
		return this.dbUpdTs;
	}

	public void setDbUpdTs(Timestamp dbUpdTs) {
		this.dbUpdTs = dbUpdTs;
	}

	public int getDbUpdUser() {
		return this.dbUpdUser;
	}

	public void setDbUpdUser(int dbUpdUser) {
		this.dbUpdUser = dbUpdUser;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set<UserSecurity> getUserSecurities() {
		return this.userSecurities;
	}

	public void setUserSecurities(Set<UserSecurity> userSecurities) {
		this.userSecurities = userSecurities;
	}
	
}