package com.org.coop.admin.ws;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.coop.admin.service.BranchSetupServiceImpl;
import com.org.coop.canonical.beans.UIModel;

@RestController
@RequestMapping("/rest")
public class BranchSetupWSImpl {
	
	private static final Logger log = Logger.getLogger(BranchSetupWSImpl.class); 
	
	@Autowired
	private BranchSetupServiceImpl branchSetupServiceImpl;
	
	@PreAuthorize("hasRole('ROLE_ADMIN_CREATE')")
	@RequestMapping(value = "/createBranch", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json",consumes="application/json")
	public UIModel createBranch(@RequestBody UIModel uiModel) {
		try {
			branchSetupServiceImpl.addOrUpdateBranch(uiModel);
		} catch (Exception e) {
			log.error("Error Creating new branch", e);
			uiModel.setErrorMsg("Error Creating new branch");
		}
		return uiModel;
	}
	
}
