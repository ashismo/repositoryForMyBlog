package com.ashish.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.entity.MaterialMaster;
import com.ashish.repositories.MaterialMasterRepository;

@Service
public class MongoService {

	@Autowired
	private MaterialMasterRepository materialMasterRepository;
	
	public void deleteMaterials() {
		List<MaterialMaster> materials = materialMasterRepository.findAll();
		materialMasterRepository.delete(materials);
	}
	
	public void addMaterial() {
		MaterialMaster mm = new MaterialMaster();
		mm.setMaterialId(1L);
		mm.setName("Maxx Engine Oil");
		materialMasterRepository.save(mm);
	}
	
	public void addAnotherMaterial() {
		MaterialMaster mm = new MaterialMaster();
		mm.setMaterialId(2L);
		mm.setName("Castrol Engine Oil");
		materialMasterRepository.save(mm);
	}
	
	public void getMaterial() {
		MaterialMaster mm = materialMasterRepository.findByMaterialId(2L);
		System.out.println(mm.getName());
	}
	
}
