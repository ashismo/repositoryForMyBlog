package com.ashish.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.ashish.entity.EmployeeAllocationEntity;
import com.ashish.entity.EmployeeEntity;

public class EmployeeDAOImpl extends HibernateDaoSupport implements EmployeeDAO {
	
	@Override
	public void insertEmpRecords() {
			
		// Add new Employee object
	      EmployeeEntity emp = new EmployeeEntity(1, "Ashish", "Mondal", "ashismo@gmail.com");
	      
	      // Ashish Mondal has two allocations called Project1 and Project2
	      EmployeeAllocationEntity empAllocation = new EmployeeAllocationEntity(1, "Project1", emp);
	      emp.setEmpAllocations(empAllocation);
	      empAllocation = new EmployeeAllocationEntity(2, "Project2", emp);
	      emp.setEmpAllocations(empAllocation);
	      
	      getHibernateTemplate().save(emp);
	      
	   // Add another Employee object
	      emp = new EmployeeEntity(2, "Ujan", "Mondal", "ujanmo@gmail.com");
	      
	      // Ujan Mondal has two allocations called Project2 and Project3. 
	      // Also note that: In project 2, Ashish and Ujan both are allocated
	      emp.setEmpAllocations(empAllocation);
	      
	      empAllocation = new EmployeeAllocationEntity(3, "Project3", emp);
	      emp.setEmpAllocations(empAllocation);
	      
	      getHibernateTemplate().save(emp);
//	      throw new RuntimeException();
	}

	@Override
	public void listEmployees() {
		// Select Employee
			Session session = getSessionFactory().openSession();
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
	     session.close();
	}

	@Override
	public void releaseResources() {
		getSessionFactory().close();
	}
	
}
