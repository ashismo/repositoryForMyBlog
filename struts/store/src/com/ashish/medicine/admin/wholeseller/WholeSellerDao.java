package com.ashish.medicine.admin.wholeseller;

import com.ashish.medicine.exception.AppException;

public interface WholeSellerDao {
	public void addOrUpdateWholeSeller(WholeSellerBean wholesellerBean) throws AppException;
	public void searchWholeSeller(WholeSellerBean wholesellerBean) throws AppException;
	public void viewWholeSeller(WholeSellerBean wholesellerBean) throws AppException;
	public void deleteWholeSeller(WholeSellerBean wholesellerBean) throws AppException;
	public void getAllWholesellers(WholeSellerBean wholeSellerBean) throws AppException;
}
