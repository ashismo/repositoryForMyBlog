package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the contacts database table.
 * 
 */
@NamedQueries ({
	@NamedQuery(name="searchContacts",
			query="select c from Contact c " +
					"where upper(c.contactName) like :contactName and " +
					"(c.contactNo1 like :contactNo1 or c.contactNo2 like :contactNo1)"
	),
	@NamedQuery(name="countContacts",
			query="select count(*) as total from Contact c " +
			"where upper(c.contactName) like :contactName and " +
			"(c.contactNo1 like :contactNo1 or c.contactNo2 like :contactNo1)"
	),
	@NamedQuery(name="getContactByContactId",
			query="select c from Contact c where c.contactId = :contactId"
	),
	@NamedQuery(name="getAllContacts",
			query="select c.contactId as contactId, c.contactName as contactName from Contact c order by c.contactName"
	),
	@NamedQuery(name="getContactByContactName",
			query="select c from Contact c where upper(c.contactName) = :contactName"
	),
	@NamedQuery(name="getContactsByContactId",
			query="select c from Contact c where c.contactId = :contactId"
	)
})

@Entity
@Table(name="contacts")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CONTACT_ID")
	private int contactId;

    @Temporal( TemporalType.DATE)
	@Column(name="CONTACT_DATE")
	private Date contactDate;

	@Column(name="CONTACT_DESC")
	private String contactDesc;

	@Column(name="CONTACT_EMAIL")
	private String contactEmail;

	@Column(name="CONTACT_FAX")
	private String contactFax;

	@Column(name="CONTACT_NAME")
	private String contactName;

	@Column(name="CONTACT_NO1")
	private String contactNo1;

	@Column(name="CONTACT_NO2")
	private String contactNo2;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

    public Contact() {
    }

	public int getContactId() {
		return this.contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public Date getContactDate() {
		return this.contactDate;
	}

	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}

	public String getContactDesc() {
		return this.contactDesc;
	}

	public void setContactDesc(String contactDesc) {
		this.contactDesc = contactDesc;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactFax() {
		return this.contactFax;
	}

	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNo1() {
		return this.contactNo1;
	}

	public void setContactNo1(String contactNo1) {
		this.contactNo1 = contactNo1;
	}

	public String getContactNo2() {
		return this.contactNo2;
	}

	public void setContactNo2(String contactNo2) {
		this.contactNo2 = contactNo2;
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

}