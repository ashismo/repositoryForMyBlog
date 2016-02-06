package com.org.coop.admin.servicehelper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.org.coop.canonical.beans.UserProfile;
import com.org.coop.society.data.admin.entities.BranchRule;
import com.org.coop.society.data.admin.entities.User;
import com.org.coop.society.data.admin.repositories.UserAdminRepository;

@Service
public class UserProfileAdminServiceHelperImpl {
	
	@Autowired
	private UserAdminRepository userAdminRepository;
	
	@Transactional(value="adminTransactionManager", propagation=Propagation.REQUIRED)
	public UserProfile getUserProfile(String userName) {
		UserProfile userProfile = new UserProfile();
		User user = userAdminRepository.findByUserName(userName);
		Map<String, String> ruleIdValMap =new HashMap<String, String>();
		for(BranchRule branchRule: user.getBranchMaster().getBranchRules()) {
			ruleIdValMap.put(branchRule.getRuleMaster().getModuleMaster().getModuleName(), branchRule.getRuleMaster().getRuleName());
		}
		userProfile.setBranchRuleMap(ruleIdValMap);
		return userProfile;
	}
}
