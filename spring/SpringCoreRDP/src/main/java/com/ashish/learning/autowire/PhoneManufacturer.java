package com.ashish.learning.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PhoneManufacturer {
	@Autowired
	// If qualifier is not added then by default phone bean (i.e. com.ashish.learning.autowire.Nokia) gets injected 
	// Because, by default, autowiring is by name
	@Qualifier("phone1")
	private Phone phone;

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}
}
