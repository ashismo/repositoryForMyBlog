package com.ashish.medicine.admin.buy;

import com.ashish.medicine.exception.AppException;

public class BuyServiceImpl implements BuyService {

	public void addBuy(BuyBean buyBean) throws AppException {
		new BuyServiceHelperImpl().addBuy(buyBean);
	}

}
