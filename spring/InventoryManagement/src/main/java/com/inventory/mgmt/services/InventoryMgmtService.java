package com.inventory.mgmt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.mgmt.beans.TaskMasterBean;
import com.inventory.mgmt.dao.TaskMasterRepository;

@Service("inventoryManagementService")
public class InventoryMgmtService {
	@Autowired
    private TaskMasterRepository taskMasterRepository;
	public TaskMasterBean getTaskMaster() {
		return taskMasterRepository.findById(1);
	}
}
