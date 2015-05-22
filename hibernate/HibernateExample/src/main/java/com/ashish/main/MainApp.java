package com.ashish.main;


import java.util.List;

import org.hibernate.Session;

import com.ashish.entity.EmployeeEntity;
import com.ashish.util.HibernateUtil;
 
 
public class MainApp
{
   public static void main(String[] args)
   {
      Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      insertRecord(session);
      selectRecord(session);
      HibernateUtil.shutdown();
   }

	private static void insertRecord(Session session) {
		// Add new Employee object
	      EmployeeEntity emp = new EmployeeEntity();
	      emp.setEmployeeId(1);
	      emp.setEmail("ashismo@gmail.com");
	      emp.setFirstName("Ashish");
	      emp.setLastName("Mondal");
	      session.save(emp);
	      session.getTransaction().commit();
	}
	
	private static void selectRecord(Session session) {
		// Select Employee
	     List<EmployeeEntity> empList = session.createQuery("from EmployeeEntity").list();
	     for(EmployeeEntity emp : empList) {
	    	 System.out.println("First Name: " + emp.getFirstName());
	    	 System.out.println("Last Name: " + emp.getLastName());
	    	 System.out.println("Email : " + emp.getEmail());
	     }
	     
	}
}