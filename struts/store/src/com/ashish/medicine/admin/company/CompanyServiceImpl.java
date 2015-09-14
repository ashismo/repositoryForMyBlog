package com.ashish.medicine.admin.company;

import com.ashish.medicine.exception.AppException;

public class CompanyServiceImpl implements CompanyService {

	public void addOrUpdateCompany(CompanyBean companyBean) throws AppException {
		new CompanyServiceHelperImpl().addOrUpdateCompany(companyBean);
	}

	public void searchCompany(CompanyBean companyBean) throws AppException {
		new CompanyServiceHelperImpl().searchCompany(companyBean);
	}

	public void viewCompany(CompanyBean companyBean) throws AppException {
		new CompanyServiceHelperImpl().viewCompany(companyBean);
	}

	public void deleteCompany(CompanyBean companyBean) throws AppException {
		new CompanyServiceHelperImpl().deleteCompany(companyBean);
	}

	public void getAllCompanies(CompanyBean companyBean) throws AppException {
		new CompanyServiceHelperImpl().getAllCompanies(companyBean);
	}

}
