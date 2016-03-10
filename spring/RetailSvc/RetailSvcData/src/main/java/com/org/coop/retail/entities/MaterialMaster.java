package com.org.coop.retail.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the material_master database table.
 * 
 */
@Entity
@Table(name="material_master")
@NamedQuery(name="MaterialMaster.findAll", query="SELECT m FROM MaterialMaster m")
public class MaterialMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="material_id")
	private int materialId;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	@Column(name="low_stock_level")
	private BigDecimal lowStockLevel;

	@Column(name="material_description")
	private String materialDescription;

	@Column(name="material_name")
	private String materialName;

	@Column(name="qty_check_required_ind")
	private String qtyCheckRequiredInd;

	@Column(name="stock_balance")
	private BigDecimal stockBalance;

	@Column(name="stock_in")
	private BigDecimal stockIn;

	@Column(name="stock_out")
	private BigDecimal stockOut;

	private String uom;

	@Column(name="update_date")
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to MaterialGroup
	@ManyToOne
	@JoinColumn(name="material_grp_id")
	private MaterialGroup materialGroup;

	//bi-directional many-to-one association to MaterialTranDtl
	@OneToMany(mappedBy="materialMaster", fetch = FetchType.LAZY, cascade={CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH})
	private List<MaterialTranDtl> materialTranDtls;

	//bi-directional many-to-one association to RetailRateChart
	@OneToMany(mappedBy="materialMaster", fetch = FetchType.LAZY, cascade={CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH})
	private List<RetailRateChart> retailRateCharts;

	//bi-directional many-to-one association to StockEntry
	@OneToMany(mappedBy="materialMaster", fetch = FetchType.LAZY, cascade={CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH})
	private List<StockEntry> stockEntries;

	public MaterialMaster() {
	}

	public int getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
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

	public BigDecimal getLowStockLevel() {
		return this.lowStockLevel;
	}

	public void setLowStockLevel(BigDecimal lowStockLevel) {
		this.lowStockLevel = lowStockLevel;
	}

	public String getMaterialDescription() {
		return this.materialDescription;
	}

	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getQtyCheckRequiredInd() {
		return this.qtyCheckRequiredInd;
	}

	public void setQtyCheckRequiredInd(String qtyCheckRequiredInd) {
		this.qtyCheckRequiredInd = qtyCheckRequiredInd;
	}

	public BigDecimal getStockBalance() {
		return this.stockBalance;
	}

	public void setStockBalance(BigDecimal stockBalance) {
		this.stockBalance = stockBalance;
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

	public String getUom() {
		return this.uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
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

	public List<MaterialTranDtl> getMaterialTranDtls() {
		return this.materialTranDtls;
	}

	public void setMaterialTranDtls(List<MaterialTranDtl> materialTranDtls) {
		this.materialTranDtls = materialTranDtls;
	}

	public MaterialTranDtl addMaterialTranDtl(MaterialTranDtl materialTranDtl) {
		getMaterialTranDtls().add(materialTranDtl);
		materialTranDtl.setMaterialMaster(this);

		return materialTranDtl;
	}

	public MaterialTranDtl removeMaterialTranDtl(MaterialTranDtl materialTranDtl) {
		getMaterialTranDtls().remove(materialTranDtl);
		materialTranDtl.setMaterialMaster(null);

		return materialTranDtl;
	}

	public List<RetailRateChart> getRetailRateCharts() {
		return this.retailRateCharts;
	}

	public void setRetailRateCharts(List<RetailRateChart> retailRateCharts) {
		this.retailRateCharts = retailRateCharts;
	}

	public RetailRateChart addRetailRateChart(RetailRateChart retailRateChart) {
		getRetailRateCharts().add(retailRateChart);
		retailRateChart.setMaterialMaster(this);

		return retailRateChart;
	}

	public RetailRateChart removeRetailRateChart(RetailRateChart retailRateChart) {
		getRetailRateCharts().remove(retailRateChart);
		retailRateChart.setMaterialMaster(null);

		return retailRateChart;
	}

	public List<StockEntry> getStockEntries() {
		return this.stockEntries;
	}

	public void setStockEntries(List<StockEntry> stockEntries) {
		this.stockEntries = stockEntries;
	}

	public StockEntry addStockEntry(StockEntry stockEntry) {
		getStockEntries().add(stockEntry);
		stockEntry.setMaterialMaster(this);

		return stockEntry;
	}

	public StockEntry removeStockEntry(StockEntry stockEntry) {
		getStockEntries().remove(stockEntry);
		stockEntry.setMaterialMaster(null);

		return stockEntry;
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
		MaterialMaster other = (MaterialMaster) obj;
		if (materialId != other.materialId)
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