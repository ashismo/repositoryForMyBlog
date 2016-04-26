package com.org.coop.retail.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;


/**
 * The persistent class for the ledger_code_retail_sale database table.
 * 
 */
@Entity
@Table(name="ledger_code_retail_sale")
@NamedQuery(name="LedgerCodeRetailSale.findAll", query="SELECT l FROM LedgerCodeRetailSale l")
@SQLDelete(sql="update ledger_code_retail_sale set delete_ind='Y' where retail_ledger_code_sale_id = ?")
@Where(clause="delete_ind is NULL")
public class LedgerCodeRetailSale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="retail_ledger_code_sale_id")
	private int retailLedgerCodeSaleId;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	@Column(name="customer_type")
	private String customerType;

	@Column(name="delete_ind")
	private String deleteInd;

	@Column(name="delete_remark")
	private String deleteRemark;

	@Column(name="gl_mas_code")
	private String glMasCode;

	@Column(name="passing_auth_ind")
	private String passingAuthInd;

	@Column(name="passing_auth_remark")
	private String passingAuthRemark;

	@Column(name="update_date")
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	public LedgerCodeRetailSale() {
	}

	public int getRetailLedgerCodeSaleId() {
		return this.retailLedgerCodeSaleId;
	}

	public void setRetailLedgerCodeSaleId(int retailLedgerCodeSaleId) {
		this.retailLedgerCodeSaleId = retailLedgerCodeSaleId;
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

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getDeleteInd() {
		return this.deleteInd;
	}

	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}

	public String getDeleteRemark() {
		return this.deleteRemark;
	}

	public void setDeleteRemark(String deleteRemark) {
		this.deleteRemark = deleteRemark;
	}

	public String getGlMasCode() {
		return this.glMasCode;
	}

	public void setGlMasCode(String glMasCode) {
		this.glMasCode = glMasCode;
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

}