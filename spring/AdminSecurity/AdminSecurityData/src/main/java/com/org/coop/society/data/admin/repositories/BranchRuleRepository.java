package com.org.coop.society.data.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.society.data.admin.entities.BranchRule;
import com.org.coop.society.data.admin.entities.RuleMaster;

public interface BranchRuleRepository extends JpaRepository<BranchRule, Integer> {
	
	public BranchRule findByBranchRuleId(int branchRuleId);
}
