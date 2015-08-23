package com.ashish.business;

import com.ashish.beans.CreditCardBean;

public interface CreditCardManagement {
	public void addCrCardCustomerData(CreditCardBean ccBean);
	public void payCreditCardBill(CreditCardBean ccBean);
}
