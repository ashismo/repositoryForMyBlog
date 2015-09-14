package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity

@NamedQueries ({
	@NamedQuery(name="searchTransactionsIncome",
			query="select td.transactionDetailsId as transactionDetailsId, " +
					"td.invoice.customer.customerName as customerName, " +
					"td.invoice.customer.mob1 as mob1, " +
					"td.invoice.invoiceId as invoiceId, " +
					"td.cardNumber as cardNumber, td.buyOrSell as buyOrSell, " +
					"sum(td.totalPaid) as totalPaid, td.invoice.totalAmt as totalAmt, " +
					"(td.invoice.totalAmt - sum(td.totalPaid)) as totalDueAmount, " +
					"td.transactionDesc as transactionDesc, " +
					"td.paymentDate as paymentDateActual, td.paymentMode as paymentMode " +
					"from TransactionDetails td where " +
					"td.invoice.customer.customerName like :customerName and " +
					"td.invoice.customer.mob1 like :customerMob and " +
					"td.paymentDate between :startDate and :endDate and td.paymentMode like :paymentMode " +
					"group by td.invoice.invoiceId order by td.invoice.invoiceId desc "
	),
	@NamedQuery(name="searchRevenueDetails",
			query="select sum(td.totalAmt) as totalAmt, sum(td.totalPaid) as totalPaid, td.paymentMode as paymentMode " +
					"from TransactionDetails td where " +
					"td.paymentDate between :startDate and :endDate " +
					"and td.buyOrSell = :buyOrSell and " +
					"td.dbAddUser = case when :userId = -1 then td.dbAddUser else :userId end " +
					"and td.paymentMode like :paymentMode group by td.paymentMode"
	),
	@NamedQuery(name="searchTransactionsExpenditure",
			query="select td.transactionDetailsId as transactionDetailsId, " +
					"td.medRep.medRepId as medRepId, " +
					"td.medRep.medRepName as medRepName," +
					"td.medRep.wholeSeller.wholesellerName as wholesellerName, " +
					"td.medRep.wholeSeller.wholesellerId as wholesellerId, " +
					"td.cardNumber as cardNumber, td.buyOrSell as buyOrSell, " +
					"td.totalPaid as totalPaid, td.totalAmt as totalAmt, " +
					"(td.totalAmt - td.totalPaid) as totalDueAmount, " +
					"td.transactionDesc as transactionDesc, " +
					"td.paymentDate as paymentDateActual, td.paymentMode as paymentMode " +
					"from TransactionDetails td where " +
					"td.medRep.wholeSeller.wholesellerId = " +
							"case when :wholesellerId = 0 then td.medRep.wholeSeller.wholesellerId else :wholesellerId end and " +
					"td.medRep.medRepId = " +
							"case when :medRepId = 0 then td.medRep.medRepId else :medRepId end and " +
					"td.paymentMode like :paymentMode and " +
					"td.paymentDate between :startDate and :endDate order by td.paymentDate desc "
	),
	@NamedQuery(name="viewTransactionsExpenditure",
			query="select td.transactionDetailsId as transactionDetailsId, " +
					"td.medRep.medRepId as medRepId, " +
					"td.medRep.medRepName as medRepName," +
					"td.medRep.wholeSeller.wholesellerName as wholesellerName, " +
					"td.medRep.wholeSeller.wholesellerId as wholesellerId, " +
					"td.cardNumber as cardNumber, td.buyOrSell as buyOrSell, " +
					"td.totalPaid as totalPaid, td.totalAmt as totalAmt, " +
					"(td.totalAmt - td.totalPaid) as totalDueAmount, " +
					"td.transactionDesc as transactionDesc, " +
					"td.paymentDate as paymentDateActual, td.paymentMode as paymentMode " +
					"from TransactionDetails td where td.transactionDetailsId = :transactionDetailsId"
	),
	@NamedQuery(name="getTransactionDetailsByTransactionDetailsId",
			query="select td from TransactionDetails td where td.transactionDetailsId = :transactionDetailsId"
	),
	@NamedQuery(name="getAllTransactionDetails",
			query="select ws.wholesellerId as wholesellerId, ws.wholesellerName as wholesellerName " +
					"from WholeSeller ws order by ws.wholesellerName"
	),
	@NamedQuery(name="getTransactionDetailsByInvoiceId",
			query="select td.transactionDetailsId as transactionDetailsId," +
					"td.invoice.invoiceId as invoiceId," +
					"td.paymentDate as paymentDateActual," +
					"td.totalPaid as totalPaid," +
					"td.paymentMode as paymentMode," +
					"td.cardNumber as cardNumber," +
					"td.transactionDesc as transactionDesc" +
					" from TransactionDetails td where td.invoice.invoiceId = :invoiceId order by td.paymentDate asc"
	),
	@NamedQuery(name="getTransactionAmtByInvoiceId",
			query="select sum(td.totalPaid) as  totalPaid " +
					"from TransactionDetails td " +
					"where td.invoice.invoiceId = :invoiceId"
	),
	@NamedQuery(name="getTransactionDetailsEntityByInvoiceId",
			query="select td from TransactionDetails td " +
					"where td.invoice.invoiceId = :invoiceId"
	)
})

@Table(name="transaction_details")
public class TransactionDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TRANSACTION_DETAILS_ID", unique=true, nullable=false)
	private int transactionDetailsId;

	@Column(name="BUY_OR_SELL", length=50)
	private String buyOrSell;

	@Column(name="CARD_NUMBER", length=50)
	private String cardNumber;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

	@Column(name="TOTAL_PAID")
	private double totalPaid;

    @Temporal( TemporalType.DATE)
	@Column(name="PAYMENT_DATE")
	private Date paymentDate;

	@Column(name="PAYMENT_MODE", length=50)
	private String paymentMode;

	@Column(name="TOTAL_AMT")
	private double totalAmt;

	@Column(name="TRANSACTION_DESC", length=255)
	private String transactionDesc;

	//bi-directional many-to-one association to Invoice
    @ManyToOne
	@JoinColumn(name="INVOICE_ID")
	private Invoice invoice;

	//bi-directional many-to-one association to MedRep
    @ManyToOne
	@JoinColumn(name="MED_REP_ID")
	private MedRep medRep;
    
    //bi-directional many-to-one association to Attachment
	@OneToMany(mappedBy="transactionDetail")
	private Set<Attachment> attachments;

    public TransactionDetails() {
    }

	public int getTransactionDetailsId() {
		return transactionDetailsId;
	}


	public void setTransactionDetailsId(int transactionDetailsId) {
		this.transactionDetailsId = transactionDetailsId;
	}


	public String getBuyOrSell() {
		return this.buyOrSell;
	}

	public void setBuyOrSell(String buyOrSell) {
		this.buyOrSell = buyOrSell;
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

	public double getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getTransactionDesc() {
		return this.transactionDesc;
	}

	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	public MedRep getMedRep() {
		return this.medRep;
	}

	public void setMedRep(MedRep medRep) {
		this.medRep = medRep;
	}

	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}
}