package com.org.coop.retail.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the material_tran_hrd database table.
 * 
 */
@Entity
@Table(name="material_tran_hrd")
@NamedQuery(name="MaterialTranHrd.findAll", query="SELECT m FROM MaterialTranHrd m")
public class MaterialTranHrd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tran_id")
	private int tranId;

	@Column(name="bill_amt")
	private BigDecimal billAmt;

	@Temporal(TemporalType.DATE)
	@Column(name="bill_date")
	private Date billDate;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	@Column(name="delete_ind")
	private String deleteInd;

	@Column(name="delete_reason")
	private String deleteReason;

	@Column(name="gross_total")
	private BigDecimal grossTotal;

	@Column(name="outstanding_amt")
	private BigDecimal outstandingAmt;

	@Column(name="paid_amt")
	private BigDecimal paidAmt;

	@Column(name="paid_by")
	private String paidBy;

	@Column(name="passing_auth_ind")
	private String passingAuthInd;

	@Column(name="passing_auth_remark")
	private String passingAuthRemark;

	@Column(name="tran_no")
	private String tranNo;

	@Column(name="tran_type")
	private String tranType;

	@Column(name="update_date")
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	private BigDecimal vat;

	//bi-directional many-to-one association to MaterialTranDtl
	@OneToMany(mappedBy="materialTranHrd")
	private List<MaterialTranDtl> materialTranDtls;

	//bi-directional many-to-one association to BranchMaster
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchMaster branchMaster;

	//bi-directional many-to-one association to RetailCustomerMaster
	@ManyToOne
	@JoinColumn(name="customer_id")
	private RetailCustomerMaster retailCustomerMaster;

	public MaterialTranHrd() {
	}

	public int getTranId() {
		return this.tranId;
	}

	public void setTranId(int tranId) {
		this.tranId = tranId;
	}

	public BigDecimal getBillAmt() {
		return this.billAmt;
	}

	public void setBillAmt(BigDecimal billAmt) {
		this.billAmt = billAmt;
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
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

	public BigDecimal getGrossTotal() {
		return this.grossTotal;
	}

	public void setGrossTotal(BigDecimal grossTotal) {
		this.grossTotal = grossTotal;
	}

	public BigDecimal getOutstandingAmt() {
		return this.outstandingAmt;
	}

	public void setOutstandingAmt(BigDecimal outstandingAmt) {
		this.outstandingAmt = outstandingAmt;
	}

	public BigDecimal getPaidAmt() {
		return this.paidAmt;
	}

	public void setPaidAmt(BigDecimal paidAmt) {
		this.paidAmt = paidAmt;
	}

	public String getPaidBy() {
		return this.paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
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

	public String getTranNo() {
		return this.tranNo;
	}

	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}

	public String getTranType() {
		return this.tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
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

	public BigDecimal getVat() {
		return this.vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

	public List<MaterialTranDtl> getMaterialTranDtls() {
		return this.materialTranDtls;
	}

	public void setMaterialTranDtls(List<MaterialTranDtl> materialTranDtls) {
		this.materialTranDtls = materialTranDtls;
	}

	public MaterialTranDtl addMaterialTranDtl(MaterialTranDtl materialTranDtl) {
		getMaterialTranDtls().add(materialTranDtl);
		materialTranDtl.setMaterialTranHrd(this);

		return materialTranDtl;
	}

	public MaterialTranDtl removeMaterialTranDtl(MaterialTranDtl materialTranDtl) {
		getMaterialTranDtls().remove(materialTranDtl);
		materialTranDtl.setMaterialTranHrd(null);

		return materialTranDtl;
	}

	public BranchMaster getBranchMaster() {
		return this.branchMaster;
	}

	public void setBranchMaster(BranchMaster branchMaster) {
		this.branchMaster = branchMaster;
	}

	public RetailCustomerMaster getRetailCustomerMaster() {
		return this.retailCustomerMaster;
	}

	public void setRetailCustomerMaster(RetailCustomerMaster retailCustomerMaster) {
		this.retailCustomerMaster = retailCustomerMaster;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tranId;
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
		MaterialTranHrd other = (MaterialTranHrd) obj;
		if (tranId != other.tranId)
			return false;
		return true;
	}

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
	    updateDate = new Timestamp(System.currentTimeMillis());
	    if (createDate == null) {
	    	createDate = new Timestamp(System.currentTimeMillis());
	    }
	}
}