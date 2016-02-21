package com.org.coop.society.data.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.society.data.admin.entities.PermissionMaster;
import com.org.coop.society.data.admin.entities.RoleMaster;

public interface PermissionMasterRepository extends JpaRepository<PermissionMaster, Integer> {
}
