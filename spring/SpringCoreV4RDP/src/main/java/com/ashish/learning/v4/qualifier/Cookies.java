package com.ashish.learning.v4.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cookies")
public class Cookies implements Dessert {

	public void dessertName() {
		System.out.println("Cookies");
	}

}
