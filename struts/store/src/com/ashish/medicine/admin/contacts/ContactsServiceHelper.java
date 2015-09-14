package com.ashish.medicine.admin.contacts;

import com.ashish.medicine.exception.AppException;

public interface ContactsServiceHelper {
	public void addOrUpdateContacts(ContactsBean contactsBean) throws AppException;
	public void getAllCompanies(ContactsBean contactsBean) throws AppException;
	public void searchContacts(ContactsBean contactsBean) throws AppException;
	public void viewContacts(ContactsBean contactsBean) throws AppException;
	public void deleteContacts(ContactsBean contactsBean) throws AppException;
}
