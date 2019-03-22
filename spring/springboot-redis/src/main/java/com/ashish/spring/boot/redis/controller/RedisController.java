package com.ashish.spring.boot.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.spring.boot.redis.dao.Employee;
import com.ashish.spring.boot.redis.repository.EmployeeRepository;

@RestController
@RequestMapping("/rest")
public class RedisController {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@RequestMapping(path="/set",method=RequestMethod.GET)
	public String setValue() {
		Employee emp = new Employee();
		emp.setId("111111");
		emp.setName("Ashish");
		
		empRepo.save(emp);
		return "value set for key: " + emp.getId() + " value: " + emp.getName();
	}
	
	@RequestMapping(path="/get",method=RequestMethod.GET)
	public String getValue() {
		Employee retrievedEmp = 
				  empRepo.findById("111111").get();
		
		return retrievedEmp.getName();
	}
	
	@RequestMapping(path="/update",method=RequestMethod.GET)
	public String updateValue() {
		Employee retrievedEmp = 
				  empRepo.findById("111111").get();
		
		retrievedEmp.setName("Mondal");
		empRepo.save(retrievedEmp);
		return "value UPDATED for key: " + retrievedEmp.getId() + " value: " + retrievedEmp.getName();
	}
}
