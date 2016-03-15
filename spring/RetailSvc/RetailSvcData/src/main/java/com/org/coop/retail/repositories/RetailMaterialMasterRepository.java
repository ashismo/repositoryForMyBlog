package com.org.coop.retail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.retail.entities.MaterialMaster;

public interface RetailMaterialMasterRepository extends JpaRepository<MaterialMaster, Integer> {
}
