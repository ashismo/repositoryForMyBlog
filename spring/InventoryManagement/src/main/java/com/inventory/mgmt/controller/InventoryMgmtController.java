package com.inventory.mgmt.controller;

import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.mgmt.beans.TaskMasterBean;
import com.inventory.mgmt.services.InventoryMgmtService;


@RestController
@RequestMapping("/rest")
public class InventoryMgmtController {
	private static final Logger logger = LoggerFactory.getLogger(InventoryMgmtController.class);
	

	@Autowired
	@Qualifier("inventoryManagementService")
    private InventoryMgmtService inventoryMgmtService;
	
	/**
	 * This method returns Test String
	 * @return
	 */
	@ApiOperation(value="hello", notes="Returns hello world")
	
	@RequestMapping(path="/hello", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
    public String hello() {
		
        return "Hello World";
    }
	
	@RequestMapping(path="/tasks", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public TaskMasterBean getTasks(@RequestParam(name="taskId")long taskId) {
		return inventoryMgmtService.getTaskMaster();
	}
	
	
}
