package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the medicine database table.
 * 
 */
@Entity
@Table(name="medicine")
public class Medicine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MEDICINE_ID", unique=true, nullable=false)
	private int medicineId;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

	@Column(name="MEDICINE_DESC", length=255)
	private String medicineDesc;

	@Column(name="MEDICINE_NAME", length=100)
	private String medicineName;

	//bi-directional many-to-one association to Company
    @ManyToOne
	@JoinColumn(name="COMPANY_ID")
	private Company company;

	//bi-directional many-to-one association to MedicineDetail
	@OneToMany(mappedBy="medicine")
	private Set<MedicineDetail> medicineDetails;

    public Medicine() {
    }

	public int getMedicineId() {
		return this.medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
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

	public String getMedicineDesc() {
		return this.medicineDesc;
	}

	public void setMedicineDesc(String medicineDesc) {
		this.medicineDesc = medicineDesc;
	}

	public String getMedicineName() {
		return this.medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Set<MedicineDetail> getMedicineDetails() {
		return this.medicineDetails;
	}

	public void setMedicineDetails(Set<MedicineDetail> medicineDetails) {
		this.medicineDetails = medicineDetails;
	}
	
}