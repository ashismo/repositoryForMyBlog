package com.ashish.java8.coding.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//Partition array into N subsets with balanced sum
//Give you one sorted array, please put them into n buckets, we need to ensure we get n sub array with approximately equal weights.
//Example;
//input {1, 2, 3, 4, 5} n = 3
//output [[[5],[1,4],[2,3]];
public class PartionIntoBuckets {
	public static void main(String[] args) {
		int[] arr = {2,5,8,9,10,12,18,20};
		int numberOfBuckets = 3;
		
		List<Integer> arrList = Arrays.stream(arr).boxed().collect(Collectors.toList());
		Collections.sort(arrList, Collections.reverseOrder());
		
		System.out.println("Sorted List: " + arrList);
		List<List<Integer>> finalList = splitAndPopulate(arr, numberOfBuckets);
		
		System.out.println(String.valueOf(finalList));
	}

	private static List<List<Integer>> splitAndPopulate(int[] arr, int numberOfBuckets) {
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum = sum + arr[i];
		}
		int weight = sum/numberOfBuckets;
		
		System.out.println("Approximate weight of each bucket: " + weight);
		
		List<List<Integer>> finalList = new ArrayList<>();
		List<Integer> bucket = new ArrayList<Integer>();
		finalList.add(bucket);
		sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum = sum + arr[i];
			bucket.add(arr[i]);
			if(sum >= weight) {
				sum = 0;
				bucket = new ArrayList<Integer>();
				finalList.add(bucket);
			}
		}
		return finalList;
	}
}
