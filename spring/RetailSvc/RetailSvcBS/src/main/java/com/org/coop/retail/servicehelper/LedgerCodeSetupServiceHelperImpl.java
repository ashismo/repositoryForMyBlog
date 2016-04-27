package com.org.coop.retail.servicehelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coop.org.exception.RetailException;
import com.org.coop.bs.util.RetailBusinessConstants;
import com.org.coop.canonical.account.beans.LedgerCodePaymentBean;
import com.org.coop.canonical.account.beans.LedgerCodeRetailPurchaseBean;
import com.org.coop.canonical.account.beans.LedgerCodeRetailSaleBean;
import com.org.coop.canonical.beans.BranchBean;
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.canonical.retail.beans.RetailVendorBean;
import com.org.coop.retail.bs.mapper.LedgerCodeSetupMappingImpl;
import com.org.coop.retail.entities.LedgerCodePayment;
import com.org.coop.retail.entities.LedgerCodeRetailPurchase;
import com.org.coop.retail.entities.LedgerCodeRetailSale;
import com.org.coop.retail.entities.MaterialGroup;
import com.org.coop.retail.entities.VendorMaster;
import com.org.coop.retail.repositories.LedgerCodePaymentRepository;
import com.org.coop.retail.repositories.LedgerCodeRetailPurchaseRepository;
import com.org.coop.retail.repositories.LedgerCodeRetailSaleRepository;
import com.org.coop.retail.repositories.RetailBranchMasterRepository;
import com.org.coop.retail.repositories.RetailMaterialGroupMasterRepository;
import com.org.coop.retail.repositories.RetailVendorMasterRepository;

@Service
public class LedgerCodeSetupServiceHelperImpl {

	private static final Logger log = Logger.getLogger(LedgerCodeSetupServiceHelperImpl.class); 
	
	@Autowired
	private RetailBranchMasterRepository branchMasterRepository;
	
	@Autowired
	private LedgerCodeSetupMappingImpl ledgerCodeSetupMappingImpl;
	
	@Autowired
	private RetailVendorMasterRepository retailVendorMasterRepository;
	
	@Autowired
	private LedgerCodeRetailPurchaseRepository retailLedgerCodePurchaseRepository;
	
	@Autowired
	private LedgerCodeRetailSaleRepository ledgerCodeRetailSaleRepository;
	
	@Autowired
	private LedgerCodePaymentRepository ledgerCodePaymentRepository;
	
	@Autowired
	private RetailMaterialGroupMasterRepository retailMaterialGroupMasterRepository;
	
	@Transactional(value="retailTransactionManager")
	public UIModel getRetailLedgerCode(int vendorId, int materialGrpId, int pageNo, int recordsPerPage) {
		UIModel uiModel = new UIModel();
		
		// Ruel 1: If materialGrpId = 0 and vendorId = 0 then retrieve all retail ledger code
		List<LedgerCodeRetailPurchaseBean> ledgerCodePurchaseBeans = new ArrayList<LedgerCodeRetailPurchaseBean>();
		List<LedgerCodeRetailPurchase> retailLedgerCodePurchases = null;
		
		Pageable  pagable = new PageRequest(pageNo - 1, recordsPerPage, Sort.Direction.DESC, "materialGroup.materialGrpId", "vendorMaster.vendorId");
		
		if(vendorId == 0 && materialGrpId == 0) {
			retailLedgerCodePurchases = retailLedgerCodePurchaseRepository.findAllLedgerCodes(pagable);
		}else if(vendorId > 0 && materialGrpId > 0) {
			pagable = new PageRequest(pageNo - 1, recordsPerPage, Sort.Direction.DESC, "materialGroup.materialGrpId", "vendorMaster.vendorId");
			retailLedgerCodePurchases = retailLedgerCodePurchaseRepository.findRetailLedgerByMaterialGrpIdAndVendorId(materialGrpId, vendorId, pagable);
		} else if(vendorId > 0) {
			pagable = new PageRequest(pageNo - 1, recordsPerPage, Sort.Direction.DESC, "vendorMaster.vendorId");
			retailLedgerCodePurchases = retailLedgerCodePurchaseRepository.findRetailLedgerByVendorId(vendorId, pagable);
		} else if(materialGrpId > 0) {
			pagable = new PageRequest(pageNo - 1, recordsPerPage, Sort.Direction.DESC, "materialGroup.materialGrpId", "vendorMaster.vendorId");
			retailLedgerCodePurchases = retailLedgerCodePurchaseRepository.findRetailLedgerByMaterialGrpId(materialGrpId, pagable);
		}
		BranchBean branchBean = new BranchBean();
		uiModel.setBranchBean(branchBean);
		uiModel.setPageNo(pageNo);
		uiModel.setRecordsPerPage(retailLedgerCodePurchases.size());
		uiModel.setRecordCount(retailLedgerCodePurchases.size());
		for(LedgerCodeRetailPurchase ledgerCode : retailLedgerCodePurchases) {
			LedgerCodeRetailPurchaseBean ledgerCodeBean = new LedgerCodeRetailPurchaseBean();
			ledgerCodeSetupMappingImpl.mapRetailLedgerCodePurchaseBean(ledgerCode, ledgerCodeBean);
			branchBean.setBranchId(ledgerCode.getMaterialGroup().getBranchMaster().getBranchId());
			ledgerCodePurchaseBeans.add(ledgerCodeBean);
		}
		branchBean.setRetailLedgerCodePurchases(ledgerCodePurchaseBeans);
		
		if(log.isDebugEnabled()) {
			log.debug("Going to retrieve retail ledger code for sale");
		}
		List<LedgerCodeRetailSaleBean> ledgerCodeSaleBeans = new ArrayList<LedgerCodeRetailSaleBean>();
		List<LedgerCodeRetailSale> ledgerCodeRetailSales = ledgerCodeRetailSaleRepository.findAll();
		for(LedgerCodeRetailSale ledgerCodeRetailSale : ledgerCodeRetailSales) {
			LedgerCodeRetailSaleBean ledgerCodeRetailSaleBean = new LedgerCodeRetailSaleBean();
			ledgerCodeSetupMappingImpl.mapRetailLedgerCodeSaleBean(ledgerCodeRetailSale, ledgerCodeRetailSaleBean);
			ledgerCodeSaleBeans.add(ledgerCodeRetailSaleBean);
		}
		branchBean.setRetailLedgerCodeSales(ledgerCodeSaleBeans);
		
		if(log.isDebugEnabled()) {
			log.debug("Retail Ledger details has been retrieved from database for vendorId: " + vendorId);
		}
		return uiModel;
	}
	
	@Transactional(value="retailTransactionManager")
	public UIModel saveRetailLedgerCode(UIModel uiModel) {
		if(uiModel.getBranchBean().getRetailLedgerCodePurchases() != null && uiModel.getBranchBean().getRetailLedgerCodePurchases().size() > 0 ) {
			List<LedgerCodeRetailPurchaseBean> retailLedgerCodeBeans = uiModel.getBranchBean().getRetailLedgerCodePurchases();
			
			for(LedgerCodeRetailPurchaseBean retailLedgerCodeBean : retailLedgerCodeBeans) {
				int vendorId = retailLedgerCodeBean.getVendorId();
				int materialGrpId = retailLedgerCodeBean.getMaterialGrpId();
				
				VendorMaster vendor = retailVendorMasterRepository.findOne(vendorId);
				if(vendor == null) {
					String msg = "Vendor does not exist for vendor Id: " + vendorId;
					log.error(msg);
					throw new RetailException(msg, RetailBusinessConstants.EXCEPTION_RETAIL);
				}
				
				MaterialGroup materialGroup = retailMaterialGroupMasterRepository.findOne(materialGrpId);
				if(materialGroup == null) {
					String msg = "Material Group does not exist for material group Id: " + materialGrpId;
					log.error(msg);
					throw new RetailException(msg, RetailBusinessConstants.EXCEPTION_RETAIL);
				}
				
				LedgerCodeRetailPurchase LedgerCodeRetail = new LedgerCodeRetailPurchase();
				ledgerCodeSetupMappingImpl.mapRetailLedgerCodePurchaseBean(retailLedgerCodeBean, LedgerCodeRetail);
				retailLedgerCodePurchaseRepository.saveAndFlush(LedgerCodeRetail);
//				retailLedgerCodes.add(retailLedgerCode);
			}
		} else if(uiModel.getBranchBean().getRetailLedgerCodeSales() != null && uiModel.getBranchBean().getRetailLedgerCodeSales().size() > 0 ) {
			List<LedgerCodeRetailSaleBean> retailLedgerCodeBeans = uiModel.getBranchBean().getRetailLedgerCodeSales();
			
			for(LedgerCodeRetailSaleBean retailLedgerCodeSaleBean : retailLedgerCodeBeans) {
				LedgerCodeRetailSale LedgerCodeRetailSale = new LedgerCodeRetailSale();
				ledgerCodeSetupMappingImpl.mapRetailLedgerCodeSaleBean(retailLedgerCodeSaleBean, LedgerCodeRetailSale);
				ledgerCodeRetailSaleRepository.saveAndFlush(LedgerCodeRetailSale);
//				retailLedgerCodes.add(retailLedgerCode);
			}
		} else {
			uiModel.setErrorMsg("Retail Ledger code details not passed correctly");
		}
		return uiModel;
	}
	
	@Transactional(value="retailTransactionManager")
	public UIModel deleteRetailLedgerCode(UIModel uiModel) {
		
		return uiModel;
	}
	
	@Transactional(value="retailTransactionManager")
	public UIModel getPaymentLedgerCode() {
		UIModel uiModel = new UIModel();
		BranchBean branchBean = new BranchBean();
		uiModel.setBranchBean(branchBean);
		List<LedgerCodePaymentBean> ledgerCodePaymentBeans = new ArrayList<LedgerCodePaymentBean>();
		Pageable  pagable = new PageRequest(1, 1000, Sort.Direction.DESC, "glCode");
		Page<LedgerCodePayment> paymentLedgerCodes = ledgerCodePaymentRepository.findAll(pagable);
		
		for(LedgerCodePayment ledgerCodePayment : paymentLedgerCodes) {
			LedgerCodePaymentBean ledgerCodePaymentBean = new LedgerCodePaymentBean();
			ledgerCodeSetupMappingImpl.mapRetailLedgerCodePurchaseBean(ledgerCodePayment, ledgerCodePaymentBean);
			ledgerCodePaymentBeans.add(ledgerCodePaymentBean);
		}
		branchBean.setPaymentLedgerCodes(ledgerCodePaymentBeans);
		
		
		if(log.isDebugEnabled()) {
			log.debug("Payment Retail Ledger details has been retrieved from database");
		}
		return uiModel;
	}
	
	@Transactional(value="retailTransactionManager")
	public UIModel savePaymentLedgerCode(UIModel uiModel) {
		if(uiModel.getBranchBean().getPaymentLedgerCodes() != null && uiModel.getBranchBean().getPaymentLedgerCodes().size() > 0 ) {
			List<LedgerCodePaymentBean> paymentLedgerCodeBeans = uiModel.getBranchBean().getPaymentLedgerCodes();
			
			if(paymentLedgerCodeBeans != null && paymentLedgerCodeBeans.size() > 0) {
				for(LedgerCodePaymentBean paymentLedgerCodeBean : paymentLedgerCodeBeans) {
					LedgerCodePayment paymentLedgerCode = new LedgerCodePayment();
					ledgerCodeSetupMappingImpl.mapPaymentLedgerCodeBean(paymentLedgerCodeBean, paymentLedgerCode);
					ledgerCodePaymentRepository.saveAndFlush(paymentLedgerCode);
				}
			}
		} else {
			uiModel.setErrorMsg("Payment Ledger code details not passed correctly");
		}
		return uiModel;
	}
}
