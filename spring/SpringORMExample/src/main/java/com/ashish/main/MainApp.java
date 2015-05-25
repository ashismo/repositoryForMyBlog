package com.ashish.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ashish.dao.EmployeeDAO;
import com.ashish.dao.EmployeeDAOImpl;
 
 
public class MainApp
{
   public static void main(String[] args)
   {
	   ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-config.xml");
		
	   EmployeeDAO employeeDao = (EmployeeDAOImpl) appContext.getBean("employeeDao");
	   employeeDao.insertEmpRecords();
	   employeeDao.listEmployees();
	   employeeDao.releaseResources();
   }

	
}