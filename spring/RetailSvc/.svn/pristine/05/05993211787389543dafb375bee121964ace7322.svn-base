package com.org.coop.society.data.customer.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the state_master database table.
 * 
 */
@Entity
@Table(name="state_master")
@NamedQuery(name="StateMaster.findAll", query="SELECT s FROM StateMaster s")
public class StateMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="state_id")
	private int stateId;

	@Column(name="state_code")
	private String stateCode;

	@Column(name="state_name")
	private String stateName;

	//bi-directional many-to-one association to DistrictMaster
	@OneToMany(mappedBy="stateMaster")
	private List<DistrictMaster> districtMasters;

	//bi-directional many-to-one association to CountryMaster
	@ManyToOne
	@JoinColumn(name="country_id")
	private CountryMaster countryMaster;

	public StateMaster() {
	}

	public int getStateId() {
		return this.stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<DistrictMaster> getDistrictMasters() {
		return this.districtMasters;
	}

	public void setDistrictMasters(List<DistrictMaster> districtMasters) {
		this.districtMasters = districtMasters;
	}

	public DistrictMaster addDistrictMaster(DistrictMaster districtMaster) {
		getDistrictMasters().add(districtMaster);
		districtMaster.setStateMaster(this);

		return districtMaster;
	}

	public DistrictMaster removeDistrictMaster(DistrictMaster districtMaster) {
		getDistrictMasters().remove(districtMaster);
		districtMaster.setStateMaster(null);

		return districtMaster;
	}

	public CountryMaster getCountryMaster() {
		return this.countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

}