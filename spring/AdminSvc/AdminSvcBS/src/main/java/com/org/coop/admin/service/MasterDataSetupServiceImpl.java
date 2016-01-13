package com.org.coop.admin.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.coop.bs.mapper.BranchMappingImpl;
import com.org.coop.bs.mapper.CountryStateDistMappingImpl;
import com.org.coop.canonical.beans.BranchBean;
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.canonical.master.beans.CountryMasterBean;
import com.org.coop.canonical.master.beans.MasterDataBean;
import com.org.coop.society.data.admin.entities.CountryMaster;
import com.org.coop.society.data.admin.repositories.CountryMasterRepository;

@Service
public class MasterDataSetupServiceImpl {

	private static final Logger log = Logger.getLogger(MasterDataSetupServiceImpl.class); 
	
	@Autowired
	private CountryMasterRepository countryMasterRepository;
	
	@Autowired
	private CountryStateDistMappingImpl countryStateDistMap;
	
//	@Transactional(value="adminTransactionManager")
//	public void addOrUpdateBranch(UIModel uiModel) {
//		// Check if the branch already exists
//		BranchMaster branch = branchMasterRepository.findByMicrCodeAndIfscCode(uiModel.getBranchBean().getMicrCode(), uiModel.getBranchBean().getIfscCode());
//		if(branch != null && uiModel.getBranchBean().getBranchId() == 0) {
//			uiModel.setErrorMsg("Branch already exists");
//			return;
//		}
//		if(branch == null) {
//			branch = new BranchMaster();
//		}
//		branchMappingImpl.mapBean(uiModel.getBranchBean(), branch);
//		branchMasterRepository.save(branch);
//		log.debug("A new branch has been created");
//	}
	
	@Transactional(value="adminTransactionManager")
	public MasterDataBean getCountryStateDist(int countryId) {
		MasterDataBean masterData = new MasterDataBean();
		// Check if the branch already exists
		CountryMaster country = countryMasterRepository.findOne(countryId);
		if(country == null) {
			masterData.setErrorMsg("Country does not exist for Country Id: " + countryId);
			return masterData;
		}
		CountryMasterBean countryMaster = new CountryMasterBean();
		Set<CountryMasterBean> countrySet = new HashSet<CountryMasterBean>();
		countrySet.add(countryMaster);
		countryStateDistMap.mapBean(country, countryMaster);
		masterData.setCountries(countrySet);
		
		log.debug("Branch details has been retrieved from database for branchId: " + countryId);
		return masterData;
	}
}
