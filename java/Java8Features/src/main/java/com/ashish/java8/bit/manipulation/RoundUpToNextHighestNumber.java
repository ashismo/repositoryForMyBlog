package com.ashish.java8.bit.manipulation;

// Round up to the next highest power of 2
// Given a number n, find the next highest power of 2. If n itself is a power of 2, return n
//Input:  n = 20
//Output: 32
// 
//Input:  n = 16
//Output: 16
public class RoundUpToNextHighestNumber {
	public static void main(String[] args) {
		int a = 127;
		String aStr = Integer.toBinaryString(a);
		int count = 0;
		for(int i = 0 ; i < aStr.length(); i++) {
			if(aStr.charAt(i) == '1') {
				count++;
			}
		}
		
		if(count == 1) {
			System.out.println("Value a=" + a + " is power of two");
		} else {
			int bitIndex = aStr.length() - aStr.indexOf("1");
			System.out.println("First 1 is in bitIndex: " + bitIndex);
			
			int result = (1 << bitIndex);
			System.out.println("Next highest power of 2 for " + a + " is = " + result + " Binary = " + Integer.toBinaryString( result ));
		}
	}
}
