package com.org.coop.retail.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.org.coop.retail.data.entities.BranchMaster;

@Repository
public interface RetailBranchMasterRepository extends MongoRepository<BranchMaster, Integer> {
	public BranchMaster findByBranchId(@Param("branchId") long branchId);
	
	@Query(value = "{ 'branchId' : ?0, 'materials.materialId' : ?1}", fields = "{ 'materials.$' : 1, '$' : 1 }")
    public BranchMaster findMaterialByMaterialId(int branchId, int materialId);

}
