package com.ashish.java8.streams;

import java.util.ArrayList;
import java.util.List;

public class Average {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		double avg = list.stream().mapToInt(x->x).average().getAsDouble();
		
		System.out.println("Average value is: " + avg);
	}

}
