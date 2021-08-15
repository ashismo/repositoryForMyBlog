package com.ashish.java8.streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PrimitiveToList {
	public static void main(String[] args) {
		int[] arr = {2,8,5, 9,10,18,12 ,20};
		List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
		
		System.out.println("Before sorting: " + list);
		
		Collections.sort(list, Collections.reverseOrder());
		
		System.out.println("Reversed Sorted order: " + list);
	}
}
