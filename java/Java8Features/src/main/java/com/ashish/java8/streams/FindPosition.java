package com.ashish.java8.streams;

import java.util.stream.IntStream;

public class FindPosition {
	public static void main(String[] args) {
		Integer[] list = {2,6,5,6,6,7,8};
		int index = findIndex(list, 6);
		
		System.out.println("Index of the searched element = " + index);
	}

	private static int findIndex(Integer[] list, int value) {
		return IntStream.range(0, list.length)
				.filter(i->value==list[i])
				.findFirst()
				.orElse(-1);
	}
}
