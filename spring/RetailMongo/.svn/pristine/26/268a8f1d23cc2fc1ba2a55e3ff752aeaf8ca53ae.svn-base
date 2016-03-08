package com.org.coop.customer.ws;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.data.entities.MaterialMaster;
import com.org.coop.retail.data.entities.RetailData;
import com.org.coop.retail.data.repositories.MaterialMasterRepository;
import com.org.coop.retail.service.RetailMasterDataSetupServiceImpl;

@RestController
@RequestMapping("/rest")
public class MasterDataSetupWSImpl {
	
	private static final Logger log = Logger.getLogger(MasterDataSetupWSImpl.class); 
	
	@Autowired
	private RetailMasterDataSetupServiceImpl masterDataSetupServiceImpl;
	@RequestMapping(value = "/retailMasterData", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public RetailData getBranchData(@RequestParam(value = "branchId",required = true,defaultValue = "0") Integer branchId,
								@RequestParam(value = "materialId",required = true,defaultValue = "0") Integer materialId) {
		RetailData retail = new RetailData();
		try {
			retail.setBranchId(branchId);
			retail.setMaterialId(materialId);
			
			retail = masterDataSetupServiceImpl.getMasterData(retail);
		} catch (Exception e) {
			log.error("Error Retrieving branch by branch Id", e);
			retail.setErrorMsg("Error Retrieving branch by branch Id: " + branchId);
		}
		return retail;
	}
	
	@RequestMapping(value = "/retailMasterData", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public RetailData saveMasterData(@RequestBody RetailData retailData) {
		RetailData retail = new RetailData();
		try {
			retail = masterDataSetupServiceImpl.saveMasterData(retailData);
		} catch (Exception e) {
			log.error("Error while saving master data", e);
			retail.setErrorMsg("Error while saving master data");
		}
		return retail;
	}
	
	
	@Autowired
	private MaterialMasterRepository materialMasterRepository;
	
	public void deleteMaterials() {
		List<MaterialMaster> materials = materialMasterRepository.findAll();
		materialMasterRepository.delete(materials);
	}
	
	public void addMaterial() {
		MaterialMaster mm = new MaterialMaster();
//		mm.setId("1");
		mm.setName("Maxx Engine Oil");
		materialMasterRepository.save(mm);
	}
	
	public void addAnotherMaterial() {
		MaterialMaster mm = new MaterialMaster();
//		mm.setId("2");
		mm.setName("Castrol Engine Oil");
		materialMasterRepository.save(mm);
	}
	
	public void updateMaterial() {
		MaterialMaster mm = materialMasterRepository.findOne(1);
		mm.setName("Maxx Engine Oil1");
		materialMasterRepository.save(mm);
	}
	
	public MaterialMaster getMaterial() {
		MaterialMaster mm = materialMasterRepository.findOne(2);
		return mm;
	}
}
