package com.ashish.java8.coding.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OverlappingRange {
	public static void main(String[] args) {
		List<Integer> starting = Arrays.asList(1,3,6,9);
		List<Integer> ending = Arrays.asList(5,7,10,11);
		List<Character> style = Arrays.asList('b','b','i','u');
		
		
		List<Integer> newStarting = new ArrayList<>();
		List<Integer> newEnding = new ArrayList<>();
		List<Character> newStyle = new ArrayList<>();
		newStarting.addAll(starting);
		newEnding.addAll(ending);
		newStyle.addAll(style);
		
		for(int i = 0; i < starting.size() - 1; i++) {
			if(starting.get(i + 1) <= ending.get(i)) { // Overlap
				System.out.println(i);
				if(ending.get(i) < ending.get(i + 1)) { // subset
					newStarting.remove(1);
					newEnding.remove(1);
					newStyle.remove(0);
				} else {
					newStarting.remove(1);
					newEnding.remove(1);
					newStyle.remove(0);
				}
			}
		}
		
		System.out.println(newStarting + " " + newEnding + " " + newStyle);
		
	}
}
