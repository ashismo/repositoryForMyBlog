package com.ashish.learning.v4.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionInjectionServices {
	
	private Map<String, String> countryCurrencyMap;
	private List<String> countries;
	
	@Autowired
	public void countryCurrencyMap(Map<String, String> countryCurrencyMap) {
		this.countryCurrencyMap = countryCurrencyMap;
	}
	
	@Autowired
	public void countries(List<String> countries) {
		this.countries = countries;
	}

	public Map<String, String> getCountryCurrencyMap() {
		return countryCurrencyMap;
	}

	public List<String> getCountries() {
		return countries;
	}
}
