package com.ashish.learning.v4.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DessertService {
	
	private Dessert dessert;
	
	@Autowired // By writing this it would try to inject 3 implementations called IceCream, Cake, Cookies. So ambiguity starts here.
	@Qualifier("iceCream")
	public void setDessert(Dessert dessert) {
		this.dessert = dessert;
	}
	
	public void getDessert() {
		dessert.dessertName();
	}
}
