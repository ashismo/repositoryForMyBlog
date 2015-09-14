package com.ashish.medicine.admin.wholeseller;

import com.ashish.medicine.exception.AppException;

public class WholeSellerServiceImpl implements WholeSellerService {

	public void addOrUpdateWholeSeller(WholeSellerBean wholesellerBean) throws AppException {
		new WholeSellerServiceHelperImpl().addOrUpdateWholeSeller(wholesellerBean);
	}

	public void searchWholeSeller(WholeSellerBean wholesellerBean) throws AppException {
		new WholeSellerServiceHelperImpl().searchWholeSeller(wholesellerBean);
	}

	public void viewWholeSeller(WholeSellerBean wholesellerBean) throws AppException {
		new WholeSellerServiceHelperImpl().viewWholeSeller(wholesellerBean);
	}

	public void deleteWholeSeller(WholeSellerBean wholesellerBean) throws AppException {
		new WholeSellerServiceHelperImpl().deleteWholeSeller(wholesellerBean);
	}

	public void getAllWholesellers(WholeSellerBean wholesellerBean) throws AppException {
		new WholeSellerServiceHelperImpl().getAllWholesellers(wholesellerBean);		
	}
}
