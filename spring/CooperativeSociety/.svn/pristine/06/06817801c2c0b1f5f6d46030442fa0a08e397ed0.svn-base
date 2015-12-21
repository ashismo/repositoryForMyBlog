package com.org.coop.admin.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.coop.bs.util.BusinessConstants;
import com.org.coop.society.data.admin.entities.BranchRule;
import com.org.coop.society.data.admin.entities.SecurityQuestion;
import com.org.coop.society.data.admin.entities.User;
import com.org.coop.society.data.admin.repositories.SecurityQuestionRepository;
import com.org.coop.society.data.admin.repositories.UserAdminRepository;

@Service
public class CommonAdminServiceImpl {
	private static final Logger log = Logger.getLogger(AdminLoginServiceImpl.class); 
	
	@Autowired
	private SecurityQuestionRepository securityQuestionRepository;
	
	@Autowired
	private UserAdminRepository userAdminRepository;
	
	
	@Transactional(value="adminTransactionManager")
	public List<SecurityQuestion> getAllSecurityQuestions() {
		return securityQuestionRepository.findAll();
	}
	
	@Transactional(value="adminTransactionManager")
	public Map<String, String> getBranchRules(String username) {
		User user = userAdminRepository.findByUserName(username);
		Map<String, String> branchRuleMap = new HashMap<String, String>();
		for(BranchRule branchRule: user.getBranchMaster().getBranchRules()) {
			branchRuleMap.put(branchRule.getRuleMaster().getRuleName(), branchRule.getRuleValue());
		}
		log.debug("Rule setup for the branch is : " + branchRuleMap);
		return branchRuleMap;
	}
	
	public String getBranchRuleValueByKey(String username, String key) {
		Map<String, String> branchRulesMap = getBranchRules(username);
		String value = branchRulesMap.get(key);
		log.debug("Rule setup for the key " + key + " is : " + value);
		return value;
	}
	
	public Date getDateByString(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = formatter.parse(dateInString);
			log.debug("Date is : " + date);
		} catch (Exception e) {
			log.error("Error occured in the input date: " + dateInString, e);
		}
		return date;
	}
}
