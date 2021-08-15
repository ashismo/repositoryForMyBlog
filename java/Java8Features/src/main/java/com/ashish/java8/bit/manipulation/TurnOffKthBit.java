package com.ashish.java8.bit.manipulation;

//Problem 1. Turn off k’th bit in a number
//<<&~~ (1 << (k - 1))k'thnn & ~(1 << (k - 1))nk'th
//
//n = 20k = 3
//
//00010100    &                 (n = 20)
//11111011                    ~ (1 << (3-1))
//~~~~~~~~
//00010000
public class TurnOffKthBit {
	public static void main(String[] args) {
		int a = 20;
		int k = 3;
		
		String aStr = Integer.toBinaryString(a);
		System.out.println(a + " binary is = " + aStr);
		
		String leftShift = Integer.toBinaryString((1 << (k-1)));
		System.out.println(" Left shift of 1 is = " + leftShift);
		
		int result = a ^ (1 << (k-1));
		
		System.out.println("Final result = " + result + " binary: " + Integer.toBinaryString(result));
	}
}
