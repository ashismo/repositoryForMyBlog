package com.org.coop.society.data.admin.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the company_master database table.
 * 
 */
@Entity
@Table(name="company_master")
@NamedQuery(name="CompanyMaster.findAll", query="SELECT c FROM CompanyMaster c")
public class CompanyMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="branch_id")
	private int branchId;

	@Column(name="bank_name")
	private String bankName;

	@Column(name="branch_name")
	private String branchName;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	private String email1;

	private String email2;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="ifsc_code")
	private String ifscCode;

	@Column(name="micr_code")
	private String micrCode;

	@Column(name="parent_id")
	private int parentId;

	private String phone1;

	private String phone2;

	private String remarks;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="update_date")
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to CompanyAddress
	@OneToMany(mappedBy="companyMaster")
	private List<CompanyAddress> companyAddresses;

	//bi-directional many-to-one association to RoleMaster
	@OneToMany(mappedBy="companyMaster")
	private List<RoleMaster> roleMasters;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="companyMaster")
	private List<User> users;

	public CompanyMaster() {
	}

	public int getBranchId() {
		return this.branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIfscCode() {
		return this.ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getMicrCode() {
		return this.micrCode;
	}

	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
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

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public List<CompanyAddress> getCompanyAddresses() {
		return this.companyAddresses;
	}

	public void setCompanyAddresses(List<CompanyAddress> companyAddresses) {
		this.companyAddresses = companyAddresses;
	}

	public CompanyAddress addCompanyAddress(CompanyAddress companyAddress) {
		getCompanyAddresses().add(companyAddress);
		companyAddress.setCompanyMaster(this);

		return companyAddress;
	}

	public CompanyAddress removeCompanyAddress(CompanyAddress companyAddress) {
		getCompanyAddresses().remove(companyAddress);
		companyAddress.setCompanyMaster(null);

		return companyAddress;
	}

	public List<RoleMaster> getRoleMasters() {
		return this.roleMasters;
	}

	public void setRoleMasters(List<RoleMaster> roleMasters) {
		this.roleMasters = roleMasters;
	}

	public RoleMaster addRoleMaster(RoleMaster roleMaster) {
		getRoleMasters().add(roleMaster);
		roleMaster.setCompanyMaster(this);

		return roleMaster;
	}

	public RoleMaster removeRoleMaster(RoleMaster roleMaster) {
		getRoleMasters().remove(roleMaster);
		roleMaster.setCompanyMaster(null);

		return roleMaster;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setCompanyMaster(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setCompanyMaster(null);

		return user;
	}

}