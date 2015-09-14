package com.ashish.medicine.admin.company;

import com.ashish.medicine.exception.AppException;

public class CompanyServiceHelperImpl implements CompanyServiceHelper {

	public void addOrUpdateCompany(CompanyBean companyBean) throws AppException {
		new CompanyDaoImpl().addOrUpdateCompany(companyBean);
	}

	public void searchCompany(CompanyBean companyBean) throws AppException {
		new CompanyDaoImpl().searchCompany(companyBean);
	}

	public void viewCompany(CompanyBean companyBean) throws AppException {
		new CompanyDaoImpl().viewCompany(companyBean);
	}

	public void deleteCompany(CompanyBean companyBean) throws AppException {
		new CompanyDaoImpl().deleteCompany(companyBean);		
	}

	public void getAllCompanies(CompanyBean companyBean) throws AppException {
		new CompanyDaoImpl().getAllCompanies(companyBean);		
	}
}
