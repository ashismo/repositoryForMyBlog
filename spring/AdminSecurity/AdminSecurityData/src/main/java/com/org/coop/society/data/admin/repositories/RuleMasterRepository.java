package com.org.coop.society.data.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.society.data.admin.entities.RuleMaster;

public interface RuleMasterRepository extends JpaRepository<RuleMaster, Integer> {
	
	@Query("select r from RuleMaster r where r.ruleName = :ruleName and r.moduleMaster.moduleName = :moduleName")
	public RuleMaster findByRuleNameAndModuleName(@Param("ruleName")String ruleName, @Param("moduleName")String moduleName);
}
