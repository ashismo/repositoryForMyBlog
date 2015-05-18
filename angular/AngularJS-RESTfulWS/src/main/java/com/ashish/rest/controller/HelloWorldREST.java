package com.ashish.rest.controller;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ashish.rest.bean.Employee;

@Path("/hello")
public class HelloWorldREST {

	@GET
	@Path("/getEmployee/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployee( @PathParam("empId") int empId,
			@DefaultValue("No Employee Id passed") @QueryParam("value") String value) {
		System.out.println("getEmployee method is called");
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setName("Ashish Mondal");

		return emp;

	}
	
	@POST
	@Path("/getSalary")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getSalary( @PathParam("empId") int empId,
			@DefaultValue("No Employee Id passed") @QueryParam("value") String value) {
		System.out.println("getSalary method is called");
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setName("Ashish Mondal");
		emp.setSalary(1000);
		
		return emp;

	}

}
