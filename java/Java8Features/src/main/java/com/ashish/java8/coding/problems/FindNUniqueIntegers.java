package com.ashish.java8.coding.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

//Given an integer n, return any array containing n unique integers such that they add up to 0.

//Example 1:
//
//Input: n = 5
//Output: [-7,-1,1,3,4]
//Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
//Example 2:
//
//Input: n = 3
//Output: [-1,0,1]
//Example 3:
//
//Input: n = 1
//Output: [0]
public class FindNUniqueIntegers {
	public static void main(String[] args) {
		int[] result = sumZero(5);
		
		int sum = 0;
		for(int i = 0; i < result.length; i++) {
			sum = sum + result[i];
			System.out.print(result[i] + " ");
		}
		
		System.out.println();
		
		System.out.println("Sum = " + sum);
	}
	
	public static int[] sumZero(int n) {
        int leftArraySize = n/2;
        
        int[] finalArr = new int[n];
        boolean isBalanced = true;
        
        if(n % 2 == 1) {
        	isBalanced = false;
        }
        
        // Create set of positive values
        Set<Integer> set = new HashSet<Integer>();
        Random rand = new Random();
        
        int count = 0;
        while(count != leftArraySize) {
        	Integer random = rand.nextInt();
        	if(!set.contains(random)) {
        		set.add(random);
        		finalArr[count++] = random;
        	}
        }
        
        AtomicInteger c = new AtomicInteger();
        c.set(count);
        if(!isBalanced) {
    		finalArr[c.getAndIncrement()] = 0;
    	}
        set.iterator().forEachRemaining(x -> {
        	
        	finalArr[c.getAndIncrement()] = -x;
        });
        
        return finalArr;
    }
}


