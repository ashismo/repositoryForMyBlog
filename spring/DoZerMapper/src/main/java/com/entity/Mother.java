package com.entity;

import java.util.List;

public class Mother {
	private int id;
	private int spouseId;
	private String name;
	private int age;
	private List<Child> child;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSpouseId() {
		return spouseId;
	}
	public void setSpouseId(int spouseId) {
		this.spouseId = spouseId;
	}
	public List<Child> getChild() {
		return child;
	}
	public void setChild(List<Child> child) {
		this.child = child;
	}
	@Override
	public String toString() {
		return "Mother [id=" + id + ", spouseId=" + spouseId + ", name=" + name
				+ ", age=" + age + ", child=" + child + "]";
	}
}
