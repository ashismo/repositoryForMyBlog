package com.ashish.learning.v4.dfault.autodetect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class DependentClass {
	
	private IndependentClass independentClass;
	
	public DependentClass() {
		System.out.println("Default constructor is invoked");
	}
	
	public DependentClass(IndependentClass independentClass) {
		System.out.println("Parameterized constructor is invoked");
		this.independentClass = independentClass;
		independentClass.setProperty("Setting value in Constructor");
	}
	
	public void printIndependentClass() {
		System.out.println("Value of the property in independent class is : " + independentClass.getProperty());
	}

	public IndependentClass getIndependentClass() {
		return independentClass;
	}

	public void setIndependentClass(IndependentClass independentClass) {
		this.independentClass = independentClass;
	}
}
