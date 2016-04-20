package com.org.coop.retail.bs.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.account.beans.CardRegisterBean;
import com.org.coop.canonical.account.beans.CashRegisterBean;
import com.org.coop.canonical.account.beans.ChequeRegisterBean;
import com.org.coop.retail.entities.BranchMaster;
import com.org.coop.retail.entities.GlLedgerHrd;
import com.org.coop.retail.entities.TransactionPayment;
import com.org.coop.retail.repositories.GlLedgerHeaderRepository;
import com.org.coop.retail.repositories.RetailBranchMasterRepository;

@Component("transactionPaymentCC")
public class TransactionPaymentCC extends DozerConverter<Object, TransactionPayment> implements MapperAware, CustomConverter {
	@Autowired
	private RetailBranchMasterRepository retailBranchMasterRepository;
	
	@Autowired
	private GlLedgerHeaderRepository glLedgerHeaderRepository;
	
	public TransactionPaymentCC() {
		super(Object.class, TransactionPayment.class);
	}
	
	public TransactionPaymentCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object convertFrom(TransactionPayment src, Object dest) {
		return null;
	}

	@Override
	public TransactionPayment convertTo(Object src, TransactionPayment dest) {
		if(src != null) {
			int branchId = 0;
			int glTranId = 0;
			if("CASH_PAYMENT".equalsIgnoreCase(getParameter())) {
				CashRegisterBean cash = (CashRegisterBean) src;
				branchId = cash.getBranchId();
				glTranId = cash.getGlTranId();
			} else if("CHEQUE_PAYMENT".equalsIgnoreCase(getParameter())) {
				ChequeRegisterBean cheque = (ChequeRegisterBean) src;
				branchId = cheque.getBranchId();
				glTranId = cheque.getGlTranId();
			} else if("CARD_PAYMENT".equalsIgnoreCase(getParameter())) {
				CardRegisterBean card = (CardRegisterBean) src;
				branchId = card.getBranchId();
				glTranId = card.getGlTranId();
			}
			BranchMaster branch = retailBranchMasterRepository.findOne(branchId);
			GlLedgerHrd glLedgerHrd = glLedgerHeaderRepository.findOne(glTranId);
			dest.setBranchMaster(branch);
			dest.setGlLedgerHrd(glLedgerHrd);
		}
		return dest;
	}
}
