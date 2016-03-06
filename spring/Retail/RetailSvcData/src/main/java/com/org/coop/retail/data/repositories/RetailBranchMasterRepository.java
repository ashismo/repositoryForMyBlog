package com.org.coop.retail.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.org.coop.retail.data.entities.BranchMaster;
import com.org.coop.retail.data.entities.MaterialMaster;

@Repository
public interface RetailBranchMasterRepository extends MongoRepository<BranchMaster, Integer> {
	public BranchMaster findByBranchId(@Param("branchId") long branchId);
}
