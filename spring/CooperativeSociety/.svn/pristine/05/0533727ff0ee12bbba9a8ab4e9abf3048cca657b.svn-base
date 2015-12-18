package com.org.coop.society.data.admin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.society.data.admin.entities.BranchAddress;

public interface BranchAddressRepository extends JpaRepository<BranchAddress, Integer> {
	
	@Query("select ba from BranchAddress ba where ba.branchMaster.branchId = :branchId")
	public List<BranchAddress> findByBranchId(@Param("branchId")int branchId);
}
