package com.ashish.business;

import com.ashish.beans.DebitCardBean;

public interface DebitCardManagement {
	public void addDebitCardCustomerData(DebitCardBean dcBean);
	public void payCreditCardBill(DebitCardBean dcBean);
}
