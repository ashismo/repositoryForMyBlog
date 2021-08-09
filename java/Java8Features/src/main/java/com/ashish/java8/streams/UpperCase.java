package com.ashish.java8.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UpperCase {
	public static void main(String[] args) {
		List<String> strs = new ArrayList<>(Arrays.asList("Ashish", "Mondal"));
		strs.stream()
				.map(x->x.toUpperCase())
				.collect(Collectors.toList())
				.forEach(x-> System.out.println(x));
		
	}
}
