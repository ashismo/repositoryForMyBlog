package com.ashish.medicine.admin.order;

import com.ashish.medicine.exception.AppException;

public interface OrderServiceHelper {
	public void addOrUpdateOrder(OrderBean orderBean) throws AppException;
	public void deleteOrder(OrderBean orderBean) throws AppException;
	public void addOrUpdateNotes(OrderBean orderBean) throws AppException;
	public void deleteNotes(OrderBean orderBean) throws AppException;
	public void searchOrderDetails(OrderBean orderBean) throws AppException;
}
