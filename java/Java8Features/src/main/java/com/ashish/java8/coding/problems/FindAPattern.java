package com.ashish.java8.coding.problems;

import java.util.ArrayList;
import java.util.List;

//Given a number n, print following a pattern without using any loop.
//
//Examples : 
//
//Input: n = 16
//Output: 16, 11, 6, 1, -4, 1, 6, 11, 16
//
//Input: n = 10
//Output: 10, 5, 0, 5, 10
public class FindAPattern {
	static List<Integer> list = new ArrayList<Integer>();
	static boolean isDownCompleted = false;
	
	public static void main(String[] args) {
		findNextVal(10, 10);
		System.out.println(list);
	}
	
	public static int findNextVal(int n, int limit) {
		
		System.out.println("Val = " + n);
		
		if( n <= -5  ) {
			isDownCompleted = true;
			return n + 5;
		}
		
		if( n > limit  ) {
			isDownCompleted = false;
			return limit;
		}
		
		list.add(n);
		int val = 0;
		if(!isDownCompleted) {
			
			val = findNextVal(n - 5, limit);
			
		} 
		
		if(isDownCompleted) {
			val = findNextVal(n + 5, limit);
		}
		return val;
		
	}
}
