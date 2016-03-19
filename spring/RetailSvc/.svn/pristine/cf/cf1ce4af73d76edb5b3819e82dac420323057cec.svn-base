package com.org.coop.retail.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.retail.entities.RetailRateChart;

public interface RetailRateChartRepository extends JpaRepository<RetailRateChart, Integer> {
	@Query("select rrc from RetailRateChart rrc where rrc.branchMaster.branchId = :branchId "
			+ "and rrc.materialMaster.materialId = :materialId and rrc.deleteInd is NULL "
			+ "and rrc.startDate <> Date(NOW())")
	public List<RetailRateChart> findExistingMaterialRateForBranch(@Param("branchId")int branchId, @Param("materialId")int materialId);
	
	@Query("select rrc from RetailRateChart rrc where rrc.branchMaster.branchId = :branchId "
			+ "and rrc.materialMaster.materialId = :materialId and rrc.deleteInd is NULL "
			+ "and rrc.startDate = Date(NOW())")
	public List<RetailRateChart> findTodaysMaterialRateForBranch(@Param("branchId")int branchId, @Param("materialId")int materialId);
	
	@Query("select rrc from RetailRateChart rrc where "
			+ " rrc.materialMaster.materialId = :materialId and rrc.deleteInd is NULL")
	public List<RetailRateChart> findMaterialRateForBranch(@Param("materialId")int materialId);
	
}
