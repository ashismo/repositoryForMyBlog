package com.ashish.medicine.admin.wholeseller;

import com.ashish.medicine.exception.AppException;

public class WholeSellerServiceHelperImpl implements WholeSellerServiceHelper {

	public void addOrUpdateWholeSeller(WholeSellerBean wholesellerBean) throws AppException {
		new WholeSellerDaoImpl().addOrUpdateWholeSeller(wholesellerBean);
	}

	public void searchWholeSeller(WholeSellerBean wholesellerBean) throws AppException {
		new WholeSellerDaoImpl().searchWholeSeller(wholesellerBean);
	}

	public void viewWholeSeller(WholeSellerBean wholesellerBean) throws AppException {
		new WholeSellerDaoImpl().viewWholeSeller(wholesellerBean);
	}

	public void deleteWholeSeller(WholeSellerBean wholesellerBean) throws AppException {
		new WholeSellerDaoImpl().deleteWholeSeller(wholesellerBean);		
	}
	
	public void getAllWholesellers(WholeSellerBean wholesellerBean) throws AppException {
		new WholeSellerDaoImpl().getAllWholesellers(wholesellerBean);		
	}
}
