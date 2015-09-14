package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the invoice_details database table.
 * 
 */
@NamedQueries ({
	@NamedQuery(name="searchInvoices",
			query="select i.invoiceId as invoiceId, " +
					"i.printIndicator as printIndicator," +
					"i.billNo as billNo," +
					"i.customer.customerName as customerName, " +
					"i.customer.customerAddr1 as customerAddr1," +
					"i.customer.customerId as customerId," +
					"i.customer.mob1 as customerMob1," +
					"i.doctor.doctorName as doctorName, " +
					"i.doctor.doctorAddr1 as doctorAddr1," +
					"i.doctor.doctorId as doctorId," +
					"i.doctor.mob1 as doctorMob1," +
					"i.purchaseDate as purchaseDateActual," +
					"i.totalAmt as grandTotalPrice," +
					"i.totalPaid as totalPaid," +
					"i.vat as vat," +
					"i.discount as discount," +
					"i.cardNumber as cardNumber," +
					"((i.totalAmt - i.totalPaid)) as due," +
					"i.paymentMode as paymentMode " +
					"from Invoice i " +
					"where upper(i.customer.customerName) like :customerName and " +
					"i.customer.mob1 like :customerMob1 and " +
					"upper(i.doctor.doctorName) like :doctorName and " +
					"i.purchaseDate between :startDate and :endDate " +
					"order by i.purchaseDate desc, i.invoiceId desc"
	),
	@NamedQuery(name="getInvoiceByInvoiceId",
			query="select i from Invoice i where i.invoiceId = :invoiceId"
	),
	@NamedQuery(name="getMaxBillNoByInvoiceId",
			query="select max(i.billNo) as billNo from Invoice i"
	),
	@NamedQuery(name="getInvoiceDetailsEntityByInvoiceId",
			query="select id from InvoiceDetail id where id.invoice.invoiceId = :invoiceId"
	),
	@NamedQuery(name="getSoldoutStockByMedicineDetailsId",
			query="select sum(id.soldoutStock) as soldoutStock from InvoiceDetail id " +
					"where id.medicineDetail.medicineDetailsId = :medicineDetailsId"
	),
	@NamedQuery(name="getInvoiceDetailsByInvoiceId",
			query="select id.invoiceDetailsId as invoiceDetailsId," +
					"id.invoice.invoiceId as invoiceId," +
					"id.invoice.printIndicator as printIndicator," +
					"id.invoice.billNo as billNo," +
					"id.medicineDetail.medicineDetailsId as medicineDetailsId," +
					"id.medicineDetail.medicine.company.companyId as companyId," +
					"id.medicineDetail.medicine.medicineId as medicineId," +
					"id.medicineDetail.batchName as batchName," +
					"id.medicineDetail.batchName as batchId," +
					"id.medicineDetail.stock as stock," +
					"(id.medicineDetail.stock - id.soldoutStock) as availableStock," +
//					"(id.medicineDetail.stock - (select sum(invDet.soldoutStock) from InvoiceDetail invDet " +
//					"where invDet.medicineDetail.medicineDetailsId = :medicineDetailsId group by invDet.soldoutStock)) as availableStock," +
					"id.medicineDetail.purchaseDate as purchaseDateActual," +
					"id.medicineDetail.unitPrice as unitPrice," +
					"id.medicineDetail.medDose as medDose," +
					"id.medicineDetail.medWeight as medWeight," +
					"id.medicineDetail.medType as medType," +
					"id.soldoutStock as soldoutStock," +
					"id.medicineDetail.medicine.medicineName as medicineName," +
					"id.medicineDetail.medicine.company.companyName as companyName," +
					"id.medicineDetail.mfgDate as mfgDateActual," +
					"concat(id.medicineDetail.mfgDate,'') as mfgDate," +
					"id.medicineDetail.expDate as expDateActual," +
					"concat(id.medicineDetail.expDate,'') as expDate," +
					"id.invoice.doctor.doctorName as doctorName," +
					"id.invoice.doctor.doctorAddr1 as doctorAddr1," +
					"id.invoice.customer.customerName as customerName," +
					"id.invoice.customer.customerAddr1 as customerAddr1," +
					"id.schedule as schedule," +
					"id.invoice.purchaseDate as billDateActual," +
					"id.invoice.vat as vat," +
					"id.invoice.discount as discount," +
					"id.invoice.cardNumber as cardNumber," +
					"id.medicineDetail.soldoutUnitPrice as soldoutUnitPrice," +
					"(id.medicineDetail.soldoutUnitPrice * id.soldoutStock) as totalPrice " +
					"from InvoiceDetail id where id.invoice.invoiceId = :invoiceId"
	),
	@NamedQuery(name="getInvoiceDetailsByInvoiceDetailsId",
			query="select id from InvoiceDetail id where id.invoiceDetailsId = :invoiceDetailsId"
	),
	@NamedQuery(name="getAllInvoiceDetailsByInvoiceId",
			query="select id from InvoiceDetail id where id.invoice.invoiceId = :invoiceId"
	)
//	@NamedQuery(name="getInvoiceDetailsByInvoiceBatchAndInvoiceId",
//			query="select id.invoice.invoiceId as invoiceId, " +
//					"id.invoice.invoiceName as invoiceName, id.purchaseDate as purchaseDateActual," +
//					"id.invoiceDesc as invoiceDesc, id.medDose as medDose, " +
//					"id.medType as medType, id.medWeight as medWeight, " +
//					"id.stock as stock, (id.stock - id.soldoutStock) as availableStock, " +
//					"id.invoiceDetailsId as invoiceDetailsId, id.batchName as batchName, " +
//					"id.invoice.company.companyName as companyName, id.mfgDate as mfgDateActual, " +
//					"id.invoice.company.companyId as companyId, id.soldoutUnitPrice as soldoutUnitPrice, " +
//					"id.expDate as expDateActual, id.batchName as batchId, id.medType as medType " +
//					"from InvoiceDetail id " +
//					"where id.batchName = :batchName and id.invoice.invoiceId = :invoiceId " +
//					"order by id.invoice.invoiceName"
//	),
//	@NamedQuery(name="countInvoices",
//			query="select count(*) as total " +
//			"from Invoice invoice " +
//			"where upper(invoice.customer.customerName) like :customerName and " +
//			"upper(invoice.customer.customerMob1) like :customerMob1 and " +
//			"upper(invoice.doctor.doctorName) like :doctorName"
//	),
//	@NamedQuery(name="getInvoiceByInvoiceDetailsId",
//			query="select id.invoice.invoiceId as invoiceId, " +
//					"id.invoice.invoiceName as invoiceName, id.soldoutUnitPrice as soldoutUnitPrice," +
//					"id.invoiceDesc as invoiceDesc, id.purchaseDate as purchaseDateActual," +
//					"id.invoiceDetailsId as invoiceDetailsId, id.batchName as batchId, " +
//					"id.invoice.company.companyName as companyName, id.mfgDate as mfgDateActual, " +
//					"id.invoice.company.companyId as companyId," +
//					"id.expDate as expDateActual, id.stock as stock, (id.stock - id.soldoutStock) as availableStock, " +
//					"id.unitPrice as unitPrice, id.medDose as medDose, " +
//					"medType as medType, medWeight as medWeight " +
//					"from InvoiceDetail id " +
//					"where id.invoiceDetailsId = :invoiceDetailsId order by id.invoice.invoiceName"
//	),
//	@NamedQuery(name="getAllInvoices",
//			query="select m.invoiceId as invoiceId, m.invoiceName as invoiceName from Invoice m order by m.invoiceName"
//	),
//	@NamedQuery(name="getInvoiceByInvoiceName",
//			query="select m from Invoice m where upper(m.invoiceName) = :invoiceName"
//	),
//	@NamedQuery(name="getInvoiceByInvoiceNameAndCompany",
//			query="select m from Invoice m where upper(m.invoiceName) = :invoiceName and m.company.companyId <> :companyId"
//	),
//	@NamedQuery(name="getInvoiceDetailsByInvoiceDetailsId",
//			query="select id from InvoiceDetail id where id.invoiceDetailsId = :invoiceDetailsId"
//	),
//	@NamedQuery(name="getInvoiceByCompanyId",
//			query="select m.invoiceName as invoiceName, m.invoiceId as invoiceId " +
//					"from Invoice m where m.company.companyId = :companyId order by m.invoiceName"
//	),
//	@NamedQuery(name="getAllInvoiceBatches",
//			query="select distinct(id.batchName) as batchName from InvoiceDetail id where id.batchName is not null order by id.batchName"
//	),
//	@NamedQuery(name="getInvoiceDetailsByInvoiceId",
//			query="select distinct(id.batchName) as batchName from InvoiceDetail id " +
//					"where id.batchName is not null and id.expDate > :today and id.invoice.invoiceId = :invoiceId " +
//					"order by id.batchName"
//	)
})
@Entity
@Table(name="invoice_details")
public class InvoiceDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="INVOICE_DETAILS_ID", unique=true, nullable=false)
	private int invoiceDetailsId;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

	@Column(name="SCHEDULE", length=100)
	private String schedule;

	@Column(name="SOLDOUT_STOCK")
	private int soldoutStock;
	
	//bi-directional many-to-one association to Invoice
    @ManyToOne
	@JoinColumn(name="INVOICE_ID")
	private Invoice invoice;

	//bi-directional many-to-one association to MedicineDetail
    @ManyToOne
	@JoinColumn(name="MEDICINE_DETAILS_ID")
	private MedicineDetail medicineDetail;

    public InvoiceDetail() {
    }

	public int getInvoiceDetailsId() {
		return this.invoiceDetailsId;
	}

	public void setInvoiceDetailsId(int invoiceDetailsId) {
		this.invoiceDetailsId = invoiceDetailsId;
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

	public String getSchedule() {
		return this.schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	public MedicineDetail getMedicineDetail() {
		return this.medicineDetail;
	}

	public void setMedicineDetail(MedicineDetail medicineDetail) {
		this.medicineDetail = medicineDetail;
	}

	public int getSoldoutStock() {
		return soldoutStock;
	}

	public void setSoldoutStock(int soldoutStock) {
		this.soldoutStock = soldoutStock;
	}
}