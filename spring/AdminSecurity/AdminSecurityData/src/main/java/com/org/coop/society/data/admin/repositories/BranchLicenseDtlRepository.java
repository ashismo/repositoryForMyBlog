package com.org.coop.society.data.admin.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.society.data.admin.entities.BranchLicenseDtl;

public interface BranchLicenseDtlRepository extends JpaRepository<BranchLicenseDtl, Integer> {
	public List<BranchLicenseDtl> findByBranchIdOrderByEndDateDesc(int branchId);
}
