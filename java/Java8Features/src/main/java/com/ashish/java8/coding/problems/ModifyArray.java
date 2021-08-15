package com.ashish.java8.coding.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

// Write a function which accepts an integer array and its size and modifies the array in the following manner:
//1) If the elements of index i and (i+1) are equal then, double the value at index i
//and replace the element at index (i+1) with 0. 
//
//2) If the element at index i is 0, then ignore it.
//
//3) Any number (element in an array) must be modified only once.
//
//4) At the end, shift all the zeros (0s) to the right of the array and remaining
//nonzeros to the left of the array.
//
//Example: 
//Input: 2 2 0 4 0 8
//Output: 4 4 8 0 0 0
//
//Input: 2 2 0 4 0 2
//Output: 4 4 2 0 0 0
public class ModifyArray {
	public static void main(String[] args) {
//		Integer[] arr = {2,2,0,4,0,8};
		Integer[] arr = {2,2,0,4,0,2};
		modifyArray(arr, arr.length);
	}
	
	public static void modifyArray(Integer[] arr, int size) {
		List<Integer> list = Arrays.asList(arr);
		System.out.println("Before any sorting" + String.valueOf(list));
		for(int i = 0; i < size - 1; i++) {
			if(arr[i] != 0 && arr[i] == arr[i+1]) {
				arr[i] = arr[i] * 2;
				arr[i+1] = 0;
			}
		}
		list = Arrays.asList(arr);
		
		System.out.println("After 1st sorting: " + String.valueOf(list));
		
		List<Integer> nonZeroList = new ArrayList<>();
		AtomicInteger count = new AtomicInteger();
		count.set(0);
		list.stream().filter(x->{ 
			if(x != 0) {
				return true;
			}
			else {
				count.getAndIncrement();
				return false;
			}
		}).forEach(x -> nonZeroList.add(x));
		
		IntStream.range(0, count.get()).forEach(x-> nonZeroList.add(0));
		System.out.println("Final output: " + String.valueOf(nonZeroList));
	}
}
