package com.ashish.main;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.ashish.entity.AllocationEntity;
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
	      EmployeeEntity emp1 = new EmployeeEntity(1, "Ashish", "Mondal", "ashismo@gmail.com");
	      
	      AllocationEntity allocation1 = new AllocationEntity("Project1");
	      AllocationEntity allocation2 = new AllocationEntity("Project2");
	      AllocationEntity allocation3 = new AllocationEntity("Project3");
	      
	      // Ashish Mondal has two allocations called Project1 and Project2
	      emp1.setAllocations(allocation1);
	      emp1.setAllocations(allocation2);
	      
	      session.save(emp1);
	      
	      // Add another Employee object
	      EmployeeEntity emp2 = new EmployeeEntity(2, "Ujan", "Mondal", "ujanmo@gmail.com");
	      
	      // Ujan Mondal has two allocations called Project2 and Project3. 
	      // Also note that: In project 2, Ashish and Ujan both are allocated
	      emp2.setAllocations(allocation2);
	      emp2.setAllocations(allocation3);
	      session.save(emp2);
	      
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
	    	 Set<AllocationEntity> empAllocationSet = emp.getAllocations();
	    	 Iterator<AllocationEntity> it = empAllocationSet.iterator();
	    	 while(it.hasNext()) {
	    		 System.out.println("Allocation: " + it.next().getAllocationName());
	    	 }
	     }
	     
	}
}