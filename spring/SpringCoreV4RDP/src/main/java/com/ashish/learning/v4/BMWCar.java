package com.ashish.learning.v4;

import org.springframework.stereotype.Component;

@Component
public class BMWCar implements FourWheelers {

	@Override
	public void prepareFourwheelers() {
		System.out.println("Dear Customer!!! your dream car, BMW is ready for delivery");

	}

}
