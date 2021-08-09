package com.ashish.java8.streams;

import java.util.stream.IntStream;

public class ReverseAString {

	public static void main(String[] args) {
		String str = "Ashish";
		IntStream.range(0, str.length())
				.mapToObj(x -> str.charAt(str.length() - 1 - x))
				.forEach(System.out::print);
	}

}
