package com.org.coop.retail.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the retail_payment database table.
 * 
 */
@Entity
@Table(name="retail_payment")
@NamedQuery(name="RetailPayment.findAll", query="SELECT r FROM RetailPayment r")
@SQLDelete(sql="update retail_payment set delete_ind='Y' where retail_payment_id = ?")
@Where(clause="delete_ind is NULL")
public class RetailPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="retail_payment_id")
	private int retailPaymentId;

	@Temporal(TemporalType.DATE)
	@Column(name="action_date")
	private Date actionDate;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	@Column(name="delete_ind")
	private String deleteInd;

	@Column(name="delete_reason")
	private String deleteReason;

	@Column(name="passing_auth_ind")
	private String passingAuthInd;

	@Column(name="passing_auth_remark")
	private String passingAuthRemark;

	@Column(name="update_date")
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to MaterialTranHrd
	@ManyToOne
	@JoinColumn(name="tran_id")
	private MaterialTranHrd materialTranHrd;

	//bi-directional many-to-one association to TransactionPayment
	@ManyToOne
	@JoinColumn(name="payment_id")
	private TransactionPayment transactionPayment;

	public RetailPayment() {
	}

	public int getRetailPaymentId() {
		return this.retailPaymentId;
	}

	public void setRetailPaymentId(int retailPaymentId) {
		this.retailPaymentId = retailPaymentId;
	}

	public Date getActionDate() {
		return this.actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getDeleteInd() {
		return this.deleteInd;
	}

	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}

	public String getDeleteReason() {
		return this.deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	public String getPassingAuthInd() {
		return this.passingAuthInd;
	}

	public void setPassingAuthInd(String passingAuthInd) {
		this.passingAuthInd = passingAuthInd;
	}

	public String getPassingAuthRemark() {
		return this.passingAuthRemark;
	}

	public void setPassingAuthRemark(String passingAuthRemark) {
		this.passingAuthRemark = passingAuthRemark;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public MaterialTranHrd getMaterialTranHrd() {
		return this.materialTranHrd;
	}

	public void setMaterialTranHrd(MaterialTranHrd materialTranHrd) {
		this.materialTranHrd = materialTranHrd;
	}

	public TransactionPayment getTransactionPayment() {
		return this.transactionPayment;
	}

	public void setTransactionPayment(TransactionPayment transactionPayment) {
		this.transactionPayment = transactionPayment;
	}

	@PreUpdate
	public void updateTimeStamps() {
	    updateDate = new Timestamp(System.currentTimeMillis());
	    if (createDate == null) {
	    	createDate = new Timestamp(System.currentTimeMillis());
	    }
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + retailPaymentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RetailPayment other = (RetailPayment) obj;
		if (retailPaymentId != other.retailPaymentId)
			return false;
		return true;
	}
}