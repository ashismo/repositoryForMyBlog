package com.ashish.medicine.admin.contacts;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.ashish.medicine.admin.base.MedicineBaseDaoImpl;
import com.ashish.medicine.entity.Contact;
import com.ashish.medicine.exception.AppException;

public class ContactsDaoImpl extends MedicineBaseDaoImpl implements ContactsDao {

	public void addOrUpdateContacts(ContactsBean contactsBean) throws AppException {
		Transaction tx = null;
		Contact c = new Contact();
		Contact tempContacts = null;
		try {
			if(checkDuplicateContactsByContactName(contactsBean)) {
				contactsBean.setMsg("Contact Name Already Exists");
				throw new AppException();
			}
			if(contactsBean.getContactId() == 0) {
				contactsBean.setMsg("Contact Created");
			}
			
			BeanUtils.copyProperties(c, contactsBean);
			
			if(contactsBean.getContactId() == 0) {
				c.setDbAddTs(new Timestamp(new Date().getTime()));
				c.setDbUpdTs(new Timestamp(new Date().getTime()));
				c.setDbAddUser(contactsBean.getDbAddUser());
			} else {
				Query query = s.getNamedQuery("getContactsByContactId");
				query.setLong("contactId", contactsBean.getContactId());
				List<Contact> contactsList = query.list();
				
				if(contactsList != null && contactsList.size() == 1) {
					tempContacts = contactsList.get(0);
				}
				c.setDbAddTs(tempContacts.getDbAddTs());
				c.setDbAddUser(tempContacts.getDbAddUser());
				c.setDbUpdTs(new Timestamp(new Date().getTime()));
				c.setDbUpdUser(contactsBean.getDbUpdUser());
				s.close();
				getHibernateSession();
			}
			
			tx = s.beginTransaction();
			s.saveOrUpdate(c);
			
		} catch (Exception e) {
			if(contactsBean.getMsg() != null && "".equals(contactsBean.getMsg().trim())) {
				contactsBean.setMsg("Operation failed");
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
				contactsBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
		
		if(contactsBean.getContactId() == 0) {
			contactsBean.setMsg("Contact Created successfully!!!!!");
			contactsBean.setContactId(c.getContactId());
		} else {
			contactsBean.setMsg("Contact Updated successfully!!!!!");
		}
		
	}

	public void searchContacts(ContactsBean contactsBean) throws AppException {
		try {
			String contactsName = "%";
			String contactNo1 = "%";
			// Search Companies by page
			int startIndex = (contactsBean.getPage() - 1) * contactsBean.getRows();
			int totalRecords = contactsBean.getRows();
			Query query = s.getNamedQuery("searchContacts");
			if(contactsBean.getContactName() != null && !"".equals(contactsBean.getContactName().trim())) {
				contactsName = contactsName + contactsBean.getContactName().toUpperCase() + "%";
			}
			
			if(contactsBean.getContactNo1() != null && !"".equals(contactsBean.getContactNo1().trim())) {
				contactNo1 = contactNo1 + contactsBean.getContactNo1() + "%";
			}
			
			query.setString("contactName", contactsName);
			query.setString("contactNo1", contactNo1);
			
			query.setFirstResult(startIndex);
			query.setMaxResults(totalRecords);
			List<Contact> contactsList = query.list();
			List<ContactsBean> cBeanList = new ArrayList<ContactsBean>();
			for(Contact c: contactsList) {
				ContactsBean cBean = new ContactsBean();
				BeanUtils.copyProperties(cBean, c);
				cBeanList.add(cBean);
			}
			contactsBean.setSearchContactsList(cBeanList);
			
			// count companies
			query = s.getNamedQuery("countContacts");
			query.setString("contactName", contactsName);
			query.setString("contactNo1", contactNo1);
			List<Integer> countCompanies = query.list();
			if(countCompanies != null && countCompanies.size() > 0) {
				contactsBean.setTotal(Integer.parseInt(countCompanies.get(0) + ""));
			} else {
				contactsBean.setTotal(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} 
	}

	public void viewContacts(ContactsBean contactsBean) throws AppException {
		Query query = s.getNamedQuery("getContactsByContactId");
		query.setLong("contactId", contactsBean.getContactId());
		List<Contact> contactsList = query.list();
		
		if(contactsList != null && contactsList.size() == 1) {
			try {
				BeanUtils.copyProperties(contactsBean, contactsList.get(0));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			throw new AppException();
		}
	}
	public boolean checkDuplicateContactsByContactName(ContactsBean contactsBean) throws AppException {
		if(contactsBean.getContactName() != null) {
			Query query = s.getNamedQuery("getContactByContactName");
			query.setString("contactName", contactsBean.getContactName().toUpperCase());
			List<Contact> contactsList = query.list();
			
			if(contactsList != null && contactsList.size() > 0) {
				if(contactsBean.getContactId() == contactsList.get(0).getContactId()) {
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

	public void deleteContacts(ContactsBean contactsBean) throws AppException {
		if(contactsBean.getContactId() == 0) {
			contactsBean.setMsg("Operation Failed...<br>Contact Does not Exists");
			throw new AppException();
		}
		Query query = s.getNamedQuery("getContactsByContactId");
		query.setLong("contactId", contactsBean.getContactId());
		List<Contact> contactsList = query.list();
		
		if(contactsList != null && contactsList.size() == 1) {
			Transaction tx = null;
			try {
				tx = s.beginTransaction();
				s.delete(contactsList.get(0));
				contactsBean.setMsg("Contact Deleted Successfully");
			} catch (Exception e) {
				if(contactsBean.getMsg() != null && "".equals(contactsBean.getMsg().trim())) {
					contactsBean.setMsg("Operation failed");
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
					contactsBean.setMsg("Operation failed.Unable to commit changes into database.");
				}
				if(s != null) s.close();
			}
		}
	}

	public void getAllCompanies(ContactsBean contactsBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getAllCompanies");
			List<ContactsBean> contactsList =
				query.setResultTransformer(Transformers.aliasToBean(ContactsBean.class)).list();
			
			contactsBean.setSearchContactsList(contactsList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("Failed to fetch all companies");
		} finally {
			s.close();
		}
	}
	
	public Contact getContactsByContactId(ContactsBean contactsBean) throws AppException {
		try {
			Query query = s.getNamedQuery("getContactsByContactId");
			query.setLong("contactsId", contactsBean.getContactId());
			List<Contact> contactsList = query.list();
			
			if(contactsList != null && contactsList.size() == 1) {
				return contactsList.get(0);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
		return null;
	}

	public Contact getContactsByContactsId(ContactsBean contactsBean)
			throws AppException {
		// TODO Auto-generated method stub
		return null;
	}
}
