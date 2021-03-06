package com.org.coop.retail.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;


/**
 * The persistent class for the ledger_code_retail database table.
 * 
 */
@Entity
@Table(name="ledger_code_retail")
@NamedQuery(name="LedgerCodeRetail.findAll", query="SELECT l FROM LedgerCodeRetail l")
@SQLDelete(sql="update ledger_code_retail set delete_ind='Y' where retail_ledger_code_id = ?")
@Where(clause="delete_ind is NULL")
public class LedgerCodeRetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="retail_ledger_code_id")
	private int retailLedgerCodeId;

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

	@Column(name="gl_mas_code")
	private int glMasCode;

	@Column(name="update_date")
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;
	
	@Column(name="purchase_sale")
	private String purchaseSale;
	
	@Column(name="customer_type")
	private String customerType;

	//bi-directional many-to-one association to MaterialGroup
	@ManyToOne
	@JoinColumn(name="material_grp_id")
	private MaterialGroup materialGroup;

	//bi-directional many-to-one association to VendorMaster
	@ManyToOne
	@JoinColumn(name="vendor_id")
	private VendorMaster vendorMaster;

	public LedgerCodeRetail() {
	}

	public int getRetailLedgerCodeId() {
		return this.retailLedgerCodeId;
	}

	public void setRetailLedgerCodeId(int retailLedgerCodeId) {
		this.retailLedgerCodeId = retailLedgerCodeId;
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

	public int getGlMasCode() {
		return glMasCode;
	}

	public void setGlMasCode(int glMasCode) {
		this.glMasCode = glMasCode;
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

	public MaterialGroup getMaterialGroup() {
		return this.materialGroup;
	}

	public void setMaterialGroup(MaterialGroup materialGroup) {
		this.materialGroup = materialGroup;
	}

	public VendorMaster getVendorMaster() {
		return this.vendorMaster;
	}

	public void setVendorMaster(VendorMaster vendorMaster) {
		this.vendorMaster = vendorMaster;
	}

	public String getPurchaseSale() {
		return purchaseSale;
	}

	public void setPurchaseSale(String purchaseSale) {
		this.purchaseSale = purchaseSale;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
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
		result = prime * result + retailLedgerCodeId;
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
		LedgerCodeRetail other = (LedgerCodeRetail) obj;
		if (retailLedgerCodeId != other.retailLedgerCodeId)
			return false;
		return true;
	}
}