package com.org.coop.society.data.admin.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the district_master database table.
 * 
 */
@Entity
@Table(name="district_master")
@NamedQuery(name="DistrictMaster.findAll", query="SELECT d FROM DistrictMaster d")
public class DistrictMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="dist_id")
	private int distId;

	@Column(name="district_code")
	private String districtCode;

	@Column(name="district_name")
	private String districtName;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="districtMaster")
	private List<Address> addresses;

	//bi-directional many-to-one association to StateMaster
	@ManyToOne
	@JoinColumn(name="state_id")
	private StateMaster stateMaster;

	public DistrictMaster() {
	}

	public int getDistId() {
		return this.distId;
	}

	public void setDistId(int distId) {
		this.distId = distId;
	}

	public String getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setDistrictMaster(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setDistrictMaster(null);

		return address;
	}

	public StateMaster getStateMaster() {
		return this.stateMaster;
	}

	public void setStateMaster(StateMaster stateMaster) {
		this.stateMaster = stateMaster;
	}

}