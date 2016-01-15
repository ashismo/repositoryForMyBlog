package com.org.coop.society.data.admin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.society.data.admin.entities.BranchMaster;
import com.org.coop.society.data.admin.entities.DistrictMaster;
import com.org.coop.society.data.admin.entities.StateMaster;
import com.org.coop.society.data.admin.entities.User;

public interface StateMasterRepository extends JpaRepository<StateMaster, Integer> {
	@Query("select s from StateMaster s where s.stateCode = :stateCode and s.countryMaster.countryCode = :countryCode")
	public StateMaster findByStateCodeAndCountryCode(@Param("stateCode")String stateCode, @Param("countryCode")String countryCode);
}
