package com.org.coop.society.data.admin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.society.data.admin.entities.InactiveDropdown;

public interface InactiveDropdownRepository extends JpaRepository<InactiveDropdown, Integer> {
	
	@Query("select id.dropdownMaster.code from InactiveDropdown id where branchMaster.branchId = :branchId")
	public List<String> findCodeByBranchId(@Param("branchId") int branchId);
}
