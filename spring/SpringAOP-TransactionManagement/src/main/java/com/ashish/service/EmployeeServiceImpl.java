package com.ashish.service;

import org.springframework.transaction.annotation.Transactional;

import com.ashish.dao.EmployeeDAO;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO employeeDao = null;
	/**
	 * Declarative Transaction Management using Spring AOP is applied 
	 * on this method by marking the method with @Transactional 
	 */
//	@Transactional(readOnly = false)
	@Override
	public void insertEmpRecords() {
		employeeDao.insertEmpRecords();
		
	}

	@Override
	public void listEmployees() {
		employeeDao.listEmployees();
	}

	@Override
	public void releaseResources() {
		employeeDao.releaseResources();
		
	}
	
	/**
	 * @return the employeeDao
	 */
	public EmployeeDAO getEmployeeDao() {
		return employeeDao;
	}

	/**
	 * @param employeeDao the employeeDao to set
	 */
	public void setEmployeeDao(EmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}

}
