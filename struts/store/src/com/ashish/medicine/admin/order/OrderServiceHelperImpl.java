package com.ashish.medicine.admin.order;

import com.ashish.medicine.exception.AppException;

public class OrderServiceHelperImpl implements OrderServiceHelper {

	public void addOrUpdateOrder(OrderBean orderBean) throws AppException {
		new OrderDaoImpl().addOrUpdateOrder(orderBean);
	}

	public void deleteOrder(OrderBean orderBean) throws AppException {
		new OrderDaoImpl().deleteOrder(orderBean);
	}

	public void searchOrderDetails(OrderBean orderBean) throws AppException {
		new OrderDaoImpl().searchOrderDetails(orderBean);
	}

	public void addOrUpdateNotes(OrderBean orderBean) throws AppException {
		new OrderDaoImpl().addOrUpdateNotes(orderBean);
	}

	public void deleteNotes(OrderBean orderBean) throws AppException {
		new OrderDaoImpl().deleteNotes(orderBean);
	}

}
