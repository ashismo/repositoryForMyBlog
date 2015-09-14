package com.ashish.medicine.admin.buy;

import com.ashish.medicine.exception.AppException;

public interface BuyService {
	public void addBuy(BuyBean buyBean) throws AppException;
}
