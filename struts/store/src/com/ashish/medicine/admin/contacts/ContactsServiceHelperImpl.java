package com.ashish.medicine.admin.contacts;

import com.ashish.medicine.exception.AppException;

public class ContactsServiceHelperImpl implements ContactsServiceHelper {

	public void addOrUpdateContacts(ContactsBean contactsBean) throws AppException {
		new ContactsDaoImpl().addOrUpdateContacts(contactsBean);
	}

	public void searchContacts(ContactsBean contactsBean) throws AppException {
		new ContactsDaoImpl().searchContacts(contactsBean);
	}

	public void viewContacts(ContactsBean contactsBean) throws AppException {
		new ContactsDaoImpl().viewContacts(contactsBean);
	}

	public void deleteContacts(ContactsBean contactsBean) throws AppException {
		new ContactsDaoImpl().deleteContacts(contactsBean);		
	}

	public void getAllCompanies(ContactsBean contactsBean) throws AppException {
		new ContactsDaoImpl().getAllCompanies(contactsBean);		
	}
}
