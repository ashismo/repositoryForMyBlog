package com.org.coop.society.data.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.coop.society.data.admin.entities.BranchMaster;
import com.org.coop.society.data.admin.entities.CountryMaster;
import com.org.coop.society.data.admin.entities.DistrictMaster;
import com.org.coop.society.data.admin.entities.ModuleMaster;
import com.org.coop.society.data.admin.entities.User;

public interface ModuleMasterRepository extends JpaRepository<ModuleMaster, Integer> {
	public ModuleMaster findByModuleId(int moduleId);
	public ModuleMaster findByModuleName(String moduleName);
}
