package com.ashish.spring.boot.springbootftl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.spring.boot.springbootftl.dao.Employee;

@RestController
@RequestMapping("/rest")
public class RestfulWebService {
	
	@RequestMapping(path="/getEmployee",method=RequestMethod.GET,produces="application/json")
	public Employee getEmployee() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Ashish Mondal");
		return employee;
	}
}
