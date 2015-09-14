package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;


import java.sql.Timestamp;
import java.util.Set;

@NamedQueries ({
	@NamedQuery(name="searchCompanies",
			query="select c from Company c " +
					"where upper(c.companyName) like :companyName and " +
					"upper(c.pin) like :pin and " +
					"upper(c.state) like :state"
	),
	@NamedQuery(name="countCompanies",
			query="select count(*) as total from Company c " +
			"where upper(c.companyName) like :companyName and " +
			"upper(c.pin) like :pin and " +
			"upper(c.state) like :state"
	),
	@NamedQuery(name="getCompanyByCompanyId",
			query="select c from Company c where c.companyId = :companyId"
	),
	@NamedQuery(name="getAllCompanies",
			query="select c.companyId as companyId, c.companyName as companyName from Company c order by c.companyName"
	),
	@NamedQuery(name="getCompanyByCompanyName",
			query="select c from Company c where upper(c.companyName) = :companyName"
	)
})
/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@Table(name="company")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COMPANY_ID", unique=true, nullable=false)
	private int companyId;

	@Column(name="COMPANY_ADDR1", length=100)
	private String companyAddr1;

	@Column(name="COMPANY_ADDR2", length=100)
	private String companyAddr2;

	@Column(name="COMPANY_DESC", length=100)
	private String companyDesc;

	@Column(name="COMPANY_NAME", length=100)
	private String companyName;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

	@Column(name="EMAIL_ID", length=50)
	private String emailId;

	@Column(name="FAX", length=20)
	private String fax;

	@Column(name="MOB1", length=20)
	private String mob1;

	@Column(name="MOB2", length=20)
	private String mob2;

	@Column(name="PHONE1", length=20)
	private String phone1;

	@Column(name="PHONE2", length=20)
	private String phone2;

	@Column(name="PIN", length=20)
	private String pin;

	@Column(name="STATE", length=20)
	private String state;

	@Column(name="WEBSITE", length=100)
	private String website;

	//bi-directional many-to-one association to Medicine
	@OneToMany(mappedBy="company")
	private Set<Medicine> medicines;

    public Company() {
    }

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyAddr1() {
		return this.companyAddr1;
	}

	public void setCompanyAddr1(String companyAddr1) {
		this.companyAddr1 = companyAddr1;
	}

	public String getCompanyAddr2() {
		return this.companyAddr2;
	}

	public void setCompanyAddr2(String companyAddr2) {
		this.companyAddr2 = companyAddr2;
	}

	public String getCompanyDesc() {
		return this.companyDesc;
	}

	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMob1() {
		return this.mob1;
	}

	public void setMob1(String mob1) {
		this.mob1 = mob1;
	}

	public String getMob2() {
		return this.mob2;
	}

	public void setMob2(String mob2) {
		this.mob2 = mob2;
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

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Set<Medicine> getMedicines() {
		return this.medicines;
	}

	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}
	
}