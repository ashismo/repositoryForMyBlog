package com.entity;

public class Parent {
	private Father father;
	private Mother mother;
	public Father getFather() {
		return father;
	}
	public void setFather(Father father) {
		this.father = father;
	}
	public Mother getMother() {
		return mother;
	}
	public void setMother(Mother mother) {
		this.mother = mother;
	}
	@Override
	public String toString() {
		return "Parent [father=" + father + ", mother=" + mother + "]";
	}
	
	
}
