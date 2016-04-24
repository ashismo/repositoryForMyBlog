package com.org.coop.retail.servicehelper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coop.org.exception.RetailStockEntryException;
import com.org.coop.bs.util.RetailBusinessConstants;
import com.org.coop.canonical.account.beans.GlMasterBean;
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.canonical.retail.beans.BankMasterBean;
import com.org.coop.retail.bs.mapper.RetailMasterDataMappingImpl;
import com.org.coop.retail.entities.BankMaster;
import com.org.coop.retail.entities.GlHeader;
import com.org.coop.retail.entities.GlMaster;
import com.org.coop.retail.entities.GlSubHeader;
import com.org.coop.retail.repositories.BankMasterRepository;
import com.org.coop.retail.repositories.GlHeaderRepository;
import com.org.coop.retail.repositories.GlMasterRepository;
import com.org.coop.retail.repositories.GlSubHeaderRepository;

@Service
public class RetailMasterDataSetupServiceHelperImpl {

	private static final Logger log = Logger.getLogger(RetailMasterDataSetupServiceHelperImpl.class); 
	
	@Autowired
	private GlMasterRepository glMasterRepository;
	
	@Autowired
	private RetailMasterDataMappingImpl retailMasterDataMappingImpl;
	
	@Autowired
	private BankMasterRepository bankMasterRepository;
	
	@Autowired
	private GlHeaderRepository glHeaderRepository;
	
	@Autowired
	private GlSubHeaderRepository glSubHeaderRepository;
	
	
	@Transactional(value="retailTransactionManager")
	public UIModel getRetailMasterData(int rateChartId, int materialId) {
		UIModel uiModel = new UIModel();
		return uiModel;
	}
	
	@Transactional(value="retailTransactionManager")
	public UIModel saveGlLedgers(UIModel uiModel) {
		// 1. Save bank master data and their code
		if(uiModel.getGlMasters() != null && uiModel.getGlMasters().size() > 0) {
			for(GlMasterBean glMasterBean : uiModel.getGlMasters()) {
				int glHeaderCode = glMasterBean.getGlHeaderCode();
				
				if(glHeaderCode > 0) {
					GlHeader glHeader = glHeaderRepository.findOne(glHeaderCode);
					if(glHeader == null) {
						glHeader = new GlHeader();
					}
					
					retailMasterDataMappingImpl.mapGlHeaderBean(glMasterBean, glHeader);
					glHeaderRepository.saveAndFlush(glHeader);
				}
				
				int glSubHeaderCode = glMasterBean.getGlSubHeaderCode();
				if(glSubHeaderCode > 0) {
					GlSubHeader glSubHeader = glSubHeaderRepository.findOne(glSubHeaderCode);
					if(glSubHeader == null) {
						glSubHeader = new GlSubHeader();
					}
					
					retailMasterDataMappingImpl.mapGlSubHeaderBean(glMasterBean, glSubHeader);
					glSubHeaderRepository.saveAndFlush(glSubHeader);
				}
				
				int glMasCode = glMasterBean.getGlMasCode();
				if(glMasCode > 0) {
					GlMaster glMaster = glMasterRepository.findOne(glMasCode);
					if(glMaster == null) {
						glMaster = new GlMaster();
					}
					
					retailMasterDataMappingImpl.mapGlMasterBean(glMasterBean, glMaster);
					glMasterRepository.saveAndFlush(glMaster);
				}
			}
		}
		return uiModel;
	}
	
	@Transactional(value="retailTransactionManager")
	public UIModel saveRetailMasterData(UIModel uiModel) {
		// 1. Save bank master data and their code
		if(uiModel.getBankMasters() != null && uiModel.getBankMasters().size() > 0) {
			for(BankMasterBean bankMasterBean : uiModel.getBankMasters()) {
				BankMaster bankMaster = null;
				if(bankMasterBean.getBankId() == 0) {
					bankMaster = new BankMaster();
				} else {
					bankMaster = bankMasterRepository.findOne(bankMasterBean.getBankId());
					if(bankMaster == null) {
						String errMsg = "Data does not exists for the bankId = " + bankMasterBean.getBankId();
						log.error(errMsg);
						throw new RetailStockEntryException(errMsg, RetailBusinessConstants.EXCEPTION_RETAIL_MASTER_DATA_SETUP);
					}
				}
				retailMasterDataMappingImpl.mapBankMasterBean(bankMasterBean, bankMaster);
				bankMasterRepository.saveAndFlush(bankMaster);
				bankMasterBean.setBankId(bankMaster.getBankId());
			}
		}
		return uiModel;
	}
	
}
