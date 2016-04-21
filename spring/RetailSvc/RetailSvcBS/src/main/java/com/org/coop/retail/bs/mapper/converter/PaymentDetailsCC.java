package com.org.coop.retail.bs.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.account.beans.CardRegisterBean;
import com.org.coop.canonical.account.beans.CashRegisterBean;
import com.org.coop.retail.entities.CardRegister;
import com.org.coop.retail.entities.CashRegister;
import com.org.coop.retail.entities.TransactionPayment;
import com.org.coop.retail.repositories.RetailBranchMasterRepository;
import com.org.coop.retail.repositories.TransactionPaymentRepository;

@Component("paymentDetailsCC")
public class PaymentDetailsCC extends DozerConverter<Object, Object> implements MapperAware, CustomConverter {
	@Autowired
	private RetailBranchMasterRepository retailBranchMasterRepository;
	
	@Autowired
	private TransactionPaymentRepository transactionPaymentRepository;
	
	public PaymentDetailsCC() {
		super(Object.class, Object.class);
	}
	
	public PaymentDetailsCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object convertFrom(Object src, Object dest) {
		if(src != null) {
			if("CASH_REGISTER_TABLE".equalsIgnoreCase(getParameter())) {
				CashRegisterBean cashBean = (CashRegisterBean) src;
				CashRegister cash = (CashRegister) dest;
				TransactionPayment transactionPayment = transactionPaymentRepository.findOne(cashBean.getPaymentId());
				cash.setTransactionPayment(transactionPayment);
			} else if("CARD_REGISTER_TABLE".equalsIgnoreCase(getParameter())) {
				CardRegisterBean cardBean = (CardRegisterBean) src;
				CardRegister card = (CardRegister) dest;
				TransactionPayment transactionPayment = transactionPaymentRepository.findOne(cardBean.getPaymentId());
				card.setTransactionPayment(transactionPayment);
			} 
		}
		return dest;
	}

	@Override
	public Object convertTo(Object src, Object dest) {
		return null;
	}
}
