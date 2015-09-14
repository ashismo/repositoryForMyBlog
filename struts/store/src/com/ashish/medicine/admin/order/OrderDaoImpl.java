package com.ashish.medicine.admin.order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.ashish.medicine.admin.base.MedicineBaseDaoImpl;
import com.ashish.medicine.admin.medicine.MedicineBean;
import com.ashish.medicine.admin.medicine.MedicineDaoImpl;
import com.ashish.medicine.entity.Medicine;
import com.ashish.medicine.entity.MedicineDetail;
import com.ashish.medicine.entity.Order;
import com.ashish.medicine.exception.AppException;
import com.ashish.medicine.hinernate.util.HibernateUtil;
import com.ashish.medicine.util.MedicineConstants;
import com.ashish.medicine.util.MedicineUtility;

public class OrderDaoImpl extends MedicineBaseDaoImpl implements OrderDao {
//	private Session s = HibernateUtil.getSessionFactory().openSession();
	MedicineUtility mUtil = new MedicineUtility();
	
	public void addOrUpdateOrder(OrderBean orderBean) throws AppException {
		Date orderDate = new Date();
		if(orderBean.getOrderDate() != null && !orderBean.getOrderDate().trim().equals("")) {
			orderDate = mUtil.getDateFromString(orderBean.getOrderDate());
		}
		
		Order order = null;
		String medicineDetails = orderBean.getMedicineId() + "~" + orderBean.getMedDose() + "~" + 
								orderBean.getMedWeight() + "~" + orderBean.getMedType();
		Query query = s.getNamedQuery("getOrderByOrderId");
		query.setInteger("orderId", orderBean.getOrderId());
//		query.setDate("orderDate", orderDate);
		List<Order> orderList = query.list();
		
		if(orderList != null && orderList.size() > 0) {
//			orderBean.setErrorMsg("Order is placed for the same medicine on " + orderBean.getOrderDate());
//			throw new AppException();
			order = orderList.get(0);
			order.setDbUpdTs(new Timestamp(new Date().getTime()));
			order.setDbAddUser(orderBean.getDbAddUser());
		} else {
			order = new Order();
			order.setDbAddTs(new Timestamp(new Date().getTime()));
			order.setDbUpdTs(new Timestamp(new Date().getTime()));
			order.setDbUpdUser(orderBean.getDbUpdUser());
		}
		MedicineBean medicineBean = new MedicineBean();
		medicineBean.setMedicineId(orderBean.getMedicineId());
		Medicine medicine = new MedicineDaoImpl().getMedicineByIdIfExists(medicineBean);
		if(medicine == null) {
			orderBean.setErrorMsg("Medicine Does not exists");
			throw new AppException();
		}
		Transaction tx = null;
		try {
			order.setOrderId(orderBean.getOrderId());
			order.setMedicine(medicine);
			order.setOrderDate(orderDate);
			order.setMedicineDetails(medicineDetails);
			order.setOrderDesc(orderBean.getOrderDesc());
			
			tx = s.beginTransaction();
			s.saveOrUpdate(order);
			orderBean.setOrderId(order.getOrderId());
			orderBean.setMsg("Order placed successfully");
			
		} catch (Exception e) {
			if(orderBean.getMsg() != null && "".equals(orderBean.getMsg().trim())) {
				orderBean.setMsg("Order Place is not successful");
			}
			e.printStackTrace();
			throw new AppException();
		} finally {
			try {
				if(tx != null)tx.commit();
			} catch (Exception e) {
				if(tx != null)tx.rollback();
				e.printStackTrace();
				orderBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
	}

	public void deleteOrder(OrderBean orderBean) throws AppException {
		
		Date orderDate = new Date();
		if(orderBean.getOrderDate() != null && !orderBean.getOrderDate().trim().equals("")) {
			orderDate = mUtil.getDateFromString(orderBean.getOrderDate());
			
		}
		Query query = s.getNamedQuery("getOrderByOrderId");
		query.setInteger("orderId", orderBean.getOrderId());
		query.setMaxResults(1);
		List<Order> orderList = query.list();
		
		if(orderList != null && orderList.size() == 1) {
			Transaction tx = null;
			try {
				Order order = orderList.get(0);
				tx = s.beginTransaction();
				s.delete(order);
//				orderBean.setOrderDate(mUtil.getDateStrFromDate(order.getOrderDate()));
//				orderBean.setOrderDesc(order.getOrderDesc());
				BeanUtils.copyProperties(orderBean, order);
				orderBean.setMsg("Order Deleted Successfully");
			} catch (Exception e) {
				if(orderBean.getMsg() != null && "".equals(orderBean.getMsg().trim())) {
					orderBean.setMsg("Operation failed");
				}
				e.printStackTrace();
				throw new AppException();
			} finally {
				try {
					if(tx != null)tx.commit();
				} catch (Exception e) {
					if(tx != null)tx.rollback();
					e.printStackTrace();
					orderBean.setMsg("Operation failed. Unable to commit changes into database.");
				}
				if(s != null) s.close();
			}
		}
	}

	public void searchOrderDetails(OrderBean orderBean) throws AppException {
		try {
			int lowStock = 0;
			int recentNotes = 0;
			int executedOrder = 0;
			String medType = "%";
			// Search Companies by page
			int startIndex = (orderBean.getPage() - 1) * orderBean.getRows();
			int totalRecords = orderBean.getRows();
			Query query = null;
			SQLQuery namedNativeQuery = null;
			if(orderBean.getRecentNotes() != null && !"".equals(orderBean.getRecentNotes().trim())) {
				recentNotes = Integer.parseInt(orderBean.getRecentNotes());
			}
			if(orderBean.getLowStock() != null && !"".equals(orderBean.getLowStock().trim())) {
				lowStock = Integer.parseInt(orderBean.getLowStock());
			}
			if(orderBean.getExecutedOrder() != null && !"".equals(orderBean.getExecutedOrder().trim())) {
				executedOrder = Integer.parseInt(orderBean.getExecutedOrder());
			}
			if(orderBean.getMedType() != null && !"".equals(orderBean.getMedType().trim()) && 
					!"All".equalsIgnoreCase(orderBean.getMedType().trim()) ) {
				medType = orderBean.getMedType();
			}
			Date startDate = null;
			Date endDate = null;
			if(orderBean.getStartDate() != null && !orderBean.getStartDate().trim().equals("")) {
				startDate = mUtil.getDateFromString(orderBean.getStartDate());
			} else {
				startDate = mUtil.getDateSubtractedByDays(30);
			}
			if(orderBean.getEndDate() != null && !orderBean.getEndDate().trim().equals("")) {
				endDate = mUtil.getDateFromString(orderBean.getEndDate());
			} else {
				endDate = new Date();
			}

			String attrValue = "";
			if(lowStock != 0) {
				// Search lower stocks
				query = s.getNamedQuery("searchLowerStocks");
				String queryStr = query.getQueryString();
				namedNativeQuery = s.createSQLQuery(queryStr);
				namedNativeQuery.setInteger("availableStock", new Integer(lowStock));
				namedNativeQuery.setString("medType", medType);
				attrValue = "medicineId-integer,medicineName-String,companyName-String,batchName-String," +
						"availableStock-Integer,expDateActual-Date,medDose-String," +
						"medWeight-String,medType-String";
			} else if(recentNotes != 0) {
				// Search Recent Notes (Default - last 1 month)
				query = s.getNamedQuery("searchRecentNotes");
				String queryStr = query.getQueryString();
				namedNativeQuery = s.createSQLQuery(queryStr);
				namedNativeQuery.setDate("startDate", startDate);
				namedNativeQuery.setDate("endDate", endDate);
				
				attrValue = "orderDateActual-Date,orderId-integer," +
							"orderExecutionDateActual-Date,orderDesc-String,orderStatus-String";
			} else if(executedOrder != 0) {
				// Search Executed Orders (Default - All orders)
				query = s.getNamedQuery("searchExecutedOrders");
				String queryStr = query.getQueryString();
				namedNativeQuery = s.createSQLQuery(queryStr);
				namedNativeQuery.setDate("startDate", startDate);
				namedNativeQuery.setDate("endDate", endDate);
				
				attrValue = "medicineId-integer,medicineName-String,companyName-String,batchName-String," +
						"mfgDateActual-Date,expDateActual-Date,medDose-String," +
						"medWeight-String,medType-String," +
						"orderDateActual-Date,orderId-integer," +
						"orderExecutionDateActual-Date,orderStatus-String,orderDesc-String";
			}  
			new MedicineUtility().setScalarInQuery(namedNativeQuery, attrValue);
			List<OrderBean> orderCountList = namedNativeQuery.setResultTransformer(Transformers.aliasToBean(OrderBean.class)).list();
			
			namedNativeQuery.setFirstResult(startIndex);
			namedNativeQuery.setMaxResults(totalRecords);
			
			List<OrderBean> orderList = namedNativeQuery.setResultTransformer(Transformers.aliasToBean(OrderBean.class)).list();
			
			
			if(lowStock != 0) {
				if(orderList != null && orderList.size() > 0) {
					List<Integer> medicineIds = new ArrayList<Integer>();
					for(OrderBean ob : orderList) {
						Query query1 = s.getNamedQuery("getOrderByMedicineId");
						query1.setInteger("medicineId", ob.getMedicineId());
						List<Order> orders = query1.list();
						
						if(orders != null && orders.size() > 0) {
							Order order = orders.get(0);
							ob.setOrderId(order.getOrderId());
							ob.setOrderDate(mUtil.getDateStrFromDate(order.getOrderDate()));
							ob.setOrderDesc(order.getOrderDesc());
						} else {
							ob.setOrderDate(null);
							ob.setOrderDesc(null);
							ob.setOrderId(null);
						}
					}
				}
			}  else if(recentNotes != 0) {
				if(orderList != null && orderList.size() > 0) {
					List<OrderBean> orderListProcessed = new ArrayList<OrderBean>();
					for(OrderBean ob : orderList) {
						// Search All
						if("0".equals(orderBean.getNotesCriteria()) || "".equals(orderBean.getNotesCriteria().trim())) {
							ob.setOrderDate(mUtil.getDateStrFromDate(ob.getOrderDateActual()));
							ob.setOrderExecutionDate(mUtil.getDateStrFromDate(ob.getOrderExecutionDateActual()));
							orderListProcessed.add(ob);
						} 
						//Orders to be executed
						else if("1".equals(orderBean.getNotesCriteria())) {
							if(ob.getOrderStatus() == null) {
								ob.setOrderDate(mUtil.getDateStrFromDate(ob.getOrderDateActual()));
								ob.setOrderExecutionDate(mUtil.getDateStrFromDate(ob.getOrderExecutionDateActual()));
								orderListProcessed.add(ob);
							}
						}
						//Orders already executed
						else if("2".equals(orderBean.getNotesCriteria())) {
							if(ob.getOrderStatus() != null) {
								ob.setOrderDate(mUtil.getDateStrFromDate(ob.getOrderDateActual()));
								ob.setOrderExecutionDate(mUtil.getDateStrFromDate(ob.getOrderExecutionDateActual()));
								orderListProcessed.add(ob);
							}
						}
					}
					orderList = orderListProcessed;
				}
			}else if(executedOrder != 0) {
				if(orderList != null && orderList.size() > 0) {
					for(OrderBean ob : orderList) {
						ob.setOrderDate(mUtil.getDateStrFromDate(ob.getOrderDateActual()));
						ob.setOrderExecutionDate(mUtil.getDateStrFromDate(ob.getOrderExecutionDateActual()));
						ob.setMfgDate(mUtil.getDateStrFromDate(ob.getMfgDateActual()));
						ob.setExpDate(mUtil.getDateStrFromDate(ob.getExpDateActual()));
					}
				}
			}
			
			orderBean.setSearchOrderList(orderList);
			
			// count medicines
			orderBean.setTotal(orderCountList.size());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException();
		} finally {
			s.close();
		}
	}

	public void addOrUpdateNotes(OrderBean orderBean) throws AppException {
		Order order = null;
		Query query = s.getNamedQuery("getOrderByOrderId");
		query.setInteger("orderId", orderBean.getOrderId());
		List<Order> orderList = query.list();
		
		if(orderList != null && orderList.size() > 0) {
			order = orderList.get(0);
			order.setDbUpdTs(new Timestamp(new Date().getTime()));
			order.setDbUpdUser(orderBean.getDbUpdUser());
		} else {
			order = new Order();
			order.setDbAddTs(new Timestamp(new Date().getTime()));
			order.setDbUpdTs(new Timestamp(new Date().getTime()));
			order.setDbAddUser(orderBean.getDbAddUser());
		}
		Transaction tx = null;
		try {
			order.setOrderId(orderBean.getOrderId());
			order.setOrderDesc(orderBean.getOrderDesc());
			if(orderBean.getOrderExecutionDate() != null && !orderBean.getOrderExecutionDate().equals("")) {
				order.setOrderExecutionDate(mUtil.getDateFromString(orderBean.getOrderExecutionDate()));
				order.setOrderStatus(MedicineConstants.ORDER_EXECUTION);
			} else {
				order.setOrderExecutionDate(null);
				order.setOrderStatus(null);
			}
			if(orderBean.getOrderDate() != null && !orderBean.getOrderDate().equals("")) {
				order.setOrderDate(mUtil.getDateFromString(orderBean.getOrderDate()));
			}
			
			tx = s.beginTransaction();
			s.saveOrUpdate(order);
			orderBean.setOrderId(order.getOrderId());
			orderBean.setMsg("Notes Added successfully");
			
		} catch (Exception e) {
			if(orderBean.getMsg() != null && "".equals(orderBean.getMsg().trim())) {
				orderBean.setMsg("Note is not saved");
			}
			e.printStackTrace();
			throw new AppException();
		} finally {
			try {
				if(tx != null)tx.commit();
			} catch (Exception e) {
				if(tx != null)tx.rollback();
				e.printStackTrace();
				orderBean.setMsg("Operation failed. Unable to commit changes into database.");
			}
			if(s != null) s.close();
		}
		
	}

	public void deleteNotes(OrderBean orderBean) throws AppException {
		Query query = s.getNamedQuery("getOrderByOrderId");
		query.setInteger("orderId", orderBean.getOrderId());
		query.setMaxResults(1);
		List<Order> orderList = query.list();
		
		if(orderList != null && orderList.size() == 1) {
			Transaction tx = null;
			try {
				Order order = orderList.get(0);
				tx = s.beginTransaction();
				s.delete(order);
				BeanUtils.copyProperties(orderBean, order);
				orderBean.setMsg("Note Deleted Successfully");
			} catch (Exception e) {
				if(orderBean.getMsg() != null && "".equals(orderBean.getMsg().trim())) {
					orderBean.setMsg("Operation failed");
				}
				e.printStackTrace();
				throw new AppException();
			} finally {
				try {
					if(tx != null)tx.commit();
				} catch (Exception e) {
					if(tx != null)tx.rollback();
					e.printStackTrace();
					orderBean.setMsg("Operation failed. Unable to commit changes into database.");
				}
				if(s != null) s.close();
			}
		}
	}

}
