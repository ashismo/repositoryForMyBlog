package com.ashish.main;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.ashish.entity.EmployeeAllocationEntity;
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
	      EmployeeEntity emp = new EmployeeEntity(1, "Ashish", "Mondal", "ashismo@gmail.com");
	      
	      // Ashish Mondal has two allocations called Project1 and Project2
	      EmployeeAllocationEntity empAllocation = new EmployeeAllocationEntity(1, "Project1", emp);
	      emp.setEmpAllocations(empAllocation);
	      empAllocation = new EmployeeAllocationEntity(2, "Project2", emp);
	      emp.setEmpAllocations(empAllocation);
	      
	      session.save(emp);
	      
	   // Add another Employee object
	      emp = new EmployeeEntity(2, "Ujan", "Mondal", "ujanmo@gmail.com");
	      
	      // Ujan Mondal has two allocations called Project2 and Project3. 
	      // Also note that: In project 2, Ashish and Ujan both are allocated
	      emp.setEmpAllocations(empAllocation);
	      
	      empAllocation = new EmployeeAllocationEntity(3, "Project3", emp);
	      emp.setEmpAllocations(empAllocation);
	      
	      session.save(emp);
	      
	      // After saving all employees, commit the transaction
	      session.getTransaction().commit();
	}
	
	private static void selectRecord(Session session) {
		// Select Employee
	     List<EmployeeEntity> empList = session.createQuery("from EmployeeEntity").list();
	     for(EmployeeEntity emp : empList) {
	    	 System.out.println("==================Employee Details======================");
	    	 System.out.println("Employee Name: " + emp.getFirstName() + " " + emp.getLastName());
	    	 System.out.println("Email : " + emp.getEmail());
	    	 
	    	 System.out.println("+++++++++++++Employee Allocation Details+++++++++++++");
	    	 Set<EmployeeAllocationEntity> empAllocationSet = emp.getEmpAllocations();
	    	 Iterator<EmployeeAllocationEntity> it = empAllocationSet.iterator();
	    	 while(it.hasNext()) {
	    		 System.out.println("Allocation: " + it.next().getAllocationName());
	    	 }
	     }
	     
	}
}