package com.ashish.learning.v4.dfault.autowire;

import org.springframework.stereotype.Component;

@Component
public class IndependentClass {
	private String property;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
