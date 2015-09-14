package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@NamedQueries ({
	@NamedQuery(name="searchMedReps",
			query="select mr from MedRep mr " +
					"where upper(mr.medRepName) like :medRepName and " +
					"upper(mr.wholeSeller.wholesellerName) like :wholesellerName"
	),
	@NamedQuery(name="countMedReps",
			query="select count(*) as total from MedRep mr " +
			"where upper(mr.medRepName) like :medRepName and " +
			"upper(mr.wholeSeller.wholesellerName) like :wholesellerName"
	),
	@NamedQuery(name="getMedRepByMedRepId",
			query="select mr from MedRep mr where mr.medRepId = :medRepId"
	),
	@NamedQuery(name="viewMedRepByMedRepId",
			query="select mr.medRepId as medRepId, mr.medRepName as medRepName, " +
					"mr.dateOfAssociation as dateOfAssociationActual," +
					"mr.medRepAddr1 as medRepAddr1, mr.medRepAddr2 as medRepAddr2, " +
					"mr.pin as pin, mr.medRepDesc as medRepDesc, mr.fax as fax, " +
					"mr.email as email, mr.mob1 as mob1, mr.mob2 as mob2," +
					"mr.phone1 as phone1, mr.phone2 as phone2,mr.panNo as panNo," +
					"mr.state as state, mr.voterIdNo as voterIdNo," +
					"mr.wholeSeller.wholesellerId as wholesellerId from MedRep mr where mr.medRepId = :medRepId"
	),
	@NamedQuery(name="getMedRepByMedRepName",
			query="select c from MedRep c where upper(c.medRepName) = :medRepName"
	),
	@NamedQuery(name="getMedRepByWholeSellerId",
			query="select mr.medRepId as medRepId, mr.medRepName as medRepName from MedRep mr " +
					"where mr.wholeSeller.wholesellerId = :wholesellerId"
	)
})

/**
 * The persistent class for the med_rep database table.
 * 
 */
@Entity
@Table(name="med_rep")
public class MedRep implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MED_REP_ID", unique=true, nullable=false)
	private int medRepId;

    @Temporal( TemporalType.DATE)
	@Column(name="DATE_OF_ASSOCIATION")
	private Date dateOfAssociation;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

	@Column(name="EMAIL", length=50)
	private String email;

	@Column(name="FAX", length=20)
	private String fax;

	@Column(name="MED_REP_ADDR1", length=100)
	private String medRepAddr1;

	@Column(name="MED_REP_ADDR2", length=100)
	private String medRepAddr2;

	@Column(name="MED_REP_DESC", length=255)
	private String medRepDesc;

	@Column(name="MED_REP_NAME", length=100)
	private String medRepName;

	@Column(name="MOB1", length=20)
	private String mob1;

	@Column(name="MOB2", length=20)
	private String mob2;

	@Column(name="PAN_NO", length=20)
	private String panNo;

	@Column(name="PHONE1", length=20)
	private String phone1;

	@Column(name="PHONE2", length=20)
	private String phone2;

	@Column(name="PIN", length=20)
	private String pin;

	@Column(name="STATE", length=20)
	private String state;

	@Column(name="VOTER_ID_NO", length=20)
	private String voterIdNo;

	//bi-directional many-to-one association to WholeSeller
    @ManyToOne
	@JoinColumn(name="WHOLESELLER_ID")
	private WholeSeller wholeSeller;

	//bi-directional many-to-one association to MedicineDetail
	@OneToMany(mappedBy="medRep")
	private Set<MedicineDetail> medicineDetails;

	//bi-directional many-to-one association to TransactionDetail
	@OneToMany(mappedBy="medRep")
	private Set<TransactionDetails> transactionDetails;

	//bi-directional many-to-one association to Schedule
	@OneToMany(mappedBy="medRep")
	private Set<Schedule> schedules;

    public MedRep() {
    }

	public int getMedRepId() {
		return this.medRepId;
	}

	public void setMedRepId(int medRepId) {
		this.medRepId = medRepId;
	}

	public Date getDateOfAssociation() {
		return this.dateOfAssociation;
	}

	public void setDateOfAssociation(Date dateOfAssociation) {
		this.dateOfAssociation = dateOfAssociation;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMedRepAddr1() {
		return this.medRepAddr1;
	}

	public void setMedRepAddr1(String medRepAddr1) {
		this.medRepAddr1 = medRepAddr1;
	}

	public String getMedRepAddr2() {
		return this.medRepAddr2;
	}

	public void setMedRepAddr2(String medRepAddr2) {
		this.medRepAddr2 = medRepAddr2;
	}

	public String getMedRepDesc() {
		return this.medRepDesc;
	}

	public void setMedRepDesc(String medRepDesc) {
		this.medRepDesc = medRepDesc;
	}

	public String getMedRepName() {
		return this.medRepName;
	}

	public void setMedRepName(String medRepName) {
		this.medRepName = medRepName;
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

	public String getPanNo() {
		return this.panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
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

	public String getVoterIdNo() {
		return this.voterIdNo;
	}

	public void setVoterIdNo(String voterIdNo) {
		this.voterIdNo = voterIdNo;
	}

	public WholeSeller getWholeSeller() {
		return this.wholeSeller;
	}

	public void setWholeSeller(WholeSeller wholeSeller) {
		this.wholeSeller = wholeSeller;
	}
	
	public Set<MedicineDetail> getMedicineDetails() {
		return this.medicineDetails;
	}

	public void setMedicineDetails(Set<MedicineDetail> medicineDetails) {
		this.medicineDetails = medicineDetails;
	}
	
	public Set<TransactionDetails> getTransactionDetails() {
		return this.transactionDetails;
	}

	public void setTransactionDetails(Set<TransactionDetails> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
	
	public Set<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
	}
	
}