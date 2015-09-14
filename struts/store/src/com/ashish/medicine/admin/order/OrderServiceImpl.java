package com.ashish.medicine.admin.order;

import com.ashish.medicine.exception.AppException;

public class OrderServiceImpl implements OrderService {

	public void addOrUpdateOrder(OrderBean orderBean) throws AppException {
		new OrderServiceHelperImpl().addOrUpdateOrder(orderBean);
		
	}
	public void deleteOrder(OrderBean orderBean) throws AppException {
		new OrderServiceHelperImpl().deleteOrder(orderBean);
		
	}
	
	public void searchOrderDetails(OrderBean orderBean) throws AppException {
		new OrderServiceHelperImpl().searchOrderDetails(orderBean);
	}

	public void addOrUpdateNotes(OrderBean orderBean) throws AppException {
		new OrderServiceHelperImpl().addOrUpdateNotes(orderBean);
	}
	
	public void deleteNotes(OrderBean orderBean) throws AppException {
		new OrderServiceHelperImpl().deleteNotes(orderBean);
	}

}
