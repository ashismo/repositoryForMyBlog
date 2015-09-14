package com.ashish.medicine.admin.contacts;

import com.ashish.medicine.entity.Contact;
import com.ashish.medicine.exception.AppException;

public interface ContactsDao {
	public void addOrUpdateContacts(ContactsBean contactsBean) throws AppException;
	public void getAllCompanies(ContactsBean contactsBean) throws AppException;
	public void searchContacts(ContactsBean contactsBean) throws AppException;
	public void viewContacts(ContactsBean contactsBean) throws AppException;
	public void deleteContacts(ContactsBean contactsBean) throws AppException;
	public Contact getContactsByContactsId(ContactsBean contactsBean) throws AppException;
}
