package com.ashish.learning.v4.dfault.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DependentClass {
	
	@Autowired
	private IndependentClass independentClass;
	
	public DependentClass() {
		System.out.println("Default constructor is invoked");
	}
	
	@Autowired // Both property and constructor are autowired so constructor will get priority
	public DependentClass(IndependentClass independentClass) {
		System.out.println("Parameterized constructor is invoked");
		this.independentClass = independentClass;
		independentClass.setProperty("Setting value in Constructor");
	}
	
	public void printIndependentClass() {
		System.out.println("Value of the property in independent class is : " + independentClass.getProperty());
	}
}
