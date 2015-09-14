package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the invoice database table.
 * 
 */
@Entity
@Table(name="invoice")
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="INVOICE_ID", unique=true, nullable=false)
	private int invoiceId;

	@Column(name="BILL_NO")
	private int billNo;

	@Column(name="CARD_NUMBER", length=25)
	private String cardNumber;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

	@Column(name="DISCOUNT")
	private double discount;

	@Column(name="PAYMENT_MODE", length=50)
	private String paymentMode;

	@Column(name="PRINT_INDICATOR", length=50)
	private String printIndicator;

    @Temporal( TemporalType.DATE)
	@Column(name="PURCHASE_DATE")
	private Date purchaseDate;

	@Column(name="TOTAL_AMT")
	private double totalAmt;

	@Column(name="TOTAL_PAID")
	private double totalPaid;

	@Column(name="VAT")
	private double vat;

	//bi-directional many-to-one association to Owner
    @ManyToOne
	@JoinColumn(name="OWNER_ID")
	private Owner owner;

	//bi-directional many-to-one association to Customer
    @ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;

	//bi-directional many-to-one association to Doctor
    @ManyToOne
	@JoinColumn(name="DOCTOR_ID")
	private Doctor doctor;

	//bi-directional many-to-one association to InvoiceDetail
	@OneToMany(mappedBy="invoice")
	private Set<InvoiceDetail> invoiceDetails;

    public Invoice() {
    }

	public int getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getBillNo() {
		return this.billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPrintIndicator() {
		return this.printIndicator;
	}

	public void setPrintIndicator(String printIndicator) {
		this.printIndicator = printIndicator;
	}

	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getTotalAmt() {
		return this.totalAmt;
	}

	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public double getTotalPaid() {
		return this.totalPaid;
	}

	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public double getVat() {
		return this.vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public Owner getOwner() {
		return this.owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public Set<InvoiceDetail> getInvoiceDetails() {
		return this.invoiceDetails;
	}

	public void setInvoiceDetails(Set<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}
	
}