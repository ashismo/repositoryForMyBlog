package com.ashish.medicine.admin.buy;

import com.ashish.medicine.exception.AppException;

public class BuyServiceHelperImpl implements BuyServiceHelper {

	public void addBuy(BuyBean buyBean) throws AppException {
		new BuyDaoImpl().addBuy(buyBean);
	}
}
