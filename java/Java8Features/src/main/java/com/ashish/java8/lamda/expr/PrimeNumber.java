package com.ashish.java8.lamda.expr;

import java.util.stream.IntStream;

public class PrimeNumber {
	public static void main(String[] args) {
		
		System.out.println("METHOD 1");
		IntStream
			.range(1, 20)
			.forEach(x->{
				if(x%2 == 1) {
					System.out.println(x);
				}
			});
		
		System.out.print("METHOD 2: ");
		IntStream
			.range(1, 20)
			.filter(x-> x%2 != 0)
			.forEach((x)->System.out.print(x + " "));
			
	}
}
