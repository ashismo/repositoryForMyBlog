package com.org.coop.retail.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.retail.entities.MaterialAvailability;

public interface RetailMaterialAvailabilityRepository extends JpaRepository<MaterialAvailability, Integer> {
	
	@Query(value="select 0 as available_id, branch_id, material_id, sum(qty) stock_in, sum(qty - available_qty) stock_out, "
			+ "sum(available_qty) available_stock, create_date, create_user, update_date, update_user, '1900-01-01' fy_start_date, '1900-01-01' fy_end_date "
			+ "from stock_entry where branch_id= :branchId and material_id= :materialId "
			+ "and entry_type in ('PURCHASED','NEW','OPENING','TRANSFER') and action_date between :fyStartDate and :fyEndDate", nativeQuery=true)
	public MaterialAvailability findMaterialStockByBranch(@Param("branchId") int branchId, @Param("materialId") int materialId,
								@Param("fyStartDate") Date fyStartDate, @Param("fyEndDate") Date fyEndDate);
	
	@Query("select ma from MaterialAvailability ma where ma.branchMaster.branchId = :branchId and "
			+ "ma.materialMaster.materialId = :materialId and fyStartDate = :fyStartDate and fyEndDate = :fyEndDate")
	public MaterialAvailability findMaterialAvailabilityByBranchIdAndMaterialId(@Param("branchId") int branchId, @Param("materialId") int materialId,
									@Param("fyStartDate") Date fyStartDate, @Param("fyEndDate") Date fyEndDate);
}
