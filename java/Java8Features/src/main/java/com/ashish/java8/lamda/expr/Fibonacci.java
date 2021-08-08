package com.ashish.java8.lamda.expr;

import java.util.stream.Stream;

public class Fibonacci {

	public static void main(String[] args) {
		Stream
			.iterate(new Integer[] {1,1}, (x)->new Integer[] {x[0], x[0] + x[1]})
			.limit(10)
			.map(x->x[1])
			.forEach(System.out::println);
	}
	
}
