package com.org.coop.retail.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.servicehelper.MaterialSetupServiceHelperImpl;
import com.org.coop.retail.servicehelper.RetailBranchSetupServiceHelperImpl;

@Service
public class MaterialSetupServiceImpl {

	private static final Logger log = Logger.getLogger(MaterialSetupServiceImpl.class); 
	
	@Autowired
	private MaterialSetupServiceHelperImpl materialSetupServiceHelperImpl;
	
	
	public UIModel getMaterial(int materialId) {
		return materialSetupServiceHelperImpl.getMaterial(materialId);
	}
	
	public UIModel saveMaterial(UIModel uiModel) {
		int materialId = uiModel.getBranchBean().getMaterialGroups().get(0).getMaterials().get(0).getMaterialId();
		uiModel = materialSetupServiceHelperImpl.saveMaterial(uiModel);
		if(uiModel.getErrorMsg() != null) {
			return uiModel;
		}
		return materialSetupServiceHelperImpl.getMaterial(materialId);
	}
}
