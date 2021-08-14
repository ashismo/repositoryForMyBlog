package com.ashish.java8.datastructure.sorting;

import java.util.Arrays;

public class MergeSort {
	public static void main(String[] args) {
		
		int[] list = {1,5,2,6,7,8};
		System.out.print("Before sort :");
		System.out.println(Arrays.toString(list));
		
		list = MergeSort.mergeSort(list);
		System.out.print("After sort : ");
		System.out.println(Arrays.toString(list));
	}
	
	public static int[] mergeSort(int[] list) {
		if(list.length <= 1) return list;
		
		// Break the array into 2 arrays
		int midPoint = list.length / 2;
		int[] left = new int[midPoint];
		int[] right = new int[list.length - midPoint];
		
		// Populate the left and right arrays.
		for(int i=0; i < midPoint; i++) {
			
			left[i] = list[i];
			
		}
		
		for(int j=0; j < right.length; j++) {
			
			right[j] = list[midPoint + j];
			
		}
		
		left = mergeSort(left);
		right = mergeSort(right);
		
		int[] result = new int[list.length];
		
		result = merge(left, right);
		
		return result;
	}
	
	public static int[] merge(int[] left, int[] right) {
		// Merged result array.
		int[] result = new int[left.length + right.length];
		
		// Declare and initialize pointers for all arrays.
		int leftPointer = 0;
		int rightPointer = 0;
		int resultPointer = 0;
		
		// While the items are there either of the arrays
		while(leftPointer < left.length || rightPointer < right.length) {
			// Items are present in both the arrays
			if(leftPointer < left.length && rightPointer < right.length) {

				// If left item is less than right item...
				if(left[leftPointer] < right[rightPointer]) {
					result[resultPointer++] = left[leftPointer++];
				} else {
					result[resultPointer++] = right[rightPointer++];
				} 
			} 
			// If there are only items in the left array...
			else if(leftPointer < left.length) {
				
				result[resultPointer++] = left[leftPointer++];
				
			}
			// If there are only items in the right array...
			else if(rightPointer < right.length) {
				result[resultPointer++] = right[rightPointer++];
			}
		}
		return result;
	}
}
