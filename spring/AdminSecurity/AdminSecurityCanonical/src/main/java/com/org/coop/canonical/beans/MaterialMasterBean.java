package com.org.coop.canonical.beans;

import java.math.BigDecimal;
import java.util.Date;

public class MaterialMasterBean {
	protected int materialId;
	protected int materialGrpId;
	protected String createUser;
	protected BigDecimal lowStockLevel;
	protected String materialDescription;
	protected String materialName;
	protected String qtyCheckRequiredInd;
	protected BigDecimal stockBalance;
	protected BigDecimal stockIn;
	protected BigDecimal stockOut;
	protected String uom;
	protected String updateUser;
	protected Date createDate;
	protected Date updateDate;
	public int getMaterialGrpId() {
		return materialGrpId;
	}
	public void setMaterialGrpId(int materialGrpId) {
		this.materialGrpId = materialGrpId;
	}
	public int getMaterialId() {
		return materialId;
	}
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public BigDecimal getLowStockLevel() {
		return lowStockLevel;
	}
	public void setLowStockLevel(BigDecimal lowStockLevel) {
		this.lowStockLevel = lowStockLevel;
	}
	public String getMaterialDescription() {
		return materialDescription;
	}
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getQtyCheckRequiredInd() {
		return qtyCheckRequiredInd;
	}
	public void setQtyCheckRequiredInd(String qtyCheckRequiredInd) {
		this.qtyCheckRequiredInd = qtyCheckRequiredInd;
	}
	public BigDecimal getStockBalance() {
		return stockBalance;
	}
	public void setStockBalance(BigDecimal stockBalance) {
		this.stockBalance = stockBalance;
	}
	public BigDecimal getStockIn() {
		return stockIn;
	}
	public void setStockIn(BigDecimal stockIn) {
		this.stockIn = stockIn;
	}
	public BigDecimal getStockOut() {
		return stockOut;
	}
	public void setStockOut(BigDecimal stockOut) {
		this.stockOut = stockOut;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + materialId;
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
		MaterialMasterBean other = (MaterialMasterBean) obj;
		if (materialId != other.materialId)
			return false;
		return true;
	}
	
	
}
