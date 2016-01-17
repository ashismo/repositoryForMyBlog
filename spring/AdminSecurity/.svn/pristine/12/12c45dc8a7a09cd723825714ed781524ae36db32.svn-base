package com.org.coop.society.data.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.society.data.admin.entities.RuleMasterValue;

public interface RuleMasterValueRepository extends JpaRepository<RuleMasterValue, Integer> {
	public RuleMasterValue findByRuleValueId(int permissionId);
	
	@Query("select r from RuleMasterValue r where r.ruleMaster.ruleName = :ruleName and r.ruleMaster.moduleMaster.moduleName = :moduleName")
	public RuleMasterValue findByRuleNameAndModuleName(@Param("ruleName")String ruleName, @Param("moduleName")String moduleName);
}
