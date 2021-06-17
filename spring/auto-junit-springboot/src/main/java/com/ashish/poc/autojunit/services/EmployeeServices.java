package com.ashish.poc.autojunit.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.poc.autojunit.entities.Employees;
import com.ashish.poc.autojunit.repositories.EmployeeRepository;

@Service
public class EmployeeServices {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employees> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public List<Employees> addEmployees() {
		List<Employees> employees = new ArrayList<>();
		Employees employee = new Employees();
		employee.setFirstName("Ashish");
		employee.setLastName("Mondal");
		employee.setSkills("Java");
		employees.add(employee);
		
		employeeRepository.saveAll(employees);
		return employeeRepository.findAll();
	}

	public Employees findEmployee(int employeeId) {
		return employeeRepository.findById(employeeId);
	}

	public List<Employees> deleteEmployee(int employeeId) {
		employeeRepository.deleteById(employeeId);
		return employeeRepository.findAll();
	}
	
}
