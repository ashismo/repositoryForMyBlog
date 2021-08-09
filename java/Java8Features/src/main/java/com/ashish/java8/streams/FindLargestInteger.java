package com.ashish.java8.streams;

import java.util.Arrays;

public class FindLargestInteger {

	public static void main(String[] args) {
		Integer[] i = {1,2,6,9,10,5,12,11};
		int max = Arrays.asList(i)
					.stream()
					.mapToInt(x -> x)
					.max().orElse(-1);
		System.out.println("Maximum value in the list: " + max);
	}

}
