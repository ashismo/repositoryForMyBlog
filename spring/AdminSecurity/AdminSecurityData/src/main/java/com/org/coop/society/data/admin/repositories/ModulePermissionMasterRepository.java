package com.org.coop.society.data.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.society.data.admin.entities.BranchMaster;
import com.org.coop.society.data.admin.entities.CountryMaster;
import com.org.coop.society.data.admin.entities.DistrictMaster;
import com.org.coop.society.data.admin.entities.ModuleMaster;
import com.org.coop.society.data.admin.entities.PermissionMaster;
import com.org.coop.society.data.admin.entities.User;

public interface ModulePermissionMasterRepository extends JpaRepository<PermissionMaster, Integer> {
	public PermissionMaster findByPermissionId(int permissionId);
	
	@Query("select p from PermissionMaster p where p.permission = :permissionName and p.moduleMaster.moduleName = :moduleName")
	public PermissionMaster findByPermissionAndModuleName(@Param("permissionName")String permissionName, @Param("moduleName")String moduleName);
}
