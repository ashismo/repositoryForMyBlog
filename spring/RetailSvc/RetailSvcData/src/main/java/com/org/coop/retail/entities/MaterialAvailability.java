package com.org.coop.retail.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;


/**
 * The persistent class for the material_availability database table.
 * 
 */
@Entity
@Table(name="material_availability")
@NamedQuery(name="MaterialAvailability.findAll", query="SELECT m FROM MaterialAvailability m")
public class MaterialAvailability implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="available_id")
	private int availableId;

	@Column(name="available_stock")
	private BigDecimal availableStock;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	@Column(name="stock_in")
	private BigDecimal stockIn;

	@Column(name="stock_out")
	private BigDecimal stockOut;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fy_start_date")
	private Date fyStartDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fy_end_date")
	private Date fyEndDate;

	@Column(name="update_date")
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to BranchMaster
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchMaster branchMaster;

	//bi-directional many-to-one association to MaterialMaster
	@ManyToOne
	@JoinColumn(name="material_id")
	private MaterialMaster materialMaster;

	public MaterialAvailability() {
	}

	public int getAvailableId() {
		return this.availableId;
	}

	public void setAvailableId(int availableId) {
		this.availableId = availableId;
	}

	public BigDecimal getAvailableStock() {
		return this.availableStock;
	}

	public void setAvailableStock(BigDecimal availableStock) {
		this.availableStock = availableStock;
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

	public BigDecimal getStockIn() {
		return this.stockIn;
	}

	public void setStockIn(BigDecimal stockIn) {
		this.stockIn = stockIn;
	}

	public BigDecimal getStockOut() {
		return this.stockOut;
	}

	public void setStockOut(BigDecimal stockOut) {
		this.stockOut = stockOut;
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

	public MaterialMaster getMaterialMaster() {
		return this.materialMaster;
	}

	public void setMaterialMaster(MaterialMaster materialMaster) {
		this.materialMaster = materialMaster;
	}

	@PreUpdate
	@PrePersist
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
		result = prime * result + availableId;
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
		MaterialAvailability other = (MaterialAvailability) obj;
		if (availableId != other.availableId)
			return false;
		return true;
	}

	public Date getFyStartDate() {
		return fyStartDate;
	}

	public void setFyStartDate(Date fyStartDate) {
		this.fyStartDate = fyStartDate;
	}

	public Date getFyEndDate() {
		return fyEndDate;
	}

	public void setFyEndDate(Date fyEndDate) {
		this.fyEndDate = fyEndDate;
	}
	
}