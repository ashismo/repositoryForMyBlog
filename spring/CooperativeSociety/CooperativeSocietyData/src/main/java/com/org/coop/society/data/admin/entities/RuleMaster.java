package com.org.coop.society.data.admin.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rule_master database table.
 * 
 */
@Entity
@Table(name="rule_master")
@NamedQuery(name="RuleMaster.findAll", query="SELECT r FROM RuleMaster r")
public class RuleMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rule_id")
	private int ruleId;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="rule_description")
	private String ruleDescription;

	@Column(name="rule_name")
	private String ruleName;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="update_date")
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to BranchRule
	@OneToMany(mappedBy="ruleMaster")
	private List<BranchRule> branchRules;

	//bi-directional many-to-one association to ModuleMaster
	@ManyToOne
	@JoinColumn(name="module_id")
	private ModuleMaster moduleMaster;

	public RuleMaster() {
	}

	public int getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
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

	public String getRuleDescription() {
		return this.ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
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

	public List<BranchRule> getBranchRules() {
		return this.branchRules;
	}

	public void setBranchRules(List<BranchRule> branchRules) {
		this.branchRules = branchRules;
	}

	public BranchRule addBranchRule(BranchRule branchRule) {
		getBranchRules().add(branchRule);
		branchRule.setRuleMaster(this);

		return branchRule;
	}

	public BranchRule removeBranchRule(BranchRule branchRule) {
		getBranchRules().remove(branchRule);
		branchRule.setRuleMaster(null);

		return branchRule;
	}

	public ModuleMaster getModuleMaster() {
		return this.moduleMaster;
	}

	public void setModuleMaster(ModuleMaster moduleMaster) {
		this.moduleMaster = moduleMaster;
	}

}