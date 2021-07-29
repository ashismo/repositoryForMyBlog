package com.ashish.java8.streams;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreams1 {

	public static void main(String[] args) {
		// A Stream pipeline consists of a source, followed by zero or more intermediate operations and a terminal operation.
		// _________
		// | SOURCE | --> FILTER --> SORT --> MAP --> COLLECT
		// |________|
		
		// Source: It can be collections, List, Sets, ints, doubles etc
		// Stream operations:
		//		- Intermediate Operations: filter, map or sort are the intermediate operations returns stream so we can chain multiple intermediate operation.
		//		- Terminal Operations: forEach, collect or reduce (count, min, max etc) are either void or returns a non stream result.
		
		
		// Example 1: Integer stream: Print range
		System.out.println("Example 1: Integer stream: Print range");
		IntStream
			.range(1, 5)
			.forEach(System.out::println);
		
		// Example 2: Integer stream with skip
		System.out.println("\nExample 2: Integer stream with skip");
		IntStream
			.range(1, 10)
			.skip(5)
			.forEach(System.out::println);
		
		// Example 3: Integer stream with sum
		System.out.println("\nExample 3: Integer stream with sum");
		System.out.println(IntStream
			.range(1, 5)
			.sum());
		
		// Example 4: Stream.of, sorted and findFirst
		System.out.println("\nExample 4: Stream.of, sorted and findFirst");
		Stream
			.of("A","C","B")
			.sorted()
			.findFirst()
			.ifPresent(System.out::println);
	
		// Example 5: Stream from an array, sort filter and print
		System.out.println("\nExample 5: Stream from an array, sort filter and print");
		String[] names = {"Ashish","Azim","Ujan","Akash", "Dona"};
		Arrays
			.stream(names)
			.filter(x->x.startsWith("A"))
			.sorted()
			.forEach(System.out::println);
		
		// Example 6: Average of square of an int array
		System.out.println("\nExample 6: Average of square of an int array");
		Arrays
			.stream(new int[] {2,4,6,8})
			.map((int x)->  x * x)
			.average()
			.ifPresent(System.out::println);
	}

}
