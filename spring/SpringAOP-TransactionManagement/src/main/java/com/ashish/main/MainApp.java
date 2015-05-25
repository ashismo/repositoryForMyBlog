package com.ashish.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ashish.service.EmployeeService;
import com.ashish.service.EmployeeServiceImpl;
 
 
public class MainApp
{
   public static void main(String[] args)
   {
	   ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-config.xml");
		
	   EmployeeService employeeService = (EmployeeService) appContext.getBean("employeeService");
	   try {
		   employeeService.insertEmpRecords();
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   employeeService.listEmployees();
	   employeeService.releaseResources();
   }

	
}