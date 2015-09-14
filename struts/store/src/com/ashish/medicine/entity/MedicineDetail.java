package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the medicine_details database table.
 * 
 */
@NamedQueries ({
	@NamedQuery(name="searchMedicines",
			query="select md.medicine.medicineId as medicineId, " +
					"md.medicine.medicineName as medicineName, md.soldoutUnitPrice as soldoutUnitPrice," +
					"md.medicineDesc as medicineDesc, md.purchaseDate as purchaseDateActual," +
					"md.medicineDetailsId as medicineDetailsId, md.batchName as batchName, " +
					"md.medicine.company.companyName as companyName, md.mfgDate as mfgDateActual, " +
					"md.medicine.company.companyId as companyId, md.unitPrice as unitPrice," +
					"md.expDate as expDateActual, md.medDose as medDose, " +
					"md.medType as medType, md.medWeight as medWeight,  " +
					"md.stock as stock, (md.stock - md.soldoutStock) as availableStock," +
					"md.medRep.medRepName as medRepName, md.medRep.wholeSeller.wholesellerName as wholesellerName," +
					"md.medRep.medRepId as medRepId, md.medRep.wholeSeller.wholesellerId as wholesellerId " +
					"from MedicineDetail md " +
					"where upper(md.medicine.medicineName) like :medicineName and " +
					"upper(md.medicine.company.companyName) like :companyName and " +
					"upper(md.batchName) like :batchName and " +
					"md.expDate between :expDate1 and :expDate2 and " +
					"md.purchaseDate between :startDate and :endDate and " +
					"md.medType like :medType " +
//					"and md.expDate > case when :expiringAfter = null then md.expDate else :expiringAfter end " +
					"order by md.medicine.medicineName"
	),
	@NamedQuery(name="countTotalMedicineStock",
			query="select sum(md.stock) as totalStock, sum(md.stock - md.soldoutStock) as totalAvailableStock " +
					"from MedicineDetail md " +
					"where upper(md.medicine.medicineName) like :medicineName and " +
					"upper(md.medicine.company.companyName) like :companyName and " +
					"upper(md.batchName) like :batchName and " +
					"md.expDate between :expDate1 and :expDate2 and " + 
					"md.purchaseDate between :startDate and :endDate and " +
					"md.medType like :medType "
	),
	@NamedQuery(name="getMedicineDetailsByMedicineBatchAndMedicineId",
			query="select md.medicine.medicineId as medicineId, " +
					"md.medicine.medicineName as medicineName, md.purchaseDate as purchaseDateActual," +
					"md.medicineDesc as medicineDesc, md.medDose as medDose, " +
					"md.medType as medType, md.medWeight as medWeight, " +
					"md.stock as stock, (md.stock - md.soldoutStock) as availableStock, " +
					"md.medicineDetailsId as medicineDetailsId, md.batchName as batchName, " +
					"md.medicine.company.companyName as companyName, md.mfgDate as mfgDateActual, " +
					"md.medicine.company.companyId as companyId, md.soldoutUnitPrice as soldoutUnitPrice, " +
					"md.expDate as expDateActual, md.batchName as batchId, md.medType as medType, " +
					"md.medRep.medRepName as medRepName, md.medRep.wholeSeller.wholesellerName as wholesellerName," +
					"md.medRep.medRepId as medRepId, md.medRep.wholeSeller.wholesellerId as wholesellerId " +
					"from MedicineDetail md " +
					"where md.batchName = :batchName and md.medicine.medicineId = :medicineId " +
					"order by md.medicine.medicineName"
	),
	@NamedQuery(name="countMedicines",
			query="select count(*) as total from MedicineDetail md " +
			"where upper(md.medicine.medicineName) like :medicineName and " +
			"upper(md.medicine.company.companyName) like :companyName and " +
			"upper(md.batchName) like :batchName and " +
			"md.expDate between :expDate1 and :expDate2 and " +
			"md.purchaseDate between :startDate and :endDate"
	),
	@NamedQuery(name="getMedicineByMedicineDetailsId",
			query="select md.medicine.medicineId as medicineId, " +
					"md.medicine.medicineName as medicineName, md.soldoutUnitPrice as soldoutUnitPrice," +
					"md.medicineDesc as medicineDesc, md.purchaseDate as purchaseDateActual," +
					"md.medicineDetailsId as medicineDetailsId, md.batchName as batchId, " +
					"md.medicine.company.companyName as companyName, md.mfgDate as mfgDateActual, " +
					"md.medicine.company.companyId as companyId," +
					"md.expDate as expDateActual, md.stock as stock, (md.stock - md.soldoutStock) as availableStock, " +
					"md.unitPrice as unitPrice, md.medDose as medDose, " +
					"medType as medType, medWeight as medWeight, " +
					"md.medRep.medRepName as medRepName, md.medRep.wholeSeller.wholesellerName as wholesellerName," +
					"md.medRep.medRepId as medRepId, md.medRep.wholeSeller.wholesellerId as wholesellerId " +
					"from MedicineDetail md " +
					"where md.medicineDetailsId = :medicineDetailsId order by md.medicine.medicineName"
	),
	@NamedQuery(name="getAllMedicines",
			query="select m.medicineId as medicineId, m.medicineName as medicineName from Medicine m order by m.medicineName"
	),
	@NamedQuery(name="getMedicineByMedicineName",
			query="select m from Medicine m where upper(m.medicineName) = :medicineName"
	),
	@NamedQuery(name="getMedicineByMedicineNameAndCompany",
			query="select m from Medicine m where upper(m.medicineName) = :medicineName and m.company.companyId <> :companyId"
	),
	@NamedQuery(name="getMedicineByMedicineId",
			query="select m from Medicine m where m.medicineId = :medicineId"
	),
	@NamedQuery(name="getMedicineDetailsByMedicineDetailsId",
			query="select md from MedicineDetail md where md.medicineDetailsId = :medicineDetailsId"
	),
	@NamedQuery(name="getMedicineByCompanyId",
			query="select m.medicineName as medicineName, m.medicineId as medicineId " +
					"from Medicine m where m.company.companyId = :companyId order by m.medicineName"
	),
	@NamedQuery(name="getAllMedicineBatches",
			query="select distinct(md.batchName) as batchName from MedicineDetail md where md.batchName is not null order by md.batchName"
	),
	@NamedQuery(name="getMedicineDetailsByMedicineId",
			query="select distinct(md.batchName) as batchName from MedicineDetail md " +
					"where md.batchName is not null and md.expDate > :today and md.medicine.medicineId = :medicineId " +
					"order by md.batchName"
	)
})
@Entity
@Table(name="medicine_details")
public class MedicineDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MEDICINE_DETAILS_ID", unique=true, nullable=false)
	private int medicineDetailsId;

	@Column(name="BATCH_NAME", length=50)
	private String batchName;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

    @Temporal( TemporalType.DATE)
	@Column(name="EXP_DATE")
	private Date expDate;

	@Column(name="MED_DOSE", length=50)
	private String medDose;

	@Column(name="MED_TYPE", length=50)
	private String medType;

	@Column(name="MED_WEIGHT", length=50)
	private String medWeight;

	@Column(name="MEDICINE_DESC", length=255)
	private String medicineDesc;

    @Temporal( TemporalType.DATE)
	@Column(name="MFG_DATE")
	private Date mfgDate;

    @Temporal( TemporalType.DATE)
	@Column(name="PURCHASE_DATE")
	private Date purchaseDate;

	@Column(name="SOLDOUT_STOCK")
	private int soldoutStock;

	@Column(name="SOLDOUT_UNIT_PRICE")
	private double soldoutUnitPrice;

	@Column(name="STOCK")
	private int stock;

	@Column(name="UNIT_PRICE")
	private double unitPrice;

	//bi-directional many-to-one association to MedRep
    @ManyToOne
	@JoinColumn(name="MED_REP_ID")
	private MedRep medRep;

	//bi-directional many-to-one association to Medicine
    @ManyToOne
	@JoinColumn(name="MEDICINE_ID")
	private Medicine medicine;

	//bi-directional many-to-one association to InvoiceDetail
	@OneToMany(mappedBy="medicineDetail")
	private Set<InvoiceDetail> invoiceDetails;

    public MedicineDetail() {
    }

	public int getMedicineDetailsId() {
		return this.medicineDetailsId;
	}

	public void setMedicineDetailsId(int medicineDetailsId) {
		this.medicineDetailsId = medicineDetailsId;
	}

	public String getBatchName() {
		return this.batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
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

	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getMedDose() {
		return this.medDose;
	}

	public void setMedDose(String medDose) {
		this.medDose = medDose;
	}

	public String getMedType() {
		return this.medType;
	}

	public void setMedType(String medType) {
		this.medType = medType;
	}

	public String getMedWeight() {
		return this.medWeight;
	}

	public void setMedWeight(String medWeight) {
		this.medWeight = medWeight;
	}

	public String getMedicineDesc() {
		return this.medicineDesc;
	}

	public void setMedicineDesc(String medicineDesc) {
		this.medicineDesc = medicineDesc;
	}

	public Date getMfgDate() {
		return this.mfgDate;
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getSoldoutStock() {
		return this.soldoutStock;
	}

	public void setSoldoutStock(int soldoutStock) {
		this.soldoutStock = soldoutStock;
	}

	public double getSoldoutUnitPrice() {
		return this.soldoutUnitPrice;
	}

	public void setSoldoutUnitPrice(double soldoutUnitPrice) {
		this.soldoutUnitPrice = soldoutUnitPrice;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public MedRep getMedRep() {
		return this.medRep;
	}

	public void setMedRep(MedRep medRep) {
		this.medRep = medRep;
	}
	
	public Medicine getMedicine() {
		return this.medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	
	public Set<InvoiceDetail> getInvoiceDetails() {
		return this.invoiceDetails;
	}

	public void setInvoiceDetails(Set<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}
	
}