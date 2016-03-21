package com.org.coop.society.data.admin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.society.data.admin.entities.BranchAddress;
import com.org.coop.society.data.admin.entities.CustomBranchModule;

public interface CustomBranchModuleRepository extends JpaRepository<CustomBranchModule, Integer> {
	
	@Query(name="CustomBranchModules.branchModules")
	public List<CustomBranchModule> findByBranchId(@Param("branchId")int branchId);
}
