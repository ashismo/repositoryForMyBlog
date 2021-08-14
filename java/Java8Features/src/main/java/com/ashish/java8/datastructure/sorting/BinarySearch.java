package com.ashish.java8.datastructure.sorting;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class BinarySearch {
	public static void main(String[] args) {
		Integer[] array = {2,5,1,7,9,4,12,100,91,13,14};
		
		int itemToSearch = 100;
		// In binary sort, the array must be sorted
		Arrays.sort(array);
		System.out.println("Sorted the array: " + Arrays.toString(array));
		
		boolean isFound = binarySearch(array, itemToSearch);
		if(isFound) {
			System.out.println("Item found: " + itemToSearch);
		} else {
			System.out.println("Item not found");
		}
	}
	
	public static boolean binarySearch(Integer[] array, int itemToSearch) {
		// Termination condition in recursion
		// Is this the last element in an array. If yes, then find if the element matches with the item to be searched
		if(array.length == 1) {
			
			if(array[0] == null) {
				return false;
			} else if(array[0] == itemToSearch) {
				return true;
			} else {
				return false;
			}
		}
		
		int midIndex = array.length / 2;
		if(array.length % 2 != 0) midIndex = midIndex + 1;
		
		Integer[] result = new Integer[midIndex];
		// Check if the midElement is larger than the itemToBeSearched
		if(array[midIndex - 1] > itemToSearch) {
			// Search left half of the list
			IntStream.range(0,midIndex).forEach(x-> result[x] = array[x]);
		} else if(array[midIndex - 1] < itemToSearch) {
			// Search right half of the list
			AtomicInteger i = new AtomicInteger();
			IntStream.range(midIndex, array.length).forEach(x-> result[i.getAndIncrement()] = array[x]);
		} else {
			// Item found in the midIndex
			return true;
		}
		
		
		System.out.println("Divided the array: " + Arrays.toString(result));
		
		boolean isFound = binarySearch(result, itemToSearch);
		
		return isFound;
	}
}
