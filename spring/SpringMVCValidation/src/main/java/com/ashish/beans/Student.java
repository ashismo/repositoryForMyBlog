package com.ashish.beans;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Student {
	@NotNull(message="Please enter your age")
	@Min(value=1, message="Age should be between 1-30")
	@Max(value=30, message="Age should be between 1-30")
	private int age;
	
	@NotEmpty(message="Please enter your name")
	@Size(min=1,max=40,message="Name should not exceed 40 character long")
	private String name;

	@NotNull(message="Please enter your id")
	@Min(value=100, message="Id should be 3 digits long")
	@Max(value=999, message="Id should be 3 digits long")
	private int id;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}