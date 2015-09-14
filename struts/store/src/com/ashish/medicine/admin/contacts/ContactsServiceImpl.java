package com.ashish.medicine.admin.contacts;

import com.ashish.medicine.exception.AppException;

public class ContactsServiceImpl implements ContactsService {

	public void addOrUpdateContacts(ContactsBean contactsBean) throws AppException {
		new ContactsServiceHelperImpl().addOrUpdateContacts(contactsBean);
	}

	public void searchContacts(ContactsBean contactsBean) throws AppException {
		new ContactsServiceHelperImpl().searchContacts(contactsBean);
	}

	public void viewContacts(ContactsBean contactsBean) throws AppException {
		new ContactsServiceHelperImpl().viewContacts(contactsBean);
	}

	public void deleteContacts(ContactsBean contactsBean) throws AppException {
		new ContactsServiceHelperImpl().deleteContacts(contactsBean);
	}

	public void getAllCompanies(ContactsBean contactsBean) throws AppException {
		new ContactsServiceHelperImpl().getAllCompanies(contactsBean);
	}

}
