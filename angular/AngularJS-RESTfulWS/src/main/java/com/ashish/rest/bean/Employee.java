package com.ashish.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Employee {
	private String name;
	private int empId;
	private String address1;
	private String address2;
	private String address3;
	private String pin;
	private double salary;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
}
