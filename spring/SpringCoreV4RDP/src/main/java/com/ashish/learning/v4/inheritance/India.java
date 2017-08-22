package com.ashish.learning.v4.inheritance;

import org.springframework.stereotype.Component;

@Component
public class India extends Country {
	private String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "India [language=" + language + ", getCountryName()="
				+ getCountryName() + "]";
	}
	
}
