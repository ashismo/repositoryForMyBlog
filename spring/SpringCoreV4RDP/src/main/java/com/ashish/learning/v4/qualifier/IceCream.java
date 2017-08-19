package com.ashish.learning.v4.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary  // By annotating this class as primary ensures that this class would be injected in case of ambiguity
@Qualifier("iceCream")
public class IceCream implements Dessert {

	public void dessertName() {
		System.out.println("Ice Cream");
	}

}
