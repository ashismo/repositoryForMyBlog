package com.ashish.java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LinearSearch {
	public static void main(String[] args) {
//		Write a method that returns the index of the first occurrence of given integer in a list.
//		Assume that the index of the first element in the list is zero.
//		If the number does not exist return -1.
		
		Integer[] arr = {2,3,4,1,4,6,6,7};
		int val = 6;
		List<Integer> list = Arrays.stream(arr).collect(Collectors.toList());
		int pos = IntStream.range(0,list.size())
						.filter(x-> list.get(x) == val)
						.findFirst()
						.orElse(-1);
		
		System.out.println(pos);
	}
}
