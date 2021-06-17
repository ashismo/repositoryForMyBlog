package com.ashish.poc.autojunit.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.poc.autojunit.entities.Employees;
import com.ashish.poc.autojunit.services.EmployeeServices;

@RestController
public class EndpointsController {
	
	@Autowired
	private EmployeeServices employeeServices;
	
	@GetMapping(path = "/health")
	public String health() {
		return "ok";
	}
	
	@GetMapping(path = "/all")
	public List<Employees> getAllEmployees() {
		return employeeServices.getAllEmployees();
	}
	
	@GetMapping(path = "/add")
	public List<Employees> addEmployees() {
		return employeeServices.addEmployees();
	}
	
	@GetMapping(path = "/find/{id}")
	public Employees findEmployee(@PathVariable("id") Integer id) {
		return employeeServices.findEmployee(id);
	}
	
	@GetMapping(path = "/delete/{id}")
	public List<Employees> deleteEmployee(@PathVariable("id") Integer id) {
		return employeeServices.deleteEmployee(id);
	}
}
