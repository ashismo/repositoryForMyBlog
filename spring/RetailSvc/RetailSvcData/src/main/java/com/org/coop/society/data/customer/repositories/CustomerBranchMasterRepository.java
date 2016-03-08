package com.org.coop.society.data.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.society.data.customer.entities.BranchMaster;

public interface CustomerBranchMasterRepository extends JpaRepository<BranchMaster, Integer> {
	public BranchMaster findByMicrCode(String micrCode);
	public BranchMaster findByIfscCode(String ifscCode);
	
	public BranchMaster findByMicrCodeAndIfscCode(String micrCode, String ifscCode);
}
