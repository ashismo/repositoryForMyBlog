package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the doctors database table.
 * 
 */

@NamedQueries ({
	@NamedQuery(name="getAllDoctors",
			query="select d.doctorId as doctorId, d.doctorName as doctorName from Doctor d"
	),
	@NamedQuery(name="getDoctorByDoctorId",
			query="select d.doctorId as doctorId," +
					"d.doctorName as doctorName," +
					"d.doctorAddr1 as doctorAddr1," +
					"d.mob1 as mob1 from Doctor d " +
					"where d.doctorId = :doctorId"
	),
	@NamedQuery(name="searchDoctors",
			query="select d.doctorName as doctorName," +
					"d.doctorId as doctorId," +
					"d.doctorAddr1 as doctorAddr1," +
					"d.doctorAddr2 as doctorAddr2," +
					"d.state as state," +
					"d.pin as pin," +
					"d.fax as fax," +
					"d.phone1 as phone1," +
					"d.phone2 as phone2," +
					"d.mob1 as mob1," +
					"d.mob2 as mob2," +
					"d.dateOfAssociation as dateOfAssociationActual," +
					"d.dateOfRelease as dateOfReleaseActual," +
					"d.isActive as isActive," +
					"d.website as website," +
					"d.emailId as emailId," +
					"d.qualification as qualification," +
					"d.speciality as speciality," +
					"d.doctorDesc as doctorDesc from Doctor d " +
					"where upper(d.doctorName) like :doctorName" 
	),
	@NamedQuery(name="countDoctors",
			query="select count(*) as total from Doctor c " +
			"where upper(c.doctorName) like :doctorName"
	),
	@NamedQuery(name="getDoctorByDoctorName",
			query="select d from Doctor d where upper(d.doctorName) = :doctorName"
	),
	@NamedQuery(name="getDoctorDetailsByDoctorId",
			query="select d from Doctor d where d.doctorId = :doctorId"
	),
	@NamedQuery(name="getDoctorDetailsByDoctorId1",
			query="select d.doctorName as doctorName," +
					"d.doctorId as doctorId," +
					"d.doctorAddr1 as doctorAddr1," +
					"d.doctorAddr2 as doctorAddr2," +
					"d.state as state," +
					"d.pin as pin," +
					"d.fax as fax," +
					"d.phone1 as phone1," +
					"d.phone2 as phone2," +
					"d.mob1 as mob1," +
					"d.mob2 as mob2," +
					"d.dateOfAssociation as dateOfAssociationActual," +
					"d.dateOfRelease as dateOfReleaseActual," +
					"d.website as website," +
					"d.emailId as emailId," +
					"d.qualification as qualification," +
					"d.speciality as speciality," +
					"d.doctorDesc as doctorDesc " +
					"from Doctor d where d.doctorId = :doctorId"
	)
})
@Entity
@Table(name="doctors")
public class Doctor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DOCTOR_ID", unique=true, nullable=false)
	private int doctorId;

    @Temporal( TemporalType.DATE)
	@Column(name="DATE_OF_ASSOCIATION")
	private Date dateOfAssociation;

    @Temporal( TemporalType.DATE)
	@Column(name="DATE_OF_RELEASE")
	private Date dateOfRelease;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

	@Column(name="DOCTOR_ADDR1", length=100)
	private String doctorAddr1;

	@Column(name="DOCTOR_ADDR2", length=100)
	private String doctorAddr2;

	@Column(name="DOCTOR_DESC", length=255)
	private String doctorDesc;

	@Column(name="DOCTOR_NAME", length=100)
	private String doctorName;

	@Column(name="EMAIL_ID", length=50)
	private String emailId;

	@Column(name="FAX", length=20)
	private String fax;

	@Column(name="IS_ACTIVE", length=10)
	private String isActive;

	@Column(name="MOB1", length=20)
	private String mob1;

	@Column(name="MOB2", length=20)
	private String mob2;

	@Column(name="PHONE1", length=20)
	private String phone1;

	@Column(name="PHONE2", length=20)
	private String phone2;

	@Column(name="PIN", length=10)
	private String pin;

	@Column(name="QUALIFICATION", length=100)
	private String qualification;

	@Column(name="REG_NO", length=100)
	private String regNo;

	@Column(name="SPECIALITY", length=100)
	private String speciality;

	@Column(name="STATE", length=50)
	private String state;

	@Column(name="WEBSITE", length=100)
	private String website;

	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="doctor")
	private Set<Invoice> invoices;

	//bi-directional many-to-one association to Schedule
	@OneToMany(mappedBy="doctor")
	private Set<Schedule> schedules;

    public Doctor() {
    }

	public int getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public Date getDateOfAssociation() {
		return this.dateOfAssociation;
	}

	public void setDateOfAssociation(Date dateOfAssociation) {
		this.dateOfAssociation = dateOfAssociation;
	}

	public Date getDateOfRelease() {
		return this.dateOfRelease;
	}

	public void setDateOfRelease(Date dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
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

	public String getDoctorAddr1() {
		return this.doctorAddr1;
	}

	public void setDoctorAddr1(String doctorAddr1) {
		this.doctorAddr1 = doctorAddr1;
	}

	public String getDoctorAddr2() {
		return this.doctorAddr2;
	}

	public void setDoctorAddr2(String doctorAddr2) {
		this.doctorAddr2 = doctorAddr2;
	}

	public String getDoctorDesc() {
		return this.doctorDesc;
	}

	public void setDoctorDesc(String doctorDesc) {
		this.doctorDesc = doctorDesc;
	}

	public String getDoctorName() {
		return this.doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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

	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getRegNo() {
		return this.regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
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