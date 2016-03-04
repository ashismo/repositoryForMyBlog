package com.org.coop.retail.data.entities;

import java.util.List;

public class RetailData {
	private List<MaterialMaster> materials;

	private String errorMsg;
	
	public List<MaterialMaster> getMaterials() {
		return materials;
	}

	public void setMaterials(List<MaterialMaster> materials) {
		this.materials = materials;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
