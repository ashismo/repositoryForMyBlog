package com.ashish.medicine.admin.company;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.ashish.medicine.admin.base.MedicineBaseDaoImpl;
import com.ashish.medicine.entity.Company;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.hinernate.util.HibernateUtil;

public class CompanyDaoImpl extends MedicineBaseDaoImpl implements CompanyDao {

//	private Session s = null;
//	public CompanyDaoImpl() {
//		try {
//			s = HibernateUtil.getSessionFactory().openSession();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void getHibernateSession() {
//		try {
//			s = HibernateUtil.getSessionFactory().openSession();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	public void addOrUpdateCompany(CompanyBean companyBean) throws AppException {
		Transaction tx = null;
		Company c = new Company();
		Company tempCompany = null;
		try {
			if(checkDuplicateCompanyByCompanyName(companyBean)) {
				companyBean.setMsg("Manufacturer Name Already Exists");
				throw new AppException();
			}
			if(companyBean.getCompanyId() == 0) {
				companyBean.setMsg("Manufacturer Created");
			}
			
			BeanUtils.copyProperties(c, companyBean);
			
			if(companyBean.getCompanyId() == 0) {
				c.setDbAddTs(new Timestamp(new Date().getTime()));
				c.setDbUpdTs(new Timestamp(new Date().getTime()));
				c.setDbAddUser(companyBean.getDbAddUser());
			} else {
				Query query = s.getNamedQuery("getCompanyByCompanyId");
				query.setLong("companyId", companyBean.getCompanyId());
				List<Company> companyList = query.list();
				
				if(companyList != null && companyList.size() == 1) {
					tempCompany = companyList.get(0);
				}
				c.setDbAddTs(tempCompany.getDbAddTs());
				c.setDbAddUser(tempCompany.getDbAddUser());
				c.setDbUpdTs(new Timestamp(new Date().getTime()));
				c.setDbUpdUser(companyBean.getDbUpdUser());
				s.close();
				getHibernateSession();
			}
			
			tx = s.beginTransaction();
			s.saveOrUpdate(c);
			
		} catch (Exception e) {
			if(companyBean.getMsg() != null && "".equals(companyBean.getMsg().trim())) {
				companyBean.setMsg("Operation failed");
			}
			tx.rollback();
			e.printStackTrace();
			throw new AppException();
		} finally {
			try {
				if(tx != null)tx.commit();
			} catch (Exception e) {
				if(tx != null)tx.rollback();
				e.printStackTrace();
				companyBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
		
		if(companyBean.getCompanyId() == 0) {
			companyBean.setMsg("Manufacturer Created successfully!!!!!");
			companyBean.setCompanyId(c.getCompanyId());
		} else {
			companyBean.setMsg("Manufacturer Updated successfully!!!!!");
		}
		
	}

	public void searchCompany(CompanyBean companyBean) throws AppException {
		try {
			String pin = "%";
			String state = "%";
			String companyName = "%";
			// Search Companies by page
			int startIndex = (companyBean.getPage() - 1) * companyBean.getRows();
			int totalRecords = companyBean.getRows();
			Query query = s.getNamedQuery("searchCompanies");
			if(companyBean.getCompanyName() != null && !"".equals(companyBean.getCompanyName().trim())) {
				companyName = companyName + companyBean.getCompanyName().toUpperCase() + "%";
			}
			if(companyBean.getState() != null && !"".equals(companyBean.getState().trim())) {
				state = state + companyBean.getState().toUpperCase() + "%";
			}
			if(companyBean.getPin() != null && !"".equals(companyBean.getPin().trim())) {
				pin = pin + companyBean.getPin().toUpperCase() + "%";
			}
			query.setString("pin", pin);
			query.setString("state", state);
			query.setString("companyName", companyName);
			
			query.setFirstResult(startIndex);
			query.setMaxResults(totalRecords);
			List<Company> companyList = query.list();
			List<CompanyBean> cBeanList = new ArrayList<CompanyBean>();
			for(Company c: companyList) {
				CompanyBean cBean = new CompanyBean();
				BeanUtils.copyProperties(cBean, c);
				cBeanList.add(cBean);
			}
			companyBean.setSearchCompanyList(cBeanList);
			
			// count companies
			query = s.getNamedQuery("countCompanies");
			query.setString("pin", pin);
			query.setString("state", state);
			query.setString("companyName", companyName);
			List<Integer> countCompanies = query.list();
			if(countCompanies != null && countCompanies.size() > 0) {
				companyBean.setTotal(Integer.parseInt(countCompanies.get(0) + ""));
			} else {
				companyBean.setTotal(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} 
	}

	public void viewCompany(CompanyBean companyBean) throws AppException {
		Query query = s.getNamedQuery("getCompanyByCompanyId");
		query.setLong("companyId", companyBean.getCompanyId());
		List<Company> companyList = query.list();
		
		if(companyList != null && companyList.size() == 1) {
			try {
				BeanUtils.copyProperties(companyBean, companyList.get(0));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			throw new AppException();
		}
	}
	public boolean checkDuplicateCompanyByCompanyName(CompanyBean companyBean) throws AppException {
		if(companyBean.getCompanyName() != null) {
			Query query = s.getNamedQuery("getCompanyByCompanyName");
			query.setString("companyName", companyBean.getCompanyName().toUpperCase());
			List<Company> companyList = query.list();
			
			if(companyList != null && companyList.size() > 0) {
				if(companyBean.getCompanyId() == companyList.get(0).getCompanyId()) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	public void deleteCompany(CompanyBean companyBean) throws AppException {
		if(companyBean.getCompanyId() == 0) {
			companyBean.setMsg("Operation Failed...<br>Manufacturer Does not Exists");
			throw new AppException();
		}
		Query query = s.getNamedQuery("getCompanyByCompanyId");
		query.setLong("companyId", companyBean.getCompanyId());
		List<Company> companyList = query.list();
		
		if(companyList != null && companyList.size() == 1) {
			Transaction tx = null;
			try {
				tx = s.beginTransaction();
				s.delete(companyList.get(0));
				companyBean.setMsg("Manufacturer Deleted Successfully");
			} catch (Exception e) {
				if(companyBean.getMsg() != null && "".equals(companyBean.getMsg().trim())) {
					companyBean.setMsg("Operation failed");
				}
				tx.rollback();
				e.printStackTrace();
				throw new AppException();
			} finally {
				try {
					if(tx != null)tx.commit();
				} catch (Exception e) {
					if(tx != null)tx.rollback();
					e.printStackTrace();
					companyBean.setMsg("Operation failed.Unable to commit changes into database.");
				}
				if(s != null) s.close();
			}
		}
	}

	public void getAllCompanies(CompanyBean companyBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getAllCompanies");
			List<CompanyBean> companyList =
				query.setResultTransformer(Transformers.aliasToBean(CompanyBean.class)).list();
			
			companyBean.setSearchCompanyList(companyList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch all companies");
		} finally {
			s.close();
		}
	}
	
	public Company getCompanyByCompanyId(CompanyBean companyBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getCompanyByCompanyId");
			query.setLong("companyId", companyBean.getCompanyId());
			List<Company> companyList = query.list();
			
			if(companyList != null && companyList.size() == 1) {
				return companyList.get(0);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
		return null;
	}
}
