package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the customer database table.
 * 
 */
@NamedQueries ({
	@NamedQuery(name="getAllCustomers",
			query="select c.customerId as customerId,c.customerName as customerName from Customer c"
	),
	@NamedQuery(name="searchCustomers",
			query="select c from Customer c " +
					"where upper(c.customerName) like :customerName" +
					" and upper(c.customerAddr1) like :customerAddr1"
	),
	@NamedQuery(name="countCustomers",
			query="select count(*) as total from Customer c " +
			"where upper(c.customerName) like :customerName" +
			" and upper(c.customerAddr1) like :customerAddr1"
	),
	@NamedQuery(name="getCustomerDetailsByCustomerId",
			query="select c from Customer c where c.customerId = :customerId"
	),
	@NamedQuery(name="getCustomerByCustomerName",
			query="select c from Customer c where upper(c.customerName) = :customerName"
	),
	@NamedQuery(name="getCustomerByCustomerId",
			query="select c.customerId as customerId," +
					"c.customerName as customerName," +
					"c.customerAddr1 as customerAddr1," +
					"c.mob1 as mob1 from Customer c " +
					"where c.customerId = :customerId"
	)
})
@Entity
@Table(name="customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CUSTOMER_ID", unique=true, nullable=false)
	private int customerId;

	@Column(name="CUSTOMER_ADDR1", length=100)
	private String customerAddr1;

	@Column(name="CUSTOMER_ADDR2", length=100)
	private String customerAddr2;

	@Column(name="CUSTOMER_DESC", length=255)
	private String customerDesc;

	@Column(name="CUSTOMER_NAME", length=50)
	private String customerName;

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

	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="customer")
	private Set<Invoice> invoices;

	//bi-directional many-to-one association to Schedule
	@OneToMany(mappedBy="customer")
	private Set<Schedule> schedules;

    public Customer() {
    }

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerAddr1() {
		return this.customerAddr1;
	}

	public void setCustomerAddr1(String customerAddr1) {
		this.customerAddr1 = customerAddr1;
	}

	public String getCustomerAddr2() {
		return this.customerAddr2;
	}

	public void setCustomerAddr2(String customerAddr2) {
		this.customerAddr2 = customerAddr2;
	}

	public String getCustomerDesc() {
		return this.customerDesc;
	}

	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	public Set<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
	}
	
}