package com.ashish.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ashish.entity.MaterialMaster;

@Repository
public interface MaterialMasterRepository extends MongoRepository<MaterialMaster, Long> {
	
	MaterialMaster findByMaterialId(@Param("materialId") long materialId);
}
