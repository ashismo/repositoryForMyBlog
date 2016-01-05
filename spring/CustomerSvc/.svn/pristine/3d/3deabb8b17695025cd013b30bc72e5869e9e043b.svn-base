package com.org.coop.society.data.customer.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the rule_master_values database table.
 * 
 */
@Entity
@Table(name="rule_master_values")
@NamedQuery(name="RuleMasterValue.findAll", query="SELECT r FROM RuleMasterValue r")
public class RuleMasterValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rule_value_id")
	private int ruleValueId;

	@Column(name="create_date", updatable=false)
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="rule_desction")
	private String ruleDesction;

	@Column(name="rule_value")
	private String ruleValue;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="update_date", insertable=false)
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to RuleMaster
	@ManyToOne
	@JoinColumn(name="rule_id")
	private RuleMaster ruleMaster;

	public RuleMasterValue() {
	}

	public int getRuleValueId() {
		return this.ruleValueId;
	}

	public void setRuleValueId(int ruleValueId) {
		this.ruleValueId = ruleValueId;
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRuleDesction() {
		return this.ruleDesction;
	}

	public void setRuleDesction(String ruleDesction) {
		this.ruleDesction = ruleDesction;
	}

	public String getRuleValue() {
		return this.ruleValue;
	}

	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

	public RuleMaster getRuleMaster() {
		return this.ruleMaster;
	}

	public void setRuleMaster(RuleMaster ruleMaster) {
		this.ruleMaster = ruleMaster;
	}

}