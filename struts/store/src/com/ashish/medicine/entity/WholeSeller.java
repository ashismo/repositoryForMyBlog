package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;

@NamedQueries ({
	@NamedQuery(name="searchWholeSeller",
			query="select ws from WholeSeller ws " +
					"where upper(ws.wholesellerName) like :wholesellerName and " +
					"upper(ws.pin) like :pin and " +
					"upper(ws.state) like :state"
	),
	@NamedQuery(name="countWholeSeller",
			query="select count(*) as total from WholeSeller ws " +
			"where upper(ws.wholesellerName) like :wholesellerName and " +
			"upper(ws.pin) like :pin and " +
			"upper(ws.state) like :state"
	),
	@NamedQuery(name="getWholeSellerByWholeSellerId",
			query="select ws from WholeSeller ws where ws.wholesellerId = :wholesellerId"
	),
	@NamedQuery(name="getWholeSellerByWholeSellerName",
			query="select ws from WholeSeller ws where upper(ws.wholesellerName) = :wholesellerName"
	),
	@NamedQuery(name="getAllWholesellers",
			query="select ws.wholesellerId as wholesellerId, ws.wholesellerName as wholesellerName " +
					"from WholeSeller ws order by ws.wholesellerName"
	)
})

/**
 * The persistent class for the whole_seller database table.
 * 
 */
@Entity
@Table(name="whole_seller")
public class WholeSeller implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="WHOLESELLER_ID", unique=true, nullable=false)
	private int wholesellerId;

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

	@Column(name="WHOLESELLER_ADDR1", length=100)
	private String wholesellerAddr1;

	@Column(name="WHOLESELLER_ADDR2", length=100)
	private String wholesellerAddr2;

	@Column(name="WHOLESELLER_DESC", length=255)
	private String wholesellerDesc;

	@Column(name="WHOLESELLER_NAME", length=100)
	private String wholesellerName;

	//bi-directional many-to-one association to MedRep
	@OneToMany(mappedBy="wholeSeller")
	private Set<MedRep> medReps;

	//bi-directional many-to-one association to Schedule
	@OneToMany(mappedBy="wholeSeller")
	private Set<Schedule> schedules;

    public WholeSeller() {
    }

	public int getWholesellerId() {
		return this.wholesellerId;
	}

	public void setWholesellerId(int wholesellerId) {
		this.wholesellerId = wholesellerId;
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

	public String getWholesellerAddr1() {
		return this.wholesellerAddr1;
	}

	public void setWholesellerAddr1(String wholesellerAddr1) {
		this.wholesellerAddr1 = wholesellerAddr1;
	}

	public String getWholesellerAddr2() {
		return this.wholesellerAddr2;
	}

	public void setWholesellerAddr2(String wholesellerAddr2) {
		this.wholesellerAddr2 = wholesellerAddr2;
	}

	public String getWholesellerDesc() {
		return this.wholesellerDesc;
	}

	public void setWholesellerDesc(String wholesellerDesc) {
		this.wholesellerDesc = wholesellerDesc;
	}

	public String getWholesellerName() {
		return this.wholesellerName;
	}

	public void setWholesellerName(String wholesellerName) {
		this.wholesellerName = wholesellerName;
	}

	public Set<MedRep> getMedReps() {
		return this.medReps;
	}

	public void setMedReps(Set<MedRep> medReps) {
		this.medReps = medReps;
	}
	
	public Set<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
	}
	
}