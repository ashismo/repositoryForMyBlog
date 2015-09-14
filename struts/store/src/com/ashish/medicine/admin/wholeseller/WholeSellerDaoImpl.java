package com.ashish.medicine.admin.wholeseller;

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
import com.ashish.medicine.admin.medicine.MedicineBean;
import com.ashish.medicine.entity.Company;
import com.ashish.medicine.entity.WholeSeller;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.hinernate.util.HibernateUtil;

public class WholeSellerDaoImpl extends MedicineBaseDaoImpl implements WholeSellerDao {

//	private Session s = HibernateUtil.getSessionFactory().openSession();
	public void addOrUpdateWholeSeller(WholeSellerBean wholesellerBean) throws AppException {
		Transaction tx = null;
		WholeSeller ws = new WholeSeller();
		try {
			if(checkDuplicateWholeSellerByWholeSellerName(wholesellerBean)) {
				wholesellerBean.setMsg("Whole Seller Name Already Exists");
				throw new AppException();
			}
			if(wholesellerBean.getWholesellerId() == 0) {
				wholesellerBean.setMsg("Whole Seller Created");
			}
			
			BeanUtils.copyProperties(ws, wholesellerBean);
			
			if(wholesellerBean.getWholesellerId() == 0) {
				ws.setDbAddTs(new Timestamp(new Date().getTime()));
				ws.setDbUpdTs(new Timestamp(new Date().getTime()));
				ws.setDbAddUser(wholesellerBean.getDbAddUser());
			} else {
				WholeSeller tempWholeseller = null;
				Query query = s.getNamedQuery("getWholeSellerByWholeSellerId");
				query.setLong("wholesellerId", wholesellerBean.getWholesellerId());
				List<WholeSeller> wholesellerList = query.list();
				
				if(wholesellerList != null && wholesellerList.size() == 1) {
					tempWholeseller = wholesellerList.get(0);
				}
				ws.setDbAddTs(tempWholeseller.getDbAddTs());
				ws.setDbAddUser(tempWholeseller.getDbAddUser());
				ws.setDbUpdTs(new Timestamp(new Date().getTime()));
				ws.setDbUpdUser(wholesellerBean.getDbUpdUser());
				s.close();
				getHibernateSession();
			}
			tx = s.beginTransaction();
			s.saveOrUpdate(ws);
			
		} catch (Exception e) {
			if(wholesellerBean.getMsg() != null && "".equals(wholesellerBean.getMsg().trim())) {
				wholesellerBean.setMsg("Operation failed");
			}
			e.printStackTrace();
			throw new AppException();
		} finally {
			try {
				if(tx != null)tx.commit();
			} catch (Exception e) {
				if(tx != null)tx.rollback();
				e.printStackTrace();
				wholesellerBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
		
		if(wholesellerBean.getWholesellerId() == 0) {
			wholesellerBean.setMsg("Whole Seller Created successfully!!!!!");
			wholesellerBean.setWholesellerId(ws.getWholesellerId());
		} else {
			wholesellerBean.setMsg("Whole Seller Updated successfully!!!!!");
		}
		
	}

	public void searchWholeSeller(WholeSellerBean wholesellerBean) throws AppException {
		try {
			String pin = "%";
			String state = "%";
			String wholesellerName = "%";
			// Search WholeSeller by page
			int startIndex = (wholesellerBean.getPage() - 1) * wholesellerBean.getRows();
			int totalRecords = wholesellerBean.getRows();
			Query query = s.getNamedQuery("searchWholeSeller");
			if(wholesellerBean.getWholesellerName() != null && !"".equals(wholesellerBean.getWholesellerName().trim())) {
				wholesellerName = wholesellerName + wholesellerBean.getWholesellerName().toUpperCase() + "%";
			}
			if(wholesellerBean.getState() != null && !"".equals(wholesellerBean.getState().trim())) {
				state = state + wholesellerBean.getState().toUpperCase() + "%";
			}
			if(wholesellerBean.getPin() != null && !"".equals(wholesellerBean.getPin().trim())) {
				pin = pin + wholesellerBean.getPin().toUpperCase() + "%";
			}
			query.setString("pin", pin);
			query.setString("state", state);
			query.setString("wholesellerName", wholesellerName);
			
			query.setFirstResult(startIndex);
			query.setMaxResults(totalRecords);
			List<WholeSeller> wholesellerList = query.list();
			List<WholeSellerBean> cBeanList = new ArrayList<WholeSellerBean>();
			for(WholeSeller c: wholesellerList) {
				WholeSellerBean cBean = new WholeSellerBean();
				BeanUtils.copyProperties(cBean, c);
				cBeanList.add(cBean);
			}
			wholesellerBean.setSearchWholeSellerList(cBeanList);
			
			// count companies
			query = s.getNamedQuery("countWholeSeller");
			query.setString("pin", pin);
			query.setString("state", state);
			query.setString("wholesellerName", wholesellerName);
			List<Integer> countCompanies = query.list();
			if(countCompanies != null && countCompanies.size() > 0) {
				wholesellerBean.setTotal(Integer.parseInt(countCompanies.get(0) + ""));
			} else {
				wholesellerBean.setTotal(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} 
	}

	public void viewWholeSeller(WholeSellerBean wholesellerBean) throws AppException {
		Query query = s.getNamedQuery("getWholeSellerByWholeSellerId");
		query.setLong("wholesellerId", wholesellerBean.getWholesellerId());
		List<WholeSeller> wholesellerList = query.list();
		
		if(wholesellerList != null && wholesellerList.size() == 1) {
			try {
				BeanUtils.copyProperties(wholesellerBean, wholesellerList.get(0));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			throw new AppException();
		}
	}
	public boolean checkDuplicateWholeSellerByWholeSellerName(WholeSellerBean wholesellerBean) throws AppException {
		if(wholesellerBean.getWholesellerName() != null) {
			Query query = s.getNamedQuery("getWholeSellerByWholeSellerName");
			query.setString("wholesellerName", wholesellerBean.getWholesellerName().toUpperCase());
			List<WholeSeller> wholesellerList = query.list();
			
			if(wholesellerList != null && wholesellerList.size() > 0) {
				if(wholesellerBean.getWholesellerId() == wholesellerList.get(0).getWholesellerId()) {
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

	public void deleteWholeSeller(WholeSellerBean wholesellerBean) throws AppException {
		if(wholesellerBean.getWholesellerId() == 0) {
			wholesellerBean.setMsg("Operation Failed...<br>Whole Seller Does not Exists");
			throw new AppException();
		}
		Query query = s.getNamedQuery("getWholeSellerByWholeSellerId");
		query.setLong("wholesellerId", wholesellerBean.getWholesellerId());
		List<WholeSeller> wholesellerList = query.list();
		
		if(wholesellerList != null && wholesellerList.size() == 1) {
			Transaction tx = null;
			try {
				tx = s.beginTransaction();
				s.delete(wholesellerList.get(0));
				s.flush();
				wholesellerBean.setMsg("Whole Seller Deleted Successfully");
			} catch (Exception e) {
				if(wholesellerBean.getMsg() != null && "".equals(wholesellerBean.getMsg().trim())) {
					wholesellerBean.setMsg("Operation failed");
				}
				e.printStackTrace();
				throw new AppException();
			} finally {
				try {
					if(tx != null)tx.commit();
				} catch (Exception e) {
					if(tx != null)tx.rollback();
					e.printStackTrace();
					wholesellerBean.setMsg("Operation failed. Unable to commit changes into database.");
				}
				if(s != null) s.close();
			}
		}
	}
	public void getAllWholesellers(WholeSellerBean wholeSellerBean)
			throws AppException {
		try {
			createDummyWholeSeller(wholeSellerBean);
			Query query = s.getNamedQuery("getAllWholesellers");
			List<WholeSellerBean> wsList =
				query.setResultTransformer(Transformers.aliasToBean(WholeSellerBean.class)).list();
			List<WholeSellerBean> wholesellerList = new ArrayList<WholeSellerBean>();
			WholeSellerBean wsBean = null;
			for (WholeSellerBean wsb : wsList) {
				if(!"NONE".equals(wsb.getWholesellerName())) {
					wholesellerList.add(wsb);
				} else {
					wsBean = wsb;
				}
			}
			
			
			wholesellerList.add(0, wsBean);
			wholeSellerBean.setWholeSellerList(wholesellerList);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch Wholeseller List");
		}
	}
	
	private void createDummyWholeSeller(WholeSellerBean wholesellerBean) throws AppException {
		Transaction tx = null;
		WholeSeller ws = new WholeSeller();
		try {
			Query query = s.getNamedQuery("getWholeSellerByWholeSellerName");
			query.setString("wholesellerName", "NONE");
			List<WholeSeller> wholesellerList = query.list();
			
			if(wholesellerList != null && wholesellerList.size() == 0) {
				ws.setWholesellerId(0);
				ws.setWholesellerName("NONE");
				ws.setDbAddTs(new Timestamp(new Date().getTime()));
				ws.setDbUpdTs(new Timestamp(new Date().getTime()));
				tx = s.beginTransaction();
				s.saveOrUpdate(ws);
			}
		} catch (Exception e) {
			System.out.println("Unable to create Dummy whole seller");
			e.printStackTrace();
			throw new AppException();
		} finally {
			try {
				if(tx != null)tx.commit();
			} catch (Exception e) {
				if(tx != null)tx.rollback();
				e.printStackTrace();
				wholesellerBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
//			if(s != null) s.close();
		}
	}
}
