package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the owner database table.
 * 
 */

@NamedQueries ({
	@NamedQuery(name="getAccountDetails",
			query="select o.ownerName as ownerName, " +
					"o.ownerId as ownerId, o.ownerAddr1 as ownerAddr1, " +
					"o.ownerAddr2 as ownerAddr2, o.ownerDesc as ownerDesc," +
					"o.emailId as emailId, o.fax as fax, " +
					"o.mob1 as mob1, o.mob2 as mob2, " +
					"o.phone1 as phone1, o.phone2 as phone2," +
					"o.pin as pin, o.state as state, " +
					"o.website as website, o.shopNo as shopNo,  " +
					"o.licenceNo as licenceNo, o.babyFoodLcNo as babyFoodLcNo, o.shopName as shopName " +
					"from Owner o"
	),
	@NamedQuery(name="getAccountEntityDetails",
			query="select o from Owner o"
	)
})

@Entity
@Table(name="owner")
public class Owner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="OWNER_ID", unique=true, nullable=false)
	private int ownerId;

	@Column(name="BABY_FOOD_LC_NO", length=100)
	private String babyFoodLcNo;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

	@Column(name="EMAIL_ID", length=100)
	private String emailId;

	@Column(name="FAX", length=100)
	private String fax;

	@Column(name="LICENCE_NO", length=100)
	private String licenceNo;

	@Column(name="MOB1", length=100)
	private String mob1;

	@Column(name="MOB2", length=100)
	private String mob2;

	@Column(name="OWNER_ADDR1", length=100)
	private String ownerAddr1;

	@Column(name="OWNER_ADDR2", length=100)
	private String ownerAddr2;

	@Column(name="OWNER_DESC", length=255)
	private String ownerDesc;

	@Column(name="OWNER_NAME", length=100)
	private String ownerName;

	@Column(name="PHONE1", length=100)
	private String phone1;

	@Column(name="PHONE2", length=100)
	private String phone2;

	@Column(name="PIN", length=100)
	private String pin;

	@Column(name="SHOP_NO", length=100)
	private String shopNo;
	
	@Column(name="SHOP_NAME", length=100)
	private String shopName;

	@Column(name="STATE", length=100)
	private String state;

	@Column(name="WEBSITE", length=100)
	private String website;

	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="owner")
	private Set<Invoice> invoices;

    public Owner() {
    }

	public int getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getBabyFoodLcNo() {
		return this.babyFoodLcNo;
	}

	public void setBabyFoodLcNo(String babyFoodLcNo) {
		this.babyFoodLcNo = babyFoodLcNo;
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

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getLicenceNo() {
		return this.licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getMob1() {
		return this.mob1;
	}

	public void setMob1(String mob1) {
		this.mob1 = mob1;
	}

	public String getMob2() {
		return this.mob2;
	}

	public void setMob2(String mob2) {
		this.mob2 = mob2;
	}

	public String getOwnerAddr1() {
		return this.ownerAddr1;
	}

	public void setOwnerAddr1(String ownerAddr1) {
		this.ownerAddr1 = ownerAddr1;
	}

	public String getOwnerAddr2() {
		return this.ownerAddr2;
	}

	public void setOwnerAddr2(String ownerAddr2) {
		this.ownerAddr2 = ownerAddr2;
	}

	public String getOwnerDesc() {
		return this.ownerDesc;
	}

	public void setOwnerDesc(String ownerDesc) {
		this.ownerDesc = ownerDesc;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getShopNo() {
		return this.shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}
	
}