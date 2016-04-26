package com.org.coop.retail.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the denomination_register database table.
 * 
 */
@Entity
@Table(name="denomination_register")
@NamedQuery(name="DenominationRegister.findAll", query="SELECT d FROM DenominationRegister d")
@SQLDelete(sql="update denomination_register set delete_ind='Y' where deno_id = ?")
@Where(clause="delete_ind is NULL")
public class DenominationRegister implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="deno_id")
	private int denoId;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	@Column(name="delete_ind")
	private String deleteInd;

	@Column(name="delete_reason")
	private String deleteReason;

	private int denomination;

	@Column(name="note_coin")
	private String noteCoin;

	@Column(name="note_coin_amount")
	private BigDecimal noteCoinAmount;

	@Column(name="note_coin_count")
	private int noteCoinCount;

	@Column(name="passing_auth_ind")
	private String passingAuthInd;

	@Column(name="passing_auth_remark")
	private String passingAuthRemark;

	@Column(name="update_date")
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to BranchMaster
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchMaster branchMaster;

	//bi-directional many-to-one association to CashRegister
	@ManyToOne
	@JoinColumn(name="cash_id")
	private CashRegister cashRegister;

	
	public DenominationRegister() {
	}

	public int getDenoId() {
		return this.denoId;
	}

	public void setDenoId(int denoId) {
		this.denoId = denoId;
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

	public int getDenomination() {
		return this.denomination;
	}

	public void setDenomination(int denomination) {
		this.denomination = denomination;
	}

	public String getNoteCoin() {
		return this.noteCoin;
	}

	public void setNoteCoin(String noteCoin) {
		this.noteCoin = noteCoin;
	}

	public BigDecimal getNoteCoinAmount() {
		return this.noteCoinAmount;
	}

	public void setNoteCoinAmount(BigDecimal noteCoinAmount) {
		this.noteCoinAmount = noteCoinAmount;
	}

	public int getNoteCoinCount() {
		return this.noteCoinCount;
	}

	public void setNoteCoinCount(int noteCoinCount) {
		this.noteCoinCount = noteCoinCount;
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

	public BranchMaster getBranchMaster() {
		return this.branchMaster;
	}

	public void setBranchMaster(BranchMaster branchMaster) {
		this.branchMaster = branchMaster;
	}

	public CashRegister getCashRegister() {
		return this.cashRegister;
	}

	public void setCashRegister(CashRegister cashRegister) {
		this.cashRegister = cashRegister;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + denoId;
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
		DenominationRegister other = (DenominationRegister) obj;
		if (denoId != other.denoId)
			return false;
		return true;
	}

	@PreUpdate
	public void updateTimeStamps() {
		long currentTime = System.currentTimeMillis();
	    updateDate = new Timestamp(currentTime);
	    if (createDate == null) {
	    	createDate = new Timestamp(currentTime);
	    }
	}
}