package com.org.coop.society.data.customer.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;

	@Column(name="create_date", updatable=false)
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	private String email1;

	private String email2;

	@Column(name="end_date")
	private Timestamp endDate;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="middle_name")
	private String middleName;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private String modifyUser;

	private String phone1;

	private String phone2;

	@Column(name="start_date")
	private Timestamp startDate;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-one association to BranchMaster
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchMaster branchMaster;

	//bi-directional one-to-one association to UserCredential
	@OneToOne(mappedBy="user")
	private UserCredential userCredential;

	//bi-directional one-to-one association to UserCredentialOtp
	@OneToOne(mappedBy="user")
	private UserCredentialOtp userCredentialOtp;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;

	//bi-directional many-to-one association to UserSequrityQuestion
	@OneToMany(mappedBy="user")
	private List<UserSequrityQuestion> userSequrityQuestions;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getEmail1() {
		return this.email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return this.email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
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

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
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

	public BranchMaster getBranchMaster() {
		return this.branchMaster;
	}

	public void setBranchMaster(BranchMaster branchMaster) {
		this.branchMaster = branchMaster;
	}

	public UserCredential getUserCredential() {
		return this.userCredential;
	}

	public void setUserCredential(UserCredential userCredential) {
		this.userCredential = userCredential;
	}

	public UserCredentialOtp getUserCredentialOtp() {
		return this.userCredentialOtp;
	}

	public void setUserCredentialOtp(UserCredentialOtp userCredentialOtp) {
		this.userCredentialOtp = userCredentialOtp;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

	public List<UserSequrityQuestion> getUserSequrityQuestions() {
		return this.userSequrityQuestions;
	}

	public void setUserSequrityQuestions(List<UserSequrityQuestion> userSequrityQuestions) {
		this.userSequrityQuestions = userSequrityQuestions;
	}

	public UserSequrityQuestion addUserSequrityQuestion(UserSequrityQuestion userSequrityQuestion) {
		getUserSequrityQuestions().add(userSequrityQuestion);
		userSequrityQuestion.setUser(this);

		return userSequrityQuestion;
	}

	public UserSequrityQuestion removeUserSequrityQuestion(UserSequrityQuestion userSequrityQuestion) {
		getUserSequrityQuestions().remove(userSequrityQuestion);
		userSequrityQuestion.setUser(null);

		return userSequrityQuestion;
	}

}