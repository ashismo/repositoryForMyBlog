package com.org.coop.retail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.retail.entities.MaterialGroup;

public interface RetailMaterialGroupMasterRepository extends JpaRepository<MaterialGroup, Integer> {
}
