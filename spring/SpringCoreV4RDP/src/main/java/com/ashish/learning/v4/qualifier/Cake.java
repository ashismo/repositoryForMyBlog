package com.ashish.learning.v4.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cake")
public class Cake implements Dessert {

	public void dessertName() {
		System.out.println("Cake");
	}
	
}
