package com.ashish.medicine.admin.buy;

import com.ashish.medicine.exception.AppException;

public interface BuyDao {
	public void addBuy(BuyBean orderBean) throws AppException;
}
