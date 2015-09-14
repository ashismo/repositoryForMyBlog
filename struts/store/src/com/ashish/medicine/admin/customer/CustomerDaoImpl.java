package com.ashish.medicine.admin.customer;

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
import com.ashish.medicine.admin.customer.CustomerBean;
import com.ashish.medicine.admin.customer.CustomerBean;
import com.ashish.medicine.admin.customer.CustomerBean;
import com.ashish.medicine.admin.customer.CustomerBean;
import com.ashish.medicine.admin.doctor.DoctorBean;
import com.ashish.medicine.entity.Customer;
import com.ashish.medicine.entity.Customer;
import com.ashish.medicine.entity.Customer;
import com.ashish.medicine.entity.Customer;
import com.ashish.medicine.entity.WholeSeller;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.hinernate.util.HibernateUtil;

public class CustomerDaoImpl extends MedicineBaseDaoImpl implements CustomerDao {
//	private Session s = HibernateUtil.getSessionFactory().openSession();
	
	public void searchCustomer(CustomerBean customerBean) throws AppException {
		try {
			String customerAddr1 = "%";
			String customerName = "%";
			// Search Customers by page
			int startIndex = (customerBean.getPage() - 1) * customerBean.getRows();
			int totalRecords = customerBean.getRows();
			Query query = s.getNamedQuery("searchCustomers");
			if(customerBean.getCustomerName() != null && !"".equals(customerBean.getCustomerName().trim())) {
				customerName = customerName + customerBean.getCustomerName().toUpperCase() + "%";
			}
			if(customerBean.getCustomerAddr1() != null && !"".equals(customerBean.getCustomerAddr1().trim())) {
				customerAddr1 = customerAddr1 + customerBean.getCustomerAddr1().toUpperCase() + "%";
			}
			query.setString("customerAddr1", customerAddr1);
			query.setString("customerName", customerName);
			
			query.setFirstResult(startIndex);
			query.setMaxResults(totalRecords);
			List<Customer> customerList = query.list();
			List<CustomerBean> cBeanList = new ArrayList<CustomerBean>();
			for(Customer c: customerList) {
				CustomerBean cBean = new CustomerBean();
				BeanUtils.copyProperties(cBean, c);
				cBeanList.add(cBean);
			}
			customerBean.setSearchCustomerList(cBeanList);
			
			// count customers
			query = s.getNamedQuery("countCustomers");
			query.setString("customerAddr1", customerAddr1);
			query.setString("customerName", customerName);
			List<Integer> countCustomers = query.list();
			if(countCustomers != null && countCustomers.size() > 0) {
				customerBean.setTotal(Integer.parseInt(countCustomers.get(0) + ""));
			} else {
				customerBean.setTotal(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} 
	}
	
	public boolean checkDuplicateCustomerByCustomerName(CustomerBean customerBean) throws AppException {
		if(customerBean.getCustomerName() != null) {
			Query query = s.getNamedQuery("getCustomerByCustomerName");
			query.setString("customerName", customerBean.getCustomerName().toUpperCase());
			List<Customer> customerList = query.list();
			
			if(customerList != null && customerList.size() > 0) {
				if(customerBean.getCustomerId() == customerList.get(0).getCustomerId()) {
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
	
	public void addOrUpdateCustomer(CustomerBean customerBean) throws AppException {
		Transaction tx = null;
		Customer c = new Customer();
		try {
			if(checkDuplicateCustomerByCustomerName(customerBean)) {
				customerBean.setMsg("Customer Name Already Exists");
				throw new AppException();
			}
			if(customerBean.getCustomerId() == 0) {
				customerBean.setMsg("Customer Created");
			}
			
			BeanUtils.copyProperties(c, customerBean);
			
			if(customerBean.getCustomerId() == 0) {
				c.setDbAddTs(new Timestamp(new Date().getTime()));
				c.setDbUpdTs(new Timestamp(new Date().getTime()));
				c.setDbAddUser(customerBean.getDbAddUser());
			} else {
				Customer tempCust = null;
				Query query = s.getNamedQuery("getCustomerDetailsByCustomerId");
				query.setLong("customerId", customerBean.getCustomerId());
				List<Customer> custList = query.list();
				
				if(custList != null && custList.size() == 1) {
					tempCust = custList.get(0);
				}
				c.setDbAddTs(tempCust.getDbAddTs());
				c.setDbAddUser(tempCust.getDbAddUser());
				c.setDbUpdTs(new Timestamp(new Date().getTime()));
				c.setDbUpdUser(customerBean.getDbUpdUser());
				s.close();
				getHibernateSession();
			}
			
			tx = s.beginTransaction();
			s.saveOrUpdate(c);
			
		} catch (Exception e) {
			if(customerBean.getMsg() != null && "".equals(customerBean.getMsg().trim())) {
				customerBean.setMsg("Operation failed");
			}
			e.printStackTrace();
			throw new AppException();
		} finally {
			try {
				if(tx != null)tx.commit();
			} catch (Exception e) {
				if(tx != null)tx.rollback();
				e.printStackTrace();
				customerBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
		
		if(customerBean.getCustomerId() == 0) {
			customerBean.setMsg("Customer Created successfully!!!!!");
			customerBean.setCustomerId(c.getCustomerId());
		} else {
			customerBean.setMsg("Customer Updated successfully!!!!!");
		}
		
	}
	
	public void viewCustomer(CustomerBean customerBean) throws AppException {
		Query query = s.getNamedQuery("getCustomerDetailsByCustomerId");
		query.setLong("customerId", customerBean.getCustomerId());
		List<Customer> customerList = query.list();
		
		if(customerList != null && customerList.size() == 1) {
			try {
				BeanUtils.copyProperties(customerBean, customerList.get(0));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			throw new AppException();
		}
	}

	public void deleteCustomer(CustomerBean customerBean) throws AppException {
		if(customerBean.getCustomerId() == 0) {
			customerBean.setMsg("Operation Failed...<br>Customer Does not Exists");
			throw new AppException();
		}
		Query query = s.getNamedQuery("getCustomerDetailsByCustomerId");
		query.setLong("customerId", customerBean.getCustomerId());
		List<Customer> customerList = query.list();
		
		if(customerList != null && customerList.size() == 1) {
			Transaction tx = null;
			try {
				tx = s.beginTransaction();
				s.delete(customerList.get(0));
				customerBean.setMsg("Customer Deleted Successfully");
			} catch (Exception e) {
				if(customerBean.getMsg() != null && "".equals(customerBean.getMsg().trim())) {
					customerBean.setMsg("Operation failed");
				}
				e.printStackTrace();
				throw new AppException();
			} finally {
				try {
					if(tx != null)tx.commit();
				} catch (Exception e) {
					if(tx != null)tx.rollback();
					e.printStackTrace();
					customerBean.setMsg("Operation failed. Unable to commit changes into database.");
				}
				if(s != null) s.close();
			}
		}
	}
	
	public void addCustomer(CustomerBean customerBean) throws AppException {
		if(customerBean.getCustomerId() == 0) {
			Customer c = new Customer();
			try {
				BeanUtils.copyProperties(c, customerBean);
				c.setDbAddTs(new Timestamp(new Date().getTime()));
				c.setDbUpdTs(new Timestamp(new Date().getTime()));
				c.setDbAddUser(customerBean.getDbAddUser());
				
				Session s = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = s.beginTransaction();
				s.save(c);
				try {
					if(tx != null)tx.commit();
				} catch (Exception e) {
					if(tx != null)tx.rollback();
					e.printStackTrace();
					customerBean.setMsg("Operation failed. Unable to commit changes into database.");
				}
				s.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new AppException();
			} 
		}
		
	}

	public void getAllCustomers(CustomerBean customerBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getAllCustomers");
			List<CustomerBean> customerList =
				query.setResultTransformer(Transformers.aliasToBean(CustomerBean.class)).list();
			CustomerBean customer = new CustomerBean();
			customer.setCustomerId(-1);
			customer.setCustomerName("Other");
			customerList.add(0, customer);
			customerBean.setCustomerList(customerList);
		} catch(Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch customer list");
		}
	}

	public void getCustomerByCustomerId(CustomerBean customerBean)
			throws AppException {
		try {
			Query query = s.getNamedQuery("getCustomerByCustomerId");
			query.setInteger("customerId", customerBean.getCustomerId());
			List<CustomerBean> customerList =
				query.setResultTransformer(Transformers.aliasToBean(CustomerBean.class)).list();
			if(customerList != null && customerList.size() > 0) {
				BeanUtils.copyProperties(customerBean, customerList.get(0));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch customer list");
		}
	}

	

}
