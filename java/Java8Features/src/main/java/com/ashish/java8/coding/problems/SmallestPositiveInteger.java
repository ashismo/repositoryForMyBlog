package com.ashish.java8.coding.problems;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//Write a function:
//
//class Solution { public int solution(int[] A); }
//
//that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
//
//For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
//
//Given A = [1, 2, 3], the function should return 4.
//
//Given A = [−1, −3], the function should return 1.
//
//Write an efficient algorithm for the following assumptions:
//
//N is an integer within the range [1..100,000];
//each element of array A is an integer within the range [−1,000,000..1,000,000].
public class SmallestPositiveInteger {
	public static void main(String[] args) {
//		int A[] = {1,4,2,3,9,2};
//		int A[] = {-1, -3};
		int A[] = {1,2,3};
		int result = solution(A);
		System.out.println("Result: " + result);
	}
	
	public static int solution(int[] A) {
		List<Integer> list = Arrays.stream(A).boxed().collect(Collectors.toList());
		Collections.sort(list);
		System.out.println("Sorted list: " + String.valueOf(list));
		int result = 0;
		for(int i = 0 ; i < list.size() - 1; i++) {
			if(list.get(i+1) - list.get(i) > 1) {
				result = list.get(i) + 1;
				if(result <= 0) {
					return 1;
				}
				return result;
			}
		}
		
		if(result == 0) {
			result = list.get(list.size() - 1) + 1;
			if(result <= 0) { 
				return 1;
			}
			return result;
		}
		
		
		return 1;
	}
}
