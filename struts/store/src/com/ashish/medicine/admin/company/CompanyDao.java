package com.ashish.medicine.admin.company;

import com.ashish.medicine.entity.Company;
import com.ashish.medicine.exception.AppException;

public interface CompanyDao {
	public void addOrUpdateCompany(CompanyBean companyBean) throws AppException;
	public void getAllCompanies(CompanyBean companyBean) throws AppException;
	public void searchCompany(CompanyBean companyBean) throws AppException;
	public void viewCompany(CompanyBean companyBean) throws AppException;
	public void deleteCompany(CompanyBean companyBean) throws AppException;
	public Company getCompanyByCompanyId(CompanyBean companyBean) throws AppException;
}
